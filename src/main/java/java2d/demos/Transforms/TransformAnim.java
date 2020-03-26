//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Transforms;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
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
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalBorders.ButtonBorder;

public final class TransformAnim extends AnimatingControlsSurface {
  private static final TexturePaint texturePaint;
  private static BasicStroke bs;
  private static Font[] fonts;
  private static String[] strings;
  private static String[] imgs;
  private static Paint[] paints;

  static {
    BufferedImage var0 = new BufferedImage(10, 10, 1);
    Graphics2D var1 = var0.createGraphics();
    var1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    var1.setColor(Color.RED);
    var1.fillOval(0, 0, 9, 9);
    texturePaint = new TexturePaint(var0, new Rectangle(0, 0, 10, 10));
    bs = new BasicStroke(6.0F);
    fonts =
        new Font[] {
          new Font("Times New Roman", 0, 48),
          new Font("serif", 3, 24),
          new Font("Courier", 1, 36),
          new Font("Arial", 3, 64),
          new Font("Helvetica", 0, 52)
        };
    strings = new String[] {"Transformation", "Rotate", "Translate", "Shear", "Scale"};
    imgs = new String[] {"duke.png"};
    paints =
        new Paint[] {
          Color.RED,
          Color.BLUE,
          texturePaint,
          Color.GREEN,
          Color.MAGENTA,
          Color.ORANGE,
          Color.PINK,
          Color.CYAN,
          new Color(0, 255, 0, 128),
          new Color(0, 0, 255, 128),
          Color.YELLOW,
          Color.LIGHT_GRAY,
          Color.WHITE
        };
  }

  protected boolean doRotate = true;
  protected boolean doTranslate = true;
  protected boolean doScale = true;
  protected boolean doShear;
  private List<TransformAnim.ObjData> objDatas = new ArrayList(13);
  private int numShapes;
  private int numStrings;
  private int numImages;

  public TransformAnim() {
    this.setBackground(Color.BLACK);
    this.setStrings(1);
    this.setImages(2);
    this.setShapes(10);
    this.setControls(new Component[] {new TransformAnim.DemoControls(this)});
    this.setConstraints(new String[] {"East"});
  }

  public static void main(String[] var0) {
    createDemoFrame(new TransformAnim());
  }

  public void setImages(int var1) {
    if (var1 < this.numImages) {
      ArrayList var2 = new ArrayList(this.objDatas.size());
      Iterator var3 = this.objDatas.iterator();

      while (var3.hasNext()) {
        TransformAnim.ObjData var4 = (TransformAnim.ObjData) var3.next();
        if (var4.object instanceof Image) {
          var2.add(var4);
        }
      }

      this.objDatas.removeAll(var2.subList(var1, var2.size()));
    } else {
      Dimension var6 = this.getSize();

      for (int var7 = this.numImages; var7 < var1; ++var7) {
        Image var8 = this.getImage(imgs[var7 % imgs.length]);
        TransformAnim.ObjData var5 = new TransformAnim.ObjData(var8, Color.BLACK);
        var5.reset(var6.width, var6.height);
        this.objDatas.add(var5);
      }
    }

    this.numImages = var1;
  }

  public void setStrings(int var1) {
    if (var1 < this.numStrings) {
      ArrayList var2 = new ArrayList(this.objDatas.size());
      Iterator var3 = this.objDatas.iterator();

      while (var3.hasNext()) {
        TransformAnim.ObjData var4 = (TransformAnim.ObjData) var3.next();
        if (var4.object instanceof TransformAnim.TextData) {
          var2.add(var4);
        }
      }

      this.objDatas.removeAll(var2.subList(var1, var2.size()));
    } else {
      Dimension var8 = this.getSize();

      for (int var9 = this.numStrings; var9 < var1; ++var9) {
        int var10 = var9 % fonts.length;
        int var5 = var9 % strings.length;
        TransformAnim.TextData var6 = new TransformAnim.TextData(strings[var5], fonts[var10]);
        TransformAnim.ObjData var7 = new TransformAnim.ObjData(var6, paints[var9 % paints.length]);
        var7.reset(var8.width, var8.height);
        this.objDatas.add(var7);
      }
    }

    this.numStrings = var1;
  }

