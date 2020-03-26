//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Arcs_Curves;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D.Float;
import java2d.AnimatingSurface;

public class Arcs extends AnimatingSurface {
  private static final int CLOSE = 0;
  private static final int OPEN = 1;
  private static final int FORWARD = 0;
  private static final int BACKWARD = 1;
  private static final int DOWN = 2;
  private static final int UP = 3;
  private static String[] types = new String[] {"Arc2D.OPEN", "Arc2D.CHORD", "Arc2D.PIE"};
  private int aw;
  private int ah;
  private int x;
  private int y;
  private int angleStart = 45;
  private int angleExtent = 270;
  private int mouth = 0;
  private int direction = 0;

  public Arcs() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Arcs());
  }

  public void reset(int var1, int var2) {
    this.x = 0;
    this.y = 0;
    this.aw = var1 / 12;
    this.ah = var2 / 12;
  }

  public void step(int var1, int var2) {
    if (this.x + this.aw >= var1 - 5 && this.direction == 0) {
      this.direction = 2;
    }

    if (this.y + this.ah >= var2 - 5 && this.direction == 2) {
      this.direction = 1;
    }

    if (this.x - this.aw <= 5 && this.direction == 1) {
      this.direction = 3;
    }

    if (this.y - this.ah <= 5 && this.direction == 3) {
      this.direction = 0;
    }

    if (this.mouth == 0) {
      this.angleStart -= 5;
      this.angleExtent += 10;
    }

    if (this.mouth == 1) {
      this.angleStart += 5;
      this.angleExtent -= 10;
    }

    if (this.direction == 0) {
      this.x += 5;
      this.y = 0;
    }

    if (this.direction == 2) {
      this.x = var1;
      this.y += 5;
    }

    if (this.direction == 1) {
      this.x -= 5;
      this.y = var2;
    }

    if (this.direction == 3) {
      this.x = 0;
      this.y -= 5;
    }

    if (this.angleStart == 0) {
      this.mouth = 1;
    }

    if (this.angleStart > 45) {
      this.mouth = 0;
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    var3.setStroke(new BasicStroke(5.0F));

    for (int var4 = 0; var4 < types.length; ++var4) {
      Float var5 = new Float(var4);
      var5.setFrame(
          (double) ((var4 + 1) * var1) * 0.2D,
          (double) ((var4 + 1) * var2) * 0.2D,
          (double) var1 * 0.17D,
          (double) var2 * 0.17D);
      var5.setAngleStart(45.0D);
      var5.setAngleExtent(270.0D);
      var3.setColor(Color.BLUE);
      var3.draw(var5);
      var3.setColor(Color.GRAY);
      var3.fill(var5);
      var3.setColor(Color.BLACK);
      var3.drawString(
          types[var4],
          (int) ((double) ((var4 + 1) * var1) * 0.2D),
          (int) ((double) ((var4 + 1) * var2) * 0.2D - 3.0D));
    }

    Float var6 = new Float(2);
    var6.setFrame(0.0D, 0.0D, this.aw, this.ah);
    var6.setAngleStart(this.angleStart);
    var6.setAngleExtent(this.angleExtent);
    AffineTransform var7 = AffineTransform.getTranslateInstance(this.x, this.y);
    switch (this.direction) {
      case 1:
        var7.rotate(Math.toRadians(180.0D));
        break;
      case 2:
        var7.rotate(Math.toRadians(90.0D));
        break;
      case 3:
        var7.rotate(Math.toRadians(270.0D));
    }

    var3.setColor(Color.BLUE);
    var3.fill(var7.createTransformedShape(var6));
  }
}
