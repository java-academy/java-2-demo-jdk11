//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Lines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Arc2D.Float;
import java2d.Surface;

public class Dash extends Surface {
  public Dash() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Dash());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    FontRenderContext var4 = var3.getFontRenderContext();
    Font var5 = var3.getFont();
    TextLayout var6 = new TextLayout("Dashes", var5, var4);
    float var7 = (float) var6.getBounds().getWidth();
    float var8 = var6.getAscent() + var6.getDescent();
    var3.setColor(Color.BLACK);
    var6.draw(var3, (float) (var1 / 2) - var7 / 2.0F, var8 + 5.0F);
    BasicStroke var9 =
        new BasicStroke(3.0F, 1, 1, 0.0F, new float[] {0.0F, 6.0F, 0.0F, 6.0F}, 0.0F);
    var3.setStroke(var9);
    var3.drawRect(3, 3, var1 - 6, var2 - 6);
    int var10 = 0;
    int var11 = var2 - 34;
    BasicStroke[] var12 = new BasicStroke[6];
    float var13 = 1.1F;

    for (int var14 = 0; var14 < var12.length; ++var13) {
      float[] var15 = new float[] {var13};
      BasicStroke var16 = new BasicStroke(1.0F, 0, 0, 10.0F, var15, 0.0F);
      var3.setStroke(var16);
      var3.drawLine(20, var11, var1 - 20, var11);
      var12[var14] = new BasicStroke(3.0F, 0, 0, 10.0F, var15, 0.0F);
      var11 += 5;
      ++var14;
    }

    Object var18 = null;
    boolean var17 = false;

    for (int var19 = 0; var19 < 6; ++var19) {
      var10 = var19 != 0 && var19 != 3 ? var10 + var1 / 3 : (var1 / 3 - var1 / 5) / 2;
      var11 = var19 <= 2 ? (int) var8 + var2 / 12 : var2 / 2;
      var3.setStroke(var12[var19]);
      var3.translate(var10, var11);
      switch (var19) {
        case 0:
          var18 = new Float(0.0F, 0.0F, (float) (var1 / 5), (float) (var2 / 4), 45.0F, 270.0F, 2);
          break;
        case 1:
          var18 =
              new java.awt.geom.Ellipse2D.Float(0.0F, 0.0F, (float) (var1 / 5), (float) (var2 / 4));
          break;
        case 2:
          var18 =
              new java.awt.geom.RoundRectangle2D.Float(
                  0.0F, 0.0F, (float) (var1 / 5), (float) (var2 / 4), 10.0F, 10.0F);
          break;
        case 3:
          var18 =
              new java.awt.geom.Rectangle2D.Float(
                  0.0F, 0.0F, (float) (var1 / 5), (float) (var2 / 4));
          break;
        case 4:
          var18 =
              new java.awt.geom.QuadCurve2D.Float(
                  0.0F, 0.0F, (float) (var1 / 10), (float) (var2 / 2), (float) (var1 / 5), 0.0F);
          break;
        case 5:
          var18 =
              new java.awt.geom.CubicCurve2D.Float(
                  0.0F,
                  0.0F,
                  (float) (var1 / 15),
                  (float) (var2 / 2),
                  (float) (var1 / 10),
                  (float) (var2 / 4),
                  (float) (var1 / 5),
                  0.0F);
      }

      var3.draw((Shape) var18);
      var3.translate(-var10, -var11);
    }
  }
}
