//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D.Float;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Intro extends JPanel {
  static Intro.Surface surface;
  private static Color myBlack = new Color(20, 20, 20);
  private static Color myWhite = new Color(240, 240, 255);
  private static Color myRed = new Color(149, 43, 42);
  private static Color myBlue = new Color(94, 105, 176);
  private static Color myYellow = new Color(255, 255, 140);
  private Intro.ScenesTable scenesTable;
  private boolean doTable;

  public Intro() {
    EmptyBorder var1 = new EmptyBorder(80, 110, 80, 110);
    BevelBorder var2 = new BevelBorder(1);
    this.setBorder(new CompoundBorder(var1, var2));
    this.setLayout(new BorderLayout());
    this.setBackground(Color.GRAY);
    this.setToolTipText("click for scene table");
    this.add(surface = new Intro.Surface());
    this.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent var1) {
            Intro.this.removeAll();
            if (Intro.this.doTable = !Intro.this.doTable) {
              Intro.this.setToolTipText("click for animation");
              Intro.surface.stop();
              if (Intro.this.scenesTable == null) {
                Intro.this.scenesTable = new Intro.ScenesTable();
              }

              Intro.this.add(Intro.this.scenesTable);
            } else {
              Intro.this.setToolTipText("click for scene table");
              Intro.surface.start();
              Intro.this.add(Intro.surface);
            }

            Intro.this.revalidate();
            Intro.this.repaint();
          }
        });
  }

  public static void main(String[] var0) {
    final Intro var1 = new Intro();
    WindowAdapter var2 =
        new WindowAdapter() {
          public void windowClosing(WindowEvent var1x) {
            System.exit(0);
          }

          public void windowDeiconified(WindowEvent var1x) {
            var1.start();
          }

          public void windowIconified(WindowEvent var1x) {
            var1.stop();
          }
        };
    JFrame var3 = new JFrame("Java2D Demo - Intro");
    var3.addWindowListener(var2);
    var3.getContentPane().add("Center", var1);
    var3.pack();
    Dimension var4 = Toolkit.getDefaultToolkit().getScreenSize();
    short var5 = 720;
    short var6 = 510;
    var3.setLocation(var4.width / 2 - var5 / 2, var4.height / 2 - var6 / 2);
    var3.setSize(var5, var6);
    var3.setVisible(true);
    var1.start();
  }

  public void start() {
    if (!this.doTable) {
      surface.start();
    }
  }

  public void stop() {
    if (!this.doTable) {
      surface.stop();
    }
  }

  static class Surface extends JPanel implements Runnable {
    static Intro.Surface surf;
    static Image cupanim;
    static Image java_logo;
    static BufferedImage bimg;
    public Intro.Surface.Director director;
    public int index;
    public long sleepAmt = 30L;
    private Thread thread;

    public Surface() {
      surf = this;
      this.setBackground(Intro.myBlack);
      this.setLayout(new BorderLayout());
      this.addMouseListener(
          new MouseAdapter() {
            public void mouseClicked(MouseEvent var1) {
              if (Surface.this.thread == null) {
                Surface.this.start();
              } else {
                Surface.this.stop();
              }
            }
          });
      cupanim = DemoImages.getImage("cupanim.gif", this);
      java_logo = DemoImages.getImage("java_logo.png", this);
      this.director = new Intro.Surface.Director();
    }

    static FontMetrics getMetrics(Font var0) {
      return surf.getFontMetrics(var0);
    }

    public void paint(Graphics var1) {
      Dimension var2 = this.getSize();
      if (var2.width > 0 && var2.height > 0) {
        if (bimg == null || bimg.getWidth() != var2.width || bimg.getHeight() != var2.height) {
          bimg = this.getGraphicsConfiguration().createCompatibleImage(var2.width, var2.height);

          for (int var3 = this.index + 1; var3 < this.director.size(); ++var3) {
            this.director.get(var3).reset(var2.width, var2.height);
          }
        }

        Intro.Surface.Scene var5 = this.director.get(this.index);
        if (var5.index <= var5.length) {
          if (this.thread != null) {
            var5.step(var2.width, var2.height);
          }

          Graphics2D var4 = bimg.createGraphics();
          var4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          var4.setBackground(this.getBackground());
          var4.clearRect(0, 0, var2.width, var2.height);
          var5.render(var2.width, var2.height, var4);
          if (this.thread != null) {
            ++var5.index;
          }

          var4.dispose();
        }

        var1.drawImage(bimg, 0, 0, this);
      }
    }

    public void start() {
      if (this.thread == null) {
        this.thread = new Thread(this);
        this.thread.setPriority(1);
        this.thread.setName("Intro");
        this.thread.start();
      }
    }

    public synchronized void stop() {
      if (this.thread != null) {
        this.thread.interrupt();
      }

      this.thread = null;
      this.notifyAll();
    }

    public void reset() {
      this.index = 0;
      Dimension var1 = this.getSize();
      Iterator var2 = this.director.iterator();

      while (var2.hasNext()) {
        Intro.Surface.Scene var3 = (Intro.Surface.Scene) var2.next();
        var3.reset(var1.width, var1.height);
      }
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1 && !this.isShowing() || this.getSize().width <= 0) {
        try {
          Thread.sleep(500L);
        } catch (InterruptedException var4) {
          return;
        }
      }

      if (this.index == 0) {
        this.reset();
      }

      while (this.thread == var1) {
        Intro.Surface.Scene var2 = this.director.get(this.index);
        if ((Boolean) var2.participate) {
          this.repaint();

          try {
            Thread.sleep(this.sleepAmt);
          } catch (InterruptedException var5) {
            break;
          }

          if (var2.index > var2.length) {
            var2.pause();
            if (++this.index >= this.director.size()) {
              this.reset();
            }
          }
        } else if (++this.index >= this.director.size()) {
          this.reset();
        }
      }

      this.thread = null;
    }

    interface Part {
      void reset(int var1, int var2);

      void step(int var1, int var2);

      void render(int var1, int var2, Graphics2D var3);

      int getBegin();

      int getEnd();
    }

    static class Contributors implements Intro.Surface.Part {
      static final Font font = new Font("serif", 0, 26);
      static String[] members =
          new String[] {
            "Brian Lichtenwalter",
            "Jeannette Hung",
            "Thanh Nguyen",
            "Jim Graham",
            "Jerry Evans",
            "John Raley",
            "Michael Peirce",
            "Robert Kim",
            "Jennifer Ball",
            "Deborah Adair",
            "Paul Charlton",
            "Dmitry Feld",
            "Gregory Stone",
            "Richard Blanchard",
            "Link Perry",
            "Phil Race",
            "Vincent Hardy",
            "Parry Kejriwal",
            "Doug Felt",
            "Rekha Rangarajan",
            "Paula Patel",
            "Michael Bundschuh",
            "Joe Warzecha",
            "Joey Beheler",
            "Aastha Bhardwaj",
            "Daniel Rice",
            "Chris Campbell",
            "Shinsuke Fukuda",
            "Dmitri Trembovetski",
            "Chet Haase",
            "Jennifer Godinez",
            "Nicholas Talian",
            "Raul Vera",
            "Ankit Patel",
            "Ilya Bagrak",
            "Praveen Mohan",
            "Rakesh Menon"
          };
      static FontMetrics fm;

      static {
        fm = Intro.Surface.getMetrics(font);
      }

      private int beginning;
      private int ending;
      private int nStrs;
      private int strH;
      private int index;
      private int yh;
      private int height;
      private List<String> v = new ArrayList();
      private List<String> cast;
      private int counter;
      private int cntMod;
      private GradientPaint gp;

      public Contributors(int var1, int var2) {
        this.cast = new ArrayList(members.length + 3);
        this.beginning = var1;
        this.ending = var2;
        Arrays.sort(members);
        this.cast.add("CONTRIBUTORS");
        this.cast.add(" ");
        this.cast.addAll(Arrays.asList(members));
        this.cast.add(" ");
        this.cast.add(" ");
        this.cntMod = (this.ending - this.beginning) / this.cast.size() - 1;
      }

      public void reset(int var1, int var2) {
        this.v.clear();
        this.strH = fm.getAscent() + fm.getDescent();
        this.nStrs = (var2 - 40) / this.strH + 1;
        this.height = this.strH * (this.nStrs - 1) + 48;
        this.index = 0;
        this.gp =
            new GradientPaint(
                0.0F, (float) (var2 / 2), Color.WHITE, 0.0F, (float) (var2 + 20), Color.BLACK);
        this.counter = 0;
      }

      public void step(int var1, int var2) {
        if (this.counter++ % this.cntMod == 0) {
          if (this.index < this.cast.size()) {
            this.v.add(this.cast.get(this.index));
          }

          if ((this.v.size() == this.nStrs || this.index >= this.cast.size())
              && !this.v.isEmpty()) {
            this.v.remove(0);
          }

          ++this.index;
        }
      }

      public void render(int var1, int var2, Graphics2D var3) {
        var3.setPaint(this.gp);
        var3.setFont(font);
        double var4 = this.counter % this.cntMod;
        double var6 = 1.0D - var4 / (double) this.cntMod;
        var6 = var6 == 1.0D ? 0.0D : var6;
        int var8 = (int) (var6 * (double) this.strH);
        if (this.index >= this.cast.size()) {
          var8 += this.yh;
        } else {
          var8 = this.yh = this.height - this.v.size() * this.strH + var8;
        }

        Iterator var9 = this.v.iterator();

        while (var9.hasNext()) {
          String var10 = (String) var9.next();
          var3.drawString(var10, var1 / 2 - fm.stringWidth(var10) / 2, var8 += this.strH);
        }
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class Features implements Intro.Surface.Part {
      static final int GRAPHICS = 0;
      static final int TEXT = 1;
      static final int IMAGES = 2;
      static final int COLOR = 3;
      static final Font font1 = new Font("serif", 1, 38);
      static final Font font2 = new Font("serif", 0, 24);
      static FontMetrics fm1;
      static FontMetrics fm2;
      static String[][] table;

      static {
        fm1 = Intro.Surface.getMetrics(font1);
        fm2 = Intro.Surface.getMetrics(font2);
        table =
            new String[][] {
              {
                "Graphics",
                "Antialiased rendering",
                "Bezier paths",
                "Transforms",
                "Compositing",
                "Stroking parameters"
              },
              {
                "Text",
                "Extended font support",
                "Advanced text layout",
                "Dynamic font loading",
                "AttributeSets for font customization"
              },
              {
                "Images",
                "Flexible image layouts",
                "Extended imaging operations",
                "   Convolutions, Lookup Tables",
                "RenderableImage interface"
              },
              {"Color", "ICC profile support", "Color conversion", "Arbitrary color spaces"}
            };
      }

      private String[] list;
      private int beginning;
      private int ending;
      private int strH;
      private int endIndex;
      private int listIndex;
      private List<String> v = new ArrayList();

      public Features(int var1, int var2, int var3) {
        this.list = table[var1];
        this.beginning = var2;
        this.ending = var3;
      }

      public void reset(int var1, int var2) {
        this.strH = fm2.getAscent() + fm2.getDescent();
        this.endIndex = 1;
        this.listIndex = 0;
        this.v.clear();
        this.v.add(this.list[this.listIndex].substring(0, this.endIndex));
      }

      public void step(int var1, int var2) {
        if (this.listIndex < this.list.length) {
          if (++this.endIndex > this.list[this.listIndex].length()) {
            if (++this.listIndex < this.list.length) {
              this.endIndex = 1;
              this.v.add(this.list[this.listIndex].substring(0, this.endIndex));
            }
          } else {
            this.v.set(this.listIndex, this.list[this.listIndex].substring(0, this.endIndex));
          }
        }
      }

      public void render(int var1, int var2, Graphics2D var3) {
        var3.setColor(Intro.myWhite);
        var3.setFont(font1);
        var3.drawString(this.v.get(0), 90, 85);
        var3.setFont(font2);
        int var4 = 1;

        for (int var5 = 90; var4 < this.v.size(); ++var4) {
          var3.drawString(this.v.get(var4), 120, var5 += this.strH);
        }
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class Temp implements Intro.Surface.Part {
      static final int NOANIM = 1;
      static final int RECT = 2;
      static final int IMG = 4;
      static final int RNA = 3;
      static final int INA = 5;
      private int beginning;
      private int ending;
      private float alpha;
      private float aIncr;
      private int type;
      private Rectangle rect1;
      private Rectangle rect2;
      private int x;
      private int y;
      private int xIncr;
      private int yIncr;
      private Image img;

      public Temp(int var1, Image var2, int var3, int var4) {
        this.type = var1;
        this.img = var2;
        this.beginning = var3;
        this.ending = var4;
        this.aIncr = 0.9F / (float) (this.ending - this.beginning);
        if ((var1 & 1) != 0) {
          this.alpha = 1.0F;
        }
      }

      public void reset(int var1, int var2) {
        this.rect1 = new Rectangle(8, 20, var1 - 20, 30);
        this.rect2 = new Rectangle(20, 8, 30, var2 - 20);
        if ((this.type & 1) == 0) {
          this.alpha = 0.0F;
          this.xIncr = var1 / (this.ending - this.beginning);
          this.yIncr = var2 / (this.ending - this.beginning);
          this.x = var1 + (int) ((double) this.xIncr * 1.4D);
          this.y = var2 + (int) ((double) this.yIncr * 1.4D);
        }
      }

      public void step(int var1, int var2) {
        if ((this.type & 1) == 0) {
          if ((this.type & 2) != 0) {
            this.rect1.setLocation(this.x -= this.xIncr, 20);
            this.rect2.setLocation(20, this.y -= this.yIncr);
          }

          if ((this.type & 4) != 0) {
            this.alpha += this.aIncr;
          }
        }
      }

      public void render(int var1, int var2, Graphics2D var3) {
        if ((this.type & 2) != 0) {
          var3.setColor(Intro.myBlue);
          var3.fill(this.rect1);
          var3.setColor(Intro.myRed);
          var3.fill(this.rect2);
        }

        if ((this.type & 4) != 0) {
          Composite var4 = var3.getComposite();
          if (this.alpha >= 0.0F && this.alpha <= 1.0F) {
            var3.setComposite(AlphaComposite.getInstance(3, this.alpha));
          }

          var3.drawImage(this.img, 30, 30, null);
          var3.setComposite(var4);
        }
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class LnE implements Intro.Surface.Part {
      static final int INC = 1;
      static final int DEC = 2;
      static final int R = 4;
      static final int ZOOM = 8;
      static final int AC = 32;
      static final int RI = 5;
      static final int RD = 6;
      static final int ZOOMI = 9;
      static final int ZOOMD = 10;
      static final int ACI = 33;
      static final int ACD = 34;
      private int beginning;
      private int ending;
      private double rIncr;
      private double rotate;
      private double zIncr;
      private double zoom;
      private List<Double> pts = new ArrayList();
      private float alpha;
      private float aIncr;
      private int type;

      public LnE(int var1, int var2, int var3) {
        this.type = var1;
        this.beginning = var2;
        this.ending = var3;
        float var4 = (float) (this.ending - this.beginning);
        this.rIncr = 360.0F / var4;
        this.aIncr = 0.9F / var4;
        this.zIncr = 2.0F / var4;
        if ((var1 & 2) != 0) {
          this.rIncr = -this.rIncr;
          this.aIncr = -this.aIncr;
          this.zIncr = -this.zIncr;
        }
      }

      public void generatePts(int var1, int var2, double var3) {
        this.pts.clear();
        double var5 = (double) Math.min(var1, var2) * var3;
        java.awt.geom.Ellipse2D.Double var7 =
            new java.awt.geom.Ellipse2D.Double(
                (double) (var1 / 2) - var5 / 2.0D, (double) (var2 / 2) - var5 / 2.0D, var5, var5);
        PathIterator var8 = var7.getPathIterator(null, 0.8D);

        while (!var8.isDone()) {
          double[] var9 = new double[6];
          switch (var8.currentSegment(var9)) {
            case 0:
            case 1:
              this.pts.add(new Double(var9[0], var9[1]));
            default:
              var8.next();
          }
        }
      }

      public void reset(int var1, int var2) {
        if ((this.type & 2) != 0) {
          this.rotate = 360.0D;
          this.alpha = 1.0F;
          this.zoom = 2.0D;
        } else {
          this.rotate = this.alpha = 0.0F;
          this.zoom = 0.0D;
        }

        if ((this.type & 8) == 0) {
          this.generatePts(var1, var2, 0.5D);
        }
      }

      public void step(int var1, int var2) {
        if ((this.type & 8) != 0) {
          this.generatePts(var1, var2, this.zoom += this.zIncr);
        }

        if ((this.type & 5) != 0 || (this.type & 5) != 0) {
          this.rotate += this.rIncr;
        }

        if ((this.type & 33) != 0 || (this.type & 34) != 0) {
          this.alpha += this.aIncr;
        }
      }

      public void render(int var1, int var2, Graphics2D var3) {
        Composite var4 = null;
        if ((this.type & 32) != 0 && this.alpha >= 0.0F && this.alpha <= 1.0F) {
          var4 = var3.getComposite();
          var3.setComposite(AlphaComposite.getInstance(3, this.alpha));
        }

        AffineTransform var5 = null;
        if ((this.type & 4) != 0) {
          var5 = var3.getTransform();
          AffineTransform var6 = new AffineTransform();
          var6.rotate(Math.toRadians(this.rotate), var1 / 2, var2 / 2);
          var3.setTransform(var6);
        }

        Double var9 = new Double(var1 / 2, var2 / 2);
        var3.setColor(Color.YELLOW);
        Iterator var7 = this.pts.iterator();

        while (var7.hasNext()) {
          Point2D var8 = (Point2D) var7.next();
          var3.draw(new Float(var9, var8));
        }

        if (var5 != null) {
          var3.setTransform(var5);
        }

        if (var4 != null) {
          var3.setComposite(var4);
        }
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class SiE implements Intro.Surface.Part {
      private int beginning;
      private int ending;
      private BufferedImage bimg;
      private double rIncr;
      private double sIncr;
      private double scale;
      private double rotate;
      private int siw;
      private int sih;
      private List<BufferedImage> subs = new ArrayList(20);
      private List<Point> pts = new ArrayList(20);

      public SiE(int var1, int var2, int var3, int var4) {
        this.siw = var1;
        this.sih = var2;
        this.beginning = var3;
        this.ending = var4;
        this.rIncr = 360.0D / (double) (this.ending - this.beginning);
        this.sIncr = 1.0D / (double) (this.ending - this.beginning);
      }

      public void reset(int var1, int var2) {
        this.scale = 1.0D;
        this.rotate = 0.0D;
        this.bimg = null;
        this.subs.clear();
        this.pts.clear();
      }

      public void step(int var1, int var2) {
        if (this.bimg == null) {
          int var3 = Intro.Surface.bimg.getWidth();
          int var4 = Intro.Surface.bimg.getHeight();
          this.bimg = new BufferedImage(var3, var4, 1);
          Graphics2D var5 = this.bimg.createGraphics();
          var5.drawImage(Intro.Surface.bimg, 0, 0, null);

          for (int var6 = 0; var6 < var1 && this.scale > 0.0D; var6 += this.siw) {
            int var7 = var6 + this.siw < var1 ? this.siw : var1 - var6;

            for (int var8 = 0; var8 < var2; var8 += this.sih) {
              int var9 = var8 + this.sih < var2 ? this.sih : var2 - var8;
              this.subs.add(this.bimg.getSubimage(var6, var8, var7, var9));
              this.pts.add(new Point(var6, var8));
            }
          }
        }

        this.rotate += this.rIncr;
        this.scale -= this.sIncr;
      }

      public void render(int var1, int var2, Graphics2D var3) {
        AffineTransform var4 = var3.getTransform();
        var3.setColor(Intro.myBlue);

        for (int var5 = 0; var5 < this.subs.size() && this.scale > 0.0D; ++var5) {
          BufferedImage var6 = this.subs.get(var5);
          Point var7 = this.pts.get(var5);
          int var8 = var6.getWidth();
          int var9 = var6.getHeight();
          AffineTransform var10 = new AffineTransform();
          var10.rotate(Math.toRadians(this.rotate), var7.x + var8 / 2, var7.y + var9 / 2);
          var10.translate(var7.x, var7.y);
          var10.scale(this.scale, this.scale);
          Rectangle var11 = new Rectangle(0, 0, var8, var9);
          Shape var12 = var10.createTransformedShape(var11);
          Rectangle2D var13 = var12.getBounds2D();
          double var14 = (double) (var7.x + var8 / 2) - (var13.getX() + var13.getWidth() / 2.0D);
          double var16 = (double) (var7.y + var9 / 2) - (var13.getY() + var13.getHeight() / 2.0D);
          AffineTransform var18 = new AffineTransform();
          var18.translate(var14, var16);
          var18.concatenate(var10);
          var3.setTransform(var18);
          var3.drawImage(var6, 0, 0, null);
          var3.draw(var11);
        }

        var3.setTransform(var4);
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class DdE implements Intro.Surface.Part {
      private int beginning;
      private int ending;
      private BufferedImage bimg;
      private Graphics2D big;
      private List<Integer> list;
      private List<Integer> xlist;
      private List<Integer> ylist;
      private int xeNum;
      private int yeNum;
      private int xcSize;
      private int ycSize;
      private int inc;
      private int blocksize;

      public DdE(int var1, int var2, int var3) {
        this.beginning = var1;
        this.ending = var2;
        this.blocksize = var3;
      }

      private void createShuffledLists() {
        int var1 = this.bimg.getWidth();
        int var2 = this.bimg.getHeight();
        this.xlist = new ArrayList(var1);
        this.ylist = new ArrayList(var2);
        this.list = new ArrayList(this.ending - this.beginning + 1);

        int var3;
        for (var3 = 0; var3 < var1; ++var3) {
          this.xlist.add(var3, var3);
        }

        for (var3 = 0; var3 < var2; ++var3) {
          this.ylist.add(var3, var3);
        }

        for (var3 = 0; var3 < this.ending - this.beginning + 1; ++var3) {
          this.list.add(var3, var3);
        }

        Collections.shuffle(this.xlist);
        Collections.shuffle(this.ylist);
        Collections.shuffle(this.list);
      }

      public void reset(int var1, int var2) {
        this.bimg = null;
      }

      public void step(int var1, int var2) {
        if (this.inc > this.ending) {
          this.bimg = null;
        }

        if (this.bimg == null) {
          int var3 = Intro.Surface.bimg.getWidth();
          int var4 = Intro.Surface.bimg.getHeight();
          this.bimg = new BufferedImage(var3, var4, 1);
          this.createShuffledLists();
          this.big = this.bimg.createGraphics();
          this.big.drawImage(Intro.Surface.bimg, 0, 0, null);
          this.xcSize = this.xlist.size() / (this.ending - this.beginning) + 1;
          this.ycSize = this.ylist.size() / (this.ending - this.beginning) + 1;
          this.xeNum = 0;
          this.inc = 0;
        }

        this.xeNum = this.xcSize * this.list.get(this.inc);
        this.yeNum = -this.ycSize;
        ++this.inc;
      }

      public void render(int var1, int var2, Graphics2D var3) {
        this.big.setColor(Intro.myBlack);

        for (int var4 = 0; var4 <= this.ending - this.beginning; ++var4) {
          if (this.xeNum + this.xcSize > this.xlist.size()) {
            this.xeNum = 0;
          } else {
            this.xeNum += this.xcSize;
          }

          this.yeNum += this.ycSize;

          for (int var5 = this.xeNum;
              var5 < this.xeNum + this.xcSize && var5 < this.xlist.size();
              ++var5) {
            for (int var6 = this.yeNum;
                var6 < this.yeNum + this.ycSize && var6 < this.ylist.size();
                ++var6) {
              int var7 = this.xlist.get(var5);
              int var8 = this.ylist.get(var6);
              if (var7 % this.blocksize == 0 && var8 % this.blocksize == 0) {
                this.big.fillRect(var7, var8, this.blocksize, this.blocksize);
              }
            }
          }
        }

        var3.drawImage(this.bimg, 0, 0, null);
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class CoE implements Intro.Surface.Part {
      static final int WID = 1;
      static final int HEI = 2;
      static final int OVAL = 4;
      static final int RECT = 8;
      static final int RAND = 16;
      static final int ARC = 32;
      private int type;
      private int beginning;
      private int ending;
      private BufferedImage bimg;
      private Shape shape;
      private double zoom;
      private double extent;
      private double zIncr;
      private double eIncr;
      private boolean doRandom;

      public CoE(int var1, int var2, int var3) {
        this.type = var1;
        this.beginning = var2;
        this.ending = var3;
        this.zIncr = -(2.0D / (double) (this.ending - this.beginning));
        this.eIncr = 360.0D / (double) (this.ending - this.beginning);
        this.doRandom = (var1 & 16) != 0;
      }

      public void reset(int var1, int var2) {
        if (this.doRandom) {
          int var3 = (int) (Math.random() * 5.0D);
          switch (var3) {
            case 0:
              this.type = 4;
              break;
            case 1:
              this.type = 8;
              break;
            case 2:
              this.type = 9;
              break;
            case 3:
              this.type = 10;
              break;
            case 4:
              this.type = 32;
              break;
            default:
              this.type = 4;
          }
        }

        this.shape = null;
        this.bimg = null;
        this.extent = 360.0D;
        this.zoom = 2.0D;
      }

      public void step(int var1, int var2) {
        if (this.bimg == null) {
          int var3 = Intro.Surface.bimg.getWidth();
          int var4 = Intro.Surface.bimg.getHeight();
          this.bimg = new BufferedImage(var3, var4, 1);
          Graphics2D var5 = this.bimg.createGraphics();
          var5.drawImage(Intro.Surface.bimg, 0, 0, null);
        }

        double var6 = (double) Math.min(var1, var2) * this.zoom;
        if ((this.type & 4) != 0) {
          this.shape =
              new java.awt.geom.Ellipse2D.Double(
                  (double) (var1 / 2) - var6 / 2.0D, (double) (var2 / 2) - var6 / 2.0D, var6, var6);
        } else if ((this.type & 32) != 0) {
          this.shape =
              new java.awt.geom.Arc2D.Double(
                  -100.0D, -100.0D, var1 + 200, var2 + 200, 90.0D, this.extent, 2);
          this.extent -= this.eIncr;
        } else if ((this.type & 8) != 0) {
          if ((this.type & 1) != 0) {
            this.shape =
                new java.awt.geom.Rectangle2D.Double(
                    (double) (var1 / 2) - var6 / 2.0D, 0.0D, var6, var2);
          } else if ((this.type & 2) != 0) {
            this.shape =
                new java.awt.geom.Rectangle2D.Double(
                    0.0D, (double) (var2 / 2) - var6 / 2.0D, var1, var6);
          } else {
            this.shape =
                new java.awt.geom.Rectangle2D.Double(
                    (double) (var1 / 2) - var6 / 2.0D,
                    (double) (var2 / 2) - var6 / 2.0D,
                    var6,
                    var6);
          }
        }

        this.zoom += this.zIncr;
      }

      public void render(int var1, int var2, Graphics2D var3) {
        var3.clip(this.shape);
        var3.drawImage(this.bimg, 0, 0, null);
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static final class TpE implements Intro.Surface.Part {
      static final int INC = 1;
      static final int DEC = 2;
      static final int OVAL = 4;
      static final int RECT = 8;
      static final int HAF = 16;
      static final int NF = 32;
      static final int OI = 5;
      static final int OD = 6;
      static final int RI = 9;
      static final int RD = 10;
      private Paint p1;
      private Paint p2;
      private int beginning;
      private int ending;
      private float incr;
      private float index;
      private TexturePaint texture;
      private int type;
      private int size;
      private BufferedImage bimg;
      private Rectangle rect;

      public TpE(int var1, Paint var2, Paint var3, int var4, int var5, int var6) {
        this.type = var1;
        this.p1 = var2;
        this.p2 = var3;
        this.beginning = var5;
        this.ending = var6;
        this.setTextureSize(var4);
      }

      public void setTextureSize(int var1) {
        this.size = var1;
        this.bimg = new BufferedImage(var1, var1, 1);
        this.rect = new Rectangle(0, 0, var1, var1);
      }

      public void reset(int var1, int var2) {
        this.incr = (float) this.size / (float) (this.ending - this.beginning);
        if ((this.type & 16) != 0) {
          this.incr /= 2.0F;
        }

        if ((this.type & 2) != 0) {
          this.index = (float) this.size;
          if ((this.type & 16) != 0) {
            this.index /= 2.0F;
          }

          this.incr = -this.incr;
        } else {
          this.index = 0.0F;
        }

        this.index += this.incr;
      }

      public void step(int var1, int var2) {
        Graphics2D var3 = this.bimg.createGraphics();
        var3.setPaint(this.p1);
        var3.fillRect(0, 0, this.size, this.size);
        var3.setPaint(this.p2);
        if ((this.type & 4) != 0) {
          var3.fill(new java.awt.geom.Ellipse2D.Float(0.0F, 0.0F, this.index, this.index));
        } else if ((this.type & 8) != 0) {
          var3.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, this.index, this.index));
        }

        this.texture = new TexturePaint(this.bimg, this.rect);
        var3.dispose();
        this.index += this.incr;
      }

      public void render(int var1, int var2, Graphics2D var3) {
        var3.setPaint(this.texture);
        if ((this.type & 32) == 0) {
          var3.fillRect(0, 0, var1, var2);
        }
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class GpE implements Intro.Surface.Part {
      static final int INC = 1;
      static final int DEC = 2;
      static final int CNT = 4;
      static final int WID = 8;
      static final int WI = 9;
      static final int WD = 10;
      static final int HEI = 16;
      static final int HI = 17;
      static final int HD = 18;
      static final int SPL = 36;
      static final int SIW = 45;
      static final int SDW = 46;
      static final int SIH = 53;
      static final int SDH = 54;
      static final int BUR = 68;
      static final int BURI = 69;
      static final int BURD = 70;
      static final int NF = 128;
      private Color c1;
      private Color c2;
      private int beginning;
      private int ending;
      private float incr;
      private float index;
      private List<Rectangle2D> rect = new ArrayList();
      private List<GradientPaint> grad = new ArrayList();
      private int type;

      public GpE(int var1, Color var2, Color var3, int var4, int var5) {
        this.type = var1;
        this.c1 = var2;
        this.c2 = var3;
        this.beginning = var4;
        this.ending = var5;
      }

      public void reset(int var1, int var2) {
        this.incr = 1.0F / (float) (this.ending - this.beginning);
        if ((this.type & 4) != 0) {
          this.incr /= 2.3F;
        }

        if ((this.type & 4) != 0 && (this.type & 1) != 0) {
          this.index = 0.5F;
        } else if ((this.type & 2) != 0) {
          this.index = 1.0F;
          this.incr = -this.incr;
        } else {
          this.index = 0.0F;
        }

        this.index += this.incr;
      }

      public void step(int var1, int var2) {
        this.rect.clear();
        this.grad.clear();
        float var3;
        float var4;
        float var5;
        if ((this.type & 8) != 0) {
          var3 = 0.0F;
          var4 = 0.0F;
          var5 = 0.0F;
          if ((this.type & 36) != 0) {
            var3 = (float) var1 * 0.5F;
            var4 = (float) var1 * (1.0F - this.index);
            var5 = (float) var1 * this.index;
          } else {
            var3 = (float) var1 * this.index;
            var5 = var3;
            var4 = var3;
          }

          this.rect.add(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, var3, (float) var2));
          this.rect.add(
              new java.awt.geom.Rectangle2D.Float(var3, 0.0F, (float) var1 - var3, (float) var2));
          this.grad.add(new GradientPaint(0.0F, 0.0F, this.c1, var4, 0.0F, this.c2));
          this.grad.add(new GradientPaint(var5, 0.0F, this.c2, (float) var1, 0.0F, this.c1));
        } else if ((this.type & 16) != 0) {
          var3 = 0.0F;
          var4 = 0.0F;
          var5 = 0.0F;
          if ((this.type & 36) != 0) {
            var3 = (float) var2 * 0.5F;
            var4 = (float) var2 * (1.0F - this.index);
            var5 = (float) var2 * this.index;
          } else {
            var3 = (float) var2 * this.index;
            var5 = var3;
            var4 = var3;
          }

          this.rect.add(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, (float) var1, var3));
          this.rect.add(
              new java.awt.geom.Rectangle2D.Float(0.0F, var3, (float) var1, (float) var2 - var3));
          this.grad.add(new GradientPaint(0.0F, 0.0F, this.c1, 0.0F, var4, this.c2));
          this.grad.add(new GradientPaint(0.0F, var5, this.c2, 0.0F, (float) var2, this.c1));
        } else if ((this.type & 68) != 0) {
          var3 = (float) (var1 / 2);
          var4 = (float) (var2 / 2);
          this.rect.add(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, var3, var4));
          this.rect.add(new java.awt.geom.Rectangle2D.Float(var3, 0.0F, var3, var4));
          this.rect.add(new java.awt.geom.Rectangle2D.Float(0.0F, var4, var3, var4));
          this.rect.add(new java.awt.geom.Rectangle2D.Float(var3, var4, var3, var4));
          var5 = (float) var1 * (1.0F - this.index);
          float var6 = (float) var1 * this.index;
          float var7 = (float) var2 * (1.0F - this.index);
          float var8 = (float) var2 * this.index;
          this.grad.add(new GradientPaint(0.0F, 0.0F, this.c1, var5, var7, this.c2));
          this.grad.add(new GradientPaint((float) var1, 0.0F, this.c1, var6, var7, this.c2));
          this.grad.add(new GradientPaint(0.0F, (float) var2, this.c1, var5, var8, this.c2));
          this.grad.add(
              new GradientPaint((float) var1, (float) var2, this.c1, var6, var8, this.c2));
        } else if ((this.type & 128) != 0) {
          var3 = (float) var2 * this.index;
          this.grad.add(new GradientPaint(0.0F, 0.0F, this.c1, 0.0F, var3, this.c2));
        }

        if ((this.type & 1) != 0 || (this.type & 2) != 0) {
          this.index += this.incr;
        }
      }

      public void render(int var1, int var2, Graphics2D var3) {
        var3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        for (int var4 = 0; var4 < this.grad.size(); ++var4) {
          var3.setPaint(this.grad.get(var4));
          if ((this.type & 128) == 0) {
            var3.fill(this.rect.get(var4));
          }
        }

        var3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static final class TxE implements Intro.Surface.Part {
      static final int INC = 1;
      static final int DEC = 2;
      static final int R = 4;
      static final int RI = 5;
      static final int RD = 6;
      static final int SC = 8;
      static final int SCI = 9;
      static final int SCD = 10;
      static final int SCX = 16;
      static final int SCXI = 25;
      static final int SCXD = 26;
      static final int SCY = 32;
      static final int SCYI = 41;
      static final int SCYD = 42;
      static final int AC = 64;
      static final int CLIP = 128;
      static final int NOP = 512;
      private int beginning;
      private int ending;
      private int type;
      private double rIncr;
      private double sIncr;
      private double sx;
      private double sy;
      private double rotate;
      private Shape[] shapes;
      private Shape[] txShapes;
      private int sw;
      private int numRev;
      private Paint paint;

      public TxE(String var1, Font var2, int var3, Paint var4, int var5, int var6) {
        this.type = var3;
        this.paint = var4;
        this.beginning = var5;
        this.ending = var6;
        this.setIncrements(2.0D);
        char[] var7 = var1.toCharArray();
        this.shapes = new Shape[var7.length];
        this.txShapes = new Shape[var7.length];
        FontRenderContext var8 = new FontRenderContext(null, true, true);
        TextLayout var9 = new TextLayout(var1, var2, var8);
        this.sw = (int) var9.getOutline(null).getBounds().getWidth();

        for (int var10 = 0; var10 < var7.length; ++var10) {
          String var11 = String.valueOf(var7[var10]);
          this.shapes[var10] = (new TextLayout(var11, var2, var8)).getOutline(null);
        }
      }

      public void setIncrements(double var1) {
        this.numRev = (int) var1;
        this.rIncr = 360.0D / ((double) (this.ending - this.beginning) / var1);
        this.sIncr = 1.0D / (double) (this.ending - this.beginning);
        if ((this.type & 16) != 0 || (this.type & 32) != 0) {
          this.sIncr *= 2.0D;
        }

        if ((this.type & 2) != 0) {
          this.rIncr = -this.rIncr;
          this.sIncr = -this.sIncr;
        }
      }

      public void reset(int var1, int var2) {
        if (this.type == 25) {
          this.sx = -1.0D;
          this.sy = 1.0D;
        } else if (this.type == 41) {
          this.sx = 1.0D;
          this.sy = -1.0D;
        } else {
          this.sx = this.sy = (this.type & 2) != 0 ? 1.0D : 0.0D;
        }

        this.rotate = 0.0D;
      }

      public void step(int var1, int var2) {
        float var3 = (float) (var1 / 2 - this.sw / 2);

        for (int var4 = 0; var4 < this.shapes.length; ++var4) {
          AffineTransform var5 = new AffineTransform();
          Rectangle var6 = this.shapes[var4].getBounds();
          var5.translate(var3, (double) (var2 / 2) + var6.getHeight() / 2.0D);
          var3 += (float) var6.getWidth() + 1.0F;
          Shape var7 = var5.createTransformedShape(this.shapes[var4]);
          Rectangle2D var8 = var7.getBounds2D();
          if ((this.type & 4) != 0) {
            var5.rotate(Math.toRadians(this.rotate));
          }

          if ((this.type & 8) != 0) {
            var5.scale(this.sx, this.sy);
          }

          var7 = var5.createTransformedShape(this.shapes[var4]);
          Rectangle2D var9 = var7.getBounds2D();
          double var10 =
              var8.getX() + var8.getWidth() / 2.0D - (var9.getX() + var9.getWidth() / 2.0D);
          double var12 =
              var8.getY() + var8.getHeight() / 2.0D - (var9.getY() + var9.getHeight() / 2.0D);
          AffineTransform var14 = new AffineTransform();
          var14.translate(var10, var12);
          var14.concatenate(var5);
          this.txShapes[var4] = var14.createTransformedShape(this.shapes[var4]);
        }

        if (Math.abs(this.rotate) <= (double) (this.numRev * 360)) {
          this.rotate += this.rIncr;
          if ((this.type & 16) != 0) {
            this.sx += this.sIncr;
          } else if ((this.type & 32) != 0) {
            this.sy += this.sIncr;
          } else {
            this.sx += this.sIncr;
            this.sy += this.sIncr;
          }
        }
      }

      public void render(int var1, int var2, Graphics2D var3) {
        Composite var4 = null;
        if ((this.type & 64) != 0 && this.sx > 0.0D && this.sx < 1.0D) {
          var4 = var3.getComposite();
          var3.setComposite(AlphaComposite.getInstance(3, (float) this.sx));
        }

        GeneralPath var5 = null;
        if ((this.type & 128) != 0) {
          var5 = new GeneralPath();
        }

        if (this.paint != null) {
          var3.setPaint(this.paint);
        }

        for (int var6 = 0; var6 < this.txShapes.length; ++var6) {
          if ((this.type & 128) != 0) {
            var5.append(this.txShapes[var6], false);
          } else {
            var3.fill(this.txShapes[var6]);
          }
        }

        if ((this.type & 128) != 0) {
          var3.clip(var5);
        }

        if (var4 != null) {
          var3.setComposite(var4);
        }
      }

      public int getBegin() {
        return this.beginning;
      }

      public int getEnd() {
        return this.ending;
      }
    }

    static class Scene {
      public Object name;
      public Object participate;
      public Object pauseAmt;
      public List<Intro.Surface.Part> parts;
      public int index;
      public int length;

      public Scene(List<Intro.Surface.Part> var1, Object var2, Object var3) {
        this.participate = Boolean.TRUE;
        this.name = var2;
        this.parts = var1;
        this.pauseAmt = var3;
        Iterator var4 = var1.iterator();

        while (var4.hasNext()) {
          Intro.Surface.Part var5 = (Intro.Surface.Part) var4.next();
          int var6 = var5.getEnd();
          if (var6 > this.length) {
            this.length = var6;
          }
        }
      }

      public void reset(int var1, int var2) {
        this.index = 0;

        for (int var3 = 0; var3 < this.parts.size(); ++var3) {
          this.parts.get(var3).reset(var1, var2);
        }
      }

      public void step(int var1, int var2) {
        for (int var3 = 0; var3 < this.parts.size(); ++var3) {
          Intro.Surface.Part var4 = this.parts.get(var3);
          if (this.index >= var4.getBegin() && this.index <= var4.getEnd()) {
            var4.step(var1, var2);
          }
        }
      }

      public void render(int var1, int var2, Graphics2D var3) {
        for (int var4 = 0; var4 < this.parts.size(); ++var4) {
          Intro.Surface.Part var5 = this.parts.get(var4);
          if (this.index >= var5.getBegin() && this.index <= var5.getEnd()) {
            var5.render(var1, var2, var3);
          }
        }
      }

      public void pause() {
        try {
          Thread.sleep(Long.parseLong((String) this.pauseAmt));
        } catch (Exception var2) {
        }

        System.gc();
      }
    }

    static class Director extends ArrayList<Intro.Surface.Scene> {
      GradientPaint gp;
      Font f1;
      Font f2;
      Font f3;
      Object[][][] partsInfo;

      public Director() {
        this.gp = new GradientPaint(0.0F, 40.0F, Intro.myBlue, 38.0F, 2.0F, Intro.myBlack);
        this.f1 = new Font("serif", 0, 200);
        this.f2 = new Font("serif", 0, 120);
        this.f3 = new Font("serif", 0, 72);
        this.partsInfo =
            new Object[][][] {
              {
                {"J  -  scale text on gradient", "0"},
                {
                  new Intro.Surface.GpE(69, Intro.myBlack, Intro.myBlue, 0, 20),
                  new Intro.Surface.TxE("J", this.f1, 9, Intro.myYellow, 2, 20)
                }
              },
              {
                {"2  -  scale & rotate text on gradient", "0"},
                {
                  new Intro.Surface.GpE(69, Intro.myBlue, Intro.myBlack, 0, 22),
                  new Intro.Surface.TxE("2", this.f1, 13, Intro.myYellow, 2, 22)
                }
              },
              {
                {"D  -  scale text on gradient", "0"},
                {
                  new Intro.Surface.GpE(69, Intro.myBlack, Intro.myBlue, 0, 20),
                  new Intro.Surface.TxE("D", this.f1, 9, Intro.myYellow, 2, 20)
                }
              },
              {
                {"Java2D  -  scale & rotate text on gradient", "1000"},
                {
                  new Intro.Surface.GpE(53, Intro.myBlue, Intro.myBlack, 0, 40),
                  new Intro.Surface.TxE("Java2D", this.f2, 13, Intro.myYellow, 0, 40)
                }
              },
              {{"Previous scene dither dissolve out", "0"}, {new Intro.Surface.DdE(0, 20, 1)}},
              {
                {"Graphics Features", "999"},
                {
                  new Intro.Surface.Temp(2, null, 0, 15),
                  new Intro.Surface.Temp(4, Intro.Surface.java_logo, 2, 15),
                  new Intro.Surface.Temp(7, Intro.Surface.java_logo, 16, 130),
                  new Intro.Surface.Features(0, 16, 130)
                }
              },
              {
                {"Java2D  -  texture text on gradient", "1000"},
                {
                  new Intro.Surface.GpE(9, Intro.myBlue, Intro.myBlack, 0, 20),
                  new Intro.Surface.GpE(10, Intro.myBlue, Intro.myBlack, 21, 40),
                  new Intro.Surface.TpE(37, Intro.myBlack, Intro.myYellow, 4, 0, 10),
                  new Intro.Surface.TpE(38, Intro.myBlack, Intro.myYellow, 4, 11, 20),
                  new Intro.Surface.TpE(53, Intro.myBlack, Intro.myYellow, 5, 21, 40),
                  new Intro.Surface.TxE("Java2D", this.f2, 0, null, 0, 40)
                }
              },
              {{"Previous scene random close out", "0"}, {new Intro.Surface.CoE(16, 0, 20)}},
              {
                {"Text Features", "999"},
                {
                  new Intro.Surface.Temp(2, null, 0, 15),
                  new Intro.Surface.Temp(4, Intro.Surface.java_logo, 2, 15),
                  new Intro.Surface.Temp(7, Intro.Surface.java_logo, 16, 130),
                  new Intro.Surface.Features(1, 16, 130)
                }
              },
              {
                {"Java2D  -  composite text on texture", "1000"},
                {
                  new Intro.Surface.TpE(9, Intro.myBlack, this.gp, 40, 0, 20),
                  new Intro.Surface.TpE(10, Intro.myBlack, this.gp, 40, 21, 40),
                  new Intro.Surface.TpE(9, Intro.myBlack, this.gp, 40, 41, 60),
                  new Intro.Surface.TxE("Java2D", this.f2, 64, Intro.myYellow, 0, 60)
                }
              },
              {{"Previous scene dither dissolve out", "0"}, {new Intro.Surface.DdE(0, 20, 4)}},
              {
                {"Imaging Features", "999"},
                {
                  new Intro.Surface.Temp(2, null, 0, 15),
                  new Intro.Surface.Temp(4, Intro.Surface.java_logo, 2, 15),
                  new Intro.Surface.Temp(7, Intro.Surface.java_logo, 16, 130),
                  new Intro.Surface.Features(2, 16, 130)
                }
              },
              {
                {"Java2D  -  text on gradient", "1000"},
                {
                  new Intro.Surface.GpE(54, Intro.myBlue, Intro.myBlack, 0, 20),
                  new Intro.Surface.GpE(53, Intro.myBlue, Intro.myBlack, 21, 40),
                  new Intro.Surface.GpE(54, Intro.myBlue, Intro.myBlack, 41, 50),
                  new Intro.Surface.GpE(129, Intro.myRed, Intro.myYellow, 0, 50),
                  new Intro.Surface.TxE("Java2D", this.f2, 512, null, 0, 50)
                }
              },
              {{"Previous scene ellipse close out", "0"}, {new Intro.Surface.CoE(4, 0, 20)}},
              {
                {"Color Features", "999"},
                {
                  new Intro.Surface.Temp(2, null, 0, 15),
                  new Intro.Surface.Temp(4, Intro.Surface.java_logo, 2, 15),
                  new Intro.Surface.Temp(7, Intro.Surface.java_logo, 16, 99),
                  new Intro.Surface.Features(3, 16, 99)
                }
              },
              {
                {"Java2D  -  composite and rotate text on paints", "2000"},
                {
                  new Intro.Surface.GpE(69, Intro.myBlack, Intro.myBlue, 0, 20),
                  new Intro.Surface.GpE(70, Intro.myBlack, Intro.myBlue, 21, 30),
                  new Intro.Surface.TpE(21, Intro.myBlack, Intro.myBlue, 10, 31, 40),
                  new Intro.Surface.TxE("Java2D", this.f2, 69, Intro.myYellow, 0, 40)
                }
              },
              {
                {"Previous scene subimage transform out", "0"},
                {new Intro.Surface.SiE(60, 60, 0, 40)}
              },
              {
                {"CREDITS  -  transform in", "1000"},
                {
                  new Intro.Surface.LnE(45, 0, 60),
                  new Intro.Surface.TxE("CREDITS", this.f3, 73, Color.RED, 20, 30),
                  new Intro.Surface.TxE("CREDITS", this.f3, 26, Color.RED, 31, 38),
                  new Intro.Surface.TxE("CREDITS", this.f3, 25, Color.RED, 39, 48),
                  new Intro.Surface.TxE("CREDITS", this.f3, 26, Color.RED, 49, 54),
                  new Intro.Surface.TxE("CREDITS", this.f3, 25, Color.RED, 55, 60)
                }
              },
              {
                {"CREDITS  -  transform out", "0"},
                {
                  new Intro.Surface.LnE(46, 0, 45),
                  new Intro.Surface.TxE("CREDITS", this.f3, 0, Color.RED, 0, 9),
                  new Intro.Surface.TxE("CREDITS", this.f3, 14, Color.RED, 10, 30)
                }
              },
              {
                {"Contributors", "1000"},
                {
                  new Intro.Surface.Temp(2, null, 0, 30),
                  new Intro.Surface.Temp(4, Intro.Surface.cupanim, 4, 30),
                  new Intro.Surface.Temp(7, Intro.Surface.cupanim, 31, 200),
                  new Intro.Surface.Contributors(34, 200)
                }
              }
            };
        Object[][][] var1 = this.partsInfo;
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
          Object[][] var4 = var1[var3];
          ArrayList var5 = new ArrayList();
          Object[] var6 = var4[1];
          int var7 = var6.length;

          for (int var8 = 0; var8 < var7; ++var8) {
            Object var9 = var6[var8];
            var5.add(var9);
          }

          this.add(new Intro.Surface.Scene(var5, var4[0][0], var4[0][1]));
        }
      }
    }
  }

  static class ScenesTable extends JPanel implements ActionListener, ChangeListener {
    private JTable table;
    private TableModel dataModel;

    public ScenesTable() {
      this.setBackground(Color.WHITE);
      this.setLayout(new BorderLayout());
      final String[] var1 = new String[] {"", "Scenes", "Pause"};
      this.dataModel =
          new AbstractTableModel() {
            public int getColumnCount() {
              return var1.length;
            }

            public int getRowCount() {
              return Intro.surface.director.size();
            }

            public Object getValueAt(int var1x, int var2) {
              Intro.Surface.Scene var3 = Intro.surface.director.get(var1x);
              if (var2 == 0) {
                return var3.participate;
              } else {
                return var2 == 1 ? var3.name : var3.pauseAmt;
              }
            }

            public String getColumnName(int var1x) {
              return var1[var1x];
            }

            public Class<?> getColumnClass(int var1x) {
              return this.getValueAt(0, var1x).getClass();
            }

            public boolean isCellEditable(int var1x, int var2) {
              return var2 != 1;
            }

            public void setValueAt(Object var1x, int var2, int var3) {
              Intro.Surface.Scene var4 = Intro.surface.director.get(var2);
              if (var3 == 0) {
                var4.participate = var1x;
              } else if (var3 == 1) {
                var4.name = var1x;
              } else {
                var4.pauseAmt = var1x;
              }
            }
          };
      this.table = new JTable(this.dataModel);
      TableColumn var2 = this.table.getColumn("");
      var2.setWidth(16);
      var2.setMinWidth(16);
      var2.setMaxWidth(20);
      var2 = this.table.getColumn("Pause");
      var2.setWidth(60);
      var2.setMinWidth(60);
      var2.setMaxWidth(60);
      this.table.sizeColumnsToFit(0);
      JScrollPane var3 = new JScrollPane(this.table);
      this.add(var3);
      JPanel var4 = new JPanel(new BorderLayout());
      JButton var5 = new JButton("Unselect All");
      var5.setHorizontalAlignment(2);
      Font var6 = new Font("serif", 0, 10);
      var5.setFont(var6);
      var5.addActionListener(this);
      var4.add("West", var5);
      JSlider var7 = new JSlider(0, 0, 200, (int) Intro.surface.sleepAmt);
      var7.addChangeListener(this);
      TitledBorder var8 = new TitledBorder(new EtchedBorder());
      var8.setTitleFont(var6);
      var8.setTitle("Anim delay = " + Intro.surface.sleepAmt + " ms");
      var7.setBorder(var8);
      var7.setPreferredSize(new Dimension(140, 40));
      var7.setMinimumSize(new Dimension(100, 40));
      var7.setMaximumSize(new Dimension(180, 40));
      var4.add("East", var7);
      this.add("South", var4);
    }

    public void actionPerformed(ActionEvent var1) {
      JButton var2 = (JButton) var1.getSource();
      var2.setSelected(!var2.isSelected());
      var2.setText(var2.isSelected() ? "Select All" : "Unselect All");

      for (int var3 = 0; var3 < Intro.surface.director.size(); ++var3) {
        Intro.Surface.Scene var4 = Intro.surface.director.get(var3);
        var4.participate = !var2.isSelected();
      }

      this.table.tableChanged(new TableModelEvent(this.dataModel));
    }

    public void stateChanged(ChangeEvent var1) {
      JSlider var2 = (JSlider) var1.getSource();
      int var3 = var2.getValue();
      TitledBorder var4 = (TitledBorder) var2.getBorder();
      var4.setTitle("Anim delay = " + var3 + " ms");
      Intro.surface.sleepAmt = var3;
      var2.repaint();
    }
  }
}
