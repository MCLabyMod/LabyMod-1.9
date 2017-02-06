package de.labystudio.gui;

import bcf;
import bcz;
import bni;
import de.labystudio.games.EnumGame;
import de.labystudio.games.Mario;
import de.labystudio.games.Snake;
import de.labystudio.labymod.LabyMod;
import de.labystudio.language.L;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiGames
  extends GuiMenuScreen
{
  DrawUtils draw;
  
  public GuiGames()
  {
    super(null);
    this.childScreen = this;
    this.draw = LabyMod.getInstance().draw;
    this.id = "Games";
  }
  
  EnumGame selectedGame = EnumGame.None;
  private bcz btnPlay;
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    this.n.add(this.btnPlay = new bcz(0, this.l / 2 - 100, this.m - 70, 200, 20, "Play"));
    
    int bx = 0;
    for (EnumGame g : EnumGame.values()) {
      if (g != EnumGame.None)
      {
        bcz b = new bcz(-1, 5 + bx, this.m - 26, 80, 20, g.name());
        b.l = (g != this.selectedGame);
        this.n.add(b);
        bx += 82;
      }
    }
    super.b();
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    super.a(mouseX, mouseY, mouseButton);
  }
  
  public void a(bcz button)
    throws IOException
  {
    if (button.k == -1) {
      for (EnumGame g : EnumGame.values()) {
        if (g.name().equals(button.j))
        {
          this.selectedGame = g;
          b();
        }
      }
    }
    if (button.k == 0)
    {
      if (this.selectedGame == EnumGame.Snake)
      {
        this.j.a(new Snake());
        return;
      }
      if (this.selectedGame == EnumGame.Mario)
      {
        this.j.a(new Mario());
        return;
      }
    }
    super.actionPermformed(button);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    super.a(typedChar, keyCode);
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    if (LabyMod.getInstance().isInGame())
    {
      bni.m();
      this.draw.drawTransparentBackground(0, 32, this.l, this.m - 33);
    }
    else
    {
      c();
      this.draw.drawChatBackground(0, 32, this.l, this.m - 33);
    }
    bni.l();
    this.draw.overlayBackground(0, 32);
    this.draw.overlayBackground(this.m - 33, this.m);
    if (this.selectedGame == EnumGame.None) {
      this.draw.drawCenteredString(Color.cl("c") + L._("gui_games_nogames", new Object[0]), this.l / 2, this.m / 2);
    }
    if (this.selectedGame == EnumGame.Snake)
    {
      GL11.glPushMatrix();
      int k = 2;
      GL11.glScaled(k, k, k);
      this.draw.drawCenteredString(Color.cl("a") + "Snake", this.l / 2 / k, (this.m / 2 - 82) / k);
      GL11.glPopMatrix();
      int i = 60;
      this.draw.drawCenteredString("The purpose of this game is to gain as much", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("score as possible by collecting the dots that are randomly spawning", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("There are several types of dots which are differentiate by their color", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("The " + Color.cl("4") + "red" + Color.cl("f") + " dot is the normal one, which increases your score by 10,", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("all the other dots increase your score by 20", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("The " + Color.cl("9") + "blue" + Color.cl("f") + " dot makes you slower, while the " + Color.cl("b") + "cyan" + Color.cl("f") + " dot makes you faster.", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("The last one, the " + Color.cl("6") + "golden" + Color.cl("f") + " dot, increases the spawning", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("rate of the dots by one for each golden dot you collect.", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("But collecting these dots also lets you become longer, and once you hit", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("yourself with your head, the game is over. You can also increase your", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("speed by manually keeping the W, A, S, D or the arrow keys pressed.", this.l / 2, this.m / 2 - i);
    }
    else if (this.selectedGame == EnumGame.Mario)
    {
      GL11.glPushMatrix();
      int k = 2;
      GL11.glScaled(k, k, k);
      this.draw.drawCenteredString(Color.cl("a") + "Mario", this.l / 2 / k, (this.m / 2 - 82) / k);
      GL11.glPopMatrix();
      int i = 60;
      this.draw.drawCenteredString("This game is similar to the popular game SuperMario.", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("You are a blue pixel and have to run through a random generated world.", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("The purpose is to get the highest distance from the spawn.", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString("You jump by pressing space, and by pressing A and D you move left/right.", this.l / 2, this.m / 2 - i);
      i -= 10;
      this.draw.drawCenteredString(" That's it - have fun!", this.l / 2, this.m / 2 - i);
    }
    this.btnPlay.m = (this.selectedGame != EnumGame.None);
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
