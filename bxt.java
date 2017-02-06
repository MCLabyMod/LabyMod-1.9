import org.apache.commons.lang3.StringUtils;

public class bxt
  extends kk
{
  private final String c;
  
  protected bxt(int ☃, String... ☃)
  {
    super(0, new String[] { ☃[0], ☃[1] });
    
    this.c = (StringUtils.isEmpty(☃[2]) ? "normal" : ☃[2].toLowerCase());
  }
  
  public bxt(String ☃)
  {
    this(0, b(☃));
  }
  
  public bxt(kk ☃, String ☃)
  {
    this(☃.toString(), ☃);
  }
  
  public bxt(String ☃, String ☃)
  {
    this(0, b(☃ + '#' + (☃ == null ? "normal" : ☃)));
  }
  
  protected static String[] b(String ☃)
  {
    String[] ☃ = { null, ☃, null };
    
    int ☃ = ☃.indexOf('#');
    String ☃ = ☃;
    if (☃ >= 0)
    {
      ☃[2] = ☃.substring(☃ + 1, ☃.length());
      if (☃ > 1) {
        ☃ = ☃.substring(0, ☃);
      }
    }
    System.arraycopy(kk.a(☃), 0, ☃, 0, 2);
    
    return ☃;
  }
  
  public String c()
  {
    return this.c;
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (((☃ instanceof bxt)) && 
      (super.equals(☃)))
    {
      bxt ☃ = (bxt)☃;
      
      return this.c.equals(☃.c);
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * super.hashCode() + this.c.hashCode();
  }
  
  public String toString()
  {
    return super.toString() + '#' + this.c;
  }
}
