package solver;

public abstract interface TypesSolver
{
  public static final int geodSpacelike = -1;
  public static final int geodLightlike = 0;
  public static final int geodTimelike = 1;
  public static final int geodBackward = -1;
  public static final int geodForward = 1;
  public static final int geodContraDir = -1;
  public static final int geodCoDir = 1;
  public static final int noBreak = 2;
  public static final int yesBreak = 3;
  public static final int breakCond = 4;
  public static final int breakOutside = 5;
  public static final int breakNumExceeded = 6;
  public static final int breakStepsize = 7;
  public static final int breakStartCond = 8;
}

/* Location:           /home/frutos/Documents/JWFront-Grave/JWaveFront.jar
 * Qualified Name:     solver.TypesSolver
 * JD-Core Version:    0.6.0
 */