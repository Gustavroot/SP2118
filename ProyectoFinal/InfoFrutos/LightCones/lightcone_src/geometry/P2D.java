/*    */ package geometry;
/*    */ 
/*    */ public class P2D extends PnD
/*    */ {
/*    */   public P2D()
/*    */   {
/*  7 */     super(2);
/*    */   }
/*    */   public P2D(double[] data) {
/* 10 */     super(2, data);
/*    */   }
/*    */   public P2D(PnD p) {
/* 13 */     super(2, p);
/*    */   }
/*    */ 
/*    */   public PnD add(VnD v)
/*    */   {
/* 18 */     P2D result = new P2D(this);
/* 19 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 20 */     for (int i = 0; i < size; i++) {
/* 21 */       result.setX(i, result.getX(i) + v.getX(i));
/*    */     }
/* 23 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD add(PnD p) {
/* 27 */     P2D result = new P2D(this);
/* 28 */     int size = getDim() >= p.getDim() ? p.getDim() : getDim();
/* 29 */     for (int i = 0; i < size; i++) {
/* 30 */       result.setX(i, result.getX(i) + p.getX(i));
/*    */     }
/* 32 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD sub(VnD v) {
/* 36 */     P2D result = new P2D(this);
/* 37 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 38 */     for (int i = 0; i < size; i++) {
/* 39 */       result.setX(i, result.getX(i) - v.getX(i));
/*    */     }
/* 41 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD sub(PnD p) {
/* 45 */     P2D result = new P2D(this);
/* 46 */     int size = getDim() >= p.getDim() ? p.getDim() : getDim();
/* 47 */     for (int i = 0; i < size; i++) {
/* 48 */       result.setX(i, result.getX(i) - p.getX(i));
/*    */     }
/* 50 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD mul(double a) {
/* 54 */     P2D result = new P2D(this);
/* 55 */     if (a == 0.0D) {
/* 56 */       return new P2D();
/*    */     }
/* 58 */     for (int i = 0; i < getDim(); i++) {
/* 59 */       result.setX(i, result.getX(i) * a);
/*    */     }
/* 61 */     return result;
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.P2D
 * JD-Core Version:    0.6.0
 */