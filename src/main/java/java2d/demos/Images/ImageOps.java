//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Images;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.RescaleOp;
import java2d.ControlsSurface;
import java2d.CustomControls;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageOps extends ControlsSurface implements ChangeListener {
  private static final String[] imgName = new String[] {"bld.jpg", "boat.png"};
  private static final BufferedImage[] img;
  private static final String[] opsName;
  private static final BufferedImageOp[] biop;
  private static final int low = 100;
  private static final int high = 200;
  private static int rescaleFactor;
  private static float rescaleOffset;

  static {
    img = new BufferedImage[imgName.length];
    opsName =
        new String[] {
          "Threshold",
          "RescaleOp",
          "Invert",
          "Yellow Invert",
          "3x3 Blur",
          "3x3 Sharpen",
          "3x3 Edge",
          "5x5 Edge"
        };
    biop = new BufferedImageOp[opsName.length];
    rescaleFactor = 128;
    rescaleOffset = 0.0F;
    thresholdOp(100, 200);
    byte var0 = 1;
    int var7 = var0 + 1;
    biop[var0] = new RescaleOp(1.0F, 0.0F, null);
    byte[] var1 = new byte[256];
    byte[] var2 = new byte[256];

    for (int var3 = 0; var3 < 256; ++var3) {
      var1[var3] = (byte) (256 - var3);
      var2[var3] = (byte) var3;
    }

    biop[var7++] = new LookupOp(new ByteLookupTable(0, var1), null);
    byte[][] var8 = new byte[][] {var1, var1, var2};
    biop[var7++] = new LookupOp(new ByteLookupTable(0, var8), null);
    int[][] var4 = new int[][] {{3, 3}, {3, 3}, {3, 3}, {5, 5}};
    float[][] var5 =
        new float[][] {
          {0.1F, 0.1F, 0.1F, 0.1F, 0.2F, 0.1F, 0.1F, 0.1F, 0.1F},
          {-1.0F, -1.0F, -1.0F, -1.0F, 9.0F, -1.0F, -1.0F, -1.0F, -1.0F},
          {0.0F, -1.0F, 0.0F, -1.0F, 5.0F, -1.0F, 0.0F, -1.0F, 0.0F},
          {
            -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F,
            24.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F, -1.0F,
            -1.0F
          }
        };

    for (int var6 = 0; var6 < var5.length; ++var7) {
      biop[var7] = new ConvolveOp(new Kernel(var4[var6][0], var4[var6][1], var5[var6]));
      ++var6;
    }
  }

  protected JSlider slider1;
  protected JSlider slider2;
  private int opsIndex;
  private int imgIndex;

  public ImageOps() {
    this.setDoubleBuffered(true);
    this.setBackground(Color.white);

    for (int var1 = 0; var1 < imgName.length; ++var1) {
      Image var2 = this.getImage(imgName[var1]);
      int var3 = var2.getWidth(this);
      int var4 = var2.getHeight(this);
      img[var1] = new BufferedImage(var3, var4, 1);
      img[var1].createGraphics().drawImage(var2, 0, 0, null);
    }

    this.slider1 = new JSlider(1, 0, 255, 100);
    this.slider1.setPreferredSize(new Dimension(15, 100));
    this.slider1.addChangeListener(this);
    this.slider2 = new JSlider(1, 0, 255, 200);
    this.slider2.setPreferredSize(new Dimension(15, 100));
    this.slider2.addChangeListener(this);
    this.setControls(new Component[] {new ImageOps.DemoControls(this), this.slider1, this.slider2});
    this.setConstraints(new String[] {"North", "West", "East"});
  }

  public static void thresholdOp(int var0, int var1) {
    byte[] var2 = new byte[256];

    for (int var3 = 0; var3 < 256; ++var3) {
      if (var3 > var1) {
        var2[var3] = -1;
      } else if (var3 < var0) {
        var2[var3] = 0;
      } else {
        var2[var3] = (byte) var3;
      }
    }

    biop[0] = new LookupOp(new ByteLookupTable(0, var2), null);
  }

  public static void main(String[] var0) {
    createDemoFrame(new ImageOps());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    int var4 = img[this.imgIndex].getWidth(null);
    int var5 = img[this.imgIndex].getHeight(null);
    AffineTransform var6 = var3.getTransform();
    var3.scale((double) var1 / (double) var4, (double) var2 / (double) var5);
    var3.drawImage(img[this.imgIndex], biop[this.opsIndex], 0, 0);
    var3.setTransform(var6);
  }

  public void stateChanged(ChangeEvent var1) {
    if (var1.getSource().equals(this.slider1)) {
      if (this.opsIndex == 0) {
        thresholdOp(this.slider1.getValue(), 200);
      } else {
        rescaleFactor = this.slider1.getValue();
        biop[1] = new RescaleOp((float) rescaleFactor / 128.0F, rescaleOffset, null);
      }
    } else if (this.opsIndex == 0) {
      thresholdOp(100, this.slider2.getValue());
    } else {
      rescaleOffset = (float) this.slider2.getValue();
      biop[1] = new RescaleOp((float) rescaleFactor / 128.0F, rescaleOffset, null);
    }

    this.repaint();
  }

  static class DemoControls extends CustomControls implements ActionListener {
    ImageOps demo;
    JComboBox imgCombo;
    JComboBox opsCombo;
    Font font = new Font("serif", 0, 10);

    public DemoControls(ImageOps var1) {
      super(var1.name);
      this.demo = var1;
      this.add(this.imgCombo = new JComboBox());
      this.imgCombo.setFont(this.font);
      String[] var2 = ImageOps.imgName;
      int var3 = var2.length;

      int var4;
      String var5;
      for (var4 = 0; var4 < var3; ++var4) {
        var5 = var2[var4];
        this.imgCombo.addItem(var5);
      }

      this.imgCombo.addActionListener(this);
      this.add(this.opsCombo = new JComboBox());
      this.opsCombo.setFont(this.font);
      var2 = ImageOps.opsName;
      var3 = var2.length;

      for (var4 = 0; var4 < var3; ++var4) {
        var5 = var2[var4];
        this.opsCombo.addItem(var5);
      }

      this.opsCombo.addActionListener(this);
    }

    public void actionPerformed(ActionEvent var1) {
      if (var1.getSource().equals(this.opsCombo)) {
        this.demo.opsIndex = this.opsCombo.getSelectedIndex();
        if (this.demo.opsIndex == 0) {
          this.demo.slider1.setValue(100);
          this.demo.slider2.setValue(200);
          this.demo.slider1.setEnabled(true);
          this.demo.slider2.setEnabled(true);
        } else if (this.demo.opsIndex == 1) {
          this.demo.slider1.setValue(ImageOps.rescaleFactor);
          this.demo.slider2.setValue((int) ImageOps.rescaleOffset);
          this.demo.slider1.setEnabled(true);
          this.demo.slider2.setEnabled(true);
        } else {
          this.demo.slider1.setEnabled(false);
          this.demo.slider2.setEnabled(false);
        }
      } else if (var1.getSource().equals(this.imgCombo)) {
        this.demo.imgIndex = this.imgCombo.getSelectedIndex();
      }

      this.demo.repaint(10L);
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 39);
    }

    public void run() {
      try {
        Thread.sleep(1111L);
      } catch (Exception var8) {
        return;
      }

      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 0; var2 < ImageOps.imgName.length; ++var2) {
          this.imgCombo.setSelectedIndex(var2);

          for (int var3 = 0; var3 < ImageOps.opsName.length; ++var3) {
            this.opsCombo.setSelectedIndex(var3);
            if (var3 <= 1) {
              for (int var4 = 50; var4 <= 200; var4 += 10) {
                this.demo.slider1.setValue(var4);

                try {
                  Thread.sleep(200L);
                } catch (InterruptedException var6) {
                  return;
                }
              }
            }

            try {
              Thread.sleep(4444L);
            } catch (InterruptedException var7) {
              return;
            }
          }
        }
      }

      this.thread = null;
    }
  }
}
