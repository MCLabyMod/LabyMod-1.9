final class bcr$4
  implements bcq
{
  public int a(adq ☃, int ☃)
  {
    if (☃ != 1) {
      return -1;
    }
    eb ☃ = adf.a(☃, "Colors");
    if (!(☃ instanceof ds)) {
      return 9079434;
    }
    int[] ☃ = ((ds)☃).c();
    if (☃.length == 1) {
      return ☃[0];
    }
    int ☃ = 0;
    int ☃ = 0;
    int ☃ = 0;
    
    int ☃ = 0;
    for (int ☃ = ☃.length; ☃ < ☃; ☃++)
    {
      int ☃ = ☃[☃];
      ☃ += ((☃ & 0xFF0000) >> 16);
      ☃ += ((☃ & 0xFF00) >> 8);
      ☃ += ((☃ & 0xFF) >> 0);
    }
    ☃ /= ☃.length;
    ☃ /= ☃.length;
    ☃ /= ☃.length;
    
    return ☃ << 16 | ☃ << 8 | ☃;
  }
}
