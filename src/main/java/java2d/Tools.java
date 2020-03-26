//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterJob;
import java.security.AccessControlException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class Tools extends JPanel implements ActionListener, ChangeListener, Runnable {
  public JToggleButton toggleB;
  public JButton printB;
  public JComboBox screenCombo;
  public JToggleButton renderB;
  public JToggleButton aliasB;
  public JToggleButton textureB;
  public JToggleButton compositeB;
  public JButton startStopB;
  public JButton cloneB;
  public boolean issueRepaint = true;
  public JToolBar toolbar;
  public JSlider slider;
  public boolean doSlider;
  public boolean isExpanded;
  protected boolean focus;
  private ImageIcon stopIcon;
  private ImageIcon startIcon;
  private Font font = new Font("serif", 0, 10);
  private Color roColor = new Color(187, 213, 238);
  private Surface surface;
  private Thread thread;
  private JPanel toolbarPanel;
  private JPanel sliderPanel;
  private JLabel label;
  private Tools.ToggleIcon bumpyIcon;
  private Tools.ToggleIcon rolloverIcon;
  private DecimalFormat decimalFormat = new DecimalFormat("000");

  public Tools(Surface var1) {
    this.surface = var1;
    this.setLayout(new BorderLayout());
    this.stopIcon = new ImageIcon(DemoImages.getImage("stop.gif", this));
    this.startIcon = new ImageIcon(DemoImages.getImage("start.gif", this));
    this.bumpyIcon = new Tools.ToggleIcon(this, Color.LIGHT_GRAY);
    this.rolloverIcon = new Tools.ToggleIcon(this, this.roColor);
    this.toggleB = new JToggleButton(this.bumpyIcon);
    this.toggleB.addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent var1) {
            Tools.this.focus = true;
            Tools.this.bumpyIcon.start();
          }

          public void mouseExited(MouseEvent var1) {
            Tools.this.focus = false;
            Tools.this.bumpyIcon.stop();
          }
        });
    this.isExpanded = false;
    this.toggleB.addActionListener(this);
    this.toggleB.setMargin(new Insets(0, 0, -4, 0));
    this.toggleB.setBorderPainted(false);
    this.toggleB.setFocusPainted(false);
    this.toggleB.setContentAreaFilled(false);
    this.toggleB.setRolloverIcon(this.rolloverIcon);
    this.add("North", this.toggleB);
    this.toolbar = new JToolBar();
    this.toolbar.setPreferredSize(new Dimension(112, 26));
    this.toolbar.setFloatable(false);
    String var2 = var1.AntiAlias == RenderingHints.VALUE_ANTIALIAS_ON ? "On" : "Off";
    this.aliasB = this.addTool("A", "Antialiasing " + var2, this);
    var2 = var1.Rendering == RenderingHints.VALUE_RENDER_SPEED ? "Speed" : "Quality";
    this.renderB = this.addTool("R", "Rendering " + var2, this);
    var2 = var1.texture != null ? "On" : "Off";
    this.textureB = this.addTool("T", "Texture " + var2, this);
    var2 = var1.composite != null ? "On" : "Off";
    this.compositeB = this.addTool("C", "Composite " + var2, this);
    Image var3 = DemoImages.getImage("print.gif", this);
    this.printB = this.addTool(var3, "Print the Surface", this);
    if (var1 instanceof AnimatingSurface) {
      Image var4 = DemoImages.getImage("stop.gif", this);
      this.startStopB = this.addTool(var4, "Stop Animation", this);
      this.toolbar.setPreferredSize(new Dimension(132, 26));
    }

    this.screenCombo = new JComboBox();
    this.screenCombo.setPreferredSize(new Dimension(100, 18));
    this.screenCombo.setFont(this.font);
    String[] var8 = GlobalControls.screenNames;
    int var5 = var8.length;

    for (int var6 = 0; var6 < var5; ++var6) {
      String var7 = var8[var6];
      this.screenCombo.addItem(var7);
    }

    this.screenCombo.addActionListener(this);
    this.toolbarPanel = new JPanel(new FlowLayout(1, 5, 0));
    this.toolbarPanel.setLocation(0, 6);
    this.toolbarPanel.setVisible(false);
    this.toolbarPanel.add(this.toolbar);
    this.toolbarPanel.add(this.screenCombo);
    this.toolbarPanel.setBorder(new EtchedBorder());
    this.add(this.toolbarPanel);
    this.setPreferredSize(new Dimension(200, 8));
    if (var1 instanceof AnimatingSurface) {
      this.sliderPanel = new JPanel(new BorderLayout());
      this.label = new JLabel(" Sleep = 030 ms");
      this.label.setForeground(Color.BLACK);
      this.sliderPanel.add(this.label, "West");
      this.slider = new JSlider(0, 0, 200, 30);
      this.slider.addChangeListener(this);
      this.sliderPanel.setBorder(new EtchedBorder());
      this.sliderPanel.add(this.slider);
      this.addMouseListener(
          new MouseAdapter() {
            public void mouseClicked(MouseEvent var1) {
              if (Tools.this.toolbarPanel.isVisible()) {
                Tools.this.invalidate();
                if (Tools.this.doSlider = !Tools.this.doSlider) {
                  Tools.this.remove(Tools.this.toolbarPanel);
                  Tools.this.add(Tools.this.sliderPanel);
                } else {
                  Tools.this.remove(Tools.this.sliderPanel);
                  Tools.this.add(Tools.this.toolbarPanel);
                }

                Tools.this.validate();
                Tools.this.repaint();
              }
            }
          });
    }
  }

  public JButton addTool(Image var1, String var2, ActionListener var3) {
    JButton var4 =
        new JButton(new ImageIcon(var1)) {
          Dimension prefSize = new Dimension(21, 22);

          public Dimension getPreferredSize() {
            return this.prefSize;
          }

          public Dimension getMaximumSize() {
            return this.prefSize;
          }

          public Dimension getMinimumSize() {
            return this.prefSize;
          }
        };
    this.toolbar.add(var4);
    var4.setFocusPainted(false);
    var4.setSelected(true);
    var4.setToolTipText(var2);
    var4.addActionListener(var3);
    return var4;
  }

  public JToggleButton addTool(String var1, String var2, ActionListener var3) {
    JToggleButton var4 =
        new JToggleButton(var1) {
          Dimension prefSize = new Dimension(21, 22);

          public Dimension getPreferredSize() {
            return this.prefSize;
          }

          public Dimension getMaximumSize() {
            return this.prefSize;
          }

          public Dimension getMinimumSize() {
            return this.prefSize;
          }
        };
    this.toolbar.add(var4);
    var4.setFocusPainted(false);
    if (!var2.equals("Rendering Quality")
        && !var2.equals("Antialiasing On")
        && !var2.equals("Texture On")
        && !var2.equals("Composite On")) {
      var4.setSelected(false);
    } else {
      var4.setSelected(true);
    }

    var4.setToolTipText(var2);
    var4.addActionListener(var3);
    return var4;
  }

  public void actionPerformed(ActionEvent var1) {
    Object var2 = var1.getSource();
    if (var2 instanceof JButton) {
      JButton var3 = (JButton) var2;
      var3.setSelected(!var3.isSelected());
      if (var3.getIcon() == null) {
        var3.setBackground(var3.isSelected() ? Color.GREEN : Color.LIGHT_GRAY);
      }
    }

    if (var2.equals(this.toggleB)) {
      this.isExpanded = !this.isExpanded;
      if (this.isExpanded) {
        this.setPreferredSize(new Dimension(200, 38));
      } else {
        this.setPreferredSize(new Dimension(200, 6));
      }

      this.toolbarPanel.setVisible(this.isExpanded);
      if (this.sliderPanel != null) {
        this.sliderPanel.setVisible(this.isExpanded);
      }

      this.getParent().validate();
      this.toggleB.getModel().setRollover(false);
    } else if (var2.equals(this.printB)) {
      this.start();
    } else {
      if (var2.equals(this.startStopB)) {
        if (this.startStopB.getToolTipText().equals("Stop Animation")) {
          this.startStopB.setIcon(this.startIcon);
          this.startStopB.setToolTipText("Start Animation");
          this.surface.animating.stop();
        } else {
          this.startStopB.setIcon(this.stopIcon);
          this.startStopB.setToolTipText("Stop Animation");
          this.surface.animating.start();
        }
      } else if (var2.equals(this.aliasB)) {
        if (this.aliasB.getToolTipText().equals("Antialiasing On")) {
          this.aliasB.setToolTipText("Antialiasing Off");
        } else {
          this.aliasB.setToolTipText("Antialiasing On");
        }

        this.surface.setAntiAlias(this.aliasB.isSelected());
      } else if (var2.equals(this.renderB)) {
        if (this.renderB.getToolTipText().equals("Rendering Quality")) {
          this.renderB.setToolTipText("Rendering Speed");
        } else {
          this.renderB.setToolTipText("Rendering Quality");
        }

        this.surface.setRendering(this.renderB.isSelected());
      } else if (var2.equals(this.textureB)) {
        if (this.textureB.getToolTipText().equals("Texture On")) {
          this.textureB.setToolTipText("Texture Off");
          this.surface.setTexture(null);
          this.surface.clearSurface = true;
        } else {
          this.textureB.setToolTipText("Texture On");
          this.surface.setTexture(TextureChooser.texture);
        }
      } else if (var2.equals(this.compositeB)) {
        if (this.compositeB.getToolTipText().equals("Composite On")) {
          this.compositeB.setToolTipText("Composite Off");
        } else {
          this.compositeB.setToolTipText("Composite On");
        }

        this.surface.setComposite(this.compositeB.isSelected());
      } else if (var2.equals(this.screenCombo)) {
        this.surface.setImageType(this.screenCombo.getSelectedIndex());
      }

      if (this.issueRepaint && this.surface.animating != null) {
        if (this.surface.getSleepAmount() != 0L && this.surface.animating.running()) {
          this.surface.animating.doRepaint();
        }
      } else if (this.issueRepaint) {
        this.surface.repaint();
      }
    }
  }

  public void stateChanged(ChangeEvent var1) {
    int var2 = this.slider.getValue();
    this.label.setText(" Sleep = " + this.decimalFormat.format(var2) + " ms");
    this.label.repaint();
    this.surface.setSleepAmount(var2);
  }

  public void start() {
    this.thread = new Thread(this);
    this.thread.setPriority(10);
    this.thread.setName("Printing " + this.surface.name);
    this.thread.start();
  }

  public synchronized void stop() {
    this.thread = null;
    this.notifyAll();
  }

  public void run() {
    boolean var1 = false;
    if (this.surface.animating != null && this.surface.animating.running()) {
      var1 = true;
      this.startStopB.doClick();
    }

    try {
      PrinterJob var2 = PrinterJob.getPrinterJob();
      var2.setPrintable(this.surface);
      boolean var7 = true;
      HashPrintRequestAttributeSet var4 = new HashPrintRequestAttributeSet();
      if (!Java2Demo.printCB.isSelected()) {
        var7 = var2.printDialog(var4);
      }

      if (var7) {
        var2.print(var4);
      }
    } catch (AccessControlException var5) {
      String var3 =
          "Applet access control exception; to allow access to printer, run policytool and set\npermission for \"queuePrintJob\" in RuntimePermission.";
      JOptionPane.showMessageDialog(this, var3, "Printer Access Error", 0);
    } catch (Exception var6) {
      Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, var6);
    }

    if (var1) {
      this.startStopB.doClick();
    }

    this.thread = null;
  }

  static class ToggleIcon implements Icon, Runnable {
    private Color shadowColor = new Color(102, 102, 153);
    private Color fillColor;
    private Tools tools;
    private Thread thread;

    public ToggleIcon(Tools var1, Color var2) {
      this.tools = var1;
      this.fillColor = var2;
    }

    public void paintIcon(Component var1, Graphics var2, int var3, int var4) {
      int var5 = this.getIconWidth();
      int var6 = this.getIconHeight();
      var2.setColor(this.fillColor);
      var2.fillRect(0, 0, var5, var6);

      while (var3 < var5 - 2) {
        var2.setColor(Color.WHITE);
        var2.fillRect(var3, 1, 1, 1);
        var2.fillRect(var3 + 2, 3, 1, 1);
        var2.setColor(this.shadowColor);
        var2.fillRect(var3 + 1, 2, 1, 1);
        var2.fillRect(var3 + 3, 4, 1, 1);
        var3 += 4;
      }
    }

    public int getIconWidth() {
      return this.tools.getSize().width;
    }

    public int getIconHeight() {
      return 6;
    }

    public void start() {
      this.thread = new Thread(this);
      this.thread.setPriority(1);
      this.thread.setName("ToggleIcon");
      this.thread.start();
    }

    public synchronized void stop() {
      if (this.thread != null) {
        this.thread.interrupt();
      }

      this.thread = null;
    }

    public void run() {
      try {
        Thread.sleep(400L);
      } catch (InterruptedException var2) {
      }

      if (this.tools.focus && this.thread != null) {
        this.tools.toggleB.doClick();
      }

      this.thread = null;
    }
  }
}
