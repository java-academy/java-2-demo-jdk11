//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.font.FontRenderContext;
import java.awt.font.ImageGraphicAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.ShapeGraphicAttribute;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java2d.Surface;

public class AttributedStr extends Surface {
  static final Color black = new Color(20, 20, 20);
  static final Color blue = new Color(94, 105, 176);
  static final Color yellow = new Color(255, 255, 140);
  static final Color red = new Color(149, 43, 42);
  static final Color white = new Color(240, 240, 255);
  static final String text = "  A quick brown  fox  jumped  over the lazy duke  ";
  static final AttributedString as =
      new AttributedString("  A quick brown  fox  jumped  over the lazy duke  ");
  static AttributedCharacterIterator aci;

  static {
    Double var0 = new Double(0.0D, 25.0D, 12.0D, 12.0D);
    ShapeGraphicAttribute var1 = new ShapeGraphicAttribute(var0, -1, false);
    as.addAttribute(TextAttribute.CHAR_REPLACEMENT, var1, 0, 1);
    Font var2 = new Font("sanserif", 3, 20);
    int var3 = "  A quick brown  fox  jumped  over the lazy duke  ".indexOf("quick");
    as.addAttribute(TextAttribute.FONT, var2, var3, var3 + 5);
    var3 = "  A quick brown  fox  jumped  over the lazy duke  ".indexOf("brown");
    var2 = new Font("serif", 1, 20);
    as.addAttribute(TextAttribute.FONT, var2, var3, var3 + 5);
    as.addAttribute(TextAttribute.FOREGROUND, red, var3, var3 + 5);
    var3 = "  A quick brown  fox  jumped  over the lazy duke  ".indexOf("fox");
    AffineTransform var4 = new AffineTransform();
    var4.rotate(Math.toRadians(10.0D));
    Font var5 = (new Font("serif", 1, 30)).deriveFont(var4);
    as.addAttribute(TextAttribute.FONT, var5, var3, var3 + 1);
    as.addAttribute(TextAttribute.FONT, var5, var3 + 1, var3 + 2);
    as.addAttribute(TextAttribute.FONT, var5, var3 + 2, var3 + 3);
    var4.setToRotation(Math.toRadians(-4.0D));
    var5 = var2.deriveFont(var4);
    var3 = "  A quick brown  fox  jumped  over the lazy duke  ".indexOf("jumped");
    as.addAttribute(TextAttribute.FONT, var5, var3, var3 + 6);
    var2 = new Font("serif", 3, 30);
    var3 = "  A quick brown  fox  jumped  over the lazy duke  ".indexOf("over");
    as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, var3, var3 + 4);
    as.addAttribute(TextAttribute.FOREGROUND, white, var3, var3 + 4);
    as.addAttribute(
        TextAttribute.FONT,
        var2,
        var3,
        "  A quick brown  fox  jumped  over the lazy duke  ".length());
    var2 = new Font("dialog", 0, 20);
    int var6 = "  A quick brown  fox  jumped  over the lazy duke  ".indexOf("duke");
    as.addAttribute(TextAttribute.FONT, var2, var3, var6 - 1);
    BufferedImage var7 = new BufferedImage(4, 4, 2);
    var7.setRGB(0, 0, -1);
    TexturePaint var8 = new TexturePaint(var7, new Rectangle(0, 0, 4, 4));
    as.addAttribute(TextAttribute.BACKGROUND, var8, var6, var6 + 4);
    var2 = new Font("serif", 1, 40);
    as.addAttribute(TextAttribute.FONT, var2, var6, var6 + 4);
  }

  public AttributedStr() {
    this.setBackground(Color.white);
    Font var1 = this.getFont("A.ttf");
    if (var1 != null) {
      var1 = var1.deriveFont(0, 70.0F);
    } else {
      var1 = new Font("serif", 0, 50);
    }

    int var2 = "  A quick brown  fox  jumped  over the lazy duke  ".indexOf("A") + 1;
    as.addAttribute(TextAttribute.FONT, var1, 0, var2);
    as.addAttribute(TextAttribute.FOREGROUND, white, 0, var2);
    var1 = new Font("dialog", 0, 40);
    int var3 = this.getFontMetrics(var1).getHeight();
    BufferedImage var4 = new BufferedImage(var3, var3, 2);
    Graphics2D var5 = var4.createGraphics();
    var5.drawImage(this.getImage("snooze.png"), 0, 0, var3, var3, null);
    ImageGraphicAttribute var6 = new ImageGraphicAttribute(var4, -1);
    as.addAttribute(
        TextAttribute.CHAR_REPLACEMENT,
        var6,
        "  A quick brown  fox  jumped  over the lazy duke  ".length() - 1,
        "  A quick brown  fox  jumped  over the lazy duke  ".length());
    aci = as.getIterator();
  }

  public static void main(String[] var0) {
    createDemoFrame(new AttributedStr());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    float var4 = 5.0F;
    float var5 = 0.0F;
    FontRenderContext var6 = var3.getFontRenderContext();
    LineBreakMeasurer var7 = new LineBreakMeasurer(aci, var6);
    var3.setPaint(new GradientPaint(0.0F, (float) var2, blue, (float) var1, 0.0F, black));
    var3.fillRect(0, 0, var1, var2);
    var3.setColor(white);
    String var8 = "AttributedString LineBreakMeasurer";
    Font var9 = new Font("serif", 0, 12);
    TextLayout var10 = new TextLayout(var8, var9, var6);
    var10.draw(var3, 5.0F, var5 += (float) var10.getBounds().getHeight());
    var3.setColor(yellow);

    while (var5 < (float) var2 - var10.getAscent()) {
      var7.setPosition(0);

      while (var7.getPosition() < "  A quick brown  fox  jumped  over the lazy duke  ".length()) {
        var10 = var7.nextLayout((float) var1 - var4);
        if (!var10.isLeftToRight()) {
          var4 = (float) var1 - var10.getAdvance();
        }

        var10.draw(var3, var4, var5 += var10.getAscent());
        var5 += var10.getDescent() + var10.getLeading();
      }
    }
  }
}
