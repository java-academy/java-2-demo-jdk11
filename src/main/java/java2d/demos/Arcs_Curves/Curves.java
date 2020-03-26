//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Arcs_Curves;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.PathIterator;
import java.awt.geom.QuadCurve2D.Float;
import java2d.Surface;

public class Curves extends Surface {
  private static Color[] colors;

  static {
    colors = new Color[] {Color.BLUE, Color.GREEN, Color.RED};
  }

  public Curves() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Curves());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    var3.setColor(Color.BLACK);
    FontRenderContext var4 = var3.getFontRenderContext();
    TextLayout var5 = new TextLayout("QuadCurve2D", var3.getFont(), var4);
    float var6 = (float) ((double) var1 * 0.5D - var5.getBounds().getWidth() / 2.0D);
    var5.draw(var3, var6, var5.getAscent());
    var5 = new TextLayout("CubicCurve2D", var3.getFont(), var4);
    var6 = (float) ((double) var1 * 0.5D - var5.getBounds().getWidth() / 2.0D);
    var5.draw(var3, var6, (float) var2 * 0.5F);
    var3.setStroke(new BasicStroke(5.0F));
    float var7 = 20.0F;

    for (int var8 = 0; var8 < 2; ++var8) {
      for (int var9 = 0; var9 < 3; ++var9) {
        Object var10 = null;
        if (var8 == 0) {
          var10 =
              new Float(
                  (float) var1 * 0.1F, var7, (float) var1 * 0.5F, 50.0F, (float) var1 * 0.9F, var7);
        } else {
          var10 =
              new java.awt.geom.CubicCurve2D.Float(
                  (float) var1 * 0.1F,
                  var7,
                  (float) var1 * 0.4F,
                  var7 - 15.0F,
                  (float) var1 * 0.6F,
                  var7 + 15.0F,
                  (float) var1 * 0.9F,
                  var7);
        }

        var3.setColor(colors[var9]);
        if (var9 != 2) {
          var3.draw((Shape) var10);
        }

        PathIterator var11;
        if (var9 == 1) {
          var3.setColor(Color.LIGHT_GRAY);

          for (var11 = ((Shape) var10).getPathIterator(null); !var11.isDone(); var11.next()) {
            float[] var14 = new float[6];
            switch (var11.currentSegment(var14)) {
              case 0:
              case 1:
                var3.fill(new java.awt.geom.Rectangle2D.Float(var14[0], var14[1], 5.0F, 5.0F));
                break;
              case 2:
              case 3:
                var3.fill(new java.awt.geom.Rectangle2D.Float(var14[0], var14[1], 5.0F, 5.0F));
                if (var14[2] != 0.0F) {
                  var3.fill(new java.awt.geom.Rectangle2D.Float(var14[2], var14[3], 5.0F, 5.0F));
                }

                if (var14[4] != 0.0F) {
                  var3.fill(new java.awt.geom.Rectangle2D.Float(var14[4], var14[5], 5.0F, 5.0F));
                }
            }
          }
        } else if (var9 == 2) {
          var11 = ((Shape) var10).getPathIterator(null);
          FlatteningPathIterator var12 = new FlatteningPathIterator(var11, 0.1D);

          while (!var12.isDone()) {
            float[] var13 = new float[6];
            switch (var12.currentSegment(var13)) {
              case 0:
              case 1:
                var3.fill(new java.awt.geom.Ellipse2D.Float(var13[0], var13[1], 3.0F, 3.0F));
              default:
                var12.next();
            }
          }
        }

        var7 += (float) (var2 / 6);
      }

      var7 = (float) (var2 / 2 + 15);
    }
  }
}
