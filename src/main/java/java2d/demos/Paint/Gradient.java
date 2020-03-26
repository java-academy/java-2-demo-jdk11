//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextLayout;
import java2d.ControlsSurface;
import java2d.CustomControls;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Gradient extends ControlsSurface {
  protected Color innerC;
  protected Color outerC;

  public Gradient() {
    this.setBackground(Color.white);
    this.innerC = Color.green;
    this.outerC = Color.blue;
    this.setControls(new Component[] {new Gradient.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Gradient());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    int var4 = var1 / 2;
    int var5 = var2 / 2;
    var3.setPaint(
        new GradientPaint(
            0.0F, 0.0F, this.outerC, (float) var1 * 0.35F, (float) var2 * 0.35F, this.innerC));
    var3.fillRect(0, 0, var4, var5);
    var3.setPaint(
        new GradientPaint(
            (float) var1,
            0.0F,
            this.outerC,
            (float) var1 * 0.65F,
            (float) var2 * 0.35F,
            this.innerC));
    var3.fillRect(var4, 0, var4, var5);
    var3.setPaint(
        new GradientPaint(
            0.0F,
            (float) var2,
            this.outerC,
            (float) var1 * 0.35F,
            (float) var2 * 0.65F,
            this.innerC));
    var3.fillRect(0, var5, var4, var5);
    var3.setPaint(
        new GradientPaint(
            (float) var1,
            (float) var2,
            this.outerC,
            (float) var1 * 0.65F,
            (float) var2 * 0.65F,
            this.innerC));
    var3.fillRect(var4, var5, var4, var5);
    var3.setColor(Color.black);
    TextLayout var6 = new TextLayout("GradientPaint", var3.getFont(), var3.getFontRenderContext());
    var6.draw(
        var3,
        (float) ((int) ((double) (var1 / 2) - var6.getBounds().getWidth() / 2.0D)),
        (float) ((int) ((double) (var2 / 2) + var6.getBounds().getHeight() / 2.0D)));
  }

  static class DemoControls extends CustomControls implements ActionListener {
    Gradient demo;
    Color[] colors;
    String[] colorName;
    JMenuItem[] innerMI;
    JMenuItem[] outerMI;
    Gradient.DemoControls.ColoredSquare[] squares;
    JMenu imenu;
    JMenu omenu;

    public DemoControls(Gradient var1) {
      super(var1.name);
      this.colors =
          new Color[] {
            Color.red,
            Color.orange,
            Color.yellow,
            Color.green,
            Color.blue,
            Color.lightGray,
            Color.cyan,
            Color.magenta
          };
      this.colorName =
          new String[] {"Red", "Orange", "Yellow", "Green", "Blue", "lightGray", "Cyan", "Magenta"};
      this.innerMI = new JMenuItem[this.colors.length];
      this.outerMI = new JMenuItem[this.colors.length];
      this.squares = new Gradient.DemoControls.ColoredSquare[this.colors.length];
      this.demo = var1;
      JMenuBar var2 = new JMenuBar();
      this.add(var2);
      JMenuBar var3 = new JMenuBar();
      this.add(var3);
      Font var4 = new Font("serif", 0, 10);
      this.imenu = var2.add(new JMenu("Inner Color"));
      this.imenu.setFont(var4);
      this.imenu.setIcon(new Gradient.DemoControls.ColoredSquare(var1.innerC));
      this.omenu = var3.add(new JMenu("Outer Color"));
      this.omenu.setFont(var4);
      this.omenu.setIcon(new Gradient.DemoControls.ColoredSquare(var1.outerC));

      for (int var5 = 0; var5 < this.colors.length; ++var5) {
        this.squares[var5] = new Gradient.DemoControls.ColoredSquare(this.colors[var5]);
        this.innerMI[var5] = this.imenu.add(new JMenuItem(this.colorName[var5]));
        this.innerMI[var5].setFont(var4);
        this.innerMI[var5].setIcon(this.squares[var5]);
        this.innerMI[var5].addActionListener(this);
        this.outerMI[var5] = this.omenu.add(new JMenuItem(this.colorName[var5]));
        this.outerMI[var5].setFont(var4);
        this.outerMI[var5].setIcon(this.squares[var5]);
        this.outerMI[var5].addActionListener(this);
      }
    }

    public void actionPerformed(ActionEvent var1) {
      for (int var2 = 0; var2 < this.colors.length; ++var2) {
        if (var1.getSource().equals(this.innerMI[var2])) {
          this.demo.innerC = this.colors[var2];
          this.imenu.setIcon(this.squares[var2]);
          break;
        }

        if (var1.getSource().equals(this.outerMI[var2])) {
          this.demo.outerC = this.colors[var2];
          this.omenu.setIcon(this.squares[var2]);
          break;
        }
      }

      this.demo.repaint();
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 37);
    }

    public void run() {
      if (this.demo.getImageType() <= 1) {
        this.demo.setImageType(2);
      }

      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 0; var2 < this.innerMI.length; ++var2) {
          if (var2 != 4) {
            try {
              Thread.sleep(4444L);
            } catch (InterruptedException var4) {
              return;
            }

            this.innerMI[var2].doClick();
          }
        }
      }

      this.thread = null;
    }

    class ColoredSquare implements Icon {
      Color color;

      public ColoredSquare(Color var2) {
        this.color = var2;
      }

      public void paintIcon(Component var1, Graphics var2, int var3, int var4) {
        Color var5 = var2.getColor();
        var2.setColor(this.color);
        var2.fill3DRect(var3, var4, this.getIconWidth(), this.getIconHeight(), true);
        var2.setColor(var5);
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
