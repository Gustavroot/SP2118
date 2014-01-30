/*    */ package geometry;
/*    */ 
/*    */ public class Spline
/*    */ {
/*    */   private double[] y;
/*    */   private double[] y2;
/*    */ 
/*    */   public Spline(double[] y)
/*    */   {
/* 14 */     this.y = y;
/* 15 */     int n = y.length;
/* 16 */     this.y2 = new double[n];
/* 17 */     double[] u = new double[n];
/* 18 */     for (int i = 1; i < n - 1; i++) {
/* 19 */       this.y2[i] = (-1.0D / (4.0D + this.y2[(i - 1)]));
/* 20 */       u[i] = ((6.0D * (y[(i + 1)] - 2.0D * y[i] + y[(i - 1)]) - u[(i - 1)]) / (4.0D + this.y2[(i - 1)]));
/*    */     }
/* 22 */     for (int i = n - 2; i >= 0; i--)
/* 23 */       this.y2[i] = (this.y2[i] * this.y2[(i + 1)] + u[i]);
/*    */   }
/*    */ 
/*    */   public double fn(int n, double t)
/*    */   {
/* 37 */     return t * this.y[(n + 1)] - (t - 1.0D) * t * ((t - 2.0D) * this.y2[n] - (t + 1.0D) * this.y2[(n + 1)]) / 6.0D + this.y[n] - t * this.y[n];
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.Spline
 * JD-Core Version:    0.6.0
 */