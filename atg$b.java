import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class atg$b
  implements JsonDeserializer<atg.a>, JsonSerializer<atg.a>
{
  public atg.a a(JsonElement ☃, Type ☃, JsonDeserializationContext ☃)
    throws JsonParseException
  {
    JsonObject ☃ = ☃.getAsJsonObject();
    
    atg.a ☃ = new atg.a();
    try
    {
      ☃.b = od.a(☃, "coordinateScale", ☃.b);
      ☃.c = od.a(☃, "heightScale", ☃.c);
      ☃.e = od.a(☃, "lowerLimitScale", ☃.e);
      ☃.d = od.a(☃, "upperLimitScale", ☃.d);
      ☃.f = od.a(☃, "depthNoiseScaleX", ☃.f);
      ☃.g = od.a(☃, "depthNoiseScaleZ", ☃.g);
      ☃.h = od.a(☃, "depthNoiseScaleExponent", ☃.h);
      ☃.i = od.a(☃, "mainNoiseScaleX", ☃.i);
      ☃.j = od.a(☃, "mainNoiseScaleY", ☃.j);
      ☃.k = od.a(☃, "mainNoiseScaleZ", ☃.k);
      ☃.l = od.a(☃, "baseSize", ☃.l);
      ☃.m = od.a(☃, "stretchY", ☃.m);
      ☃.n = od.a(☃, "biomeDepthWeight", ☃.n);
      ☃.o = od.a(☃, "biomeDepthOffset", ☃.o);
      ☃.p = od.a(☃, "biomeScaleWeight", ☃.p);
      ☃.q = od.a(☃, "biomeScaleOffset", ☃.q);
      ☃.r = od.a(☃, "seaLevel", ☃.r);
      
      ☃.s = od.a(☃, "useCaves", ☃.s);
      ☃.t = od.a(☃, "useDungeons", ☃.t);
      ☃.u = od.a(☃, "dungeonChance", ☃.u);
      ☃.v = od.a(☃, "useStrongholds", ☃.v);
      ☃.w = od.a(☃, "useVillages", ☃.w);
      ☃.x = od.a(☃, "useMineShafts", ☃.x);
      ☃.y = od.a(☃, "useTemples", ☃.y);
      ☃.z = od.a(☃, "useMonuments", ☃.z);
      ☃.A = od.a(☃, "useRavines", ☃.A);
      ☃.B = od.a(☃, "useWaterLakes", ☃.B);
      ☃.C = od.a(☃, "waterLakeChance", ☃.C);
      ☃.D = od.a(☃, "useLavaLakes", ☃.D);
      ☃.E = od.a(☃, "lavaLakeChance", ☃.E);
      ☃.F = od.a(☃, "useLavaOceans", ☃.F);
      
      ☃.G = od.a(☃, "fixedBiome", ☃.G);
      if ((☃.G >= 38) || (☃.G < -1)) {
        ☃.G = -1;
      } else if (☃.G >= aig.a(ail.j)) {
        ☃.G += 2;
      }
      ☃.H = od.a(☃, "biomeSize", ☃.H);
      ☃.I = od.a(☃, "riverSize", ☃.I);
      
      ☃.J = od.a(☃, "dirtSize", ☃.J);
      ☃.K = od.a(☃, "dirtCount", ☃.K);
      ☃.L = od.a(☃, "dirtMinHeight", ☃.L);
      ☃.M = od.a(☃, "dirtMaxHeight", ☃.M);
      ☃.N = od.a(☃, "gravelSize", ☃.N);
      ☃.O = od.a(☃, "gravelCount", ☃.O);
      ☃.P = od.a(☃, "gravelMinHeight", ☃.P);
      ☃.Q = od.a(☃, "gravelMaxHeight", ☃.Q);
      ☃.R = od.a(☃, "graniteSize", ☃.R);
      ☃.S = od.a(☃, "graniteCount", ☃.S);
      ☃.T = od.a(☃, "graniteMinHeight", ☃.T);
      ☃.U = od.a(☃, "graniteMaxHeight", ☃.U);
      ☃.V = od.a(☃, "dioriteSize", ☃.V);
      ☃.W = od.a(☃, "dioriteCount", ☃.W);
      ☃.X = od.a(☃, "dioriteMinHeight", ☃.X);
      ☃.Y = od.a(☃, "dioriteMaxHeight", ☃.Y);
      ☃.Z = od.a(☃, "andesiteSize", ☃.Z);
      ☃.aa = od.a(☃, "andesiteCount", ☃.aa);
      ☃.ab = od.a(☃, "andesiteMinHeight", ☃.ab);
      ☃.ac = od.a(☃, "andesiteMaxHeight", ☃.ac);
      ☃.ad = od.a(☃, "coalSize", ☃.ad);
      ☃.ae = od.a(☃, "coalCount", ☃.ae);
      ☃.af = od.a(☃, "coalMinHeight", ☃.af);
      ☃.ag = od.a(☃, "coalMaxHeight", ☃.ag);
      ☃.ah = od.a(☃, "ironSize", ☃.ah);
      ☃.ai = od.a(☃, "ironCount", ☃.ai);
      ☃.aj = od.a(☃, "ironMinHeight", ☃.aj);
      ☃.ak = od.a(☃, "ironMaxHeight", ☃.ak);
      ☃.al = od.a(☃, "goldSize", ☃.al);
      ☃.am = od.a(☃, "goldCount", ☃.am);
      ☃.an = od.a(☃, "goldMinHeight", ☃.an);
      ☃.ao = od.a(☃, "goldMaxHeight", ☃.ao);
      ☃.ap = od.a(☃, "redstoneSize", ☃.ap);
      ☃.aq = od.a(☃, "redstoneCount", ☃.aq);
      ☃.ar = od.a(☃, "redstoneMinHeight", ☃.ar);
      ☃.as = od.a(☃, "redstoneMaxHeight", ☃.as);
      ☃.at = od.a(☃, "diamondSize", ☃.at);
      ☃.au = od.a(☃, "diamondCount", ☃.au);
      ☃.av = od.a(☃, "diamondMinHeight", ☃.av);
      ☃.aw = od.a(☃, "diamondMaxHeight", ☃.aw);
      ☃.ax = od.a(☃, "lapisSize", ☃.ax);
      ☃.ay = od.a(☃, "lapisCount", ☃.ay);
      ☃.az = od.a(☃, "lapisCenterHeight", ☃.az);
      ☃.aA = od.a(☃, "lapisSpread", ☃.aA);
    }
    catch (Exception localException) {}
    return ☃;
  }
  
  public JsonElement a(atg.a ☃, Type ☃, JsonSerializationContext ☃)
  {
    JsonObject ☃ = new JsonObject();
    
    ☃.addProperty("coordinateScale", Float.valueOf(☃.b));
    ☃.addProperty("heightScale", Float.valueOf(☃.c));
    ☃.addProperty("lowerLimitScale", Float.valueOf(☃.e));
    ☃.addProperty("upperLimitScale", Float.valueOf(☃.d));
    ☃.addProperty("depthNoiseScaleX", Float.valueOf(☃.f));
    ☃.addProperty("depthNoiseScaleZ", Float.valueOf(☃.g));
    ☃.addProperty("depthNoiseScaleExponent", Float.valueOf(☃.h));
    ☃.addProperty("mainNoiseScaleX", Float.valueOf(☃.i));
    ☃.addProperty("mainNoiseScaleY", Float.valueOf(☃.j));
    ☃.addProperty("mainNoiseScaleZ", Float.valueOf(☃.k));
    ☃.addProperty("baseSize", Float.valueOf(☃.l));
    ☃.addProperty("stretchY", Float.valueOf(☃.m));
    ☃.addProperty("biomeDepthWeight", Float.valueOf(☃.n));
    ☃.addProperty("biomeDepthOffset", Float.valueOf(☃.o));
    ☃.addProperty("biomeScaleWeight", Float.valueOf(☃.p));
    ☃.addProperty("biomeScaleOffset", Float.valueOf(☃.q));
    ☃.addProperty("seaLevel", Integer.valueOf(☃.r));
    
    ☃.addProperty("useCaves", Boolean.valueOf(☃.s));
    ☃.addProperty("useDungeons", Boolean.valueOf(☃.t));
    ☃.addProperty("dungeonChance", Integer.valueOf(☃.u));
    ☃.addProperty("useStrongholds", Boolean.valueOf(☃.v));
    ☃.addProperty("useVillages", Boolean.valueOf(☃.w));
    ☃.addProperty("useMineShafts", Boolean.valueOf(☃.x));
    ☃.addProperty("useTemples", Boolean.valueOf(☃.y));
    ☃.addProperty("useMonuments", Boolean.valueOf(☃.z));
    ☃.addProperty("useRavines", Boolean.valueOf(☃.A));
    ☃.addProperty("useWaterLakes", Boolean.valueOf(☃.B));
    ☃.addProperty("waterLakeChance", Integer.valueOf(☃.C));
    ☃.addProperty("useLavaLakes", Boolean.valueOf(☃.D));
    ☃.addProperty("lavaLakeChance", Integer.valueOf(☃.E));
    ☃.addProperty("useLavaOceans", Boolean.valueOf(☃.F));
    
    ☃.addProperty("fixedBiome", Integer.valueOf(☃.G));
    ☃.addProperty("biomeSize", Integer.valueOf(☃.H));
    ☃.addProperty("riverSize", Integer.valueOf(☃.I));
    
    ☃.addProperty("dirtSize", Integer.valueOf(☃.J));
    ☃.addProperty("dirtCount", Integer.valueOf(☃.K));
    ☃.addProperty("dirtMinHeight", Integer.valueOf(☃.L));
    ☃.addProperty("dirtMaxHeight", Integer.valueOf(☃.M));
    ☃.addProperty("gravelSize", Integer.valueOf(☃.N));
    ☃.addProperty("gravelCount", Integer.valueOf(☃.O));
    ☃.addProperty("gravelMinHeight", Integer.valueOf(☃.P));
    ☃.addProperty("gravelMaxHeight", Integer.valueOf(☃.Q));
    ☃.addProperty("graniteSize", Integer.valueOf(☃.R));
    ☃.addProperty("graniteCount", Integer.valueOf(☃.S));
    ☃.addProperty("graniteMinHeight", Integer.valueOf(☃.T));
    ☃.addProperty("graniteMaxHeight", Integer.valueOf(☃.U));
    ☃.addProperty("dioriteSize", Integer.valueOf(☃.V));
    ☃.addProperty("dioriteCount", Integer.valueOf(☃.W));
    ☃.addProperty("dioriteMinHeight", Integer.valueOf(☃.X));
    ☃.addProperty("dioriteMaxHeight", Integer.valueOf(☃.Y));
    ☃.addProperty("andesiteSize", Integer.valueOf(☃.Z));
    ☃.addProperty("andesiteCount", Integer.valueOf(☃.aa));
    ☃.addProperty("andesiteMinHeight", Integer.valueOf(☃.ab));
    ☃.addProperty("andesiteMaxHeight", Integer.valueOf(☃.ac));
    ☃.addProperty("coalSize", Integer.valueOf(☃.ad));
    ☃.addProperty("coalCount", Integer.valueOf(☃.ae));
    ☃.addProperty("coalMinHeight", Integer.valueOf(☃.af));
    ☃.addProperty("coalMaxHeight", Integer.valueOf(☃.ag));
    ☃.addProperty("ironSize", Integer.valueOf(☃.ah));
    ☃.addProperty("ironCount", Integer.valueOf(☃.ai));
    ☃.addProperty("ironMinHeight", Integer.valueOf(☃.aj));
    ☃.addProperty("ironMaxHeight", Integer.valueOf(☃.ak));
    ☃.addProperty("goldSize", Integer.valueOf(☃.al));
    ☃.addProperty("goldCount", Integer.valueOf(☃.am));
    ☃.addProperty("goldMinHeight", Integer.valueOf(☃.an));
    ☃.addProperty("goldMaxHeight", Integer.valueOf(☃.ao));
    ☃.addProperty("redstoneSize", Integer.valueOf(☃.ap));
    ☃.addProperty("redstoneCount", Integer.valueOf(☃.aq));
    ☃.addProperty("redstoneMinHeight", Integer.valueOf(☃.ar));
    ☃.addProperty("redstoneMaxHeight", Integer.valueOf(☃.as));
    ☃.addProperty("diamondSize", Integer.valueOf(☃.at));
    ☃.addProperty("diamondCount", Integer.valueOf(☃.au));
    ☃.addProperty("diamondMinHeight", Integer.valueOf(☃.av));
    ☃.addProperty("diamondMaxHeight", Integer.valueOf(☃.aw));
    ☃.addProperty("lapisSize", Integer.valueOf(☃.ax));
    ☃.addProperty("lapisCount", Integer.valueOf(☃.ay));
    ☃.addProperty("lapisCenterHeight", Integer.valueOf(☃.az));
    ☃.addProperty("lapisSpread", Integer.valueOf(☃.aA));
    
    return ☃;
  }
}
