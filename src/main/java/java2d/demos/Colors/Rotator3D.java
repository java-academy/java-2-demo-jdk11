//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package java2d.demos.Colors;

import java.awt.Color;
import java.awt.Graphics2D;
import java2d.AnimatingSurface;

public class Rotator3D extends AnimatingSurface {
  private static final int[][][] polygons =
      new int[][][] {
        {
          {5, 1, 15, 13, 21, 23, 15},
          {5, 2, 21, 13, 19, 27, 21},
          {5, 3, 23, 15, 17, 25, 23},
          {5, 4, 19, 13, 15, 17, 19},
          {5, 5, 27, 21, 23, 25, 27},
          {5, 6, 27, 19, 17, 25, 27}
        },
        {
          {5, 1, 21, 13, 19, 27, 21},
          {5, 5, 23, 15, 17, 25, 23},
          {4, 0, 15, 14, 16, 15},
          {7, 6, 16, 14, 13, 12, 18, 17, 16},
          {4, 0, 12, 19, 18, 12},
          {4, 2, 22, 21, 20, 22},
          {7, 0, 24, 23, 22, 20, 27, 26, 24},
          {4, 2, 24, 26, 25, 24},
          {4, 3, 15, 13, 23, 15},
          {4, 0, 23, 13, 21, 23},
          {5, 0, 27, 26, 18, 19, 27},
          {5, 4, 25, 17, 18, 26, 25}
        },
        {
          {4, 3, 18, 21, 16, 18},
          {4, 1, 20, 16, 18, 20},
          {4, 1, 18, 21, 16, 18},
          {4, 3, 20, 17, 19, 20},
          {4, 2, 20, 26, 27, 20},
          {5, 3, 26, 18, 16, 27, 26},
          {5, 0, 17, 24, 25, 19, 17},
          {4, 3, 21, 25, 24, 21},
          {4, 4, 18, 21, 22, 18},
          {4, 2, 22, 21, 17, 22},
          {4, 5, 20, 23, 16, 20},
          {4, 1, 20, 23, 19, 20},
          {4, 6, 21, 23, 16, 21},
          {4, 4, 21, 23, 19, 21},
          {4, 5, 20, 18, 22, 20},
          {4, 6, 20, 22, 17, 20}
        }
      };
  private static final double[][][] points =
      new double[][][] {
        {
          {1.0D, 0.0D, 0.0D},
          {-1.0D, 0.0D, 0.0D},
          {0.0D, 1.0D, 0.0D},
          {0.0D, -1.0D, 0.0D},
          {0.0D, 0.0D, 1.0D},
          {0.0D, 0.0D, -1.0D},
          {1.0D, 0.0D, 0.0D},
          {-1.0D, 0.0D, 0.0D},
          {0.0D, 1.0D, 0.0D},
          {0.0D, -1.0D, 0.0D},
          {0.0D, 0.0D, 1.0D},
          {0.0D, 0.0D, -1.0D},
          {1.0D, 1.0D, 0.0D},
          {1.0D, 1.0D, 1.0D},
          {0.0D, 1.0D, 1.0D},
          {-1.0D, 1.0D, 1.0D},
          {-1.0D, 1.0D, 0.0D},
          {-1.0D, 1.0D, -1.0D},
          {0.0D, 1.0D, -1.0D},
          {1.0D, 1.0D, -1.0D},
          {1.0D, -1.0D, 0.0D},
          {1.0D, -1.0D, 1.0D},
          {0.0D, -1.0D, 1.0D},
          {-1.0D, -1.0D, 1.0D},
          {-1.0D, -1.0D, 0.0D},
          {-1.0D, -1.0D, -1.0D},
          {0.0D, -1.0D, -1.0D},
          {1.0D, -1.0D, -1.0D}
        },
        {
          {0.0D, 0.0D, 1.0D},
          {0.0D, 0.0D, -1.0D},
          {-0.8165D, 0.4714D, 0.33333D},
          {0.8165D, -0.4714D, -0.33333D},
          {0.8165D, 0.4714D, 0.33333D},
          {-0.8165D, -0.4714D, -0.33333D},
          {0.0D, -0.9428D, 0.3333D},
          {0.0D, 0.9428D, -0.33333D},
          {0.0D, 0.0D, 1.0D},
          {0.0D, 0.0D, -1.0D},
          {-0.8165D, 0.4714D, 0.33333D},
          {0.8165D, -0.4714D, -0.33333D},
          {0.8165D, 0.4714D, 0.33333D},
          {-0.8165D, -0.4714D, -0.33333D},
          {0.0D, -0.9428D, 0.33333D},
          {0.0D, 0.9428D, -0.33333D},
          {-1.2247D, -0.7071D, 1.0D},
          {1.2247D, 0.7071D, -1.0D},
          {0.0D, 1.4142D, 1.0D},
          {0.0D, -1.4142D, -1.0D},
          {-1.2247D, 0.7071D, -1.0D},
          {1.2247D, -0.7071D, 1.0D},
          {0.61237D, 1.06066D, 0.0D},
          {-0.61237D, -1.06066D, 0.0D},
          {1.2247D, 0.0D, 0.0D},
          {0.61237D, -1.06066D, 0.0D},
          {-0.61237D, 1.06066D, 0.0D},
          {-1.2247D, 0.0D, 0.0D}
        }
      };
  private static final int[][][] faces =
      new int[][][] {
        {{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 0}, {1, 5}},
        {{1, 0}, {1, 1}, {3, 2, 3, 4}, {3, 5, 6, 7}, {2, 8, 9}, {2, 10, 11}},
        {{1, 2}, {1, 3}, {2, 4, 5}, {2, 6, 7}, {2, 8, 9}, {2, 10, 11}, {2, 12, 13}, {2, 14, 15}}
      };
  private Rotator3D.Objects3D[] objs = new Rotator3D.Objects3D[3];

