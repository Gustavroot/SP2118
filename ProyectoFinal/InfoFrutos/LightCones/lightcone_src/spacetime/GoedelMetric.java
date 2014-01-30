/*     */ package spacetime;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ 
/*     */ public class GoedelMetric extends AbstractMetric
/*     */ {
/*     */   private double mA;
/*     */   private double mAngVel;
/*     */ 
/*     */   public GoedelMetric()
/*     */   {
/*  14 */     this.mA = 10000000000.0D;
/*  15 */     this.mAngVel = 0.0D;
/*     */ 
/*  17 */     this.cr.min[2] = 0.0D; this.cr.max[2] = 1.570796326794897D; this.cr.type[2] = 5;
/*  18 */     this.cr.sys = 2;
/*     */   }
/*     */ 
/*     */   public void calculateMetric(double[] pos)
/*     */   {
/*  26 */     double r = pos[1];
/*  27 */     double a = this.mA;
/*     */ 
/*  29 */     double t1 = r * r;
/*  30 */     double t2 = Math.sqrt(2.0D);
/*  31 */     double t6 = t1 * t2 / a / 2.0D;
/*  32 */     double t7 = a * a;
/*  33 */     double t8 = 4.0D * t7;
/*     */ 
/*  35 */     this.g_compts[0][0] = -1.0D;
/*  36 */     this.g_compts[0][1] = 0.0D;
/*  37 */     this.g_compts[0][2] = (-t6);
/*  38 */     this.g_compts[0][3] = 0.0D;
/*  39 */     this.g_compts[1][0] = 0.0D;
/*  40 */     this.g_compts[1][1] = (4.0D * t7 / (t8 + t1));
/*  41 */     this.g_compts[1][2] = 0.0D;
/*  42 */     this.g_compts[1][3] = 0.0D;
/*  43 */     this.g_compts[2][0] = (-t6);
/*  44 */     this.g_compts[2][1] = 0.0D;
/*  45 */     this.g_compts[2][2] = ((t8 - t1) / t7 * t1 / 4.0D);
/*  46 */     this.g_compts[2][3] = 0.0D;
/*  47 */     this.g_compts[3][0] = 0.0D;
/*  48 */     this.g_compts[3][1] = 0.0D;
/*  49 */     this.g_compts[3][2] = 0.0D;
/*  50 */     this.g_compts[3][3] = 1.0D;
/*     */   }
/*     */ 
/*     */   public void calculateChristoffelSymbols(double[] pos)
/*     */   {
/*  57 */     double r = pos[1];
/*  58 */     double a = this.mA;
/*     */ 
/*  60 */     double t1 = a * a;
/*  61 */     double t3 = r * r;
/*  62 */     double t4 = 4.0D * t1 + t3;
/*  63 */     double t5 = 1.0D / t4;
/*  64 */     double t6 = t5 * r;
/*  65 */     double t7 = 2.0D * t6;
/*  66 */     double t8 = 1.0D / r;
/*  67 */     double t10 = Math.sqrt(2.0D);
/*  68 */     double t13 = 2.0D * t8 * t5 * a * t10;
/*  69 */     double t19 = 1.0D / t1 / a * t4 * r * t10 / 8.0D;
/*  70 */     double t25 = t3 * r * t10 * t5 / a / 2.0D;
/*  71 */     double t28 = 4.0D * t1 * t8 * t5;
/*  72 */     double t29 = t1 * t1;
/*     */ 
/*  74 */     this.christoffel[0][0][0] = 0.0D;
/*  75 */     this.christoffel[0][0][1] = 0.0D;
/*  76 */     this.christoffel[0][0][2] = 0.0D;
/*  77 */     this.christoffel[0][0][3] = 0.0D;
/*  78 */     this.christoffel[0][1][0] = t7;
/*  79 */     this.christoffel[0][1][1] = 0.0D;
/*  80 */     this.christoffel[0][1][2] = (-t13);
/*  81 */     this.christoffel[0][1][3] = 0.0D;
/*  82 */     this.christoffel[0][2][0] = 0.0D;
/*  83 */     this.christoffel[0][2][1] = t19;
/*  84 */     this.christoffel[0][2][2] = 0.0D;
/*  85 */     this.christoffel[0][2][3] = 0.0D;
/*  86 */     this.christoffel[0][3][0] = 0.0D;
/*  87 */     this.christoffel[0][3][1] = 0.0D;
/*  88 */     this.christoffel[0][3][2] = 0.0D;
/*  89 */     this.christoffel[0][3][3] = 0.0D;
/*  90 */     this.christoffel[1][0][0] = t7;
/*  91 */     this.christoffel[1][0][1] = 0.0D;
/*  92 */     this.christoffel[1][0][2] = (-t13);
/*  93 */     this.christoffel[1][0][3] = 0.0D;
/*  94 */     this.christoffel[1][1][0] = 0.0D;
/*  95 */     this.christoffel[1][1][1] = (-t6);
/*  96 */     this.christoffel[1][1][2] = 0.0D;
/*  97 */     this.christoffel[1][1][3] = 0.0D;
/*  98 */     this.christoffel[1][2][0] = t25;
/*  99 */     this.christoffel[1][2][1] = 0.0D;
/* 100 */     this.christoffel[1][2][2] = t28;
/* 101 */     this.christoffel[1][2][3] = 0.0D;
/* 102 */     this.christoffel[1][3][0] = 0.0D;
/* 103 */     this.christoffel[1][3][1] = 0.0D;
/* 104 */     this.christoffel[1][3][2] = 0.0D;
/* 105 */     this.christoffel[1][3][3] = 0.0D;
/* 106 */     this.christoffel[2][0][0] = 0.0D;
/* 107 */     this.christoffel[2][0][1] = t19;
/* 108 */     this.christoffel[2][0][2] = 0.0D;
/* 109 */     this.christoffel[2][0][3] = 0.0D;
/* 110 */     this.christoffel[2][1][0] = t25;
/* 111 */     this.christoffel[2][1][1] = 0.0D;
/* 112 */     this.christoffel[2][1][2] = t28;
/* 113 */     this.christoffel[2][1][3] = 0.0D;
/* 114 */     this.christoffel[2][2][0] = 0.0D;
/* 115 */     this.christoffel[2][2][1] = (-1.0D / t29 * t4 * r * (-t3 + 2.0D * t1) / 8.0D);
/* 116 */     this.christoffel[2][2][2] = 0.0D;
/* 117 */     this.christoffel[2][2][3] = 0.0D;
/* 118 */     this.christoffel[2][3][0] = 0.0D;
/* 119 */     this.christoffel[2][3][1] = 0.0D;
/* 120 */     this.christoffel[2][3][2] = 0.0D;
/* 121 */     this.christoffel[2][3][3] = 0.0D;
/* 122 */     this.christoffel[3][0][0] = 0.0D;
/* 123 */     this.christoffel[3][0][1] = 0.0D;
/* 124 */     this.christoffel[3][0][2] = 0.0D;
/* 125 */     this.christoffel[3][0][3] = 0.0D;
/* 126 */     this.christoffel[3][1][0] = 0.0D;
/* 127 */     this.christoffel[3][1][1] = 0.0D;
/* 128 */     this.christoffel[3][1][2] = 0.0D;
/* 129 */     this.christoffel[3][1][3] = 0.0D;
/* 130 */     this.christoffel[3][2][0] = 0.0D;
/* 131 */     this.christoffel[3][2][1] = 0.0D;
/* 132 */     this.christoffel[3][2][2] = 0.0D;
/* 133 */     this.christoffel[3][2][3] = 0.0D;
/* 134 */     this.christoffel[3][3][0] = 0.0D;
/* 135 */     this.christoffel[3][3][1] = 0.0D;
/* 136 */     this.christoffel[3][3][2] = 0.0D;
/* 137 */     this.christoffel[3][3][3] = 0.0D;
/*     */   }
/*     */ 
/*     */   public V4D localToCoord(P4D pos, V4D oldDir, int localFrameType)
/*     */   {
/* 145 */     double[] pos2 = new double[4];
/* 146 */     pos2[0] = pos.getX(0);
/* 147 */     pos2[1] = pos.getX(1);
/* 148 */     pos2[2] = pos.getX(2);
/* 149 */     pos2[3] = pos.getX(3);
/*     */ 
/* 151 */     calculateMetric(pos2);
/*     */ 
/* 153 */     double r = pos.getX(1);
/*     */ 
/* 156 */     double vtCam = oldDir.getX(0);
/* 157 */     double vrCam = oldDir.getX(1);
/* 158 */     double vphiCam = oldDir.getX(2);
/* 159 */     double vzCam = oldDir.getX(3);
/*     */ 
/* 161 */     double[] nDir = new double[4];
/*     */ 
/* 163 */     double A = 1.0D / Math.sqrt(-(this.g_compts[0][0] + this.mAngVel * this.g_compts[0][2] + this.mAngVel * this.mAngVel * this.g_compts[2][2]));
/*     */ 
/* 165 */     double B = 1.0D / 
/* 166 */       Math.sqrt((this.g_compts[0][0] * this.g_compts[2][2] - this.g_compts[0][2] * this.g_compts[0][2]) * (
/* 166 */       this.g_compts[0][0] + 2.0D * this.mAngVel * this.g_compts[0][2] - this.mAngVel * this.mAngVel * this.g_compts[2][2]));
/*     */ 
/* 168 */     nDir[0] = (vtCam * A - vphiCam * (this.g_compts[0][2] + this.mAngVel * this.g_compts[2][2]) * B);
/* 169 */     nDir[1] = (vrCam / Math.sqrt(this.g_compts[1][1]));
/* 170 */     nDir[2] = (vtCam * A * this.mAngVel + vphiCam * (this.g_compts[0][0] + this.mAngVel * this.g_compts[0][2]) * B);
/* 171 */     nDir[3] = (vzCam / Math.sqrt(this.g_compts[3][3]));
/*     */ 
/* 173 */     return new V4D(nDir);
/*     */   }
/*     */ 
/*     */   public boolean breakCondition(double[] pos) {
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */   public double getA()
/*     */   {
/* 182 */     return this.mA;
/*     */   }
/*     */ 
/*     */   public void setA(double a) {
/* 186 */     this.mA = a;
/*     */   }
/*     */ 
/*     */   public double getAngularVel()
/*     */   {
/* 191 */     return this.mAngVel;
/*     */   }
/*     */ 
/*     */   public void setAngularVel(double ang) {
/* 195 */     this.mAngVel = ang;
/*     */   }
/*     */ 
/*     */   public double getParam(int paramName) {
/* 199 */     if (paramName == 13)
/* 200 */       return getA();
/* 201 */     if (paramName == 14) {
/* 202 */       return getAngularVel();
/*     */     }
/* 204 */     return (0.0D / 0.0D);
/*     */   }
/*     */   public void setParam(int paramName, double val) {
/* 207 */     if (paramName == 13)
/* 208 */       setA(val);
/* 209 */     else if (paramName == 14)
/* 210 */       setAngularVel(val);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     spacetime.GoedelMetric
 * JD-Core Version:    0.6.0
 */