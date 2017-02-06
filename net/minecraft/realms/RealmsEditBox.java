package net.minecraft.realms;

import bcf;
import bdd;

public class RealmsEditBox
{
  private final bdd editBox;
  
  public RealmsEditBox(int ☃, int ☃, int ☃, int ☃, int ☃)
  {
    this.editBox = new bdd(☃, bcf.z().k, ☃, ☃, ☃, ☃);
  }
  
  public String getValue()
  {
    return this.editBox.b();
  }
  
  public void tick()
  {
    this.editBox.a();
  }
  
  public void setFocus(boolean ☃)
  {
    this.editBox.b(☃);
  }
  
  public void setValue(String ☃)
  {
    this.editBox.a(☃);
  }
  
  public void keyPressed(char ☃, int ☃)
  {
    this.editBox.a(☃, ☃);
  }
  
  public boolean isFocused()
  {
    return this.editBox.m();
  }
  
  public void mouseClicked(int ☃, int ☃, int ☃)
  {
    this.editBox.a(☃, ☃, ☃);
  }
  
  public void render()
  {
    this.editBox.g();
  }
  
  public void setMaxLength(int ☃)
  {
    this.editBox.f(☃);
  }
  
  public void setIsEditable(boolean ☃)
  {
    this.editBox.c(☃);
  }
}