  public Rotator3D() {
    this.setBackground(Color.white);
  }

  public static void main(String[] var0) {
    createDemoFrame(new Rotator3D());
  }

  public void reset(int var1, int var2) {
    this.objs[0] = new Rotator3D.Objects3D(polygons[0], points[0], faces[0], var1, var2);
    this.objs[1] = new Rotator3D.Objects3D(polygons[1], points[0], faces[1], var1, var2);
    this.objs[2] = new Rotator3D.Objects3D(polygons[2], points[1], faces[2], var1, var2);
  }

  public void step(int var1, int var2) {
    Rotator3D.Objects3D[] var3 = this.objs;
    int var4 = var3.length;

    for (int var5 = 0; var5 < var4; ++var5) {
      Rotator3D.Objects3D var6 = var3[var5];
      if (var6 != null) {
        var6.step(var1, var2);
      }
    }
  }

  public void render(int var1, int var2, Graphics2D var3) {
    Rotator3D.Objects3D[] var4 = this.objs;
    int var5 = var4.length;

    for (int var6 = 0; var6 < var5; ++var6) {
      Rotator3D.Objects3D var7 = var4[var6];
      if (var7 != null) {
        var7.render(var3);
      }
    }
  }

  public class Objects3D {
    private final int UP = 0;
    private final int DOWN = 1;
    private int[][] polygons;
    private double[][] points;
    private int npoint;
    private int[][] faces;
    private int nface;
    private int ncolour = 10;
    private Color[][] colours;
    private double[] lightvec;
    private double Zeye;
    private double angle;
    private Rotator3D.Objects3D.Matrix3D orient;
    private Rotator3D.Objects3D.Matrix3D tmp;
    private Rotator3D.Objects3D.Matrix3D tmp2;
    private Rotator3D.Objects3D.Matrix3D tmp3;
    private int scaleDirection;
    private double scale;
    private double scaleAmt;
    private double ix;
    private double iy;
    private double[][] rotPts;
    private int[][] scrPts;
    private int[] xx;
    private int[] yy;
    private double x;
    private double y;
    private int p;
    private int j;
    private int colour;
    private double bounce;
    private double persp;

