//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Fonts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java2d.Surface;

public class Outline extends Surface {
  public Outline() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Outline());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    FontRenderContext var4 = var3.getFontRenderContext();
    Font var5 = new Font("sansserif", 0, var1 / 8);
    Font var6 = new Font("sansserif", 2, var1 / 8);
    String var7 = "AttributedString";
    AttributedString var8 = new AttributedString(var7);
    var8.addAttribute(TextAttribute.FONT, var5, 0, 10);
    var8.addAttribute(TextAttribute.FONT, var6, 10, var7.length());
    AttributedCharacterIterator var9 = var8.getIterator();
    TextLayout var10 = new TextLayout(var9, var4);
    float var11 = (float) var10.getBounds().getWidth();
    float var12 = (float) var10.getBounds().getHeight();
    Shape var13 =
        var10.getOutline(
            AffineTransform.getTranslateInstance(
                (float) (var1 / 2) - var11 / 2.0F, (double) var2 * 0.2D + (double) (var12 / 2.0F)));
    var3.setColor(Color.BLUE);
    var3.setStroke(new BasicStroke(1.5F));
    var3.draw(var13);
    var3.setColor(Color.MAGENTA);
    var3.fill(var13);
    var5 = new Font("serif", 1, var1 / 6);
    var10 = new TextLayout("Outline", var5, var4);
    var11 = (float) var10.getBounds().getWidth();
    var12 = (float) var10.getBounds().getHeight();
    var13 =
        var10.getOutline(
            AffineTransform.getTranslateInstance(
                (float) (var1 / 2) - var11 / 2.0F, (double) var2 * 0.5D + (double) (var12 / 2.0F)));
    var3.setColor(Color.BLACK);
    var3.draw(var13);
    var3.setColor(Color.RED);
    var3.fill(var13);
    var5 = new Font("sansserif", 2, var1 / 8);
    AffineTransform var14 = new AffineTransform();
    var14.shear(-0.2D, 0.0D);
    Font var15 = var5.deriveFont(var14);
    var10 = new TextLayout("Italic-Shear", var15, var4);
    var11 = (float) var10.getBounds().getWidth();
    var12 = (float) var10.getBounds().getHeight();
    var13 =
        var10.getOutline(
            AffineTransform.getTranslateInstance(
                (float) (var1 / 2) - var11 / 2.0F, (float) var2 * 0.8F + var12 / 2.0F));
    var3.setColor(Color.GREEN);
    var3.draw(var13);
    var3.setColor(Color.BLACK);
    var3.fill(var13);
  }
}
