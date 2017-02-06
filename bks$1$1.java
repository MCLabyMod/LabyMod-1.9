import com.google.common.util.concurrent.Futures;

class bks$1$1
  implements beg
{
  bks$1$1(bks.1 this$1) {}
  
  public void a(boolean result, int id)
  {
    bks.a(this.this$1.this$0, bcf.z());
    if (result)
    {
      if (bks.a(this.this$1.this$0).C() != null) {
        bks.a(this.this$1.this$0).C().a(bkx.a.a);
      }
      bks.b(this.this$1.this$0).a(new ja(this.this$1.val$s1, ja.a.d));
      Futures.addCallback(bks.a(this.this$1.this$0).P().a(this.this$1.val$s, this.this$1.val$s1), bks.a(this.this$1.this$0, this.this$1.val$s1));
    }
    else
    {
      if (bks.a(this.this$1.this$0).C() != null) {
        bks.a(this.this$1.this$0).C().a(bkx.a.b);
      }
      bks.b(this.this$1.this$0).a(new ja(this.this$1.val$s1, ja.a.b));
    }
    bky.b(bks.a(this.this$1.this$0).C());
    bks.a(this.this$1.this$0).a((bfb)null);
  }
}
