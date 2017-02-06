package org.h2.command.dml;

import java.util.Random;
import org.h2.engine.Session;
import org.h2.expression.Expression;
import org.h2.table.Plan;
import org.h2.table.PlanItem;
import org.h2.table.TableFilter;
import org.h2.util.BitField;
import org.h2.util.Permutations;

class Optimizer
{
  private static final int MAX_BRUTE_FORCE_FILTERS = 7;
  private static final int MAX_BRUTE_FORCE = 2000;
  private static final int MAX_GENETIC = 500;
  private long start;
  private BitField switched;
  private final TableFilter[] filters;
  private final Expression condition;
  private final Session session;
  private Plan bestPlan;
  private TableFilter topFilter;
  private double cost;
  private Random random;
  
  Optimizer(TableFilter[] filters, Expression condition, Session session)
  {
    this.filters = filters;
    this.condition = condition;
    this.session = session;
  }
  
  private static int getMaxBruteForceFilters(int filterCount)
  {
    int i = 0;int j = filterCount;int total = filterCount;
    while ((j > 0) && (total * (j * (j - 1) / 2) < 2000))
    {
      j--;
      total *= j;
      i++;
    }
    return i;
  }
  
  private void calculateBestPlan()
  {
    this.start = System.currentTimeMillis();
    this.cost = -1.0D;
    if (this.filters.length == 1)
    {
      testPlan(this.filters);
    }
    else if (this.filters.length <= 7)
    {
      calculateBruteForceAll();
    }
    else
    {
      calculateBruteForceSome();
      this.random = new Random(0L);
      calculateGenetic();
    }
  }
  
  private boolean canStop(int x)
  {
    if ((x & 0x7F) == 0)
    {
      long t = System.currentTimeMillis() - this.start;
      if ((this.cost >= 0.0D) && (10L * t > this.cost)) {
        return true;
      }
    }
    return false;
  }
  
  private void calculateBruteForceAll()
  {
    TableFilter[] list = new TableFilter[this.filters.length];
    Permutations<TableFilter> p = Permutations.create(this.filters, list);
    for (int x = 0; (!canStop(x)) && (p.next()); x++) {
      testPlan(list);
    }
  }
  
  private void calculateBruteForceSome()
  {
    int bruteForce = getMaxBruteForceFilters(this.filters.length);
    TableFilter[] list = new TableFilter[this.filters.length];
    Permutations<TableFilter> p = Permutations.create(this.filters, list, bruteForce);
    for (int x = 0; (!canStop(x)) && (p.next()); x++)
    {
      for (TableFilter f : this.filters) {
        f.setUsed(false);
      }
      for (int i = 0; i < bruteForce; i++) {
        list[i].setUsed(true);
      }
      for (int i = bruteForce; i < this.filters.length; i++)
      {
        double costPart = -1.0D;
        int bestPart = -1;
        for (int j = 0; j < this.filters.length; j++) {
          if (!this.filters[j].isUsed())
          {
            if (i == this.filters.length - 1)
            {
              bestPart = j;
              break;
            }
            list[i] = this.filters[j];
            Plan part = new Plan(list, i + 1, this.condition);
            double costNow = part.calculateCost(this.session);
            if ((costPart < 0.0D) || (costNow < costPart))
            {
              costPart = costNow;
              bestPart = j;
            }
          }
        }
        this.filters[bestPart].setUsed(true);
        list[i] = this.filters[bestPart];
      }
      testPlan(list);
    }
  }
  
  private void calculateGenetic()
  {
    TableFilter[] best = new TableFilter[this.filters.length];
    TableFilter[] list = new TableFilter[this.filters.length];
    for (int x = 0; x < 500; x++)
    {
      if (canStop(x)) {
        break;
      }
      boolean generateRandom = (x & 0x7F) == 0;
      if (!generateRandom)
      {
        System.arraycopy(best, 0, list, 0, this.filters.length);
        if (!shuffleTwo(list)) {
          generateRandom = true;
        }
      }
      if (generateRandom)
      {
        this.switched = new BitField();
        System.arraycopy(this.filters, 0, best, 0, this.filters.length);
        shuffleAll(best);
        System.arraycopy(best, 0, list, 0, this.filters.length);
      }
      if (testPlan(list))
      {
        this.switched = new BitField();
        System.arraycopy(list, 0, best, 0, this.filters.length);
      }
    }
  }
  
  private boolean testPlan(TableFilter[] list)
  {
    Plan p = new Plan(list, list.length, this.condition);
    double costNow = p.calculateCost(this.session);
    if ((this.cost < 0.0D) || (costNow < this.cost))
    {
      this.cost = costNow;
      this.bestPlan = p;
      return true;
    }
    return false;
  }
  
  private void shuffleAll(TableFilter[] f)
  {
    for (int i = 0; i < f.length - 1; i++)
    {
      int j = i + this.random.nextInt(f.length - i);
      if (j != i)
      {
        TableFilter temp = f[i];
        f[i] = f[j];
        f[j] = temp;
      }
    }
  }
  
  private boolean shuffleTwo(TableFilter[] f)
  {
    int a = 0;int b = 0;
    for (int i = 0; i < 20; i++)
    {
      a = this.random.nextInt(f.length);
      b = this.random.nextInt(f.length);
      if (a != b)
      {
        if (a < b)
        {
          int temp = a;
          a = b;
          b = temp;
        }
        int s = a * f.length + b;
        if (!this.switched.get(s))
        {
          this.switched.set(s);
          break;
        }
      }
    }
    if (i == 20) {
      return false;
    }
    TableFilter temp = f[a];
    f[a] = f[b];
    f[b] = temp;
    return true;
  }
  
  void optimize()
  {
    calculateBestPlan();
    this.bestPlan.removeUnusableIndexConditions();
    TableFilter[] f2 = this.bestPlan.getFilters();
    this.topFilter = f2[0];
    for (int i = 0; i < f2.length - 1; i++) {
      f2[i].addJoin(f2[(i + 1)], false, false, null);
    }
    for (TableFilter f : f2)
    {
      PlanItem item = this.bestPlan.getItem(f);
      f.setPlanItem(item);
    }
  }
  
  public TableFilter getTopFilter()
  {
    return this.topFilter;
  }
  
  double getCost()
  {
    return this.cost;
  }
}
