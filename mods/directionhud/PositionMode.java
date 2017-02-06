package mods.directionhud;

public enum PositionMode
{
  CUSTOM,  TOPCENTER,  TOPLEFT,  TOPRIGHT,  MIDDLELEFT,  MIDDLECENTER,  MIDDLERIGHT,  BOTTOMLEFT,  BOTTOMCENTER,  BOTTOMRIGHT;
  
  private PositionMode() {}
  
  public static PositionMode getByName(String name)
  {
    for (PositionMode constant : (PositionMode[])PositionMode.class.getEnumConstants()) {
      if (constant.name().equals(name)) {
        return constant;
      }
    }
    return CUSTOM;
  }
  
  public static PositionMode getNext(String nameOfCurrent)
  {
    return getNext(getByName(nameOfCurrent));
  }
  
  public static PositionMode getNext(PositionMode before)
  {
    PositionMode next = null;
    boolean chooseNext = false;
    for (PositionMode constant : (PositionMode[])PositionMode.class.getEnumConstants()) {
      if (constant.equals(before))
      {
        chooseNext = true;
      }
      else if (chooseNext)
      {
        next = constant;
        break;
      }
    }
    if ((next == null) && (chooseNext)) {
      return CUSTOM;
    }
    return next;
  }
}
