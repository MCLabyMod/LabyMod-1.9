package org.h2.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileManager.Location;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import org.h2.engine.Constants;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;

public class SourceCompiler
{
  static final JavaCompiler JAVA_COMPILER;
  private static final Class<?> JAVAC_SUN;
  private static final String COMPILE_DIR = Utils.getProperty("java.io.tmpdir", ".");
  final HashMap<String, String> sources;
  final HashMap<String, Class<?>> compiled;
  boolean useJavaSystemCompiler;
  
  public SourceCompiler()
  {
    this.sources = New.hashMap();
    
    this.compiled = New.hashMap();
    
    this.useJavaSystemCompiler = SysProperties.JAVA_SYSTEM_COMPILER;
  }
  
  static
  {
    JavaCompiler c;
    try
    {
      c = ToolProvider.getSystemJavaCompiler();
    }
    catch (Exception e)
    {
      c = null;
    }
    JAVA_COMPILER = c;
    Class<?> clazz;
    try
    {
      clazz = Class.forName("com.sun.tools.javac.Main");
    }
    catch (Exception e)
    {
      clazz = null;
    }
    JAVAC_SUN = clazz;
  }
  
  public void setSource(String className, String source)
  {
    this.sources.put(className, source);
    this.compiled.clear();
  }
  
  public void setJavaSystemCompiler(boolean enabled)
  {
    this.useJavaSystemCompiler = enabled;
  }
  
  public Class<?> getClass(String packageAndClassName)
    throws ClassNotFoundException
  {
    Class<?> compiledClass = (Class)this.compiled.get(packageAndClassName);
    if (compiledClass != null) {
      return compiledClass;
    }
    String source = (String)this.sources.get(packageAndClassName);
    if (isGroovySource(source))
    {
      Class<?> clazz = GroovyCompiler.parseClass(source, packageAndClassName);
      this.compiled.put(packageAndClassName, clazz);
      return clazz;
    }
    ClassLoader classLoader = new ClassLoader(getClass().getClassLoader())
    {
      public Class<?> findClass(String name)
        throws ClassNotFoundException
      {
        Class<?> classInstance = (Class)SourceCompiler.this.compiled.get(name);
        if (classInstance == null)
        {
          String source = (String)SourceCompiler.this.sources.get(name);
          String packageName = null;
          int idx = name.lastIndexOf('.');
          String className;
          String className;
          if (idx >= 0)
          {
            packageName = name.substring(0, idx);
            className = name.substring(idx + 1);
          }
          else
          {
            className = name;
          }
          String s = SourceCompiler.getCompleteSourceCode(packageName, className, source);
          if ((SourceCompiler.JAVA_COMPILER != null) && (SourceCompiler.this.useJavaSystemCompiler))
          {
            classInstance = SourceCompiler.this.javaxToolsJavac(packageName, className, s);
          }
          else
          {
            byte[] data = SourceCompiler.this.javacCompile(packageName, className, s);
            if (data == null) {
              classInstance = findSystemClass(name);
            } else {
              classInstance = defineClass(name, data, 0, data.length);
            }
          }
          SourceCompiler.this.compiled.put(name, classInstance);
        }
        return classInstance;
      }
    };
    return classLoader.loadClass(packageAndClassName);
  }
  
  private static boolean isGroovySource(String source)
  {
    return (source.startsWith("//groovy")) || (source.startsWith("@groovy"));
  }
  
  public Method getMethod(String className)
    throws ClassNotFoundException
  {
    Class<?> clazz = getClass(className);
    Method[] methods = clazz.getDeclaredMethods();
    for (Method m : methods)
    {
      int modifiers = m.getModifiers();
      if ((Modifier.isPublic(modifiers)) && (Modifier.isStatic(modifiers)))
      {
        String name = m.getName();
        if ((!name.startsWith("_")) && (!m.getName().equals("main"))) {
          return m;
        }
      }
    }
    return null;
  }
  
