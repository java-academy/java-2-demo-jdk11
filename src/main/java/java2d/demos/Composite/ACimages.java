//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Composite;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java2d.Surface;

public class ACimages extends Surface {
  private static final String[] s =
      new String[] {
        "box", "fight", "magnify", "boxwave", "globe", "snooze", "tip", "thumbsup", "dukeplug"
      };
  private static Image[] imgs;
  private static Color[] colors;

  static {
    imgs = new Image[s.length];
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
          Color.LIGHT_GRAY
        };
  }

  public ACimages() {
    this.setBackground(Color.WHITE);

    for (int var1 = 0; var1 < imgs.length; ++var1) {
      imgs[var1] = this.getImage(s[var1] + ".png");
    }
  }

  public static void main(String[] var0) {
    createDemoFrame(new ACimages());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    float var4 = 0.0F;
    int var5 = var1 / 3;
    int var6 = (var2 - 45) / 3;
    float var7 = 0.0F;
    float var8 = 15.0F;

    for (int var9 = 0; var9 < imgs.length; ++var9) {
      var7 = var9 % 3 == 0 ? 0.0F : var7 + (float) (var1 / 3);
      switch (var9) {
        case 3:
          var8 = (float) (var2 / 3 + 15);
          break;
        case 6:
          var8 = (float) (var2 / 3 * 2 + 15);
      }

      var3.setComposite(AlphaComposite.SrcOver);
      var3.setColor(Color.BLACK);
      AlphaComposite var10 = AlphaComposite.SrcOver.derive(var4 += 0.1F);
      String var11 = "a=" + Float.toString(var4).substring(0, 3);
      (new TextLayout(var11, var3.getFont(), var3.getFontRenderContext()))
          .draw(var3, var7 + 3.0F, var8 - 2.0F);
      Object var12 = null;
      switch (var9 % 3) {
        case 0:
          var12 = new java.awt.geom.Ellipse2D.Float(var7, var8, (float) var5, (float) var6);
          break;
        case 1:
          var12 =
              new java.awt.geom.RoundRectangle2D.Float(
                  var7, var8, (float) var5, (float) var6, 25.0F, 25.0F);
          break;
        case 2:
          var12 = new java.awt.geom.Rectangle2D.Float(var7, var8, (float) var5, (float) var6);
      }

      var3.setColor(colors[var9]);
      var3.setComposite(var10);
      var3.fill((Shape) var12);
      var3.drawImage(imgs[var9], (int) var7, (int) var8, var5, var6, null);
    }
  }
}
