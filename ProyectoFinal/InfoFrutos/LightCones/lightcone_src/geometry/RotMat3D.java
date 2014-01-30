/*    */ package geometry;
/*    */ 
/*    */ public class RotMat3D
/*    */ {
/*  5 */   private double[][] mat = new double[3][3];
/*    */ 
/*    */   public RotMat3D(V3D rotAxis, double rotAngle) {
/*  8 */     V3D axis = (V3D)rotAxis.getNormalized();
/*    */ 
/* 10 */     double sinAngle = Math.sin(rotAngle);
/* 11 */     double cosAngle = Math.cos(rotAngle);
/* 12 */     double one_cosAngle = 1.0D - cosAngle;
/*    */ 
/* 14 */     this.mat[0][0] = (one_cosAngle * axis.getX(0) * axis.getX(0) + cosAngle);
/* 15 */     this.mat[0][1] = (one_cosAngle * axis.getX(0) * axis.getX(1) - sinAngle * axis.getX(2));
/* 16 */     this.mat[0][2] = (one_cosAngle * axis.getX(0) * axis.getX(2) + sinAngle * axis.getX(1));
/*    */ 
/* 18 */     this.mat[1][0] = (one_cosAngle * axis.getX(1) * axis.getX(0) + sinAngle * axis.getX(2));
/* 19 */     this.mat[1][1] = (one_cosAngle * axis.getX(1) * axis.getX(1) + cosAngle);
/* 20 */     this.mat[1][2] = (one_cosAngle * axis.getX(1) * axis.getX(2) - sinAngle * axis.getX(0));
/*    */ 
/* 22 */     this.mat[2][0] = (one_cosAngle * axis.getX(2) * axis.getX(0) - sinAngle * axis.getX(1));
/* 23 */     this.mat[2][1] = (one_cosAngle * axis.getX(2) * axis.getX(1) + sinAngle * axis.getX(0));
/* 24 */     this.mat[2][2] = (one_cosAngle * axis.getX(2) * axis.getX(2) + cosAngle);
/*    */   }
/*    */ 
/*    */   public void setCoeff(int row, int col, double val) {
/* 28 */     this.mat[row][col] = val;
/*    */   }
/*    */ 
/*    */   public double getCoeff(int row, int col) {
/* 32 */     return this.mat[row][col];
/*    */   }
/*    */ 
/*    */   public V3D mul(V3D vec) {
/* 36 */     V3D q = new V3D(new double[] { 0.0D, 0.0D, 0.0D });
/* 37 */     for (int i = 0; i < 3; i++) {
/* 38 */       for (int j = 0; j < 3; j++) {
/* 39 */         q.setX(i, q.getX(i) + this.mat[i][j] * vec.getX(j));
/*    */       }
/*    */     }
/* 42 */     return q;
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.RotMat3D
 * JD-Core Version:    0.6.0
 */