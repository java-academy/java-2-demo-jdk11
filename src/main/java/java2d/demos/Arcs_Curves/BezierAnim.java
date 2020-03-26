//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Arcs_Curves;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BezierAnim extends AnimatingControlsSurface {
  private static final int NUMPTS = 6;
  protected BasicStroke solid = new BasicStroke(10.0F, 0, 1);
  protected BasicStroke dashed = new BasicStroke(10.0F, 0, 1, 10.0F, new float[] {5.0F}, 0.0F);
  protected Paint fillPaint;
  protected Paint drawPaint;
  protected boolean doFill = true;
  protected boolean doDraw = true;
  protected GradientPaint gradient;
  protected BasicStroke stroke;
  private float[] animpts = new float[12];
  private float[] deltas = new float[12];

  public BezierAnim() {
    this.setBackground(Color.WHITE);
    this.gradient = new GradientPaint(0.0F, 0.0F, Color.RED, 200.0F, 200.0F, Color.YELLOW);
    this.fillPaint = this.gradient;
    this.drawPaint = Color.BLUE;
    this.stroke = this.solid;
    this.setControls(new Component[] {new BezierAnim.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new BezierAnim());
  }

  public void animate(float[] var1, float[] var2, int var3, int var4) {
    float var5 = var1[var3] + var2[var3];
    if (var5 <= 0.0F) {
      var5 = -var5;
      var2[var3] = (float) (Math.random() * 4.0D + 2.0D);
    } else if (var5 >= (float) var4) {
      var5 = 2.0F * (float) var4 - var5;
      var2[var3] = -((float) (Math.random() * 4.0D + 2.0D));
    }

    var1[var3] = var5;
  }

  public void reset(int var1, int var2) {
    for (int var3 = 0; var3 < this.animpts.length; var3 += 2) {
      this.animpts[var3 + 0] = (float) (Math.random() * (double) var1);
      this.animpts[var3 + 1] = (float) (Math.random() * (double) var2);
      this.deltas[var3 + 0] = (float) (Math.random() * 6.0D + 4.0D);
      this.deltas[var3 + 1] = (float) (Math.random() * 6.0D + 4.0D);
      if (this.animpts[var3 + 0] > (float) var1 / 2.0F) {
        this.deltas[var3 + 0] = -this.deltas[var3 + 0];
      }

      if (this.animpts[var3 + 1] > (float) var2 / 2.0F) {
        this.deltas[var3 + 1] = -this.deltas[var3 + 1];
      }
    }

    this.gradient =
        new GradientPaint(
            0.0F, 0.0F, Color.RED, (float) var1 * 0.7F, (float) var2 * 0.7F, Color.YELLOW);
  }

  public void step(int var1, int var2) {
    for (int var3 = 0; var3 < this.animpts.length; var3 += 2) {
      this.animate(this.animpts, this.deltas, var3 + 0, var1);
      this.animate(this.animpts, this.deltas, var3 + 1, var2);
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    float[] var4 = this.animpts;
    int var5 = var4.length;
    float var6 = var4[var5 - 2];
    float var7 = var4[var5 - 1];
    float var8 = var4[0];
    float var9 = var4[1];
    float var10 = (var8 + var6) / 2.0F;
    float var11 = (var9 + var7) / 2.0F;
    GeneralPath var12 = new GeneralPath(1);
    var12.moveTo(var10, var11);

    for (int var13 = 2; var13 <= var4.length; var13 += 2) {
      float var14 = (var10 + var8) / 2.0F;
      float var15 = (var11 + var9) / 2.0F;
      var6 = var8;
      var7 = var9;
      if (var13 < var4.length) {
        var8 = var4[var13 + 0];
        var9 = var4[var13 + 1];
      } else {
        var8 = var4[0];
        var9 = var4[1];
      }

      var10 = (var8 + var6) / 2.0F;
      var11 = (var9 + var7) / 2.0F;
      float var16 = (var6 + var10) / 2.0F;
      float var17 = (var7 + var11) / 2.0F;
      var12.curveTo(var14, var15, var16, var17, var10, var11);
    }

    var12.closePath();
    if (this.doDraw) {
      var3.setPaint(this.drawPaint);
      var3.setStroke(this.stroke);
      var3.draw(var12);
    }

    if (this.doFill) {
      if (this.fillPaint instanceof GradientPaint) {
        this.fillPaint = this.gradient;
      }

      var3.setPaint(this.fillPaint);
      var3.fill(var12);
    }
  }

  static class DemoControls extends CustomControls implements ActionListener {
    static final TexturePaint tp1;
    static final TexturePaint tp2;
    static Paint[] drawPaints;
    static String[] drawName;
    static Paint[] fillPaints;

    static {
      BufferedImage var0 = new BufferedImage(2, 1, 1);
      var0.setRGB(0, 0, -16711936);
      var0.setRGB(1, 0, -65536);
      tp1 = new TexturePaint(var0, new Rectangle(0, 0, 2, 1));
      var0 = new BufferedImage(2, 1, 1);
      var0.setRGB(0, 0, -16776961);
      var0.setRGB(1, 0, -65536);
      tp2 = new TexturePaint(var0, new Rectangle(0, 0, 2, 1));
      drawPaints =
          new Paint[] {
            new Color(0, 0, 0, 0), Color.BLUE, new Color(0, 0, 255, 126), Color.BLUE, tp2
          };
      drawName = new String[] {"No Draw", "Blue", "Blue w/ Alpha", "Blue Dash", "Texture"};
      fillPaints =
          new Paint[] {
            new Color(0, 0, 0, 0),
            Color.GREEN,
            new Color(0, 255, 0, 126),
            tp1,
            new GradientPaint(0.0F, 0.0F, Color.RED, 30.0F, 30.0F, Color.YELLOW)
          };
    }

    BezierAnim demo;
    String[] fillName = new String[] {"No Fill", "Green", "Green w/ Alpha", "Texture", "Gradient"};
    JMenu fillMenu;
    JMenu drawMenu;
    JMenuItem[] fillMI;
    JMenuItem[] drawMI;
    BezierAnim.DemoControls.PaintedIcon[] fillIcons;
    BezierAnim.DemoControls.PaintedIcon[] drawIcons;
    Font font;

    public DemoControls(BezierAnim var1) {
      super(var1.name);
      this.fillMI = new JMenuItem[fillPaints.length];
      this.drawMI = new JMenuItem[drawPaints.length];
      this.fillIcons = new BezierAnim.DemoControls.PaintedIcon[fillPaints.length];
      this.drawIcons = new BezierAnim.DemoControls.PaintedIcon[drawPaints.length];
      this.font = new Font("serif", 0, 10);
      this.demo = var1;
      JMenuBar var2 = new JMenuBar();
      this.add(var2);
      JMenuBar var3 = new JMenuBar();
      this.add(var3);
      this.drawMenu = var2.add(new JMenu("Draw Choice"));
      this.drawMenu.setFont(this.font);

      int var4;
      for (var4 = 0; var4 < drawPaints.length; ++var4) {
        this.drawIcons[var4] = new BezierAnim.DemoControls.PaintedIcon(drawPaints[var4]);
        this.drawMI[var4] = this.drawMenu.add(new JMenuItem(drawName[var4]));
        this.drawMI[var4].setFont(this.font);
        this.drawMI[var4].setIcon(this.drawIcons[var4]);
        this.drawMI[var4].addActionListener(this);
      }

      this.drawMenu.setIcon(this.drawIcons[1]);
      this.fillMenu = var3.add(new JMenu("Fill Choice"));
      this.fillMenu.setFont(this.font);

      for (var4 = 0; var4 < fillPaints.length; ++var4) {
        this.fillIcons[var4] = new BezierAnim.DemoControls.PaintedIcon(fillPaints[var4]);
        this.fillMI[var4] = this.fillMenu.add(new JMenuItem(this.fillName[var4]));
        this.fillMI[var4].setFont(this.font);
        this.fillMI[var4].setIcon(this.fillIcons[var4]);
        this.fillMI[var4].addActionListener(this);
      }

      this.fillMenu.setIcon(this.fillIcons[fillPaints.length - 1]);
    }

    public void actionPerformed(ActionEvent var1) {
      Object var2 = var1.getSource();

      int var3;
      for (var3 = 0; var3 < fillPaints.length; ++var3) {
        if (var2.equals(this.fillMI[var3])) {
          this.demo.doFill = true;
          this.demo.fillPaint = fillPaints[var3];
          this.fillMenu.setIcon(this.fillIcons[var3]);
          break;
        }
      }

      for (var3 = 0; var3 < drawPaints.length; ++var3) {
        if (var2.equals(this.drawMI[var3])) {
          this.demo.doDraw = true;
          this.demo.drawPaint = drawPaints[var3];
          if (((JMenuItem) var2).getText().endsWith("Dash")) {
            this.demo.stroke = this.demo.dashed;
          } else {
            this.demo.stroke = this.demo.solid;
          }

          this.drawMenu.setIcon(this.drawIcons[var3]);
          break;
        }
      }

      if (var2.equals(this.fillMI[0])) {
        this.demo.doFill = false;
      } else if (var2.equals(this.drawMI[0])) {
        this.demo.doDraw = false;
      }

      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 36);
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        JMenuItem[] var2 = this.drawMI;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
          JMenuItem var5 = var2[var4];
          var5.doClick();
          JMenuItem[] var6 = this.fillMI;
          int var7 = var6.length;

          for (int var8 = 0; var8 < var7; ++var8) {
            JMenuItem var9 = var6[var8];
            var9.doClick();

            try {
              Thread.sleep(3000L + (long) (Math.random() * 3000.0D));
            } catch (InterruptedException var11) {
              break;
            }
          }
        }
      }

      this.thread = null;
    }

    static class PaintedIcon implements Icon {
      Paint paint;

      public PaintedIcon(Paint var1) {
        this.paint = var1;
      }

      public void paintIcon(Component var1, Graphics var2, int var3, int var4) {
        Graphics2D var5 = (Graphics2D) var2;
        var5.setPaint(this.paint);
        var5.fillRect(var3, var4, this.getIconWidth(), this.getIconHeight());
        var5.setColor(Color.GRAY);
        var5.draw3DRect(var3, var4, this.getIconWidth() - 1, this.getIconHeight() - 1, true);
      }

      public int getIconWidth() {
        return 12;
      }

      public int getIconHeight() {
        return 12;
      }
    }
  }
}
