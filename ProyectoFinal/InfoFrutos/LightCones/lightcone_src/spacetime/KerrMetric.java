/*     */ package spacetime;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ 
/*     */ public class KerrMetric extends AbstractMetric
/*     */ {
/*     */   private double mMass;
/*     */   private double mAng;
/*     */   private double mHorizont;
/*     */ 
/*     */   public KerrMetric()
/*     */   {
/*  14 */     this.mMass = 1.0D;
/*  15 */     this.mAng = 0.1D;
/*  16 */     this.mHorizont = (this.mMass + Math.sqrt(this.mMass * this.mMass - this.mAng * this.mAng));
/*     */ 
/*  18 */     this.cr.min[2] = 0.0D; this.cr.max[2] = 1.570796326794897D; this.cr.type[2] = 5;
/*  19 */     this.cr.min[3] = 0.0D; this.cr.max[3] = 6.283185307179586D; this.cr.type[3] = 5;
/*  20 */     this.cr.sys = 1;
/*     */   }
/*     */ 
/*     */   public void setMass(double mass) {
/*  24 */     this.mMass = mass;
/*  25 */     this.mHorizont = (this.mMass + Math.sqrt(this.mMass * this.mMass - this.mAng * this.mAng));
/*     */   }
/*     */   public double getMass() {
/*  28 */     return this.mMass;
/*     */   }
/*     */   public void setAng(double ang) {
/*  31 */     this.mAng = ang;
/*  32 */     this.mHorizont = (this.mMass + Math.sqrt(this.mMass * this.mMass - this.mAng * this.mAng));
/*     */   }
/*     */   public double getAng() {
/*  35 */     return this.mAng;
/*     */   }
/*     */   public double getHorizont() {
/*  38 */     return this.mHorizont;
/*     */   }
/*     */ 
/*     */   public void setParam(int paramName, double val) {
/*  42 */     if (paramName == 10)
/*  43 */       setMass(val);
/*  44 */     else if (paramName == 11)
/*  45 */       setAng(val);
/*     */   }
/*     */ 
/*     */   public double getParam(int paramName)
/*     */   {
/*  50 */     if (paramName == 10)
/*  51 */       return getMass();
/*  52 */     if (paramName == 11)
/*  53 */       return getAng();
/*  54 */     if (paramName == 12) {
/*  55 */       return getHorizont();
/*     */     }
/*  57 */     return (0.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public void calculateMetric(double[] pos)
/*     */   {
/*  63 */     double r = pos[1];
/*  64 */     double theta = pos[2];
/*     */ 
/*  66 */     double m = this.mMass;
/*  67 */     double a = this.mAng;
/*     */ 
/*  70 */     double t1 = r * r;
/*  71 */     double t3 = 2.0D * m * r;
/*  72 */     double t4 = a * a;
/*  73 */     double t5 = Math.cos(theta);
/*  74 */     double t6 = t5 * t5;
/*  75 */     double t7 = t4 * t6;
/*  76 */     double t9 = t1 + t7;
/*  77 */     double t10 = 1.0D / t9;
/*  78 */     double t13 = -1.0D + t6;
/*  79 */     double t17 = 2.0D * m * a * r * t13 * t10;
/*  80 */     double t21 = t4 * m;
/*  81 */     double t25 = t1 * t4;
/*  82 */     double t27 = t4 * t4;
/*  83 */     double t31 = t1 * t1;
/*     */ 
/*  85 */     this.g_compts[0][0] = (-(t1 - t3 + t7) * t10);
/*  86 */     this.g_compts[0][1] = 0.0D;
/*  87 */     this.g_compts[0][2] = 0.0D;
/*  88 */     this.g_compts[0][3] = t17;
/*  89 */     this.g_compts[1][0] = 0.0D;
/*  90 */     this.g_compts[1][1] = (t9 / (t1 - t3 + t4));
/*  91 */     this.g_compts[1][2] = 0.0D;
/*  92 */     this.g_compts[1][3] = 0.0D;
/*  93 */     this.g_compts[2][0] = 0.0D;
/*  94 */     this.g_compts[2][1] = 0.0D;
/*  95 */     this.g_compts[2][2] = t9;
/*  96 */     this.g_compts[2][3] = 0.0D;
/*  97 */     this.g_compts[3][0] = t17;
/*  98 */     this.g_compts[3][1] = 0.0D;
/*  99 */     this.g_compts[3][2] = 0.0D;
/* 100 */     this.g_compts[3][3] = (-(-2.0D * t21 * r * t6 + t25 * t6 + t27 * t6 + 2.0D * t21 * r + t31 + t25) * t10 * t13);
/*     */   }
/*     */ 
/*     */   public void calculateChristoffelSymbols(double[] pos)
/*     */   {
/* 106 */     double r = pos[1];
/* 107 */     double theta = pos[2];
/*     */ 
/* 109 */     double m = this.mMass;
/* 110 */     double a = this.mAng;
/*     */ 
/* 112 */     double t1 = r * r;
/* 113 */     double t2 = m * r;
/* 114 */     double t4 = a * a;
/* 115 */     double t5 = t1 - 2.0D * t2 + t4;
/* 116 */     double t7 = Math.cos(theta);
/* 117 */     double t8 = t7 * t7;
/* 118 */     double t9 = t4 * t8;
/* 119 */     double t10 = -t1 + t9;
/* 120 */     double t11 = t1 * t1;
/* 121 */     double t12 = t11 * t1;
/* 122 */     double t13 = t11 * t4;
/* 123 */     double t14 = t13 * t8;
/* 124 */     double t16 = t4 * t4;
/* 125 */     double t17 = t8 * t8;
/* 126 */     double t18 = t16 * t17;
/* 127 */     double t19 = t18 * t1;
/* 128 */     double t21 = t16 * t4;
/* 129 */     double t22 = t17 * t8;
/* 130 */     double t25 = 1.0D / (t12 + 3.0D * t14 + 3.0D * t19 + t21 * t22);
/* 131 */     double t29 = Math.sin(theta);
/* 132 */     double t30 = t4 * t7 * t29;
/* 133 */     double t34 = t1 + t4;
/* 134 */     double t36 = 1.0D / t5;
/* 135 */     double t38 = t1 * t4;
/* 136 */     double t40 = 2.0D * t38 * t8;
/* 137 */     double t42 = 1.0D / (t18 + t40 + t11);
/* 138 */     double t43 = t10 * t36 * t42;
/* 139 */     double t44 = m * t34 * t43;
/* 140 */     double t45 = a * m;
/* 141 */     double t46 = t45 * t43;
/* 142 */     double t49 = 2.0D * t30 * t2 * t42;
/* 143 */     double t50 = t45 * r;
/* 144 */     double t51 = t7 * t29;
/* 145 */     double t52 = t11 * t8;
/* 146 */     double t53 = t38 * t17;
/* 147 */     double t55 = t16 * t22;
/* 148 */     double t57 = 1.0D / (-t11 + t52 - t40 + 2.0D * t53 - t18 + t55);
/* 149 */     double t60 = 2.0D * t50 * t51 * t57;
/* 150 */     double t61 = t8 * t1;
/* 151 */     double t66 = t45 * t5 * (-t61 + t4 * t17 + t1 - t9) * t25;
/* 152 */     double t70 = 2.0D * t50 * t51 * t34 * t25;
/* 153 */     double t72 = 1.0D / (t1 + t9);
/* 154 */     double t75 = r * t4;
/* 155 */     double t77 = m * t4;
/* 156 */     double t81 = t72 * t4;
/* 157 */     double t84 = t81 * t51;
/* 158 */     double t85 = t72 * r;
/* 159 */     double t92 = t45 * (t18 - t16 * t8 - t53 + t38 - 3.0D * t52 + 3.0D * t11) * t36 * t42;
/* 160 */     double t93 = m * t16;
/* 161 */     double t94 = t93 * t17;
/* 162 */     double t95 = t18 * r;
/* 163 */     double t96 = t93 * t8;
/* 164 */     double t98 = t1 * r * t4;
/* 165 */     double t100 = 2.0D * t98 * t8;
/* 166 */     double t101 = t77 * t61;
/* 167 */     double t102 = t77 * t1;
/* 168 */     double t105 = t11 * r;
/* 169 */     double t108 = (t94 - t95 - t96 - t100 + t101 + t102 + 2.0D * t11 * m - t105) * t36 * t42;
/* 170 */     double t118 = 2.0D * m * t4 * a * r * t51 * (-1.0D + t8) * t42;
/* 171 */     double t126 = t51 * (t18 + t40 - 2.0D * t77 * r * t8 + 2.0D * t77 * r + t11) * t57;
/* 172 */     double t136 = -t77 * t17 * t1 + 2.0D * t101 + t93 * t22 - 2.0D * t94 - 2.0D * t98 * t17 + t100 - t55 * r + t95 - t102 + t96 - t105 * t8 + t105;
/*     */ 
/* 174 */     this.christoffel[0][0][0] = 0.0D;
/* 175 */     this.christoffel[0][0][1] = (-m * t5 * t10 * t25);
/* 176 */     this.christoffel[0][0][2] = (-2.0D * t30 * t2 * t25);
/* 177 */     this.christoffel[0][0][3] = 0.0D;
/* 178 */     this.christoffel[0][1][0] = (-t44);
/* 179 */     this.christoffel[0][1][1] = 0.0D;
/* 180 */     this.christoffel[0][1][2] = 0.0D;
/* 181 */     this.christoffel[0][1][3] = (-t46);
/* 182 */     this.christoffel[0][2][0] = (-t49);
/* 183 */     this.christoffel[0][2][1] = 0.0D;
/* 184 */     this.christoffel[0][2][2] = 0.0D;
/* 185 */     this.christoffel[0][2][3] = t60;
/* 186 */     this.christoffel[0][3][0] = 0.0D;
/* 187 */     this.christoffel[0][3][1] = (-t66);
/* 188 */     this.christoffel[0][3][2] = t70;
/* 189 */     this.christoffel[0][3][3] = 0.0D;
/* 190 */     this.christoffel[1][0][0] = (-t44);
/* 191 */     this.christoffel[1][0][1] = 0.0D;
/* 192 */     this.christoffel[1][0][2] = 0.0D;
/* 193 */     this.christoffel[1][0][3] = (-t46);
/* 194 */     this.christoffel[1][1][0] = 0.0D;
/* 195 */     this.christoffel[1][1][1] = (t36 * t72 * (-m * t1 + t75 - t75 * t8 + t77 * t8));
/* 196 */     this.christoffel[1][1][2] = (t81 * t51 * t36);
/* 197 */     this.christoffel[1][1][3] = 0.0D;
/* 198 */     this.christoffel[1][2][0] = 0.0D;
/* 199 */     this.christoffel[1][2][1] = (-t84);
/* 200 */     this.christoffel[1][2][2] = t85;
/* 201 */     this.christoffel[1][2][3] = 0.0D;
/* 202 */     this.christoffel[1][3][0] = (-t92);
/* 203 */     this.christoffel[1][3][1] = 0.0D;
/* 204 */     this.christoffel[1][3][2] = 0.0D;
/* 205 */     this.christoffel[1][3][3] = (-t108);
/* 206 */     this.christoffel[2][0][0] = (-t49);
/* 207 */     this.christoffel[2][0][1] = 0.0D;
/* 208 */     this.christoffel[2][0][2] = 0.0D;
/* 209 */     this.christoffel[2][0][3] = t60;
/* 210 */     this.christoffel[2][1][0] = 0.0D;
/* 211 */     this.christoffel[2][1][1] = (-t84);
/* 212 */     this.christoffel[2][1][2] = t85;
/* 213 */     this.christoffel[2][1][3] = 0.0D;
/* 214 */     this.christoffel[2][2][0] = 0.0D;
/* 215 */     this.christoffel[2][2][1] = (-t5 * t72 * r);
/* 216 */     this.christoffel[2][2][2] = (-t84);
/* 217 */     this.christoffel[2][2][3] = 0.0D;
/* 218 */     this.christoffel[2][3][0] = (-t118);
/* 219 */     this.christoffel[2][3][1] = 0.0D;
/* 220 */     this.christoffel[2][3][2] = 0.0D;
/* 221 */     this.christoffel[2][3][3] = (-t126);
/* 222 */     this.christoffel[3][0][0] = 0.0D;
/* 223 */     this.christoffel[3][0][1] = (-t66);
/* 224 */     this.christoffel[3][0][2] = t70;
/* 225 */     this.christoffel[3][0][3] = 0.0D;
/* 226 */     this.christoffel[3][1][0] = (-t92);
/* 227 */     this.christoffel[3][1][1] = 0.0D;
/* 228 */     this.christoffel[3][1][2] = 0.0D;
/* 229 */     this.christoffel[3][1][3] = (-t108);
/* 230 */     this.christoffel[3][2][0] = (-t118);
/* 231 */     this.christoffel[3][2][1] = 0.0D;
/* 232 */     this.christoffel[3][2][2] = 0.0D;
/* 233 */     this.christoffel[3][2][3] = (-t126);
/* 234 */     this.christoffel[3][3][0] = 0.0D;
/* 235 */     this.christoffel[3][3][1] = (-t5 * t136 * t25);
/* 236 */     this.christoffel[3][3][2] = (-t51 * (4.0D * t98 * m + t19 + t13 + t21 * t17 + t12 - 4.0D * t98 * m * t8 - 2.0D * t18 * t2 + 2.0D * t93 * r + 2.0D * t14 + 2.0D * t1 * t16 * t8) * t25);
/* 237 */     this.christoffel[3][3][3] = 0.0D;
/*     */   }
/*     */ 
/*     */   public V4D localToCoord(P4D pos, V4D oldDir, int localFrameType) {
/* 241 */     double t = pos.getX(0);
/* 242 */     double r = pos.getX(1);
/* 243 */     double theta = pos.getX(2);
/* 244 */     double phi = pos.getX(3);
/*     */ 
/* 246 */     double vtCam = oldDir.getX(0);
/* 247 */     double vrCam = oldDir.getX(1);
/* 248 */     double vthetaCam = oldDir.getX(2);
/* 249 */     double vphiCam = oldDir.getX(3);
/*     */ 
/* 252 */     double rho2 = r * r + this.mAng * this.mAng * Math.cos(theta) * Math.cos(theta);
/* 253 */     double delta = r * r - 2.0D * this.mMass * r + this.mAng * this.mAng;
/* 254 */     double A = (r * r + this.mAng * this.mAng) * (r * r + this.mAng * this.mAng) - this.mAng * this.mAng * delta * Math.sin(theta) * Math.sin(theta);
/* 255 */     double omega = 2.0D * this.mMass * this.mAng * r / A;
/*     */ 
/* 257 */     double[] nDir = new double[4];
/*     */ 
/* 259 */     if (localFrameType == 8)
/*     */     {
/* 261 */       nDir[0] = (vtCam * Math.sqrt(rho2 / (rho2 - 2.0D * this.mMass * r)) + vphiCam * 2.0D * this.mMass * r * this.mAng / Math.sqrt(rho2 * delta) * Math.sqrt(Math.pow(Math.sin(theta), 2.0D) / (rho2 - 2.0D * this.mMass * r)));
/* 262 */       nDir[1] = (vrCam * Math.sqrt(delta / rho2));
/* 263 */       nDir[2] = (vthetaCam / Math.sqrt(rho2));
/* 264 */       nDir[3] = (vphiCam * Math.sqrt((rho2 - 2.0D * this.mMass * r) / (rho2 * delta * Math.pow(Math.sin(theta), 2.0D))));
/*     */     }
/* 267 */     else if ((localFrameType == 7) || (localFrameType == 9))
/*     */     {
/* 269 */       nDir[0] = (vtCam * Math.sqrt(A / (rho2 * delta)));
/* 270 */       nDir[1] = (vrCam * Math.sqrt(delta / rho2));
/* 271 */       nDir[2] = (vthetaCam / Math.sqrt(rho2));
/* 272 */       nDir[3] = (vtCam * omega * Math.sqrt(A / (rho2 * delta)) + vphiCam * Math.sqrt(rho2 / A) / Math.sin(theta));
/*     */     }
/*     */ 
/* 275 */     return new V4D(nDir);
/*     */   }
/*     */ 
/*     */   public boolean breakCondition(double[] pos)
/*     */   {
/* 280 */     boolean br = false;
/* 281 */     if (pos[1] <= this.mHorizont + 1.0E-10D) {
/* 282 */       br = true;
/*     */     }
/* 284 */     return br;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     spacetime.KerrMetric
 * JD-Core Version:    0.6.0
 */