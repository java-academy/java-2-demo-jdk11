//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.Component;

public abstract class AnimatingControlsSurface extends AnimatingSurface
    implements CustomControlsContext {
  private Component[] controls;
  private String[] constraints = new String[] {"North"};

  public AnimatingControlsSurface() {}

  public String[] getConstraints() {
    return this.constraints;
  }

  public void setConstraints(String[] var1) {
    this.constraints = var1;
  }

  public Component[] getControls() {
    return this.controls;
  }

  public void setControls(Component[] var1) {
    this.controls = var1;
  }

  public void handleThread(State var1) {
    Component[] var2 = this.controls;
    int var3 = var2.length;

    for (int var4 = 0; var4 < var3; ++var4) {
      Component var5 = var2[var4];
      if (var5 instanceof CustomControls) {
        if (var1 == State.START) {
          ((CustomControls) var5).start();
        } else {
          ((CustomControls) var5).stop();
        }
      }
    }
  }
}
