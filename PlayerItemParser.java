import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PlayerItemParser
{
  private static JsonParser jsonParser = new JsonParser();
  public static final String ITEM_TYPE = "type";
  public static final String ITEM_TEXTURE_SIZE = "textureSize";
  public static final String ITEM_USE_PLAYER_TEXTURE = "usePlayerTexture";
  public static final String ITEM_MODELS = "models";
  public static final String MODEL_ID = "id";
  public static final String MODEL_BASE_ID = "baseId";
  public static final String MODEL_TYPE = "type";
  public static final String MODEL_ATTACH_TO = "attachTo";
  public static final String MODEL_INVERT_AXIS = "invertAxis";
  public static final String MODEL_MIRROR_TEXTURE = "mirrorTexture";
  public static final String MODEL_TRANSLATE = "translate";
  public static final String MODEL_ROTATE = "rotate";
  public static final String MODEL_SCALE = "scale";
  public static final String MODEL_BOXES = "boxes";
  public static final String MODEL_SPRITES = "sprites";
  public static final String MODEL_SUBMODEL = "submodel";
  public static final String MODEL_SUBMODELS = "submodels";
  public static final String BOX_TEXTURE_OFFSET = "textureOffset";
  public static final String BOX_COORDINATES = "coordinates";
  public static final String BOX_SIZE_ADD = "sizeAdd";
  public static final String ITEM_TYPE_MODEL = "PlayerItem";
  public static final String MODEL_TYPE_BOX = "ModelBox";
  
  public static PlayerItemModel parseItemModel(JsonObject obj)
  {
    String type = Json.getString(obj, "type");
    if (!Config.equals(type, "PlayerItem")) {
      throw new JsonParseException("Unknown model type: " + type);
    }
    int[] textureSize = Json.parseIntArray(obj.get("textureSize"), 2);
    checkNull(textureSize, "Missing texture size");
    
    Dimension textureDim = new Dimension(textureSize[0], textureSize[1]);
    
    boolean usePlayerTexture = Json.getBoolean(obj, "usePlayerTexture", false);
    
    JsonArray models = (JsonArray)obj.get("models");
    checkNull(models, "Missing elements");
    
    Map mapModelJsons = new HashMap();
    
    List listModels = new ArrayList();
    List listAttachTos = new ArrayList();
    for (int i = 0; i < models.size(); i++)
    {
      JsonObject elem = (JsonObject)models.get(i);
      
      String baseId = Json.getString(elem, "baseId");
      Iterator iterator;
      if (baseId != null)
      {
        JsonObject baseObj = (JsonObject)mapModelJsons.get(baseId);
        if (baseObj == null)
        {
          Config.warn("BaseID not found: " + baseId);
        }
        else
        {
          Set<Map.Entry<String, JsonElement>> setEntries = baseObj.entrySet();
          for (iterator = setEntries.iterator(); iterator.hasNext();)
          {
            Map.Entry<String, JsonElement> entry = (Map.Entry)iterator.next();
            if (!elem.has((String)entry.getKey())) {
              elem.add((String)entry.getKey(), (JsonElement)entry.getValue());
            }
          }
        }
      }
      else
      {
        String id = Json.getString(elem, "id");
        if (id != null) {
          if (!mapModelJsons.containsKey(id)) {
            mapModelJsons.put(id, elem);
          } else {
            Config.warn("Duplicate model ID: " + id);
          }
        }
        PlayerItemRenderer mr = parseItemRenderer(elem, textureDim);
        if (mr != null) {
          listModels.add(mr);
        }
      }
    }
    PlayerItemRenderer[] modelRenderers = (PlayerItemRenderer[])listModels.toArray(new PlayerItemRenderer[listModels.size()]);
    
    return new PlayerItemModel(textureDim, usePlayerTexture, modelRenderers);
  }
  
  private static void checkNull(Object obj, String msg)
  {
    if (obj == null) {
      throw new JsonParseException(msg);
    }
  }
  
  private static kk makeResourceLocation(String texture)
  {
    int pos = texture.indexOf(':');
    if (pos < 0) {
      return new kk(texture);
    }
    String domain = texture.substring(0, pos);
    String path = texture.substring(pos + 1);
    return new kk(domain, path);
  }
  
  private static int parseAttachModel(String attachModelStr)
  {
    String str = attachModelStr;
    if (str == null) {
      return 0;
    }
    if (str.equals("body")) {
      return 0;
    }
    if (str.equals("head")) {
      return 1;
    }
    if (str.equals("leftArm")) {
      return 2;
    }
    if (str.equals("rightArm")) {
      return 3;
    }
    if (str.equals("leftLeg")) {
      return 4;
    }
    if (str.equals("rightLeg")) {
      return 5;
    }
    if (str.equals("cape")) {
      return 6;
    }
    Config.warn("Unknown attachModel: " + str);
    return 0;
  }
  
  private static PlayerItemRenderer parseItemRenderer(JsonObject elem, Dimension textureDim)
  {
    String type = Json.getString(elem, "type");
    if (!Config.equals(type, "ModelBox"))
    {
      Config.warn("Unknown model type: " + type);
      return null;
    }
    String attachToStr = Json.getString(elem, "attachTo");
    int attachTo = parseAttachModel(attachToStr);
    
    float scale = Json.getFloat(elem, "scale", 1.0F);
    
    bjc modelBase = new ModelPlayerItem();
    modelBase.s = textureDim.width;
    modelBase.t = textureDim.height;
    
    bkm mr = parseModelRenderer(elem, modelBase);
    
    PlayerItemRenderer pir = new PlayerItemRenderer(attachTo, scale, mr);
    
    return pir;
  }
  
  private static bkm parseModelRenderer(JsonObject elem, bjc modelBase)
  {
    bkm mr = new bkm(modelBase);
    
    String invertAxis = Json.getString(elem, "invertAxis", "").toLowerCase();
    boolean invertX = invertAxis.contains("x");
    boolean invertY = invertAxis.contains("y");
    boolean invertZ = invertAxis.contains("z");
    
    float[] translate = Json.parseFloatArray(elem.get("translate"), 3, new float[3]);
    if (invertX) {
      translate[0] = (-translate[0]);
    }
    if (invertY) {
      translate[1] = (-translate[1]);
    }
    if (invertZ) {
      translate[2] = (-translate[2]);
    }
    float[] rotateAngles = Json.parseFloatArray(elem.get("rotate"), 3, new float[3]);
    for (int i = 0; i < rotateAngles.length; i++) {
      rotateAngles[i] = (rotateAngles[i] / 180.0F * 3.1415927F);
    }
    if (invertX) {
      rotateAngles[0] = (-rotateAngles[0]);
    }
    if (invertY) {
      rotateAngles[1] = (-rotateAngles[1]);
    }
    if (invertZ) {
      rotateAngles[2] = (-rotateAngles[2]);
    }
    mr.a(translate[0], translate[1], translate[2]);
    
    mr.f = rotateAngles[0];
    mr.g = rotateAngles[1];
    mr.h = rotateAngles[2];
    
    String mirrorTexture = Json.getString(elem, "mirrorTexture", "").toLowerCase();
    boolean invertU = mirrorTexture.contains("u");
    boolean invertV = mirrorTexture.contains("v");
    if (invertU) {
      mr.i = true;
    }
    if (invertV) {
      mr.mirrorV = true;
    }
    JsonArray boxes = elem.getAsJsonArray("boxes");
    if (boxes != null) {
      for (int i = 0; i < boxes.size(); i++)
      {
        JsonObject box = boxes.get(i).getAsJsonObject();
        
        int[] textureOffset = Json.parseIntArray(box.get("textureOffset"), 2);
        if (textureOffset == null) {
          throw new JsonParseException("Texture offset not specified");
        }
        float[] coordinates = Json.parseFloatArray(box.get("coordinates"), 6);
        if (coordinates == null) {
          throw new JsonParseException("Coordinates not specified");
        }
        if (invertX) {
          coordinates[0] = (-coordinates[0] - coordinates[3]);
        }
        if (invertY) {
          coordinates[1] = (-coordinates[1] - coordinates[4]);
        }
        if (invertZ) {
          coordinates[2] = (-coordinates[2] - coordinates[5]);
        }
        float sizeAdd = Json.getFloat(box, "sizeAdd", 0.0F);
        
        mr.a(textureOffset[0], textureOffset[1]);
        
        mr.a(coordinates[0], coordinates[1], coordinates[2], (int)coordinates[3], (int)coordinates[4], (int)coordinates[5], sizeAdd);
      }
    }
    JsonArray sprites = elem.getAsJsonArray("sprites");
    if (sprites != null) {
      for (int i = 0; i < sprites.size(); i++)
      {
        JsonObject sprite = sprites.get(i).getAsJsonObject();
        
        int[] textureOffset = Json.parseIntArray(sprite.get("textureOffset"), 2);
        if (textureOffset == null) {
          throw new JsonParseException("Texture offset not specified");
        }
        float[] coordinates = Json.parseFloatArray(sprite.get("coordinates"), 6);
        if (coordinates == null) {
          throw new JsonParseException("Coordinates not specified");
        }
        if (invertX) {
          coordinates[0] = (-coordinates[0] - coordinates[3]);
        }
        if (invertY) {
          coordinates[1] = (-coordinates[1] - coordinates[4]);
        }
        if (invertZ) {
          coordinates[2] = (-coordinates[2] - coordinates[5]);
        }
        float sizeAdd = Json.getFloat(sprite, "sizeAdd", 0.0F);
        
        mr.a(textureOffset[0], textureOffset[1]);
        
        mr.addSprite(coordinates[0], coordinates[1], coordinates[2], (int)coordinates[3], (int)coordinates[4], (int)coordinates[5], sizeAdd);
      }
    }
    JsonObject submodel = (JsonObject)elem.get("submodel");
    if (submodel != null)
    {
      bkm subMr = parseModelRenderer(submodel, modelBase);
      mr.a(subMr);
    }
    JsonArray submodels = (JsonArray)elem.get("submodels");
    if (submodels != null) {
      for (int i = 0; i < submodels.size(); i++)
      {
        JsonObject sm = (JsonObject)submodels.get(i);
        bkm subMr = parseModelRenderer(sm, modelBase);
        mr.a(subMr);
      }
    }
    return mr;
  }
}
