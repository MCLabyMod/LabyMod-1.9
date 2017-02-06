package de.zockermaus.serverpinger;

import bcf;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.List;

public class PingUtils
{
  public static class ServerListPing
  {
    private String host1;
    private int port;
    
    public void setHost1(String host1)
    {
      this.host1 = host1;
    }
    
    public void setPort(int port)
    {
      this.port = port;
    }
    
    public void setTimeout(int timeout)
    {
      this.timeout = timeout;
    }
    
    private int timeout = 100;
    private static final Gson gson = new Gson();
    
    public PingUtils.StatusResponse fetchData()
      throws Exception
    {
      Socket socket = null;
      OutputStream oStr = null;
      InputStream inputStream = null;
      PingUtils.StatusResponse response = null;
      try
      {
        socket = new Socket(this.host1, this.port);
        socket.setSoTimeout(this.timeout);
        
        oStr = socket.getOutputStream();
        DataOutputStream dataOut = new DataOutputStream(oStr);
        
        inputStream = socket.getInputStream();
        dIn = new DataInputStream(inputStream);
        
        long start = bcf.I();
        
        sendPacket(dataOut, prepareHandshake());
        sendPacket(dataOut, preparePing());
        
        response = receiveResponse(dIn);
        
        response.ms = ((int)(bcf.I() - start));
        
        dIn.close();
        dataOut.close();
        
        return response;
      }
      catch (Exception ex)
      {
        DataInputStream dIn;
        return response;
      }
      finally
      {
        if (oStr != null) {
          oStr.close();
        }
        if (inputStream != null) {
          inputStream.close();
        }
        if (socket != null) {
          socket.close();
        }
      }
    }
    
    private PingUtils.StatusResponse receiveResponse(DataInputStream dIn)
      throws IOException
    {
      readVarInt(dIn);
      int packetId = readVarInt(dIn);
      if (packetId != 0) {
        throw new IOException("Invalid packetId");
      }
      int stringLength = readVarInt(dIn);
      if (stringLength < 1) {
        throw new IOException("Invalid string length.");
      }
      byte[] responseData = new byte[stringLength];
      dIn.readFully(responseData);
      String jsonString = new String(responseData, Charset.forName("utf-8"));
      if (jsonString.contains("\"text\":")) {
        jsonString = jsonString.replace("\"description\"", "\"descriptions\"");
      }
      PingUtils.StatusResponse response = (PingUtils.StatusResponse)gson.fromJson(jsonString, PingUtils.StatusResponse.class);
      return response;
    }
    
    private void sendPacket(DataOutputStream out, byte[] data)
      throws IOException
    {
      writeVarInt(out, data.length);
      out.write(data);
    }
    
    private byte[] preparePing()
      throws IOException
    {
      return new byte[] { 0 };
    }
    
    private byte[] prepareHandshake()
      throws IOException
    {
      ByteArrayOutputStream bOut = new ByteArrayOutputStream();
      DataOutputStream handshake = new DataOutputStream(bOut);
      bOut.write(0);
      writeVarInt(handshake, 4);
      writeString(handshake, this.host1);
      handshake.writeShort(this.port);
      writeVarInt(handshake, 1);
      return bOut.toByteArray();
    }
    
    public void writeString(DataOutputStream out, String string)
      throws IOException
    {
      writeVarInt(out, string.length());
      out.write(string.getBytes(Charset.forName("utf-8")));
    }
    
    public int readVarInt(DataInputStream in)
      throws IOException
    {
      int i = 0;
      int j = 0;
      for (;;)
      {
        int k = in.readByte();
        i |= (k & 0x7F) << j++ * 7;
        if (j > 5) {
          throw new RuntimeException("VarInt too big");
        }
        if ((k & 0x80) != 128) {
          break;
        }
      }
      return i;
    }
    
    public void writeVarInt(DataOutputStream out, int paramInt)
      throws IOException
    {
      for (;;)
      {
        if ((paramInt & 0xFFFFFF80) == 0)
        {
          out.write(paramInt);
          return;
        }
        out.write(paramInt & 0x7F | 0x80);
        paramInt >>>= 7;
      }
    }
  }
  
  public static class StatusResponse
  {
    private String description;
    private Descriptions descriptions;
    private Players players;
    private Version version;
    private String favicon;
    private int time;
    private int ms;
    
    public int hashCode()
    {
      int result = 1;
      Object $description = getDescription();
      result = result * 59 + ($description == null ? 0 : $description.hashCode());
      Object $players = getPlayers();
      result = result * 59 + ($players == null ? 0 : $players.hashCode());
      Object $version = getVersion();
      result = result * 59 + ($version == null ? 0 : $version.hashCode());
      Object $favicon = getFavicon();
      result = result * 59 + ($favicon == null ? 0 : $favicon.hashCode());
      result = result * 59 + getTime();
      return result;
    }
    
    public boolean canEqual(Object other)
    {
      return other instanceof StatusResponse;
    }
    
    public boolean equals(Object o)
    {
      if (o == this) {
        return true;
      }
      if (!(o instanceof StatusResponse)) {
        return false;
      }
      StatusResponse other = (StatusResponse)o;
      if (!other.canEqual(this)) {
        return false;
      }
      Object this$description = getDescription();
      Object other$description = other.getDescription();
      if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
        return false;
      }
      Object this$players = getPlayers();
      Object other$players = other.getPlayers();
      if (this$players == null ? other$players != null : !this$players.equals(other$players)) {
        return false;
      }
      Object this$version = getVersion();
      Object other$version = other.getVersion();
      if (this$version == null ? other$version != null : !this$version.equals(other$version)) {
        return false;
      }
      Object this$favicon = getFavicon();
      Object other$favicon = other.getFavicon();
      if (this$favicon == null ? other$favicon != null : !this$favicon.equals(other$favicon)) {
        return false;
      }
      return getTime() == other.getTime();
    }
    
