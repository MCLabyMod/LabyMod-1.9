import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class eb
{
  public static final String[] a = { "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]" };
  
  abstract void a(DataOutput paramDataOutput)
    throws IOException;
  
  abstract void a(DataInput paramDataInput, int paramInt, dw paramdw)
    throws IOException;
  
  public abstract String toString();
  
  public abstract byte a();
  
  protected static eb a(byte ☃)
  {
    switch (☃)
    {
    case 0: 
      return new dq();
    case 1: 
      return new dm();
    case 2: 
      return new dz();
    case 3: 
      return new dt();
    case 4: 
      return new dv();
    case 5: 
      return new dr();
    case 6: 
      return new dp();
    case 7: 
      return new dl();
    case 11: 
      return new ds();
    case 8: 
      return new ea();
    case 9: 
      return new du();
    case 10: 
      return new dn();
    }
    return null;
  }
  
  public abstract eb b();
  
  public boolean c_()
  {
    return false;
  }
  
  public boolean equals(Object ☃)
  {
    if (!(☃ instanceof eb)) {
      return false;
    }
    eb ☃ = (eb)☃;
    if (a() != ☃.a()) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    return a();
  }
  
  protected String a_()
  {
    return toString();
  }
  
  public static abstract class a
    extends eb
  {
    public abstract long c();
    
    public abstract int d();
    
    public abstract short e();
    
    public abstract byte f();
    
    public abstract double g();
    
    public abstract float h();
  }
}
