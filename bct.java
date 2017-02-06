import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import com.ibm.icu.text.Bidi;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.io.IOUtils;

public class bct
  implements bwh
{
  private static final kk[] c = new kk['Ā'];
  private float[] d = new float['Ā'];
  public int a = 9;
  public Random b = new Random();
  private byte[] e = new byte[65536];
  private int[] f = new int[32];
  private kk g;
  private final bvi h;
  private float i;
  private float j;
  private boolean k;
  private boolean l;
  private float m;
  private float n;
  private float o;
  private float p;
  private int q;
  private boolean r;
  private boolean s;
  private boolean t;
  private boolean u;
  private boolean v;
  public bch gameSettings;
  public kk locationFontTextureBase;
  public boolean enabled = true;
  public float scaleFactor = 1.0F;
  
  public bct(bch gameSettingsIn, kk location, bvi textureManagerIn, boolean unicode)
  {
    this.gameSettings = gameSettingsIn;
    this.locationFontTextureBase = location;
    
    this.g = location;
    this.h = textureManagerIn;
    this.k = unicode;
    
    this.g = FontUtils.getHdFontLocation(this.locationFontTextureBase);
    
    bindTexture(this.g);
    for (int i = 0; i < 32; i++)
    {
      int j = (i >> 3 & 0x1) * 85;
      int k = (i >> 2 & 0x1) * 170 + j;
      int l = (i >> 1 & 0x1) * 170 + j;
      int i1 = (i >> 0 & 0x1) * 170 + j;
      if (i == 6) {
        k += 85;
      }
      if (gameSettingsIn.e)
      {
        int j1 = (k * 30 + l * 59 + i1 * 11) / 100;
        int k1 = (k * 30 + l * 70) / 100;
        int l1 = (k * 30 + i1 * 70) / 100;
        k = j1;
        l = k1;
        i1 = l1;
      }
      if (i >= 16)
      {
        k /= 4;
        l /= 4;
        i1 /= 4;
      }
      this.f[i] = ((k & 0xFF) << 16 | (l & 0xFF) << 8 | i1 & 0xFF);
    }
    d();
  }
  
  public void a(bwg resourceManager)
  {
    this.g = FontUtils.getHdFontLocation(this.locationFontTextureBase);
    for (int i = 0; i < c.length; i++) {
      c[i] = null;
    }
    c();
    
    d();
  }
  
  private void c()
  {
    bwf iresource = null;
    BufferedImage bufferedimage;
    try
    {
      iresource = getResource(this.g);
      bufferedimage = bvk.a(iresource.b());
    }
    catch (IOException ioexception)
    {
      throw new RuntimeException(ioexception);
    }
    finally
    {
      IOUtils.closeQuietly(iresource);
    }
    int imgWidth = bufferedimage.getWidth();
    int imgHeight = bufferedimage.getHeight();
    
    int charW = imgWidth / 16;
    int charH = imgHeight / 16;
    
    float kx = imgWidth / 128.0F;
    this.scaleFactor = Config.limit(kx, 1.0F, 2.0F);
    
    int[] ai = new int[imgWidth * imgHeight];
    bufferedimage.getRGB(0, 0, imgWidth, imgHeight, ai, 0, imgWidth);
    for (int k = 0; k < 256; k++)
    {
      int cx = k % 16;
      int cy = k / 16;
      int px = 0;
      for (px = charW - 1; px >= 0; px--)
      {
        int x = cx * charW + px;
        boolean flag = true;
        for (int py = 0; (py < charH) && (flag); py++)
        {
          int ypos = (cy * charH + py) * imgWidth;
          int col = ai[(x + ypos)];
          int al = col >> 24 & 0xFF;
          if (al > 16) {
            flag = false;
          }
        }
        if (!flag) {
          break;
        }
      }
      if (k == 65) {
        k = k;
      }
      if (k == 32) {
        if (charW <= 8) {
          px = (int)(2.0F * kx);
        } else {
          px = (int)(1.5F * kx);
        }
      }
      this.d[k] = ((px + 1) / kx + 1.0F);
    }
    FontUtils.readCustomCharWidths(this.g, this.d);
  }
  
  private void d()
  {
    bwf iresource = null;
    try
    {
      iresource = getResource(new kk("font/glyph_sizes.bin"));
      iresource.b().read(this.e);
    }
    catch (IOException ioexception)
    {
      throw new RuntimeException(ioexception);
    }
    finally
    {
      IOUtils.closeQuietly(iresource);
    }
  }
  
  private float a(char ch, boolean italic)
  {
    if (ch == ' ') {
      return this.d[ch];
    }
    int i = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".indexOf(ch);
    return (i != -1) && (!this.k) ? a(i, italic) : b(ch, italic);
  }
  
  private float a(int ch, boolean italic)
  {
    int i = ch % 16 * 8;
    int j = ch / 16 * 8;
    int k = italic ? 1 : 0;
    
    bindTexture(this.g);
    float l = this.d[ch];
    
    float f = 7.99F;
    bni.r(5);
    bni.b(i / 128.0F, j / 128.0F);
    bni.e(this.i + k, this.j, 0.0F);
    bni.b(i / 128.0F, (j + 7.99F) / 128.0F);
    bni.e(this.i - k, this.j + 7.99F, 0.0F);
    bni.b((i + f - 1.0F) / 128.0F, j / 128.0F);
    bni.e(this.i + f - 1.0F + k, this.j, 0.0F);
    bni.b((i + f - 1.0F) / 128.0F, (j + 7.99F) / 128.0F);
    bni.e(this.i + f - 1.0F - k, this.j + 7.99F, 0.0F);
    bni.J();
    return l;
  }
  
  private kk a(int page)
  {
    if (c[page] == null)
    {
      c[page] = new kk(String.format("textures/font/unicode_page_%02x.png", new Object[] { Integer.valueOf(page) }));
      
      c[page] = FontUtils.getHdFontLocation(c[page]);
    }
    return c[page];
  }
  
  private void b(int page)
  {
    bindTexture(a(page));
  }
  
  private float b(char ch, boolean italic)
  {
    int i = this.e[ch] & 0xFF;
    if (i == 0) {
      return 0.0F;
    }
    int j = ch / 'Ā';
    b(j);
    int k = i >>> 4;
    int l = i & 0xF;
    float f = k;
    float f1 = l + 1;
    float f2 = ch % '\020' * 16 + f;
    float f3 = (ch & 0xFF) / '\020' * 16;
    float f4 = f1 - f - 0.02F;
    float f5 = italic ? 1.0F : 0.0F;
    bni.r(5);
    bni.b(f2 / 256.0F, f3 / 256.0F);
    bni.e(this.i + f5, this.j, 0.0F);
    bni.b(f2 / 256.0F, (f3 + 15.98F) / 256.0F);
    bni.e(this.i - f5, this.j + 7.99F, 0.0F);
    bni.b((f2 + f4) / 256.0F, f3 / 256.0F);
    bni.e(this.i + f4 / 2.0F + f5, this.j, 0.0F);
    bni.b((f2 + f4) / 256.0F, (f3 + 15.98F) / 256.0F);
    bni.e(this.i + f4 / 2.0F - f5, this.j + 7.99F, 0.0F);
    bni.J();
    return (f1 - f) / 2.0F + 1.0F;
  }
  
  public int a(String text, float x, float y, int color)
  {
    return a(text, x, y, color, true);
  }
  
  public int a(String text, int x, int y, int color)
  {
    return a(text, x, y, color, false);
  }
  
  public int a(String text, float x, float y, int color, boolean dropShadow)
  {
    enableAlpha();
    
    e();
    int i;
    if (dropShadow)
    {
      int i = b(text, x + 1.0F, y + 1.0F, color, true);
      i = Math.max(i, b(text, x, y, color, false));
    }
    else
    {
      i = b(text, x, y, color, false);
    }
    return i;
  }
  
  private String c(String text)
  {
    try
    {
      Bidi bidi = new Bidi(new ArabicShaping(8).shape(text), 127);
      bidi.setReorderingMode(0);
      return bidi.writeReordered(2);
    }
    catch (ArabicShapingException var3) {}
    return text;
  }
  
  private void e()
  {
    this.r = false;
    this.s = false;
    this.t = false;
    this.u = false;
    this.v = false;
  }
  
  private void a(String text, boolean shadow)
  {
    for (int i = 0; i < text.length(); i++)
    {
      char c0 = text.charAt(i);
      if ((c0 == '§') && (i + 1 < text.length()))
      {
        int i1 = "0123456789abcdefklmnor".indexOf(text.toLowerCase(Locale.ENGLISH).charAt(i + 1));
        if (i1 < 16)
        {
          this.r = false;
          this.s = false;
          this.v = false;
          this.u = false;
          this.t = false;
          if ((i1 < 0) || (i1 > 15)) {
            i1 = 15;
          }
          if (shadow) {
            i1 += 16;
          }
          int j1 = this.f[i1];
          if (Config.isCustomColors()) {
            j1 = CustomColors.getTextColor(i1, j1);
          }
          this.q = j1;
          
          setColor((j1 >> 16) / 255.0F, (j1 >> 8 & 0xFF) / 255.0F, (j1 & 0xFF) / 255.0F, this.p);
        }
        else if (i1 == 16)
        {
          this.r = true;
        }
        else if (i1 == 17)
        {
          this.s = true;
        }
        else if (i1 == 18)
        {
          this.v = true;
        }
        else if (i1 == 19)
        {
          this.u = true;
        }
        else if (i1 == 20)
        {
          this.t = true;
        }
        else if (i1 == 21)
        {
          this.r = false;
          this.s = false;
          this.v = false;
          this.u = false;
          this.t = false;
          
          setColor(this.m, this.n, this.o, this.p);
        }
        i++;
      }
      else
      {
        int j = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".indexOf(c0);
        if ((this.r) && (j != -1))
        {
          int k = a(c0);
          char c1;
          for (;;)
          {
            j = this.b.nextInt("ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".length());
            c1 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".charAt(j);
            if (k == a(c1)) {
              break;
            }
          }
          c0 = c1;
        }
        float f1 = (j == -1) || (this.k) ? 0.5F : 1.0F / this.scaleFactor;
        boolean flag = ((c0 == 0) || (j == -1) || (this.k)) && (shadow);
        if (flag)
        {
          this.i -= f1;
          this.j -= f1;
        }
        float f = a(c0, this.t);
        if (flag)
        {
          this.i += f1;
          this.j += f1;
        }
        if (this.s)
        {
          this.i += f1;
          if (flag)
          {
            this.i -= f1;
            this.j -= f1;
          }
          a(c0, this.t);
          this.i -= f1;
          if (flag)
          {
            this.i += f1;
            this.j += f1;
          }
          f += f1;
        }
        if (this.v)
        {
          bnu tessellator = bnu.a();
          bmz vertexbuffer = tessellator.c();
          bni.z();
          vertexbuffer.a(7, bvp.e);
          vertexbuffer.b(this.i, this.j + this.a / 2, 0.0D).d();
          vertexbuffer.b(this.i + f, this.j + this.a / 2, 0.0D).d();
          vertexbuffer.b(this.i + f, this.j + this.a / 2 - 1.0F, 0.0D).d();
          vertexbuffer.b(this.i, this.j + this.a / 2 - 1.0F, 0.0D).d();
          tessellator.b();
          bni.y();
        }
        if (this.u)
        {
          bnu tessellator1 = bnu.a();
          bmz vertexbuffer1 = tessellator1.c();
          bni.z();
          vertexbuffer1.a(7, bvp.e);
          int l = this.u ? -1 : 0;
          vertexbuffer1.b(this.i + l, this.j + this.a, 0.0D).d();
          vertexbuffer1.b(this.i + f, this.j + this.a, 0.0D).d();
          vertexbuffer1.b(this.i + f, this.j + this.a - 1.0F, 0.0D).d();
          vertexbuffer1.b(this.i + l, this.j + this.a - 1.0F, 0.0D).d();
          tessellator1.b();
          bni.y();
        }
        this.i += f;
      }
    }
  }
  
  private int a(String text, int x, int y, int width, int color, boolean dropShadow)
  {
    if (this.l)
    {
      int i = a(c(text));
      x = x + width - i;
    }
    return b(text, x, y, color, dropShadow);
  }
  
  private int b(String text, float x, float y, int color, boolean dropShadow)
  {
    if (text == null) {
      return 0;
    }
    if (this.l) {
      text = c(text);
    }
    if ((color & 0xFC000000) == 0) {
      color |= 0xFF000000;
    }
    if (dropShadow) {
      color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
    }
    this.m = ((color >> 16 & 0xFF) / 255.0F);
    this.n = ((color >> 8 & 0xFF) / 255.0F);
    this.o = ((color & 0xFF) / 255.0F);
    this.p = ((color >> 24 & 0xFF) / 255.0F);
    
    setColor(this.m, this.n, this.o, this.p);
    this.i = x;
    this.j = y;
    a(text, dropShadow);
    return (int)this.i;
  }
  
  public int a(String text)
  {
    if (text == null) {
      return 0;
    }
    float i = 0.0F;
    boolean flag = false;
    for (int j = 0; j < text.length(); j++)
    {
      char c0 = text.charAt(j);
      
      float k = getCharWidthFloat(c0);
      if ((k < 0.0F) && (j < text.length() - 1))
      {
        j++;
        c0 = text.charAt(j);
        if ((c0 != 'l') && (c0 != 'L'))
        {
          if ((c0 == 'r') || (c0 == 'R')) {
            flag = false;
          }
        }
        else {
          flag = true;
        }
        k = 0.0F;
      }
      i += k;
      if ((flag) && (k > 0.0F)) {
        i += 1.0F / this.scaleFactor;
      }
    }
    return (int)i;
  }
  
  public int a(char character)
  {
    return Math.round(getCharWidthFloat(character));
  }
  
  private float getCharWidthFloat(char character)
  {
    if (character == '§') {
      return -1.0F;
    }
    if (character == ' ') {
      return this.d[32];
    }
    int i = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\000\000\000\000\000\000\000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\000".indexOf(character);
    if ((character > 0) && (i != -1) && (!this.k)) {
      return this.d[i];
    }
    if (this.e[character] != 0)
    {
      int j = this.e[character] & 0xFF;
      int k = j >>> 4;
      int l = j & 0xF;
      l++;
      return (l - k) / 2 + 1;
    }
    return 0.0F;
  }
  
  public String a(String text, int width)
  {
    return a(text, width, false);
  }
  
  public String a(String text, int width, boolean reverse)
  {
    StringBuilder stringbuilder = new StringBuilder();
    
    float i = 0.0F;
    int j = reverse ? text.length() - 1 : 0;
    int k = reverse ? -1 : 1;
    boolean flag = false;
    boolean flag1 = false;
    for (int l = j; (l >= 0) && (l < text.length()) && (i < width); l += k)
    {
      char c0 = text.charAt(l);
      
      float i1 = getCharWidthFloat(c0);
      if (flag)
      {
        flag = false;
        if ((c0 != 'l') && (c0 != 'L'))
        {
          if ((c0 == 'r') || (c0 == 'R')) {
            flag1 = false;
          }
        }
        else {
          flag1 = true;
        }
      }
      else if (i1 < 0.0F)
      {
        flag = true;
      }
      else
      {
        i += i1;
        if (flag1) {
          i += 1.0F;
        }
      }
      if (i > width) {
        break;
      }
      if (reverse) {
        stringbuilder.insert(0, c0);
      } else {
        stringbuilder.append(c0);
      }
    }
    return stringbuilder.toString();
  }
  
  private String d(String text)
  {
    while ((text != null) && (text.endsWith("\n"))) {
      text = text.substring(0, text.length() - 1);
    }
    return text;
  }
  
  public void a(String str, int x, int y, int wrapWidth, int textColor)
  {
    e();
    this.q = textColor;
    str = d(str);
    a(str, x, y, wrapWidth, false);
  }
  
  private void a(String str, int x, int y, int wrapWidth, boolean addShadow)
  {
    for (String s : c(str, wrapWidth))
    {
      a(s, x, y, wrapWidth, this.q, addShadow);
      y += this.a;
    }
  }
  
  public int b(String str, int maxLength)
  {
    return this.a * c(str, maxLength).size();
  }
  
  public void a(boolean unicodeFlagIn)
  {
    this.k = unicodeFlagIn;
  }
  
  public boolean a()
  {
    return this.k;
  }
  
  public void b(boolean bidiFlagIn)
  {
    this.l = bidiFlagIn;
  }
  
  public List<String> c(String str, int wrapWidth)
  {
    return Arrays.asList(d(str, wrapWidth).split("\n"));
  }
  
  String d(String str, int wrapWidth)
  {
    int i = e(str, wrapWidth);
    if (str.length() <= i) {
      return str;
    }
    String s = str.substring(0, i);
    char c0 = str.charAt(i);
    boolean flag = (c0 == ' ') || (c0 == '\n');
    String s1 = b(s) + str.substring(i + (flag ? 1 : 0));
    return s + "\n" + d(s1, wrapWidth);
  }
  
  private int e(String str, int wrapWidth)
  {
    int i = str.length();
    
    float j = 0.0F;
    int k = 0;
    int l = -1;
    for (boolean flag = false; k < i; k++)
    {
      char c0 = str.charAt(k);
      switch (c0)
      {
      case '\n': 
        k--;
        break;
      case '§': 
        if (k < i - 1)
        {
          k++;
          char c1 = str.charAt(k);
          if ((c1 != 'l') && (c1 != 'L'))
          {
            if ((c1 == 'r') || (c1 == 'R') || (c(c1))) {
              flag = false;
            }
          }
          else {
            flag = true;
          }
        }
        break;
      case ' ': 
        l = k;
      default: 
        j += getCharWidthFloat(c0);
        if (flag) {
          j += 1.0F;
        }
        break;
      }
      if (c0 == '\n')
      {
        k++;
        l = k;
      }
      else
      {
        if (j > wrapWidth) {
          break;
        }
      }
    }
    return (k != i) && (l != -1) && (l < k) ? l : k;
  }
  
  private static boolean c(char colorChar)
  {
    return ((colorChar >= '0') && (colorChar <= '9')) || ((colorChar >= 'a') && (colorChar <= 'f')) || ((colorChar >= 'A') && (colorChar <= 'F'));
  }
  
  private static boolean d(char formatChar)
  {
    return ((formatChar >= 'k') && (formatChar <= 'o')) || ((formatChar >= 'K') && (formatChar <= 'O')) || (formatChar == 'r') || (formatChar == 'R');
  }
  
  public static String b(String text)
  {
    String s = "";
    int i = -1;
    int j = text.length();
    while ((i = text.indexOf('§', i + 1)) != -1) {
      if (i < j - 1)
      {
        char c0 = text.charAt(i + 1);
        if (c(c0)) {
          s = "§" + c0;
        } else if (d(c0)) {
          s = s + "§" + c0;
        }
      }
    }
    return s;
  }
  
  public boolean b()
  {
    return this.l;
  }
  
  public int b(char character)
  {
    int i = "0123456789abcdef".indexOf(character);
    if ((i < 0) || (i >= this.f.length)) {
      return 16777215;
    }
    int color = this.f[i];
    if (Config.isCustomColors()) {
      color = CustomColors.getTextColor(i, color);
    }
    return color;
  }
  
  protected void setColor(float r, float g, float b, float a)
  {
    bni.c(r, g, b, a);
  }
  
  protected void enableAlpha() {}
  
  protected void bindTexture(kk location)
  {
    this.h.a(location);
  }
  
  protected bwf getResource(kk location)
    throws IOException
  {
    return bcf.z().O().a(location);
  }
}
