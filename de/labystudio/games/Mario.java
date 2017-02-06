package de.labystudio.games;

import bcf;
import bcz;
import bfb;
import bni;
import de.labystudio.gui.GuiGames;
import de.labystudio.labymod.LabyMod;
import de.labystudio.utils.DrawUtils;
import de.labystudio.utils.StatsLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Mario
  extends bfb
{
  DrawUtils draw;
  
  public Mario()
  {
    this.draw = LabyMod.getInstance().draw;
    this.thread = null;
  }
  
  int lengthX = 0;
  int posX = 1;
  int posY = 4;
  int lengthY = 0;
  int speed = 60;
  int score = 0;
  bcz quit;
  bcz restart;
  Thread thread;
  boolean gameOver = false;
  ArrayList<Location> terrain = new ArrayList();
  double fallDistance = 0.0D;
  int jumpVelocity = 0;
  Location playerLocation;
  
  static enum EnumDirection
  {
    UP,  RIGHT,  DOWN,  LEFT;
    
    private EnumDirection() {}
  }
  
  static enum EnumPixelType
  {
    PIXEL,  MARIO,  LAVA,  BLOCK,  CLOUD,  BUSH,  TUBE,  BONUS,  CLOUDBLOCK;
    
    private EnumPixelType() {}
  }
  
  class Location
  {
    int x = 0;
    int y = 0;
    Mario.EnumPixelType type = Mario.EnumPixelType.PIXEL;
    
    public Location(int x, int y, Mario.EnumPixelType type)
    {
      this.x = x;
      this.y = y;
      this.type = type;
    }
    
    public Location add(int x, int y)
    {
      this.x += x;
      this.y += y;
      return this;
    }
    
    public Location clone()
    {
      return new Location(Mario.this, this.x, this.y, this.type);
    }
    
    public int getX()
    {
      return this.x;
    }
    
    public int getY()
    {
      return this.y;
    }
    
    public boolean collideWith(Location loc)
    {
      if ((getX() == loc.getX()) && (getY() == loc.getY())) {
        return true;
      }
      return false;
    }
  }
  
  public boolean collideWithPixelType(Location loc, EnumPixelType pixel)
  {
    for (Location terrain : getTerrain()) {
      if ((terrain.collideWith(loc)) && (terrain.type == pixel)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean collideWithBlock(Location loc)
  {
    for (Location terrain : getTerrain()) {
      if ((terrain.collideWith(loc)) && (
        (terrain.type == EnumPixelType.BLOCK) || (terrain.type == EnumPixelType.TUBE) || (terrain.type == EnumPixelType.BONUS) || (terrain.type == EnumPixelType.CLOUDBLOCK))) {
        return true;
      }
    }
    return false;
  }
  
  public void b()
  {
    Keyboard.enableRepeatEvents(true);
    this.n.clear();
    
    this.quit = new bcz(0, 5, 5, 30, 20, de.labystudio.utils.Color.cl("c") + "Quit");
    this.n.add(this.quit);
    
    this.lengthX = (this.l / 10 - 2);
    this.lengthY = (this.m / 10 - 4);
    if (this.gameOver)
    {
      this.restart = new bcz(1, this.l / 2 - 100, this.m - 25, 200, 20, "Play again");
      this.n.add(this.restart);
      super.b();
      return;
    }
    if (this.thread == null) {
      create();
    }
    super.b();
  }
  
  private void create()
  {
    this.gameOver = false;
    buildTerrain();
    spawnPlayer();
    start();
  }
  
  private void spawnPlayer()
  {
    this.playerLocation = new Location(5, 2, EnumPixelType.MARIO);
  }
  
  public void moveTerrain(int x)
  {
    for (Location loc : getTerrain()) {
      loc.add(x, 0);
    }
    getPlayerLocation().add(x, 0);
  }
  
  private void buildTerrain()
  {
    for (int m = 0; m <= 50; m++) {
      this.terrain.add(new Location(-1, m, EnumPixelType.BLOCK));
    }
    int x = 0;
    int y = 14;
    for (int i = 0; i <= 400; i++)
    {
      if (i == 0) {
        for (int tt = 0; tt <= 20; tt++)
        {
          for (int m = 0; m <= 50; m++) {
            this.terrain.add(new Location(0 + x, y + m, EnumPixelType.BLOCK));
          }
          x++;
        }
      }
      for (int m = 0; m <= 50; m++) {
        this.terrain.add(new Location(0 + x, y + m, EnumPixelType.BLOCK));
      }
      if (LabyMod.getRandom().nextInt(20) == 0) {
        this.terrain.add(new Location(0 + x, y - 1, EnumPixelType.BUSH));
      }
      x++;
      if (LabyMod.getRandom().nextInt(10) == 0) {
        for (int t = 0; t <= 3; t++) {
          if (LabyMod.getRandom().nextBoolean())
          {
            x++;
            if (LabyMod.getRandom().nextBoolean())
            {
              if (y < 30) {
                y++;
              }
            }
            else if (y > 0) {
              y--;
            }
          }
        }
      }
      if (LabyMod.getRandom().nextInt(5) == 0)
      {
        int mt = 0;
        this.terrain.add(new Location(0 + x, y - mt - 1, EnumPixelType.TUBE));
        mt++;
        for (int t = 0; t <= 1; t++) {
          if (LabyMod.getRandom().nextBoolean())
          {
            this.terrain.add(new Location(0 + x, y - mt - 1, EnumPixelType.TUBE));
            mt++;
          }
        }
      }
      if (LabyMod.getRandom().nextInt(3) == 0)
      {
        int mt = 0;
        if (y - 5 > 0)
        {
          int yy = LabyMod.getRandom().nextInt(y - 5);
          for (int t = 0; t <= 4; t++) {
            if (LabyMod.getRandom().nextBoolean())
            {
              this.terrain.add(new Location(0 + x + mt, yy, EnumPixelType.CLOUD));
              mt++;
            }
          }
        }
      }
    }
  }
  
  public Location getPlayerLocation()
  {
    return this.playerLocation;
  }
  
  public Location getPlayerHeadLocation()
  {
    if (getPlayerLocation() != null)
    {
      Location loc = getPlayerLocation().clone().add(0, -1);
      return loc;
    }
    return null;
  }
  
  public boolean isPlayer()
  {
    return getPlayerLocation() != null;
  }
  
  public ArrayList<Location> getTerrain()
  {
    return this.terrain;
  }
  
  public boolean isOnGround()
  {
    return this.fallDistance == 0.0D;
  }
  
  public int getFallDistance()
  {
    return (int)this.fallDistance;
  }
  
  private void start()
  {
    this.thread = new Thread(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          if ((Mario.this.gameOver) || (Mario.this.j.m != Mario.this)) {
            return;
          }
          Mario.this.tick();
          try
          {
            synchronized (Mario.this.thread)
            {
              Mario.this.thread.wait(Mario.this.speed);
            }
          }
          catch (Exception e)
          {
            e.printStackTrace();
          }
        }
      }
    });
    this.thread.start();
  }
  
  private void gameOver()
  {
    if (!this.gameOver)
    {
      ArrayList<String> list = new ArrayList();
      if (StatsLoader.stats.containsKey("mario")) {
        list = (ArrayList)StatsLoader.stats.get("mario");
      }
      if (StatsLoader.isHighScore(this.score, list)) {
        list.add(0, "" + this.score);
      }
      if (list.size() > 15) {
        list.remove(15);
      }
      StatsLoader.stats.put("mario", list);
      StatsLoader.savestats();
    }
    this.gameOver = true;
    b();
  }
  
  int tick = 0;
  
  private void tick()
  {
    this.fallDistance += 1.0D;
    if (collideWithBlock(this.playerLocation.clone().add(0, 1))) {
      this.fallDistance = 0.0D;
    }
    if (isPlayer())
    {
      if ((!isOnGround()) && (LabyMod.getRandom().nextInt(getFallDistance()) != 0) && (this.jumpVelocity == 0) && 
        (!collideWithBlock(this.playerLocation.clone().add(0, 1)))) {
        this.playerLocation.add(0, 1);
      }
      if ((this.jumpVelocity > 0) && 
        (!collideWithBlock(this.playerLocation.clone().add(0, -1))))
      {
        this.playerLocation.add(0, -1);
        this.jumpVelocity -= 1;
      }
      if (collideWithPixelType(getPlayerLocation(), EnumPixelType.LAVA)) {
        gameOver();
      }
      if (getPlayerLocation().getX() > 30) {
        moveTerrain(-1);
      }
      if (getPlayerLocation().getY() > 30) {
        gameOver();
      }
    }
    handleKeyBoardInput();
    
    this.tick += 1;
  }
  
  protected void a(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    super.a(mouseX, mouseY, mouseButton);
  }
  
  public void a(bcz button)
    throws IOException
  {
    if (button.k == 0) {
      this.j.a(new GuiGames());
    }
    if (button.k == 1) {
      this.j.a(new Mario());
    }
    super.a(button);
  }
  
  protected void a(char typedChar, int keyCode)
    throws IOException
  {
    if (keyCode == 1)
    {
      this.j.a(new GuiGames());
      return;
    }
    super.a(typedChar, keyCode);
  }
  
  protected void handleKeyBoardInput()
  {
    if (!this.gameOver)
    {
      if (((Keyboard.isKeyDown(205)) || (Keyboard.isKeyDown(32))) && 
        (!collideWithBlock(getPlayerLocation().clone().add(1, 0))) && (!collideWithBlock(getPlayerHeadLocation().clone().add(1, 0))))
      {
        getPlayerLocation().add(1, 0);
        this.score += 1;
      }
      if (((Keyboard.isKeyDown(203)) || (Keyboard.isKeyDown(30))) && 
        (!collideWithBlock(getPlayerLocation().clone().add(-1, 0))) && (!collideWithBlock(getPlayerHeadLocation().clone().add(-1, 0))))
      {
        getPlayerLocation().add(-1, 0);
        this.score -= 1;
      }
      if ((Keyboard.isKeyDown(57)) && 
        (isOnGround())) {
        this.jumpVelocity = 3;
      }
    }
  }
  
  public void a(int mouseX, int mouseY, float partialTicks)
  {
    if (LabyMod.getInstance().isInGame())
    {
      bni.m();
      this.draw.drawTransparentBackground(0, 32, this.l, this.m - 33);
    }
    else
    {
      c();
      this.draw.drawChatBackground(0, 32, this.l, this.m - 33);
    }
    DrawUtils.a(0, 0, this.l, this.m, new java.awt.Color(107, 140, 255).getRGB());
    for (Iterator localIterator1 = getTerrain().iterator(); localIterator1.hasNext();)
    {
      terrain = (Location)localIterator1.next();
      if (terrain.type == EnumPixelType.CLOUD) {
        drawPixel(terrain.getX(), terrain.getY(), java.awt.Color.WHITE.getRGB());
      }
    }
    Location loc = getPlayerLocation();
    if (loc != null)
    {
      drawPixel(loc.getX(), loc.getY(), java.awt.Color.BLUE.getRGB());
      loc = getPlayerHeadLocation();
      drawPixel(loc.getX(), loc.getY(), java.awt.Color.BLUE.getRGB());
    }
    for (Location terrain = getTerrain().iterator(); terrain.hasNext();)
    {
      terrain = (Location)terrain.next();
      if (terrain.type == EnumPixelType.LAVA) {
        drawPixel(terrain.getX(), terrain.getY(), java.awt.Color.ORANGE.getRGB());
      }
      if (terrain.type == EnumPixelType.BLOCK) {
        drawPixel(terrain.getX(), terrain.getY(), new java.awt.Color(231, 99, 24).getRGB());
      }
      if (terrain.type == EnumPixelType.TUBE) {
        drawPixel(terrain.getX(), terrain.getY(), new java.awt.Color(0, 173, 0).getRGB());
      }
      if (terrain.type == EnumPixelType.BUSH) {
        drawPixel(terrain.getX(), terrain.getY(), new java.awt.Color(189, 247, 24).getRGB());
      }
      if (terrain.type == EnumPixelType.CLOUDBLOCK) {
        drawPixel(terrain.getX(), terrain.getY(), new java.awt.Color(239, 239, 239).getRGB());
      }
      if (terrain.type == EnumPixelType.BONUS) {
        drawPixel(terrain.getX(), terrain.getY(), new java.awt.Color(255, 165, 66).getRGB());
      }
    }
    Location terrain;
    ArrayList<Location> toRem = new ArrayList();
    for (Location lm : getTerrain()) {
      if (loc.getX() < 0) {
        toRem.add(loc);
      }
    }
    for (Location ll : toRem) {
      getTerrain().remove(ll);
    }
    if (this.gameOver)
    {
      GL11.glPushMatrix();
      int k = 3;
      GL11.glScaled(k, k, k);
      this.draw.drawCenteredString(de.labystudio.utils.Color.cl("c") + "Game Over", this.l / 2 / k, (this.m / 4 - 5) / k);
      GL11.glPopMatrix();
      int i;
      if (StatsLoader.stats.containsKey("mario"))
      {
        ArrayList<String> list = (ArrayList)StatsLoader.stats.get("mario");
        i = 1;
        for (String sc : list)
        {
          this.draw.drawString(i + ". Place - " + de.labystudio.utils.Color.cl("b") + sc + "m", this.l / 2 - 43, this.m / 4 - 5 + i * 10 + 20);
          i++;
          if (i > 10) {
            break;
          }
        }
      }
      else
      {
        this.draw.drawCenteredString(de.labystudio.utils.Color.cl("f") + "No stats found", this.l / 2, this.m / 4 - 5 + 30);
      }
    }
    bni.l();
    this.draw.overlayBackground(0, 32);
    this.draw.overlayBackground(this.m - 33, this.m);
    
    int s = this.score;
    if (s < 0) {
      s = 0;
    }
    this.draw.drawString("Score: " + s + "m", 5.0D, this.m - 24);
    
    super.a(mouseX, mouseY, partialTicks);
  }
  
  public void drawPixel(int x, int y, int color)
  {
    DrawUtils.a(x * 10, y * 10, (x + 1) * 10, (y + 1) * 10, color);
  }
}
