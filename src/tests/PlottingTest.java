/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import util.Plotting;

/**
 * @author Wesley
 *
 */
class PlottingTest
{

  /**
   * Test method for {@link util.Plotting#paintComponent(java.awt.Graphics)}.
   */
  @Test
  void testPaintComponentGraphics()
  {
    double real = 7.5;
    double imaginary = 4.2;
    int width = 300;
    int height = 600;
    Plotting plot = new Plotting();
    plot.setWidthAndHeight(width, height);
    plot.setRealNumber(real);
    plot.setImagineNumber(imaginary);
    assertEquals(7.5, plot.getReal(), "tests real setter");
    assertEquals(4.2, plot.getImaginary(), "tests imaginary setter");
    assertEquals(300, plot.getWidth(), "tests width setter");
    assertEquals(600, plot.getHeight(), "tests height setter");
  }

}
