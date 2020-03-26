//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Arcs_Curves;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D.Float;
import java2d.AnimatingSurface;

public final class Ellipses extends AnimatingSurface {
  private static Color[] colors;

  static {
    colors =
        new Color[] {
          Color.BLUE,
          Color.CYAN,
          Color.GREEN,
          Color.MAGENTA,
          Color.ORANGE,
          Color.PINK,
          Color.RED,
          Color.YELLOW,
          Color.LIGHT_GRAY,
          Color.WHITE
        };
  }

  private Float[] ellipses;
  private double[] esize;
  private float[] estroke;
  private double maxSize;

  public Ellipses() {
    this.setBackground(Color.BLACK);
    this.ellipses = new Float[25];
    this.esize = new double[this.ellipses.length];
    this.estroke = new float[this.ellipses.length];

    for (int var1 = 0; var1 < this.ellipses.length; ++var1) {
      this.ellipses[var1] = new Float();
      this.getRandomXY(var1, 20.0D * Math.random(), 200, 200);
    }
  }

  public static void main(String[] var0) {
    createDemoFrame(new Ellipses());
  }

  public void getRandomXY(int var1, double var2, int var4, int var5) {
    this.esize[var1] = var2;
    this.estroke[var1] = 1.0F;
    double var6 = Math.random() * ((double) var4 - this.maxSize / 2.0D);
    double var8 = Math.random() * ((double) var5 - this.maxSize / 2.0D);
    this.ellipses[var1].setFrame(var6, var8, var2, var2);
  }

  public void reset(int var1, int var2) {
    this.maxSize = var1 / 10;

    for (int var3 = 0; var3 < this.ellipses.length; ++var3) {
      this.getRandomXY(var3, this.maxSize * Math.random(), var1, var2);
    }
  }

  public void step(int var1, int var2) {
    for (int var3 = 0; var3 < this.ellipses.length; ++var3) {
      float[] var10000 = this.estroke;
      var10000[var3] += 0.025F;
      int var10002 = (int) this.esize[var3]++;
      if (this.esize[var3] > this.maxSize) {
        this.getRandomXY(var3, 1.0D, var1, var2);
      } else {
        this.ellipses[var3].setFrame(
            this.ellipses[var3].getX(),
            this.ellipses[var3].getY(),
            this.esize[var3],
            this.esize[var3]);
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    for (int var4 = 0; var4 < this.ellipses.length; ++var4) {
      var3.setColor(colors[var4 % colors.length]);
      var3.setStroke(new BasicStroke(this.estroke[var4]));
      var3.draw(this.ellipses[var4]);
    }
  }
}
