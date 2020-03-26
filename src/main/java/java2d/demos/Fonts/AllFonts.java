//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Fonts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java2d.AnimatingControlsSurface;
import java2d.CustomControls;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AllFonts extends AnimatingControlsSurface {
  private static final List<Font> fonts = new ArrayList();

  static {
    GraphicsEnvironment var0 = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Font[] var1 = var0.getAllFonts();
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      Font var4 = var1[var3];
      if (var4.canDisplayUpTo(var4.getName()) != 0) {
        fonts.add(var4);
      }
    }
  }

  protected int fsize = 14;
  protected List<Font> v = new ArrayList();
  private int nStrs;
  private int strH;
  private int fi;

  public AllFonts() {
    this.setBackground(Color.WHITE);
    this.setSleepAmount(500L);
    this.setControls(new Component[] {new AllFonts.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new AllFonts());
  }

  public void handleThread(int var1) {}

  public void reset(int var1, int var2) {
    this.v.clear();
    Font var3 = fonts.get(0).deriveFont(0, (float) this.fsize);
    FontMetrics var4 = this.getFontMetrics(var3);
    this.strH = var4.getAscent() + var4.getDescent();
    this.nStrs = var2 / this.strH + 1;
    this.fi = 0;
  }

  public void step(int var1, int var2) {
    if (this.fi < fonts.size()) {
      this.v.add(fonts.get(this.fi).deriveFont(0, (float) this.fsize));
    }

    if (this.v.size() == this.nStrs && !this.v.isEmpty() || this.fi > fonts.size()) {
      this.v.remove(0);
    }

    this.fi = this.v.isEmpty() ? 0 : ++this.fi;
  }

  public void render(int var1, int var2, Graphics2D var3) {
    var3.setColor(Color.BLACK);
    int var4 = this.fi >= fonts.size() ? 0 : var2 - this.v.size() * this.strH - this.strH / 2;

    for (int var5 = 0; var5 < this.v.size(); ++var5) {
      Font var6 = this.v.get(var5);
      int var7 = this.getFontMetrics(var6).stringWidth(var6.getName());
      var3.setFont(var6);
      var3.drawString(var6.getName(), var1 / 2 - var7 / 2, var4 += this.strH);
    }
  }

  static class DemoControls extends CustomControls implements ActionListener, ChangeListener {
    AllFonts demo;
    JSlider slider;
    int[] fsize = new int[] {8, 14, 18, 24};
    JMenuItem[] menuitem;
    Font[] font;

    public DemoControls(AllFonts var1) {
      this.menuitem = new JMenuItem[this.fsize.length];
      this.font = new Font[this.fsize.length];
      this.demo = var1;
      this.setBackground(Color.GRAY);
      int var2 = (int) var1.getSleepAmount();
      this.slider = new JSlider(0, 0, 999, var2);
      this.slider.setBorder(new EtchedBorder());
      this.slider.setPreferredSize(new Dimension(90, 22));
      this.slider.addChangeListener(this);
      this.add(this.slider);
      JMenuBar var3 = new JMenuBar();
      this.add(var3);
      JMenu var4 = var3.add(new JMenu("Font Size"));

      for (int var5 = 0; var5 < this.fsize.length; ++var5) {
        this.font[var5] = new Font("serif", 0, this.fsize[var5]);
        this.menuitem[var5] = var4.add(new JMenuItem(String.valueOf(this.fsize[var5])));
        this.menuitem[var5].setFont(this.font[var5]);
        this.menuitem[var5].addActionListener(this);
      }
    }

    public void actionPerformed(ActionEvent var1) {
      for (int var2 = 0; var2 < this.fsize.length; ++var2) {
        if (var1.getSource().equals(this.menuitem[var2])) {
          this.demo.fsize = this.fsize[var2];
          Dimension var3 = this.demo.getSize();
          this.demo.reset(var3.width, var3.height);
          break;
        }
      }
    }

    public void stateChanged(ChangeEvent var1) {
      this.demo.setSleepAmount(this.slider.getValue());
    }
  }
}
