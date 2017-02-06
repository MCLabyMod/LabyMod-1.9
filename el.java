import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum el
{
  private static int e;
  private static int f;
  private static final el[] g;
  private static final Map<Class<? extends ff<?>>, el> h;
  private final int i;
  private final Map<fg, BiMap<Integer, Class<? extends ff<?>>>> j;
  
  private el(int protocolId)
  {
    this.j = Maps.newEnumMap(fg.class);
    this.i = protocolId;
  }
  
  protected el a(fg direction, Class<? extends ff<?>> packetClass)
  {
    BiMap<Integer, Class<? extends ff<?>>> bimap = (BiMap)this.j.get(direction);
    if (bimap == null)
    {
      bimap = HashBiMap.create();
      this.j.put(direction, bimap);
    }
    if (bimap.containsValue(packetClass))
    {
      String s = direction + " packet " + packetClass + " is already known to ID " + bimap.inverse().get(packetClass);
      LogManager.getLogger().fatal(s);
      throw new IllegalArgumentException(s);
    }
    bimap.put(Integer.valueOf(bimap.size()), packetClass);
    return this;
  }
  
  public Integer a(fg direction, ff<?> packetIn)
  {
    return (Integer)((BiMap)this.j.get(direction)).inverse().get(packetIn.getClass());
  }
  
  public ff<?> a(fg direction, int packetId)
    throws InstantiationException, IllegalAccessException
  {
    Class<? extends ff<?>> oclass = (Class)((BiMap)this.j.get(direction)).get(Integer.valueOf(packetId));
    return oclass == null ? null : (ff)oclass.newInstance();
  }
  
  public int a()
  {
    return this.i;
  }
  
  public static el a(int stateId)
  {
    return (stateId >= e) && (stateId <= f) ? g[(stateId - e)] : null;
  }
  
  public static el a(ff<?> packetIn)
  {
    return (el)h.get(packetIn.getClass());
  }
  
  static
  {
    e = -1;
    f = 2;
    g = new el[f - e + 1];
    h = Maps.newHashMap();
    el enumconnectionstate;
    for (enumconnectionstate : values())
    {
      int i = enumconnectionstate.a();
      if ((i < e) || (i > f)) {
        throw new Error("Invalid protocol ID " + Integer.toString(i));
      }
      g[(i - e)] = enumconnectionstate;
      for (fg enumpacketdirection : enumconnectionstate.j.keySet()) {
        for (Class<? extends ff<?>> oclass : ((BiMap)enumconnectionstate.j.get(enumpacketdirection)).values())
        {
          if ((h.containsKey(oclass)) && (h.get(oclass) != enumconnectionstate)) {
            throw new Error("Packet " + oclass + " is already assigned to protocol " + h.get(oclass) + " - can't reassign to " + enumconnectionstate);
          }
          try
          {
            oclass.newInstance();
          }
          catch (Throwable var10)
          {
            throw new Error("Packet " + oclass + " fails instantiation checks! " + oclass);
          }
          h.put(oclass, enumconnectionstate);
        }
      }
    }
  }
}
