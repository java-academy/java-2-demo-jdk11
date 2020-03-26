//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paths;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.GeneralPath;
import java2d.Surface;

public class FillStroke extends Surface {
  public FillStroke() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new FillStroke());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    GeneralPath var4 = new GeneralPath(0);
    var4.moveTo((float) var1 * 0.5F, (float) var2 * 0.15F);
    var4.lineTo((float) var1 * 0.8F, (float) var2 * 0.75F);
    var4.lineTo((float) var1 * 0.2F, (float) var2 * 0.75F);
    var3.setColor(Color.LIGHT_GRAY);
    var3.fill(var4);
    var3.setColor(Color.BLACK);
    var3.setStroke(new BasicStroke(10.0F));
    var3.draw(var4);
    TextLayout var5 =
        new TextLayout("Fill, Stroke w/o closePath", var3.getFont(), var3.getFontRenderContext());
    var5.draw(
        var3,
        (float) ((double) (var1 / 2) - var5.getBounds().getWidth() / 2.0D),
        (float) var2 * 0.85F);
  }
}
