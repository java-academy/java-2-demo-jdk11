//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.awt.image.BufferedImage;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MemoryMonitor extends JPanel {
  static JCheckBox dateStampCB = new JCheckBox("Output Date Stamp");
  public MemoryMonitor.Surface surf;
  JPanel controls;
  boolean doControls;
  JTextField tf;

  public MemoryMonitor() {
    this.setLayout(new BorderLayout());
    this.setBorder(new TitledBorder(new EtchedBorder(), "Memory Monitor"));
    this.add(this.surf = new MemoryMonitor.Surface());
    this.controls = new JPanel();
    this.controls.setPreferredSize(new Dimension(135, 80));
    Font var1 = new Font("serif", 0, 10);
    JLabel var2 = new JLabel("Sample Rate");
    var2.setFont(var1);
    var2.setForeground(Color.BLACK);
    this.controls.add(var2);
    this.tf = new JTextField("1000");
    this.tf.setPreferredSize(new Dimension(45, 20));
    this.controls.add(this.tf);
    this.controls.add(var2 = new JLabel("ms"));
    var2.setFont(var1);
    var2.setForeground(Color.BLACK);
    this.controls.add(dateStampCB);
    dateStampCB.setFont(var1);
    this.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent var1) {
            MemoryMonitor.this.removeAll();
            if (MemoryMonitor.this.doControls = !MemoryMonitor.this.doControls) {
              MemoryMonitor.this.surf.stop();
              MemoryMonitor.this.add(MemoryMonitor.this.controls);
            } else {
              try {
                MemoryMonitor.this.surf.sleepAmount =
                    Long.parseLong(MemoryMonitor.this.tf.getText().trim());
              } catch (Exception var3) {
              }

              MemoryMonitor.this.surf.start();
              MemoryMonitor.this.add(MemoryMonitor.this.surf);
            }

            MemoryMonitor.this.revalidate();
            MemoryMonitor.this.repaint();
          }
        });
  }

  public static void main(String[] var0) {
    final MemoryMonitor var1 = new MemoryMonitor();
    WindowAdapter var2 =
        new WindowAdapter() {
          public void windowClosing(WindowEvent var1x) {
            System.exit(0);
          }

          public void windowDeiconified(WindowEvent var1x) {
            var1.surf.start();
          }

          public void windowIconified(WindowEvent var1x) {
            var1.surf.stop();
          }
        };
    JFrame var3 = new JFrame("Java2D Demo - MemoryMonitor");
    var3.addWindowListener(var2);
    var3.getContentPane().add("Center", var1);
    var3.pack();
    var3.setSize(new Dimension(200, 200));
    var3.setVisible(true);
    var1.surf.start();
  }

  public class Surface extends JPanel implements Runnable {
    public Thread thread;
    public long sleepAmount = 1000L;
    private int w;
    private int h;
    private BufferedImage bimg;
    private Graphics2D big;
    private Font font = new Font("Times New Roman", 0, 11);
    private Runtime r = Runtime.getRuntime();
    private int columnInc;
    private int[] pts;
    private int ptNum;
    private int ascent;
    private int descent;
    private Rectangle graphOutlineRect = new Rectangle();
    private Rectangle2D mfRect = new Float();
    private Rectangle2D muRect = new Float();
    private Line2D graphLine = new java.awt.geom.Line2D.Float();
    private Color graphColor = new Color(46, 139, 87);
    private Color mfColor = new Color(0, 100, 0);
    private String usedStr;

    public Surface() {
      this.setBackground(Color.BLACK);
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
      return new Dimension(135, 80);
    }

    public void paint(Graphics var1) {
      if (this.big != null) {
        this.big.setBackground(this.getBackground());
        this.big.clearRect(0, 0, this.w, this.h);
        float var2 = (float) this.r.freeMemory();
        float var3 = (float) this.r.totalMemory();
        this.big.setColor(Color.GREEN);
        this.big.drawString((int) var3 / 1024 + "K allocated", 4.0F, (float) this.ascent + 0.5F);
        this.usedStr = (int) (var3 - var2) / 1024 + "K used";
        this.big.drawString(this.usedStr, 4, this.h - this.descent);
        float var4 = (float) (this.ascent + this.descent);
        float var5 = (float) this.h - var4 * 2.0F - 0.5F;
        float var6 = var5 / 10.0F;
        float var7 = 20.0F;
        this.big.setColor(this.mfColor);
        int var8 = (int) (var2 / var3 * 10.0F);

        int var9;
        for (var9 = 0; var9 < var8; ++var9) {
          this.mfRect.setRect(5.0D, var4 + (float) var9 * var6, var7, var6 - 1.0F);
          this.big.fill(this.mfRect);
        }

        this.big.setColor(Color.GREEN);

        while (var9 < 10) {
          this.muRect.setRect(5.0D, var4 + (float) var9 * var6, var7, var6 - 1.0F);
          this.big.fill(this.muRect);
          ++var9;
        }

        this.big.setColor(this.graphColor);
        byte var10 = 30;
        int var11 = (int) var4;
        int var12 = this.w - var10 - 5;
        int var13 = (int) var5;
        this.graphOutlineRect.setRect(var10, var11, var12, var13);
        this.big.draw(this.graphOutlineRect);
        int var14 = var13 / 10;

        int var15;
        for (var15 = var11; var15 <= var13 + var11; var15 += var14) {
          this.graphLine.setLine(var10, var15, var10 + var12, var15);
          this.big.draw(this.graphLine);
        }

        var15 = var12 / 15;
        if (this.columnInc == 0) {
          this.columnInc = var15;
        }

        int var16;
        for (var16 = var10 + this.columnInc; var16 < var12 + var10; var16 += var15) {
          this.graphLine.setLine(var16, var11, var16, var11 + var13);
          this.big.draw(this.graphLine);
        }

        --this.columnInc;
        if (this.pts == null) {
          this.pts = new int[var12];
          this.ptNum = 0;
        } else if (this.pts.length != var12) {
          Object var18 = null;
          int[] var19;
          if (this.ptNum < var12) {
            var19 = new int[this.ptNum];
            System.arraycopy(this.pts, 0, var19, 0, var19.length);
          } else {
            var19 = new int[var12];
            System.arraycopy(this.pts, this.pts.length - var19.length, var19, 0, var19.length);
            this.ptNum = var19.length - 2;
          }

          this.pts = new int[var12];
          System.arraycopy(var19, 0, this.pts, 0, var19.length);
        } else {
          this.big.setColor(Color.YELLOW);
          this.pts[this.ptNum] = (int) ((float) var11 + (float) var13 * (var2 / var3));
          var16 = var10 + var12 - this.ptNum;

          for (int var17 = 0; var17 < this.ptNum; ++var16) {
            if (var17 != 0) {
              if (this.pts[var17] != this.pts[var17 - 1]) {
                this.big.drawLine(var16 - 1, this.pts[var17 - 1], var16, this.pts[var17]);
              } else {
                this.big.fillRect(var16, this.pts[var17], 1, 1);
              }
            }

            ++var17;
          }

          if (this.ptNum + 2 == this.pts.length) {
            for (var16 = 1; var16 < this.ptNum; ++var16) {
              this.pts[var16 - 1] = this.pts[var16];
            }

            --this.ptNum;
          } else {
            ++this.ptNum;
          }
        }

        var1.drawImage(this.bimg, 0, 0, this);
      }
    }

    public void start() {
      this.thread = new Thread(this);
      this.thread.setPriority(1);
      this.thread.setName("MemoryMonitor");
      this.thread.start();
    }

    public synchronized void stop() {
      this.thread = null;
      this.notify();
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1 && !this.isShowing() || this.getSize().width == 0) {
        try {
          Thread.sleep(500L);
        } catch (InterruptedException var4) {
          return;
        }
      }

      while (this.thread == var1 && this.isShowing()) {
        Dimension var2 = this.getSize();
        if (var2.width != this.w || var2.height != this.h) {
          this.w = var2.width;
          this.h = var2.height;
          this.bimg = (BufferedImage) this.createImage(this.w, this.h);
          this.big = this.bimg.createGraphics();
          this.big.setFont(this.font);
          FontMetrics var3 = this.big.getFontMetrics(this.font);
          this.ascent = var3.getAscent();
          this.descent = var3.getDescent();
        }

        this.repaint();

        try {
          Thread.sleep(this.sleepAmount);
        } catch (InterruptedException var5) {
          break;
        }

        if (MemoryMonitor.dateStampCB.isSelected()) {
          System.out.println((new Date()).toString() + " " + this.usedStr);
        }
      }

      this.thread = null;
    }
  }
}
