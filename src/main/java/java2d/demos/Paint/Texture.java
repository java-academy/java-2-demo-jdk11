//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java2d.Surface;

public class Texture extends Surface {
  private static TexturePaint bluedots;
  private static TexturePaint greendots;
  private static TexturePaint triangles;
  private static TexturePaint blacklines;
  private static TexturePaint gradient;

  static {
    BufferedImage var0 = new BufferedImage(10, 10, 1);
    Graphics2D var1 = var0.createGraphics();
    var1.setBackground(Color.WHITE);
    var1.clearRect(0, 0, 10, 10);
    GeneralPath var2 = new GeneralPath();
    var2.moveTo(0.0F, 0.0F);
    var2.lineTo(5.0F, 10.0F);
    var2.lineTo(10.0F, 0.0F);
    var2.closePath();
    var1.setColor(Color.LIGHT_GRAY);
    var1.fill(var2);
    triangles = new TexturePaint(var0, new Rectangle(0, 0, 10, 10));
    var0 = new BufferedImage(5, 5, 1);
    var1 = var0.createGraphics();
    var1.setColor(Color.BLACK);
    var1.fillRect(0, 0, 5, 5);
    var1.setColor(Color.GRAY);
    var1.fillRect(1, 1, 4, 4);
    blacklines = new TexturePaint(var0, new Rectangle(0, 0, 5, 5));
    byte var3 = 30;
    byte var4 = 30;
    var0 = new BufferedImage(var3, var4, 1);
    var1 = var0.createGraphics();
    Color var5 = Color.WHITE;
    Color var6 = Color.LIGHT_GRAY;
    var1.setPaint(
        new GradientPaint(0.0F, 0.0F, var5, (float) var3 * 0.35F, (float) var4 * 0.35F, var6));
    var1.fillRect(0, 0, var3 / 2, var4 / 2);
    var1.setPaint(
        new GradientPaint(var3, 0.0F, var5, (float) var3 * 0.65F, (float) var4 * 0.35F, var6));
    var1.fillRect(var3 / 2, 0, var3 / 2, var4 / 2);
    var1.setPaint(
        new GradientPaint(0.0F, var4, var5, (float) var3 * 0.35F, (float) var4 * 0.65F, var6));
    var1.fillRect(0, var4 / 2, var3 / 2, var4 / 2);
    var1.setPaint(
        new GradientPaint(var3, var4, var5, (float) var3 * 0.65F, (float) var4 * 0.65F, var6));
    var1.fillRect(var3 / 2, var4 / 2, var3 / 2, var4 / 2);
    gradient = new TexturePaint(var0, new Rectangle(0, 0, var3, var4));
    var0 = new BufferedImage(2, 2, 1);
    var0.setRGB(0, 0, -1);
    var0.setRGB(1, 0, -1);
    var0.setRGB(0, 1, -1);
    var0.setRGB(1, 1, -16776961);
    bluedots = new TexturePaint(var0, new Rectangle(0, 0, 2, 2));
    var0 = new BufferedImage(2, 2, 1);
    var0.setRGB(0, 0, -1);
    var0.setRGB(1, 0, -1);
    var0.setRGB(0, 1, -1);
    var0.setRGB(1, 1, -16711936);
    greendots = new TexturePaint(var0, new Rectangle(0, 0, 2, 2));
  }

  public Texture() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Texture());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Rectangle var4 = new Rectangle(10, 10, var1 - 20, var2 / 2 - 20);
    var3.setPaint(gradient);
    var3.fill(var4);
    var3.setPaint(Color.GREEN);
    var3.setStroke(new BasicStroke(20.0F));
    var3.draw(var4);
    var3.setPaint(blacklines);
    var3.setStroke(new BasicStroke(15.0F));
    var3.draw(var4);
    Font var5 = new Font("Times New Roman", 1, var1 / 5);
    TextLayout var6 = new TextLayout("Texture", var5, var3.getFontRenderContext());
    int var7 = (int) var6.getBounds().getWidth();
    int var8 = (int) var6.getBounds().getHeight();
    Shape var9 =
        var6.getOutline(
            AffineTransform.getTranslateInstance(
                var1 / 2 - var7 / 2, (double) var2 * 0.25D + (double) (var8 / 2)));
    var3.setColor(Color.BLACK);
    var3.setStroke(new BasicStroke(3.0F));
    var3.draw(var9);
    var3.setPaint(greendots);
    var3.fill(var9);
    var4.setLocation(10, var2 / 2 + 10);
    var3.setPaint(triangles);
    var3.fill(var4);
    var3.setPaint(blacklines);
    var3.setStroke(new BasicStroke(20.0F));
    var3.draw(var4);
    var3.setPaint(Color.GREEN);
    var3.setStroke(new BasicStroke(4.0F));
    var3.draw(var4);
    var5 = new Font("serif", 1, var1 / 4);
    var6 = new TextLayout("Paint", var5, var3.getFontRenderContext());
    var7 = (int) var6.getBounds().getWidth();
    var8 = (int) var6.getBounds().getHeight();
    var9 =
        var6.getOutline(
            AffineTransform.getTranslateInstance(
                var1 / 2 - var7 / 2, (double) var2 * 0.75D + (double) (var8 / 2)));
    var3.setColor(Color.BLACK);
    var3.setStroke(new BasicStroke(5.0F));
    var3.draw(var9);
    var3.setPaint(bluedots);
    var3.fill(var9);
  }
}
