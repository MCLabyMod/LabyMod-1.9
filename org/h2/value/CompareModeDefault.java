package org.h2.value;

import java.text.CollationKey;
import java.text.Collator;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.util.SmallLRUCache;

public class CompareModeDefault
  extends CompareMode
{
  private final Collator collator;
  private final SmallLRUCache<String, CollationKey> collationKeys;
  
  protected CompareModeDefault(String name, int strength, boolean binaryUnsigned)
  {
    super(name, strength, binaryUnsigned);
    this.collator = CompareMode.getCollator(name);
    if (this.collator == null) {
      throw DbException.throwInternalError(name);
    }
    this.collator.setStrength(strength);
    int cacheSize = SysProperties.COLLATOR_CACHE_SIZE;
    if (cacheSize != 0) {
      this.collationKeys = SmallLRUCache.newInstance(cacheSize);
    } else {
      this.collationKeys = null;
    }
  }
  
  public int compareString(String a, String b, boolean ignoreCase)
  {
    if (ignoreCase)
    {
      a = a.toUpperCase();
      b = b.toUpperCase();
    }
    int comp;
    int comp;
    if (this.collationKeys != null)
    {
      CollationKey aKey = getKey(a);
      CollationKey bKey = getKey(b);
      comp = aKey.compareTo(bKey);
    }
    else
    {
      comp = this.collator.compare(a, b);
    }
    return comp;
  }
  
  public boolean equalsChars(String a, int ai, String b, int bi, boolean ignoreCase)
  {
    return compareString(a.substring(ai, ai + 1), b.substring(bi, bi + 1), ignoreCase) == 0;
  }
  
  private CollationKey getKey(String a)
  {
    synchronized (this.collationKeys)
    {
      CollationKey key = (CollationKey)this.collationKeys.get(a);
      if (key == null)
      {
        key = this.collator.getCollationKey(a);
        this.collationKeys.put(a, key);
      }
      return key;
    }
  }
}
