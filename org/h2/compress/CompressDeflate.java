package org.h2.compress;

import java.util.StringTokenizer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.h2.message.DbException;

public class CompressDeflate
  implements Compressor
{
  private int level = -1;
  private int strategy = 0;
  
  public void setOptions(String options)
  {
    if (options == null) {
      return;
    }
    try
    {
      StringTokenizer tokenizer = new StringTokenizer(options);
      while (tokenizer.hasMoreElements())
      {
        String option = tokenizer.nextToken();
        if (("level".equals(option)) || ("l".equals(option))) {
          this.level = Integer.parseInt(tokenizer.nextToken());
        } else if (("strategy".equals(option)) || ("s".equals(option))) {
          this.strategy = Integer.parseInt(tokenizer.nextToken());
        }
        Deflater deflater = new Deflater(this.level);
        deflater.setStrategy(this.strategy);
      }
    }
    catch (Exception e)
    {
      throw DbException.get(90102, options);
    }
  }
  
  public int compress(byte[] in, int inLen, byte[] out, int outPos)
  {
    Deflater deflater = new Deflater(this.level);
    deflater.setStrategy(this.strategy);
    deflater.setInput(in, 0, inLen);
    deflater.finish();
    int compressed = deflater.deflate(out, outPos, out.length - outPos);
    if (compressed == 0)
    {
      this.strategy = 0;
      this.level = -1;
      return compress(in, inLen, out, outPos);
    }
    deflater.end();
    return outPos + compressed;
  }
  
  public int getAlgorithm()
  {
    return 2;
  }
  
  public void expand(byte[] in, int inPos, int inLen, byte[] out, int outPos, int outLen)
  {
    Inflater decompresser = new Inflater();
    decompresser.setInput(in, inPos, inLen);
    decompresser.finished();
    try
    {
      int len = decompresser.inflate(out, outPos, outLen);
      if (len != outLen) {
        throw new DataFormatException(len + " " + outLen);
      }
    }
    catch (DataFormatException e)
    {
      throw DbException.get(90104, e, new String[0]);
    }
    decompresser.end();
  }
}
