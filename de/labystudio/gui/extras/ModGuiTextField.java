package de.labystudio.gui.extras;

import bct;
import bcv;
import bdd;
import bdo.b;
import bfb;
import bmz;
import bni;
import bnu;
import bvp;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import f;
import on;

public class ModGuiTextField
  extends bdd
{
  private final int id;
  private final bct fontRendererInstance;
  public int a;
  public int f;
  private final int width;
  private final int height;
  private String text = "";
  private int maxStringLength = 32;
  private int cursorCounter;
  private boolean enableBackgroundDrawing = true;
  private boolean canLoseFocus = true;
  private boolean isFocused;
  private boolean isEnabled = true;
  private int lineScrollOffset;
  private int cursorPosition;
  private int selectionEnd;
  private int enabledColor = 14737632;
  private int disabledColor = 7368816;
  private boolean visible = true;
  private bdo.b field_175210_x;
  private Predicate<String> field_175209_y = Predicates.alwaysTrue();
  
  public ModGuiTextField(int componentId, bct fontrendererObj, int x, int y, int par5Width, int par6Height)
  {
    super(componentId, fontrendererObj, x, y, par5Width, par6Height);
    this.id = componentId;
    this.fontRendererInstance = fontrendererObj;
    this.a = x;
    this.f = y;
    this.width = par5Width;
    this.height = par6Height;
  }
  
  public void a(bdo.b p_175207_1_)
  {
    this.field_175210_x = p_175207_1_;
  }
  
  public void a()
  {
    this.cursorCounter += 1;
  }
  
  public void a(String p_146180_1_)
  {
    if (this.field_175209_y.apply(p_146180_1_))
    {
      if (p_146180_1_.length() > this.maxStringLength) {
        this.text = p_146180_1_.substring(0, this.maxStringLength);
      } else {
        this.text = p_146180_1_;
      }
      f();
    }
  }
  
  public String b()
  {
    return this.text;
  }
  
  public String c()
  {
    int i = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
    int j = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
    return this.text.substring(i, j);
  }
  
  public void func_175205_a(Predicate<String> p_175205_1_)
  {
    this.field_175209_y = p_175205_1_;
  }
  
  public void b(String p_146191_1_)
  {
    String s = "";
    String s1 = f.a(p_146191_1_);
    int i = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
    int j = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
    int k = this.maxStringLength - this.text.length() - (i - j);
    int l = 0;
    if (this.text.length() > 0) {
      s = s + this.text.substring(0, i);
    }
    if (k < s1.length())
    {
      s = s + s1.substring(0, k);
      l = k;
    }
    else
    {
      s = s + s1;
      l = s1.length();
    }
    if ((this.text.length() > 0) && (j < this.text.length())) {
      s = s + this.text.substring(j);
    }
    if (this.field_175209_y.apply(s))
    {
      this.text = s;
      d(i - this.selectionEnd + l);
      if (this.field_175210_x != null) {
        this.field_175210_x.a(this.id, this.text);
      }
    }
  }
  
  public void a(int p_146177_1_)
  {
    if (this.text.length() != 0) {
      if (this.selectionEnd != this.cursorPosition) {
        b("");
      } else {
        b(c(p_146177_1_) - this.cursorPosition);
      }
    }
  }
  
  public void b(int p_146175_1_)
  {
    if (this.text.length() != 0) {
      if (this.selectionEnd != this.cursorPosition)
      {
        b("");
      }
      else
      {
        boolean flag = p_146175_1_ < 0;
        int i = flag ? this.cursorPosition + p_146175_1_ : this.cursorPosition;
        int j = flag ? this.cursorPosition : this.cursorPosition + p_146175_1_;
        String s = "";
        if (i >= 0) {
          s = this.text.substring(0, i);
        }
        if (j < this.text.length()) {
          s = s + this.text.substring(j);
        }
        if (this.field_175209_y.apply(s))
        {
          this.text = s;
          if (flag) {
            d(p_146175_1_);
          }
          if (this.field_175210_x != null) {
            this.field_175210_x.a(this.id, this.text);
          }
        }
      }
    }
  }
  
  public int d()
  {
    return this.id;
  }
  
  public int c(int p_146187_1_)
  {
    return a(p_146187_1_, i());
  }
  
  public int a(int p_146183_1_, int p_146183_2_)
  {
    return a(p_146183_1_, p_146183_2_, true);
  }
  
  public int a(int p_146197_1_, int p_146197_2_, boolean p_146197_3_)
  {
    int i = p_146197_2_;
    boolean flag = p_146197_1_ < 0;
    int j = Math.abs(p_146197_1_);
    for (int k = 0; k < j; k++) {
      if (!flag)
      {
        int l = this.text.length();
        i = this.text.indexOf(' ', i);
        if (i == -1) {
          i = l;
        } else {
          while ((p_146197_3_) && (i < l) && (this.text.charAt(i) == ' ')) {
            i++;
          }
        }
      }
      else
      {
        while ((p_146197_3_) && (i > 0) && (this.text.charAt(i - 1) == ' ')) {
          i--;
        }
        while ((i > 0) && (this.text.charAt(i - 1) != ' ')) {
          i--;
        }
      }
    }
    return i;
  }
  
  public void d(int p_146182_1_)
  {
    e(this.selectionEnd + p_146182_1_);
  }
  
  public void e(int p_146190_1_)
  {
    this.cursorPosition = p_146190_1_;
    int i = this.text.length();
    this.cursorPosition = on.a(this.cursorPosition, 0, i);
    i(this.cursorPosition);
  }
  
  public void e()
  {
    e(0);
  }
  
  public void f()
  {
    e(this.text.length());
  }
  
  public void a(int p_146192_1_, int p_146192_2_, int p_146192_3_)
  {
    boolean flag = (p_146192_1_ >= this.a) && (p_146192_1_ < this.a + this.width) && (p_146192_2_ >= this.f) && (p_146192_2_ < this.f + this.height);
    if (this.canLoseFocus) {
      b(flag);
    }
    if ((this.isFocused) && (flag) && (p_146192_3_ == 0))
    {
      int i = p_146192_1_ - this.a;
      if (this.enableBackgroundDrawing) {
        i -= 4;
      }
      String s = this.fontRendererInstance.a(this.text.substring(this.lineScrollOffset), p());
      e(this.fontRendererInstance.a(s, i).length() + this.lineScrollOffset);
    }
  }
  
  private boolean modPasswordBox = false;
  private String modBlacklistWord = "";
  
  public void setBlacklistWord(String modBlacklistWords)
  {
    this.modBlacklistWord = modBlacklistWords;
  }
  
  public String getBlacklistWord()
  {
    return this.modBlacklistWord;
  }
  
  public void setPasswordBox(boolean modPasswordBox)
  {
    this.modPasswordBox = modPasswordBox;
  }
  
  public boolean isPasswordBox()
  {
    return this.modPasswordBox;
  }
  
  public void g()
  {
    if (r())
    {
      if ((!getBlacklistWord().isEmpty()) && (b().contains(getBlacklistWord()))) {
        a(b().replace(getBlacklistWord(), ""));
      }
      if (j())
      {
        a(this.a - 1, this.f - 1, this.a + this.width + 1, this.f + this.height + 1, -6250336);
        a(this.a, this.f, this.a + this.width, this.f + this.height, -16777216);
      }
      int var1 = this.isEnabled ? this.enabledColor : this.disabledColor;
      int var2 = i() - this.lineScrollOffset;
      int var3 = o() - this.lineScrollOffset;
      String theText = b().substring(this.lineScrollOffset);
      if (isPasswordBox()) {
        theText = theText.replaceAll(".", "*");
      }
      String var4 = this.fontRendererInstance.a(theText, p());
      boolean var5 = (var2 >= 0) && (var2 <= var4.length());
      boolean var6 = (m()) && (this.cursorCounter / 6 % 2 == 0) && (var5);
      int var7 = j() ? this.a + 4 : this.a;
      int var8 = j() ? this.f + (this.height - 8) / 2 : this.f;
      int var9 = var7;
      if (var3 > var4.length()) {
        var3 = var4.length();
      }
      if (var4.length() > 0)
      {
        String var10 = var5 ? var4.substring(0, var2) : var4;
        var9 = this.fontRendererInstance.a(var10, var7, var8, var1);
      }
      boolean var13 = (i() < this.text.length()) || (this.text.length() >= h());
      int var11 = var9;
      if (!var5)
      {
        var11 = var2 > 0 ? var7 + this.width : var7;
      }
      else if (var13)
      {
        var11 = var9 - 1;
        var9--;
      }
      if ((var4.length() > 0) && (var5) && (var2 < var4.length())) {
        var9 = this.fontRendererInstance.a(var4.substring(var2), var9, var8, var1);
      }
      if (var6) {
        if (var13) {
          bcv.a(var11, var8 - 1, var11 + 1, var8 + 1 + this.fontRendererInstance.a, -3092272);
        } else {
          this.fontRendererInstance.a("_", var11, var8, var1);
        }
      }
      if (var3 != var2)
      {
        int var12 = var7 + this.fontRendererInstance.a(var4.substring(0, var3));
        drawCursorVertical(var11, var8 - 1, var12 - 1, var8 + 1 + this.fontRendererInstance.a);
      }
    }
  }
  
  private void drawCursorVertical(int p_146188_1_, int p_146188_2_, int p_146188_3_, int p_146188_4_)
  {
    if (p_146188_1_ < p_146188_3_)
    {
      int i = p_146188_1_;
      p_146188_1_ = p_146188_3_;
      p_146188_3_ = i;
    }
    if (p_146188_2_ < p_146188_4_)
    {
      int j = p_146188_2_;
      p_146188_2_ = p_146188_4_;
      p_146188_4_ = j;
    }
    if (p_146188_3_ > this.a + this.width) {
      p_146188_3_ = this.a + this.width;
    }
    if (p_146188_1_ > this.a + this.width) {
      p_146188_1_ = this.a + this.width;
    }
    bnu tessellator = bnu.a();
    bmz worldrenderer = tessellator.c();
    bni.c(0.0F, 0.0F, 255.0F, 255.0F);
    bni.z();
    bni.w();
    bni.f(5387);
    worldrenderer.a(7, bvp.e);
    worldrenderer.b(p_146188_1_, p_146188_4_, 0.0D).d();
    worldrenderer.b(p_146188_3_, p_146188_4_, 0.0D).d();
    worldrenderer.b(p_146188_3_, p_146188_2_, 0.0D).d();
    worldrenderer.b(p_146188_1_, p_146188_2_, 0.0D).d();
    tessellator.b();
    bni.x();
    bni.y();
  }
  
  public boolean a(char p_146201_1_, int p_146201_2_)
  {
    super.a(p_146201_1_, p_146201_2_);
    if (!this.isFocused) {
      return false;
    }
    if (bfb.g(p_146201_2_))
    {
      f();
      i(0);
      return true;
    }
    if (bfb.f(p_146201_2_))
    {
      if (!isPasswordBox()) {
        bfb.e(c());
      }
      return true;
    }
    if (bfb.e(p_146201_2_))
    {
      if (this.isEnabled) {
        b(bfb.o());
      }
      return true;
    }
    if (bfb.d(p_146201_2_))
    {
      if (!isPasswordBox()) {
        bfb.e(c());
      }
      if (this.isEnabled) {
        b("");
      }
      return true;
    }
    switch (p_146201_2_)
    {
    case 14: 
      if (bfb.q())
      {
        if (this.isEnabled) {
          a(-1);
        }
      }
      else if (this.isEnabled) {
        b(-1);
      }
      return true;
    case 199: 
      if (bfb.r()) {
        i(0);
      } else {
        e();
      }
      return true;
    case 203: 
      if (bfb.r())
      {
        if (bfb.q()) {
          i(a(-1, o()));
        } else {
          i(o() - 1);
        }
      }
      else if (bfb.q()) {
        e(c(-1));
      } else {
        d(-1);
      }
      return true;
    case 205: 
      if (bfb.r())
      {
        if (bfb.q()) {
          i(a(1, o()));
        } else {
          i(o() + 1);
        }
      }
      else if (bfb.q()) {
        e(c(1));
      } else {
        d(1);
      }
      return true;
    case 207: 
      if (bfb.r()) {
        i(this.text.length());
      } else {
        f();
      }
      return true;
    case 211: 
      if (bfb.q())
      {
        if (this.isEnabled) {
          a(1);
        }
      }
      else if (this.isEnabled) {
        b(1);
      }
      return true;
    }
    if (f.a(p_146201_1_))
    {
      if (this.isEnabled) {
        b(Character.toString(p_146201_1_));
      }
      return true;
    }
    return false;
  }
  
  public void f(int p_146203_1_)
  {
    this.maxStringLength = p_146203_1_;
    if (this.text.length() > p_146203_1_) {
      this.text = this.text.substring(0, p_146203_1_);
    }
  }
  
  public int h()
  {
    return this.maxStringLength;
  }
  
  public int i()
  {
    return this.cursorPosition;
  }
  
  public boolean j()
  {
    return this.enableBackgroundDrawing;
  }
  
  public void a(boolean p_146185_1_)
  {
    this.enableBackgroundDrawing = p_146185_1_;
  }
  
  public void g(int p_146193_1_)
  {
    this.enabledColor = p_146193_1_;
  }
  
  public void h(int p_146204_1_)
  {
    this.disabledColor = p_146204_1_;
  }
  
  public void b(boolean p_146195_1_)
  {
    if ((p_146195_1_) && (!this.isFocused)) {
      this.cursorCounter = 0;
    }
    this.isFocused = p_146195_1_;
  }
  
  public boolean m()
  {
    return this.isFocused;
  }
  
  public void c(boolean p_146184_1_)
  {
    this.isEnabled = p_146184_1_;
  }
  
  public int o()
  {
    return this.selectionEnd;
  }
  
  public int p()
  {
    return j() ? this.width - 8 : this.width;
  }
  
  public void i(int p_146199_1_)
  {
    int i = this.text.length();
    if (p_146199_1_ > i) {
      p_146199_1_ = i;
    }
    if (p_146199_1_ < 0) {
      p_146199_1_ = 0;
    }
    this.selectionEnd = p_146199_1_;
    if (this.fontRendererInstance != null)
    {
      if (this.lineScrollOffset > i) {
        this.lineScrollOffset = i;
      }
      int j = p();
      String s = this.fontRendererInstance.a(this.text.substring(this.lineScrollOffset), j);
      int k = s.length() + this.lineScrollOffset;
      if (p_146199_1_ == this.lineScrollOffset) {
        this.lineScrollOffset -= this.fontRendererInstance.a(this.text, j, true).length();
      }
      if (p_146199_1_ > k) {
        this.lineScrollOffset += p_146199_1_ - k;
      } else if (p_146199_1_ <= this.lineScrollOffset) {
        this.lineScrollOffset -= this.lineScrollOffset - p_146199_1_;
      }
      this.lineScrollOffset = on.a(this.lineScrollOffset, 0, i);
    }
  }
  
  public void d(boolean p_146205_1_)
  {
    this.canLoseFocus = p_146205_1_;
  }
  
  public boolean r()
  {
    return this.visible;
  }
  
  public void e(boolean p_146189_1_)
  {
    this.visible = p_146189_1_;
  }
}
