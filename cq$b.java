public enum cq$b
{
  private final int c;
  private final String d;
  
  private cq$b(int offset, String description)
  {
    this.c = offset;
    this.d = description;
  }
  
  public int a()
  {
    return this.c;
  }
  
  public String toString()
  {
    return this.d;
  }
}
