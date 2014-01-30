/*     */ package spacetime;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ 
/*     */ public class DeSitterMetric extends AbstractMetric
/*     */ {
/*     */   private double mL;
/*     */ 
/*     */   public DeSitterMetric()
/*     */   {
/*  13 */     this.mL = 1.0D;
/*     */   }
/*     */ 
/*     */   public boolean breakCondition(double[] pos) {
/*  17 */     return false;
/*     */   }
/*     */ 
/*     */   public void calculateMetric(double[] pos) {
/*  21 */     double t = pos[0];
/*  22 */     double l = this.mL;
/*     */ 
/*  24 */     double t4 = Math.exp(2.0D * t / l);
/*  25 */     this.g_compts[0][0] = -1.0D;
/*  26 */     this.g_compts[0][1] = 0.0D;
/*  27 */     this.g_compts[0][2] = 0.0D;
/*  28 */     this.g_compts[0][3] = 0.0D;
/*  29 */     this.g_compts[1][0] = 0.0D;
/*  30 */     this.g_compts[1][1] = t4;
/*  31 */     this.g_compts[1][2] = 0.0D;
/*  32 */     this.g_compts[1][3] = 0.0D;
/*  33 */     this.g_compts[2][0] = 0.0D;
/*  34 */     this.g_compts[2][1] = 0.0D;
/*  35 */     this.g_compts[2][2] = t4;
/*  36 */     this.g_compts[2][3] = 0.0D;
/*  37 */     this.g_compts[3][0] = 0.0D;
/*  38 */     this.g_compts[3][1] = 0.0D;
/*  39 */     this.g_compts[3][2] = 0.0D;
/*  40 */     this.g_compts[3][3] = t4;
/*     */   }
/*     */ 
/*     */   public void calculateChristoffelSymbols(double[] pos)
/*     */   {
/*  45 */     double t = pos[0];
/*  46 */     double l = this.mL;
/*     */ 
/*  48 */     double t1 = 1.0D / l;
/*  49 */     double t4 = Math.exp(2.0D * t * t1);
/*  50 */     double t5 = t1 * t4;
/*  51 */     this.christoffel[0][0][0] = 0.0D;
/*  52 */     this.christoffel[0][0][1] = 0.0D;
/*  53 */     this.christoffel[0][0][2] = 0.0D;
/*  54 */     this.christoffel[0][0][3] = 0.0D;
/*  55 */     this.christoffel[0][1][0] = 0.0D;
/*  56 */     this.christoffel[0][1][1] = t1;
/*  57 */     this.christoffel[0][1][2] = 0.0D;
/*  58 */     this.christoffel[0][1][3] = 0.0D;
/*  59 */     this.christoffel[0][2][0] = 0.0D;
/*  60 */     this.christoffel[0][2][1] = 0.0D;
/*  61 */     this.christoffel[0][2][2] = t1;
/*  62 */     this.christoffel[0][2][3] = 0.0D;
/*  63 */     this.christoffel[0][3][0] = 0.0D;
/*  64 */     this.christoffel[0][3][1] = 0.0D;
/*  65 */     this.christoffel[0][3][2] = 0.0D;
/*  66 */     this.christoffel[0][3][3] = t1;
/*  67 */     this.christoffel[1][0][0] = 0.0D;
/*  68 */     this.christoffel[1][0][1] = t1;
/*  69 */     this.christoffel[1][0][2] = 0.0D;
/*  70 */     this.christoffel[1][0][3] = 0.0D;
/*  71 */     this.christoffel[1][1][0] = t5;
/*  72 */     this.christoffel[1][1][1] = 0.0D;
/*  73 */     this.christoffel[1][1][2] = 0.0D;
/*  74 */     this.christoffel[1][1][3] = 0.0D;
/*  75 */     this.christoffel[1][2][0] = 0.0D;
/*  76 */     this.christoffel[1][2][1] = 0.0D;
/*  77 */     this.christoffel[1][2][2] = 0.0D;
/*  78 */     this.christoffel[1][2][3] = 0.0D;
/*  79 */     this.christoffel[1][3][0] = 0.0D;
/*  80 */     this.christoffel[1][3][1] = 0.0D;
/*  81 */     this.christoffel[1][3][2] = 0.0D;
/*  82 */     this.christoffel[1][3][3] = 0.0D;
/*  83 */     this.christoffel[2][0][0] = 0.0D;
/*  84 */     this.christoffel[2][0][1] = 0.0D;
/*  85 */     this.christoffel[2][0][2] = t1;
/*  86 */     this.christoffel[2][0][3] = 0.0D;
/*  87 */     this.christoffel[2][1][0] = 0.0D;
/*  88 */     this.christoffel[2][1][1] = 0.0D;
/*  89 */     this.christoffel[2][1][2] = 0.0D;
/*  90 */     this.christoffel[2][1][3] = 0.0D;
/*  91 */     this.christoffel[2][2][0] = t5;
/*  92 */     this.christoffel[2][2][1] = 0.0D;
/*  93 */     this.christoffel[2][2][2] = 0.0D;
/*  94 */     this.christoffel[2][2][3] = 0.0D;
/*  95 */     this.christoffel[2][3][0] = 0.0D;
/*  96 */     this.christoffel[2][3][1] = 0.0D;
/*  97 */     this.christoffel[2][3][2] = 0.0D;
/*  98 */     this.christoffel[2][3][3] = 0.0D;
/*  99 */     this.christoffel[3][0][0] = 0.0D;
/* 100 */     this.christoffel[3][0][1] = 0.0D;
/* 101 */     this.christoffel[3][0][2] = 0.0D;
/* 102 */     this.christoffel[3][0][3] = t1;
/* 103 */     this.christoffel[3][1][0] = 0.0D;
/* 104 */     this.christoffel[3][1][1] = 0.0D;
/* 105 */     this.christoffel[3][1][2] = 0.0D;
/* 106 */     this.christoffel[3][1][3] = 0.0D;
/* 107 */     this.christoffel[3][2][0] = 0.0D;
/* 108 */     this.christoffel[3][2][1] = 0.0D;
/* 109 */     this.christoffel[3][2][2] = 0.0D;
/* 110 */     this.christoffel[3][2][3] = 0.0D;
/* 111 */     this.christoffel[3][3][0] = t5;
/* 112 */     this.christoffel[3][3][1] = 0.0D;
/* 113 */     this.christoffel[3][3][2] = 0.0D;
/* 114 */     this.christoffel[3][3][3] = 0.0D;
/*     */   }
/*     */ 
/*     */   public V4D localToCoord(P4D pos, V4D oldDir, int localFrameType) {
/* 118 */     double t = pos.getX(0);
/* 119 */     double x = pos.getX(1);
/* 120 */     double y = pos.getX(2);
/* 121 */     double z = pos.getX(3);
/*     */ 
/* 123 */     double vtCam = oldDir.getX(0);
/* 124 */     double vxCam = oldDir.getX(1);
/* 125 */     double vyCam = oldDir.getX(2);
/* 126 */     double vzCam = oldDir.getX(3);
/*     */ 
/* 128 */     double[] nDir = new double[4];
/*     */ 
/* 130 */     double l = this.mL;
/*     */ 
/* 132 */     nDir[0] = vtCam;
/* 133 */     nDir[1] = (vxCam * Math.exp(-t / l));
/* 134 */     nDir[2] = (vyCam * Math.exp(-t / l));
/* 135 */     nDir[3] = (vzCam * Math.exp(-t / l));
/*     */ 
/* 137 */     return new V4D(nDir);
/*     */   }
/*     */ 
/*     */   public double getL()
/*     */   {
/* 142 */     return this.mL;
/*     */   }
/*     */ 
/*     */   public void setL(double l) {
/* 146 */     this.mL = l;
/*     */   }
/*     */ 
/*     */   public double getParam(int paramName) {
/* 150 */     if (paramName == 16) {
/* 151 */       return getL();
/*     */     }
/* 153 */     return (0.0D / 0.0D);
/*     */   }
/*     */   public void setParam(int paramName, double val) {
/* 156 */     if (paramName == 16)
/* 157 */       setL(val);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     spacetime.DeSitterMetric
 * JD-Core Version:    0.6.0
 */