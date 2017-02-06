import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

public class bqi
{
  private static final int a = (int)Math.pow(16.0D, 0.0D);
  private static final int b = (int)Math.pow(16.0D, 1.0D);
  private static final int c = (int)Math.pow(16.0D, 2.0D);
  private final BitSet d = new BitSet(4096);
  private static final int[] e = new int['Ո'];
  private int f = 4096;
  
  public void a(cj pos)
  {
    this.d.set(c(pos), true);
    this.f -= 1;
  }
  
  private static int c(cj pos)
  {
    return a(pos.p() & 0xF, pos.q() & 0xF, pos.r() & 0xF);
  }
  
  private static int a(int x, int y, int z)
  {
    return x << 0 | y << 8 | z << 4;
  }
  
  public bqj a()
  {
    bqj setvisibility = new bqj();
    if (4096 - this.f < 256) {
      setvisibility.a(true);
    } else if (this.f == 0) {
      setvisibility.a(false);
    } else {
      for (int i : e) {
        if (!this.d.get(i)) {
          setvisibility.a(a(i));
        }
      }
    }
    return setvisibility;
  }
  
  public Set<cq> b(cj pos)
  {
    return a(c(pos));
  }
  
  private Set<cq> a(int p_178604_1_)
  {
    Set<cq> set = EnumSet.noneOf(cq.class);
    
    ArrayDeque queue = new ArrayDeque(384);
    queue.add(og.a(p_178604_1_));
    this.d.set(p_178604_1_, true);
    while (!queue.isEmpty())
    {
      int i = ((Integer)queue.poll()).intValue();
      a(i, set);
      for (cq enumfacing : cq.n)
      {
        int j = a(i, enumfacing);
        if ((j >= 0) && (!this.d.get(j)))
        {
          this.d.set(j, true);
          queue.add(og.a(j));
        }
      }
    }
    return set;
  }
  
  private void a(int p_178610_1_, Set<cq> p_178610_2_)
  {
    int i = p_178610_1_ >> 0 & 0xF;
    if (i == 0) {
      p_178610_2_.add(cq.e);
    } else if (i == 15) {
      p_178610_2_.add(cq.f);
    }
    int j = p_178610_1_ >> 8 & 0xF;
    if (j == 0) {
      p_178610_2_.add(cq.a);
    } else if (j == 15) {
      p_178610_2_.add(cq.b);
    }
    int k = p_178610_1_ >> 4 & 0xF;
    if (k == 0) {
      p_178610_2_.add(cq.c);
    } else if (k == 15) {
      p_178610_2_.add(cq.d);
    }
  }
  
  private int a(int p_178603_1_, cq p_178603_2_)
  {
    switch (p_178603_2_)
    {
    case a: 
      if ((p_178603_1_ >> 8 & 0xF) == 0) {
        return -1;
      }
      return p_178603_1_ - c;
    case b: 
      if ((p_178603_1_ >> 8 & 0xF) == 15) {
        return -1;
      }
      return p_178603_1_ + c;
    case c: 
      if ((p_178603_1_ >> 4 & 0xF) == 0) {
        return -1;
      }
      return p_178603_1_ - b;
    case d: 
      if ((p_178603_1_ >> 4 & 0xF) == 15) {
        return -1;
      }
      return p_178603_1_ + b;
    case e: 
      if ((p_178603_1_ >> 0 & 0xF) == 0) {
        return -1;
      }
      return p_178603_1_ - a;
    case f: 
      if ((p_178603_1_ >> 0 & 0xF) == 15) {
        return -1;
      }
      return p_178603_1_ + a;
    }
    return -1;
  }
  
  static
  {
    int i = 0;
    int j = 15;
    int k = 0;
    for (int l = 0; l < 16; l++) {
      for (int i1 = 0; i1 < 16; i1++) {
        for (int j1 = 0; j1 < 16; j1++) {
          if ((l == 0) || (l == 15) || (i1 == 0) || (i1 == 15) || (j1 == 0) || (j1 == 15)) {
            e[(k++)] = a(l, i1, j1);
          }
        }
      }
    }
  }
}
