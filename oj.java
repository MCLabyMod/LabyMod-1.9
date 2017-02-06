public abstract class oj<T>
{
  private T a;
  private boolean b = false;
  
  public T c()
  {
    if (!this.b)
    {
      this.b = true;
      this.a = b();
    }
    return (T)this.a;
  }
  
  protected abstract T b();
}
