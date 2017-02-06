import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;

public class bhl
  extends bfb
{
  private final bfb a;
  private bdd f;
  private final String g;
  
  public bhl(bfb ☃, String ☃)
  {
    this.a = ☃;
    this.g = ☃;
  }
  
  public void e()
  {
    this.f.a();
  }
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    bcz ☃;
    this.n.add(☃ = new bcz(3, this.l / 2 - 100, this.m / 4 + 24 + 12, bwo.a("selectWorld.edit.resetIcon", new Object[0])));
    this.n.add(new bcz(4, this.l / 2 - 100, this.m / 4 + 48 + 12, bwo.a("selectWorld.edit.openFolder", new Object[0])));
    this.n.add(new bcz(0, this.l / 2 - 100, this.m / 4 + 96 + 12, bwo.a("selectWorld.edit.save", new Object[0])));
    this.n.add(new bcz(1, this.l / 2 - 100, this.m / 4 + 120 + 12, bwo.a("gui.cancel", new Object[0])));
    
    ☃.l = this.j.g().b(this.g, "icon.png").isFile();
    
    azk ☃ = this.j.g();
    azh ☃ = ☃.c(this.g);
    String ☃ = ☃.j();
    
    this.f = new bdd(2, this.q, this.l / 2 - 100, 60, 200, 20);
    this.f.b(true);
    this.f.a(☃);
  }
  
  public void m()
  {
    Keyboard.enableRepeatEvents(false);
  }
  
  protected void a(bcz ☃)
  {
    if (!☃.l) {
      return;
    }
    if (☃.k == 1)
    {
      this.j.a(this.a);
    }
    else if (☃.k == 0)
    {
      azk ☃ = this.j.g();
      ☃.a(this.g, this.f.b().trim());
      
      this.j.a(this.a);
    }
    else if (☃.k == 3)
    {
      azk ☃ = this.j.g();
      FileUtils.deleteQuietly(☃.b(this.g, "icon.png"));
      ☃.l = false;
    }
    else if (☃.k == 4)
    {
      azk ☃ = this.j.g();
      bzg.a(☃.b(this.g, "icon.png").getParentFile());
    }
  }
  
  protected void a(char ☃, int ☃)
  {
    this.f.a(☃, ☃);
    ((bcz)this.n.get(2)).l = (!this.f.b().trim().isEmpty());
    if ((☃ == 28) || (☃ == 156)) {
      a((bcz)this.n.get(2));
    }
  }
  
  protected void a(int ☃, int ☃, int ☃)
  {
    super.a(☃, ☃, ☃);
    
    this.f.a(☃, ☃, ☃);
  }
  
  public void a(int ☃, int ☃, float ☃)
  {
    c();
    
    a(this.q, bwo.a("selectWorld.edit.title", new Object[0]), this.l / 2, 20, 16777215);
    c(this.q, bwo.a("selectWorld.enterName", new Object[0]), this.l / 2 - 100, 47, 10526880);
    
    this.f.g();
    
    super.a(☃, ☃, ☃);
  }
}
