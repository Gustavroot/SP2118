/*     */ package geometry;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ 
/*     */ public abstract class PnD
/*     */ {
/*     */   protected int dim;
/*     */   protected double[] data;
/*     */ 
/*     */   public PnD(int _dim)
/*     */   {
/*  13 */     this.dim = _dim;
/*  14 */     this.data = new double[this.dim];
/*  15 */     for (int i = 0; i < this.dim; i++)
/*  16 */       this.data[i] = 0.0D;
/*     */   }
/*     */ 
/*     */   public PnD(int _dim, PnD p) {
/*  20 */     this.dim = _dim;
/*  21 */     this.data = new double[this.dim];
/*  22 */     int size = this.dim >= p.getDim() ? p.getDim() : this.dim;
/*  23 */     for (int i = 0; i < size; i++) {
/*  24 */       this.data[i] = p.getX(i);
/*     */     }
/*  26 */     for (int i = size; i < this.dim; i++)
/*  27 */       this.data[i] = 0.0D;
/*     */   }
/*     */ 
/*     */   public PnD(int _dim, double[] _data) {
/*  31 */     this.dim = _dim;
/*  32 */     this.data = new double[this.dim];
/*  33 */     int size = this.dim >= _data.length ? _data.length : this.dim;
/*  34 */     for (int i = 0; i < size; i++) {
/*  35 */       this.data[i] = _data[i];
/*     */     }
/*  37 */     for (int i = size; i < this.dim; i++)
/*  38 */       this.data[i] = 0.0D;  } 
/*     */   public abstract PnD add(VnD paramVnD);
/*     */ 
/*     */   public abstract PnD add(PnD paramPnD);
/*     */ 
/*     */   public abstract PnD sub(VnD paramVnD);
/*     */ 
/*     */   public abstract PnD sub(PnD paramPnD);
/*     */ 
/*     */   public abstract PnD mul(double paramDouble);
/*     */ 
/*  52 */   public boolean same(PnD p) { boolean same = true;
/*  53 */     int size = this.dim >= p.getDim() ? p.getDim() : this.dim;
/*     */ 
/*  55 */     for (int i = 0; i < size; i++) {
/*  56 */       same = (same) && (Math.abs(this.data[i] - p.getX(i)) < 1.0E-10D);
/*     */     }
/*     */ 
/*  59 */     return same;
/*     */   }
/*     */ 
/*     */   public double getX(int i)
/*     */   {
/*  65 */     return this.data[i];
/*     */   }
/*     */ 
/*     */   public PnD get() {
/*  69 */     return this;
/*     */   }
/*     */ 
/*     */   public double[] getData() {
/*  73 */     return this.data;
/*     */   }
/*     */ 
/*     */   public int getDim() {
/*  77 */     return this.dim;
/*     */   }
/*     */ 
/*     */   public String print() {
/*  81 */     String data_string = new String();
/*  82 */     DecimalFormat f = new DecimalFormat("0.000");
/*     */ 
/*  84 */     data_string = data_string + "P" + this.dim + "D: ";
/*  85 */     for (int i = 0; i < this.dim; i++) {
/*  86 */       data_string = data_string + f.format(this.data[i]) + "\t";
/*     */     }
/*  88 */     data_string = data_string + "\n";
/*     */ 
/*  90 */     return data_string;
/*     */   }
/*     */ 
/*     */   public void setX(int i, double val)
/*     */   {
/*  96 */     if ((i >= 0) && (i < this.dim))
/*  97 */       this.data[i] = val;
/*     */   }
/*     */ 
/*     */   public void set(PnD p) {
/* 101 */     int size = this.dim >= p.getDim() ? p.getDim() : this.dim;
/* 102 */     for (int i = 0; i < size; i++) {
/* 103 */       this.data[i] = p.getX(i);
/*     */     }
/* 105 */     for (int i = size; i < this.dim; i++)
/* 106 */       this.data[i] = 0.0D;
/*     */   }
/*     */ 
/*     */   public void set(double[] components)
/*     */   {
/* 111 */     int size = this.dim >= components.length ? components.length : this.dim;
/* 112 */     for (int i = 0; i < size; i++) {
/* 113 */       this.data[i] = components[i];
/*     */     }
/* 115 */     for (int i = size; i < this.dim; i++)
/* 116 */       this.data[i] = 0.0D;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.PnD
 * JD-Core Version:    0.6.0
 */