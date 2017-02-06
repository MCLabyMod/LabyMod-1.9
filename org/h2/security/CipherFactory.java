package org.h2.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.StringUtils;

public class CipherFactory
{
  public static final String KEYSTORE_PASSWORD = "h2pass";
  private static final String KEYSTORE = "~/.h2.keystore";
  private static final String KEYSTORE_KEY = "javax.net.ssl.keyStore";
  private static final String KEYSTORE_PASSWORD_KEY = "javax.net.ssl.keyStorePassword";
  
  public static BlockCipher getBlockCipher(String algorithm)
  {
    if ("XTEA".equalsIgnoreCase(algorithm)) {
      return new XTEA();
    }
    if ("AES".equalsIgnoreCase(algorithm)) {
      return new AES();
    }
    if ("FOG".equalsIgnoreCase(algorithm)) {
      return new Fog();
    }
    throw DbException.get(90055, algorithm);
  }
  
  public static Socket createSocket(InetAddress address, int port)
    throws IOException
  {
    Socket socket = null;
    setKeystore();
    SSLSocketFactory f = (SSLSocketFactory)SSLSocketFactory.getDefault();
    SSLSocket secureSocket = (SSLSocket)f.createSocket();
    secureSocket.connect(new InetSocketAddress(address, port), SysProperties.SOCKET_CONNECT_TIMEOUT);
    
    secureSocket.setEnabledProtocols(disableSSL(secureSocket.getEnabledProtocols()));
    if (SysProperties.ENABLE_ANONYMOUS_TLS)
    {
      String[] list = enableAnonymous(secureSocket.getEnabledCipherSuites(), secureSocket.getSupportedCipherSuites());
      
      secureSocket.setEnabledCipherSuites(list);
    }
    socket = secureSocket;
    return socket;
  }
  
  public static ServerSocket createServerSocket(int port, InetAddress bindAddress)
    throws IOException
  {
    ServerSocket socket = null;
    setKeystore();
    ServerSocketFactory f = SSLServerSocketFactory.getDefault();
    SSLServerSocket secureSocket;
    SSLServerSocket secureSocket;
    if (bindAddress == null) {
      secureSocket = (SSLServerSocket)f.createServerSocket(port);
    } else {
      secureSocket = (SSLServerSocket)f.createServerSocket(port, 0, bindAddress);
    }
    secureSocket.setEnabledProtocols(disableSSL(secureSocket.getEnabledProtocols()));
    if (SysProperties.ENABLE_ANONYMOUS_TLS)
    {
      String[] list = enableAnonymous(secureSocket.getEnabledCipherSuites(), secureSocket.getSupportedCipherSuites());
      
      secureSocket.setEnabledCipherSuites(list);
    }
    socket = secureSocket;
    return socket;
  }
  
  private static byte[] getKeyStoreBytes(KeyStore store, String password)
    throws IOException
  {
    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    try
    {
      store.store(bout, password.toCharArray());
    }
    catch (Exception e)
    {
      throw DbException.convertToIOException(e);
    }
    return bout.toByteArray();
  }
  
