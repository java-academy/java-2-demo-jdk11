//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paths;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java2d.Surface;

public class CurveQuadTo extends Surface {
  public CurveQuadTo() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new CurveQuadTo());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    GeneralPath var4 = new GeneralPath(0);
    var4.moveTo((float) var1 * 0.2F, (float) var2 * 0.25F);
    var4.curveTo(
        (float) var1 * 0.4F,
        (float) var2 * 0.5F,
        (float) var1 * 0.6F,
        0.0F,
        (float) var1 * 0.8F,
        (float) var2 * 0.25F);
    var4.moveTo((float) var1 * 0.2F, (float) var2 * 0.6F);
    var4.quadTo((float) var1 * 0.5F, (float) var2 * 1.0F, (float) var1 * 0.8F, (float) var2 * 0.6F);
    var3.setColor(Color.LIGHT_GRAY);
    var3.fill(var4);
    var3.setColor(Color.BLACK);
    var3.draw(var4);
    var3.drawString("curveTo", (int) ((double) var1 * 0.2D), (int) ((float) var2 * 0.25F) - 5);
    var3.drawString("quadTo", (int) ((double) var1 * 0.2D), (int) ((float) var2 * 0.6F) - 5);
  }
}
