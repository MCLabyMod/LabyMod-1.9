import com.google.common.util.concurrent.FutureCallback;

class bks$2
  implements FutureCallback<Object>
{
  bks$2(bks this$0, String paramString) {}
  
  public void onSuccess(Object p_onSuccess_1_)
  {
    bks.b(this.this$0).a(new ja(this.val$hash, ja.a.a));
  }
  
  public void onFailure(Throwable p_onFailure_1_)
  {
    bks.b(this.this$0).a(new ja(this.val$hash, ja.a.c));
  }
}
