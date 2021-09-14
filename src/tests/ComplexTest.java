package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Complex;

/**
 * ComplexTest.
 * @author Team 15
 * @version Rimplex v3
 */
public class ComplexTest
{
  private static final double EPSILON = 1.0e-3;

  /**
   * testAdd. 
   */
  @Test
  public void testAdd()
  {
    Complex num1 = new Complex(1.5, 3);
    Complex num2 = new Complex(5, 3.2);
    Complex ans = num1.add(num2);
    assertEquals(6.5, ans.getReal(), EPSILON);
    assertEquals(6.2, ans.getImaginary(), EPSILON);
  }

  /**
   * testSubtract.
   */
  @Test
  public void testSubtract()
  {
    Complex num1 = new Complex(1.5, 3);
    Complex num2 = new Complex(5, 3.2);
    Complex ans = num1.subtract(num2);
    assertEquals(-3.5, ans.getReal(), EPSILON);
    assertEquals(-.2, ans.getImaginary(), EPSILON);
  }
  
  /**
   * testMultiply.
   */
  @Test
  public void testMultiply()
  {
    Complex num1 = new Complex(1.5, 3);
    Complex num2 = new Complex(5, 3.2);
    Complex ans = num1.multiply(num2);
    assertEquals(1.5 * 5 - 3 * 3.2, ans.getReal(), EPSILON);
    assertEquals(1.5 * 3.2 + 3 * 5, ans.getImaginary(), EPSILON);
  }

  /**
   * testDivide.
   */
  @Test
  public void testDivide()
  {
    Complex num1 = new Complex(1.5, 3);
    Complex num2 = new Complex(5, 3.2);
    Complex num3 = new Complex(0, 0);
    Complex ans = num1.divide(num2);
    assertEquals(num1.multiply(num2.inverse()).getReal(), ans.getReal(), EPSILON);
    assertEquals(num1.multiply(num2.inverse()).getImaginary(), ans.getImaginary(), EPSILON);
    assertNull(num1.divide(num3));
  }

  /**
   * testInverse.
   */
  @Test
  public void testInverse()
  {
    Complex num1 = new Complex(1.5, 3);
    Complex num2 = new Complex(0, 0);
    Complex ans = num1.inverse();
    assertEquals(1, num1.multiply(num1.inverse()).getReal(), EPSILON);
    assertEquals(0, num1.multiply(ans).getImaginary(), EPSILON);
    assertNull(num2.inverse());
  }
  
  /**
   * testLog.
   */
  @Test
  public void testLog()
  {
    Complex num1 = new Complex(1,0);
    Complex num2 = new Complex(0,1);
    Complex num3 = new Complex(1,1);
    Complex num4 = new Complex(-1,1);
    Complex num5 = new Complex(-1,-1);
    Complex num6 = new Complex(1, -1);
    Complex num7 = new Complex(0, 0);
    assertEquals(0, num1.log().getReal(), EPSILON);
    assertEquals(0, num1.log().getImaginary(), EPSILON);
    assertEquals(0, num2.log().getReal(), EPSILON);
    assertEquals(Math.PI / 2, num2.log().getImaginary(), EPSILON);
    assertEquals(Math.log(Math.sqrt(2)), num3.log().getReal(), EPSILON);
    assertEquals(Math.PI / 4, num3.log().getImaginary(), EPSILON);
    assertEquals(Math.log(Math.sqrt(2)), num4.log().getReal(), EPSILON);
    assertEquals(3 * Math.PI / 4, num4.log().getImaginary(), EPSILON);
    assertEquals(Math.log(Math.sqrt(2)), num5.log().getReal(), EPSILON);
    assertEquals(-3 * Math.PI / 4, num5.log().getImaginary(), EPSILON);
    assertEquals(Math.log(Math.sqrt(2)), num6.log().getReal(), EPSILON);
    assertEquals(-Math.PI / 4, num6.log().getImaginary(), EPSILON);
    assertNull(num7.log());
  }
  
