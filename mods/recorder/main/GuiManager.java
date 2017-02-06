package mods.recorder.main;

import bcf;
import bcz;
import bfb;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.utils.Color;
import de.labystudio.utils.DrawUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GuiManager
  extends bfb
{
  bcz a;
  bcz b;
  bcz c;
  bcz d;
  bcz e;
  bcz f;
  
  public void b()
  {
    this.n.add(new bcz(0, this.l / 2 - 100, this.m / 6 + 150, "Done"));
    this.n.add(this.a = new bcz(1, this.l / 2 - 100, this.m / 6 + 30, 70, 20, ""));
    this.n.add(this.b = new bcz(2, this.l / 2 - 100, this.m / 6 + 52, 70, 20, ""));
    
    this.n.add(this.c = new bcz(3, this.l / 2 - 20, this.m / 6 + 52, 120, 20, ""));
    this.n.add(this.d = new bcz(4, this.l / 2 - 20, this.m / 6 + 30, 120, 20, ""));
    
    this.n.add(this.e = new bcz(5, this.l / 2 - 20, this.m / 6 + 80, 120, 20, ""));
    
    refresh();
  }
  
  public void refresh()
  {
    this.a.j = "Import";
    this.b.j = "Export";
    
    this.b.l = (RecorderManager.end != 0L);
    
    this.c.j = "Play recording";
    this.d.j = "Start recording";
    
    this.e.j = "Reset";
    
    this.e.l = ((RecorderManager.end != 0L) && (RecorderManager.recordingState == EnumRecordingState.NONE));
    this.c.l = ((RecorderManager.end != 0L) && (RecorderManager.recordingState != EnumRecordingState.RECORDING));
    this.d.l = (RecorderManager.recordingState != EnumRecordingState.PLAYING);
    if (RecorderManager.recordingState == EnumRecordingState.RECORDING) {
      this.d.j = (Color.cl("4") + "Stop recording");
    }
    if (RecorderManager.recordingState == EnumRecordingState.PLAYING) {
      this.c.j = (Color.cl("e") + "Stop playing");
    }
  }
  
  protected void a(bcz button)
    throws IOException
  {
    if (button.k == 0) {
      this.j.a(ModAPI.getLastScreen());
    }
    if (button.k == 1) {
      RecorderManager.importRecording();
    }
    if (button.k == 2) {
      RecorderManager.exportRecording();
    }
    if (button.k == 3) {
      if (RecorderManager.recordingState != EnumRecordingState.PLAYING) {
        RecorderManager.playRecording();
      } else {
        RecorderManager.stopPlaying();
      }
    }
    if (button.k == 4) {
      if (RecorderManager.recordingState != EnumRecordingState.RECORDING) {
        RecorderManager.startRecording();
      } else {
        RecorderManager.stopRecording();
      }
    }
    if (button.k == 5)
    {
      RecorderManager.end = 0L;
      RecorderManager.movementInput.clear();
      RecorderManager.recordingTime = 0;
      RecorderManager.recordingState = EnumRecordingState.NONE;
      RecorderManager.x = 0.0D;
      RecorderManager.y = 0.0D;
      RecorderManager.z = 0.0D;
    }
    refresh();
    super.a(button);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1) {
      this.j.a(ModAPI.getLastScreen());
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    c();
    
    LabyMod.getInstance().draw.drawString("Save:", this.l / 2 - 100, this.m / 6 + 20);
    
    LabyMod.getInstance().draw.drawString("Recording:", this.l / 2 - 20, this.m / 6 + 20);
    LabyMod.getInstance().draw.drawString("Information:", this.l / 2 - 100, this.m / 6 + 80);
    
    LabyMod.getInstance().draw.drawString(Color.cl("7") + RecorderManager.end / 20L + " seconds", this.l / 2 - 100, this.m / 6 + 93);
    LabyMod.getInstance().draw.drawString(Color.cl("7") + RecorderManager.end + " frames", this.l / 2 - 100, this.m / 6 + 103);
    if (RecorderManager.movementInput.size() != 0) {
      LabyMod.getInstance().draw.drawString(Color.cl("7") + "Start position: " + (int)RecorderManager.x + ", " + (int)RecorderManager.y + ", " + (int)RecorderManager.z, this.l / 2 - 100, this.m / 6 + 123);
    }
    String status = Color.cl("7") + "Off";
    if (RecorderManager.recordingState == EnumRecordingState.RECORDING) {
      status = Color.cl("4") + "Recording";
    }
    if (RecorderManager.recordingState == EnumRecordingState.PLAYING) {
      status = Color.cl("a") + "Playing";
    }
    LabyMod.getInstance().draw.drawString("Status: " + status, 4.0D, 4.0D);
    
    super.a(mouseX, mouseY, partialTicks);
  }
}
