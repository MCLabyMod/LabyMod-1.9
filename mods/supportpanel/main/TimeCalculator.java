package mods.supportpanel.main;

public class TimeCalculator
{
  public static String parseTime(long time)
  {
    long current = System.currentTimeMillis();
    long dif = (current - time) / 1000L;
    
    String v = "vor ";
    if ((dif > 31104000L) && (dif / 60L / 60L / 24L / 30L / 12L != 0L)) {
      return v + dif / 60L / 60L / 24L / 30L / 12L + " Jahr" + mdf((int)(dif / 60L / 60L / 24L / 30L / 12L), "en");
    }
    if ((dif > 2592000L) && (dif / 60L / 60L / 24L / 30L != 0L)) {
      return v + dif / 60L / 60L / 24L / 30L + " Monat" + mdf((int)(dif / 60L / 60L / 24L / 30L), "en");
    }
    if ((dif > 86400L) && (dif / 60L / 60L / 24L != 0L)) {
      return v + dif / 60L / 60L / 24L + " Tag" + mdf((int)(dif / 60L / 60L / 24L), "en");
    }
    if ((dif > 3600L) && (dif / 60L / 60L != 0L)) {
      return v + dif / 60L / 60L + " Stunde" + mdf((int)(dif / 60L / 60L), "n");
    }
    if ((dif > 60L) && (dif / 60L != 0L)) {
      return v + dif / 60L + " Minute" + mdf((int)(dif / 60L), "n");
    }
    if ((dif > 0L) && (dif != 0L)) {
      return v + dif + " Sekunde" + mdf((int)dif, "n");
    }
    return "?";
  }
  
  public static String mdf(int amount, String m)
  {
    if (amount == 1) {
      return "";
    }
    return m;
  }
}
