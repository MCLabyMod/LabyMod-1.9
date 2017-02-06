public class aid
  extends ov.a
{
  private final dn b;
  
  public aid()
  {
    super(1);
    
    this.b = new dn();
    this.b.a("id", "Pig");
  }
  
  public aid(dn ☃)
  {
    this(☃.b("Weight", 99) ? ☃.h("Weight") : 1, ☃.o("Entity"));
  }
  
  public aid(int ☃, dn ☃)
  {
    super(☃);
    
    this.b = ☃;
  }
  
  public dn a()
  {
    dn ☃ = new dn();
    
    ☃.a("Entity", this.b);
    ☃.a("Weight", this.a);
    
    return ☃;
  }
  
  public dn b()
  {
    return this.b;
  }
}
