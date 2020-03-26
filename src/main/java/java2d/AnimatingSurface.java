//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

public abstract class AnimatingSurface extends Surface implements Runnable {
  private volatile boolean running = false;
  private volatile Thread thread;

  public AnimatingSurface() {}

  public abstract void step(int var1, int var2);

  public abstract void reset(int var1, int var2);

  public void start() {
    if (!this.running() && !this.dontThread) {
      this.thread = new Thread(this);
      this.thread.setPriority(1);
      this.thread.setName(this.name + " Demo");
      this.thread.start();
      this.running = true;
    }
  }

  public synchronized void stop() {
    if (this.thread != null) {
      this.running = false;
      this.thread.interrupt();
    }

    this.thread = null;
    this.notifyAll();
  }

  public void run() {
    while (this.running() && !this.isShowing() || this.getSize().width == 0) {
      try {
        Thread.sleep(200L);
      } catch (InterruptedException var2) {
      }
    }

    while (this.running()) {
      this.repaint();

      try {
        Thread.sleep(this.sleepAmount);
      } catch (InterruptedException var3) {
      }
    }

    this.running = false;
  }

  public boolean running() {
    return this.running;
  }

  public void doRepaint() {
    if (this.running() && this.thread != null) {
      this.thread.interrupt();
    }
  }
}
