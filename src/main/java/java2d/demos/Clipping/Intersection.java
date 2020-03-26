//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Clipping;

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
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class Intersection extends AnimatingControlsSurface {
  private static final int HEIGHT_DECREASE = 0;
  private static final int HEIGHT_INCREASE = 1;
  private static final int WIDTH_DECREASE = 2;
  private static final int WIDTH_INCREASE = 3;
  protected boolean doIntersection = true;
  protected boolean doOvals = true;
  protected boolean doText;
  protected boolean threeSixty;
  private int xx;
  private int yy;
  private int ww;
  private int hh;
  private int direction = 0;
  private int angdeg;
  private Shape textshape;
  private double sw;
  private double sh;
  private GeneralPath ovals;
  private Rectangle2D rectshape;

  public Intersection() {
    this.setBackground(Color.WHITE);
    this.setControls(new Component[] {new Intersection.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Intersection());
  }

  public void reset(int var1, int var2) {
    this.xx = this.yy = 0;
    this.ww = var1 - 1;
    this.hh = var2;
    this.direction = 0;
    this.angdeg = 0;
    FontRenderContext var3 = new FontRenderContext(null, true, false);
    Font var4 = new Font("serif", 1, 32);
    TextLayout var5 = new TextLayout("J2D", var4, var3);
    this.sw = var5.getBounds().getWidth();
    this.sh = var5.getBounds().getHeight();
    int var6 = Math.min(var1, var2);
    double var7 = (double) (var6 - 40) / this.sw;
    double var9 = (double) (var6 - 100) / this.sh;
    AffineTransform var11 = AffineTransform.getScaleInstance(var7, var9);
    this.textshape = var5.getOutline(var11);
    this.rectshape = this.textshape.getBounds();
    this.sw = this.rectshape.getWidth();
    this.sh = this.rectshape.getHeight();
    this.ovals = new GeneralPath();
    this.ovals.append(new Double(10.0D, 10.0D, 20.0D, 20.0D), false);
    this.ovals.append(new Double(var1 - 30, 10.0D, 20.0D, 20.0D), false);
    this.ovals.append(new Double(10.0D, var2 - 30, 20.0D, 20.0D), false);
    this.ovals.append(new Double(var1 - 30, var2 - 30, 20.0D, 20.0D), false);
  }

  public void step(int var1, int var2) {
    if (this.direction == 0) {
      this.yy += 2;
      this.hh -= 4;
      if (this.yy >= var2 / 2) {
        this.direction = 1;
      }
    } else if (this.direction == 1) {
      this.yy -= 2;
      this.hh += 4;
      if (this.yy <= 0) {
        this.direction = 2;
        this.hh = var2 - 1;
        this.yy = 0;
      }
    }

    if (this.direction == 2) {
      this.xx += 2;
      this.ww -= 4;
      if (this.xx >= var1 / 2) {
        this.direction = 3;
      }
    } else if (this.direction == 3) {
      this.xx -= 2;
      this.ww += 4;
      if (this.xx <= 0) {
        this.direction = 0;
        this.ww = var1 - 1;
        this.xx = 0;
      }
    }

    if ((this.angdeg += 5) == 360) {
      this.angdeg = 0;
      this.threeSixty = true;
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Rectangle var4 = new Rectangle(this.xx, this.yy, this.ww, this.hh);
    AffineTransform var5 = new AffineTransform();
    var5.rotate(Math.toRadians(this.angdeg), var1 / 2, var2 / 2);
    var5.translate(
        (double) (var1 / 2) - this.sw / 2.0D, this.sh + ((double) var2 - this.sh) / 2.0D);
    GeneralPath var6 = new GeneralPath();
    if (this.doOvals) {
      var6.append(this.ovals, false);
    }

    if (this.doText) {
      var6.append(var5.createTransformedShape(this.textshape), false);
    } else {
      var6.append(var5.createTransformedShape(this.rectshape), false);
    }

    if (this.doIntersection) {
      var3.clip(var4);
      var3.clip(var6);
    }

    var3.setColor(Color.GREEN);
    var3.fill(var4);
    var3.setClip(new Rectangle(0, 0, var1, var2));
    var3.setColor(Color.LIGHT_GRAY);
    var3.draw(var4);
    var3.setColor(Color.BLACK);
    var3.draw(var6);
  }

  static final class DemoControls extends CustomControls implements ActionListener {
    Intersection demo;
    JToolBar toolbar;

    public DemoControls(Intersection var1) {
      super(var1.name);
      this.demo = var1;
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("Intersect", true);
      this.addTool("Text", false);
      this.addTool("Ovals", true);
    }

    public void addTool(String var1, boolean var2) {
      JToggleButton var3 = (JToggleButton) this.toolbar.add(new JToggleButton(var1));
      var3.setFocusPainted(false);
      var3.setSelected(var2);
      var3.addActionListener(this);
      int var4 = var3.getPreferredSize().width;
      Dimension var5 = new Dimension(var4, 21);
      var3.setPreferredSize(var5);
      var3.setMaximumSize(var5);
      var3.setMinimumSize(var5);
    }

    public void actionPerformed(ActionEvent var1) {
      JToggleButton var2 = (JToggleButton) var1.getSource();
      if (var2.getText().equals("Intersect")) {
        this.demo.doIntersection = var2.isSelected();
      } else if (var2.getText().equals("Ovals")) {
        this.demo.doOvals = var2.isSelected();
      } else if (var2.getText().equals("Text")) {
        this.demo.doText = var2.isSelected();
      }

      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 40);
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        if (this.demo.threeSixty) {
          ((AbstractButton) this.toolbar.getComponentAtIndex(1)).doClick();
          this.demo.threeSixty = false;
        }

        try {
          Thread.sleep(500L);
        } catch (InterruptedException var3) {
          return;
        }
      }

      this.thread = null;
    }
  }
}
