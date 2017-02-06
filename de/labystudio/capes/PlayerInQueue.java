package de.labystudio.capes;

import bmq;

public class PlayerInQueue
{
  private bmq player;
  private boolean refresh;
  
  public PlayerInQueue(bmq player, boolean refresh)
  {
    this.player = player;
    this.refresh = refresh;
  }
  
  public bmq getPlayer()
  {
    return this.player;
  }
  
  public boolean isRefresh()
  {
    return this.refresh;
  }
}