  /**
   * testExp.
   */
  @Test
  public void testExp()
  {
    Complex num1 = new Complex(0, Math.PI );
    Complex num2 = new Complex(1, Math.PI );
    Complex num3 = new Complex(0, -Math.PI / 2 );
    Complex num4 = new Complex(0, Math.PI / 4);
    Complex num5 = new Complex(3, 0);
    assertEquals(-1, num1.exp().getReal(), EPSILON);
    assertEquals(0, num1.exp().getImaginary(), EPSILON);
    assertEquals(-Math.E, num2.exp().getReal(), EPSILON);
    assertEquals(0, num2.exp().getImaginary(), EPSILON);
    assertEquals(0, num3.exp().getReal(), EPSILON);
    assertEquals(-1, num3.exp().getImaginary(), EPSILON);
    assertEquals(Math.sqrt(2) / 2, num4.exp().getReal(), EPSILON);
    assertEquals(Math.sqrt(2) / 2, num4.exp().getImaginary(), EPSILON);
    assertEquals(Math.exp(3), num5.exp().getReal(), EPSILON);
    assertEquals(0, num5.exp().getImaginary(), EPSILON);
    
  }
  
  /**
   * testSqrt.
   */
  @Test
  public void testSqrt()
  {
    Complex num1 = new Complex(0, 0);
    Complex num2 = new Complex(4, 0);
    Complex num3 = new Complex(0, 1);
    Complex num4 = new Complex(-1, 0);
    assertEquals(0, num1.sqrt().getReal(), EPSILON);
    assertEquals(0, num1.sqrt().getImaginary(), EPSILON);
    assertEquals(2, num2.sqrt().getReal(), EPSILON);
    assertEquals(0, num2.sqrt().getImaginary(), EPSILON);
    assertEquals(Math.sqrt(2) / 2, num3.sqrt().getReal(), EPSILON);
    assertEquals(Math.sqrt(2) / 2, num3.sqrt().getImaginary(), EPSILON);
    assertEquals(0, num4.sqrt().getReal(), EPSILON);
    assertEquals(1, num4.sqrt().getImaginary(), EPSILON);
  }
   
  /**
   * testPow.
   */
  @Test
  public void testPow() 
  {
    Complex num1 = new Complex(1, 0);
    Complex num2 = new Complex(0, 0);
    Complex num3 = new Complex(3, 0);
    Complex num4 = new Complex(2, 0);
    Complex num5 = new Complex(Math.E, 0);
    Complex num6 = new Complex(0, Math.PI / 2);
    assertEquals(1, num1.pow(num2).getReal(), EPSILON);
    assertEquals(0, num1.pow(num2).getImaginary(), EPSILON);
    assertEquals(0, num2.pow(num1).getReal(), EPSILON);
    assertEquals(0, num2.pow(num1).getImaginary(), EPSILON);
    assertEquals(9, num3.pow(num4).getReal(), EPSILON);
    assertEquals(0, num3.pow(num4).getImaginary(), EPSILON);
    assertEquals(0, num5.pow(num6).getReal(), EPSILON);
    assertEquals(1, num5.pow(num6).getImaginary(), EPSILON);
  }

  /**
   * testToString.
   */
  @Test
  public void testToString()
  {
    Complex num1 = new Complex(1.5, 3);
    Complex num2 = new Complex(1.5, -3);
    Complex num3 = new Complex(0, 1);
    Complex num4 = new Complex(1, 0);
    assertEquals("1.5+3.0i", num1.toString());
    assertEquals("1.5-3.0i", num2.toString());
    assertEquals("1.0i", num3.toString());
    assertEquals("1.0", num4.toString());
  }
  
  /**
   * testConjugate.
   */
  @Test
  public void testConjugate()
  {
    Complex num = new Complex(1, 2);
    assertEquals(1, num.getReal(), EPSILON);
    assertEquals(-2, num.conjugate().getImaginary(), EPSILON);
  }
  

}
