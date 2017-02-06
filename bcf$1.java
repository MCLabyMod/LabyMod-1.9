class bcf$1
  implements nl
{
  bcf$1(bcf this$0) {}
  
  public String a(String str)
  {
    try
    {
      return String.format(str, new Object[] { bch.c(this.this$0.u.W.j()) });
    }
    catch (Exception exception)
    {
      return "Error: " + exception.getLocalizedMessage();
    }
  }
}
