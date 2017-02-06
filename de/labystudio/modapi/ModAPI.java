package de.labystudio.modapi;

import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ModAPI
{
  public static int registeredEvents = 0;
  
  public static Event callEvent(Event event)
  {
    try
    {
      for (Iterator localIterator1 = event.getListenerMethods().entrySet().iterator(); localIterator1.hasNext();)
      {
        callListeners = (Map.Entry)localIterator1.next();
        for (Method method : (List)callListeners.getValue()) {
          try
          {
            method.invoke(callListeners.getKey(), new Object[] { event });
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }
      Map.Entry<Listener, List<Method>> callListeners;
      return event;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    return null;
  }
  
  public static boolean enabled()
  {
    return registeredEvents != 0;
  }
  
  public static void registerListener(Listener listener)
  {
    try
    {
      for (Method method : listener.getClass().getDeclaredMethods())
      {
        method.setAccessible(true);
        if ((method.isAnnotationPresent(EventHandler.class)) && 
          (method.getParameterTypes()[0].getSuperclass() == Event.class))
        {
          Class<? extends Event> eventClass = method.getParameterTypes()[0];
          Map<Listener, List<Method>> methodList = (Map)eventClass.getDeclaredField("listenerMethods").get(null);
          if (!methodList.containsKey(listener)) {
            methodList.put(listener, new ArrayList());
          }
          List<Method> methods = (List)methodList.get(listener);
          methods.add(method);
        }
      }
      System.out.println("[LabyMod] New listener " + listener.getClass().getSimpleName());
      registeredEvents += 1;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  
  public static void addSettingsButton(String name, bfb screen)
  {
    HashMap<String, bfb> temp = new HashMap();
    temp.putAll(ModManager.getSettings());
    temp.put(name, screen);
    ModManager.setSettings(temp);
  }
  
  public static LabyMod getLabyMod()
  {
    return LabyMod.getInstance();
  }
  
  public static DrawUtils getDrawUtils()
  {
    return LabyMod.getInstance().draw;
  }
  
  public static bfb getLastScreen()
  {
    return ModManager.getLastScreen();
  }
}
