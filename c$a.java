class c$a
{
  private final String a;
  private final String b;
  
  public c$a(String ☃, Object ☃)
  {
    this.a = ☃;
    if (☃ == null)
    {
      this.b = "~~NULL~~";
    }
    else if ((☃ instanceof Throwable))
    {
      Throwable ☃ = (Throwable)☃;
      this.b = ("~~ERROR~~ " + ☃.getClass().getSimpleName() + ": " + ☃.getMessage());
    }
    else
    {
      this.b = ☃.toString();
    }
  }
  
  public String a()
  {
    return this.a;
  }
  
  public String b()
  {
    return this.b;
  }
}
