package shadersmod.client;

import bdq;
import java.util.ArrayList;

class GuiSlotShaders
  extends bdq
{
  private ArrayList shaderslist;
  private int selectedIndex;
  private long lastClickedCached = 0L;
  final GuiShaders shadersGui;
  
  public GuiSlotShaders(GuiShaders par1GuiShaders, int width, int height, int top, int bottom, int slotHeight)
  {
    super(par1GuiShaders.getMc(), width, height, top, bottom, slotHeight);
    this.shadersGui = par1GuiShaders;
    updateList();
    
    this.n = 0.0F;
    int posYSelected = this.selectedIndex * slotHeight;
    int wMid = (bottom - top) / 2;
    if (posYSelected > wMid) {
      h(posYSelected - wMid);
    }
  }
  
  public int c()
  {
    return this.b - 20;
  }
  
  public void updateList()
  {
    this.shaderslist = Shaders.listOfShaders();
    this.selectedIndex = 0;
    int i = 0;
    for (int n = this.shaderslist.size(); i < n; i++) {
      if (((String)this.shaderslist.get(i)).equals(Shaders.currentshadername))
      {
        this.selectedIndex = i;
        break;
      }
    }
  }
  
  protected int b()
  {
    return this.shaderslist.size();
  }
  
  protected void a(int index, boolean doubleClicked, int mouseX, int mouseY)
  {
    if ((index == this.selectedIndex) && (this.p == this.lastClickedCached)) {
      return;
    }
    this.selectedIndex = index;
    
    this.lastClickedCached = this.p;
    Shaders.setShaderPack((String)this.shaderslist.get(index));
    Shaders.uninit();
    
    this.shadersGui.updateButtons();
  }
  
  protected boolean a(int index)
  {
    return index == this.selectedIndex;
  }
  
  protected int d()
  {
    return this.b - 6;
  }
  
  protected int k()
  {
    return b() * 18;
  }
  
  protected void a() {}
  
  protected void a(int index, int posX, int posY, int contentY, int mouseX, int mouseY)
  {
    this.shadersGui.drawCenteredString((String)this.shaderslist.get(index), this.b / 2, posY + 1, 16777215);
  }
  
  public int getSelectedIndex()
  {
    return this.selectedIndex;
  }
}
