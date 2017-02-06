public class bga
  extends bft
{
  private static final kk u = new kk("textures/gui/container/crafting_table.png");
  
  public bga(zi ☃, aht ☃)
  {
    this(☃, ☃, cj.a);
  }
  
  public bga(zi ☃, aht ☃, cj ☃)
  {
    super(new abd(☃, ☃, ☃));
  }
  
  protected void b(int ☃, int ☃)
  {
    this.q.a(bwo.a("container.crafting", new Object[0]), 28, 6, 4210752);
    this.q.a(bwo.a("container.inventory", new Object[0]), 8, this.g - 96 + 2, 4210752);
  }
  
  protected void a(float ☃, int ☃, int ☃)
  {
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.j.N().a(u);
    int ☃ = (this.l - this.f) / 2;
    int ☃ = (this.m - this.g) / 2;
    b(☃, ☃, 0, 0, this.f, this.g);
  }
}
