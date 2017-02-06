package de.labystudio.spotify;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;

abstract interface SpotifyUser32
  extends StdCallLibrary
{
  public abstract boolean EnumWindows(WinUser.WNDENUMPROC paramWNDENUMPROC, Pointer paramPointer);
  
  public abstract int GetWindowTextA(WinDef.HWND paramHWND, byte[] paramArrayOfByte, int paramInt);
}
