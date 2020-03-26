//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Composite;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class FadeAnim extends AnimatingControlsSurface {
  private static final TexturePaint texturePaint;
  private static BasicStroke bs;
  private static Font[] fonts;
  private static String[] strings;
  private static String[] imgs;
  private static Paint[] paints;

  static {
    byte var0 = 10;
    byte var1 = 10;
    BufferedImage var2 = new BufferedImage(var0, var1, 1);
    Graphics2D var3 = var2.createGraphics();
    Color var4 = Color.BLUE;
    Color var5 = Color.GREEN;
    var3.setPaint(
        new GradientPaint(0.0F, 0.0F, var4, (float) var0 * 0.35F, (float) var1 * 0.35F, var5));
    var3.fillRect(0, 0, var0 / 2, var1 / 2);
    var3.setPaint(
        new GradientPaint(var0, 0.0F, var4, (float) var0 * 0.65F, (float) var1 * 0.35F, var5));
    var3.fillRect(var0 / 2, 0, var0 / 2, var1 / 2);
    var3.setPaint(
        new GradientPaint(0.0F, var1, var4, (float) var0 * 0.35F, (float) var1 * 0.65F, var5));
    var3.fillRect(0, var1 / 2, var0 / 2, var1 / 2);
    var3.setPaint(
        new GradientPaint(var0, var1, var4, (float) var0 * 0.65F, (float) var1 * 0.65F, var5));
    var3.fillRect(var0 / 2, var1 / 2, var0 / 2, var1 / 2);
    texturePaint = new TexturePaint(var2, new Rectangle(0, 0, var0, var1));
    bs = new BasicStroke(6.0F);
    fonts =
        new Font[] {
          new Font("Times New Roman", 0, 64),
          new Font("serif", 3, 24),
          new Font("Courier", 1, 36),
          new Font("Arial", 3, 48),
          new Font("Helvetica", 0, 52)
        };
    strings =
        new String[] {
          "Alpha", "Composite", "Src", "SrcOver", "SrcIn", "SrcOut", "Clear", "DstOver", "DstIn"
        };
    imgs = new String[] {"jumptojavastrip.png", "duke.png", "star7.png"};
    paints =
        new Paint[] {
          Color.RED,
          Color.BLUE,
          Color.GREEN,
          Color.MAGENTA,
          Color.ORANGE,
          Color.PINK,
          Color.CYAN,
          texturePaint,
          Color.YELLOW,
          Color.LIGHT_GRAY,
          Color.WHITE
        };
  }

  private List<FadeAnim.ObjectData> objects = new ArrayList(20);
  private int numShapes;
  private int numStrings;
  private int numImages;

  public FadeAnim() {
    this.setBackground(Color.BLACK);
    this.setStrings(2);
    this.setImages(3);
    this.setShapes(8);
    this.setControls(new Component[] {new FadeAnim.DemoControls(this)});
    this.setConstraints(new String[] {"East"});
  }

  public static void main(String[] var0) {
    createDemoFrame(new FadeAnim());
  }

  public void setImages(int var1) {
    if (var1 < this.numImages) {
      ArrayList var2 = new ArrayList(this.objects.size());
      Iterator var3 = this.objects.iterator();

      while (var3.hasNext()) {
        FadeAnim.ObjectData var4 = (FadeAnim.ObjectData) var3.next();
        if (var4.object instanceof Image) {
          var2.add(var4);
        }
      }

      this.objects.removeAll(var2.subList(var1, var2.size()));
    } else {
      Dimension var8 = this.getSize();

      for (int var9 = this.numImages; var9 < var1; ++var9) {
        Object var10 = this.getImage(imgs[var9 % imgs.length]);
        if (imgs[var9 % imgs.length].equals("jumptojavastrip.png")) {
          int var5 = ((Image) var10).getWidth(null);
          int var6 = ((Image) var10).getHeight(null);
          BufferedImage var7 = new BufferedImage(var5, var6, 1);
          var7.createGraphics().drawImage((Image) var10, 0, 0, null);
          var10 = var7;
        }

        FadeAnim.ObjectData var11 = new FadeAnim.ObjectData(var10, Color.BLACK);
        var11.reset(var8.width, var8.height);
        this.objects.add(var11);
      }
    }

    this.numImages = var1;
  }

  public void setStrings(int var1) {
    if (var1 < this.numStrings) {
      ArrayList var2 = new ArrayList(this.objects.size());
      Iterator var3 = this.objects.iterator();

      while (var3.hasNext()) {
        FadeAnim.ObjectData var4 = (FadeAnim.ObjectData) var3.next();
        if (var4.object instanceof FadeAnim.TextData) {
          var2.add(var4);
        }
      }

      this.objects.removeAll(var2.subList(var1, var2.size()));
    } else {
      Dimension var8 = this.getSize();

      for (int var9 = this.numStrings; var9 < var1; ++var9) {
        int var10 = var9 % fonts.length;
        int var5 = var9 % strings.length;
        FadeAnim.TextData var6 = new FadeAnim.TextData(strings[var5], fonts[var10], this);
        FadeAnim.ObjectData var7 = new FadeAnim.ObjectData(var6, paints[var9 % paints.length]);
        var7.reset(var8.width, var8.height);
        this.objects.add(var7);
      }
    }

    this.numStrings = var1;
  }

  public void setShapes(int var1) {
    if (var1 < this.numShapes) {
      ArrayList var2 = new ArrayList(this.objects.size());
      Iterator var3 = this.objects.iterator();

      while (var3.hasNext()) {
        FadeAnim.ObjectData var4 = (FadeAnim.ObjectData) var3.next();
        if (var4.object instanceof Shape) {
          var2.add(var4);
        }
      }

      this.objects.removeAll(var2.subList(var1, var2.size()));
    } else {
      Dimension var6 = this.getSize();

      for (int var7 = this.numShapes; var7 < var1; ++var7) {
        Object var8 = null;
        switch (var7 % 7) {
          case 0:
            var8 = new GeneralPath();
            break;
          case 1:
            var8 = new Double();
            break;
          case 2:
            var8 = new java.awt.geom.Ellipse2D.Double();
            break;
          case 3:
            var8 = new java.awt.geom.Arc2D.Double();
            break;
          case 4:
            var8 = new java.awt.geom.RoundRectangle2D.Double();
            break;
          case 5:
            var8 = new java.awt.geom.CubicCurve2D.Double();
            break;
          case 6:
            var8 = new java.awt.geom.QuadCurve2D.Double();
        }

        FadeAnim.ObjectData var5 = new FadeAnim.ObjectData(var8, paints[var7 % paints.length]);
        var5.reset(var6.width, var6.height);
        this.objects.add(var5);
      }
    }

    this.numShapes = var1;
  }

  public void reset(int var1, int var2) {
    for (int var3 = 0; var3 < this.objects.size(); ++var3) {
      this.objects.get(var3).reset(var1, var2);
    }
  }

  public void step(int var1, int var2) {
    for (int var3 = 0; var3 < this.objects.size(); ++var3) {
      this.objects.get(var3).step(var1, var2);
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    for (int var4 = 0; var4 < this.objects.size(); ++var4) {
      FadeAnim.ObjectData var5 = this.objects.get(var4);
      AlphaComposite var6 = AlphaComposite.getInstance(3, var5.alpha);
      var3.setComposite(var6);
      var3.setPaint(var5.paint);
      var3.translate(var5.x, var5.y);
      if (var5.object instanceof Image) {
        var3.drawImage((Image) var5.object, 0, 0, this);
      } else if (var5.object instanceof FadeAnim.TextData) {
        var3.setFont(((FadeAnim.TextData) var5.object).font);
        var3.drawString(((FadeAnim.TextData) var5.object).string, 0, 0);
      } else if (!(var5.object instanceof QuadCurve2D) && !(var5.object instanceof CubicCurve2D)) {
        if (var5.object instanceof Shape) {
          var3.fill((Shape) var5.object);
        }
      } else {
        var3.setStroke(bs);
        var3.draw((Shape) var5.object);
      }

      var3.translate(-var5.x, -var5.y);
    }
  }

  static class DemoControls extends CustomControls implements ChangeListener {
    FadeAnim demo;
    JSlider shapeSlider;
    JSlider stringSlider;
    JSlider imageSlider;
    Font font = new Font("serif", 1, 10);

    public DemoControls(FadeAnim var1) {
      super(var1.name);
      this.demo = var1;
      this.setLayout(new BoxLayout(this, 1));
      this.add(Box.createVerticalStrut(5));
      JToolBar var2 = new JToolBar(1);
      var2.setFloatable(false);
      this.shapeSlider = new JSlider(0, 0, 20, var1.numShapes);
      this.shapeSlider.addChangeListener(this);
      TitledBorder var3 = new TitledBorder(new EtchedBorder());
      var3.setTitleFont(this.font);
      var3.setTitle(var1.numShapes + " Shapes");
      this.shapeSlider.setBorder(var3);
      this.shapeSlider.setPreferredSize(new Dimension(80, 45));
      this.shapeSlider.setOpaque(true);
      var2.addSeparator();
      var2.add(this.shapeSlider);
      var2.addSeparator();
      this.stringSlider = new JSlider(0, 0, 10, var1.numStrings);
      this.stringSlider.addChangeListener(this);
      var3 = new TitledBorder(new EtchedBorder());
      var3.setTitleFont(this.font);
      var3.setTitle(var1.numStrings + " Strings");
      this.stringSlider.setBorder(var3);
      this.stringSlider.setPreferredSize(new Dimension(80, 45));
      this.stringSlider.setOpaque(true);
      var2.add(this.stringSlider);
      var2.addSeparator();
      this.imageSlider = new JSlider(0, 0, 10, var1.numImages);
      this.imageSlider.addChangeListener(this);
      var3 = new TitledBorder(new EtchedBorder());
      var3.setTitleFont(this.font);
      var3.setTitle(var1.numImages + " Images");
      this.imageSlider.setBorder(var3);
      this.imageSlider.setPreferredSize(new Dimension(80, 45));
      this.imageSlider.setOpaque(true);
      var2.add(this.imageSlider);
      var2.addSeparator();
      this.add(var2);
    }

    public void stateChanged(ChangeEvent var1) {
      JSlider var2 = (JSlider) var1.getSource();
      int var3 = var2.getValue();
      TitledBorder var4 = (TitledBorder) var2.getBorder();
      if (var2.equals(this.shapeSlider)) {
        var4.setTitle(var3 + " Shapes");
        this.demo.setShapes(var3);
      } else if (var2.equals(this.stringSlider)) {
        var4.setTitle(var3 + " Strings");
        this.demo.setStrings(var3);
      } else if (var2.equals(this.imageSlider)) {
        var4.setTitle(var3 + " Images");
        this.demo.setImages(var3);
      }

      var2.repaint();
      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(80, 0);
    }

    public void run() {
      try {
        Thread.sleep(999L);
      } catch (InterruptedException var2) {
        return;
      }

      this.shapeSlider.setValue((int) (Math.random() * 5.0D));
      this.stringSlider.setValue(10);
      this.thread = null;
    }
  }

  static class ObjectData {
    final int UP = 0;
    final int DOWN = 1;
    Object object;
    BufferedImage bimg;
    Paint paint;
    double x;
    double y;
    float alpha;
    int alphaDirection;
    int imgX;

    public ObjectData(Object var1, Paint var2) {
      this.object = var1;
      this.paint = var2;
      if (var1 instanceof BufferedImage) {
        this.bimg = (BufferedImage) var1;
        this.object = this.bimg.getSubimage(0, 0, 80, 80);
      }

      this.getRandomXY(300, 250);
      this.alpha = (float) Math.random();
      this.alphaDirection = Math.random() > 0.5D ? 0 : 1;
    }

    private void getRandomXY(int var1, int var2) {
      if (this.object instanceof FadeAnim.TextData) {
        this.x = Math.random() * (double) (var1 - ((FadeAnim.TextData) this.object).width);
        this.y = Math.random() * (double) var2;
        this.y =
            this.y < (double) ((FadeAnim.TextData) this.object).height
                ? (double) ((FadeAnim.TextData) this.object).height
                : this.y;
      } else if (this.object instanceof Image) {
        this.x = Math.random() * (double) (var1 - ((Image) this.object).getWidth(null));
        this.y = Math.random() * (double) (var2 - ((Image) this.object).getHeight(null));
      } else if (this.object instanceof Shape) {
        Rectangle var3 = ((Shape) this.object).getBounds();
        this.x = Math.random() * (double) (var1 - var3.width);
        this.y = Math.random() * (double) (var2 - var3.height);
      }
    }

    public void reset(int var1, int var2) {
      this.getRandomXY(var1, var2);
      double var3 = 20.0D + Math.random() * (double) ((var1 == 0 ? 400 : var1) / 4);
      double var5 = 20.0D + Math.random() * (double) ((var2 == 0 ? 300 : var2) / 4);
      if (this.object instanceof Ellipse2D) {
        ((Ellipse2D) this.object).setFrame(0.0D, 0.0D, var3, var5);
      } else if (this.object instanceof Rectangle2D) {
        ((Rectangle2D) this.object).setRect(0.0D, 0.0D, var3, var3);
      } else if (this.object instanceof RoundRectangle2D) {
        ((RoundRectangle2D) this.object).setRoundRect(0.0D, 0.0D, var5, var5, 20.0D, 20.0D);
      } else if (this.object instanceof Arc2D) {
        ((Arc2D) this.object).setArc(0.0D, 0.0D, var5, var5, 45.0D, 270.0D, 2);
      } else if (this.object instanceof QuadCurve2D) {
        ((QuadCurve2D) this.object)
            .setCurve(
                0.0D, 0.0D, (double) var1 * 0.2D, (double) var2 * 0.4D, (double) var1 * 0.4D, 0.0D);
      } else if (this.object instanceof CubicCurve2D) {
        ((CubicCurve2D) this.object).setCurve(0.0D, 0.0D, 30.0D, -60.0D, 60.0D, 60.0D, 90.0D, 0.0D);
      } else if (this.object instanceof GeneralPath) {
        GeneralPath var7 = new GeneralPath();
        float var8 = (float) var3;
        var7.moveTo(-var8 / 2.0F, -var8 / 8.0F);
        var7.lineTo(var8 / 2.0F, -var8 / 8.0F);
        var7.lineTo(-var8 / 4.0F, var8 / 2.0F);
        var7.lineTo(0.0F, -var8 / 2.0F);
        var7.lineTo(var8 / 4.0F, var8 / 2.0F);
        var7.closePath();
        this.object = var7;
      }
    }

    public void step(int var1, int var2) {
      if (this.object instanceof BufferedImage) {
        if ((this.imgX += 80) == 800) {
          this.imgX = 0;
        }

        this.object = this.bimg.getSubimage(this.imgX, 0, 80, 80);
      }

      if (this.alphaDirection == 0) {
        if ((double) (this.alpha = (float) ((double) this.alpha + 0.05D)) > 0.99D) {
          this.alphaDirection = 1;
          this.alpha = 1.0F;
        }
      } else if (this.alphaDirection == 1
          && (double) (this.alpha = (float) ((double) this.alpha - 0.05D)) < 0.01D) {
        this.alphaDirection = 0;
        this.alpha = 0.0F;
        this.getRandomXY(var1, var2);
      }
    }
  }

  static class TextData {
    public String string;
    public Font font;
    public int width;
    public int height;

    public TextData(String var1, Font var2, Component var3) {
      this.string = var1;
      this.font = var2;
      FontMetrics var4 = var3.getFontMetrics(var2);
      this.width = var4.stringWidth(var1);
      this.height = var4.getHeight();
    }
  }
}
