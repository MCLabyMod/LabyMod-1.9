public final class ku
  extends RuntimeException
{
  public static final ku a = new ku();
  
  private ku()
  {
    setStackTrace(new StackTraceElement[0]);
  }
  
  public synchronized Throwable fillInStackTrace()
  {
    setStackTrace(new StackTraceElement[0]);
    return this;
  }
}
