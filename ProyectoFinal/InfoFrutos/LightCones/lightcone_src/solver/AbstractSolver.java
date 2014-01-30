/*     */ package solver;
/*     */ 
/*     */ import geometry.CoordsGeometry;
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ import spacetime.AbstractMetric;
/*     */ 
/*     */ public abstract class AbstractSolver
/*     */ {
/*  12 */   protected boolean ready = false;
/*     */ 
/*  15 */   protected double[] boundBoxMin = new CoordsGeometry().min;
/*  16 */   protected double[] boundBoxMax = new CoordsGeometry().max;
/*     */   protected double epsilon_abs;
/*     */   protected double epsilon_rel;
/*     */   protected AbstractMetric mMetric;
/*  26 */   protected int mGeodType = 0;
/*  27 */   protected int mTimeDir = 1;
/*  28 */   protected int mGeodDest = 1;
/*     */ 
/*     */   public void setMetric(AbstractMetric _metric)
/*     */   {
/*  32 */     this.mMetric = _metric;
/*     */   }
/*     */   public void setGeodType(int geodType) {
/*  35 */     this.mGeodType = geodType;
/*     */   }
/*     */   public void setTimeDir(int timeDir) {
/*  38 */     this.mTimeDir = timeDir;
/*     */   }
/*     */   public void setGeodDest(int geodDest) {
/*  41 */     this.mGeodDest = geodDest;
/*     */   }
/*     */   public void setGeodCharacter(int geodType, int timeDir, int geodDest) {
/*  44 */     this.mGeodType = geodType;
/*  45 */     this.mTimeDir = timeDir;
/*  46 */     this.mGeodDest = geodDest;
/*     */   }
/*     */   public void setEpsilons(double eps_abs, double eps_rel) {
/*  49 */     this.epsilon_abs = eps_abs;
/*  50 */     this.epsilon_rel = eps_rel;
/*     */   }
/*     */ 
/*     */   public void setBoundingBox(P4D p1, P4D p2) {
/*  54 */     for (int i = 0; i < 4; i++)
/*  55 */       if (p1.getX(i) < p2.getX(i)) {
/*  56 */         this.boundBoxMin[i] = p1.getX(i);
/*  57 */         this.boundBoxMax[i] = p2.getX(i);
/*     */       }
/*     */       else {
/*  60 */         this.boundBoxMin[i] = p2.getX(i);
/*  61 */         this.boundBoxMax[i] = p1.getX(i);
/*     */       }
/*     */   }
/*     */ 
/*     */   public int getGeodType()
/*     */   {
/*  68 */     return this.mGeodType;
/*     */   }
/*     */   public int getTimeDir() {
/*  71 */     return this.mTimeDir;
/*     */   }
/*     */   public int getGeodDest() {
/*  74 */     return this.mGeodDest;
/*     */   }
/*     */   public double getEpsAbs() {
/*  77 */     return this.epsilon_abs;
/*     */   }
/*     */   public double getEpsRel() {
/*  80 */     return this.epsilon_rel;
/*     */   }
/*     */   public P4D getBoundingBoxMin() {
/*  83 */     return new P4D(this.boundBoxMin);
/*     */   }
/*     */   public P4D getBoundingBoxMax() {
/*  86 */     return new P4D(this.boundBoxMax);
/*     */   }
/*     */ 
/*     */   public abstract P4D[] calculateGeodesic(P4D paramP4D, V4D paramV4D, double paramDouble, int paramInt);
/*     */ 
/*     */   public abstract double[] derivs(double[] paramArrayOfDouble);
/*     */ 
/*     */   public V4D startCondition(P4D pos, V4D dir)
/*     */   {
/*  97 */     double[] p = pos.getData();
/*  98 */     double[] v = dir.getData();
/*     */ 
/* 104 */     this.mMetric.calculateMetric(p);
/*     */ 
/* 106 */     if (this.mGeodType != -1)
/*     */     {
/*     */       double norm;
/* 107 */       if (this.mGeodType == 0) {
/* 108 */         norm = v[1] * v[1] + v[2] * v[2] + v[3] * v[3];
/* 109 */         if (norm <= 0.0D) {
/* 110 */           return new V4D();
/*     */         }
/* 112 */         norm = Math.sqrt(norm);
/*     */       } else {
/* 114 */         norm = 1.0D;
/*     */       }
/*     */ 
/* 117 */       for (int l = 1; l < 4; l++) {
/* 118 */         v[l] /= norm;
/* 119 */         v[l] = (this.mGeodDest * v[l]);
/*     */       }
/* 121 */       double a = this.mMetric.getMetricCoeff(0, 0);
/* 122 */       double b = 0.0D;
/* 123 */       double c = 0.0D;
/*     */ 
/* 125 */       for (int i = 1; i < 4; i++) {
/* 126 */         b += this.mMetric.getMetricCoeff(0, i) * v[i];
/* 127 */         for (int j = 1; j < 4; j++) {
/* 128 */           c += this.mMetric.getMetricCoeff(i, j) * v[i] * v[j];
/*     */         }
/*     */       }
/* 131 */       b *= 2.0D;
/* 132 */       c += this.mGeodType;
/*     */ 
/* 134 */       double d = b * b - 4.0D * a * c;
/* 135 */       if (Math.abs(d) < 1.0E-10D)
/* 136 */         d = 0.0D;
/* 137 */       if (d < 0.0D) {
/* 138 */         return new V4D();
/*     */       }
/*     */ 
/* 141 */       d = Math.sqrt(d);
/* 142 */       a *= 2.0D;
/* 143 */       double u1 = (-b + d) / a;
/* 144 */       double u2 = (-b - d) / a;
/*     */       double w;
/* 147 */       if (this.mTimeDir == 1)
/* 148 */         w = u1 > u2 ? u1 : u2;
/*     */       else {
/* 150 */         w = u1 < u2 ? u1 : u2;
/*     */       }
/* 152 */       v[0] = w;
/*     */     }
/*     */     else {
/* 155 */       for (int l = 1; l < 4; l++) {
/* 156 */         v[l] = (this.mGeodDest * v[l]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 162 */     V4D nDir = new V4D(new double[] { v[0], v[1], v[2], v[3] });
/*     */ 
/* 165 */     return nDir;
/*     */   }
/*     */ 
/*     */   public double calcNormCondition(P4D pos, V4D dir) {
/* 169 */     double[] direction = dir.getData();
/*     */ 
/* 172 */     this.mMetric.calculateMetric(pos.getData());
/*     */ 
/* 174 */     double sum = 0.0D;
/* 175 */     for (int i = 0; i < 4; i++) {
/* 176 */       for (int j = 0; j < 4; j++) {
/* 177 */         sum += this.mMetric.getMetricCoeff(i, j) * direction[i] * direction[j];
/*     */       }
/*     */     }
/* 180 */     return sum + this.mGeodType;
/*     */   }
/*     */ 
/*     */   public boolean outsideBoundingBox(double[] pos)
/*     */   {
/* 188 */     return (pos[0] < this.boundBoxMin[0]) || (pos[0] > this.boundBoxMax[0]) || 
/* 186 */       (pos[1] < this.boundBoxMin[1]) || (pos[1] > this.boundBoxMax[1]) || 
/* 187 */       (pos[2] < this.boundBoxMin[2]) || (pos[2] > this.boundBoxMax[2]) || 
/* 188 */       (pos[3] < this.boundBoxMin[3]) || (pos[3] > this.boundBoxMax[3]);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     solver.AbstractSolver
 * JD-Core Version:    0.6.0
 */