import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class bkm
{
  public float a;
  public float b;
  private int r;
  private int s;
  public float c;
  public float d;
  public float e;
  public float f;
  public float g;
  public float h;
  private boolean t;
  private int u;
  public boolean i;
  public boolean j;
  public boolean k;
  public List<bkk> l;
  public List<bkm> m;
  public final String n;
  private bjc v;
  public float o;
  public float p;
  public float q;
  public List spriteList = new ArrayList();
  public boolean mirrorV = false;
  float savedScale;
  
  public bkm(bjc model, String boxNameIn)
  {
    this.a = 64.0F;
    this.b = 32.0F;
    this.j = true;
    this.l = Lists.newArrayList();
    this.v = model;
    model.r.add(this);
    this.n = boxNameIn;
    b(model.s, model.t);
  }
  
  public bkm(bjc model)
  {
    this(model, (String)null);
  }
  
  public bkm(bjc model, int texOffX, int texOffY)
  {
    this(model);
    a(texOffX, texOffY);
  }
  
  public void a(bkm renderer)
  {
    if (this.m == null) {
      this.m = Lists.newArrayList();
    }
    this.m.add(renderer);
  }
  
  public bkm a(int x, int y)
  {
    this.r = x;
    this.s = y;
    return this;
  }
  
  public bkm a(String partName, float offX, float offY, float offZ, int width, int height, int depth)
  {
    partName = this.n + "." + partName;
    bkn textureoffset = this.v.a(partName);
    a(textureoffset.a, textureoffset.b);
    this.l.add(new bkk(this, this.r, this.s, offX, offY, offZ, width, height, depth, 0.0F).a(partName));
    return this;
  }
  
  public bkm a(float offX, float offY, float offZ, int width, int height, int depth)
  {
    this.l.add(new bkk(this, this.r, this.s, offX, offY, offZ, width, height, depth, 0.0F));
    return this;
  }
  
  public bkm a(float p_178769_1_, float p_178769_2_, float p_178769_3_, int p_178769_4_, int p_178769_5_, int p_178769_6_, boolean p_178769_7_)
  {
    this.l.add(new bkk(this, this.r, this.s, p_178769_1_, p_178769_2_, p_178769_3_, p_178769_4_, p_178769_5_, p_178769_6_, 0.0F, p_178769_7_));
    return this;
  }
  
  public void a(float p_78790_1_, float p_78790_2_, float p_78790_3_, int width, int height, int depth, float scaleFactor)
  {
    this.l.add(new bkk(this, this.r, this.s, p_78790_1_, p_78790_2_, p_78790_3_, width, height, depth, scaleFactor));
  }
  
  public void a(float rotationPointXIn, float rotationPointYIn, float rotationPointZIn)
  {
    this.c = rotationPointXIn;
    this.d = rotationPointYIn;
    this.e = rotationPointZIn;
  }
  
  public void a(float scale)
  {
    if (!this.k) {
      if (this.j)
      {
        if (!this.t) {
          d(scale);
        }
        bni.c(this.o, this.p, this.q);
        if ((this.f == 0.0F) && (this.g == 0.0F) && (this.h == 0.0F))
        {
          if ((this.c == 0.0F) && (this.d == 0.0F) && (this.e == 0.0F))
          {
            bni.s(this.u);
            if (this.m != null) {
              for (int k = 0; k < this.m.size(); k++) {
                ((bkm)this.m.get(k)).a(scale);
              }
            }
          }
          else
          {
            bni.c(this.c * scale, this.d * scale, this.e * scale);
            bni.s(this.u);
            if (this.m != null) {
              for (int j = 0; j < this.m.size(); j++) {
                ((bkm)this.m.get(j)).a(scale);
              }
            }
            bni.c(-this.c * scale, -this.d * scale, -this.e * scale);
          }
        }
        else
        {
          bni.G();
          bni.c(this.c * scale, this.d * scale, this.e * scale);
          if (this.h != 0.0F) {
            bni.b(this.h * 57.295776F, 0.0F, 0.0F, 1.0F);
          }
          if (this.g != 0.0F) {
            bni.b(this.g * 57.295776F, 0.0F, 1.0F, 0.0F);
          }
          if (this.f != 0.0F) {
            bni.b(this.f * 57.295776F, 1.0F, 0.0F, 0.0F);
          }
          bni.s(this.u);
          if (this.m != null) {
            for (int i = 0; i < this.m.size(); i++) {
              ((bkm)this.m.get(i)).a(scale);
            }
          }
          bni.H();
        }
        bni.c(-this.o, -this.p, -this.q);
      }
    }
  }
  
  public void b(float p_78791_1_)
  {
    if (!this.k) {
      if (this.j)
      {
        if (!this.t) {
          d(p_78791_1_);
        }
        bni.G();
        bni.c(this.c * p_78791_1_, this.d * p_78791_1_, this.e * p_78791_1_);
        if (this.g != 0.0F) {
          bni.b(this.g * 57.295776F, 0.0F, 1.0F, 0.0F);
        }
        if (this.f != 0.0F) {
          bni.b(this.f * 57.295776F, 1.0F, 0.0F, 0.0F);
        }
        if (this.h != 0.0F) {
          bni.b(this.h * 57.295776F, 0.0F, 0.0F, 1.0F);
        }
        bni.s(this.u);
        bni.H();
      }
    }
  }
  
  public void c(float scale)
  {
    if (!this.k) {
      if (this.j)
      {
        if (!this.t) {
          d(scale);
        }
        if ((this.f == 0.0F) && (this.g == 0.0F) && (this.h == 0.0F))
        {
          if ((this.c != 0.0F) || (this.d != 0.0F) || (this.e != 0.0F)) {
            bni.c(this.c * scale, this.d * scale, this.e * scale);
          }
        }
        else
        {
          bni.c(this.c * scale, this.d * scale, this.e * scale);
          if (this.h != 0.0F) {
            bni.b(this.h * 57.295776F, 0.0F, 0.0F, 1.0F);
          }
          if (this.g != 0.0F) {
            bni.b(this.g * 57.295776F, 0.0F, 1.0F, 0.0F);
          }
          if (this.f != 0.0F) {
            bni.b(this.f * 57.295776F, 1.0F, 0.0F, 0.0F);
          }
        }
      }
    }
  }
  
  private void d(float scale)
  {
    if (this.u == 0)
    {
      this.savedScale = scale;
      
      this.u = bce.a(1);
    }
    bni.f(this.u, 4864);
    bmz vertexbuffer = bnu.a().c();
    for (int i = 0; i < this.l.size(); i++) {
      ((bkk)this.l.get(i)).a(vertexbuffer, scale);
    }
    for (int i = 0; i < this.spriteList.size(); i++)
    {
      ModelSprite sprite = (ModelSprite)this.spriteList.get(i);
      sprite.render(bnu.a(), scale);
    }
    bni.K();
    this.t = true;
  }
  
  public bkm b(int textureWidthIn, int textureHeightIn)
  {
    this.a = textureWidthIn;
    this.b = textureHeightIn;
    return this;
  }
  
  public void addSprite(float posX, float posY, float posZ, int sizeX, int sizeY, int sizeZ, float sizeAdd)
  {
    this.spriteList.add(new ModelSprite(this, this.r, this.s, posX, posY, posZ, sizeX, sizeY, sizeZ, sizeAdd));
  }
  
  public boolean getCompiled()
  {
    return this.t;
  }
  
  public int getDisplayList()
  {
    return this.u;
  }
  
  public void resetDisplayList()
  {
    if (this.t)
    {
      this.t = false;
      d(this.savedScale);
    }
  }
}
