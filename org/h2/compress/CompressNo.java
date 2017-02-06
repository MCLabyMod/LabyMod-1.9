package org.h2.compress;

public class CompressNo
  implements Compressor
{
  public int getAlgorithm()
  {
    return 0;
  }
  
  public void setOptions(String options) {}
  
  public int compress(byte[] in, int inLen, byte[] out, int outPos)
  {
    System.arraycopy(in, 0, out, outPos, inLen);
    return outPos + inLen;
  }
  
  public void expand(byte[] in, int inPos, int inLen, byte[] out, int outPos, int outLen)
  {
    System.arraycopy(in, inPos, out, outPos, outLen);
  }
}
