//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Clipping;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class ClipAnim extends AnimatingControlsSurface {
  static TexturePaint texturePaint;
  private static Image dimg;
  private static Image cimg;

  static {
    BufferedImage var0 = new BufferedImage(5, 5, 1);
    Graphics2D var1 = var0.createGraphics();
    var1.setBackground(Color.YELLOW);
    var1.clearRect(0, 0, 5, 5);
    var1.setColor(Color.RED);
    var1.fillRect(0, 0, 3, 3);
    texturePaint = new TexturePaint(var0, new Rectangle(0, 0, 5, 5));
  }

  protected boolean doObjects = true;
  private ClipAnim.AnimVal[] animval = new ClipAnim.AnimVal[3];
  private Font originalFont = new Font("serif", 0, 12);
  private Font font;
  private GradientPaint gradient;
  private int strX;
  private int strY;
  private int dukeX;
  private int dukeY;
  private int dukeWidth;
  private int dukeHeight;

  public ClipAnim() {
    cimg = this.getImage("clouds.jpg");
    dimg = this.getImage("duke.png");
    this.setBackground(Color.WHITE);
    this.animval[0] = new ClipAnim.AnimVal(true);
    this.animval[1] = new ClipAnim.AnimVal(false);
    this.animval[2] = new ClipAnim.AnimVal(false);
    this.setControls(new Component[] {new ClipAnim.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new ClipAnim());
  }

  public void reset(int var1, int var2) {
    ClipAnim.AnimVal[] var3 = this.animval;
    int var4 = var3.length;

    for (int var5 = 0; var5 < var4; ++var5) {
      ClipAnim.AnimVal var6 = var3[var5];
      var6.reset(var1, var2);
    }

    this.gradient =
        new GradientPaint(
            0.0F,
            (float) (var2 / 2),
            Color.RED,
            (float) var1 * 0.4F,
            (float) var2 * 0.9F,
            Color.YELLOW);
    double var15 = 0.4D;
    this.dukeHeight = (int) (var15 * (double) var2);
    this.dukeWidth =
        (int)
            ((double) dimg.getWidth(this) * var15 * (double) var2 / (double) dimg.getHeight(this));
    this.dukeX = (int) ((double) var1 * 0.25D - (double) (this.dukeWidth / 2));
    this.dukeY = (int) ((double) var2 * 0.25D - (double) (this.dukeHeight / 2));
    FontMetrics var16 = this.getFontMetrics(this.originalFont);
    double var17 = var16.stringWidth("CLIPPING");
    double var8 = var16.getAscent() + var16.getDescent();
    double var10 = (double) (var1 / 2 - 30) / var17;
    double var12 = (double) (var2 / 2 - 30) / var8;
    AffineTransform var14 = AffineTransform.getScaleInstance(var10, var12);
    this.font = this.originalFont.deriveFont(var14);
    var16 = this.getFontMetrics(this.font);
    this.strX = (int) ((double) var1 * 0.75D - (double) (var16.stringWidth("CLIPPING") / 2));
    this.strY = (int) ((double) var2 * 0.72D + (double) (var16.getAscent() / 2));
  }

  public void step(int var1, int var2) {
    ClipAnim.AnimVal[] var3 = this.animval;
    int var4 = var3.length;

    for (int var5 = 0; var5 < var4; ++var5) {
      ClipAnim.AnimVal var6 = var3[var5];
      if (var6.isSelected) {
        var6.step(var1, var2);
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    GeneralPath var4 = new GeneralPath();
    GeneralPath var5 = new GeneralPath();
    ClipAnim.AnimVal[] var6 = this.animval;
    int var7 = var6.length;

    for (int var8 = 0; var8 < var7; ++var8) {
      ClipAnim.AnimVal var9 = var6[var8];
      if (var9.isSelected) {
        double var10 = var9.x;
        double var12 = var9.y;
        double var14 = var9.ew;
        double var16 = var9.eh;
        var4.append(new Double(var10, var12, var14, var16), false);
        var5.append(
            new java.awt.geom.Rectangle2D.Double(
                var10 + 5.0D, var12 + 5.0D, var14 - 10.0D, var16 - 10.0D),
            false);
      }
    }

    if (this.animval[0].isSelected || this.animval[1].isSelected || this.animval[2].isSelected) {
      var3.setClip(var4);
      var3.clip(var5);
    }

    if (this.doObjects) {
      int var18 = var1 / 2;
      var7 = var2 / 2;
      var3.drawImage(cimg, 0, 0, var18, var7, null);
      var3.drawImage(dimg, this.dukeX, this.dukeY, this.dukeWidth, this.dukeHeight, null);
      var3.setPaint(texturePaint);
      var3.fillRect(var18, 0, var18, var7);
      var3.setPaint(this.gradient);
      var3.fillRect(0, var7, var18, var7);
      var3.setColor(Color.LIGHT_GRAY);
      var3.fillRect(var18, var7, var18, var7);
      var3.setColor(Color.RED);
      var3.drawOval(var18, var7, var18 - 1, var7 - 1);
      var3.setFont(this.font);
      var3.drawString("CLIPPING", this.strX, this.strY);
    } else {
      var3.setColor(Color.LIGHT_GRAY);
      var3.fillRect(0, 0, var1, var2);
    }
  }

  static final class DemoControls extends CustomControls implements ActionListener {
    ClipAnim demo;
    JToolBar toolbar;

    public DemoControls(ClipAnim var1) {
      super(var1.name);
      this.demo = var1;
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("Objects", true);
      this.addTool("Clip1", true);
      this.addTool("Clip2", false);
      this.addTool("Clip3", false);
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
      if (var2.getText().equals("Objects")) {
        this.demo.doObjects = var2.isSelected();
      } else if (var2.getText().equals("Clip1")) {
        this.demo.animval[0].isSelected = var2.isSelected();
      } else if (var2.getText().equals("Clip2")) {
        this.demo.animval[1].isSelected = var2.isSelected();
      } else if (var2.getText().equals("Clip3")) {
        this.demo.animval[2].isSelected = var2.isSelected();
      }

      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 40);
    }

    public void run() {
      try {
        Thread.sleep(5000L);
      } catch (InterruptedException var3) {
        return;
      }

      ((AbstractButton) this.toolbar.getComponentAtIndex(2)).doClick();

      try {
        Thread.sleep(5000L);
      } catch (InterruptedException var2) {
        return;
      }

      if (this.getSize().width > 400) {
        ((AbstractButton) this.toolbar.getComponentAtIndex(3)).doClick();
      }

      this.thread = null;
    }
  }

  public class AnimVal {
    double ix = 5.0D;
    double iy = 3.0D;
    double iw = 5.0D;
    double ih = 3.0D;
    double x;
    double y;
    double ew;
    double eh;
    boolean isSelected;

    public AnimVal(boolean var2) {
      this.isSelected = var2;
    }

    public void step(int var1, int var2) {
      this.x += this.ix;
      this.y += this.iy;
      this.ew += this.iw;
      this.eh += this.ih;
      if (this.ew > (double) (var1 / 2)) {
        this.ew = var1 / 2;
        this.iw = Math.random() * (double) (-var1) / 16.0D - 1.0D;
      }

      if (this.ew < (double) (var1 / 8)) {
        this.ew = var1 / 8;
        this.iw = Math.random() * (double) var1 / 16.0D + 1.0D;
      }

      if (this.eh > (double) (var2 / 2)) {
        this.eh = var2 / 2;
        this.ih = Math.random() * (double) (-var2) / 16.0D - 1.0D;
      }

      if (this.eh < (double) (var2 / 8)) {
        this.eh = var2 / 8;
        this.ih = Math.random() * (double) var2 / 16.0D + 1.0D;
      }

      if (this.x + this.ew > (double) var1) {
        this.x = (double) var1 - this.ew - 1.0D;
        this.ix = Math.random() * (double) (-var1) / 32.0D - 1.0D;
      }

      if (this.y + this.eh > (double) var2) {
        this.y = (double) var2 - this.eh - 2.0D;
        this.iy = Math.random() * (double) (-var2) / 32.0D - 1.0D;
      }

      if (this.x < 0.0D) {
        this.x = 2.0D;
        this.ix = Math.random() * (double) var1 / 32.0D + 1.0D;
      }

      if (this.y < 0.0D) {
        this.y = 2.0D;
        this.iy = Math.random() * (double) var2 / 32.0D + 1.0D;
      }
    }

    public void reset(int var1, int var2) {
      this.x = Math.random() * (double) var1;
      this.y = Math.random() * (double) var2;
      this.ew = Math.random() * (double) var1 / 2.0D;
      this.eh = Math.random() * (double) var2 / 2.0D;
    }
  }
}
