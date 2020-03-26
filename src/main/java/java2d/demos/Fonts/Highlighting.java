//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextHitInfo;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D.Float;
import java2d.AnimatingSurface;

public class Highlighting extends AnimatingSurface {
  private static String[] text = new String[] {"HILIGHTING", "Java2D"};
  private static Color[] colors;
  private static Font smallF;

  static {
    colors = new Color[] {Color.CYAN, Color.LIGHT_GRAY};
    smallF = new Font("Monospaced", 0, 8);
  }

  private int[] curPos;
  private TextLayout[] layouts;
  private Font[] fonts;

  public Highlighting() {
    this.setBackground(Color.WHITE);
    this.fonts = new Font[2];
    this.layouts = new TextLayout[this.fonts.length];
    this.curPos = new int[this.fonts.length];
  }

  public static void main(String[] var0) {
    createDemoFrame(new Highlighting());
  }

  public void reset(int var1, int var2) {
    this.fonts[0] = new Font("Monospaced", 0, var1 / text[0].length() + 8);
    this.fonts[1] = new Font("Serif", 1, var1 / text[1].length());

    for (int var3 = 0; var3 < this.layouts.length; ++var3) {
      this.curPos[var3] = 0;
    }
  }

  public void step(int var1, int var2) {
    this.setSleepAmount(900L);

    for (int var3 = 0; var3 < 2; ++var3) {
      if (this.layouts[var3] != null
          && this.curPos[var3]++ == this.layouts[var3].getCharacterCount()) {
        this.curPos[var3] = 0;
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    FontRenderContext var4 = var3.getFontRenderContext();

    for (int var5 = 0; var5 < 2; ++var5) {
      this.layouts[var5] = new TextLayout(text[var5], this.fonts[var5], var4);
      float var6 = this.layouts[var5].getAdvance();
      float var7 = ((float) var1 - var6) / 2.0F;
      float var8 = var5 == 0 ? (float) (var2 / 3) : (float) var2 * 0.75F;
      Shape var9 = this.layouts[var5].getLogicalHighlightShape(0, this.curPos[var5]);
      AffineTransform var10 = AffineTransform.getTranslateInstance(var7, var8);
      var9 = var10.createTransformedShape(var9);
      float var11 = (float) var9.getBounds2D().getY();
      float var12 = (float) var9.getBounds2D().getHeight();
      var3.setColor(colors[var5]);
      var3.fill(var9);
      Shape[] var13 = this.layouts[var5].getCaretShapes(this.curPos[var5]);
      Shape var14 = var10.createTransformedShape(var13[0]);
      var3.setColor(Color.BLACK);
      this.layouts[var5].draw(var3, var7, var8);
      var3.draw(var14);
      var3.draw(new Float(var7, var11, var6, var12));

      for (int var15 = 0; var15 <= this.layouts[var5].getCharacterCount(); ++var15) {
        float[] var16 = this.layouts[var5].getCaretInfo(TextHitInfo.leading(var15));
        String var17 = String.valueOf((int) var16[0]);
        TextLayout var18 = new TextLayout(var17, smallF, var4);
        var18.draw(
            var3,
            var7 + var16[0] - var18.getAdvance() / 2.0F,
            var11 + var12 + var18.getAscent() + 1.0F);
      }
    }
  }
}
