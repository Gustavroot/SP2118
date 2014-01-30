/*    */ package geometry;
/*    */ 
/*    */ public class P4D extends PnD
/*    */ {
/*    */   public P4D()
/*    */   {
/*  6 */     super(4);
/*    */   }
/*    */   public P4D(double[] data) {
/*  9 */     super(4, data);
/*    */   }
/*    */   public P4D(PnD p) {
/* 12 */     super(4, p);
/*    */   }
/*    */ 
/*    */   public PnD add(VnD v)
/*    */   {
/* 17 */     P4D result = new P4D(this);
/* 18 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 19 */     for (int i = 0; i < size; i++) {
/* 20 */       result.setX(i, result.getX(i) + v.getX(i));
/*    */     }
/* 22 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD add(PnD p) {
/* 26 */     P4D result = new P4D(this);
/* 27 */     int size = getDim() >= p.getDim() ? p.getDim() : getDim();
/* 28 */     for (int i = 0; i < size; i++) {
/* 29 */       result.setX(i, result.getX(i) + p.getX(i));
/*    */     }
/* 31 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD sub(VnD v) {
/* 35 */     P4D result = new P4D(this);
/* 36 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 37 */     for (int i = 0; i < size; i++) {
/* 38 */       result.setX(i, result.getX(i) - v.getX(i));
/*    */     }
/* 40 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD sub(PnD p) {
/* 44 */     P4D result = new P4D(this);
/* 45 */     int size = getDim() >= p.getDim() ? p.getDim() : getDim();
/* 46 */     for (int i = 0; i < size; i++) {
/* 47 */       result.setX(i, result.getX(i) - p.getX(i));
/*    */     }
/* 49 */     return result;
/*    */   }
/*    */ 
/*    */   public PnD mul(double a) {
/* 53 */     P4D result = new P4D(this);
/* 54 */     if (a == 0.0D) {
/* 55 */       return new P4D();
/*    */     }
/* 57 */     for (int i = 0; i < getDim(); i++) {
/* 58 */       result.setX(i, result.getX(i) * a);
/*    */     }
/* 60 */     return result;
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.P4D
 * JD-Core Version:    0.6.0
 */