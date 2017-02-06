package org.h2.mvstore.type;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import org.h2.mvstore.DataUtils;
import org.h2.mvstore.WriteBuffer;
import org.h2.util.New;

public class ObjectDataType
  implements DataType
{
  static final int TYPE_NULL = 0;
  static final int TYPE_BOOLEAN = 1;
  static final int TYPE_BYTE = 2;
  static final int TYPE_SHORT = 3;
  static final int TYPE_INT = 4;
  static final int TYPE_LONG = 5;
  static final int TYPE_BIG_INTEGER = 6;
  static final int TYPE_FLOAT = 7;
  static final int TYPE_DOUBLE = 8;
  static final int TYPE_BIG_DECIMAL = 9;
  static final int TYPE_CHAR = 10;
  static final int TYPE_STRING = 11;
  static final int TYPE_UUID = 12;
  static final int TYPE_DATE = 13;
  static final int TYPE_ARRAY = 14;
  static final int TYPE_SERIALIZED_OBJECT = 19;
  static final int TAG_BOOLEAN_TRUE = 32;
  static final int TAG_INTEGER_NEGATIVE = 33;
  static final int TAG_INTEGER_FIXED = 34;
  static final int TAG_LONG_NEGATIVE = 35;
  static final int TAG_LONG_FIXED = 36;
  static final int TAG_BIG_INTEGER_0 = 37;
  static final int TAG_BIG_INTEGER_1 = 38;
  static final int TAG_BIG_INTEGER_SMALL = 39;
  static final int TAG_FLOAT_0 = 40;
  static final int TAG_FLOAT_1 = 41;
  static final int TAG_FLOAT_FIXED = 42;
  static final int TAG_DOUBLE_0 = 43;
  static final int TAG_DOUBLE_1 = 44;
  static final int TAG_DOUBLE_FIXED = 45;
  static final int TAG_BIG_DECIMAL_0 = 46;
  static final int TAG_BIG_DECIMAL_1 = 47;
  static final int TAG_BIG_DECIMAL_SMALL = 48;
  static final int TAG_BIG_DECIMAL_SMALL_SCALED = 49;
  static final int TAG_INTEGER_0_15 = 64;
  static final int TAG_LONG_0_7 = 80;
  static final int TAG_STRING_0_15 = 88;
  static final int TAG_BYTE_ARRAY_0_15 = 104;
  static final int FLOAT_ZERO_BITS = Float.floatToIntBits(0.0F);
  static final int FLOAT_ONE_BITS = Float.floatToIntBits(1.0F);
  static final long DOUBLE_ZERO_BITS = Double.doubleToLongBits(0.0D);
  static final long DOUBLE_ONE_BITS = Double.doubleToLongBits(1.0D);
  static final Class<?>[] COMMON_CLASSES = { Boolean.TYPE, Byte.TYPE, Short.TYPE, Character.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Object.class, Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, BigInteger.class, Float.class, Double.class, BigDecimal.class, String.class, UUID.class, Date.class };
  private static final HashMap<Class<?>, Integer> COMMON_CLASSES_MAP = New.hashMap();
  private AutoDetectDataType last;
  
  public ObjectDataType()
  {
    this.last = new StringType(this);
  }
  
  public int compare(Object a, Object b)
  {
    return this.last.compare(a, b);
  }
  
  public int getMemory(Object obj)
  {
    return this.last.getMemory(obj);
  }
  
  public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      obj[i] = read(buff);
    }
  }
  
  public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
  {
    for (int i = 0; i < len; i++) {
      write(buff, obj[i]);
    }
  }
  
  public void write(WriteBuffer buff, Object obj)
  {
    this.last.write(buff, obj);
  }
  
  private AutoDetectDataType newType(int typeId)
  {
    switch (typeId)
    {
    case 0: 
      return new NullType(this);
    case 1: 
      return new BooleanType(this);
    case 2: 
      return new ByteType(this);
    case 3: 
      return new ShortType(this);
    case 10: 
      return new CharacterType(this);
    case 4: 
      return new IntegerType(this);
    case 5: 
      return new LongType(this);
    case 7: 
      return new FloatType(this);
    case 8: 
      return new DoubleType(this);
    case 6: 
      return new BigIntegerType(this);
    case 9: 
      return new BigDecimalType(this);
    case 11: 
      return new StringType(this);
    case 12: 
      return new UUIDType(this);
    case 13: 
      return new DateType(this);
    case 14: 
      return new ObjectArrayType(this);
    case 19: 
      return new SerializedObjectType(this);
    }
    throw DataUtils.newIllegalStateException(3, "Unsupported type {0}", new Object[] { Integer.valueOf(typeId) });
  }
  
  public Object read(ByteBuffer buff)
  {
    int tag = buff.get();
    int typeId;
    if (tag <= 19)
    {
      typeId = tag;
    }
    else
    {
      int typeId;
      switch (tag)
      {
      case 32: 
        typeId = 1;
        break;
      case 33: 
      case 34: 
        typeId = 4;
        break;
      case 35: 
      case 36: 
        typeId = 5;
        break;
      case 37: 
      case 38: 
      case 39: 
        typeId = 6;
        break;
      case 40: 
      case 41: 
      case 42: 
        typeId = 7;
        break;
      case 43: 
      case 44: 
      case 45: 
        typeId = 8;
        break;
      case 46: 
      case 47: 
      case 48: 
      case 49: 
        typeId = 9;
        break;
      default: 
        if ((tag >= 64) && (tag <= 79))
        {
          typeId = 4;
        }
        else
        {
          int typeId;
          if ((tag >= 88) && (tag <= 103))
          {
            typeId = 11;
          }
          else
          {
            int typeId;
            if ((tag >= 80) && (tag <= 87))
            {
              typeId = 5;
            }
            else
            {
              int typeId;
              if ((tag >= 104) && (tag <= 119)) {
                typeId = 14;
              } else {
                throw DataUtils.newIllegalStateException(6, "Unknown tag {0}", new Object[] { Integer.valueOf(tag) });
              }
            }
          }
        }
        break;
      }
    }
    int typeId;
    AutoDetectDataType t = this.last;
    if (typeId != t.typeId) {
      this.last = (t = newType(typeId));
    }
    return t.read(buff, tag);
  }
  
  private static int getTypeId(Object obj)
  {
    if ((obj instanceof Integer)) {
      return 4;
    }
    if ((obj instanceof String)) {
      return 11;
    }
    if ((obj instanceof Long)) {
      return 5;
    }
    if ((obj instanceof Double)) {
      return 8;
    }
    if ((obj instanceof Float)) {
      return 7;
    }
    if ((obj instanceof Boolean)) {
      return 1;
    }
    if ((obj instanceof UUID)) {
      return 12;
    }
    if ((obj instanceof Byte)) {
      return 2;
    }
    if ((obj instanceof Short)) {
      return 3;
    }
    if ((obj instanceof Character)) {
      return 10;
    }
    if (obj == null) {
      return 0;
    }
    if (isDate(obj)) {
      return 13;
    }
    if (isBigInteger(obj)) {
      return 6;
    }
    if (isBigDecimal(obj)) {
      return 9;
    }
    if (obj.getClass().isArray()) {
      return 14;
    }
    return 19;
  }
  
  AutoDetectDataType switchType(Object obj)
  {
    int typeId = getTypeId(obj);
    AutoDetectDataType l = this.last;
    if (typeId != l.typeId) {
      this.last = (l = newType(typeId));
    }
    return l;
  }
  
  static boolean isBigInteger(Object obj)
  {
    return ((obj instanceof BigInteger)) && (obj.getClass() == BigInteger.class);
  }
  
  static boolean isBigDecimal(Object obj)
  {
    return ((obj instanceof BigDecimal)) && (obj.getClass() == BigDecimal.class);
  }
  
  static boolean isDate(Object obj)
  {
    return ((obj instanceof Date)) && (obj.getClass() == Date.class);
  }
  
  static boolean isArray(Object obj)
  {
    return (obj != null) && (obj.getClass().isArray());
  }
  
  static Integer getCommonClassId(Class<?> clazz)
  {
    HashMap<Class<?>, Integer> map = COMMON_CLASSES_MAP;
    if (map.size() == 0)
    {
      int i = 0;
      for (int size = COMMON_CLASSES.length; i < size; i++) {
        COMMON_CLASSES_MAP.put(COMMON_CLASSES[i], Integer.valueOf(i));
      }
    }
    return (Integer)map.get(clazz);
  }
  
  public static byte[] serialize(Object obj)
  {
    try
    {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream os = new ObjectOutputStream(out);
      os.writeObject(obj);
      return out.toByteArray();
    }
    catch (Throwable e)
    {
      throw DataUtils.newIllegalArgumentException("Could not serialize {0}", new Object[] { obj, e });
    }
  }
  
  public static Object deserialize(byte[] data)
  {
    try
    {
      ByteArrayInputStream in = new ByteArrayInputStream(data);
      ObjectInputStream is = new ObjectInputStream(in);
      return is.readObject();
    }
    catch (Throwable e)
    {
      throw DataUtils.newIllegalArgumentException("Could not deserialize {0}", new Object[] { Arrays.toString(data), e });
    }
  }
  
  public static int compareNotNull(byte[] data1, byte[] data2)
  {
    if (data1 == data2) {
      return 0;
    }
    int len = Math.min(data1.length, data2.length);
    for (int i = 0; i < len; i++)
    {
      int b = data1[i] & 0xFF;
      int b2 = data2[i] & 0xFF;
      if (b != b2) {
        return b > b2 ? 1 : -1;
      }
    }
    return Integer.signum(data1.length - data2.length);
  }
  
  static abstract class AutoDetectDataType
    implements DataType
  {
    protected final ObjectDataType base;
    protected final int typeId;
    
    AutoDetectDataType(ObjectDataType base, int typeId)
    {
      this.base = base;
      this.typeId = typeId;
    }
    
    public int getMemory(Object o)
    {
      return getType(o).getMemory(o);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      AutoDetectDataType aType = getType(aObj);
      AutoDetectDataType bType = getType(bObj);
      int typeDiff = aType.typeId - bType.typeId;
      if (typeDiff == 0) {
        return aType.compare(aObj, bObj);
      }
      return Integer.signum(typeDiff);
    }
    
    public void write(WriteBuffer buff, Object[] obj, int len, boolean key)
    {
      for (int i = 0; i < len; i++) {
        write(buff, obj[i]);
      }
    }
    
    public void write(WriteBuffer buff, Object o)
    {
      getType(o).write(buff, o);
    }
    
    public void read(ByteBuffer buff, Object[] obj, int len, boolean key)
    {
      for (int i = 0; i < len; i++) {
        obj[i] = read(buff);
      }
    }
    
    public final Object read(ByteBuffer buff)
    {
      throw DataUtils.newIllegalStateException(3, "Internal error", new Object[0]);
    }
    
    AutoDetectDataType getType(Object o)
    {
      return this.base.switchType(o);
    }
    
    abstract Object read(ByteBuffer paramByteBuffer, int paramInt);
  }
  
  static class NullType
    extends ObjectDataType.AutoDetectDataType
  {
    NullType(ObjectDataType base)
    {
      super(0);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if ((aObj == null) && (bObj == null)) {
        return 0;
      }
      if (aObj == null) {
        return -1;
      }
      if (bObj == null) {
        return 1;
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return obj == null ? 0 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (obj != null)
      {
        super.write(buff, obj);
        return;
      }
      buff.put((byte)0);
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      return null;
    }
  }
  
  static class BooleanType
    extends ObjectDataType.AutoDetectDataType
  {
    BooleanType(ObjectDataType base)
    {
      super(1);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Boolean)) && ((bObj instanceof Boolean)))
      {
        Boolean a = (Boolean)aObj;
        Boolean b = (Boolean)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Boolean) ? 0 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Boolean))
      {
        super.write(buff, obj);
        return;
      }
      int tag = ((Boolean)obj).booleanValue() ? 32 : 1;
      buff.put((byte)tag);
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      return tag == 1 ? Boolean.FALSE : Boolean.TRUE;
    }
  }
  
  static class ByteType
    extends ObjectDataType.AutoDetectDataType
  {
    ByteType(ObjectDataType base)
    {
      super(2);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Byte)) && ((bObj instanceof Byte)))
      {
        Byte a = (Byte)aObj;
        Byte b = (Byte)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Byte) ? 0 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Byte))
      {
        super.write(buff, obj);
        return;
      }
      buff.put((byte)2);
      buff.put(((Byte)obj).byteValue());
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      return Byte.valueOf(buff.get());
    }
  }
  
  static class CharacterType
    extends ObjectDataType.AutoDetectDataType
  {
    CharacterType(ObjectDataType base)
    {
      super(10);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Character)) && ((bObj instanceof Character)))
      {
        Character a = (Character)aObj;
        Character b = (Character)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Character) ? 24 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Character))
      {
        super.write(buff, obj);
        return;
      }
      buff.put((byte)10);
      buff.putChar(((Character)obj).charValue());
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      return Character.valueOf(buff.getChar());
    }
  }
  
  static class ShortType
    extends ObjectDataType.AutoDetectDataType
  {
    ShortType(ObjectDataType base)
    {
      super(3);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Short)) && ((bObj instanceof Short)))
      {
        Short a = (Short)aObj;
        Short b = (Short)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Short) ? 24 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Short))
      {
        super.write(buff, obj);
        return;
      }
      buff.put((byte)3);
      buff.putShort(((Short)obj).shortValue());
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      return Short.valueOf(buff.getShort());
    }
  }
  
  static class IntegerType
    extends ObjectDataType.AutoDetectDataType
  {
    IntegerType(ObjectDataType base)
    {
      super(4);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Integer)) && ((bObj instanceof Integer)))
      {
        Integer a = (Integer)aObj;
        Integer b = (Integer)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Integer) ? 24 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Integer))
      {
        super.write(buff, obj);
        return;
      }
      int x = ((Integer)obj).intValue();
      if (x < 0)
      {
        if ((-x < 0) || (-x > 2097151)) {
          buff.put((byte)34).putInt(x);
        } else {
          buff.put((byte)33).putVarInt(-x);
        }
      }
      else if (x <= 15) {
        buff.put((byte)(64 + x));
      } else if (x <= 2097151) {
        buff.put((byte)4).putVarInt(x);
      } else {
        buff.put((byte)34).putInt(x);
      }
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      switch (tag)
      {
      case 4: 
        return Integer.valueOf(DataUtils.readVarInt(buff));
      case 33: 
        return Integer.valueOf(-DataUtils.readVarInt(buff));
      case 34: 
        return Integer.valueOf(buff.getInt());
      }
      return Integer.valueOf(tag - 64);
    }
  }
  
  static class LongType
    extends ObjectDataType.AutoDetectDataType
  {
    LongType(ObjectDataType base)
    {
      super(5);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Long)) && ((bObj instanceof Long)))
      {
        Long a = (Long)aObj;
        Long b = (Long)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Long) ? 30 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Long))
      {
        super.write(buff, obj);
        return;
      }
      long x = ((Long)obj).longValue();
      if (x < 0L)
      {
        if ((-x < 0L) || (-x > 562949953421311L))
        {
          buff.put((byte)36);
          buff.putLong(x);
        }
        else
        {
          buff.put((byte)35);
          buff.putVarLong(-x);
        }
      }
      else if (x <= 7L)
      {
        buff.put((byte)(int)(80L + x));
      }
      else if (x <= 562949953421311L)
      {
        buff.put((byte)5);
        buff.putVarLong(x);
      }
      else
      {
        buff.put((byte)36);
        buff.putLong(x);
      }
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      switch (tag)
      {
      case 5: 
        return Long.valueOf(DataUtils.readVarLong(buff));
      case 35: 
        return Long.valueOf(-DataUtils.readVarLong(buff));
      case 36: 
        return Long.valueOf(buff.getLong());
      }
      return Long.valueOf(tag - 80);
    }
  }
  
  static class FloatType
    extends ObjectDataType.AutoDetectDataType
  {
    FloatType(ObjectDataType base)
    {
      super(7);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Float)) && ((bObj instanceof Float)))
      {
        Float a = (Float)aObj;
        Float b = (Float)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Float) ? 24 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Float))
      {
        super.write(buff, obj);
        return;
      }
      float x = ((Float)obj).floatValue();
      int f = Float.floatToIntBits(x);
      if (f == ObjectDataType.FLOAT_ZERO_BITS)
      {
        buff.put((byte)40);
      }
      else if (f == ObjectDataType.FLOAT_ONE_BITS)
      {
        buff.put((byte)41);
      }
      else
      {
        int value = Integer.reverse(f);
        if ((value >= 0) && (value <= 2097151)) {
          buff.put((byte)7).putVarInt(value);
        } else {
          buff.put((byte)42).putFloat(x);
        }
      }
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      switch (tag)
      {
      case 40: 
        return Float.valueOf(0.0F);
      case 41: 
        return Float.valueOf(1.0F);
      case 42: 
        return Float.valueOf(buff.getFloat());
      }
      return Float.valueOf(Float.intBitsToFloat(Integer.reverse(DataUtils.readVarInt(buff))));
    }
  }
  
  static class DoubleType
    extends ObjectDataType.AutoDetectDataType
  {
    DoubleType(ObjectDataType base)
    {
      super(8);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof Double)) && ((bObj instanceof Double)))
      {
        Double a = (Double)aObj;
        Double b = (Double)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof Double) ? 30 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof Double))
      {
        super.write(buff, obj);
        return;
      }
      double x = ((Double)obj).doubleValue();
      long d = Double.doubleToLongBits(x);
      if (d == ObjectDataType.DOUBLE_ZERO_BITS)
      {
        buff.put((byte)43);
      }
      else if (d == ObjectDataType.DOUBLE_ONE_BITS)
      {
        buff.put((byte)44);
      }
      else
      {
        long value = Long.reverse(d);
        if ((value >= 0L) && (value <= 562949953421311L))
        {
          buff.put((byte)8);
          buff.putVarLong(value);
        }
        else
        {
          buff.put((byte)45);
          buff.putDouble(x);
        }
      }
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      switch (tag)
      {
      case 43: 
        return Double.valueOf(0.0D);
      case 44: 
        return Double.valueOf(1.0D);
      case 45: 
        return Double.valueOf(buff.getDouble());
      }
      return Double.valueOf(Double.longBitsToDouble(Long.reverse(DataUtils.readVarLong(buff))));
    }
  }
  
  static class BigIntegerType
    extends ObjectDataType.AutoDetectDataType
  {
    BigIntegerType(ObjectDataType base)
    {
      super(6);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if ((ObjectDataType.isBigInteger(aObj)) && (ObjectDataType.isBigInteger(bObj)))
      {
        BigInteger a = (BigInteger)aObj;
        BigInteger b = (BigInteger)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return ObjectDataType.isBigInteger(obj) ? 100 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!ObjectDataType.isBigInteger(obj))
      {
        super.write(buff, obj);
        return;
      }
      BigInteger x = (BigInteger)obj;
      if (BigInteger.ZERO.equals(x))
      {
        buff.put((byte)37);
      }
      else if (BigInteger.ONE.equals(x))
      {
        buff.put((byte)38);
      }
      else
      {
        int bits = x.bitLength();
        if (bits <= 63)
        {
          buff.put((byte)39).putVarLong(x.longValue());
        }
        else
        {
          byte[] bytes = x.toByteArray();
          buff.put((byte)6).putVarInt(bytes.length).put(bytes);
        }
      }
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      switch (tag)
      {
      case 37: 
        return BigInteger.ZERO;
      case 38: 
        return BigInteger.ONE;
      case 39: 
        return BigInteger.valueOf(DataUtils.readVarLong(buff));
      }
      int len = DataUtils.readVarInt(buff);
      byte[] bytes = DataUtils.newBytes(len);
      buff.get(bytes);
      return new BigInteger(bytes);
    }
  }
  
  static class BigDecimalType
    extends ObjectDataType.AutoDetectDataType
  {
    BigDecimalType(ObjectDataType base)
    {
      super(9);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if ((ObjectDataType.isBigDecimal(aObj)) && (ObjectDataType.isBigDecimal(bObj)))
      {
        BigDecimal a = (BigDecimal)aObj;
        BigDecimal b = (BigDecimal)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public int getMemory(Object obj)
    {
      return ObjectDataType.isBigDecimal(obj) ? 150 : super.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!ObjectDataType.isBigDecimal(obj))
      {
        super.write(buff, obj);
        return;
      }
      BigDecimal x = (BigDecimal)obj;
      if (BigDecimal.ZERO.equals(x))
      {
        buff.put((byte)46);
      }
      else if (BigDecimal.ONE.equals(x))
      {
        buff.put((byte)47);
      }
      else
      {
        int scale = x.scale();
        BigInteger b = x.unscaledValue();
        int bits = b.bitLength();
        if (bits < 64)
        {
          if (scale == 0) {
            buff.put((byte)48);
          } else {
            buff.put((byte)49).putVarInt(scale);
          }
          buff.putVarLong(b.longValue());
        }
        else
        {
          byte[] bytes = b.toByteArray();
          buff.put((byte)9).putVarInt(scale).putVarInt(bytes.length).put(bytes);
        }
      }
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      switch (tag)
      {
      case 46: 
        return BigDecimal.ZERO;
      case 47: 
        return BigDecimal.ONE;
      case 48: 
        return BigDecimal.valueOf(DataUtils.readVarLong(buff));
      case 49: 
        int scale = DataUtils.readVarInt(buff);
        return BigDecimal.valueOf(DataUtils.readVarLong(buff), scale);
      }
      int scale = DataUtils.readVarInt(buff);
      int len = DataUtils.readVarInt(buff);
      byte[] bytes = DataUtils.newBytes(len);
      buff.get(bytes);
      BigInteger b = new BigInteger(bytes);
      return new BigDecimal(b, scale);
    }
  }
  
  static class StringType
    extends ObjectDataType.AutoDetectDataType
  {
    StringType(ObjectDataType base)
    {
      super(11);
    }
    
    public int getMemory(Object obj)
    {
      if (!(obj instanceof String)) {
        return super.getMemory(obj);
      }
      return 24 + 2 * obj.toString().length();
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof String)) && ((bObj instanceof String))) {
        return aObj.toString().compareTo(bObj.toString());
      }
      return super.compare(aObj, bObj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof String))
      {
        super.write(buff, obj);
        return;
      }
      String s = (String)obj;
      int len = s.length();
      if (len <= 15) {
        buff.put((byte)(88 + len));
      } else {
        buff.put((byte)11).putVarInt(len);
      }
      buff.putStringData(s, len);
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      int len;
      int len;
      if (tag == 11) {
        len = DataUtils.readVarInt(buff);
      } else {
        len = tag - 88;
      }
      return DataUtils.readString(buff, len);
    }
  }
  
  static class UUIDType
    extends ObjectDataType.AutoDetectDataType
  {
    UUIDType(ObjectDataType base)
    {
      super(12);
    }
    
    public int getMemory(Object obj)
    {
      return (obj instanceof UUID) ? 40 : super.getMemory(obj);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (((aObj instanceof UUID)) && ((bObj instanceof UUID)))
      {
        UUID a = (UUID)aObj;
        UUID b = (UUID)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!(obj instanceof UUID))
      {
        super.write(buff, obj);
        return;
      }
      buff.put((byte)12);
      UUID a = (UUID)obj;
      buff.putLong(a.getMostSignificantBits());
      buff.putLong(a.getLeastSignificantBits());
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      long a = buff.getLong();long b = buff.getLong();
      return new UUID(a, b);
    }
  }
  
  static class DateType
    extends ObjectDataType.AutoDetectDataType
  {
    DateType(ObjectDataType base)
    {
      super(13);
    }
    
    public int getMemory(Object obj)
    {
      return ObjectDataType.isDate(obj) ? 40 : super.getMemory(obj);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if ((ObjectDataType.isDate(aObj)) && (ObjectDataType.isDate(bObj)))
      {
        Date a = (Date)aObj;
        Date b = (Date)bObj;
        return a.compareTo(b);
      }
      return super.compare(aObj, bObj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!ObjectDataType.isDate(obj))
      {
        super.write(buff, obj);
        return;
      }
      buff.put((byte)13);
      Date a = (Date)obj;
      buff.putLong(a.getTime());
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      long a = buff.getLong();
      return new Date(a);
    }
  }
  
  static class ObjectArrayType
    extends ObjectDataType.AutoDetectDataType
  {
    private final ObjectDataType elementType = new ObjectDataType();
    
    ObjectArrayType(ObjectDataType base)
    {
      super(14);
    }
    
    public int getMemory(Object obj)
    {
      if (!ObjectDataType.isArray(obj)) {
        return super.getMemory(obj);
      }
      int size = 64;
      Class<?> type = obj.getClass().getComponentType();
      if (type.isPrimitive())
      {
        int len = Array.getLength(obj);
        if (type == Boolean.TYPE) {
          size += len;
        } else if (type == Byte.TYPE) {
          size += len;
        } else if (type == Character.TYPE) {
          size += len * 2;
        } else if (type == Short.TYPE) {
          size += len * 2;
        } else if (type == Integer.TYPE) {
          size += len * 4;
        } else if (type == Float.TYPE) {
          size += len * 4;
        } else if (type == Double.TYPE) {
          size += len * 8;
        } else if (type == Long.TYPE) {
          size += len * 8;
        }
      }
      else
      {
        for (Object x : (Object[])obj) {
          if (x != null) {
            size += this.elementType.getMemory(x);
          }
        }
      }
      return size * 2;
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if ((!ObjectDataType.isArray(aObj)) || (!ObjectDataType.isArray(bObj))) {
        return super.compare(aObj, bObj);
      }
      if (aObj == bObj) {
        return 0;
      }
      Class<?> type = aObj.getClass().getComponentType();
      Class<?> bType = bObj.getClass().getComponentType();
      if (type != bType)
      {
        Integer classA = ObjectDataType.getCommonClassId(type);
        Integer classB = ObjectDataType.getCommonClassId(bType);
        if (classA != null)
        {
          if (classB != null) {
            return classA.compareTo(classB);
          }
          return -1;
        }
        if (classB != null) {
          return 1;
        }
        return type.getName().compareTo(bType.getName());
      }
      int aLen = Array.getLength(aObj);
      int bLen = Array.getLength(bObj);
      int len = Math.min(aLen, bLen);
      if (type.isPrimitive())
      {
        if (type == Byte.TYPE)
        {
          byte[] a = (byte[])aObj;
          byte[] b = (byte[])bObj;
          return ObjectDataType.compareNotNull(a, b);
        }
        for (int i = 0; i < len; i++)
        {
          int x;
          int x;
          if (type == Boolean.TYPE)
          {
            x = Integer.signum((((boolean[])(boolean[])aObj)[i] != 0 ? 1 : 0) - (((boolean[])(boolean[])bObj)[i] != 0 ? 1 : 0));
          }
          else
          {
            int x;
            if (type == Character.TYPE)
            {
              x = Integer.signum(((char[])(char[])aObj)[i] - ((char[])(char[])bObj)[i]);
            }
            else
            {
              int x;
              if (type == Short.TYPE)
              {
                x = Integer.signum(((short[])(short[])aObj)[i] - ((short[])(short[])bObj)[i]);
              }
              else
              {
                int x;
                if (type == Integer.TYPE)
                {
                  int a = ((int[])(int[])aObj)[i];
                  int b = ((int[])(int[])bObj)[i];
                  x = a < b ? -1 : a == b ? 0 : 1;
                }
                else
                {
                  int x;
                  if (type == Float.TYPE)
                  {
                    x = Float.compare(((float[])(float[])aObj)[i], ((float[])(float[])bObj)[i]);
                  }
                  else
                  {
                    int x;
                    if (type == Double.TYPE)
                    {
                      x = Double.compare(((double[])(double[])aObj)[i], ((double[])(double[])bObj)[i]);
                    }
                    else
                    {
                      long a = ((long[])(long[])aObj)[i];
                      long b = ((long[])(long[])bObj)[i];
                      x = a < b ? -1 : a == b ? 0 : 1;
                    }
                  }
                }
              }
            }
          }
          if (x != 0) {
            return x;
          }
        }
      }
      else
      {
        Object[] a = (Object[])aObj;
        Object[] b = (Object[])bObj;
        for (int i = 0; i < len; i++)
        {
          int comp = this.elementType.compare(a[i], b[i]);
          if (comp != 0) {
            return comp;
          }
        }
      }
      return aLen < bLen ? -1 : aLen == bLen ? 0 : 1;
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      if (!ObjectDataType.isArray(obj))
      {
        super.write(buff, obj);
        return;
      }
      Class<?> type = obj.getClass().getComponentType();
      Integer classId = ObjectDataType.getCommonClassId(type);
      if (classId != null)
      {
        if (type.isPrimitive())
        {
          if (type == Byte.TYPE)
          {
            byte[] data = (byte[])obj;
            int len = data.length;
            if (len <= 15) {
              buff.put((byte)(104 + len));
            } else {
              buff.put((byte)14).put((byte)classId.intValue()).putVarInt(len);
            }
            buff.put(data);
            return;
          }
          int len = Array.getLength(obj);
          buff.put((byte)14).put((byte)classId.intValue()).putVarInt(len);
          for (int i = 0; i < len; i++) {
            if (type == Boolean.TYPE) {
              buff.put((byte)(((boolean[])(boolean[])obj)[i] != 0 ? 1 : 0));
            } else if (type == Character.TYPE) {
              buff.putChar(((char[])(char[])obj)[i]);
            } else if (type == Short.TYPE) {
              buff.putShort(((short[])(short[])obj)[i]);
            } else if (type == Integer.TYPE) {
              buff.putInt(((int[])(int[])obj)[i]);
            } else if (type == Float.TYPE) {
              buff.putFloat(((float[])(float[])obj)[i]);
            } else if (type == Double.TYPE) {
              buff.putDouble(((double[])(double[])obj)[i]);
            } else {
              buff.putLong(((long[])(long[])obj)[i]);
            }
          }
          return;
        }
        buff.put((byte)14).put((byte)classId.intValue());
      }
      else
      {
        buff.put((byte)14).put((byte)-1);
        String c = type.getName();
        StringDataType.INSTANCE.write(buff, c);
      }
      Object[] array = (Object[])obj;
      int len = array.length;
      buff.putVarInt(len);
      for (Object x : array) {
        this.elementType.write(buff, x);
      }
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      if (tag != 14)
      {
        int len = tag - 104;
        byte[] data = DataUtils.newBytes(len);
        buff.get(data);
        return data;
      }
      int ct = buff.get();
      Class<?> clazz;
      if (ct == -1)
      {
        String componentType = StringDataType.INSTANCE.read(buff);
        try
        {
          clazz = Class.forName(componentType);
        }
        catch (Exception e)
        {
          Class<?> clazz;
          throw DataUtils.newIllegalStateException(8, "Could not get class {0}", new Object[] { componentType, e });
        }
      }
      else
      {
        clazz = ObjectDataType.COMMON_CLASSES[ct];
      }
      int len = DataUtils.readVarInt(buff);
      Object obj;
      try
      {
        obj = Array.newInstance(clazz, len);
      }
      catch (Exception e)
      {
        throw DataUtils.newIllegalStateException(8, "Could not create array of type {0} length {1}", new Object[] { clazz, Integer.valueOf(len), e });
      }
      if (clazz.isPrimitive())
      {
        for (int i = 0; i < len; i++) {
          if (clazz == Boolean.TYPE) {
            ((boolean[])obj)[i] = (buff.get() == 1 ? 1 : 0);
          } else if (clazz == Byte.TYPE) {
            ((byte[])obj)[i] = buff.get();
          } else if (clazz == Character.TYPE) {
            ((char[])obj)[i] = buff.getChar();
          } else if (clazz == Short.TYPE) {
            ((short[])obj)[i] = buff.getShort();
          } else if (clazz == Integer.TYPE) {
            ((int[])obj)[i] = buff.getInt();
          } else if (clazz == Float.TYPE) {
            ((float[])obj)[i] = buff.getFloat();
          } else if (clazz == Double.TYPE) {
            ((double[])obj)[i] = buff.getDouble();
          } else {
            ((long[])obj)[i] = buff.getLong();
          }
        }
      }
      else
      {
        Object[] array = (Object[])obj;
        for (int i = 0; i < len; i++) {
          array[i] = this.elementType.read(buff);
        }
      }
      return obj;
    }
  }
  
  static class SerializedObjectType
    extends ObjectDataType.AutoDetectDataType
  {
    private int averageSize = 10000;
    
    SerializedObjectType(ObjectDataType base)
    {
      super(19);
    }
    
    public int compare(Object aObj, Object bObj)
    {
      if (aObj == bObj) {
        return 0;
      }
      DataType ta = getType(aObj);
      DataType tb = getType(bObj);
      if ((ta != this) || (tb != this))
      {
        if (ta == tb) {
          return ta.compare(aObj, bObj);
        }
        return super.compare(aObj, bObj);
      }
      if (((aObj instanceof Comparable)) && 
        (aObj.getClass().isAssignableFrom(bObj.getClass()))) {
        return ((Comparable)aObj).compareTo(bObj);
      }
      if (((bObj instanceof Comparable)) && 
        (bObj.getClass().isAssignableFrom(aObj.getClass()))) {
        return -((Comparable)bObj).compareTo(aObj);
      }
      byte[] a = ObjectDataType.serialize(aObj);
      byte[] b = ObjectDataType.serialize(bObj);
      return ObjectDataType.compareNotNull(a, b);
    }
    
    public int getMemory(Object obj)
    {
      DataType t = getType(obj);
      if (t == this) {
        return this.averageSize;
      }
      return t.getMemory(obj);
    }
    
    public void write(WriteBuffer buff, Object obj)
    {
      DataType t = getType(obj);
      if (t != this)
      {
        t.write(buff, obj);
        return;
      }
      byte[] data = ObjectDataType.serialize(obj);
      
      int size = data.length * 2;
      
      this.averageSize = ((size + 15 * this.averageSize) / 16);
      buff.put((byte)19).putVarInt(data.length).put(data);
    }
    
    public Object read(ByteBuffer buff, int tag)
    {
      int len = DataUtils.readVarInt(buff);
      byte[] data = DataUtils.newBytes(len);
      buff.get(data);
      return ObjectDataType.deserialize(data);
    }
  }
}