    public Objects3D(int[][] var2, double[][] var3, int[][] var4, int var5, int var6) {
      this.colours = new Color[this.ncolour][7];
      this.lightvec = new double[] {0.0D, 1.0D, 1.0D};
      this.Zeye = 10.0D;
      this.ix = 3.0D;
      this.iy = 3.0D;
      this.xx = new int[20];
      this.yy = new int[20];
      this.polygons = var2;
      this.points = var3;
      this.faces = var4;
      this.npoint = var3.length;
      this.nface = var4.length;
      this.x = (double) var5 * Math.random();
      this.y = (double) var6 * Math.random();
      this.ix = Math.random() > 0.5D ? this.ix : -this.ix;
      this.iy = Math.random() > 0.5D ? this.iy : -this.iy;
      this.rotPts = new double[this.npoint][3];
      this.scrPts = new int[this.npoint][2];

      for (int var7 = 0; var7 < this.ncolour; ++var7) {
        int var8 = 255 - (this.ncolour - var7 - 1) * 100 / this.ncolour;
        Color[] var9 =
            new Color[] {
              new Color(var8, var8, var8),
              new Color(var8, 0, 0),
              new Color(0, var8, 0),
              new Color(0, 0, var8),
              new Color(var8, var8, 0),
              new Color(0, var8, var8),
              new Color(var8, 0, var8)
            };
        this.colours[var7] = var9;
      }

      double var12 =
          Math.sqrt(
              this.lightvec[0] * this.lightvec[0]
                  + this.lightvec[1] * this.lightvec[1]
                  + this.lightvec[2] * this.lightvec[2]);
      this.lightvec[0] /= var12;
      this.lightvec[1] /= var12;
      this.lightvec[2] /= var12;
      double var13 = 0.0D;

      int var11;
      for (var11 = 0; var11 < this.npoint; ++var11) {
        var12 =
            Math.sqrt(
                var3[var11][0] * var3[var11][0]
                    + var3[var11][1] * var3[var11][1]
                    + var3[var11][2] * var3[var11][2]);
        if (var12 > var13) {
          var13 = var12;
        }
      }

      for (var11 = 0; var11 < this.nface; ++var11) {
        var12 =
            Math.sqrt(
                var3[var11][0] * var3[var11][0]
                    + var3[var11][1] * var3[var11][1]
                    + var3[var11][2] * var3[var11][2]);
        var3[var11][0] /= var12;
        var3[var11][1] /= var12;
        var3[var11][2] /= var12;
      }

      this.orient = new Rotator3D.Objects3D.Matrix3D();
      this.tmp = new Rotator3D.Objects3D.Matrix3D();
      this.tmp2 = new Rotator3D.Objects3D.Matrix3D();
      this.tmp3 = new Rotator3D.Objects3D.Matrix3D();
      this.tmp.Rotation(2, 0, 0.06283185307179587D);
      this.CalcScrPts((double) var5 / 3.0D, (double) var6 / 3.0D, 0.0D);
      this.scale = Math.min((double) (var5 / 3) / var13 / 1.2D, (double) (var6 / 3) / var13 / 1.2D);
      this.scaleAmt = this.scale;
      this.scale *= Math.random() * 1.5D;
      this.scaleDirection = this.scaleAmt < this.scale ? 1 : 0;
    }

    private Color getColour(int var1, int var2) {
      this.colour =
          (int)
              ((this.rotPts[var1][0] * this.lightvec[0]
                      + this.rotPts[var1][1] * this.lightvec[1]
                      + this.rotPts[var1][2] * this.lightvec[2])
                  * (double) this.ncolour);
      if (this.colour < 0) {
        this.colour = 0;
      }

      if (this.colour > this.ncolour - 1) {
        this.colour = this.ncolour - 1;
      }

      return this.colours[this.colour][this.polygons[this.faces[var1][var2]][1]];
    }

    private void CalcScrPts(double var1, double var3, double var5) {
      for (this.p = 0; this.p < this.npoint; ++this.p) {
        this.rotPts[this.p][2] =
            this.points[this.p][0] * this.orient.M[2][0]
                + this.points[this.p][1] * this.orient.M[2][1]
                + this.points[this.p][2] * this.orient.M[2][2];
        this.rotPts[this.p][0] =
            this.points[this.p][0] * this.orient.M[0][0]
                + this.points[this.p][1] * this.orient.M[0][1]
                + this.points[this.p][2] * this.orient.M[0][2];
        this.rotPts[this.p][1] =
            -this.points[this.p][0] * this.orient.M[1][0]
                - this.points[this.p][1] * this.orient.M[1][1]
                - this.points[this.p][2] * this.orient.M[1][2];
      }

      for (this.p = this.nface; this.p < this.npoint; ++this.p) {
        double[] var10000 = this.rotPts[this.p];
        var10000[2] += var5;
        this.persp = (this.Zeye - this.rotPts[this.p][2]) / (this.scale * this.Zeye);
        this.scrPts[this.p][0] = (int) (this.rotPts[this.p][0] / this.persp + var1);
        this.scrPts[this.p][1] = (int) (this.rotPts[this.p][1] / this.persp + var3);
      }
    }

