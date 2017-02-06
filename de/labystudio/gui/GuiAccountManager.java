package de.labystudio.gui;

import bcf;
import bcz;
import bfb;
import de.labystudio.account.AccountManager;
import de.labystudio.gui.extras.ModGuiTextField;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class GuiAccountManager
  extends bfb
{
  DrawUtils draw;
  bfb lastScreen;
  
  public GuiAccountManager(bfb lastScreen)
  {
    this.draw = LabyMod.getInstance().draw;
    this.lastScreen = lastScreen;
  }
  
  String selectedFriend = "";
  boolean allowScroll = false;
  ModGuiTextField username;
  ModGuiTextField password;
  bcz done;
  bcz cancel;
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    
    this.username = new ModGuiTextField(-1, this.draw.fontRenderer, this.l / 2 - 100, this.m / 2 - 50, 200, 20);
    this.username.setBlacklistWord(" ");
    this.username.a(AccountManager.accountManagerUsername);
    this.username.f(64);
    this.password = new ModGuiTextField(-1, this.draw.fontRenderer, this.l / 2 - 100, this.m / 2, 200, 20);
    this.password.setBlacklistWord(" ");
    this.password.a(AccountManager.accountManagerPassword);
    this.password.setPasswordBox(true);
    this.password.f(64);
    
    this.done = new bcz(0, this.l / 2 - 100, this.m / 2 + 28, "Login");
    this.n.add(this.done);
    
    this.cancel = new bcz(1, this.l / 2 - 100, this.m / 2 + 53, "Cancel");
    this.n.add(this.cancel);
    
    super.b();
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (!this.login)
    {
      if (this.username.a(typedChar, keyCode)) {
        AccountManager.accountManagerUsername = this.username.b();
      }
      if (this.password.a(typedChar, keyCode)) {
        AccountManager.accountManagerPassword = this.password.b();
      }
    }
    if ((this.done.l) && (
      (keyCode == 28) || (keyCode == 156))) {
      a(this.done);
    }
    if (keyCode == 15) {
      if (!this.password.m())
      {
        this.username.b(false);
        this.password.b(true);
      }
      else
      {
        this.username.b(true);
        this.password.b(false);
      }
    }
    if (keyCode == 1)
    {
      if (this.login) {
        this.j.a(this.lastScreen);
      }
      return;
    }
    super.a(typedChar, keyCode);
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    this.username.a(mouseX, mouseY, mouseButton);
    this.password.a(mouseX, mouseY, mouseButton);
    super.a(mouseX, mouseY, mouseButton);
  }
  
  String badLogin = "";
  long time = 0L;
  boolean flash = false;
  boolean login = false;
  
  protected void a(bcz button)
    throws IOException
  {
    super.a(button);
    if (button.k == 0)
    {
      this.login = true;
      new Login().start();
    }
    if (button.k == 1) {
      this.j.a(this.lastScreen);
    }
  }
  
  class Login
    extends Thread
  {
    Login() {}
    
    public void run()
    {
      String result = AccountManager.login(GuiAccountManager.this.username.b(), GuiAccountManager.this.password.b());
      if (result.isEmpty())
      {
        GuiAccountManager.this.badLogin = "";
        GuiAccountManager.this.j.a(GuiAccountManager.this.lastScreen);
      }
      else
      {
        GuiAccountManager.this.badLogin = result;
        GuiAccountManager.this.time = System.currentTimeMillis();
      }
      GuiAccountManager.this.login = false;
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    this.done.l = (!this.login);
    this.cancel.l = (!this.login);
    
    this.draw.drawString("Username/Email:", this.l / 2 - 100, this.m / 2 - 63);
    this.draw.drawString("Password:", this.l / 2 - 100, this.m / 2 - 13);
    if (!this.badLogin.isEmpty())
    {
      String c = de.labystudio.utils.Color.cl("f");
      a(0, 10, this.l, 30, java.awt.Color.RED.getRGB());
      if ((this.time + 1000L > System.currentTimeMillis()) && (this.flash)) {
        this.draw.drawCenteredString(c + "Error: " + this.badLogin, this.l / 2 - 1, 16);
      } else {
        this.draw.drawCenteredString(c + "Error: " + this.badLogin, this.l / 2, 16);
      }
      this.flash = (!this.flash);
    }
    if (this.login)
    {
      a(0, 10, this.l, 30, java.awt.Color.BLUE.getRGB());
      this.draw.drawCenteredString("Logging in..", this.l / 2 - 1, 16);
    }
    this.username.g();
    this.password.g();
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
