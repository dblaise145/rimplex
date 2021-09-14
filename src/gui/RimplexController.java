package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static gui.RimplexWindow.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import util.Complex;
import util.Fraction;
import util.Plotting;

/**
 * The observer of all GUI components in the RimplexWindow.
 * 
 * @author team15
 */
public class RimplexController implements ActionListener
{

  private RimplexWindow window;
  private Complex solution = null;
  private ArrayList<String> history;
  public static Color panelColor = Color.white;
  private int filenameCounter;
  private boolean polarC= false;


  /**
   * Default Constructor.
   */
  public RimplexController()
  {
    super();
    history = new ArrayList<String>();
    filenameCounter = 1;
  }

  /**
   * Handle actionPerformed() message (required by ActionListener).
   * 
   * @param ae
   *          The ActionEvent that generated the message
   */
  public void actionPerformed(final ActionEvent ae)
  {
    String ac = ae.getActionCommand();

    if (ac.equals(EXIT))
      System.exit(0);
    else if (ac.equals(ZERO))
      appendOperator(ZERO);
    else if (ac.equals(ONE))
      appendOperator(ONE);
    else if (ac.equals(TWO))
      appendOperator(TWO);
    else if (ac.equals(THREE))
      appendOperator(THREE);
    else if (ac.equals(FOUR))
      appendOperator(FOUR);
    else if (ac.equals(FIVE))
      appendOperator(FIVE);
    else if (ac.equals(SIX))
      appendOperator(SIX);
    else if (ac.equals(SEVEN))
      appendOperator(SEVEN);
    else if (ac.equals(EIGHT))
      appendOperator(EIGHT);
    else if (ac.equals(NINE))
      appendOperator(NINE);
    else if (ac.equals(OPENPAREN))
      appendOperator(OPENPAREN);
    else if (ac.equals(CLOSEPAREN))
      appendOperator(CLOSEPAREN);
    else if (ac.equals(DECIMAL))
      appendOperator(DECIMAL);
    else if (ac.equals(PLUS))
    {
      replaceWithSolution();
      appendOperator(PLUS);
    }
    else if (ac.equals(MINUS))
    {
      replaceWithSolution();
      appendOperator(MINUS);
    }
    else if (ac.equals(MULTIPLY))
    {
      replaceWithSolution();
      appendOperator(MULTIPLY);
    }
    else if (ac.equals(DIVIDE))
    {
      replaceWithSolution();
      appendOperator(DIVIDE);
    }
    else if (ac.equals(INVERSE))
    {
      replaceWithSolution();
      preppendOperator(INVERSE);
    }
    else if (ac.equals(EXPONENTIAL))
    {
      replaceWithSolution();
      appendOperator(EXPONENTIAL);
    }
    else if (ac.equals(LOGARITHM))
    {
      replaceWithSolution();
      preppendOperator(LOGARITHM);
    }      
    else if (ac.equals(SQUAREROOT))
    {
      replaceWithSolution();
      preppendOperator(SQUAREROOT);
    }
    else if (ac.equals(CONJUGATE))
    {
      replaceWithSolution();
      appendOperator("*");
    }
    else if (ac.equals(REAL))
    {
      replaceWithSolution();
      preppendOperator(REAL);
    }
    else if (ac.equals(IMAGINARY))
    {
      replaceWithSolution();
      preppendOperator(IMAGINARY);
    }
    else if (ac.equals(POLAR))
      polar();
    else if (ac.equals(I))
      appendOperator(I);
    else if (ac.equals(EQUALS))
      compute();
    else if (ac.equals(CLEAR))
      window.clear();
    else if (ac.equals(BACKSPACE))
      backspace();
    else if (ac.equals(RESET))
      reset();
    else if (ac.equals(FORMAT))
      format();
    else if (ac.equals(POLAR))
      polar();
    else if (ac.equals(FLIPSIGN))
      flipsign();
    else if (ac.equals(VISUALIZATION))
      try {
        graph();
      } catch (StringIndexOutOfBoundsException e) {
        window.getVisualizationPanel().setVisible(false);
      }
    else if (ac.equals(HISTORY))
      setHistory();
    else if (ac.equals(PRINT))
      print();
    else if (ac.equals(COLOR)) {
      changeColor();
    }
    else if (ac.equals("Help"))
      help();
    else if (ac.equals("About"))
      about();
    else if (ac.equals("English"))
      language(0);
    else if (ac.equals("Spanish"))
      language(1);
    else if (ac.equals("French"))
      language(2);
    else if (ac.equals(recordT))
      record();
    else if (ac.equals(QUIT))
      System.exit(0);
  }
  
