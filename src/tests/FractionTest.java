package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Fraction;

/**
 * FractionTest
 * @author Charles Seaver
 * @version Rimplex v1
 */
public class FractionTest
{
 

  @Test
  public void testFraction()
  {
    Fraction fraction1 = new Fraction(0.5);
    Fraction fraction2 = new Fraction(0.25);
    Fraction fraction3 = new Fraction(5);
    Fraction fraction4 = new Fraction(1.34);
    Fraction fraction5 = new Fraction(0.41235);
  }

  @Test
  public void testToString()
  {
	  Fraction fraction1 = new Fraction(0.5);
	  Fraction fraction2 = new Fraction(0.25);
	  Fraction fraction3 = new Fraction(5);
	  Fraction fraction4 = new Fraction(1.34);
	  Fraction fraction5 = new Fraction(0.41235);
	  
	  assertEquals(fraction1.toString(), "1/2");
	  assertEquals(fraction2.toString(), "1/4");
	  assertEquals(fraction3.toString(), "5/1");
	  assertEquals(fraction4.toString(), "67/50");
	  assertEquals(fraction5.toString(), "8247/20000");
  }
}
