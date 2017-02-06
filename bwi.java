import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public abstract interface bwi
{
  public abstract InputStream a(kk paramkk)
    throws IOException;
  
  public abstract boolean b(kk paramkk);
  
  public abstract Set<String> c();
  
  public abstract <T extends bwu> T a(bww parambww, String paramString)
    throws IOException;
  
  public abstract BufferedImage a()
    throws IOException;
  
  public abstract String b();
}
