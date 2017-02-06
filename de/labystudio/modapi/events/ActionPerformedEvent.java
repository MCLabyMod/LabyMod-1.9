package de.labystudio.modapi.events;

import bcz;
import bfb;
import de.labystudio.modapi.Event;
import de.labystudio.modapi.Listener;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionPerformedEvent
  extends Event
{
  public static final Map<Listener, List<Method>> listenerMethods = new HashMap();
  private bfb screen;
  private bcz button;
  
  public ActionPerformedEvent(bfb screen, bcz button)
  {
    this.screen = screen;
    this.button = button;
  }
  
  public bfb getScreen()
  {
    return this.screen;
  }
  
  public bcz getButton()
  {
    return this.button;
  }
  
  public Map<Listener, List<Method>> getListenerMethods()
  {
    return listenerMethods;
  }
}
