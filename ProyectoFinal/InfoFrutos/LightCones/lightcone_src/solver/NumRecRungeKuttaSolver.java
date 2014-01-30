/*     */ package solver;
/*     */ 
/*     */ import geometry.P2D;
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ import spacetime.AbstractMetric;
/*     */ 
/*     */ public class NumRecRungeKuttaSolver extends AbstractSolver
/*     */ {
/*     */   public NumRecRungeKuttaSolver()
/*     */   {
/*  11 */     this.ready = false;
/*     */   }
/*     */   public NumRecRungeKuttaSolver(AbstractMetric metric) {
/*  14 */     this.mMetric = metric;
/*  15 */     this.epsilon_abs = 0.0001D;
/*  16 */     this.epsilon_rel = 0.0D;
/*  17 */     this.ready = true;
/*     */   }
/*     */   public NumRecRungeKuttaSolver(AbstractMetric metric, int geodType, int timeDir, int geodDest) {
/*  20 */     this.mMetric = metric;
/*  21 */     this.epsilon_abs = 0.0001D;
/*  22 */     this.epsilon_rel = 0.0D;
/*  23 */     this.mGeodType = geodType;
/*  24 */     this.mTimeDir = timeDir;
/*  25 */     this.mGeodDest = geodDest;
/*  26 */     this.ready = true;
/*     */   }
/*     */ 
/*     */   public P2D rkqs(double[] y, double[] dydx, int n, double htry, double eps, double[] yscal, P2D hdidnext, double hmin)
/*     */   {
/*  34 */     double hdid = hdidnext.getX(0);
/*  35 */     double hnext = hdidnext.getX(1);
/*     */ 
/*  37 */     double[] yerr = new double[n];
/*  38 */     double[] ytemp = new double[n];
/*  39 */     double h = htry;
/*     */ 
/*  41 */     rkck(y, dydx, n, h, ytemp, yerr);
/*  42 */     double errmax = 0.0D;
/*  43 */     for (int i = 0; i < n; i++)
/*  44 */       errmax = FMAX(errmax, Math.abs(yerr[i] / yscal[i]));
/*  45 */     errmax /= eps;
/*  46 */     if (errmax > 1.0D)
/*     */     {
/*  48 */       double htemp = 0.9D * h * Math.pow(errmax, -0.25D);
/*  49 */       h = h >= 0.0D ? FMAX(htemp, 0.1D * h) : FMIN(htemp, 0.1D * h);
/*     */ 
/*  52 */       if (h < 0.0D)
/*  53 */         h = -hmin;
/*     */       else {
/*  55 */         h = hmin;
/*     */       }
/*     */     }
/*     */ 
/*  59 */     hdid = hnext;
/*     */ 
/*  61 */     if (errmax > 0.000189D)
/*  62 */       hnext = 0.9D * h * Math.pow(errmax, -0.2D);
/*     */     else
/*  64 */       hnext = 5.0D * h;
/*  65 */     for (int i = 0; i < n; i++) {
/*  66 */       y[i] = ytemp[i];
/*     */     }
/*  68 */     return new P2D(new double[] { hdid, hnext });
/*     */   }
/*     */ 
/*     */   public void rkck(double[] y, double[] dydx, int n, double h, double[] yout, double[] yerr)
/*     */   {
/*  75 */     double b21 = 0.2D;
/*  76 */     double b31 = 0.075D;
/*  77 */     double b32 = 0.225D;
/*  78 */     double b41 = 0.3D;
/*  79 */     double b42 = -0.9D;
/*  80 */     double b43 = 1.2D;
/*  81 */     double b51 = -0.2037037037037037D;
/*  82 */     double b52 = 2.5D;
/*  83 */     double b53 = -2.592592592592593D;
/*  84 */     double b54 = 1.296296296296296D;
/*  85 */     double b61 = 0.02949580439814815D;
/*  86 */     double b62 = 0.341796875D;
/*  87 */     double b63 = 0.04159432870370371D;
/*  88 */     double b64 = 0.4003454137731481D;
/*  89 */     double b65 = 0.061767578125D;
/*  90 */     double c1 = 0.09788359788359788D;
/*  91 */     double c3 = 0.4025764895330113D;
/*  92 */     double c4 = 0.2104377104377105D;
/*  93 */     double c6 = 0.2891022021456804D;
/*  94 */     double dc5 = -0.01932198660714286D;
/*  95 */     double dc1 = c1 - 0.1021773726851852D;
/*  96 */     double dc3 = c3 - 0.3839079034391534D;
/*  97 */     double dc4 = c4 - 0.2445927372685185D;
/*  98 */     double dc6 = c6 - 0.25D;
/*     */ 
/* 100 */     double[] ak2 = new double[n];
/* 101 */     double[] ak3 = new double[n];
/* 102 */     double[] ak4 = new double[n];
/* 103 */     double[] ak5 = new double[n];
/* 104 */     double[] ak6 = new double[n];
/* 105 */     double[] ytemp = new double[n];
/*     */ 
/* 107 */     for (int i = 0; i < n; i++)
/* 108 */       y[i] += b21 * h * dydx[i];
/* 109 */     ak2 = derivs(ytemp);
/* 110 */     for (int i = 0; i < n; i++)
/* 111 */       y[i] += h * (b31 * dydx[i] + b32 * ak2[i]);
/* 112 */     ak3 = derivs(ytemp);
/* 113 */     for (int i = 0; i < n; i++)
/* 114 */       y[i] += h * (b41 * dydx[i] + b42 * ak2[i] + b43 * ak3[i]);
/* 115 */     ak4 = derivs(ytemp);
/* 116 */     for (int i = 0; i < n; i++)
/* 117 */       y[i] += h * (b51 * dydx[i] + b52 * ak2[i] + b53 * ak3[i] + b54 * ak4[i]);
/* 118 */     ak5 = derivs(ytemp);
/* 119 */     for (int i = 0; i < n; i++)
/* 120 */       y[i] += h * (b61 * dydx[i] + b62 * ak2[i] + b63 * ak3[i] + b64 * ak4[i] + b65 * ak5[i]);
/* 121 */     ak6 = derivs(ytemp);
/* 122 */     for (int i = 0; i < n; i++) {
/* 123 */       y[i] += h * (c1 * dydx[i] + c3 * ak3[i] + c4 * ak4[i] + c6 * ak6[i]);
/*     */     }
/* 125 */     for (int i = 0; i < n; i++)
/* 126 */       yerr[i] = (h * (dc1 * dydx[i] + dc3 * ak3[i] + dc4 * ak4[i] + dc5 * ak5[i] + dc6 * ak6[i]));
/*     */   }
/*     */ 
/*     */   public double FMAX(double a, double b) {
/* 130 */     return a > b ? a : b;
/*     */   }
/*     */   public double FMIN(double a, double b) {
/* 133 */     return a < b ? a : b;
/*     */   }
/*     */ 
/*     */   public P4D[] calculateGeodesic(P4D yStart, V4D yDir, double lambdaStep, int maxNumPoints) {
/* 137 */     int breakCond = 2;
/*     */ 
/* 141 */     P2D hdidnext = new P2D();
/* 142 */     int numPoints = 0;
/* 143 */     P4D[] yout = new P4D[maxNumPoints];
/* 144 */     for (int i = 0; i < maxNumPoints; i++)
/* 145 */       yout[i] = 
/* 148 */         new P4D(new double[] { (0.0D / 0.0D), 
/* 146 */         (0.0D / 0.0D), 
/* 147 */         (0.0D / 0.0D), 
/* 148 */         (0.0D / 0.0D) });
/* 149 */     P4D currPoint = new P4D(yStart);
/* 150 */     V4D currDir = new V4D(yDir);
/*     */ 
/* 152 */     double h = lambdaStep;
/* 153 */     double hmin = 1.0E-12D;
/*     */     double hdid;
/* 155 */     double hnext = hdid = hmin;
/*     */ 
/* 158 */     double[] y = new double[8];
/* 159 */     double[] yscal = new double[8];
/* 160 */     double[] yOld = new double[8];
/* 161 */     double[] dydx = new double[8];
/*     */ 
/* 163 */     for (int i = 0; i < 4; i++) {
/* 164 */       y[i] = yStart.getX(i);
/* 165 */       y[(i + 4)] = yDir.getX(i);
/*     */     }
/*     */ 
/* 168 */     yDir = startCondition(yStart, yDir);
/* 169 */     if (yDir == new V4D()) {
/* 170 */       return new P4D[1];
/*     */     }
/*     */ 
/* 173 */     double tiny = 1.0E-07D;
/* 174 */     if ((Math.abs(yDir.getX(1)) < tiny) && (Math.abs(yDir.getX(3)) < tiny)) {
/* 175 */       yDir.setX(3, tiny);
/* 176 */       yDir.setX(1, tiny);
/* 177 */       yDir = startCondition(yStart, yDir);
/*     */     }
/*     */ 
/* 181 */     for (int i = 0; i < 4; i++)
/* 182 */       y[(i + 4)] = yDir.getX(i);
/*     */     int nbad;
/* 184 */     int nok = nbad = -1;
/* 185 */     double intError = 0.0D;
/*     */     do
/*     */     {
/* 189 */       for (int i = 0; i < 4; i++) {
/* 190 */         yout[numPoints].setX(i, y[i]);
/* 191 */         yOld[i] = y[i];
/* 192 */         yOld[(i + 4)] = y[(i + 4)];
/*     */       }
/*     */ 
/* 195 */       dydx = derivs(y);
/*     */ 
/* 197 */       for (int i = 0; i < 8; i++) {
/* 198 */         yscal[i] = (Math.abs(y[i]) + Math.abs(dydx[i] * h) + 1.E-30D);
/*     */       }
/* 200 */       if (breakCond == 2) {
/* 201 */         hdidnext.set(new double[] { hdid, hnext });
/* 202 */         hdidnext = rkqs(y, dydx, 8, h, this.epsilon_abs, yscal, hdidnext, hmin);
/* 203 */         hdid = hdidnext.getX(0);
/* 204 */         hnext = hdidnext.getX(1);
/* 205 */         if (hdid == h)
/* 206 */           nok++;
/*     */         else {
/* 208 */           nbad++;
/*     */         }
/* 210 */         if (Math.abs(hnext) <= hmin) {
/* 211 */           if (hnext < 0.0D)
/* 212 */             hnext = -hmin;
/*     */           else {
/* 214 */             hnext = hmin;
/*     */           }
/*     */         }
/* 217 */         h = hnext;
/*     */ 
/* 221 */         currPoint = yout[numPoints];
/* 222 */         currDir = new V4D(new double[] { y[4], y[5], y[6], y[7] });
/*     */ 
/* 224 */         numPoints++;
/* 225 */         intError = Math.abs(calcNormCondition(currPoint, currDir));
/*     */       }
/*     */     }
/* 228 */     while ((!outsideBoundingBox(y)) && (numPoints < maxNumPoints) && (!
/* 229 */       this.mMetric.breakCondition(y)));
/*     */ 
/* 242 */     if ((!outsideBoundingBox(y)) && (numPoints < maxNumPoints) && 
/* 243 */       (intError < 1.0D)) {
/* 244 */       for (int j = 0; j < 4; j++) {
/* 245 */         yout[numPoints].setX(j, y[j]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 253 */     for (int i = numPoints; i < maxNumPoints; i++) {
/* 254 */       yout[i].setX(0, yout[(numPoints - 1)].getX(0) + (i - numPoints + 1));
/* 255 */       yout[i].setX(1, yout[(numPoints - 1)].getX(1));
/* 256 */       yout[i].setX(2, yout[(numPoints - 1)].getX(2));
/* 257 */       yout[i].setX(3, yout[(numPoints - 1)].getX(3));
/*     */     }
/*     */ 
/* 261 */     return yout;
/*     */   }
/*     */ 
/*     */   public double[] derivs(double[] y)
/*     */   {
/* 266 */     double[] dydx = new double[8];
/* 267 */     dydx[0] = y[4];
/* 268 */     dydx[1] = y[5];
/* 269 */     dydx[2] = y[6];
/* 270 */     dydx[3] = y[7];
/*     */ 
/* 272 */     this.mMetric.calculateChristoffelSymbols(y);
/*     */ 
/* 274 */     for (int k = 0; k < 4; k++) {
/* 275 */       dydx[(k + 4)] = 0.0D;
/* 276 */       for (int i = 0; i < 4; i++) {
/* 277 */         for (int j = 0; j < 4; j++) {
/* 278 */           dydx[(k + 4)] += -this.mMetric.getChristoffelCoeff(i, j, k) * y[(i + 4)] * y[(j + 4)];
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 284 */     return dydx;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     solver.NumRecRungeKuttaSolver
 * JD-Core Version:    0.6.0
 */