package de.zockermaus.ts3;

import bcf;
import de.labystudio.gui.extras.ModGuiTextField;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;
import java.util.ArrayList;

public class TeamSpeakOverlayWindow
{
  private ArrayList<InfoMessage> info = new ArrayList();
  String infoTitle;
  boolean infoDrag;
  int infoPosX;
  int infoPosY;
  int infoLengthX;
  int infoLengthY;
  int infoClickX;
  int infoClickY;
  String inputTitle;
  boolean inputDrag;
  int inputPosX;
  int inputPosY;
  int inputLengthX;
  int inputLengthY;
  int inputClickX;
  int inputClickY;
  int inputTarget;
  String input;
  PopUpCallback inputCallBack;
  ModGuiTextField inputField;
  
  class InfoMessage
  {
    String message;
    int clientId;
    
    public InfoMessage(int clientId, String message)
    {
      this.clientId = clientId;
      this.message = message;
    }
  }
  
  public void openInfo(int clientId, String title, String message)
  {
    if (message.length() > 70) {
      message = message.substring(0, 70) + "..";
    }
    this.info.add(new InfoMessage(clientId, message));
    this.infoTitle = title;
    if (this.info.size() > 15) {
      this.info.remove(0);
    }
    calcInfo();
  }
  
  public void openInput(int targetId, String title, String message, PopUpCallback callBack)
  {
    this.inputTitle = title;
    this.input = message;
    this.inputTarget = targetId;
    this.inputCallBack = callBack;
    this.inputField = new ModGuiTextField(0, LabyMod.getInstance().mc.k, 0, 0, 159, 10);
    this.inputField.a("");
    this.inputField.f(50);
    this.inputField.b(true);
    calcInput();
  }
  
  boolean closedInfo = false;
  
  private void resetInfo()
  {
    this.info.clear();
    this.infoTitle = null;
    this.infoDrag = false;
    this.infoPosX = 0;
    this.infoPosY = 0;
    this.infoLengthX = 0;
    this.infoLengthY = 0;
    this.infoClickX = 0;
    this.infoClickY = 0;
    this.closedInfo = true;
  }
  
  boolean closedInput = false;
  
  private void resetinput()
  {
    this.input = null;
    this.inputTitle = null;
    this.inputDrag = false;
    this.inputPosX = 0;
    this.inputPosY = 0;
    this.inputLengthX = 0;
    this.inputLengthY = 0;
    this.inputClickX = 0;
    this.inputClickY = 0;
    this.inputTarget = 0;
    this.closedInput = true;
    this.inputField = null;
  }
  
  public void drawWindow(int mouseX, int mouseY)
  {
    if (!this.info.isEmpty()) {
      drawInfo(mouseX, mouseY);
    }
    if (this.input != null) {
      drawInput(mouseX, mouseY);
    }
  }
  
  public boolean isInInfoScreen(int mouseX, int mouseY)
  {
    if (this.closedInfo) {
      return true;
    }
    return (mouseX > this.infoPosX - 1) && (mouseX < this.infoPosX + this.infoLengthX + 1) && (mouseY > this.infoPosY - 1) && (mouseY < this.infoPosY + this.infoLengthY + 1);
  }
  
  public boolean isInInfoFrame(int mouseX, int mouseY)
  {
    return (mouseX > this.infoPosX - 1) && (mouseX < this.infoPosX + this.infoLengthX + 1) && (mouseY > this.infoPosY - 1) && (mouseY < this.infoPosY + 15);
  }
  
  public boolean isInInputScreen(int mouseX, int mouseY)
  {
    if (this.closedInput) {
      return true;
    }
    return (mouseX > this.inputPosX - 1) && (mouseX < this.inputPosX + this.inputLengthX + 1) && (mouseY > this.inputPosY - 1) && (mouseY < this.inputPosY + this.inputLengthY + 1);
  }
  
  public boolean isInInputFrame(int mouseX, int mouseY)
  {
    return (mouseX > this.inputPosX - 1) && (mouseX < this.inputPosX + this.inputLengthX + 1) && (mouseY > this.inputPosY - 1) && (mouseY < this.inputPosY + 15);
  }
  
  public boolean isInScreen(int mouseX, int mouseY)
  {
    return (isInInfoScreen(mouseX, mouseY)) || (isInInputScreen(mouseX, mouseY));
  }
  
  public boolean isInFrame(int mouseX, int mouseY)
  {
    return (isInInputScreen(mouseX, mouseY)) || (isInInputScreen(mouseX, mouseY));
  }
  
