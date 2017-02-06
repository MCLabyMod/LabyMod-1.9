package installer;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;

public class ProgressBarUpdater
  extends Thread
{
  public static String title = "Reading files..";
  public static String subTitle = "Please wait!";
  public static int value = 0;
  public static double max = 0.0D;
  public static double calc = 0.0D;
  private JProgressBar progressBar;
  private JTextArea titleArea;
  private JTextArea subTitleArea;
  
  public ProgressBarUpdater(JProgressBar progressBar, JTextArea title, JTextArea subTitle)
  {
    this.progressBar = progressBar;
    this.titleArea = title;
    this.subTitleArea = subTitle;
    start();
  }
  
  public static void setMax(int max)
  {
    max = max;
  }
  
  public static void next()
  {
    calc += 1.0D;
    value = (int)Math.round(100.0D / max * calc);
  }
  
  public void run()
  {
    for (;;)
    {
      this.progressBar.setValue(value);
      this.titleArea.setText(title);
      this.subTitleArea.setText(subTitle);
      if (value == 100)
      {
        this.progressBar.setValue(100);
        break;
      }
      try
      {
        synchronized (this)
        {
          wait(10L);
        }
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
