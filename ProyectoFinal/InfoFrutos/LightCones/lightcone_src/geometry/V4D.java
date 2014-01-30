/*    */ package geometry;
/*    */ 
/*    */ public class V4D extends VnD
/*    */ {
/*    */   public V4D()
/*    */   {
/*  7 */     super(4);
/*    */   }
/*    */   public V4D(double[] data) {
/* 10 */     super(4, data);
/*    */   }
/*    */   public V4D(VnD v) {
/* 13 */     super(4, v);
/*    */   }
/*    */ 
/*    */   public VnD add(VnD v)
/*    */   {
/* 18 */     V4D result = new V4D(this);
/* 19 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 20 */     for (int i = 0; i < size; i++) {
/* 21 */       result.setX(i, result.getX(i) + v.getX(i));
/*    */     }
/* 23 */     return result;
/*    */   }
/*    */ 
/*    */   public VnD sub(VnD v) {
/* 27 */     V4D result = new V4D(this);
/* 28 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 29 */     for (int i = 0; i < size; i++) {
/* 30 */       result.setX(i, result.getX(i) - v.getX(i));
/*    */     }
/* 32 */     return result;
/*    */   }
/*    */ 
/*    */   public VnD mul(double a) {
/* 36 */     V4D result = new V4D(this);
/* 37 */     if (a == 0.0D) {
/* 38 */       return new V4D();
/*    */     }
/* 40 */     for (int i = 0; i < getDim(); i++) {
/* 41 */       result.setX(i, result.getX(i) * a);
/*    */     }
/* 43 */     return result;
/*    */   }
/*    */ 
/*    */   public VnD cro(VnD v)
/*    */   {
/* 48 */     V3D cprod = new V3D();
/*    */ 
/* 50 */     cprod.setX(0, getX(2) * v.getX(3) - getX(3) * v.getX(2));
/* 51 */     cprod.setX(1, getX(3) * v.getX(1) - getX(1) * v.getX(3));
/* 52 */     cprod.setX(2, getX(1) * v.getX(2) - getX(2) * v.getX(1));
/*    */ 
/* 54 */     return cprod;
/*    */   }
/*    */ 
/*    */   public VnD getNormalized() {
/* 58 */     V4D result = new V4D(this);
/* 59 */     result.normalize();
/*    */ 
/* 61 */     return result;
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.V4D
 * JD-Core Version:    0.6.0
 */