  private void replaceWithSolution() {
    if (window.getDisplay().getText().contains("=") && solution != null)
      window.getDisplay().setText("(" + solution.toString() + ")");
  }

  private void changeColor()
  {
    panelColor = JColorChooser.showDialog(null, "Choose the Color Scheme!", panelColor);
    setPanelColor(window);
    window.getVisualizationPanel().repaint();
  }
  
  private static void setPanelColor(Container parent)
  {
    for(Component child : parent.getComponents())
    {
      if(child instanceof Container)
      {
        if(child instanceof JPanel)
        {
          child.setBackground(panelColor);
        }
        setPanelColor((Container) child);
      }
    }
  }

  private void polar()
  {
    polarC = !polarC;
    String equation = window.getDisplay().getText().substring(0,
        window.getDisplay().getText().lastIndexOf("=") + 1);
    if (polarC)
      equation += Math.round(solution.mag() * 1e4) / 1e4 + "e^(" + Math.round(solution.arg() * 1e4) / 1e4 + "i)";
    else
      equation += "(" + solution.toString() + ")";
    window.getDisplay().setText(equation);
  }

  private void record()
  {
    // TODO Auto-generated method stub
	try 
	{
	  if (!history.isEmpty()) 
	  {
		FileWriter historyWriter = new FileWriter("Record_" + filenameCounter + ".txt");
		for (String str : history) 
		historyWriter.write(str + "\n");
		historyWriter.close();
		filenameCounter++;
	  }
	} catch (IOException e)
	{
	  // Display error box??
	}
  }

  private void language(int i)
  {
    // TODO Auto-generated method stub
    
  }

  private void about()
  {
    // TODO Auto-generated method stub
    
  }

  private void help()
  {
    // TODO Auto-generated method stub
    
  }

  private void setHistory() {
	boolean visible = window.getHistoryPanel().isVisible();
	if (!window.getHistoryDisplay().getText().equals(""))
	{
	  window.getHistoryPanel().setVisible(!visible);
	}
	window.pack();
}

private void flipsign()
  {
    int result = -1;
    Pattern p = Pattern.compile("[0-9]+\\.?[0-9]?i?$");
    Matcher m = p.matcher(window.getDisplay().getText());
    if (m.find())
    {
      result = window.getDisplay().getText().indexOf(m.group());
    }
    if (result > 0)
    {
      if (window.getDisplay().getText().charAt(result - 1) == '-')
      {
        if (Character.isDigit(window.getDisplay().getText().charAt(result - 2)))
        {
          window.getDisplay().insert(MINUS, result);
          return;
        }
        window.getDisplay().replaceRange("", result - 1, result);
      }
      else
      {
        window.getDisplay().insert(MINUS, result);
      }
    }
  }

  private void reset()
  {
    solution = null;
    window.reset();
    graph();
  }

  private void backspace()
  {
    if (window.getDisplay().getText().length() > 0)
    {
      String text = window.getDisplay().getText();
      window.getDisplay().setText(text.substring(0, text.length() - 1));
    }
  }

