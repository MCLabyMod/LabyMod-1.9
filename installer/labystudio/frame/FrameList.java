package installer.labystudio.frame;

import installer.Main;
import installer.ModTemplate;
import installer.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class FrameList
  extends JFrame
{
  JTextArea textArea;
  
  public FrameList()
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
    
    JButton btnNext = new JButton("Install LabyMod");
    btnNext.setFont(new Font("Dialog", 0, 15));
    btnNext.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FrameList.this.dispose();
        new FrameInstall();
      }
    });
    btnNext.setBounds(443, 330, 212, 39);
    mainPanel.add(btnNext);
    
    JPanel panel = new JPanel();
    panel.setBounds(317, 11, 338, 49);
    mainPanel.add(panel);
    panel.setLayout(null);
    
    JLabel title = new JLabel();
    title.setText("Install list");
    title.setPreferredSize(new Dimension(385, 42));
    title.setOpaque(false);
    title.setHorizontalAlignment(0);
    title.setFont(new Font("Dialog", 1, 19));
    title.setEnabled(true);
    title.setBounds(53, 11, 227, 21);
    panel.add(title);
    
    JPanel listPanel = new JPanel();
    listPanel.setLayout(null);
    listPanel.setBounds(317, 69, 338, 250);
    mainPanel.add(listPanel);
    
    this.textArea = new JTextArea();
    this.textArea.setBounds(10, 11, 318, 203);
    this.textArea.setEditable(false);
    this.textArea.setEnabled(true);
    this.textArea.setFont(new Font("Arial", 2, 14));
    this.textArea.setLineWrap(true);
    this.textArea.setOpaque(false);
    String list = "- LabyMod " + Main.modVersion + " for Minecraft " + Main.mcVersion + "\n";
    boolean showProhibited = false;
    String p;
    for (ModTemplate mod : Main.modTempates) {
      if (mod.isEnabled())
      {
        p = "";
        if (mod.isProhibited())
        {
          p = "* ";
          showProhibited = true;
        }
        list = list + "- " + p + mod.getModName() + "\n";
      }
    }
    if (Main.mods != null)
    {
      File[] arrayOfFile;
      p = (arrayOfFile = Main.mods).length;
      for (String str1 = 0; str1 < p; str1++)
      {
        File mod = arrayOfFile[str1];
        list = list + "- " + mod.getName() + "\n";
      }
    }
    this.textArea.setText(list);
    this.textArea.setWrapStyleWord(true);
    listPanel.add(this.textArea);
    if (showProhibited)
    {
      JTextArea txtrLabymod = new JTextArea();
      txtrLabymod.setWrapStyleWord(true);
      txtrLabymod.setLineWrap(true);
      txtrLabymod.setText("* Not allowed on every server");
      txtrLabymod.setOpaque(false);
      txtrLabymod.setFont(new Font("Arial", 1, 14));
      txtrLabymod.setEnabled(true);
      txtrLabymod.setEditable(false);
      txtrLabymod.setBounds(10, 218, 318, 21);
      listPanel.add(txtrLabymod);
    }
    JButton btnBack = new JButton("Back");
    btnBack.setFont(new Font("Dialog", 0, 15));
    btnBack.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FrameList.this.dispose();
        new FrameCompatibleMods();
      }
    });
    btnBack.setBounds(317, 330, 114, 39);
    mainPanel.add(btnBack);
    
    JLabel label = new JLabel(new ImageIcon(FrameList.class.getResource("/installer/images/features.jpg")));
    label.setHorizontalAlignment(2);
    label.setBounds(10, 11, 297, 359);
    mainPanel.add(label);
    
    JTextArea textArea = new JTextArea();
    show();
  }
}
