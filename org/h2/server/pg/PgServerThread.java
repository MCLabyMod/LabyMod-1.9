package org.h2.server.pg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import org.h2.engine.SysProperties;
import org.h2.jdbc.JdbcPreparedStatement;
import org.h2.jdbc.JdbcStatement;
import org.h2.message.DbException;
import org.h2.mvstore.DataUtils;
import org.h2.util.IOUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.MathUtils;
import org.h2.util.ScriptReader;
import org.h2.util.StringUtils;
import org.h2.util.Utils;
import org.h2.value.CaseInsensitiveMap;

public class PgServerThread
  implements Runnable
{
  private final PgServer server;
  private Socket socket;
  private Connection conn;
  private boolean stop;
  private DataInputStream dataInRaw;
  private DataInputStream dataIn;
  private OutputStream out;
  private int messageType;
  private ByteArrayOutputStream outBuffer;
  private DataOutputStream dataOut;
  private Thread thread;
  private boolean initDone;
  private String userName;
  private String databaseName;
  private int processId;
  private int secret;
  private JdbcStatement activeRequest;
  private String clientEncoding = SysProperties.PG_DEFAULT_CLIENT_ENCODING;
  private String dateStyle = "ISO";
  private final HashMap<String, Prepared> prepared = new CaseInsensitiveMap();
  private final HashMap<String, Portal> portals = new CaseInsensitiveMap();
  
  PgServerThread(Socket socket, PgServer server)
  {
    this.server = server;
    this.socket = socket;
    this.secret = ((int)MathUtils.secureRandomLong());
  }
  
  public void run()
  {
    try
    {
      this.server.trace("Connect");
      InputStream ins = this.socket.getInputStream();
      this.out = this.socket.getOutputStream();
      this.dataInRaw = new DataInputStream(ins);
      while (!this.stop)
      {
        process();
        this.out.flush();
      }
    }
    catch (EOFException e) {}catch (Exception e)
    {
      this.server.traceError(e);
    }
    finally
    {
      this.server.trace("Disconnect");
      close();
    }
  }
  
  private String readString()
    throws IOException
  {
    ByteArrayOutputStream buff = new ByteArrayOutputStream();
    for (;;)
    {
      int x = this.dataIn.read();
      if (x <= 0) {
        break;
      }
      buff.write(x);
    }
    return new String(buff.toByteArray(), getEncoding());
  }
  
  private int readInt()
    throws IOException
  {
    return this.dataIn.readInt();
  }
  
  private short readShort()
    throws IOException
  {
    return this.dataIn.readShort();
  }
  
  private byte readByte()
    throws IOException
  {
    return this.dataIn.readByte();
  }
  
  private void readFully(byte[] buff)
    throws IOException
  {
    this.dataIn.readFully(buff);
  }
  
  /* Error */
  private void process()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 197	org/h2/server/pg/PgServerThread:initDone	Z
    //   4: ifeq +21 -> 25
    //   7: aload_0
    //   8: getfield 118	org/h2/server/pg/PgServerThread:dataInRaw	Ljava/io/DataInputStream;
    //   11: invokevirtual 159	java/io/DataInputStream:read	()I
    //   14: istore_1
    //   15: iload_1
    //   16: ifge +11 -> 27
    //   19: aload_0
    //   20: iconst_1
    //   21: putfield 122	org/h2/server/pg/PgServerThread:stop	Z
    //   24: return
    //   25: iconst_0
    //   26: istore_1
    //   27: aload_0
    //   28: getfield 118	org/h2/server/pg/PgServerThread:dataInRaw	Ljava/io/DataInputStream;
    //   31: invokevirtual 180	java/io/DataInputStream:readInt	()I
    //   34: istore_2
    //   35: iinc 2 -4
    //   38: iload_2
    //   39: invokestatic 203	org/h2/mvstore/DataUtils:newBytes	(I)[B
    //   42: astore_3
    //   43: aload_0
    //   44: getfield 118	org/h2/server/pg/PgServerThread:dataInRaw	Ljava/io/DataInputStream;
    //   47: aload_3
    //   48: iconst_0
    //   49: iload_2
    //   50: invokevirtual 206	java/io/DataInputStream:readFully	([BII)V
    //   53: aload_0
    //   54: new 113	java/io/DataInputStream
    //   57: dup
    //   58: new 208	java/io/ByteArrayInputStream
    //   61: dup
    //   62: aload_3
    //   63: iconst_0
    //   64: iload_2
    //   65: invokespecial 210	java/io/ByteArrayInputStream:<init>	([BII)V
    //   68: invokespecial 116	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   71: putfield 155	org/h2/server/pg/PgServerThread:dataIn	Ljava/io/DataInputStream;
    //   74: iload_1
    //   75: lookupswitch	default:+1914->1989, 0:+89->164, 66:+810->885, 67:+1039->1114, 68:+1164->1239, 69:+1381->1456, 80:+658->733, 81:+1628->1703, 83:+1611->1686, 88:+1897->1972, 112:+467->542
    //   164: aload_0
    //   165: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   168: ldc -43
    //   170: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   173: aload_0
    //   174: invokespecial 214	org/h2/server/pg/PgServerThread:readInt	()I
    //   177: istore 4
    //   179: iload 4
    //   181: ldc -41
    //   183: if_icmpne +102 -> 285
    //   186: aload_0
    //   187: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   190: ldc -39
    //   192: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   195: aload_0
    //   196: invokespecial 214	org/h2/server/pg/PgServerThread:readInt	()I
    //   199: istore 5
    //   201: aload_0
    //   202: invokespecial 214	org/h2/server/pg/PgServerThread:readInt	()I
    //   205: istore 6
    //   207: aload_0
    //   208: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   211: iload 5
    //   213: invokevirtual 221	org/h2/server/pg/PgServer:getThread	(I)Lorg/h2/server/pg/PgServerThread;
    //   216: astore 7
    //   218: aload 7
    //   220: ifnull +21 -> 241
    //   223: iload 6
    //   225: aload 7
    //   227: getfield 84	org/h2/server/pg/PgServerThread:secret	I
    //   230: if_icmpne +11 -> 241
    //   233: aload 7
    //   235: invokespecial 224	org/h2/server/pg/PgServerThread:cancelRequest	()V
    //   238: goto +40 -> 278
    //   241: aload_0
    //   242: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   245: new 226	java/lang/StringBuilder
    //   248: dup
    //   249: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   252: ldc -27
    //   254: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: iload 5
    //   259: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   262: ldc -18
    //   264: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: iload 6
    //   269: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   272: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   275: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   278: aload_0
    //   279: invokevirtual 135	org/h2/server/pg/PgServerThread:close	()V
    //   282: goto +1749 -> 2031
    //   285: iload 4
    //   287: ldc -14
    //   289: if_icmpne +24 -> 313
    //   292: aload_0
    //   293: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   296: ldc -12
    //   298: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   301: aload_0
    //   302: getfield 111	org/h2/server/pg/PgServerThread:out	Ljava/io/OutputStream;
    //   305: bipush 78
    //   307: invokevirtual 245	java/io/OutputStream:write	(I)V
    //   310: goto +1721 -> 2031
    //   313: aload_0
    //   314: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   317: ldc -9
    //   319: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   322: aload_0
    //   323: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   326: new 226	java/lang/StringBuilder
    //   329: dup
    //   330: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   333: ldc -7
    //   335: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: iload 4
    //   340: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   343: ldc -5
    //   345: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: iload 4
    //   350: bipush 16
    //   352: ishr
    //   353: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   356: ldc -3
    //   358: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: iload 4
    //   363: sipush 255
    //   366: iand
    //   367: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   370: ldc -1
    //   372: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   378: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   381: aload_0
    //   382: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   385: astore 5
    //   387: aload 5
    //   389: invokevirtual 260	java/lang/String:length	()I
    //   392: ifne +6 -> 398
    //   395: goto +135 -> 530
    //   398: aload_0
    //   399: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   402: astore 6
    //   404: ldc_w 262
    //   407: aload 5
    //   409: invokevirtual 266	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   412: ifeq +12 -> 424
    //   415: aload_0
    //   416: aload 6
    //   418: putfield 268	org/h2/server/pg/PgServerThread:userName	Ljava/lang/String;
    //   421: goto +67 -> 488
    //   424: ldc_w 270
    //   427: aload 5
    //   429: invokevirtual 266	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   432: ifeq +19 -> 451
    //   435: aload_0
    //   436: aload_0
    //   437: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   440: aload 6
    //   442: invokevirtual 274	org/h2/server/pg/PgServer:checkKeyAndGetDatabaseName	(Ljava/lang/String;)Ljava/lang/String;
    //   445: putfield 276	org/h2/server/pg/PgServerThread:databaseName	Ljava/lang/String;
    //   448: goto +40 -> 488
    //   451: ldc_w 278
    //   454: aload 5
    //   456: invokevirtual 266	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   459: ifeq +12 -> 471
    //   462: aload_0
    //   463: aload 6
    //   465: putfield 61	org/h2/server/pg/PgServerThread:clientEncoding	Ljava/lang/String;
    //   468: goto +20 -> 488
    //   471: ldc_w 280
    //   474: aload 5
    //   476: invokevirtual 266	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   479: ifeq +9 -> 488
    //   482: aload_0
    //   483: aload 6
    //   485: putfield 65	org/h2/server/pg/PgServerThread:dateStyle	Ljava/lang/String;
    //   488: aload_0
    //   489: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   492: new 226	java/lang/StringBuilder
    //   495: dup
    //   496: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   499: ldc_w 282
    //   502: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   505: aload 5
    //   507: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: ldc_w 284
    //   513: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   516: aload 6
    //   518: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   524: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   527: goto -146 -> 381
    //   530: aload_0
    //   531: invokespecial 287	org/h2/server/pg/PgServerThread:sendAuthenticationCleartextPassword	()V
    //   534: aload_0
    //   535: iconst_1
    //   536: putfield 197	org/h2/server/pg/PgServerThread:initDone	Z
    //   539: goto +1492 -> 2031
    //   542: aload_0
    //   543: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   546: ldc_w 289
    //   549: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   552: aload_0
    //   553: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   556: astore 5
    //   558: new 291	java/util/Properties
    //   561: dup
    //   562: invokespecial 292	java/util/Properties:<init>	()V
    //   565: astore 6
    //   567: aload 6
    //   569: ldc_w 294
    //   572: ldc_w 296
    //   575: invokevirtual 300	java/util/Properties:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   578: pop
    //   579: aload 6
    //   581: ldc_w 302
    //   584: aload_0
    //   585: getfield 268	org/h2/server/pg/PgServerThread:userName	Ljava/lang/String;
    //   588: invokevirtual 300	java/util/Properties:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   591: pop
    //   592: aload 6
    //   594: ldc_w 304
    //   597: aload 5
    //   599: invokevirtual 300	java/util/Properties:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   602: pop
    //   603: new 226	java/lang/StringBuilder
    //   606: dup
    //   607: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   610: ldc_w 306
    //   613: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   616: aload_0
    //   617: getfield 276	org/h2/server/pg/PgServerThread:databaseName	Ljava/lang/String;
    //   620: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   626: astore 7
    //   628: new 308	org/h2/engine/ConnectionInfo
    //   631: dup
    //   632: aload 7
    //   634: aload 6
    //   636: invokespecial 311	org/h2/engine/ConnectionInfo:<init>	(Ljava/lang/String;Ljava/util/Properties;)V
    //   639: astore 8
    //   641: aload_0
    //   642: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   645: invokevirtual 314	org/h2/server/pg/PgServer:getBaseDir	()Ljava/lang/String;
    //   648: astore 9
    //   650: aload 9
    //   652: ifnonnull +8 -> 660
    //   655: invokestatic 315	org/h2/engine/SysProperties:getBaseDir	()Ljava/lang/String;
    //   658: astore 9
    //   660: aload 9
    //   662: ifnull +10 -> 672
    //   665: aload 8
    //   667: aload 9
    //   669: invokevirtual 318	org/h2/engine/ConnectionInfo:setBaseDir	(Ljava/lang/String;)V
    //   672: aload_0
    //   673: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   676: invokevirtual 322	org/h2/server/pg/PgServer:getIfExists	()Z
    //   679: ifeq +14 -> 693
    //   682: aload 8
    //   684: ldc_w 324
    //   687: ldc_w 326
    //   690: invokevirtual 330	org/h2/engine/ConnectionInfo:setProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   693: aload_0
    //   694: new 332	org/h2/jdbc/JdbcConnection
    //   697: dup
    //   698: aload 8
    //   700: iconst_0
    //   701: invokespecial 335	org/h2/jdbc/JdbcConnection:<init>	(Lorg/h2/engine/ConnectionInfo;Z)V
    //   704: putfield 337	org/h2/server/pg/PgServerThread:conn	Ljava/sql/Connection;
    //   707: aload_0
    //   708: invokespecial 340	org/h2/server/pg/PgServerThread:initDb	()V
    //   711: aload_0
    //   712: invokespecial 343	org/h2/server/pg/PgServerThread:sendAuthenticationOk	()V
    //   715: goto +1316 -> 2031
    //   718: astore 6
    //   720: aload 6
    //   722: invokevirtual 346	java/lang/Exception:printStackTrace	()V
    //   725: aload_0
    //   726: iconst_1
    //   727: putfield 122	org/h2/server/pg/PgServerThread:stop	Z
    //   730: goto +1301 -> 2031
    //   733: aload_0
    //   734: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   737: ldc_w 348
    //   740: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   743: new 12	org/h2/server/pg/PgServerThread$Prepared
    //   746: dup
    //   747: invokespecial 349	org/h2/server/pg/PgServerThread$Prepared:<init>	()V
    //   750: astore 5
    //   752: aload 5
    //   754: aload_0
    //   755: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   758: putfield 352	org/h2/server/pg/PgServerThread$Prepared:name	Ljava/lang/String;
    //   761: aload 5
    //   763: aload_0
    //   764: aload_0
    //   765: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   768: invokespecial 355	org/h2/server/pg/PgServerThread:getSQL	(Ljava/lang/String;)Ljava/lang/String;
    //   771: putfield 358	org/h2/server/pg/PgServerThread$Prepared:sql	Ljava/lang/String;
    //   774: aload_0
    //   775: invokespecial 359	org/h2/server/pg/PgServerThread:readShort	()S
    //   778: istore 6
    //   780: aload 5
    //   782: iload 6
    //   784: newarray <illegal type>
    //   786: putfield 363	org/h2/server/pg/PgServerThread$Prepared:paramType	[I
    //   789: iconst_0
    //   790: istore 7
    //   792: iload 7
    //   794: iload 6
    //   796: if_icmpge +34 -> 830
    //   799: aload_0
    //   800: invokespecial 214	org/h2/server/pg/PgServerThread:readInt	()I
    //   803: istore 8
    //   805: aload_0
    //   806: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   809: iload 8
    //   811: invokevirtual 366	org/h2/server/pg/PgServer:checkType	(I)V
    //   814: aload 5
    //   816: getfield 363	org/h2/server/pg/PgServerThread$Prepared:paramType	[I
    //   819: iload 7
    //   821: iload 8
    //   823: iastore
    //   824: iinc 7 1
    //   827: goto -35 -> 792
    //   830: aload 5
    //   832: aload_0
    //   833: getfield 337	org/h2/server/pg/PgServerThread:conn	Ljava/sql/Connection;
    //   836: aload 5
    //   838: getfield 358	org/h2/server/pg/PgServerThread$Prepared:sql	Ljava/lang/String;
    //   841: invokeinterface 372 2 0
    //   846: checkcast 374	org/h2/jdbc/JdbcPreparedStatement
    //   849: putfield 378	org/h2/server/pg/PgServerThread$Prepared:prep	Lorg/h2/jdbc/JdbcPreparedStatement;
    //   852: aload_0
    //   853: getfield 70	org/h2/server/pg/PgServerThread:prepared	Ljava/util/HashMap;
    //   856: aload 5
    //   858: getfield 352	org/h2/server/pg/PgServerThread$Prepared:name	Ljava/lang/String;
    //   861: aload 5
    //   863: invokevirtual 381	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   866: pop
    //   867: aload_0
    //   868: invokespecial 384	org/h2/server/pg/PgServerThread:sendParseComplete	()V
    //   871: goto +1160 -> 2031
    //   874: astore 7
    //   876: aload_0
    //   877: aload 7
    //   879: invokespecial 387	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/Exception;)V
    //   882: goto +1149 -> 2031
    //   885: aload_0
    //   886: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   889: ldc_w 389
    //   892: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   895: new 9	org/h2/server/pg/PgServerThread$Portal
    //   898: dup
    //   899: invokespecial 390	org/h2/server/pg/PgServerThread$Portal:<init>	()V
    //   902: astore 5
    //   904: aload 5
    //   906: aload_0
    //   907: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   910: putfield 391	org/h2/server/pg/PgServerThread$Portal:name	Ljava/lang/String;
    //   913: aload_0
    //   914: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   917: astore 6
    //   919: aload_0
    //   920: getfield 70	org/h2/server/pg/PgServerThread:prepared	Ljava/util/HashMap;
    //   923: aload 6
    //   925: invokevirtual 395	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   928: checkcast 12	org/h2/server/pg/PgServerThread$Prepared
    //   931: astore 7
    //   933: aload 7
    //   935: ifnonnull +13 -> 948
    //   938: aload_0
    //   939: ldc_w 397
    //   942: invokespecial 399	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/String;)V
    //   945: goto +1086 -> 2031
    //   948: aload 5
    //   950: aload 7
    //   952: putfield 402	org/h2/server/pg/PgServerThread$Portal:prep	Lorg/h2/server/pg/PgServerThread$Prepared;
    //   955: aload_0
    //   956: getfield 72	org/h2/server/pg/PgServerThread:portals	Ljava/util/HashMap;
    //   959: aload 5
    //   961: getfield 391	org/h2/server/pg/PgServerThread$Portal:name	Ljava/lang/String;
    //   964: aload 5
    //   966: invokevirtual 381	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   969: pop
    //   970: aload_0
    //   971: invokespecial 359	org/h2/server/pg/PgServerThread:readShort	()S
    //   974: istore 8
    //   976: iload 8
    //   978: newarray <illegal type>
    //   980: astore 9
    //   982: iconst_0
    //   983: istore 10
    //   985: iload 10
    //   987: iload 8
    //   989: if_icmpge +18 -> 1007
    //   992: aload 9
    //   994: iload 10
    //   996: aload_0
    //   997: invokespecial 359	org/h2/server/pg/PgServerThread:readShort	()S
    //   1000: iastore
    //   1001: iinc 10 1
    //   1004: goto -19 -> 985
    //   1007: aload_0
    //   1008: invokespecial 359	org/h2/server/pg/PgServerThread:readShort	()S
    //   1011: istore 10
    //   1013: iconst_0
    //   1014: istore 11
    //   1016: iload 11
    //   1018: iload 10
    //   1020: if_icmpge +30 -> 1050
    //   1023: aload_0
    //   1024: aload 7
    //   1026: getfield 378	org/h2/server/pg/PgServerThread$Prepared:prep	Lorg/h2/jdbc/JdbcPreparedStatement;
    //   1029: aload 7
    //   1031: getfield 363	org/h2/server/pg/PgServerThread$Prepared:paramType	[I
    //   1034: iload 11
    //   1036: iaload
    //   1037: iload 11
    //   1039: aload 9
    //   1041: invokespecial 407	org/h2/server/pg/PgServerThread:setParameter	(Ljava/sql/PreparedStatement;II[I)V
    //   1044: iinc 11 1
    //   1047: goto -31 -> 1016
    //   1050: goto +14 -> 1064
    //   1053: astore 11
    //   1055: aload_0
    //   1056: aload 11
    //   1058: invokespecial 387	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/Exception;)V
    //   1061: goto +970 -> 2031
    //   1064: aload_0
    //   1065: invokespecial 359	org/h2/server/pg/PgServerThread:readShort	()S
    //   1068: istore 11
    //   1070: aload 5
    //   1072: iload 11
    //   1074: newarray <illegal type>
    //   1076: putfield 410	org/h2/server/pg/PgServerThread$Portal:resultColumnFormat	[I
    //   1079: iconst_0
    //   1080: istore 12
    //   1082: iload 12
    //   1084: iload 11
    //   1086: if_icmpge +21 -> 1107
    //   1089: aload 5
    //   1091: getfield 410	org/h2/server/pg/PgServerThread$Portal:resultColumnFormat	[I
    //   1094: iload 12
    //   1096: aload_0
    //   1097: invokespecial 359	org/h2/server/pg/PgServerThread:readShort	()S
    //   1100: iastore
    //   1101: iinc 12 1
    //   1104: goto -22 -> 1082
    //   1107: aload_0
    //   1108: invokespecial 413	org/h2/server/pg/PgServerThread:sendBindComplete	()V
    //   1111: goto +920 -> 2031
    //   1114: aload_0
    //   1115: invokespecial 414	org/h2/server/pg/PgServerThread:readByte	()B
    //   1118: i2c
    //   1119: istore 5
    //   1121: aload_0
    //   1122: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   1125: astore 6
    //   1127: aload_0
    //   1128: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1131: ldc_w 416
    //   1134: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1137: iload 5
    //   1139: bipush 83
    //   1141: if_icmpne +33 -> 1174
    //   1144: aload_0
    //   1145: getfield 70	org/h2/server/pg/PgServerThread:prepared	Ljava/util/HashMap;
    //   1148: aload 6
    //   1150: invokevirtual 419	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1153: checkcast 12	org/h2/server/pg/PgServerThread$Prepared
    //   1156: astore 7
    //   1158: aload 7
    //   1160: ifnull +11 -> 1171
    //   1163: aload 7
    //   1165: getfield 378	org/h2/server/pg/PgServerThread$Prepared:prep	Lorg/h2/jdbc/JdbcPreparedStatement;
    //   1168: invokestatic 425	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   1171: goto +61 -> 1232
    //   1174: iload 5
    //   1176: bipush 80
    //   1178: if_icmpne +16 -> 1194
    //   1181: aload_0
    //   1182: getfield 72	org/h2/server/pg/PgServerThread:portals	Ljava/util/HashMap;
    //   1185: aload 6
    //   1187: invokevirtual 419	java/util/HashMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1190: pop
    //   1191: goto +41 -> 1232
    //   1194: aload_0
    //   1195: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1198: new 226	java/lang/StringBuilder
    //   1201: dup
    //   1202: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   1205: ldc_w 427
    //   1208: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1211: iload 5
    //   1213: invokevirtual 430	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   1216: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1219: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1222: aload_0
    //   1223: ldc_w 432
    //   1226: invokespecial 399	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/String;)V
    //   1229: goto +802 -> 2031
    //   1232: aload_0
    //   1233: invokespecial 435	org/h2/server/pg/PgServerThread:sendCloseComplete	()V
    //   1236: goto +795 -> 2031
    //   1239: aload_0
    //   1240: invokespecial 414	org/h2/server/pg/PgServerThread:readByte	()B
    //   1243: i2c
    //   1244: istore 5
    //   1246: aload_0
    //   1247: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   1250: astore 6
    //   1252: aload_0
    //   1253: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1256: ldc_w 437
    //   1259: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1262: iload 5
    //   1264: bipush 83
    //   1266: if_icmpne +59 -> 1325
    //   1269: aload_0
    //   1270: getfield 70	org/h2/server/pg/PgServerThread:prepared	Ljava/util/HashMap;
    //   1273: aload 6
    //   1275: invokevirtual 395	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1278: checkcast 12	org/h2/server/pg/PgServerThread$Prepared
    //   1281: astore 7
    //   1283: aload 7
    //   1285: ifnonnull +31 -> 1316
    //   1288: aload_0
    //   1289: new 226	java/lang/StringBuilder
    //   1292: dup
    //   1293: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   1296: ldc_w 439
    //   1299: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1302: aload 6
    //   1304: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1307: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1310: invokespecial 399	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/String;)V
    //   1313: goto +9 -> 1322
    //   1316: aload_0
    //   1317: aload 7
    //   1319: invokespecial 443	org/h2/server/pg/PgServerThread:sendParameterDescription	(Lorg/h2/server/pg/PgServerThread$Prepared;)V
    //   1322: goto +709 -> 2031
    //   1325: iload 5
    //   1327: bipush 80
    //   1329: if_icmpne +89 -> 1418
    //   1332: aload_0
    //   1333: getfield 72	org/h2/server/pg/PgServerThread:portals	Ljava/util/HashMap;
    //   1336: aload 6
    //   1338: invokevirtual 395	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1341: checkcast 9	org/h2/server/pg/PgServerThread$Portal
    //   1344: astore 7
    //   1346: aload 7
    //   1348: ifnonnull +31 -> 1379
    //   1351: aload_0
    //   1352: new 226	java/lang/StringBuilder
    //   1355: dup
    //   1356: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   1359: ldc_w 445
    //   1362: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1365: aload 6
    //   1367: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1370: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1373: invokespecial 399	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/String;)V
    //   1376: goto +39 -> 1415
    //   1379: aload 7
    //   1381: getfield 402	org/h2/server/pg/PgServerThread$Portal:prep	Lorg/h2/server/pg/PgServerThread$Prepared;
    //   1384: getfield 378	org/h2/server/pg/PgServerThread$Prepared:prep	Lorg/h2/jdbc/JdbcPreparedStatement;
    //   1387: astore 8
    //   1389: aload 8
    //   1391: invokeinterface 451 1 0
    //   1396: astore 9
    //   1398: aload_0
    //   1399: aload 9
    //   1401: invokespecial 455	org/h2/server/pg/PgServerThread:sendRowDescription	(Ljava/sql/ResultSetMetaData;)V
    //   1404: goto +11 -> 1415
    //   1407: astore 9
    //   1409: aload_0
    //   1410: aload 9
    //   1412: invokespecial 387	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/Exception;)V
    //   1415: goto +616 -> 2031
    //   1418: aload_0
    //   1419: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1422: new 226	java/lang/StringBuilder
    //   1425: dup
    //   1426: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   1429: ldc_w 427
    //   1432: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1435: iload 5
    //   1437: invokevirtual 430	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   1440: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1443: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1446: aload_0
    //   1447: ldc_w 432
    //   1450: invokespecial 399	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/String;)V
    //   1453: goto +578 -> 2031
    //   1456: aload_0
    //   1457: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   1460: astore 5
    //   1462: aload_0
    //   1463: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1466: ldc_w 457
    //   1469: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1472: aload_0
    //   1473: getfield 72	org/h2/server/pg/PgServerThread:portals	Ljava/util/HashMap;
    //   1476: aload 5
    //   1478: invokevirtual 395	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1481: checkcast 9	org/h2/server/pg/PgServerThread$Portal
    //   1484: astore 6
    //   1486: aload 6
    //   1488: ifnonnull +31 -> 1519
    //   1491: aload_0
    //   1492: new 226	java/lang/StringBuilder
    //   1495: dup
    //   1496: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   1499: ldc_w 445
    //   1502: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1505: aload 5
    //   1507: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1510: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1513: invokespecial 399	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/String;)V
    //   1516: goto +515 -> 2031
    //   1519: aload_0
    //   1520: invokespecial 359	org/h2/server/pg/PgServerThread:readShort	()S
    //   1523: istore 7
    //   1525: aload 6
    //   1527: getfield 402	org/h2/server/pg/PgServerThread$Portal:prep	Lorg/h2/server/pg/PgServerThread$Prepared;
    //   1530: astore 8
    //   1532: aload 8
    //   1534: getfield 378	org/h2/server/pg/PgServerThread$Prepared:prep	Lorg/h2/jdbc/JdbcPreparedStatement;
    //   1537: astore 9
    //   1539: aload_0
    //   1540: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1543: aload 8
    //   1545: getfield 358	org/h2/server/pg/PgServerThread$Prepared:sql	Ljava/lang/String;
    //   1548: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1551: aload 9
    //   1553: iload 7
    //   1555: invokevirtual 460	org/h2/jdbc/JdbcPreparedStatement:setMaxRows	(I)V
    //   1558: aload_0
    //   1559: aload 9
    //   1561: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1564: aload 9
    //   1566: invokevirtual 467	org/h2/jdbc/JdbcPreparedStatement:execute	()Z
    //   1569: istore 10
    //   1571: iload 10
    //   1573: ifeq +50 -> 1623
    //   1576: aload 9
    //   1578: invokevirtual 471	org/h2/jdbc/JdbcPreparedStatement:getResultSet	()Ljava/sql/ResultSet;
    //   1581: astore 11
    //   1583: aload 11
    //   1585: invokeinterface 476 1 0
    //   1590: ifeq +12 -> 1602
    //   1593: aload_0
    //   1594: aload 11
    //   1596: invokespecial 480	org/h2/server/pg/PgServerThread:sendDataRow	(Ljava/sql/ResultSet;)V
    //   1599: goto -16 -> 1583
    //   1602: aload_0
    //   1603: aload 9
    //   1605: iconst_0
    //   1606: invokespecial 484	org/h2/server/pg/PgServerThread:sendCommandComplete	(Lorg/h2/jdbc/JdbcStatement;I)V
    //   1609: goto +25 -> 1634
    //   1612: astore 11
    //   1614: aload_0
    //   1615: aload 11
    //   1617: invokespecial 387	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/Exception;)V
    //   1620: goto +14 -> 1634
    //   1623: aload_0
    //   1624: aload 9
    //   1626: aload 9
    //   1628: invokevirtual 487	org/h2/jdbc/JdbcPreparedStatement:getUpdateCount	()I
    //   1631: invokespecial 484	org/h2/server/pg/PgServerThread:sendCommandComplete	(Lorg/h2/jdbc/JdbcStatement;I)V
    //   1634: aload_0
    //   1635: aconst_null
    //   1636: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1639: goto +44 -> 1683
    //   1642: astore 10
    //   1644: aload 9
    //   1646: invokevirtual 490	org/h2/jdbc/JdbcPreparedStatement:wasCancelled	()Z
    //   1649: ifeq +10 -> 1659
    //   1652: aload_0
    //   1653: invokespecial 493	org/h2/server/pg/PgServerThread:sendCancelQueryResponse	()V
    //   1656: goto +9 -> 1665
    //   1659: aload_0
    //   1660: aload 10
    //   1662: invokespecial 387	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/Exception;)V
    //   1665: aload_0
    //   1666: aconst_null
    //   1667: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1670: goto +13 -> 1683
    //   1673: astore 13
    //   1675: aload_0
    //   1676: aconst_null
    //   1677: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1680: aload 13
    //   1682: athrow
    //   1683: goto +348 -> 2031
    //   1686: aload_0
    //   1687: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1690: ldc_w 495
    //   1693: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1696: aload_0
    //   1697: invokespecial 498	org/h2/server/pg/PgServerThread:sendReadyForQuery	()V
    //   1700: goto +331 -> 2031
    //   1703: aload_0
    //   1704: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1707: ldc_w 500
    //   1710: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1713: aload_0
    //   1714: invokespecial 257	org/h2/server/pg/PgServerThread:readString	()Ljava/lang/String;
    //   1717: astore 5
    //   1719: new 502	org/h2/util/ScriptReader
    //   1722: dup
    //   1723: new 504	java/io/StringReader
    //   1726: dup
    //   1727: aload 5
    //   1729: invokespecial 506	java/io/StringReader:<init>	(Ljava/lang/String;)V
    //   1732: invokespecial 509	org/h2/util/ScriptReader:<init>	(Ljava/io/Reader;)V
    //   1735: astore 6
    //   1737: aconst_null
    //   1738: astore 7
    //   1740: aload 6
    //   1742: invokevirtual 512	org/h2/util/ScriptReader:readStatement	()Ljava/lang/String;
    //   1745: astore 8
    //   1747: aload 8
    //   1749: ifnonnull +16 -> 1765
    //   1752: aload 7
    //   1754: invokestatic 425	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   1757: aload_0
    //   1758: aconst_null
    //   1759: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1762: goto +203 -> 1965
    //   1765: aload_0
    //   1766: aload 8
    //   1768: invokespecial 355	org/h2/server/pg/PgServerThread:getSQL	(Ljava/lang/String;)Ljava/lang/String;
    //   1771: astore 8
    //   1773: aload_0
    //   1774: getfield 337	org/h2/server/pg/PgServerThread:conn	Ljava/sql/Connection;
    //   1777: invokeinterface 518 1 0
    //   1782: checkcast 514	org/h2/jdbc/JdbcStatement
    //   1785: astore 7
    //   1787: aload_0
    //   1788: aload 7
    //   1790: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1793: aload 7
    //   1795: aload 8
    //   1797: invokevirtual 521	org/h2/jdbc/JdbcStatement:execute	(Ljava/lang/String;)Z
    //   1800: istore 9
    //   1802: iload 9
    //   1804: ifeq +78 -> 1882
    //   1807: aload 7
    //   1809: invokevirtual 522	org/h2/jdbc/JdbcStatement:getResultSet	()Ljava/sql/ResultSet;
    //   1812: astore 10
    //   1814: aload 10
    //   1816: invokeinterface 523 1 0
    //   1821: astore 11
    //   1823: aload_0
    //   1824: aload 11
    //   1826: invokespecial 455	org/h2/server/pg/PgServerThread:sendRowDescription	(Ljava/sql/ResultSetMetaData;)V
    //   1829: aload 10
    //   1831: invokeinterface 476 1 0
    //   1836: ifeq +12 -> 1848
    //   1839: aload_0
    //   1840: aload 10
    //   1842: invokespecial 480	org/h2/server/pg/PgServerThread:sendDataRow	(Ljava/sql/ResultSet;)V
    //   1845: goto -16 -> 1829
    //   1848: aload_0
    //   1849: aload 7
    //   1851: iconst_0
    //   1852: invokespecial 484	org/h2/server/pg/PgServerThread:sendCommandComplete	(Lorg/h2/jdbc/JdbcStatement;I)V
    //   1855: goto +24 -> 1879
    //   1858: astore 12
    //   1860: aload_0
    //   1861: aload 12
    //   1863: invokespecial 387	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/Exception;)V
    //   1866: aload 7
    //   1868: invokestatic 425	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   1871: aload_0
    //   1872: aconst_null
    //   1873: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1876: goto +89 -> 1965
    //   1879: goto +14 -> 1893
    //   1882: aload_0
    //   1883: aload 7
    //   1885: aload 7
    //   1887: invokevirtual 526	org/h2/jdbc/JdbcStatement:getUpdateCount	()I
    //   1890: invokespecial 484	org/h2/server/pg/PgServerThread:sendCommandComplete	(Lorg/h2/jdbc/JdbcStatement;I)V
    //   1893: aload 7
    //   1895: invokestatic 425	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   1898: aload_0
    //   1899: aconst_null
    //   1900: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1903: goto +59 -> 1962
    //   1906: astore 8
    //   1908: aload 7
    //   1910: ifnull +18 -> 1928
    //   1913: aload 7
    //   1915: invokevirtual 527	org/h2/jdbc/JdbcStatement:wasCancelled	()Z
    //   1918: ifeq +10 -> 1928
    //   1921: aload_0
    //   1922: invokespecial 493	org/h2/server/pg/PgServerThread:sendCancelQueryResponse	()V
    //   1925: goto +9 -> 1934
    //   1928: aload_0
    //   1929: aload 8
    //   1931: invokespecial 387	org/h2/server/pg/PgServerThread:sendErrorResponse	(Ljava/lang/Exception;)V
    //   1934: aload 7
    //   1936: invokestatic 425	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   1939: aload_0
    //   1940: aconst_null
    //   1941: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1944: goto +21 -> 1965
    //   1947: astore 14
    //   1949: aload 7
    //   1951: invokestatic 425	org/h2/util/JdbcUtils:closeSilently	(Ljava/sql/Statement;)V
    //   1954: aload_0
    //   1955: aconst_null
    //   1956: invokespecial 464	org/h2/server/pg/PgServerThread:setActiveRequest	(Lorg/h2/jdbc/JdbcStatement;)V
    //   1959: aload 14
    //   1961: athrow
    //   1962: goto -225 -> 1737
    //   1965: aload_0
    //   1966: invokespecial 498	org/h2/server/pg/PgServerThread:sendReadyForQuery	()V
    //   1969: goto +62 -> 2031
    //   1972: aload_0
    //   1973: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1976: ldc_w 529
    //   1979: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   1982: aload_0
    //   1983: invokevirtual 135	org/h2/server/pg/PgServerThread:close	()V
    //   1986: goto +45 -> 2031
    //   1989: aload_0
    //   1990: getfield 74	org/h2/server/pg/PgServerThread:server	Lorg/h2/server/pg/PgServer;
    //   1993: new 226	java/lang/StringBuilder
    //   1996: dup
    //   1997: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   2000: ldc_w 531
    //   2003: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2006: iload_1
    //   2007: invokevirtual 236	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2010: ldc -5
    //   2012: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2015: iload_1
    //   2016: i2c
    //   2017: invokevirtual 430	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   2020: ldc -1
    //   2022: invokevirtual 233	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2025: invokevirtual 241	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2028: invokevirtual 99	org/h2/server/pg/PgServer:trace	(Ljava/lang/String;)V
    //   2031: return
    // Line number table:
    //   Java source line #134	-> byte code offset #0
    //   Java source line #135	-> byte code offset #7
    //   Java source line #136	-> byte code offset #15
    //   Java source line #137	-> byte code offset #19
    //   Java source line #138	-> byte code offset #24
    //   Java source line #141	-> byte code offset #25
    //   Java source line #143	-> byte code offset #27
    //   Java source line #144	-> byte code offset #35
    //   Java source line #145	-> byte code offset #38
    //   Java source line #146	-> byte code offset #43
    //   Java source line #147	-> byte code offset #53
    //   Java source line #148	-> byte code offset #74
    //   Java source line #150	-> byte code offset #164
    //   Java source line #151	-> byte code offset #173
    //   Java source line #152	-> byte code offset #179
    //   Java source line #153	-> byte code offset #186
    //   Java source line #154	-> byte code offset #195
    //   Java source line #155	-> byte code offset #201
    //   Java source line #156	-> byte code offset #207
    //   Java source line #157	-> byte code offset #218
    //   Java source line #158	-> byte code offset #233
    //   Java source line #163	-> byte code offset #241
    //   Java source line #165	-> byte code offset #278
    //   Java source line #166	-> byte code offset #282
    //   Java source line #167	-> byte code offset #292
    //   Java source line #168	-> byte code offset #301
    //   Java source line #170	-> byte code offset #313
    //   Java source line #171	-> byte code offset #322
    //   Java source line #174	-> byte code offset #381
    //   Java source line #175	-> byte code offset #387
    //   Java source line #176	-> byte code offset #395
    //   Java source line #178	-> byte code offset #398
    //   Java source line #179	-> byte code offset #404
    //   Java source line #180	-> byte code offset #415
    //   Java source line #181	-> byte code offset #424
    //   Java source line #182	-> byte code offset #435
    //   Java source line #183	-> byte code offset #451
    //   Java source line #185	-> byte code offset #462
    //   Java source line #186	-> byte code offset #471
    //   Java source line #187	-> byte code offset #482
    //   Java source line #191	-> byte code offset #488
    //   Java source line #192	-> byte code offset #527
    //   Java source line #193	-> byte code offset #530
    //   Java source line #194	-> byte code offset #534
    //   Java source line #196	-> byte code offset #539
    //   Java source line #198	-> byte code offset #542
    //   Java source line #199	-> byte code offset #552
    //   Java source line #201	-> byte code offset #558
    //   Java source line #202	-> byte code offset #567
    //   Java source line #203	-> byte code offset #579
    //   Java source line #204	-> byte code offset #592
    //   Java source line #205	-> byte code offset #603
    //   Java source line #206	-> byte code offset #628
    //   Java source line #207	-> byte code offset #641
    //   Java source line #208	-> byte code offset #650
    //   Java source line #209	-> byte code offset #655
    //   Java source line #211	-> byte code offset #660
    //   Java source line #212	-> byte code offset #665
    //   Java source line #214	-> byte code offset #672
    //   Java source line #215	-> byte code offset #682
    //   Java source line #217	-> byte code offset #693
    //   Java source line #221	-> byte code offset #707
    //   Java source line #222	-> byte code offset #711
    //   Java source line #226	-> byte code offset #715
    //   Java source line #223	-> byte code offset #718
    //   Java source line #224	-> byte code offset #720
    //   Java source line #225	-> byte code offset #725
    //   Java source line #227	-> byte code offset #730
    //   Java source line #230	-> byte code offset #733
    //   Java source line #231	-> byte code offset #743
    //   Java source line #232	-> byte code offset #752
    //   Java source line #233	-> byte code offset #761
    //   Java source line #234	-> byte code offset #774
    //   Java source line #235	-> byte code offset #780
    //   Java source line #236	-> byte code offset #789
    //   Java source line #237	-> byte code offset #799
    //   Java source line #238	-> byte code offset #805
    //   Java source line #239	-> byte code offset #814
    //   Java source line #236	-> byte code offset #824
    //   Java source line #242	-> byte code offset #830
    //   Java source line #243	-> byte code offset #852
    //   Java source line #244	-> byte code offset #867
    //   Java source line #247	-> byte code offset #871
    //   Java source line #245	-> byte code offset #874
    //   Java source line #246	-> byte code offset #876
    //   Java source line #248	-> byte code offset #882
    //   Java source line #251	-> byte code offset #885
    //   Java source line #252	-> byte code offset #895
    //   Java source line #253	-> byte code offset #904
    //   Java source line #254	-> byte code offset #913
    //   Java source line #255	-> byte code offset #919
    //   Java source line #256	-> byte code offset #933
    //   Java source line #257	-> byte code offset #938
    //   Java source line #258	-> byte code offset #945
    //   Java source line #260	-> byte code offset #948
    //   Java source line #261	-> byte code offset #955
    //   Java source line #262	-> byte code offset #970
    //   Java source line #263	-> byte code offset #976
    //   Java source line #264	-> byte code offset #982
    //   Java source line #265	-> byte code offset #992
    //   Java source line #264	-> byte code offset #1001
    //   Java source line #267	-> byte code offset #1007
    //   Java source line #269	-> byte code offset #1013
    //   Java source line #270	-> byte code offset #1023
    //   Java source line #269	-> byte code offset #1044
    //   Java source line #275	-> byte code offset #1050
    //   Java source line #272	-> byte code offset #1053
    //   Java source line #273	-> byte code offset #1055
    //   Java source line #274	-> byte code offset #1061
    //   Java source line #276	-> byte code offset #1064
    //   Java source line #277	-> byte code offset #1070
    //   Java source line #278	-> byte code offset #1079
    //   Java source line #279	-> byte code offset #1089
    //   Java source line #278	-> byte code offset #1101
    //   Java source line #281	-> byte code offset #1107
    //   Java source line #282	-> byte code offset #1111
    //   Java source line #285	-> byte code offset #1114
    //   Java source line #286	-> byte code offset #1121
    //   Java source line #287	-> byte code offset #1127
    //   Java source line #288	-> byte code offset #1137
    //   Java source line #289	-> byte code offset #1144
    //   Java source line #290	-> byte code offset #1158
    //   Java source line #291	-> byte code offset #1163
    //   Java source line #293	-> byte code offset #1171
    //   Java source line #294	-> byte code offset #1181
    //   Java source line #296	-> byte code offset #1194
    //   Java source line #297	-> byte code offset #1222
    //   Java source line #298	-> byte code offset #1229
    //   Java source line #300	-> byte code offset #1232
    //   Java source line #301	-> byte code offset #1236
    //   Java source line #304	-> byte code offset #1239
    //   Java source line #305	-> byte code offset #1246
    //   Java source line #306	-> byte code offset #1252
    //   Java source line #307	-> byte code offset #1262
    //   Java source line #308	-> byte code offset #1269
    //   Java source line #309	-> byte code offset #1283
    //   Java source line #310	-> byte code offset #1288
    //   Java source line #312	-> byte code offset #1316
    //   Java source line #314	-> byte code offset #1322
    //   Java source line #315	-> byte code offset #1332
    //   Java source line #316	-> byte code offset #1346
    //   Java source line #317	-> byte code offset #1351
    //   Java source line #319	-> byte code offset #1379
    //   Java source line #321	-> byte code offset #1389
    //   Java source line #322	-> byte code offset #1398
    //   Java source line #325	-> byte code offset #1404
    //   Java source line #323	-> byte code offset #1407
    //   Java source line #324	-> byte code offset #1409
    //   Java source line #327	-> byte code offset #1415
    //   Java source line #328	-> byte code offset #1418
    //   Java source line #329	-> byte code offset #1446
    //   Java source line #331	-> byte code offset #1453
    //   Java source line #334	-> byte code offset #1456
    //   Java source line #335	-> byte code offset #1462
    //   Java source line #336	-> byte code offset #1472
    //   Java source line #337	-> byte code offset #1486
    //   Java source line #338	-> byte code offset #1491
    //   Java source line #339	-> byte code offset #1516
    //   Java source line #341	-> byte code offset #1519
    //   Java source line #342	-> byte code offset #1525
    //   Java source line #343	-> byte code offset #1532
    //   Java source line #344	-> byte code offset #1539
    //   Java source line #346	-> byte code offset #1551
    //   Java source line #347	-> byte code offset #1558
    //   Java source line #348	-> byte code offset #1564
    //   Java source line #349	-> byte code offset #1571
    //   Java source line #351	-> byte code offset #1576
    //   Java source line #353	-> byte code offset #1583
    //   Java source line #354	-> byte code offset #1593
    //   Java source line #356	-> byte code offset #1602
    //   Java source line #359	-> byte code offset #1609
    //   Java source line #357	-> byte code offset #1612
    //   Java source line #358	-> byte code offset #1614
    //   Java source line #359	-> byte code offset #1620
    //   Java source line #361	-> byte code offset #1623
    //   Java source line #370	-> byte code offset #1634
    //   Java source line #371	-> byte code offset #1639
    //   Java source line #363	-> byte code offset #1642
    //   Java source line #364	-> byte code offset #1644
    //   Java source line #365	-> byte code offset #1652
    //   Java source line #367	-> byte code offset #1659
    //   Java source line #370	-> byte code offset #1665
    //   Java source line #371	-> byte code offset #1670
    //   Java source line #370	-> byte code offset #1673
    //   Java source line #372	-> byte code offset #1683
    //   Java source line #375	-> byte code offset #1686
    //   Java source line #376	-> byte code offset #1696
    //   Java source line #377	-> byte code offset #1700
    //   Java source line #380	-> byte code offset #1703
    //   Java source line #381	-> byte code offset #1713
    //   Java source line #382	-> byte code offset #1719
    //   Java source line #384	-> byte code offset #1737
    //   Java source line #386	-> byte code offset #1740
    //   Java source line #387	-> byte code offset #1747
    //   Java source line #418	-> byte code offset #1752
    //   Java source line #419	-> byte code offset #1757
    //   Java source line #390	-> byte code offset #1765
    //   Java source line #391	-> byte code offset #1773
    //   Java source line #392	-> byte code offset #1787
    //   Java source line #393	-> byte code offset #1793
    //   Java source line #394	-> byte code offset #1802
    //   Java source line #395	-> byte code offset #1807
    //   Java source line #396	-> byte code offset #1814
    //   Java source line #398	-> byte code offset #1823
    //   Java source line #399	-> byte code offset #1829
    //   Java source line #400	-> byte code offset #1839
    //   Java source line #402	-> byte code offset #1848
    //   Java source line #406	-> byte code offset #1855
    //   Java source line #403	-> byte code offset #1858
    //   Java source line #404	-> byte code offset #1860
    //   Java source line #418	-> byte code offset #1866
    //   Java source line #419	-> byte code offset #1871
    //   Java source line #407	-> byte code offset #1879
    //   Java source line #408	-> byte code offset #1882
    //   Java source line #418	-> byte code offset #1893
    //   Java source line #419	-> byte code offset #1898
    //   Java source line #420	-> byte code offset #1903
    //   Java source line #410	-> byte code offset #1906
    //   Java source line #411	-> byte code offset #1908
    //   Java source line #412	-> byte code offset #1921
    //   Java source line #414	-> byte code offset #1928
    //   Java source line #418	-> byte code offset #1934
    //   Java source line #419	-> byte code offset #1939
    //   Java source line #418	-> byte code offset #1947
    //   Java source line #419	-> byte code offset #1954
    //   Java source line #421	-> byte code offset #1962
    //   Java source line #422	-> byte code offset #1965
    //   Java source line #423	-> byte code offset #1969
    //   Java source line #426	-> byte code offset #1972
    //   Java source line #427	-> byte code offset #1982
    //   Java source line #428	-> byte code offset #1986
    //   Java source line #431	-> byte code offset #1989
    //   Java source line #434	-> byte code offset #2031
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2032	0	this	PgServerThread
    //   14	2	1	x	int
    //   26	1990	1	x	int
    //   34	31	2	len	int
    //   42	21	3	data	byte[]
    //   177	185	4	version	int
    //   199	59	5	pid	int
    //   385	121	5	param	String
    //   556	42	5	password	String
    //   750	112	5	p	Prepared
    //   902	188	5	portal	Portal
    //   1119	93	5	type	char
    //   1244	192	5	type	char
    //   1460	46	5	name	String
    //   1717	11	5	query	String
    //   205	63	6	key	int
    //   402	115	6	value	String
    //   565	70	6	info	java.util.Properties
    //   718	3	6	e	Exception
    //   778	17	6	count	int
    //   917	7	6	prepName	String
    //   1125	61	6	name	String
    //   1250	116	6	name	String
    //   1484	42	6	p	Portal
    //   1735	6	6	reader	ScriptReader
    //   216	18	7	c	PgServerThread
    //   626	7	7	url	String
    //   790	35	7	i	int
    //   874	4	7	e	Exception
    //   931	99	7	prep	Prepared
    //   1156	8	7	p	Prepared
    //   1281	37	7	p	Prepared
    //   1344	36	7	p	Portal
    //   1523	31	7	maxRows	int
    //   1738	212	7	stat	JdbcStatement
    //   639	60	8	ci	org.h2.engine.ConnectionInfo
    //   803	19	8	type	int
    //   974	14	8	formatCodeCount	int
    //   1387	3	8	prep	PreparedStatement
    //   1530	14	8	prepared	Prepared
    //   1745	51	8	s	String
    //   1906	24	8	e	SQLException
    //   648	20	9	baseDir	String
    //   980	60	9	formatCodes	int[]
    //   1396	4	9	meta	ResultSetMetaData
    //   1407	4	9	e	Exception
    //   1537	108	9	prep	JdbcPreparedStatement
    //   1800	3	9	result	boolean
    //   983	19	10	i	int
    //   1011	8	10	paramCount	int
    //   1569	3	10	result	boolean
    //   1642	19	10	e	Exception
    //   1812	29	10	rs	ResultSet
    //   1014	31	11	i	int
    //   1053	4	11	e	Exception
    //   1068	17	11	resultCodeCount	int
    //   1581	14	11	rs	ResultSet
    //   1612	4	11	e	Exception
    //   1821	4	11	meta	ResultSetMetaData
    //   1080	22	12	i	int
    //   1858	4	12	e	Exception
    //   1673	8	13	localObject1	Object
    //   1947	13	14	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   558	715	718	java/lang/Exception
    //   830	871	874	java/lang/Exception
    //   1013	1050	1053	java/lang/Exception
    //   1389	1404	1407	java/lang/Exception
    //   1576	1609	1612	java/lang/Exception
    //   1551	1634	1642	java/lang/Exception
    //   1551	1634	1673	finally
    //   1642	1665	1673	finally
    //   1673	1675	1673	finally
    //   1823	1855	1858	java/lang/Exception
    //   1740	1752	1906	java/sql/SQLException
    //   1765	1866	1906	java/sql/SQLException
    //   1879	1893	1906	java/sql/SQLException
    //   1740	1752	1947	finally
    //   1765	1866	1947	finally
    //   1879	1893	1947	finally
    //   1906	1934	1947	finally
    //   1947	1949	1947	finally
  }
  
  private String getSQL(String s)
  {
    String lower = StringUtils.toLowerEnglish(s);
    if (lower.startsWith("show max_identifier_length")) {
      s = "CALL 63";
    } else if (lower.startsWith("set client_encoding to")) {
      s = "set DATESTYLE ISO";
    }
    if (this.server.getTrace()) {
      this.server.trace(s + ";");
    }
    return s;
  }
  
  private void sendCommandComplete(JdbcStatement stat, int updateCount)
    throws IOException
  {
    startMessage(67);
    switch (stat.getLastExecutedCommandType())
    {
    case 61: 
      writeStringPart("INSERT 0 ");
      writeString(Integer.toString(updateCount));
      break;
    case 68: 
      writeStringPart("UPDATE ");
      writeString(Integer.toString(updateCount));
      break;
    case 58: 
      writeStringPart("DELETE ");
      writeString(Integer.toString(updateCount));
      break;
    case 57: 
    case 66: 
      writeString("SELECT");
      break;
    case 83: 
      writeString("BEGIN");
      break;
    default: 
      this.server.trace("check CommandComplete tag for command " + stat);
      writeStringPart("UPDATE ");
      writeString(Integer.toString(updateCount));
    }
    sendMessage();
  }
  
  private void sendDataRow(ResultSet rs)
    throws Exception
  {
    ResultSetMetaData metaData = rs.getMetaData();
    int columns = metaData.getColumnCount();
    startMessage(68);
    writeShort(columns);
    for (int i = 1; i <= columns; i++) {
      writeDataColumn(rs, i, PgServer.convertType(metaData.getColumnType(i)));
    }
    sendMessage();
  }
  
  private void writeDataColumn(ResultSet rs, int column, int pgType)
    throws Exception
  {
    if (formatAsText(pgType)) {
      switch (pgType)
      {
      case 16: 
        writeInt(1);
        this.dataOut.writeByte(rs.getBoolean(column) ? 116 : 102);
        break;
      default: 
        String s = rs.getString(column);
        if (s == null)
        {
          writeInt(-1);
        }
        else
        {
          byte[] data = s.getBytes(getEncoding());
          writeInt(data.length);
          write(data);
        }
        break;
      }
    } else {
      switch (pgType)
      {
      case 21: 
        writeInt(2);
        writeShort(rs.getShort(column));
        break;
      case 23: 
        writeInt(4);
        writeInt(rs.getInt(column));
        break;
      case 20: 
        writeInt(8);
        this.dataOut.writeLong(rs.getLong(column));
        break;
      case 700: 
        writeInt(4);
        this.dataOut.writeFloat(rs.getFloat(column));
        break;
      case 701: 
        writeInt(8);
        this.dataOut.writeDouble(rs.getDouble(column));
        break;
      case 17: 
        byte[] data = rs.getBytes(column);
        if (data == null)
        {
          writeInt(-1);
        }
        else
        {
          writeInt(data.length);
          write(data);
        }
        break;
      default: 
        throw new IllegalStateException("output binary format is undefined");
      }
    }
  }
  
  private String getEncoding()
  {
    if ("UNICODE".equals(this.clientEncoding)) {
      return "UTF-8";
    }
    return this.clientEncoding;
  }
  
  private void setParameter(PreparedStatement prep, int pgType, int i, int[] formatCodes)
    throws SQLException, IOException
  {
    boolean text = (i >= formatCodes.length) || (formatCodes[i] == 0);
    int col = i + 1;
    int paramLen = readInt();
    if (paramLen == -1)
    {
      prep.setNull(col, 0);
    }
    else if (text)
    {
      byte[] data = DataUtils.newBytes(paramLen);
      readFully(data);
      prep.setString(col, new String(data, getEncoding()));
    }
    else
    {
      switch (pgType)
      {
      case 21: 
        checkParamLength(4, paramLen);
        prep.setShort(col, readShort());
        break;
      case 23: 
        checkParamLength(4, paramLen);
        prep.setInt(col, readInt());
        break;
      case 20: 
        checkParamLength(8, paramLen);
        prep.setLong(col, this.dataIn.readLong());
        break;
      case 700: 
        checkParamLength(4, paramLen);
        prep.setFloat(col, this.dataIn.readFloat());
        break;
      case 701: 
        checkParamLength(8, paramLen);
        prep.setDouble(col, this.dataIn.readDouble());
        break;
      case 17: 
        byte[] d1 = DataUtils.newBytes(paramLen);
        readFully(d1);
        prep.setBytes(col, d1);
        break;
      default: 
        this.server.trace("Binary format for type: " + pgType + " is unsupported");
        byte[] d2 = DataUtils.newBytes(paramLen);
        readFully(d2);
        prep.setString(col, new String(d2, getEncoding()));
      }
    }
  }
  
  private static void checkParamLength(int expected, int got)
  {
    if (expected != got) {
      throw DbException.getInvalidValueException("paramLen", Integer.valueOf(got));
    }
  }
  
  private void sendErrorResponse(Exception re)
    throws IOException
  {
    SQLException e = DbException.toSQLException(re);
    this.server.traceError(e);
    startMessage(69);
    write(83);
    writeString("ERROR");
    write(67);
    writeString(e.getSQLState());
    write(77);
    writeString(e.getMessage());
    write(68);
    writeString(e.toString());
    write(0);
    sendMessage();
  }
  
  private void sendCancelQueryResponse()
    throws IOException
  {
    this.server.trace("CancelSuccessResponse");
    startMessage(69);
    write(83);
    writeString("ERROR");
    write(67);
    writeString("57014");
    write(77);
    writeString("canceling statement due to user request");
    write(0);
    sendMessage();
  }
  
  private void sendParameterDescription(Prepared p)
    throws IOException
  {
    try
    {
      PreparedStatement prep = p.prep;
      ParameterMetaData meta = prep.getParameterMetaData();
      int count = meta.getParameterCount();
      startMessage(116);
      writeShort(count);
      for (int i = 0; i < count; i++)
      {
        int type;
        int type;
        if ((p.paramType != null) && (p.paramType[i] != 0)) {
          type = p.paramType[i];
        } else {
          type = 1043;
        }
        this.server.checkType(type);
        writeInt(type);
      }
      sendMessage();
    }
    catch (Exception e)
    {
      sendErrorResponse(e);
    }
  }
  
  private void sendNoData()
    throws IOException
  {
    startMessage(110);
    sendMessage();
  }
  
  private void sendRowDescription(ResultSetMetaData meta)
    throws Exception
  {
    if (meta == null)
    {
      sendNoData();
    }
    else
    {
      int columns = meta.getColumnCount();
      int[] types = new int[columns];
      int[] precision = new int[columns];
      String[] names = new String[columns];
      for (int i = 0; i < columns; i++)
      {
        String name = meta.getColumnName(i + 1);
        names[i] = name;
        int type = meta.getColumnType(i + 1);
        int pgType = PgServer.convertType(type);
        
        precision[i] = meta.getColumnDisplaySize(i + 1);
        if (type != 0) {
          this.server.checkType(pgType);
        }
        types[i] = pgType;
      }
      startMessage(84);
      writeShort(columns);
      for (int i = 0; i < columns; i++)
      {
        writeString(StringUtils.toLowerEnglish(names[i]));
        
        writeInt(0);
        
        writeShort(0);
        
        writeInt(types[i]);
        
        writeShort(getTypeSize(types[i], precision[i]));
        
        writeInt(-1);
        
        writeShort(formatAsText(types[i]) ? 0 : 1);
      }
      sendMessage();
    }
  }
  
  private static boolean formatAsText(int pgType)
  {
    switch (pgType)
    {
    case 17: 
      return false;
    }
    return true;
  }
  
  private static int getTypeSize(int pgType, int precision)
  {
    switch (pgType)
    {
    case 16: 
      return 1;
    case 1043: 
      return Math.max(255, precision + 10);
    }
    return precision + 4;
  }
  
  private void sendErrorResponse(String message)
    throws IOException
  {
    this.server.trace("Exception: " + message);
    startMessage(69);
    write(83);
    writeString("ERROR");
    write(67);
    
    writeString("08P01");
    write(77);
    writeString(message);
    sendMessage();
  }
  
  private void sendParseComplete()
    throws IOException
  {
    startMessage(49);
    sendMessage();
  }
  
  private void sendBindComplete()
    throws IOException
  {
    startMessage(50);
    sendMessage();
  }
  
  private void sendCloseComplete()
    throws IOException
  {
    startMessage(51);
    sendMessage();
  }
  
  private void initDb()
    throws SQLException
  {
    Statement stat = null;
    ResultSet rs = null;
    try
    {
      synchronized (this.server)
      {
        rs = this.conn.getMetaData().getTables(null, "PG_CATALOG", "PG_VERSION", null);
        boolean tableFound = rs.next();
        stat = this.conn.createStatement();
        if (!tableFound) {
          installPgCatalog(stat);
        }
        rs = stat.executeQuery("select * from pg_catalog.pg_version");
        if ((!rs.next()) || (rs.getInt(1) < 2))
        {
          installPgCatalog(stat);
        }
        else
        {
          int versionRead = rs.getInt(2);
          if (versionRead > 2) {
            throw DbException.throwInternalError("Incompatible PG_VERSION");
          }
        }
      }
      stat.execute("set search_path = PUBLIC, pg_catalog");
      HashSet<Integer> typeSet = this.server.getTypeSet();
      if (typeSet.size() == 0)
      {
        rs = stat.executeQuery("select oid from pg_catalog.pg_type");
        while (rs.next()) {
          typeSet.add(Integer.valueOf(rs.getInt(1)));
        }
      }
    }
    finally
    {
      JdbcUtils.closeSilently(stat);
      JdbcUtils.closeSilently(rs);
    }
  }
  
  private static void installPgCatalog(Statement stat)
    throws SQLException
  {
    Reader r = null;
    try
    {
      r = new InputStreamReader(new ByteArrayInputStream(Utils.getResource("/org/h2/server/pg/pg_catalog.sql")));
      
      ScriptReader reader = new ScriptReader(r);
      for (;;)
      {
        String sql = reader.readStatement();
        if (sql == null) {
          break;
        }
        stat.execute(sql);
      }
      reader.close();
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, "Can not read pg_catalog resource");
    }
    finally
    {
      IOUtils.closeSilently(r);
    }
  }
  
  void close()
  {
    try
    {
      this.stop = true;
      JdbcUtils.closeSilently(this.conn);
      if (this.socket != null) {
        this.socket.close();
      }
      this.server.trace("Close");
    }
    catch (Exception e)
    {
      this.server.traceError(e);
    }
    this.conn = null;
    this.socket = null;
    this.server.remove(this);
  }
  
  private void sendAuthenticationCleartextPassword()
    throws IOException
  {
    startMessage(82);
    writeInt(3);
    sendMessage();
  }
  
  private void sendAuthenticationOk()
    throws IOException
  {
    startMessage(82);
    writeInt(0);
    sendMessage();
    sendParameterStatus("client_encoding", this.clientEncoding);
    sendParameterStatus("DateStyle", this.dateStyle);
    sendParameterStatus("integer_datetimes", "off");
    sendParameterStatus("is_superuser", "off");
    sendParameterStatus("server_encoding", "SQL_ASCII");
    sendParameterStatus("server_version", "8.1.4");
    sendParameterStatus("session_authorization", this.userName);
    sendParameterStatus("standard_conforming_strings", "off");
    
    sendParameterStatus("TimeZone", "CET");
    sendBackendKeyData();
    sendReadyForQuery();
  }
  
  private void sendReadyForQuery()
    throws IOException
  {
    startMessage(90);
    char c;
    try
    {
      char c;
      if (this.conn.getAutoCommit()) {
        c = 'I';
      } else {
        c = 'T';
      }
    }
    catch (SQLException e)
    {
      c = 'E';
    }
    write((byte)c);
    sendMessage();
  }
  
  private void sendBackendKeyData()
    throws IOException
  {
    startMessage(75);
    writeInt(this.processId);
    writeInt(this.secret);
    sendMessage();
  }
  
  private void writeString(String s)
    throws IOException
  {
    writeStringPart(s);
    write(0);
  }
  
  private void writeStringPart(String s)
    throws IOException
  {
    write(s.getBytes(getEncoding()));
  }
  
  private void writeInt(int i)
    throws IOException
  {
    this.dataOut.writeInt(i);
  }
  
  private void writeShort(int i)
    throws IOException
  {
    this.dataOut.writeShort(i);
  }
  
  private void write(byte[] data)
    throws IOException
  {
    this.dataOut.write(data);
  }
  
  private void write(int b)
    throws IOException
  {
    this.dataOut.write(b);
  }
  
  private void startMessage(int newMessageType)
  {
    this.messageType = newMessageType;
    this.outBuffer = new ByteArrayOutputStream();
    this.dataOut = new DataOutputStream(this.outBuffer);
  }
  
  private void sendMessage()
    throws IOException
  {
    this.dataOut.flush();
    byte[] buff = this.outBuffer.toByteArray();
    int len = buff.length;
    this.dataOut = new DataOutputStream(this.out);
    this.dataOut.write(this.messageType);
    this.dataOut.writeInt(len + 4);
    this.dataOut.write(buff);
    this.dataOut.flush();
  }
  
  private void sendParameterStatus(String param, String value)
    throws IOException
  {
    startMessage(83);
    writeString(param);
    writeString(value);
    sendMessage();
  }
  
  void setThread(Thread thread)
  {
    this.thread = thread;
  }
  
  Thread getThread()
  {
    return this.thread;
  }
  
  void setProcessId(int id)
  {
    this.processId = id;
  }
  
  int getProcessId()
  {
    return this.processId;
  }
  
  private synchronized void setActiveRequest(JdbcStatement statement)
  {
    this.activeRequest = statement;
  }
  
  private synchronized void cancelRequest()
  {
    if (this.activeRequest != null) {
      try
      {
        this.activeRequest.cancel();
        this.activeRequest = null;
      }
      catch (SQLException e)
      {
        throw DbException.convert(e);
      }
    }
  }
  
  static class Portal
  {
    String name;
    int[] resultColumnFormat;
    PgServerThread.Prepared prep;
  }
  
  static class Prepared
  {
    String name;
    String sql;
    JdbcPreparedStatement prep;
    int[] paramType;
  }
}
