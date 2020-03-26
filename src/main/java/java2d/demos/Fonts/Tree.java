//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Fonts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java2d.AnimatingSurface;

public class Tree extends AnimatingSurface {
  static Font theFont = new Font("serif", 0, 1);
  static double Twidth = 0.6D;
  static double Rwidth = 0.6D;
  static double FontHeight = 0.75D;
  static Color[] colors;

  static {
    colors = new Color[] {Color.BLUE, Color.RED.darker(), Color.GREEN.darker()};
  }

  private char theC = 'A';
  private Character theT;
  private Character theR;

  public Tree() {
    this.theT = new Character(this.theC);
    this.theR = new Character((char) (this.theC + 1));
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Tree());
  }

  public void reset(int var1, int var2) {}

  public void step(int var1, int var2) {
    this.setSleepAmount(4000L);
    this.theT = new Character(++this.theC);
    this.theR = new Character((char) (this.theC + 1));
    if (this.theR.compareTo(new Character('z')) == 0) {
      this.theC = 'A';
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    int var4 = Math.min(var1, var2);
    AffineTransform var5 = new AffineTransform();
    var5.translate((double) (var1 - var4) / 2.0D, (double) (var2 - var4) / 2.0D);
    var5.scale(var4, var4);
    var5.translate(0.5D, 0.5D);
    var5.scale(0.3D, 0.3D);
    var5.translate(-(Twidth + Rwidth), FontHeight / 4.0D);
    var3.transform(var5);
    this.tree(var3, (double) var4 * 0.3D, 0);
  }

  public void tree(Graphics2D var1, double var2, int var4) {
    var1.setColor(colors[var4 % 3]);
    (new TextLayout(this.theT.toString(), theFont, var1.getFontRenderContext()))
        .draw(var1, 0.0F, 0.0F);
    if (var2 > 10.0D) {
      AffineTransform var5 = new AffineTransform();
      var5.setToTranslation(Twidth, -0.1D);
      var5.scale(0.6D, 0.6D);
      var1.transform(var5);
      var2 *= 0.6D;
      (new TextLayout(this.theR.toString(), theFont, var1.getFontRenderContext()))
          .draw(var1, 0.0F, 0.0F);
      var5.setToTranslation(Rwidth + 0.75D, 0.0D);
      var1.transform(var5);
      Graphics2D var6 = (Graphics2D) var1.create();
      var5.setToRotation(-1.5707963267948966D);
      var6.transform(var5);
      this.tree(var6, var2, var4 + 1);
      var6.dispose();
      var5.setToTranslation(0.75D, 0.0D);
      var5.rotate(-1.5707963267948966D);
      var5.scale(-1.0D, 1.0D);
      var5.translate(-Twidth, 0.0D);
      var1.transform(var5);
      this.tree(var1, var2, var4);
    }

    var1.setTransform(new AffineTransform());
  }
}
