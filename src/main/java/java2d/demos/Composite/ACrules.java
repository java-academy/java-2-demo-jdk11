//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Composite;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextLayout;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java2d.AnimatingSurface;

public class ACrules extends AnimatingSurface {
  private static final AlphaComposite[] compObjs;
  private static final int NUM_RULES;
  private static final int HALF_NUM_RULES;
  private static String[] compNames =
      new String[] {
        "Src", "SrcOver", "SrcIn", "SrcOut", "SrcAtop", "Clear", "Dst", "DstOver", "DstIn",
        "DstOut", "DstAtop", "Xor"
      };
  private static float[][] fadeValues;
  private static String[] fadeNames;
  private static Font f;

  static {
    compObjs =
        new AlphaComposite[] {
          AlphaComposite.Src,
          AlphaComposite.SrcOver,
          AlphaComposite.SrcIn,
          AlphaComposite.SrcOut,
          AlphaComposite.SrcAtop,
          AlphaComposite.Clear,
          AlphaComposite.Dst,
          AlphaComposite.DstOver,
          AlphaComposite.DstIn,
          AlphaComposite.DstOut,
          AlphaComposite.DstAtop,
          AlphaComposite.Xor
        };
    NUM_RULES = compObjs.length;
    HALF_NUM_RULES = NUM_RULES / 2;
    fadeValues =
        new float[][] {
          {1.0F, -0.1F, 0.0F, 1.0F, 0.0F, 1.0F},
          {0.0F, 0.1F, 1.0F, 1.0F, -0.1F, 0.0F},
          {1.0F, 0.0F, 1.0F, 0.0F, 0.1F, 1.0F}
        };
    fadeNames =
        new String[] {
          "Src => transparent, Dest opaque",
          "Src => opaque, Dest => transparent",
          "Src opaque, Dest => opaque"
        };
    f = new Font("serif", 0, 10);
  }

  private int fadeIndex;
  private float srca;
  private float dsta;
  private String fadeLabel;
  private BufferedImage statBI;
  private BufferedImage animBI;
  private int PADLEFT;
  private int PADRIGHT;
  private int HPAD;
  private int PADABOVE;
  private int PADBELOW;
  private int VPAD;
  private int RECTWIDTH;
  private int RECTHEIGHT;
  private int PADDEDHEIGHT;
  private GeneralPath srcpath;
  private GeneralPath dstpath;
  private LineMetrics lm;
  private BufferedImage dBI;
  private BufferedImage sBI;
  private GradientPaint gradientDst;
  private GradientPaint gradientSrc;

  public ACrules() {
    this.srca = fadeValues[this.fadeIndex][0];
    this.dsta = fadeValues[this.fadeIndex][3];
    this.fadeLabel = fadeNames[0];
    this.srcpath = new GeneralPath();
    this.dstpath = new GeneralPath();
    this.setBackground(Color.white);
  }

  public static void main(String[] var0) {
    createDemoFrame(new ACrules());
  }

  public void reset(int var1, int var2) {
    this.setSleepAmount(400L);
    FontRenderContext var3 = new FontRenderContext(null, false, false);
    this.lm = f.getLineMetrics(compNames[0], var3);
    this.PADLEFT = var1 < 150 ? 10 : 15;
    this.PADRIGHT = var1 < 150 ? 10 : 15;
    this.HPAD = this.PADLEFT + this.PADRIGHT;
    this.PADBELOW = var2 < 250 ? 1 : 2;
    this.PADABOVE = this.PADBELOW + (int) this.lm.getHeight();
    this.VPAD = this.PADABOVE + this.PADBELOW;
    this.RECTWIDTH = var1 / 4 - this.HPAD;
    this.RECTWIDTH = this.RECTWIDTH < 6 ? 6 : this.RECTWIDTH;
    this.RECTHEIGHT = (var2 - this.VPAD) / HALF_NUM_RULES - this.VPAD;
    this.RECTHEIGHT = this.RECTHEIGHT < 6 ? 6 : this.RECTHEIGHT;
    this.PADDEDHEIGHT = this.RECTHEIGHT + this.VPAD;
    this.srcpath.reset();
    this.srcpath.moveTo(0.0F, 0.0F);
    this.srcpath.lineTo((float) this.RECTWIDTH, 0.0F);
    this.srcpath.lineTo(0.0F, (float) this.RECTHEIGHT);
    this.srcpath.closePath();
    this.dstpath.reset();
    this.dstpath.moveTo(0.0F, 0.0F);
    this.dstpath.lineTo((float) this.RECTWIDTH, (float) this.RECTHEIGHT);
    this.dstpath.lineTo((float) this.RECTWIDTH, 0.0F);
    this.dstpath.closePath();
    this.dBI = new BufferedImage(this.RECTWIDTH, this.RECTHEIGHT, 2);
    this.sBI = new BufferedImage(this.RECTWIDTH, this.RECTHEIGHT, 2);
    this.gradientDst =
        new GradientPaint(
            0.0F,
            0.0F,
            new Color(1.0F, 0.0F, 0.0F, 1.0F),
            0.0F,
            (float) this.RECTHEIGHT,
            new Color(1.0F, 0.0F, 0.0F, 0.0F));
    this.gradientSrc =
        new GradientPaint(
            0.0F,
            0.0F,
            new Color(0.0F, 0.0F, 1.0F, 1.0F),
            (float) this.RECTWIDTH,
            0.0F,
            new Color(0.0F, 0.0F, 1.0F, 0.0F));
    this.statBI = new BufferedImage(var1 / 2, var2, 1);
    this.statBI = this.drawCompBI(this.statBI, true);
    this.animBI = new BufferedImage(var1 / 2, var2, 1);
  }

