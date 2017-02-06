package shadersmod.client;

import bni;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class HFNoiseTexture
{
  public int texID;
  public int textureUnit;
  
  public HFNoiseTexture(int width, int height)
  {
    this.texID = GL11.glGenTextures();
    this.textureUnit = 15;
    
    byte[] image = genHFNoiseImage(width, height);
    ByteBuffer data = BufferUtils.createByteBuffer(image.length);
    data.put(image);
    data.flip();
    
    bni.i(this.texID);
    GL11.glTexImage2D(3553, 0, 6407, width, height, 0, 6407, 5121, data);
    GL11.glTexParameteri(3553, 10242, 10497);
    GL11.glTexParameteri(3553, 10243, 10497);
    GL11.glTexParameteri(3553, 10240, 9729);
    GL11.glTexParameteri(3553, 10241, 9729);
    
    bni.i(0);
  }
  
  public int getID()
  {
    return this.texID;
  }
  
  public void destroy()
  {
    bni.h(this.texID);
    this.texID = 0;
  }
  
  private int random(int seed)
  {
    seed ^= seed << 13;
    seed ^= seed >> 17;
    seed ^= seed << 5;
    return seed;
  }
  
  private byte random(int x, int y, int z)
  {
    int seed = (random(x) + random(y * 19)) * random(z * 23) - z;
    return (byte)(random(seed) % 128);
  }
  
  private byte[] genHFNoiseImage(int width, int height)
  {
    byte[] image = new byte[width * height * 3];
    int index = 0;
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        for (int z = 1; z < 4; z++) {
          image[(index++)] = random(x, y, z);
        }
      }
    }
    return image;
  }
}
