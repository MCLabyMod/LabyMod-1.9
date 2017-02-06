public class PlayerItemRenderer
{
  private int attachTo = 0;
  private float scaleFactor = 0.0F;
  private bkm modelRenderer = null;
  
  public PlayerItemRenderer(int attachTo, float scaleFactor, bkm modelRenderer)
  {
    this.attachTo = attachTo;
    this.scaleFactor = scaleFactor;
    this.modelRenderer = modelRenderer;
  }
  
  public bkm getModelRenderer()
  {
    return this.modelRenderer;
  }
  
  public void render(bix modelBiped, float scale)
  {
    bkm attachModel = PlayerItemModel.getAttachModel(modelBiped, this.attachTo);
    if (attachModel != null) {
      attachModel.c(scale);
    }
    this.modelRenderer.a(scale * this.scaleFactor);
  }
}
