public enum aoe
{
  private final String e;
  private static String[] f;
  
  static
  {
    f = new String[values().length];
    
    int ☃ = 0;
    for (aoe ☃ : values()) {
      f[(☃++)] = ☃.e;
    }
  }
  
  private aoe(String ☃)
  {
    this.e = ☃;
  }
  
  public aoe a(aoe ☃)
  {
    switch (aoe.1.a[☃.ordinal()])
    {
    case 3: 
      switch (aoe.1.a[ordinal()])
      {
      case 1: 
        return c;
      case 2: 
        return d;
      case 3: 
        return a;
      case 4: 
        return b;
      }
    case 4: 
      switch (aoe.1.a[ordinal()])
      {
      case 1: 
        return d;
      case 2: 
        return a;
      case 3: 
        return b;
      case 4: 
        return c;
      }
    case 2: 
      switch (aoe.1.a[ordinal()])
      {
      case 1: 
        return b;
      case 2: 
        return c;
      case 3: 
        return d;
      case 4: 
        return a;
      }
      break;
    }
    return this;
  }
  
  public cq a(cq ☃)
  {
    if (☃.k() == cq.a.b) {
      return ☃;
    }
    switch (aoe.1.a[ordinal()])
    {
    case 3: 
      return ☃.d();
    case 4: 
      return ☃.f();
    case 2: 
      return ☃.e();
    }
    return ☃;
  }
  
  public int a(int ☃, int ☃)
  {
    switch (aoe.1.a[ordinal()])
    {
    case 3: 
      return (☃ + ☃ / 2) % ☃;
    case 4: 
      return (☃ + ☃ * 3 / 4) % ☃;
    case 2: 
      return (☃ + ☃ / 4) % ☃;
    }
    return ☃;
  }
}
