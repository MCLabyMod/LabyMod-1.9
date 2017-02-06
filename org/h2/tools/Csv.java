package org.h2.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.h2.engine.SysProperties;
import org.h2.message.DbException;
import org.h2.store.fs.FileUtils;
import org.h2.util.IOUtils;
import org.h2.util.JdbcUtils;
import org.h2.util.New;
import org.h2.util.StringUtils;

public class Csv
  implements SimpleRowSource
{
  private String[] columnNames;
  private String characterSet = SysProperties.FILE_ENCODING;
  private char escapeCharacter = '"';
  private char fieldDelimiter = '"';
  private char fieldSeparatorRead = ',';
  private String fieldSeparatorWrite = ",";
  private boolean caseSensitiveColumnNames;
  private boolean preserveWhitespace;
  private boolean writeColumnHeader = true;
  private char lineComment;
  private String lineSeparator = SysProperties.LINE_SEPARATOR;
  private String nullString = "";
  private String fileName;
  private Reader input;
  private char[] inputBuffer;
  private int inputBufferPos;
  private int inputBufferStart = -1;
  private int inputBufferEnd;
  private Writer output;
  private boolean endOfLine;
  private boolean endOfFile;
  
  private int writeResultSet(ResultSet rs)
    throws SQLException
  {
    try
    {
      int rows = 0;
      ResultSetMetaData meta = rs.getMetaData();
      int columnCount = meta.getColumnCount();
      String[] row = new String[columnCount];
      int[] sqlTypes = new int[columnCount];
      for (int i = 0; i < columnCount; i++)
      {
        row[i] = meta.getColumnLabel(i + 1);
        sqlTypes[i] = meta.getColumnType(i + 1);
      }
      if (this.writeColumnHeader) {
        writeRow(row);
      }
      int i;
      while (rs.next())
      {
        for (i = 0; i < columnCount; i++)
        {
          Object o;
          switch (sqlTypes[i])
          {
          case 91: 
            o = rs.getDate(i + 1);
            break;
          case 92: 
            o = rs.getTime(i + 1);
            break;
          case 93: 
            o = rs.getTimestamp(i + 1);
            break;
          default: 
            o = rs.getString(i + 1);
          }
          row[i] = (o == null ? null : o.toString());
        }
        writeRow(row);
        rows++;
      }
      this.output.close();
      return rows;
    }
    catch (IOException e)
    {
      throw DbException.convertIOException(e, null);
    }
    finally
    {
      close();
      JdbcUtils.closeSilently(rs);
    }
  }
  
  public int write(Writer writer, ResultSet rs)
    throws SQLException
  {
    this.output = writer;
    return writeResultSet(rs);
  }
  
  public int write(String outputFileName, ResultSet rs, String charset)
    throws SQLException
  {
    init(outputFileName, charset);
    try
    {
      initWrite();
      return writeResultSet(rs);
    }
    catch (IOException e)
    {
      throw convertException("IOException writing " + outputFileName, e);
    }
  }
  
  public int write(Connection conn, String outputFileName, String sql, String charset)
    throws SQLException
  {
    Statement stat = conn.createStatement();
    ResultSet rs = stat.executeQuery(sql);
    int rows = write(outputFileName, rs, charset);
    stat.close();
    return rows;
  }
  
  public ResultSet read(String inputFileName, String[] colNames, String charset)
    throws SQLException
  {
    init(inputFileName, charset);
    try
    {
      return readResultSet(colNames);
    }
    catch (IOException e)
    {
      throw convertException("IOException reading " + inputFileName, e);
    }
  }
  
  public ResultSet read(Reader reader, String[] colNames)
    throws IOException
  {
    init(null, null);
    this.input = reader;
    return readResultSet(colNames);
  }
  
  private ResultSet readResultSet(String[] colNames)
    throws IOException
  {
    this.columnNames = colNames;
    initRead();
    SimpleResultSet result = new SimpleResultSet(this);
    makeColumnNamesUnique();
    for (String columnName : this.columnNames) {
      result.addColumn(columnName, 12, Integer.MAX_VALUE, 0);
    }
    return result;
  }
  
  private void makeColumnNamesUnique()
  {
    for (int i = 0; i < this.columnNames.length; i++)
    {
      StringBuilder buff = new StringBuilder();
      String n = this.columnNames[i];
      if ((n == null) || (n.length() == 0)) {
        buff.append('C').append(i + 1);
      } else {
        buff.append(n);
      }
      for (int j = 0; j < i; j++)
      {
        String y = this.columnNames[j];
        if (buff.toString().equals(y))
        {
          buff.append('1');
          j = -1;
        }
      }
      this.columnNames[i] = buff.toString();
    }
  }
  
  private void init(String newFileName, String charset)
  {
    this.fileName = newFileName;
    if (charset != null) {
      this.characterSet = charset;
    }
  }
  
  private void initWrite()
    throws IOException
  {
    if (this.output == null) {
      try
      {
        OutputStream out = FileUtils.newOutputStream(this.fileName, false);
        out = new BufferedOutputStream(out, 4096);
        this.output = new BufferedWriter(new OutputStreamWriter(out, this.characterSet));
      }
      catch (Exception e)
      {
        close();
        throw DbException.convertToIOException(e);
      }
    }
  }
  
  private void writeRow(String[] values)
    throws IOException
  {
    for (int i = 0; i < values.length; i++)
    {
      if ((i > 0) && 
        (this.fieldSeparatorWrite != null)) {
        this.output.write(this.fieldSeparatorWrite);
      }
      String s = values[i];
      if (s != null)
      {
        if (this.escapeCharacter != 0)
        {
          if (this.fieldDelimiter != 0) {
            this.output.write(this.fieldDelimiter);
          }
          this.output.write(escape(s));
          if (this.fieldDelimiter != 0) {
            this.output.write(this.fieldDelimiter);
          }
        }
        else
        {
          this.output.write(s);
        }
      }
      else if ((this.nullString != null) && (this.nullString.length() > 0)) {
        this.output.write(this.nullString);
      }
    }
    this.output.write(this.lineSeparator);
  }
  
  private String escape(String data)
  {
    if ((data.indexOf(this.fieldDelimiter) < 0) && (
      (this.escapeCharacter == this.fieldDelimiter) || (data.indexOf(this.escapeCharacter) < 0))) {
      return data;
    }
    int length = data.length();
    StringBuilder buff = new StringBuilder(length);
    for (int i = 0; i < length; i++)
    {
      char ch = data.charAt(i);
      if ((ch == this.fieldDelimiter) || (ch == this.escapeCharacter)) {
        buff.append(this.escapeCharacter);
      }
      buff.append(ch);
    }
    return buff.toString();
  }
  
  private void initRead()
    throws IOException
  {
    if (this.input == null) {
      try
      {
        InputStream in = FileUtils.newInputStream(this.fileName);
        in = new BufferedInputStream(in, 4096);
        this.input = new InputStreamReader(in, this.characterSet);
      }
      catch (IOException e)
      {
        close();
        throw e;
      }
    }
    if (!this.input.markSupported()) {
      this.input = new BufferedReader(this.input);
    }
    this.input.mark(1);
    int bom = this.input.read();
    if (bom != 65279) {
      this.input.reset();
    }
    this.inputBuffer = new char[' '];
    if (this.columnNames == null) {
      readHeader();
    }
  }
  
  private void readHeader()
    throws IOException
  {
    ArrayList<String> list = New.arrayList();
    for (;;)
    {
      String v = readValue();
      if (v == null)
      {
        if (this.endOfLine)
        {
          if (this.endOfFile) {
            break;
          }
          if (list.size() > 0) {
            break;
          }
        }
        else
        {
          v = "COLUMN" + list.size();
          list.add(v);
        }
      }
      else
      {
        if (v.length() == 0) {
          v = "COLUMN" + list.size();
        } else if ((!this.caseSensitiveColumnNames) && (isSimpleColumnName(v))) {
          v = v.toUpperCase();
        }
        list.add(v);
        if (this.endOfLine) {
          break;
        }
      }
    }
    this.columnNames = new String[list.size()];
    list.toArray(this.columnNames);
  }
  
  private static boolean isSimpleColumnName(String columnName)
  {
    int i = 0;
    for (int length = columnName.length(); i < length; i++)
    {
      char ch = columnName.charAt(i);
      if (i == 0)
      {
        if ((ch != '_') && (!Character.isLetter(ch))) {
          return false;
        }
      }
      else if ((ch != '_') && (!Character.isLetterOrDigit(ch))) {
        return false;
      }
    }
    if (columnName.length() == 0) {
      return false;
    }
    return true;
  }
  
  private void pushBack()
  {
    this.inputBufferPos -= 1;
  }
  
  private int readChar()
    throws IOException
  {
    if (this.inputBufferPos >= this.inputBufferEnd) {
      return readBuffer();
    }
    return this.inputBuffer[(this.inputBufferPos++)];
  }
  
  private int readBuffer()
    throws IOException
  {
    if (this.endOfFile) {
      return -1;
    }
    int keep;
    if (this.inputBufferStart >= 0)
    {
      int keep = this.inputBufferPos - this.inputBufferStart;
      if (keep > 0)
      {
        char[] src = this.inputBuffer;
        if (keep + 4096 > src.length) {
          this.inputBuffer = new char[src.length * 2];
        }
        System.arraycopy(src, this.inputBufferStart, this.inputBuffer, 0, keep);
      }
      this.inputBufferStart = 0;
    }
    else
    {
      keep = 0;
    }
    this.inputBufferPos = keep;
    int len = this.input.read(this.inputBuffer, keep, 4096);
    if (len == -1)
    {
      this.inputBufferEnd = 64512;
      this.endOfFile = true;
      
      this.inputBufferPos += 1;
      return -1;
    }
    this.inputBufferEnd = (keep + len);
    return this.inputBuffer[(this.inputBufferPos++)];
  }
  
  private String readValue()
    throws IOException
  {
    this.endOfLine = false;
    this.inputBufferStart = this.inputBufferPos;
    int ch;
    label108:
    do
    {
      ch = readChar();
      if (ch == this.fieldDelimiter)
      {
        boolean containsEscape = false;
        this.inputBufferStart = this.inputBufferPos;
        do
        {
          for (;;)
          {
            ch = readChar();
            if (ch == this.fieldDelimiter)
            {
              ch = readChar();
              if (ch != this.fieldDelimiter)
              {
                int sep = 2;
                break label108;
              }
              containsEscape = true;
            }
            else
            {
              if (ch != this.escapeCharacter) {
                break;
              }
              ch = readChar();
              if (ch < 0)
              {
                int sep = 1;
                break label108;
              }
              containsEscape = true;
            }
          }
        } while (ch >= 0);
        int sep = 1;
        
        String s = new String(this.inputBuffer, this.inputBufferStart, this.inputBufferPos - this.inputBufferStart - sep);
        if (containsEscape) {
          s = unEscape(s);
        }
        this.inputBufferStart = -1;
        while (ch != this.fieldSeparatorRead)
        {
          if ((ch == 10) || (ch < 0) || (ch == 13))
          {
            this.endOfLine = true;
            break;
          }
          if ((ch != 32) && (ch != 9))
          {
            pushBack();
            break;
          }
          ch = readChar();
        }
        return s;
      }
      if ((ch == 10) || (ch < 0) || (ch == 13))
      {
        this.endOfLine = true;
        return null;
      }
      if (ch == this.fieldSeparatorRead) {
        return null;
      }
    } while (ch <= 32);
    if ((this.lineComment != 0) && (ch == this.lineComment))
    {
      this.inputBufferStart = -1;
      for (;;)
      {
        ch = readChar();
        if ((ch != 10) && (ch >= 0)) {
          if (ch == 13) {
            break;
          }
        }
      }
      this.endOfLine = true;
      return null;
    }
    do
    {
      ch = readChar();
      if (ch == this.fieldSeparatorRead) {
        break;
      }
    } while ((ch != 10) && (ch >= 0) && (ch != 13));
    this.endOfLine = true;
    
    String s = new String(this.inputBuffer, this.inputBufferStart, this.inputBufferPos - this.inputBufferStart - 1);
    if (!this.preserveWhitespace) {
      s = s.trim();
    }
    this.inputBufferStart = -1;
    
    return readNull(s);
  }
  
  private String readNull(String s)
  {
    return s.equals(this.nullString) ? null : s;
  }
  
  private String unEscape(String s)
  {
    StringBuilder buff = new StringBuilder(s.length());
    int start = 0;
    char[] chars = null;
    for (;;)
    {
      int idx = s.indexOf(this.escapeCharacter, start);
      if (idx < 0)
      {
        idx = s.indexOf(this.fieldDelimiter, start);
        if (idx < 0) {
          break;
        }
      }
      if (chars == null) {
        chars = s.toCharArray();
      }
      buff.append(chars, start, idx - start);
      if (idx == s.length() - 1)
      {
        start = s.length();
        break;
      }
      buff.append(chars[(idx + 1)]);
      start = idx + 2;
    }
    buff.append(s.substring(start));
    return buff.toString();
  }
  
  public Object[] readRow()
    throws SQLException
  {
    if (this.input == null) {
      return null;
    }
    String[] row = new String[this.columnNames.length];
    try
    {
      int i = 0;
      for (;;)
      {
        String v = readValue();
        if ((v == null) && 
          (this.endOfLine))
        {
          if (i != 0) {
            break;
          }
          if (this.endOfFile) {
            return null;
          }
        }
        else
        {
          if (i < row.length) {
            row[(i++)] = v;
          }
          if (this.endOfLine) {
            break;
          }
        }
      }
    }
    catch (IOException e)
    {
      throw convertException("IOException reading from " + this.fileName, e);
    }
    return row;
  }
  
  private static SQLException convertException(String message, Exception e)
  {
    return DbException.get(90028, e, new String[] { message }).getSQLException();
  }
  
  public void close()
  {
    IOUtils.closeSilently(this.input);
    this.input = null;
    IOUtils.closeSilently(this.output);
    this.output = null;
  }
  
  public void reset()
    throws SQLException
  {
    throw new SQLException("Method is not supported", "CSV");
  }
  
  public void setFieldSeparatorWrite(String fieldSeparatorWrite)
  {
    this.fieldSeparatorWrite = fieldSeparatorWrite;
  }
  
  public String getFieldSeparatorWrite()
  {
    return this.fieldSeparatorWrite;
  }
  
  public void setCaseSensitiveColumnNames(boolean caseSensitiveColumnNames)
  {
    this.caseSensitiveColumnNames = caseSensitiveColumnNames;
  }
  
  public boolean getCaseSensitiveColumnNames()
  {
    return this.caseSensitiveColumnNames;
  }
  
  public void setFieldSeparatorRead(char fieldSeparatorRead)
  {
    this.fieldSeparatorRead = fieldSeparatorRead;
  }
  
  public char getFieldSeparatorRead()
  {
    return this.fieldSeparatorRead;
  }
  
  public void setLineCommentCharacter(char lineCommentCharacter)
  {
    this.lineComment = lineCommentCharacter;
  }
  
  public char getLineCommentCharacter()
  {
    return this.lineComment;
  }
  
  public void setFieldDelimiter(char fieldDelimiter)
  {
    this.fieldDelimiter = fieldDelimiter;
  }
  
  public char getFieldDelimiter()
  {
    return this.fieldDelimiter;
  }
  
  public void setEscapeCharacter(char escapeCharacter)
  {
    this.escapeCharacter = escapeCharacter;
  }
  
  public char getEscapeCharacter()
  {
    return this.escapeCharacter;
  }
  
  public void setLineSeparator(String lineSeparator)
  {
    this.lineSeparator = lineSeparator;
  }
  
  public String getLineSeparator()
  {
    return this.lineSeparator;
  }
  
  public void setNullString(String nullString)
  {
    this.nullString = nullString;
  }
  
  public String getNullString()
  {
    return this.nullString;
  }
  
  public void setPreserveWhitespace(boolean value)
  {
    this.preserveWhitespace = value;
  }
  
  public boolean getPreserveWhitespace()
  {
    return this.preserveWhitespace;
  }
  
  public void setWriteColumnHeader(boolean value)
  {
    this.writeColumnHeader = value;
  }
  
  public boolean getWriteColumnHeader()
  {
    return this.writeColumnHeader;
  }
  
  public String setOptions(String options)
  {
    String charset = null;
    String[] keyValuePairs = StringUtils.arraySplit(options, ' ', false);
    for (String pair : keyValuePairs) {
      if (pair.length() != 0)
      {
        int index = pair.indexOf('=');
        String key = StringUtils.trim(pair.substring(0, index), true, true, " ");
        String value = pair.substring(index + 1);
        char ch = value.length() == 0 ? '\000' : value.charAt(0);
        if (isParam(key, new String[] { "escape", "esc", "escapeCharacter" }))
        {
          setEscapeCharacter(ch);
        }
        else if (isParam(key, new String[] { "fieldDelimiter", "fieldDelim" }))
        {
          setFieldDelimiter(ch);
        }
        else if (isParam(key, new String[] { "fieldSeparator", "fieldSep" }))
        {
          setFieldSeparatorRead(ch);
          setFieldSeparatorWrite(value);
        }
        else if (isParam(key, new String[] { "lineComment", "lineCommentCharacter" }))
        {
          setLineCommentCharacter(ch);
        }
        else if (isParam(key, new String[] { "lineSeparator", "lineSep" }))
        {
          setLineSeparator(value);
        }
        else if (isParam(key, new String[] { "null", "nullString" }))
        {
          setNullString(value);
        }
        else if (isParam(key, new String[] { "charset", "characterSet" }))
        {
          charset = value;
        }
        else if (isParam(key, new String[] { "preserveWhitespace" }))
        {
          setPreserveWhitespace(Boolean.parseBoolean(value));
        }
        else if (isParam(key, new String[] { "writeColumnHeader" }))
        {
          setWriteColumnHeader(Boolean.parseBoolean(value));
        }
        else if (isParam(key, new String[] { "caseSensitiveColumnNames" }))
        {
          setCaseSensitiveColumnNames(Boolean.parseBoolean(value));
        }
        else
        {
          throw DbException.getUnsupportedException(key);
        }
      }
    }
    return charset;
  }
  
  private static boolean isParam(String key, String... values)
  {
    for (String v : values) {
      if (key.equalsIgnoreCase(v)) {
        return true;
      }
    }
    return false;
  }
}
