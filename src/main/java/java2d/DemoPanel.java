//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2d.CustomControlsContext.State;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

public class DemoPanel extends JPanel {
  public Surface surface;
  public CustomControlsContext ccc;
  public Tools tools;
  public String className;

  public DemoPanel(Object var1) {
    this.setLayout(new BorderLayout());

    try {
      if (var1 instanceof String) {
        this.className = (String) var1;
        var1 = Class.forName(this.className).newInstance();
      }

      if (var1 instanceof Component) {
        this.add((Component) var1);
      }

      if (var1 instanceof Surface) {
        this.add("South", this.tools = new Tools(this.surface = (Surface) var1));
      }

      if (var1 instanceof CustomControlsContext) {
        this.ccc = (CustomControlsContext) var1;
        Component[] var2 = this.ccc.getControls();
        String[] var3 = this.ccc.getConstraints();

        for (int var4 = 0; var4 < var2.length; ++var4) {
          this.add(var2[var4], var3[var4]);
        }
      }
    } catch (Exception var5) {
      Logger.getLogger(DemoPanel.class.getName()).log(Level.SEVERE, null, var5);
    }
  }

  public void start() {
    if (this.surface != null) {
      this.surface.startClock();
    }

    if (this.tools != null
        && this.surface != null
        && this.tools.startStopB != null
        && this.tools.startStopB.isSelected()) {
      this.surface.animating.start();
    }

    if (this.ccc != null && Java2Demo.ccthreadCB != null && Java2Demo.ccthreadCB.isSelected()) {
      this.ccc.handleThread(State.START);
    }
  }

  public void stop() {
    if (this.surface != null) {
      if (this.surface.animating != null) {
        this.surface.animating.stop();
      }

      this.surface.bimg = null;
    }

    if (this.ccc != null) {
      this.ccc.handleThread(State.STOP);
    }
  }

  public void setDemoBorder(JPanel var1) {
    int var2 = var1.getComponentCount() + 1 >= 3 ? 0 : 5;
    int var3 = (var1.getComponentCount() + 1) % 2 == 0 ? 0 : 5;
    EmptyBorder var4 = new EmptyBorder(var2, var3, 5, 5);
    SoftBevelBorder var5 = new SoftBevelBorder(0);
    this.setBorder(new CompoundBorder(var4, var5));
  }
}
