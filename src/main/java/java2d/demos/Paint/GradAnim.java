//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.JComboBox;

public class GradAnim extends AnimatingControlsSurface {
  private static final int BASIC_GRADIENT = 0;
  private static final int LINEAR_GRADIENT = 1;
  private static final int RADIAL_GRADIENT = 2;
  private static final int FOCUS_GRADIENT = 3;
  private static final int MAX_HUE = 1536;
  private GradAnim.animval x1;
  private GradAnim.animval y1;
  private GradAnim.animval x2;
  private GradAnim.animval y2;
  private int hue = (int) (Math.random() * 1536.0D);
  private int gradientType;

  public GradAnim() {
    this.setBackground(Color.white);
    this.setControls(new Component[] {new GradAnim.DemoControls(this)});
    this.x1 = new GradAnim.animval(0, 300, 2, 10);
    this.y1 = new GradAnim.animval(0, 300, 2, 10);
    this.x2 = new GradAnim.animval(0, 300, 2, 10);
    this.y2 = new GradAnim.animval(0, 300, 2, 10);
    this.gradientType = 0;
  }

  public static Color getColor(int var0) {
    int var1 = var0 / 256 % 6;
    int var2 = var0 % 256 * 2;
    int var3 = var2 < 256 ? 255 : 511 - var2;
    int var4 = var2 < 256 ? var2 : 255;
    int var7 = 0;
    int var6 = 0;
    int var5 = 0;
    switch (var1) {
      case 0:
        var5 = 255;
        break;
      case 1:
        var5 = var3;
        var6 = var4;
        break;
      case 2:
        var6 = 255;
        break;
      case 3:
        var6 = var3;
        var7 = var4;
        break;
      case 4:
        var7 = 255;
        break;
      case 5:
        var7 = var3;
        var5 = var4;
    }

    return new Color(var5, var6, var7);
  }

  public static void main(String[] var0) {
    createDemoFrame(new GradAnim());
  }

  public void reset(int var1, int var2) {
    this.x1.newlimits(0, var1);
    this.y1.newlimits(0, var2);
    this.x2.newlimits(0, var1);
    this.y2.newlimits(0, var2);
  }

  public void step(int var1, int var2) {
    this.x1.anim();
    this.y1.anim();
    this.x2.anim();
    this.y2.anim();
    this.hue = (this.hue + (int) (Math.random() * 10.0D)) % 1536;
  }

  public void render(int var1, int var2, Graphics2D var3) {
    float var4 = this.x1.getFlt();
    float var5 = this.y1.getFlt();
    float var6 = this.x2.getFlt();
    float var7 = this.y2.getFlt();
    if (var4 == var6 && var5 == var7) {
      ++var6;
      ++var7;
    }

    Color var8 = getColor(this.hue);
    Color var9 = getColor(this.hue + 768);
    Object var10;
    float[] var11;
    Color var12;
    Color var13;
    Color[] var14;
    float var15;
    switch (this.gradientType) {
      case 0:
      default:
        var10 = new GradientPaint(var4, var5, var8, var6, var7, var9, true);
        break;
      case 1:
        var11 = new float[] {0.0F, 0.2F, 1.0F};
        var12 = getColor(this.hue + 512);
        Color[] var17 = new Color[] {var8, var9, var12};
        var10 = new LinearGradientPaint(var4, var5, var6, var7, var11, var17, CycleMethod.REFLECT);
        break;
      case 2:
        var11 = new float[] {0.0F, 0.2F, 0.8F, 1.0F};
        var12 = getColor(this.hue + 512);
        var13 = getColor(this.hue + 1024);
        var14 = new Color[] {var8, var9, var12, var13};
        var15 = (float) Point2D.distance(var4, var5, var6, var7);
        var10 = new RadialGradientPaint(var4, var5, var15, var11, var14, CycleMethod.REFLECT);
        break;
      case 3:
        var11 = new float[] {0.0F, 0.2F, 0.8F, 1.0F};
        var12 = getColor(this.hue + 1024);
        var13 = getColor(this.hue + 512);
        var14 = new Color[] {var8, var9, var12, var13};
        var15 = (float) Point2D.distance(var4, var5, var6, var7);
        float var16 = (float) Math.max(var1, var2);
        var15 = var16 * (var15 / var16 * 0.9F + 0.1F);
        var10 =
            new RadialGradientPaint(
                var6, var7, var15, var4, var5, var11, var14, CycleMethod.REPEAT);
    }

    var3.setPaint((Paint) var10);
    var3.fillRect(0, 0, var1, var2);
    var3.setColor(Color.yellow);
    var3.drawLine(this.x1.getInt(), this.y1.getInt(), this.x2.getInt(), this.y2.getInt());
  }

  class DemoControls extends CustomControls implements ActionListener {
    GradAnim demo;
    JComboBox combo;

    public DemoControls(GradAnim var2) {
      super(var2.name);
      this.demo = var2;
      this.combo = new JComboBox();
      this.combo.addActionListener(this);
      this.combo.addItem("2-color GradientPaint");
      this.combo.addItem("3-color LinearGradientPaint");
      this.combo.addItem("4-color RadialGradientPaint");
      this.combo.addItem("4-color RadialGradientPaint with focus");
      this.combo.setSelectedIndex(0);
      this.add(this.combo);
    }

    public void actionPerformed(ActionEvent var1) {
      int var2 = this.combo.getSelectedIndex();
      if (var2 >= 0) {
        this.demo.gradientType = var2;
      }

      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 41);
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 0; var2 < this.combo.getItemCount(); ++var2) {
          this.combo.setSelectedIndex(var2);

          try {
            Thread.sleep(4444L);
          } catch (InterruptedException var4) {
            return;
          }
        }
      }

      this.thread = null;
    }
  }

  public final class animval {
    float curval;
    float lowval;
    float highval;
    float currate;
    float lowrate;
    float highrate;

    public animval(int var2, int var3, int var4, int var5) {
      this.lowval = (float) var2;
      this.highval = (float) var3;
      this.lowrate = (float) var4;
      this.highrate = (float) var5;
      this.curval = this.randval((float) var2, (float) var3);
      this.currate = this.randval((float) var4, (float) var5);
    }

    public float randval(float var1, float var2) {
      return (float) ((double) var1 + Math.random() * (double) (var2 - var1));
    }

    public float getFlt() {
      return this.curval;
    }

    public int getInt() {
      return (int) this.curval;
    }

    public void anim() {
      this.curval += this.currate;
      this.clip();
    }

    public void clip() {
      if (this.curval > this.highval) {
        this.curval = this.highval - (this.curval - this.highval);
        if (this.curval < this.lowval) {
          this.curval = this.highval;
        }

        this.currate = -this.randval(this.lowrate, this.highrate);
      } else if (this.curval < this.lowval) {
        this.curval = this.lowval + (this.lowval - this.curval);
        if (this.curval > this.highval) {
          this.curval = this.lowval;
        }

        this.currate = this.randval(this.lowrate, this.highrate);
      }
    }

    public void newlimits(int var1, int var2) {
      this.lowval = (float) var1;
      this.highval = (float) var2;
      this.clip();
    }
  }
}
