package de.labystudio.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;

public class Utils
{
  public static String download(String urlStr)
  {
    try
    {
      URL url = new URL(urlStr);
      
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      HttpURLConnection.setFollowRedirects(true);
      conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
      String encoding = conn.getContentEncoding();
      InputStream inStr = null;
      if ((encoding != null) && (encoding.equalsIgnoreCase("gzip"))) {
        inStr = new GZIPInputStream(conn.getInputStream());
      } else if ((encoding != null) && (encoding.equalsIgnoreCase("deflate"))) {
        inStr = new InflaterInputStream(conn.getInputStream(), new Inflater(true));
      } else {
        inStr = conn.getInputStream();
      }
      return IOUtils.toString(inStr);
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String getContentString(String page)
  {
    try
    {
      URLConnection connection = new URL(page).openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      connection.connect();
      BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
      
      String s = "";
      String line;
      while ((line = r.readLine()) != null) {
        s = s + line;
      }
      return s;
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
    return "";
  }
  
  public static ArrayList<String> getContentList(String page)
  {
    try
    {
      URLConnection connection = new URL(page).openConnection();
      connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
      connection.connect();
      BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
      
      ArrayList<String> s = new ArrayList();
      String line;
      while ((line = r.readLine()) != null) {
        s.add(line);
      }
      return s;
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
    return new ArrayList();
  }
  
  public static void openWebpage(URI uri)
  {
    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
    if ((desktop != null) && (desktop.isSupported(Desktop.Action.BROWSE))) {
      try
      {
        desktop.browse(uri);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public static String parseTimeNormal(long time)
  {
    long formata = time / 600L % 60L;
    long formatb = time / 60L % 60L;
    long formatc = time % 60L;
    long formatd = time / 600L / 60L % 24L;
    long formate = time / 600L / 60L / 24L;
    String out = "";
    if (formate != 0L) {
      out = out + formate + "d ";
    }
    if (formatd != 0L) {
      out = out + formatd + "h ";
    }
    if (formatb != 0L) {
      out = out + formatb + "m ";
    }
    if (formatc != 0L) {
      out = out + formatc + "s";
    }
    return out;
  }
  
  public static String performPost(URL url, String parameters, String contentType, boolean returnErrorPage)
    throws IOException
  {
    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
    byte[] paramAsBytes = parameters.getBytes(Charset.forName("UTF-8"));
    
    connection.setConnectTimeout(15000);
    connection.setReadTimeout(15000);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", contentType + "; charset=utf-8");
    
    connection.setRequestProperty("Content-Length", "" + paramAsBytes.length);
    connection.setRequestProperty("Content-Language", "en-US");
    
    connection.setUseCaches(false);
    connection.setDoInput(true);
    connection.setDoOutput(true);
    
    DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
    writer.write(paramAsBytes);
    writer.flush();
    writer.close();
    try
    {
      reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }
    catch (IOException e)
    {
      BufferedReader reader;
      BufferedReader reader;
      if (returnErrorPage)
      {
        InputStream stream = connection.getErrorStream();
        BufferedReader reader;
        if (stream != null) {
          reader = new BufferedReader(new InputStreamReader(stream));
        } else {
          throw e;
        }
      }
      else
      {
        throw e;
      }
    }
    BufferedReader reader;
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null)
    {
      response.append(line);
      response.append('\r');
    }
    reader.close();
    return response.toString();
  }
  
  public static URL constantURL(String input)
  {
    try
    {
      return new URL(input);
    }
    catch (MalformedURLException localMalformedURLException) {}
    return null;
  }
  
  public static String jsonPost(String urlStr, String json)
    throws Exception
  {
    URL url = new URL(urlStr);
    HttpsURLConnection httpConnection = (HttpsURLConnection)url.openConnection();
    httpConnection.setDoOutput(true);
    httpConnection.setDoInput(true);
    httpConnection.setRequestProperty("Content-Type", "application/json");
    httpConnection.setRequestMethod("POST");
    OutputStreamWriter out = new OutputStreamWriter(httpConnection.getOutputStream());
    
    out.write(json);
    out.close();
    BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
    StringBuffer sb = new StringBuffer();
    String str = br.readLine();
    while (str != null)
    {
      sb.append(str);
      str = br.readLine();
    }
    br.close();
    return sb.toString();
  }
  
  public static String normalizeString(String input)
  {
    char[] c = input.toLowerCase().toCharArray();
    
    c[0] = Character.toUpperCase(c[0]);
    
    return new String(c);
  }
  
  public static ArrayList<String> extractDomains(String value)
  {
    value = value.replaceAll(Color.c + "[a-z0-9]", "");
    ArrayList<String> result = new ArrayList();
    if (value == null) {
      return result;
    }
    String domainPattern = "(?i)\\b((?:[a-z][\\w-]+:(?:\\/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}\\/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))";
    
    Pattern p = Pattern.compile(domainPattern, 2);
    Matcher m = p.matcher(value);
    while (m.find()) {
      result.add(value.substring(m.start(0), m.end(0)));
    }
    return result;
  }
  
  public static class ConvertJsonToObject
  {
    private static Gson gson = new GsonBuilder().create();
    
    public static final <T> T getFromJSON(String json, Class<T> clazz)
    {
      return (T)gson.fromJson(json, clazz);
    }
    
    public static final <T> String toJSON(T clazz)
    {
      return gson.toJson(clazz);
    }
  }
}
