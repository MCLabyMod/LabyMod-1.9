public class fc
  extends IllegalArgumentException
{
  public fc(fb ☃, String ☃)
  {
    super(String.format("Error parsing: %s: %s", new Object[] { ☃, ☃ }));
  }
  
  public fc(fb ☃, int ☃)
  {
    super(String.format("Invalid index %d requested for %s", new Object[] { Integer.valueOf(☃), ☃ }));
  }
  
  public fc(fb ☃, Throwable ☃)
  {
    super(String.format("Error while parsing: %s", new Object[] { ☃ }), ☃);
  }
}
