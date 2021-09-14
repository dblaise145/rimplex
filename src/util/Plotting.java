package util;

import java.awt.*;

import javax.swing.*;
import gui.RimplexController;

/**
 * Plotting.
 * 
 * @author Team15
 * @version v3
 */
public class Plotting extends JPanel
{
  private static final long serialVersionUID = 1L;
  private static final int PAD = 20;
  private final int ovalSize = 4;
  private double re;
  private double im;
  private int width = 0;
  private int height = 0;
  private Color color = RimplexController.panelColor;

  /**
   * paintComonent - graph.
   * @param g graphics
   */
  public void paintComponent(final Graphics g)
  {

    // Set up drawing tool.
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int rgb = 0;
    if (color.getRed() > 75)
      rgb++;
    if (color.getGreen() > 75)
      rgb++;
    if (color.getBlue() > 75)
      rgb++;

    // Variables.
    double xScale = (width - 2 * PAD) / 15;
    double yScale = (height - 2 * PAD) / 15;

    // The origin location.
    int yAxis = (height - PAD) / 2;
    int xAxis = (width - PAD) / 2;

    // Draw background
    g2.setPaint(color);
    g2.fillRect(0, 0, width, height);
    // Drawing axes.
    if (rgb <= 2)
    {
      g2.setPaint(Color.white);
    }
    else
    {
      g2.setPaint(Color.BLACK);
    }
    g2.drawLine(xAxis, PAD, xAxis, height - PAD); // y-axis line
    g2.drawLine(PAD, yAxis, width - PAD, yAxis); // x-axis line

    // Graphing.
    int x = xAxis + (int) (xScale * re);
    int y = yAxis - (int) (yScale * im);
    g2.fillOval(x, y, ovalSize, ovalSize);
    rgb = 0;
    if (color.getRed() > 200)
      rgb++;
    if (color.getGreen() > 200)
      rgb++;
    if (color.getBlue() > 200)
      rgb++;
    if (rgb != 0)
    {
      g2.setPaint(Color.blue);
    }
    else
    {
      g2.setPaint(Color.red);
    }
    g2.drawLine(xAxis, yAxis, x, y);
  }

  /**
   * setRealNumber.
   * @param real - real
   */
  public void setRealNumber(final double real)
  {
    this.re = real;
  }

  /**
   * setImagineNumber.
   * @param imag - imaginary
   */
  public void setImagineNumber(final double imag)
  {
    this.im = imag;
  }

  /**
   * setWidthAndHeight.
   * @param w - width
   * @param h - height
   */
  public void setWidthAndHeight(final int w, final int h)
  {
    this.width = w;
    this.height = h;
  }

  /**
   * getWidth.
   * @return width
   */
  @Override
  public int getWidth()
  {
    return width;
  }

  /**
   * getHeight.
   * @return height
   */
  @Override
  public int getHeight()
  {
    return height;
  }

  /**
   * getImaginary.
   * @return imaginary
   */
  public double getImaginary()
  {
    return im;
  }

  /**
   * getReal.
   * @return real
   */
  public double getReal()
  {
    return re;
  }
}
