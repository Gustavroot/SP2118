package solver;

public abstract interface ConstantsSolver
{
  public static final double minStepsize = 1.E-15D;
  public static final double TINY = 1.E-30D;
  public static final double ERROR_INT = 1.0D;
  public static final double SAFETY = 0.9D;
  public static final double PGROW = -0.2D;
  public static final double PSHRNK = -0.25D;
  public static final double ERRCON = 0.000189D;
}

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     solver.ConstantsSolver
 * JD-Core Version:    0.6.0
 */