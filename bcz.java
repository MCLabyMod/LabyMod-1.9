import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import org.lwjgl.opengl.GL11;

public class bcz
  extends bcv
{
  protected static final kk a = new kk("textures/gui/widgets.png");
  protected int f;
  protected int g;
  public int h;
  public int i;
  public String j;
  public int k;
  public boolean l;
  public boolean m;
  protected boolean n;
  
  public bcz(int buttonId, int x, int y, String buttonText)
  {
    this(buttonId, x, y, 200, 20, buttonText);
  }
  
  public bcz(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
  {
    this.f = 200;
    this.g = 20;
    this.l = true;
    this.m = true;
    this.k = buttonId;
    this.h = x;
    this.i = y;
    this.f = widthIn;
    this.g = heightIn;
    this.j = buttonText;
  }
  
  protected int a(boolean mouseOver)
  {
    int i = 1;
    if (!this.l) {
      i = 0;
    } else if (mouseOver) {
      i = 2;
    }
    return i;
  }
  
  public boolean centered = true;
  public String subTitle = "";
  long showInfo = 0L;
  public String info = "";
  public String run = "";
  public boolean noGui = false;
  
  public void drawInfoBox()
  {
    if (!this.n) {
      this.showInfo = System.currentTimeMillis();
    }
    if (this.info.isEmpty()) {
      return;
    }
    if (this.showInfo + 300L > System.currentTimeMillis()) {
      return;
    }
    int l = Integer.MIN_VALUE;
    GL11.glPushMatrix();
    double k = 0.7D;
    GL11.glScaled(k, k, k);
    for (int i = 0; i < 3; i++) {
      DrawUtils.a((int)((LabyMod.getInstance().draw.getWidth() / 2 - 140) / k), 
        (int)((LabyMod.getInstance().draw.getHeight() - 30 - 10 * getRange(this.info, 300)) / k), 
        (int)((LabyMod.getInstance().draw.getWidth() / 2 + 140) / k), (int)((LabyMod.getInstance().draw.getHeight() - 15) / k), l);
    }
    drawContent(this.info, (int)(LabyMod.getInstance().draw.getWidth() / 2 / k), (int)((LabyMod.getInstance().draw.getHeight() - 25 - 10 * getRange(this.info, 300)) / k), 300);
    GL11.glPopMatrix();
  }
  
  private void drawContent(String msg, int x, int y, int max)
  {
    int range = getRange(msg, max);
    String next = getFirstStrings(max, msg);
    String done = "";
    for (int i = 0; i <= range; i++)
    {
      LabyMod.getInstance().draw.drawCenteredString(next, x, y + i * 12);
      done = done + next;
      next = getFirstStrings(max, msg.replace(done, ""));
    }
  }
  
  private String getFirstStrings(int pixels, String string)
  {
    int k = 0;
    String result = "";
    for (int i = 0; i < string.length(); i++)
    {
      char c = string.charAt(i);
      k += LabyMod.getInstance().draw.getStringWidth(new String(new char[] { c }));
      if (pixels > k)
      {
        result = result + new String(new char[] { c });
      }
      else
      {
        if (pixels != k) {
          break;
        }
        result = result + c;
        break;
      }
    }
    return result;
  }
  
  private int getRange(String msg, int max)
  {
    int k = 0;
    int range = 0;
    String line = msg;
    for (int i = 0; i <= line.length() - 1; i++)
    {
      char a = line.charAt(i);
      if (k > max)
      {
        range++;
        k = 0;
      }
      k += LabyMod.getInstance().draw.getStringWidth("" + a);
    }
    return range;
  }
  
  public void a(bcf mc, int mouseX, int mouseY)
  {
    if (this.m)
    {
      bct fontrenderer = mc.k;
      mc.N().a(a);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      this.n = ((mouseX >= this.h) && (mouseY >= this.i) && (mouseX < this.h + this.f) && (mouseY < this.i + this.g));
      int i = a(this.n);
      bni.m();
      bni.a(bni.r.l, bni.l.j, bni.r.e, bni.l.n);
      bni.a(bni.r.l, bni.l.j);
      if (!this.noGui)
      {
        b(this.h, this.i, 0, 46 + i * 20, this.f / 2, this.g);
        b(this.h + this.f / 2, this.i, 200 - this.f / 2, 46 + i * 20, this.f / 2, this.g);
      }
      b(mc, mouseX, mouseY);
      int j = 14737632;
      if (!this.l) {
        j = 10526880;
      } else if (this.n) {
        j = 16777120;
      }
      if (this.centered)
      {
        a(fontrenderer, this.j, this.h + this.f / 2, this.i + (this.g - 8) / 2, j);
      }
      else
      {
        c(fontrenderer, Color.cl("f") + this.j, this.h + 6, this.i + (this.g - 8) / 2, 0);
        a(fontrenderer, this.subTitle, this.h + 40 + (this.h + this.f / 2 - (this.h + 30)) * 2 - this.subTitle.length(), this.i + (this.g - 8) / 2, j);
      }
    }
  }
  
  protected void b(bcf mc, int mouseX, int mouseY) {}
  
  public void a(int mouseX, int mouseY) {}
  
  public boolean c(bcf mc, int mouseX, int mouseY)
  {
    return (this.l) && (this.m) && (mouseX >= this.h) && (mouseY >= this.i) && (mouseX < this.h + this.f) && (mouseY < this.i + this.g);
  }
  
  public boolean a()
  {
    return this.n;
  }
  
  public void b(int mouseX, int mouseY) {}
  
  public void a(byx soundHandlerIn)
  {
    soundHandlerIn.a(bye.a(ng.go, 1.0F));
  }
  
  public int b()
  {
    return this.f;
  }
  
  public void a(int width)
  {
    this.f = width;
  }
}
