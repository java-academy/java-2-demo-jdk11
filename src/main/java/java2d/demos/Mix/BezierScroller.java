//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Mix;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class BezierScroller extends AnimatingControlsSurface {
  private static final int NUMPTS = 6;
  private static final int UP = 0;
  private static final int DOWN = 1;
  private static String[] appletStrs =
      new String[] {
        " ",
        "Java2Demo",
        "BezierScroller - Animated Bezier Curve shape with images",
        "For README.txt file scrolling run in application mode",
        " "
      };
  private static Color greenBlend = new Color(0, 255, 0, 100);
  private static Color blueBlend = new Color(0, 0, 255, 100);
  private static Font font = new Font("serif", 0, 12);
  private static BasicStroke bs = new BasicStroke(3.0F);
  private static Image hotj_img;
  private static BufferedImage img;
  protected boolean doImage;
  protected boolean doShape;
  protected boolean doText;
  protected boolean buttonToggle;
  private float[] animpts = new float[12];
  private float[] deltas = new float[12];
  private BufferedReader reader;
  private int nStrs;
  private int strH;
  private int yy;
  private int ix;
  private int iy;
  private int imgX;
  private List<String> vector;
  private List<String> appletVector;
  private float alpha = 0.2F;
  private int alphaDirection;

  public BezierScroller() {
    this.setBackground(Color.WHITE);
    this.doShape = this.doText = true;
    hotj_img = this.getImage("java-logo.gif");
    Image var1 = this.getImage("jumptojavastrip.png");
    int var2 = var1.getWidth(this);
    int var3 = var1.getHeight(this);
    img = new BufferedImage(var2, var3, 1);
    img.createGraphics().drawImage(var1, 0, 0, this);
    this.setControls(new Component[] {new BezierScroller.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new BezierScroller());
  }

  public void animate(float[] var1, float[] var2, int var3, int var4) {
    float var5 = var1[var3] + var2[var3];
    if (var5 <= 0.0F) {
      var5 = -var5;
      var2[var3] = (float) (Math.random() * 4.0D + 2.0D);
    } else if (var5 >= (float) var4) {
      var5 = 2.0F * (float) var4 - var5;
      var2[var3] = -((float) (Math.random() * 4.0D + 2.0D));
    }

    var1[var3] = var5;
  }

  public void getFile() {
    try {
      String var1 = "README.txt";
      if ((this.reader = new BufferedReader(new FileReader(var1))) != null) {
        this.getLine();
      }
    } catch (Exception var2) {
      this.reader = null;
    }

    if (this.reader == null) {
      this.appletVector = new ArrayList(100);

      for (int var3 = 0; var3 < 100; ++var3) {
        this.appletVector.add(appletStrs[var3 % appletStrs.length]);
      }

      this.getLine();
    }

    this.buttonToggle = true;
  }

  public String getLine() {
    String var1 = null;
    if (this.reader != null) {
      try {
        if ((var1 = this.reader.readLine()) != null) {
          if (var1.length() == 0) {
            var1 = " ";
          }

          this.vector.add(var1);
        }
      } catch (Exception var3) {
        Logger.getLogger(BezierScroller.class.getName()).log(Level.SEVERE, null, var3);
        this.reader = null;
      }
    } else if (!this.appletVector.isEmpty()) {
      this.vector.add(var1 = this.appletVector.remove(0));
    }

    return var1;
  }

  public void reset(int var1, int var2) {
    for (int var3 = 0; var3 < this.animpts.length; var3 += 2) {
      this.animpts[var3 + 0] = (float) (Math.random() * (double) var1);
      this.animpts[var3 + 1] = (float) (Math.random() * (double) var2);
      this.deltas[var3 + 0] = (float) (Math.random() * 6.0D + 4.0D);
      this.deltas[var3 + 1] = (float) (Math.random() * 6.0D + 4.0D);
      if (this.animpts[var3 + 0] > (float) var1 / 2.0F) {
        this.deltas[var3 + 0] = -this.deltas[var3 + 0];
      }

      if (this.animpts[var3 + 1] > (float) var2 / 2.0F) {
        this.deltas[var3 + 1] = -this.deltas[var3 + 1];
      }
    }

    FontMetrics var4 = this.getFontMetrics(font);
    this.strH = var4.getAscent() + var4.getDescent();
    this.nStrs = var2 / this.strH + 2;
    this.vector = new ArrayList(this.nStrs);
    this.ix = (int) (Math.random() * (double) (var1 - 80));
    this.iy = (int) (Math.random() * (double) (var2 - 80));
  }

  public void step(int var1, int var2) {
    if (this.doText && this.vector.isEmpty()) {
      this.getFile();
    }

    if (this.doText) {
      String var3 = this.getLine();
      if (var3 == null || this.vector.size() == this.nStrs && !this.vector.isEmpty()) {
        this.vector.remove(0);
      }

      this.yy = var3 == null ? 0 : var2 - this.vector.size() * this.strH;
    }

    for (int var4 = 0; var4 < this.animpts.length && this.doShape; var4 += 2) {
      this.animate(this.animpts, this.deltas, var4 + 0, var1);
      this.animate(this.animpts, this.deltas, var4 + 1, var2);
    }

    if (this.doImage && this.alphaDirection == 0) {
      if ((double) (this.alpha = (float) ((double) this.alpha + 0.025D)) > 0.99D) {
        this.alphaDirection = 1;
        this.alpha = 1.0F;
      }
    } else if (this.doImage
        && this.alphaDirection == 1
        && (double) (this.alpha = (float) ((double) this.alpha - 0.02D)) < 0.01D) {
      this.alphaDirection = 0;
      this.alpha = 0.0F;
      this.ix = (int) (Math.random() * (double) (var1 - 80));
      this.iy = (int) (Math.random() * (double) (var2 - 80));
    }

    if (this.doImage && (this.imgX += 80) == 800) {
      this.imgX = 0;
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    if (this.doText) {
      var3.setColor(Color.LIGHT_GRAY);
      var3.setFont(font);
      float var4 = (float) this.yy;
      Iterator var5 = this.vector.iterator();

      while (var5.hasNext()) {
        String var6 = (String) var5.next();
        var3.drawString(var6, 1.0F, var4 += (float) this.strH);
      }
    }

    if (this.doShape) {
      float[] var18 = this.animpts;
      int var20 = var18.length;
      float var21 = var18[var20 - 2];
      float var7 = var18[var20 - 1];
      float var8 = var18[0];
      float var9 = var18[1];
      float var10 = (var8 + var21) / 2.0F;
      float var11 = (var9 + var7) / 2.0F;
      GeneralPath var12 = new GeneralPath(1);
      var12.moveTo(var10, var11);

      for (int var13 = 2; var13 <= var18.length; var13 += 2) {
        float var14 = (var10 + var8) / 2.0F;
        float var15 = (var11 + var9) / 2.0F;
        var21 = var8;
        var7 = var9;
        if (var13 < var18.length) {
          var8 = var18[var13 + 0];
          var9 = var18[var13 + 1];
        } else {
          var8 = var18[0];
          var9 = var18[1];
        }

        var10 = (var8 + var21) / 2.0F;
        var11 = (var9 + var7) / 2.0F;
        float var16 = (var21 + var10) / 2.0F;
        float var17 = (var7 + var11) / 2.0F;
        var12.curveTo(var14, var15, var16, var17, var10, var11);
      }

      var12.closePath();
      var3.setColor(blueBlend);
      var3.setStroke(bs);
      var3.draw(var12);
      var3.setColor(greenBlend);
      var3.fill(var12);
      PathIterator var22 = var12.getPathIterator(null);

      for (float[] var23 = new float[6]; !var22.isDone(); var22.next()) {
        if (var22.currentSegment(var23) == 3) {
          var3.drawImage(hotj_img, (int) var23[0], (int) var23[1], this);
        }
      }
    }

    if (this.doImage) {
      AlphaComposite var19 = AlphaComposite.getInstance(3, this.alpha);
      var3.setComposite(var19);
      var3.drawImage(img.getSubimage(this.imgX, 0, 80, 80), this.ix, this.iy, this);
    }
  }

  static final class DemoControls extends CustomControls implements ActionListener {
    BezierScroller demo;
    JToolBar toolbar;
    JComboBox combo;

    public DemoControls(BezierScroller var1) {
      super(var1.name);
      this.demo = var1;
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("Image", false);
      this.addTool("Shape", true);
      this.addTool("Text", true);
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
      if (var2.getText().equals("Image")) {
        this.demo.doImage = var2.isSelected();
      } else if (var2.getText().equals("Shape")) {
        this.demo.doShape = var2.isSelected();
      } else {
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
      int var2 = 0;

      while (this.thread == var1) {
        try {
          Thread.sleep(250L);
        } catch (InterruptedException var4) {
          return;
        }

        if (this.demo.buttonToggle) {
          ((AbstractButton) this.toolbar.getComponentAtIndex(var2++ % 2)).doClick();
          this.demo.buttonToggle = false;
        }
      }

      this.thread = null;
    }
  }
}