  private void compute()
  {
    String equation = window.getDisplay().getText();
    ArrayList<Complex> operands = operands(equation);
    String operator = operator(equation);
    switch (operator)
    {
      case PLUS:
        solution = operands.get(0).add(operands.get(1));
        break;
      case MINUS:
        solution = operands.get(0).subtract(operands.get(1));
        break;
      case MULTIPLY:
        solution = operands.get(0).multiply(operands.get(1));
        break;
      case DIVIDE:
        solution = operands.get(0).divide(operands.get(1));
        break;
      case INVERSE:
        solution = operands.get(0).inverse();
        break;
      case EXPONENTIAL:
        solution = operands.get(0).pow(operands.get(1));
        break;
      case LOGARITHM:
        solution = operands.get(0).log();
        break;
      case SQUAREROOT:
        solution = operands.get(0).sqrt();
        break;
      case CONJUGATE:
        solution = operands.get(0).conjugate();
        break;
      case REAL:
        solution = new Complex(operands.get(0).getReal(), 0);
        break;
      case IMAGINARY:
        solution = new Complex(0, operands.get(0).getImaginary());
        break;
      default:
        break;
    }
    if (solution != null)
    {
    if (polarC)
      window.getDisplay().setText(equation + "=" + Math.round(solution.mag() * 1e4) / 1e4 + "e^(" + Math.round(solution.arg() * 1e4) / 1e4 + "i)");   
    else
      window.getDisplay().setText(equation + " = " + "(" + solution.toString() + ")");
    history.add(window.getDisplay().getText());
    window.getHistoryDisplay().append(window.getDisplay().getText() + "\n");
    } else
      window.getDisplay().setText(equation + " = " + "error");
  }

  private void appendOperator(final String op)
  {
    window.getDisplay().append(op);

  }

  private void preppendOperator(final String op)
  {
    window.getDisplay().setText(op + window.getDisplay().getText());
  }

