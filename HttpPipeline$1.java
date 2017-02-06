import java.util.Map;

final class HttpPipeline$1
  implements HttpListener
{
  HttpPipeline$1(Map paramMap) {}
  
  public void finished(HttpRequest req, HttpResponse resp)
  {
    synchronized (this.val$map)
    {
      this.val$map.put("Response", resp);
      this.val$map.notifyAll();
    }
  }
  
  public void failed(HttpRequest req, Exception e)
  {
    synchronized (this.val$map)
    {
      this.val$map.put("Exception", e);
      this.val$map.notifyAll();
    }
  }
}
