public class FileDownloadThread
  extends Thread
{
  private String urlString = null;
  private IFileDownloadListener listener = null;
  
  public FileDownloadThread(String urlString, IFileDownloadListener listener)
  {
    this.urlString = urlString;
    this.listener = listener;
  }
  
  public void run()
  {
    try
    {
      byte[] bytes = HttpPipeline.get(this.urlString, bcf.z().M());
      
      this.listener.fileDownloadFinished(this.urlString, bytes, null);
    }
    catch (Exception e)
    {
      this.listener.fileDownloadFinished(this.urlString, null, e);
    }
  }
  
  public String getUrlString()
  {
    return this.urlString;
  }
  
  public IFileDownloadListener getListener()
  {
    return this.listener;
  }
}
