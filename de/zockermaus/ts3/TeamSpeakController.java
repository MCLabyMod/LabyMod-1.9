package de.zockermaus.ts3;

import de.labystudio.utils.Debug;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TeamSpeakController
  implements Runnable
{
  private static TeamSpeakController instance;
  private Socket socket;
  private BufferedWriter writer;
  private List<ControlListener> listeners = new ArrayList();
  public String serverIP = "";
  public int serverPort = 0;
  TeamSpeakUser me;
  private boolean connectionEstablished;
  
  public TeamSpeakController()
  {
    instance = this;
    new Thread(this, "TeamSpeak").start();
  }
  
  public TeamSpeakController(ControlListener listener)
  {
    this();
    this.listeners.add(listener);
  }
  
  public static TeamSpeakController getInstance()
  {
    return instance;
  }
  
  public void addControlListener(ControlListener listener)
  {
    this.listeners.add(listener);
  }
  
  public void run()
  {
    try
    {
      TeamSpeak.print("Connect to TeamSpeak..");
      this.socket = new Socket("localhost", 25639);
      OutputStream socketOutputStream = this.socket.getOutputStream();
      InputStream socketInputStream = this.socket.getInputStream();
      this.writer = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(socketOutputStream), Charset.forName("utf-8")));
      new InputStreamReaderThread(socketInputStream);
      new OutputStreamWriterThread(socketOutputStream);
      onEnable();
    }
    catch (UnknownHostException e)
    {
      TeamSpeak.print("TeamSpeak Connection Error: " + e.getMessage());
      if (Debug.teamspeak()) {
        e.printStackTrace();
      }
    }
    catch (IOException e)
    {
      TeamSpeak.print("Can't connect to TeamSpeak");
      if (Debug.teamspeak()) {
        e.printStackTrace();
      }
    }
  }
  
  public Socket getSocket()
  {
    return this.socket;
  }
  
  protected class OutputStreamWriterThread
    extends Thread
  {
    private OutputStream output;
    
    public OutputStreamWriterThread(OutputStream output)
    {
      super();
      this.output = output;
      start();
    }
    
    public void run()
    {
      TeamSpeakController.this.testForConnectionEstablished();
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("utf-8")));
      for (;;)
      {
        if (!TeamSpeakController.this.isConnectionEstablished()) {
          return;
        }
        try
        {
          String line = reader.readLine();
          if (line != null)
          {
            if (line.equalsIgnoreCase("usage"))
            {
              TeamSpeak.print("Free: " + Runtime.getRuntime().freeMemory() / 1024L / 1024L);
              TeamSpeak.print("Max: " + Runtime.getRuntime().maxMemory() / 1024L / 1024L);
              TeamSpeak.print("Total: " + Runtime.getRuntime().totalMemory() / 1024L / 1024L);
              continue;
            }
            TeamSpeakController.this.sendMessage(line);
          }
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    
    public OutputStream getOutput()
    {
      return this.output;
    }
  }
  
  private class InputStreamReaderThread
    extends Thread
  {
    private InputStream input;
    
    public InputStreamReaderThread(InputStream input)
    {
      super();
      this.input = input;
      start();
    }
    
    public void run()
    {
      TeamSpeakController.this.testForConnectionEstablished();
      InputStreamReader in = new InputStreamReader(new BufferedInputStream(getInput()), Charset.forName("utf-8"));
      BufferedReader reader = new BufferedReader(in);
      for (;;)
      {
        if (!TeamSpeakController.this.isConnectionEstablished()) {
          return;
        }
        try
        {
          String line = reader.readLine();
          if (line != null) {
            TeamSpeakController.this.onMessageRecieved(line);
          }
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
    
    public InputStream getInput()
    {
      return this.input;
    }
  }
  
  protected void sendMessage(String message)
  {
    if ((getSocket() == null) || (getSocket().isClosed()))
    {
      TeamSpeak.print("Trying to send message via closed socket");
      return;
    }
    try
    {
      this.writer.write(message + "\n");
      this.writer.flush();
    }
    catch (IOException e)
    {
      TeamSpeak.print("Can't send message");
      reset();
    }
  }
  
  public static void main(String[] args)
  {
    new TeamSpeakController();
  }
  
  protected void onMessageRecieved(String message)
  {
    if ((!message.isEmpty()) && 
      (Debug.teamspeak())) {
      if (TeamSpeak.inputString.contains("-nonotify"))
      {
        if (!message.startsWith("notify")) {
          TeamSpeak.print("TeamSpeak: " + message);
        }
      }
      else {
        TeamSpeak.print("TeamSpeak: " + message);
      }
    }
    if (message.startsWith("notify"))
    {
      handleNotifyMessage(message);
    }
    else
    {
      Object users;
      Object rem;
      if ((message.startsWith("clid")) && (message.contains("|")))
      {
        ArrayList<Integer> ids = new ArrayList();
        String[] clients = message.split("[|]");
        for (String client : clients)
        {
          String[] clientargs = client.split(" ");
          Argument[] args = new Argument[clientargs.length];
          for (int i = 0; i < args.length; i++) {
            args[i] = new Argument(clientargs[i]);
          }
          TeamSpeakUser user = null;
          if (TeamSpeakUser.contains(args[0].getAsInt())) {
            user = getInstance().getUser(args[0].getAsInt());
          } else {
            user = new TeamSpeakUser(args[0].getAsInt());
          }
          if (user != null)
          {
            user.updateChannelId(args[1].getAsInt());
            user.updateDatabaseId(args[2].getAsInt());
            user.updateNickname(TeamSpeak.fix(args[3].getValue()));
            user.updateTyping(args[4].getAsBoolean());
            user.updateAway(args[5].getAsBoolean(), TeamSpeak.fix(args[6].getValue()));
            user.updateTalkStatus(args[7].getAsBoolean());
            user.updateClientInput(args[8].getAsBoolean());
            user.updateClientOutput(args[9].getAsBoolean());
            user.updateClientInputHardware(args[10].getAsBoolean());
            user.updateClientOutputHardware(args[11].getAsBoolean());
            user.updateTalkPower(args[12].getAsInt());
            user.updateTalker(args[13].getAsBoolean());
            user.updatePrioritySpeaker(args[14].getAsBoolean());
            user.updateRecording(args[15].getAsBoolean());
            user.updateChannelCommander(args[16].getAsBoolean());
            user.updateMuted(args[17].getAsBoolean());
            user.updateUid(args[18].getValue());
            user.updateServerGroups(args[19].getAsIntArray());
            user.updateChannelGroupId(args[20].getAsInt());
            user.updateIconId(args[21].getAsInt());
            user.updateCountry(args[22].getValue());
            
            ids.add(Integer.valueOf(user.getClientId()));
          }
        }
        users = TeamSpeakUser.getUsers();
        Collections.synchronizedList((List)users);
        rem = new ArrayList();
        for (TeamSpeakUser user : (List)users) {
          if (!ids.contains(Integer.valueOf(user.getClientId()))) {
            ((ArrayList)rem).add(user);
          }
        }
        for (TeamSpeakUser user : (ArrayList)rem) {
          TeamSpeakUser.unregisterUser(user);
        }
      }
      else if ((message.startsWith("cid")) && (message.contains("|")))
      {
        ArrayList<Integer> ids = new ArrayList();
        String[] channels = message.split("[|]");
        for (String channel : channels)
        {
          String[] channelargs = channel.split(" ");
          Argument[] args = new Argument[channelargs.length];
          for (int i = 0; i < args.length; i++) {
            args[i] = new Argument(channelargs[i]);
          }
          TeamSpeakChannel ch = null;
          if (TeamSpeakChannel.contains(args[0].getAsInt())) {
            ch = getInstance().getChannel(args[0].getAsInt());
          } else {
            ch = new TeamSpeakChannel(args[0].getAsInt());
          }
          ch.updatePID(args[1].getAsInt());
          ch.updateChannelOrder(args[2].getAsInt());
          ch.updateChannelName(TeamSpeak.fix(args[3].getValue()));
          ch.updateTopic(args[4].getValue());
          ch.updateFlagDefault(args[5].getAsBoolean());
          ch.updateIsPassword(args[6].getAsBoolean());
          ch.updatePermanent(args[7].getAsBoolean());
          ch.updateSemiPermanent(args[8].getAsBoolean());
          ch.updateChannelCodec(args[9].getAsInt());
          ch.updateChannelCodecQuality(args[10].getAsInt());
          ch.updateTalkPower(args[11].getAsInt());
          ch.updateIconID(args[12].getAsInt());
          ch.updateMaxClients(args[13].getAsInt());
          ch.updateMaxFamilyClients(args[14].getAsInt());
          ch.updateFlagAreSubscribed(args[15].getAsBoolean());
          if (args.length == 17) {
            ch.updateTotalClients(args[16].getAsInt());
          }
          ids.add(Integer.valueOf(ch.getChannelId()));
        }
        Object ch = TeamSpeakChannel.getChannels();
        Collections.synchronizedList((List)ch);
        Object rem = new ArrayList();
        for (TeamSpeakChannel c : (List)ch) {
          if (!ids.contains(Integer.valueOf(c.getChannelId()))) {
            ((ArrayList)rem).add(c);
          }
        }
        for (TeamSpeakChannel c : (ArrayList)rem) {
          TeamSpeakChannel.deleteChannel(c);
        }
      }
      else if ((message.startsWith("clid")) && (!message.contains("|")) && (message.contains("cid")))
      {
        String[] sargs = message.split(" ");
        Argument[] args = new Argument[sargs.length];
        for (int i = 0; i < args.length; i++) {
          args[i] = new Argument(sargs[i]);
        }
        if (args.length == 2)
        {
          TeamSpeakUser user = getUser(args[0].getAsInt());
          if (user != null)
          {
            user.updateChannelId(args[1].getAsInt());
            this.me = user;
          }
        }
      }
      else
      {
        int i;
        if ((message.startsWith("ip=")) && (message.contains("port=")))
        {
          String[] sargs = message.split(" ");
          Argument[] args = new Argument[sargs.length];
          for (i = 0; i < args.length; i++) {
            args[i] = new Argument(sargs[i]);
          }
          if (args.length >= 2)
          {
            this.serverIP = args[0].getValue();
            this.serverPort = args[1].getAsInt();
            TeamSpeak.print("Connected to " + this.serverIP + ":" + this.serverPort);
            TeamSpeak.setupChat();
            for (ControlListener listener : this.listeners) {
              listener.onConnect();
            }
          }
        }
        else
        {
          handleOther(message);
        }
      }
    }
  }
  
  private void onEnable()
  {
    TeamSpeak.print("Successfully connected to TeamSpeak!");
    updateInformation(EnumUpdateType.ALL);
    sendMessage("clientnotifyregister schandlerid=1 event=any");
    String message = "Hallo lieber Decompiler, wie geht's dir heute? Macht das Code-Lesen denn auch Spaß? Grüße, die Internet-Polizei";
    message.length();
  }
  
  private void handleOther(String message)
  {
    String[] cmdWithArgs = message.split(" ");
    Argument[] args = new Argument[cmdWithArgs.length - 1];
    for (int i = 0; i < args.length; i++) {
      args[i] = new Argument(cmdWithArgs[(i + 1)]);
    }
    String cmd = cmdWithArgs[0];
    int errorId;
    String errorMessage;
    if (cmd.equalsIgnoreCase("error"))
    {
      errorId = args[0].getAsInt();
      if ((errorId != 0) && (errorId != 1794))
      {
        errorMessage = args[1].getValue();
        for (ControlListener listener : this.listeners) {
          listener.onError(errorId, errorMessage);
        }
      }
    }
  }
  
  private void handleNotifyMessage(String message)
  {
    message = message.substring(6, message.length());
    String[] cmdWithArgs = message.split(" ");
    Argument[] args = new Argument[cmdWithArgs.length - 1];
    for (int i = 0; i < args.length; i++) {
      args[i] = new Argument(cmdWithArgs[(i + 1)]);
    }
    String cmd = cmdWithArgs[0];
    if (cmd.equalsIgnoreCase("talkstatuschange"))
    {
      TeamSpeakUser user = getUser(args[3].getAsInt());
      if (user != null) {
        user.updateTalkStatus(args[1].getAsBoolean());
      } else {
        updateInformation(EnumUpdateType.CLIENTS);
      }
    }
    else if (cmd.equalsIgnoreCase("cliententerview"))
    {
      if (getUser(args[4].getAsInt()) != null) {
        return;
      }
      TeamSpeakUser user = new TeamSpeakUser(args[4].getAsInt());
      user.updateChannelId(args[14].getAsInt());
      user.updateNickname(args[6].getValue().replace("\\s", " "));
      user.updateClientInput(args[7].getAsBoolean());
      user.updateClientOutput(args[8].getAsBoolean());
      for (ControlListener listener : this.listeners) {
        listener.onClientConnect(user);
      }
      updateInformation(EnumUpdateType.CLIENTS);
      TeamSpeak.updateScroll(user.getChannelId(), true);
    }
    else
    {
      ControlListener listener;
      if (cmd.equalsIgnoreCase("clientleftview"))
      {
        TeamSpeakUser user;
        TeamSpeakUser user;
        if ((args.length > 5) && (args[5].isInt()))
        {
          user = getUser(args[5].getAsInt());
          if (args[3].getAsInt() == 3) {
            for (ControlListener listener : this.listeners)
            {
              listener.onClientTimout(user);
              TeamSpeakUser.unregisterUser(user);
            }
          } else {
            for (ControlListener listener : this.listeners)
            {
              listener.onClientDisconnected(user, args[4].getValue());
              TeamSpeakUser.unregisterUser(user);
            }
          }
        }
        else
        {
          user = getUser(args[3].getAsInt());
          if (args[2].getAsInt() == 3) {
            for (ControlListener listener : this.listeners)
            {
              listener.onClientTimout(user);
              TeamSpeakUser.unregisterUser(user);
            }
          } else {
            for (??? = this.listeners.iterator(); ???.hasNext();)
            {
              listener = (ControlListener)???.next();
              
              listener.onClientDisconnected(user, "Disconnected");
              TeamSpeakUser.unregisterUser(user);
            }
          }
        }
        updateInformation(EnumUpdateType.CLIENTS);
        TeamSpeakUser user = getUser(args[3].getAsInt());
        if (user != null) {
          TeamSpeak.updateScroll(user.getChannelId(), false);
        }
      }
      else
      {
        TeamSpeakUser user;
        if (cmd.equalsIgnoreCase("clientupdated"))
        {
          user = getUser(args[1].getAsInt());
          if (user == null) {
            return;
          }
          if (args[2].getKey().equalsIgnoreCase("client_input_muted")) {
            user.updateClientInput(args[2].getAsBoolean());
          } else if (args[2].getKey().equalsIgnoreCase("client_output_muted")) {
            user.updateClientOutput(args[2].getAsBoolean());
          } else if (args[2].getKey().equalsIgnoreCase("client_input_hardware")) {
            user.updateClientInputHardware(args[2].getAsBoolean());
          } else if (args[2].getKey().equalsIgnoreCase("client_output_hardware")) {
            user.updateClientOutputHardware(args[2].getAsBoolean());
          } else if (args[2].getKey().equalsIgnoreCase("client_away")) {
            if (args.length == 4) {
              user.updateAway(args[2].getAsBoolean(), args[3].getValue());
            } else {
              user.updateAway(args[2].getAsBoolean());
            }
          }
          updateInformation(EnumUpdateType.CLIENTS);
        }
        else if ((cmd.equalsIgnoreCase("channeldeleted")) || (cmd.equalsIgnoreCase("channelcreated")) || (cmd.equalsIgnoreCase("channeledited")))
        {
          updateInformation(EnumUpdateType.CHANNELS);
        }
        else if (cmd.equalsIgnoreCase("connectstatuschange"))
        {
          if (args[1].getValue().equalsIgnoreCase("disconnected")) {
            for (ControlListener listener : this.listeners)
            {
              reset();
              listener.onDisconnect();
            }
          } else if (args[1].getValue().equalsIgnoreCase("connection_established")) {
            updateInformation(EnumUpdateType.ALL);
          }
        }
        else
        {
          TeamSpeakUser user;
          String msg;
          ControlListener listener;
          if (cmd.equalsIgnoreCase("clientpoke"))
          {
            user = getUser(args[1].getAsInt());
            msg = TeamSpeak.fix(args[4].getValue());
            for (listener = this.listeners.iterator(); listener.hasNext();)
            {
              listener = (ControlListener)listener.next();
              
              listener.onPokeRecieved(user, msg);
            }
          }
          else
          {
            TeamSpeakUser user;
            Object user;
            ControlListener listener;
            if (cmd.equalsIgnoreCase("textmessage"))
            {
              int targetMode = args[1].getAsInt();
              TeamSpeakUser target;
              if (targetMode == 1)
              {
                target = getUser(args[3].getAsInt());
                user = getUser(args[4].getAsInt());
                if (user == null) {
                  return;
                }
                for (ControlListener listener : this.listeners)
                {
                  if (user.isTyping()) {
                    user.updateTyping(false);
                  }
                  listener.onMessageRecieved(target, user, TeamSpeak.fix(args[2].getValue()));
                }
              }
              TeamSpeakUser user;
              if (targetMode == 2)
              {
                user = getUser(args[3].getAsInt());
                if (user == null) {
                  return;
                }
                for (ControlListener listener : this.listeners)
                {
                  if (user.isTyping()) {
                    user.updateTyping(false);
                  }
                  listener.onChannelMessageRecieved(user, TeamSpeak.fix(args[2].getValue()));
                }
              }
              if (targetMode == 3)
              {
                user = getUser(args[3].getAsInt());
                if (user == null) {
                  return;
                }
                for (user = this.listeners.iterator(); user.hasNext();)
                {
                  listener = (ControlListener)user.next();
                  if (((TeamSpeakUser)user).isTyping()) {
                    ((TeamSpeakUser)user).updateTyping(false);
                  }
                  listener.onServerMessageRecieved((TeamSpeakUser)user, TeamSpeak.fix(args[2].getValue()));
                }
              }
            }
            else
            {
              TeamSpeakUser user;
              ControlListener listener;
              if (cmd.equalsIgnoreCase("clientchatcomposing"))
              {
                user = getUser(args[1].getAsInt());
                if (user == null) {
                  return;
                }
                for (user = this.listeners.iterator(); ((Iterator)user).hasNext();)
                {
                  listener = (ControlListener)((Iterator)user).next();
                  
                  listener.onClientStartTyping(user);
                  user.updateTyping(true);
                }
              }
              else
              {
                Object user;
                if (cmd.equalsIgnoreCase("clientmoved"))
                {
                  int channel = args[1].getAsInt();
                  user = getUser(args[2].getAsInt());
                  if (user != null) {
                    TeamSpeak.updateScroll(((TeamSpeakUser)user).getChannelId(), false);
                  }
                  updateInformation(EnumUpdateType.CLIENTS);
                  TeamSpeak.updateScroll(channel, true);
                }
                else if (cmd.equalsIgnoreCase("currentserverconnectionchanged"))
                {
                  updateInformation(EnumUpdateType.ALL);
                }
                else if (cmd.equalsIgnoreCase("servergrouplist"))
                {
                  TeamSpeakServerGroup.getGroups().clear();
                  String[] channels = message.replace("servergrouplist", "").replace(" schandlerid=1 ", "").replace(" schandlerid=0 ", "").split("[|]");
                  user = channels;listener = user.length;
                  for (listener = 0; listener < listener; listener++)
                  {
                    String channel = user[listener];
                    
                    String[] channelargs = channel.split(" ");
                    args = new Argument[channelargs.length];
                    for (int i = 0; i < args.length; i++) {
                      args[i] = new Argument(channelargs[i]);
                    }
                    TeamSpeakServerGroup group = new TeamSpeakServerGroup(args[0].getAsInt());
                    group.setGroupName(args[1].getValue());
                    group.setType(args[2].getAsInt());
                    group.setIconId(args[3].getAsInt());
                    group.setSavebd(args[4].getAsInt());
                    TeamSpeakServerGroup.addGroup(group);
                  }
                }
                else if (cmd.equalsIgnoreCase("channelgrouplist"))
                {
                  TeamSpeakChannelGroup.getGroups().clear();
                  String[] channels = message.replace("channelgrouplist", "").replace(" schandlerid=1 ", "").replace(" schandlerid=0 ", "").split("[|]");
                  user = channels;listener = user.length;
                  for (listener = 0; listener < listener; listener++)
                  {
                    String channel = user[listener];
                    
                    String[] channelargs = channel.split(" ");
                    args = new Argument[channelargs.length];
                    for (int i = 0; i < args.length; i++) {
                      args[i] = new Argument(channelargs[i]);
                    }
                    TeamSpeakChannelGroup group = new TeamSpeakChannelGroup(args[0].getAsInt());
                    group.setGroupName(args[1].getValue());
                    group.setType(args[2].getAsInt());
                    group.setIconId(args[3].getAsInt());
                    group.setSavebd(args[4].getAsInt());
                    group.setNamemode(args[5].getAsInt());
                    group.setNameModifyPower(args[6].getAsInt());
                    group.setNameMemberAddPower(args[7].getAsInt());
                    group.setNameMemberRemovePower(args[8].getAsInt());
                    TeamSpeakChannelGroup.addGroup(group);
                  }
                }
                else
                {
                  updateInformation(EnumUpdateType.ALL);
                }
              }
            }
          }
        }
      }
    }
    if ((TeamSpeakChannel.getChannels().size() == 0) || (TeamSpeakUser.getUsers().size() == 0)) {
      updateInformation(EnumUpdateType.ALL);
    }
    if (((TeamSpeakServerGroup.getGroups().isEmpty()) || (TeamSpeakChannelGroup.getGroups().isEmpty())) && (!cmd.contains("grouplist"))) {
      updateInformation(EnumUpdateType.GROUPS);
    }
  }
  
  private void updateInformation(EnumUpdateType type)
  {
    if ((type == EnumUpdateType.ALL) || (type == EnumUpdateType.CHANNELS)) {
      sendMessage("channellist -topic -flags -voice -icon -limits");
    }
    if ((type == EnumUpdateType.ALL) || (type == EnumUpdateType.CLIENTS)) {
      sendMessage("clientlist -uid -away -voice -groups -icon -country");
    }
    if ((type == EnumUpdateType.ALL) || (type == EnumUpdateType.ME)) {
      sendMessage("whoami");
    }
    if ((type == EnumUpdateType.ALL) || (type == EnumUpdateType.GROUPS))
    {
      sendMessage("servergrouplist");
      sendMessage("channelgrouplist");
    }
    if ((this.serverIP.isEmpty()) || (this.serverPort == 0) || (!isConnectionEstablished())) {
      sendMessage("serverconnectinfo");
    }
    int k = 0;
    for (Chat chat : TeamSpeak.chats) {
      if (chat.getSlotId() < 0) {
        k++;
      }
    }
    if (k != 2) {
      TeamSpeak.setupChat();
    }
  }
  
  public void tick()
  {
    testForConnectionEstablished();
    if ((isConnectionEstablished()) && (this.serverIP.isEmpty()) && (this.serverPort == 0)) {
      updateInformation(EnumUpdateType.ALL);
    }
  }
  
  public static class Argument
  {
    private String key;
    private String value;
    
    public Argument(String input)
    {
      if ((input.contains("=")) && (input.split("=").length >= 1))
      {
        String[] splitted = input.split("=", 2);
        this.key = splitted[0];
        this.value = splitted[1];
      }
      else
      {
        this.key = input;
        this.value = "";
      }
    }
    
    public String getKey()
    {
      return this.key;
    }
    
    public String getValue()
    {
      return this.value;
    }
    
    public boolean isInt()
    {
      try
      {
        Integer.parseInt(this.value);
        return true;
      }
      catch (Exception e) {}
      return false;
    }
    
    public int getAsInt()
    {
      return Integer.parseInt(this.value);
    }
    
    public boolean getAsBoolean()
    {
      int i = Integer.parseInt(this.value);
      if (i == 1) {
        return true;
      }
      return false;
    }
    
    public ArrayList<Integer> getAsIntArray()
    {
      ArrayList<Integer> array = new ArrayList();
      if (this.value.contains(","))
      {
        String[] split = this.value.split(",");
        for (String a : split) {
          array.add(Integer.valueOf(Integer.parseInt(a)));
        }
      }
      else
      {
        array.add(Integer.valueOf(Integer.parseInt(this.value)));
      }
      return array;
    }
  }
  
  public TeamSpeakUser getUser(int clientId)
  {
    List<TeamSpeakUser> users = TeamSpeakUser.getUsers();
    Collections.synchronizedList(users);
    for (TeamSpeakUser ts : users) {
      if (ts.getClientId() == clientId) {
        return ts;
      }
    }
    return null;
  }
  
  public TeamSpeakUser getUser(String clientName)
  {
    List<TeamSpeakUser> users = TeamSpeakUser.getUsers();
    for (TeamSpeakUser ts : users) {
      if (ts.getNickName() == clientName) {
        return ts;
      }
    }
    return null;
  }
  
  public TeamSpeakChannel getChannel(int channelId)
  {
    List<TeamSpeakChannel> channels = TeamSpeakChannel.getChannels();
    for (TeamSpeakChannel ch : channels) {
      if (ch.getChannelId() == channelId) {
        return ch;
      }
    }
    return null;
  }
  
  public TeamSpeakServerGroup getServerGroup(int id)
  {
    List<TeamSpeakServerGroup> group = new ArrayList();
    group.addAll(TeamSpeakServerGroup.getGroups());
    for (TeamSpeakServerGroup ts : group) {
      if (ts.getSgid() == id) {
        return ts;
      }
    }
    return null;
  }
  
  public TeamSpeakChannelGroup getChannelGroup(int id)
  {
    List<TeamSpeakChannelGroup> group = new ArrayList();
    group.addAll(TeamSpeakChannelGroup.getGroups());
    for (TeamSpeakChannelGroup ts : group) {
      if (ts != null) {
        if (ts.getSgid() == id) {
          return ts;
        }
      }
    }
    return null;
  }
  
  public void diconnectUser(TeamSpeakUser user)
  {
    TeamSpeakUser.unregisterUser(user);
  }
  
  public TeamSpeakUser me()
  {
    return this.me;
  }
  
  private void reset()
  {
    TeamSpeak.chats.clear();
    TeamSpeakUser.reset();
    TeamSpeakChannel.reset();
    this.serverIP = "";
    this.serverPort = 0;
  }
  
  public void connect()
  {
    this.listeners.clear();
    reset();
    TeamSpeak.enable();
  }
  
  private boolean tested = false;
  
  public boolean isConnectionEstablished()
  {
    if (!this.tested)
    {
      this.tested = true;
      testForConnectionEstablished();
    }
    return this.connectionEstablished;
  }
  
  public void testForConnectionEstablished()
  {
    try
    {
      this.writer.write("whoami\n");
      this.writer.flush();
      this.connectionEstablished = true;
    }
    catch (Exception e)
    {
      this.connectionEstablished = false;
    }
  }
  
  public void quit()
  {
    sendMessage("quit");
  }
}
