import java.util.List;

public class asf
{
  private int a;
  private int b;
  private int c;
  private arx d;
  private asa e;
  private asa f;
  
  public asf(int y, boolean storeSkylight)
  {
    this.a = y;
    this.d = new arx();
    this.e = new asa();
    if (storeSkylight) {
      this.f = new asa();
    }
  }
  
  public arc a(int x, int y, int z)
  {
    return this.d.a(x, y, z);
  }
  
  public void a(int x, int y, int z, arc state)
  {
    if (Reflector.IExtendedBlockState.isInstance(state)) {
      state = (arc)Reflector.call(state, Reflector.IExtendedBlockState_getClean, new Object[0]);
    }
    arc iblockstate = a(x, y, z);
    ajt block = iblockstate.t();
    ajt block1 = state.t();
    if (block != aju.a)
    {
      this.b -= 1;
      if (block.l()) {
        this.c -= 1;
      }
    }
    if (block1 != aju.a)
    {
      this.b += 1;
      if (block1.l()) {
        this.c += 1;
      }
    }
    this.d.a(x, y, z, state);
  }
  
  public boolean a()
  {
    return this.b == 0;
  }
  
  public boolean b()
  {
    return this.c > 0;
  }
  
  public int d()
  {
    return this.a;
  }
  
  public void a(int x, int y, int z, int value)
  {
    this.f.a(x, y, z, value);
  }
  
  public int b(int x, int y, int z)
  {
    return this.f.a(x, y, z);
  }
  
  public void b(int x, int y, int z, int value)
  {
    this.e.a(x, y, z, value);
  }
  
  public int c(int x, int y, int z)
  {
    return this.e.a(x, y, z);
  }
  
  public void e()
  {
    List blockStates = ajt.i.getObjectList();
    int maxStateId = blockStates.size();
    arc STATE_AIR = aju.a.u();
    
    int localBlockRefCount = 0;
    int localTickRefCount = 0;
    for (int y = 0; y < 16; y++)
    {
      int by = y << 8;
      for (int z = 0; z < 16; z++)
      {
        int byz = by | z << 4;
        for (int x = 0; x < 16; x++)
        {
          arc bs = this.d.a(x, y, z);
          if (bs != STATE_AIR)
          {
            localBlockRefCount++;
            ajt block = bs.t();
            if (block.l()) {
              localTickRefCount++;
            }
          }
        }
      }
    }
    this.b = localBlockRefCount;
    this.c = localTickRefCount;
  }
  
  public arx g()
  {
    return this.d;
  }
  
  public asa h()
  {
    return this.e;
  }
  
  public asa i()
  {
    return this.f;
  }
  
  public void a(asa newBlocklightArray)
  {
    this.e = newBlocklightArray;
  }
  
  public void b(asa newSkylightArray)
  {
    this.f = newSkylightArray;
  }
}
