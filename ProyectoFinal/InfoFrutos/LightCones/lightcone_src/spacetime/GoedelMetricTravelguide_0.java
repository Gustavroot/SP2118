/*     */ package spacetime;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ 
/*     */ public class GoedelMetricTravelguide_0 extends AbstractMetric
/*     */ {
/*     */   private double mOmega;
/*     */ 
/*     */   public GoedelMetricTravelguide_0()
/*     */   {
/*  14 */     this.mOmega = 1.0D;
/*  15 */     this.cr.min[2] = 0.0D; this.cr.max[2] = 6.283185307179586D; this.cr.type[2] = 5;
/*  16 */     this.cr.sys = 2;
/*     */   }
/*     */ 
/*     */   public void calculateMetric(double[] pos)
/*     */   {
/*  22 */     double R = pos[1];
/*  23 */     double omega = this.mOmega;
/*     */ 
/*  25 */     double t1 = R * R;
/*  26 */     double t2 = -1.0D + t1;
/*  27 */     double t7 = 2.0D / t2 / omega * t1;
/*  28 */     double t8 = omega * omega;
/*  29 */     double t9 = 1.0D / t8;
/*  30 */     double t10 = t2 * t2;
/*  31 */     double t11 = 1.0D / t10;
/*     */ 
/*  33 */     this.g_compts[0][0] = -1.0D;
/*  34 */     this.g_compts[0][1] = 0.0D;
/*  35 */     this.g_compts[0][2] = t7;
/*  36 */     this.g_compts[0][3] = 0.0D;
/*  37 */     this.g_compts[1][0] = 0.0D;
/*  38 */     this.g_compts[1][1] = (2.0D * t9 * t11);
/*  39 */     this.g_compts[1][2] = 0.0D;
/*  40 */     this.g_compts[1][3] = 0.0D;
/*  41 */     this.g_compts[2][0] = t7;
/*  42 */     this.g_compts[2][1] = 0.0D;
/*  43 */     this.g_compts[2][2] = (-2.0D * (2.0D * t1 - 1.0D) * t9 * t11 * t1);
/*  44 */     this.g_compts[2][3] = 0.0D;
/*  45 */     this.g_compts[3][0] = 0.0D;
/*  46 */     this.g_compts[3][1] = 0.0D;
/*  47 */     this.g_compts[3][2] = 0.0D;
/*  48 */     this.g_compts[3][3] = 1.0D;
/*     */   }
/*     */ 
/*     */   public void calculateChristoffelSymbols(double[] pos)
/*     */   {
/*  54 */     double R = pos[1];
/*  55 */     double omega = this.mOmega;
/*     */ 
/*  57 */     double t1 = R * R;
/*  58 */     double t2 = -1.0D + t1;
/*  59 */     double t4 = 1.0D / t2 * R;
/*  60 */     double t5 = 2.0D * t4;
/*  61 */     double t6 = 1.0D / R;
/*  62 */     double t7 = t6 * omega;
/*  63 */     double t8 = omega * R;
/*  64 */     double t9 = t2 * t2;
/*  65 */     double t15 = 2.0D / t9 / omega * t1 * R;
/*     */ 
/*  67 */     this.christoffel[0][0][0] = 0.0D;
/*  68 */     this.christoffel[0][0][1] = 0.0D;
/*  69 */     this.christoffel[0][0][2] = 0.0D;
/*  70 */     this.christoffel[0][0][3] = 0.0D;
/*  71 */     this.christoffel[0][1][0] = (-t5);
/*  72 */     this.christoffel[0][1][1] = 0.0D;
/*  73 */     this.christoffel[0][1][2] = (-t7);
/*  74 */     this.christoffel[0][1][3] = 0.0D;
/*  75 */     this.christoffel[0][2][0] = 0.0D;
/*  76 */     this.christoffel[0][2][1] = t8;
/*  77 */     this.christoffel[0][2][2] = 0.0D;
/*  78 */     this.christoffel[0][2][3] = 0.0D;
/*  79 */     this.christoffel[0][3][0] = 0.0D;
/*  80 */     this.christoffel[0][3][1] = 0.0D;
/*  81 */     this.christoffel[0][3][2] = 0.0D;
/*  82 */     this.christoffel[0][3][3] = 0.0D;
/*  83 */     this.christoffel[1][0][0] = (-t5);
/*  84 */     this.christoffel[1][0][1] = 0.0D;
/*  85 */     this.christoffel[1][0][2] = (-t7);
/*  86 */     this.christoffel[1][0][3] = 0.0D;
/*  87 */     this.christoffel[1][1][0] = 0.0D;
/*  88 */     this.christoffel[1][1][1] = (-t5);
/*  89 */     this.christoffel[1][1][2] = 0.0D;
/*  90 */     this.christoffel[1][1][3] = 0.0D;
/*  91 */     this.christoffel[1][2][0] = t15;
/*  92 */     this.christoffel[1][2][1] = 0.0D;
/*  93 */     this.christoffel[1][2][2] = t6;
/*  94 */     this.christoffel[1][2][3] = 0.0D;
/*  95 */     this.christoffel[1][3][0] = 0.0D;
/*  96 */     this.christoffel[1][3][1] = 0.0D;
/*  97 */     this.christoffel[1][3][2] = 0.0D;
/*  98 */     this.christoffel[1][3][3] = 0.0D;
/*  99 */     this.christoffel[2][0][0] = 0.0D;
/* 100 */     this.christoffel[2][0][1] = t8;
/* 101 */     this.christoffel[2][0][2] = 0.0D;
/* 102 */     this.christoffel[2][0][3] = 0.0D;
/* 103 */     this.christoffel[2][1][0] = t15;
/* 104 */     this.christoffel[2][1][1] = 0.0D;
/* 105 */     this.christoffel[2][1][2] = t6;
/* 106 */     this.christoffel[2][1][3] = 0.0D;
/* 107 */     this.christoffel[2][2][0] = 0.0D;
/* 108 */     this.christoffel[2][2][1] = (-t4 * (3.0D * t1 - 1.0D));
/* 109 */     this.christoffel[2][2][2] = 0.0D;
/* 110 */     this.christoffel[2][2][3] = 0.0D;
/* 111 */     this.christoffel[2][3][0] = 0.0D;
/* 112 */     this.christoffel[2][3][1] = 0.0D;
/* 113 */     this.christoffel[2][3][2] = 0.0D;
/* 114 */     this.christoffel[2][3][3] = 0.0D;
/* 115 */     this.christoffel[3][0][0] = 0.0D;
/* 116 */     this.christoffel[3][0][1] = 0.0D;
/* 117 */     this.christoffel[3][0][2] = 0.0D;
/* 118 */     this.christoffel[3][0][3] = 0.0D;
/* 119 */     this.christoffel[3][1][0] = 0.0D;
/* 120 */     this.christoffel[3][1][1] = 0.0D;
/* 121 */     this.christoffel[3][1][2] = 0.0D;
/* 122 */     this.christoffel[3][1][3] = 0.0D;
/* 123 */     this.christoffel[3][2][0] = 0.0D;
/* 124 */     this.christoffel[3][2][1] = 0.0D;
/* 125 */     this.christoffel[3][2][2] = 0.0D;
/* 126 */     this.christoffel[3][2][3] = 0.0D;
/* 127 */     this.christoffel[3][3][0] = 0.0D;
/* 128 */     this.christoffel[3][3][1] = 0.0D;
/* 129 */     this.christoffel[3][3][2] = 0.0D;
/* 130 */     this.christoffel[3][3][3] = 0.0D;
/*     */   }
/*     */ 
/*     */   public V4D localToCoord(P4D pos, V4D oldDir, int localFrameType) {
/* 134 */     double t = pos.getX(0);
/* 135 */     double R = pos.getX(1);
/* 136 */     double phi = pos.getX(2);
/* 137 */     double z = pos.getX(3);
/*     */ 
/* 139 */     double vtCam = oldDir.getX(0);
/* 140 */     double vRCam = oldDir.getX(1);
/* 141 */     double vphiCam = oldDir.getX(2);
/* 142 */     double vzCam = oldDir.getX(3);
/*     */ 
/* 144 */     double[] nDir = new double[4];
/* 145 */     double R2 = R * R;
/*     */ 
/* 148 */     nDir[0] = (vtCam + Math.sqrt(2.0D) * R * vphiCam);
/* 149 */     nDir[1] = (this.mOmega * (1.0D - R2) / Math.sqrt(2.0D) * vRCam);
/* 150 */     nDir[2] = (-this.mOmega * (1.0D - R2) / (Math.sqrt(2.0D) * R) * vphiCam);
/* 151 */     nDir[3] = vzCam;
/*     */ 
/* 153 */     return new V4D(nDir);
/*     */   }
/*     */ 
/*     */   public boolean breakCondition(double[] pos) {
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */   public double getOmega()
/*     */   {
/* 162 */     return this.mOmega;
/*     */   }
/*     */ 
/*     */   public void setOmega(double o) {
/* 166 */     this.mOmega = o;
/*     */   }
/*     */ 
/*     */   public double getParam(int paramName) {
/* 170 */     if (paramName == 15) {
/* 171 */       return getOmega();
/*     */     }
/* 173 */     return (0.0D / 0.0D);
/*     */   }
/*     */   public void setParam(int paramName, double val) {
/* 176 */     if (paramName == 15)
/* 177 */       setOmega(val);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     spacetime.GoedelMetricTravelguide_0
 * JD-Core Version:    0.6.0
 */