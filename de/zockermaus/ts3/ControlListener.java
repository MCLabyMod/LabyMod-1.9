package de.zockermaus.ts3;

public abstract interface ControlListener
{
  public abstract void onPokeRecieved(TeamSpeakUser paramTeamSpeakUser, String paramString);
  
  public abstract void onClientDisconnected(TeamSpeakUser paramTeamSpeakUser, String paramString);
  
  public abstract void onClientTimout(TeamSpeakUser paramTeamSpeakUser);
  
  public abstract void onClientConnect(TeamSpeakUser paramTeamSpeakUser);
  
  public abstract void onMessageRecieved(TeamSpeakUser paramTeamSpeakUser1, TeamSpeakUser paramTeamSpeakUser2, String paramString);
  
  public abstract void onClientStartTyping(TeamSpeakUser paramTeamSpeakUser);
  
  public abstract void onDisconnect();
  
  public abstract void onConnect();
  
  public abstract void onChannelMessageRecieved(TeamSpeakUser paramTeamSpeakUser, String paramString);
  
  public abstract void onServerMessageRecieved(TeamSpeakUser paramTeamSpeakUser, String paramString);
  
  public abstract void onError(int paramInt, String paramString);
}
