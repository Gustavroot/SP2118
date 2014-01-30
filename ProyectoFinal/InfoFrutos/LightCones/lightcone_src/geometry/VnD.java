/*     */ package geometry;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ 
/*     */ public abstract class VnD
/*     */ {
/*     */   protected int dim;
/*     */   protected double[] data;
/*     */ 
/*     */   public VnD(int _dim)
/*     */   {
/*  13 */     this.dim = _dim;
/*  14 */     this.data = new double[this.dim];
/*  15 */     for (int i = 0; i < this.dim; i++)
/*  16 */       this.data[i] = 0.0D;
/*     */   }
/*     */ 
/*     */   public VnD(int _dim, VnD v) {
/*  20 */     this.dim = _dim;
/*  21 */     this.data = new double[this.dim];
/*  22 */     int size = this.dim >= v.getDim() ? v.getDim() : this.dim;
/*  23 */     for (int i = 0; i < size; i++) {
/*  24 */       this.data[i] = v.getX(i);
/*     */     }
/*  26 */     for (int i = size; i < this.dim; i++)
/*  27 */       this.data[i] = 0.0D;
/*     */   }
/*     */ 
/*     */   public VnD(int _dim, double[] _data) {
/*  31 */     this.dim = _dim;
/*  32 */     this.data = new double[this.dim];
/*  33 */     int size = this.dim >= _data.length ? _data.length : this.dim;
/*  34 */     for (int i = 0; i < size; i++) {
/*  35 */       this.data[i] = _data[i];
/*     */     }
/*  37 */     for (int i = size; i < this.dim; i++)
/*  38 */       this.data[i] = 0.0D; 
/*     */   }
/*     */ 
/*     */   public abstract VnD add(VnD paramVnD);
/*     */ 
/*     */   public abstract VnD sub(VnD paramVnD);
/*     */ 
/*     */   public abstract VnD mul(double paramDouble);
/*     */ 
/*     */   public double mul(VnD v) {
/*  50 */     int size = this.dim >= v.getDim() ? v.getDim() : this.dim;
/*  51 */     double prod = 0.0D;
/*  52 */     for (int i = 0; i < size; i++) {
/*  53 */       prod += this.data[i] * v.getX(i);
/*     */     }
/*  55 */     return prod;
/*     */   }
/*     */ 
/*     */   public abstract VnD cro(VnD paramVnD);
/*     */ 
/*     */   public boolean same(VnD v)
/*     */   {
/*  63 */     boolean same = true;
/*  64 */     int size = this.dim >= v.getDim() ? v.getDim() : this.dim;
/*     */ 
/*  66 */     for (int i = 0; i < size; i++) {
/*  67 */       same = (same) && (Math.abs(this.data[i] - v.getX(i)) < 1.0E-10D);
/*     */     }
/*     */ 
/*  70 */     return same;
/*     */   }
/*     */ 
/*     */   public void normalize()
/*     */   {
/*  75 */     double norm = getNorm();
/*     */ 
/*  77 */     if (norm == 0.0D) {
/*  78 */       return;
/*     */     }
/*  80 */     for (int i = 0; i < this.dim; i++)
/*  81 */       this.data[i] /= norm;
/*     */   }
/*     */ 
/*     */   public double getX(int i)
/*     */   {
/*  88 */     return this.data[i];
/*     */   }
/*     */ 
/*     */   public VnD get() {
/*  92 */     return this;
/*     */   }
/*     */ 
/*     */   public double[] getData() {
/*  96 */     return this.data;
/*     */   }
/*     */ 
/*     */   public double getNorm() {
/* 100 */     double norm = 0.0D;
/*     */ 
/* 102 */     for (int i = 0; i < this.dim; i++) {
/* 103 */       norm += this.data[i] * this.data[i];
/*     */     }
/*     */ 
/* 106 */     norm = Math.sqrt(norm);
/*     */ 
/* 108 */     return norm;
/*     */   }
/*     */   public abstract VnD getNormalized();
/*     */ 
/* 113 */   public String print() { String data_string = new String();
/* 114 */     DecimalFormat f = new DecimalFormat("0.000");
/*     */ 
/* 116 */     data_string = data_string + "V" + this.dim + "D: ";
/* 117 */     for (int i = 0; i < this.dim; i++) {
/* 118 */       data_string = data_string + f.format(this.data[i]) + "\t";
/*     */     }
/*     */ 
/* 121 */     data_string = data_string + "\n";
/*     */ 
/* 123 */     return data_string; }
/*     */ 
/*     */   public int getDim()
/*     */   {
/* 127 */     return this.dim;
/*     */   }
/*     */ 
/*     */   public void setX(int i, double val)
/*     */   {
/* 134 */     if ((i >= 0) && (i < this.dim))
/* 135 */       this.data[i] = val;
/*     */   }
/*     */ 
/*     */   public void set(VnD v) {
/* 139 */     int size = this.dim >= v.getDim() ? v.getDim() : this.dim;
/* 140 */     for (int i = 0; i < size; i++) {
/* 141 */       this.data[i] = v.getX(i);
/*     */     }
/* 143 */     for (int i = size; i < this.dim; i++)
/* 144 */       this.data[i] = 0.0D;
/*     */   }
/*     */ 
/*     */   public void set(double[] components)
/*     */   {
/* 149 */     int size = this.dim >= components.length ? components.length : this.dim;
/* 150 */     for (int i = 0; i < size; i++) {
/* 151 */       this.data[i] = components[i];
/*     */     }
/* 153 */     for (int i = size; i < this.dim; i++)
/* 154 */       this.data[i] = 0.0D;
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     geometry.VnD
 * JD-Core Version:    0.6.0
 */