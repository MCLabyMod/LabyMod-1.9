package de.labystudio.language;

import bcf;
import bwp;
import bwq;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class L
{
  private static Map<String, Language> lang = new ConcurrentHashMap();
  public static Language language;
  public static String lastMCCode = "";
  
  public static class Language
  {
    public Map<String, String> translations = new ConcurrentHashMap();
    private String name;
    
    public Language(String name)
    {
      this.name = name;
    }
    
    public void add(String key, String translation)
    {
      this.translations.put(key.toLowerCase(), translation);
    }
    
    public String get(String key)
    {
      return (String)this.translations.get(key.toLowerCase());
    }
    
    public String getName()
    {
      return this.name;
    }
  }
  
  public static void load()
  {
    try
    {
      String path = "/assets/minecraft/lang/";
      load("en", L.class.getResourceAsStream(path + "labymod_en.properties"));
      load("de", L.class.getResourceAsStream(path + "labymod_de.properties"));
      load("pt", L.class.getResourceAsStream(path + "labymod_pt.properties"));
      load("es", L.class.getResourceAsStream(path + "labymod_es.properties"));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    language = (Language)lang.get("en");
    updateLang();
  }
  
  public static void updateLang()
  {
    String get = "en";
    if ((bcf.z() != null) && (bcf.z().Q() != null) && 
      (bcf.z().Q().c() != null) && 
      (bcf.z().Q().c().a() != null)) {
      get = bcf.z().Q().c().a();
    }
    String mcLang = get;
    if (mcLang.startsWith("en")) {
      setLang("en");
    }
    if (mcLang.startsWith("de")) {
      setLang("de");
    }
    if (mcLang.startsWith("pt")) {
      setLang("pt");
    }
    if (mcLang.startsWith("es")) {
      setLang("es");
    }
    lastMCCode = mcLang;
  }
  
  public static void setLang(String s)
  {
    language = (Language)lang.get(s);
  }
  
  public static void load(String name, InputStream input)
    throws IOException
  {
    try
    {
      Properties prop = new Properties();
      prop.load(input);
      Language lang = new Language(name);
      for (Map.Entry<Object, Object> s : prop.entrySet()) {
        lang.translations.put(s.getKey().toString(), s.getValue().toString());
      }
      lang.put(name, lang);
    }
    catch (Exception error)
    {
      System.out.println("Can't load language file " + name);
    }
  }
  
  public static String _(String key, Object... args)
  {
    return translate(key, true, args);
  }
  
  public static String translate(String key, boolean format, Object... args)
  {
    String get = "en";
    if ((bcf.z() != null) && (bcf.z().Q() != null) && 
      (bcf.z().Q().c() != null) && 
      (bcf.z().Q().c().a() != null) && 
      (lastMCCode != bcf.z().Q().c().a())) {
      updateLang();
    }
    if ((key == null) || (language == null)) {
      return "unknown";
    }
    String trans = language.get(key);
    if (trans == null) {
      return "unknown";
    }
    if (format) {
      trans = String.format(trans, args);
    }
    return trans;
  }
  
  public static String translate(String key, Object... args)
  {
    return translate(key, true, args);
  }
  
  public static void registerLanguage(String lang, Language l)
  {
    lang.put(lang.toLowerCase(), l);
  }
  
  public static Language getLanguage()
  {
    return language;
  }
  
  public static Language getLanguage(String l)
  {
    if (l.indexOf("_") == -1) {
      return null;
    }
    String t = l.split("_")[0];
    if (lang.containsKey(t)) {
      return (Language)lang.get(t);
    }
    return (Language)lang.get("en");
  }
}