  public Complex toComplex(String expression)
  {
    String[] complexArr;
    String real;
    String imaginary;
    if (expression.contains(PLUS))
    {
      complexArr = expression.split("\\+");
    }
    else if (expression.contains(MULTIPLY))
    {
      complexArr = expression.split(MULTIPLY);
    }
    else if (expression.contains(DIVIDE))
    {
      complexArr = expression.split(DIVIDE);
    }
    else
    {
      complexArr = expression.split(MINUS);
      if (complexArr.length == 3)
      {
        complexArr[0] = "-" + complexArr[1];
        complexArr[1] = "-" + complexArr[2];
      }
      else
      {
        complexArr[1] = "-" + complexArr[1];
      }
    }
    if (complexArr[0].contains("i"))
    {
      imaginary = complexArr[0];
      imaginary = imaginary.replaceAll("i", "");
      real = complexArr[1];
    }
    else
    {
      imaginary = complexArr[1];
      imaginary = imaginary.replaceAll("i", "");
      real = complexArr[0];
    }
    return new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));
  }

  /**
   * Set the RimplexWindow that this object is controlling.
   * 
   * @param window
   *          The window.
   */
  public void setWindow(final RimplexWindow window)
  {
    this.window = window;
  }

  public void format()
  {
    if (solution != null)
    {
      Fraction real = new Fraction(solution.getReal());
      Fraction imaginary = new Fraction(solution.getImaginary());

      String equation = window.getDisplay().getText().substring(0,
          window.getDisplay().getText().lastIndexOf("=") + 1);

      if (!window.getDisplay().getText()
          .substring(window.getDisplay().getText().lastIndexOf("=") + 1,
              window.getDisplay().getText().length())
          .contains("/"))
      {
        if (window.getDisplay().getText()
            .substring(window.getDisplay().getText().lastIndexOf("=") + 1,
                window.getDisplay().getText().length())
            .contains("+"))
        {
          window.getDisplay()
              .setText(equation + " (" + real.toString() + "+" + imaginary.toString() + I + ")");
        }
        else
        {
          window.getDisplay()
              .setText(equation + " (" + real.toString() + imaginary.toString() + I + ")");
        }
      }
      else
      {
        window.getDisplay().setText(equation + " (" + solution.toString() + ")");
      }
    }
  }

  private ArrayList<Complex> operands(final String text)
  {
    ArrayList<Complex> out = new ArrayList<>();
    String str = new String(text);
    while (str.contains("(") && str.contains(")") && (str.indexOf(")") - str.indexOf("(") > 1))
    {
      out.add(parseComplex(str.substring(str.indexOf("(") + 1, str.indexOf(")"))));
      str = str.substring(0, text.indexOf("(")) + str.substring(str.indexOf(")"), str.length());
      str = str.substring(1);
    }
    return out;
  }

  private String removeInClosedParen(final String str)
  {
    return str.replaceAll("\\([^()]*\\)", "");
  }

  private String operator(final String str)
  {
    String out = null;
    String text = removeInClosedParen(str);

    if (text.contains(INVERSE))
      out = INVERSE;
    else if (text.contains(EXPONENTIAL))
      out = EXPONENTIAL;
    else if (text.contains(LOGARITHM))
      out = LOGARITHM;
    else if (text.contains(SQUAREROOT))
      out = SQUAREROOT;
    else if (text.contains("*"))
      out = CONJUGATE;
    else if (text.contains(PLUS))
      out = PLUS;
    else if (text.contains(MINUS))
      out = MINUS;
    else if (text.contains(MULTIPLY))
      out = MULTIPLY;
    else if (text.contains(DIVIDE))
      out = DIVIDE;
    else if (text.contains(REAL))
      out = REAL;
    else if (text.contains(IMAGINARY))
      out = IMAGINARY;
    return out;
  }

  private Complex parseComplex(final String str)
  {
    String text = new String(str);
    int signMult = 1;
    double real = 0;
    double imaginary = 0;
    if (str.charAt(0) == '+' || str.charAt(0) == '-')
    {
      text = text.substring(1);
      if (str.charAt(0) == '-')
      {
        signMult = -1;
      }
    }
    if (text.contains("+"))
    {
      try
      {
        real = signMult * Double.parseDouble(text.substring(0, text.indexOf("+")));
        imaginary = Double.parseDouble(text.substring(text.indexOf("+") + 1, text.length() - 1));
      }
      catch (Error e)
      {
      }

    }
    else if (text.contains("-") && text.indexOf("-") > 0)

    {
      try
      {
        real = signMult * Double.parseDouble(text.substring(0, text.indexOf("-")));
        imaginary = Double.parseDouble(text.substring(text.indexOf("-"), text.length() - 1));
      }
      catch (Error e)
      {
      }

    }
    else if (text.contains("i"))
    {
      try
      {
        imaginary = signMult * Double.parseDouble(text.substring(0, text.length() - 1));
      }
      catch (Error e)
      {
      }

    }
    else
    {
      try
      {
        real = signMult * Double.parseDouble(text.substring(0, text.length()));
      }
      catch (Error e)
      {
      }
    }
    return new Complex(real, imaginary);
  }

  private void graph() throws StringIndexOutOfBoundsException
  {
    String complex = window.getDisplay().getText();
    complex = complex.replace(')', ' ');
    complex = complex.replace('(', ' ');
    complex = complex.replaceAll(" ", "");
    Complex number;
    if (solution != null)
    {
      number = solution;
    }
    else
    {
      number = parseComplex(complex);
    }
    Plotting plot = new Plotting();
    plot.setRealNumber(number.getReal());
    plot.setImagineNumber(number.getImaginary());
    
    // Set up new window format/put it back
    Dimension visPanelDimension = window.getVisualizationPanel().getPreferredSize();
    int visWidth = visPanelDimension.width;
    int visHeight = visPanelDimension.width;
    if (!window.getVisualizationPanel().isVisible()) {
      window.getVisualizationPanel().add(plot);
      plot.setWidthAndHeight(visWidth, visHeight);
      window.getVisualizationPanel().setVisible(true);
    } else {
      window.getVisualizationPanel().setVisible(false);
      window.getVisualizationPanel().removeAll();
      window.getVisualizationPanel().add(plot);
    }
    plot.repaint();
    window.getVisualizationPanel().repaint();
    window.pack();
  }
  
  private void print() {
    try {
      window.getHistoryDisplay().print();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
