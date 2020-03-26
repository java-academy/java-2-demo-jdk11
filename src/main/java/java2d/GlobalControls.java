//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GlobalControls extends JPanel implements ItemListener, ChangeListener {
  static String[] screenNames =
      new String[] {
        "Auto Screen",
        "On Screen",
        "Off Screen",
        "INT_xRGB",
        "INT_ARGB",
        "INT_ARGB_PRE",
        "INT_BGR",
        "3BYTE_BGR",
        "4BYTE_ABGR",
        "4BYTE_ABGR_PRE",
        "USHORT_565_RGB",
        "USHORT_x555_RGB",
        "BYTE_GRAY",
        "USHORT_GRAY",
        "BYTE_BINARY",
        "BYTE_INDEXED",
        "BYTE_BINARY 2 bit",
        "BYTE_BINARY 4 bit",
        "INT_RGBx",
        "USHORT_555x_RGB"
      };
  static JComboBox screenCombo;
  public TextureChooser texturechooser;
  public JCheckBox aliasCB;
  public JCheckBox renderCB;
  public JCheckBox toolBarCB;
  public JCheckBox compositeCB;
  public JCheckBox textureCB;
  public JSlider slider;
  public Object obj;
  private Font font = new Font("serif", 0, 12);

  public GlobalControls() {
    this.setLayout(new GridBagLayout());
    this.setBorder(new TitledBorder(new EtchedBorder(), "Global Controls"));
    this.aliasCB = this.createCheckBox("Anti-Aliasing", true, 0);
    this.renderCB = this.createCheckBox("Rendering Quality", false, 1);
    this.textureCB = this.createCheckBox("Texture", false, 2);
    this.compositeCB = this.createCheckBox("AlphaComposite", false, 3);
    screenCombo = new JComboBox();
    screenCombo.setPreferredSize(new Dimension(120, 18));
    screenCombo.setLightWeightPopupEnabled(true);
    screenCombo.setFont(this.font);

    for (int var1 = 0; var1 < screenNames.length; ++var1) {
      screenCombo.addItem(screenNames[var1]);
    }

    screenCombo.addItemListener(this);
    Java2Demo.addToGridBag(this, screenCombo, 0, 4, 1, 1, 0.0D, 0.0D);
    this.toolBarCB = this.createCheckBox("Tools", false, 5);
    this.slider = new JSlider(0, 0, 200, 30);
    this.slider.addChangeListener(this);
    TitledBorder var2 = new TitledBorder(new EtchedBorder());
    var2.setTitleFont(this.font);
    var2.setTitle("Anim delay = 30 ms");
    this.slider.setBorder(var2);
    this.slider.setMinimumSize(new Dimension(80, 46));
    Java2Demo.addToGridBag(this, this.slider, 0, 6, 1, 1, 1.0D, 1.0D);
    this.texturechooser = new TextureChooser(0);
    Java2Demo.addToGridBag(this, this.texturechooser, 0, 7, 1, 1, 1.0D, 1.0D);
  }

  private JCheckBox createCheckBox(String var1, boolean var2, int var3) {
    JCheckBox var4 = new JCheckBox(var1, var2);
    var4.setFont(this.font);
    var4.setHorizontalAlignment(2);
    var4.addItemListener(this);
    Java2Demo.addToGridBag(this, var4, 0, var3, 1, 1, 1.0D, 1.0D);
    return var4;
  }

  public void stateChanged(ChangeEvent var1) {
    int var2 = this.slider.getValue();
    TitledBorder var3 = (TitledBorder) this.slider.getBorder();
    var3.setTitle("Anim delay = " + var2 + " ms");
    int var4 = Java2Demo.tabbedPane.getSelectedIndex() - 1;
    DemoGroup var5 = Java2Demo.group[var4];
    JPanel var6 = var5.getPanel();

    for (int var7 = 0; var7 < var6.getComponentCount(); ++var7) {
      DemoPanel var8 = (DemoPanel) var6.getComponent(var7);
      if (var8.tools != null && var8.tools.slider != null) {
        var8.tools.slider.setValue(var2);
      }
    }

    this.slider.repaint();
  }

  public void itemStateChanged(ItemEvent var1) {
    if (Java2Demo.tabbedPane.getSelectedIndex() != 0) {
      this.obj = var1.getSource();
      int var2 = Java2Demo.tabbedPane.getSelectedIndex() - 1;
      Java2Demo.group[var2].setup(true);
      this.obj = null;
    }
  }

  public Dimension getPreferredSize() {
    return new Dimension(135, 260);
  }
}
