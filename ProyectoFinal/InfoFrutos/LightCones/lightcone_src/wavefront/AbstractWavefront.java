/*     */ package wavefront;
/*     */ 
/*     */ import geometry.P2D;
/*     */ import geometry.P4D;
/*     */ import geometry.PnD;
/*     */ import geometry.RotMat3D;
/*     */ import geometry.V3D;
/*     */ import geometry.V4D;
/*     */ import java.awt.Graphics;
/*     */ import javax.swing.JProgressBar;
/*     */ import solver.AbstractSolver;
/*     */ import spacetime.AbstractMetric;
/*     */ 
/*     */ public abstract class AbstractWavefront
/*     */ {
/*     */   P4D[][][] wavefront_data;
/*     */   protected P4D startPos;
/*     */   protected V4D startDirLoc;
/*     */   protected double tStart;
/*     */   protected double tObsStart;
/*     */   protected double tStep;
/*     */   protected double tEnd;
/*     */   protected int numRaysTheta;
/*     */   protected int numRaysPhi;
/*     */   protected double thetaMin;
/*     */   protected double thetaMax;
/*     */   protected double phiMin;
/*     */   protected double phiMax;
/*     */   protected double[] thetaArray;
/*     */   protected double[] phiArray;
/*  80 */   V3D[] rotAxes = new V3D[2];
/*  81 */   protected boolean rotAxesAreSet = false;
/*     */   protected AbstractMetric mMetric;
/*     */   protected AbstractSolver mSolver;
/*     */   protected double lambdaStep;
/*     */   protected int maxNumPoints;
/*     */   protected int realMaxNumPoints;
/*     */ 
/*     */   public P4D[][][] getWaveFront()
/*     */   {
/*  23 */     return this.wavefront_data;
/*     */   }
/*     */ 
/*     */   public P4D getStartPos()
/*     */   {
/*  34 */     return this.startPos; } 
/*  35 */   public void setStartPos(P4D pos) { this.startPos = pos; } 
/*  36 */   public V4D getStartDirLoc() { return this.startDirLoc; } 
/*  37 */   public void setStartDirLoc(V4D dir) { this.startDirLoc = dir; } 
/*     */   public P4D getTimes() {
/*  39 */     return new P4D(new double[] { this.tStart, this.tObsStart, this.tStep, this.tEnd });
/*     */   }
/*     */   public void setTimes(P4D times) {
/*  42 */     this.tStart = times.getX(0); this.tObsStart = times.getX(1);
/*  43 */     this.tStep = times.getX(2); this.tEnd = times.getX(3);
/*     */   }
/*     */ 
/*     */   public P2D getNumRays()
/*     */   {
/*  52 */     return new P2D(new double[] { this.numRaysTheta, this.numRaysPhi });
/*     */   }
/*     */   public void setNumRays(P2D numRays) {
/*  55 */     this.numRaysTheta = (int)numRays.getX(0); this.numRaysPhi = (int)numRays.getX(1);
/*     */   }
/*     */   public P2D getThetaSpan() {
/*  58 */     return new P2D(new double[] { this.thetaMin, this.thetaMax });
/*     */   }
/*     */   public void setThetaSpan(P2D thetaSpan) {
/*  61 */     this.thetaMin = thetaSpan.getX(0); this.thetaMax = thetaSpan.getX(1);
/*     */   }
/*     */   public P2D getPhiSpan() {
/*  64 */     return new P2D(new double[] { this.phiMin, this.phiMax });
/*     */   }
/*     */   public void setPhiSpan(P2D phiSpan) {
/*  67 */     this.phiMin = phiSpan.getX(0); this.phiMax = phiSpan.getX(1);
/*     */   }
/*     */ 
/*     */   public double[] getThetaArray()
/*     */   {
/*  72 */     return this.thetaArray;
/*     */   }
/*     */   public double[] getPhiArray() {
/*  75 */     return this.phiArray;
/*     */   }
/*     */ 
/*     */   public V3D[] getRotAxes()
/*     */   {
/*  83 */     return this.rotAxes;
/*     */   }
/*     */   public void setRotAxes(V3D[] axes) {
/*  86 */     this.rotAxes[0] = axes[0];
/*  87 */     this.rotAxes[1] = axes[1];
/*  88 */     this.rotAxesAreSet = true;
/*     */   }
/*     */ 
/*     */   public AbstractMetric getMetric()
/*     */   {
/*  94 */     return this.mMetric;
/*     */   }
/*     */   public void setMetric(AbstractMetric metric) {
/*  97 */     this.mMetric = metric;
/*     */   }
/*     */ 
/*     */   public AbstractSolver getSolver()
/*     */   {
/* 103 */     return this.mSolver;
/*     */   }
/*     */   public void setSolver(AbstractSolver solver) {
/* 106 */     this.mSolver = solver;
/*     */   }
/*     */ 
/*     */   public double getLambdaStep() {
/* 110 */     return this.lambdaStep;
/*     */   }
/*     */   public void setLambdaStep(double step) {
/* 113 */     this.lambdaStep = step;
/*     */   }
/*     */ 
/*     */   public int getMaxNumPoints()
/*     */   {
/* 118 */     return this.maxNumPoints;
/*     */   }
/*     */   public void setMaxNumPoints(int numPts) {
/* 121 */     this.maxNumPoints = numPts;
/* 122 */     this.realMaxNumPoints = numPts;
/*     */   }
/*     */ 
/*     */   public void calculate(JProgressBar progress, Graphics g)
/*     */   {
/* 130 */     int numPics = (int)((this.tEnd - this.tObsStart) / this.tStep) + 1;
/*     */ 
/* 132 */     this.wavefront_data = new P4D[this.numRaysTheta][][];
/* 133 */     for (int i = 0; i < this.numRaysTheta; i++) {
/* 134 */       this.wavefront_data[i] = new P4D[this.numRaysPhi][];
/*     */     }
/*     */ 
/* 138 */     calcRotAxes();
/* 139 */     calcStartAng();
/*     */ 
/* 141 */     progress.setMinimum(0);
/* 142 */     progress.setMaximum(this.numRaysTheta * this.numRaysPhi);
/* 143 */     progress.setValue(0);
/*     */ 
/* 146 */     for (int i = 0; i < this.numRaysTheta; i++) {
/* 147 */       for (int j = 0; j < this.numRaysPhi; j++) {
/* 148 */         V4D startDir = calcStartDir(i, j);
/* 149 */         P4D[] ray = calcGeodesic(startDir);
/* 150 */         this.wavefront_data[i][j] = calcPositions(ray, i, j, numPics);
/*     */ 
/* 153 */         progress.setValue(i * this.numRaysPhi + j);
/* 154 */         progress.update(g);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 162 */     transCart(numPics);
/*     */   }
/*     */   public abstract void calcRotAxes();
/*     */ 
/*     */   public abstract void calcStartAng();
/*     */ 
/* 170 */   public V4D calcStartDir(int i, int j) { V3D startDirLocSpace = new V3D(new double[] { this.startDirLoc.getX(1), 
/* 171 */       this.startDirLoc.getX(2), 
/* 172 */       this.startDirLoc.getX(3) });
/*     */ 
/* 175 */     RotMat3D rotMat1 = new RotMat3D(this.rotAxes[0], this.thetaArray[i]);
/* 176 */     RotMat3D rotMat2 = new RotMat3D(this.rotAxes[1], this.phiArray[j]);
/*     */ 
/* 178 */     V3D rotDirLoc = rotMat1.mul(rotMat2.mul(startDirLocSpace));
/* 179 */     V4D newStartDirLoc = new V4D(new double[] { 
/* 180 */       0.0D, rotDirLoc.getX(0), rotDirLoc.getX(1), rotDirLoc.getX(2) });
/*     */ 
/* 185 */     V4D newStartDir = this.mMetric.localToCoord(this.startPos, newStartDirLoc, this.mMetric.getCurrFrame());
/*     */ 
/* 187 */     return newStartDir; } 
/*     */   public P4D[] calcGeodesic(V4D newStartDir) {
/* 195 */     int iter = 1;
/*     */     P4D[] ray;
/*     */     do {
/* 197 */       this.realMaxNumPoints *= iter;
/* 198 */       ray = this.mSolver.calculateGeodesic(this.startPos, newStartDir, this.lambdaStep, this.realMaxNumPoints);
/* 199 */       iter++;
/* 200 */     }while (ray[(ray.length - 1)].getX(0) < this.tEnd);
/*     */ 
/* 204 */     return ray;
/*     */   }
/*     */ 
/*     */   public P4D[] calcPositions(P4D[] ray, int i, int j, int numPics)
/*     */   {
/* 210 */     double tNow = this.tObsStart;
/* 211 */     int counter = 0;
/* 212 */     int timeDir = this.mSolver.getTimeDir();
/* 213 */     P4D[] positions = new P4D[numPics];
/*     */ 
/* 215 */     for (int k = 0; k < numPics; k++) {
/* 216 */       positions[k] = new P4D();
/*     */     }
/*     */ 
/* 219 */     for (int l = 0; l < ray.length; l++) {
/* 220 */       double time = ray[l].getX(0);
/*     */ 
/* 223 */       if (counter > numPics - 1)
/*     */       {
/*     */         break;
/*     */       }
/* 227 */       if ((timeDir * time < timeDir * tNow) || (timeDir * tNow > timeDir * this.tEnd) || (timeDir * time < this.tObsStart + this.tStep * counter))
/*     */         continue;
/*     */       P4D pos;
/* 231 */       if (l != 0) {
/* 232 */         double delta = ray[l].getX(0) - ray[(l - 1)].getX(0);
/* 233 */         double diff = tNow - ray[(l - 1)].getX(0);
/* 234 */         pos = (P4D)ray[(l - 1)].add((P4D)ray[l].sub(ray[(l - 1)]).mul(diff / delta));
/*     */       }
/*     */       else {
/* 237 */         pos = ray[0];
/*     */       }
/*     */ 
/* 240 */       positions[counter] = 
/* 243 */         new P4D(new double[] { pos.getX(0), 
/* 241 */         pos.getX(1), 
/* 242 */         pos.getX(2), 
/* 243 */         pos.getX(3) });
/* 244 */       counter++;
/* 245 */       l--;
/* 246 */       tNow = this.tObsStart + this.tStep * counter;
/*     */     }
/*     */ 
/* 250 */     return positions;
/*     */   }
/*     */ 
/*     */   public void transCart(int numPics) {
/* 254 */     int currSys = this.mMetric.getCoordSys();
/* 255 */     if (currSys == 0) {
/* 256 */       return;
/*     */     }
/* 258 */     for (int i = 0; i < this.numRaysTheta; i++)
/* 259 */       for (int j = 0; j < this.numRaysPhi; j++)
/* 260 */         for (int k = 0; k < numPics; k++)
/* 261 */           this.wavefront_data[i][j][k] = this.mMetric
/* 263 */             .coordTransf(this.wavefront_data[i][j][k], 
/* 262 */             currSys, 
/* 263 */             0);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     wavefront.AbstractWavefront
 * JD-Core Version:    0.6.0
 */