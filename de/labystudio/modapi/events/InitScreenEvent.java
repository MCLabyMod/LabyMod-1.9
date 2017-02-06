package de.labystudio.modapi.events;

import bcz;
import bfb;
import de.labystudio.modapi.Event;
import de.labystudio.modapi.Listener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InitScreenEvent
  extends Event
{
  public static final Map<Listener, List<Method>> listenerMethods = new HashMap();
  private bfb screen;
  private List<bcz> buttonList;
  
  public InitScreenEvent(bfb screen, List<bcz> buttonList)
  {
    this.screen = screen;
    this.buttonList = buttonList;
  }
  
  public bfb getScreen()
  {
    return this.screen;
  }
  
  public List<bcz> getButtonList()
  {
    return this.buttonList;
  }
  
  public Map<Listener, List<Method>> getListenerMethods()
  {
    return listenerMethods;
  }
}
