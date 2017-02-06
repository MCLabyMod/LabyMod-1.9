package de.labystudio.gui;

import bcf;
import bcz;
import bvi;
import de.labystudio.gui.extras.ModGuiTextField;
import de.labystudio.labymod.LabyMod;
import de.labystudio.language.L;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.Utils;
import de.zockermaus.ts3.Chat;
import de.zockermaus.ts3.EnumTargetMode;
import de.zockermaus.ts3.Message;
import de.zockermaus.ts3.PopUpCallback;
import de.zockermaus.ts3.TeamSpeak;
import de.zockermaus.ts3.TeamSpeakBridge;
import de.zockermaus.ts3.TeamSpeakChannel;
import de.zockermaus.ts3.TeamSpeakChannelGroup;
import de.zockermaus.ts3.TeamSpeakController;
import de.zockermaus.ts3.TeamSpeakOverlayWindow;
import de.zockermaus.ts3.TeamSpeakServerGroup;
import de.zockermaus.ts3.TeamSpeakUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiTeamSpeak
  extends GuiMenuScreen
{
  private DrawUtils draw;
  
  public GuiTeamSpeak()
  {
    super(null);
    this.childScreen = this;
    this.draw = LabyMod.getInstance().draw;
    this.id = "TeamSpeak";
  }
  
  boolean boxEnabled = false;
  boolean boxIsUser = false;
  int boxId = 0;
  int boxPosX = 0;
  int boxPosY = 0;
  int boxLengthX = 0;
  int boxLengthY = 0;
  boolean drag = false;
  boolean drop = false;
  int dragVisible = 0;
  boolean dropFocus = false;
  int dropX = 0;
  int dropY = 0;
  boolean dragIsUser = false;
  int dragId = 0;
  boolean changeNickName = false;
  ModGuiTextField nickNameField;
  ModGuiTextField chatInputField;
  long lastClick = 0L;
  boolean allowChatScroll;
  boolean allowChannelScroll;
  int yMouse = 0;
  int doubleClickDelay = 500;
  boolean doubleClickIsUser;
  int doubleClickTarget = 0;
  bcz connectButton;
  int connect = 0;
  
  public void b()
  {
    TeamSpeak.setDefaultScreen();
    this.changeNickName = false;
    this.drag = false;
    closeBox();
    this.connect = 0;
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    
    this.chatInputField = new ModGuiTextField(0, LabyMod.getInstance().mc.k, 5, this.draw.getHeight() - 17, this.draw.getWidth() - 10, 12);
    this.chatInputField.a(TeamSpeak.inputString);
    this.chatInputField.f(200);
    
    this.nickNameField = new ModGuiTextField(0, LabyMod.getInstance().mc.k, 0, 0, TeamSpeak.xSplit - 26, 9);
    this.nickNameField.f(20);
    
    this.connectButton = new bcz(1, this.l / 2 - 100, this.m / 2, L._("gui_ts_connect", new Object[0]));
    if (TeamSpeakController.getInstance() != null) {
      this.connectButton.m = (!TeamSpeakController.getInstance().isConnectionEstablished());
    }
    this.n.add(this.connectButton);
    while (TeamSpeak.xSplit > LabyMod.getInstance().draw.getWidth() / 4 * 3) {
      TeamSpeak.xSplit -= 1;
    }
    while (TeamSpeak.xSplit < 200) {
      TeamSpeak.xSplit += 1;
    }
    while (TeamSpeak.ySplit > LabyMod.getInstance().draw.getHeight() - 50) {
      TeamSpeak.ySplit -= 1;
    }
    while (TeamSpeak.ySplit < 50) {
      TeamSpeak.ySplit += 1;
    }
    this.boxEnabled = false;
    
    super.b();
  }
  
  public void a(bcz button)
    throws IOException
  {
    switch (button.k)
    {
    case 0: 
      break;
    case 1: 
      this.connect = 1;
    }
    super.actionPermformed(button);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1) {
      LabyMod.getInstance().back();
    }
    if (TeamSpeak.overlayWindows != null)
    {
      TeamSpeak.overlayWindows.KeyTyped(typedChar, keyCode);
      if (!TeamSpeak.overlayWindows.allow()) {
        return;
      }
    }
    if (keyCode == 28) {
      if ((this.changeNickName) && (this.nickNameField != null) && (this.nickNameField.m()))
      {
        changeNickname();
      }
      else if ((!TeamSpeak.inputString.isEmpty()) && (this.chatInputField != null) && (this.chatInputField.m()))
      {
        TeamSpeakBridge.sendTextMessage(TeamSpeak.selectedChat, TeamSpeak.inputString);
        TeamSpeak.inputString = "";
        this.chatInputField.a("");
      }
    }
    if (this.chatInputField.a(typedChar, keyCode)) {
      TeamSpeak.inputString = this.chatInputField.b();
    }
    if (this.changeNickName) {
      this.nickNameField.a(typedChar, keyCode);
    }
    super.a(typedChar, keyCode);
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    if (boxAction(mouseX, mouseY, mouseButton))
    {
      super.a(mouseX, mouseY, mouseButton);
      return;
    }
    closeBox();
    if (TeamSpeak.overlayWindows != null)
    {
      TeamSpeak.overlayWindows.mouseClicked(mouseX, mouseY, mouseButton);
      if (TeamSpeak.overlayWindows.isInScreen(mouseX, mouseY))
      {
        super.a(mouseX, mouseY, mouseButton);
        return;
      }
    }
    if (this.changeNickName) {
      changeNickname();
    }
    this.chatInputField.a(mouseX, mouseY, mouseButton);
    if (this.changeNickName) {
      this.nickNameField.a(mouseX, mouseY, mouseButton);
    }
    this.clickX = (mouseX - TeamSpeak.xSplit);
    this.clickY = (mouseY - TeamSpeak.ySplit);
    if ((mouseX > TeamSpeak.xSplit - 5) && (mouseX < TeamSpeak.xSplit + 5)) {
      this.moveX = true;
    }
    if ((mouseY > TeamSpeak.ySplit - 5) && (mouseY < TeamSpeak.ySplit + 5)) {
      this.moveY = true;
    }
    if ((this.moveX) || (this.moveY)) {
      return;
    }
    switchChat(mouseX, mouseY, mouseButton);
    channelAction(mouseX, mouseY, mouseButton);
    menuAction(mouseX, mouseY, mouseButton);
    
    super.a(mouseX, mouseY, mouseButton);
  }
  
  public void k()
    throws IOException
  {
    super.k();
    int var1 = Mouse.getEventDWheel();
    if (var1 != 0)
    {
      if (var1 > 1) {
        var1 = 1;
      }
      if (var1 < -1) {
        var1 = -1;
      }
      if (this.boxEnabled) {
        return;
      }
      if (this.changeNickName) {
        return;
      }
      if (this.yMouse > TeamSpeak.ySplit)
      {
        if (var1 > 0)
        {
          if (this.allowChatScroll) {
            TeamSpeak.scrollChat -= 10;
          }
        }
        else if (TeamSpeak.scrollChat < 0) {
          TeamSpeak.scrollChat += 10;
        }
      }
      else if (var1 > 0)
      {
        if (TeamSpeak.scrollChannel < 0) {
          TeamSpeak.scrollChannel += 20;
        }
      }
      else if (this.allowChannelScroll) {
        TeamSpeak.scrollChannel -= 20;
      }
    }
  }
  
  boolean init = false;
  int clickX;
  int clickY;
  boolean moveX;
  boolean moveY;
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    callBackListener(mouseX, mouseY);
    
    this.connectButton.m = (!TeamSpeakController.getInstance().isConnectionEstablished());
    if (this.connectButton != null)
    {
      if (this.connect == 2)
      {
        this.connect = 0;
        TeamSpeakController.getInstance().connect();
      }
      if (this.connect == 1) {
        this.connect += 1;
      }
    }
    if (!TeamSpeakController.getInstance().isConnectionEstablished())
    {
      String s = TeamSpeakController.getInstance().serverIP + ":" + TeamSpeakController.getInstance().serverPort;
      if ((TeamSpeakController.getInstance().serverIP.isEmpty()) || (TeamSpeakController.getInstance().serverPort == 0))
      {
        this.draw.drawCenteredString(Color.cl("c") + L._("gui_ts_error_connect", new Object[0]), this.l / 2, this.m / 2 - 15);
        if (this.connectButton != null)
        {
          this.connectButton.m = true;
          if (!this.connectButton.l) {
            this.connectButton.j = L._("gui_ts_connecting", new Object[0]);
          } else {
            this.connectButton.j = L._("gui_ts_connect", new Object[0]);
          }
        }
      }
      else
      {
        this.draw.drawCenteredString(Color.cl("a") + L._("gui_ts_connectto", new Object[] { s }), this.l / 2, this.m / 2 - 15);
      }
      if (this.init)
      {
        this.init = false;
        b();
      }
      super.a(mouseX, mouseY, partialTicks);
      return;
    }
    if ((TeamSpeakController.getInstance().serverIP.isEmpty()) || (TeamSpeakController.getInstance().serverPort == 0) || (TeamSpeakUser.amount() == 0) || (TeamSpeakChannel.amount() == 0))
    {
      if (this.connectButton != null) {
        this.connectButton.m = false;
      }
      this.draw.drawCenteredString(Color.cl("c") + L._("gui_ts_noserverfound", new Object[0]), this.l / 2, this.m / 2 - 15);
      this.draw.drawCenteredString(Color.cl("7") + L._("gui_ts_tryrestart", new Object[0]), this.l / 2, this.m / 2 - 5);
      super.a(mouseX, mouseY, partialTicks);
      return;
    }
    if (!this.init)
    {
      this.init = true;
      b();
    }
    this.chatInputField.g();
    
    drawChat();
    drawChannel(mouseX, mouseY);
    drawClientInfo();
    drawMenu(mouseX, mouseY);
    if (this.changeNickName) {
      this.nickNameField.g();
    }
    this.yMouse = mouseY;
    if ((TeamSpeak.selectedUser != -1) && 
      (TeamSpeakController.getInstance().getUser(TeamSpeak.selectedUser) == null)) {
      TeamSpeak.selectedUser = -1;
    }
    if ((TeamSpeak.selectedChannel != -1) && 
      (TeamSpeakController.getInstance().getChannel(TeamSpeak.selectedChannel) == null)) {
      TeamSpeak.selectedChannel = -1;
    }
    if ((TeamSpeak.selectedChannel == -1) && (TeamSpeak.selectedUser == -1) && 
      (TeamSpeakController.getInstance().me() != null)) {
      TeamSpeak.selectedUser = TeamSpeakController.getInstance().me().getClientId();
    }
    if (TeamSpeak.overlayWindows != null)
    {
      TeamSpeak.overlayWindows.drawWindow(mouseX, mouseY);
      if (TeamSpeak.overlayWindows.isInScreen(mouseX, mouseY))
      {
        super.a(mouseX, mouseY, partialTicks);
        drawBox(mouseX, mouseY);
        return;
      }
    }
    drawBox(mouseX, mouseY);
    if ((mouseX > TeamSpeak.xSplit - 5) && (mouseX < TeamSpeak.xSplit + 5) && (mouseY > TeamSpeak.ySplit - 5) && (mouseY < TeamSpeak.ySplit + 5))
    {
      LabyMod.getInstance().draw.drawCenteredString(Color.cl("7") + "+", mouseX + 1, mouseY - 2);
    }
    else
    {
      if ((mouseX > TeamSpeak.xSplit - 5) && (mouseX < TeamSpeak.xSplit + 5) && (mouseY < TeamSpeak.ySplit))
      {
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("7") + "...", mouseX + 1, mouseY - 6);
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("7") + "...", mouseX + 1, mouseY - 3);
      }
      if ((mouseY > TeamSpeak.ySplit - 5) && (mouseY < TeamSpeak.ySplit + 5)) {
        LabyMod.getInstance().draw.drawCenteredString(Color.cl("7") + "||", mouseX + 1, mouseY - 3);
      }
    }
    if (this.drag) {
      drawDrag(mouseX, mouseY);
    }
    super.a(mouseX, mouseY, partialTicks);
  }
  
  protected void a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
  {
    if (TeamSpeak.overlayWindows != null)
    {
      TeamSpeak.overlayWindows.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      if (TeamSpeak.overlayWindows.isInScreen(mouseX, mouseY))
      {
        super.a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        return;
      }
    }
    if ((this.moveX) && 
      (mouseX - this.clickX < LabyMod.getInstance().draw.getWidth() / 4 * 3) && (mouseX - this.clickX > 200)) {
      TeamSpeak.xSplit = mouseX - this.clickX;
    }
    if ((this.moveY) && 
      (mouseY - this.clickY < LabyMod.getInstance().draw.getHeight() - 50) && (mouseY - this.clickY > 50)) {
      TeamSpeak.ySplit = mouseY - this.clickY;
    }
    if (this.drag) {
      this.dragVisible += 1;
    }
    super.a(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
  }
  
  protected void b(int mouseX, int mouseY, int state)
  {
    if (TeamSpeak.overlayWindows != null)
    {
      TeamSpeak.overlayWindows.mouseReleased(mouseX, mouseY, state);
      if (TeamSpeak.overlayWindows.isInScreen(mouseX, mouseY))
      {
        super.b(mouseX, mouseY, state);
        return;
      }
    }
    if ((this.moveX) || (this.moveY)) {
      b();
    }
    this.moveX = false;
    this.moveY = false;
    if (this.drag) {
      setDrop(mouseX, mouseY);
    }
    super.b(mouseX, mouseY, state);
  }
  
  private void drawChat()
  {
    DrawUtils.a(5, TeamSpeak.ySplit, this.draw.getWidth() - 5, this.draw.getHeight() - 20, Integer.MIN_VALUE);
    Chat selected = null;
    int slot = 0;
    for (Chat chat : TeamSpeak.chats)
    {
      DrawUtils.a(5 + slot, this.draw.getHeight() - 33, 50 + slot, this.draw.getHeight() - 20, 835640000);
      c = "";
      if (TeamSpeak.selectedChat == chat.getSlotId())
      {
        DrawUtils.a(5 + slot + 1, this.draw.getHeight() - 33 + 1, 50 + slot - 1, this.draw.getHeight() - 20 - 1, Integer.MAX_VALUE);
        selected = chat;
      }
      String name = Utils.normalizeString(chat.getTargetMode().name());
      if (chat.getTargetMode() == EnumTargetMode.USER)
      {
        name = chat.getChatOwner().getNickName();
        if (name.length() > 7) {
          name = name.substring(0, 7);
        }
      }
      this.draw.drawCenteredString(c + name, 27 + slot, this.draw.getHeight() - 30);
      slot += 46;
    }
    String c;
    if (selected != null)
    {
      int y = 0;
      List<Message> list = new ArrayList();
      list.addAll(selected.getLog());
      Collections.reverse(list);
      for (Message msg : list)
      {
        if ((LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat >= TeamSpeak.ySplit) && (LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat <= LabyMod.getInstance().draw.getHeight() - 45)) {
          if (msg.getUser() == null) {
            LabyMod.getInstance().draw.drawString(msg.getMessage(), 8.0D, LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat);
          } else {
            LabyMod.getInstance().draw.drawString(Color.cl("9") + msg.getUser().getNickName() + Color.cl("7") + ": " + msg.getMessage(), 8.0D, LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat);
          }
        }
        y += 10;
      }
      this.allowChatScroll = (LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat < TeamSpeak.ySplit - 10);
    }
  }
  
  public void switchChat(int mouseX, int mouseY, int mouseButton)
  {
    int slot = 0;
    Chat close = null;
    for (Iterator localIterator1 = TeamSpeak.chats.iterator(); localIterator1.hasNext();)
    {
      chat = (Chat)localIterator1.next();
      if ((mouseX > 5 + slot) && (mouseX < 50 + slot) && (mouseY > this.draw.getHeight() - 33) && (mouseY < this.draw.getHeight() - 20))
      {
        TeamSpeak.scrollChat = 0;
        if (mouseButton == 0) {
          TeamSpeak.selectedChat = chat.getSlotId();
        }
        if ((mouseButton == 1) && 
          (chat.getSlotId() >= 0))
        {
          TeamSpeak.selectedChat = -1;
          close = chat;
        }
      }
      slot += 46;
    }
    Chat chat;
    if (close != null) {
      TeamSpeak.chats.remove(close);
    }
    Chat selected = null;
    for (Chat chat : TeamSpeak.chats) {
      if (TeamSpeak.selectedChat == chat.getSlotId()) {
        selected = chat;
      }
    }
    int y;
    if (selected != null)
    {
      y = 0;
      List<Message> list = new ArrayList();
      list.addAll(selected.getLog());
      Collections.reverse(list);
      for (Message msg : list)
      {
        if ((LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat >= TeamSpeak.ySplit) && (LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat <= LabyMod.getInstance().draw.getHeight() - 45) && 
          (msg.getUser() != null))
        {
          if ((mouseX > 8) && (mouseX < LabyMod.getInstance().draw.getStringWidth(msg.getUser().getNickName()) + 8) && (mouseY > LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat) && (mouseY < LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat + 10)) {
            openBox(true, msg.getUser().getClientId(), mouseX, mouseY);
          }
          if ((mouseX > 8) && (mouseX > LabyMod.getInstance().draw.getStringWidth(msg.getUser().getNickName()) + 8) && (mouseY > LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat) && (mouseY < LabyMod.getInstance().draw.getHeight() - 45 - y - TeamSpeak.scrollChat + 10))
          {
            ArrayList<String> urls = Utils.extractDomains(msg.getMessage());
            if (!urls.isEmpty()) {
              LabyMod.getInstance().openWebpage((String)urls.get(0));
            }
          }
        }
        y += 10;
      }
    }
  }
  
  private void drawChannel(int mouseX, int mouseY)
  {
    DrawUtils.a(5, TeamSpeak.ySplit - 1, TeamSpeak.xSplit, 25, Integer.MIN_VALUE);
    ArrayList<TeamSpeakChannel> list = new ArrayList();
    int next = 0;
    for (TeamSpeakChannel channel : TeamSpeakBridge.getChannels()) {
      if (channel.getChannelOrder() == 0)
      {
        list.add(channel);
      }
      else
      {
        TeamSpeakChannel ch = TeamSpeak.getFromOrder(next);
        if (ch == null) {
          break;
        }
        next = ch.getChannelId();
        list.add(ch);
      }
    }
    boolean isInRegion = false;
    int regionChannelId = 0;
    
    int slot = 0;
    TeamSpeak.outOfView.clear();
    for (TeamSpeakChannel channel : list)
    {
      if ((mouseX > 5) && (mouseX < TeamSpeak.xSplit) && (mouseY > 30 + slot + TeamSpeak.scrollChannel) && (mouseY < 30 + slot + TeamSpeak.scrollChannel + 11) && (30 + slot + TeamSpeak.scrollChannel + 10 < TeamSpeak.ySplit) && (30 + slot + TeamSpeak.scrollChannel > 20))
      {
        isInRegion = true;
        regionChannelId = channel.getChannelId();
        if (this.drag) {
          a(5, 30 + slot + TeamSpeak.scrollChannel - 1, TeamSpeak.xSplit, 30 + slot + TeamSpeak.scrollChannel + 10, 1230000000);
        }
      }
      if (30 + slot + TeamSpeak.scrollChannel < 20) {
        TeamSpeak.outOfView.add(Integer.valueOf(channel.getChannelId()));
      }
      String c2;
      if ((30 + slot + TeamSpeak.scrollChannel + 10 < TeamSpeak.ySplit) && (30 + slot + TeamSpeak.scrollChannel > 20))
      {
        String c = Color.cl("7");
        if (TeamSpeak.selectedChannel == channel.getChannelId()) {
          a(5, 30 + slot + TeamSpeak.scrollChannel - 1, TeamSpeak.xSplit, 30 + slot + TeamSpeak.scrollChannel + 10, 1230000000);
        }
        if (TeamSpeak.isSpacer(channel.getChannelName()))
        {
          LabyMod.getInstance().draw.drawCenteredString(c + TeamSpeak.toStarSpacer(channel.getChannelName(), TeamSpeak.xSplit), TeamSpeak.xSplit / 2, 30 + slot + TeamSpeak.scrollChannel);
        }
        else if (TeamSpeak.isStarSpacer(channel.getChannelName()))
        {
          LabyMod.getInstance().draw.drawCenteredString(c + TeamSpeak.toStarSpacer(channel.getChannelName(), TeamSpeak.xSplit), TeamSpeak.xSplit / 2, 30 + slot + TeamSpeak.scrollChannel);
        }
        else if (TeamSpeak.isCenterSpacer(channel.getChannelName()))
        {
          LabyMod.getInstance().draw.drawCenteredString(c + TeamSpeak.toCenterSpacer(channel.getChannelName()), TeamSpeak.xSplit / 2, 30 + slot + TeamSpeak.scrollChannel);
        }
        else
        {
          c2 = Color.cl("2");
          if (channel.getIsPassword()) {
            c2 = Color.cl("e");
          }
          if (!channel.getSubscription()) {
            c2 = Color.cl("b");
          }
          if ((channel.getTotalClients() >= channel.getMaxClients()) && (channel.getMaxClients() != -1)) {
            c2 = Color.cl("4");
          }
          LabyMod.getInstance().draw.drawString(c2 + "⬛" + c + channel.getChannelName(), 5.0D, 30 + slot + TeamSpeak.scrollChannel);
        }
      }
      ArrayList<TeamSpeakUser> users = new ArrayList();
      users.addAll(TeamSpeakBridge.getUsers());
      
      Collections.sort(users, new Comparator()
      {
        public int compare(TeamSpeakUser o1, TeamSpeakUser o2)
        {
          if ((o1 == null) || (o2 == null)) {
            return 0;
          }
          if (o1.getTalkPower() < o2.getTalkPower()) {
            return 1;
          }
          if (o1.getTalkPower() > o2.getTalkPower()) {
            return -1;
          }
          return 0;
        }
      });
      for (TeamSpeakUser user : users) {
        if (user != null) {
          if (user.getChannelId() == channel.getChannelId())
          {
            slot += 10;
            if ((30 + slot + TeamSpeak.scrollChannel + 10 < TeamSpeak.ySplit) && (30 + slot + TeamSpeak.scrollChannel > 20))
            {
              String c = "";
              if (TeamSpeak.selectedUser == user.getClientId()) {
                a(5, 30 + slot + TeamSpeak.scrollChannel - 1, TeamSpeak.xSplit, 30 + slot + TeamSpeak.scrollChannel + 10, 1230000000);
              }
              if ((TeamSpeakController.getInstance().me() != null) && 
                (user.getClientId() == TeamSpeakController.getInstance().me().getClientId()))
              {
                c = c + Color.cl("l");
                
                this.nickNameField.a = 25;
                this.nickNameField.f = (30 + slot + TeamSpeak.scrollChannel);
              }
              String groups = "";
              if (TeamSpeak.teamSpeakGroupPrefix)
              {
                ArrayList<TeamSpeakServerGroup> g = new ArrayList();
                g.addAll(TeamSpeakServerGroup.getGroups());
                for (TeamSpeakServerGroup group : g) {
                  if (group != null) {
                    if ((user.getServerGroups().contains(Integer.valueOf(group.getSgid()))) && (group.getType() == 1) && (group.getIconId() != 0)) {
                      groups = groups + Color.cl("b") + "[" + TeamSpeak.fix(group.getGroupName()) + "] ";
                    }
                  }
                }
              }
              LabyMod.getInstance().draw.drawString(TeamSpeak.getTalkColor(user) + "  ⬤ " + Color.cl("f") + groups + Color.cl("f") + c + user.getNickName() + TeamSpeak.getAway(user), 5.0D, 30 + slot + TeamSpeak.scrollChannel);
            }
          }
        }
      }
      slot += 10;
    }
    this.allowChannelScroll = (30 + slot + TeamSpeak.scrollChannel > TeamSpeak.ySplit - 10);
    if (30 + slot + TeamSpeak.scrollChannel < TeamSpeak.ySplit - 30) {
      TeamSpeak.scrollChannel += 10;
    }
    if (TeamSpeak.scrollChannel > 20) {
      TeamSpeak.scrollChannel -= 10;
    }
    drop(regionChannelId, isInRegion);
  }
  
  private void channelAction(int mouseX, int mouseY, int mouseButton)
  {
    ArrayList<TeamSpeakChannel> list = new ArrayList();
    int next = 0;
    for (Iterator localIterator1 = TeamSpeakBridge.getChannels().iterator(); localIterator1.hasNext();)
    {
      channel = (TeamSpeakChannel)localIterator1.next();
      TeamSpeakChannel ch = TeamSpeak.getFromOrder(next);
      if (channel.getChannelOrder() == 0)
      {
        list.add(channel);
      }
      else
      {
        if (ch == null) {
          break;
        }
        next = ch.getChannelId();
        list.add(ch);
      }
    }
    TeamSpeakChannel channel;
    int slot = 0;
    for (TeamSpeakChannel channel : list)
    {
      if ((30 + slot + TeamSpeak.scrollChannel + 5 < TeamSpeak.ySplit) && (30 + slot + TeamSpeak.scrollChannel > 20) && 
        (mouseX > 5) && (mouseX < TeamSpeak.xSplit) && (mouseY > 30 + slot + TeamSpeak.scrollChannel) && (mouseY < 30 + slot + TeamSpeak.scrollChannel + 10))
      {
        TeamSpeak.selectedChannel = channel.getChannelId();
        TeamSpeak.selectedUser = -1;
        if (mouseButton == 1)
        {
          openBox(false, channel.getChannelId(), mouseX, mouseY);
          return;
        }
        if ((this.lastClick + this.doubleClickDelay > System.currentTimeMillis()) && (this.doubleClickTarget == channel.getChannelId()) && (!this.doubleClickIsUser) && 
          (TeamSpeakController.getInstance().me() != null) && (TeamSpeakController.getInstance().me().getChannelId() != channel.getChannelId()))
        {
          if (channel.getIsPassword()) {
            TeamSpeak.overlayWindows.openInput(channel.getChannelId(), L._("gui_ts_channelpassword_title", new Object[0]), L._("gui_ts_channelpassword_content", new Object[] { channel.getChannelName() }), new PopUpCallback()
            {
              public void ok(int cid, String message)
              {
                TeamSpeakBridge.moveClient(TeamSpeakController.getInstance().me().getClientId(), cid);
              }
              
              public void ok() {}
              
              public void cancel() {}
              
              public boolean tick(int cid)
              {
                if ((TeamSpeakController.getInstance().me() != null) && (TeamSpeakController.getInstance().me().getChannelId() == cid)) {
                  return true;
                }
                return false;
              }
            });
          }
          TeamSpeakBridge.moveClient(TeamSpeakController.getInstance().me().getClientId(), channel.getChannelId());
        }
        this.doubleClickTarget = channel.getChannelId();
        this.doubleClickIsUser = false;
        this.lastClick = System.currentTimeMillis();
      }
      ArrayList<TeamSpeakUser> users = new ArrayList();
      users.addAll(TeamSpeakBridge.getUsers());
      Collections.sort(users, new Comparator()
      {
        public int compare(TeamSpeakUser o1, TeamSpeakUser o2)
        {
          if ((o1 == null) || (o2 == null)) {
            return 0;
          }
          if (o1.getTalkPower() < o2.getTalkPower()) {
            return 1;
          }
          if (o1.getTalkPower() > o2.getTalkPower()) {
            return -1;
          }
          return 0;
        }
      });
      for (TeamSpeakUser user : users) {
        if (user.getChannelId() == channel.getChannelId())
        {
          slot += 10;
          if ((30 + slot + TeamSpeak.scrollChannel + 5 < TeamSpeak.ySplit) && (30 + slot + TeamSpeak.scrollChannel > 20) && 
            (mouseX > 5) && (mouseX < TeamSpeak.xSplit) && (mouseY > 30 + slot + TeamSpeak.scrollChannel) && (mouseY < 30 + slot + TeamSpeak.scrollChannel + 10))
          {
            TeamSpeak.selectedUser = user.getClientId();
            TeamSpeak.selectedChannel = -1;
            if (mouseButton == 1)
            {
              openBox(true, user.getClientId(), mouseX, mouseY);
              return;
            }
            drag(true, user.getClientId());
            if ((this.lastClick + this.doubleClickDelay > System.currentTimeMillis()) && (this.doubleClickTarget == user.getClientId()) && (this.doubleClickIsUser)) {
              if ((TeamSpeakController.getInstance().me() != null) && (user.getClientId() != TeamSpeakController.getInstance().me().getClientId()))
              {
                TeamSpeak.addChat(user, TeamSpeakController.getInstance().me(), null, EnumTargetMode.USER);
                TeamSpeak.selectedChat = user.getClientId();
              }
              else
              {
                openNickNameBox();
              }
            }
            this.doubleClickTarget = user.getClientId();
            this.doubleClickIsUser = true;
            this.lastClick = System.currentTimeMillis();
          }
        }
      }
      slot += 10;
    }
  }
  
  private void drawClientInfo()
  {
    if (TeamSpeak.selectedUser != -1)
    {
      TeamSpeakUser user = TeamSpeakController.getInstance().getUser(TeamSpeak.selectedUser);
      if (user == null) {
        return;
      }
      int y = 30;
      int x = TeamSpeak.xSplit + 10;
      drawInfo(Color.cl("7") + L._("gui_ts_user_nickname", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      drawInfo(Color.cl("7") + L._("gui_ts_user_country", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      drawInfo(Color.cl("7") + L._("gui_ts_user_talkpower", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      
      y = 30;
      drawInfo(user.getNickName(), x + 70, y);y += 12;
      drawInfo("" + TeamSpeak.country(user.getCountry()), x + 70, y);y += 12;
      drawInfo("" + user.getTalkPower(), x + 70, y);y += 12;
      
      y += 10;
      drawInfo(Color.cl("7") + L._("gui_ts_user_servergroups", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      if (user.getServerGroups() == null) {
        return;
      }
      for (Iterator localIterator = user.getServerGroups().iterator(); localIterator.hasNext();)
      {
        int groupId = ((Integer)localIterator.next()).intValue();
        TeamSpeakServerGroup group = TeamSpeakController.getInstance().getServerGroup(groupId);
        if (group != null)
        {
          drawInfo(TeamSpeak.fix(group.getGroupName()), x, y);y += 12;
        }
        else
        {
          drawInfo("" + groupId, x, y);y += 12;
        }
      }
      y += 10;
      drawInfo(Color.cl("7") + L._("gui_ts_user_channelgroups", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      TeamSpeakChannelGroup group = TeamSpeakController.getInstance().getChannelGroup(user.getChannelGroupId());
      if (group != null)
      {
        drawInfo(TeamSpeak.fix(group.getGroupName()), x, y);y += 12;
      }
      else
      {
        drawInfo("" + user.getChannelGroupId(), x, y);y += 12;
      }
      y += 10;
      if (!user.hasClientInputHardware())
      {
        drawInfo(Color.cl("c") + L._("gui_ts_user_micoff", new Object[0]), x, y);y += 12;
      }
      if (user.hasClientInputMuted())
      {
        drawInfo(Color.cl("c") + L._("gui_ts_user_micmute", new Object[0]), x, y);y += 12;
      }
      if (!user.hasClientOutputHardware())
      {
        drawInfo(Color.cl("4") + L._("gui_ts_user_soundoff", new Object[0]), x, y);y += 12;
      }
      if (user.hasClientOutputMuted())
      {
        drawInfo(Color.cl("4") + L._("gui_ts_user_soundmute", new Object[0]), x, y);y += 12;
      }
    }
    else if (TeamSpeak.selectedChannel != -1)
    {
      TeamSpeakChannel channel = TeamSpeakController.getInstance().getChannel(TeamSpeak.selectedChannel);
      if (channel == null) {
        return;
      }
      int y = 30;
      int x = TeamSpeak.xSplit + 10;
      drawInfo(Color.cl("7") + L._("gui_ts_channel_name", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      if ((channel != null) && (channel.getTopic() != null) && (!channel.getTopic().isEmpty()))
      {
        drawInfo(Color.cl("7") + L._("gui_ts_channel_topic", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      }
      drawInfo(Color.cl("7") + L._("gui_ts_channel_codec", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      drawInfo(Color.cl("7") + L._("gui_ts_channel_codecquality", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      drawInfo(Color.cl("7") + L._("gui_ts_channel_type", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      drawInfo(Color.cl("7") + L._("gui_ts_channel_currentclients", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      drawInfo(Color.cl("7") + L._("gui_ts_channel_subscriptionsatus", new Object[0]) + Color.cl("f") + ":", x, y);y += 12;
      
      y = 30;
      x += 110;
      drawInfo(channel.getChannelName(), x, y);y += 12;
      if (!channel.getTopic().isEmpty())
      {
        drawInfo("" + channel.getTopic(), x, y);y += 12;
      }
      drawInfo("" + channel.getChannelCodecName(), x, y);y += 12;
      drawInfo("" + channel.getChannelCodecQuality(), x, y);y += 12;
      drawInfo("" + TeamSpeak.status(channel.getIsPermanent(), new StringBuilder().append(L._("gui_ts_channel_permanent", new Object[0])).append(" ").toString(), "") + TeamSpeak.status(channel.getIsSemiPermanent(), new StringBuilder().append(L._("gui_ts_channel_semipermanent", new Object[0])).append(" ").toString(), "") + TeamSpeak.status(channel.getIsPassword(), L._("gui_ts_channel_password", new Object[0]), ""), x, y);y += 12;
      drawInfo("" + channel.getTotalClients() + "/" + new StringBuilder().append(channel.getMaxClients()).append("").toString().replace("-1", "Unlimited"), x, y);y += 12;
      drawInfo("" + TeamSpeak.status(channel.getSubscription(), L._("gui_ts_channel_subscribed", new Object[0]), L._("gui_ts_channel_notsubscribed", new Object[0])), x, y);y += 12;
    }
  }
  
  public void drawInfo(String text, int x, int y)
  {
    if (y < TeamSpeak.ySplit - 10) {
      LabyMod.getInstance().draw.drawString(text, x, y);
    }
  }
  
  public void drawBox(int mouseX, int mouseY)
  {
    if (!this.boxEnabled) {
      return;
    }
    int x = this.boxPosX;
    int y = this.boxPosY;
    
    int lengthX = this.boxPosX + this.boxLengthX;
    int lengthY = this.boxPosY + this.boxLengthY;
    
    boolean cover = this.boxLengthY == 0;
    if (this.boxIsUser)
    {
      if (this.boxId == TeamSpeakController.getInstance().me().getClientId())
      {
        int slot = 0;
        LabyMod.getInstance().draw.drawBox(x, y, lengthX, lengthY);
        boxSlot(L._("gui_ts_action_changenickname", new Object[0]), x, y, lengthX, lengthY, slot, mouseX, mouseY);slot += 15;
        boxSlot(L._("gui_ts_action_channelcommander", new Object[0]), x, y, lengthX, lengthY, slot, mouseX, mouseY);slot += 15;
        
        this.boxLengthX = 110;
        this.boxLengthY = slot;
      }
      else
      {
        int slot = 0;
        LabyMod.getInstance().draw.drawBox(x, y, lengthX, lengthY);
        boxSlot(L._("gui_ts_action_opentextchat", new Object[0]), x, y, lengthX, lengthY, slot, mouseX, mouseY);slot += 15;
        boxSlot(L._("gui_ts_action_poke", new Object[0]), x, y, lengthX, lengthY, slot, mouseX, mouseY);slot += 15;
        boxSlot(L._("gui_ts_action_movetoown", new Object[0]), x, y, lengthX, lengthY, slot, mouseX, mouseY);slot += 15;
        
        this.boxLengthX = 145;
        this.boxLengthY = slot;
      }
    }
    else
    {
      int slot = 0;
      LabyMod.getInstance().draw.drawBox(x, y, lengthX, lengthY);
      boxSlot(L._("gui_ts_action_switchchannel", new Object[0]), x, y, lengthX, lengthY, slot, mouseX, mouseY);slot += 15;
      
      this.boxLengthX = 100;
      this.boxLengthY = slot;
    }
    if (cover) {
      LabyMod.getInstance().draw.drawBox(x, y, x + this.boxLengthX, y + this.boxLengthY);
    }
  }
  
  public void boxSlot(String text, int x, int y, int lengthX, int lengthY, int slot, int mouseX, int mouseY)
  {
    String c = Color.cl("7");
    if ((mouseX > x) && (mouseX < x + lengthX) && (mouseY > y + slot) && (mouseY < y + slot + 15)) {
      c = Color.cl("f");
    }
    LabyMod.getInstance().draw.drawString(c + text, x + 5, y + 4 + slot);
  }
  
  public void boxSplit(int x, int y, int lengthX, int lengthY, int slot, int mouseX, int mouseY)
  {
    DrawUtils.a(x + 5, y + slot + 3, lengthX - 5, y + slot + 4, 1423232232);
  }
  
  private boolean boxClick(int x, int y, int lengthX, int lengthY, int slot, int mouseX, int mouseY)
  {
    if ((mouseX > x) && (mouseX < x + lengthX) && (mouseY > y + slot) && (mouseY < y + slot + 15)) {
      return true;
    }
    return false;
  }
  
  private boolean boxAction(int mouseX, int mouseY, int mouseButton)
  {
    if (!this.boxEnabled) {
      return false;
    }
    if ((mouseX <= this.boxPosX) || (mouseX >= this.boxPosX + this.boxLengthX) || (mouseY <= this.boxPosY) || (mouseY >= this.boxPosY + this.boxLengthY)) {
      return false;
    }
    if (mouseButton != 0) {
      return true;
    }
    int x = this.boxPosX;
    int y = this.boxPosY;
    
    int lengthX = this.boxPosX + this.boxLengthX;
    int lengthY = this.boxPosY + this.boxLengthY;
    if (this.boxIsUser)
    {
      if (this.boxId == TeamSpeakController.getInstance().me().getClientId())
      {
        int slot = 0;
        if (boxClick(x, y, lengthX, lengthY, slot, mouseX, mouseY)) {
          openNickNameBox();
        }
        slot += 15;
        if (boxClick(x, y, lengthX, lengthY, slot, mouseX, mouseY)) {
          TeamSpeakBridge.setChannelCommander(!TeamSpeakController.getInstance().me().isChannelCommander());
        }
        slot += 15;
      }
      else
      {
        int slot = 0;
        if (boxClick(x, y, lengthX, lengthY, slot, mouseX, mouseY))
        {
          TeamSpeak.addChat(TeamSpeakController.getInstance().getUser(this.boxId), TeamSpeakController.getInstance().me(), null, EnumTargetMode.USER);
          TeamSpeak.selectedChat = this.boxId;
        }
        slot += 15;
        if (boxClick(x, y, lengthX, lengthY, slot, mouseX, mouseY)) {
          TeamSpeak.overlayWindows.openInput(TeamSpeak.selectedUser, L._("gui_ts_window_poke_title", new Object[0]), L._("gui_ts_window_poke_content", new Object[0]), new PopUpCallback()
          {
            public void ok(int id, String message)
            {
              TeamSpeakUser user = TeamSpeakController.getInstance().getUser(id);
              if (user == null)
              {
                TeamSpeak.error(L._("gui_ts_window_poke_error_offline", new Object[0]));
                return;
              }
              TeamSpeakBridge.pokeClient(user, message);
            }
            
            public void ok() {}
            
            public void cancel() {}
            
            public boolean tick(int cid)
            {
              return false;
            }
          });
        }
        slot += 15;
        if (boxClick(x, y, lengthX, lengthY, slot, mouseX, mouseY)) {
          TeamSpeakBridge.moveClient(this.boxId, TeamSpeakController.getInstance().me().getChannelId());
        }
        slot += 15;
      }
    }
    else
    {
      int slot = 0;
      if (boxClick(x, y, lengthX, lengthY, slot, mouseX, mouseY))
      {
        if (TeamSpeakController.getInstance().me() != null) {
          TeamSpeakBridge.moveClient(TeamSpeakController.getInstance().me().getClientId(), this.boxId);
        }
        closeBox();
      }
      slot += 15;
    }
    closeBox();
    return true;
  }
  
  private void openBox(boolean isUser, int id, int x, int y)
  {
    this.boxEnabled = true;
    this.boxIsUser = isUser;
    this.boxId = id;
    this.boxPosX = x;
    this.boxPosY = y;
    if (isUser)
    {
      TeamSpeak.selectedChannel = -1;
      TeamSpeak.selectedUser = id;
    }
    else
    {
      TeamSpeak.selectedUser = -1;
      TeamSpeak.selectedChannel = id;
    }
  }
  
  private void closeBox()
  {
    this.boxEnabled = false;
    this.boxIsUser = true;
    this.boxId = 0;
    this.boxPosX = 0;
    this.boxPosY = 0;
    this.boxLengthX = 0;
    this.boxLengthY = 0;
  }
  
  private void changeNickname()
  {
    this.changeNickName = false;
    if (!this.nickNameField.b().equals(TeamSpeakController.getInstance().me().getNickName())) {
      TeamSpeakBridge.setNickname(this.nickNameField.b());
    }
  }
  
  private void openNickNameBox()
  {
    this.changeNickName = true;
    this.nickNameField.b(true);
    this.nickNameField.a(TeamSpeakController.getInstance().me().getNickName());
  }
  
  private void drag(boolean isUser, int Id)
  {
    resetDrag();
    this.drag = true;
    this.dragIsUser = isUser;
    this.dragId = Id;
  }
  
  private void drop(int channelId, boolean isInRegion)
  {
    if (!this.drag) {
      return;
    }
    this.dropFocus = isInRegion;
    if (!this.drop) {
      return;
    }
    if ((this.dragIsUser) && (this.dropFocus)) {
      TeamSpeakBridge.moveClient(this.dragId, channelId);
    }
    resetDrag();
  }
  
  private void resetDrag()
  {
    this.drag = false;
    this.drop = false;
    this.dragIsUser = false;
    this.dragId = 0;
    this.dropX = 0;
    this.dropY = 0;
    this.dragVisible = 0;
    this.dropFocus = false;
  }
  
  public void setDrop(int x, int y)
  {
    this.dropX = x;
    this.dropY = y;
    this.drop = true;
  }
  
  public void drawDrag(int mouseX, int mouseY)
  {
    if (!this.drag) {
      return;
    }
    if (this.dragVisible < 5) {
      return;
    }
    String color = "";
    String name = "";
    if (this.dragIsUser)
    {
      TeamSpeakUser user = TeamSpeakController.getInstance().getUser(this.dragId);
      if (user == null) {
        return;
      }
      name = user.getNickName();
    }
    else
    {
      TeamSpeakChannel channel = TeamSpeakController.getInstance().getChannel(this.dragId);
      if (channel == null) {
        return;
      }
      name = channel.getChannelName();
    }
    if (!this.dropFocus) {
      color = Color.cl("c");
    }
    LabyMod.getInstance().draw.drawString(color + name, mouseX, mouseY);
  }
  
  public void drawMenu(int mouseX, int mouseY)
  {
    if (TeamSpeakController.getInstance().me() == null) {
      return;
    }
    int slot = 0;
    String s = TeamSpeakController.getInstance().serverIP + ":" + TeamSpeakController.getInstance().serverPort;
    slot += LabyMod.getInstance().draw.getWidth() - 30 - LabyMod.getInstance().draw.getStringWidth(s);
    DrawUtils.a(slot, 5, LabyMod.getInstance().draw.getWidth() - 5, 20, Integer.MIN_VALUE);
    LabyMod.getInstance().draw.drawRightString(Color.cl("a") + s, LabyMod.getInstance().draw.getWidth() - 20, 9.0D);
    
    slot -= 4;
    
    int c = Integer.MIN_VALUE;
    if (TeamSpeakController.getInstance().me().hasClientOutputMuted()) {
      c = 2122022291;
    }
    DrawUtils.a(slot, 5, slot - 16, 20, c);
    LabyMod.getInstance().draw.drawString(Color.cl("f") + "", 0.0D, 0.0D);
    this.j.N().a(LabyMod.getInstance().texture_teamSpeak);
    LabyMod.getInstance().draw.b(slot - 16 + 3, 7, 12, 0, 12, 12);
    
    slot -= 20;
    
    c = Integer.MIN_VALUE;
    if (TeamSpeakController.getInstance().me().hasClientInputMuted()) {
      c = 2122022291;
    }
    DrawUtils.a(slot, 5, slot - 16, 20, c);
    LabyMod.getInstance().draw.drawString(Color.cl("f") + "", 0.0D, 0.0D);
    this.j.N().a(LabyMod.getInstance().texture_teamSpeak);
    LabyMod.getInstance().draw.b(slot - 16 + 3, 7, 0, 0, 12, 12);
    
    slot -= 20;
    
    c = Integer.MIN_VALUE;
    if (TeamSpeak.teamSpeakGroupPrefix) {
      c = 2122022291;
    }
    DrawUtils.a(slot, 5, slot - 16, 20, c);
    LabyMod.getInstance().draw.drawCenteredString(Color.cl("b") + "[]", slot - 8, 9);
  }
  
  public void menuAction(int mouseX, int mouseY, int mouseButton)
  {
    int slot = 0;
    String s = TeamSpeakController.getInstance().serverIP + ":" + TeamSpeakController.getInstance().serverPort;
    slot += LabyMod.getInstance().draw.getWidth() - 30 - LabyMod.getInstance().draw.getStringWidth(s);
    slot -= 4;
    if ((mouseX > slot - 16) && (mouseX < slot) && (mouseY > 5) && (mouseY < 20)) {
      TeamSpeakBridge.setOutputMuted(!TeamSpeakController.getInstance().me().hasClientOutputMuted());
    }
    slot -= 20;
    if ((mouseX > slot - 16) && (mouseX < slot) && (mouseY > 5) && (mouseY < 20)) {
      TeamSpeakBridge.setInputMuted(!TeamSpeakController.getInstance().me().hasClientInputMuted());
    }
    slot -= 20;
    if ((mouseX > slot - 16) && (mouseX < slot) && (mouseY > 5) && (mouseY < 20)) {
      TeamSpeak.teamSpeakGroupPrefix = !TeamSpeak.teamSpeakGroupPrefix;
    }
  }
  
  public void callBackListener(int mouseX, int mouseY)
  {
    if (TeamSpeak.callBack)
    {
      TeamSpeak.callBack = false;
      openBox(true, TeamSpeak.callBackClient, mouseX, mouseY);
    }
  }
}