    private boolean faceUp(int var1) {
      return this.rotPts[var1][0] * this.rotPts[this.nface + var1][0]
              + this.rotPts[var1][1] * this.rotPts[this.nface + var1][1]
              + this.rotPts[var1][2] * (this.rotPts[this.nface + var1][2] - this.Zeye)
          < 0.0D;
    }

    public void step(int var1, int var2) {
      this.x += this.ix;
      this.y += this.iy;
      if (this.x > (double) var1 - this.scale) {
        this.x = (double) var1 - this.scale - 1.0D;
        this.ix = -var1 / 100 - 1;
      }

      if (this.x - this.scale < 0.0D) {
        this.x = 2.0D + this.scale;
        this.ix = (double) (var1 / 100) + Math.random() * 3.0D;
      }

      if (this.y > (double) var2 - this.scale) {
        this.y = (double) var2 - this.scale - 2.0D;
        this.iy = -var2 / 100 - 1;
      }

      if (this.y - this.scale < 0.0D) {
        this.y = 2.0D + this.scale;
        this.iy = (double) (var2 / 100) + Math.random() * 3.0D;
      }

      this.angle += Math.random() * 0.15D;
      this.tmp3.Rotation(1, 2, this.angle);
      this.tmp2.Rotation(1, 0, this.angle * Math.sqrt(2.0D) / 2.0D);
      this.tmp.Rotation(0, 2, this.angle * 3.141592653589793D / 4.0D);
      this.orient.M = this.tmp3.Times(this.tmp2.Times(this.tmp.M));
      this.bounce = Math.abs(Math.cos(0.5D * this.angle)) * 2.0D - 1.0D;
      if (this.scale > this.scaleAmt * 1.4D) {
        this.scaleDirection = 1;
      }

      if (this.scale < this.scaleAmt * 0.4D) {
        this.scaleDirection = 0;
      }

      if (this.scaleDirection == 0) {
        this.scale += Math.random();
      }

      if (this.scaleDirection == 1) {
        this.scale -= Math.random();
      }

      this.CalcScrPts(this.x, this.y, this.bounce);
    }

    public void render(Graphics2D var1) {
      for (int var2 = 0; var2 < this.nface; ++var2) {
        if (this.faceUp(var2)) {
          for (this.j = 1; this.j < this.faces[var2][0] + 1; ++this.j) {
            this.DrawPoly(var1, this.faces[var2][this.j], this.getColour(var2, this.j));
          }
        }
      }
    }

    private void DrawPoly(Graphics2D var1, int var2, Color var3) {
      for (int var4 = 2; var4 < this.polygons[var2][0] + 2; ++var4) {
        this.xx[var4 - 2] = this.scrPts[this.polygons[var2][var4]][0];
        this.yy[var4 - 2] = this.scrPts[this.polygons[var2][var4]][1];
      }

      var1.setColor(var3);
      var1.fillPolygon(this.xx, this.yy, this.polygons[var2][0]);
      var1.setColor(Color.black);
      var1.drawPolygon(this.xx, this.yy, this.polygons[var2][0]);
    }

    public class Matrix3D {
      public double[][] M =
          new double[][] {{1.0D, 0.0D, 0.0D}, {0.0D, 1.0D, 0.0D}, {0.0D, 0.0D, 1.0D}};
      private double[][] tmp = new double[3][3];
      private int row;
      private int col;
      private int k;

      public Matrix3D() {}

      public void Rotation(int var1, int var2, double var3) {
        for (this.row = 0; this.row < 3; ++this.row) {
          for (this.col = 0; this.col < 3; ++this.col) {
            if (this.row != this.col) {
              this.M[this.row][this.col] = 0.0D;
            } else {
              this.M[this.row][this.col] = 1.0D;
            }
          }
        }

        this.M[var1][var1] = Math.cos(var3);
        this.M[var2][var2] = Math.cos(var3);
        this.M[var1][var2] = Math.sin(var3);
        this.M[var2][var1] = -Math.sin(var3);
      }

      public double[][] Times(double[][] var1) {
        for (this.row = 0; this.row < 3; ++this.row) {
          for (this.col = 0; this.col < 3; ++this.col) {
            this.tmp[this.row][this.col] = 0.0D;

            for (this.k = 0; this.k < 3; ++this.k) {
              double[] var10000 = this.tmp[this.row];
              int var10001 = this.col;
              var10000[var10001] += this.M[this.row][this.k] * var1[this.k][this.col];
            }
          }
        }

        return this.tmp;
      }
    }
  }
}
