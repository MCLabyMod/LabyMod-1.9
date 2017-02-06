package installer.labystudio.frame;

import installer.Main;
import installer.ModTemplate;
import installer.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrameCompatibleMods
  extends JFrame
{
  JTextArea textArea;
  JButton clearButton;
  
  public FrameCompatibleMods()
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
    
    JButton btnNext = new JButton("Next");
    btnNext.setFont(new Font("Dialog", 0, 15));
    btnNext.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FrameCompatibleMods.this.dispose();
        new FrameList();
      }
    });
    btnNext.setBounds(435, 331, 220, 39);
    mainPanel.add(btnNext);
    
    JPanel panel = new JPanel();
    panel.setBounds(10, 11, 278, 47);
    mainPanel.add(panel);
    panel.setLayout(null);
    
    JLabel lblAddOtherMods = new JLabel();
    lblAddOtherMods.setText("Add other mods");
    lblAddOtherMods.setPreferredSize(new Dimension(385, 42));
    lblAddOtherMods.setOpaque(false);
    lblAddOtherMods.setHorizontalAlignment(0);
    lblAddOtherMods.setFont(new Font("Dialog", 1, 19));
    lblAddOtherMods.setEnabled(true);
    lblAddOtherMods.setBounds(28, 11, 227, 21);
    panel.add(lblAddOtherMods);
    
    JPanel modList = new JPanel();
    modList.setLayout(null);
    modList.setBounds(10, 63, 278, 307);
    mainPanel.add(modList);
    
    int y = 0;
    for (final ModTemplate mod : Main.modTempates)
    {
      JCheckBox checkBox = new JCheckBox(mod.getModName());
      checkBox.setSelected(mod.isEnabled());
      checkBox.setBounds(6, 7 + y, 266, 14);
      checkBox.addItemListener(new ItemListener()
      {
        public void itemStateChanged(ItemEvent e)
        {
          mod.setEnabled(e.getStateChange() == 1);
        }
      });
      modList.add(checkBox);
      y += 20;
    }
    this.textArea = new JTextArea();
    this.textArea.setBounds(10, 5 + Main.modTempates.size() * 20, 258, 246 - Main.modTempates.size() * 20);
    this.textArea.setEditable(false);
    this.textArea.setEnabled(true);
    this.textArea.setFont(new Font("Dialog", 0, 10));
    this.textArea.setLineWrap(true);
    this.textArea.setOpaque(false);
    this.textArea.setText("");
    this.textArea.setWrapStyleWord(true);
    modList.add(this.textArea);
    
    JButton button = new JButton("Choose file");
    button.setBounds(91, 267, 177, 29);
    button.setFont(new Font("Dialog", 0, 15));
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FrameCompatibleMods.this.selectMods();
        FrameCompatibleMods.this.initList();
      }
    });
    modList.add(button);
    
    this.clearButton = new JButton("Clear");
    this.clearButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Main.mods = null;
        FrameCompatibleMods.this.initList();
      }
    });
    this.clearButton.setFont(new Font("Dialog", 0, 15));
    this.clearButton.setBounds(8, 267, 73, 29);
    modList.add(this.clearButton);
    initList();
    
    JButton btnBack = new JButton("Back");
    btnBack.setFont(new Font("Dialog", 0, 15));
    btnBack.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FrameCompatibleMods.this.dispose();
        new FrameMain();
      }
    });
    btnBack.setBounds(298, 331, 126, 39);
    mainPanel.add(btnBack);
    
    JLabel label = new JLabel(new ImageIcon(FrameCompatibleMods.class.getResource("/installer/images/addons.jpg")));
    label.setBounds(298, 11, 357, 309);
    mainPanel.add(label);
    
    JTextArea textArea = new JTextArea();
    show();
  }
  
  private void initList()
  {
    String list = "";
    if (Main.mods != null)
    {
      File[] arrayOfFile;
      int j = (arrayOfFile = Main.mods).length;
      for (int i = 0; i < j; i++)
      {
        File mod = arrayOfFile[i];
        list = list + "+ " + mod.getName() + "\n";
      }
    }
    this.textArea.setText(list);
    if (this.clearButton != null) {
      this.clearButton.setEnabled(!list.isEmpty());
    }
  }
  
  private boolean selectMods()
  {
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Minecraft Mods", new String[] { "jar", "zip" });
    chooser.setFileFilter(filter);
    chooser.setMultiSelectionEnabled(true);
    chooser.setCurrentDirectory(new File(Utils.getDesktop()));
    chooser.setDialogTitle("Minecraft Mod");
    int returnVal = chooser.showOpenDialog(getParent());
    if (returnVal != 0) {
      return false;
    }
    if (Main.mods == null) {
      Main.mods = chooser.getSelectedFiles();
    } else {
      Main.mods = Utils.concat(Main.mods, chooser.getSelectedFiles());
    }
    return true;
  }
}
