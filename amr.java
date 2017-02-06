public enum amr
{
  private final String d;
  private static String[] e;
  
  static
  {
    e = new String[values().length];
    
    int ☃ = 0;
    for (amr ☃ : values()) {
      e[(☃++)] = ☃.d;
    }
  }
  
  private amr(String ☃)
  {
    this.d = ☃;
  }
  
  public int a(int ☃, int ☃)
  {
    int ☃ = ☃ / 2;
    int ☃ = ☃ > ☃ ? ☃ - ☃ : ☃;
    switch (amr.1.a[ordinal()])
    {
    case 1: 
      return (☃ - ☃) % ☃;
    case 2: 
      return (☃ - ☃ + ☃) % ☃;
    }
    return ☃;
  }
  
  public aoe a(cq ☃)
  {
    cq.a ☃ = ☃.k();
    return ((this == b) && (☃ == cq.a.c)) || ((this == c) && (☃ == cq.a.a)) ? aoe.c : aoe.a;
  }
  
  public cq b(cq ☃)
  {
    switch (amr.1.a[ordinal()])
    {
    case 1: 
      if (☃ == cq.e) {
        return cq.f;
      }
      if (☃ == cq.f) {
        return cq.e;
      }
      return ☃;
    case 2: 
      if (☃ == cq.c) {
        return cq.d;
      }
      if (☃ == cq.d) {
        return cq.c;
      }
      return ☃;
    }
    return ☃;
  }
}
