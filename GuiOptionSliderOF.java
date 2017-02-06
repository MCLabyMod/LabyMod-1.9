public class GuiOptionSliderOF
  extends bdr
  implements IOptionControl
{
  private bch.a option = null;
  
  public GuiOptionSliderOF(int id, int x, int y, bch.a option)
  {
    super(id, x, y, option);
    this.option = option;
  }
  
  public bch.a getOption()
  {
    return this.option;
  }
}
