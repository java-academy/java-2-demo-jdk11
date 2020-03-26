//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paths;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D.Double;
import java2d.Surface;

public class Append extends Surface {
  public Append() {
    this.setBackground(Color.WHITE);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Append());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    GeneralPath var4 = new GeneralPath(1);
    var4.moveTo((float) var1 * 0.25F, (float) var2 * 0.2F);
    var4.lineTo((float) var1 * 0.75F, (float) var2 * 0.2F);
    var4.closePath();
    var4.append(
        new Double(
            (double) var1 * 0.4D, (double) var2 * 0.3D, (double) var1 * 0.2D, (double) var2 * 0.1D),
        false);
    var3.setColor(Color.GRAY);
    var3.fill(var4);
    var3.setColor(Color.BLACK);
    var3.draw(var4);
    var3.drawString(
        "Append rect to path", (int) ((double) var1 * 0.25D), (int) ((double) var2 * 0.2D) - 5);
    var4.reset();
    var4.moveTo((float) var1 * 0.25F, (float) var2 * 0.6F);
    var4.lineTo((float) var1 * 0.75F, (float) var2 * 0.6F);
    var4.closePath();
    var4.append(
        new Double(
            (double) var1 * 0.4D, (double) var2 * 0.7D, (double) var1 * 0.2D, (double) var2 * 0.1D),
        true);
    var3.setColor(Color.GRAY);
    var3.fill(var4);
    var3.setColor(Color.BLACK);
    var3.draw(var4);
    var3.drawString(
        "Append, connect", (int) ((double) var1 * 0.25D), (int) ((double) var2 * 0.6D) - 5);
  }
}
