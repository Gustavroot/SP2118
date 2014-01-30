/*     */ package display;
/*     */ 
/*     */ import geometry.P4D;
/*     */ import geometry.Spline;
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ public class CanvasSimple extends Canvas
/*     */ {
/*     */   private P4D[][][] wf_data;
/*     */   private int numPics;
/*     */   private int currPic;
/*  43 */   private int c_width = 600;
/*  44 */   private int c_height = 600;
/*  45 */   private Spline[] spline_x = null;
/*  46 */   private Spline[] spline_y = null;
/*     */ 
/*  48 */   private double[] ergo_x_val = null;
/*  49 */   private double[] ergo_y_val = null;
/*     */   private Graphics g_buff;
/*     */   private BufferedImage img_buff;
/*  58 */   private boolean showCoords = true;
/*  59 */   private boolean showMass = true;
/*  60 */   private boolean showErgo = true;
/*  61 */   private double radius = 0.0D;
/*  62 */   private double halfAxis1 = 0.0D;
/*  63 */   private double halfAxis2 = 0.0D;
/*  64 */   private boolean ergo_xz = false;
/*     */ 
/*  66 */   private boolean is_closed = false;
/*     */ 
/*  72 */   private double scale = 10.5D;
/*     */ 
/*  92 */   private boolean paint_xz = false;
/*     */ 
/*  99 */   int num_seg_ergo = 10;
/*     */ 
/*     */   public void init()
/*     */   {
/*  21 */     setBackground(Color.darkGray);
/*  22 */     setMinimumSize(this.c_width, this.c_height);
/*     */ 
/*  25 */     setBackground(Color.white);
/*  26 */     setBounds(5, 5, this.c_width, this.c_height);
/*     */ 
/*  29 */     setErgoSplines(1.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   private void setMinimumSize(int c_width2, int c_height2)
/*     */   {
/*     */   }
/*     */ 
/*     */   public BufferedImage getImage()
/*     */   {
/*  55 */     return this.img_buff;
/*     */   }
/*     */ 
/*     */   public void setClosed(boolean closed)
/*     */   {
/*  68 */     this.is_closed = closed;
/*     */   }
/*     */ 
/*     */   public void setShowCoords(boolean show)
/*     */   {
/*  75 */     this.showCoords = show;
/*     */   }
/*     */   public void setShowMass(boolean show, double _radius) {
/*  78 */     this.showMass = show;
/*  79 */     this.radius = _radius;
/*     */   }
/*     */   public void setShowErgo(boolean show, double _halfAxis1, double _halfAxis2, boolean xz, double m, double a) {
/*  82 */     this.showErgo = show;
/*  83 */     this.halfAxis1 = _halfAxis1;
/*  84 */     this.halfAxis2 = _halfAxis2;
/*  85 */     this.ergo_xz = xz;
/*  86 */     setErgoSplines(m, a);
/*     */   }
/*     */   public void setScale(double _scale) {
/*  89 */     this.scale = _scale;
/*     */   }
/*     */ 
/*     */   public void setPaintXY(boolean paint)
/*     */   {
/*  96 */     this.paint_xz = (!paint);
/*     */   }
/*     */ 
/*     */   public void setErgoSplines(double m, double a)
/*     */   {
/* 102 */     this.ergo_x_val = new double[this.num_seg_ergo];
/* 103 */     this.ergo_y_val = new double[this.num_seg_ergo];
/* 104 */     double delta_theta = 1.570796326794897D / (this.num_seg_ergo - 1.0D);
/* 105 */     for (int i = 0; i < this.num_seg_ergo; i++) {
/* 106 */       double cos_theta = Math.cos(delta_theta * i);
/* 107 */       double r = m + Math.sqrt(m * m - a * a * cos_theta * cos_theta);
/* 108 */       this.ergo_y_val[i] = (r * cos_theta);
/* 109 */       this.ergo_x_val[i] = Math.sqrt(r * r - this.ergo_y_val[i] * this.ergo_y_val[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setData(P4D[][][] data)
/*     */   {
/* 115 */     this.wf_data = data;
/* 116 */     this.numPics = this.wf_data[0][0].length;
/* 117 */     int numRaysTheta = data.length;
/* 118 */     int numRaysPhi = data[0].length;
/* 119 */     if ((numRaysTheta != 1) && (numRaysPhi == 1)) {
/* 120 */       this.spline_x = new Spline[this.numPics];
/* 121 */       this.spline_y = new Spline[this.numPics];
/* 122 */       for (int i = 0; i < this.numPics; i++) {
/* 123 */         double[] x_val = new double[numRaysTheta];
/* 124 */         for (int j = 0; j < numRaysTheta; j++) {
/* 125 */           x_val[j] = this.wf_data[j][0][i].getX(1);
/*     */         }
/* 127 */         this.spline_x[i] = new Spline(x_val);
/* 128 */         double[] y_val = new double[numRaysTheta];
/* 129 */         for (int j = 0; j < numRaysTheta; j++) {
/* 130 */           if (!this.paint_xz)
/* 131 */             y_val[j] = this.wf_data[j][0][i].getX(2);
/*     */           else
/* 133 */             y_val[j] = this.wf_data[j][0][i].getX(3);
/*     */         }
/* 135 */         this.spline_y[i] = new Spline(y_val);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setFrame(int frame)
/*     */   {
/* 142 */     this.currPic = (frame > this.numPics ? this.numPics : frame);
/*     */   }
/*     */ 
/*     */   public CanvasSimple()
/*     */   {
/* 148 */     init();
/* 149 */     this.currPic = 0;
/*     */   }
/*     */ 
/*     */   public void repaint() {
/* 153 */     paint(getGraphics());
/*     */   }
/*     */ 
/*     */   public void paint(Graphics g)
/*     */   {
/* 159 */     if (this.img_buff == null) {
/* 160 */       this.img_buff = ((BufferedImage)createImage(this.c_width, this.c_height));
/*     */     }
/* 162 */     this.g_buff = this.img_buff.getGraphics();
/* 163 */     this.g_buff.setColor(Color.black);
/* 164 */     this.g_buff.drawLine(0, 0, this.c_width - 1, 0);
/* 165 */     this.g_buff.drawLine(0, 0, 0, this.c_height - 1);
/* 166 */     this.g_buff.drawLine(0, this.c_height - 1, this.c_width - 1, this.c_height - 1);
/* 167 */     this.g_buff.drawLine(this.c_width - 1, 0, this.c_width - 1, this.c_height - 1);
/*     */ 
/* 169 */     if ((this.currPic == -1) || (this.wf_data == null)) {
/* 170 */       return;
/*     */     }
/* 172 */     int x = this.c_width / 2;
/* 173 */     int y = this.c_height / 2;
/*     */     try {
/* 175 */       Thread.sleep(10L);
/*     */     }
/*     */     catch (InterruptedException e) {
/* 178 */       e.printStackTrace();
/*     */     }
/* 180 */     this.g_buff.clearRect(0, 0, getSize().width, getSize().height);
/*     */ 
/* 184 */     if ((this.showErgo) && (!this.ergo_xz))
/*     */     {
/* 186 */       this.g_buff.setColor(Color.orange);
/* 187 */       this.g_buff.drawOval(x - (int)(this.halfAxis1 * this.c_width / this.scale / 2.0D), y - (int)(this.halfAxis1 * this.c_width / this.scale / 2.0D), (int)(this.halfAxis1 * this.c_width / this.scale), (int)(this.halfAxis1 * this.c_width / this.scale));
/*     */     }
/*     */ 
/* 190 */     if ((this.showErgo) && (this.ergo_xz)) {
/* 191 */       this.g_buff.setColor(Color.orange);
/* 192 */       for (int i = 0; i < this.num_seg_ergo - 1; i++) {
/* 193 */         int lspx = (int)(this.ergo_x_val[i] * this.c_width / (2.0D * this.scale));
/* 194 */         int lspy = -(int)(this.ergo_y_val[i] * this.c_width / (2.0D * this.scale));
/* 195 */         int spx = (int)(this.ergo_x_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 196 */         int spy = -(int)(this.ergo_y_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 197 */         this.g_buff.drawLine(lspx + x, lspy + y, spx + x, spy + y);
/*     */       }
/* 199 */       for (int i = 0; i < this.num_seg_ergo - 1; i++) {
/* 200 */         int lspx = (int)(this.ergo_x_val[i] * this.c_width / (2.0D * this.scale));
/* 201 */         int lspy = -(int)(this.ergo_y_val[i] * this.c_width / (2.0D * this.scale));
/* 202 */         int spx = (int)(this.ergo_x_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 203 */         int spy = -(int)(this.ergo_y_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 204 */         this.g_buff.drawLine(-lspx + x, lspy + y, -spx + x, spy + y);
/*     */       }
/* 206 */       for (int i = 0; i < this.num_seg_ergo - 1; i++) {
/* 207 */         int lspx = (int)(this.ergo_x_val[i] * this.c_width / (2.0D * this.scale));
/* 208 */         int lspy = -(int)(this.ergo_y_val[i] * this.c_width / (2.0D * this.scale));
/* 209 */         int spx = (int)(this.ergo_x_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 210 */         int spy = -(int)(this.ergo_y_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 211 */         this.g_buff.drawLine(lspx + x, -lspy + y, spx + x, -spy + y);
/*     */       }
/* 213 */       for (int i = 0; i < this.num_seg_ergo - 1; i++) {
/* 214 */         int lspx = (int)(this.ergo_x_val[i] * this.c_width / (2.0D * this.scale));
/* 215 */         int lspy = -(int)(this.ergo_y_val[i] * this.c_width / (2.0D * this.scale));
/* 216 */         int spx = (int)(this.ergo_x_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 217 */         int spy = -(int)(this.ergo_y_val[(i + 1)] * this.c_width / (2.0D * this.scale));
/* 218 */         this.g_buff.drawLine(-lspx + x, -lspy + y, -spx + x, -spy + y);
/*     */       }
/*     */     }
/* 221 */     if (this.showMass)
/*     */     {
/* 223 */       this.g_buff.setColor(Color.orange);
/* 224 */       this.g_buff.drawOval(x - (int)(this.radius * this.c_width / this.scale / 2.0D), y - (int)(this.radius * this.c_width / this.scale / 2.0D), (int)(this.radius * this.c_width / this.scale), (int)(this.radius * this.c_width / this.scale));
/* 225 */       this.g_buff.fillOval(x - (int)(this.radius * this.c_width / this.scale / 2.0D), y - (int)(this.radius * this.c_width / this.scale / 2.0D), (int)(this.radius * this.c_width / this.scale), (int)(this.radius * this.c_width / this.scale));
/*     */     }
/*     */ 
/* 230 */     if (this.showCoords) {
/* 231 */       this.g_buff.setColor(Color.LIGHT_GRAY);
/* 232 */       this.g_buff.drawLine(x, 0, x, this.c_height - 1);
/* 233 */       this.g_buff.drawLine(0, y, this.c_width - 1, y);
/* 234 */       int ticks = (int)this.scale;
/* 235 */       double delta = this.c_width / (2.0D * this.scale);
/* 236 */       for (int t = -ticks; t <= ticks; t++) {
/* 237 */         int diff = (int)(t * delta);
/* 238 */         if (t % 5 == 0) {
/* 239 */           this.g_buff.setColor(Color.DARK_GRAY);
/*     */         }
/* 241 */         this.g_buff.drawLine(x + diff, y - 5, x + diff, y + 5);
/* 242 */         this.g_buff.drawLine(x - 5, y + diff, x + 5, y + diff);
/* 243 */         if (t % 5 == 0) {
/* 244 */           this.g_buff.setColor(Color.LIGHT_GRAY);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 249 */     this.g_buff.setColor(Color.black);
/* 250 */     this.g_buff.drawLine(0, 0, this.c_width - 1, 0);
/* 251 */     this.g_buff.drawLine(0, 0, 0, this.c_height - 1);
/* 252 */     this.g_buff.drawLine(0, this.c_height - 1, this.c_width - 1, this.c_height - 1);
/* 253 */     this.g_buff.drawLine(this.c_width - 1, 0, this.c_width - 1, this.c_height - 1);
/* 254 */     int lpx = (int)this.wf_data[0][0][this.currPic].getX(1) + x;
/*     */     int lpy;
/* 256 */     if (!this.paint_xz)
/* 257 */       lpy = (int)this.wf_data[0][0][this.currPic].getX(2) + y;
/*     */     else {
/* 259 */       lpy = (int)this.wf_data[0][0][this.currPic].getX(3) + y;
/*     */     }
/*     */ 
/* 262 */     for (int i = 0; i < this.wf_data.length; i++) {
/* 263 */       for (int j = 0; j < this.wf_data[0].length; j++) {
/* 264 */         int px = (int)(this.wf_data[i][j][this.currPic].getX(1) * this.c_width / (2.0D * this.scale));
/*     */         int py;
/* 266 */         if (!this.paint_xz)
/* 267 */           py = -(int)(this.wf_data[i][j][this.currPic].getX(2) * this.c_height / (2.0D * this.scale));
/*     */         else
/* 269 */           py = -(int)(this.wf_data[i][j][this.currPic].getX(3) * this.c_height / (2.0D * this.scale));
/* 270 */         this.g_buff.drawOval(px + x, py + y, 1, 1);
/*     */ 
/* 272 */         int seg_max = 10;
/* 273 */         for (int seg = 0; seg < seg_max; seg++) {
/* 274 */           if (i < this.wf_data.length - 1) {
/* 275 */             int lspx = (int)(this.spline_x[this.currPic].fn(i, seg / seg_max) * this.c_width / (2.0D * this.scale));
/* 276 */             int lspy = -(int)(this.spline_y[this.currPic].fn(i, seg / seg_max) * this.c_width / (2.0D * this.scale));
/* 277 */             int spx = (int)(this.spline_x[this.currPic].fn(i, (seg + 1.0D) / seg_max) * this.c_width / (2.0D * this.scale));
/* 278 */             int spy = -(int)(this.spline_y[this.currPic].fn(i, (seg + 1.0D) / seg_max) * this.c_width / (2.0D * this.scale));
/* 279 */             this.g_buff.drawLine(lspx + x, lspy + y, spx + x, spy + y);
/*     */           }
/*     */         }
/* 282 */         lpx = px;
/* 283 */         lpy = py;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 288 */     g.drawImage(this.img_buff, 0, 0, this);
/*     */   }
/*     */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     display.CanvasSimple
 * JD-Core Version:    0.6.0
 */