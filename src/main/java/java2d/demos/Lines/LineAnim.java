//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Lines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Float;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java2d.AnimatingSurface;

public class LineAnim extends AnimatingSurface {
  private static final int CLOCKWISE = 0;
  private static int[] caps = new int[] {0, 2, 1};
  private static int[] joins = new int[] {0, 2, 1};
  private static Color[] colors;
  private static BasicStroke bs1;

  static {
    colors = new Color[] {Color.GRAY, Color.PINK, Color.LIGHT_GRAY};
    bs1 = new BasicStroke(1.0F);
  }

  private Line2D[] lines = new Line2D[3];
  private int[] rAmt;
  private int[] direction;
  private int[] speed;
  private BasicStroke[] strokes;
  private GeneralPath path;
  private Point2D[] pts;
  private float size;
  private Ellipse2D ellipse;

  public LineAnim() {
    this.rAmt = new int[this.lines.length];
    this.direction = new int[this.lines.length];
    this.speed = new int[this.lines.length];
    this.strokes = new BasicStroke[this.lines.length];
    this.ellipse = new Double();
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new LineAnim());
  }

  public void reset(int var1, int var2) {
    this.size = var1 > var2 ? (float) var2 / 6.0F : (float) var1 / 6.0F;

    for (int var3 = 0; var3 < this.lines.length; ++var3) {
      this.lines[var3] = new Float(0.0F, 0.0F, this.size, 0.0F);
      this.strokes[var3] = new BasicStroke(this.size / 3.0F, caps[var3], joins[var3]);
      this.rAmt[var3] = var3 * 360 / this.lines.length;
      this.direction[var3] = var3 % 2;
      this.speed[var3] = var3 + 1;
    }

    this.path = new GeneralPath();
    this.path.moveTo(this.size, -this.size / 2.0F);
    this.path.lineTo(this.size + this.size / 2.0F, 0.0F);
    this.path.lineTo(this.size, this.size / 2.0F);
    this.ellipse.setFrame(
        (float) (var1 / 2) - this.size * 2.0F - 4.5F,
        (float) (var2 / 2) - this.size * 2.0F - 4.5F,
        this.size * 4.0F,
        this.size * 4.0F);
    PathIterator var7 = this.ellipse.getPathIterator(null, 0.9D);
    Point2D[] var4 = new Point2D[100];
    int var5 = 0;

    while (!var7.isDone()) {
      float[] var6 = new float[6];
      switch (var7.currentSegment(var6)) {
        case 0:
        case 1:
          var4[var5] = new java.awt.geom.Point2D.Float(var6[0], var6[1]);
          ++var5;
        default:
          var7.next();
      }
    }

    this.pts = new Point2D[var5];
    System.arraycopy(var4, 0, this.pts, 0, var5);
  }

  public void step(int var1, int var2) {
    for (int var3 = 0; var3 < this.lines.length; ++var3) {
      int[] var10000;
      if (this.direction[var3] == 0) {
        var10000 = this.rAmt;
        var10000[var3] += this.speed[var3];
        if (this.rAmt[var3] == 360) {
          this.rAmt[var3] = 0;
        }
      } else {
        var10000 = this.rAmt;
        var10000[var3] -= this.speed[var3];
        if (this.rAmt[var3] == 0) {
          this.rAmt[var3] = 360;
        }
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    this.ellipse.setFrame(
        (float) (var1 / 2) - this.size,
        (float) (var2 / 2) - this.size,
        this.size * 2.0F,
        this.size * 2.0F);
    var3.setColor(Color.BLACK);
    var3.draw(this.ellipse);

    int var4;
    for (var4 = 0; var4 < this.lines.length; ++var4) {
      AffineTransform var5 = AffineTransform.getTranslateInstance(var1 / 2, var2 / 2);
      var5.rotate(Math.toRadians(this.rAmt[var4]));
      var3.setStroke(this.strokes[var4]);
      var3.setColor(colors[var4]);
      var3.draw(var5.createTransformedShape(this.lines[var4]));
      var3.draw(var5.createTransformedShape(this.path));
      int var6 = (int) ((double) this.rAmt[var4] / 360.0D * (double) this.pts.length);
      var6 = var6 == this.pts.length ? this.pts.length - 1 : var6;
      this.ellipse.setFrame(this.pts[var6].getX(), this.pts[var6].getY(), 9.0D, 9.0D);
      var3.fill(this.ellipse);
    }

    var3.setStroke(bs1);
    var3.setColor(Color.BLACK);

    for (var4 = 0; var4 < this.pts.length; ++var4) {
      this.ellipse.setFrame(this.pts[var4].getX(), this.pts[var4].getY(), 9.0D, 9.0D);
      var3.draw(this.ellipse);
    }
  }
}
