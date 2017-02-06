import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class bcg
{
  public int a;
  public int b;
  
  public void a()
  {
    Mouse.setGrabbed(true);
    this.a = 0;
    this.b = 0;
  }
  
  public void b()
  {
    Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
    Mouse.setGrabbed(false);
  }
  
  public void c()
  {
    this.a = Mouse.getDX();
    this.b = Mouse.getDY();
  }
}
