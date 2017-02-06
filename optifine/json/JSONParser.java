package optifine.json;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JSONParser
{
  public static final int S_INIT = 0;
  public static final int S_IN_FINISHED_VALUE = 1;
  public static final int S_IN_OBJECT = 2;
  public static final int S_IN_ARRAY = 3;
  public static final int S_PASSED_PAIR_KEY = 4;
  public static final int S_IN_PAIR_VALUE = 5;
  public static final int S_END = 6;
  public static final int S_IN_ERROR = -1;
  private LinkedList handlerStatusStack;
  private Yylex lexer = new Yylex(null);
  private Yytoken token = null;
  private int status = 0;
  
  private int peekStatus(LinkedList statusStack)
  {
    if (statusStack.size() == 0) {
      return -1;
    }
    Integer status = (Integer)statusStack.getFirst();
    return status.intValue();
  }
  
  public void reset()
  {
    this.token = null;
    this.status = 0;
    this.handlerStatusStack = null;
  }
  
  public void reset(Reader in)
  {
    this.lexer.yyreset(in);
    reset();
  }
  
  public int getPosition()
  {
    return this.lexer.getPosition();
  }
  
  public Object parse(String s)
    throws ParseException
  {
    return parse(s, null);
  }
  
  public Object parse(String s, ContainerFactory containerFactory)
    throws ParseException
  {
    StringReader in = new StringReader(s);
    try
    {
      return parse(in, containerFactory);
    }
    catch (IOException ie)
    {
      throw new ParseException(-1, 2, ie);
    }
  }
  
  public Object parse(Reader in)
    throws IOException, ParseException
  {
    return parse(in, null);
  }
  
  public Object parse(Reader in, ContainerFactory containerFactory)
    throws IOException, ParseException
  {
    reset(in);
    LinkedList statusStack = new LinkedList();
    LinkedList valueStack = new LinkedList();
    try
    {
      do
      {
        nextToken();
        switch (this.status)
        {
        case 0: 
          switch (this.token.type)
          {
          case 0: 
            this.status = 1;
            statusStack.addFirst(new Integer(this.status));
            valueStack.addFirst(this.token.value);
            break;
          case 1: 
            this.status = 2;
            statusStack.addFirst(new Integer(this.status));
            valueStack.addFirst(createObjectContainer(containerFactory));
            break;
          case 3: 
            this.status = 3;
            statusStack.addFirst(new Integer(this.status));
            valueStack.addFirst(createArrayContainer(containerFactory));
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case 1: 
          if (this.token.type == -1) {
            return valueStack.removeFirst();
          }
          throw new ParseException(getPosition(), 1, this.token);
        case 2: 
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            if ((this.token.value instanceof String))
            {
              String key = (String)this.token.value;
              valueStack.addFirst(key);
              this.status = 4;
              statusStack.addFirst(new Integer(this.status));
            }
            else
            {
              this.status = -1;
            }
            break;
          case 2: 
            if (valueStack.size() > 1)
            {
              statusStack.removeFirst();
              valueStack.removeFirst();
              this.status = peekStatus(statusStack);
            }
            else
            {
              this.status = 1;
            }
            break;
          case 1: 
          case 3: 
          case 4: 
          default: 
            this.status = -1;
          }
          break;
        case 4: 
          switch (this.token.type)
          {
          case 6: 
            break;
          case 0: 
            statusStack.removeFirst();
            String key = (String)valueStack.removeFirst();
            Map parent = (Map)valueStack.getFirst();
            parent.put(key, this.token.value);
            this.status = peekStatus(statusStack);
            break;
          case 3: 
            statusStack.removeFirst();
            String key = (String)valueStack.removeFirst();
            Map parent = (Map)valueStack.getFirst();
            List newArray = createArrayContainer(containerFactory);
            parent.put(key, newArray);
            this.status = 3;
            statusStack.addFirst(new Integer(this.status));
            valueStack.addFirst(newArray);
            break;
          case 1: 
            statusStack.removeFirst();
            String key = (String)valueStack.removeFirst();
            Map parent = (Map)valueStack.getFirst();
            Map newObject = createObjectContainer(containerFactory);
            parent.put(key, newObject);
            this.status = 2;
            statusStack.addFirst(new Integer(this.status));
            valueStack.addFirst(newObject);
            break;
          case 2: 
          case 4: 
          case 5: 
          default: 
            this.status = -1;
          }
          break;
        case 3: 
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            List val = (List)valueStack.getFirst();
            val.add(this.token.value);
            break;
          case 4: 
            if (valueStack.size() > 1)
            {
              statusStack.removeFirst();
              valueStack.removeFirst();
              this.status = peekStatus(statusStack);
            }
            else
            {
              this.status = 1;
            }
            break;
          case 1: 
            List val = (List)valueStack.getFirst();
            Map newObject = createObjectContainer(containerFactory);
            val.add(newObject);
            this.status = 2;
            statusStack.addFirst(new Integer(this.status));
            valueStack.addFirst(newObject);
            break;
          case 3: 
            List val = (List)valueStack.getFirst();
            List newArray = createArrayContainer(containerFactory);
            val.add(newArray);
            this.status = 3;
            statusStack.addFirst(new Integer(this.status));
            valueStack.addFirst(newArray);
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case -1: 
          throw new ParseException(getPosition(), 1, this.token);
        }
        if (this.status == -1) {
          throw new ParseException(getPosition(), 1, this.token);
        }
      } while (this.token.type != -1);
    }
    catch (IOException ie)
    {
      throw ie;
    }
    throw new ParseException(getPosition(), 1, this.token);
  }
  
  private void nextToken()
    throws ParseException, IOException
  {
    this.token = this.lexer.yylex();
    if (this.token == null) {
      this.token = new Yytoken(-1, null);
    }
  }
  
  private Map createObjectContainer(ContainerFactory containerFactory)
  {
    if (containerFactory == null) {
      return new JSONObject();
    }
    Map m = containerFactory.createObjectContainer();
    if (m == null) {
      return new JSONObject();
    }
    return m;
  }
  
  private List createArrayContainer(ContainerFactory containerFactory)
  {
    if (containerFactory == null) {
      return new JSONArray();
    }
    List l = containerFactory.creatArrayContainer();
    if (l == null) {
      return new JSONArray();
    }
    return l;
  }
  
  public void parse(String s, ContentHandler contentHandler)
    throws ParseException
  {
    parse(s, contentHandler, false);
  }
  
  public void parse(String s, ContentHandler contentHandler, boolean isResume)
    throws ParseException
  {
    StringReader in = new StringReader(s);
    try
    {
      parse(in, contentHandler, isResume);
    }
    catch (IOException ie)
    {
      throw new ParseException(-1, 2, ie);
    }
  }
  
  public void parse(Reader in, ContentHandler contentHandler)
    throws IOException, ParseException
  {
    parse(in, contentHandler, false);
  }
  
  public void parse(Reader in, ContentHandler contentHandler, boolean isResume)
    throws IOException, ParseException
  {
    if (!isResume)
    {
      reset(in);
      this.handlerStatusStack = new LinkedList();
    }
    else if (this.handlerStatusStack == null)
    {
      isResume = false;
      reset(in);
      this.handlerStatusStack = new LinkedList();
    }
    LinkedList statusStack = this.handlerStatusStack;
    try
    {
      do
      {
        switch (this.status)
        {
        case 0: 
          contentHandler.startJSON();
          nextToken();
          switch (this.token.type)
          {
          case 0: 
            this.status = 1;
            statusStack.addFirst(new Integer(this.status));
            if (!contentHandler.primitive(this.token.value)) {
              return;
            }
            break;
          case 1: 
            this.status = 2;
            statusStack.addFirst(new Integer(this.status));
            if (!contentHandler.startObject()) {
              return;
            }
            break;
          case 3: 
            this.status = 3;
            statusStack.addFirst(new Integer(this.status));
            if (!contentHandler.startArray()) {
              return;
            }
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case 1: 
          nextToken();
          if (this.token.type == -1)
          {
            contentHandler.endJSON();
            this.status = 6;
            return;
          }
          this.status = -1;
          throw new ParseException(getPosition(), 1, this.token);
        case 2: 
          nextToken();
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            if ((this.token.value instanceof String))
            {
              String key = (String)this.token.value;
              this.status = 4;
              statusStack.addFirst(new Integer(this.status));
              if (contentHandler.startObjectEntry(key)) {
                break;
              }
            }
            else
            {
              this.status = -1;
            }
            break;
          case 2: 
            if (statusStack.size() > 1)
            {
              statusStack.removeFirst();
              this.status = peekStatus(statusStack);
            }
            else
            {
              this.status = 1;
            }
            if (!contentHandler.endObject()) {
              return;
            }
            break;
          case 1: 
          case 3: 
          case 4: 
          default: 
            this.status = -1;
          }
          break;
        case 4: 
          nextToken();
          switch (this.token.type)
          {
          case 6: 
            break;
          case 0: 
            statusStack.removeFirst();
            this.status = peekStatus(statusStack);
            if (!contentHandler.primitive(this.token.value)) {
              return;
            }
            if (!contentHandler.endObjectEntry()) {
              return;
            }
            break;
          case 3: 
            statusStack.removeFirst();
            statusStack.addFirst(new Integer(5));
            this.status = 3;
            statusStack.addFirst(new Integer(this.status));
            if (!contentHandler.startArray()) {
              return;
            }
            break;
          case 1: 
            statusStack.removeFirst();
            statusStack.addFirst(new Integer(5));
            this.status = 2;
            statusStack.addFirst(new Integer(this.status));
            if (!contentHandler.startObject()) {
              return;
            }
            break;
          case 2: 
          case 4: 
          case 5: 
          default: 
            this.status = -1;
          }
          break;
        case 5: 
          statusStack.removeFirst();
          this.status = peekStatus(statusStack);
          if (!contentHandler.endObjectEntry()) {
            return;
          }
          break;
        case 3: 
          nextToken();
          switch (this.token.type)
          {
          case 5: 
            break;
          case 0: 
            if (!contentHandler.primitive(this.token.value)) {
              return;
            }
            break;
          case 4: 
            if (statusStack.size() > 1)
            {
              statusStack.removeFirst();
              this.status = peekStatus(statusStack);
            }
            else
            {
              this.status = 1;
            }
            if (!contentHandler.endArray()) {
              return;
            }
            break;
          case 1: 
            this.status = 2;
            statusStack.addFirst(new Integer(this.status));
            if (!contentHandler.startObject()) {
              return;
            }
            break;
          case 3: 
            this.status = 3;
            statusStack.addFirst(new Integer(this.status));
            if (!contentHandler.startArray()) {
              return;
            }
            break;
          case 2: 
          default: 
            this.status = -1;
          }
          break;
        case 6: 
          return;
        case -1: 
          throw new ParseException(getPosition(), 1, this.token);
        }
        if (this.status == -1) {
          throw new ParseException(getPosition(), 1, this.token);
        }
      } while (this.token.type != -1);
    }
    catch (IOException ie)
    {
      this.status = -1;
      throw ie;
    }
    catch (ParseException pe)
    {
      this.status = -1;
      throw pe;
    }
    catch (RuntimeException re)
    {
      this.status = -1;
      throw re;
    }
    catch (Error e)
    {
      this.status = -1;
      throw e;
    }
    this.status = -1;
    throw new ParseException(getPosition(), 1, this.token);
  }
  
  public static Date parseDate(String input)
  {
    if (input == null) {
      return null;
    }
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    if (input.endsWith("Z"))
    {
      input = input.substring(0, input.length() - 1) + "GMT-00:00";
    }
    else
    {
      int inset = 6;
      
      String s0 = input.substring(0, input.length() - inset);
      String s1 = input.substring(input.length() - inset, input.length());
      
      input = s0 + "GMT" + s1;
    }
    try
    {
      return df.parse(input);
    }
    catch (java.text.ParseException e)
    {
      System.out.println("Error parsing date: " + input);
      System.out.println(e.getClass().getName() + ": " + e.getMessage());
    }
    return null;
  }
}
