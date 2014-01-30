/*     */ package geometry;
/*     */ 
/*     */ public class TransCoordinates
/*     */ {
/*     */   public static P3D transCartSph(P3D oldPos)
/*     */   {
/*   9 */     double x = oldPos.getX(0);
/*  10 */     double y = oldPos.getX(1);
/*  11 */     double z = oldPos.getX(2);
/*  12 */     double r = Math.sqrt(x * x + y * y + z * z);
/*     */ 
/*  14 */     if ((x == 0.0D) && (y == 0.0D)) {
/*  15 */       return new P3D(new double[] { r, 0.0D, 0.0D });
/*     */     }
/*     */ 
/*  19 */     double theta = 0.0D; double tan_theta = 0.0D;
/*  20 */     if ((z > 0.0D) || (z < 0.0D)) tan_theta = Math.sqrt((x * x + y * y) / (z * z));
/*  21 */     if (z > 0.0D) theta = Math.atan(tan_theta);
/*  22 */     if (z < 0.0D) theta = 3.141592653589793D - Math.atan(tan_theta);
/*  23 */     if (z == 0.0D) theta = 1.570796326794897D;
/*     */ 
/*  25 */     double phi = 0.0D; double tan_phi = 0.0D;
/*  26 */     if ((x > 0.0D) || (x < 0.0D)) { tan_phi = y / x; phi = 0.0D; }
/*  27 */     if ((y >= 0.0D) && (x > 0.0D)) phi = Math.atan(tan_phi);
/*  28 */     if ((y >= 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/*  29 */     if ((y < 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/*  30 */     if ((y < 0.0D) && (x > 0.0D)) phi = 6.283185307179586D + Math.atan(tan_phi);
/*  31 */     if ((x == 0.0D) && (y > 0.0D)) phi = 1.570796326794897D;
/*  32 */     if ((x == 0.0D) && (y < 0.0D)) phi = 4.71238898038469D;
/*     */ 
/*  34 */     return new P3D(new double[] { r, theta, phi });
/*     */   }
/*     */ 
/*     */   public static P3D transSphCart(P3D oldPos) {
/*  38 */     double r = oldPos.getX(0);
/*  39 */     double theta = oldPos.getX(1);
/*  40 */     double phi = oldPos.getX(2);
/*     */ 
/*  42 */     return new P3D(new double[] { 
/*  43 */       r * Math.sin(theta) * Math.cos(phi), 
/*  44 */       r * Math.sin(theta) * Math.sin(phi), 
/*  45 */       r * Math.cos(theta) });
/*     */   }
/*     */ 
/*     */   public static P4D transCartSph(P4D oldPos) {
/*  49 */     double t = oldPos.getX(0);
/*  50 */     double x = oldPos.getX(1);
/*  51 */     double y = oldPos.getX(2);
/*  52 */     double z = oldPos.getX(3);
/*  53 */     double r = Math.sqrt(x * x + y * y + z * z);
/*     */ 
/*  55 */     if ((x == 0.0D) && (y == 0.0D)) {
/*  56 */       return new P4D(new double[] { t, r, 0.0D, 0.0D });
/*     */     }
/*     */ 
/*  60 */     double theta = 0.0D; double tan_theta = 0.0D;
/*  61 */     if ((z > 0.0D) || (z < 0.0D)) tan_theta = Math.sqrt((x * x + y * y) / (z * z));
/*  62 */     if (z > 0.0D) theta = Math.atan(tan_theta);
/*  63 */     if (z < 0.0D) theta = 3.141592653589793D - Math.atan(tan_theta);
/*  64 */     if (z == 0.0D) theta = 1.570796326794897D;
/*     */ 
/*  66 */     double phi = 0.0D; double tan_phi = 0.0D;
/*  67 */     if ((x > 0.0D) || (x < 0.0D)) { tan_phi = y / x; phi = 0.0D; }
/*  68 */     if ((y >= 0.0D) && (x > 0.0D)) phi = Math.atan(tan_phi);
/*  69 */     if ((y >= 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/*  70 */     if ((y < 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/*  71 */     if ((y < 0.0D) && (x > 0.0D)) phi = 6.283185307179586D + Math.atan(tan_phi);
/*  72 */     if ((x == 0.0D) && (y > 0.0D)) phi = 1.570796326794897D;
/*  73 */     if ((x == 0.0D) && (y < 0.0D)) phi = 4.71238898038469D;
/*     */ 
/*  75 */     return new P4D(new double[] { t, r, theta, phi });
/*     */   }
/*     */ 
/*     */   public static P4D transSphCart(P4D oldPos) {
/*  79 */     double t = oldPos.getX(0);
/*  80 */     double r = oldPos.getX(1);
/*  81 */     double theta = oldPos.getX(2);
/*  82 */     double phi = oldPos.getX(3);
/*     */ 
/*  84 */     return new P4D(new double[] { 
/*  85 */       t, 
/*  86 */       r * Math.sin(theta) * Math.cos(phi), 
/*  87 */       r * Math.sin(theta) * Math.sin(phi), 
/*  88 */       r * Math.cos(theta) });
/*     */   }
/*     */ 
/*     */   public static P3D transCartCyl(P3D oldPos)
/*     */   {
/*  94 */     double x = oldPos.getX(0);
/*  95 */     double y = oldPos.getX(1);
/*  96 */     double z = oldPos.getX(2);
/*  97 */     double r = Math.sqrt(x * x + y * y);
/*     */ 
/* 100 */     double phi = 0.0D;
/* 101 */     double tan_phi = 0.0D;
/* 102 */     if ((x > 0.0D) || (x < 0.0D)) { tan_phi = y / x; phi = 0.0D; }
/* 103 */     if ((y > 0.0D) && (x > 0.0D)) phi = Math.atan(tan_phi);
/* 104 */     if ((y > 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/* 105 */     if ((y < 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/* 106 */     if ((y < 0.0D) && (x > 0.0D)) phi = 6.283185307179586D + Math.atan(tan_phi);
/* 107 */     if ((x == 0.0D) && (y > 0.0D)) phi = 1.570796326794897D;
/* 108 */     if ((x == 0.0D) && (y < 0.0D)) phi = 4.71238898038469D;
/*     */ 
/* 110 */     return new P3D(new double[] { r, phi, z });
/*     */   }
/*     */   public static P3D transCylCart(P3D oldPos) {
/* 113 */     double r = oldPos.getX(0);
/* 114 */     double phi = oldPos.getX(1);
/* 115 */     double z = oldPos.getX(2);
/*     */ 
/* 117 */     return new P3D(new double[] { r * Math.cos(phi), r * Math.sin(phi), z });
/*     */   }
/*     */   public static P4D transCartCyl(P4D oldPos) {
/* 120 */     double t = oldPos.getX(0);
/* 121 */     double x = oldPos.getX(1);
/* 122 */     double y = oldPos.getX(2);
/* 123 */     double z = oldPos.getX(3);
/* 124 */     double r = Math.sqrt(x * x + y * y);
/*     */ 
/* 127 */     double phi = 0.0D;
/* 128 */     double tan_phi = 0.0D;
/* 129 */     if ((x > 0.0D) || (x < 0.0D)) { tan_phi = y / x; phi = 0.0D; }
/* 130 */     if ((y > 0.0D) && (x > 0.0D)) phi = Math.atan(tan_phi);
/* 131 */     if ((y > 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/* 132 */     if ((y < 0.0D) && (x < 0.0D)) phi = 3.141592653589793D + Math.atan(tan_phi);
/* 133 */     if ((y < 0.0D) && (x > 0.0D)) phi = 6.283185307179586D + Math.atan(tan_phi);
/* 134 */     if ((x == 0.0D) && (y > 0.0D)) phi = 1.570796326794897D;
/* 135 */     if ((x == 0.0D) && (y < 0.0D)) phi = 4.71238898038469D;
/*     */ 
/* 137 */     return new P4D(new double[] { t, r, phi, z });
/*     */   }
/*     */   public static P4D transCylCart(P4D oldPos) {
/* 140 */     double t = oldPos.getX(0);
/* 141 */     double r = oldPos.getX(1);
/* 142 */     double phi = oldPos.getX(2);
/* 143 */     double z = oldPos.getX(3);
/*     */ 
/* 145 */     return new P4D(new double[] { t, r * Math.cos(phi), r * Math.sin(phi), z });
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.TransCoordinates
 * JD-Core Version:    0.6.0
 */