package installer.labystudio.frame;

import installer.Main;
import installer.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class FrameMain
  extends JFrame
{
  private JTextArea infoArea;
  
  public FrameMain()
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
    setSize(671, 410);
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
    
    JButton button = new JButton("Next");
    button.setFont(new Font("Dialog", 0, 15));
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FrameMain.this.dispose();
        new FrameCompatibleMods();
      }
    });
    button.setBounds(355, 332, 300, 39);
    mainPanel.add(button);
    
    Panel imagePanel = new Panel();
    imagePanel.setBounds(0, 0, 349, 381);
    mainPanel.add(imagePanel);
    
    Panel textPanel = new Panel();
    textPanel.setBackground(new Color(250, 250, 250));
    textPanel.setBounds(355, 10, 300, 316);
    mainPanel.add(textPanel);
    
    JTextArea textArea = new JTextArea();
    textPanel.setLayout(null);
    this.infoArea = new JTextArea();
    this.infoArea.setBounds(10, 78, 280, 238);
    this.infoArea.setEditable(false);
    this.infoArea.setEnabled(true);
    this.infoArea.setFont(new Font("Dialog", 0, 12));
    this.infoArea.setLineWrap(true);
    this.infoArea.setOpaque(false);
    this.infoArea.setPreferredSize(new Dimension(290, 144));
    this.infoArea.setText(Main.text);
    this.infoArea.setWrapStyleWord(true);
    textPanel.add(this.infoArea);
    
    JLabel titleLabel = new JLabel();
    titleLabel.setText("LabyMod v" + Main.modVersion);
    titleLabel.setPreferredSize(new Dimension(385, 42));
    titleLabel.setOpaque(false);
    titleLabel.setFont(new Font("Dialog", 1, 31));
    titleLabel.setHorizontalAlignment(0);
    titleLabel.setEnabled(true);
    titleLabel.setBounds(10, 11, 280, 39);
    textPanel.add(titleLabel);
    
    JLabel subLabel = new JLabel();
    subLabel.setText("for Minecraft " + Main.mcVersion);
    subLabel.setPreferredSize(new Dimension(385, 42));
    subLabel.setOpaque(false);
    subLabel.setHorizontalAlignment(0);
    subLabel.setFont(new Font("Dialog", 1, 19));
    subLabel.setEnabled(true);
    subLabel.setBounds(10, 49, 280, 21);
    textPanel.add(subLabel);
    
    imagePanel.setLayout(null);
    JLabel imageLabel = new JLabel(new ImageIcon(FrameMain.class.getResource("/installer/images/sideImage.png")));
    imageLabel.setBounds(0, 0, 349, 381);
    imagePanel.add(imageLabel);
    show();
  }
}
