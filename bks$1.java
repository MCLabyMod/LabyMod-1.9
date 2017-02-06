import com.google.common.util.concurrent.Futures;

class bks$1
  implements Runnable
{
  bks$1(bks this$0, String paramString1, String paramString2) {}
  
  public void run()
  {
    bks.a(this.this$0).a(new beh(new beg()
    {
      public void a(boolean result, int id)
      {
        bks.a(bks.1.this.this$0, bcf.z());
        if (result)
        {
          if (bks.a(bks.1.this.this$0).C() != null) {
            bks.a(bks.1.this.this$0).C().a(bkx.a.a);
          }
          bks.b(bks.1.this.this$0).a(new ja(bks.1.this.val$s1, ja.a.d));
          Futures.addCallback(bks.a(bks.1.this.this$0).P().a(bks.1.this.val$s, bks.1.this.val$s1), bks.a(bks.1.this.this$0, bks.1.this.val$s1));
        }
        else
        {
          if (bks.a(bks.1.this.this$0).C() != null) {
            bks.a(bks.1.this.this$0).C().a(bkx.a.b);
          }
          bks.b(bks.1.this.this$0).a(new ja(bks.1.this.val$s1, ja.a.b));
        }
        bky.b(bks.a(bks.1.this.this$0).C());
        bks.a(bks.1.this.this$0).a((bfb)null);
      }
    }, bwo.a("multiplayer.texturePrompt.line1", new Object[0]), bwo.a("multiplayer.texturePrompt.line2", new Object[0]), 0));
  }
}
