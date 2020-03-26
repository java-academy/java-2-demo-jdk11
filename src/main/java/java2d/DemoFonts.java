//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d;

import java.awt.Font;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoFonts {
  private static final String[] names = new String[] {"A.ttf"};
  private static final Map<String, Font> cache;

  static {
    cache = new ConcurrentHashMap(names.length);
    String[] var0 = names;
    int var1 = var0.length;

    for (int var2 = 0; var2 < var1; ++var2) {
      String var3 = var0[var2];
      cache.put(var3, getFont(var3));
    }
  }

  public DemoFonts() {}

  public static void newDemoFonts() {}

  public static Font getFont(String var0) {
    Font var1 = null;
    if (cache != null && (var1 = cache.get(var0)) != null) {
      return var1;
    } else {
      String var2 = "fonts/" + var0;

      try {
        InputStream var3 = DemoFonts.class.getResourceAsStream(var2);
        var1 = Font.createFont(0, var3);
      } catch (Exception var4) {
        Logger.getLogger(DemoFonts.class.getName())
            .log(Level.SEVERE, var2 + " not loaded.  Using serif font.", var4);
        var1 = new Font("serif", 0, 24);
      }

      return var1;
    }
  }
}
