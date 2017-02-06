package org.h2.expression;

class FunctionInfo
{
  String name;
  int type;
  int dataType;
  int parameterCount;
  boolean nullIfParameterIsNull;
  boolean deterministic;
  boolean bufferResultSetToLocalTemp = true;
}
