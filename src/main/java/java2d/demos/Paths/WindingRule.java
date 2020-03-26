//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paths;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java2d.Surface;

public class WindingRule extends Surface {
  public WindingRule() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new WindingRule());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    var3.translate((double) var1 * 0.2D, (double) var2 * 0.2D);
    GeneralPath var4 = new GeneralPath(1);
    var4.moveTo(0.0F, 0.0F);
    var4.lineTo((float) var1 * 0.5F, 0.0F);
    var4.lineTo((float) var1 * 0.5F, (float) var2 * 0.2F);
    var4.lineTo(0.0F, (float) var2 * 0.2F);
    var4.closePath();
    var4.moveTo((float) var1 * 0.05F, (float) var2 * 0.05F);
    var4.lineTo((float) var1 * 0.55F, (float) var2 * 0.05F);
    var4.lineTo((float) var1 * 0.55F, (float) var2 * 0.25F);
    var4.lineTo((float) var1 * 0.05F, (float) var2 * 0.25F);
    var4.closePath();
    var3.setColor(Color.LIGHT_GRAY);
    var3.fill(var4);
    var3.setColor(Color.BLACK);
    var3.draw(var4);
    var3.drawString("NON_ZERO rule", 0, -5);
    var3.translate(0.0D, (double) var2 * 0.45D);
    var4.setWindingRule(0);
    var3.setColor(Color.LIGHT_GRAY);
    var3.fill(var4);
    var3.setColor(Color.BLACK);
    var3.draw(var4);
    var3.drawString("EVEN_ODD rule", 0, -5);
  }
}
