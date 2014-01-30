/*     */ package spacetime;
/*     */ 
/*     */ import geometry.CoordsGeometry;
/*     */ import geometry.P4D;
/*     */ import geometry.TransCoordinates;
/*     */ import geometry.V4D;
/*     */ import java.text.DecimalFormat;
/*     */ 
/*     */ public abstract class AbstractMetric
/*     */ {
/*  13 */   protected double[][] g_compts = new double[4][4];
/*     */ 
/*  16 */   protected double[][][] christoffel = new double[4][4][4];
/*     */ 
/*  19 */   protected CoordsGeometry cr = new CoordsGeometry();
/*     */ 
/*  28 */   protected int currFrame = 7;
/*     */ 
/*     */   public abstract void setParam(int paramInt, double paramDouble);
/*     */ 
/*     */   public abstract double getParam(int paramInt);
/*     */ 
/*  30 */   public int getCurrFrame() { return this.currFrame; }
/*     */ 
/*     */   public void setFrame(int currlf) {
/*  33 */     this.currFrame = currlf;
/*     */   }
/*     */ 
/*     */   public P4D coordTransf(P4D oldPos, int oldCoordSys, int newCoordSys)
/*     */   {
/*  38 */     P4D newPos = oldPos;
/*  39 */     switch (oldCoordSys) {
/*     */     case 0:
/*  41 */       switch (newCoordSys) {
/*     */       case 1:
/*  43 */         newPos = TransCoordinates.transCartSph(oldPos);
/*  44 */         break;
/*     */       case 2:
/*  46 */         newPos = TransCoordinates.transCartCyl(oldPos);
/*     */       }
/*     */ 
/*  51 */       break;
/*     */     case 1:
/*  53 */       switch (newCoordSys) {
/*     */       case 0:
/*  55 */         newPos = TransCoordinates.transSphCart(oldPos);
/*  56 */         break;
/*     */       case 2:
/*  58 */         newPos = TransCoordinates.transSphCart(oldPos);
/*  59 */         newPos = TransCoordinates.transCartCyl(newPos);
/*     */       case 1:
/*     */       }
/*  62 */       break;
/*     */     case 2:
/*  64 */       switch (newCoordSys) {
/*     */       case 0:
/*  66 */         newPos = TransCoordinates.transCylCart(oldPos);
/*  67 */         break;
/*     */       case 1:
/*  69 */         newPos = TransCoordinates.transCylCart(oldPos);
/*  70 */         newPos = TransCoordinates.transCartSph(newPos);
/*     */       }
/*     */ 
/*  73 */       break;
/*     */     }
/*     */ 
/*  78 */     return newPos; } 
/*     */   public abstract void calculateMetric(double[] paramArrayOfDouble);
/*     */ 
/*     */   public abstract void calculateChristoffelSymbols(double[] paramArrayOfDouble);
/*     */ 
/*     */   public abstract V4D localToCoord(P4D paramP4D, V4D paramV4D, int paramInt);
/*     */ 
/*     */   public abstract boolean breakCondition(double[] paramArrayOfDouble);
/*     */ 
/*  90 */   public double getMetricCoeff(int mu, int nu) { return this.g_compts[mu][nu]; }
/*     */ 
/*     */   public double[][] getMetric() {
/*  93 */     return this.g_compts;
/*     */   }
/*     */   public double getChristoffelCoeff(int mu, int nu, int kappa) {
/*  96 */     return this.christoffel[mu][nu][kappa];
/*     */   }
/*     */   public double[][][] getChristoffel() {
/*  99 */     return this.christoffel;
/*     */   }
/*     */   public double[] getCoordMin() {
/* 102 */     return this.cr.min;
/*     */   }
/*     */   public double[] getCoordMax() {
/* 105 */     return this.cr.max;
/*     */   }
/*     */   public int[] getCoordType() {
/* 108 */     return this.cr.type;
/*     */   }
/*     */   public int getCoordSys() {
/* 111 */     return this.cr.sys;
/*     */   }
/*     */ 
/*     */   public String printMetric()
/*     */   {
/* 116 */     String metric_string = new String();
/* 117 */     DecimalFormat f = new DecimalFormat("0.000");
/*     */ 
/* 119 */     metric_string = metric_string + "Metric:\n";
/* 120 */     for (int mu = 0; mu < 4; mu++) {
/* 121 */       for (int nu = 0; nu < 4; nu++) {
/* 122 */         metric_string = metric_string + f.format(this.g_compts[mu][nu]) + "\t";
/*     */       }
/* 124 */       metric_string = metric_string + "\n";
/*     */     }
/* 126 */     metric_string = metric_string + "\n";
/*     */ 
/* 128 */     return metric_string;
/*     */   }
/*     */ 
/*     */   public String printChristoffel() {
/* 132 */     String christoffel_string = new String();
/* 133 */     DecimalFormat f = new DecimalFormat("0.000");
/*     */ 
/* 135 */     christoffel_string = christoffel_string + "Christoffel:\n";
/* 136 */     for (int mu = 0; mu < 4; mu++) {
/* 137 */       for (int nu = 0; nu < 4; nu++) {
/* 138 */         for (int kappa = 0; kappa < 4; kappa++) {
/* 139 */           christoffel_string = christoffel_string + mu + " " + nu + " " + kappa + ": ";
/* 140 */           christoffel_string = christoffel_string + f.format(this.christoffel[mu][nu][kappa]) + "\n";
/*     */         }
/*     */       }
/*     */     }
/* 144 */     christoffel_string = christoffel_string + "\n";
/*     */ 
/* 146 */     return christoffel_string;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     spacetime.AbstractMetric
 * JD-Core Version:    0.6.0
 */