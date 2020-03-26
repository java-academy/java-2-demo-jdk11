//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.Component;

public interface CustomControlsContext {
  String[] getConstraints();

  void setConstraints(String[] var1);

  Component[] getControls();

  void setControls(Component[] var1);

  void handleThread(CustomControlsContext.State var1);

  enum State {
    START,
    STOP;

    State() {}
  }
}
