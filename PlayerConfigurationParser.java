import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PlayerConfigurationParser
{
  private String player = null;
  public static final String CONFIG_ITEMS = "items";
  public static final String ITEM_TYPE = "type";
  public static final String ITEM_ACTIVE = "active";
  
  public PlayerConfigurationParser(String player)
  {
    this.player = player;
  }
  
  public PlayerConfiguration parsePlayerConfiguration(JsonElement je)
  {
    if (je == null) {
      throw new JsonParseException("JSON object is null, player: " + this.player);
    }
    JsonObject jo = (JsonObject)je;
    
    PlayerConfiguration pc = new PlayerConfiguration();
    JsonArray items = (JsonArray)jo.get("items");
    if (items != null) {
      for (int i = 0; i < items.size(); i++)
      {
        JsonObject item = (JsonObject)items.get(i);
        
        boolean active = Json.getBoolean(item, "active", true);
        if (active)
        {
          String type = Json.getString(item, "type");
          if (type == null)
          {
            Config.warn("Item type is null, player: " + this.player);
          }
          else
          {
            String modelPath = Json.getString(item, "model");
            if (modelPath == null) {
              modelPath = "items/" + type + "/model.cfg";
            }
            PlayerItemModel model = downloadModel(modelPath);
            if (model != null) {
              if (!model.isUsePlayerTexture())
              {
                String texturePath = Json.getString(item, "texture");
                if (texturePath == null) {
                  texturePath = "items/" + type + "/users/" + this.player + ".png";
                }
                BufferedImage image = downloadTextureImage(texturePath);
                if (image != null)
                {
                  model.setTextureImage(image);
                  
                  kk loc = new kk("optifine.net", texturePath);
                  model.setTextureLocation(loc);
                }
              }
              else
              {
                pc.addPlayerItemModel(model);
              }
            }
          }
        }
      }
    }
    return pc;
  }
  
  private BufferedImage downloadTextureImage(String texturePath)
  {
    String textureUrl = "http://s.optifine.net/" + texturePath;
    try
    {
      byte[] body = HttpPipeline.get(textureUrl, bcf.z().M());
      
      return ImageIO.read(new ByteArrayInputStream(body));
    }
    catch (IOException e)
    {
      Config.warn("Error loading item texture " + texturePath + ": " + e.getClass().getName() + ": " + e.getMessage());
    }
    return null;
  }
  
  private PlayerItemModel downloadModel(String modelPath)
  {
    String modelUrl = "http://s.optifine.net/" + modelPath;
    try
    {
      byte[] bytes = HttpPipeline.get(modelUrl, bcf.z().M());
      String jsonStr = new String(bytes, "ASCII");
      JsonParser jp = new JsonParser();
      JsonObject jo = (JsonObject)jp.parse(jsonStr);
      PlayerItemParser pip = new PlayerItemParser();
      return PlayerItemParser.parseItemModel(jo);
    }
    catch (Exception e)
    {
      Config.warn("Error loading item model " + modelPath + ": " + e.getClass().getName() + ": " + e.getMessage());
    }
    return null;
  }
}
