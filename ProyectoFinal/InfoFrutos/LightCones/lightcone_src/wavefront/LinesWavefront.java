/*     */ package wavefront;
/*     */ 
/*     */ import geometry.V3D;
/*     */ import geometry.V4D;
/*     */ 
/*     */ public class LinesWavefront extends AbstractWavefront
/*     */ {
/*     */   public void calcRotAxes()
/*     */   {
/*   8 */     if (this.rotAxesAreSet) {
/*   9 */       return;
/*     */     }
/*     */ 
/*  19 */     V3D startDirLocSpace = new V3D(new double[] { this.startDirLoc.getX(1), 
/*  20 */       this.startDirLoc.getX(2), 
/*  21 */       this.startDirLoc.getX(3) });
/*     */ 
/*  23 */     startDirLocSpace = (V3D)startDirLocSpace.getNormalized();
/*  24 */     double x1Norm = startDirLocSpace.getX(0);
/*  25 */     double x2Norm = startDirLocSpace.getX(1);
/*  26 */     double x3Norm = startDirLocSpace.getX(2);
/*     */ 
/*  28 */     V3D rotAxis1 = new V3D();
/*  29 */     V3D rotAxis2 = new V3D();
/*  30 */     boolean foundRotAxes = false;
/*     */ 
/*  33 */     if ((Math.abs(x1Norm) == 1.0D) && (Math.abs(x2Norm) == 0.0D) && (Math.abs(x3Norm) == 0.0D))
/*     */     {
/*  35 */       rotAxis1 = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*  36 */       rotAxis2 = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  37 */       foundRotAxes = true;
/*     */     }
/*  39 */     if ((Math.abs(x1Norm) == 0.0D) && (Math.abs(x2Norm) == 1.0D) && (Math.abs(x3Norm) == 0.0D))
/*     */     {
/*  41 */       rotAxis1 = new V3D(new double[] { 1.0D, 0.0D, 0.0D });
/*  42 */       rotAxis2 = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  43 */       foundRotAxes = true;
/*     */     }
/*  45 */     if ((Math.abs(x1Norm) == 0.0D) && (Math.abs(x2Norm) == 0.0D) && (Math.abs(x3Norm) == 1.0D))
/*     */     {
/*  47 */       rotAxis1 = new V3D(new double[] { 1.0D, 0.0D, 0.0D });
/*  48 */       rotAxis2 = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*  49 */       foundRotAxes = true;
/*     */     }
/*     */ 
/*  56 */     if (!foundRotAxes) {
/*  57 */       if ((Math.abs(x1Norm) != 0.0D) && (Math.abs(x2Norm) != 0.0D)) {
/*  58 */         rotAxis1 = new V3D(new double[] { x2Norm, -x1Norm, 0.0D });
/*  59 */         rotAxis2 = (V3D)startDirLocSpace.cro(rotAxis1);
/*  60 */         foundRotAxes = true;
/*     */       }
/*  62 */       if ((Math.abs(x2Norm) != 0.0D) && (Math.abs(x3Norm) != 0.0D) && (!foundRotAxes)) {
/*  63 */         rotAxis1 = new V3D(new double[] { 0.0D, x3Norm, -x2Norm });
/*  64 */         rotAxis2 = (V3D)startDirLocSpace.cro(rotAxis1);
/*  65 */         foundRotAxes = true;
/*     */       }
/*  67 */       if ((Math.abs(x1Norm) != 0.0D) && (Math.abs(x3Norm) != 0.0D) && (!foundRotAxes)) {
/*  68 */         rotAxis1 = new V3D(new double[] { x3Norm, 0.0D, -x1Norm });
/*  69 */         rotAxis2 = (V3D)startDirLocSpace.cro(rotAxis1);
/*  70 */         foundRotAxes = true;
/*     */       }
/*     */     }
/*     */ 
/*  74 */     this.rotAxes[0] = rotAxis1;
/*  75 */     this.rotAxes[1] = rotAxis2;
/*     */   }
/*     */ 
/*     */   public void calcStartAng()
/*     */   {
/*  82 */     this.thetaArray = new double[this.numRaysTheta];
/*  83 */     this.phiArray = new double[this.numRaysPhi];
/*     */ 
/*  85 */     double thetaRangeLoc = this.thetaMax - this.thetaMin;
/*  86 */     double phiRangeLoc = this.phiMax - this.phiMin;
/*     */     double thetaStep;
/*  90 */     if ((Math.abs(thetaRangeLoc - 6.283185307179586D) < 1.0E-08D) && (this.numRaysTheta != 1)) {
/*  91 */       thetaStep = thetaRangeLoc / this.numRaysTheta;
/*     */     }
/*     */     else
/*  94 */       thetaStep = thetaRangeLoc / (this.numRaysTheta - 1);
/*     */     double phiStep;
/*  99 */     if ((Math.abs(phiRangeLoc - 6.283185307179586D) < 1.0E-08D) && (this.numRaysPhi != 1)) {
/* 100 */       phiStep = phiRangeLoc / this.numRaysPhi;
/*     */     }
/*     */     else {
/* 103 */       phiStep = phiRangeLoc / (this.numRaysPhi - 1);
/*     */     }
/*     */ 
/* 119 */     if (this.numRaysTheta == 1) {
/* 120 */       this.thetaArray[0] = (thetaRangeLoc / 2.0D + this.thetaMin);
/*     */     }
/*     */     else {
/* 123 */       for (int I = 0; I < this.numRaysTheta; I++) {
/* 124 */         this.thetaArray[I] = (thetaStep * I + this.thetaMin);
/*     */       }
/*     */     }
/*     */ 
/* 128 */     if (this.numRaysPhi == 1) {
/* 129 */       this.phiArray[0] = (phiRangeLoc / 2.0D + this.phiMin);
/*     */     }
/*     */     else
/* 132 */       for (int J = 0; J < this.numRaysPhi; J++)
/* 133 */         this.phiArray[J] = (phiStep * J + this.phiMin);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     wavefront.LinesWavefront
 * JD-Core Version:    0.6.0
 */