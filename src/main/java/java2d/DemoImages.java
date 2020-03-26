//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoImages extends Component {
  private static final String[] names =
      new String[] {
        "java-logo.gif",
        "bld.jpg",
        "boat.png",
        "box.png",
        "boxwave.png",
        "clouds.jpg",
        "duke.gif",
        "duke.running.gif",
        "dukeplug.png",
        "fight.png",
        "globe.png",
        "java_logo.png",
        "jumptojavastrip.png",
        "magnify.png",
        "painting.png",
        "remove.gif",
        "snooze.png",
        "star7.gif",
        "surfing.png",
        "thumbsup.png",
        "tip.png",
        "duke.png",
        "print.gif",
        "loop.gif",
        "looping.gif",
        "start.gif",
        "start2.gif",
        "stop.gif",
        "stop2.gif",
        "clone.gif"
      };
  private static Map<String, Image> cache;

  static {
    cache = new ConcurrentHashMap(names.length);
  }

  private DemoImages() {}

  public static void newDemoImages() {
    DemoImages var0 = new DemoImages();
    String[] var1 = names;
    int var2 = var1.length;

    for (int var3 = 0; var3 < var2; ++var3) {
      String var4 = var1[var3];
      cache.put(var4, getImage(var4, var0));
    }
  }

  public static Image getImage(String var0, Component var1) {
    Image var2 = null;
    if (cache != null && (var2 = cache.get(var0)) != null) {
      return var2;
    } else {

      // Changes for JDK11 - START
      String pathSeparator = System.getProperty("path.separator");
      String[] classPathEntries = System.getProperty("java.class.path").split(pathSeparator);

      List<URL> urls = new LinkedList<>();

      for (String path : classPathEntries) {
        try {
          urls.add(Paths.get(path).toUri().toURL());
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
      }

      URL[] ulrsArray = new URL[urls.size()];
      urls.toArray(ulrsArray);

      URLClassLoader var3 = URLClassLoader.newInstance(ulrsArray); // JDK11
      // Changes for JDK11 - END
      //      URLClassLoader var3 = (URLClassLoader) var1.getClass().getClassLoader(); // jdk8
      URL var4 = var3.findResource("java2d/images/" + var0);
      var2 = var1.getToolkit().getImage(var4);
      MediaTracker var5 = new MediaTracker(var1);
      var5.addImage(var2, 0);

      try {
        var5.waitForID(0);
        if (var5.isErrorAny()) {
          System.out.println("Error loading image " + var0);
        }
      } catch (Exception var7) {
        Logger.getLogger(DemoImages.class.getName()).log(Level.SEVERE, null, var7);
      }

      return var2;
    }
  }
}