  public void step(int var1, int var2) {
    if (this.getSleepAmount() == 5000L) {
      this.setSleepAmount(200L);
    }

    this.srca += fadeValues[this.fadeIndex][1];
    this.dsta += fadeValues[this.fadeIndex][4];
    this.fadeLabel = fadeNames[this.fadeIndex];
    if (this.srca < 0.0F
        || (double) this.srca > 1.0D
        || this.dsta < 0.0F
        || (double) this.dsta > 1.0D) {
      this.setSleepAmount(5000L);
      this.srca = fadeValues[this.fadeIndex][2];
      this.dsta = fadeValues[this.fadeIndex][5];
      if (this.fadeIndex++ == fadeValues.length - 1) {
        this.fadeIndex = 0;
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    if (this.statBI != null && this.animBI != null) {
      var3.drawImage(this.statBI, 0, 0, null);
      var3.drawImage(this.drawCompBI(this.animBI, false), var1 / 2, 0, null);
      var3.setColor(Color.black);
      FontRenderContext var4 = var3.getFontRenderContext();
      TextLayout var5 = new TextLayout("AC Rules", var3.getFont(), var4);
      var5.draw(var3, 15.0F, (float) var5.getBounds().getHeight() + 3.0F);
      var5 = new TextLayout(this.fadeLabel, f, var4);
      float var6 = (float) ((double) var1 * 0.75D - var5.getBounds().getWidth() / 2.0D);
      if ((double) var6 + var5.getBounds().getWidth() > (double) var1) {
        var6 = (float) ((double) var1 - var5.getBounds().getWidth());
      }

      var5.draw(var3, var6, (float) var5.getBounds().getHeight() + 3.0F);
    }
  }

  private BufferedImage drawCompBI(BufferedImage var1, boolean var2) {
    Graphics2D var3 = var1.createGraphics();
    var3.setColor(this.getBackground());
    var3.fillRect(0, 0, var1.getWidth(), var1.getHeight());
    var3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.AntiAlias);
    var3.setFont(f);
    Graphics2D var4 = this.dBI.createGraphics();
    var4.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.AntiAlias);
    Graphics2D var5 = this.sBI.createGraphics();
    var5.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.AntiAlias);
    boolean var6 = false;
    int var7 = 0;
    int var8 = (int) this.lm.getHeight() + this.VPAD;

    for (int var9 = 0; var9 < compNames.length; ++var9) {
      var7 = var9 != 0 && var9 != HALF_NUM_RULES ? var7 + this.PADDEDHEIGHT : var8;
      int var10 = var9 >= HALF_NUM_RULES ? var1.getWidth() / 2 + this.PADLEFT : this.PADLEFT;
      var3.translate(var10, var7);
      var4.setComposite(AlphaComposite.Clear);
      var4.fillRect(0, 0, this.RECTWIDTH, this.RECTHEIGHT);
      var4.setComposite(AlphaComposite.Src);
      if (var2) {
        var4.setPaint(this.gradientDst);
        var4.fillRect(0, 0, this.RECTWIDTH, this.RECTHEIGHT);
      } else {
        var4.setPaint(new Color(1.0F, 0.0F, 0.0F, this.dsta));
        var4.fill(this.dstpath);
      }

      var5.setComposite(AlphaComposite.Clear);
      var5.fillRect(0, 0, this.RECTWIDTH, this.RECTHEIGHT);
      var5.setComposite(AlphaComposite.Src);
      if (var2) {
        var5.setPaint(this.gradientSrc);
        var5.fillRect(0, 0, this.RECTWIDTH, this.RECTHEIGHT);
      } else {
        var5.setPaint(new Color(0.0F, 0.0F, 1.0F, this.srca));
        var5.fill(this.srcpath);
      }

      var4.setComposite(compObjs[var9]);
      var4.drawImage(this.sBI, 0, 0, null);
      var3.drawImage(this.dBI, 0, 0, null);
      var3.setColor(Color.black);
      var3.drawString(compNames[var9], 0.0F, -this.lm.getDescent());
      var3.drawRect(0, 0, this.RECTWIDTH, this.RECTHEIGHT);
      var3.translate(-var10, -var7);
    }

    var4.dispose();
    var5.dispose();
    var3.dispose();
    return var1;
  }
}
