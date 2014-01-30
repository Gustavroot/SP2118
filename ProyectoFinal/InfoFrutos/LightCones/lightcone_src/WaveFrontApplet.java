/*      */ import display.CanvasGL;
/*      */ import display.CanvasSimple;
/*      */ import geometry.P2D;
/*      */ import geometry.P4D;
/*      */ import geometry.V3D;
/*      */ import geometry.V4D;
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Color;
/*      */ import java.awt.Component;
/*      */ import java.awt.Container;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.GridBagConstraints;
/*      */ import java.awt.GridBagLayout;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.swing.JApplet;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFormattedTextField;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JProgressBar;
/*      */ import javax.swing.JSlider;
/*      */ import javax.swing.JTextArea;
/*      */ import javax.swing.event.ChangeEvent;
/*      */ import javax.swing.event.ChangeListener;
/*      */ import org.lwjgl.LWJGLException;
/*      */ import solver.AbstractSolver;
/*      */ import solver.NumRecRungeKuttaSolver;
/*      */ import spacetime.AbstractMetric;
/*      */ import spacetime.DeSitterMetric;
/*      */ import spacetime.GoedelMetric;
/*      */ import spacetime.GoedelMetricTravelguide_0;
/*      */ import spacetime.KerrMetric;
/*      */ import spacetime.MinkowskiMetric;
/*      */ import spacetime.SchwarzschildMetric;
/*      */ import wavefront.AbstractWavefront;
/*      */ import wavefront.LinesWavefront;
/*      */ 
/*      */ public class WaveFrontApplet extends JApplet
/*      */ {
/*      */   private static final long serialVersionUID = 1L;
/*   82 */   private JPanel jContentPane = null;
/*      */   private JTextArea output;
/*      */   private CanvasSimple canvas_output;
/*      */   private CanvasGL canvas_output_gl;
/*   86 */   private boolean ready = false;
/*      */   JButton los;
/*      */   JButton savePic;
/*      */   JSlider position;
/*      */   JSlider zoom;
/*      */   Container c;
/*      */   static JFrame frame;
/*   94 */   private AbstractMetric metric = new MinkowskiMetric();
/*   95 */   private AbstractMetric mink_metric = new MinkowskiMetric();
/*   96 */   private AbstractMetric schw_metric = new SchwarzschildMetric();
/*   97 */   private AbstractMetric kerr_metric = new KerrMetric();
/*   98 */   private AbstractMetric goed_metric = new GoedelMetric();
/*   99 */   private AbstractMetric goed_travelguide_metric = new GoedelMetricTravelguide_0();
/*  100 */   private AbstractMetric desitter_metric = new DeSitterMetric();
/*      */ 
/*  102 */   private AbstractSolver solver = new NumRecRungeKuttaSolver();
/*      */ 
/*  105 */   private AbstractWavefront wf = new LinesWavefront();
/*      */   private JLabel lab_display;
/*      */   private JComboBox com_display;
/*      */   private JLabel lab_metric;
/*      */   private JComboBox com_metric;
/*      */   private JLabel lab_param1;
/*      */   private JFormattedTextField tex_param1;
/*      */   private JLabel lab_param2;
/*      */   private JFormattedTextField tex_param2;
/*      */   private JLabel lab_param5;
/*      */   private JComboBox com_param5;
/*      */   private JButton but_calc;
/*      */   private JLabel lab_startPos;
/*      */   private JLabel lab_startPos_x;
/*      */   private JLabel lab_startPos_y;
/*      */   private JLabel lab_startPos_z;
/*      */   private JFormattedTextField tex_startPos_x;
/*      */   private JFormattedTextField tex_startPos_y;
/*      */   private JFormattedTextField tex_startPos_z;
/*      */   private JLabel lab_startDirLoc;
/*      */   private JLabel lab_startDirLoc_x;
/*      */   private JLabel lab_startDirLoc_y;
/*      */   private JLabel lab_startDirLoc_z;
/*      */   private JFormattedTextField tex_startDirLoc_x;
/*      */   private JFormattedTextField tex_startDirLoc_y;
/*      */   private JFormattedTextField tex_startDirLoc_z;
/*      */   private JLabel lab_times;
/*      */   private JLabel lab_tStart;
/*      */   private JLabel lab_tObsStart;
/*      */   private JLabel lab_tStep;
/*      */   private JLabel lab_tEnd;
/*      */   private JFormattedTextField tex_tStart;
/*      */   private JFormattedTextField tex_tObsStart;
/*      */   private JFormattedTextField tex_tStep;
/*      */   private JFormattedTextField tex_tEnd;
/*      */   private JLabel lab_rays;
/*      */   private JLabel lab_numRaysTheta;
/*      */   private JLabel lab_numRaysPhi;
/*      */   private JLabel lab_thetaMin;
/*      */   private JLabel lab_thetaMax;
/*      */   private JLabel lab_phiMin;
/*      */   private JLabel lab_phiMax;
/*      */   private JFormattedTextField tex_thetaMin;
/*      */   private JFormattedTextField tex_thetaMax;
/*      */   private JFormattedTextField tex_phiMin;
/*      */   private JFormattedTextField tex_phiMax;
/*      */   private JFormattedTextField tex_numRaysTheta;
/*      */   private JFormattedTextField tex_numRaysPhi;
/*      */   private JLabel lab_geod;
/*      */   private JComboBox com_geodType;
/*      */   private JComboBox com_geodDir;
/*      */   private JProgressBar bar_progress;
/*      */   private JLabel lab_obstype;
/*      */   private JComboBox com_conetype;
/*  163 */   final String str_2d = "2d";
/*  164 */   final String str_cone = "light cone";
/*  165 */   final String str_3d = "3d";
/*      */ 
/*  167 */   final String str_mink = "Minkowski";
/*  168 */   final String str_schw = "Schwarzschild";
/*  169 */   final String str_kerr = "Kerr";
/*  170 */   final String str_goed = "Gödel";
/*  171 */   final String str_goed_travelguide = "Gödel Tr";
/*  172 */   final String str_desitter = "De-Sitter";
/*      */ 
/*  174 */   final String str_mass = "mass ";
/*  175 */   final String str_ang = "ang. momentum ";
/*  176 */   final String str_a = "a ";
/*  177 */   final String str_omega = "Omega ";
/*  178 */   final String str_omega_0 = "omega";
/*  179 */   final String str_l = "l";
/*      */ 
/*  181 */   final String str_licht = "lightlike";
/*  182 */   final String str_zeit = "timelike";
/*  183 */   final String str_lnrf = "nonrotating";
/*  184 */   final String str_lsf = "static";
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/*  193 */     frame = new JFrame();
/*  194 */     frame.setTitle("JWFront");
/*  195 */     frame.setBackground(Color.lightGray);
/*  196 */     frame.getContentPane().setLayout(new BorderLayout());
/*      */ 
/*  198 */     WaveFrontApplet wfapplet = new WaveFrontApplet();
/*  199 */     wfapplet.setSize(1000, 800);
/*      */ 
/*  205 */     frame.getContentPane().add("Center", wfapplet);
/*  206 */     frame.pack();
/*  207 */     frame.getContentPane().setSize(1000, 800);
/*  208 */     frame.setResizable(false);
/*  209 */     frame.setVisible(true);
/*      */   }
/*      */ 
/*      */   public WaveFrontApplet()
/*      */   {
/*  218 */     initialize();
/*      */   }
/*      */ 
/*      */   static void addComponent(Container cont, GridBagLayout gbl, Component c, int x, int y, int width, int height, double weightx, double weighty)
/*      */   {
/*  227 */     GridBagConstraints gbc = new GridBagConstraints();
/*  228 */     gbc.fill = 1;
/*  229 */     gbc.anchor = 10;
/*  230 */     gbc.gridx = x; gbc.gridy = y;
/*  231 */     gbc.gridwidth = width; gbc.gridheight = height;
/*  232 */     gbc.weightx = weightx; gbc.weighty = weighty;
/*  233 */     gbl.setConstraints(c, gbc);
/*  234 */     cont.add(c);
/*      */   }
/*      */ 
/*      */   public void initialize()
/*      */   {
/*  243 */     this.output = new JTextArea();
/*  244 */     this.canvas_output = new CanvasSimple();
/*      */     try {
/*  246 */       this.canvas_output_gl = new CanvasGL();
/*      */     }
/*      */     catch (LWJGLException e1)
/*      */     {
/*  251 */       e1.printStackTrace();
/*      */     }
/*      */ 
/*  254 */     JFrame f = new JFrame();
/*  255 */     f.setDefaultCloseOperation(3);
/*  256 */     this.c = f.getContentPane();
/*  257 */     GridBagLayout gbl = new GridBagLayout();
/*  258 */     this.c.setLayout(gbl);
/*      */ 
/*  261 */     JLabel lab_top = new JLabel(" ");
/*  262 */     JLabel lab_bot = new JLabel(" ");
/*      */ 
/*  264 */     this.lab_display = new JLabel("  display mode ");
/*  265 */     this.com_display = new JComboBox();
/*  266 */     this.com_display.addItem("2d");
/*  267 */     this.com_display.addItem("light cone");
/*  268 */     this.com_display.addItem("3d");
/*      */ 
/*  271 */     this.lab_metric = new JLabel("  spacetime ");
/*  272 */     this.com_metric = new JComboBox();
/*      */ 
/*  274 */     this.lab_param1 = new JLabel();
/*  275 */     this.lab_param1.setHorizontalAlignment(4);
/*  276 */     this.tex_param1 = new JFormattedTextField();
/*  277 */     this.tex_param1.setText("1");
/*      */ 
/*  279 */     this.lab_param2 = new JLabel();
/*  280 */     this.lab_param2.setHorizontalAlignment(4);
/*  281 */     this.tex_param2 = new JFormattedTextField();
/*  282 */     this.tex_param2.setText("0.1");
/*      */ 
/*  284 */     this.lab_param5 = new JLabel("observer type ");
/*  285 */     this.lab_param5.setHorizontalAlignment(4);
/*  286 */     this.com_param5 = new JComboBox();
/*  287 */     this.com_param5.addItem("nonrotating");
/*  288 */     this.com_param5.addItem("static");
/*      */ 
/*  292 */     this.lab_param1.setVisible(false);
/*  293 */     this.tex_param1.setVisible(false);
/*  294 */     this.lab_param2.setVisible(false);
/*  295 */     this.tex_param2.setVisible(false);
/*  296 */     this.lab_param5.setVisible(false);
/*  297 */     this.com_param5.setVisible(false);
/*      */ 
/*  300 */     this.but_calc = new JButton("calculate");
/*      */ 
/*  302 */     this.lab_startPos = new JLabel("  start position");
/*  303 */     this.lab_startPos_x = new JLabel("    x ");
/*  304 */     this.lab_startPos_y = new JLabel("    y ");
/*  305 */     this.lab_startPos_z = new JLabel("    z ");
/*  306 */     this.lab_startPos_x.setHorizontalAlignment(4);
/*  307 */     this.lab_startPos_y.setHorizontalAlignment(4);
/*  308 */     this.lab_startPos_z.setHorizontalAlignment(4);
/*  309 */     this.tex_startPos_x = new JFormattedTextField("3");
/*  310 */     this.tex_startPos_y = new JFormattedTextField("0");
/*  311 */     this.tex_startPos_z = new JFormattedTextField("0");
/*      */ 
/*  313 */     this.lab_startDirLoc = new JLabel("  local start direction");
/*  314 */     this.lab_startDirLoc_x = new JLabel("    e_1 ");
/*  315 */     this.lab_startDirLoc_y = new JLabel("    e_2 ");
/*  316 */     this.lab_startDirLoc_z = new JLabel("    e_3 ");
/*  317 */     this.lab_startDirLoc_x.setHorizontalAlignment(4);
/*  318 */     this.lab_startDirLoc_y.setHorizontalAlignment(4);
/*  319 */     this.lab_startDirLoc_z.setHorizontalAlignment(4);
/*  320 */     this.tex_startDirLoc_x = new JFormattedTextField("-1");
/*  321 */     this.tex_startDirLoc_y = new JFormattedTextField("0");
/*  322 */     this.tex_startDirLoc_z = new JFormattedTextField("0");
/*      */ 
/*  324 */     this.lab_rays = new JLabel("  angular ranges and number of rays");
/*  325 */     this.lab_thetaMin = new JLabel("    thetaMin ");
/*  326 */     this.lab_thetaMax = new JLabel("    thetaMax ");
/*  327 */     this.lab_phiMin = new JLabel("    phiMin ");
/*  328 */     this.lab_phiMax = new JLabel("    phiMax ");
/*  329 */     this.lab_numRaysTheta = new JLabel("    numTheta ");
/*  330 */     this.lab_numRaysPhi = new JLabel("    numPhi ");
/*  331 */     this.lab_thetaMin.setHorizontalAlignment(4);
/*  332 */     this.lab_thetaMax.setHorizontalAlignment(4);
/*  333 */     this.lab_phiMin.setHorizontalAlignment(4);
/*  334 */     this.lab_phiMax.setHorizontalAlignment(4);
/*  335 */     this.lab_numRaysTheta.setHorizontalAlignment(4);
/*  336 */     this.lab_numRaysPhi.setHorizontalAlignment(4);
/*  337 */     this.tex_thetaMin = new JFormattedTextField("-90");
/*  338 */     this.tex_thetaMax = new JFormattedTextField("90");
/*  339 */     this.tex_phiMin = new JFormattedTextField("-90");
/*  340 */     this.tex_phiMax = new JFormattedTextField("90");
/*  341 */     this.tex_numRaysTheta = new JFormattedTextField("100");
/*  342 */     this.tex_numRaysPhi = new JFormattedTextField("1");
/*      */ 
/*  344 */     this.lab_times = new JLabel("  times");
/*  345 */     this.lab_tStart = new JLabel("    tStart ");
/*  346 */     this.lab_tObsStart = new JLabel("    tObsStart ");
/*  347 */     this.lab_tStep = new JLabel("    tStep ");
/*  348 */     this.lab_tEnd = new JLabel("    tEnd ");
/*  349 */     this.lab_tStart.setHorizontalAlignment(4);
/*  350 */     this.lab_tObsStart.setHorizontalAlignment(4);
/*  351 */     this.lab_tStep.setHorizontalAlignment(4);
/*  352 */     this.lab_tEnd.setHorizontalAlignment(4);
/*  353 */     this.tex_tStart = new JFormattedTextField("0");
/*  354 */     this.tex_tObsStart = new JFormattedTextField("0");
/*  355 */     this.tex_tStep = new JFormattedTextField("0.1");
/*  356 */     this.tex_tEnd = new JFormattedTextField("10");
/*      */ 
/*  358 */     this.lab_geod = new JLabel("  type of geodesic");
/*  359 */     this.com_geodType = new JComboBox();
/*  360 */     this.com_geodType.addItem("lightlike");
/*  361 */     this.com_geodType.addItem("timelike");
/*      */ 
/*  364 */     this.com_conetype = new JComboBox();
/*  365 */     this.com_conetype.addItem("xy");
/*  366 */     this.com_conetype.addItem("xz");
/*      */ 
/*  368 */     this.lab_obstype = new JLabel("observer type");
/*      */ 
/*  370 */     this.com_metric.addItem("Minkowski");
/*  371 */     this.com_metric.addItem("Schwarzschild");
/*  372 */     this.com_metric.addItem("Kerr");
/*  373 */     this.com_metric.addItem("Gödel");
/*  374 */     this.com_metric.addItem("Gödel Tr");
/*  375 */     this.com_metric.addItem("De-Sitter");
/*      */ 
/*  378 */     this.bar_progress = new JProgressBar();
/*      */ 
/*  380 */     this.savePic = new JButton("save picture");
/*  381 */     this.savePic.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  383 */         String displayMode = WaveFrontApplet.this.com_display.getSelectedItem().toString();
/*  384 */         BufferedImage currImage = new BufferedImage(10, 10, 2);
/*  385 */         if (displayMode == "2d") {
/*  386 */           currImage = WaveFrontApplet.this.canvas_output.getImage();
/*      */         }
/*      */ 
/*  388 */         if (displayMode != "light cone");
/*      */         try
/*      */         {
/*  394 */           File file = new File("./newimage.png");
/*  395 */           ImageIO.write(currImage, "png", file);
/*      */ 
/*  398 */           file = new File("./newimage.jpg");
/*  399 */           ImageIO.write(currImage, "jpg", file);
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/*      */         }
/*      */       }
/*      */     });
/*  408 */     addComponent(this.c, gbl, this.lab_display, 0, 0, 20, 2, 0.0D, 0.0D);
/*  409 */     addComponent(this.c, gbl, this.com_display, 20, 0, 20, 2, 0.0D, 0.0D);
/*  410 */     addComponent(this.c, gbl, lab_top, 0, 2, 40, 1, 0.0D, 1.0D);
/*      */ 
/*  412 */     addComponent(this.c, gbl, this.lab_metric, 0, 3, 20, 2, 0.0D, 0.01D);
/*  413 */     addComponent(this.c, gbl, this.com_metric, 20, 3, 20, 2, 0.0D, 0.01D);
/*      */ 
/*  415 */     addComponent(this.c, gbl, this.lab_param1, 5, 5, 15, 2, 0.0D, 0.01D);
/*  416 */     addComponent(this.c, gbl, this.tex_param1, 25, 5, 15, 2, 0.0D, 0.01D);
/*  417 */     addComponent(this.c, gbl, this.lab_param2, 5, 7, 15, 2, 0.0D, 0.01D);
/*  418 */     addComponent(this.c, gbl, this.tex_param2, 25, 7, 15, 2, 0.0D, 0.01D);
/*      */ 
/*  422 */     addComponent(this.c, gbl, this.lab_startPos, 0, 15, 15, 2, 0.0D, 0.01D);
/*  423 */     addComponent(this.c, gbl, this.lab_startPos_x, 10, 17, 15, 2, 0.0D, 0.01D);
/*  424 */     addComponent(this.c, gbl, this.tex_startPos_x, 25, 17, 15, 2, 0.0D, 0.01D);
/*  425 */     addComponent(this.c, gbl, this.lab_startPos_y, 10, 19, 15, 2, 0.0D, 0.01D);
/*  426 */     addComponent(this.c, gbl, this.tex_startPos_y, 25, 19, 15, 2, 0.0D, 0.01D);
/*  427 */     addComponent(this.c, gbl, this.lab_startPos_z, 10, 21, 15, 2, 0.0D, 0.01D);
/*  428 */     addComponent(this.c, gbl, this.tex_startPos_z, 25, 21, 15, 2, 0.0D, 0.01D);
/*  429 */     addComponent(this.c, gbl, this.com_conetype, 25, 21, 15, 2, 0.0D, 0.01D);
/*      */ 
/*  431 */     addComponent(this.c, gbl, this.lab_startDirLoc, 0, 23, 15, 2, 0.0D, 0.01D);
/*  432 */     addComponent(this.c, gbl, this.lab_startDirLoc_x, 10, 25, 15, 2, 0.0D, 0.01D);
/*  433 */     addComponent(this.c, gbl, this.tex_startDirLoc_x, 25, 25, 15, 2, 0.0D, 0.01D);
/*  434 */     addComponent(this.c, gbl, this.lab_startDirLoc_y, 10, 27, 15, 2, 0.0D, 0.01D);
/*  435 */     addComponent(this.c, gbl, this.tex_startDirLoc_y, 25, 27, 15, 2, 0.0D, 0.01D);
/*  436 */     addComponent(this.c, gbl, this.lab_startDirLoc_z, 10, 29, 15, 2, 0.0D, 0.01D);
/*  437 */     addComponent(this.c, gbl, this.tex_startDirLoc_z, 25, 29, 15, 2, 0.0D, 0.01D);
/*      */ 
/*  439 */     addComponent(this.c, gbl, this.lab_times, 0, 31, 15, 2, 0.0D, 0.01D);
/*  440 */     addComponent(this.c, gbl, this.lab_tStart, 0, 33, 10, 2, 0.1D, 0.01D);
/*  441 */     addComponent(this.c, gbl, this.tex_tStart, 10, 33, 10, 2, 0.1D, 0.01D);
/*  442 */     addComponent(this.c, gbl, this.lab_tObsStart, 20, 33, 10, 2, 0.1D, 0.01D);
/*  443 */     addComponent(this.c, gbl, this.tex_tObsStart, 30, 33, 10, 2, 0.1D, 0.01D);
/*  444 */     addComponent(this.c, gbl, this.lab_tStep, 0, 35, 10, 2, 0.1D, 0.01D);
/*  445 */     addComponent(this.c, gbl, this.tex_tStep, 10, 35, 10, 2, 0.1D, 0.01D);
/*  446 */     addComponent(this.c, gbl, this.lab_tEnd, 20, 35, 10, 2, 0.1D, 0.01D);
/*  447 */     addComponent(this.c, gbl, this.tex_tEnd, 30, 35, 10, 2, 0.1D, 0.01D);
/*      */ 
/*  449 */     addComponent(this.c, gbl, this.lab_rays, 0, 37, 15, 2, 0.0D, 0.01D);
/*  450 */     addComponent(this.c, gbl, this.lab_thetaMin, 0, 39, 10, 2, 0.1D, 0.01D);
/*  451 */     addComponent(this.c, gbl, this.tex_thetaMin, 10, 39, 10, 2, 0.1D, 0.01D);
/*  452 */     addComponent(this.c, gbl, this.lab_thetaMax, 20, 39, 10, 2, 0.1D, 0.01D);
/*  453 */     addComponent(this.c, gbl, this.tex_thetaMax, 30, 39, 10, 2, 0.1D, 0.01D);
/*  454 */     addComponent(this.c, gbl, this.lab_phiMin, 0, 41, 10, 2, 0.1D, 0.01D);
/*  455 */     addComponent(this.c, gbl, this.tex_phiMin, 10, 41, 10, 2, 0.1D, 0.01D);
/*  456 */     addComponent(this.c, gbl, this.lab_phiMax, 20, 41, 10, 2, 0.1D, 0.01D);
/*  457 */     addComponent(this.c, gbl, this.tex_phiMax, 30, 41, 10, 2, 0.1D, 0.01D);
/*  458 */     addComponent(this.c, gbl, this.lab_numRaysTheta, 0, 43, 10, 2, 0.1D, 0.01D);
/*  459 */     addComponent(this.c, gbl, this.tex_numRaysTheta, 10, 43, 10, 2, 0.1D, 0.01D);
/*  460 */     addComponent(this.c, gbl, this.lab_numRaysPhi, 20, 43, 10, 2, 0.1D, 0.01D);
/*  461 */     addComponent(this.c, gbl, this.tex_numRaysPhi, 30, 43, 10, 2, 0.1D, 0.01D);
/*      */ 
/*  463 */     addComponent(this.c, gbl, this.lab_geod, 0, 45, 20, 2, 0.0D, 0.01D);
/*  464 */     addComponent(this.c, gbl, this.com_geodType, 20, 45, 20, 2, 0.0D, 0.01D);
/*      */ 
/*  466 */     addComponent(this.c, gbl, this.bar_progress, 0, 47, 40, 3, 0.0D, 0.01D);
/*      */ 
/*  468 */     addComponent(this.c, gbl, this.but_calc, 0, 50, 40, 3, 0.0D, 0.1D);
/*      */ 
/*  470 */     this.lab_numRaysPhi.setVisible(false);
/*  471 */     this.lab_phiMin.setVisible(false);
/*  472 */     this.lab_phiMax.setVisible(false);
/*  473 */     this.lab_startPos_z.setVisible(false);
/*  474 */     this.tex_numRaysPhi.setVisible(false);
/*  475 */     this.tex_phiMin.setVisible(false);
/*  476 */     this.tex_phiMax.setVisible(false);
/*  477 */     this.tex_startPos_z.setVisible(false);
/*  478 */     this.com_conetype.setVisible(true);
/*      */ 
/*  482 */     getContentPane().add(this.c, "East");
/*      */ 
/*  484 */     this.com_metric.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent arg0) {
/*  486 */         if (arg0.getStateChange() != 1)
/*  487 */           return;
/*  488 */         V3D[] e = new V3D[2];
/*  489 */         V4D dir = WaveFrontApplet.this.wf.getStartDirLoc();
/*  490 */         V3D dir3d = new V3D(new double[] { dir.getX(1), dir.getX(2), dir.getX(3) });
/*  491 */         String metricName = arg0.getItem().toString();
/*  492 */         System.out.println(metricName);
/*  493 */         if (metricName == "Minkowski") {
/*  494 */           WaveFrontApplet.this.metric = WaveFrontApplet.this.mink_metric;
/*  495 */           WaveFrontApplet.this.lab_param1.setVisible(false);
/*  496 */           WaveFrontApplet.this.tex_param1.setVisible(false);
/*  497 */           WaveFrontApplet.this.lab_param2.setVisible(false);
/*  498 */           WaveFrontApplet.this.tex_param2.setVisible(false);
/*  499 */           WaveFrontApplet.this.lab_param5.setVisible(false);
/*  500 */           WaveFrontApplet.this.com_param5.setVisible(false);
/*  501 */           WaveFrontApplet.this.canvas_output.setShowMass(false, 0.0D);
/*  502 */           e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  503 */           e[1] = ((V3D)dir3d.cro(e[0]));
/*  504 */           WaveFrontApplet.this.wf.setRotAxes(e);
/*      */         }
/*  506 */         if (metricName == "Schwarzschild") {
/*  507 */           WaveFrontApplet.this.metric = WaveFrontApplet.this.schw_metric;
/*  508 */           WaveFrontApplet.this.lab_param1.setText("mass ");
/*  509 */           WaveFrontApplet.this.lab_param1.setVisible(true);
/*  510 */           WaveFrontApplet.this.tex_param1.setVisible(true);
/*  511 */           WaveFrontApplet.this.lab_param2.setVisible(false);
/*  512 */           WaveFrontApplet.this.tex_param2.setVisible(false);
/*  513 */           WaveFrontApplet.this.lab_param5.setVisible(false);
/*  514 */           WaveFrontApplet.this.com_param5.setVisible(false);
/*  515 */           WaveFrontApplet.this.canvas_output.setShowMass(true, Double.valueOf(WaveFrontApplet.this.tex_param1.getText()).doubleValue());
/*  516 */           e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*  517 */           if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  518 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  519 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xy")) {
/*  520 */             e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*      */           }
/*  522 */           else if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  523 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  524 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xz")) {
/*  525 */             e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*      */           }
/*  527 */           e[1] = ((V3D)dir3d.cro(e[0]));
/*  528 */           WaveFrontApplet.this.wf.setRotAxes(e);
/*      */         }
/*  530 */         if (metricName == "Kerr") {
/*  531 */           WaveFrontApplet.this.metric = WaveFrontApplet.this.kerr_metric;
/*  532 */           WaveFrontApplet.this.lab_param1.setText("mass ");
/*  533 */           WaveFrontApplet.this.lab_param1.setVisible(true);
/*  534 */           WaveFrontApplet.this.tex_param1.setVisible(true);
/*  535 */           WaveFrontApplet.this.lab_param2.setText("ang. momentum ");
/*  536 */           WaveFrontApplet.this.lab_param2.setVisible(true);
/*  537 */           WaveFrontApplet.this.tex_param2.setVisible(true);
/*  538 */           WaveFrontApplet.this.lab_param5.setVisible(true);
/*  539 */           WaveFrontApplet.this.com_param5.setVisible(true);
/*  540 */           WaveFrontApplet.this.canvas_output.setShowMass(true, Double.valueOf(WaveFrontApplet.this.tex_param1.getText()).doubleValue());
/*  541 */           e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*  542 */           if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  543 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  544 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xy")) {
/*  545 */             e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*      */           }
/*  548 */           else if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  549 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  550 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xz")) {
/*  551 */             e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*      */           }
/*      */ 
/*  554 */           e[1] = ((V3D)dir3d.cro(e[0]));
/*  555 */           WaveFrontApplet.this.wf.setRotAxes(e);
/*      */         }
/*      */ 
/*  558 */         if (metricName == "Gödel") {
/*  559 */           WaveFrontApplet.this.metric = WaveFrontApplet.this.goed_metric;
/*  560 */           WaveFrontApplet.this.lab_param1.setText("a ");
/*  561 */           WaveFrontApplet.this.lab_param1.setVisible(true);
/*  562 */           WaveFrontApplet.this.tex_param1.setVisible(true);
/*  563 */           WaveFrontApplet.this.lab_param2.setText("Omega ");
/*  564 */           WaveFrontApplet.this.lab_param2.setVisible(true);
/*  565 */           WaveFrontApplet.this.tex_param2.setVisible(true);
/*  566 */           WaveFrontApplet.this.lab_param5.setVisible(true);
/*  567 */           WaveFrontApplet.this.com_param5.setVisible(true);
/*  568 */           WaveFrontApplet.this.canvas_output.setShowMass(false, 0.0D);
/*  569 */           e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  570 */           if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  571 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  572 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xy")) {
/*  573 */             e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*      */           }
/*  576 */           else if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  577 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  578 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xz")) {
/*  579 */             e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*      */           }
/*      */ 
/*  582 */           e[1] = ((V3D)dir3d.cro(e[0]));
/*  583 */           WaveFrontApplet.this.wf.setRotAxes(e);
/*      */         }
/*      */ 
/*  586 */         if (metricName == "Gödel Tr") {
/*  587 */           WaveFrontApplet.this.metric = WaveFrontApplet.this.goed_travelguide_metric;
/*  588 */           WaveFrontApplet.this.lab_param1.setText("omega");
/*  589 */           WaveFrontApplet.this.lab_param1.setVisible(true);
/*  590 */           WaveFrontApplet.this.tex_param1.setVisible(true);
/*  591 */           WaveFrontApplet.this.lab_param2.setVisible(false);
/*  592 */           WaveFrontApplet.this.tex_param2.setVisible(false);
/*  593 */           WaveFrontApplet.this.lab_param5.setVisible(false);
/*  594 */           WaveFrontApplet.this.com_param5.setVisible(false);
/*  595 */           WaveFrontApplet.this.canvas_output.setShowMass(false, 0.0D);
/*  596 */           e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  597 */           if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  598 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  599 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xy")) {
/*  600 */             e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*      */           }
/*  603 */           else if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  604 */             (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  605 */             (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xz")) {
/*  606 */             e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*      */           }
/*      */ 
/*  609 */           e[1] = ((V3D)dir3d.cro(e[0]));
/*  610 */           WaveFrontApplet.this.wf.setRotAxes(e);
/*      */         }
/*      */ 
/*  613 */         if (metricName == "De-Sitter") {
/*  614 */           WaveFrontApplet.this.metric = WaveFrontApplet.this.desitter_metric;
/*  615 */           WaveFrontApplet.this.lab_param1.setText("l");
/*  616 */           WaveFrontApplet.this.lab_param1.setVisible(true);
/*  617 */           WaveFrontApplet.this.tex_param1.setVisible(true);
/*  618 */           WaveFrontApplet.this.lab_param2.setVisible(false);
/*  619 */           WaveFrontApplet.this.tex_param2.setVisible(false);
/*  620 */           WaveFrontApplet.this.lab_param5.setVisible(false);
/*  621 */           WaveFrontApplet.this.com_param5.setVisible(false);
/*  622 */           WaveFrontApplet.this.canvas_output.setShowMass(false, 0.0D);
/*  623 */           e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  624 */           e[1] = ((V3D)dir3d.cro(e[0]));
/*  625 */           WaveFrontApplet.this.wf.setRotAxes(e);
/*      */         }
/*  627 */         WaveFrontApplet.this.wf.setMetric(WaveFrontApplet.this.metric);
/*  628 */         WaveFrontApplet.this.solver.setMetric(WaveFrontApplet.this.metric);
/*  629 */         WaveFrontApplet.this.canvas_output_gl.setSize(600, 600);
/*  630 */         WaveFrontApplet.this.canvas_output_gl.validate();
/*  631 */         WaveFrontApplet.this.ready = false;
/*      */       }
/*      */     });
/*  639 */     this.com_geodType.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent arg0) {
/*  641 */         if (arg0.getStateChange() != 1)
/*  642 */           return;
/*  643 */         String geodType = arg0.getItem().toString();
/*  644 */         if (geodType == "lightlike")
/*  645 */           WaveFrontApplet.this.solver.setGeodType(0);
/*  646 */         else if (geodType == "timelike")
/*  647 */           WaveFrontApplet.this.solver.setGeodType(1);
/*      */       }
/*      */     });
/*  653 */     this.com_display.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent arg0) {
/*  655 */         if (arg0.getStateChange() != 1)
/*  656 */           return;
/*  657 */         String displayMode = arg0.getItem().toString();
/*      */ 
/*  659 */         WaveFrontApplet.this.changeDisplayMode(displayMode);
/*      */       }
/*      */     });
/*  665 */     this.com_conetype.addItemListener(new ItemListener() {
/*      */       public void itemStateChanged(ItemEvent arg0) {
/*  667 */         if (arg0.getStateChange() != 1)
/*  668 */           return;
/*  669 */         V3D[] e = new V3D[2];
/*  670 */         V4D dir = WaveFrontApplet.this.wf.getStartDirLoc();
/*  671 */         V3D dir3d = new V3D(new double[] { dir.getX(1), dir.getX(2), dir.getX(3) });
/*  672 */         if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  673 */           (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  674 */           (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xy")) {
/*  675 */           e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*  676 */           if (WaveFrontApplet.this.com_metric.getSelectedItem().toString() == "Gödel") {
/*  677 */             e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*      */           }
/*  679 */           WaveFrontApplet.this.canvas_output_gl.setPaintConeXY(true);
/*  680 */           WaveFrontApplet.this.canvas_output.setPaintXY(true);
/*      */         }
/*  682 */         else if (((WaveFrontApplet.this.com_display.getSelectedItem().toString() == "light cone") || 
/*  683 */           (WaveFrontApplet.this.com_display.getSelectedItem().toString() == "2d")) && 
/*  684 */           (WaveFrontApplet.this.com_conetype.getSelectedItem().toString() == "xz")) {
/*  685 */           e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  686 */           if (WaveFrontApplet.this.com_metric.getSelectedItem().toString() == "Gödel") {
/*  687 */             e[0] = new V3D(new double[] { 0.0D, 1.0D, 0.0D });
/*      */           }
/*  689 */           WaveFrontApplet.this.canvas_output_gl.setPaintConeXY(false);
/*  690 */           WaveFrontApplet.this.canvas_output.setPaintXY(false);
/*      */         }
/*  692 */         e[1] = ((V3D)dir3d.cro(e[0]));
/*  693 */         WaveFrontApplet.this.wf.setRotAxes(e);
/*      */       }
/*      */     });
/*  697 */     this.but_calc.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent arg0) {
/*  699 */         WaveFrontApplet.this.calculate();
/*      */       }
/*      */     });
/*  703 */     this.los = new JButton("play");
/*  704 */     this.los.addActionListener(new ActionListener() {
/*      */       public void actionPerformed(ActionEvent e) {
/*  706 */         if (!WaveFrontApplet.this.ready)
/*  707 */           WaveFrontApplet.this.calculate();
/*  708 */         WaveFrontApplet.this.paint_film();
/*      */       }
/*      */     });
/*  711 */     this.position = 
/*  712 */       new JSlider(0, 
/*  712 */       0, 1, 0);
/*  713 */     this.position.addChangeListener(new ChangeListener()
/*      */     {
/*      */       public void stateChanged(ChangeEvent arg0) {
/*  716 */         WaveFrontApplet.this.paint_frame(((JSlider)arg0.getSource()).getValue());
/*      */       }
/*      */     });
/*  720 */     this.zoom = new JSlider(1, 0, 1000, 105);
/*  721 */     this.zoom.addChangeListener(new ChangeListener() {
/*      */       public void stateChanged(ChangeEvent arg0) {
/*  723 */         String displayMode = WaveFrontApplet.this.com_display.getSelectedItem().toString();
/*  724 */         double val = ((JSlider)arg0.getSource()).getValue();
/*  725 */         if (displayMode == "2d") {
/*  726 */           WaveFrontApplet.this.canvas_output.setScale(val / 10.0D);
/*  727 */           WaveFrontApplet.this.canvas_output.repaint();
/*      */         }
/*  729 */         else if ((displayMode == "light cone") || (displayMode == "3d")) {
/*  730 */           WaveFrontApplet.this.canvas_output_gl.setScale((float)(val / 10.0D));
/*  731 */           WaveFrontApplet.this.canvas_output_gl.repaint();
/*      */         }
/*      */       }
/*      */     });
/*  739 */     getContentPane().add(this.canvas_output, "Center");
/*  740 */     getContentPane().add(this.los, "South");
/*  741 */     getContentPane().add(this.position, "North");
/*  742 */     getContentPane().add(this.zoom, "West");
/*  743 */     setSize(1050, 667);
/*      */ 
/*  748 */     init_wf();
/*      */   }
/*      */ 
/*      */   public void changeDisplayMode(String displayMode) {
/*  752 */     if (displayMode == "2d") {
/*  753 */       getContentPane().removeAll();
/*  754 */       getContentPane().add(this.canvas_output, "Center");
/*  755 */       getContentPane().add(this.los, "South");
/*  756 */       getContentPane().add(this.c, "East");
/*  757 */       getContentPane().add(this.position, "North");
/*  758 */       getContentPane().add(this.zoom, "West");
/*  759 */       this.lab_numRaysPhi.setVisible(false);
/*  760 */       this.lab_phiMin.setVisible(false);
/*  761 */       this.lab_phiMax.setVisible(false);
/*  762 */       this.lab_startPos_z.setVisible(false);
/*  763 */       this.tex_numRaysPhi.setText("1");
/*  764 */       this.tex_phiMin.setText("-90");
/*  765 */       this.tex_phiMax.setText("90");
/*  766 */       this.tex_startPos_z.setText("0");
/*  767 */       this.tex_numRaysPhi.setVisible(false);
/*  768 */       this.tex_phiMin.setVisible(false);
/*  769 */       this.tex_phiMax.setVisible(false);
/*  770 */       this.tex_startPos_z.setVisible(false);
/*  771 */       this.com_conetype.setVisible(true);
/*      */     }
/*  775 */     else if ((displayMode == "light cone") || (displayMode == "3d")) {
/*  776 */       getContentPane().removeAll();
/*  777 */       getContentPane().add(this.canvas_output_gl, "Center");
/*  778 */       getContentPane().add(this.los, "South");
/*  779 */       getContentPane().add(this.c, "East");
/*  780 */       getContentPane().add(this.position, "North");
/*  781 */       getContentPane().add(this.zoom, "West");
/*  782 */       this.lab_numRaysPhi.setVisible(false);
/*  783 */       this.lab_phiMin.setVisible(false);
/*  784 */       this.lab_phiMax.setVisible(false);
/*  785 */       this.lab_startPos_z.setVisible(false);
/*  786 */       this.tex_numRaysPhi.setText("1");
/*  787 */       this.tex_phiMin.setText("-90");
/*  788 */       this.tex_phiMax.setText("90");
/*  789 */       this.tex_startPos_z.setText("0");
/*  790 */       this.tex_numRaysPhi.setVisible(false);
/*  791 */       this.tex_phiMin.setVisible(false);
/*  792 */       this.tex_phiMax.setVisible(false);
/*  793 */       this.tex_startPos_z.setVisible(false);
/*  794 */       this.com_conetype.setVisible(false);
/*      */ 
/*  796 */       if ((displayMode == "light cone") || (displayMode == "2d")) {
/*  797 */         this.canvas_output_gl.setPaintCone(true);
/*  798 */         this.com_conetype.setVisible(true);
/*      */       }
/*  800 */       else if (displayMode == "3d") {
/*  801 */         this.canvas_output_gl.setPaint3d(true);
/*  802 */         this.lab_numRaysPhi.setVisible(true);
/*  803 */         this.lab_phiMin.setVisible(true);
/*  804 */         this.lab_phiMax.setVisible(true);
/*  805 */         this.lab_startPos_z.setVisible(true);
/*  806 */         this.tex_numRaysPhi.setVisible(true);
/*  807 */         this.tex_phiMin.setVisible(true);
/*  808 */         this.tex_phiMax.setVisible(true);
/*  809 */         this.tex_startPos_z.setVisible(true);
/*  810 */         this.tex_numRaysPhi.setText("10");
/*      */ 
/*  812 */         double tStart = Double.valueOf(this.tex_tStart.getText()).doubleValue();
/*  813 */         double tObsStart = Double.valueOf(this.tex_tObsStart.getText()).doubleValue();
/*  814 */         double tEnd = Double.valueOf(this.tex_tEnd.getText()).doubleValue();
/*  815 */         double tStep = Double.valueOf(this.tex_tStep.getText()).doubleValue();
/*  816 */         int numPics = (int)((tEnd - tObsStart) / tStep);

// La primera expresion no funciona, la segunda si y la tercera tambien.

/*  817 */         if (numPics > 15)
                      // this.tex_tStep.setText((tEnd - tObsStart) / 10.0D);
                      // this.tex_tStep.setText(""+(tEnd - tObsStart) / 10.0D);
/*  818 */            this.tex_tStep.setText(Double.toString((tEnd - tObsStart) / 10.0D)); 
/*      */       }
/*      */     }
/*  821 */     getContentPane().validate();
/*      */   }
/*      */ 
/*      */   public void init_wf()
/*      */   {
/*  829 */     this.solver.setMetric(this.metric);
/*  830 */     this.solver.setGeodType(0);
/*  831 */     this.solver.setTimeDir(1);
/*  832 */     this.solver.setGeodDest(1);
/*  833 */     this.solver.setEpsilons(1.0E-10D, 0.0D);
/*  834 */     P4D boundingBoxMin = new P4D(new double[] { (-1.0D / 0.0D), 
/*  835 */       -500.0D, -500.0D, -500.0D });
/*  836 */     P4D boundingBoxMax = new P4D(new double[] { (1.0D / 0.0D), 
/*  837 */       500.0D, 500.0D, 500.0D });
/*  838 */     this.solver.setBoundingBox(boundingBoxMin, boundingBoxMax);
/*      */ 
/*  841 */     P4D startPos = new P4D(new double[] { 0.0D, 3.0D, 0.0D, 0.0D });
/*      */ 
/*  844 */     this.wf.setStartPos(startPos);
/*      */ 
/*  846 */     V4D startDirLoc = new V4D(new double[] { 0.0D, -1.0D, 0.0D, 0.0D });
/*  847 */     this.wf.setStartDirLoc(startDirLoc);
/*  848 */     V3D[] e = new V3D[2];
/*  849 */     V4D dir = startDirLoc;
/*  850 */     V3D dir3d = new V3D(new double[] { dir.getX(1), dir.getX(2), dir.getX(3) });
/*  851 */     e[0] = new V3D(new double[] { 0.0D, 0.0D, 1.0D });
/*  852 */     e[1] = ((V3D)dir3d.cro(e[0]));
/*  853 */     this.wf.setRotAxes(e);
/*      */ 
/*  855 */     double tStart = 0.0D;
/*  856 */     double tObsStart = 0.0D;
/*  857 */     double tStep = 0.1D;
/*  858 */     double tEnd = 10.0D;
/*  859 */     P4D times = new P4D(new double[] { tStart, tObsStart, tStep, tEnd });
/*  860 */     this.wf.setTimes(times);
/*      */ 
/*  862 */     int numRaysTheta = 100;
/*  863 */     int numRaysPhi = 1;
/*  864 */     P2D numRays = new P2D(new double[] { numRaysTheta, numRaysPhi });
/*  865 */     this.wf.setNumRays(numRays);
/*      */ 
/*  867 */     double thetaMin = -1.570796326794897D;
/*  868 */     double thetaMax = 1.570796326794897D;
/*  869 */     double phiMin = -1.570796326794897D;
/*  870 */     double phiMax = 1.570796326794897D;
/*  871 */     P2D thetaSpan = new P2D(new double[] { thetaMin, thetaMax });
/*  872 */     P2D phiSpan = new P2D(new double[] { phiMin, phiMax });
/*  873 */     this.wf.setThetaSpan(thetaSpan);
/*  874 */     this.wf.setPhiSpan(phiSpan);
/*      */ 
/*  877 */     this.wf.setMetric(this.metric);
/*  878 */     this.wf.setSolver(this.solver);
/*      */ 
/*  880 */     double lambdaStep = 0.01D;
/*  881 */     int maxNumPoints = 2;
/*  882 */     this.wf.setLambdaStep(lambdaStep);
/*  883 */     this.wf.setMaxNumPoints(maxNumPoints);
/*      */   }
/*      */ 
/*      */   public void paint_film()
/*      */   {
/*  888 */     if (!this.ready)
/*      */     {
/*  891 */       this.wf.calculate(this.bar_progress, getGraphics());
/*      */ 
/*  894 */       P4D times = this.wf.getTimes();
/*  895 */       this.position.setMaximum((int)((times.getX(3) - times.getX(1)) / times.getX(2)));
/*      */     }
/*      */ 
/*  898 */     this.ready = true;
/*  899 */     P4D[][][] wf_data = this.wf.getWaveFront();
/*      */ 
/*  903 */     String displayMode = this.com_display.getSelectedItem().toString();
/*  904 */     if (displayMode == "2d") {
/*  905 */       this.canvas_output.setData(wf_data);
/*  906 */       Graphics arg0 = this.canvas_output.getGraphics();
/*  907 */       this.canvas_output.setFrame(-1);
/*  908 */       this.canvas_output.paint(arg0);
/*  909 */       for (int i = 0; i < wf_data[0][0].length; i++) {
/*  910 */         this.canvas_output.setFrame(i);
/*  911 */         this.canvas_output.paint(arg0);
/*      */       }
/*      */     }
/*  914 */     if ((displayMode == "light cone") || (displayMode == "3d")) {
/*  915 */       this.canvas_output_gl.setData(wf_data);
/*  916 */       this.canvas_output_gl.setFrame(-1);
/*  917 */       this.canvas_output_gl.setPlaying();
/*      */     }
/*      */   }
/*      */ 
/*      */   private void calculate()
/*      */   {
/*  924 */     String metricName = this.com_metric.getSelectedItem().toString();
/*  925 */     String frameName = this.com_param5.getSelectedItem().toString();
/*      */ 
/*  927 */     P4D startPos = new P4D(new double[] { 0.0D, 
/*  928 */       Double.valueOf(this.tex_startPos_x.getText()).doubleValue(), 
/*  929 */       Double.valueOf(this.tex_startPos_y.getText()).doubleValue(), 
/*  930 */       Double.valueOf(this.tex_startPos_z.getText()).doubleValue() });
/*  931 */     V4D startDirLoc = new V4D(new double[] { 0.0D, 
/*  932 */       Double.valueOf(this.tex_startDirLoc_x.getText()).doubleValue(), 
/*  933 */       Double.valueOf(this.tex_startDirLoc_y.getText()).doubleValue(), 
/*  934 */       Double.valueOf(this.tex_startDirLoc_z.getText()).doubleValue() });
/*      */ 
/*  936 */     P4D times = new P4D(new double[] { 
/*  937 */       Double.valueOf(this.tex_tStart.getText()).doubleValue(), 
/*  938 */       Double.valueOf(this.tex_tObsStart.getText()).doubleValue(), 
/*  939 */       Double.valueOf(this.tex_tStep.getText()).doubleValue(), 
/*  940 */       Double.valueOf(this.tex_tEnd.getText()).doubleValue() });
/*      */ 
/*  942 */     P2D thetaSpan = new P2D(new double[] { 
/*  943 */       Double.valueOf(this.tex_thetaMin.getText()).doubleValue(), 
/*  944 */       Double.valueOf(this.tex_thetaMax.getText()).doubleValue() });
/*      */ 
/*  946 */     P2D phiSpan = new P2D(new double[] { 
/*  947 */       Double.valueOf(this.tex_phiMin.getText()).doubleValue(), 
/*  948 */       Double.valueOf(this.tex_phiMax.getText()).doubleValue() });
/*      */ 
/*  950 */     if (Math.abs(thetaSpan.getX(1) - thetaSpan.getX(0)) >= 360.0D) {
/*  951 */       this.canvas_output.setClosed(true);
/*  952 */       this.canvas_output_gl.setClosed(true);
/*      */     }
/*      */     else {
/*  955 */       this.canvas_output.setClosed(false);
/*  956 */       this.canvas_output_gl.setClosed(false);
/*      */     }
/*  958 */     int numSeg = Math.abs((int)thetaSpan.getX(0) - (int)thetaSpan.getX(1)) / 9;
/*  959 */     int numSeg3D_1 = Math.abs((int)thetaSpan.getX(0) - (int)thetaSpan.getX(1)) / 9;
/*  960 */     int numSeg3D_2 = Math.abs((int)phiSpan.getX(0) - (int)phiSpan.getX(1)) / 9;
/*  961 */     this.canvas_output_gl.setNumSeg(numSeg);
/*  962 */     this.canvas_output_gl.setNumSeg3D(numSeg3D_1, numSeg3D_2);
/*      */ 
/*  964 */     thetaSpan = (P2D)thetaSpan.mul(0.0174532925199433D);
/*  965 */     phiSpan = (P2D)phiSpan.mul(0.0174532925199433D);
/*  966 */     P2D numRays = new P2D(new double[] { 
/*  967 */       Double.valueOf(this.tex_numRaysTheta.getText()).doubleValue(), 
/*  968 */       Double.valueOf(this.tex_numRaysPhi.getText()).doubleValue() });
/*      */ 
/*  972 */     if (metricName == "Minkowski") {
/*  973 */       this.canvas_output.setShowMass(false, 0.0D);
/*  974 */       this.canvas_output.setShowErgo(false, 0.0D, 0.0D, false, 0.0D, 0.0D);
/*      */     }
/*  976 */     if (metricName == "Schwarzschild") {
/*  977 */       this.metric.setParam(10, Double.valueOf(this.tex_param1.getText()).doubleValue());
/*  978 */       this.canvas_output.setShowMass(true, 2.0D * Double.valueOf(this.tex_param1.getText()).doubleValue());
/*  979 */       this.canvas_output.setShowErgo(false, 0.0D, 0.0D, false, 0.0D, 0.0D);
/*      */     }
/*  981 */     if (metricName == "Kerr") {
/*  982 */       this.metric.setParam(10, Double.valueOf(this.tex_param1.getText()).doubleValue());
/*  983 */       this.metric.setParam(11, Double.valueOf(this.tex_param2.getText()).doubleValue());
/*  984 */       double mass = this.metric.getParam(10);
/*  985 */       double ang = this.metric.getParam(11);
/*  986 */       double rmin = mass - Math.sqrt(mass * mass - ang * ang);
/*  987 */       double rmax = mass + Math.sqrt(mass * mass - ang * ang);
/*      */ 
/*  989 */       this.canvas_output.setShowMass(true, rmin);
/*  990 */       boolean ergo_xz = this.com_conetype.getSelectedItem().toString() == "xz";
/*  991 */       this.canvas_output.setShowErgo(true, rmax, rmax, ergo_xz, mass, ang);
/*      */ 
/*  993 */       if (frameName == "nonrotating")
/*  994 */         this.metric.setFrame(9);
/*  995 */       else if (frameName == "static")
/*  996 */         this.metric.setFrame(8);
/*      */     }
/*  998 */     if (metricName == "Gödel") {
/*  999 */       this.metric.setParam(13, Double.valueOf(this.tex_param1.getText()).doubleValue());
/* 1000 */       this.metric.setParam(14, Double.valueOf(this.tex_param2.getText()).doubleValue());
/*      */ 
/* 1002 */       this.canvas_output.setShowMass(false, 0.0D);
/* 1003 */       this.canvas_output.setShowErgo(false, 0.0D, 0.0D, false, 0.0D, 0.0D);
/*      */     }
/*      */ 
/* 1006 */     if (metricName == "Gödel Tr") {
/* 1007 */       this.metric.setParam(15, Double.valueOf(this.tex_param1.getText()).doubleValue());
/*      */ 
/* 1009 */       this.canvas_output.setShowMass(false, 0.0D);
/* 1010 */       this.canvas_output.setShowErgo(false, 0.0D, 0.0D, false, 0.0D, 0.0D);
/*      */     }
/* 1012 */     if (metricName == "De-Sitter") {
/* 1013 */       this.metric.setParam(16, Double.valueOf(this.tex_param1.getText()).doubleValue());
/*      */ 
/* 1015 */       this.canvas_output.setShowMass(false, 0.0D);
/* 1016 */       this.canvas_output.setShowErgo(false, 0.0D, 0.0D, false, 0.0D, 0.0D);
/*      */     }
/* 1018 */     startPos = this.metric.coordTransf(startPos, 0, this.metric.getCoordSys());
/* 1019 */     this.wf.setStartPos(startPos);
/* 1020 */     this.wf.setStartDirLoc(startDirLoc);
/* 1021 */     this.wf.setTimes(times);
/* 1022 */     this.wf.setNumRays(numRays);
/* 1023 */     this.wf.setThetaSpan(thetaSpan);
/* 1024 */     this.wf.setPhiSpan(phiSpan);
/*      */ 
/* 1027 */     this.wf.calculate(this.bar_progress, getGraphics());
/*      */ 
/* 1030 */     this.position.setMaximum((int)((times.getX(3) - times.getX(1)) / times.getX(2)));
/*      */ 
/* 1032 */     this.ready = true;
/*      */   }
/*      */ 
/*      */   public void paint_frame(int currFrame)
/*      */   {
/* 1039 */     String displayMode = this.com_display.getSelectedItem().toString();
/* 1040 */     if (displayMode == "2d") {
/* 1041 */       this.canvas_output.setFrame(currFrame);
/* 1042 */       this.canvas_output.paint(this.canvas_output.getGraphics());
/*      */     }
/* 1044 */     else if ((displayMode == "light cone") || (displayMode == "3d")) {
/* 1045 */       this.canvas_output_gl.setFrame(currFrame);
/*      */     }
/*      */   }
/*      */ 
/*      */   private JPanel getJContentPane()
/*      */   {
/* 1056 */     if (this.jContentPane == null) {
/* 1057 */       this.jContentPane = new JPanel();
/* 1058 */       this.jContentPane.setLayout(new BorderLayout());
/*      */     }
/* 1060 */     return this.jContentPane;
/*      */   }
/*      */ }

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     WaveFrontApplet
 * JD-Core Version:    0.6.0
 */
