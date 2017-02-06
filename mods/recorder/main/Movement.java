package mods.recorder.main;

public class Movement
{
  boolean sneak;
  boolean sprint;
  float yaw;
  float pitch;
  boolean jump;
  float mf;
  float ms;
  
  public Movement(boolean sneak, boolean sprint, float yaw, float pitch, boolean jump, float mf, float ms)
  {
    this.sneak = sneak;
    this.sprint = sprint;
    this.yaw = yaw;
    this.pitch = pitch;
    this.jump = jump;
    this.mf = mf;
    this.ms = ms;
  }
}