  byte[] javacCompile(String packageName, String className, String source)
  {
    File dir = new File(COMPILE_DIR);
    if (packageName != null)
    {
      dir = new File(dir, packageName.replace('.', '/'));
      FileUtils.createDirectories(dir.getAbsolutePath());
    }
    File javaFile = new File(dir, className + ".java");
    File classFile = new File(dir, className + ".class");
    try
    {
      OutputStream f = FileUtils.newOutputStream(javaFile.getAbsolutePath(), false);
      Writer out = IOUtils.getBufferedWriter(f);
      classFile.delete();
      out.write(source);
      out.close();
      if (JAVAC_SUN != null) {
        javacSun(javaFile);
      } else {
        javacProcess(javaFile);
      }
      byte[] data = new byte[(int)classFile.length()];
      DataInputStream in = new DataInputStream(new FileInputStream(classFile));
      in.readFully(data);
      in.close();
      return data;
    }
    catch (Exception e)
    {
      throw DbException.convert(e);
    }
    finally
    {
      javaFile.delete();
      classFile.delete();
    }
  }
  
  static String getCompleteSourceCode(String packageName, String className, String source)
  {
    if (source.startsWith("package ")) {
      return source;
    }
    StringBuilder buff = new StringBuilder();
    if (packageName != null) {
      buff.append("package ").append(packageName).append(";\n");
    }
    int endImport = source.indexOf("@CODE");
    String importCode = "import java.util.*;\nimport java.math.*;\nimport java.sql.*;\n";
    if (endImport >= 0)
    {
      importCode = source.substring(0, endImport);
      source = source.substring("@CODE".length() + endImport);
    }
    buff.append(importCode);
    buff.append("public class ").append(className).append(" {\n    public static ").append(source).append("\n}\n");
    
    return buff.toString();
  }
  
  Class<?> javaxToolsJavac(String packageName, String className, String source)
  {
    String fullClassName = packageName + "." + className;
    StringWriter writer = new StringWriter();
    JavaFileManager fileManager = new ClassFileManager(JAVA_COMPILER.getStandardFileManager(null, null, null));
    
    ArrayList<JavaFileObject> compilationUnits = new ArrayList();
    compilationUnits.add(new StringJavaFileObject(fullClassName, source));
    JAVA_COMPILER.getTask(writer, fileManager, null, null, null, compilationUnits).call();
    
    String err = writer.toString();
    throwSyntaxError(err);
    try
    {
      return fileManager.getClassLoader(null).loadClass(fullClassName);
    }
    catch (ClassNotFoundException e)
    {
      throw DbException.convert(e);
    }
  }
  
  private static void javacProcess(File javaFile)
  {
    exec(new String[] { "javac", "-sourcepath", COMPILE_DIR, "-d", COMPILE_DIR, "-encoding", "UTF-8", javaFile.getAbsolutePath() });
  }
  
  private static int exec(String... args)
  {
    ByteArrayOutputStream buff = new ByteArrayOutputStream();
    try
    {
      ProcessBuilder builder = new ProcessBuilder(new String[0]);
      
      builder.environment().remove("JAVA_TOOL_OPTIONS");
      builder.command(args);
      
      Process p = builder.start();
      copyInThread(p.getInputStream(), buff);
      copyInThread(p.getErrorStream(), buff);
      p.waitFor();
      String err = new String(buff.toByteArray(), Constants.UTF8);
      throwSyntaxError(err);
      return p.exitValue();
    }
    catch (Exception e)
    {
      throw DbException.convert(e);
    }
  }
  
  private static void copyInThread(InputStream in, final OutputStream out)
  {
    new Task()
    {
      public void call()
        throws IOException
      {
        IOUtils.copy(this.val$in, out);
      }
    }.execute();
  }
  
  private static void javacSun(File javaFile)
  {
    PrintStream old = System.err;
    ByteArrayOutputStream buff = new ByteArrayOutputStream();
    PrintStream temp = new PrintStream(buff);
    try
    {
      System.setErr(temp);
      
      Method compile = JAVAC_SUN.getMethod("compile", new Class[] { String[].class });
      Object javac = JAVAC_SUN.newInstance();
      compile.invoke(javac, new Object[] { { "-sourcepath", COMPILE_DIR, "-d", COMPILE_DIR, "-encoding", "UTF-8", javaFile.getAbsolutePath() } });
      
      String err = new String(buff.toByteArray(), Constants.UTF8);
      throwSyntaxError(err);
    }
    catch (Exception e)
    {
      throw DbException.convert(e);
    }
    finally
    {
      System.setErr(old);
    }
  }
  
