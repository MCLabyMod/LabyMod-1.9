import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConnectedTextures
{
  private static Map[] spriteQuadMaps = null;
  private static ConnectedProperties[][] blockProperties = (ConnectedProperties[][])null;
  private static ConnectedProperties[][] tileProperties = (ConnectedProperties[][])null;
  private static boolean multipass = false;
  private static final int Y_NEG_DOWN = 0;
  private static final int Y_POS_UP = 1;
  private static final int Z_NEG_NORTH = 2;
  private static final int Z_POS_SOUTH = 3;
  private static final int X_NEG_WEST = 4;
  private static final int X_POS_EAST = 5;
  private static final int Y_AXIS = 0;
  private static final int Z_AXIS = 1;
  private static final int X_AXIS = 2;
  private static final String[] propSuffixes = { "", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
  private static final int[] ctmIndexes = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 0, 0, 0, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 0, 0, 0, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 0, 0, 0, 0, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 0, 0, 0, 0, 0 };
  public static final arc AIR_DEFAULT_STATE = aju.a.u();
  private static bvh emptySprite = null;
  
  public static synchronized bof getConnectedTexture(ahx blockAccess, arc blockState, cj blockPos, bof quad, RenderEnv renderEnv)
  {
    bvh spriteIn = quad.a();
    if (spriteIn == null) {
      return quad;
    }
    ajt block = blockState.t();
    
    cq side = quad.e();
    if ((block instanceof apd)) {
      if (spriteIn.i().startsWith("minecraft:blocks/glass_pane_top"))
      {
        arc stateNeighbour = blockAccess.o(blockPos.a(quad.e()));
        if (stateNeighbour == blockState) {
          return getQuad(emptySprite, block, blockState, quad);
        }
      }
    }
    bvh sprite = getConnectedTextureMultiPass(blockAccess, blockState, blockPos, side, spriteIn, renderEnv);
    if (sprite == spriteIn) {
      return quad;
    }
    return getQuad(sprite, block, blockState, quad);
  }
  
  private static bof getQuad(bvh sprite, ajt block, arc blockState, bof quadIn)
  {
    if (spriteQuadMaps == null) {
      return quadIn;
    }
    int spriteIndex = sprite.getIndexInMap();
    if ((spriteIndex < 0) || (spriteIndex >= spriteQuadMaps.length)) {
      return quadIn;
    }
    Map quadMap = spriteQuadMaps[spriteIndex];
    if (quadMap == null)
    {
      quadMap = new IdentityHashMap(1);
      spriteQuadMaps[spriteIndex] = quadMap;
    }
    bof quad = (bof)quadMap.get(quadIn);
    if (quad == null)
    {
      quad = makeSpriteQuad(quadIn, sprite);
      quadMap.put(quadIn, quad);
    }
    return quad;
  }
  
  private static bof makeSpriteQuad(bof quad, bvh sprite)
  {
    int[] data = (int[])quad.b().clone();
    bvh spriteFrom = quad.a();
    for (int i = 0; i < 4; i++) {
      fixVertex(data, i, spriteFrom, sprite);
    }
    bof bq = new bof(data, quad.d(), quad.e(), sprite);
    return bq;
  }
  
  private static void fixVertex(int[] data, int vertex, bvh spriteFrom, bvh spriteTo)
  {
    int mul = data.length / 4;
    int pos = mul * vertex;
    
    float u = Float.intBitsToFloat(data[(pos + 4)]);
    float v = Float.intBitsToFloat(data[(pos + 4 + 1)]);
    
    double su16 = spriteFrom.getSpriteU16(u);
    double sv16 = spriteFrom.getSpriteV16(v);
    
    data[(pos + 4)] = Float.floatToRawIntBits(spriteTo.a(su16));
    data[(pos + 4 + 1)] = Float.floatToRawIntBits(spriteTo.b(sv16));
  }
  
  private static bvh getConnectedTextureMultiPass(ahx blockAccess, arc blockState, cj blockPos, cq side, bvh icon, RenderEnv renderEnv)
  {
    bvh newIcon = getConnectedTextureSingle(blockAccess, blockState, blockPos, side, icon, true, renderEnv);
    if (!multipass) {
      return newIcon;
    }
    if (newIcon == icon) {
      return newIcon;
    }
    bvh mpIcon = newIcon;
    for (int i = 0; i < 3; i++)
    {
      bvh newMpIcon = getConnectedTextureSingle(blockAccess, blockState, blockPos, side, mpIcon, false, renderEnv);
      if (newMpIcon == mpIcon) {
        break;
      }
      mpIcon = newMpIcon;
    }
    return mpIcon;
  }
  
  public static bvh getConnectedTextureSingle(ahx blockAccess, arc blockState, cj blockPos, cq facing, bvh icon, boolean checkBlocks, RenderEnv renderEnv)
  {
    ajt block = blockState.t();
    if (!(blockState instanceof ara)) {
      return icon;
    }
    ara blockStateBase = (ara)blockState;
    if (tileProperties != null)
    {
      int iconId = icon.getIndexInMap();
      if ((iconId >= 0) && (iconId < tileProperties.length))
      {
        ConnectedProperties[] cps = tileProperties[iconId];
        if (cps != null)
        {
          int metadata = blockStateBase.getMetadata();
          
          int side = getSide(facing);
          for (int i = 0; i < cps.length; i++)
          {
            ConnectedProperties cp = cps[i];
            if (cp != null) {
              if (cp.matchesBlock(blockStateBase))
              {
                bvh newIcon = getConnectedTexture(cp, blockAccess, blockState, blockPos, side, icon, metadata, renderEnv);
                if (newIcon != null) {
                  return newIcon;
                }
              }
            }
          }
        }
      }
    }
    if ((blockProperties != null) && (checkBlocks))
    {
      int blockId = renderEnv.getBlockId();
      if ((blockId >= 0) && (blockId < blockProperties.length))
      {
        ConnectedProperties[] cps = blockProperties[blockId];
        if (cps != null)
        {
          int metadata = blockStateBase.getMetadata();
          
          int side = getSide(facing);
          for (int i = 0; i < cps.length; i++)
          {
            ConnectedProperties cp = cps[i];
            if (cp != null) {
              if (cp.matchesIcon(icon)) {
                if (cp.matchesBlock(blockStateBase))
                {
                  bvh newIcon = getConnectedTexture(cp, blockAccess, blockState, blockPos, side, icon, metadata, renderEnv);
                  if (newIcon != null) {
                    return newIcon;
                  }
                }
              }
            }
          }
        }
      }
    }
    return icon;
  }
  
  public static int getSide(cq facing)
  {
    if (facing == null) {
      return -1;
    }
    switch (facing)
    {
    case a: 
      return 0;
    case b: 
      return 1;
    case f: 
      return 5;
    case e: 
      return 4;
    case c: 
      return 2;
    case d: 
      return 3;
    }
    return -1;
  }
  
  private static cq getFacing(int side)
  {
    switch (side)
    {
    case 0: 
      return cq.a;
    case 1: 
      return cq.b;
    case 5: 
      return cq.f;
    case 4: 
      return cq.e;
    case 2: 
      return cq.c;
    case 3: 
      return cq.d;
    }
    return cq.b;
  }
  
  private static bvh getConnectedTexture(ConnectedProperties cp, ahx blockAccess, arc blockState, cj blockPos, int side, bvh icon, int metadata, RenderEnv renderEnv)
  {
    int y = blockPos.q();
    if ((y < cp.minHeight) || (y > cp.maxHeight)) {
      return null;
    }
    if (cp.biomes != null)
    {
      aig blockBiome = blockAccess.b(blockPos);
      boolean biomeOk = false;
      for (int i = 0; i < cp.biomes.length; i++)
      {
        aig biome = cp.biomes[i];
        if (blockBiome == biome)
        {
          biomeOk = true;
          break;
        }
      }
      if (!biomeOk) {
        return null;
      }
    }
    int vertAxis = 0;
    
    int metadataCheck = metadata;
    
    ajt block = blockState.t();
    if ((block instanceof aod))
    {
      vertAxis = getWoodAxis(side, metadata);
      
      metadataCheck &= 0x3;
    }
    if ((block instanceof ans))
    {
      vertAxis = getQuartzAxis(side, metadata);
      if (metadataCheck > 2) {
        metadataCheck = 2;
      }
    }
    if (side >= 0) {
      if (cp.faces != 63)
      {
        int sideCheck = side;
        if (vertAxis != 0) {
          sideCheck = fixSideByAxis(side, vertAxis);
        }
        if ((1 << sideCheck & cp.faces) == 0) {
          return null;
        }
      }
    }
    switch (cp.method)
    {
    case 1: 
      return getConnectedTextureCtm(cp, blockAccess, blockState, blockPos, side, icon, metadata, renderEnv);
    case 2: 
      return getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    case 6: 
      return getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    case 3: 
      return getConnectedTextureTop(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    case 4: 
      return getConnectedTextureRandom(cp, blockPos, side);
    case 5: 
      return getConnectedTextureRepeat(cp, blockPos, side);
    case 7: 
      return getConnectedTextureFixed(cp);
    case 8: 
      return getConnectedTextureHorizontalVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    case 9: 
      return getConnectedTextureVerticalHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    }
    return null;
  }
  
  private static int fixSideByAxis(int side, int vertAxis)
  {
    switch (vertAxis)
    {
    case 0: 
      return side;
    case 1: 
      switch (side)
      {
      case 0: 
        return 2;
      case 1: 
        return 3;
      case 2: 
        return 1;
      case 3: 
        return 0;
      }
      return side;
    case 2: 
      switch (side)
      {
      case 0: 
        return 4;
      case 1: 
        return 5;
      case 4: 
        return 1;
      case 5: 
        return 0;
      }
      return side;
    }
    return side;
  }
  
  private static int getWoodAxis(int side, int metadata)
  {
    int orient = (metadata & 0xC) >> 2;
    switch (orient)
    {
    case 1: 
      return 2;
    case 2: 
      return 1;
    }
    return 0;
  }
  
  private static int getQuartzAxis(int side, int metadata)
  {
    switch (metadata)
    {
    case 3: 
      return 2;
    case 4: 
      return 1;
    }
    return 0;
  }
  
  private static bvh getConnectedTextureRandom(ConnectedProperties cp, cj blockPos, int side)
  {
    if (cp.tileIcons.length == 1) {
      return cp.tileIcons[0];
    }
    int face = side / cp.symmetry * cp.symmetry;
    
    int rand = Config.getRandom(blockPos, face) & 0x7FFFFFFF;
    
    int index = 0;
    if (cp.weights == null)
    {
      index = rand % cp.tileIcons.length;
    }
    else
    {
      int randWeight = rand % cp.sumAllWeights;
      
      int[] sumWeights = cp.sumWeights;
      for (int i = 0; i < sumWeights.length; i++) {
        if (randWeight < sumWeights[i])
        {
          index = i;
          break;
        }
      }
    }
    return cp.tileIcons[index];
  }
  
  private static bvh getConnectedTextureFixed(ConnectedProperties cp)
  {
    return cp.tileIcons[0];
  }
  
  private static bvh getConnectedTextureRepeat(ConnectedProperties cp, cj blockPos, int side)
  {
    if (cp.tileIcons.length == 1) {
      return cp.tileIcons[0];
    }
    int x = blockPos.p();
    int y = blockPos.q();
    int z = blockPos.r();
    
    int nx = 0;
    int ny = 0;
    switch (side)
    {
    case 0: 
      nx = x;
      ny = z;
      break;
    case 1: 
      nx = x;
      ny = z;
      break;
    case 2: 
      nx = -x - 1;
      ny = -y;
      break;
    case 3: 
      nx = x;
      ny = -y;
      break;
    case 4: 
      nx = z;
      ny = -y;
      break;
    case 5: 
      nx = -z - 1;
      ny = -y;
    }
    nx %= cp.width;
    ny %= cp.height;
    if (nx < 0) {
      nx += cp.width;
    }
    if (ny < 0) {
      ny += cp.height;
    }
    int index = ny * cp.width + nx;
    
    return cp.tileIcons[index];
  }
  
  private static bvh getConnectedTextureCtm(ConnectedProperties cp, ahx blockAccess, arc blockState, cj blockPos, int side, bvh icon, int metadata, RenderEnv renderEnv)
  {
    boolean[] borders = renderEnv.getBorderFlags();
    switch (side)
    {
    case 0: 
      borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
      borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
      borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
      borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
      break;
    case 1: 
      borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
      borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
      borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
      borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
      break;
    case 2: 
      borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
      borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
      borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
      borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
      break;
    case 3: 
      borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
      borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
      borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
      borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
      break;
    case 4: 
      borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
      borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
      borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
      borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
      break;
    case 5: 
      borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
      borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
      borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
      borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
    }
    int index = 0;
    if ((borders[0] & (borders[1] == 0 ? 1 : 0) & (borders[2] == 0 ? 1 : 0) & (borders[3] == 0 ? 1 : 0)) != 0) {
      index = 3;
    } else if (((borders[0] == 0 ? 1 : 0) & borders[1] & (borders[2] == 0 ? 1 : 0) & (borders[3] == 0 ? 1 : 0)) != 0) {
      index = 1;
    } else if (((borders[0] == 0 ? 1 : 0) & (borders[1] == 0 ? 1 : 0) & borders[2] & (borders[3] == 0 ? 1 : 0)) != 0) {
      index = 12;
    } else if (((borders[0] == 0 ? 1 : 0) & (borders[1] == 0 ? 1 : 0) & (borders[2] == 0 ? 1 : 0) & borders[3]) != 0) {
      index = 36;
    } else if ((borders[0] & borders[1] & (borders[2] == 0 ? 1 : 0) & (borders[3] == 0 ? 1 : 0)) != 0) {
      index = 2;
    } else if (((borders[0] == 0 ? 1 : 0) & (borders[1] == 0 ? 1 : 0) & borders[2] & borders[3]) != 0) {
      index = 24;
    } else if ((borders[0] & (borders[1] == 0 ? 1 : 0) & borders[2] & (borders[3] == 0 ? 1 : 0)) != 0) {
      index = 15;
    } else if ((borders[0] & (borders[1] == 0 ? 1 : 0) & (borders[2] == 0 ? 1 : 0) & borders[3]) != 0) {
      index = 39;
    } else if (((borders[0] == 0 ? 1 : 0) & borders[1] & borders[2] & (borders[3] == 0 ? 1 : 0)) != 0) {
      index = 13;
    } else if (((borders[0] == 0 ? 1 : 0) & borders[1] & (borders[2] == 0 ? 1 : 0) & borders[3]) != 0) {
      index = 37;
    } else if (((borders[0] == 0 ? 1 : 0) & borders[1] & borders[2] & borders[3]) != 0) {
      index = 25;
    } else if ((borders[0] & (borders[1] == 0 ? 1 : 0) & borders[2] & borders[3]) != 0) {
      index = 27;
    } else if ((borders[0] & borders[1] & (borders[2] == 0 ? 1 : 0) & borders[3]) != 0) {
      index = 38;
    } else if ((borders[0] & borders[1] & borders[2] & (borders[3] == 0 ? 1 : 0)) != 0) {
      index = 14;
    } else if ((borders[0] & borders[1] & borders[2] & borders[3]) != 0) {
      index = 26;
    }
    if (index == 0) {
      return cp.tileIcons[index];
    }
    if (!Config.isConnectedTexturesFancy()) {
      return cp.tileIcons[index];
    }
    boolean[] edges = borders;
    switch (side)
    {
    case 0: 
      edges[0] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().c(), side, icon, metadata) ? 1 : false);
      edges[1] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().c(), side, icon, metadata) ? 1 : false);
      edges[2] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().d(), side, icon, metadata) ? 1 : false);
      edges[3] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().d(), side, icon, metadata) ? 1 : false);
      break;
    case 1: 
      edges[0] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().d(), side, icon, metadata) ? 1 : false);
      edges[1] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().d(), side, icon, metadata) ? 1 : false);
      edges[2] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().c(), side, icon, metadata) ? 1 : false);
      edges[3] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().c(), side, icon, metadata) ? 1 : false);
      break;
    case 2: 
      edges[0] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().b(), side, icon, metadata) ? 1 : false);
      edges[1] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().b(), side, icon, metadata) ? 1 : false);
      edges[2] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().a(), side, icon, metadata) ? 1 : false);
      edges[3] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().a(), side, icon, metadata) ? 1 : false);
      break;
    case 3: 
      edges[0] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().b(), side, icon, metadata) ? 1 : false);
      edges[1] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().b(), side, icon, metadata) ? 1 : false);
      edges[2] = (!isNeighbour(cp, blockAccess, blockState, blockPos.f().a(), side, icon, metadata) ? 1 : false);
      edges[3] = (!isNeighbour(cp, blockAccess, blockState, blockPos.e().a(), side, icon, metadata) ? 1 : false);
      break;
    case 4: 
      edges[0] = (!isNeighbour(cp, blockAccess, blockState, blockPos.b().d(), side, icon, metadata) ? 1 : false);
      edges[1] = (!isNeighbour(cp, blockAccess, blockState, blockPos.b().c(), side, icon, metadata) ? 1 : false);
      edges[2] = (!isNeighbour(cp, blockAccess, blockState, blockPos.a().d(), side, icon, metadata) ? 1 : false);
      edges[3] = (!isNeighbour(cp, blockAccess, blockState, blockPos.a().c(), side, icon, metadata) ? 1 : false);
      break;
    case 5: 
      edges[0] = (!isNeighbour(cp, blockAccess, blockState, blockPos.b().c(), side, icon, metadata) ? 1 : false);
      edges[1] = (!isNeighbour(cp, blockAccess, blockState, blockPos.b().d(), side, icon, metadata) ? 1 : false);
      edges[2] = (!isNeighbour(cp, blockAccess, blockState, blockPos.a().c(), side, icon, metadata) ? 1 : false);
      edges[3] = (!isNeighbour(cp, blockAccess, blockState, blockPos.a().d(), side, icon, metadata) ? 1 : false);
    }
    if ((index == 13) && (edges[0] != 0)) {
      index = 4;
    } else if ((index == 15) && (edges[1] != 0)) {
      index = 5;
    } else if ((index == 37) && (edges[2] != 0)) {
      index = 16;
    } else if ((index == 39) && (edges[3] != 0)) {
      index = 17;
    } else if ((index == 14) && (edges[0] != 0) && (edges[1] != 0)) {
      index = 7;
    } else if ((index == 25) && (edges[0] != 0) && (edges[2] != 0)) {
      index = 6;
    } else if ((index == 27) && (edges[3] != 0) && (edges[1] != 0)) {
      index = 19;
    } else if ((index == 38) && (edges[3] != 0) && (edges[2] != 0)) {
      index = 18;
    } else if ((index == 14) && (edges[0] == 0) && (edges[1] != 0)) {
      index = 31;
    } else if ((index == 25) && (edges[0] != 0) && (edges[2] == 0)) {
      index = 30;
    } else if ((index == 27) && (edges[3] == 0) && (edges[1] != 0)) {
      index = 41;
    } else if ((index == 38) && (edges[3] != 0) && (edges[2] == 0)) {
      index = 40;
    } else if ((index == 14) && (edges[0] != 0) && (edges[1] == 0)) {
      index = 29;
    } else if ((index == 25) && (edges[0] == 0) && (edges[2] != 0)) {
      index = 28;
    } else if ((index == 27) && (edges[3] != 0) && (edges[1] == 0)) {
      index = 43;
    } else if ((index == 38) && (edges[3] == 0) && (edges[2] != 0)) {
      index = 42;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] != 0) && (edges[2] != 0) && (edges[3] != 0)) {
      index = 46;
    } else if ((index == 26) && (edges[0] == 0) && (edges[1] != 0) && (edges[2] != 0) && (edges[3] != 0)) {
      index = 9;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] == 0) && (edges[2] != 0) && (edges[3] != 0)) {
      index = 21;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] != 0) && (edges[2] == 0) && (edges[3] != 0)) {
      index = 8;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] != 0) && (edges[2] != 0) && (edges[3] == 0)) {
      index = 20;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] != 0) && (edges[2] == 0) && (edges[3] == 0)) {
      index = 11;
    } else if ((index == 26) && (edges[0] == 0) && (edges[1] == 0) && (edges[2] != 0) && (edges[3] != 0)) {
      index = 22;
    } else if ((index == 26) && (edges[0] == 0) && (edges[1] != 0) && (edges[2] == 0) && (edges[3] != 0)) {
      index = 23;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] == 0) && (edges[2] != 0) && (edges[3] == 0)) {
      index = 10;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] == 0) && (edges[2] == 0) && (edges[3] != 0)) {
      index = 34;
    } else if ((index == 26) && (edges[0] == 0) && (edges[1] != 0) && (edges[2] != 0) && (edges[3] == 0)) {
      index = 35;
    } else if ((index == 26) && (edges[0] != 0) && (edges[1] == 0) && (edges[2] == 0) && (edges[3] == 0)) {
      index = 32;
    } else if ((index == 26) && (edges[0] == 0) && (edges[1] != 0) && (edges[2] == 0) && (edges[3] == 0)) {
      index = 33;
    } else if ((index == 26) && (edges[0] == 0) && (edges[1] == 0) && (edges[2] != 0) && (edges[3] == 0)) {
      index = 44;
    } else if ((index == 26) && (edges[0] == 0) && (edges[1] == 0) && (edges[2] == 0) && (edges[3] != 0)) {
      index = 45;
    }
    return cp.tileIcons[index];
  }
  
  private static boolean isNeighbour(ConnectedProperties cp, ahx iblockaccess, arc blockState, cj blockPos, int side, bvh icon, int metadata)
  {
    arc neighbourState = iblockaccess.o(blockPos);
    if (blockState == neighbourState) {
      return true;
    }
    if (cp.connect == 2)
    {
      if (neighbourState == null) {
        return false;
      }
      if (neighbourState == AIR_DEFAULT_STATE) {
        return false;
      }
      bvh neighbourIcon = getNeighbourIcon(iblockaccess, blockState, blockPos, neighbourState, side);
      return neighbourIcon == icon;
    }
    if (cp.connect == 3)
    {
      if (neighbourState == null) {
        return false;
      }
      if (neighbourState == AIR_DEFAULT_STATE) {
        return false;
      }
      return neighbourState.a() == blockState.a();
    }
    if ((neighbourState instanceof ara))
    {
      ara neighbourStateBase = (ara)neighbourState;
      ajt neighbourBlock = neighbourStateBase.t();
      int neighbourMetadata = neighbourStateBase.getMetadata();
      return (neighbourBlock == blockState.t()) && (neighbourMetadata == metadata);
    }
    return false;
  }
  
  private static bvh getNeighbourIcon(ahx iblockaccess, arc blockState, cj blockPos, arc neighbourState, int side)
  {
    neighbourState = neighbourState.t().b(neighbourState, iblockaccess, blockPos);
    
    bxo model = bcf.z().ab().a().b(neighbourState);
    if (model == null) {
      return null;
    }
    cq facing = getFacing(side);
    
    List quads = model.a(neighbourState, facing, 0L);
    if (quads.size() > 0)
    {
      bof quad = (bof)quads.get(0);
      
      return quad.a();
    }
    List quadsGeneral = model.a(neighbourState, null, 0L);
    for (int i = 0; i < quadsGeneral.size(); i++)
    {
      bof quad = (bof)quadsGeneral.get(i);
      if (quad.e() == facing) {
        return quad.a();
      }
    }
    return null;
  }
  
  private static bvh getConnectedTextureHorizontal(ConnectedProperties cp, ahx blockAccess, arc blockState, cj blockPos, int vertAxis, int side, bvh icon, int metadata)
  {
    boolean left = false;
    boolean right = false;
    switch (vertAxis)
    {
    case 0: 
      switch (side)
      {
      case 0: 
      case 1: 
        return null;
      case 2: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
        break;
      case 3: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
        break;
      case 4: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
        break;
      case 5: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
      }
      break;
    case 1: 
      switch (side)
      {
      case 2: 
      case 3: 
        return null;
      case 0: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
        break;
      case 1: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
        break;
      case 4: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
        break;
      case 5: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
      }
      break;
    case 2: 
      switch (side)
      {
      case 4: 
      case 5: 
        return null;
      case 2: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
        break;
      case 3: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
        break;
      case 0: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
        break;
      case 1: 
        left = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
        right = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
      }
      break;
    }
    int index = 3;
    if (left)
    {
      if (right) {
        index = 1;
      } else {
        index = 2;
      }
    }
    else if (right) {
      index = 0;
    } else {
      index = 3;
    }
    return cp.tileIcons[index];
  }
  
  private static bvh getConnectedTextureVertical(ConnectedProperties cp, ahx blockAccess, arc blockState, cj blockPos, int vertAxis, int side, bvh icon, int metadata)
  {
    boolean bottom = false;
    boolean top = false;
    switch (vertAxis)
    {
    case 0: 
      if ((side == 1) || (side == 0)) {
        return null;
      }
      bottom = isNeighbour(cp, blockAccess, blockState, blockPos.b(), side, icon, metadata);
      top = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
      break;
    case 1: 
      if ((side == 3) || (side == 2)) {
        return null;
      }
      bottom = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
      top = isNeighbour(cp, blockAccess, blockState, blockPos.c(), side, icon, metadata);
      break;
    case 2: 
      if ((side == 5) || (side == 4)) {
        return null;
      }
      bottom = isNeighbour(cp, blockAccess, blockState, blockPos.e(), side, icon, metadata);
      top = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
    }
    int index = 3;
    if (bottom)
    {
      if (top) {
        index = 1;
      } else {
        index = 2;
      }
    }
    else if (top) {
      index = 0;
    } else {
      index = 3;
    }
    return cp.tileIcons[index];
  }
  
  private static bvh getConnectedTextureHorizontalVertical(ConnectedProperties cp, ahx blockAccess, arc blockState, cj blockPos, int vertAxis, int side, bvh icon, int metadata)
  {
    bvh[] tileIcons = cp.tileIcons;
    
    bvh iconH = getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    if ((iconH != null) && (iconH != icon) && (iconH != tileIcons[3])) {
      return iconH;
    }
    bvh iconV = getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    if (iconV == tileIcons[0]) {
      return tileIcons[4];
    }
    if (iconV == tileIcons[1]) {
      return tileIcons[5];
    }
    if (iconV == tileIcons[2]) {
      return tileIcons[6];
    }
    return iconV;
  }
  
  private static bvh getConnectedTextureVerticalHorizontal(ConnectedProperties cp, ahx blockAccess, arc blockState, cj blockPos, int vertAxis, int side, bvh icon, int metadata)
  {
    bvh[] tileIcons = cp.tileIcons;
    
    bvh iconV = getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    if ((iconV != null) && (iconV != icon) && (iconV != tileIcons[3])) {
      return iconV;
    }
    bvh iconH = getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
    if (iconH == tileIcons[0]) {
      return tileIcons[4];
    }
    if (iconH == tileIcons[1]) {
      return tileIcons[5];
    }
    if (iconH == tileIcons[2]) {
      return tileIcons[6];
    }
    return iconH;
  }
  
  private static bvh getConnectedTextureTop(ConnectedProperties cp, ahx blockAccess, arc blockState, cj blockPos, int vertAxis, int side, bvh icon, int metadata)
  {
    boolean top = false;
    switch (vertAxis)
    {
    case 0: 
      if ((side == 1) || (side == 0)) {
        return null;
      }
      top = isNeighbour(cp, blockAccess, blockState, blockPos.a(), side, icon, metadata);
      break;
    case 1: 
      if ((side == 3) || (side == 2)) {
        return null;
      }
      top = isNeighbour(cp, blockAccess, blockState, blockPos.d(), side, icon, metadata);
      break;
    case 2: 
      if ((side == 5) || (side == 4)) {
        return null;
      }
      top = isNeighbour(cp, blockAccess, blockState, blockPos.f(), side, icon, metadata);
    }
    if (top) {
      return cp.tileIcons[0];
    }
    return null;
  }
  
  public static void updateIcons(bvg textureMap)
  {
    blockProperties = (ConnectedProperties[][])null;
    tileProperties = (ConnectedProperties[][])null;
    spriteQuadMaps = null;
    if (!Config.isConnectedTextures()) {
      return;
    }
    bwi[] rps = Config.getResourcePacks();
    for (int i = rps.length - 1; i >= 0; i--)
    {
      bwi rp = rps[i];
      updateIcons(textureMap, rp);
    }
    updateIcons(textureMap, Config.getDefaultResourcePack());
    
    kk locEmpty = new kk("mcpatcher/ctm/default/empty");
    emptySprite = textureMap.a(locEmpty);
    
    spriteQuadMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
    if (blockProperties.length <= 0) {
      blockProperties = (ConnectedProperties[][])null;
    }
    if (tileProperties.length <= 0) {
      tileProperties = (ConnectedProperties[][])null;
    }
  }
  
  private static void updateIconEmpty(bvg textureMap) {}
  
  public static void updateIcons(bvg textureMap, bwi rp)
  {
    String[] names = ResUtils.collectFiles(rp, "mcpatcher/ctm/", ".properties", getDefaultCtmPaths());
    
    Arrays.sort(names);
    
    List tileList = makePropertyList(tileProperties);
    List blockList = makePropertyList(blockProperties);
    for (int i = 0; i < names.length; i++)
    {
      String name = names[i];
      Config.dbg("ConnectedTextures: " + name);
      try
      {
        kk locFile = new kk(name);
        InputStream in = rp.a(locFile);
        if (in == null)
        {
          Config.warn("ConnectedTextures file not found: " + name);
        }
        else
        {
          Properties props = new Properties();
          props.load(in);
          ConnectedProperties cp = new ConnectedProperties(props, name);
          if (cp.isValid(name))
          {
            cp.updateIcons(textureMap);
            
            addToTileList(cp, tileList);
            addToBlockList(cp, blockList);
          }
        }
      }
      catch (FileNotFoundException e)
      {
        Config.warn("ConnectedTextures file not found: " + name);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    blockProperties = propertyListToArray(blockList);
    tileProperties = propertyListToArray(tileList);
    
    multipass = detectMultipass();
    Config.dbg("Multipass connected textures: " + multipass);
  }
  
  private static List makePropertyList(ConnectedProperties[][] propsArr)
  {
    List list = new ArrayList();
    if (propsArr != null) {
      for (int i = 0; i < propsArr.length; i++)
      {
        ConnectedProperties[] props = propsArr[i];
        List propList = null;
        if (props != null) {
          propList = new ArrayList(Arrays.asList(props));
        }
        list.add(propList);
      }
    }
    return list;
  }
  
  private static boolean detectMultipass()
  {
    List propList = new ArrayList();
    for (int i = 0; i < tileProperties.length; i++)
    {
      ConnectedProperties[] cps = tileProperties[i];
      if (cps != null) {
        propList.addAll(Arrays.asList(cps));
      }
    }
    for (int i = 0; i < blockProperties.length; i++)
    {
      ConnectedProperties[] cps = blockProperties[i];
      if (cps != null) {
        propList.addAll(Arrays.asList(cps));
      }
    }
    ConnectedProperties[] props = (ConnectedProperties[])propList.toArray(new ConnectedProperties[propList.size()]);
    
    Set matchIconSet = new HashSet();
    Set tileIconSet = new HashSet();
    for (int i = 0; i < props.length; i++)
    {
      ConnectedProperties cp = props[i];
      if (cp.matchTileIcons != null) {
        matchIconSet.addAll(Arrays.asList(cp.matchTileIcons));
      }
      if (cp.tileIcons != null) {
        tileIconSet.addAll(Arrays.asList(cp.tileIcons));
      }
    }
    matchIconSet.retainAll(tileIconSet);
    return !matchIconSet.isEmpty();
  }
  
  private static ConnectedProperties[][] propertyListToArray(List list)
  {
    ConnectedProperties[][] propArr = new ConnectedProperties[list.size()][];
    for (int i = 0; i < list.size(); i++)
    {
      List subList = (List)list.get(i);
      if (subList != null)
      {
        ConnectedProperties[] subArr = (ConnectedProperties[])subList.toArray(new ConnectedProperties[subList.size()]);
        
        propArr[i] = subArr;
      }
    }
    return propArr;
  }
  
  private static void addToTileList(ConnectedProperties cp, List tileList)
  {
    if (cp.matchTileIcons == null) {
      return;
    }
    for (int i = 0; i < cp.matchTileIcons.length; i++)
    {
      bvh icon = cp.matchTileIcons[i];
      if (!(icon instanceof bvh))
      {
        Config.warn("TextureAtlasSprite is not TextureAtlasSprite: " + icon + ", name: " + icon.i());
      }
      else
      {
        bvh ts = icon;
        int tileId = ts.getIndexInMap();
        if (tileId < 0) {
          Config.warn("Invalid tile ID: " + tileId + ", icon: " + ts.i());
        } else {
          addToList(cp, tileList, tileId);
        }
      }
    }
  }
  
  private static void addToBlockList(ConnectedProperties cp, List blockList)
  {
    if (cp.matchBlocks == null) {
      return;
    }
    for (int i = 0; i < cp.matchBlocks.length; i++)
    {
      int blockId = cp.matchBlocks[i].getBlockId();
      if (blockId < 0) {
        Config.warn("Invalid block ID: " + blockId);
      } else {
        addToList(cp, blockList, blockId);
      }
    }
  }
  
  private static void addToList(ConnectedProperties cp, List list, int id)
  {
    while (id >= list.size()) {
      list.add(null);
    }
    List subList = (List)list.get(id);
    if (subList == null)
    {
      subList = new ArrayList();
      list.set(id, subList);
    }
    subList.add(cp);
  }
  
  private static String[] getDefaultCtmPaths()
  {
    List list = new ArrayList();
    
    String defPath = "mcpatcher/ctm/default/";
    if (Config.isFromDefaultResourcePack(new kk("textures/blocks/glass.png")))
    {
      list.add(defPath + "glass.properties");
      list.add(defPath + "glasspane.properties");
    }
    if (Config.isFromDefaultResourcePack(new kk("textures/blocks/bookshelf.png"))) {
      list.add(defPath + "bookshelf.properties");
    }
    if (Config.isFromDefaultResourcePack(new kk("textures/blocks/sandstone_normal.png"))) {
      list.add(defPath + "sandstone.properties");
    }
    String[] colors = { "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black" };
    for (int i = 0; i < colors.length; i++)
    {
      String color = colors[i];
      if (Config.isFromDefaultResourcePack(new kk("textures/blocks/glass_" + color + ".png")))
      {
        list.add(defPath + i + "_glass_" + color + "/glass_" + color + ".properties");
        list.add(defPath + i + "_glass_" + color + "/glass_pane_" + color + ".properties");
      }
    }
    String[] paths = (String[])list.toArray(new String[list.size()]);
    
    return paths;
  }
  
  public static int getPaneTextureIndex(boolean linkP, boolean linkN, boolean linkYp, boolean linkYn)
  {
    if ((linkN) && (linkP))
    {
      if (linkYp)
      {
        if (linkYn) {
          return 34;
        }
        return 50;
      }
      if (linkYn) {
        return 18;
      }
      return 2;
    }
    if ((linkN) && (!linkP))
    {
      if (linkYp)
      {
        if (linkYn) {
          return 35;
        }
        return 51;
      }
      if (linkYn) {
        return 19;
      }
      return 3;
    }
    if ((!linkN) && (linkP))
    {
      if (linkYp)
      {
        if (linkYn) {
          return 33;
        }
        return 49;
      }
      if (linkYn) {
        return 17;
      }
      return 1;
    }
    if (linkYp)
    {
      if (linkYn) {
        return 32;
      }
      return 48;
    }
    if (linkYn) {
      return 16;
    }
    return 0;
  }
  
  public static int getReversePaneTextureIndex(int texNum)
  {
    int col = texNum % 16;
    if (col == 1) {
      return texNum + 2;
    }
    if (col == 3) {
      return texNum - 2;
    }
    return texNum;
  }
  
  public static bvh getCtmTexture(ConnectedProperties cp, int ctmIndex, bvh icon)
  {
    if (cp.method != 1) {
      return icon;
    }
    if ((ctmIndex < 0) || (ctmIndex >= ctmIndexes.length)) {
      return icon;
    }
    int index = ctmIndexes[ctmIndex];
    
    bvh[] ctmIcons = cp.tileIcons;
    if ((index < 0) || (index >= ctmIcons.length)) {
      return icon;
    }
    return ctmIcons[index];
  }
}
