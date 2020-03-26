//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class RunWindow extends JPanel implements Runnable, ActionListener {
  static JButton runB;
  static int delay = 10;
  static int numRuns = 20;
  static boolean exit;
  static JCheckBox zoomCB = new JCheckBox("Zoom");
  static JCheckBox printCB = new JCheckBox("Print");
  static boolean buffersFlag;
  static int bufBeg;
  static int bufEnd;
  private JTextField delayTextField;
  private JTextField runsTextField;
  private Thread thread;
  private JProgressBar pb;
  private DemoGroup dg = null;
  private DemoPanel dp = null;

  public RunWindow() {
    this.setLayout(new GridBagLayout());
    EmptyBorder var1 = new EmptyBorder(5, 5, 5, 5);
    this.setBorder(new CompoundBorder(var1, new BevelBorder(1)));
    Font var2 = new Font("serif", 0, 10);
    runB = new JButton("Run");
    runB.setBackground(Color.GREEN);
    runB.addActionListener(this);
    runB.setMinimumSize(new Dimension(70, 30));
    Java2Demo.addToGridBag(this, runB, 0, 0, 1, 1, 0.0D, 0.0D);
    this.pb = new JProgressBar();
    this.pb.setPreferredSize(new Dimension(100, 30));
    this.pb.setMinimum(0);
    Java2Demo.addToGridBag(this, this.pb, 1, 0, 2, 1, 1.0D, 0.0D);
    JPanel var3 = new JPanel(new GridLayout(2, 2));
    JPanel var4 = new JPanel();
    JLabel var5 = new JLabel("Runs:");
    var5.setFont(var2);
    var5.setForeground(Color.BLACK);
    var4.add(var5);
    var4.add(this.runsTextField = new JTextField(String.valueOf(numRuns)));
    this.runsTextField.setPreferredSize(new Dimension(30, 20));
    this.runsTextField.addActionListener(this);
    var3.add(var4);
    var4 = new JPanel();
    var5 = new JLabel("Delay:");
    var5.setFont(var2);
    var5.setForeground(Color.BLACK);
    var4.add(var5);
    var4.add(this.delayTextField = new JTextField(String.valueOf(delay)));
    this.delayTextField.setPreferredSize(new Dimension(30, 20));
    this.delayTextField.addActionListener(this);
    var3.add(var4);
    zoomCB.setHorizontalAlignment(0);
    zoomCB.setFont(var2);
    printCB.setFont(var2);
    var3.add(zoomCB);
    var3.add(printCB);
    printCB.addActionListener(this);
    Java2Demo.addToGridBag(this, var3, 0, 1, 3, 1, 1.0D, 1.0D);
  }

  private static void invokeAndWait(Runnable var0) {
    try {
      SwingUtilities.invokeAndWait(var0);
    } catch (Exception var2) {
      Logger.getLogger(RunWindow.class.getName()).log(Level.SEVERE, "ERROR in invokeAndWait", var2);
    }
  }

  public void actionPerformed(ActionEvent var1) {
    if (var1.getSource().equals(printCB)) {
      Java2Demo.printCB.setSelected(printCB.isSelected());
    } else if (var1.getSource().equals(this.delayTextField)) {
      delay = Integer.parseInt(this.delayTextField.getText().trim());
    } else if (var1.getSource().equals(this.runsTextField)) {
      numRuns = Integer.parseInt(this.runsTextField.getText().trim());
    } else if ("Run".equals(var1.getActionCommand())) {
      this.doRunAction();
    } else if ("Stop".equals(var1.getActionCommand())) {
      this.stop();
    }
  }

  public void doRunAction() {
    runB.setText("Stop");
    runB.setBackground(Color.RED);
    this.start();
  }

  public void start() {
    this.thread = new Thread(this);
    this.thread.setPriority(6);
    this.thread.setName("RunWindow");
    this.thread.start();
  }

  public synchronized void stop() {
    if (this.thread != null) {
      this.thread.interrupt();
    }

    this.thread = null;
    this.notifyAll();
  }

  public void sleepPerTab() {
    for (int var1 = 0; var1 < delay + 1 && this.thread != null; ++var1) {
      for (int var2 = 0; var2 < 10 && this.thread != null; ++var2) {
        try {
          Thread.sleep(100L);
        } catch (Exception var4) {
        }
      }

      Runnable var5 =
          new Runnable() {
            public void run() {
              RunWindow.this.pb.setValue(RunWindow.this.pb.getValue() + 1);
            }
          };
      SwingUtilities.invokeLater(var5);
    }
  }

  private void printDemo(final DemoGroup var1) {
    Runnable var2 =
        new Runnable() {
          public void run() {
            if (!Java2Demo.controls.toolBarCB.isSelected()) {
              Java2Demo.controls.toolBarCB.setSelected(true);
              var1.invalidate();
            }

            Component[] var1x = var1.getPanel().getComponents();
            int var2 = var1x.length;

            for (int var3 = 0; var3 < var2; ++var3) {
              Component var4 = var1x[var3];
              DemoPanel var5 = (DemoPanel) var4;
              if (var5.tools != null) {
                if (var5.surface.animating != null && var5.surface.animating.running()) {
                  var5.tools.startStopB.doClick();
                }

                var5.tools.printB.doClick();
              }
            }
          }
        };
    invokeAndWait(var2);
  }

  public void run() {
    System.out.println(
        "\nJava2D Demo RunWindow : "
            + numRuns
            + " Runs, "
            + delay
            + " second delay between tabs\n"
            + "java version: "
            + System.getProperty("java.version")
            + "\n"
            + System.getProperty("os.name")
            + " "
            + System.getProperty("os.version")
            + "\n");
    Runtime var1 = Runtime.getRuntime();

    for (int var2 = 0; var2 < numRuns && this.thread != null; ++var2) {
      Date var3 = new Date();
      System.out.print("#" + var2 + " " + var3.toString() + ", ");
      var1.gc();
      float var4 = (float) var1.freeMemory();
      float var5 = (float) var1.totalMemory();
      System.out.println((var5 - var4) / 1024.0F + "K used");

      for (int var6 = 0; var6 < Java2Demo.tabbedPane.getTabCount() && this.thread != null; ++var6) {
        int finalVar = var6;
        Runnable var8 =
            new Runnable() {
              public void run() {
                RunWindow.this.pb.setValue(0);
                RunWindow.this.pb.setMaximum(RunWindow.delay);
                if (finalVar != 0) {
                  RunWindow.this.dg = Java2Demo.group[finalVar - 1];
                  RunWindow.this.dg.invalidate();
                }

                Java2Demo.tabbedPane.setSelectedIndex(finalVar);
              }
            };
        invokeAndWait(var8);
        if (var6 != 0 && (zoomCB.isSelected() || buffersFlag)) {
          this.dp = (DemoPanel) this.dg.getPanel().getComponent(0);
          if (this.dg.tabbedPane == null && this.dp.surface != null) {
            Runnable var9 =
                new Runnable() {
                  public void run() {
                    RunWindow.this.dg.mouseClicked(RunWindow.this.dp.surface);
                  }
                };
            invokeAndWait(var9);
          }

          for (int var17 = 1;
              var17 < this.dg.tabbedPane.getTabCount() && this.thread != null;
              ++var17) {
            int finalVar1 = var17;
            Runnable var11 =
                new Runnable() {
                  public void run() {
                    RunWindow.this.pb.setValue(0);
                    RunWindow.this.pb.setMaximum(RunWindow.delay);
                    RunWindow.this.dg.tabbedPane.setSelectedIndex(finalVar1);
                  }
                };
            invokeAndWait(var11);
            final JPanel var12 = this.dg.getPanel();
            if (buffersFlag && var12.getComponentCount() == 1) {
              this.dp = (DemoPanel) var12.getComponent(0);
              if (this.dp.surface.animating != null) {
                this.dp.surface.animating.stop();
              }

              for (int var13 = bufBeg; var13 <= bufEnd && this.thread != null; ++var13) {
                int finalVar11 = var13;
                Runnable var15 =
                    new Runnable() {
                      public void run() {
                        RunWindow.this.dp.tools.cloneB.doClick();
                        int var1 = var12.getComponentCount();
                        DemoPanel var2 = (DemoPanel) var12.getComponent(var1 - 1);
                        if (var2.surface.animating != null) {
                          var2.surface.animating.stop();
                        }

                        var2.tools.issueRepaint = true;
                        var2.tools.screenCombo.setSelectedIndex(finalVar11);
                        var2.tools.issueRepaint = false;
                      }
                    };
                invokeAndWait(var15);
              }
            }

            if (printCB.isSelected()) {
              this.printDemo(this.dg);
            }

            this.sleepPerTab();
          }
        } else if (var6 != 0 && printCB.isSelected()) {
          this.printDemo(this.dg);
          this.sleepPerTab();
        } else {
          this.sleepPerTab();
        }
      }

      if (var2 + 1 == numRuns) {
        System.out.println("Finished.");
        if (exit && this.thread != null) {
          System.out.println("System.exit(0).");
          System.exit(0);
        }
      }
    }

    Runnable var16 =
        new Runnable() {
          public void run() {
            RunWindow.runB.setText("Run");
            RunWindow.runB.setBackground(Color.GREEN);
            RunWindow.this.pb.setValue(0);
          }
        };
    invokeAndWait(var16);
    this.thread = null;
    this.dg = null;
    this.dp = null;
  }
}
