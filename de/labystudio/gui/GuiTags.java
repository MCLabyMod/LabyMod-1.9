package de.labystudio.gui;

import bcz;
import bni;
import de.labystudio.gui.extras.ModGuiTextField;
import de.labystudio.labymod.LabyMod;
import de.labystudio.language.L;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.FriendsLoader;
import de.labystudio.utils.Scrollbar;
import de.labystudio.utils.TextureManager;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiTags
  extends GuiMenuScreen
{
  DrawUtils draw;
  Scrollbar scrollbar;
  
  public GuiTags()
  {
    super(null);
    this.childScreen = this;
    this.draw = LabyMod.getInstance().draw;
    this.editor = false;
    this.id = "Tags";
  }
  
  String selectedFriend = "";
  private bcz btnAddFriend;
  private bcz btnEditFriend;
  private bcz btnDeleteFriend;
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    this.n.add(this.btnAddFriend = new bcz(0, this.l / 2 - 154, this.m - 26, 100, 20, L._("gui_tags_addfriend", new Object[0])));
    this.n.add(this.btnEditFriend = new bcz(1, this.l / 2 - 50, this.m - 26, 100, 20, L._("gui_tags_editfriend", new Object[0])));
    this.n.add(this.btnDeleteFriend = new bcz(2, this.l / 2 + 4 + 50, this.m - 26, 100, 20, L._("gui_tags_deletefriend", new Object[0])));
    
    super.b();
    initEditor(this.selectedFriend);
    if (FriendsLoader.friends == null) {
      this.scrollbar = new Scrollbar(0);
    } else {
      this.scrollbar = new Scrollbar(FriendsLoader.friends.size());
    }
    this.scrollbar.setPosition(this.l / 2 + 154, 40, this.l / 2 + 160, this.m - 40);
    this.scrollbar.update(35);
  }
  
  private void drawFriends()
  {
    int listY = 0;
    if ((FriendsLoader.friends != null) && (!FriendsLoader.friends.isEmpty())) {
      for (String friend : FriendsLoader.friends.keySet())
      {
        String nick = (String)FriendsLoader.friends.get(friend);
        if (this.selectedFriend.equalsIgnoreCase(friend)) {
          DrawUtils.a(this.l / 2 - 151, 50 + this.scrollbar.getScrollY() + listY - 4, this.l / 2 + 158, 50 + this.scrollbar.getScrollY() + listY + 30, 632207020);
        }
        bni.d(1.0F, 1.0F, 1.0F);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        LabyMod.getInstance().textureManager.drawPlayerHead(friend, this.l / 2 - 150, 50 + this.scrollbar.getScrollY() + listY, 1.0D);
        this.draw.drawString(Color.cl("l") + friend, this.l / 2 - 110, 50 + this.scrollbar.getScrollY() + listY);
        if (nick.isEmpty()) {
          this.draw.drawString(Color.cl("c") + L._("gui_tags_nonickname", new Object[0]) + Color.cl("r"), this.l / 2 - 110, 25 + this.scrollbar.getScrollY() + listY + 35);
        } else {
          this.draw.drawString(Color.cl("e") + L._("gui_tags_nickname", new Object[0]) + ": " + Color.cl("r") + nick.replace("&", Color.c) + Color.cl("r"), this.l / 2 - 110, 25 + this.scrollbar.getScrollY() + listY + 35);
        }
        listY += 35;
      }
    }
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    super.a(mouseX, mouseY, mouseButton);
    
    this.scrollbar.mouseAction(mouseX, mouseY, false);
    int listY;
    if (this.editor)
    {
      this.editName.a(mouseX, mouseY, mouseButton);
      this.editNick.a(mouseX, mouseY, mouseButton);
    }
    else
    {
      listY = 0;
      for (String friend : FriendsLoader.friends.keySet())
      {
        String nick = (String)FriendsLoader.friends.get(friend);
        if ((mouseX > this.l / 2 - 151) && (mouseX < this.l / 2 + 160) && (mouseY > 50 + this.scrollbar.getScrollY() + listY - 4) && (mouseY < 50 + this.scrollbar.getScrollY() + listY + 31))
        {
          this.selectedFriend = friend;
          return;
        }
        listY += 35;
      }
    }
  }
  
  protected void a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
  {
    this.scrollbar.mouseAction(mouseX, mouseY, true);
    super.a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
  }
  
  public void k()
    throws IOException
  {
    this.scrollbar.mouseInput();
    super.k();
  }
  
  public void a(bcz button)
    throws IOException
  {
    super.actionPermformed(button);
    if (button.k == 0)
    {
      this.selectedFriend = "";
      edit("");
    }
    if (button.k == 1) {
      edit(this.selectedFriend);
    }
    if (button.k == 2)
    {
      FriendsLoader.friends.remove(this.selectedFriend);
      FriendsLoader.saveFriends();
      b();
      this.selectedFriend = "";
    }
    if (button.k == 3)
    {
      if ((!this.selectedFriend.isEmpty()) && 
        (!this.editName.b().equals(this.selectedFriend))) {
        FriendsLoader.friends.remove(this.selectedFriend);
      }
      this.selectedFriend = this.editName.b();
      FriendsLoader.friends.put(this.editName.b(), this.editNick.b());
      this.editor = false;
      FriendsLoader.saveFriends();
      b();
    }
    if (button.k == 4)
    {
      this.editor = false;
      b();
    }
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    this.editName.a(typedChar, keyCode);
    this.editNick.a(typedChar, keyCode);
    if ((this.editor) && (this.done.l) && (
      (keyCode == 28) || (keyCode == 156))) {
      a(this.done);
    }
    super.a(typedChar, keyCode);
  }
  
  boolean editor = false;
  ModGuiTextField editName = new ModGuiTextField(-1, null, 0, 0, 0, 0);
  ModGuiTextField editNick = new ModGuiTextField(-1, null, 0, 0, 0, 0);
  bcz done;
  
  private void edit(String name)
  {
    this.editor = true;
    this.selectedFriend = name;
    this.focus = false;
    initEditor(name);
  }
  
  boolean focus = false;
  
  private void initEditor(String name)
  {
    if (!this.editor) {
      return;
    }
    for (int i = 0; i <= this.n.size() - 1; i++)
    {
      bcz b = (bcz)this.n.get(i);
      b.m = false;
    }
    this.editName = new ModGuiTextField(-1, this.draw.fontRenderer, this.l / 2 - 100, this.m / 2 - 50, 200, 20);
    this.editName.f(16);
    this.editName.setBlacklistWord(" ");
    this.editName.a(name);
    this.editNick = new ModGuiTextField(-1, this.draw.fontRenderer, this.l / 2 - 100, this.m / 2, 200, 20);
    this.editNick.f(50);
    if (FriendsLoader.friends.containsKey(name)) {
      this.editNick.a((String)FriendsLoader.friends.get(name));
    }
    bcz cancel = new bcz(4, this.l / 2 - 100, this.m / 2 + 53, L._("button_cancel", new Object[0]));
    this.n.add(cancel);
    
    this.done = new bcz(3, this.l / 2 - 100, this.m / 2 + 28, L._("button_done", new Object[0]));
    this.n.add(this.done);
    
    this.btnDeleteFriend.m = false;
    this.btnEditFriend.m = false;
    this.btnAddFriend.m = false;
  }
  
  private void drawEditor()
  {
    this.draw.drawString(L._("gui_tags_playername", new Object[0]) + ":", this.l / 2 - 100, this.m / 2 - 63);
    this.draw.drawString(L._("gui_tags_nickname", new Object[0]) + ":", this.l / 2 - 100, this.m / 2 - 13);
    if ((this.editName != null) && (this.editNick != null))
    {
      this.editName.g();
      this.editNick.g();
      if (this.done != null) {
        this.done.l = ((!this.editName.b().replace(" ", "").isEmpty()) && ((!FriendsLoader.friends.containsKey(this.editName.b())) || (this.editName.b().equals(this.selectedFriend))));
      }
    }
    if (!this.focus)
    {
      this.editName.b(true);
      this.focus = true;
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    if (this.editor)
    {
      c(0);
      drawEditor();
      super.a(mouseX, mouseY, partialTicks);
      return;
    }
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
    drawFriends();
    
    bni.l();
    this.draw.overlayBackground(0, 32);
    this.draw.overlayBackground(this.m - 33, this.m);
    
    this.scrollbar.draw();
    
    this.btnEditFriend.l = (!this.selectedFriend.isEmpty());
    this.btnDeleteFriend.l = (!this.selectedFriend.isEmpty());
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
