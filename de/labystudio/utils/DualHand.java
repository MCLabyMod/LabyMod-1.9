package de.labystudio.utils;

import ado;
import adq;
import aex;
import bcf;
import bch;
import qm;
import rz;
import sa;
import zj;

public class DualHand
{
  static qm usingHand = qm.a;
  
  public static qm getUsingHand()
  {
    return usingHand;
  }
  
  public static void setUsingHand(qm usingHand)
  {
    usingHand = usingHand;
  }
  
  public static int getItemIdInMainHand()
  {
    if (bcf.z().h == null) {
      return 0;
    }
    sa entity = bcf.z().h;
    int item = 0;
    if (entity.cb() != null) {
      item = ado.a(entity.cb().b());
    }
    return item;
  }
  
  public static int getItemIdInOffHand()
  {
    if (bcf.z().h == null) {
      return 0;
    }
    sa entity = bcf.z().h;
    int item = 0;
    if (entity.cc() != null) {
      item = ado.a(entity.cc().b());
    }
    return item;
  }
  
  public static int getItemIdInRightHand()
  {
    if (bcf.z().u == null) {
      return 0;
    }
    if (bcf.z().u.A == null) {
      return 0;
    }
    if (bcf.z().u.A == rz.b) {
      return getItemIdInMainHand();
    }
    return getItemIdInOffHand();
  }
  
  public static int getItemIdInLeftHand()
  {
    if (bcf.z().u == null) {
      return 0;
    }
    if (bcf.z().u.A == null) {
      return 0;
    }
    if (bcf.z().u.A == rz.a) {
      return getItemIdInMainHand();
    }
    return getItemIdInOffHand();
  }
  
  public static ado getItemInRightHand()
  {
    if (bcf.z().u == null) {
      return ado.c(0);
    }
    if (bcf.z().u.A == null) {
      return ado.c(0);
    }
    if (bcf.z().u.A == rz.b) {
      return getItemInMainHand();
    }
    return getItemInOffHand();
  }
  
  public static ado getItemInLeftHand()
  {
    if (bcf.z().u == null) {
      return ado.c(0);
    }
    if (bcf.z().u.A == null) {
      return ado.c(0);
    }
    if (bcf.z().u.A == rz.a) {
      return getItemInMainHand();
    }
    return getItemInOffHand();
  }
  
  public static rz getDefaultHand()
  {
    if (bcf.z().h == null) {
      return rz.b;
    }
    sa entity = bcf.z().h;
    return entity.cr();
  }
  
  public static rz getUseFocus()
  {
    if (bcf.z().h == null) {
      return rz.b;
    }
    sa entity = bcf.z().h;
    
    qm pri = entity.ct();
    if ((Allowed.blocking()) && (Allowed.animations()))
    {
      if ((getItemInOffHand() instanceof aex)) {
        pri = qm.b;
      }
      if ((getItemInMainHand() instanceof aex)) {
        pri = qm.a;
      }
    }
    if ((entity.cs()) && (entity.cw() > 0) && (pri == qm.a)) {
      return toEnumHandSide(qm.a);
    }
    if ((entity.cs()) && (entity.cw() > 0) && (pri == qm.b)) {
      return toEnumHandSide(qm.b);
    }
    return toEnumHandSide(entity.ct());
  }
  
  public static qm getUseMainFocus()
  {
    if (bcf.z().h == null) {
      return qm.a;
    }
    sa entity = bcf.z().h;
    if (bcf.z().u.A == rz.b)
    {
      if (getUseFocus() == rz.b) {
        return qm.a;
      }
      return qm.b;
    }
    if (bcf.z().u.A == rz.a)
    {
      if (getUseFocus() == rz.a) {
        return qm.a;
      }
      return qm.b;
    }
    return qm.a;
  }
  
  public static rz getHoldingItem(int id)
  {
    if (getItemIdInRightHand() == id) {
      return rz.b;
    }
    if (getItemIdInLeftHand() == id) {
      return rz.a;
    }
    return rz.b;
  }
  
  public static ado getItemInMainHand()
  {
    if (bcf.z().h == null) {
      return ado.c(0);
    }
    sa entity = bcf.z().h;
    if (entity.cb() != null) {
      return entity.cb().b();
    }
    return ado.c(0);
  }
  
  public static ado getItemInOffHand()
  {
    if (bcf.z().h == null) {
      return ado.c(0);
    }
    sa entity = bcf.z().h;
    if (entity.cc() != null) {
      return entity.cc().b();
    }
    return ado.c(0);
  }
  
  public static boolean hasSword(rz hand)
  {
    if (hand == rz.b) {
      return getItemInRightHand() instanceof aex;
    }
    return ((getItemInMainHand() instanceof aex)) || ((getItemInOffHand() instanceof aex));
  }
  
  public static boolean isBlocking(sa entity, rz hand)
  {
    if (hand == getDefaultHand()) {
      return (((zj)entity).isBlocking(hand)) && (getItemIdInMainHand() != 261);
    }
    return (((zj)entity).isBlocking(hand)) && (getItemIdInOffHand() != 261);
  }
  
  public static qm toEnumHand(rz hand)
  {
    if (bcf.z().u.A == rz.b)
    {
      if (hand == rz.b) {
        return qm.a;
      }
      return qm.b;
    }
    if (bcf.z().u.A == rz.a)
    {
      if (hand == rz.a) {
        return qm.a;
      }
      return qm.b;
    }
    return qm.a;
  }
  
  public static rz toEnumHandSide(qm hand)
  {
    if (bcf.z().u.A == rz.b)
    {
      if (hand == qm.a) {
        return rz.b;
      }
      return rz.a;
    }
    if (bcf.z().u.A == rz.a)
    {
      if (hand == qm.a) {
        return rz.b;
      }
      return rz.a;
    }
    return rz.b;
  }
}
