import com.google.common.util.concurrent.ThreadFactoryBuilder;
import de.labystudio.labymod.LabyMod;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bgu
  implements bdl.a
{
  private static final Logger a = ;
  private static final ThreadPoolExecutor b = new ScheduledThreadPoolExecutor(5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build());
  private static final kk c = new kk("textures/misc/unknown_server.png");
  private static final kk d = new kk("textures/gui/server_selection.png");
  private final bgr e;
  private final bcf f;
  private final bkx g;
  private final kk h;
  private String i;
  private bux j;
  private long k;
  
  protected bgu(bgr p_i45048_1_, bkx serverIn)
  {
    this.e = p_i45048_1_;
    this.g = serverIn;
    this.f = bcf.z();
    this.h = new kk("servers/" + serverIn.b + "/icon");
    this.j = ((bux)this.f.N().b(this.h));
  }
  
  public void a(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected)
  {
    if (!this.g.h)
    {
      this.g.h = true;
      this.g.e = -2L;
      this.g.d = "";
      this.g.c = "";
      b.submit(new Runnable()
      {
        public void run()
        {
          try
          {
            bgu.b(bgu.this).g().a(bgu.a(bgu.this));
          }
          catch (UnknownHostException var2)
          {
            bgu.a(bgu.this).e = -1L;
            bgu.a(bgu.this).d = (a.e + "Can't resolve hostname");
          }
          catch (Exception var3)
          {
            bgu.a(bgu.this).e = -1L;
            bgu.a(bgu.this).d = (a.e + "Can't connect to server.");
          }
        }
      });
    }
    boolean flag = this.g.f > LabyMod.getClientVersion();
    boolean flag1 = this.g.f < LabyMod.getClientVersion();
    boolean flag2 = (flag) || (flag1);
    this.f.k.a(this.g.a, x + 32 + 3, y + 1, 16777215);
    List<String> list = this.f.k.c(this.g.d, listWidth - 32 - 2);
    for (int i = 0; i < Math.min(list.size(), 2); i++) {
      this.f.k.a((String)list.get(i), x + 32 + 3, y + 12 + this.f.k.a * i, 8421504);
    }
    String s2 = flag2 ? a.e + this.g.g : this.g.c;
    int j = this.f.k.a(s2);
    this.f.k.a(s2, x + listWidth - j - 15 - 2, y + 1, 8421504);
    int k = 0;
    String s = null;
    int l;
    String s1;
    if (flag2)
    {
      int l = 5;
      String s1 = flag ? "Client out of date!" : "Server out of date!";
      s = this.g.i;
    }
    else if ((this.g.h) && (this.g.e != -2L))
    {
      int l;
      int l;
      if (this.g.e < 0L)
      {
        l = 5;
      }
      else
      {
        int l;
        if (this.g.e < 150L)
        {
          l = 0;
        }
        else
        {
          int l;
          if (this.g.e < 300L)
          {
            l = 1;
          }
          else
          {
            int l;
            if (this.g.e < 600L)
            {
              l = 2;
            }
            else
            {
              int l;
              if (this.g.e < 1000L) {
                l = 3;
              } else {
                l = 4;
              }
            }
          }
        }
      }
      String s1;
      if (this.g.e < 0L)
      {
        s1 = "(no connection)";
      }
      else
      {
        String s1 = this.g.e + "ms";
        s = this.g.i;
      }
    }
    else
    {
      k = 1;
      l = (int)(bcf.I() / 100L + slotIndex * 2 & 0x7);
      if (l > 4) {
        l = 8 - l;
      }
      s1 = "Pinging...";
    }
    bni.c(1.0F, 1.0F, 1.0F, 1.0F);
    this.f.N().a(bcv.d);
    bcv.a(x + listWidth - 15, y, k * 10, 176 + l * 8, 10, 8, 256.0F, 256.0F);
    if ((this.g.c() != null) && (!this.g.c().equals(this.i)))
    {
      this.i = this.g.c();
      c();
      this.e.h().b();
    }
    if (this.j != null) {
      a(x, y, this.h);
    } else {
      a(x, y, c);
    }
    int i1 = mouseX - x;
    int j1 = mouseY - y;
    if ((i1 >= listWidth - 15) && (i1 <= listWidth - 5) && (j1 >= 0) && (j1 <= 8)) {
      this.e.a(s1);
    } else if ((i1 >= listWidth - j - 15 - 2) && (i1 <= listWidth - 15 - 2) && (j1 >= 0) && (j1 <= 8)) {
      this.e.a(s);
    }
    if ((this.f.u.z) || (isSelected))
    {
      this.f.N().a(d);
      bcv.a(x, y, x + 32, y + 32, -1601138544);
      bni.c(1.0F, 1.0F, 1.0F, 1.0F);
      int k1 = mouseX - x;
      int l1 = mouseY - y;
      if (b()) {
        if ((k1 < 32) && (k1 > 16)) {
          bcv.a(x, y, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
        } else {
          bcv.a(x, y, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
        }
      }
      if (this.e.a(this, slotIndex)) {
        if ((k1 < 16) && (l1 < 16)) {
          bcv.a(x, y, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
        } else {
          bcv.a(x, y, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
        }
      }
      if (this.e.b(this, slotIndex)) {
        if ((k1 < 16) && (l1 > 16)) {
          bcv.a(x, y, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
        } else {
          bcv.a(x, y, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
        }
      }
    }
  }
  
  protected void a(int p_178012_1_, int p_178012_2_, kk p_178012_3_)
  {
    this.f.N().a(p_178012_3_);
    bni.m();
    bcv.a(p_178012_1_, p_178012_2_, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
    bni.l();
  }
  
  private boolean b()
  {
    return true;
  }
  
  /* Error */
  private void c()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 40	bgu:g	Lbkx;
    //   4: invokevirtual 219	bkx:c	()Ljava/lang/String;
    //   7: ifnonnull +25 -> 32
    //   10: aload_0
    //   11: getfield 48	bgu:f	Lbcf;
    //   14: invokevirtual 78	bcf:N	()Lbvi;
    //   17: aload_0
    //   18: getfield 74	bgu:h	Lkk;
    //   21: invokevirtual 300	bvi:c	(Lkk;)V
    //   24: aload_0
    //   25: aconst_null
    //   26: putfield 87	bgu:j	Lbux;
    //   29: goto +274 -> 303
    //   32: aload_0
    //   33: getfield 40	bgu:g	Lbkx;
    //   36: invokevirtual 219	bkx:c	()Ljava/lang/String;
    //   39: getstatic 306	com/google/common/base/Charsets:UTF_8	Ljava/nio/charset/Charset;
    //   42: invokestatic 312	io/netty/buffer/Unpooled:copiedBuffer	(Ljava/lang/CharSequence;Ljava/nio/charset/Charset;)Lio/netty/buffer/ByteBuf;
    //   45: astore_1
    //   46: aload_1
    //   47: invokestatic 318	io/netty/handler/codec/base64/Base64:decode	(Lio/netty/buffer/ByteBuf;)Lio/netty/buffer/ByteBuf;
    //   50: astore_2
    //   51: new 320	io/netty/buffer/ByteBufInputStream
    //   54: dup
    //   55: aload_2
    //   56: invokespecial 323	io/netty/buffer/ByteBufInputStream:<init>	(Lio/netty/buffer/ByteBuf;)V
    //   59: invokestatic 328	bvk:a	(Ljava/io/InputStream;)Ljava/awt/image/BufferedImage;
    //   62: astore_3
    //   63: aload_3
    //   64: invokevirtual 333	java/awt/image/BufferedImage:getWidth	()I
    //   67: bipush 64
    //   69: if_icmpne +7 -> 76
    //   72: iconst_1
    //   73: goto +4 -> 77
    //   76: iconst_0
    //   77: ldc_w 337
    //   80: iconst_0
    //   81: anewarray 4	java/lang/Object
    //   84: invokestatic 343	org/apache/commons/lang3/Validate:validState	(ZLjava/lang/String;[Ljava/lang/Object;)V
    //   87: aload_3
    //   88: invokevirtual 346	java/awt/image/BufferedImage:getHeight	()I
    //   91: bipush 64
    //   93: if_icmpne +7 -> 100
    //   96: iconst_1
    //   97: goto +4 -> 101
    //   100: iconst_0
    //   101: ldc_w 348
    //   104: iconst_0
    //   105: anewarray 4	java/lang/Object
    //   108: invokestatic 343	org/apache/commons/lang3/Validate:validState	(ZLjava/lang/String;[Ljava/lang/Object;)V
    //   111: aload_1
    //   112: invokevirtual 351	io/netty/buffer/ByteBuf:release	()Z
    //   115: pop
    //   116: aload_2
    //   117: invokevirtual 351	io/netty/buffer/ByteBuf:release	()Z
    //   120: pop
    //   121: goto +103 -> 224
    //   124: astore 4
    //   126: getstatic 353	bgu:a	Lorg/apache/logging/log4j/Logger;
    //   129: new 52	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 53	java/lang/StringBuilder:<init>	()V
    //   136: ldc_w 355
    //   139: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_0
    //   143: getfield 40	bgu:g	Lbkx;
    //   146: getfield 130	bkx:a	Ljava/lang/String;
    //   149: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: ldc_w 357
    //   155: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload_0
    //   159: getfield 40	bgu:g	Lbkx;
    //   162: getfield 63	bkx:b	Ljava/lang/String;
    //   165: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: ldc_w 359
    //   171: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: aload 4
    //   179: invokeinterface 365 3 0
    //   184: aload_0
    //   185: getfield 40	bgu:g	Lbkx;
    //   188: aconst_null
    //   189: checkcast 156	java/lang/String
    //   192: invokevirtual 366	bkx:a	(Ljava/lang/String;)V
    //   195: aload_1
    //   196: invokevirtual 351	io/netty/buffer/ByteBuf:release	()Z
    //   199: pop
    //   200: aload_2
    //   201: invokevirtual 351	io/netty/buffer/ByteBuf:release	()Z
    //   204: pop
    //   205: goto +18 -> 223
    //   208: astore 5
    //   210: aload_1
    //   211: invokevirtual 351	io/netty/buffer/ByteBuf:release	()Z
    //   214: pop
    //   215: aload_2
    //   216: invokevirtual 351	io/netty/buffer/ByteBuf:release	()Z
    //   219: pop
    //   220: aload 5
    //   222: athrow
    //   223: return
    //   224: aload_0
    //   225: getfield 87	bgu:j	Lbux;
    //   228: ifnonnull +41 -> 269
    //   231: aload_0
    //   232: new 85	bux
    //   235: dup
    //   236: aload_3
    //   237: invokevirtual 333	java/awt/image/BufferedImage:getWidth	()I
    //   240: aload_3
    //   241: invokevirtual 346	java/awt/image/BufferedImage:getHeight	()I
    //   244: invokespecial 369	bux:<init>	(II)V
    //   247: putfield 87	bgu:j	Lbux;
    //   250: aload_0
    //   251: getfield 48	bgu:f	Lbcf;
    //   254: invokevirtual 78	bcf:N	()Lbvi;
    //   257: aload_0
    //   258: getfield 74	bgu:h	Lkk;
    //   261: aload_0
    //   262: getfield 87	bgu:j	Lbux;
    //   265: invokevirtual 372	bvi:a	(Lkk;Lbvj;)Z
    //   268: pop
    //   269: aload_3
    //   270: iconst_0
    //   271: iconst_0
    //   272: aload_3
    //   273: invokevirtual 333	java/awt/image/BufferedImage:getWidth	()I
    //   276: aload_3
    //   277: invokevirtual 346	java/awt/image/BufferedImage:getHeight	()I
    //   280: aload_0
    //   281: getfield 87	bgu:j	Lbux;
    //   284: invokevirtual 375	bux:e	()[I
    //   287: iconst_0
    //   288: aload_3
    //   289: invokevirtual 333	java/awt/image/BufferedImage:getWidth	()I
    //   292: invokevirtual 379	java/awt/image/BufferedImage:getRGB	(IIII[III)[I
    //   295: pop
    //   296: aload_0
    //   297: getfield 87	bgu:j	Lbux;
    //   300: invokevirtual 381	bux:d	()V
    //   303: return
    // Line number table:
    //   Java source line #248	-> byte code offset #0
    //   Java source line #250	-> byte code offset #10
    //   Java source line #251	-> byte code offset #24
    //   Java source line #255	-> byte code offset #32
    //   Java source line #256	-> byte code offset #46
    //   Java source line #262	-> byte code offset #51
    //   Java source line #263	-> byte code offset #63
    //   Java source line #264	-> byte code offset #87
    //   Java source line #274	-> byte code offset #111
    //   Java source line #275	-> byte code offset #116
    //   Java source line #267	-> byte code offset #124
    //   Java source line #269	-> byte code offset #126
    //   Java source line #270	-> byte code offset #184
    //   Java source line #274	-> byte code offset #195
    //   Java source line #275	-> byte code offset #200
    //   Java source line #276	-> byte code offset #205
    //   Java source line #274	-> byte code offset #208
    //   Java source line #275	-> byte code offset #215
    //   Java source line #278	-> byte code offset #223
    //   Java source line #281	-> byte code offset #224
    //   Java source line #283	-> byte code offset #231
    //   Java source line #284	-> byte code offset #250
    //   Java source line #287	-> byte code offset #269
    //   Java source line #288	-> byte code offset #296
    //   Java source line #290	-> byte code offset #303
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	304	0	this	bgu
    //   45	166	1	bytebuf	io.netty.buffer.ByteBuf
    //   50	166	2	bytebuf1	io.netty.buffer.ByteBuf
    //   62	26	3	bufferedimage	java.awt.image.BufferedImage
    //   224	65	3	bufferedimage	java.awt.image.BufferedImage
    //   124	54	4	throwable	Throwable
    //   208	13	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   51	111	124	java/lang/Throwable
    //   51	111	208	finally
    //   124	195	208	finally
    //   208	210	208	finally
  }
  
  public boolean a(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_)
  {
    if (p_148278_5_ <= 32)
    {
      if ((p_148278_5_ < 32) && (p_148278_5_ > 16) && (b()))
      {
        this.e.b(slotIndex);
        this.e.f();
        return true;
      }
      if ((p_148278_5_ < 16) && (p_148278_6_ < 16) && (this.e.a(this, slotIndex)))
      {
        this.e.a(this, slotIndex, bfb.r());
        return true;
      }
      if ((p_148278_5_ < 16) && (p_148278_6_ > 16) && (this.e.b(this, slotIndex)))
      {
        this.e.b(this, slotIndex, bfb.r());
        return true;
      }
    }
    this.e.b(slotIndex);
    if (bcf.I() - this.k < 250L) {
      this.e.f();
    }
    this.k = bcf.I();
    return false;
  }
  
  public void a(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
  
  public void b(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}
  
  public bkx a()
  {
    return this.g;
  }
}
