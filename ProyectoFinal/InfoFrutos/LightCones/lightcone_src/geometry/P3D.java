/*    */ package geometry;
/*    */ 
/*    */ public class P3D extends PnD
/*    */ {
/*    */   public P3D()
/*    */   {
/*  7 */     super(3);
/*    */   }
/*    */   public P3D(double[] data) {
/* 10 */     super(3, data);
/*    */   }
/*    */   public P3D(PnD p) {
/* 13 */     super(3, p);
/*    */   }
/*    */ 
/*    */   public PnD add(VnD v)
/*    */   {
/* 18 */     P3D result = new P3D(this);
/* 19 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 20 */     for (int i = 0; i < size; i++) {
/* 21 */       result.setX(i, result.getX(i) + v.getX(i));
/*    */     }
/* 23 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD add(PnD p) {
/* 27 */     P3D result = new P3D(this);
/* 28 */     int size = getDim() >= p.getDim() ? p.getDim() : getDim();
/* 29 */     for (int i = 0; i < size; i++) {
/* 30 */       result.setX(i, result.getX(i) + p.getX(i));
/*    */     }
/* 32 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD sub(VnD v) {
/* 36 */     P3D result = new P3D(this);
/* 37 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 38 */     for (int i = 0; i < size; i++) {
/* 39 */       result.setX(i, result.getX(i) - v.getX(i));
/*    */     }
/* 41 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD sub(PnD p) {
/* 45 */     P3D result = new P3D(this);
/* 46 */     int size = getDim() >= p.getDim() ? p.getDim() : getDim();
/* 47 */     for (int i = 0; i < size; i++) {
/* 48 */       result.setX(i, result.getX(i) - p.getX(i));
/*    */     }
/* 50 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD mul(double a) {
/* 54 */     P3D result = new P3D(this);
/* 55 */     if (a == 0.0D) {
/* 56 */       return new P3D();
/*    */     }
/* 58 */     for (int i = 0; i < getDim(); i++) {
/* 59 */       result.setX(i, result.getX(i) * a);
/*    */     }
/* 61 */     return result;
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.P3D
 * JD-Core Version:    0.6.0
 */