package util;

/**
 * Fraction.
 * 
 * @author Team15
 */
public class Fraction
{

  private int numerator, denominator;

  /**
   * Constructor.
   * 
   * @param dec number to fraction
   */
  public Fraction(final double dec)
  {
    double decimal = dec;
    String stringNumber = String.valueOf(decimal);
    int numberDigitsDecimals = stringNumber.length() - 1 - stringNumber.indexOf('.');
    int denom = 1;
    for (int i = 0; i < numberDigitsDecimals; i++)
    {
      decimal *= 10;
      denom *= 10;
    }

    int num = (int) Math.round(decimal);
    int greatestCommonFactor = greatestCommonFactor(numerator, denom);
    this.numerator = num / greatestCommonFactor;
    this.denominator = denom / greatestCommonFactor;
  }

  /**
   * toString.
   */
  @Override
  public String toString()
  {
    return String.valueOf(numerator) + "/" + String.valueOf(denominator);
  }

  /**
   * greatestCommonFactor.
   * @param num number
   * @param denom denominator
   * @return greatestCommonFactor
   */
  public static int greatestCommonFactor(final int num, final int denom)
  {
    if (denom == 0)
    {
      return num;
    }
    return greatestCommonFactor(denom, num % denom);
  }
}
