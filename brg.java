public class brg
  extends brn<rr>
{
  public brg(brm renderManagerIn)
  {
    super(renderManagerIn);
  }
  
  public void a(rr entity, double x, double y, double z, float entityYaw, float partialTicks)
  {
    bni.G();
    a(entity.bl(), x - entity.M, y - entity.N, z - entity.O);
    bni.H();
    super.a(entity, x, y, z, entityYaw, partialTicks);
  }
  
  protected kk a(rr entity)
  {
    return null;
  }
}
