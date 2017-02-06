package shadersmod.client;

import Lang;
import java.util.ArrayList;
import java.util.List;

public class ShaderOptionProfile
  extends ShaderOption
{
  private ShaderProfile[] profiles = null;
  private ShaderOption[] options = null;
  private static final String NAME_PROFILE = "<profile>";
  private static final String VALUE_CUSTOM = "<custom>";
  
  public ShaderOptionProfile(ShaderProfile[] profiles, ShaderOption[] options)
  {
    super("<profile>", "", detectProfileName(profiles, options), getProfileNames(profiles), detectProfileName(profiles, options, true), null);
    this.profiles = profiles;
    this.options = options;
  }
  
  public void nextValue()
  {
    super.nextValue();
    if (getValue().equals("<custom>")) {
      super.nextValue();
    }
    applyProfileOptions();
  }
  
  public void updateProfile()
  {
    ShaderProfile prof = getProfile(getValue());
    if (prof != null) {
      if (ShaderUtils.matchProfile(prof, this.options, false)) {
        return;
      }
    }
    String val = detectProfileName(this.profiles, this.options);
    setValue(val);
  }
  
  private void applyProfileOptions()
  {
    ShaderProfile prof = getProfile(getValue());
    if (prof == null) {
      return;
    }
    String[] opts = prof.getOptions();
    for (int i = 0; i < opts.length; i++)
    {
      String name = opts[i];
      ShaderOption so = getOption(name);
      if (so != null)
      {
        String val = prof.getValue(name);
        so.setValue(val);
      }
    }
  }
  
  private ShaderOption getOption(String name)
  {
    for (int i = 0; i < this.options.length; i++)
    {
      ShaderOption so = this.options[i];
      if (so.getName().equals(name)) {
        return so;
      }
    }
    return null;
  }
  
  private ShaderProfile getProfile(String name)
  {
    for (int i = 0; i < this.profiles.length; i++)
    {
      ShaderProfile prof = this.profiles[i];
      if (prof.getName().equals(name)) {
        return prof;
      }
    }
    return null;
  }
  
  public String getNameText()
  {
    return Lang.get("of.shaders.profile");
  }
  
  public String getValueText(String val)
  {
    if (val.equals("<custom>")) {
      return Lang.get("of.general.custom", "<custom>");
    }
    return Shaders.translate("profile." + val, val);
  }
  
  public String getValueColor(String val)
  {
    if (val.equals("<custom>")) {
      return "§c";
    }
    return "§a";
  }
  
  private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts)
  {
    return detectProfileName(profs, opts, false);
  }
  
  private static String detectProfileName(ShaderProfile[] profs, ShaderOption[] opts, boolean def)
  {
    ShaderProfile prof = ShaderUtils.detectProfile(profs, opts, def);
    if (prof == null) {
      return "<custom>";
    }
    return prof.getName();
  }
  
  private static String[] getProfileNames(ShaderProfile[] profs)
  {
    List<String> list = new ArrayList();
    for (int i = 0; i < profs.length; i++)
    {
      ShaderProfile prof = profs[i];
      list.add(prof.getName());
    }
    list.add("<custom>");
    
    String[] names = (String[])list.toArray(new String[list.size()]);
    
    return names;
  }
}
