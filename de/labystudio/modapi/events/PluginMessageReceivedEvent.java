package de.labystudio.modapi.events;

import de.labystudio.modapi.Event;
import de.labystudio.modapi.Listener;
import em;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginMessageReceivedEvent
  extends Event
{
  public static final Map<Listener, List<Method>> listenerMethods = new HashMap();
  private String channel;
  private em data;
  
  public PluginMessageReceivedEvent(String channel, em data)
  {
    this.channel = channel;
    this.data = data;
  }
  
  public Map<Listener, List<Method>> getListenerMethods()
  {
    return listenerMethods;
  }
  
  public String getChannel()
  {
    return this.channel;
  }
  
  public em getData()
  {
    return this.data;
  }
}
