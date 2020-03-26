//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Mix;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D.Double;
import java2d.ControlsSurface;
import java2d.CustomControls;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Stars3D extends ControlsSurface {
  private static Color[] colors;
  private static AffineTransform at;

  static {
    colors = new Color[] {Color.RED, Color.GREEN, Color.WHITE};
    at = AffineTransform.getTranslateInstance(-5.0D, -5.0D);
  }

  protected int fontSize = 72;
  protected String text = "Java2D";
  protected int numStars = 300;
  private Shape shape;
  private Shape tshape;
  private Shape ribbon;

  public Stars3D() {
    this.setBackground(Color.BLACK);
    this.setControls(new Component[] {new Stars3D.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Stars3D());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Double var4 = new Double();

    for (int var5 = 0; var5 < this.numStars; ++var5) {
      var3.setColor(colors[var5 % 3]);
      var3.setComposite(AlphaComposite.getInstance(3, (float) Math.random()));
      var4.setRect((double) var1 * Math.random(), (double) var2 * Math.random(), 2.0D, 2.0D);
      var3.fill(var4);
    }

    FontRenderContext var20 = var3.getFontRenderContext();
    Font var6 = new Font("serif.bolditalic", 0, this.fontSize);
    this.shape = var6.createGlyphVector(var20, this.text).getOutline();
    this.tshape = at.createTransformedShape(this.shape);
    PathIterator var7 = this.shape.getPathIterator(null);
    float[] var8 = new float[6];
    float[] var9 = new float[6];
    GeneralPath var10 = new GeneralPath(1);
    float var11 = 0.0F;
    float var12 = 0.0F;
    float var13 = 0.0F;
    float var14 = 0.0F;
    float var15 = 0.0F;
    float var16 = 0.0F;
    float var17 = 0.0F;

    for (float var18 = 0.0F; !var7.isDone(); var7.next()) {
      int var19 = var7.currentSegment(var8);
      switch (var19) {
        case 0:
          at.transform(var8, 0, var9, 0, 1);
          var11 = var8[0];
          var12 = var8[1];
          var13 = var9[0];
          var14 = var9[1];
          var15 = var11;
          var16 = var12;
          var17 = var13;
          var18 = var14;
          break;
        case 1:
          at.transform(var8, 0, var9, 0, 1);
          if (Line2D.relativeCCW(var11, var12, var13, var14, var8[0], var8[1]) < 0) {
            var10.moveTo(var11, var12);
            var10.lineTo(var8[0], var8[1]);
            var10.lineTo(var9[0], var9[1]);
            var10.lineTo(var13, var14);
            var10.lineTo(var11, var12);
          } else {
            var10.moveTo(var11, var12);
            var10.lineTo(var13, var14);
            var10.lineTo(var9[0], var9[1]);
            var10.lineTo(var8[0], var8[1]);
            var10.lineTo(var11, var12);
          }

          var11 = var8[0];
          var12 = var8[1];
          var13 = var9[0];
          var14 = var9[1];
          break;
        case 2:
          at.transform(var8, 0, var9, 0, 2);
          if (Line2D.relativeCCW(var11, var12, var13, var14, var8[2], var8[3]) < 0) {
            var10.moveTo(var11, var12);
            var10.quadTo(var8[0], var8[1], var8[2], var8[3]);
            var10.lineTo(var9[2], var9[3]);
            var10.quadTo(var9[0], var9[1], var13, var14);
            var10.lineTo(var11, var12);
          } else {
            var10.moveTo(var11, var12);
            var10.lineTo(var13, var14);
            var10.quadTo(var9[0], var9[1], var9[2], var9[3]);
            var10.lineTo(var8[2], var8[3]);
            var10.quadTo(var8[0], var8[1], var11, var12);
          }

          var11 = var8[2];
          var12 = var8[3];
          var13 = var9[2];
          var14 = var9[3];
          break;
        case 3:
          at.transform(var8, 0, var9, 0, 3);
          if (Line2D.relativeCCW(var11, var12, var13, var14, var8[4], var8[5]) < 0) {
            var10.moveTo(var11, var12);
            var10.curveTo(var8[0], var8[1], var8[2], var8[3], var8[4], var8[5]);
            var10.lineTo(var9[4], var9[5]);
            var10.curveTo(var9[2], var9[3], var9[0], var9[1], var13, var14);
            var10.lineTo(var11, var12);
          } else {
            var10.moveTo(var11, var12);
            var10.lineTo(var13, var14);
            var10.curveTo(var9[0], var9[1], var9[2], var9[3], var9[4], var9[5]);
            var10.lineTo(var8[4], var8[5]);
            var10.curveTo(var8[2], var8[3], var8[0], var8[1], var11, var12);
          }

          var11 = var8[4];
          var12 = var8[5];
          var13 = var9[4];
          var14 = var9[5];
          break;
        case 4:
          if (Line2D.relativeCCW(var11, var12, var13, var14, var15, var16) < 0) {
            var10.moveTo(var11, var12);
            var10.lineTo(var15, var16);
            var10.lineTo(var17, var18);
            var10.lineTo(var13, var14);
            var10.lineTo(var11, var12);
          } else {
            var10.moveTo(var11, var12);
            var10.lineTo(var13, var14);
            var10.lineTo(var17, var18);
            var10.lineTo(var15, var16);
            var10.lineTo(var11, var12);
          }

          var11 = var15;
          var12 = var16;
          var13 = var17;
          var14 = var18;
      }
    }

    this.ribbon = var10;
    if (this.composite != null) {
      var3.setComposite(this.composite);
    } else {
      var3.setComposite(AlphaComposite.SrcOver);
    }

    Rectangle var21 = this.shape.getBounds();
    var3.translate(
        (double) var1 * 0.5D - (double) var21.width * 0.5D,
        (double) var2 * 0.5D + (double) var21.height * 0.5D);
    var3.setColor(Color.BLUE);
    var3.fill(this.tshape);
    var3.setColor(new Color(255, 255, 255, 200));
    var3.fill(this.ribbon);
    var3.setColor(Color.WHITE);
    var3.fill(this.shape);
    var3.setColor(Color.BLUE);
    var3.draw(this.shape);
  }

  static class DemoControls extends CustomControls implements ActionListener {
    Stars3D demo;
    JTextField tf1;
    JTextField tf2;

    public DemoControls(Stars3D var1) {
      super(var1.name);
      this.demo = var1;
      JLabel var2 = new JLabel("  Text:");
      var2.setForeground(Color.BLACK);
      this.add(var2);
      this.add(this.tf1 = new JTextField(var1.text));
      this.tf1.setPreferredSize(new Dimension(60, 20));
      this.tf1.addActionListener(this);
      var2 = new JLabel("  Size:");
      var2.setForeground(Color.BLACK);
      this.add(var2);
      this.add(this.tf2 = new JTextField(String.valueOf(var1.fontSize)));
      this.tf2.setPreferredSize(new Dimension(30, 20));
      this.tf2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent var1) {
      try {
        if (var1.getSource().equals(this.tf1)) {
          this.demo.text = this.tf1.getText().trim();
        } else if (var1.getSource().equals(this.tf2)) {
          this.demo.fontSize = Integer.parseInt(this.tf2.getText().trim());
          if (this.demo.fontSize < 10) {
            this.demo.fontSize = 10;
          }
        }

        this.demo.repaint();
      } catch (Exception var3) {
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 37);
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      try {
        Thread.sleep(999L);
      } catch (Exception var8) {
        return;
      }

      int var2 = this.getSize().width / 4;
      int[] var3 = new int[] {var2, var2};
      String[] var4 = new String[] {"JAVA", "J2D"};

      while (this.thread == var1) {
        for (int var5 = 0; var5 < var4.length; ++var5) {
          this.demo.fontSize = var3[var5];
          this.tf2.setText(String.valueOf(this.demo.fontSize));
          this.tf1.setText(this.demo.text = var4[var5]);
          this.demo.repaint();

          try {
            Thread.sleep(5555L);
          } catch (InterruptedException var7) {
            return;
          }
        }
      }

      this.thread = null;
    }
  }
}
