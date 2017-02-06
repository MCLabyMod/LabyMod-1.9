import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SmartLeaves
{
  private static bxo modelLeavesCullAcacia = null;
  private static bxo modelLeavesCullBirch = null;
  private static bxo modelLeavesCullDarkOak = null;
  private static bxo modelLeavesCullJungle = null;
  private static bxo modelLeavesCullOak = null;
  private static bxo modelLeavesCullSpruce = null;
  private static List generalQuadsCullAcacia = null;
  private static List generalQuadsCullBirch = null;
  private static List generalQuadsCullDarkOak = null;
  private static List generalQuadsCullJungle = null;
  private static List generalQuadsCullOak = null;
  private static List generalQuadsCullSpruce = null;
  private static bxo modelLeavesDoubleAcacia = null;
  private static bxo modelLeavesDoubleBirch = null;
  private static bxo modelLeavesDoubleDarkOak = null;
  private static bxo modelLeavesDoubleJungle = null;
  private static bxo modelLeavesDoubleOak = null;
  private static bxo modelLeavesDoubleSpruce = null;
  
  public static bxo getLeavesModel(bxo model)
  {
    if (!Config.isTreesSmart()) {
      return model;
    }
    List generalQuads = model.a(null, null, 0L);
    if (generalQuads == generalQuadsCullAcacia) {
      return modelLeavesDoubleAcacia;
    }
    if (generalQuads == generalQuadsCullBirch) {
      return modelLeavesDoubleBirch;
    }
    if (generalQuads == generalQuadsCullDarkOak) {
      return modelLeavesDoubleDarkOak;
    }
    if (generalQuads == generalQuadsCullJungle) {
      return modelLeavesDoubleJungle;
    }
    if (generalQuads == generalQuadsCullOak) {
      return modelLeavesDoubleOak;
    }
    if (generalQuads == generalQuadsCullSpruce) {
      return modelLeavesDoubleSpruce;
    }
    return model;
  }
  
  public static void updateLeavesModels()
  {
    List updatedTypes = new ArrayList();
    
    modelLeavesCullAcacia = getModelCull("acacia", updatedTypes);
    modelLeavesCullBirch = getModelCull("birch", updatedTypes);
    modelLeavesCullDarkOak = getModelCull("dark_oak", updatedTypes);
    modelLeavesCullJungle = getModelCull("jungle", updatedTypes);
    modelLeavesCullOak = getModelCull("oak", updatedTypes);
    modelLeavesCullSpruce = getModelCull("spruce", updatedTypes);
    
    generalQuadsCullAcacia = getGeneralQuadsSafe(modelLeavesCullAcacia);
    generalQuadsCullBirch = getGeneralQuadsSafe(modelLeavesCullBirch);
    generalQuadsCullDarkOak = getGeneralQuadsSafe(modelLeavesCullDarkOak);
    generalQuadsCullJungle = getGeneralQuadsSafe(modelLeavesCullJungle);
    generalQuadsCullOak = getGeneralQuadsSafe(modelLeavesCullOak);
    generalQuadsCullSpruce = getGeneralQuadsSafe(modelLeavesCullSpruce);
    
    modelLeavesDoubleAcacia = getModelDoubleFace(modelLeavesCullAcacia);
    modelLeavesDoubleBirch = getModelDoubleFace(modelLeavesCullBirch);
    modelLeavesDoubleDarkOak = getModelDoubleFace(modelLeavesCullDarkOak);
    modelLeavesDoubleJungle = getModelDoubleFace(modelLeavesCullJungle);
    modelLeavesDoubleOak = getModelDoubleFace(modelLeavesCullOak);
    modelLeavesDoubleSpruce = getModelDoubleFace(modelLeavesCullSpruce);
    if (updatedTypes.size() > 0) {
      Config.dbg("Enable face culling: " + Config.arrayToString(updatedTypes.toArray()));
    }
  }
  
  private static List getGeneralQuadsSafe(bxo model)
  {
    if (model == null) {
      return null;
    }
    return model.a(null, null, 0L);
  }
  
  static bxo getModelCull(String type, List updatedTypes)
  {
    bxs modelManager = Config.getModelManager();
    if (modelManager == null) {
      return null;
    }
    kk locState = new kk("blockstates/" + type + "_leaves.json");
    if (Config.getDefiningResourcePack(locState) != Config.getDefaultResourcePack()) {
      return null;
    }
    kk locModel = new kk("models/block/" + type + "_leaves.json");
    if (Config.getDefiningResourcePack(locModel) != Config.getDefaultResourcePack()) {
      return null;
    }
    bxt mrl = new bxt(type + "_leaves", "normal");
    bxo model = modelManager.a(mrl);
    if ((model == null) || (model == modelManager.a())) {
      return null;
    }
    List listGeneral = model.a(null, null, 0L);
    if (listGeneral.size() != 6) {
      return null;
    }
    for (Iterator it = listGeneral.iterator(); it.hasNext();)
    {
      bof quad = (bof)it.next();
      List listFace = model.a(null, quad.e(), 0L);
      if (listFace.size() > 0) {
        return null;
      }
      listFace.add(quad);
    }
    listGeneral.clear();
    
    updatedTypes.add(type + "_leaves");
    
    return model;
  }
  
  private static bxo getModelDoubleFace(bxo model)
  {
    if (model == null) {
      return null;
    }
    if (model.a(null, null, 0L).size() > 0)
    {
      Config.warn("SmartLeaves: Model is not cube, general quads: " + model.a(null, null, 0L).size() + ", model: " + model);
      return model;
    }
    cq[] faces = cq.n;
    for (int i = 0; i < faces.length; i++)
    {
      cq face = faces[i];
      List<bof> quads = model.a(null, face, 0L);
      if (quads.size() != 1)
      {
        Config.warn("SmartLeaves: Model is not cube, side: " + face + ", quads: " + quads.size() + ", model: " + model);
        return model;
      }
    }
    bxo model2 = ModelUtils.duplicateModel(model);
    
    List[] faceQuads = new List[faces.length];
    for (int i = 0; i < faces.length; i++)
    {
      cq face = faces[i];
      List<bof> quads = model2.a(null, face, 0L);
      
      bof quad = (bof)quads.get(0);
      
      bof quad2 = new bof((int[])quad.b().clone(), quad.d(), quad.e(), quad.a());
      int[] vd = quad2.b();
      int[] vd2 = (int[])vd.clone();
      int step = vd.length / 4;
      
      System.arraycopy(vd, 0 * step, vd2, 3 * step, step);
      System.arraycopy(vd, 1 * step, vd2, 2 * step, step);
      System.arraycopy(vd, 2 * step, vd2, 1 * step, step);
      System.arraycopy(vd, 3 * step, vd2, 0 * step, step);
      
      System.arraycopy(vd2, 0, vd, 0, vd2.length);
      
      quads.add(quad2);
    }
    return model2;
  }
}
