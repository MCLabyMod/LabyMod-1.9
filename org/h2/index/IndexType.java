package org.h2.index;

public class IndexType
{
  private boolean primaryKey;
  private boolean persistent;
  private boolean unique;
  private boolean hash;
  private boolean scan;
  private boolean spatial;
  private boolean belongsToConstraint;
  
  public static IndexType createPrimaryKey(boolean persistent, boolean hash)
  {
    IndexType type = new IndexType();
    type.primaryKey = true;
    type.persistent = persistent;
    type.hash = hash;
    type.unique = true;
    return type;
  }
  
  public static IndexType createUnique(boolean persistent, boolean hash)
  {
    IndexType type = new IndexType();
    type.unique = true;
    type.persistent = persistent;
    type.hash = hash;
    return type;
  }
  
  public static IndexType createNonUnique(boolean persistent)
  {
    return createNonUnique(persistent, false, false);
  }
  
  public static IndexType createNonUnique(boolean persistent, boolean hash, boolean spatial)
  {
    IndexType type = new IndexType();
    type.persistent = persistent;
    type.hash = hash;
    type.spatial = spatial;
    return type;
  }
  
  public static IndexType createScan(boolean persistent)
  {
    IndexType type = new IndexType();
    type.persistent = persistent;
    type.scan = true;
    return type;
  }
  
  public void setBelongsToConstraint(boolean belongsToConstraint)
  {
    this.belongsToConstraint = belongsToConstraint;
  }
  
  public boolean getBelongsToConstraint()
  {
    return this.belongsToConstraint;
  }
  
  public boolean isHash()
  {
    return this.hash;
  }
  
  public boolean isSpatial()
  {
    return this.spatial;
  }
  
  public boolean isPersistent()
  {
    return this.persistent;
  }
  
  public boolean isPrimaryKey()
  {
    return this.primaryKey;
  }
  
  public boolean isUnique()
  {
    return this.unique;
  }
  
  public String getSQL()
  {
    StringBuilder buff = new StringBuilder();
    if (this.primaryKey)
    {
      buff.append("PRIMARY KEY");
      if (this.hash) {
        buff.append(" HASH");
      }
    }
    else
    {
      if (this.unique) {
        buff.append("UNIQUE ");
      }
      if (this.hash) {
        buff.append("HASH ");
      }
      if (this.spatial) {
        buff.append("SPATIAL ");
      }
      buff.append("INDEX");
    }
    return buff.toString();
  }
  
  public boolean isScan()
  {
    return this.scan;
  }
}
