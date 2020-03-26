//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Mix;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class Balls extends AnimatingControlsSurface {
  private static Color[] colors;

  static {
    colors =
        new Color[] {
          Color.RED,
          Color.ORANGE,
          Color.YELLOW,
          Color.GREEN.darker(),
          Color.BLUE,
          new Color(75, 0, 82),
          new Color(238, 130, 238)
        };
  }

  protected Balls.Ball[] balls;
  protected boolean clearToggle;
  protected JComboBox combo;
  private long now;
  private long deltaT;
  private long lasttime;
  private boolean active;

  public Balls() {
    this.balls = new Balls.Ball[colors.length];
    this.setBackground(Color.WHITE);

    for (int var1 = 0; var1 < colors.length; ++var1) {
      this.balls[var1] = new Balls.Ball(colors[var1], 30);
    }

    this.balls[0].isSelected = true;
    this.balls[3].isSelected = true;
    this.balls[4].isSelected = true;
    this.balls[6].isSelected = true;
    this.setControls(new Component[] {new Balls.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Balls());
  }

  public void reset(int var1, int var2) {
    if (var1 > 400 && var2 > 100) {
      this.combo.setSelectedIndex(5);
    }
  }

  public void step(int var1, int var2) {
    if (this.lasttime == 0L) {
      this.lasttime = System.currentTimeMillis();
    }

    this.now = System.currentTimeMillis();
    this.deltaT = this.now - this.lasttime;
    this.active = false;
    Balls.Ball[] var3 = this.balls;
    int var4 = var3.length;

    int var5;
    Balls.Ball var6;
    for (var5 = 0; var5 < var4; ++var5) {
      var6 = var3[var5];
      if (var6 == null) {
        return;
      }

      var6.step(this.deltaT, var1, var2);
      if ((double) var6.Vy > 0.02D
          || (double) (-var6.Vy) > 0.02D
          || var6.y + (float) var6.bsize < (float) var2) {
        this.active = true;
      }
    }

    if (!this.active) {
      var3 = this.balls;
      var4 = var3.length;

      for (var5 = 0; var5 < var4; ++var5) {
        var6 = var3[var5];
        var6.Vx = (float) Math.random() / 4.0F - 0.125F;
        var6.Vy = -((float) Math.random()) / 4.0F - 0.2F;
      }

      this.clearToggle = true;
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Balls.Ball[] var4 = this.balls;
    int var5 = var4.length;

    for (int var6 = 0; var6 < var5; ++var6) {
      Balls.Ball var7 = var4[var6];
      if (var7 != null && var7.imgs[var7.index] != null && var7.isSelected) {
        var3.drawImage(var7.imgs[var7.index], (int) var7.x, (int) var7.y, this);
      }
    }

    this.lasttime = this.now;
  }

  protected static final class Ball {
    public static final int nImgs = 5;
    private static final float inelasticity = 0.96F;
    private static final float Ax = 0.0F;
    private static final float Ay = 2.0E-4F;
    private static final int UP = 0;
    private static final int DOWN = 1;
    public int bsize;
    public float x;
    public float y;
    public float Vx = 0.1F;
    public float Vy = 0.05F;
    public BufferedImage[] imgs;
    public int index = (int) (Math.random() * 4.0D);
    private int indexDirection = 0;
    private float jitter;
    private Color color;
    private boolean isSelected;

    public Ball(Color var1, int var2) {
      this.color = var1;
      this.makeImages(var2);
    }

    public void makeImages(int var1) {
      this.bsize = var1 * 2;
      int var2 = var1;
      byte[] var3 = new byte[var1 * 2 * var1 * 2];
      int var4 = 0;
      int var5 = 2 * var1;

      while (true) {
        --var5;
        int var9;
        int var11;
        if (var5 < 0) {
          this.imgs = new BufferedImage[5];
          short var15 = 255;
          byte[] var16 = new byte[256];
          var16[0] = (byte) var15;
          byte[] var17 = new byte[256];
          var17[0] = (byte) var15;
          byte[] var18 = new byte[256];
          var18[0] = (byte) var15;

          for (var9 = 0; var9 < this.imgs.length; ++var9) {
            float var19 = 0.5F + ((float) var9 + 1.0F) / (float) this.imgs.length / 2.0F;

            for (var11 = var4; var11 >= 1; --var11) {
              float var12 = (float) var11 / (float) var4;
              var16[var11] =
                  (byte) this.blend(this.blend(this.color.getRed(), 255, var12), var15, var19);
              var17[var11] =
                  (byte) this.blend(this.blend(this.color.getGreen(), 255, var12), var15, var19);
              var18[var11] =
                  (byte) this.blend(this.blend(this.color.getBlue(), 255, var12), var15, var19);
            }

            IndexColorModel var20 = new IndexColorModel(8, var4 + 1, var16, var17, var18, 0);
            DataBufferByte var21 = new DataBufferByte(var3, var3.length);
            int[] var13 = new int[] {0};
            WritableRaster var14 =
                Raster.createInterleavedRaster(var21, var2 * 2, var2 * 2, var2 * 2, 1, var13, null);
            this.imgs[var9] = new BufferedImage(var20, var14, var20.isAlphaPremultiplied(), null);
          }

          return;
        }

        int var6 = (int) (Math.sqrt(var2 * var2 - (var5 - var2) * (var5 - var2)) + 0.5D);
        int var7 = var5 * var2 * 2 + var2 - var6;

        for (int var8 = -var6; var8 < var6; ++var8) {
          var9 = var8 + 15;
          int var10 = var5 - var2 + 15;
          var11 = (int) (Math.hypot(var9, var10) + 0.5D);
          if (var11 > var4) {
            var4 = var11;
          }

          var3[var7++] = var11 <= 0 ? 1 : (byte) var11;
        }
      }
    }

    private int blend(int var1, int var2, float var3) {
      return (int) ((float) var2 + (float) (var1 - var2) * var3);
    }

    public void step(long var1, int var3, int var4) {
      this.jitter = (float) Math.random() * 0.01F - 0.005F;
      this.x =
          (float)
              ((double) this.x
                  + (double) (this.Vx * (float) var1)
                  + 0.0D * (double) var1 * (double) var1);
      this.y =
          (float)
              ((double) this.y
                  + (double) (this.Vy * (float) var1)
                  + 9.999999747378752E-5D * (double) var1 * (double) var1);
      if (this.x <= 0.0F) {
        this.x = 0.0F;
        this.Vx = -this.Vx * 0.96F + this.jitter;
      }

      if (this.x + (float) this.bsize >= (float) var3) {
        this.x = (float) (var3 - this.bsize);
        this.Vx = -this.Vx * 0.96F + this.jitter;
      }

      if (this.y <= 0.0F) {
        this.y = 0.0F;
        this.Vy = -this.Vy * 0.96F + this.jitter;
      }

      if (this.y + (float) this.bsize >= (float) var4) {
        this.y = (float) (var4 - this.bsize);
        this.Vx *= 0.96F;
        this.Vy = -this.Vy * 0.96F + this.jitter;
      }

      this.Vy += 2.0E-4F * (float) var1;
      this.Vx += 0.0F * (float) var1;
      if (this.indexDirection == 0) {
        ++this.index;
      }

      if (this.indexDirection == 1) {
        --this.index;
      }

      if (this.index + 1 == 5) {
        this.indexDirection = 1;
      }

      if (this.index == 0) {
        this.indexDirection = 0;
      }
    }
  }

  final class DemoControls extends CustomControls implements ActionListener {
    Balls demo;
    JToolBar toolbar;

    public DemoControls(Balls var2) {
      super(var2.name);
      this.demo = var2;
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("Clear", true);
      this.addTool("R", var2.balls[0].isSelected);
      this.addTool("O", var2.balls[1].isSelected);
      this.addTool("Y", var2.balls[2].isSelected);
      this.addTool("G", var2.balls[3].isSelected);
      this.addTool("B", var2.balls[4].isSelected);
      this.addTool("I", var2.balls[5].isSelected);
      this.addTool("V", var2.balls[6].isSelected);
      this.add(Balls.this.combo = new JComboBox());
      Balls.this.combo.addItem("10");
      Balls.this.combo.addItem("20");
      Balls.this.combo.addItem("30");
      Balls.this.combo.addItem("40");
      Balls.this.combo.addItem("50");
      Balls.this.combo.addItem("60");
      Balls.this.combo.addItem("70");
      Balls.this.combo.addItem("80");
      Balls.this.combo.setSelectedIndex(2);
      Balls.this.combo.addActionListener(this);
    }

    public void addTool(String var1, boolean var2) {
      JToggleButton var3 = (JToggleButton) this.toolbar.add(new JToggleButton(var1));
      var3.setFocusPainted(false);
      var3.setSelected(var2);
      var3.addActionListener(this);
      int var4 = var3.getPreferredSize().width;
      Dimension var5 = new Dimension(var4, 21);
      var3.setPreferredSize(var5);
      var3.setMaximumSize(var5);
      var3.setMinimumSize(var5);
    }

    public void actionPerformed(ActionEvent var1) {
      if (!(var1.getSource() instanceof JComboBox)) {
        JToggleButton var7 = (JToggleButton) var1.getSource();
        if (var7.getText().equals("Clear")) {
          this.demo.clearSurface = var7.isSelected();
        } else {
          int var8 = this.toolbar.getComponentIndex(var7) - 1;
          this.demo.balls[var8].isSelected = var7.isSelected();
        }

      } else {
        int var2 = Integer.parseInt((String) Balls.this.combo.getSelectedItem());
        Balls.Ball[] var3 = this.demo.balls;
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
          Balls.Ball var6 = var3[var5];
          var6.makeImages(var2);
        }
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 40);
    }

    public void run() {
      try {
        Thread.sleep(999L);
      } catch (Exception var4) {
        return;
      }

      Thread var1 = Thread.currentThread();
      ((AbstractButton) this.toolbar.getComponentAtIndex(2)).doClick();

      while (this.thread == var1) {
        try {
          Thread.sleep(222L);
        } catch (InterruptedException var3) {
          return;
        }

        if (this.demo.clearToggle) {
          if (this.demo.clearSurface) {
            Balls.this.combo.setSelectedIndex((int) (Math.random() * 5.0D));
          }

          ((AbstractButton) this.toolbar.getComponentAtIndex(0)).doClick();
          this.demo.clearToggle = false;
        }
      }

      this.thread = null;
    }
  }
}
