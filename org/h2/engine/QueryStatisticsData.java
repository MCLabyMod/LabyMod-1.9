package org.h2.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class QueryStatisticsData
{
  private static final int MAX_QUERY_ENTRIES = 100;
  private static final Comparator<QueryEntry> QUERY_ENTRY_COMPARATOR = new Comparator()
  {
    public int compare(QueryStatisticsData.QueryEntry o1, QueryStatisticsData.QueryEntry o2)
    {
      return (int)Math.signum((float)(o1.lastUpdateTime - o2.lastUpdateTime));
    }
  };
  private final HashMap<String, QueryEntry> map;
  
  public QueryStatisticsData()
  {
    this.map = new HashMap();
  }
  
  public synchronized List<QueryEntry> getQueries()
  {
    ArrayList<QueryEntry> list = new ArrayList();
    list.addAll(this.map.values());
    
    Collections.sort(list, QUERY_ENTRY_COMPARATOR);
    return list.subList(0, Math.min(list.size(), 100));
  }
  
  public synchronized void update(String sqlStatement, long executionTime, int rowCount)
  {
    QueryEntry entry = (QueryEntry)this.map.get(sqlStatement);
    if (entry == null)
    {
      entry = new QueryEntry();
      entry.sqlStatement = sqlStatement;
      this.map.put(sqlStatement, entry);
    }
    entry.update(executionTime, rowCount);
    if (this.map.size() > 150.0F)
    {
      ArrayList<QueryEntry> list = new ArrayList();
      list.addAll(this.map.values());
      Collections.sort(list, QUERY_ENTRY_COMPARATOR);
      
      HashSet<QueryEntry> oldestSet = new HashSet(list.subList(0, list.size() / 3));
      
      Iterator<Map.Entry<String, QueryEntry>> it = this.map.entrySet().iterator();
      while (it.hasNext())
      {
        Map.Entry<String, QueryEntry> mapEntry = (Map.Entry)it.next();
        if (oldestSet.contains(mapEntry.getValue())) {
          it.remove();
        }
      }
    }
  }
  
  public static final class QueryEntry
  {
    public String sqlStatement;
    public int count;
    public long lastUpdateTime;
    public long executionTimeMin;
    public long executionTimeMax;
    public long executionTimeCumulative;
    public int rowCountMin;
    public int rowCountMax;
    public long rowCountCumulative;
    public double executionTimeMean;
    public double rowCountMean;
    private double executionTimeM2;
    private double rowCountM2;
    
    void update(long time, int rows)
    {
      this.count += 1;
      this.executionTimeMin = Math.min(time, this.executionTimeMin);
      this.executionTimeMax = Math.max(time, this.executionTimeMax);
      this.rowCountMin = Math.min(rows, this.rowCountMin);
      this.rowCountMax = Math.max(rows, this.rowCountMax);
      
      double delta = rows - this.rowCountMean;
      this.rowCountMean += delta / this.count;
      this.rowCountM2 += delta * (rows - this.rowCountMean);
      
      delta = time - this.executionTimeMean;
      this.executionTimeMean += delta / this.count;
      this.executionTimeM2 += delta * (time - this.executionTimeMean);
      
      this.executionTimeCumulative += time;
      this.rowCountCumulative += rows;
      this.lastUpdateTime = System.currentTimeMillis();
    }
    
    public double getExecutionTimeStandardDeviation()
    {
      return Math.sqrt(this.executionTimeM2 / this.count);
    }
    
    public double getRowCountStandardDeviation()
    {
      return Math.sqrt(this.rowCountM2 / this.count);
    }
  }
}
