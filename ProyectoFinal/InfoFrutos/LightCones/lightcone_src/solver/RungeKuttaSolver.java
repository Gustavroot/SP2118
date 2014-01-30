/*     */ package solver;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ import spacetime.AbstractMetric;
/*     */ 
/*     */ public class RungeKuttaSolver extends AbstractSolver
/*     */ {
/*     */   public P4D[] calculateGeodesic(P4D yStart, V4D yDir, double lambdaStep, int maxNumPoints)
/*     */   {
/*  11 */     double[] y = new double[8];
/*  12 */     double[] yOld = new double[8];
/*  13 */     double[] yn = new double[8];
/*  14 */     double[] dydx = new double[8];
/*  15 */     double[] k1 = new double[8];
/*  16 */     double[] k2 = new double[8];
/*  17 */     double[] k3 = new double[8];
/*  18 */     double[] k4 = new double[8];
/*     */ 
/*  20 */     int numPoints = 0;

              int i = 0;

/*  21 */     P4D[] yout = new P4D[maxNumPoints];
/*  22 */     for (i = 0; i < maxNumPoints; i++)
/*  23 */       yout[i] = 
/*  26 */         new P4D(new double[] { (0.0D / 0.0D), 
/*  24 */         (0.0D / 0.0D), 
/*  25 */         (0.0D / 0.0D), 
/*  26 */         (0.0D / 0.0D) });
/*  27 */     P4D currPoint = new P4D(yStart);
/*  28 */     V4D currDir = new V4D(yDir);
/*     */ 
/*  30 */     for (i = 0; i < 4; i++) {
/*  31 */       y[i] = yStart.getX(i);
/*  32 */       y[(i + 4)] = yDir.getX(i);
/*     */     }
/*     */ 
/*  35 */     yDir = startCondition(yStart, yDir);
/*  36 */     if (yDir == new V4D()) {
/*  37 */       return new P4D[1];
/*     */     }
/*  39 */     for (i = 0; i < 4; i++) {
/*  40 */       y[(i + 4)] = yDir.getX(i);
/*     */     }
/*  42 */     double h = lambdaStep;
/*  43 */     double intError = 0.0D;
/*     */     do
/*     */     {
/*  47 */       for (int j = 0; j < 4; j++) {
/*  48 */         yout[numPoints].setX(j, y[j]);
/*     */       }
/*     */ 
/*  51 */       dydx = derivs(y);
/*  52 */       for (i = 0; i < 8; i++) {
/*  53 */         yOld[i] = y[i];
/*  54 */         k1[i] = (h * dydx[i]);
/*  55 */         y[i] += k1[i] * 0.5D;
/*     */       }
/*  57 */       dydx = derivs(yn);
/*  58 */       for (i = 0; i < 8; i++) {
/*  59 */         k2[i] = (h * dydx[i]);
/*  60 */         y[i] += k2[i] * 0.5D;
/*     */       }
/*  62 */       dydx = derivs(yn);
/*  63 */       for (i = 0; i < 8; i++) {
/*  64 */         k3[i] = (h * dydx[i]);
/*  65 */         y[i] += k3[i];
/*     */       }
/*  67 */       dydx = derivs(yn);
/*  68 */       for (i = 0; i < 8; i++) {
/*  69 */         k4[i] = (h * dydx[i]);
/*  70 */         y[i] += (k1[i] + 2.0D * k2[i] + 2.0D * k3[i] + k4[i]) / 6.0D;
/*  71 */         y[i] = yn[i];
/*     */       }
/*     */ 
/*  74 */       currPoint = yout[numPoints];
/*  75 */       currDir = new V4D(new double[] { y[4], y[5], y[6], y[7] });
/*     */ 
/*  77 */       numPoints++;
/*  78 */       intError = Math.abs(calcNormCondition(currPoint, currDir));
/*     */     }
/*  80 */     while ((!outsideBoundingBox(y)) && (numPoints < maxNumPoints) && 
/*  81 */       (!this.mMetric.breakCondition(y)) && (intError < 1.0D));
/*     */ 
/*  83 */     if ((!outsideBoundingBox(y)) && (numPoints < maxNumPoints) && 
/*  84 */       (intError < 1.0D)) {
/*  85 */       for (int j = 0; j < 4; j++) {
/*  86 */         yout[numPoints].setX(j, y[j]);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  94 */     for (i = numPoints; i < maxNumPoints; i++) {
/*  95 */       yout[i].setX(0, yout[(numPoints - 1)].getX(0) + (i - numPoints + 1));
/*  96 */       yout[i].setX(1, yout[(numPoints - 1)].getX(1));
/*  97 */       yout[i].setX(2, yout[(numPoints - 1)].getX(2));
/*  98 */       yout[i].setX(3, yout[(numPoints - 1)].getX(3));
/*     */     }
/*     */ 
/* 102 */     return yout;
/*     */   }
/*     */ 
/*     */   public double[] derivs(double[] y) {
/* 106 */     double[] dydx = new double[8];
/* 107 */     dydx[0] = y[4];
/* 108 */     dydx[1] = y[5];
/* 109 */     dydx[2] = y[6];
/* 110 */     dydx[3] = y[7];
/*     */ 
/* 112 */     this.mMetric.calculateChristoffelSymbols(y);
/*     */ 
/* 114 */     for (int k = 0; k < 4; k++) {
/* 115 */       dydx[(k + 4)] = 0.0D;
/* 116 */       for (int i = 0; i < 4; i++) {
/* 117 */         for (int j = 0; j < 4; j++) {
/* 118 */           dydx[(k + 4)] += -this.mMetric.getChristoffelCoeff(i, j, k) * y[(i + 4)] * y[(j + 4)];
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 123 */     return dydx;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     solver.RungeKuttaSolver
 * JD-Core Version:    0.6.0
 */