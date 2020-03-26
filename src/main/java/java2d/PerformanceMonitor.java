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
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PerformanceMonitor extends JPanel {
  PerformanceMonitor.Surface surf;

  public PerformanceMonitor() {
    this.setLayout(new BorderLayout());
    this.setBorder(new TitledBorder(new EtchedBorder(), "Performance"));
    this.add(this.surf = new PerformanceMonitor.Surface());
  }

  public class Surface extends JPanel implements Runnable {
    public Thread thread;
    private BufferedImage bimg;
    private Font font = new Font("Times New Roman", 0, 12);
    private JPanel panel;

    public Surface() {
      this.setBackground(Color.black);
      this.addMouseListener(
          new MouseAdapter() {
            public void mouseClicked(MouseEvent var1) {
              if (Surface.this.thread == null) {
                Surface.this.start();
              } else {
                Surface.this.stop();
              }
            }
          });
    }

    public Dimension getMinimumSize() {
      return this.getPreferredSize();
    }

    public Dimension getMaximumSize() {
      return this.getPreferredSize();
    }

    public Dimension getPreferredSize() {
      int var1 = this.getFontMetrics(this.font).getHeight();
      return new Dimension(135, 2 + var1 * 4);
    }

    public void paint(Graphics var1) {
      if (this.bimg != null) {
        var1.drawImage(this.bimg, 0, 0, this);
      }
    }

    public void start() {
      this.thread = new Thread(this);
      this.thread.setPriority(1);
      this.thread.setName("PerformanceMonitor");
      this.thread.start();
    }

    public synchronized void stop() {
      this.thread = null;
      this.setSurfaceState();
      this.notify();
    }

    public void setSurfaceState() {
      if (this.panel != null) {
        Component[] var1 = this.panel.getComponents();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
          Component var4 = var1[var3];
          if (((DemoPanel) var4).surface != null) {
            ((DemoPanel) var4).surface.setMonitor(this.thread != null);
          }
        }
      }
    }

    public void setPanel(JPanel var1) {
      this.panel = var1;
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1 && !this.isShowing() || this.getSize().width == 0) {
        try {
          Thread.sleep(500L);
        } catch (InterruptedException var13) {
          return;
        }
      }

      Dimension var2 = new Dimension(0, 0);
      Graphics2D var3 = null;
      FontMetrics var4 = null;
      int var5 = 0;
      int var6 = 0;

      while (this.thread == var1 && this.isShowing()) {
        if (this.getWidth() != var2.width || this.getHeight() != var2.height) {
          var2 = this.getSize();
          this.bimg = (BufferedImage) this.createImage(var2.width, var2.height);
          var3 = this.bimg.createGraphics();
          var3.setFont(this.font);
          var4 = var3.getFontMetrics();
          var5 = var4.getAscent();
          var6 = var4.getDescent();
          this.setSurfaceState();
        }

        var3.setBackground(this.getBackground());
        var3.clearRect(0, 0, var2.width, var2.height);
        if (this.panel != null) {
          var3.setColor(Color.green);
          int var7 = 1;
          Component[] var8 = this.panel.getComponents();
          int var9 = var8.length;

          for (int var10 = 0; var10 < var9; ++var10) {
            Component var11 = var8[var10];
            if (((DemoPanel) var11).surface != null) {
              String var12 = ((DemoPanel) var11).surface.perfStr;
              if (var12 != null) {
                var7 += var5;
                var3.drawString(var12, 4, var7 + 1);
                var7 += var6;
              }
            }
          }

          this.repaint();

          try {
            Thread.sleep(999L);
          } catch (InterruptedException var14) {
            break;
          }
        }
      }

      this.thread = null;
    }
  }
}
