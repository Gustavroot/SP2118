/*     */ package solver;
/*     */ 
/*     */ import geometry.P2D;
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ import java.io.PrintStream;
/*     */ import spacetime.AbstractMetric;
/*     */ 
/*     */ public class NumRecRungeKutta3DSolver extends AbstractSolver
/*     */ {
/*     */   public NumRecRungeKutta3DSolver()
/*     */   {
/*  15 */     this.ready = false;
/*     */   }
/*     */   public NumRecRungeKutta3DSolver(AbstractMetric metric) {
/*  18 */     this.mMetric = metric;
/*  19 */     this.epsilon_abs = 0.0001D;
/*  20 */     this.epsilon_rel = 0.0D;
/*  21 */     this.ready = true;
/*     */   }
/*     */   public NumRecRungeKutta3DSolver(AbstractMetric metric, int geodType, int timeDir, int geodDest) {
/*  24 */     this.mMetric = metric;
/*  25 */     this.epsilon_abs = 0.0001D;
/*  26 */     this.epsilon_rel = 0.0D;
/*  27 */     this.mGeodType = geodType;
/*  28 */     this.mTimeDir = timeDir;
/*  29 */     this.mGeodDest = geodDest;
/*  30 */     this.ready = true;
/*     */   }
/*     */ 
/*     */   public P2D rkqs(double[] y, double[] dydx, int n, double htry, double eps, double[] yscal, P2D hdidnext, double hmin)
/*     */   {
/*  38 */     double hdid = hdidnext.getX(0);
/*  39 */     double hnext = hdidnext.getX(1);
/*     */ 
/*  41 */     double[] yerr = new double[n];
/*  42 */     double[] ytemp = new double[n];
/*  43 */     double h = htry;
/*     */ 
/*  45 */     rkck(y, dydx, n, h, ytemp, yerr);
/*  46 */     double errmax = 0.0D;
/*  47 */     for (int i = 0; i < n; i++)
/*  48 */       errmax = FMAX(errmax, Math.abs(yerr[i] / yscal[i]));
/*  49 */     errmax /= eps;
/*  50 */     if (errmax > 1.0D)
/*     */     {
/*  52 */       double htemp = 0.9D * h * Math.pow(errmax, -0.25D);
/*  53 */       h = h >= 0.0D ? FMAX(htemp, 0.1D * h) : FMIN(htemp, 0.1D * h);
/*     */ 
/*  56 */       if (h < 0.0D)
/*  57 */         h = -hmin;
/*     */       else {
/*  59 */         h = hmin;
/*     */       }
/*     */     }
/*     */ 
/*  63 */     hdid = hnext;
/*     */ 
/*  65 */     if (errmax > 0.000189D)
/*  66 */       hnext = 0.9D * h * Math.pow(errmax, -0.2D);
/*     */     else
/*  68 */       hnext = 5.0D * h;
/*  69 */     for (int i = 0; i < n; i++) {
/*  70 */       y[i] = ytemp[i];
/*     */     }
/*  72 */     return new P2D(new double[] { hdid, hnext });
/*     */   }
/*     */ 
/*     */   public void rkck(double[] y, double[] dydx, int n, double h, double[] yout, double[] yerr)
/*     */   {
/*  79 */     double b21 = 0.2D;
/*  80 */     double b31 = 0.075D;
/*  81 */     double b32 = 0.225D;
/*  82 */     double b41 = 0.3D;
/*  83 */     double b42 = -0.9D;
/*  84 */     double b43 = 1.2D;
/*  85 */     double b51 = -0.2037037037037037D;
/*  86 */     double b52 = 2.5D;
/*  87 */     double b53 = -2.592592592592593D;
/*  88 */     double b54 = 1.296296296296296D;
/*  89 */     double b61 = 0.02949580439814815D;
/*  90 */     double b62 = 0.341796875D;
/*  91 */     double b63 = 0.04159432870370371D;
/*  92 */     double b64 = 0.4003454137731481D;
/*  93 */     double b65 = 0.061767578125D;
/*  94 */     double c1 = 0.09788359788359788D;
/*  95 */     double c3 = 0.4025764895330113D;
/*  96 */     double c4 = 0.2104377104377105D;
/*  97 */     double c6 = 0.2891022021456804D;
/*  98 */     double dc5 = -0.01932198660714286D;
/*  99 */     double dc1 = c1 - 0.1021773726851852D;
/* 100 */     double dc3 = c3 - 0.3839079034391534D;
/* 101 */     double dc4 = c4 - 0.2445927372685185D;
/* 102 */     double dc6 = c6 - 0.25D;
/*     */ 
/* 104 */     double[] ak2 = new double[n];
/* 105 */     double[] ak3 = new double[n];
/* 106 */     double[] ak4 = new double[n];
/* 107 */     double[] ak5 = new double[n];
/* 108 */     double[] ak6 = new double[n];
/* 109 */     double[] ytemp = new double[n];
/*     */ 
              int i = 0;  

/* 111 */     for (i = 0; i < n; i++)
/* 112 */       y[i] += b21 * h * dydx[i];
/* 113 */     ak2 = derivs(ytemp);
/* 114 */     for (i = 0; i < n; i++)
/* 115 */       y[i] += h * (b31 * dydx[i] + b32 * ak2[i]);
/* 116 */     ak3 = derivs(ytemp);
/* 117 */     for (i = 0; i < n; i++)
/* 118 */       y[i] += h * (b41 * dydx[i] + b42 * ak2[i] + b43 * ak3[i]);
/* 119 */     ak4 = derivs(ytemp);
/* 120 */     for (i = 0; i < n; i++)
/* 121 */       y[i] += h * (b51 * dydx[i] + b52 * ak2[i] + b53 * ak3[i] + b54 * ak4[i]);
/* 122 */     ak5 = derivs(ytemp);
/* 123 */     for (i = 0; i < n; i++)
/* 124 */       y[i] += h * (b61 * dydx[i] + b62 * ak2[i] + b63 * ak3[i] + b64 * ak4[i] + b65 * ak5[i]);
/* 125 */     ak6 = derivs(ytemp);
/* 126 */     for (i = 0; i < n; i++) {
/* 127 */       y[i] += h * (c1 * dydx[i] + c3 * ak3[i] + c4 * ak4[i] + c6 * ak6[i]);
/*     */     }
/* 129 */     for (i = 0; i < n; i++)
/* 130 */       yerr[i] = (h * (dc1 * dydx[i] + dc3 * ak3[i] + dc4 * ak4[i] + dc5 * ak5[i] + dc6 * ak6[i]));
/*     */   }
/*     */ 
/*     */   public double FMAX(double a, double b) {
/* 134 */     return a > b ? a : b;
/*     */   }
/*     */   public double FMIN(double a, double b) {
/* 137 */     return a < b ? a : b;
/*     */   }
/*     */ 
/*     */   public P4D[] calculateGeodesic(P4D yStart, V4D yDir, double lambdaStep, int maxNumPoints) {
/* 141 */     int breakCond = 2;
/*     */ 
/* 145 */     P2D hdidnext = new P2D();
/* 146 */     int numPoints = 0;
/* 147 */     P4D[] yout = new P4D[maxNumPoints];
/*     */ 
/* 149 */     yout[0] = yStart;
/* 150 */     for (int i = 1; i < maxNumPoints; i++)
/* 151 */       yout[i] = 
/* 154 */         new P4D(new double[] { (0.0D / 0.0D), 
/* 152 */         (0.0D / 0.0D), 
/* 153 */         (0.0D / 0.0D), 
/* 154 */         (0.0D / 0.0D) });
/* 155 */     P4D currPoint = new P4D(yStart);
/* 156 */     V4D currDir = new V4D(yDir);
/*     */ 
/* 158 */     double h = lambdaStep;
/* 159 */     double hmin = 1.0E-12D;
/*     */     double hdid;
/* 161 */     double hnext = hdid = hmin;
/*     */ 
/* 164 */     double[] y = new double[6];
/* 165 */     double[] yscal = new double[6];
/* 166 */     double[] yOld = new double[6];
/* 167 */     double[] dydx = new double[6];
/*     */ 
/* 169 */     for (int i = 0; i < 3; i++) {
/* 170 */       y[i] = yStart.getX(i + 1);
/* 171 */       y[(i + 3)] = yDir.getX(i + 1);
/*     */     }
/*     */ 
/* 174 */     yDir = startCondition(yStart, yDir);
/* 175 */     if (yDir == new V4D()) {
/* 176 */       return new P4D[1];
/*     */     }
/* 178 */     for (int i = 0; i < 3; i++)
/* 179 */       y[(i + 3)] = yDir.getX(i + 1);
/*     */     int nbad;
/* 181 */     int nok = nbad = -1;
/* 182 */     double intError = 0.0D;
/*     */     do
/*     */     {
/* 186 */       if (numPoints > 0)
/* 187 */         yout[numPoints].setX(0, yout[(numPoints - 1)].getX(0) + hdid);
/* 188 */       for (int i = 0; i < 3; i++) {
/* 189 */         yout[numPoints].setX(i + 1, y[i]);
/* 190 */         yOld[i] = y[i];
/* 191 */         yOld[(i + 3)] = y[(i + 3)];
/*     */       }
/*     */ 
/* 194 */       dydx = derivs(y);
/*     */ 
/* 196 */       for (int i = 0; i < 6; i++) {
/* 197 */         yscal[i] = (Math.abs(y[i]) + Math.abs(dydx[i] * h) + 1.E-30D);
/*     */       }
/* 199 */       if (breakCond == 2) {
/* 200 */         hdidnext.set(new double[] { hdid, hnext });
/* 201 */         hdidnext = rkqs(y, dydx, 6, h, this.epsilon_abs, yscal, hdidnext, hmin);
/* 202 */         hdid = hdidnext.getX(0);
/* 203 */         hnext = hdidnext.getX(1);
/* 204 */         if (hdid == h)
/* 205 */           nok++;
/*     */         else {
/* 207 */           nbad++;
/*     */         }
/* 209 */         if (Math.abs(hnext) <= hmin) {
/* 210 */           if (hnext < 0.0D)
/* 211 */             hnext = -hmin;
/*     */           else {
/* 213 */             hnext = hmin;
/*     */           }
/*     */         }
/* 216 */         h = hnext;
/*     */ 
/* 220 */         currPoint = yout[numPoints];
/* 221 */         currDir = new V4D(new double[] { hdid, y[3], y[4], y[5] });
/*     */ 
/* 223 */         numPoints++;
/* 224 */         intError = Math.abs(calcNormCondition(currPoint, currDir));
/*     */       }
/*     */ 
/* 227 */       if ((outsideBoundingBox(new double[] { 0.0D, y[0], y[1], y[2] })) || (numPoints >= maxNumPoints))
/*     */         break;
/*     */     }
/* 184 */     while (!
/* 228 */       this.mMetric.breakCondition(new double[] { 0.0D, y[0], y[1], y[2] }));
/*     */ 
/* 236 */     if ((!outsideBoundingBox(new double[] { 0.0D, y[0], y[1], y[2] })) && (numPoints < maxNumPoints) && 
/* 237 */       (intError < 1.0D)) {
/* 238 */       if (numPoints > 0)
/* 239 */         yout[numPoints].setX(0, yout[numPoints].getX(0) + hdid);
/* 240 */       for (int i = 0; i < 3; i++) {
/* 241 */         yout[numPoints].setX(i + 1, y[i]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 248 */     for (int i = numPoints; i < maxNumPoints; i++) {
/* 249 */       yout[i].setX(0, yout[(numPoints - 1)].getX(0) + (i - numPoints + 1));
/* 250 */       yout[i].setX(1, yout[(numPoints - 1)].getX(1));
/* 251 */       yout[i].setX(2, yout[(numPoints - 1)].getX(2));
/* 252 */       yout[i].setX(3, yout[(numPoints - 1)].getX(3));
/*     */     }
/*     */ 
/* 256 */     return yout;
/*     */   }
/*     */ 
/*     */   public double[] derivs(double[] y)
/*     */   {
/* 261 */     double[] dydx = new double[6];
/*     */ 
/* 263 */     dydx[0] = y[3];
/* 264 */     dydx[1] = y[4];
/* 265 */     dydx[2] = y[5];
/*     */ 
/* 267 */     this.mMetric.calculateChristoffelSymbols(new double[] { 0.0D, y[0], y[1], y[2] });
/* 268 */     System.out.println(y[0] + " " + y[1] + " " + y[2] + " " + y[3] + " " + y[4] + " " + y[5] + " ");
/*     */ 
/* 270 */     for (int k = 1; k < 4; k++) {
/* 271 */       dydx[(k + 2)] = 0.0D;
/* 272 */       for (int i = 1; i < 4; i++) {
/* 273 */         for (int j = 1; j < 4; j++) {
/* 274 */           dydx[(k + 2)] += 
/* 275 */             -(this.mMetric.getChristoffelCoeff(i, j, k) - 
/* 275 */             this.mMetric.getChristoffelCoeff(i, j, 0) * y[(k + 2)]) * 
/* 276 */             y[(i + 2)] * y[(j + 2)];
/*     */         }
/*     */       }
/* 279 */       dydx[(k + 2)] += -(this.mMetric.getChristoffelCoeff(0, 0, k) - this.mMetric.getChristoffelCoeff(0, 0, 0) * y[(k + 2)]);
/*     */     }
/*     */ 
/* 282 */     System.out.println(dydx[0] + " " + dydx[1] + " " + dydx[2] + " " + dydx[3] + " " + dydx[4] + " " + dydx[5] + " ");
/*     */ 
/* 285 */     return dydx;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     solver.NumRecRungeKutta3DSolver
 * JD-Core Version:    0.6.0
 */