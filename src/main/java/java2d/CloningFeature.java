//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public final class CloningFeature extends JPanel implements Runnable {
  private Thread thread;
  private JTextArea ta;

  public CloningFeature() {
    this.setLayout(new BorderLayout());
    EmptyBorder var1 = new EmptyBorder(5, 5, 5, 5);
    SoftBevelBorder var2 = new SoftBevelBorder(0);
    this.setBorder(new CompoundBorder(var1, var2));
    this.ta = new JTextArea("Cloning Demonstrated\n\nClicking once on a demo\n");
    this.ta.setMinimumSize(new Dimension(300, 500));
    JScrollPane var3 = new JScrollPane();
    var3.getViewport().add(this.ta);
    this.ta.setFont(new Font("Dialog", 0, 14));
    this.ta.setForeground(Color.black);
    this.ta.setBackground(Color.lightGray);
    this.ta.setEditable(false);
    this.add("Center", var3);
    this.start();
  }

  public void start() {
    this.thread = new Thread(this);
    this.thread.setPriority(10);
    this.thread.setName("CloningFeature");
    this.thread.start();
  }

  public void stop() {
    if (this.thread != null) {
      this.thread.interrupt();
    }

    this.thread = null;
  }

  public void run() {
    int var1 = Java2Demo.tabbedPane.getSelectedIndex();
    if (var1 == 0) {
      Java2Demo.tabbedPane.setSelectedIndex(1);

      try {
        Thread.sleep(3333L);
      } catch (Exception var13) {
        return;
      }
    }

    if (!Java2Demo.controls.toolBarCB.isSelected()) {
      Java2Demo.controls.toolBarCB.setSelected(true);

      try {
        Thread.sleep(2222L);
      } catch (Exception var12) {
        return;
      }
    }

    var1 = Java2Demo.tabbedPane.getSelectedIndex() - 1;
    DemoGroup var2 = Java2Demo.group[var1];
    DemoPanel var3 = (DemoPanel) var2.getPanel().getComponent(0);
    if (var3.surface == null) {
      this.ta.append("Sorry your zeroth component is not a Surface.");
    } else {
      var2.mouseClicked(var3.surface);

      try {
        Thread.sleep(3333L);
      } catch (Exception var11) {
        return;
      }

      this.ta.append("Clicking the ToolBar double document button\n");

      try {
        Thread.sleep(3333L);
      } catch (Exception var10) {
        return;
      }

      var3 = (DemoPanel) var2.clonePanels[0].getComponent(0);
      if (var3.tools != null) {
        for (int var4 = 0; var4 < 3 && this.thread != null; ++var4) {
          this.ta.append("   Cloning\n");
          var3.tools.cloneB.doClick();

          try {
            Thread.sleep(3333L);
          } catch (Exception var9) {
            return;
          }
        }
      }

      this.ta.append("Changing attributes \n");

      try {
        Thread.sleep(3333L);
      } catch (Exception var8) {
        return;
      }

      Component[] var14 = var2.clonePanels[0].getComponents();

      for (int var5 = 0; var5 < var14.length && this.thread != null; ++var5) {
        if ((var3 = (DemoPanel) var14[var5]).tools != null) {
          switch (var5) {
            case 0:
              this.ta.append("   Changing AntiAliasing\n");
              var3.tools.aliasB.doClick();
              break;
            case 1:
              this.ta.append("   Changing Composite & Texture\n");
              var3.tools.compositeB.doClick();
              var3.tools.textureB.doClick();
              break;
            case 2:
              this.ta.append("   Changing Screen\n");
              var3.tools.screenCombo.setSelectedIndex(4);
              break;
            case 3:
              this.ta.append("   Removing a clone\n");
              var3.tools.cloneB.doClick();
          }

          try {
            Thread.sleep(3333L);
          } catch (Exception var7) {
            return;
          }
        }
      }

      this.ta.append("\nAll Done!");
    }
  }
}
