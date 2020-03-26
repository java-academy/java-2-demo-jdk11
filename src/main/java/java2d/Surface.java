//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferUShort;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public abstract class Surface extends JPanel implements Printable {
  private static final int REPORTFRAMES = 30;
  static byte[] lut1Arr = new byte[] {0, -1};
  static byte[] lut2Arr = new byte[] {0, 85, -86, -1};
  static byte[] lut4Arr =
      new byte[] {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
  public Object AntiAlias;
  public Object Rendering;
  public AlphaComposite composite;
  public Paint texture;
  public String perfStr;
  public BufferedImage bimg;
  public int imageType;
  public String name;
  public boolean clearSurface;
  public boolean dontThread;
  public AnimatingSurface animating;
  protected long sleepAmount;
  private long orig;
  private long start;
  private long frame;
  private Toolkit toolkit;
  private boolean perfMonitor;
  private boolean outputPerf;
  private int biw;
  private int bih;
  private boolean clearOnce;
  private boolean toBeInitialized;

  public Surface() {
    this.AntiAlias = RenderingHints.VALUE_ANTIALIAS_ON;
    this.Rendering = RenderingHints.VALUE_RENDER_SPEED;
    this.clearSurface = true;
    this.sleepAmount = 50L;
    this.toBeInitialized = true;
    this.setDoubleBuffered(this instanceof AnimatingSurface);
    this.toolkit = this.getToolkit();
    this.name = this.getClass().getSimpleName();
    this.setImageType(0);

    try {
      if (System.getProperty("java2demo.perf") != null) {
        this.perfMonitor = this.outputPerf = true;
      }
    } catch (Exception var2) {
    }

    if (this instanceof AnimatingSurface) {
      this.animating = (AnimatingSurface) this;
    }
  }

  public static void createDemoFrame(Surface var0) {
    final DemoPanel var1 = new DemoPanel(var0);
    Frame var2 = new Frame("Java2D Demo - " + var0.name);
    var2.addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent var1x) {
            System.exit(0);
          }

          public void windowDeiconified(WindowEvent var1x) {
            var1.start();
          }

          public void windowIconified(WindowEvent var1x) {
            var1.stop();
          }
        });
    var2.add("Center", var1);
    var2.pack();
    var2.setSize(new Dimension(500, 300));
    var2.setVisible(true);
    if (var0.animating != null) {
      var0.animating.start();
    }
  }

  protected Image getImage(String var1) {
    return DemoImages.getImage(var1, this);
  }

  protected Font getFont(String var1) {
    return DemoFonts.getFont(var1);
  }

  public int getImageType() {
    return this.imageType;
  }

  public final void setImageType(int var1) {
    if (var1 == 0) {
      this.imageType = 1;
    } else {
      this.imageType = var1;
    }

    this.bimg = null;
  }

  public void setAntiAlias(boolean var1) {
    this.AntiAlias = var1 ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF;
  }

  public void setRendering(boolean var1) {
    this.Rendering = var1 ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_SPEED;
  }

  public void setTexture(Object var1) {
    if (var1 instanceof GradientPaint) {
      this.texture =
          new GradientPaint(
              0.0F, 0.0F, Color.white, (float) (this.getSize().width * 2), 0.0F, Color.green);
    } else {
      this.texture = (Paint) var1;
    }
  }

  public void setComposite(boolean var1) {
    this.composite = var1 ? AlphaComposite.getInstance(3, 0.5F) : null;
  }

  public void setMonitor(boolean var1) {
    this.perfMonitor = var1;
  }

  public long getSleepAmount() {
    return this.sleepAmount;
  }

  public void setSleepAmount(long var1) {
    this.sleepAmount = var1;
  }

  public BufferedImage createBufferedImage(Graphics2D var1, int var2, int var3, int var4) {
    BufferedImage var5 = null;
    if (var4 == 0) {
      var5 = var1.getDeviceConfiguration().createCompatibleImage(var2, var3);
    } else if (var4 > 0 && var4 < 14) {
      var5 = new BufferedImage(var2, var3, var4);
    } else if (var4 == 14) {
      var5 = this.createBinaryImage(var2, var3, 2);
    } else if (var4 == 15) {
      var5 = this.createBinaryImage(var2, var3, 4);
    } else if (var4 == 16) {
      var5 = this.createSGISurface(var2, var3, 32);
    } else if (var4 == 17) {
      var5 = this.createSGISurface(var2, var3, 16);
    }

    return var5;
  }

  private BufferedImage createBinaryImage(int var1, int var2, int var3) {
    int var4 = var1 * var3 / 8;
    if (var1 * var3 % 8 != 0) {
      ++var4;
    }

    byte[] var5 = new byte[var2 * var4];
    IndexColorModel var6 = null;
    switch (var3) {
      case 1:
        var6 = new IndexColorModel(var3, lut1Arr.length, lut1Arr, lut1Arr, lut1Arr);
        break;
      case 2:
        var6 = new IndexColorModel(var3, lut2Arr.length, lut2Arr, lut2Arr, lut2Arr);
        break;
      case 3:
      default:
        Logger.getLogger(Surface.class.getName())
            .log(Level.SEVERE, null, new Exception("Invalid # of bit per pixel"));
        break;
      case 4:
        var6 = new IndexColorModel(var3, lut4Arr.length, lut4Arr, lut4Arr, lut4Arr);
    }

    DataBufferByte var7 = new DataBufferByte(var5, var5.length);
    WritableRaster var8 = Raster.createPackedRaster(var7, var1, var2, var3, null);
    return new BufferedImage(var6, var8, false, null);
  }

  private BufferedImage createSGISurface(int var1, int var2, int var3) {
    int var4 = -16777216;
    char var5 = '\uf800';
    int var6 = 16711680;
    short var7 = 1984;
    char var8 = '\uff00';
    byte var9 = 62;
    DirectColorModel var10 = null;
    DataBufferInt var11 = null;
    WritableRaster var12 = null;
    switch (var3) {
      case 16:
        short[] var13 = new short[var1 * var2];
        var10 = new DirectColorModel(16, var5, var7, var9);
        DataBufferUShort var15 = new DataBufferUShort(var13, var13.length);
        var12 =
            Raster.createPackedRaster(var15, var1, var2, var1, new int[] {var5, var7, var9}, null);
        break;
      case 32:
        int[] var14 = new int[var1 * var2];
        var10 = new DirectColorModel(32, var4, var6, var8);
        var11 = new DataBufferInt(var14, var14.length);
        var12 =
            Raster.createPackedRaster(var11, var1, var2, var1, new int[] {var4, var6, var8}, null);
        break;
      default:
        Logger.getLogger(Surface.class.getName())
            .log(Level.SEVERE, null, new Exception("Invalid # of bit per pixel"));
    }

    return new BufferedImage(var10, var12, false, null);
  }

  public Graphics2D createGraphics2D(int var1, int var2, BufferedImage var3, Graphics var4) {
    Graphics2D var5 = null;
    if (var3 != null) {
      var5 = var3.createGraphics();
    } else {
      var5 = (Graphics2D) var4;
    }

    var5.setBackground(this.getBackground());
    var5.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.AntiAlias);
    var5.setRenderingHint(RenderingHints.KEY_RENDERING, this.Rendering);
    if (this.clearSurface || this.clearOnce) {
      var5.clearRect(0, 0, var1, var2);
      this.clearOnce = false;
    }

    if (this.texture != null) {
      var5.setComposite(AlphaComposite.SrcOver);
      var5.setPaint(this.texture);
      var5.fillRect(0, 0, var1, var2);
    }

    if (this.composite != null) {
      var5.setComposite(this.composite);
    }

    return var5;
  }

  public abstract void render(int var1, int var2, Graphics2D var3);

  public void paintImmediately(int var1, int var2, int var3, int var4) {
    RepaintManager var5 = null;
    boolean var6 = true;
    if (!this.isDoubleBuffered()) {
      var5 = RepaintManager.currentManager(this);
      var6 = var5.isDoubleBufferingEnabled();
      var5.setDoubleBufferingEnabled(false);
    }

    super.paintImmediately(var1, var2, var3, var4);
    if (var5 != null) {
      var5.setDoubleBufferingEnabled(var6);
    }
  }

  public void paint(Graphics var1) {
    super.paint(var1);
    Dimension var2 = this.getSize();
    if (this.biw != var2.width || this.bih != var2.height) {
      this.toBeInitialized = true;
      this.biw = var2.width;
      this.bih = var2.height;
    }

    if (this.imageType == 1) {
      this.bimg = null;
    } else if (this.bimg == null || this.toBeInitialized) {
      this.bimg =
          this.createBufferedImage((Graphics2D) var1, var2.width, var2.height, this.imageType - 2);
      this.clearOnce = true;
    }

    if (this.toBeInitialized) {
      if (this.animating != null) {
        this.animating.reset(var2.width, var2.height);
      }

      this.toBeInitialized = false;
      this.startClock();
    }

    if (this.animating != null && this.animating.running()) {
      this.animating.step(var2.width, var2.height);
    }

    Graphics2D var3 = this.createGraphics2D(var2.width, var2.height, this.bimg, var1);
    this.render(var2.width, var2.height, var3);
    var3.dispose();
    if (this.bimg != null) {
      var1.drawImage(this.bimg, 0, 0, null);
      this.toolkit.sync();
    }

    if (this.perfMonitor) {
      this.LogPerformance();
    }
  }

  public int print(Graphics var1, PageFormat var2, int var3) throws PrinterException {
    if (var3 >= 1) {
      return 1;
    } else {
      Graphics2D var4 = (Graphics2D) var1;
      var4.translate(var2.getImageableX(), var2.getImageableY());
      var4.translate(var2.getImageableWidth() / 2.0D, var2.getImageableHeight() / 2.0D);
      Dimension var5 = this.getSize();
      double var6 =
          Math.min(
              var2.getImageableWidth() / (double) var5.width,
              var2.getImageableHeight() / (double) var5.height);
      if (var6 < 1.0D) {
        var4.scale(var6, var6);
      }

      var4.translate((double) (-var5.width) / 2.0D, (double) (-var5.height) / 2.0D);
      if (this.bimg == null) {
        Graphics2D var8 = this.createGraphics2D(var5.width, var5.height, null, var4);
        this.render(var5.width, var5.height, var8);
        var8.dispose();
      } else {
        var4.drawImage(this.bimg, 0, 0, this);
      }

      return 0;
    }
  }

  public void startClock() {
    this.orig = System.currentTimeMillis();
    this.start = this.orig;
    this.frame = 0L;
  }

  private void LogPerformance() {
    if (this.frame % 30L == 0L) {
      long var1 = System.currentTimeMillis();
      long var3 = var1 - this.start;
      if (this.frame == 0L) {
        this.perfStr = this.name + " " + var3 + " ms";
        if (this.animating == null || !this.animating.running()) {
          this.frame = -1L;
        }
      } else {
        String var5 = Float.toString(30.0F / ((float) var3 / 1000.0F));
        var5 = var5.length() < 4 ? var5 : var5.substring(0, 4);
        this.perfStr = this.name + " " + var5 + " fps";
      }

      if (this.outputPerf) {
        System.out.println(this.perfStr);
      }

      this.start = var1;
    }

    ++this.frame;
  }

  public void verbose() {
    String var1 = "  " + this.name + " ";
    if (this.animating != null && this.animating.running()) {
      var1 = var1.concat(" Running");
    } else if (this instanceof AnimatingSurface) {
      var1 = var1.concat(" Stopped");
    }

    var1 = var1.concat(" " + GlobalControls.screenCombo.getSelectedItem());
    var1.concat(
        this.AntiAlias == RenderingHints.VALUE_ANTIALIAS_ON ? " ANTIALIAS_ON " : " ANTIALIAS_OFF ");
    var1.concat(
        this.Rendering == RenderingHints.VALUE_RENDER_QUALITY
            ? "RENDER_QUALITY "
            : "RENDER_SPEED ");
    if (this.texture != null) {
      var1 = var1.concat("Texture ");
    }

    if (this.composite != null) {
      var1 = var1.concat("Composite=" + this.composite.getAlpha() + " ");
    }

    Runtime var2 = Runtime.getRuntime();
    var2.gc();
    float var3 = (float) var2.freeMemory();
    float var4 = (float) var2.totalMemory();
    var1 = var1.concat((var4 - var3) / 1024.0F + "K used");
    System.out.println(var1);
  }
}
