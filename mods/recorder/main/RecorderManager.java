package mods.recorder.main;

import bcf;
import bcu;
import bda;
import bmr;
import bmt;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.Module;
import de.labystudio.utils.Color;
import fa;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RecorderManager
  extends Module
{
  public static EnumRecordingState recordingState = EnumRecordingState.NONE;
  public static HashMap<Integer, Movement> movementInput = new HashMap();
  public static double x;
  public static double y;
  public static double z;
  public static long end;
  public static boolean allowed = true;
  public static int recordingTime = 0;
  
  public void onEnable()
  {
    ModAPI.addSettingsButton("Recorder", new GuiManager());
  }
  
  public void pluginMessage(String key, boolean value)
  {
    if (key.equalsIgnoreCase("recorder")) {
      allowed = value;
    }
  }
  
  public static void startRecording()
  {
    recordingTime = 0;
    movementInput.clear();
    end = 0L;
    recordingState = EnumRecordingState.RECORDING;
    bcf.z().r.d().a(new fa(Color.cl("7") + "[" + Color.cl("4") + "Recorder" + Color.cl("7") + "]" + Color.cl("e") + " Started recording!"));
  }
  
  public static void stopRecording()
  {
    end = recordingTime;
    recordingTime = 0;
    recordingState = EnumRecordingState.NONE;
    bcf.z().r.d().a(new fa(Color.cl("7") + "[" + Color.cl("4") + "Recorder" + Color.cl("7") + "]" + Color.cl("e") + " Stopped recording! Saved " + end / 20L + " seconds."));
  }
  
  public static void playRecording()
  {
    recordingTime = 0;
    recordingState = EnumRecordingState.PLAYING;
    bcf.z().r.d().a(new fa(Color.cl("7") + "[" + Color.cl("4") + "Recorder" + Color.cl("7") + "]" + Color.cl("e") + " Play recording.."));
  }
  
  public static void stopPlaying()
  {
    recordingTime = 0;
    recordingState = EnumRecordingState.NONE;
    bcf.z().r.d().a(new fa(Color.cl("7") + "[" + Color.cl("4") + "Recorder" + Color.cl("7") + "]" + Color.cl("e") + " Stopped playing!"));
  }
  
  public static void importRecording()
  {
    File file = selectFile();
    if ((file == null) || (!file.exists())) {
      return;
    }
    recordingState = EnumRecordingState.NONE;
    end = 0L;
    recordingTime = 0;
    try
    {
      BufferedReader br = new BufferedReader(new FileReader(file));
      try
      {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> lines = new ArrayList();
        String line = br.readLine();
        while (line != null)
        {
          lines.add(line);
          line = br.readLine();
        }
        if (lines.size() != 0)
        {
          setup = true;
          for (String l : lines) {
            if (setup)
            {
              setup = false;
              String[] info = l.split(",");
              end = Integer.parseInt(info[0]);
              x = Double.parseDouble(info[1]);
              y = Double.parseDouble(info[2]);
              z = Double.parseDouble(info[3]);
            }
            else
            {
              String[] info = l.split(",");
              movementInput.put(Integer.valueOf(Integer.parseInt(info[0])), new Movement(
                Boolean.parseBoolean(info[1]), 
                Boolean.parseBoolean(info[2]), 
                Float.parseFloat(info[3]), 
                Float.parseFloat(info[4]), 
                Boolean.parseBoolean(info[5]), 
                Float.parseFloat(info[6]), 
                Float.parseFloat(info[7])));
            }
          }
        }
      }
      finally
      {
        boolean setup;
        br.close();
      }
    }
    catch (Exception error)
    {
      error.printStackTrace();
    }
  }
  
  public static void exportRecording()
  {
    File file = selectFile();
    try
    {
      PrintWriter writer = new PrintWriter(file, "UTF-8");
      writer.print(end + "," + x + "," + y + "," + z);
      for (Integer time : movementInput.keySet())
      {
        Movement move = (Movement)movementInput.get(time);
        writer.println(time + "," + move.sneak + "," + move.sprint + "," + move.yaw + "," + move.pitch + "," + move.jump + "," + move.mf + "," + move.ms);
      }
      writer.close();
    }
    catch (Exception localException) {}
  }
  
  private static File selectFile()
  {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Movement recording file", new String[] { "mrf" });
    chooser.setFileFilter(filter);
    chooser.setMultiSelectionEnabled(false);
    chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop/"));
    chooser.setDialogTitle("Select your recording");
    
    JFrame frame = new JFrame();
    
    chooser.showOpenDialog(frame.getParent());
    return chooser.getSelectedFile();
  }
  
  public static void tick()
  {
    if (!LabyMod.getInstance().isInGame()) {
      return;
    }
    if (!allowed) {
      return;
    }
    if (recordingState == EnumRecordingState.NONE) {
      return;
    }
    if (bcf.z().m != null) {
      return;
    }
    bmt player = bcf.z().h;
    bmr movementInputFromOptions = player.e;
    if (recordingState == EnumRecordingState.RECORDING)
    {
      Movement m = new Movement(movementInputFromOptions.h, player.aL(), player.v, player.w, movementInputFromOptions.g, movementInputFromOptions.b, movementInputFromOptions.a);
      movementInput.put(Integer.valueOf(recordingTime), m);
      if (recordingTime == 0)
      {
        x = player.p;
        y = player.q;
        z = player.r;
      }
    }
    else
    {
      if (movementInput.containsKey(Integer.valueOf(recordingTime)))
      {
        Movement m = (Movement)movementInput.get(Integer.valueOf(recordingTime));
        movementInputFromOptions.g = m.jump;
        movementInputFromOptions.b = m.mf;
        movementInputFromOptions.a = m.ms;
        movementInputFromOptions.h = m.sneak;
        player.e(m.sprint);
        player.v = m.yaw;
        player.w = m.pitch;
      }
      if (recordingTime >= end) {
        stopPlaying();
      }
    }
    recordingTime += 1;
  }
}
