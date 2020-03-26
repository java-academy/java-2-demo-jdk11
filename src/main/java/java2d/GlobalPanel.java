//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GlobalPanel extends JPanel implements ChangeListener {
  private JPanel p;
  private int index;

  public GlobalPanel() {
    this.setLayout(new BorderLayout());
    this.p = new JPanel(new GridBagLayout());
    EmptyBorder var1 = new EmptyBorder(5, 0, 5, 5);
    BevelBorder var2 = new BevelBorder(1);
    this.p.setBorder(new CompoundBorder(var1, var2));
    Java2Demo.addToGridBag(this.p, Java2Demo.controls, 0, 0, 1, 1, 0.0D, 0.0D);
    Java2Demo.addToGridBag(this.p, Java2Demo.memorymonitor, 0, 1, 1, 1, 0.0D, 0.0D);
    Java2Demo.addToGridBag(this.p, Java2Demo.performancemonitor, 0, 2, 1, 1, 0.0D, 0.0D);
    this.add(Java2Demo.intro);
  }

  public void stateChanged(ChangeEvent var1) {
    Java2Demo.group[this.index].shutDown(Java2Demo.group[this.index].getPanel());
    if (Java2Demo.tabbedPane.getSelectedIndex() == 0) {
      Java2Demo.memorymonitor.surf.stop();
      Java2Demo.performancemonitor.surf.stop();
      this.removeAll();
      this.add(Java2Demo.intro);
      Java2Demo.intro.start();
    } else {
      if (this.getComponentCount() == 1) {
        Java2Demo.intro.stop();
        this.remove(Java2Demo.intro);
        this.add(this.p, "East");
        if (Java2Demo.memoryCB.getState()) {
          Java2Demo.memorymonitor.surf.start();
        }

        if (Java2Demo.perfCB.getState()) {
          Java2Demo.performancemonitor.surf.start();
        }
      } else {
        this.remove(Java2Demo.group[this.index]);
      }

      this.index = Java2Demo.tabbedPane.getSelectedIndex() - 1;
      this.add(Java2Demo.group[this.index]);
      Java2Demo.group[this.index].setup(false);
    }

    this.revalidate();
  }
}
