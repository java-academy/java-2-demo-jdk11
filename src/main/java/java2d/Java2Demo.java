//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java2d.CustomControlsContext.State;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class Java2Demo extends JPanel implements ItemListener, ActionListener {
  static Java2Demo demo;
  static GlobalControls controls;
  static MemoryMonitor memorymonitor;
  static PerformanceMonitor performancemonitor;
  static JTabbedPane tabbedPane;
  static JLabel progressLabel;
  static JProgressBar progressBar;
  static DemoGroup[] group;
  static JCheckBoxMenuItem verboseCB;
  static JCheckBoxMenuItem ccthreadCB;
  static JCheckBoxMenuItem printCB = new JCheckBoxMenuItem("Default Printer");
  static Color backgroundColor;
  static JCheckBoxMenuItem memoryCB;
  static JCheckBoxMenuItem perfCB;
  static Intro intro;
  static String[][] demos =
      new String[][] {
        {"Arcs_Curves", "Arcs", "BezierAnim", "Curves", "Ellipses"},
        {"Clipping", "Areas", "ClipAnim", "Intersection", "Text"},
        {"Colors", "BullsEye", "ColorConvert", "Rotator3D"},
        {"Composite", "ACimages", "ACrules", "FadeAnim"},
        {"Fonts", "AttributedStr", "Highlighting", "Outline", "Tree"},
        {"Images", "DukeAnim", "ImageOps", "JPEGFlip", "WarpImage"},
        {"Lines", "Caps", "Dash", "Joins", "LineAnim"},
        {"Mix", "Balls", "BezierScroller", "Stars3D"},
        {"Paint", "GradAnim", "Gradient", "Texture", "TextureAnim"},
        {"Paths", "Append", "CurveQuadTo", "FillStroke", "WindingRule"},
        {"Transforms", "Rotate", "SelectTx", "TransformAnim"}
      };
  private JCheckBoxMenuItem controlsCB;
  private JMenuItem runMI;
  private JMenuItem cloneMI;
  private JMenuItem fileMI;
  private JMenuItem backgMI;
  private RunWindow runwindow;
  private CloningFeature cloningfeature;
  private JFrame rf;
  private JFrame cf;

  public Java2Demo() {
    this.setLayout(new BorderLayout());
    this.setBorder(new EtchedBorder());
    this.add(this.createMenuBar(), "North");
    progressBar.setMaximum(13);
    progressLabel.setText("Loading images");
    DemoImages.newDemoImages();
    progressBar.setValue(progressBar.getValue() + 1);
    progressLabel.setText("Loading fonts");
    DemoFonts.newDemoFonts();
    progressBar.setValue(progressBar.getValue() + 1);
    progressLabel.setText("Loading Intro");
    intro = new Intro();
    progressBar.setValue(progressBar.getValue() + 1);
    UIManager.put("Button.margin", new Insets(0, 0, 0, 0));
    controls = new GlobalControls();
    memorymonitor = new MemoryMonitor();
    performancemonitor = new PerformanceMonitor();
    GlobalPanel var1 = new GlobalPanel();
    tabbedPane = new JTabbedPane();
    tabbedPane.setFont(new Font("serif", 0, 12));
    tabbedPane.addTab("", new Java2Demo.J2DIcon(), var1);
    tabbedPane.addChangeListener(var1);
    group = new DemoGroup[demos.length];

    for (int var2 = 0; var2 < demos.length; ++var2) {
      progressLabel.setText("Loading demos." + demos[var2][0]);
      group[var2] = new DemoGroup(demos[var2][0]);
      tabbedPane.addTab(demos[var2][0], null);
      progressBar.setValue(progressBar.getValue() + 1);
    }

    this.add(tabbedPane, "Center");
  }

  static void addToGridBag(
      JPanel var0,
      Component var1,
      int var2,
      int var3,
      int var4,
      int var5,
      double var6,
      double var8) {
    GridBagLayout var10 = (GridBagLayout) var0.getLayout();
    GridBagConstraints var11 = new GridBagConstraints();
    var11.fill = 1;
    var11.gridx = var2;
    var11.gridy = var3;
    var11.gridwidth = var4;
    var11.gridheight = var5;
    var11.weightx = var6;
    var11.weighty = var8;
    var0.add(var1);
    var10.setConstraints(var1, var11);
  }

  private static void initFrame(String[] var0) {
    JFrame var1 = new JFrame("Java 2D(TM) Demo");
    var1.getAccessibleContext()
        .setAccessibleDescription("A sample application to demonstrate Java2D features");
    short var2 = 400;
    short var3 = 200;
    var1.setSize(var2, var3);
    Dimension var4 = Toolkit.getDefaultToolkit().getScreenSize();
    var1.setLocation(var4.width / 2 - var2 / 2, var4.height / 2 - var3 / 2);
    var1.setCursor(Cursor.getPredefinedCursor(3));
    var1.addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent var1) {
            System.exit(0);
          }

          public void windowDeiconified(WindowEvent var1) {
            if (Java2Demo.demo != null) {
              Java2Demo.demo.start();
            }
          }

          public void windowIconified(WindowEvent var1) {
            if (Java2Demo.demo != null) {
              Java2Demo.demo.stop();
            }
          }
        });
    JOptionPane.setRootFrame(var1);
    JPanel var5 =
        new JPanel() {
          public Insets getInsets() {
            return new Insets(40, 30, 20, 30);
          }
        };
    var5.setLayout(new BoxLayout(var5, 1));
    var1.getContentPane().add(var5, "Center");
    Dimension var6 = new Dimension(400, 20);
    progressLabel = new JLabel("Loading, please wait...");
    progressLabel.setAlignmentX(0.5F);
    progressLabel.setMaximumSize(var6);
    progressLabel.setPreferredSize(var6);
    var5.add(progressLabel);
    var5.add(Box.createRigidArea(new Dimension(1, 20)));
    progressBar = new JProgressBar();
    progressBar.setStringPainted(true);
    progressLabel.setLabelFor(progressBar);
    progressBar.setAlignmentX(0.5F);
    progressBar.setMinimum(0);
    progressBar.setValue(0);
    progressBar.getAccessibleContext().setAccessibleName("Java2D loading progress");
    var5.add(progressBar);
    var1.setVisible(true);
    demo = new Java2Demo();
    var1.getContentPane().removeAll();
    var1.getContentPane().setLayout(new BorderLayout());
    var1.getContentPane().add(demo, "Center");
    var2 = 730;
    var3 = 570;
    var1.setLocation(var4.width / 2 - var2 / 2, var4.height / 2 - var3 / 2);
    var1.setSize(var2, var3);
    var1.setCursor(Cursor.getPredefinedCursor(0));

    for (int var7 = 0; var7 < var0.length; ++var7) {
      String var8 = var0[var7];
      String var9 = var8.substring(var8.indexOf(61) + 1);
      if (var8.startsWith("-runs=")) {
        RunWindow.numRuns = Integer.parseInt(var9);
        RunWindow.exit = true;
        demo.createRunWindow();
      } else if (var8.startsWith("-screen=")) {
        GlobalControls.screenCombo.setSelectedIndex(Integer.parseInt(var9));
      } else if (var8.startsWith("-antialias=")) {
        controls.aliasCB.setSelected(var9.endsWith("true"));
      } else if (var8.startsWith("-rendering=")) {
        controls.renderCB.setSelected(var9.endsWith("true"));
      } else if (var8.startsWith("-texture=")) {
        controls.textureCB.setSelected(var9.endsWith("true"));
      } else if (var8.startsWith("-composite=")) {
        controls.compositeCB.setSelected(var9.endsWith("true"));
      } else if (var8.startsWith("-verbose")) {
        verboseCB.setSelected(true);
      } else if (var8.startsWith("-print")) {
        printCB.setSelected(true);
        RunWindow.printCB.setSelected(true);
      } else if (var8.startsWith("-columns=")) {
        DemoGroup.columns = Integer.parseInt(var9);
      } else if (var8.startsWith("-buffers=")) {
        RunWindow.buffersFlag = true;
        int var10 = var8.indexOf(61) + 1;
        int var11 = var8.indexOf(44);
        String var12 = var8.substring(var10, var11);
        RunWindow.bufBeg = Integer.parseInt(var12);
        var12 = var8.substring(var11 + 1);
        RunWindow.bufEnd = Integer.parseInt(var12);
      } else if (var8.startsWith("-ccthread")) {
        ccthreadCB.setSelected(true);
      } else if (var8.startsWith("-zoom")) {
        RunWindow.zoomCB.setSelected(true);
      } else if (var8.startsWith("-maxscreen")) {
        var1.setLocation(0, 0);
        var1.setSize(var4.width, var4.height);
      }
    }

    var1.validate();
    var1.repaint();
    var1.getFocusTraversalPolicy().getDefaultComponent(var1).requestFocus();
    demo.start();
    if (RunWindow.exit) {
      demo.startRunWindow();
    }
  }

  public static void main(final String[] var0) {
    for (int var1 = 0; var1 < var0.length; ++var1) {
      String var2;
      if (!var0[var1].startsWith("-h") && !var0[var1].startsWith("-help")) {
        if (var0[var1].startsWith("-delay=")) {
          var2 = var0[var1].substring(var0[var1].indexOf(61) + 1);
          RunWindow.delay = Integer.parseInt(var2);
        }
      } else {
        var2 =
            "\njava -jar Java2Demo.jar -runs=5 -delay=5 -screen=5 -antialias=true -rendering=true -texture=true -composite=true -verbose -print -columns=3 -buffers=5,10 -ccthread -zoom -maxscreen \n";
        System.out.println(var2);
        var2 =
            "    -runs=5       Number of runs to execute\n    -delay=5      Sleep amount between tabs\n    -antialias=   true or false for antialiasing\n    -rendering=   true or false for quality or speed\n    -texture=     true or false for texturing\n    -composite=   true or false for compositing\n    -verbose      output Surface graphic states \n    -print        during run print the Surface, use the Default Printer\n    -columns=3    # of columns to use in clone layout \n    -screen=3     demos all use this screen type \n    -buffers=5,10 during run - clone to see screens five through ten\n    -ccthread     Invoke the Custom Controls Thread \n    -zoom         mouseClick on surface for zoom in  \n    -maxscreen    take up the entire monitor screen \n";
        System.out.println(var2);
        var2 =
            "Examples : \n    Print all of the demos : \n        java -jar Java2Demo.jar -runs=1 -delay=60 -print \n    Run zoomed in with custom control thread \n        java -jar Java2Demo.jar -runs=10 -zoom -ccthread\n";
        System.out.println(var2);
        System.exit(0);
      }
    }

    SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            Java2Demo.initFrame(var0);
          }
        });
  }

  private JMenuBar createMenuBar() {
    JPopupMenu.setDefaultLightWeightPopupEnabled(false);
    JMenuBar var1 = new JMenuBar();
    JMenu var2;
    if (Java2DemoApplet.applet == null) {
      var2 = var1.add(new JMenu("File"));
      this.fileMI = var2.add(new JMenuItem("Exit"));
      this.fileMI.addActionListener(this);
    }

    var2 = var1.add(new JMenu("Options"));
    this.controlsCB = (JCheckBoxMenuItem) var2.add(new JCheckBoxMenuItem("Global Controls", true));
    this.controlsCB.addItemListener(this);
    memoryCB = (JCheckBoxMenuItem) var2.add(new JCheckBoxMenuItem("Memory Monitor", true));
    memoryCB.addItemListener(this);
    perfCB = (JCheckBoxMenuItem) var2.add(new JCheckBoxMenuItem("Performance Monitor", true));
    perfCB.addItemListener(this);
    var2.add(new JSeparator());
    ccthreadCB = (JCheckBoxMenuItem) var2.add(new JCheckBoxMenuItem("Custom Controls Thread"));
    ccthreadCB.addItemListener(this);
    printCB = (JCheckBoxMenuItem) var2.add(printCB);
    verboseCB = (JCheckBoxMenuItem) var2.add(new JCheckBoxMenuItem("Verbose"));
    var2.add(new JSeparator());
    this.backgMI = var2.add(new JMenuItem("Background Color"));
    this.backgMI.addActionListener(this);
    this.runMI = var2.add(new JMenuItem("Run Window"));
    this.runMI.addActionListener(this);
    this.cloneMI = var2.add(new JMenuItem("Cloning Feature"));
    this.cloneMI.addActionListener(this);
    return var1;
  }

  public void createRunWindow() {
    if (this.rf != null) {
      this.rf.toFront();
    } else {
      this.runwindow = new RunWindow();
      WindowAdapter var1 =
          new WindowAdapter() {
            public void windowClosing(WindowEvent var1) {
              Java2Demo.this.runwindow.stop();
              Java2Demo.this.rf.dispose();
            }

            public void windowClosed(WindowEvent var1) {
              Java2Demo.this.rf = null;
            }
          };
      this.rf = new JFrame("Run");
      this.rf.addWindowListener(var1);
      this.rf.getContentPane().add("Center", this.runwindow);
      this.rf.pack();
      if (Java2DemoApplet.applet == null) {
        this.rf.setSize(new Dimension(200, 125));
      } else {
        this.rf.setSize(new Dimension(200, 150));
      }

      this.rf.setVisible(true);
    }
  }

  public void startRunWindow() {
    SwingUtilities.invokeLater(
        new Runnable() {
          public void run() {
            Java2Demo.this.runwindow.doRunAction();
          }
        });
  }

  public void actionPerformed(ActionEvent var1) {
    if (var1.getSource().equals(this.fileMI)) {
      System.exit(0);
    } else if (var1.getSource().equals(this.runMI)) {
      this.createRunWindow();
    } else if (var1.getSource().equals(this.cloneMI)) {
      if (this.cloningfeature == null) {
        this.cloningfeature = new CloningFeature();
        WindowAdapter var2 =
            new WindowAdapter() {
              public void windowClosing(WindowEvent var1) {
                Java2Demo.this.cloningfeature.stop();
                Java2Demo.this.cf.dispose();
              }

              public void windowClosed(WindowEvent var1) {
                Java2Demo.this.cloningfeature = null;
              }
            };
        this.cf = new JFrame("Cloning Demo");
        this.cf.addWindowListener(var2);
        this.cf.getContentPane().add("Center", this.cloningfeature);
        this.cf.pack();
        this.cf.setSize(new Dimension(320, 330));
        this.cf.setVisible(true);
      } else {
        this.cf.toFront();
      }
    } else if (var1.getSource().equals(this.backgMI)) {
      backgroundColor = JColorChooser.showDialog(this, "Background Color", Color.white);

      for (int var6 = 1; var6 < tabbedPane.getTabCount(); ++var6) {
        JPanel var3 = group[var6 - 1].getPanel();

        for (int var4 = 0; var4 < var3.getComponentCount(); ++var4) {
          DemoPanel var5 = (DemoPanel) var3.getComponent(var4);
          if (var5.surface != null) {
            var5.surface.setBackground(backgroundColor);
          }
        }
      }
    }
  }

  public void itemStateChanged(ItemEvent var1) {
    int var4;
    if (var1.getSource().equals(this.controlsCB)) {
      boolean var2 = !controls.isVisible();
      controls.setVisible(var2);
      Component[] var3 = controls.texturechooser.getComponents();
      var4 = var3.length;

      for (int var5 = 0; var5 < var4; ++var5) {
        Component var6 = var3[var5];
        var6.setVisible(var2);
      }
    } else if (var1.getSource().equals(memoryCB)) {
      if (memorymonitor.isVisible()) {
        memorymonitor.setVisible(false);
        memorymonitor.surf.setVisible(false);
        memorymonitor.surf.stop();
      } else {
        memorymonitor.setVisible(true);
        memorymonitor.surf.setVisible(true);
        memorymonitor.surf.start();
      }
    } else if (var1.getSource().equals(perfCB)) {
      if (performancemonitor.isVisible()) {
        performancemonitor.setVisible(false);
        performancemonitor.surf.setVisible(false);
        performancemonitor.surf.stop();
      } else {
        performancemonitor.setVisible(true);
        performancemonitor.surf.setVisible(true);
        performancemonitor.surf.start();
      }
    } else if (var1.getSource().equals(ccthreadCB)) {
      State var7 = ccthreadCB.isSelected() ? State.START : State.STOP;
      if (tabbedPane.getSelectedIndex() != 0) {
        JPanel var8 = group[tabbedPane.getSelectedIndex() - 1].getPanel();

        for (var4 = 0; var4 < var8.getComponentCount(); ++var4) {
          DemoPanel var9 = (DemoPanel) var8.getComponent(var4);
          if (var9.ccc != null) {
            var9.ccc.handleThread(var7);
          }
        }
      }
    }

    this.revalidate();
  }

  public void start() {
    if (tabbedPane.getSelectedIndex() == 0) {
      intro.start();
    } else {
      group[tabbedPane.getSelectedIndex() - 1].setup(false);
      if (memorymonitor.surf.thread == null && memoryCB.getState()) {
        memorymonitor.surf.start();
      }

      if (performancemonitor.surf.thread == null && perfCB.getState()) {
        performancemonitor.surf.start();
      }
    }
  }

  public void stop() {
    if (tabbedPane.getSelectedIndex() == 0) {
      intro.stop();
    } else {
      memorymonitor.surf.stop();
      performancemonitor.surf.stop();
      int var1 = tabbedPane.getSelectedIndex() - 1;
      group[var1].shutDown(group[var1].getPanel());
    }
  }

  static class J2DIcon implements Icon {
    private static Color myBlue = new Color(94, 105, 176);
    private static Color myBlack = new Color(20, 20, 20);
    private static Font font = new Font("serif", 1, 12);
    private FontRenderContext frc = new FontRenderContext(null, true, true);
    private TextLayout tl;

    J2DIcon() {
      this.tl = new TextLayout("Java2D", font, this.frc);
    }

    public void paintIcon(Component var1, Graphics var2, int var3, int var4) {
      Graphics2D var5 = (Graphics2D) var2;
      var5.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      var5.setFont(font);
      if (Java2Demo.tabbedPane.getSelectedIndex() == 0) {
        var5.setColor(myBlue);
      } else {
        var5.setColor(myBlack);
      }

      this.tl.draw(var5, (float) var3, (float) (var4 + 15));
    }

    public int getIconWidth() {
      return 40;
    }

    public int getIconHeight() {
      return 22;
    }
  }
}
