package installer.labystudio.frame;

import installer.Installer;
import installer.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class FrameInstall
  extends JFrame
{
  private JProgressBar progressBar;
  private JTextArea installTitle;
  private JTextArea installSubTitle;
  
  public JProgressBar getProgressBar()
  {
    return this.progressBar;
  }
  
  public JTextArea getInstallTitle()
  {
    return this.installTitle;
  }
  
  public JTextArea getInstallSubTitle()
  {
    return this.installSubTitle;
  }
  
  public FrameInstall()
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e1)
    {
      e1.printStackTrace();
    }
    setDefaultCloseOperation(3);
    setVisible(true);
    setSize(452, 145);
    setTitle("LabyMod Installer");
    setResizable(false);
    Utils.centerWindow(this, null);
    try
    {
      BufferedImage image = ImageIO.read(getClass().getResource("/installer/images/icon.png"));
      setIconImage(image);
    }
    catch (IOException localIOException) {}
    JPanel mainPanel = new JPanel();
    mainPanel.setBackground(Color.WHITE);
    setContentPane(mainPanel);
    mainPanel.setLayout(null);
    
    this.progressBar = new JProgressBar();
    this.progressBar.setForeground(new Color(255, 204, 102));
    this.progressBar.setValue(0);
    this.progressBar.setStringPainted(true);
    this.progressBar.setBounds(10, 36, 423, 39);
    mainPanel.add(this.progressBar);
    
    this.installTitle = new JTextArea();
    this.installTitle.setText("Installing LabyMod..");
    this.installTitle.setPreferredSize(new Dimension(290, 144));
    this.installTitle.setOpaque(false);
    this.installTitle.setFont(new Font("Dialog", 0, 12));
    this.installTitle.setEnabled(true);
    this.installTitle.setEditable(false);
    this.installTitle.setBounds(10, 11, 384, 19);
    mainPanel.add(this.installTitle);
    
    JButton btnCancel = new JButton("Cancel");
    btnCancel.setBounds(344, 79, 89, 23);
    btnCancel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        System.exit(0);
      }
    });
    mainPanel.add(btnCancel);
    
    this.installSubTitle = new JTextArea();
    this.installSubTitle.setText("Setup installer..");
    this.installSubTitle.setPreferredSize(new Dimension(290, 144));
    this.installSubTitle.setOpaque(false);
    this.installSubTitle.setFont(new Font("Dialog", 0, 12));
    this.installSubTitle.setEditable(false);
    this.installSubTitle.setBounds(10, 79, 321, 23);
    mainPanel.add(this.installSubTitle);
    
    JTextArea textArea = new JTextArea();
    show();
    
    new Installer(this);
  }
}
