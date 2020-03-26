//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Paint;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.plaf.metal.MetalBorders.ButtonBorder;

public final class TextureAnim extends AnimatingControlsSurface {
  public static final Color colorblend = new Color(0.0F, 0.0F, 1.0F, 0.5F);
  protected static BufferedImage textureImg;
  private static Image[] img = new Image[2];
  protected int bNum;
  protected int tilesize;
  private boolean newtexture;
  private TexturePaint texturePaint;
  private Rectangle tilerect;
  private boolean bouncesize = false;
  private boolean bouncerect = true;
  private boolean rotate = false;
  private boolean shearx = false;
  private boolean sheary = false;
  private boolean showanchor = true;
  private TextureAnim.AnimVal w;
  private TextureAnim.AnimVal h;
  private TextureAnim.AnimVal x;
  private TextureAnim.AnimVal y;
  private TextureAnim.AnimVal rot;
  private TextureAnim.AnimVal shx;
  private TextureAnim.AnimVal shy;

  public TextureAnim() {
    img[0] = this.getImage("duke.gif");
    img[1] = this.getImage("duke.png");
    textureImg = this.makeImage(32, 0);
    this.tilesize = textureImg.getWidth();
    this.w = new TextureAnim.AnimVal(0, 200, 3, 10, this.tilesize);
    this.h = new TextureAnim.AnimVal(0, 200, 3, 10, this.tilesize);
    this.x = new TextureAnim.AnimVal(0, 200, 3, 10, 0);
    this.y = new TextureAnim.AnimVal(0, 200, 3, 10, 0);
    this.rot = new TextureAnim.AnimVal(-360, 360, 5, 15, 0);
    this.shx = new TextureAnim.AnimVal(-50, 50, 3, 10, 0);
    this.shy = new TextureAnim.AnimVal(-50, 50, 3, 10, 0);
    this.tilerect =
        new Rectangle(this.x.getInt(), this.y.getInt(), this.w.getInt(), this.h.getInt());
    this.texturePaint = new TexturePaint(textureImg, this.tilerect);
    this.setControls(new Component[] {new TextureAnim.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new TextureAnim());
  }

  protected BufferedImage makeImage(int var1, int var2) {
    this.newtexture = true;
    switch (this.bNum = var2) {
      case 0:
        return this.makeRGBImage(var1);
      case 1:
        return this.makeGIFImage(var1);
      case 2:
        return this.makePNGImage(var1);
      default:
        return null;
    }
  }

  private BufferedImage makeRGBImage(int var1) {
    BufferedImage var2 = new BufferedImage(var1, var1, 1);
    Graphics2D var3 = var2.createGraphics();
    var3.setColor(Color.WHITE);
    var3.fillRect(0, 0, var1, var1);

    for (int var4 = 0; var4 < var1; ++var4) {
      float var5 = (float) var4 / (float) var1;

      for (int var6 = 0; var6 < var1; ++var6) {
        float var7 = (float) var6 / (float) var1;
        var3.setColor(new Color(1.0F - var5, 1.0F - var7, 0.0F, 1.0F));
        var3.drawLine(var6, var4, var6, var4);
      }
    }

    return var2;
  }

  private BufferedImage makeGIFImage(int var1) {
    BufferedImage var2 = new BufferedImage(var1, var1, 1);
    Graphics2D var3 = var2.createGraphics();
    var3.drawImage(img[0], 0, 0, var1, var1, new Color(204, 204, 255), null);
    return var2;
  }

  private BufferedImage makePNGImage(int var1) {
    BufferedImage var2 = new BufferedImage(var1, var1, 1);
    Graphics2D var3 = var2.createGraphics();
    var3.drawImage(img[1], 0, 0, var1, var1, Color.LIGHT_GRAY, null);
    return var2;
  }

  public void reset(int var1, int var2) {
    this.x.newlimits(-var1 / 4, var1 / 4 - this.w.getInt());
    this.y.newlimits(-var2 / 4, var2 / 4 - this.h.getInt());
  }

  public void step(int var1, int var2) {
    if (this.tilesize != textureImg.getWidth()) {
      this.tilesize = textureImg.getWidth();
    }

    if (this.bouncesize) {
      this.w.anim();
      this.h.anim();
      this.x.newlimits(-var1 / 4, var1 / 4 - this.w.getInt());
      this.y.newlimits(-var2 / 4, var2 / 4 - this.h.getInt());
    } else {
      if (this.w.getInt() != this.tilesize) {
        this.w.set((float) this.tilesize);
        this.x.newlimits(-var1 / 4, var1 / 4 - this.w.getInt());
      }

      if (this.h.getInt() != this.tilesize) {
        this.h.set((float) this.tilesize);
        this.y.newlimits(-var2 / 4, var2 / 4 - this.h.getInt());
      }
    }

    if (this.bouncerect) {
      this.x.anim();
      this.y.anim();
    }

    if (this.newtexture
        || this.x.getInt() != this.tilerect.x
        || this.y.getInt() != this.tilerect.y
        || this.w.getInt() != this.tilerect.width
        || this.h.getInt() != this.tilerect.height) {
      this.newtexture = false;
      int var3 = this.x.getInt();
      int var4 = this.y.getInt();
      int var5 = this.w.getInt();
      int var6 = this.h.getInt();
      this.tilerect = new Rectangle(var3, var4, var5, var6);
      this.texturePaint = new TexturePaint(textureImg, this.tilerect);
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    var3.translate(var1 / 2, var2 / 2);
    if (this.rotate) {
      this.rot.anim();
      var3.rotate(Math.toRadians(this.rot.getFlt()));
    } else {
      this.rot.set(0.0F);
    }

    if (this.shearx) {
      this.shx.anim();
      var3.shear(this.shx.getFlt() / 100.0F, 0.0D);
    } else {
      this.shx.set(0.0F);
    }

    if (this.sheary) {
      this.shy.anim();
      var3.shear(0.0D, this.shy.getFlt() / 100.0F);
    } else {
      this.shy.set(0.0F);
    }

    var3.setPaint(this.texturePaint);
    var3.fillRect(-1000, -1000, 2000, 2000);
    if (this.showanchor) {
      var3.setColor(Color.BLACK);
      var3.setColor(colorblend);
      var3.fill(this.tilerect);
    }
  }

  static final class AnimVal {
    float curval;
    float lowval;
    float highval;
    float currate;
    float lowrate;
    float highrate;

    public AnimVal(int var1, int var2, int var3, int var4) {
      this.lowval = (float) var1;
      this.highval = (float) var2;
      this.lowrate = (float) var3;
      this.highrate = (float) var4;
      this.curval = this.randval((float) var1, (float) var2);
      this.currate = this.randval((float) var3, (float) var4);
    }

    public AnimVal(int var1, int var2, int var3, int var4, int var5) {
      this(var1, var2, var3, var4);
      this.set((float) var5);
    }

    public float randval(float var1, float var2) {
      return (float) ((double) var1 + Math.random() * (double) (var2 - var1));
    }

    public float getFlt() {
      return this.curval;
    }

    public int getInt() {
      return (int) this.curval;
    }

    public void anim() {
      this.curval += this.currate;
      this.clip();
    }

    public void set(float var1) {
      this.curval = var1;
      this.clip();
    }

    public void clip() {
      if (this.curval > this.highval) {
        this.curval = this.highval - (this.curval - this.highval);
        if (this.curval < this.lowval) {
          this.curval = this.highval;
        }

        this.currate = -this.randval(this.lowrate, this.highrate);
      } else if (this.curval < this.lowval) {
        this.curval = this.lowval + (this.lowval - this.curval);
        if (this.curval > this.highval) {
          this.curval = this.lowval;
        }

        this.currate = this.randval(this.lowrate, this.highrate);
      }
    }

    public void newlimits(int var1, int var2) {
      this.lowval = (float) var1;
      this.highval = (float) var2;
      this.clip();
    }
  }

  final class DemoControls extends CustomControls implements ActionListener {
    TextureAnim demo;
    JToolBar toolbar;
    JComboBox combo;
    JMenu menu;
    JMenuItem[] menuitems;
    int iconSize = 20;
    ButtonBorder buttonBorder = new ButtonBorder();

    public DemoControls(TextureAnim var2) {
      super(var2.name);
      this.demo = var2;
      this.menuitems = new JMenuItem[3];
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("BO", "bounce", true);
      this.addTool("SA", "show anchor", true);
      this.addTool("RS", "resize", false);
      this.addTool("RO", "rotate", false);
      this.addTool("SX", "shear x", false);
      this.addTool("SY", "shear y", false);
      this.add(this.combo = new JComboBox());
      this.combo.addActionListener(this);
      this.combo.addItem("8");
      this.combo.addItem("16");
      this.combo.addItem("32");
      this.combo.addItem("64");
      this.combo.addItem("80");
      this.combo.setSelectedIndex(2);
      JMenuBar var3 = new JMenuBar();
      this.menu = var3.add(new JMenu());

      for (int var4 = 0; var4 < 3; ++var4) {
        BufferedImage var5 = var2.makeImage(this.iconSize, var4);
        TextureAnim.DemoControls.TexturedIcon var6 =
            new TextureAnim.DemoControls.TexturedIcon(var5);
        this.menuitems[var4] = this.menu.add(new JMenuItem(var6));
        this.menuitems[var4].addActionListener(this);
      }

      this.menu.setIcon(this.menuitems[0].getIcon());
      this.add(var3);
      var2.bNum = 0;
    }

    public void addTool(String var1, String var2, boolean var3) {
      JToggleButton var4 = (JToggleButton) this.toolbar.add(new JToggleButton(var1));
      var4.setBorder(this.buttonBorder);
      var4.setFocusPainted(false);
      var4.setSelected(var3);
      var4.setToolTipText(var2);
      var4.addActionListener(this);
      int var5 = var4.getPreferredSize().width;
      Dimension var6 = new Dimension(var5, 21);
      var4.setPreferredSize(var6);
      var4.setMaximumSize(var6);
      var4.setMinimumSize(var6);
    }

    public void actionPerformed(ActionEvent var1) {
      Object var2 = var1.getSource();
      if (var2 instanceof JComboBox) {
        String var3 = (String) this.combo.getSelectedItem();
        if (var3 != null) {
          int var4 = Integer.parseInt(var3);
          TextureAnim.textureImg = this.demo.makeImage(var4, this.demo.bNum);
        }
      } else if (var2 instanceof JMenuItem) {
        for (int var5 = 0; var5 < this.menuitems.length; ++var5) {
          if (var2.equals(this.menuitems[var5])) {
            TextureAnim.textureImg = this.demo.makeImage(this.demo.tilesize, var5);
            this.menu.setIcon(this.menuitems[var5].getIcon());
            break;
          }
        }
      } else {
        JToggleButton var6 = (JToggleButton) var2;
        if (var6.getText().equals("BO")) {
          this.demo.bouncerect = var6.isSelected();
        } else if (var6.getText().equals("SA")) {
          this.demo.showanchor = var6.isSelected();
        } else if (var6.getText().equals("RS")) {
          this.demo.bouncesize = var6.isSelected();
        } else if (var6.getText().equals("RO")) {
          this.demo.rotate = var6.isSelected();
        } else if (var6.getText().equals("SX")) {
          this.demo.shearx = var6.isSelected();
        } else if (var6.getText().equals("SY")) {
          this.demo.sheary = var6.isSelected();
        }
      }

      if (!this.demo.animating.running()) {
        this.demo.repaint();
      }
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 41);
    }

    public void run() {
      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 2; var2 < this.toolbar.getComponentCount(); ++var2) {
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

    class TexturedIcon implements Icon {
      BufferedImage bi;

      public TexturedIcon(BufferedImage var2) {
        this.bi = var2;
      }

      public void paintIcon(Component var1, Graphics var2, int var3, int var4) {
        Graphics2D var5 = (Graphics2D) var2;
        Rectangle var6 =
            new Rectangle(var3, var4, DemoControls.this.iconSize, DemoControls.this.iconSize);
        var5.setPaint(new TexturePaint(this.bi, var6));
        var5.fillRect(var3, var4, DemoControls.this.iconSize, DemoControls.this.iconSize);
        var5.setColor(Color.GRAY);
        var5.draw3DRect(
            var3, var4, DemoControls.this.iconSize - 1, DemoControls.this.iconSize - 1, true);
      }

      public int getIconWidth() {
        return DemoControls.this.iconSize;
      }

      public int getIconHeight() {
        return DemoControls.this.iconSize;
      }
    }
  }
}
