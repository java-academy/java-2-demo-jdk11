//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java2d.AnimatingSurface;
import java2d.DemoPanel;
import javax.swing.JButton;

public class DukeAnim extends AnimatingSurface implements ImageObserver {
  private static Image agif;
  private static Image clouds;
  private static int aw;
  private static int ah;
  private static int cw;
  private int x;
  private JButton b;

  public DukeAnim() {
    this.setBackground(Color.white);
    clouds = this.getImage("clouds.jpg");
    agif = this.getImage("duke.running.gif");
    aw = agif.getWidth(this) / 2;
    ah = agif.getHeight(this) / 2;
    cw = clouds.getWidth(this);
    this.dontThread = true;
  }

  public static void main(String[] var0) {
    createDemoFrame(new DukeAnim());
  }

  public void reset(int var1, int var2) {
    this.b = ((DemoPanel) this.getParent()).tools.startStopB;
  }

  public void step(int var1, int var2) {}

  public void render(int var1, int var2, Graphics2D var3) {
    if ((this.x -= 3) <= -cw) {
      this.x = var1;
    }

    var3.drawImage(clouds, this.x, 10, cw, var2 - 20, this);
    var3.drawImage(agif, var1 / 2 - aw, var2 / 2 - ah, this);
  }

  public boolean imageUpdate(Image var1, int var2, int var3, int var4, int var5, int var6) {
    if (this.b.isSelected() && (var2 & 32) != 0) {
      this.repaint();
    }

    if (this.b.isSelected() && (var2 & 16) != 0) {
      this.repaint();
    }

    return this.isShowing();
  }
}
