package org.h2.message;

abstract interface TraceWriter
{
  public abstract void setName(String paramString);
  
  public abstract void write(int paramInt, String paramString1, String paramString2, Throwable paramThrowable);
  
  public abstract boolean isEnabled(int paramInt);
}
