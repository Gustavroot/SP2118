/*     */ package spacetime;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.V4D;
/*     */ 
/*     */ public class MinkowskiMetric extends AbstractMetric
/*     */ {
/*     */   public void setParam(int paramName, double val)
/*     */   {
/*     */   }
/*     */ 
/*     */   public double getParam(int paramName)
/*     */   {
/*  20 */     return (0.0D / 0.0D);
/*     */   }
/*     */ 
/*     */   public void calculateMetric(double[] pos) {
/*  24 */     this.g_compts[0][0] = -1.0D;
/*  25 */     this.g_compts[0][1] = 0.0D;
/*  26 */     this.g_compts[0][2] = 0.0D;
/*  27 */     this.g_compts[0][3] = 0.0D;
/*  28 */     this.g_compts[1][0] = 0.0D;
/*  29 */     this.g_compts[1][1] = 1.0D;
/*  30 */     this.g_compts[1][2] = 0.0D;
/*  31 */     this.g_compts[1][3] = 0.0D;
/*  32 */     this.g_compts[2][0] = 0.0D;
/*  33 */     this.g_compts[2][1] = 0.0D;
/*  34 */     this.g_compts[2][2] = 1.0D;
/*  35 */     this.g_compts[2][3] = 0.0D;
/*  36 */     this.g_compts[3][0] = 0.0D;
/*  37 */     this.g_compts[3][1] = 0.0D;
/*  38 */     this.g_compts[3][2] = 0.0D;
/*  39 */     this.g_compts[3][3] = 1.0D;
/*     */   }
/*     */ 
/*     */   public void calculateChristoffelSymbols(double[] pos)
/*     */   {
/*  44 */     this.christoffel[0][0][0] = 0.0D;
/*  45 */     this.christoffel[0][0][1] = 0.0D;
/*  46 */     this.christoffel[0][0][2] = 0.0D;
/*  47 */     this.christoffel[0][0][3] = 0.0D;
/*  48 */     this.christoffel[0][1][0] = 0.0D;
/*  49 */     this.christoffel[0][1][1] = 0.0D;
/*  50 */     this.christoffel[0][1][2] = 0.0D;
/*  51 */     this.christoffel[0][1][3] = 0.0D;
/*  52 */     this.christoffel[0][2][0] = 0.0D;
/*  53 */     this.christoffel[0][2][1] = 0.0D;
/*  54 */     this.christoffel[0][2][2] = 0.0D;
/*  55 */     this.christoffel[0][2][3] = 0.0D;
/*  56 */     this.christoffel[0][3][0] = 0.0D;
/*  57 */     this.christoffel[0][3][1] = 0.0D;
/*  58 */     this.christoffel[0][3][2] = 0.0D;
/*  59 */     this.christoffel[0][3][3] = 0.0D;
/*  60 */     this.christoffel[1][0][0] = 0.0D;
/*  61 */     this.christoffel[1][0][1] = 0.0D;
/*  62 */     this.christoffel[1][0][2] = 0.0D;
/*  63 */     this.christoffel[1][0][3] = 0.0D;
/*  64 */     this.christoffel[1][1][0] = 0.0D;
/*  65 */     this.christoffel[1][1][1] = 0.0D;
/*  66 */     this.christoffel[1][1][2] = 0.0D;
/*  67 */     this.christoffel[1][1][3] = 0.0D;
/*  68 */     this.christoffel[1][2][0] = 0.0D;
/*  69 */     this.christoffel[1][2][1] = 0.0D;
/*  70 */     this.christoffel[1][2][2] = 0.0D;
/*  71 */     this.christoffel[1][2][3] = 0.0D;
/*  72 */     this.christoffel[1][3][0] = 0.0D;
/*  73 */     this.christoffel[1][3][1] = 0.0D;
/*  74 */     this.christoffel[1][3][2] = 0.0D;
/*  75 */     this.christoffel[1][3][3] = 0.0D;
/*  76 */     this.christoffel[2][0][0] = 0.0D;
/*  77 */     this.christoffel[2][0][1] = 0.0D;
/*  78 */     this.christoffel[2][0][2] = 0.0D;
/*  79 */     this.christoffel[2][0][3] = 0.0D;
/*  80 */     this.christoffel[2][1][0] = 0.0D;
/*  81 */     this.christoffel[2][1][1] = 0.0D;
/*  82 */     this.christoffel[2][1][2] = 0.0D;
/*  83 */     this.christoffel[2][1][3] = 0.0D;
/*  84 */     this.christoffel[2][2][0] = 0.0D;
/*  85 */     this.christoffel[2][2][1] = 0.0D;
/*  86 */     this.christoffel[2][2][2] = 0.0D;
/*  87 */     this.christoffel[2][2][3] = 0.0D;
/*  88 */     this.christoffel[2][3][0] = 0.0D;
/*  89 */     this.christoffel[2][3][1] = 0.0D;
/*  90 */     this.christoffel[2][3][2] = 0.0D;
/*  91 */     this.christoffel[2][3][3] = 0.0D;
/*  92 */     this.christoffel[3][0][0] = 0.0D;
/*  93 */     this.christoffel[3][0][1] = 0.0D;
/*  94 */     this.christoffel[3][0][2] = 0.0D;
/*  95 */     this.christoffel[3][0][3] = 0.0D;
/*  96 */     this.christoffel[3][1][0] = 0.0D;
/*  97 */     this.christoffel[3][1][1] = 0.0D;
/*  98 */     this.christoffel[3][1][2] = 0.0D;
/*  99 */     this.christoffel[3][1][3] = 0.0D;
/* 100 */     this.christoffel[3][2][0] = 0.0D;
/* 101 */     this.christoffel[3][2][1] = 0.0D;
/* 102 */     this.christoffel[3][2][2] = 0.0D;
/* 103 */     this.christoffel[3][2][3] = 0.0D;
/* 104 */     this.christoffel[3][3][0] = 0.0D;
/* 105 */     this.christoffel[3][3][1] = 0.0D;
/* 106 */     this.christoffel[3][3][2] = 0.0D;
/* 107 */     this.christoffel[3][3][3] = 0.0D;
/*     */   }
/*     */ 
/*     */   public V4D localToCoord(P4D pos, V4D oldDir, int localFrameType)
/*     */   {
/* 113 */     return oldDir;
/*     */   }
/*     */ 
/*     */   public boolean breakCondition(double[] pos) {
/* 117 */     return false;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     spacetime.MinkowskiMetric
 * JD-Core Version:    0.6.0
 */