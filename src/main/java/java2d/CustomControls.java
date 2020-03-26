//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public abstract class CustomControls extends JPanel implements Runnable {
  private static final Color blue = new Color(204, 204, 255);
  protected Thread thread;
  protected boolean doNotifier;
  private CustomControls.CCNotifierThread ccnt;
  private String name;

  public CustomControls() {
    this.name = "foo.bar Demo";
    this.setBorder(new EtchedBorder());
    this.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent var1) {
            if (CustomControls.this.thread == null) {
              CustomControls.this.start();
            } else {
              CustomControls.this.stop();
            }
          }
        });
  }

  public CustomControls(String var1) {
    this();
    this.name = var1 + " Demo";
  }

  public void paintComponent(Graphics var1) {
    super.paintComponent(var1);
    var1.setColor(this.doNotifier ? blue : Color.gray);
    var1.fillRect(this.getSize().width - 2, 0, 2, 2);
  }

  public void start() {
    if (this.thread == null) {
      this.thread = new Thread(this);
      this.thread.setPriority(1);
      this.thread.setName(this.name + " ccthread");
      this.thread.start();
      (this.ccnt = new CustomControls.CCNotifierThread()).start();
      this.ccnt.setName(this.name + " ccthread notifier");
    }
  }

  public synchronized void stop() {
    if (this.thread != null) {
      this.thread.interrupt();
      if (this.ccnt != null) {
        this.ccnt.interrupt();
      }
    }

    this.thread = null;
  }

  public void run() {}

  class CCNotifierThread extends Thread {
    CCNotifierThread() {}

    public void run() {
      while (true) {
        if (CustomControls.this.thread != null) {
          CustomControls.this.doNotifier = !CustomControls.this.doNotifier;
          CustomControls.this.repaint();

          try {
            Thread.sleep(444L);
            continue;
          } catch (Exception var2) {
          }
        }

        CustomControls.this.doNotifier = false;
        CustomControls.this.repaint();
        return;
      }
    }
  }
}
