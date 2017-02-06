import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class bum
{
  private static bum a = null;
  private final int b;
  private final int c;
  private final int d;
  private final int e;
  private final int f;
  private final boolean g;
  private final boolean h;
  
  private bum(boolean ☃, boolean ☃, int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.g = ☃;
    
    this.b = ☃;
    this.d = ☃;
    
    this.c = ☃;
    this.e = ☃;
    
    this.h = ☃;
    this.f = ☃;
  }
  
  public bum()
  {
    this(false, true, 1, 0, 1, 0, 32774);
  }
  
  public bum(int ☃, int ☃, int ☃)
  {
    this(false, false, ☃, ☃, ☃, ☃, ☃);
  }
  
  public bum(int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this(true, false, ☃, ☃, ☃, ☃, ☃);
  }
  
  public void a()
  {
    if (equals(a)) {
      return;
    }
    if ((a == null) || (this.h != a.b()))
    {
      a = this;
      if (this.h)
      {
        bni.l();
        return;
      }
      bni.m();
    }
    bni.d(this.f);
    if (this.g) {
      bni.a(this.b, this.d, this.c, this.e);
    } else {
      bni.b(this.b, this.d);
    }
  }
  
  public boolean equals(Object ☃)
  {
    if (this == ☃) {
      return true;
    }
    if (!(☃ instanceof bum)) {
      return false;
    }
    bum ☃ = (bum)☃;
    if (this.f != ☃.f) {
      return false;
    }
    if (this.e != ☃.e) {
      return false;
    }
    if (this.d != ☃.d) {
      return false;
    }
    if (this.h != ☃.h) {
      return false;
    }
    if (this.g != ☃.g) {
      return false;
    }
    if (this.c != ☃.c) {
      return false;
    }
    if (this.b != ☃.b) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int ☃ = this.b;
    ☃ = 31 * ☃ + this.c;
    ☃ = 31 * ☃ + this.d;
    ☃ = 31 * ☃ + this.e;
    ☃ = 31 * ☃ + this.f;
    ☃ = 31 * ☃ + (this.g ? 1 : 0);
    ☃ = 31 * ☃ + (this.h ? 1 : 0);
    return ☃;
  }
  
  public boolean b()
  {
    return this.h;
  }
  
  public static bum a(JsonObject ☃)
  {
    if (☃ == null) {
      return new bum();
    }
    int ☃ = 32774;
    int ☃ = 1;
    int ☃ = 0;
    int ☃ = 1;
    int ☃ = 0;
    boolean ☃ = true;
    boolean ☃ = false;
    if (od.a(☃, "func"))
    {
      ☃ = a(☃.get("func").getAsString());
      if (☃ != 32774) {
        ☃ = false;
      }
    }
    if (od.a(☃, "srcrgb"))
    {
      ☃ = b(☃.get("srcrgb").getAsString());
      if (☃ != 1) {
        ☃ = false;
      }
    }
    if (od.a(☃, "dstrgb"))
    {
      ☃ = b(☃.get("dstrgb").getAsString());
      if (☃ != 0) {
        ☃ = false;
      }
    }
    if (od.a(☃, "srcalpha"))
    {
      ☃ = b(☃.get("srcalpha").getAsString());
      if (☃ != 1) {
        ☃ = false;
      }
      ☃ = true;
    }
    if (od.a(☃, "dstalpha"))
    {
      ☃ = b(☃.get("dstalpha").getAsString());
      if (☃ != 0) {
        ☃ = false;
      }
      ☃ = true;
    }
    if (☃) {
      return new bum();
    }
    if (☃) {
      return new bum(☃, ☃, ☃, ☃, ☃);
    }
    return new bum(☃, ☃, ☃);
  }
  
  private static int a(String ☃)
  {
    String ☃ = ☃.trim().toLowerCase();
    if (☃.equals("add")) {
      return 32774;
    }
    if (☃.equals("subtract")) {
      return 32778;
    }
    if (☃.equals("reversesubtract")) {
      return 32779;
    }
    if (☃.equals("reverse_subtract")) {
      return 32779;
    }
    if (☃.equals("min")) {
      return 32775;
    }
    if (☃.equals("max")) {
      return 32776;
    }
    return 32774;
  }
  
  private static int b(String ☃)
  {
    String ☃ = ☃.trim().toLowerCase();
    ☃ = ☃.replaceAll("_", "");
    ☃ = ☃.replaceAll("one", "1");
    ☃ = ☃.replaceAll("zero", "0");
    ☃ = ☃.replaceAll("minus", "-");
    if (☃.equals("0")) {
      return 0;
    }
    if (☃.equals("1")) {
      return 1;
    }
    if (☃.equals("srccolor")) {
      return 768;
    }
    if (☃.equals("1-srccolor")) {
      return 769;
    }
    if (☃.equals("dstcolor")) {
      return 774;
    }
    if (☃.equals("1-dstcolor")) {
      return 775;
    }
    if (☃.equals("srcalpha")) {
      return 770;
    }
    if (☃.equals("1-srcalpha")) {
      return 771;
    }
    if (☃.equals("dstalpha")) {
      return 772;
    }
    if (☃.equals("1-dstalpha")) {
      return 773;
    }
    return -1;
  }
}
