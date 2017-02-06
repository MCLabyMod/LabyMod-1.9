package de.labystudio.gui;

import bcf;
import bcu;
import bcz;
import bda;
import bdd;
import bee;
import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class GuiSymbolSelector
  extends bfb
{
  private DrawUtils draw;
  private bdd inputField;
  private String text;
  
  public GuiSymbolSelector(String text)
  {
    this.text = text;
  }
  
  int z = 0;
  int y = 0;
  int lines = 8;
  
  public void addSymbol(String symbol)
  {
    bcz button = new bcz(-1, this.l - 4 - 20 - this.z, 4 + this.y, 20, 20, symbol);
    button.run = "true";
    this.n.add(button);
    this.z += 24;
    if (this.z % (24 * this.lines) == 0)
    {
      this.z = 0;
      this.y += 24;
    }
  }
  
  int page = 0;
  int per = 48;
  
  public String getSymbols()
  {
    String str = "❤❥✔✖✗✘❂⋆✢✣✤✥✦✩✪✫✬✭✮✯✰★✱✲✳✴✵✶✷✸✹✺✻✼❄❅❆❇❈❉❊❋☆✡❂✽✾✿❀❁❃❋✌♼♽✂✄✈➡⬅⬆⬇➟➡➢➣➤➥➦➧➨➚➘➙➛➜➝➞➸➲➳➳➴➵➶➷➸➹➺➻➼➽Ⓜ⬛⬜█▛▀▜▆▄▌☕ℹ™⚑⚐☃⚠⚔⚖⚒⚙⚜⚀⚁⚂⚃⚄⚅⚊⚋⚌⚍⚎⚏☰☱☲☳☴☵☶☷⚆⚇⚈⚉♿♩♪♫♬♭♮♯♠♡♢♣♤♥♦♧♔♕♖♗♘♙♚♛♜♝♞♟⚪⚫☯☮☣☏➀➁➂➃➄➅➆➇➈➉➊➋➌➍➎➏➐➑➒➓ⓐⓑⓒⓓⓔⓕⓖⓗⓘⓙⓚⓛⓜⓝⓞⓟⓠⓡⓢⓣⓤⓥⓦⓧⓨⓩ❤❥웃유♋☮✌☏☢☠✔☑♚▲♪✈⌚¿♥❣♂♀☿Ⓐ✍✉☣☤✘☒♛▼♫⌘⌛¡♡ღツ☼☁❅♒✎©®™Σ✪✯☭➳✞℃℉✿ϟ☃☂✄¢£∞✫★½☯✡☪✿☺☻☹☼☂☃⌇⚛⌨✆☎⌥⇧↩✞✡☭←→↑↓➫⬇⬆☜☞☝☟✍✎✌☮✔★☆♺⚑⚐✉✄⌲✈♦♣♠♥❤♡♪♩♫♬♯♀♂⚢⚣❑❒◈◐◑✖∞«»‹›–—⁄¶¡¿‽⁂※±×≈÷≠π†‡¥€¢£™‰…·•●";
    
    String dub = "";
    for (int i = 0; i < str.length(); i++)
    {
      String input = str.charAt(i) + "";
      if ((!input.equals(" ")) && 
        (!dub.contains(input))) {
        dub = dub + input;
      }
    }
    return dub;
  }
  
  public void initSymbols()
  {
    String str = getSymbols();
    for (int i = 0; i <= this.n.size() - 1; i++)
    {
      bcz b = (bcz)this.n.get(i);
      if (b.run.equals("true")) {
        b.m = false;
      }
    }
    bcz b = new bcz(-1, this.l - 168, 4, 20, 20, Color.cl("a") + this.page);
    b.l = false;
    b.run = "false";
    this.n.add(b);
    String bkstr = str;
    try
    {
      try
      {
        str = str.substring(0 + this.page * this.per, this.per + this.page * this.per);
        ((bcz)this.n.get(1)).l = true;
      }
      catch (Exception error)
      {
        str = str.substring(0 + this.page * this.per, bkstr.length() - 1);
        ((bcz)this.n.get(1)).l = false;
      }
    }
    catch (Exception localException1) {}
    if (this.page == 0) {
      ((bcz)this.n.get(0)).l = false;
    } else {
      ((bcz)this.n.get(0)).l = true;
    }
    this.z = 0;
    this.y = 24;
    
    String avaSymbols = "";
    for (int i = 0; i < str.length(); i++)
    {
      String input = str.charAt(i) + "";
      if (!input.equals(" ")) {
        avaSymbols = avaSymbols + input;
      }
    }
    for (int i = 0; i < avaSymbols.length(); i++)
    {
      String input = avaSymbols.charAt(i) + "";
      if (!input.equals(" ")) {
        addSymbol(input);
      }
    }
  }
  
  public void a(char typedChar, int keyCode)
    throws IOException
  {
    super.a(typedChar, keyCode);
    if (keyCode == 1)
    {
      this.j.a((bfb)null);
    }
    else if ((keyCode != 28) && (keyCode != 156))
    {
      if ((keyCode != 200) && 
        (keyCode != 208)) {
        if (keyCode == 201) {
          this.j.r.d().b(this.j.r.d().i() - 1);
        } else if (keyCode == 209) {
          this.j.r.d().b(-this.j.r.d().i() + 1);
        } else {
          this.inputField.a(typedChar, keyCode);
        }
      }
    }
    else
    {
      String var3 = this.inputField.b().trim();
      if (var3.length() > 0) {
        f(var3);
      }
      this.j.a((bfb)null);
    }
    this.text = this.inputField.b();
  }
  
  public void b()
  {
    this.n.add(new bcz(1, this.l - 192, 4, 20, 20, Color.cl("c") + "<"));
    this.n.add(new bcz(2, this.l - 144, 4, 20, 20, Color.cl("c") + ">"));
    
    this.n.add(new bcz(-1, this.l - 205, 148, 10, 20, ""));
    this.n.add(new bcz(-1, this.l - 217, 45, 10, 20, ""));
    
    int colorAdd = 0;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("0") + "0"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("1") + "1"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("2") + "2"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("3") + "3"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("4") + "4"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("5") + "5"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("6") + "6"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("7") + "7"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("8") + "8"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("9") + "9"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("a") + "a"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("b") + "b"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("c") + "c"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("d") + "d"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 10, Color.cl("e") + "e"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 205, 4 + colorAdd, 10, 13, Color.cl("f") + "f"));colorAdd += 10;
    
    colorAdd = 0;
    this.n.add(new bcz(-5, this.l - 217, 4 + colorAdd, 10, 10, Color.cl("l") + "l"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 217, 4 + colorAdd, 10, 10, Color.cl("o") + "o"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 217, 4 + colorAdd, 10, 10, Color.cl("n") + "n"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 217, 4 + colorAdd, 10, 10, Color.cl("m") + "m"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 217, 4 + colorAdd, 10, 10, Color.cl("k") + "k"));colorAdd += 10;
    this.n.add(new bcz(-5, this.l - 217, 4 + colorAdd, 10, 10, Color.cl("r") + "r"));colorAdd += 10;
    
    this.n.add(new bcz(0, this.l - 48, 4, 45, 20, Color.cl("c") + "Close"));
    initSymbols();
    
    Keyboard.enableRepeatEvents(true);
    this.inputField = new bdd(0, this.q, 4, this.m - 12, this.l - 4, 12);
    this.inputField.f(100);
    this.inputField.a(false);
    this.inputField.b(true);
    this.inputField.a(this.text);
    this.inputField.d(false);
  }
  
  protected void a(bcz button)
  {
    switch (button.k)
    {
    case 0: 
      this.j.a(new bee(this.text));
      break;
    case 1: 
      this.page -= 1;
      initSymbols();
      break;
    case 2: 
      this.page += 1;
      initSymbols();
    }
    if (button.k == -5)
    {
      this.inputField.a("&".charAt(0), 0);
      this.inputField.a(button.j.replace(Color.c + "", "").substring(0, 1).charAt(0), 0);
    }
    if (button.run.equals("true")) {
      this.inputField.a(button.j.charAt(0), 0);
    }
    this.text = this.inputField.b();
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    this.draw = LabyMod.getInstance().draw;
    a(2, this.m - 14, this.l - 2, this.m - 2, Integer.MIN_VALUE);
    a(this.l - 193, 27, this.l - 3, 169, Integer.MIN_VALUE);
    a(this.l - 193, 4, this.l - 3, 25, Integer.MIN_VALUE);
    a(this.l - 218, 4, this.l - 194, 169, Integer.MIN_VALUE);
    this.inputField.g();
    super.a(mouseX, mouseY, partialTicks);
  }
}
