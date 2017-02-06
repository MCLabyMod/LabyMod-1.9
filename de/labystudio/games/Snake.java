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
import java.util.List;
import java.util.Random;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Snake
  extends bfb
{
  DrawUtils draw;
  
  public Snake()
  {
    this.draw = LabyMod.getInstance().draw;
  }
  
  int lengthX = 0;
  int posX = 1;
  int posY = 4;
  int lengthY = 0;
  int speed = 120;
  int powerUpAmount = 40;
  boolean cooldown = false;
  bcz quit;
  bcz restart;
  int score = 0;
  Thread thread;
  boolean gameOver = false;
  EnumDirection snakeDirection = EnumDirection.DOWN;
  ArrayList<Location> points = new ArrayList();
  ArrayList<Location> snake = new ArrayList();
  
  static enum EnumDirection
  {
    UP,  RIGHT,  DOWN,  LEFT;
    
    private EnumDirection() {}
  }
  
  static enum EnumPixelType
  {
    PIXEL,  SNEAK,  FRUIT,  INCREASESPEED,  DECREASESPEED,  MOREFRUITS;
    
    private EnumPixelType() {}
  }
  
  class Location
  {
    int x = 0;
    int y = 0;
    Snake.EnumPixelType type = Snake.EnumPixelType.PIXEL;
    
    public Location(int x, int y, Snake.EnumPixelType type)
    {
      this.x = x;
      this.y = y;
      this.type = type;
    }
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
    if (this.thread == null)
    {
      create();
    }
    else
    {
      this.points.clear();
      addPoint();
      if ((getHead().x > this.lengthX) || (getHead().y > this.lengthY)) {
        expand(this.lengthX / 2, this.lengthY / 2);
      }
    }
    super.b();
  }
  
  private void create()
  {
    this.score = 0;
    this.snakeDirection = EnumDirection.DOWN;
    this.gameOver = false;
    this.snake.clear();
    for (int i = 0; i < 3; i++) {
      expand(this.lengthX / 2, this.lengthY / 2 + i);
    }
    addPoint();
    start();
  }
  
  private void start()
  {
    this.thread = new Thread(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          if ((Snake.this.gameOver) || (Snake.this.j.m != Snake.this)) {
            return;
          }
          Snake.this.tick();
          try
          {
            synchronized (Snake.this.thread)
            {
              Snake.this.thread.wait(Snake.this.speed);
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
      if (StatsLoader.stats.containsKey("snake")) {
        list = (ArrayList)StatsLoader.stats.get("snake");
      }
      if (StatsLoader.isHighScore(this.score, list)) {
        list.add(0, "" + this.score);
      }
      if (list.size() > 15) {
        list.remove(15);
      }
      StatsLoader.stats.put("snake", list);
      StatsLoader.savestats();
    }
    this.gameOver = true;
    b();
  }
  
  private void tick()
  {
    boolean devalue = true;
    if (this.snakeDirection == EnumDirection.UP)
    {
      Location head = getHead();
      if (head.y < this.posY) {
        head.y = this.lengthY;
      }
      devalue = expand(head.x, head.y - 1);
    }
    if (this.snakeDirection == EnumDirection.DOWN)
    {
      Location head = getHead();
      if (head.y > this.lengthY) {
        head.y = this.posY;
      }
      devalue = expand(head.x, head.y + 1);
    }
    if (this.snakeDirection == EnumDirection.RIGHT)
    {
      Location head = getHead();
      if (head.x > this.lengthX) {
        head.x = this.posX;
      }
      devalue = expand(head.x + 1, head.y);
    }
    if (this.snakeDirection == EnumDirection.LEFT)
    {
      Location head = getHead();
      if (head.x < this.posX) {
        head.x = this.lengthX;
      }
      devalue = expand(head.x - 1, head.y);
    }
    if (devalue) {
      devalue();
    }
    this.cooldown = false;
  }
  
  private Location getHead()
  {
    ArrayList<Location> s = getSnake();
    if (s.size() == 0) {
      return new Location(0, 0, EnumPixelType.PIXEL);
    }
    return (Location)s.get(s.size() - 1);
  }
  
  private void devalue()
  {
    if (this.snake != null) {
      this.snake.remove(0);
    }
  }
  
  private void setSpeed(int speed)
  {
    if (speed > 300) {
      speed = 300;
    }
    if (speed < 10) {
      speed = 10;
    }
    this.speed = speed;
  }
  
  private boolean expand(int x, int y)
  {
    Location l = new Location(x, y, EnumPixelType.SNEAK);
    for (Location a : getSnake()) {
      if (a != null) {
        if ((a.x == x) && (a.y == y))
        {
          gameOver();
          return false;
        }
      }
    }
    for (Location a : this.points) {
      if (a != null) {
        if ((a.x == x) && (a.y == y))
        {
          addScore();
          addPoint();
          if (a.type == EnumPixelType.MOREFRUITS)
          {
            addScore();
            addPoint();
          }
          if (a.type == EnumPixelType.INCREASESPEED)
          {
            addScore();
            setSpeed(this.speed - LabyMod.getRandom().nextInt(50));
          }
          if (a.type == EnumPixelType.DECREASESPEED)
          {
            addScore();
            setSpeed(this.speed + LabyMod.getRandom().nextInt(50));
          }
          this.snake.add(l);
          this.points.remove(a);
          return false;
        }
      }
    }
    this.snake.add(l);
    return true;
  }
  
  private void addScore()
  {
    this.score += 10;
  }
  
  public void addPoint()
  {
    EnumPixelType type = EnumPixelType.FRUIT;
    int i = LabyMod.getRandom().nextInt(this.powerUpAmount);
    if (i == 0) {
      type = EnumPixelType.INCREASESPEED;
    }
    if (i == 1) {
      type = EnumPixelType.DECREASESPEED;
    }
    if (i == 2) {
      type = EnumPixelType.MOREFRUITS;
    }
    Location l = new Location(LabyMod.getRandom().nextInt(this.lengthX - this.posX) + this.posX, LabyMod.getRandom().nextInt(this.lengthY - this.posY) + this.posY, type);
    for (Location a : this.points) {
      if (a != null) {
        if ((a.x == l.x) && (a.y == l.y))
        {
          addPoint();
          return;
        }
      }
    }
    this.points.add(l);
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
      this.j.a(new Snake());
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
    if ((!this.gameOver) && (!this.cooldown))
    {
      boolean boost = true;
      if (((keyCode == 200) || (keyCode == 17)) && 
        (this.snakeDirection != EnumDirection.DOWN) && (this.snakeDirection != EnumDirection.UP))
      {
        this.snakeDirection = EnumDirection.UP;
        this.cooldown = true;
        boost = false;
      }
      if (((keyCode == 208) || (keyCode == 31)) && 
        (this.snakeDirection != EnumDirection.UP) && (this.snakeDirection != EnumDirection.DOWN))
      {
        this.snakeDirection = EnumDirection.DOWN;
        this.cooldown = true;
        boost = false;
      }
      if (((keyCode == 205) || (keyCode == 32)) && 
        (this.snakeDirection != EnumDirection.LEFT) && (this.snakeDirection != EnumDirection.RIGHT))
      {
        this.snakeDirection = EnumDirection.RIGHT;
        this.cooldown = true;
        boost = false;
      }
      if (((keyCode == 203) || (keyCode == 30)) && 
        (this.snakeDirection != EnumDirection.RIGHT) && (this.snakeDirection != EnumDirection.LEFT))
      {
        this.snakeDirection = EnumDirection.LEFT;
        this.cooldown = true;
        boost = false;
      }
      if ((boost) && 
        (LabyMod.getRandom().nextInt(3) == 0)) {
        tick();
      }
    }
    super.a(typedChar, keyCode);
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
    for (Location loc : getSnake()) {
      if (loc != null) {
        drawPixel(loc.x, loc.y, java.awt.Color.WHITE.getRGB());
      }
    }
    for (Location loc : getPoints()) {
      if (loc != null)
      {
        if (loc.type == EnumPixelType.FRUIT) {
          drawPixel(loc.x, loc.y, java.awt.Color.RED.getRGB());
        }
        if (loc.type == EnumPixelType.MOREFRUITS) {
          drawPixel(loc.x, loc.y, java.awt.Color.ORANGE.getRGB());
        }
        if (loc.type == EnumPixelType.INCREASESPEED) {
          drawPixel(loc.x, loc.y, java.awt.Color.CYAN.getRGB());
        }
        if (loc.type == EnumPixelType.DECREASESPEED) {
          drawPixel(loc.x, loc.y, java.awt.Color.BLUE.getRGB());
        }
      }
    }
    if (this.gameOver)
    {
      GL11.glPushMatrix();
      int k = 3;
      GL11.glScaled(k, k, k);
      this.draw.drawCenteredString(de.labystudio.utils.Color.cl("c") + "Game Over", this.l / 2 / k, (this.m / 4 - 5) / k);
      GL11.glPopMatrix();
      int i;
      if (StatsLoader.stats.containsKey("snake"))
      {
        ArrayList<String> list = (ArrayList)StatsLoader.stats.get("snake");
        i = 1;
        for (String sc : list)
        {
          this.draw.drawString(i + ". Place - " + de.labystudio.utils.Color.cl("b") + sc + " Points", this.l / 2 - 50, this.m / 4 - 5 + i * 10 + 20);
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
    
    this.draw.drawString("Score: " + this.score, 5.0D, this.m - 24);
    this.draw.drawString("Speed: " + this.speed / 1000.0D + " pixel/s", 5.0D, this.m - 13);
    
    super.a(mouseX, mouseY, partialTicks);
  }
  
  private ArrayList<Location> getSnake()
  {
    ArrayList<Location> s = new ArrayList();
    s.addAll(this.snake);
    return s;
  }
  
  private ArrayList<Location> getPoints()
  {
    ArrayList<Location> s = new ArrayList();
    s.addAll(this.points);
    return s;
  }
  
  public void drawPixel(int x, int y, int color)
  {
    DrawUtils.a(x * 10, y * 10, (x + 1) * 10, (y + 1) * 10, color);
  }
}
