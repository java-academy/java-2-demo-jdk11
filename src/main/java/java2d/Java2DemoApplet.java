//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Java2DemoApplet extends JApplet {
  public static JApplet applet;

  public Java2DemoApplet() {}

  public void init() {
    applet = this;
    JPanel var1 = new JPanel();
    this.getContentPane().add(var1, "Center");
    var1.setLayout(new BoxLayout(var1, 0));
    JPanel var2 =
        new JPanel() {
          public Insets getInsets() {
            return new Insets(40, 30, 20, 30);
          }
        };
    var2.setLayout(new BoxLayout(var2, 1));
    var1.add(Box.createGlue());
    var1.add(var2);
    var1.add(Box.createGlue());
    var2.add(Box.createGlue());
    Dimension var3 = new Dimension(400, 20);
    Java2Demo.progressLabel = new JLabel("Loading, please wait...");
    Java2Demo.progressLabel.setMaximumSize(var3);
    var2.add(Java2Demo.progressLabel);
    var2.add(Box.createRigidArea(new Dimension(1, 20)));
    Java2Demo.progressBar = new JProgressBar();
    Java2Demo.progressBar.setStringPainted(true);
    Java2Demo.progressLabel.setLabelFor(Java2Demo.progressBar);
    Java2Demo.progressBar.setAlignmentX(0.5F);
    Java2Demo.progressBar.setMaximumSize(var3);
    Java2Demo.progressBar.setMinimum(0);
    Java2Demo.progressBar.setValue(0);
    var2.add(Java2Demo.progressBar);
    var2.add(Box.createGlue());
    var2.add(Box.createGlue());
    Rectangle var4 = this.getContentPane().getBounds();
    var1.setPreferredSize(new Dimension(var4.width, var4.height));
    this.getContentPane().add(var1, "Center");
    this.validate();
    this.setVisible(true);
    Java2Demo.demo = new Java2Demo();
    this.getContentPane().remove(var1);
    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(Java2Demo.demo, "Center");
    String var5 = null;
    if ((var5 = this.getParameter("delay")) != null) {
      RunWindow.delay = Integer.parseInt(var5);
    }

    if (this.getParameter("ccthread") != null) {
      Java2Demo.ccthreadCB.setSelected(true);
    }

    if ((var5 = this.getParameter("screen")) != null) {
      GlobalControls.screenCombo.setSelectedIndex(Integer.parseInt(var5));
    }

    if ((var5 = this.getParameter("antialias")) != null) {
      Java2Demo.controls.aliasCB.setSelected(var5.endsWith("true"));
    }

    if ((var5 = this.getParameter("rendering")) != null) {
      Java2Demo.controls.renderCB.setSelected(var5.endsWith("true"));
    }

    if ((var5 = this.getParameter("texture")) != null) {
      Java2Demo.controls.textureCB.setSelected(var5.endsWith("true"));
    }

    if ((var5 = this.getParameter("composite")) != null) {
      Java2Demo.controls.compositeCB.setSelected(var5.endsWith("true"));
    }

    if (this.getParameter("verbose") != null) {
      Java2Demo.verboseCB.setSelected(true);
    }

    if ((var5 = this.getParameter("columns")) != null) {
      DemoGroup.columns = Integer.parseInt(var5);
    }

    if ((var5 = this.getParameter("buffers")) != null) {
      RunWindow.buffersFlag = true;
      int var6 = var5.indexOf(44);
      String var7 = var5.substring(0, var6);
      RunWindow.bufBeg = Integer.parseInt(var7);
      var7 = var5.substring(var6 + 1);
      RunWindow.bufEnd = Integer.parseInt(var7);
    }

    if (this.getParameter("zoom") != null) {
      RunWindow.zoomCB.setSelected(true);
    }

    if ((var5 = this.getParameter("runs")) != null) {
      RunWindow.numRuns = Integer.parseInt(var5);
      Java2Demo.demo.createRunWindow();
      RunWindow.runB.doClick();
    }

    this.validate();
    this.repaint();
    this.requestDefaultFocus();
  }

  private void requestDefaultFocus() {
    Container var1 = this.getFocusCycleRootAncestor();
    if (var1 != null) {
      var1.getFocusTraversalPolicy().getDefaultComponent(var1).requestFocus();
    }
  }

  public void start() {
    Java2Demo.demo.start();
  }

  public void stop() {
    Java2Demo.demo.stop();
  }
}
