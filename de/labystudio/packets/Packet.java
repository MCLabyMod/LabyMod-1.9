package de.labystudio.packets;

import de.labystudio.handling.PacketHandler;

public abstract class Packet
{
  public abstract void read(PacketBuf paramPacketBuf);
  
  public abstract void write(PacketBuf paramPacketBuf);
  
  public abstract void handle(PacketHandler paramPacketHandler);
}
