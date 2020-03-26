//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Colors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D.Float;
import java2d.Surface;

public class BullsEye extends Surface {
  public BullsEye() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new BullsEye());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Color[] var4 = new Color[] {Color.RED.darker(), Color.RED};

    for (int var5 = 0; var5 < 18; ++var5) {
      float var6 = (float) (var5 + 2) / 2.0F;
      float var7 = 5.0F + var6 * (float) (var1 / 2 / 10);
      float var8 = 5.0F + var6 * (float) (var2 / 2 / 10);
      float var9 = (float) (var1 - 10) - var6 * (float) var1 / 10.0F;
      float var10 = (float) (var2 - 10) - var6 * (float) var2 / 10.0F;
      float var11 = var5 == 0 ? 0.1F : 1.0F / (19.0F - (float) var5);
      if (var5 >= 16) {
        var3.setColor(var4[var5 - 16]);
      } else {
        var3.setColor(new Color(0.0F, 0.0F, 0.0F, var11));
      }

      var3.fill(new Float(var7, var8, var9, var10));
    }
  }
}
