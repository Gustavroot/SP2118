/*     */ package spacetime;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ 
/*     */ public class SchwarzschildMetric extends AbstractMetric
/*     */ {
/*     */   private double mMass;
/*     */ 
/*     */   public SchwarzschildMetric()
/*     */   {
/*  13 */     this.mMass = 1.0D;
/*  14 */     this.cr.min[2] = 0.0D; this.cr.max[2] = 1.570796326794897D; this.cr.type[2] = 5;
/*  15 */     this.cr.min[3] = 0.0D; this.cr.max[3] = 6.283185307179586D; this.cr.type[3] = 5;
/*  16 */     this.cr.sys = 1;
/*     */   }
/*     */ 
/*     */   public void calculateMetric(double[] pos)
/*     */   {
/*  25 */     double r = pos[1];
/*  26 */     double theta = pos[2];
/*     */ 
/*  30 */     double rs = 2.0D * this.mMass;
/*     */ 
/*  33 */     double t1 = 1.0D;
/*  34 */     double t3 = 1.0D / r;
/*  35 */     double t9 = r * r;
/*  36 */     double t10 = Math.sin(theta);
/*  37 */     double t11 = t10 * t10;
/*     */ 
/*  39 */     this.g_compts[0][0] = (-t1 + t1 * rs * t3);
/*  40 */     this.g_compts[0][1] = 0.0D;
/*  41 */     this.g_compts[0][2] = 0.0D;
/*  42 */     this.g_compts[0][3] = 0.0D;
/*  43 */     this.g_compts[1][0] = 0.0D;
/*  44 */     this.g_compts[1][1] = (1.0D / (1.0D - rs * t3));
/*  45 */     this.g_compts[1][2] = 0.0D;
/*  46 */     this.g_compts[1][3] = 0.0D;
/*  47 */     this.g_compts[2][0] = 0.0D;
/*  48 */     this.g_compts[2][1] = 0.0D;
/*  49 */     this.g_compts[2][2] = t9;
/*  50 */     this.g_compts[2][3] = 0.0D;
/*  51 */     this.g_compts[3][0] = 0.0D;
/*  52 */     this.g_compts[3][1] = 0.0D;
/*  53 */     this.g_compts[3][2] = 0.0D;
/*  54 */     this.g_compts[3][3] = (t9 * t11);
/*     */   }
/*     */ 
/*     */   public void calculateChristoffelSymbols(double[] pos)
/*     */   {
/*  61 */     double r = pos[1];
/*  62 */     double theta = pos[2];
/*     */ 
/*  65 */     double rs = 2.0D * this.mMass;
/*     */ 
/*  67 */     double t1 = r - rs;
/*  68 */     double t2 = r * r;
/*  69 */     double t6 = 1.0D;
/*  70 */     double t10 = 1.0D / r;
/*  71 */     double t14 = t10 / t1 * rs / 2.0D;
/*  72 */     double t15 = Math.sin(theta);
/*  73 */     double t17 = Math.cos(theta);
/*  74 */     double t18 = 1.0D / t15 * t17;
/*  75 */     double t19 = t15 * t15;
/*     */ 
/*  77 */     this.christoffel[0][0][0] = 0.0D;
/*  78 */     this.christoffel[0][0][1] = (t1 / t2 / r * t6 * rs / 2.0D);
/*  79 */     this.christoffel[0][0][2] = 0.0D;
/*  80 */     this.christoffel[0][0][3] = 0.0D;
/*  81 */     this.christoffel[0][1][0] = t14;
/*  82 */     this.christoffel[0][1][1] = 0.0D;
/*  83 */     this.christoffel[0][1][2] = 0.0D;
/*  84 */     this.christoffel[0][1][3] = 0.0D;
/*  85 */     this.christoffel[0][2][0] = 0.0D;
/*  86 */     this.christoffel[0][2][1] = 0.0D;
/*  87 */     this.christoffel[0][2][2] = 0.0D;
/*  88 */     this.christoffel[0][2][3] = 0.0D;
/*  89 */     this.christoffel[0][3][0] = 0.0D;
/*  90 */     this.christoffel[0][3][1] = 0.0D;
/*  91 */     this.christoffel[0][3][2] = 0.0D;
/*  92 */     this.christoffel[0][3][3] = 0.0D;
/*  93 */     this.christoffel[1][0][0] = t14;
/*  94 */     this.christoffel[1][0][1] = 0.0D;
/*  95 */     this.christoffel[1][0][2] = 0.0D;
/*  96 */     this.christoffel[1][0][3] = 0.0D;
/*  97 */     this.christoffel[1][1][0] = 0.0D;
/*  98 */     this.christoffel[1][1][1] = (-t14);
/*  99 */     this.christoffel[1][1][2] = 0.0D;
/* 100 */     this.christoffel[1][1][3] = 0.0D;
/* 101 */     this.christoffel[1][2][0] = 0.0D;
/* 102 */     this.christoffel[1][2][1] = 0.0D;
/* 103 */     this.christoffel[1][2][2] = t10;
/* 104 */     this.christoffel[1][2][3] = 0.0D;
/* 105 */     this.christoffel[1][3][0] = 0.0D;
/* 106 */     this.christoffel[1][3][1] = 0.0D;
/* 107 */     this.christoffel[1][3][2] = 0.0D;
/* 108 */     this.christoffel[1][3][3] = t10;
/* 109 */     this.christoffel[2][0][0] = 0.0D;
/* 110 */     this.christoffel[2][0][1] = 0.0D;
/* 111 */     this.christoffel[2][0][2] = 0.0D;
/* 112 */     this.christoffel[2][0][3] = 0.0D;
/* 113 */     this.christoffel[2][1][0] = 0.0D;
/* 114 */     this.christoffel[2][1][1] = 0.0D;
/* 115 */     this.christoffel[2][1][2] = t10;
/* 116 */     this.christoffel[2][1][3] = 0.0D;
/* 117 */     this.christoffel[2][2][0] = 0.0D;
/* 118 */     this.christoffel[2][2][1] = (-t1);
/* 119 */     this.christoffel[2][2][2] = 0.0D;
/* 120 */     this.christoffel[2][2][3] = 0.0D;
/* 121 */     this.christoffel[2][3][0] = 0.0D;
/* 122 */     this.christoffel[2][3][1] = 0.0D;
/* 123 */     this.christoffel[2][3][2] = 0.0D;
/* 124 */     this.christoffel[2][3][3] = t18;
/* 125 */     this.christoffel[3][0][0] = 0.0D;
/* 126 */     this.christoffel[3][0][1] = 0.0D;
/* 127 */     this.christoffel[3][0][2] = 0.0D;
/* 128 */     this.christoffel[3][0][3] = 0.0D;
/* 129 */     this.christoffel[3][1][0] = 0.0D;
/* 130 */     this.christoffel[3][1][1] = 0.0D;
/* 131 */     this.christoffel[3][1][2] = 0.0D;
/* 132 */     this.christoffel[3][1][3] = t10;
/* 133 */     this.christoffel[3][2][0] = 0.0D;
/* 134 */     this.christoffel[3][2][1] = 0.0D;
/* 135 */     this.christoffel[3][2][2] = 0.0D;
/* 136 */     this.christoffel[3][2][3] = t18;
/* 137 */     this.christoffel[3][3][0] = 0.0D;
/* 138 */     this.christoffel[3][3][1] = (-t1 * t19);
/* 139 */     this.christoffel[3][3][2] = (-t15 * t17);
/* 140 */     this.christoffel[3][3][3] = 0.0D;
/*     */   }
/*     */ 
/*     */   public V4D localToCoord(P4D pos, V4D oldDir, int localFrameType)
/*     */   {
/* 146 */     double r = pos.getX(1);
/* 147 */     double theta = pos.getX(2);
/*     */ 
/* 150 */     double vtCam = oldDir.getX(0);
/* 151 */     double vrCam = oldDir.getX(1);
/* 152 */     double vthetaCam = oldDir.getX(2);
/* 153 */     double vphiCam = oldDir.getX(3);
/*     */ 
/* 155 */     double[] nDir = new double[4];
/*     */ 
/* 157 */     double rs = 2.0D * this.mMass;
/*     */ 
/* 159 */     nDir[0] = (vtCam / Math.sqrt(1.0D - rs / r));
/* 160 */     nDir[1] = (vrCam * Math.sqrt(1.0D - rs / r));
/* 161 */     nDir[2] = (vthetaCam / r);
/* 162 */     nDir[3] = (vphiCam / (r * Math.sin(theta)));
/*     */ 
/* 164 */     return new V4D(nDir);
/*     */   }
/*     */ 
/*     */   public boolean breakCondition(double[] pos) {
/* 168 */     boolean br = false;
/*     */ 
/* 170 */     double rs = 2.0D * this.mMass;
/*     */ 
/* 172 */     if ((pos[1] < 0.0D) || (pos[1] * pos[1] <= 1.0000000001D * rs * rs)) {
/* 173 */       br = true;
/*     */     }
/* 175 */     return br;
/*     */   }
/*     */ 
/*     */   public double getMass()
/*     */   {
/* 180 */     return this.mMass;
/*     */   }
/*     */ 
/*     */   public void setMass(double m) {
/* 184 */     this.mMass = m;
/*     */   }
/*     */ 
/*     */   public double getParam(int paramName) {
/* 188 */     if (paramName == 10) {
/* 189 */       return getMass();
/*     */     }
/* 191 */     return (0.0D / 0.0D);
/*     */   }
/*     */   public void setParam(int paramName, double val) {
/* 194 */     if (paramName == 10)
/* 195 */       setMass(val);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     spacetime.SchwarzschildMetric
 * JD-Core Version:    0.6.0
 */