public class aco
  extends ado
{
  public aco()
  {
    a(new kk("angle"), new adr()
    {
      double a;
      double b;
      long c;
      
      public float a(adq ☃, aht ☃, sa ☃)
      {
        if ((☃ == null) && (!☃.y())) {
          return 0.0F;
        }
        boolean ☃ = ☃ != null;
        rr ☃ = ☃ ? ☃ : ☃.z();
        if (☃ == null) {
          ☃ = ☃.l;
        }
        double ☃;
        double ☃;
        if (☃.s.d())
        {
          double ☃ = ☃ ? ☃.v : a((xs)☃);
          ☃ %= 360.0D;
          double ☃ = a(☃, ☃);
          
          ☃ = 3.1415927410125732D - ((☃ - 90.0D) * 0.01745329238474369D - ☃);
        }
        else
        {
          ☃ = Math.random() * 6.2831854820251465D;
        }
        if (☃) {
          ☃ = a(☃, ☃);
        }
        float ☃ = (float)(☃ / 6.2831854820251465D);
        return on.b(☃, 1.0F);
      }
      
      private double a(aht ☃, double ☃)
      {
        if (☃.P() != this.c)
        {
          this.c = ☃.P();
          
          double ☃ = ☃ - this.a;
          ☃ %= 6.2831854820251465D;
          ☃ = on.a(☃, -1.0D, 1.0D);
          
          this.b += ☃ * 0.1D;
          this.b *= 0.8D;
          this.a += this.b;
        }
        return this.a;
      }
      
      private double a(xs ☃)
      {
        return on.b(180 + ☃.b.b() * 90);
      }
      
      private double a(aht ☃, rr ☃)
      {
        cj ☃ = ☃.R();
        return Math.atan2(☃.r() - ☃.r, ☃.p() - ☃.p);
      }
    });
  }
}