  public static KeyStore getKeyStore(String password)
    throws IOException
  {
    try
    {
      KeyStore store = KeyStore.getInstance(KeyStore.getDefaultType());
      
      store.load(null, password.toCharArray());
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      store.load(null, password.toCharArray());
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(StringUtils.convertHexToBytes("30820277020100300d06092a864886f70d0101010500048202613082025d02010002818100dc0a13c602b7141110eade2f051b54777b060d0f74e6a110f9cce81159f271ebc88d8e8aa1f743b505fc2e7dfe38d33b8d3f64d1b363d1af4d877833897954cbaec2fa384c22a415498cf306bb07ac09b76b001cd68bf77ea0a628f5101959cf2993a9c23dbee79b19305977f8715ae78d023471194cc900b231eecb0aaea98d02030100010281810099aa4ff4d0a09a5af0bd953cb10c4d08c3d98df565664ac5582e494314d5c3c92dddedd5d316a32a206be4ec084616fe57be15e27cad111aa3c21fa79e32258c6ca8430afc69eddd52d3b751b37da6b6860910b94653192c0db1d02abcfd6ce14c01f238eec7c20bd3bb750940004bacba2880349a9494d10e139ecb2355d101024100ffdc3defd9c05a2d377ef6019fa62b3fbd5b0020a04cc8533bca730e1f6fcf5dfceea1b044fbe17d9eababfbc7d955edad6bc60f9be826ad2c22ba77d19a9f65024100dc28d43fdbbc93852cc3567093157702bc16f156f709fb7db0d9eec028f41fd0edcd17224c866e66be1744141fb724a10fd741c8a96afdd9141b36d67fff6309024077b1cddbde0f69604bdcfe33263fb36ddf24aa3b9922327915b890f8a36648295d0139ecdf68c245652c4489c6257b58744fbdd961834a4cab201801a3b1e52d024100b17142e8991d1b350a0802624759d48ae2b8071a158ff91fabeb6a8f7c328e762143dc726b8529f42b1fab6220d1c676fdc27ba5d44e847c72c52064afd351a902407c6e23fe35bcfcd1a662aa82a2aa725fcece311644d5b6e3894853fd4ce9fe78218c957b1ff03fc9e5ef8ffeb6bd58235f6a215c97d354fdace7e781e4a63e8b"));
      
      PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
      Certificate[] certs = { CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(StringUtils.convertHexToBytes("3082018b3081f502044295ce6b300d06092a864886f70d0101040500300d310b3009060355040313024832301e170d3035303532363133323630335a170d3337303933303036353734375a300d310b300906035504031302483230819f300d06092a864886f70d010101050003818d0030818902818100dc0a13c602b7141110eade2f051b54777b060d0f74e6a110f9cce81159f271ebc88d8e8aa1f743b505fc2e7dfe38d33b8d3f64d1b363d1af4d877833897954cbaec2fa384c22a415498cf306bb07ac09b76b001cd68bf77ea0a628f5101959cf2993a9c23dbee79b19305977f8715ae78d023471194cc900b231eecb0aaea98d0203010001300d06092a864886f70d01010405000381810083f4401a279453701bef9a7681a5b8b24f153f7d18c7c892133d97bd5f13736be7505290a445a7d5ceb75522403e5097515cd966ded6351ff60d5193de34cd36e5cb04d380398e66286f99923fd92296645fd4ada45844d194dfd815e6cd57f385c117be982809028bba1116c85740b3d27a55b1a0948bf291ddba44bed337b9"))) };
      
      store.setKeyEntry("h2", privateKey, password.toCharArray(), certs);
      
      return store;
    }
    catch (Exception e)
    {
      throw DbException.convertToIOException(e);
    }
  }
  
  private static void setKeystore()
    throws IOException
  {
    Properties p = System.getProperties();
    if (p.getProperty("javax.net.ssl.keyStore") == null)
    {
      String fileName = "~/.h2.keystore";
      byte[] data = getKeyStoreBytes(getKeyStore("h2pass"), "h2pass");
      
      boolean needWrite = true;
      if ((FileUtils.exists(fileName)) && (FileUtils.size(fileName) == data.length))
      {
        InputStream fin = FileUtils.newInputStream(fileName);
        byte[] now = IOUtils.readBytesAndClose(fin, 0);
        if ((now != null) && (Arrays.equals(data, now))) {
          needWrite = false;
        }
      }
      if (needWrite) {
        try
        {
          OutputStream out = FileUtils.newOutputStream(fileName, false);
          out.write(data);
          out.close();
        }
        catch (Exception e)
        {
          throw DbException.convertToIOException(e);
        }
      }
      String absolutePath = FileUtils.toRealPath(fileName);
      System.setProperty("javax.net.ssl.keyStore", absolutePath);
    }
    if (p.getProperty("javax.net.ssl.keyStorePassword") == null) {
      System.setProperty("javax.net.ssl.keyStorePassword", "h2pass");
    }
  }
  
  private static String[] enableAnonymous(String[] enabled, String[] supported)
  {
    HashSet<String> set = new HashSet();
    Collections.addAll(set, enabled);
    for (String x : supported) {
      if ((!x.startsWith("SSL")) && (x.indexOf("_anon_") >= 0) && (x.indexOf("_AES_") >= 0) && (x.indexOf("_SHA") >= 0)) {
        set.add(x);
      }
    }
    return (String[])set.toArray(new String[0]);
  }
  
  private static String[] disableSSL(String[] enabled)
  {
    HashSet<String> set = new HashSet();
    for (String x : enabled) {
      if (!x.startsWith("SSL")) {
        set.add(x);
      }
    }
    return (String[])set.toArray(new String[0]);
  }
}
