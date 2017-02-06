package installer;

public class ModTemplate
{
  private String modName;
  private boolean recommended;
  private String download;
  private boolean prohibited;
  
  public ModTemplate(String modName, boolean recommended, String download, boolean prohibited)
  {
    this.modName = modName;
    this.recommended = recommended;
    this.download = download;
    this.prohibited = prohibited;
  }
  
  public boolean isEnabled()
  {
    return this.recommended;
  }
  
  public String getModName()
  {
    return this.modName;
  }
  
  public void setEnabled(boolean enabled)
  {
    this.recommended = enabled;
  }
  
  public String getDownload()
  {
    return this.download;
  }
  
  public boolean isProhibited()
  {
    return this.prohibited;
  }
}
