package de.labystudio.packets;

import de.labystudio.chat.LabyModPlayer.OnlineStatus;
import de.labystudio.handling.PacketHandler;
import java.util.TimeZone;

public class PacketPlayChangeOptions
  extends Packet
{
  private PacketLoginOptions.Options options;
  
  public PacketPlayChangeOptions(PacketLoginOptions.Options options)
  {
    this.options = options;
  }
  
  public PacketPlayChangeOptions(boolean showServer, LabyModPlayer.OnlineStatus status, TimeZone timeZone)
  {
    this.options = new PacketLoginOptions.Options(showServer, status, timeZone);
  }
  
  public PacketPlayChangeOptions() {}
  
  public void read(PacketBuf buf)
  {
    this.options = new PacketLoginOptions.Options(buf.readBoolean(), buf.readOnlineStatus(), TimeZone.getTimeZone(buf.readString()));
  }
  
  public void write(PacketBuf buf)
  {
    buf.writeBoolean(getOptions().isShowServer());
    buf.writeOnlineStatus(getOptions().getOnlineStatus());
    buf.writeString(getOptions().getTimeZone().getID());
  }
  
  public void handle(PacketHandler packetHandler)
  {
    packetHandler.handle(this);
  }
  
  public PacketLoginOptions.Options getOptions()
  {
    return this.options;
  }
}
