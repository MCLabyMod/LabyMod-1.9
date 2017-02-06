package de.labystudio.modapi.events;

import de.labystudio.modapi.Event;
import de.labystudio.modapi.Listener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sa;

public class RenderNametagEvent
  extends Event
{
  public static final Map<Listener, List<Method>> listenerMethods = new HashMap();
  private sa entity;
  private double x;
  private double y;
  private double z;
  
  public Map<Listener, List<Method>> getListenerMethods()
  {
    return listenerMethods;
  }
  
  public RenderNametagEvent(sa entity, double x, double y, double z)
  {
    this.entity = entity;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public sa getEntity()
  {
    return this.entity;
  }
  
  public double getX()
  {
    return this.x;
  }
  
  public double getY()
  {
    return this.y;
  }
  
  public double getZ()
  {
    return this.z;
  }
}