  private void calcInfo()
  {
    int max = LabyMod.getInstance().draw.getStringWidth(this.infoTitle);
    for (InfoMessage infoMessage : this.info)
    {
      int l = LabyMod.getInstance().draw.getStringWidth(infoMessage.message);
      if (l > max) {
        max = l;
      }
    }
    this.infoLengthX = (20 + max);
    this.infoLengthY = (40 + this.info.size() * 10);
    if (this.info.size() == 1) {
      posInfo();
    }
  }
  
  private void calcInput()
  {
    int max = LabyMod.getInstance().draw.getStringWidth(this.inputTitle);
    int l = LabyMod.getInstance().draw.getStringWidth(this.input);
    if (l > max) {
      max = l;
    }
    int k = 165;
    if (max > 165) {
      k = max + 10;
    }
    this.inputLengthX = k;
    this.inputLengthY = 62;
    
    this.inputPosX = (LabyMod.getInstance().draw.getWidth() / 2 - this.inputLengthX / 2);
    this.inputPosY = (LabyMod.getInstance().draw.getHeight() / 2 - this.inputLengthY / 2);
  }
  
  private void posInfo()
  {
    this.infoPosX = (LabyMod.getInstance().draw.getWidth() / 2 - this.infoLengthX / 2);
    this.infoPosY = (LabyMod.getInstance().draw.getHeight() / 2 - this.infoLengthY / 2);
  }
  
  private void drawInfo(int mouseX, int mouseY)
  {
    DrawUtils.a(this.infoPosX - 1, this.infoPosY - 1, this.infoPosX + this.infoLengthX + 1, this.infoPosY + this.infoLengthY + 1, java.awt.Color.BLACK.getRGB());
    DrawUtils.a(this.infoPosX, this.infoPosY, this.infoPosX + this.infoLengthX, this.infoPosY + this.infoLengthY, java.awt.Color.GRAY.getRGB());
    DrawUtils.a(this.infoPosX, this.infoPosY, this.infoPosX + this.infoLengthX, this.infoPosY + 15, java.awt.Color.WHITE.getRGB());
    
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("6") + this.infoTitle, this.infoPosX + 3, this.infoPosY + 3);
    String c = de.labystudio.utils.Color.cl("c");
    if ((mouseX > this.infoPosX + this.infoLengthX - 20) && (mouseX < this.infoPosX + this.infoLengthX) && (mouseY > this.infoPosY + 3) && (mouseY < this.infoPosY + 15)) {
      c = de.labystudio.utils.Color.cl("4");
    }
    LabyMod.getInstance().draw.drawString(c + "x", this.infoPosX + this.infoLengthX - 13, this.infoPosY + 3);
    
