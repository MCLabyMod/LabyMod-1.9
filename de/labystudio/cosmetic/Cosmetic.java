package de.labystudio.cosmetic;

public class Cosmetic
{
  private EnumCosmetic type = EnumCosmetic.NONE;
  private String data;
  public double a;
  public double b;
  public double c;
  public double height;
  
  public Cosmetic(EnumCosmetic type)
  {
    this.type = type;
    parseData();
  }
  
  public Cosmetic(EnumCosmetic type, String data)
  {
    this.type = type;
    this.data = data;
    parseData();
  }
  
  public Cosmetic(int id)
  {
    this.type = getById(id);
    parseData();
  }
  
  public Cosmetic(int id, String data)
  {
    this.type = getById(id);
    this.data = data;
    parseData();
  }
  
  public EnumCosmetic getById(int id)
  {
    int i = 0;
    for (EnumCosmetic t : EnumCosmetic.values())
    {
      if (id == i) {
        return t;
      }
      i++;
    }
    return EnumCosmetic.NONE;
  }
  
  public EnumCosmetic getType()
  {
    return this.type;
  }
  
  public String getData()
  {
    return this.data;
  }
  
  private void parseData()
  {
    if (this.type == null) {
      return;
    }
    if (((this.type == EnumCosmetic.WINGS) || (this.type == EnumCosmetic.HAT) || (this.type == EnumCosmetic.BLAZE)) && 
      (this.data != null) && 
      (this.data.contains(","))) {
      try
      {
        String[] d = this.data.replace(" ", "").split(",");
        if (d.length >= 3)
        {
          this.a = Double.parseDouble(d[0]);
          this.b = Double.parseDouble(d[1]);
          this.c = Double.parseDouble(d[2]);
        }
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
    }
    if (this.type == EnumCosmetic.HAT) {
      this.height = 0.5D;
    }
    if (this.type == EnumCosmetic.DEADMAU5) {
      this.height = 0.25D;
    }
    if ((this.type == EnumCosmetic.TOOL) && 
      (this.data != null) && (this.data.contains(":"))) {
      try
      {
        String[] d = this.data.replace(" ", "").split(":");
        this.a = Integer.parseInt(d[0]);
        this.b = Integer.parseInt(d[1]);
      }
      catch (Exception error)
      {
        error.printStackTrace();
      }
    }
    if (this.type == EnumCosmetic.HALO) {
      this.height = 0.25D;
    }
  }
}
