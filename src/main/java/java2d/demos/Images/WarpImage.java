//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.CubicCurve2D.Float;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java2d.AnimatingSurface;

public class WarpImage extends AnimatingSurface {
  private static final int FORWARD = 0;
  private static final int BACK = 1;
  private static int iw;
  private static int ih;
  private static int iw2;
  private static int ih2;
  private static Image img;
  private Point2D[] pts;
  private int direction = 0;
  private int pNum;
  private int x;
  private int y;

  public WarpImage() {
    this.setBackground(Color.white);
    img = this.getImage("surfing.png");
    iw = img.getWidth(this);
    ih = img.getHeight(this);
    iw2 = iw / 2;
    ih2 = ih / 2;
  }

  public static void main(String[] var0) {
    createDemoFrame(new WarpImage());
  }

  public void reset(int var1, int var2) {
    this.pNum = 0;
    this.direction = 0;
    Float var3 =
        new Float(
            (float) var1 * 0.2F,
            (float) var2 * 0.5F,
            (float) var1 * 0.4F,
            0.0F,
            (float) var1 * 0.6F,
            (float) var2,
            (float) var1 * 0.8F,
            (float) var2 * 0.5F);
    PathIterator var4 = var3.getPathIterator(null, 0.1D);
    Point2D[] var5 = new Point2D[200];
    int var6 = 0;

    while (!var4.isDone()) {
      float[] var7 = new float[6];
      switch (var4.currentSegment(var7)) {
        case 0:
        case 1:
          var5[var6] = new java.awt.geom.Point2D.Float(var7[0], var7[1]);
        default:
          ++var6;
          var4.next();
      }
    }

    this.pts = new Point2D[var6];
    System.arraycopy(var5, 0, this.pts, 0, var6);
  }

  public void step(int var1, int var2) {
    if (this.pts != null) {
      this.x = (int) this.pts[this.pNum].getX();
      this.y = (int) this.pts[this.pNum].getY();
      if (this.direction == 0 && ++this.pNum == this.pts.length) {
        this.direction = 1;
      }

      if (this.direction == 1 && --this.pNum == 0) {
        this.direction = 0;
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    var3.drawImage(img, 0, 0, this.x, this.y, 0, 0, iw2, ih2, this);
    var3.drawImage(img, this.x, 0, var1, this.y, iw2, 0, iw, ih2, this);
    var3.drawImage(img, 0, this.y, this.x, var2, 0, ih2, iw2, ih, this);
    var3.drawImage(img, this.x, this.y, var1, var2, iw2, ih2, iw, ih, this);
  }
}
