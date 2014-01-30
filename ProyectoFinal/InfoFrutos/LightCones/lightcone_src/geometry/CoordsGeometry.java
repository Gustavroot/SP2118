/*    */ package geometry;
/*    */ 
/*    */ public class CoordsGeometry
/*    */ {
/* 16 */   public double[] min = new double[4];
/* 17 */   public double[] max = new double[4];
/* 18 */   public int[] type = new int[4];
/*    */   public int sys;
/*    */ 
/*    */   public CoordsGeometry()
/*    */   {
/*  8 */     for (int i = 0; i < 4; i++) {
/*  9 */       this.min[i] = (-1.0D / 0.0D);
/* 10 */       this.max[i] = (1.0D / 0.0D);
/* 11 */       this.type[i] = 3;
/* 12 */       this.sys = 0;
/*    */     }
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.CoordsGeometry
 * JD-Core Version:    0.6.0
 */