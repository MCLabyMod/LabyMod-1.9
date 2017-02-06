import com.google.common.collect.Lists;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class b
{
  private static final Logger a = ;
  private final String b;
  private final Throwable c;
  private final c d = new c(this, "System Details");
  private final List<c> e = Lists.newArrayList();
  private File f;
  private boolean g = true;
  private StackTraceElement[] h = new StackTraceElement[0];
  private boolean reported = false;
  
  public b(String descriptionIn, Throwable causeThrowable)
  {
    this.b = descriptionIn;
    this.c = causeThrowable;
    h();
  }
  
  private void h()
  {
    this.d.a("Minecraft Version", new Callable()
    {
      public String a()
      {
        return "1.9";
      }
    });
    this.d.a("Operating System", new Callable()
    {
      public String a()
      {
        return System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
      }
    });
    this.d.a("Java Version", new Callable()
    {
      public String a()
      {
        return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
      }
    });
    this.d.a("Java VM Version", new Callable()
    {
      public String a()
      {
        return System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
      }
    });
    this.d.a("Memory", new Callable()
    {
      public String a()
      {
        Runtime runtime = Runtime.getRuntime();
        long i = runtime.maxMemory();
        long j = runtime.totalMemory();
        long k = runtime.freeMemory();
        long l = i / 1024L / 1024L;
        long i1 = j / 1024L / 1024L;
        long j1 = k / 1024L / 1024L;
        return k + " bytes (" + j1 + " MB) / " + j + " bytes (" + i1 + " MB) up to " + i + " bytes (" + l + " MB)";
      }
    });
    this.d.a("JVM Flags", new Callable()
    {
      public String a()
      {
        RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
        List<String> list = runtimemxbean.getInputArguments();
        int i = 0;
        StringBuilder stringbuilder = new StringBuilder();
        for (String s : list) {
          if (s.startsWith("-X"))
          {
            if (i++ > 0) {
              stringbuilder.append(" ");
            }
            stringbuilder.append(s);
          }
        }
        return String.format("%d total; %s", new Object[] { Integer.valueOf(i), stringbuilder.toString() });
      }
    });
    this.d.a("IntCache", new Callable()
    {
      public String a()
        throws Exception
      {
        return axt.b();
      }
    });
    if (Reflector.FMLCommonHandler_enhanceCrashReport.exists())
    {
      Object instance = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
      Reflector.callString(instance, Reflector.FMLCommonHandler_enhanceCrashReport, new Object[] { this, this.d });
    }
  }
  
  public String a()
  {
    return this.b;
  }
  
  public Throwable b()
  {
    return this.c;
  }
  
  public void a(StringBuilder builder)
  {
    if (((this.h == null) || (this.h.length <= 0)) && (!this.e.isEmpty())) {
      this.h = ((StackTraceElement[])ArrayUtils.subarray(((c)this.e.get(0)).a(), 0, 1));
    }
    if ((this.h != null) && (this.h.length > 0))
    {
      builder.append("-- Head --\n");
      builder.append("Stacktrace:\n");
      for (StackTraceElement stacktraceelement : this.h)
      {
        builder.append("\t").append("at ").append(stacktraceelement.toString());
        builder.append("\n");
      }
      builder.append("\n");
    }
    for (c crashreportcategory : this.e)
    {
      crashreportcategory.a(builder);
      builder.append("\n\n");
    }
    this.d.a(builder);
  }
  
  public String d()
  {
    StringWriter stringwriter = null;
    PrintWriter printwriter = null;
    Throwable throwable = this.c;
    if (throwable.getMessage() == null)
    {
      if ((throwable instanceof NullPointerException)) {
        throwable = new NullPointerException(this.b);
      } else if ((throwable instanceof StackOverflowError)) {
        throwable = new StackOverflowError(this.b);
      } else if ((throwable instanceof OutOfMemoryError)) {
        throwable = new OutOfMemoryError(this.b);
      }
      throwable.setStackTrace(this.c.getStackTrace());
    }
    String s = throwable.toString();
    try
    {
      stringwriter = new StringWriter();
      printwriter = new PrintWriter(stringwriter);
      throwable.printStackTrace(printwriter);
      s = stringwriter.toString();
    }
    finally
    {
      IOUtils.closeQuietly(stringwriter);
      IOUtils.closeQuietly(printwriter);
    }
    return s;
  }
  
  public String e()
  {
    if (!this.reported)
    {
      this.reported = true;
      CrashReporter.onCrashReport(this, this.d);
    }
    StringBuilder stringbuilder = new StringBuilder();
    stringbuilder.append("---- Minecraft Crash Report ----\n");
    
    Reflector.call(Reflector.BlamingTransformer_onCrash, new Object[] { stringbuilder });
    Reflector.call(Reflector.CoreModManager_onCrash, new Object[] { stringbuilder });
    
    stringbuilder.append("// ");
    stringbuilder.append(i());
    stringbuilder.append("\n\n");
    stringbuilder.append("Time: ");
    stringbuilder.append(new SimpleDateFormat().format(new Date()));
    stringbuilder.append("\n");
    stringbuilder.append("Description: ");
    stringbuilder.append(this.b);
    stringbuilder.append("\n\n");
    stringbuilder.append(d());
    stringbuilder.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");
    for (int i = 0; i < 87; i++) {
      stringbuilder.append("-");
    }
    stringbuilder.append("\n\n");
    a(stringbuilder);
    return stringbuilder.toString();
  }
  
  public File f()
  {
    return this.f;
  }
  
  public boolean a(File toFile)
  {
    if (this.f != null) {
      return false;
    }
    if (toFile.getParentFile() != null) {
      toFile.getParentFile().mkdirs();
    }
    try
    {
      FileWriter filewriter = new FileWriter(toFile);
      filewriter.write(e());
      filewriter.close();
      this.f = toFile;
      return true;
    }
    catch (Throwable throwable)
    {
      a.error("Could not save crash report to " + toFile, throwable);
    }
    return false;
  }
  
  public c g()
  {
    return this.d;
  }
  
  public c a(String name)
  {
    return a(name, 1);
  }
  
  public c a(String categoryName, int stacktraceLength)
  {
    c crashreportcategory = new c(this, categoryName);
    if (this.g)
    {
      int i = crashreportcategory.a(stacktraceLength);
      StackTraceElement[] astacktraceelement = this.c.getStackTrace();
      StackTraceElement stacktraceelement = null;
      StackTraceElement stacktraceelement1 = null;
      int j = astacktraceelement.length - i;
      if (j < 0) {
        System.out.println("Negative index in crash report handler (" + astacktraceelement.length + "/" + i + ")");
      }
      if ((astacktraceelement != null) && (0 <= j) && (j < astacktraceelement.length))
      {
        stacktraceelement = astacktraceelement[j];
        if (astacktraceelement.length + 1 - i < astacktraceelement.length) {
          stacktraceelement1 = astacktraceelement[(astacktraceelement.length + 1 - i)];
        }
      }
      this.g = crashreportcategory.a(stacktraceelement, stacktraceelement1);
      if ((i > 0) && (!this.e.isEmpty()))
      {
        c crashreportcategory1 = (c)this.e.get(this.e.size() - 1);
        crashreportcategory1.b(i);
      }
      else if ((astacktraceelement != null) && (astacktraceelement.length >= i) && (0 <= j) && (j < astacktraceelement.length))
      {
        this.h = new StackTraceElement[j];
        System.arraycopy(astacktraceelement, 0, this.h, 0, this.h.length);
      }
      else
      {
        this.g = false;
      }
    }
    this.e.add(crashreportcategory);
    return crashreportcategory;
  }
  
  private static String i()
  {
    String[] astring = { "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!", "But it works on my machine." };
    try
    {
      return astring[((int)(System.nanoTime() % astring.length))];
    }
    catch (Throwable var2) {}
    return "Witty comment unavailable :(";
  }
  
  public static b a(Throwable causeIn, String descriptionIn)
  {
    b crashreport;
    b crashreport;
    if ((causeIn instanceof e)) {
      crashreport = ((e)causeIn).a();
    } else {
      crashreport = new b(descriptionIn, causeIn);
    }
    return crashreport;
  }
}
