import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.SettableFuture;
import java.io.File;

class bwk$3
  implements FutureCallback<Object>
{
  bwk$3(bwk parambwk, File paramFile, SettableFuture paramSettableFuture) {}
  
  public void onSuccess(Object ☃)
  {
    this.c.a(this.a);
    this.b.set(null);
  }
  
  public void onFailure(Throwable ☃)
  {
    this.b.setException(☃);
  }
}
