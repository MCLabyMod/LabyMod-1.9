public class GuiOptionButtonOF
  extends bdm
  implements IOptionControl
{
  private bch.a option = null;
  
  public GuiOptionButtonOF(int id, int x, int y, bch.a option, String text)
  {
    super(id, x, y, option, text);
    this.option = option;
  }
  
  public bch.a getOption()
  {
    return this.option;
  }
}
