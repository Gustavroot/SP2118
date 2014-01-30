/*      */ package display;
/*      */ 
/*      */ import geometry.P4D;
/*      */ import geometry.PnD;
/*      */ import geometry.V3D;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionListener;
/*      */ import java.nio.FloatBuffer;
/*      */ import org.lwjgl.BufferUtils;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import org.lwjgl.opengl.AWTGLCanvas;
/*      */ import org.lwjgl.opengl.Display;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ // import org.lwjgl.opengl.glu.GLU;
/*      */ 
/*      */ import org.lwjgl.util.glu.GLU; 
/*      */ 
/*      */ public class CanvasGL extends AWTGLCanvas
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*   30 */   private int c_width = 600;
/*      */ 
/*   32 */   private int c_height = 600;
/*      */ 
/*   34 */   long time_now = System.currentTimeMillis();
/*      */ 
/*   36 */   long time_last = System.currentTimeMillis();
/*      */ 
/*   38 */   private boolean playing = false;
/*      */ 
/*   40 */   private boolean is_closed = false;
/*      */ 
/*   42 */   private boolean paintCone = true;
/*      */ 
/*   44 */   private boolean paintCone_xz = false;
/*      */ 
/*   46 */   private boolean paint3d = false;
/*      */ 
/*   71 */   private float scale = 20.1F;
/*      */ 
/*   79 */   boolean clicked = false;
/*      */ 
/*   81 */   int last_x = -1;
/*      */ 
/*   83 */   int last_y = -1;
/*      */ 
/*   85 */   int diff_x = 0;
/*      */ 
/*   87 */   int diff_y = 0;
/*      */ 
/*   89 */   int last_diff_x = 0;
/*      */ 
/*   91 */   int last_diff_y = 0;
/*      */   private P4D[][][] wf_data;
/*      */   private int numPics;
/*      */   private int currPic;
/*  102 */   FloatBuffer fbm_specular = toFloatBuffer(new float[] { 1.0F, 1.0F, 1.0F, 
/*  102 */     1.0F });
/*      */ 
/*  105 */   FloatBuffer fbm_shininess = toFloatBuffer(new float[] { 50.0F, 50.0F, 
/*  105 */     50.0F, 50.0F });
/*      */ 
/*  107 */   FloatBuffer fbm_x = toFloatBuffer(new float[] { 0.0F, 0.0F, 0.0F, 0.3F });
/*      */ 
/*  109 */   FloatBuffer fbm_y = toFloatBuffer(new float[] { 0.0F, 0.0F, 0.0F, 0.3F });
/*      */ 
/*  111 */   FloatBuffer fbm_z = toFloatBuffer(new float[] { 0.4F, 0.0F, 0.0F, 0.3F });
/*      */ 
/*  113 */   FloatBuffer fbm_b = toFloatBuffer(new float[] { 0.0F, 0.0F, 0.0F, 0.3F });
/*      */ 
/*  115 */   FloatBuffer fbm_b2 = toFloatBuffer(new float[] { 0.0F, 0.0F, 0.0F, 0.3F });
/*      */ 
/*  117 */   FloatBuffer fbm_b3 = toFloatBuffer(new float[] { 0.0F, 0.0F, 0.0F, 0.8F });
/*      */ 
/*  125 */   private int num_seg = 20;
/*      */ 
/*  132 */   private int num_seg_x = 20;
/*      */ 
/*  134 */   private int num_seg_y = 20;
/*      */ 
/*      */   public void setClosed(boolean closed)
/*      */   {
/*   49 */     this.is_closed = closed;
/*      */   }
/*      */ 
/*      */   public void setPlaying() {
/*   53 */     this.playing = true;
/*   54 */     this.currPic = -1;
/*      */   }
/*      */ 
/*      */   public void setPaintCone(boolean paint) {
/*   58 */     this.paintCone = paint;
/*   59 */     this.paint3d = (!paint);
/*      */   }
/*      */ 
/*      */   public void setPaintConeXY(boolean paintxy) {
/*   63 */     this.paintCone_xz = (!paintxy);
/*      */   }
/*      */ 
/*      */   public void setPaint3d(boolean paint) {
/*   67 */     this.paint3d = paint;
/*   68 */     this.paintCone = (!paint);
/*      */   }
/*      */ 
/*      */   public void setScale(float _scale)
/*      */   {
/*   74 */     this.scale = (_scale * 2.0F);
/*      */   }
/*      */ 
/*      */   public void setData(P4D[][][] data)
/*      */   {
/*  120 */     this.wf_data = data;
/*  121 */     this.numPics = this.wf_data[0][0].length;
/*      */   }
/*      */ 
/*      */   public void setNumSeg(int _num_seg)
/*      */   {
/*  128 */     this.num_seg = _num_seg;
/*      */   }
/*      */ 
/*      */   public void setNumSeg3D(int _num_seg_x, int _num_seg_y)
/*      */   {
/*  137 */     this.num_seg_x = _num_seg_x;
/*  138 */     this.num_seg_y = _num_seg_y;
/*      */   }
/*      */ 
/*      */   public CanvasGL()
/*      */     throws LWJGLException
/*      */   {
/*  148 */     addMouseMotionListener(new MouseMotionListener()
/*      */     {
/*      */       public void mouseDragged(MouseEvent arg0) {
/*  151 */         if (CanvasGL.this.clicked) {
/*  152 */           int x = arg0.getX();
/*  153 */           int y = arg0.getY();
/*  154 */           if ((CanvasGL.this.last_x != -1) && (CanvasGL.this.last_y != -1)) {
/*  155 */             CanvasGL.this.diff_x = (x - CanvasGL.this.last_x);
/*  156 */             CanvasGL.this.diff_y = (y - CanvasGL.this.last_y);
/*      */           }
/*      */ 
/*  159 */           CanvasGL.this.last_x = x;
/*  160 */           CanvasGL.this.last_y = y;
/*      */         }
/*      */       }
/*      */ 
/*      */       public void mouseMoved(MouseEvent arg0) {
/*  165 */         int x = arg0.getX();
/*  166 */         int y = arg0.getY();
/*      */       }
/*      */     });
/*  171 */     addMouseListener(new MouseListener()
/*      */     {
/*      */       public void mouseClicked(MouseEvent arg0) {
/*  174 */         int x = arg0.getX();
/*  175 */         int y = arg0.getY();
/*  176 */         CanvasGL.this.clicked = true;
/*      */       }
/*      */ 
/*      */       public void mousePressed(MouseEvent arg0)
/*      */       {
/*  181 */         int x = arg0.getX();
/*  182 */         int y = arg0.getY();
/*  183 */         CanvasGL.this.clicked = true;
/*      */       }
/*      */ 
/*      */       public void mouseReleased(MouseEvent arg0)
/*      */       {
/*  188 */         int x = arg0.getX();
/*  189 */         int y = arg0.getY();
/*  190 */         CanvasGL.this.last_x = (CanvasGL.this.last_y = -1);
/*  191 */         CanvasGL.this.clicked = false;
/*      */       }
/*      */ 
/*      */       public void mouseEntered(MouseEvent arg0)
/*      */       {
/*  196 */         int x = arg0.getX();
/*  197 */         int y = arg0.getY();
/*      */       }
/*      */ 
/*      */       public void mouseExited(MouseEvent arg0)
/*      */       {
/*  202 */         int x = arg0.getX();
/*  203 */         int y = arg0.getY();
/*      */       }
/*      */     });
/*  209 */     Thread t = new Thread() {
/*      */       public void run() {
/*      */         while (true) {
/*  212 */           if (CanvasGL.this.isVisible()) {
/*  213 */             CanvasGL.this.repaint();
/*      */           }
/*  215 */           Display.sync(60);
/*      */         }
/*      */       }
/*      */     };
/*  219 */     t.setDaemon(true);
/*  220 */     t.start();
/*      */   }
/*      */ 
/*      */   public void initGL() {
/*  224 */     super.initGL();
/*      */ 
/*  226 */     GL11.glEnable(3042);
/*  227 */     GL11.glBlendFunc(770, 771);
/*  228 */     GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/*  229 */     GL11.glPolygonMode(1032, 6914);
/*  230 */     GL11.glEnable(2852);
/*  231 */     GL11.glEnable(2929);
/*  232 */     GL11.glDepthFunc(515);
/*  233 */     GL11.glShadeModel(7425);
/*  234 */     GL11.glMatrixMode(5888);
/*  235 */     GL11.glHint(3152, 4354);
/*  236 */     GL11.glLineWidth(1.5F);
/*  237 */     GL11.glEnable(2896);
/*  238 */     GL11.glEnable(2977);
/*  239 */     GL11.glEnable(16384);
/*  240 */     GL11.glEnable(2898);
/*      */   }
/*      */ 
/*      */   public void paintGL()
/*      */   {
/*  249 */     GL11.glClear(16640);
/*  250 */     GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
/*      */ 
/*  252 */     GL11.glLoadIdentity();
/*      */ 
/*  254 */     GLU.gluPerspective(45.0F, 1.0F, 1.0F, 1000.0F);
/*  255 */     GLU.gluLookAt(this.scale, this.scale, this.scale / 10.0F, 0.0F, 0.0F, 0.0F, 0.0F, 
/*  256 */       0.0F, 1.0F);
/*      */ 
/*  258 */     float[] fl_position = { this.scale, this.scale, this.scale / 10.0F, 0.0F };
/*      */ 
/*  265 */     GL11.glLight(16384, 4611, 
/*  266 */       toFloatBuffer(fl_position));
/*      */ 
/*  268 */     this.last_diff_x += this.diff_x;
/*  269 */     this.last_diff_y += this.diff_y;
/*      */ 
/*  271 */     GL11.glRotatef(this.last_diff_y, -1.0F, 1.0F, 0.0F);
/*  272 */     GL11.glRotatef(this.last_diff_x, 0.0F, 0.0F, 1.0F);
/*  273 */     this.diff_x = (this.diff_y = 0);
/*      */ 
/*  275 */     GL11.glPushMatrix();
/*      */ 
/*  277 */     paintGrid();
/*      */ 
/*  279 */     if (this.paintCone)
/*  280 */       paintCone();
/*  281 */     if (this.paint3d) {
/*  282 */       paint3d();
/*      */     }
/*  284 */     GL11.glPopMatrix();
/*      */     try
/*      */     {
/*  287 */       swapBuffers();
/*      */     }
/*      */     catch (Exception localException) {
/*      */     }
/*  291 */     if (this.playing) {
/*  292 */       this.time_now = System.currentTimeMillis();
/*  293 */       if (this.time_now - this.time_last > 50L) {
/*  294 */         this.currPic += 1;
/*  295 */         this.time_last = this.time_now;
/*      */       }
/*      */     }
/*      */ 
/*  299 */     if (this.currPic >= this.numPics) {
/*  300 */       this.currPic -= 1;
/*  301 */       this.playing = false;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void paintGrid()
/*      */   {
/*  308 */     float a_val = 101.0F;
/*  309 */     float off_val = 0.3F * (float)Math.sqrt(this.scale) / 16.0F;
/*  310 */     float off_val2 = 2.5F * off_val;
/*  311 */     float small = 0.2F * (float)Math.sqrt(this.scale) / 16.0F;
/*      */ 
/*  314 */     GL11.glMaterial(1032, 4610, this.fbm_specular);
/*  315 */     GL11.glMaterial(1032, 5633, 
/*  316 */       this.fbm_shininess);
/*  317 */     GL11.glMaterial(1032, 4608, this.fbm_x);
/*  318 */     GL11.glMaterial(1032, 4609, this.fbm_x);
/*      */ 
/*  320 */     GL11.glBegin(7);
/*  321 */     GL11.glVertex3f(-a_val, -off_val, off_val);
/*  322 */     GL11.glVertex3f(a_val, -off_val, off_val);
/*  323 */     GL11.glVertex3f(a_val, off_val, off_val);
/*  324 */     GL11.glVertex3f(-a_val, off_val, off_val);
/*  325 */     GL11.glEnd();
/*  326 */     GL11.glBegin(7);
/*  327 */     GL11.glVertex3f(-a_val, -off_val, -off_val);
/*  328 */     GL11.glVertex3f(a_val, -off_val, -off_val);
/*  329 */     GL11.glVertex3f(a_val, off_val, -off_val);
/*  330 */     GL11.glVertex3f(-a_val, off_val, -off_val);
/*  331 */     GL11.glEnd();
/*  332 */     GL11.glBegin(7);
/*  333 */     GL11.glVertex3f(-a_val, off_val, -off_val);
/*  334 */     GL11.glVertex3f(a_val, off_val, -off_val);
/*  335 */     GL11.glVertex3f(a_val, off_val, off_val);
/*  336 */     GL11.glVertex3f(-a_val, off_val, off_val);
/*  337 */     GL11.glEnd();
/*  338 */     GL11.glBegin(7);
/*  339 */     GL11.glVertex3f(-a_val, -off_val, -off_val);
/*  340 */     GL11.glVertex3f(a_val, -off_val, -off_val);
/*  341 */     GL11.glVertex3f(a_val, -off_val, off_val);
/*  342 */     GL11.glVertex3f(-a_val, -off_val, off_val);
/*  343 */     GL11.glEnd();
/*      */ 
/*  345 */     for (int i = 1; i <= (int)a_val; i++)
/*      */     {
/*  347 */       if (i % 5 == 0) {
/*  348 */         GL11.glMaterial(1032, 4610, 
/*  349 */           this.fbm_specular);
/*  350 */         GL11.glMaterial(1032, 5633, 
/*  351 */           this.fbm_shininess);
/*  352 */         GL11.glMaterial(1032, 4608, this.fbm_b);
/*  353 */         GL11.glMaterial(1032, 4609, this.fbm_b);
/*  354 */         off_val2 *= 2.0F;
/*      */       }
/*      */ 
/*  357 */       GL11.glBegin(7);
/*  358 */       GL11.glVertex3f(i, -off_val, -off_val2);
/*  359 */       GL11.glVertex3f(i, off_val, -off_val2);
/*  360 */       GL11.glVertex3f(i, off_val, off_val2);
/*  361 */       GL11.glVertex3f(i, -off_val, off_val2);
/*  362 */       GL11.glEnd();
/*      */ 
/*  364 */       GL11.glBegin(7);
/*  365 */       GL11.glVertex3f(i, -off_val, -off_val2);
/*  366 */       GL11.glVertex3f(i + small, -off_val, -off_val2);
/*  367 */       GL11.glVertex3f(i + small, off_val, -off_val2);
/*  368 */       GL11.glVertex3f(i, off_val, -off_val2);
/*  369 */       GL11.glEnd();
/*      */ 
/*  371 */       GL11.glBegin(7);
/*  372 */       GL11.glVertex3f(i, -off_val, off_val2);
/*  373 */       GL11.glVertex3f(i + small, -off_val, off_val2);
/*  374 */       GL11.glVertex3f(i + small, off_val, off_val2);
/*  375 */       GL11.glVertex3f(i, off_val, off_val2);
/*  376 */       GL11.glEnd();
/*      */ 
/*  378 */       GL11.glBegin(7);
/*  379 */       GL11.glVertex3f(i, -off_val, -off_val2);
/*  380 */       GL11.glVertex3f(i + small, -off_val, -off_val2);
/*  381 */       GL11.glVertex3f(i + small, -off_val, off_val2);
/*  382 */       GL11.glVertex3f(i, -off_val, off_val2);
/*  383 */       GL11.glEnd();
/*      */ 
/*  385 */       GL11.glBegin(7);
/*  386 */       GL11.glVertex3f(i, off_val, -off_val2);
/*  387 */       GL11.glVertex3f(i + small, off_val, -off_val2);
/*  388 */       GL11.glVertex3f(i + small, off_val, off_val2);
/*  389 */       GL11.glVertex3f(i, off_val, off_val2);
/*  390 */       GL11.glEnd();
/*      */ 
/*  392 */       GL11.glBegin(7);
/*  393 */       GL11.glVertex3f(i + small, -off_val, -off_val2);
/*  394 */       GL11.glVertex3f(i + small, off_val, -off_val2);
/*  395 */       GL11.glVertex3f(i + small, off_val, off_val2);
/*  396 */       GL11.glVertex3f(i + small, -off_val, off_val2);
/*  397 */       GL11.glEnd();
/*      */ 
/*  401 */       GL11.glBegin(7);
/*  402 */       GL11.glVertex3f(-i, -off_val, -off_val2);
/*  403 */       GL11.glVertex3f(-i, off_val, -off_val2);
/*  404 */       GL11.glVertex3f(-i, off_val, off_val2);
/*  405 */       GL11.glVertex3f(-i, -off_val, off_val2);
/*  406 */       GL11.glEnd();
/*      */ 
/*  408 */       GL11.glBegin(7);
/*  409 */       GL11.glVertex3f(-i, -off_val, -off_val2);
/*  410 */       GL11.glVertex3f(-i - small, -off_val, -off_val2);
/*  411 */       GL11.glVertex3f(-i - small, off_val, -off_val2);
/*  412 */       GL11.glVertex3f(-i, off_val, -off_val2);
/*  413 */       GL11.glEnd();
/*      */ 
/*  415 */       GL11.glBegin(7);
/*  416 */       GL11.glVertex3f(-i, -off_val, off_val2);
/*  417 */       GL11.glVertex3f(-i - small, -off_val, off_val2);
/*  418 */       GL11.glVertex3f(-i - small, off_val, off_val2);
/*  419 */       GL11.glVertex3f(-i, off_val, off_val2);
/*  420 */       GL11.glEnd();
/*      */ 
/*  422 */       GL11.glBegin(7);
/*  423 */       GL11.glVertex3f(-i, -off_val, -off_val2);
/*  424 */       GL11.glVertex3f(-i - small, -off_val, -off_val2);
/*  425 */       GL11.glVertex3f(-i - small, -off_val, off_val2);
/*  426 */       GL11.glVertex3f(-i, -off_val, off_val2);
/*  427 */       GL11.glEnd();
/*      */ 
/*  429 */       GL11.glBegin(7);
/*  430 */       GL11.glVertex3f(-i, off_val, -off_val2);
/*  431 */       GL11.glVertex3f(-i - small, off_val, -off_val2);
/*  432 */       GL11.glVertex3f(-i - small, off_val, off_val2);
/*  433 */       GL11.glVertex3f(-i, off_val, off_val2);
/*  434 */       GL11.glEnd();
/*      */ 
/*  436 */       GL11.glBegin(7);
/*  437 */       GL11.glVertex3f(-i - small, -off_val, -off_val2);
/*  438 */       GL11.glVertex3f(-i - small, off_val, -off_val2);
/*  439 */       GL11.glVertex3f(-i - small, off_val, off_val2);
/*  440 */       GL11.glVertex3f(-i - small, -off_val, off_val2);
/*  441 */       GL11.glEnd();
/*      */ 
/*  443 */       if (i % 5 == 0) {
/*  444 */         GL11.glMaterial(1032, 4610, 
/*  445 */           this.fbm_specular);
/*  446 */         GL11.glMaterial(1032, 5633, 
/*  447 */           this.fbm_shininess);
/*  448 */         GL11.glMaterial(1032, 4608, this.fbm_x);
/*  449 */         GL11.glMaterial(1032, 4609, this.fbm_x);
/*      */ 
/*  451 */         off_val2 /= 2.0F;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  456 */     GL11.glMaterial(1032, 4610, this.fbm_specular);
/*  457 */     GL11.glMaterial(1032, 5633, 
/*  458 */       this.fbm_shininess);
/*  459 */     GL11.glMaterial(1032, 4608, this.fbm_y);
/*  460 */     GL11.glMaterial(1032, 4609, this.fbm_y);
/*      */ 
/*  462 */     GL11.glBegin(7);
/*  463 */     GL11.glVertex3f(-off_val, -a_val, off_val);
/*  464 */     GL11.glVertex3f(-off_val, a_val, off_val);
/*  465 */     GL11.glVertex3f(off_val, a_val, off_val);
/*  466 */     GL11.glVertex3f(off_val, -a_val, off_val);
/*  467 */     GL11.glEnd();
/*  468 */     GL11.glBegin(7);
/*  469 */     GL11.glVertex3f(-off_val, -a_val, -off_val);
/*  470 */     GL11.glVertex3f(-off_val, a_val, -off_val);
/*  471 */     GL11.glVertex3f(off_val, a_val, -off_val);
/*  472 */     GL11.glVertex3f(off_val, -a_val, -off_val);
/*  473 */     GL11.glEnd();
/*  474 */     GL11.glBegin(7);
/*  475 */     GL11.glVertex3f(off_val, -a_val, -off_val);
/*  476 */     GL11.glVertex3f(off_val, a_val, -off_val);
/*  477 */     GL11.glVertex3f(off_val, a_val, off_val);
/*  478 */     GL11.glVertex3f(off_val, -a_val, off_val);
/*  479 */     GL11.glEnd();
/*  480 */     GL11.glBegin(7);
/*  481 */     GL11.glVertex3f(-off_val, -a_val, -off_val);
/*  482 */     GL11.glVertex3f(-off_val, a_val, -off_val);
/*  483 */     GL11.glVertex3f(-off_val, a_val, off_val);
/*  484 */     GL11.glVertex3f(-off_val, -a_val, off_val);
/*  485 */     GL11.glEnd();
/*      */ 
/*  487 */     for (int i = 1; i <= (int)a_val; i++)
/*      */     {
/*  489 */       if (i % 5 == 0) {
/*  490 */         GL11.glMaterial(1032, 4610, 
/*  491 */           this.fbm_specular);
/*  492 */         GL11.glMaterial(1032, 5633, 
/*  493 */           this.fbm_shininess);
/*  494 */         GL11.glMaterial(1032, 4608, this.fbm_b);
/*  495 */         GL11.glMaterial(1032, 4609, this.fbm_b);
/*      */ 
/*  497 */         off_val2 *= 2.0F;
/*      */       }
/*      */ 
/*  500 */       GL11.glBegin(7);
/*  501 */       GL11.glVertex3f(-off_val, i, -off_val2);
/*  502 */       GL11.glVertex3f(off_val, i, -off_val2);
/*  503 */       GL11.glVertex3f(off_val, i, off_val2);
/*  504 */       GL11.glVertex3f(-off_val, i, off_val2);
/*  505 */       GL11.glEnd();
/*      */ 
/*  507 */       GL11.glBegin(7);
/*  508 */       GL11.glVertex3f(-off_val, i, -off_val2);
/*  509 */       GL11.glVertex3f(-off_val, i + small, -off_val2);
/*  510 */       GL11.glVertex3f(off_val, i + small, -off_val2);
/*  511 */       GL11.glVertex3f(off_val, i, -off_val2);
/*  512 */       GL11.glEnd();
/*      */ 
/*  514 */       GL11.glBegin(7);
/*  515 */       GL11.glVertex3f(-off_val, i, off_val2);
/*  516 */       GL11.glVertex3f(-off_val, i + small, off_val2);
/*  517 */       GL11.glVertex3f(off_val, i + small, off_val2);
/*  518 */       GL11.glVertex3f(off_val, i, off_val2);
/*  519 */       GL11.glEnd();
/*      */ 
/*  521 */       GL11.glBegin(7);
/*  522 */       GL11.glVertex3f(-off_val, i, -off_val2);
/*  523 */       GL11.glVertex3f(-off_val, i + small, -off_val2);
/*  524 */       GL11.glVertex3f(-off_val, i + small, off_val2);
/*  525 */       GL11.glVertex3f(-off_val, i, off_val2);
/*  526 */       GL11.glEnd();
/*      */ 
/*  528 */       GL11.glBegin(7);
/*  529 */       GL11.glVertex3f(off_val, i, -off_val2);
/*  530 */       GL11.glVertex3f(off_val, i + small, -off_val2);
/*  531 */       GL11.glVertex3f(off_val, i + small, off_val2);
/*  532 */       GL11.glVertex3f(off_val, i, off_val2);
/*  533 */       GL11.glEnd();
/*      */ 
/*  535 */       GL11.glBegin(7);
/*  536 */       GL11.glVertex3f(-off_val, i + small, -off_val2);
/*  537 */       GL11.glVertex3f(off_val, i + small, -off_val2);
/*  538 */       GL11.glVertex3f(off_val, i + small, off_val2);
/*  539 */       GL11.glVertex3f(-off_val, i + small, off_val2);
/*  540 */       GL11.glEnd();
/*      */ 
/*  544 */       GL11.glBegin(7);
/*  545 */       GL11.glVertex3f(-off_val, -i, -off_val2);
/*  546 */       GL11.glVertex3f(off_val, -i, -off_val2);
/*  547 */       GL11.glVertex3f(off_val, -i, off_val2);
/*  548 */       GL11.glVertex3f(-off_val, -i, off_val2);
/*  549 */       GL11.glEnd();
/*      */ 
/*  551 */       GL11.glBegin(7);
/*  552 */       GL11.glVertex3f(-off_val, -i, -off_val2);
/*  553 */       GL11.glVertex3f(-off_val, -i - small, -off_val2);
/*  554 */       GL11.glVertex3f(off_val, -i - small, -off_val2);
/*  555 */       GL11.glVertex3f(off_val, -i, -off_val2);
/*  556 */       GL11.glEnd();
/*      */ 
/*  558 */       GL11.glBegin(7);
/*  559 */       GL11.glVertex3f(-off_val, -i, off_val2);
/*  560 */       GL11.glVertex3f(-off_val, -i - small, off_val2);
/*  561 */       GL11.glVertex3f(off_val, -i - small, off_val2);
/*  562 */       GL11.glVertex3f(off_val, -i, off_val2);
/*  563 */       GL11.glEnd();
/*  564 */       GL11.glEnable(16384);
/*      */ 
/*  566 */       GL11.glBegin(7);
/*  567 */       GL11.glVertex3f(-off_val, -i, -off_val2);
/*  568 */       GL11.glVertex3f(-off_val, -i - small, -off_val2);
/*  569 */       GL11.glVertex3f(-off_val, -i - small, off_val2);
/*  570 */       GL11.glVertex3f(-off_val, -i, off_val2);
/*  571 */       GL11.glEnd();
/*      */ 
/*  573 */       GL11.glBegin(7);
/*  574 */       GL11.glVertex3f(off_val, -i, -off_val2);
/*  575 */       GL11.glVertex3f(off_val, -i - small, -off_val2);
/*  576 */       GL11.glVertex3f(off_val, -i - small, off_val2);
/*  577 */       GL11.glVertex3f(off_val, -i, off_val2);
/*  578 */       GL11.glEnd();
/*      */ 
/*  580 */       GL11.glBegin(7);
/*  581 */       GL11.glVertex3f(-off_val, -i - small, -off_val2);
/*  582 */       GL11.glVertex3f(off_val, -i - small, -off_val2);
/*  583 */       GL11.glVertex3f(off_val, -i - small, off_val2);
/*  584 */       GL11.glVertex3f(-off_val, -i - small, off_val2);
/*  585 */       GL11.glEnd();
/*      */ 
/*  587 */       if (i % 5 == 0) {
/*  588 */         GL11.glMaterial(1032, 4610, 
/*  589 */           this.fbm_specular);
/*  590 */         GL11.glMaterial(1032, 5633, 
/*  591 */           this.fbm_shininess);
/*  592 */         GL11.glMaterial(1032, 4608, this.fbm_y);
/*  593 */         GL11.glMaterial(1032, 4609, this.fbm_y);
/*      */ 
/*  595 */         off_val2 /= 2.0F;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  601 */     GL11.glMaterial(1032, 4610, this.fbm_specular);
/*  602 */     GL11.glMaterial(1032, 5633, 
/*  603 */       this.fbm_shininess);
/*  604 */     GL11.glMaterial(1032, 4608, this.fbm_z);
/*  605 */     GL11.glMaterial(1032, 4609, this.fbm_z);
/*      */ 
/*  607 */     GL11.glBegin(7);
/*  608 */     GL11.glVertex3f(-off_val, off_val, -a_val);
/*  609 */     GL11.glVertex3f(-off_val, off_val, a_val);
/*  610 */     GL11.glVertex3f(off_val, off_val, a_val);
/*  611 */     GL11.glVertex3f(off_val, off_val, -a_val);
/*  612 */     GL11.glEnd();
/*  613 */     GL11.glBegin(7);
/*  614 */     GL11.glVertex3f(-off_val, -off_val, -a_val);
/*  615 */     GL11.glVertex3f(-off_val, -off_val, a_val);
/*  616 */     GL11.glVertex3f(off_val, -off_val, a_val);
/*  617 */     GL11.glVertex3f(off_val, -off_val, -a_val);
/*  618 */     GL11.glEnd();
/*  619 */     GL11.glBegin(7);
/*  620 */     GL11.glVertex3f(off_val, -off_val, -a_val);
/*  621 */     GL11.glVertex3f(off_val, -off_val, a_val);
/*  622 */     GL11.glVertex3f(off_val, off_val, a_val);
/*  623 */     GL11.glVertex3f(off_val, off_val, -a_val);
/*  624 */     GL11.glEnd();
/*  625 */     GL11.glBegin(7);
/*  626 */     GL11.glVertex3f(-off_val, -off_val, -a_val);
/*  627 */     GL11.glVertex3f(-off_val, -off_val, a_val);
/*  628 */     GL11.glVertex3f(-off_val, off_val, a_val);
/*  629 */     GL11.glVertex3f(-off_val, off_val, -a_val);
/*  630 */     GL11.glEnd();
/*      */ 
/*  632 */     for (int i = 1; i <= (int)a_val; i++)
/*      */     {
/*  634 */       if (i % 5 == 0) {
/*  635 */         GL11.glMaterial(1032, 4610, 
/*  636 */           this.fbm_specular);
/*  637 */         GL11.glMaterial(1032, 5633, 
/*  638 */           this.fbm_shininess);
/*      */ 
/*  640 */         GL11.glMaterial(1032, 4608, 
/*  641 */           this.fbm_b2);
/*      */ 
/*  643 */         GL11.glMaterial(1032, 4609, 
/*  644 */           this.fbm_b2);
/*      */ 
/*  646 */         off_val *= 2.0F;
/*      */       }
/*      */ 
/*  649 */       GL11.glBegin(7);
/*  650 */       GL11.glVertex3f(-off_val, -off_val, i);
/*  651 */       GL11.glVertex3f(off_val, -off_val, i);
/*  652 */       GL11.glVertex3f(off_val, off_val, i);
/*  653 */       GL11.glVertex3f(-off_val, off_val, i);
/*  654 */       GL11.glEnd();
/*      */ 
/*  656 */       GL11.glBegin(7);
/*  657 */       GL11.glVertex3f(-off_val, -off_val, i);
/*  658 */       GL11.glVertex3f(-off_val, -off_val, i + small);
/*  659 */       GL11.glVertex3f(off_val, -off_val, i + small);
/*  660 */       GL11.glVertex3f(off_val, -off_val, i);
/*  661 */       GL11.glEnd();
/*      */ 
/*  663 */       GL11.glBegin(7);
/*  664 */       GL11.glVertex3f(-off_val, off_val, i);
/*  665 */       GL11.glVertex3f(-off_val, off_val, i + small);
/*  666 */       GL11.glVertex3f(off_val, off_val, i + small);
/*  667 */       GL11.glVertex3f(off_val, off_val, i);
/*  668 */       GL11.glEnd();
/*      */ 
/*  670 */       GL11.glBegin(7);
/*  671 */       GL11.glVertex3f(-off_val, -off_val, i);
/*  672 */       GL11.glVertex3f(-off_val, -off_val, i + small);
/*  673 */       GL11.glVertex3f(-off_val, off_val, i + small);
/*  674 */       GL11.glVertex3f(-off_val, off_val, i);
/*  675 */       GL11.glEnd();
/*      */ 
/*  677 */       GL11.glBegin(7);
/*  678 */       GL11.glVertex3f(off_val, -off_val, i);
/*  679 */       GL11.glVertex3f(off_val, -off_val, i + small);
/*  680 */       GL11.glVertex3f(off_val, off_val, i + small);
/*  681 */       GL11.glVertex3f(off_val, off_val, i);
/*  682 */       GL11.glEnd();
/*      */ 
/*  684 */       GL11.glBegin(7);
/*  685 */       GL11.glVertex3f(-off_val, -off_val, i + small);
/*  686 */       GL11.glVertex3f(off_val, -off_val, i + small);
/*  687 */       GL11.glVertex3f(off_val, off_val, i + small);
/*  688 */       GL11.glVertex3f(-off_val, off_val, i + small);
/*  689 */       GL11.glEnd();
/*      */ 
/*  692 */       GL11.glBegin(7);
/*  693 */       GL11.glVertex3f(-off_val2, -off_val2, -i);
/*  694 */       GL11.glVertex3f(off_val2, -off_val2, -i);
/*  695 */       GL11.glVertex3f(off_val2, off_val2, -i);
/*  696 */       GL11.glVertex3f(-off_val2, off_val2, -i);
/*  697 */       GL11.glEnd();
/*      */ 
/*  699 */       GL11.glBegin(7);
/*  700 */       GL11.glVertex3f(-off_val2, -off_val2, -i);
/*  701 */       GL11.glVertex3f(-off_val2, -off_val2, -i - small);
/*  702 */       GL11.glVertex3f(off_val2, -off_val2, -i - small);
/*  703 */       GL11.glVertex3f(off_val2, -off_val2, -i);
/*  704 */       GL11.glEnd();
/*      */ 
/*  706 */       GL11.glBegin(7);
/*  707 */       GL11.glVertex3f(-off_val2, off_val2, -i);
/*  708 */       GL11.glVertex3f(-off_val2, off_val2, -i - small);
/*  709 */       GL11.glVertex3f(off_val2, off_val2, -i - small);
/*  710 */       GL11.glVertex3f(off_val2, off_val2, -i);
/*  711 */       GL11.glEnd();
/*      */ 
/*  713 */       GL11.glBegin(7);
/*  714 */       GL11.glVertex3f(-off_val2, -off_val2, -i);
/*  715 */       GL11.glVertex3f(-off_val2, -off_val2, -i - small);
/*  716 */       GL11.glVertex3f(-off_val2, off_val2, -i - small);
/*  717 */       GL11.glVertex3f(-off_val2, off_val2, -i);
/*  718 */       GL11.glEnd();
/*      */ 
/*  720 */       GL11.glBegin(7);
/*  721 */       GL11.glVertex3f(off_val2, -off_val2, -i);
/*  722 */       GL11.glVertex3f(off_val2, -off_val2, -i - small);
/*  723 */       GL11.glVertex3f(off_val2, off_val2, -i - small);
/*  724 */       GL11.glVertex3f(off_val2, off_val2, -i);
/*  725 */       GL11.glEnd();
/*      */ 
/*  727 */       GL11.glBegin(7);
/*  728 */       GL11.glVertex3f(-off_val2, -off_val2, -i - small);
/*  729 */       GL11.glVertex3f(off_val2, -off_val2, -i - small);
/*  730 */       GL11.glVertex3f(off_val2, off_val2, -i - small);
/*  731 */       GL11.glVertex3f(-off_val2, off_val2, -i - small);
/*  732 */       GL11.glEnd();
/*      */ 
/*  734 */       if (i % 5 == 0) {
/*  735 */         GL11.glMaterial(1032, 4610, 
/*  736 */           this.fbm_specular);
/*  737 */         GL11.glMaterial(1032, 5633, 
/*  738 */           this.fbm_shininess);
/*  739 */         GL11.glMaterial(1032, 4608, this.fbm_z);
/*  740 */         GL11.glMaterial(1032, 4609, this.fbm_z);
/*      */ 
/*  742 */         off_val /= 2.0F;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void paintCone() {
/*  748 */     if ((this.wf_data == null) || (this.currPic > this.numPics)) {
/*  749 */       return;
/*      */     }
/*  751 */     int numRaysTheta = this.wf_data.length;
/*  752 */     int numRaysPhi = this.wf_data[0].length;
/*      */ 
/*  754 */     P4D col_1 = new P4D(new double[] { 0.300000011920929D, 0.8999999761581421D, 0.300000011920929D, 0.5D });
/*  755 */     P4D col_2 = new P4D(new double[] { 0.0D, 0.1000000014901161D, 0.699999988079071D, 0.5D });
/*  756 */     P4D col_3 = new P4D(new double[] { 0.800000011920929D, 0.2000000029802322D, 0.1000000014901161D, 0.5D });
/*      */ 
/*  761 */     P4D col = new P4D();
/*      */ 
/*  765 */     if (!this.paintCone_xz) {
/*  766 */       for (int p = 0; p < this.currPic; p++) {
/*  767 */         if (p < this.numPics / 2)
/*  768 */           col = (P4D)col_1.mul(2 * p / this.numPics)
/*  769 */             .add(
/*  770 */             col_2.mul((this.numPics - 2 * p) / 
/*  771 */             this.numPics));
/*      */         else {
/*  773 */           col = (P4D)col_3
/*  774 */             .mul(
/*  775 */             2 * (p - this.numPics / 2) / 
/*  776 */             this.numPics)
/*  777 */             .add(
/*  778 */             col_1
/*  779 */             .mul((this.numPics - 2 * (p - this.numPics / 2)) / 
/*  780 */             this.numPics));
/*      */         }
/*      */ 
/*  786 */         float[] fm_ambient = { (float)col.getX(0), 
/*  787 */           (float)col.getX(1), (float)col.getX(2), 
/*  788 */           (float)col.getX(3) };
/*  789 */         float[] fm_diffuse = { (float)col.getX(0), 
/*  790 */           (float)col.getX(1), (float)col.getX(2), 
/*  791 */           (float)col.getX(3) };
/*      */ 
/*  793 */         GL11.glMaterial(1032, 4610, 
/*  794 */           this.fbm_specular);
/*  795 */         GL11.glMaterial(1032, 5633, 
/*  796 */           this.fbm_shininess);
/*  797 */         GL11.glMaterial(1032, 4608, 
/*  798 */           toFloatBuffer(fm_ambient));
/*  799 */         GL11.glMaterial(1032, 4609, 
/*  800 */           toFloatBuffer(fm_diffuse));
/*      */ 
/*  803 */         GL11.glBegin(5);
/*  804 */         for (int i = 0; i < numRaysTheta - 1; i += 2) {
/*  805 */           float p1x = (float)this.wf_data[i][0][p].getX(1);
/*  806 */           float p1y = (float)this.wf_data[i][0][p].getX(2);
/*  807 */           float p1z = (float)this.wf_data[i][0][p].getX(0);
/*  808 */           float p2x = (float)this.wf_data[i][0][(p + 1)].getX(1);
/*  809 */           float p2y = (float)this.wf_data[i][0][(p + 1)].getX(2);
/*  810 */           float p2z = (float)this.wf_data[i][0][(p + 1)].getX(0);
/*  811 */           float p3x = (float)this.wf_data[(i + 1)][0][p].getX(1);
/*  812 */           float p3y = (float)this.wf_data[(i + 1)][0][p].getX(2);
/*  813 */           float p3z = (float)this.wf_data[(i + 1)][0][p].getX(0);
/*  814 */           float p4x = (float)this.wf_data[(i + 1)][0][(p + 1)].getX(1);
/*  815 */           float p4y = (float)this.wf_data[(i + 1)][0][(p + 1)].getX(2);
/*  816 */           float p4z = (float)this.wf_data[(i + 1)][0][(p + 1)].getX(0);
/*  817 */           float[] normal1 = { 
/*  818 */             (p2y - p1y) * (p3z - p1z) - (p2z - p1z) * (
/*  819 */             p3y - p1y), 
/*  820 */             (p2z - p1z) * (p3x - p1x) - (p2x - p1x) * (
/*  821 */             p3z - p1z), 
/*  822 */             (p2x - p1x) * (p3y - p1y) - (p2y - p1y) * (
/*  823 */             p3x - p1x) };
/*  824 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  825 */           GL11.glVertex3f(p1x, p1y, p1z);
/*  826 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  827 */           GL11.glVertex3f(p2x, p2y, p2z);
/*  828 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  829 */           GL11.glVertex3f(p3x, p3y, p3z);
/*  830 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  831 */           GL11.glVertex3f(p4x, p4y, p4z);
/*      */         }
/*  833 */         if (this.is_closed) {
/*  834 */           float p1x = (float)this.wf_data[(numRaysTheta - 1)][0][p].getX(1);
/*  835 */           float p1y = (float)this.wf_data[(numRaysTheta - 1)][0][p].getX(2);
/*  836 */           float p1z = (float)this.wf_data[(numRaysTheta - 1)][0][p].getX(0);
/*  837 */           float p2x = 
/*  838 */             (float)this.wf_data[(numRaysTheta - 1)][0][(p + 1)]
/*  838 */             .getX(1);
/*  839 */           float p2y = 
/*  840 */             (float)this.wf_data[(numRaysTheta - 1)][0][(p + 1)]
/*  840 */             .getX(2);
/*  841 */           float p2z = 
/*  842 */             (float)this.wf_data[(numRaysTheta - 1)][0][(p + 1)]
/*  842 */             .getX(0);
/*  843 */           float p3x = (float)this.wf_data[0][0][p].getX(1);
/*  844 */           float p3y = (float)this.wf_data[0][0][p].getX(2);
/*  845 */           float p3z = (float)this.wf_data[0][0][p].getX(0);
/*  846 */           float p4x = (float)this.wf_data[0][0][(p + 1)].getX(1);
/*  847 */           float p4y = (float)this.wf_data[0][0][(p + 1)].getX(2);
/*  848 */           float p4z = (float)this.wf_data[0][0][(p + 1)].getX(0);
/*  849 */           float[] normal1 = { 
/*  850 */             (p2y - p1y) * (p3z - p1z) - (p2z - p1z) * (
/*  851 */             p3y - p1y), 
/*  852 */             (p2z - p1z) * (p3x - p1x) - (p2x - p1x) * (
/*  853 */             p3z - p1z), 
/*  854 */             (p2x - p1x) * (p3y - p1y) - (p2y - p1y) * (
/*  855 */             p3x - p1x) };
/*  856 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  857 */           GL11.glVertex3f(p1x, p1y, p1z);
/*  858 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  859 */           GL11.glVertex3f(p2x, p2y, p2z);
/*  860 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  861 */           GL11.glVertex3f(p3x, p3y, p3z);
/*  862 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/*  863 */           GL11.glVertex3f(p4x, p4y, p4z);
/*      */         }
/*      */ 
/*  866 */         GL11.glEnd();
/*      */       }
/*      */ 
/*  870 */       GL11.glMaterial(1032, 4610, 
/*  871 */         this.fbm_specular);
/*  872 */       GL11.glMaterial(1032, 5633, 
/*  873 */         this.fbm_shininess);
/*  874 */       GL11.glMaterial(1032, 4608, this.fbm_b3);
/*  875 */       GL11.glMaterial(1032, 4609, this.fbm_b3);
/*      */ 
/*  878 */       double full_sec = this.wf_data[0][0][0].getX(0);
/*      */ 
/*  880 */       for (int p = 0; p <= this.currPic; p++) {
/*  881 */         GL11.glBegin(1);
/*  882 */         if ((this.wf_data[0][0][p].getX(0) >= full_sec) || (p == this.currPic)) {
/*  883 */           for (int i = 0; i < numRaysTheta - 1; i++) {
/*  884 */             float p1x = (float)this.wf_data[i][0][p].getX(1);
/*  885 */             float p1y = (float)this.wf_data[i][0][p].getX(2);
/*  886 */             float p1z = (float)this.wf_data[i][0][p].getX(0);
/*  887 */             float p3x = (float)this.wf_data[(i + 1)][0][p].getX(1);
/*  888 */             float p3y = (float)this.wf_data[(i + 1)][0][p].getX(2);
/*  889 */             float p3z = (float)this.wf_data[(i + 1)][0][p].getX(0);
/*  890 */             GL11.glVertex3f(p1x, p1y, p1z);
/*  891 */             GL11.glVertex3f(p3x, p3y, p3z);
/*      */           }
/*  893 */           if (this.is_closed) {
/*  894 */             float p1x = 
/*  895 */               (float)this.wf_data[(numRaysTheta - 1)][0][p]
/*  895 */               .getX(1);
/*  896 */             float p1y = 
/*  897 */               (float)this.wf_data[(numRaysTheta - 1)][0][p]
/*  897 */               .getX(2);
/*  898 */             float p1z = 
/*  899 */               (float)this.wf_data[(numRaysTheta - 1)][0][p]
/*  899 */               .getX(0);
/*  900 */             float p3x = (float)this.wf_data[0][0][p].getX(1);
/*  901 */             float p3y = (float)this.wf_data[0][0][p].getX(2);
/*  902 */             float p3z = (float)this.wf_data[0][0][p].getX(0);
/*  903 */             GL11.glVertex3f(p1x, p1y, p1z);
/*  904 */             GL11.glVertex3f(p3x, p3y, p3z);
/*      */           }
/*      */ 
/*  907 */           full_sec += 1.0D;
/*      */         }
/*  909 */         GL11.glEnd();
/*      */       }
/*      */ 
/*  913 */       double step = numRaysTheta / this.num_seg;
/*  914 */       for (int seg = 0; seg <= this.num_seg; seg++) {
/*  915 */         double index = seg * step;
/*  916 */         int iindex1 = (int)index;
/*  917 */         int iindex2 = iindex1 + 1;
/*  918 */         if (iindex1 >= numRaysTheta - 1)
/*  919 */           iindex1 = iindex2 = numRaysTheta - 1;
/*  920 */         double diff = index - iindex1;
/*      */ 
/*  922 */         GL11.glBegin(1);
/*  923 */         for (int p = 0; p < this.currPic; p++) {
/*  924 */           P4D p1_1 = this.wf_data[iindex1][0][p];
/*  925 */           P4D p1_2 = this.wf_data[iindex2][0][p];
/*  926 */           P4D p1 = p1_1;
/*  927 */           p1 = (P4D)p1.add(p1_2.sub(p1_1).mul(diff));
/*  928 */           P4D p2_1 = this.wf_data[iindex1][0][(p + 1)];
/*  929 */           P4D p2_2 = this.wf_data[iindex2][0][(p + 1)];
/*  930 */           P4D p2 = p2_1;
/*  931 */           p2 = (P4D)p2.add(p2_2.sub(p2_1).mul(diff));
/*  932 */           GL11.glVertex3d(p1.getX(1), p1.getX(2), p1.getX(0));
/*  933 */           GL11.glVertex3d(p2.getX(1), p2.getX(2), p2.getX(0));
/*      */         }
/*  935 */         GL11.glEnd();
/*  936 */         if ((this.is_closed) && (seg == this.num_seg - 1)) {
/*  937 */           seg++;
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  942 */     if (this.paintCone_xz)
/*      */     {
/*  944 */       for (int p = 0; p < this.currPic; p++) {
/*  945 */         if (p < this.numPics / 2)
/*  946 */           col = (P4D)col_1.mul(2 * p / this.numPics)
/*  947 */             .add(
/*  948 */             col_2.mul((this.numPics - 2 * p) / 
/*  949 */             this.numPics));
/*      */         else {
/*  951 */           col = (P4D)col_3
/*  952 */             .mul(
/*  953 */             2 * (p - this.numPics / 2) / 
/*  954 */             this.numPics)
/*  955 */             .add(
/*  956 */             col_1
/*  957 */             .mul((this.numPics - 2 * (p - this.numPics / 2)) / 
/*  958 */             this.numPics));
/*      */         }
/*      */ 
/*  964 */         float[] fm_ambient = { (float)col.getX(0), 
/*  965 */           (float)col.getX(1), (float)col.getX(2), 
/*  966 */           (float)col.getX(3) };
/*  967 */         float[] fm_diffuse = { (float)col.getX(0), 
/*  968 */           (float)col.getX(1), (float)col.getX(2), 
/*  969 */           (float)col.getX(3) };
/*      */ 
/*  971 */         GL11.glMaterial(1032, 4610, 
/*  972 */           this.fbm_specular);
/*  973 */         GL11.glMaterial(1032, 5633, 
/*  974 */           this.fbm_shininess);
/*  975 */         GL11.glMaterial(1032, 4608, 
/*  976 */           toFloatBuffer(fm_ambient));
/*  977 */         GL11.glMaterial(1032, 4609, 
/*  978 */           toFloatBuffer(fm_diffuse));
/*      */ 
/*  980 */         GL11.glBegin(5);
/*  981 */         for (int i = 0; i < numRaysTheta - 1; i += 2) {
/*  982 */           float p1x = (float)this.wf_data[i][0][p].getX(1);
/*  983 */           float p1y = (float)this.wf_data[i][0][p].getX(3);
/*  984 */           float p1z = (float)this.wf_data[i][0][p].getX(0);
/*  985 */           float p2x = (float)this.wf_data[i][0][(p + 1)].getX(1);
/*  986 */           float p2y = (float)this.wf_data[i][0][(p + 1)].getX(3);
/*  987 */           float p2z = (float)this.wf_data[i][0][(p + 1)].getX(0);
/*  988 */           float p3x = (float)this.wf_data[(i + 1)][0][p].getX(1);
/*  989 */           float p3y = (float)this.wf_data[(i + 1)][0][p].getX(3);
/*  990 */           float p3z = (float)this.wf_data[(i + 1)][0][p].getX(0);
/*  991 */           float p4x = (float)this.wf_data[(i + 1)][0][(p + 1)].getX(1);
/*  992 */           float p4y = (float)this.wf_data[(i + 1)][0][(p + 1)].getX(3);
/*  993 */           float p4z = (float)this.wf_data[(i + 1)][0][(p + 1)].getX(0);
/*  994 */           float[] normal1 = { 
/*  995 */             (p2y - p1y) * (p3z - p1z) - (p2z - p1z) * (
/*  996 */             p3y - p1y), 
/*  997 */             (p2z - p1z) * (p3x - p1x) - (p2x - p1x) * (
/*  998 */             p3z - p1z), 
/*  999 */             (p2x - p1x) * (p3y - p1y) - (p2y - p1y) * (
/* 1000 */             p3x - p1x) };
/* 1001 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1002 */           GL11.glVertex3f(p1x, p1y, p1z);
/* 1003 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1004 */           GL11.glVertex3f(p2x, p2y, p2z);
/* 1005 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1006 */           GL11.glVertex3f(p3x, p3y, p3z);
/* 1007 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1008 */           GL11.glVertex3f(p4x, p4y, p4z);
/*      */         }
/* 1010 */         if (this.is_closed) {
/* 1011 */           float p1x = (float)this.wf_data[(numRaysTheta - 1)][0][p].getX(1);
/* 1012 */           float p1y = (float)this.wf_data[(numRaysTheta - 1)][0][p].getX(3);
/* 1013 */           float p1z = (float)this.wf_data[(numRaysTheta - 1)][0][p].getX(0);
/* 1014 */           float p2x = 
/* 1015 */             (float)this.wf_data[(numRaysTheta - 1)][0][(p + 1)]
/* 1015 */             .getX(1);
/* 1016 */           float p2y = 
/* 1017 */             (float)this.wf_data[(numRaysTheta - 1)][0][(p + 1)]
/* 1017 */             .getX(3);
/* 1018 */           float p2z = 
/* 1019 */             (float)this.wf_data[(numRaysTheta - 1)][0][(p + 1)]
/* 1019 */             .getX(0);
/* 1020 */           float p3x = (float)this.wf_data[0][0][p].getX(1);
/* 1021 */           float p3y = (float)this.wf_data[0][0][p].getX(3);
/* 1022 */           float p3z = (float)this.wf_data[0][0][p].getX(0);
/* 1023 */           float p4x = (float)this.wf_data[0][0][(p + 1)].getX(1);
/* 1024 */           float p4y = (float)this.wf_data[0][0][(p + 1)].getX(3);
/* 1025 */           float p4z = (float)this.wf_data[0][0][(p + 1)].getX(0);
/* 1026 */           float[] normal1 = { 
/* 1027 */             (p2y - p1y) * (p3z - p1z) - (p2z - p1z) * (
/* 1028 */             p3y - p1y), 
/* 1029 */             (p2z - p1z) * (p3x - p1x) - (p2x - p1x) * (
/* 1030 */             p3z - p1z), 
/* 1031 */             (p2x - p1x) * (p3y - p1y) - (p2y - p1y) * (
/* 1032 */             p3x - p1x) };
/* 1033 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1034 */           GL11.glVertex3f(p1x, p1y, p1z);
/* 1035 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1036 */           GL11.glVertex3f(p2x, p2y, p2z);
/* 1037 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1038 */           GL11.glVertex3f(p3x, p3y, p3z);
/* 1039 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1040 */           GL11.glVertex3f(p4x, p4y, p4z);
/*      */         }
/*      */ 
/* 1043 */         GL11.glEnd();
/*      */       }
/*      */ 
/* 1047 */       GL11.glMaterial(1032, 4610, 
/* 1048 */         this.fbm_specular);
/* 1049 */       GL11.glMaterial(1032, 5633, 
/* 1050 */         this.fbm_shininess);
/* 1051 */       GL11.glMaterial(1032, 4608, this.fbm_b3);
/* 1052 */       GL11.glMaterial(1032, 4609, this.fbm_b3);
/*      */ 
/* 1055 */       double full_sec = this.wf_data[0][0][0].getX(0);
/*      */ 
/* 1057 */       for (int p = 0; p <= this.currPic; p++) {
/* 1058 */         GL11.glBegin(1);
/* 1059 */         if ((this.wf_data[0][0][p].getX(0) >= full_sec) || (p == this.currPic)) {
/* 1060 */           for (int i = 0; i < numRaysTheta - 1; i++) {
/* 1061 */             float p1x = (float)this.wf_data[i][0][p].getX(1);
/* 1062 */             float p1y = (float)this.wf_data[i][0][p].getX(3);
/* 1063 */             float p1z = (float)this.wf_data[i][0][p].getX(0);
/* 1064 */             float p3x = (float)this.wf_data[(i + 1)][0][p].getX(1);
/* 1065 */             float p3y = (float)this.wf_data[(i + 1)][0][p].getX(3);
/* 1066 */             float p3z = (float)this.wf_data[(i + 1)][0][p].getX(0);
/* 1067 */             GL11.glVertex3f(p1x, p1y, p1z);
/* 1068 */             GL11.glVertex3f(p3x, p3y, p3z);
/*      */           }
/* 1070 */           if (this.is_closed) {
/* 1071 */             float p1x = 
/* 1072 */               (float)this.wf_data[(numRaysTheta - 1)][0][p]
/* 1072 */               .getX(1);
/* 1073 */             float p1y = 
/* 1074 */               (float)this.wf_data[(numRaysTheta - 1)][0][p]
/* 1074 */               .getX(3);
/* 1075 */             float p1z = 
/* 1076 */               (float)this.wf_data[(numRaysTheta - 1)][0][p]
/* 1076 */               .getX(0);
/* 1077 */             float p3x = (float)this.wf_data[0][0][p].getX(1);
/* 1078 */             float p3y = (float)this.wf_data[0][0][p].getX(3);
/* 1079 */             float p3z = (float)this.wf_data[0][0][p].getX(0);
/* 1080 */             GL11.glVertex3f(p1x, p1y, p1z);
/* 1081 */             GL11.glVertex3f(p3x, p3y, p3z);
/*      */           }
/*      */ 
/* 1084 */           full_sec += 1.0D;
/*      */         }
/* 1086 */         GL11.glEnd();
/*      */       }
/*      */ 
/* 1090 */       double step = numRaysTheta / this.num_seg;
/* 1091 */       for (int seg = 0; seg <= this.num_seg; seg++) {
/* 1092 */         double index = seg * step;
/* 1093 */         int iindex1 = (int)index;
/* 1094 */         int iindex2 = iindex1 + 1;
/* 1095 */         if (iindex1 >= numRaysTheta - 1)
/* 1096 */           iindex1 = iindex2 = numRaysTheta - 1;
/* 1097 */         double diff = index - iindex1;
/*      */ 
/* 1099 */         GL11.glBegin(1);
/* 1100 */         for (int p = 0; p < this.currPic; p++) {
/* 1101 */           P4D p1_1 = this.wf_data[iindex1][0][p];
/* 1102 */           P4D p1_2 = this.wf_data[iindex2][0][p];
/* 1103 */           P4D p1 = p1_1;
/* 1104 */           p1 = (P4D)p1.add(p1_2.sub(p1_1).mul(diff));
/* 1105 */           P4D p2_1 = this.wf_data[iindex1][0][(p + 1)];
/* 1106 */           P4D p2_2 = this.wf_data[iindex2][0][(p + 1)];
/* 1107 */           P4D p2 = p2_1;
/* 1108 */           p2 = (P4D)p2.add(p2_2.sub(p2_1).mul(diff));
/* 1109 */           GL11.glVertex3d(p1.getX(1), p1.getX(3), p1.getX(0));
/* 1110 */           GL11.glVertex3d(p2.getX(1), p2.getX(3), p2.getX(0));
/*      */         }
/* 1112 */         GL11.glEnd();
/* 1113 */         if ((this.is_closed) && (seg == this.num_seg - 1))
/* 1114 */           seg++;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public FloatBuffer toFloatBuffer(float[] fl)
/*      */   {
/* 1122 */     int len = fl.length;
/* 1123 */     FloatBuffer buffer = BufferUtils.createFloatBuffer(len);
/* 1124 */     if (len == 1)
/* 1125 */       buffer.put(fl[0]);
/* 1126 */     else if (len == 4)
/* 1127 */       buffer.put(fl[0]).put(fl[1]).put(fl[2]).put(fl[3]);
/* 1128 */     buffer.flip();
/* 1129 */     return buffer;
/*      */   }
/*      */ 
/*      */   public void paint3d()
/*      */   {
/* 1134 */     if ((this.wf_data == null) || (this.currPic > this.numPics)) {
/* 1135 */       return;
/*      */     }
/* 1137 */     int numRaysTheta = this.wf_data.length;
/* 1138 */     int numRaysPhi = this.wf_data[0].length;
/*      */ 
/* 1140 */     P4D col_1 = new P4D(new double[] { 0.300000011920929D, 0.8999999761581421D, 0.300000011920929D, 0.5D });
/* 1141 */     P4D col_2 = new P4D(new double[] { 0.0D, 0.1000000014901161D, 0.699999988079071D, 0.5D });
/* 1142 */     P4D col_3 = new P4D(new double[] { 0.800000011920929D, 0.2000000029802322D, 0.1000000014901161D, 0.5D });
/* 1143 */     P4D col = new P4D();
/*      */ 
/* 1145 */     for (int p = 0; p <= this.currPic; p++) {
/* 1146 */       if (p < this.numPics / 2)
/* 1147 */         col = (P4D)col_1.mul(2 * p / this.numPics).add(
/* 1148 */           col_2
/* 1149 */           .mul((this.numPics - 2 * p) / 
/* 1150 */           this.numPics));
/*      */       else {
/* 1152 */         col = (P4D)col_3
/* 1153 */           .mul(
/* 1154 */           2 * (p - this.numPics / 2) / 
/* 1155 */           this.numPics)
/* 1156 */           .add(
/* 1157 */           col_1
/* 1158 */           .mul((this.numPics - 2 * (p - this.numPics / 2)) / 
/* 1159 */           this.numPics));
/*      */       }
/* 1161 */       float[] fm_ambient = { (float)col.getX(0), 
/* 1162 */         (float)col.getX(1), (float)col.getX(2), 
/* 1163 */         (float)col.getX(3) };
/* 1164 */       float[] fm_diffuse = { (float)col.getX(0), 
/* 1165 */         (float)col.getX(1), (float)col.getX(2), 
/* 1166 */         (float)col.getX(3) };
/*      */ 
/* 1168 */       GL11.glMaterial(1032, 4610, 
/* 1169 */         this.fbm_specular);
/* 1170 */       GL11.glMaterial(1032, 5633, 
/* 1171 */         this.fbm_shininess);
/* 1172 */       GL11.glMaterial(1032, 4608, 
/* 1173 */         toFloatBuffer(fm_ambient));
/* 1174 */       GL11.glMaterial(1032, 4609, 
/* 1175 */         toFloatBuffer(fm_diffuse));
/*      */ 
/* 1180 */       for (int j = 0; j < numRaysPhi - 1; j++) {
/* 1181 */         GL11.glBegin(5);
/* 1182 */         for (int i = 0; i < numRaysTheta - 1; i += 2) {
/* 1183 */           float p1x = (float)this.wf_data[i][j][p].getX(1);
/* 1184 */           float p1y = (float)this.wf_data[i][j][p].getX(2);
/* 1185 */           float p1z = (float)this.wf_data[i][j][p].getX(3);
/* 1186 */           float p2x = (float)this.wf_data[i][(j + 1)][p].getX(1);
/* 1187 */           float p2y = (float)this.wf_data[i][(j + 1)][p].getX(2);
/* 1188 */           float p2z = (float)this.wf_data[i][(j + 1)][p].getX(3);
/* 1189 */           float p3x = (float)this.wf_data[(i + 1)][j][p].getX(1);
/* 1190 */           float p3y = (float)this.wf_data[(i + 1)][j][p].getX(2);
/* 1191 */           float p3z = (float)this.wf_data[(i + 1)][j][p].getX(3);
/* 1192 */           float p4x = (float)this.wf_data[(i + 1)][(j + 1)][p].getX(1);
/* 1193 */           float p4y = (float)this.wf_data[(i + 1)][(j + 1)][p].getX(2);
/* 1194 */           float p4z = (float)this.wf_data[(i + 1)][(j + 1)][p].getX(3);
/* 1195 */           float[] normal1 = { 
/* 1196 */             (p2y - p1y) * (p3z - p1z) - (p2z - p1z) * (
/* 1197 */             p3y - p1y), 
/* 1198 */             (p2z - p1z) * (p3x - p1x) - (p2x - p1x) * (
/* 1199 */             p3z - p1z), 
/* 1200 */             (p2x - p1x) * (p3y - p1y) - (p2y - p1y) * (
/* 1201 */             p3x - p1x) };
/* 1202 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1203 */           GL11.glVertex3f(p1x, p1y, p1z);
/* 1204 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1205 */           GL11.glVertex3f(p2x, p2y, p2z);
/* 1206 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1207 */           GL11.glVertex3f(p3x, p3y, p3z);
/* 1208 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1209 */           GL11.glVertex3f(p4x, p4y, p4z);
/*      */         }
/*      */ 
/* 1216 */         if (this.is_closed) {
/* 1217 */           float p1x = (float)this.wf_data[(numRaysTheta - 1)][j][p].getX(1);
/* 1218 */           float p1y = (float)this.wf_data[(numRaysTheta - 1)][j][p].getX(2);
/* 1219 */           float p1z = (float)this.wf_data[(numRaysTheta - 1)][j][p].getX(3);
/* 1220 */           float p2x = 
/* 1221 */             (float)this.wf_data[(numRaysTheta - 1)][(j + 1)][p]
/* 1221 */             .getX(1);
/* 1222 */           float p2y = 
/* 1223 */             (float)this.wf_data[(numRaysTheta - 1)][(j + 1)][p]
/* 1223 */             .getX(2);
/* 1224 */           float p2z = 
/* 1225 */             (float)this.wf_data[(numRaysTheta - 1)][(j + 1)][p]
/* 1225 */             .getX(3);
/* 1226 */           float p3x = (float)this.wf_data[0][j][p].getX(1);
/* 1227 */           float p3y = (float)this.wf_data[0][j][p].getX(2);
/* 1228 */           float p3z = (float)this.wf_data[0][j][p].getX(3);
/* 1229 */           float p4x = (float)this.wf_data[0][(j + 1)][p].getX(1);
/* 1230 */           float p4y = (float)this.wf_data[0][(j + 1)][p].getX(2);
/* 1231 */           float p4z = (float)this.wf_data[0][(j + 1)][p].getX(3);
/* 1232 */           float[] normal1 = { 
/* 1233 */             (p2y - p1y) * (p3z - p1z) - (p2z - p1z) * (
/* 1234 */             p3y - p1y), 
/* 1235 */             (p2z - p1z) * (p3x - p1x) - (p2x - p1x) * (
/* 1236 */             p3z - p1z), 
/* 1237 */             (p2x - p1x) * (p3y - p1y) - (p2y - p1y) * (
/* 1238 */             p3x - p1x) };
/* 1239 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1240 */           GL11.glVertex3f(p1x, p1y, p1z);
/* 1241 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1242 */           GL11.glVertex3f(p2x, p2y, p2z);
/* 1243 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1244 */           GL11.glVertex3f(p3x, p3y, p3z);
/* 1245 */           GL11.glNormal3f(normal1[0], normal1[1], normal1[2]);
/* 1246 */           GL11.glVertex3f(p4x, p4y, p4z);
/*      */         }
/*      */ 
/* 1249 */         GL11.glEnd();
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1257 */     double step_x = numRaysTheta / this.num_seg_x;
/* 1258 */     double step_y = numRaysPhi / this.num_seg_y;
/* 1259 */     for (int p = 0; p <= this.currPic; p++) {
/* 1260 */       if ((numRaysPhi == 1) || (numRaysTheta == 1))
/*      */         continue;
/* 1262 */       for (int seg_x = 0; seg_x <= this.num_seg_x; seg_x++) {
/* 1263 */         double index_x = seg_x * step_x;
/* 1264 */         int iindex1_x = (int)index_x;
/* 1265 */         int iindex2_x = iindex1_x + 1;
/* 1266 */         if (iindex1_x >= numRaysTheta - 1)
/* 1267 */           iindex1_x = iindex2_x = numRaysTheta - 1;
/* 1268 */         double diff_x = index_x - iindex1_x;
/* 1269 */         GL11.glBegin(1);
/* 1270 */         for (int y = 0; y < numRaysPhi - 1; y++) {
/* 1271 */           P4D p1_1 = this.wf_data[iindex1_x][y][p];
/* 1272 */           P4D p1_2 = this.wf_data[iindex2_x][y][p];
/* 1273 */           P4D p1 = p1_1;
/* 1274 */           p1 = (P4D)p1.add(p1_2.sub(p1_1).mul(diff_x));
/* 1275 */           P4D p2_1 = this.wf_data[iindex1_x][(y + 1)][p];
/* 1276 */           P4D p2_2 = this.wf_data[iindex2_x][(y + 1)][p];
/* 1277 */           P4D p2 = p2_1;
/* 1278 */           p2 = p2_1;
/* 1279 */           p2 = (P4D)p2.add(p2_2.sub(p2_1).mul(diff_x));
/* 1280 */           GL11.glVertex3d(p1.getX(1), p1.getX(2), p1.getX(3));
/* 1281 */           GL11.glVertex3d(p2.getX(1), p2.getX(2), p2.getX(3));
/*      */         }
/*      */ 
/* 1285 */         GL11.glEnd();
/*      */       }
/* 1287 */       for (int seg_y = 0; seg_y <= this.num_seg_y; seg_y++) {
/* 1288 */         double index_y = seg_y * step_y;
/* 1289 */         int iindex1_y = (int)index_y;
/* 1290 */         int iindex2_y = iindex1_y + 1;
/* 1291 */         if (iindex1_y >= numRaysPhi - 1)
/* 1292 */           iindex1_y = iindex2_y = numRaysPhi - 1;
/* 1293 */         double diff_y = index_y - iindex1_y;
/* 1294 */         GL11.glBegin(1);
/* 1295 */         for (int x = 0; x < numRaysTheta - 1; x++) {
/* 1296 */           P4D p1_1 = this.wf_data[x][iindex1_y][p];
/* 1297 */           P4D p1_2 = this.wf_data[x][iindex2_y][p];
/* 1298 */           P4D p1 = p1_1;
/* 1299 */           p1 = (P4D)p1.add(p1_2.sub(p1_1).mul(this.diff_x));
/* 1300 */           P4D p2_1 = this.wf_data[(x + 1)][iindex1_y][p];
/* 1301 */           P4D p2_2 = this.wf_data[(x + 1)][iindex2_y][p];
/* 1302 */           P4D p2 = p2_1;
/* 1303 */           p2 = p2_1;
/* 1304 */           p2 = (P4D)p2.add(p2_2.sub(p2_1).mul(this.diff_x));
/* 1305 */           GL11.glVertex3d(p1.getX(1), p1.getX(2), p1.getX(3));
/* 1306 */           GL11.glVertex3d(p2.getX(1), p2.getX(2), p2.getX(3));
/*      */         }
/*      */ 
/* 1309 */         GL11.glEnd();
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public void paint()
/*      */   {
/* 1316 */     paintGL();
/*      */   }
/*      */ 
/*      */   public void setFrame(int i) {
/* 1320 */     this.currPic = i;
/*      */   }
/*      */ 
/*      */   public V3D calcNormal(int i, int j, int imax, int jmax)
/*      */   {
/* 1538 */     return new V3D();
/*      */   }
/*      */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     display.CanvasGL
 * JD-Core Version:    0.6.0
 */