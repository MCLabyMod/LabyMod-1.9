package org.h2.engine;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import org.h2.Driver;
import org.h2.command.Parser;
import org.h2.expression.Expression;
import org.h2.message.DbException;
import org.h2.schema.Schema;
import org.h2.schema.SchemaObjectBase;
import org.h2.table.Table;
import org.h2.util.JdbcUtils;
import org.h2.util.New;
import org.h2.util.SourceCompiler;
import org.h2.util.StatementBuilder;
import org.h2.util.StringUtils;
import org.h2.value.DataType;
import org.h2.value.Value;
import org.h2.value.ValueArray;
import org.h2.value.ValueNull;

public class FunctionAlias
  extends SchemaObjectBase
{
  private String className;
  private String methodName;
  private String source;
  private JavaMethod[] javaMethods;
  private boolean deterministic;
  private boolean bufferResultSetToLocalTemp = true;
  
  private FunctionAlias(Schema schema, int id, String name)
  {
    initSchemaObjectBase(schema, id, name, "function");
  }
  
  public static FunctionAlias newInstance(Schema schema, int id, String name, String javaClassMethod, boolean force, boolean bufferResultSetToLocalTemp)
  {
    FunctionAlias alias = new FunctionAlias(schema, id, name);
    int paren = javaClassMethod.indexOf('(');
    int lastDot = javaClassMethod.lastIndexOf('.', paren < 0 ? javaClassMethod.length() : paren);
    if (lastDot < 0) {
      throw DbException.get(42000, javaClassMethod);
    }
    alias.className = javaClassMethod.substring(0, lastDot);
    alias.methodName = javaClassMethod.substring(lastDot + 1);
    alias.bufferResultSetToLocalTemp = bufferResultSetToLocalTemp;
    alias.init(force);
    return alias;
  }
  
  public static FunctionAlias newInstanceFromSource(Schema schema, int id, String name, String source, boolean force, boolean bufferResultSetToLocalTemp)
  {
    FunctionAlias alias = new FunctionAlias(schema, id, name);
    alias.source = source;
    alias.bufferResultSetToLocalTemp = bufferResultSetToLocalTemp;
    alias.init(force);
    return alias;
  }
  
  private void init(boolean force)
  {
    try
    {
      load();
    }
    catch (DbException e)
    {
      if (!force) {
        throw e;
      }
    }
  }
  
  private synchronized void load()
  {
    if (this.javaMethods != null) {
      return;
    }
    if (this.source != null) {
      loadFromSource();
    } else {
      loadClass();
    }
  }
  
  private void loadFromSource()
  {
    SourceCompiler compiler = this.database.getCompiler();
    synchronized (compiler)
    {
      String fullClassName = "org.h2.dynamic." + getName();
      compiler.setSource(fullClassName, this.source);
      try
      {
        Method m = compiler.getMethod(fullClassName);
        JavaMethod method = new JavaMethod(m, 0);
        this.javaMethods = new JavaMethod[] { method };
      }
      catch (DbException e)
      {
        throw e;
      }
      catch (Exception e)
      {
        throw DbException.get(42000, e, new String[] { this.source });
      }
    }
  }
  
  private void loadClass()
  {
    Class<?> javaClass = JdbcUtils.loadUserClass(this.className);
    Method[] methods = javaClass.getMethods();
    ArrayList<JavaMethod> list = New.arrayList();
    int i = 0;
    for (int len = methods.length; i < len; i++)
    {
      Method m = methods[i];
      if (Modifier.isStatic(m.getModifiers())) {
        if ((m.getName().equals(this.methodName)) || (getMethodSignature(m).equals(this.methodName)))
        {
          JavaMethod javaMethod = new JavaMethod(m, i);
          for (JavaMethod old : list) {
            if (old.getParameterCount() == javaMethod.getParameterCount()) {
              throw DbException.get(90073, new String[] { old.toString(), javaMethod.toString() });
            }
          }
          list.add(javaMethod);
        }
      }
    }
    if (list.size() == 0) {
      throw DbException.get(90139, this.methodName + " (" + this.className + ")");
    }
    this.javaMethods = new JavaMethod[list.size()];
    list.toArray(this.javaMethods);
    
    Arrays.sort(this.javaMethods);
  }
  
  private static String getMethodSignature(Method m)
  {
    StatementBuilder buff = new StatementBuilder(m.getName());
    buff.append('(');
    for (Class<?> p : m.getParameterTypes())
    {
      buff.appendExceptFirst(",");
      if (p.isArray()) {
        buff.append(p.getComponentType().getName()).append("[]");
      } else {
        buff.append(p.getName());
      }
    }
    return buff.append(')').toString();
  }
  
  public String getCreateSQLForCopy(Table table, String quotedName)
  {
    throw DbException.throwInternalError();
  }
  
  public String getDropSQL()
  {
    return "DROP ALIAS IF EXISTS " + getSQL();
  }
  
  public String getSQL()
  {
    if ((this.database.getSettings().functionsInSchema) || (!getSchema().getName().equals("PUBLIC"))) {
      return super.getSQL();
    }
    return Parser.quoteIdentifier(getName());
  }
  
  public String getCreateSQL()
  {
    StringBuilder buff = new StringBuilder("CREATE FORCE ALIAS ");
    buff.append(getSQL());
    if (this.deterministic) {
      buff.append(" DETERMINISTIC");
    }
    if (!this.bufferResultSetToLocalTemp) {
      buff.append(" NOBUFFER");
    }
    if (this.source != null) {
      buff.append(" AS ").append(StringUtils.quoteStringSQL(this.source));
    } else {
      buff.append(" FOR ").append(Parser.quoteIdentifier(this.className + "." + this.methodName));
    }
    return buff.toString();
  }
  
  public int getType()
  {
    return 9;
  }
  
  public synchronized void removeChildrenAndResources(Session session)
  {
    this.database.removeMeta(session, getId());
    this.className = null;
    this.methodName = null;
    this.javaMethods = null;
    invalidate();
  }
  
  public void checkRename()
  {
    throw DbException.getUnsupportedException("RENAME");
  }
  
  public JavaMethod findJavaMethod(Expression[] args)
  {
    load();
    int parameterCount = args.length;
    for (JavaMethod m : this.javaMethods)
    {
      int count = m.getParameterCount();
      if ((count == parameterCount) || ((m.isVarArgs()) && (count <= parameterCount + 1))) {
        return m;
      }
    }
    throw DbException.get(90087, getName() + " (" + this.className + ", parameter count: " + parameterCount + ")");
  }
  
  public String getJavaClassName()
  {
    return this.className;
  }
  
  public String getJavaMethodName()
  {
    return this.methodName;
  }
  
  public JavaMethod[] getJavaMethods()
  {
    load();
    return this.javaMethods;
  }
  
  public void setDeterministic(boolean deterministic)
  {
    this.deterministic = deterministic;
  }
  
  public boolean isDeterministic()
  {
    return this.deterministic;
  }
  
  public String getSource()
  {
    return this.source;
  }
  
  static boolean isVarArgs(Method m)
  {
    if ("1.5".compareTo(SysProperties.JAVA_SPECIFICATION_VERSION) > 0) {
      return false;
    }
    try
    {
      Method isVarArgs = m.getClass().getMethod("isVarArgs", new Class[0]);
      Boolean result = (Boolean)isVarArgs.invoke(m, new Object[0]);
      return result.booleanValue();
    }
    catch (Exception e) {}
    return false;
  }
  
  public boolean isBufferResultSetToLocalTemp()
  {
    return this.bufferResultSetToLocalTemp;
  }
  
  public static class JavaMethod
    implements Comparable<JavaMethod>
  {
    private final int id;
    private final Method method;
    private final int dataType;
    private boolean hasConnectionParam;
    private boolean varArgs;
    private Class<?> varArgClass;
    private int paramCount;
    
    JavaMethod(Method method, int id)
    {
      this.method = method;
      this.id = id;
      Class<?>[] paramClasses = method.getParameterTypes();
      this.paramCount = paramClasses.length;
      if (this.paramCount > 0)
      {
        Class<?> paramClass = paramClasses[0];
        if (Connection.class.isAssignableFrom(paramClass))
        {
          this.hasConnectionParam = true;
          this.paramCount -= 1;
        }
      }
      if (this.paramCount > 0)
      {
        Class<?> lastArg = paramClasses[(paramClasses.length - 1)];
        if ((lastArg.isArray()) && (FunctionAlias.isVarArgs(method)))
        {
          this.varArgs = true;
          this.varArgClass = lastArg.getComponentType();
        }
      }
      Class<?> returnClass = method.getReturnType();
      this.dataType = DataType.getTypeFromClass(returnClass);
    }
    
    public String toString()
    {
      return this.method.toString();
    }
    
    public boolean hasConnectionParam()
    {
      return this.hasConnectionParam;
    }
    
    public Value getValue(Session session, Expression[] args, boolean columnList)
    {
      Class<?>[] paramClasses = this.method.getParameterTypes();
      Object[] params = new Object[paramClasses.length];
      int p = 0;
      if ((this.hasConnectionParam) && (params.length > 0)) {
        params[(p++)] = session.createConnection(columnList);
      }
      Object varArg = null;
      if (this.varArgs)
      {
        int len = args.length - params.length + 1 + (this.hasConnectionParam ? 1 : 0);
        
        varArg = Array.newInstance(this.varArgClass, len);
        params[(params.length - 1)] = varArg;
      }
      int a = 0;
      int type;
      for (int len = args.length; a < len; p++)
      {
        boolean currentIsVarArg = (this.varArgs) && (p >= paramClasses.length - 1);
        Class<?> paramClass;
        Class<?> paramClass;
        if (currentIsVarArg) {
          paramClass = this.varArgClass;
        } else {
          paramClass = paramClasses[p];
        }
        type = DataType.getTypeFromClass(paramClass);
        Value v = args[a].getValue(session);
        Object o;
        Object o;
        if (Value.class.isAssignableFrom(paramClass))
        {
          o = v;
        }
        else
        {
          Object o;
          if ((v.getType() == 17) && (paramClass.isArray()) && (paramClass.getComponentType() != Object.class))
          {
            Value[] array = ((ValueArray)v).getList();
            Object[] objArray = (Object[])Array.newInstance(paramClass.getComponentType(), array.length);
            
            int componentType = DataType.getTypeFromClass(paramClass.getComponentType());
            for (int i = 0; i < objArray.length; i++) {
              objArray[i] = array[i].convertTo(componentType).getObject();
            }
            o = objArray;
          }
          else
          {
            v = v.convertTo(type);
            o = v.getObject();
          }
        }
        if (o == null)
        {
          if (paramClass.isPrimitive()) {
            if (columnList) {
              o = DataType.getDefaultForPrimitiveType(paramClass);
            } else {
              return ValueNull.INSTANCE;
            }
          }
        }
        else if ((!paramClass.isAssignableFrom(o.getClass())) && (!paramClass.isPrimitive())) {
          o = DataType.convertTo(session.createConnection(false), v, paramClass);
        }
        if (currentIsVarArg) {
          Array.set(varArg, p - params.length + 1, o);
        } else {
          params[p] = o;
        }
        a++;
      }
      boolean old = session.getAutoCommit();
      Value identity = session.getLastScopeIdentity();
      boolean defaultConnection = session.getDatabase().getSettings().defaultConnection;
      try
      {
        session.setAutoCommit(false);
        Object returnValue;
        try
        {
          if (defaultConnection) {
            Driver.setDefaultConnection(session.createConnection(columnList));
          }
          returnValue = this.method.invoke(null, params);
          if (returnValue == null) {
            return ValueNull.INSTANCE;
          }
        }
        catch (InvocationTargetException e)
        {
          StatementBuilder buff = new StatementBuilder(this.method.getName());
          buff.append('(');
          for (Object o : params)
          {
            buff.appendExceptFirst(", ");
            buff.append(o == null ? "null" : o.toString());
          }
          buff.append(')');
          throw DbException.convertInvocation(e, buff.toString());
        }
        catch (Exception e)
        {
          throw DbException.convert(e);
        }
        if (Value.class.isAssignableFrom(this.method.getReturnType())) {
          return (Value)returnValue;
        }
        return null;
      }
      finally
      {
        session.setLastScopeIdentity(identity);
        session.setAutoCommit(old);
        if (defaultConnection) {
          Driver.setDefaultConnection(null);
        }
      }
    }
    
    public Class<?>[] getColumnClasses()
    {
      return this.method.getParameterTypes();
    }
    
    public int getDataType()
    {
      return this.dataType;
    }
    
    public int getParameterCount()
    {
      return this.paramCount;
    }
    
    public boolean isVarArgs()
    {
      return this.varArgs;
    }
    
    public int compareTo(JavaMethod m)
    {
      if (this.varArgs != m.varArgs) {
        return this.varArgs ? 1 : -1;
      }
      if (this.paramCount != m.paramCount) {
        return this.paramCount - m.paramCount;
      }
      if (this.hasConnectionParam != m.hasConnectionParam) {
        return this.hasConnectionParam ? 1 : -1;
      }
      return this.id - m.id;
    }
  }
}
