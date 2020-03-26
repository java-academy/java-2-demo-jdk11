//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DemoGroup extends JPanel implements ChangeListener, ActionListener {
  static int columns = 2;
  private static Font font = new Font("serif", 0, 10);
  private static EmptyBorder emptyB = new EmptyBorder(5, 5, 5, 5);
  private static BevelBorder bevelB = new BevelBorder(1);
  public JPanel[] clonePanels;
  public JTabbedPane tabbedPane;
  private String groupName;
  private int index;

  public DemoGroup(String var1) {
    this.groupName = var1;
    this.setLayout(new BorderLayout());
    JPanel var2 = new JPanel(new GridLayout(0, 2));
    var2.setBorder(new CompoundBorder(emptyB, bevelB));
    int var3 = -1;

    do {
      ++var3;
    } while (!var1.equals(Java2Demo.demos[var3][0]));

    String[] var4 = Java2Demo.demos[var3];
    int var5 = var4.length - 1;
    if (var5 % 2 == 1) {
      var2.setLayout(new GridBagLayout());
    }

    MouseAdapter var6 =
        new MouseAdapter() {
          public void mouseClicked(MouseEvent var1) {
            DemoGroup.this.mouseClicked(var1.getComponent());
          }
        };

    for (int var7 = 1; var7 <= var5; ++var7) {
      DemoPanel var8 = new DemoPanel("java2d.demos." + var1 + "." + var4[var7]);
      var8.setDemoBorder(var2);
      if (var8.surface != null) {
        var8.surface.addMouseListener(var6);
        var8.surface.setMonitor(Java2Demo.performancemonitor != null);
      }

      if (var2.getLayout() instanceof GridBagLayout) {
        int var9 = var2.getComponentCount() % 2;
        int var10 = var2.getComponentCount() / 2;
        int var11 = var7 == var5 ? 2 : 1;
        Java2Demo.addToGridBag(var2, var8, var9, var10, var11, 1, 1.0D, 1.0D);
      } else {
        var2.add(var8);
      }
    }

    this.add(var2);
  }

  public static void main(String[] var0) {
    final DemoGroup var1 = new DemoGroup(var0[0]);
    JFrame var2 = new JFrame("Java2D Demo - DemoGroup");
    var2.addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent var1x) {
            System.exit(0);
          }

          public void windowDeiconified(WindowEvent var1x) {
            var1.setup(false);
          }

          public void windowIconified(WindowEvent var1x) {
            var1.shutDown(var1.getPanel());
          }
        });
    var2.getContentPane().add("Center", var1);
    var2.pack();
    short var3 = 620;
    short var4 = 530;
    var2.setSize(var3, var4);
    var2.setLocationRelativeTo(null);
    var2.setVisible(true);
    String[] var5 = var0;
    int var6 = var0.length;

    for (int var7 = 0; var7 < var6; ++var7) {
      String var8 = var5[var7];
      if (var8.startsWith("-ccthread")) {
        Java2Demo.ccthreadCB = new JCheckBoxMenuItem("CCThread", true);
      }
    }

    var1.setup(false);
  }

  public void mouseClicked(Component var1) {
    String var2 = var1.toString();
    if (this.tabbedPane == null) {
      this.shutDown(this.getPanel());
      JPanel var3 = new JPanel(new BorderLayout());
      var3.setBorder(new CompoundBorder(emptyB, bevelB));
      this.tabbedPane = new JTabbedPane();
      this.tabbedPane.setFont(font);
      JPanel var4 = (JPanel) this.getComponent(0);
      this.tabbedPane.addTab(this.groupName, var4);
      this.clonePanels = new JPanel[var4.getComponentCount()];

      for (int var5 = 0; var5 < this.clonePanels.length; ++var5) {
        this.clonePanels[var5] = new JPanel(new BorderLayout());
        DemoPanel var6 = (DemoPanel) var4.getComponent(var5);
        DemoPanel var7 = new DemoPanel(var6.className);
        var7.setDemoBorder(this.clonePanels[var5]);
        if (var7.surface != null) {
          var7.surface.setMonitor(Java2Demo.performancemonitor != null);
          Image var8 = DemoImages.getImage("clone.gif", this);
          var7.tools.cloneB = var7.tools.addTool(var8, "Clone the Surface", this);
          Dimension var9 = var7.tools.toolbar.getPreferredSize();
          var7.tools.toolbar.setPreferredSize(new Dimension(var9.width + 27, var9.height));
          if (Java2Demo.backgroundColor != null) {
            var7.surface.setBackground(Java2Demo.backgroundColor);
          }
        }

        this.clonePanels[var5].add(var7);
        String var12 = var6.className.substring(var6.className.indexOf(46) + 1);
        this.tabbedPane.addTab(var12, this.clonePanels[var5]);
      }

      var3.add(this.tabbedPane);
      this.remove(var4);
      this.add(var3);
      this.tabbedPane.addChangeListener(this);
      this.revalidate();
    }

    var2 = var2.substring(0, var2.indexOf(91));

    for (int var10 = 0; var10 < this.tabbedPane.getTabCount(); ++var10) {
      String var11 = var2.substring(var2.indexOf(46) + 1);
      if (this.tabbedPane.getTitleAt(var10).equals(var11)) {
        this.tabbedPane.setSelectedIndex(var10);
        break;
      }
    }

    this.revalidate();
  }

  public void actionPerformed(ActionEvent var1) {
    JButton var2 = (JButton) var1.getSource();
    if (var2.getToolTipText().startsWith("Clone")) {
      this.cloneDemo();
    } else {
      this.removeClone(var2.getParent().getParent().getParent().getParent());
    }
  }

  public void stateChanged(ChangeEvent var1) {
    this.shutDown((JPanel) this.tabbedPane.getComponentAt(this.index));
    this.index = this.tabbedPane.getSelectedIndex();
    this.setup(false);
  }

  public JPanel getPanel() {
    return this.tabbedPane != null
        ? (JPanel) this.tabbedPane.getSelectedComponent()
        : (JPanel) this.getComponent(0);
  }

  public void setup(boolean var1) {
    JPanel var2 = this.getPanel();
    if (Java2Demo.performancemonitor != null) {
      Java2Demo.performancemonitor.surf.setPanel(var2);
      Java2Demo.performancemonitor.surf.setSurfaceState();
    }

    GlobalControls var3 = Java2Demo.controls;

    for (int var4 = 0; var4 < var2.getComponentCount(); ++var4) {
      DemoPanel var5 = (DemoPanel) var2.getComponent(var4);
      if (var5.surface != null && var3 != null) {
        Tools var6 = var5.tools;
        var6.setVisible(this.isValid());
        var6.issueRepaint = var1;
        JToggleButton[] var7 =
            new JToggleButton[] {
              var6.toggleB, var6.aliasB, var6.renderB, var6.textureB, var6.compositeB
            };
        JCheckBox[] var8 =
            new JCheckBox[] {
              var3.toolBarCB, var3.aliasCB, var3.renderCB, var3.textureCB, var3.compositeCB
            };
        int var9 = 0;

        while (true) {
          if (var9 >= var7.length) {
            var6.setVisible(true);
            if (GlobalControls.screenCombo.getSelectedIndex()
                != var6.screenCombo.getSelectedIndex()) {
              var6.screenCombo.setSelectedIndex(GlobalControls.screenCombo.getSelectedIndex());
            }

            if (Java2Demo.verboseCB.isSelected()) {
              var5.surface.verbose();
            }

            var5.surface.setSleepAmount(var3.slider.getValue());
            if (Java2Demo.backgroundColor != null) {
              var5.surface.setBackground(Java2Demo.backgroundColor);
            }

            var6.issueRepaint = true;
            break;
          }

          if (var3.obj != null && var3.obj.equals(var8[var9])) {
            if (var7[var9].isSelected() != var8[var9].isSelected()) {
              var7[var9].doClick();
            }
          } else if (var3.obj == null && var7[var9].isSelected() != var8[var9].isSelected()) {
            var7[var9].doClick();
          }

          ++var9;
        }
      }

      var5.start();
    }

    this.revalidate();
  }

  public void shutDown(JPanel var1) {
    for (int var2 = 0; var2 < var1.getComponentCount(); ++var2) {
      ((DemoPanel) var1.getComponent(var2)).stop();
    }

    System.gc();
  }

  public void cloneDemo() {
    JPanel var1 = this.clonePanels[this.tabbedPane.getSelectedIndex() - 1];
    if (var1.getComponentCount() == 1) {
      var1.invalidate();
      var1.setLayout(new GridLayout(0, columns, 5, 5));
      var1.revalidate();
    }

    DemoPanel var2 = (DemoPanel) this.getPanel().getComponent(0);
    DemoPanel var3 = new DemoPanel(var2.className);
    if (columns == 2) {
      var3.setDemoBorder(var1);
    }

    Image var4 = DemoImages.getImage("remove.gif", this);
    var3.tools.cloneB = var3.tools.addTool(var4, "Remove the Surface", this);
    Dimension var5 = var3.tools.toolbar.getPreferredSize();
    var3.tools.toolbar.setPreferredSize(new Dimension(var5.width + 27, var5.height));
    if (Java2Demo.backgroundColor != null) {
      var3.surface.setBackground(Java2Demo.backgroundColor);
    }

    if (Java2Demo.controls != null
        && var3.tools.isExpanded != Java2Demo.controls.toolBarCB.isSelected()) {
      var3.tools.toggleB.doClick();
    }

    var3.start();
    var3.surface.setMonitor(Java2Demo.performancemonitor != null);
    var1.add(var3);
    var1.repaint();
    var1.revalidate();
  }

  public void removeClone(Component var1) {
    JPanel var2 = this.clonePanels[this.tabbedPane.getSelectedIndex() - 1];
    if (var2.getComponentCount() == 2) {
      Component var3 = var2.getComponent(0);
      var2.removeAll();
      var2.setLayout(new BorderLayout());
      var2.revalidate();
      var2.add(var3);
    } else {
      var2.remove(var1);
      int var10 = var2.getComponentCount();

      for (int var4 = 1; var4 < var10; ++var4) {
        int var5 = var4 + 1 >= 3 ? 0 : 5;
        int var6 = (var4 + 1) % 2 == 0 ? 0 : 5;
        EmptyBorder var7 = new EmptyBorder(var5, var6, 5, 5);
        SoftBevelBorder var8 = new SoftBevelBorder(0);
        JPanel var9 = (JPanel) var2.getComponent(var4);
        var9.setBorder(new CompoundBorder(var7, var8));
      }
    }

    var2.repaint();
    var2.revalidate();
  }
}
