package util;

/**
 * Complex - a class to handle complex numbers and complex operations.
 * 
 * @author team15
 * @version Rimplex v2
 */
public class Complex
{

  private double im;
  private double re;

  /**
   * Constructor.
   * 
   * @param real
   *          - real component
   * @param imaginary
   *          - imaginary component
   */
  public Complex(final double real, final double imaginary)
  {
    re = real;
    im = imaginary;
  }

  /**
   * add - add two complex numbers together.
   * 
   * @param other
   *          - other complex number added to this number
   * @return sum of two complex numbers
   */
  public Complex add(final Complex other)
  {

    return new Complex(re + other.getReal(), im + other.getImaginary());

  }

  /**
   * arg - calculates the principle value. This gives the angle of the complex number on the complex
   * plane measured from the x-axis where the positive direction is counterclockwise.
   * 
   * @return the angle in radians as a double
   */
  public double arg()
  {
    return Math.atan2(im, re);
  }

  /**
   * conjugate - gives the complex conjugate.
   * 
   * @return the complex conjugate
   */
  public Complex conjugate()
  {
    return new Complex(re, -im);
  }

  /**
   * divide - divides one complex number from another.
   * 
   * @param other
   *          - the number that divides this number
   * @return the division of two complex numbers
   */
  public Complex divide(final Complex other)
  {
    Complex out = null;
    if (other.getReal() != 0 || other.getImaginary() != 0)
    {
      out = this.multiply(other.inverse());
    }
    return out;
  }

  /**
   * exp - calculates the eulers number exponentiated by the complex number given.
   * 
   * @return e^this as a Complex number.
   */
  public Complex exp()
  {
    return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
  }

  /**
   * getImaginary.
   * 
   * @return imaginary part
   */
  public double getImaginary()
  {
    return im;
  }

  /**
   * getReal.
   * 
   * @return real part
   */
  public double getReal()
  {
    return re;
  }

  /**
   * inverse - gives the multiplicative inverse.
   * 
   * @return the inverse
   */
  public Complex inverse()
  {
    Complex out = null;
    if (re != 0 || im != 0)
    {
      double real = re / (re * re + im * im);
      double imaginary = -im / (re * re + im * im);
      out = new Complex(real, imaginary);
    }
    return out;
  }

  /**
   * log - calculates logarithm with a base of e of a complex number where imaginary output is
   * between -pi and pi. Note that this only gives one possible answer. To receive other values,
   * multiples of 2pi may be added to the imaginary component. This function will be highly unstable
   * for real < 0 and imaginary approximatedly 0 since it is discontinuous accross the -real axis.
   * 
   * @return the logarithm as a Complex number
   */
  public Complex log()
  {
    Complex out = null;

    if (re != 0 || im != 0)
    {
      out = new Complex(Math.log(this.mag()), this.arg());
    }
    return out;
  }

  /**
   * mag - calculates the magnitude of the complex number.
   *
   * @return the magnitude as a double
   */
  public double mag()
  {
    return Math.sqrt(re * re + im * im);
  }

  /**
   * multiply - multiply two complex numbers.
   * 
   * @param other
   *          - the number to be multiplied to this one
   * @return the multiplication of two complex numbers
   */
  public Complex multiply(final Complex other)
  {
    double real = re * other.getReal() - im * other.getImaginary();
    double imaginary = re * other.getImaginary() + im * other.getReal();
    return new Complex(real, imaginary);
  }

  /**
   * pow - calculates the this complex exponentiated by the complex number given as the argument.
   * This result gives a single value although other values may be possible if theta is not
   * restricted between -pi and pi.
   * 
   * @param other
   *          - exponent
   * @return this^other as a Complex number
   */
  public Complex pow(final Complex other)
  {
    Complex out = new Complex(0, 0);
    if (re != 0 || im != 0)
    {
      double r = mag();
      double theta = arg();

      double val1 = Math.pow(r, other.getReal()) * Math.exp(-theta * other.getImaginary());
      double val2 = other.getReal() * theta + other.getImaginary() * Math.log(r);

      double real = val1 * Math.cos(val2);
      double imaginary = val1 * Math.sin(val2);

      out = new Complex(real, imaginary);
    }
    return out;
  }

  /**
   * sqrt - returns the sqrt of the complex number with the smallest positive angle from the x-axis.
   * 
   * @return the square root as a Complex number.
   */
  public Complex sqrt()
  {
    double r = mag();
    double theta = arg();

    return new Complex(Math.sqrt(r) * Math.cos(theta / 2), Math.sqrt(r) * Math.sin(theta / 2));
  }

  /**
   * subtract - subtract two complex numbers.
   * 
   * @param other
   *          - the number to be subtracted from this number
   * @return subtraction of two complex numbers
   */
  public Complex subtract(final Complex other)
  {
    return new Complex(this.re - other.getReal(), this.im - other.getImaginary());
  }

  /**
   * toString - returns a string representing the complex number.
   * 
   * @return string representation of complex number
   */
  public String toString()
  {
    String i = "i";
    String out = null;
    if (im > 0)
    {
      out = re + "+" + im + i;
    }
    if (im < 0)
    {
      out = re + "-" + -im + i;
    }
    if (im == 0)
    {
      out = String.valueOf(re);
    }
    if (re == 0)
    {
      out = String.valueOf(im) + i;
    }
    return out;
  }

}
