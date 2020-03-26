//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D.Float;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public final class TextureChooser extends JPanel {
  public static Object texture = getGeomTexture();
  public int num;

  public TextureChooser(int var1) {
    this.num = var1;
    this.setLayout(new GridLayout(0, 2, 5, 5));
    this.setBorder(new TitledBorder(new EtchedBorder(), "Texture Chooser"));
    this.add(new TextureChooser.Surface(getGeomTexture(), this, 0));
    this.add(new TextureChooser.Surface(this.getImageTexture(), this, 1));
    this.add(new TextureChooser.Surface(this.getTextTexture(), this, 2));
    this.add(new TextureChooser.Surface(this.getGradientPaint(), this, 3));
  }

  public static TexturePaint getGeomTexture() {
    BufferedImage var0 = new BufferedImage(5, 5, 1);
    Graphics2D var1 = var0.createGraphics();
    var1.setBackground(Color.WHITE);
    var1.clearRect(0, 0, 5, 5);
    var1.setColor(new Color(211, 211, 211, 200));
    var1.fill(new Float(0.0F, 0.0F, 5.0F, 5.0F));
    Rectangle var2 = new Rectangle(0, 0, 5, 5);
    return new TexturePaint(var0, var2);
  }

  public static void main(String[] var0) {
    Frame var1 = new Frame("Java2D Demo - TextureChooser");
    var1.addWindowListener(
        new WindowAdapter() {
          public void windowClosing(WindowEvent var1) {
            System.exit(0);
          }
        });
    var1.add("Center", new TextureChooser(0));
    var1.pack();
    var1.setSize(new Dimension(400, 400));
    var1.setVisible(true);
  }

  public TexturePaint getImageTexture() {
    Image var1 = DemoImages.getImage("java-logo.gif", this);
    int var2 = var1.getWidth(this);
    int var3 = var1.getHeight(this);
    BufferedImage var4 = new BufferedImage(var2, var3, 1);
    Graphics2D var5 = var4.createGraphics();
    var5.drawImage(var1, 0, 0, this);
    Rectangle var6 = new Rectangle(0, 0, var2, var3);
    return new TexturePaint(var4, var6);
  }

  public TexturePaint getTextTexture() {
    Font var1 = new Font("Times New Roman", 1, 10);
    TextLayout var2 = new TextLayout("Java2D", var1, new FontRenderContext(null, false, false));
    int var3 = (int) var2.getBounds().getWidth();
    int var4 = (int) (var2.getAscent() + var2.getDescent());
    BufferedImage var5 = new BufferedImage(var3, var4, 1);
    Graphics2D var6 = var5.createGraphics();
    var6.setBackground(Color.WHITE);
    var6.clearRect(0, 0, var3, var4);
    var6.setColor(Color.LIGHT_GRAY);
    var2.draw(var6, 0.0F, var2.getAscent());
    Rectangle var7 = new Rectangle(0, 0, var3, var4);
    return new TexturePaint(var5, var7);
  }

  public GradientPaint getGradientPaint() {
    return new GradientPaint(0.0F, 0.0F, Color.WHITE, 80.0F, 0.0F, Color.GREEN);
  }

  public class Surface extends JPanel {
    public boolean clickedFrame;
    private int num;
    private TextureChooser tc;
    private boolean enterExitFrame = false;
    private Object t;

    public Surface(final Object var2, final TextureChooser var3, int var4) {
      this.setBackground(Color.WHITE);
      this.t = var2;
      this.tc = var3;
      this.clickedFrame = var4 == var3.num;
      this.num = var4;
      if (var4 == var3.num) {
        TextureChooser.texture = var2;
      }

      this.addMouseListener(
          new MouseAdapter() {
            public void mouseClicked(MouseEvent var1) {
              TextureChooser.texture = var2;
              Surface.this.clickedFrame = true;
              Component[] var2x = var3.getComponents();
              int var3x = var2x.length;

              for (int var4 = 0; var4 < var3x; ++var4) {
                Component var5 = var2x[var4];
                if (var5 instanceof TextureChooser.Surface) {
                  TextureChooser.Surface var6 = (TextureChooser.Surface) var5;
                  if (!var6.equals(Surface.this) && var6.clickedFrame) {
                    var6.clickedFrame = false;
                    var6.repaint();
                  }
                }
              }

              if (Java2Demo.controls.textureCB.isSelected()) {
                Java2Demo.controls.textureCB.doClick();
                Java2Demo.controls.textureCB.doClick();
              }
            }

            public void mouseEntered(MouseEvent var1) {
              Surface.this.enterExitFrame = true;
              Surface.this.repaint();
            }

            public void mouseExited(MouseEvent var1) {
              Surface.this.enterExitFrame = false;
              Surface.this.repaint();
            }
          });
    }

    public void paintComponent(Graphics var1) {
      super.paintComponent(var1);
      Graphics2D var2 = (Graphics2D) var1;
      int var3 = this.getSize().width;
      int var4 = this.getSize().height;
      if (this.t instanceof TexturePaint) {
        var2.setPaint((TexturePaint) this.t);
      } else {
        var2.setPaint((GradientPaint) this.t);
      }

      var2.fill(new Rectangle(0, 0, var3, var4));
      if (this.clickedFrame || this.enterExitFrame) {
        var2.setColor(Color.GRAY);
        BasicStroke var5 = new BasicStroke(3.0F, 0, 0);
        var2.setStroke(var5);
        var2.drawRect(0, 0, var3 - 1, var4 - 1);
        this.tc.num = this.num;
      }
    }

    public Dimension getMinimumSize() {
      return this.getPreferredSize();
    }

    public Dimension getMaximumSize() {
      return this.getPreferredSize();
    }

    public Dimension getPreferredSize() {
      return new Dimension(30, 30);
    }
  }
}
