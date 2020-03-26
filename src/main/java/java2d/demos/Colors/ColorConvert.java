//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Colors;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.color.ColorSpace;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java2d.Surface;

public class ColorConvert extends Surface {
  private static Image img;
  private static Color[] colors;

  static {
    colors =
        new Color[] {
          Color.red,
          Color.pink,
          Color.orange,
          Color.yellow,
          Color.green,
          Color.magenta,
          Color.cyan,
          Color.blue
        };
  }

  public ColorConvert() {
    this.setBackground(Color.white);
    img = this.getImage("clouds.jpg");
  }

  public static void main(String[] var0) {
    createDemoFrame(new ColorConvert());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    int var4 = img.getWidth(this);
    int var5 = img.getHeight(this);
    FontRenderContext var6 = var3.getFontRenderContext();
    Font var7 = var3.getFont();
    var3.setColor(Color.black);
    TextLayout var8 = new TextLayout("ColorConvertOp RGB->GRAY", var7, var6);
    var8.draw(
        var3,
        (float) ((double) (var1 / 2) - var8.getBounds().getWidth() / 2.0D),
        var8.getAscent() + var8.getLeading());
    BufferedImage var9 = new BufferedImage(var4, var5, 1);
    Graphics2D var10 = var9.createGraphics();
    RenderingHints var11 = var3.getRenderingHints();
    var10.setRenderingHints(var11);
    var10.drawImage(img, 0, 0, null);
    String var12 = "JavaColor";
    Font var13 = new Font("serif", 1, var4 / 6);
    var8 = new TextLayout(var12, var13, var6);
    Rectangle2D var14 = var8.getBounds();
    char[] var15 = var12.toCharArray();
    float var16 = 0.0F;
    int var17 = var4 / var15.length;
    int var18 = var5 / var15.length;

    for (int var19 = 0; var19 < var15.length; ++var19) {
      var8 = new TextLayout(String.valueOf(var15[var19]), var13, var6);
      Shape var20 = var8.getOutline(null);
      var10.setColor(colors[var19 % colors.length]);
      var8.draw(
          var10,
          (float) ((double) (var4 / 2) - var14.getWidth() / 2.0D + (double) var16),
          (float) ((double) (var5 / 2) + var14.getHeight() / 2.0D));
      var16 += (float) var20.getBounds().getWidth();
      var10.fillRect(var19 * var17, var5 - var18, var17, var18);
      var10.setColor(colors[colors.length - 1 - var19 % colors.length]);
      var10.fillRect(var19 * var17, 0, var17, var18);
    }

    ColorSpace var22 = ColorSpace.getInstance(1003);
    ColorConvertOp var23 = new ColorConvertOp(var22, var11);
    BufferedImage var21 = new BufferedImage(var4, var5, 1);
    var23.filter(var9, var21);
    var3.drawImage(var9, 10, 20, var1 / 2 - 20, var2 - 30, null);
    var3.drawImage(var21, var1 / 2 + 10, 20, var1 / 2 - 20, var2 - 30, null);
  }
}