    public String toString()
    {
      return "StatusResponse(description=" + getDescription() + ", players=" + getPlayers() + ", version=" + getVersion() + ", favicon=" + getFavicon() + ", time=" + getTime() + ")";
    }
    
    public String getDescription()
    {
      if ((this.description == null) || (this.description.isEmpty())) {
        return this.descriptions.getText();
      }
      return this.description;
    }
    
    public void setDescription(String description)
    {
      this.description = description;
    }
    
    public Players getPlayers()
    {
      return this.players;
    }
    
    public int getMs()
    {
      return this.ms;
    }
    
    public void setPlayers(Players players)
    {
      this.players = players;
    }
    
    public Version getVersion()
    {
      return this.version;
    }
    
    public void setVersion(Version version)
    {
      this.version = version;
    }
    
    public String getFavicon()
    {
      return this.favicon;
    }
    
    public void setFavicon(String favicon)
    {
      this.favicon = favicon;
    }
    
    public void setTime(int time)
    {
      this.time = time;
    }
    
    public int getTime()
    {
      return this.time;
    }
    
    public class Players
    {
      private int max;
      private int online;
      private List<PingUtils.StatusResponse.Player> sample;
      
      public boolean canEqual(Object other)
      {
        return other instanceof Players;
      }
      
      public boolean equals(Object o)
      {
        if (o == this) {
          return true;
        }
        if (!(o instanceof Players)) {
          return false;
        }
        Players other = (Players)o;
        if (!other.canEqual(this)) {
          return false;
        }
        if (getMax() != other.getMax()) {
          return false;
        }
        if (getOnline() != other.getOnline()) {
          return false;
        }
        Object this$sample = getSample();
        Object other$sample = other.getSample();
        return this$sample == null ? false : other$sample == null ? true : this$sample.equals(other$sample);
      }
      
      public int hashCode()
      {
        int result = 1;
        result = result * 59 + getMax();
        result = result * 59 + getOnline();
        Object $sample = getSample();
        result = result * 59 + ($sample == null ? 0 : $sample.hashCode());
        return result;
      }
      
      public String toString()
      {
        return "StatusResponse.Players(max=" + getMax() + ", online=" + getOnline() + ", sample=" + getSample() + ")";
      }
      
      public void setMax(int max)
      {
        this.max = max;
      }
      
      public int getMax()
      {
        return this.max;
      }
      
      public void setOnline(int online)
      {
        this.online = online;
      }
      
      public int getOnline()
      {
        return this.online;
      }
      
      public List<PingUtils.StatusResponse.Player> getSample()
      {
        return this.sample;
      }
      
      public void setSample(List<PingUtils.StatusResponse.Player> sample)
      {
        this.sample = sample;
      }
      
      public Players() {}
    }
    
    public class Descriptions
    {
      private String text;
      
      public Descriptions() {}
      
      public String getText()
      {
        return this.text;
      }
      
      public void setText(String text)
      {
        this.text = text;
      }
    }
    
    public class Player
    {
      private String name;
      private String id;
      
      public boolean equals(Object o)
      {
        if (o == this) {
          return true;
        }
        if (!(o instanceof Player)) {
          return false;
        }
        Player other = (Player)o;
        if (!other.canEqual(this)) {
          return false;
        }
        Object this$name = getName();
        Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
          return false;
        }
        Object this$id = getId();
        Object other$id = other.getId();
        return this$id == null ? false : other$id == null ? true : this$id.equals(other$id);
      }
      
      public int hashCode()
      {
        int result = 1;
        Object $name = getName();
        result = result * 59 + ($name == null ? 0 : $name.hashCode());
        Object $id = getId();
        result = result * 59 + ($id == null ? 0 : $id.hashCode());
        return result;
      }
      
      public boolean canEqual(Object other)
      {
        return other instanceof Player;
      }
      
      public String toString()
      {
        return "StatusResponse.Player(name=" + getName() + ", id=" + getId() + ")";
      }
      
      public void setName(String name)
      {
        this.name = name;
      }
      
      public String getName()
      {
        return this.name;
      }
      
      public void setId(String id)
      {
        this.id = id;
      }
      
      public String getId()
      {
        return this.id;
      }
      
      public Player() {}
    }
    
    public class Version
    {
      private String name;
      private String protocol;
      
      public boolean equals(Object o)
      {
        if (o == this) {
          return true;
        }
        if (!(o instanceof Version)) {
          return false;
        }
        Version other = (Version)o;
        if (!other.canEqual(this)) {
          return false;
        }
        Object this$name = getName();
        Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
          return false;
        }
        Object this$protocol = getProtocol();
        Object other$protocol = other.getProtocol();
        return this$protocol == null ? false : other$protocol == null ? true : this$protocol.equals(other$protocol);
      }
      
      public int hashCode()
      {
        int result = 1;
        Object $name = getName();
        result = result * 59 + ($name == null ? 0 : $name.hashCode());
        Object $protocol = getProtocol();
        result = result * 59 + ($protocol == null ? 0 : $protocol.hashCode());
        return result;
      }
      
      public boolean canEqual(Object other)
      {
        return other instanceof Version;
      }
      
      public String toString()
      {
        return "StatusResponse.Version(name=" + getName() + ", protocol=" + getProtocol() + ")";
      }
      
      public void setName(String name)
      {
        this.name = name;
      }
      
      public String getName()
      {
        return this.name;
      }
      
      public void setProtocol(String protocol)
      {
        this.protocol = protocol;
      }
      
      public String getProtocol()
      {
        return this.protocol;
      }
      
      public Version() {}
    }
  }
}
