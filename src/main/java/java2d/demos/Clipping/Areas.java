//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Clipping;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.GeneralPath;
import java2d.ControlsSurface;
import java2d.CustomControls;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class Areas extends ControlsSurface {
  protected String areaType = "nop";

  public Areas() {
    this.setBackground(Color.WHITE);
    this.setControls(new Component[] {new Areas.DemoControls(this)});
  }

  public static void main(String[] var0) {
    createDemoFrame(new Areas());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    GeneralPath var4 = new GeneralPath();
    var4.moveTo((float) var1 * 0.25F, 0.0F);
    var4.lineTo((float) var1 * 0.75F, (float) var2 * 0.5F);
    var4.lineTo((float) var1 * 0.25F, (float) var2);
    var4.lineTo(0.0F, (float) var2 * 0.5F);
    var4.closePath();
    GeneralPath var5 = new GeneralPath();
    var5.moveTo((float) var1 * 0.75F, 0.0F);
    var5.lineTo((float) var1, (float) var2 * 0.5F);
    var5.lineTo((float) var1 * 0.75F, (float) var2);
    var5.lineTo((float) var1 * 0.25F, (float) var2 * 0.5F);
    var5.closePath();
    Area var6 = new Area(var4);
    var3.setColor(Color.YELLOW);
    if (this.areaType.equals("nop")) {
      var3.fill(var4);
      var3.fill(var5);
      var3.setColor(Color.RED);
      var3.draw(var4);
      var3.draw(var5);
    } else {
      if (this.areaType.equals("add")) {
        var6.add(new Area(var5));
      } else if (this.areaType.equals("sub")) {
        var6.subtract(new Area(var5));
      } else if (this.areaType.equals("xor")) {
        var6.exclusiveOr(new Area(var5));
      } else if (this.areaType.equals("int")) {
        var6.intersect(new Area(var5));
      } else if (this.areaType.equals("pear")) {
        double var7 = var1 / 100;
        double var9 = var2 / 140;
        var3.scale(var7, var9);
        double var11 = (double) var1 / var7 / 2.0D;
        double var13 = (double) var2 / var9 / 2.0D;
        Double var15 = new Double(var11 - 16.0D, var13 - 29.0D, 15.0D, 15.0D);
        Area var16 = new Area(var15);
        var15.setFrame(var11 - 14.0D, var13 - 47.0D, 30.0D, 30.0D);
        Area var17 = new Area(var15);
        var16.intersect(var17);
        var3.setColor(Color.GREEN);
        var3.fill(var16);
        var15.setFrame(var11 + 1.0D, var13 - 29.0D, 15.0D, 15.0D);
        var16 = new Area(var15);
        var17.intersect(var16);
        var3.fill(var17);
        Double var18 = new Double(var11, var13 - 42.0D, 40.0D, 40.0D);
        Area var19 = new Area(var18);
        var18.setFrame(var11 + 3.0D, var13 - 47.0D, 50.0D, 50.0D);
        var19.subtract(new Area(var18));
        var3.setColor(Color.BLACK);
        var3.fill(var19);
        Double var20 = new Double(var11 - 25.0D, var13, 50.0D, 50.0D);
        Double var21 = new Double(var11 - 19.0D, var13 - 20.0D, 40.0D, 70.0D);
        Area var22 = new Area(var20);
        var22.add(new Area(var21));
        var3.setColor(Color.YELLOW);
        var3.fill(var22);
        return;
      }

      var3.fill(var6);
      var3.setColor(Color.RED);
      var3.draw(var6);
    }
  }

  static final class DemoControls extends CustomControls implements ActionListener {
    Areas demo;
    JToolBar toolbar;
    JComboBox combo;

    public DemoControls(Areas var1) {
      super(var1.name);
      this.demo = var1;
      this.add(this.toolbar = new JToolBar());
      this.toolbar.setFloatable(false);
      this.addTool("nop", "no area operation", true);
      this.addTool("add", "add", false);
      this.addTool("sub", "subtract", false);
      this.addTool("xor", "exclusiveOr", false);
      this.addTool("int", "intersection", false);
      this.addTool("pear", "pear", false);
    }

    public void addTool(String var1, String var2, boolean var3) {
      JToggleButton var4 = (JToggleButton) this.toolbar.add(new JToggleButton(var1));
      var4.setFocusPainted(false);
      var4.setToolTipText(var2);
      var4.setSelected(var3);
      var4.addActionListener(this);
      int var5 = var4.getPreferredSize().width;
      Dimension var6 = new Dimension(var5, 21);
      var4.setPreferredSize(var6);
      var4.setMaximumSize(var6);
      var4.setMinimumSize(var6);
    }

    public void actionPerformed(ActionEvent var1) {
      Component[] var2 = this.toolbar.getComponents();
      int var3 = var2.length;

      for (int var4 = 0; var4 < var3; ++var4) {
        Component var5 = var2[var4];
        ((JToggleButton) var5).setSelected(false);
      }

      JToggleButton var6 = (JToggleButton) var1.getSource();
      var6.setSelected(true);
      this.demo.areaType = var6.getText();
      this.demo.repaint();
    }

    public Dimension getPreferredSize() {
      return new Dimension(200, 40);
    }

    public void run() {
      try {
        Thread.sleep(1111L);
      } catch (Exception var8) {
        return;
      }

      Thread var1 = Thread.currentThread();

      while (this.thread == var1) {
        Component[] var2 = this.toolbar.getComponents();
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
          Component var5 = var2[var4];
          ((AbstractButton) var5).doClick();

          try {
            Thread.sleep(4444L);
          } catch (InterruptedException var7) {
            return;
          }
        }
      }

      this.thread = null;
    }
  }
}
