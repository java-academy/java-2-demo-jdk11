//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Clipping;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D.Float;
import java.awt.image.BufferedImage;
import java2d.ControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class Text extends ControlsSurface {
  static Image img;
  static TexturePaint texturePaint;

  static {
    BufferedImage var0 = new BufferedImage(5, 5, 1);
    Graphics2D var1 = var0.createGraphics();
    var1.setBackground(Color.YELLOW);
    var1.clearRect(0, 0, 5, 5);
    var1.setColor(Color.RED);
    var1.fillRect(0, 0, 3, 3);
    texturePaint = new TexturePaint(var0, new Rectangle(0, 0, 5, 5));
  }

  protected boolean doClip = true;
  private String clipType = "Lines";

  public Text() {
    this.setBackground(Color.WHITE);
    img = this.getImage("clouds.jpg");
    this.setControls(new Component[] {new Text.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Text());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    FontRenderContext var4 = var3.getFontRenderContext();
    Font var5 = new Font("sansserif", 1, 32);
    String var6 = "JAVA";
    TextLayout var7 = new TextLayout(var6, var5, var4);
    double var8 = var7.getBounds().getWidth();
    double var10 = var7.getBounds().getHeight();
    double var12 = (double) (var1 - 40) / var8;
    double var14 = (double) (var2 - 40) / var10;
    AffineTransform var16 = AffineTransform.getScaleInstance(var12, var14);
    Shape var17 = var7.getOutline(var16);
    var8 = var17.getBounds().getWidth();
    var10 = var17.getBounds().getHeight();
    var16 =
        AffineTransform.getTranslateInstance(
            (double) (var1 / 2) - var8 / 2.0D, (double) (var2 / 2) + var10 / 2.0D);
    var17 = var16.createTransformedShape(var17);
    Rectangle var18 = var17.getBounds();
    if (this.doClip) {
      var3.clip(var17);
    }

    int var19;
    if (this.clipType.equals("Lines")) {
      var3.setColor(Color.BLACK);
      var3.fill(var18);
      var3.setColor(Color.YELLOW);
      var3.setStroke(new BasicStroke(1.5F));

      for (var19 = var18.y; var19 < var18.y + var18.height; var19 += 3) {
        Float var20 =
            new Float(
                (float) var18.x, (float) var19, (float) (var18.x + var18.width), (float) var19);
        var3.draw(var20);
      }
    } else if (this.clipType.equals("Image")) {
      var3.drawImage(img, var18.x, var18.y, var18.width, var18.height, null);
    } else if (this.clipType.equals("TP")) {
      var3.setPaint(texturePaint);
      var3.fill(var18);
    } else if (this.clipType.equals("GP")) {
      var3.setPaint(
          new GradientPaint(0.0F, 0.0F, Color.BLUE, (float) var1, (float) var2, Color.YELLOW));
      var3.fill(var18);
    } else if (this.clipType.equals("Text")) {
      var3.setColor(Color.BLACK);
      var3.fill(var17.getBounds());
      var3.setColor(Color.CYAN);
      var5 = new Font("serif", 1, 10);
      var7 = new TextLayout("java", var5, var4);
      var8 = var7.getBounds().getWidth();
      var19 = var18.x;
      int var21 = (int) ((float) var18.y + var7.getAscent());
      var10 = var18.y + var18.height;

      while ((double) var21 < var10) {
        var7.draw(var3, (float) var19, (float) var21);
        if ((var19 += (int) var8) > var18.x + var18.width) {
          var19 = var18.x;
          var21 += (int) var7.getAscent();
        }
      }
    }

    var3.setClip(new Rectangle(0, 0, var1, var2));
    var3.setColor(Color.GRAY);
    var3.draw(var17);
  }

  static final class DemoControls extends CustomControls implements ActionListener {
    Text demo;
    JToolBar toolbar;

    public DemoControls(Text var1) {
      super(var1.name);
      this.demo = var1;
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("Clip", true);
      this.addTool("Lines", true);
      this.addTool("Image", false);
      this.addTool("TP", false);
      this.addTool("GP", false);
      this.addTool("Text", false);
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
      JToggleButton var2;
      if (var1.getSource().equals(this.toolbar.getComponentAtIndex(0))) {
        var2 = (JToggleButton) var1.getSource();
        this.demo.doClip = var2.isSelected();
      } else {
        Component[] var6 = this.toolbar.getComponents();
        int var3 = var6.length;

        for (int var4 = 0; var4 < var3; ++var4) {
          Component var5 = var6[var4];
          ((JToggleButton) var5).setSelected(false);
        }

        var2 = (JToggleButton) var1.getSource();
        var2.setSelected(true);
        this.demo.clipType = var2.getText();
      }

      this.demo.repaint();
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 40);
    }

    public void run() {
      try {
        Thread.sleep(1111L);
      } catch (Exception var5) {
        return;
      }

      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 1; var2 < this.toolbar.getComponentCount() - 1; ++var2) {
          ((AbstractButton) this.toolbar.getComponentAtIndex(var2)).doClick();

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
}