  private static void throwSyntaxError(String err)
  {
    if (!err.startsWith("Note:")) {
      if (err.length() > 0)
      {
        err = StringUtils.replaceAll(err, COMPILE_DIR, "");
        throw DbException.get(42000, err);
      }
    }
  }
  
  private static final class GroovyCompiler
  {
    private static final Object LOADER;
    private static final Throwable INIT_FAIL_EXCEPTION;
    
    static
    {
      Object loader = null;
      Throwable initFailException = null;
      try
      {
        Class<?> importCustomizerClass = Class.forName("org.codehaus.groovy.control.customizers.ImportCustomizer");
        
        Object importCustomizer = Utils.newInstance("org.codehaus.groovy.control.customizers.ImportCustomizer", new Object[0]);
        
        String[] importsArray = { "java.sql.Connection", "java.sql.Types", "java.sql.ResultSet", "groovy.sql.Sql", "org.h2.tools.SimpleResultSet" };
        
        Utils.callMethod(importCustomizer, "addImports", new Object[] { importsArray });
        
        Object importCustomizerArray = Array.newInstance(importCustomizerClass, 1);
        Array.set(importCustomizerArray, 0, importCustomizer);
        Object configuration = Utils.newInstance("org.codehaus.groovy.control.CompilerConfiguration", new Object[0]);
        
        Utils.callMethod(configuration, "addCompilationCustomizers", new Object[] { importCustomizerArray });
        
        ClassLoader parent = GroovyCompiler.class.getClassLoader();
        loader = Utils.newInstance("groovy.lang.GroovyClassLoader", new Object[] { parent, configuration });
      }
      catch (Exception ex)
      {
        initFailException = ex;
      }
      LOADER = loader;
      INIT_FAIL_EXCEPTION = initFailException;
    }
    
    public static Class<?> parseClass(String source, String packageAndClassName)
    {
      if (LOADER == null) {
        throw new RuntimeException("Compile fail: no Groovy jar in the classpath", INIT_FAIL_EXCEPTION);
      }
      try
      {
        Object codeSource = Utils.newInstance("groovy.lang.GroovyCodeSource", new Object[] { source, packageAndClassName + ".groovy", "UTF-8" });
        
        Utils.callMethod(codeSource, "setCachable", new Object[] { Boolean.valueOf(false) });
        return (Class)Utils.callMethod(LOADER, "parseClass", new Object[] { codeSource });
      }
      catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    }
  }
  
  static class StringJavaFileObject
    extends SimpleJavaFileObject
  {
    private final String sourceCode;
    
    public StringJavaFileObject(String className, String sourceCode)
    {
      super(JavaFileObject.Kind.SOURCE);
      
      this.sourceCode = sourceCode;
    }
    
    public CharSequence getCharContent(boolean ignoreEncodingErrors)
    {
      return this.sourceCode;
    }
  }
  
  static class JavaClassObject
    extends SimpleJavaFileObject
  {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    
    public JavaClassObject(String name, JavaFileObject.Kind kind)
    {
      super(kind);
    }
    
    public byte[] getBytes()
    {
      return this.out.toByteArray();
    }
    
    public OutputStream openOutputStream()
      throws IOException
    {
      return this.out;
    }
  }
  
  static class ClassFileManager
    extends ForwardingJavaFileManager<StandardJavaFileManager>
  {
    SourceCompiler.JavaClassObject classObject;
    
    public ClassFileManager(StandardJavaFileManager standardManager)
    {
      super();
    }
    
    public ClassLoader getClassLoader(JavaFileManager.Location location)
    {
      new SecureClassLoader()
      {
        protected Class<?> findClass(String name)
          throws ClassNotFoundException
        {
          byte[] bytes = SourceCompiler.ClassFileManager.this.classObject.getBytes();
          return super.defineClass(name, bytes, 0, bytes.length);
        }
      };
    }
    
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind, FileObject sibling)
      throws IOException
    {
      this.classObject = new SourceCompiler.JavaClassObject(className, kind);
      return this.classObject;
    }
  }
}
