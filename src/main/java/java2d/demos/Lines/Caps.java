//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Lines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D.Float;
import java2d.Surface;

public class Caps extends Surface {
  private static int[] cap = new int[] {0, 1, 2};
  private static String[] desc = new String[] {"Butt Cap", "Round Cap", "Square Cap"};

  public Caps() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Caps());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    FontRenderContext var4 = var3.getFontRenderContext();
    Font var5 = var3.getFont();
    var3.setColor(Color.BLACK);

    for (int var6 = 0; var6 < cap.length; ++var6) {
      var3.setStroke(new BasicStroke(15.0F, cap[var6], 0));
      var3.draw(
          new Float(
              (float) (var1 / 4),
              (float) ((var6 + 1) * var2 / 4),
              (float) (var1 - var1 / 4),
              (float) ((var6 + 1) * var2 / 4)));
      TextLayout var7 = new TextLayout(desc[var6], var5, var4);
      var7.draw(
          var3,
          (float) ((double) (var1 / 2) - var7.getBounds().getWidth() / 2.0D),
          (float) ((var6 + 1) * var2 / 4 - 10));
    }
  }
}