  public void setShapes(int var1) {
    if (var1 < this.numShapes) {
      ArrayList var2 = new ArrayList(this.objDatas.size());
      Iterator var3 = this.objDatas.iterator();

      while (var3.hasNext()) {
        TransformAnim.ObjData var4 = (TransformAnim.ObjData) var3.next();
        if (var4.object instanceof Shape) {
          var2.add(var4);
        }
      }

      this.objDatas.removeAll(var2.subList(var1, var2.size()));
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

        TransformAnim.ObjData var5 = new TransformAnim.ObjData(var8, paints[var7 % paints.length]);
        var5.reset(var6.width, var6.height);
        this.objDatas.add(var5);
      }
    }

    this.numShapes = var1;
  }

  public void reset(int var1, int var2) {
    Iterator var3 = this.objDatas.iterator();

    while (var3.hasNext()) {
      TransformAnim.ObjData var4 = (TransformAnim.ObjData) var3.next();
      var4.reset(var1, var2);
    }
  }

  public void step(int var1, int var2) {
    Iterator var3 = this.objDatas.iterator();

    while (var3.hasNext()) {
      TransformAnim.ObjData var4 = (TransformAnim.ObjData) var3.next();
      var4.step(var1, var2, this);
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Iterator var4 = this.objDatas.iterator();

    while (true) {
      while (var4.hasNext()) {
        TransformAnim.ObjData var5 = (TransformAnim.ObjData) var4.next();
        var3.setTransform(var5.at);
        var3.setPaint(var5.paint);
        if (var5.object instanceof Image) {
          var3.drawImage((Image) var5.object, 0, 0, this);
        } else if (var5.object instanceof TransformAnim.TextData) {
          var3.setFont(((TransformAnim.TextData) var5.object).font);
          var3.drawString(((TransformAnim.TextData) var5.object).string, 0, 0);
        } else if (!(var5.object instanceof QuadCurve2D)
            && !(var5.object instanceof CubicCurve2D)) {
          if (var5.object instanceof Shape) {
            var3.fill((Shape) var5.object);
          }
        } else {
          var3.setStroke(bs);
          var3.draw((Shape) var5.object);
        }
      }

      return;
    }
  }

  static final class DemoControls extends CustomControls implements ActionListener, ChangeListener {
    TransformAnim demo;
    JSlider shapeSlider;
    JSlider stringSlider;
    JSlider imageSlider;
    Font font = new Font("serif", 1, 10);
    JToolBar toolbar;
    ButtonBorder buttonBorder = new ButtonBorder();

    public DemoControls(TransformAnim var1) {
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
      this.shapeSlider.setOpaque(true);
      this.shapeSlider.setPreferredSize(new Dimension(80, 44));
      var2.add(this.shapeSlider);
      var2.addSeparator();
      this.stringSlider = new JSlider(0, 0, 10, var1.numStrings);
      this.stringSlider.addChangeListener(this);
      var3 = new TitledBorder(new EtchedBorder());
      var3.setTitleFont(this.font);
      var3.setTitle(var1.numStrings + " Strings");
      this.stringSlider.setBorder(var3);
      this.stringSlider.setOpaque(true);
      this.stringSlider.setPreferredSize(new Dimension(80, 44));
      var2.add(this.stringSlider);
      var2.addSeparator();
      this.imageSlider = new JSlider(0, 0, 10, var1.numImages);
      this.imageSlider.addChangeListener(this);
      var3 = new TitledBorder(new EtchedBorder());
      var3.setTitleFont(this.font);
      var3.setTitle(var1.numImages + " Images");
      this.imageSlider.setBorder(var3);
      this.imageSlider.setOpaque(true);
      this.imageSlider.setPreferredSize(new Dimension(80, 44));
      var2.add(this.imageSlider);
      var2.addSeparator();
      this.add(var2);
      this.toolbar = new JToolBar();
      this.toolbar.setFloatable(false);
      this.addButton("T", "translate", var1.doTranslate);
      this.addButton("R", "rotate", var1.doRotate);
      this.addButton("SC", "scale", var1.doScale);
      this.addButton("SH", "shear", var1.doShear);
      this.add(this.toolbar);
    }

    public void addButton(String var1, String var2, boolean var3) {
      JToggleButton var4 = (JToggleButton) this.toolbar.add(new JToggleButton(var1));
      var4.setFont(this.font);
      var4.setSelected(var3);
      var4.setToolTipText(var2);
      var4.setFocusPainted(false);
      var4.setBorder(this.buttonBorder);
      var4.addActionListener(this);
    }

    public void actionPerformed(ActionEvent var1) {
      JToggleButton var2 = (JToggleButton) var1.getSource();
      if (var2.getText().equals("T")) {
        this.demo.doTranslate = var2.isSelected();
      } else if (var2.getText().equals("R")) {
        this.demo.doRotate = var2.isSelected();
      } else if (var2.getText().equals("SC")) {
        this.demo.doScale = var2.isSelected();
      } else if (var2.getText().equals("SH")) {
        this.demo.doShear = var2.isSelected();
      }

      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }
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

      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }

      var2.repaint();
    }

    public Dimension getPreferredSize() {
      return new Dimension(80, 38);
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 1; var2 < this.toolbar.getComponentCount(); ++var2) {
          try {
            Thread.sleep(4444L);
          } catch (InterruptedException var4) {
            return;
          }

          ((AbstractButton) this.toolbar.getComponentAtIndex(var2)).doClick();
        }
      }

      this.thread = null;
    }
  }

  static class ObjData {
    static final int UP = 0;
    static final int DOWN = 1;
    Object object;
    Paint paint;
    double x;
    double y;
    double ix = 5.0D;
    double iy = 3.0D;
    int rotate;
    double scale;
    double shear;
    int scaleDirection;
    int shearDirection;
    AffineTransform at = new AffineTransform();

    public ObjData(Object var1, Paint var2) {
      this.object = var1;
      this.paint = var2;
      this.rotate = (int) (Math.random() * 360.0D);
      this.scale = Math.random() * 1.5D;
      this.scaleDirection = Math.random() > 0.5D ? 0 : 1;
      this.shear = Math.random() * 0.5D;
      this.shearDirection = Math.random() > 0.5D ? 0 : 1;
    }

    public void reset(int var1, int var2) {
      this.x = Math.random() * (double) var1;
      this.y = Math.random() * (double) var2;
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

    public void step(int var1, int var2, TransformAnim var3) {
      this.at.setToIdentity();
      if (var3.doRotate) {
        if ((this.rotate += 5) == 360) {
          this.rotate = 0;
        }

        this.at.rotate(Math.toRadians(this.rotate), this.x, this.y);
      }

      this.at.translate(this.x, this.y);
      if (var3.doTranslate) {
        this.x += this.ix;
        this.y += this.iy;
        if (this.x > (double) var1) {
          this.x = var1 - 1;
          this.ix = Math.random() * (double) (-var1) / 32.0D - 1.0D;
        }

        if (this.x < 0.0D) {
          this.x = 2.0D;
          this.ix = Math.random() * (double) var1 / 32.0D + 1.0D;
        }

        if (this.y > (double) var2) {
          this.y = var2 - 2;
          this.iy = Math.random() * (double) (-var2) / 32.0D - 1.0D;
        }

        if (this.y < 0.0D) {
          this.y = 2.0D;
          this.iy = Math.random() * (double) var2 / 32.0D + 1.0D;
        }
      }

      if (var3.doScale && this.scaleDirection == 0) {
        if ((this.scale += 0.05D) > 1.5D) {
          this.scaleDirection = 1;
        }
      } else if (var3.doScale && this.scaleDirection == 1 && (this.scale -= 0.05D) < 0.5D) {
        this.scaleDirection = 0;
      }

      if (var3.doScale) {
        this.at.scale(this.scale, this.scale);
      }

      if (var3.doShear && this.shearDirection == 0) {
        if ((this.shear += 0.05D) > 0.5D) {
          this.shearDirection = 1;
        }
      } else if (var3.doShear && this.shearDirection == 1 && (this.shear -= 0.05D) < -0.5D) {
        this.shearDirection = 0;
      }

      if (var3.doShear) {
        this.at.shear(this.shear, this.shear);
      }
    }
  }

  static class TextData {
    public String string;
    public Font font;

    public TextData(String var1, Font var2) {
      this.string = var1;
      this.font = var2;
    }
  }
}
