//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java2d.Surface;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

public class JPEGFlip extends Surface {
  private static Image img;

  public JPEGFlip() {
    this.setBackground(Color.WHITE);
    img = this.getImage("duke.png");
  }

  public static void main(String[] var0) {
    createDemoFrame(new JPEGFlip());
  }

  public void render(int var1, int var2, Graphics2D var3) {
    int var4 = var2 / 2;
    BufferedImage var5 = new BufferedImage(var1, var4, 1);
    Graphics2D var6 = var5.createGraphics();
    var6.setRenderingHints(var3.getRenderingHints());
    var6.setBackground(this.getBackground());
    var6.clearRect(0, 0, var1, var4);
    var6.setColor(Color.GREEN.darker());
    GeneralPath var7 = new GeneralPath(1);
    var7.moveTo((float) (-var1) / 2.0F, (float) (-var4) / 8.0F);
    var7.lineTo((float) var1 / 2.0F, (float) (-var4) / 8.0F);
    var7.lineTo((float) (-var1) / 4.0F, (float) var4 / 2.0F);
    var7.lineTo(0.0F, (float) (-var4) / 2.0F);
    var7.lineTo((float) var1 / 4.0F, (float) var4 / 2.0F);
    var7.closePath();
    var6.translate(var1 / 2, var4 / 2);
    var6.fill(var7);
    float var8 = 0.09F;
    int var9 = (int) (var8 * (float) var1);
    int var10 = img.getHeight(null) * var9 / img.getWidth(null);
    var6.drawImage(img, -var9 / 2, -var10 / 2, var9, var10, this);
    var3.drawImage(var5, 0, 0, this);
    var3.setFont(new Font("Dialog", 0, 10));
    var3.setColor(Color.BLACK);
    var3.drawString("BufferedImage", 4, 12);
    BufferedImage var11 = null;
    ImageOutputStream var12 = null;
    ByteArrayOutputStream var13 = null;
    ByteArrayInputStream var14 = null;

    label140:
    {
      try {
        var13 = new ByteArrayOutputStream();
        var12 = ImageIO.createImageOutputStream(var13);
        ImageWriter var15 = ImageIO.getImageWritersByFormatName("JPEG").next();
        JPEGImageWriteParam var16 = new JPEGImageWriteParam(null);
        var16.setCompressionMode(2);
        var16.setCompressionQuality(1.0F);
        var15.setOutput(var12);
        var15.write(null, new IIOImage(var5, null, null), var16);
        var14 = new ByteArrayInputStream(var13.toByteArray());
        var11 = ImageIO.read(var14);
        break label140;
      } catch (Exception var33) {
        var3.setColor(Color.RED);
        var3.drawString("Error encoding or decoding the image", 5, var4 * 2 - 5);
      } finally {
        if (var12 != null) {
          try {
            var12.close();
          } catch (IOException var32) {
            Logger.getLogger(JPEGFlip.class.getName()).log(Level.SEVERE, null, var32);
          }
        }

        if (var13 != null) {
          try {
            var13.close();
          } catch (IOException var31) {
            Logger.getLogger(JPEGFlip.class.getName()).log(Level.SEVERE, null, var31);
          }
        }

        if (var14 != null) {
          try {
            var14.close();
          } catch (IOException var30) {
            Logger.getLogger(JPEGFlip.class.getName()).log(Level.SEVERE, null, var30);
          }
        }
      }

      return;
    }

    if (var11 == null) {
      var3.setColor(Color.RED);
      var3.drawString("Error reading the image", 5, var4 * 2 - 5);
    } else {
      var3.drawImage(var11, var1, var4 * 2, -var1, -var4, null);
      var3.drawString("JPEGImage Flipped", 4, var4 * 2 - 4);
      var3.drawLine(0, var4, var1, var4);
    }
  }
}
