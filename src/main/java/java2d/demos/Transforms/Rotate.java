//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Transforms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D.Float;
import java2d.ControlsSurface;
import java2d.CustomControls;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Rotate extends ControlsSurface {
  protected double increment = 5.0D;
  protected int emphasis = 9;

  public Rotate() {
    this.setBackground(Color.WHITE);
    this.setControls(new Component[] {new Rotate.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Rotate());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    int var4 = Math.min(var1, var2);
    float var5 = (float) (var4 / 4);
    float var6 = (float) (var4 - 20);
    Float var7 = new Float(-var5 / 2.0F, -var6 / 2.0F, var5, var6);

    for (double var8 = 0.0D; var8 < 360.0D; var8 += this.increment) {
      if (var8 % (double) this.emphasis == 0.0D) {
        var3.setColor(Color.GRAY);
        var3.setStroke(new BasicStroke(2.0F));
      } else {
        var3.setColor(Color.LIGHT_GRAY);
        var3.setStroke(new BasicStroke(0.5F));
      }

      AffineTransform var10 = AffineTransform.getTranslateInstance(var1 / 2, var2 / 2);
      var10.rotate(Math.toRadians(var8));
      var3.draw(var10.createTransformedShape(var7));
    }

    var3.setColor(Color.BLUE);
    var7.setFrame(var1 / 2 - 10, var2 / 2 - 10, 20.0D, 20.0D);
    var3.fill(var7);
    var3.setColor(Color.GRAY);
    var3.setStroke(new BasicStroke(6.0F));
    var3.draw(var7);
    var3.setColor(Color.YELLOW);
    var3.setStroke(new BasicStroke(4.0F));
    var3.draw(var7);
    var3.setColor(Color.BLACK);
    var3.drawString("Rotate", 5, 15);
  }

  static class DemoControls extends CustomControls implements ActionListener {
    Rotate demo;
    JTextField tf1;
    JTextField tf2;

    public DemoControls(Rotate var1) {
      super(var1.name);
      this.demo = var1;
      JLabel var2 = new JLabel("Increment:");
      var2.setForeground(Color.BLACK);
      this.add(var2);
      this.add(this.tf1 = new JTextField("5.0"));
      this.tf1.setPreferredSize(new Dimension(30, 24));
      this.tf1.addActionListener(this);
      this.add(var2 = new JLabel("  Emphasis:"));
      var2.setForeground(Color.BLACK);
      this.add(this.tf2 = new JTextField("9"));
      this.tf2.setPreferredSize(new Dimension(30, 24));
      this.tf2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent var1) {
      try {
        if (var1.getSource().equals(this.tf1)) {
          this.demo.increment = Double.parseDouble(this.tf1.getText().trim());
          if (this.demo.increment < 1.0D) {
            this.demo.increment = 1.0D;
          }
        } else {
          this.demo.emphasis = Integer.parseInt(this.tf2.getText().trim());
        }

        this.demo.repaint();
      } catch (Exception var3) {
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 39);
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 3; var2 < 13; var2 += 3) {
          try {
            Thread.sleep(4444L);
          } catch (InterruptedException var4) {
            return;
          }

          this.tf1.setText(String.valueOf(var2));
          this.demo.increment = var2;
          this.demo.repaint();
        }
      }

      this.thread = null;
    }
  }
}
