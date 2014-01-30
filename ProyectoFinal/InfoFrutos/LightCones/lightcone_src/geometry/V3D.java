/*    */ package geometry;
/*    */ 
/*    */ public class V3D extends VnD
/*    */ {
/*    */   public V3D()
/*    */   {
/*  8 */     super(3);
/*    */   }
/*    */   public V3D(double[] data) {
/* 11 */     super(3, data);
/*    */   }
/*    */   public V3D(VnD v) {
/* 14 */     super(3, v);
/*    */   }
/*    */ 
/*    */   public VnD add(VnD v)
/*    */   {
/* 19 */     V3D result = new V3D(this);
/* 20 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 21 */     for (int i = 0; i < size; i++) {
/* 22 */       result.setX(i, result.getX(i) + v.getX(i));
/*    */     }
/* 24 */     return result;
/*    */   }
/*    */ 
/*    */   public VnD sub(VnD v) {
/* 28 */     V3D result = new V3D(this);
/* 29 */     int size = getDim() >= v.getDim() ? v.getDim() : getDim();
/* 30 */     for (int i = 0; i < size; i++) {
/* 31 */       result.setX(i, result.getX(i) - v.getX(i));
/*    */     }
/* 33 */     return result;
/*    */   }
/*    */ 
/*    */   public VnD mul(double a) {
/* 37 */     V3D result = new V3D(this);
/* 38 */     if (a == 0.0D) {
/* 39 */       return new V3D();
/*    */     }
/* 41 */     for (int i = 0; i < getDim(); i++) {
/* 42 */       result.setX(i, result.getX(i) * a);
/*    */     }
/* 44 */     return result;
/*    */   }
/*    */ 
/*    */   public VnD cro(VnD v) {
/* 48 */     V3D cprod = new V3D();
/*    */ 
/* 50 */     cprod.setX(0, getX(1) * v.getX(2) - getX(2) * v.getX(1));
/* 51 */     cprod.setX(1, getX(2) * v.getX(0) - getX(0) * v.getX(2));
/* 52 */     cprod.setX(2, getX(0) * v.getX(1) - getX(1) * v.getX(0));
/*    */ 
/* 54 */     return cprod;
/*    */   }
/*    */ 
/*    */   public VnD getNormalized() {
/* 58 */     V3D result = new V3D(this);
/* 59 */     result.normalize();
/*    */ 
/* 61 */     return result;
/*    */   }
/*    */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.V3D
 * JD-Core Version:    0.6.0
 */