//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Lines;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java2d.ControlsSurface;
import java2d.CustomControls;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Joins extends ControlsSurface {
  protected int joinType = 0;
  protected float bswidth = 20.0F;
  protected JSlider slider;
  protected JLabel label;

  public Joins() {
    this.setBackground(Color.WHITE);
    this.slider = new JSlider(1, 0, 100, (int) (this.bswidth * 2.0F));
    this.slider.setPreferredSize(new Dimension(15, 100));
    this.slider.addChangeListener(
        new ChangeListener() {
          public void stateChanged(ChangeEvent var1) {
            if (Joins.this.getImageType() <= 1) {
              Joins.this.setImageType(2);
            }

            Joins.this.bswidth = (float) Joins.this.slider.getValue() / 2.0F;
            Joins.this.label.setText(" Width = " + Joins.this.bswidth);
            Joins.this.label.repaint();
            Joins.this.repaint();
          }
        });
    this.setControls(new Component[] {new Joins.DemoControls(this), this.slider});
    this.setConstraints(new String[] {"North", "West"});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Joins());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    BasicStroke var4 = new BasicStroke(this.bswidth, 0, this.joinType);
    GeneralPath var5 = new GeneralPath();
    var5.moveTo((float) (-var1) / 4.0F, (float) (-var2) / 12.0F);
    var5.lineTo((float) var1 / 4.0F, (float) (-var2) / 12.0F);
    var5.lineTo((float) (-var1) / 6.0F, (float) var2 / 4.0F);
    var5.lineTo(0.0F, (float) (-var2) / 4.0F);
    var5.lineTo((float) var1 / 6.0F, (float) var2 / 4.0F);
    var5.closePath();
    var5.closePath();
    var3.translate(var1 / 2, var2 / 2);
    var3.setColor(Color.BLACK);
    var3.draw(var4.createStrokedShape(var5));
  }

  class DemoControls extends CustomControls implements ActionListener {
    Joins demo;
    int[] joinType = new int[] {0, 1, 2};
    String[] joinName = new String[] {"Mitered Join", "Rounded Join", "Beveled Join"};
    JMenu menu;
    JMenuItem[] menuitem;
    Joins.DemoControls.JoinIcon[] icons;
    JToolBar toolbar;

    public DemoControls(Joins var2) {
      super(var2.name);
      this.menuitem = new JMenuItem[this.joinType.length];
      this.icons = new Joins.DemoControls.JoinIcon[this.joinType.length];
      this.setBorder(new CompoundBorder(this.getBorder(), new EmptyBorder(2, 2, 2, 2)));
      this.demo = var2;
      this.setLayout(new BorderLayout());
      Joins.this.label = new JLabel(" Width = " + var2.bswidth);
      Font var3 = new Font("serif", 1, 14);
      Joins.this.label.setFont(var3);
      this.add("West", Joins.this.label);
      JMenuBar var4 = new JMenuBar();
      this.add("East", var4);
      this.menu = var4.add(new JMenu(this.joinName[0]));
      this.menu.setFont(var3 = new Font("serif", 0, 10));
      ActionListener var10000 =
          new ActionListener() {
            public void actionPerformed(ActionEvent var1) {
              throw new UnsupportedOperationException("Not supported yet.");
            }
          };

      for (int var6 = 0; var6 < this.joinType.length; ++var6) {
        this.icons[var6] = new Joins.DemoControls.JoinIcon(this.joinType[var6]);
        this.menuitem[var6] = this.menu.add(new JMenuItem(this.joinName[var6]));
        this.menuitem[var6].setFont(var3);
        this.menuitem[var6].setIcon(this.icons[var6]);
        this.menuitem[var6].addActionListener(this);
      }

      this.menu.setIcon(this.icons[0]);
    }

    public void actionPerformed(ActionEvent var1) {
      for (int var2 = 0; var2 < this.joinType.length; ++var2) {
        if (var1.getSource().equals(this.menuitem[var2])) {
          this.demo.joinType = this.joinType[var2];
          this.menu.setIcon(this.icons[var2]);
          this.menu.setText(this.joinName[var2]);
          break;
        }
      }

      this.demo.repaint();
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 37);
    }

    public void run() {
      try {
        Thread.sleep(999L);
      } catch (Exception var7) {
        return;
      }

      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        for (int var2 = 0; var2 < this.menuitem.length; ++var2) {
          this.menuitem[var2].doClick();

          for (int var3 = 10; var3 < 60; var3 += 2) {
            this.demo.slider.setValue(var3);

            try {
              Thread.sleep(100L);
            } catch (InterruptedException var5) {
              return;
            }
          }

          try {
            Thread.sleep(999L);
          } catch (InterruptedException var6) {
            return;
          }
        }
      }

      this.thread = null;
    }

    class JoinIcon implements Icon {
      int joinType;

      public JoinIcon(int var2) {
        this.joinType = var2;
      }

      public void paintIcon(Component var1, Graphics var2, int var3, int var4) {
        ((Graphics2D) var2)
            .setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke var5 = new BasicStroke(8.0F, 0, this.joinType);
        ((Graphics2D) var2).setStroke(var5);
        GeneralPath var6 = new GeneralPath();
        var6.moveTo(0.0F, 3.0F);
        var6.lineTo((float) (this.getIconWidth() - 2), (float) (this.getIconHeight() / 2));
        var6.lineTo(0.0F, (float) this.getIconHeight());
        ((Graphics2D) var2).draw(var6);
      }

      public int getIconWidth() {
        return 20;
      }

      public int getIconHeight() {
        return 20;
      }
    }
  }
}
