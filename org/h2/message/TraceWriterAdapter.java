package org.h2.message;

public class TraceWriterAdapter
  implements TraceWriter
{
  private String name;
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public boolean isEnabled(int level)
  {
    switch (level)
    {
    }
    return false;
  }
  
  public void write(int level, String module, String s, Throwable t)
  {
    if (isEnabled(level))
    {
      if (this.name != null) {
        s = this.name + ":" + module + " " + s;
      } else {
        s = module + " " + s;
      }
      switch (level)
      {
      case 3: 
        break;
      case 2: 
        break;
      case 1: 
        break;
      }
    }
  }
}