    int slot = 0;
    for (InfoMessage infoMessage : this.info)
    {
      LabyMod.getInstance().draw.drawString(infoMessage.message, this.infoPosX + 3, this.infoPosY + 20 + slot);
      slot += 10;
    }
    DrawUtils.a(this.infoPosX + this.infoLengthX / 2 - 25, this.infoPosY + this.infoLengthY - 15, this.infoPosX + this.infoLengthX / 2 + 25, this.infoPosY + this.infoLengthY - 2, java.awt.Color.BLACK.getRGB());
    DrawUtils.a(this.infoPosX + this.infoLengthX / 2 - 24, this.infoPosY + this.infoLengthY - 14, this.infoPosX + this.infoLengthX / 2 + 24, this.infoPosY + this.infoLengthY - 3, java.awt.Color.DARK_GRAY.getRGB());
    if ((mouseX > this.infoPosX + this.infoLengthX / 2 - 24) && (mouseX < this.infoPosX + this.infoLengthX / 2 + 24) && (mouseY > this.infoPosY + this.infoLengthY - 14) && (mouseY < this.infoPosY + this.infoLengthY - 3)) {
      DrawUtils.a(this.infoPosX + this.infoLengthX / 2 - 24, this.infoPosY + this.infoLengthY - 14, this.infoPosX + this.infoLengthX / 2 + 24, this.infoPosY + this.infoLengthY - 3, java.awt.Color.GRAY.getRGB() + 40);
    }
    LabyMod.getInstance().draw.drawCenteredString("OK", this.infoPosX + this.infoLengthX / 2, this.infoPosY + this.infoLengthY - 12);
    while (this.infoPosX + this.infoLengthX > LabyMod.getInstance().draw.getWidth()) {
      this.infoPosX -= 1;
    }
  }
  
  private void drawInput(int mouseX, int mouseY)
  {
    if (this.inputCallBack.tick(this.inputTarget))
    {
      resetinput();
      return;
    }
    DrawUtils.a(this.inputPosX - 1, this.inputPosY - 1, this.inputPosX + this.inputLengthX + 1, this.inputPosY + this.inputLengthY + 1, java.awt.Color.BLACK.getRGB());
    DrawUtils.a(this.inputPosX, this.inputPosY, this.inputPosX + this.inputLengthX, this.inputPosY + this.inputLengthY, java.awt.Color.GRAY.getRGB());
    DrawUtils.a(this.inputPosX, this.inputPosY, this.inputPosX + this.inputLengthX, this.inputPosY + 15, java.awt.Color.WHITE.getRGB());
    
    LabyMod.getInstance().draw.drawString(de.labystudio.utils.Color.cl("6") + this.inputTitle, this.inputPosX + 3, this.inputPosY + 3);
    String c = de.labystudio.utils.Color.cl("c");
    if ((mouseX > this.inputPosX + this.inputLengthX - 20) && (mouseX < this.inputPosX + this.inputLengthX) && (mouseY > this.inputPosY + 3) && (mouseY < this.inputPosY + 15)) {
      c = de.labystudio.utils.Color.cl("4");
    }
    LabyMod.getInstance().draw.drawString(c + "x", this.inputPosX + this.inputLengthX - 13, this.inputPosY + 3);
    
    LabyMod.getInstance().draw.drawString(this.input, this.inputPosX + 3, this.inputPosY + 20);
    
    DrawUtils.a(this.inputPosX + this.inputLengthX / 2 - 60, this.inputPosY + this.inputLengthY - 15, this.inputPosX + this.inputLengthX / 2 - 10, this.inputPosY + this.inputLengthY - 2, java.awt.Color.BLACK.getRGB());
    DrawUtils.a(this.inputPosX + this.inputLengthX / 2 - 60 + 1, this.inputPosY + this.inputLengthY - 14, this.inputPosX + this.inputLengthX / 2 - 11, this.inputPosY + this.inputLengthY - 3, java.awt.Color.DARK_GRAY.getRGB());
    if ((mouseX > this.inputPosX + this.inputLengthX / 2 - 60 + 1) && (mouseX < this.inputPosX + this.inputLengthX / 2 + 11) && (mouseY > this.inputPosY + this.inputLengthY - 14) && (mouseY < this.inputPosY + this.inputLengthY - 3)) {
      DrawUtils.a(this.inputPosX + this.inputLengthX / 2 - 60 + 1, this.inputPosY + this.inputLengthY - 14, this.inputPosX + this.inputLengthX / 2 - 11, this.inputPosY + this.inputLengthY - 3, java.awt.Color.GRAY.getRGB() + 40);
    }
    LabyMod.getInstance().draw.drawCenteredString("OK", this.inputPosX + this.inputLengthX / 2 - 35, this.inputPosY + this.inputLengthY - 12);
    
    DrawUtils.a(this.inputPosX + this.inputLengthX / 2 + 10, this.inputPosY + this.inputLengthY - 15, this.inputPosX + this.inputLengthX / 2 + 60, this.inputPosY + this.inputLengthY - 2, java.awt.Color.BLACK.getRGB());
    DrawUtils.a(this.inputPosX + this.inputLengthX / 2 + 11, this.inputPosY + this.inputLengthY - 14, this.inputPosX + this.inputLengthX / 2 + 60 - 1, this.inputPosY + this.inputLengthY - 3, java.awt.Color.DARK_GRAY.getRGB());
    if ((mouseX > this.inputPosX + this.inputLengthX / 2 + 10) && (mouseX < this.inputPosX + this.inputLengthX / 2 + 60) && (mouseY > this.inputPosY + this.inputLengthY - 14) && (mouseY < this.inputPosY + this.inputLengthY - 3)) {
      DrawUtils.a(this.inputPosX + this.inputLengthX / 2 + 11, this.inputPosY + this.inputLengthY - 14, this.inputPosX + this.inputLengthX / 2 + 60 - 1, this.inputPosY + this.inputLengthY - 3, java.awt.Color.GRAY.getRGB() + 40);
    }
    LabyMod.getInstance().draw.drawCenteredString("Cancel", this.inputPosX + this.inputLengthX / 2 + 35, this.inputPosY + this.inputLengthY - 12);
    
    this.inputField.a = (this.inputPosX + 3);
    this.inputField.f = (this.inputPosY + 32);
    
    this.inputField.a = (this.inputPosX + this.inputLengthX / 2 - 80);
    
    this.inputField.g();
  }
  
  public void mouseClicked(int mouseX, int mouseY, int mouseButton)
  {
    if (!isOpen()) {
      return;
    }
    if (isInInfoFrame(mouseX, mouseY))
    {
      this.infoClickX = (mouseX - this.infoPosX);
      this.infoClickY = (mouseY - this.infoPosY);
      this.infoDrag = true;
    }
    if (isInInputFrame(mouseX, mouseY))
    {
      this.inputClickX = (mouseX - this.inputPosX);
      this.inputClickY = (mouseY - this.inputPosY);
      this.inputDrag = true;
    }
    int slot;
    if (!this.info.isEmpty())
    {
      if ((mouseX > this.infoPosX + this.infoLengthX / 2 - 24) && (mouseX < this.infoPosX + this.infoLengthX / 2 + 24) && (mouseY > this.infoPosY + this.infoLengthY - 14) && (mouseY < this.infoPosY + this.infoLengthY - 3)) {
        resetInfo();
      }
      if ((mouseX > this.infoPosX + this.infoLengthX - 20) && (mouseX < this.infoPosX + this.infoLengthX) && (mouseY > this.infoPosY + 3) && (mouseY < this.infoPosY + 15)) {
        resetInfo();
      }
      slot = 0;
      for (InfoMessage infoMessage : this.info)
      {
        LabyMod.getInstance().draw.drawString(infoMessage.message, this.infoPosX + 3, this.infoPosY + 20 + slot);
        int l = this.infoLengthX;
        TeamSpeakUser user = TeamSpeakController.getInstance().getUser(infoMessage.clientId);
        if (user != null) {
          l = LabyMod.getInstance().draw.getStringWidth(user.getNickName());
        }
        if ((mouseX > this.infoPosX) && (mouseX < this.infoPosX + l) && (mouseY > this.infoPosY + 20 + slot) && (mouseY < this.infoPosY + 20 + slot + 10))
        {
          TeamSpeak.callBack = true;
          TeamSpeak.callBackClient = infoMessage.clientId;
          break;
        }
        slot += 10;
      }
    }
    if (this.input != null)
    {
      if ((mouseX > this.inputPosX + this.inputLengthX / 2 - 60 + 1) && (mouseX < this.inputPosX + this.inputLengthX / 2 + 11) && (mouseY > this.inputPosY + this.inputLengthY - 14) && (mouseY < this.inputPosY + this.inputLengthY - 3))
      {
        this.inputCallBack.ok(this.inputTarget, this.inputField.b());
        resetinput();
      }
      if ((mouseX > this.inputPosX + this.inputLengthX / 2 + 10) && (mouseX < this.inputPosX + this.inputLengthX / 2 + 60) && (mouseY > this.inputPosY + this.inputLengthY - 14) && (mouseY < this.inputPosY + this.inputLengthY - 3))
      {
        this.inputCallBack.cancel();
        resetinput();
      }
      if ((mouseX > this.inputPosX + this.inputLengthX - 20) && (mouseX < this.inputPosX + this.inputLengthX) && (mouseY > this.inputPosY + 3) && (mouseY < this.inputPosY + 15))
      {
        this.inputCallBack.cancel();
        resetinput();
      }
    }
    if (this.inputField != null) {
      this.inputField.a(mouseX, mouseY, mouseButton);
    }
  }
  
  public void KeyTyped(char typedChar, int keyCode)
  {
    if (!isOpen()) {
      return;
    }
    if ((keyCode == 28) && (this.inputField != null) && (this.inputField.m()) && 
      (this.inputCallBack != null) && (this.inputField != null))
    {
      this.inputCallBack.ok(this.inputTarget, this.inputField.b());
      resetinput();
    }
    if (this.inputField != null) {
      this.inputField.a(typedChar, keyCode);
    }
  }
  
  public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
  {
    if (!isOpen()) {
      return;
    }
    if ((this.infoDrag) && (!this.inputDrag))
    {
      if ((mouseX - this.infoClickX > 0) && (mouseX - this.infoClickX < LabyMod.getInstance().draw.getWidth() - this.infoLengthX)) {
        this.infoPosX = (mouseX - this.infoClickX);
      }
      if ((mouseY - this.infoClickY > 0) && (mouseY - this.infoClickY < LabyMod.getInstance().draw.getHeight() - this.infoLengthY)) {
        this.infoPosY = (mouseY - this.infoClickY);
      }
    }
    if (this.inputDrag)
    {
      if ((mouseX - this.inputClickX > 0) && (mouseX - this.inputClickX < LabyMod.getInstance().draw.getWidth() - this.inputLengthX)) {
        this.inputPosX = (mouseX - this.inputClickX);
      }
      if ((mouseY - this.inputClickY > 0) && (mouseY - this.inputClickY < LabyMod.getInstance().draw.getHeight() - this.inputLengthY)) {
        this.inputPosY = (mouseY - this.inputClickY);
      }
    }
  }
  
  public void mouseReleased(int mouseX, int mouseY, int state)
  {
    this.infoDrag = false;
    this.inputDrag = false;
    this.closedInfo = false;
    this.closedInput = false;
  }
  
  public boolean allow()
  {
    return (!this.closedInfo) && (!this.closedInput);
  }
  
  public boolean isOpen()
  {
    return (!this.info.isEmpty()) || (this.input != null);
  }
}
