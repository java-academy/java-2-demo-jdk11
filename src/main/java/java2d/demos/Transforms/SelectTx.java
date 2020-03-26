//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Transforms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class SelectTx extends AnimatingControlsSurface {
  protected static final int RIGHT = 0;
  protected static final int SCALE = 0;
  protected static final int SHEAR = 1;
  protected static final int ROTATE = 2;
  private static final int LEFT = 1;
  private static final int XMIDDLE = 2;
  private static final int DOWN = 3;
  private static final int UP = 4;
  private static final int YMIDDLE = 5;
  private static final int XupYup = 6;
  private static final int XdownYdown = 7;
  private static final String[] title = new String[] {"Scale", "Shear", "Rotate"};
  protected int transformType = 1;
  protected double sx;
  protected double sy;
  protected double angdeg;
  protected int direction = 0;
  protected int transformToggle;
  private Image original;
  private int iw;
  private int ih;

  public SelectTx() {
    this.setBackground(Color.WHITE);
    this.original = this.getImage("painting.png");
    this.iw = this.original.getWidth(this);
    this.ih = this.original.getHeight(this);
    this.setControls(new Component[] {new SelectTx.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new SelectTx());
  }

  public void reset(int var1, int var2) {
    this.iw = var1 > 3 ? var1 / 3 : 1;
    this.ih = var2 > 3 ? var2 / 3 : 1;
    if (this.transformType == 0) {
      this.direction = 0;
      this.sx = this.sy = 1.0D;
    } else if (this.transformType == 1) {
      this.direction = 0;
      this.sx = this.sy = 0.0D;
    } else {
      this.angdeg = 0.0D;
    }
  }

  public void step(int var1, int var2) {
    int var3 = this.iw + 10;
    int var4 = this.ih + 10;
    if (this.transformType == 0 && this.direction == 0) {
      this.sx += 0.05D;
      if ((double) var1 * 0.5D - (double) this.iw * 0.5D + (double) var3 * this.sx + 10.0D
          > (double) var1) {
        this.direction = 3;
      }
    } else if (this.transformType == 0 && this.direction == 3) {
      this.sy += 0.05D;
      if ((double) var2 * 0.5D - (double) this.ih * 0.5D + (double) var4 * this.sy + 20.0D
          > (double) var2) {
        this.direction = 1;
      }
    } else if (this.transformType == 0 && this.direction == 1) {
      this.sx -= 0.05D;
      if ((double) var3 * this.sx - 10.0D <= -((double) var1 * 0.5D - (double) this.iw * 0.5D)) {
        this.direction = 4;
      }
    } else if (this.transformType == 0 && this.direction == 4) {
      this.sy -= 0.05D;
      if ((double) var4 * this.sy - 20.0D <= -((double) var2 * 0.5D - (double) this.ih * 0.5D)) {
        this.direction = 0;
        this.transformToggle = 1;
      }
    }

    if (this.transformType == 1 && this.direction == 0) {
      this.sx += 0.05D;
      if ((double) var3 + (double) (2 * var4) * this.sx + 20.0D > (double) var1) {
        this.direction = 1;
        this.sx -= 0.1D;
      }
    } else if (this.transformType == 1 && this.direction == 1) {
      this.sx -= 0.05D;
      if ((double) var3 - (double) (2 * var4) * this.sx + 20.0D > (double) var1) {
        this.direction = 2;
      }
    } else if (this.transformType == 1 && this.direction == 2) {
      this.sx += 0.05D;
      if (this.sx > 0.0D) {
        this.direction = 3;
        this.sx = 0.0D;
      }
    } else if (this.transformType == 1 && this.direction == 3) {
      this.sy -= 0.05D;
      if ((double) var4 - (double) (2 * var3) * this.sy + 20.0D > (double) var2) {
        this.direction = 4;
        this.sy += 0.1D;
      }
    } else if (this.transformType == 1 && this.direction == 4) {
      this.sy += 0.05D;
      if ((double) var4 + (double) (2 * var3) * this.sy + 20.0D > (double) var2) {
        this.direction = 5;
      }
    } else if (this.transformType == 1 && this.direction == 5) {
      this.sy -= 0.05D;
      if (this.sy < 0.0D) {
        this.direction = 6;
        this.sy = 0.0D;
      }
    } else if (this.transformType == 1 && this.direction == 6) {
      this.sx += 0.05D;
      this.sy += 0.05D;
      if ((double) var3 + (double) (2 * var4) * this.sx + 30.0D > (double) var1
          || (double) var4 + (double) (2 * var3) * this.sy + 30.0D > (double) var2) {
        this.direction = 7;
      }
    } else if (this.transformType == 1 && this.direction == 7) {
      this.sy -= 0.05D;
      this.sx -= 0.05D;
      if (this.sy < 0.0D) {
        this.direction = 0;
        this.sx = this.sy = 0.0D;
        this.transformToggle = 2;
      }
    }

    if (this.transformType == 2) {
      this.angdeg += 5.0D;
      if (this.angdeg == 360.0D) {
        this.angdeg = 0.0D;
        this.transformToggle = 0;
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Font var4 = var3.getFont();
    FontRenderContext var5 = var3.getFontRenderContext();
    TextLayout var6 = new TextLayout(title[this.transformType], var4, var5);
    var3.setColor(Color.BLACK);
    var6.draw(
        var3,
        (float) ((double) (var1 / 2) - var6.getBounds().getWidth() / 2.0D),
        var6.getAscent() + var6.getDescent());
    String var7;
    if (this.transformType == 2) {
      var7 = Double.toString(this.angdeg);
      var3.drawString("angdeg=" + var7, 2, var2 - 4);
    } else {
      var7 = Double.toString(this.sx);
      var7 = var7.length() < 5 ? var7 : var7.substring(0, 5);
      TextLayout var8 = new TextLayout("sx=" + var7, var4, var5);
      var8.draw(var3, 2.0F, (float) (var2 - 4));
      var7 = Double.toString(this.sy);
      var7 = var7.length() < 5 ? var7 : var7.substring(0, 5);
      var3.drawString("sy=" + var7, (int) (var8.getBounds().getWidth() + 4.0D), var2 - 4);
    }

    if (this.transformType == 0) {
      var3.translate(var1 / 2 - this.iw / 2, var2 / 2 - this.ih / 2);
      var3.scale(this.sx, this.sy);
    } else if (this.transformType == 1) {
      var3.translate(var1 / 2 - this.iw / 2, var2 / 2 - this.ih / 2);
      var3.shear(this.sx, this.sy);
    } else {
      var3.rotate(Math.toRadians(this.angdeg), var1 / 2, var2 / 2);
      var3.translate(var1 / 2 - this.iw / 2, var2 / 2 - this.ih / 2);
    }

    var3.setColor(Color.ORANGE);
    var3.fillRect(0, 0, this.iw + 10, this.ih + 10);
    var3.drawImage(this.original, 5, 5, this.iw, this.ih, Color.ORANGE, this);
  }

  static final class DemoControls extends CustomControls implements ActionListener {
    SelectTx demo;
    JToolBar toolbar;

    public DemoControls(SelectTx var1) {
      super(var1.name);
      this.demo = var1;
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("Scale", false);
      this.addTool("Shear", true);
      this.addTool("Rotate", false);
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
      for (int var2 = 0; var2 < this.toolbar.getComponentCount(); ++var2) {
        JToggleButton var3 = (JToggleButton) this.toolbar.getComponentAtIndex(var2);
        var3.setSelected(false);
      }

      JToggleButton var4 = (JToggleButton) var1.getSource();
      var4.setSelected(true);
      if (var4.getText().equals("Scale")) {
        this.demo.transformType = 0;
        this.demo.direction = 0;
        this.demo.sx = this.demo.sy = 1.0D;
      } else if (var4.getText().equals("Shear")) {
        this.demo.transformType = 1;
        this.demo.direction = 0;
        this.demo.sx = this.demo.sy = 0.0D;
      } else if (var4.getText().equals("Rotate")) {
        this.demo.transformType = 2;
        this.demo.angdeg = 0.0D;
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 39);
    }

    public void run() {
      Thread var1 = Thread.currentThread();
      this.demo.transformToggle = this.demo.transformType;

      while (this.thread == var1) {
        try {
          Thread.sleep(222L);
        } catch (InterruptedException var3) {
          return;
        }

        if (this.demo.transformToggle != this.demo.transformType) {
          ((AbstractButton) this.toolbar.getComponentAtIndex(this.demo.transformToggle)).doClick();
        }
      }

      this.thread = null;
    }
  }
}
