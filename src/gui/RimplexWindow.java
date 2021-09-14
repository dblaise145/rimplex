package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.print.DocFlavor.URL;
import javax.swing.*;

/**
 * The main window in the Rimplex application.
 * 
 * @author Prof. David Bernstein, James Madison University
 */
public class RimplexWindow extends JFrame implements KeyListener
{
  // Constants with package visibility
  static final String ONE = "1";
  static final String TWO = "2";
  static final String THREE = "3";
  static final String FOUR = "4";
  static final String FIVE = "5";
  static final String SIX = "6";
  static final String SEVEN = "7";
  static final String EIGHT = "8";
  static final String NINE = "9";
  static final String ZERO = "0";

  static final String PLUS = "+";
  static final String MINUS = "-";
  static final String MULTIPLY = "×";
  static final String DIVIDE = "÷";
  static final String EQUALS = "=";
  static final String OPENPAREN = "(";
  static final String CLOSEPAREN = ")";
  static final String I = "i";
  static final String DECIMAL = ".";
  static final String INVERSE = "Inv";
  static final String EXPONENTIAL = "^";
  static final String LOGARITHM = "log";
  static final String SQUAREROOT = "sqrt";
  static final String CONJUGATE = "z*";
  static final String VISUALIZATION = "V";
  static final String POLAR = "Pol";
  static final String REAL = "Re";
  static final String IMAGINARY = "Im";

  static final String FLIPSIGN = "±";
  static final String BACKSPACE = "←";
  static final String EXIT = "Exit";
  static final String CLEAR = "C";
  static final String RESET = "R";
  static final String FORMAT = "F";
  static final String HISTORY = "H";

  static final String PRINT = "Print";
  static final String QUIT = "Quit";
  
  static final String COLOR = "Change Color Scheme";
  
  static String settingsT = "Settings";
  static String aboutT = "About";
  static String helpT = "Help";
  static String languageT = "Language";
  static String recordT = "Record";
  static String englishT = "English";
  static String spanishT = "Spanish";
  static String frenchT = "French";

  // Constants with private visibility
  private static final long serialVersionUID = 1L;

  // Attributes
  private JButton plusButton, minusButton, multiplyButton, divideButton, equalsButton, clearButton,
      resetButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton,
      sevenButton, eightButton, nineButton, zeroButton, openPButton, closePButton, iButton,
      inverseButton, decimalButton, flipsignButton, backspaceButton, formatButton, logarithmButton,
      exponentialButton, squareRootButton, conjugateButton, visualizationButton, historyButton,
      polarButton, realButton, imaginaryButton;
  private RimplexController controller;
  private JTextArea display, historyDisplay;
  private JPanel historyPanel, visualizationPanel, buttonLayout;
  

  // change for logo
  private final String companyIcon = "logoRimplex.png";

  /**
   * Explicit Value Constructor.
   * 
   * @param controller
   *          The observer for all GUI events.
   */
  public RimplexWindow(final RimplexController controller)
  {
    super();
    this.controller = controller;
    controller.setWindow(this);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    performLayout();
    // setSize(400, 550);
  }

  /**
   * Create a JButton.
   * 
   * @param name
   *          The name of the Image
   * @param actionCommand
   *          The action command
   * @return The JButton
   */
  private JButton createJButton(final String name, final String actionCommand)
  {
    JButton result = new JButton(name);
    Font font = new Font("Arial", Font.PLAIN, 15);
    result.setFont(font);
    result.setActionCommand(actionCommand);
    result.addActionListener(controller);
    return result;
  }

  private JMenuItem createJMenuItem(final String name, final String actionCommand)
  {
    JMenuItem result = new JMenuItem(name);
    result.setActionCommand(actionCommand);
    result.addActionListener(controller);
    return result;
  }

  /**
   * Layout this Component.
   * 
   */
  private void performLayout()
  {
    // set up top half of display
    JMenuBar toolbar = new JMenuBar();
    JMenu settings = new JMenu(settingsT);
    JMenuItem print = createJMenuItem(PRINT, PRINT);
    JMenuItem help = new JMenuItem(helpT);
    JMenuItem about = new JMenuItem(aboutT);
    JMenu language = new JMenu(languageT);
    JMenuItem english = new JMenuItem(englishT);
    JMenuItem spanish = new JMenuItem(spanishT);
    JMenuItem french = new JMenuItem(frenchT);
    JMenuItem quit = createJMenuItem(QUIT, QUIT);
    
    // JMenuItem record = new JMenuItem(recordT);
    JMenuItem record = createJMenuItem(recordT, recordT);
    JMenuItem color = createJMenuItem(COLOR, COLOR);
    toolbar.add(settings);
    //settings.add(help);
    //settings.add(about);
    //settings.add(help);
    //settings.add(about);
    settings.add(record);
    //settings.add(language);
    settings.add(print);
    settings.add(color);
    settings.add(quit);
    //language.add(english);
    //language.add(french);
    //language.add(spanish);

    // set up icon for company logo - can be changed
    JPanel icon = new JPanel();
    java.net.URL iconUrl = this.getClass().getResource(companyIcon);
    ImageIcon im = new ImageIcon(iconUrl);
    icon.add(new JLabel(im));

    // set up calculator display
    display = new JTextArea();
    Font font = new Font("Arial", Font.PLAIN, 15);
    display.setFont(font);
    display.setEditable(false);
    display.addKeyListener(this);
    
    // combine logo and settings tab
    JPanel misc = new JPanel();
    misc.setLayout(new BorderLayout());
    misc.add(toolbar, BorderLayout.NORTH);
    misc.add(icon, BorderLayout.CENTER);
    

    // combine the logo, settings tab, and the display into one jpanel to put on content pane
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new GridLayout(2, 1));
    topPanel.add(misc);
    topPanel.add(display);

    // set up bottom half of display

    // set up layout of buttons
    buttonLayout = createButtonLayout();

    // set up history panel
    historyPanel = new JPanel();
    historyDisplay = new JTextArea();
    historyDisplay.setEditable(false);
    historyPanel.setLayout(new BorderLayout());
    historyPanel.setBorder(BorderFactory.createTitledBorder("History"));
    historyPanel.add(historyDisplay);
    
    // set up visualization panel
    visualizationPanel = new JPanel();
    visualizationPanel.setLayout(new BorderLayout());
    visualizationPanel.setBorder(BorderFactory.createTitledBorder("Visualzation"));
    Dimension visPanelDimension = visualizationPanel.getPreferredSize();
    int visWidth = visPanelDimension.width;
    int visHeight = visPanelDimension.height;
    Dimension visualizationPanelNewSize = new Dimension(visWidth + 200, visHeight + 200);
    visualizationPanel.setPreferredSize(visualizationPanelNewSize);

    JPanel contentPane = (JPanel) getContentPane();

    contentPane.add(topPanel, BorderLayout.NORTH);
    contentPane.add(buttonLayout, BorderLayout.CENTER);
    contentPane.add(historyPanel, BorderLayout.EAST);
    contentPane.add(visualizationPanel, BorderLayout.WEST);
    
    // hide history and visualization panel in the beginning

    // hide history panel in the beginning
    historyPanel.setVisible(false);
    visualizationPanel.setVisible(false);

    // edit frame
    this.pack();
    this.setLocationRelativeTo(null);
  }

  private JPanel createButtonLayout()
  {
    JPanel result = new JPanel();
    result.setLayout(new GridLayout(6, 1));

    JPanel layer1Buttons = new JPanel(new GridLayout(1, 7));
    flipsignButton = createJButton(FLIPSIGN, FLIPSIGN);
    layer1Buttons.add(flipsignButton);
    clearButton = createJButton(CLEAR, CLEAR);
    layer1Buttons.add(clearButton);
    backspaceButton = createJButton(BACKSPACE, BACKSPACE);
    layer1Buttons.add(backspaceButton);
    plusButton = createJButton(PLUS, PLUS);
    layer1Buttons.add(plusButton);
    conjugateButton = createJButton(CONJUGATE, CONJUGATE);
    layer1Buttons.add(conjugateButton);
    resetButton = createJButton(RESET, RESET);
    layer1Buttons.add(resetButton);
    historyButton = createJButton(HISTORY, HISTORY);
    layer1Buttons.add(historyButton);
    result.add(layer1Buttons, BorderLayout.BEFORE_LINE_BEGINS);

    JPanel layer2Buttons = new JPanel(new GridLayout(1, 6));
    oneButton = createJButton(ONE, ONE);
    layer2Buttons.add(oneButton);
    twoButton = createJButton(TWO, TWO);
    layer2Buttons.add(twoButton);
    threeButton = createJButton(THREE, THREE);
    layer2Buttons.add(threeButton);
    minusButton = createJButton(MINUS, MINUS);
    layer2Buttons.add(minusButton);
    inverseButton = createJButton(INVERSE, INVERSE);
    layer2Buttons.add(inverseButton);
    exponentialButton = createJButton(EXPONENTIAL, EXPONENTIAL);
    layer2Buttons.add(exponentialButton);
    result.add(layer2Buttons, BorderLayout.BEFORE_LINE_BEGINS);

    JPanel layer3Buttons = new JPanel(new GridLayout(1, 6));
    fourButton = createJButton(FOUR, FOUR);
    layer3Buttons.add(fourButton);
    fiveButton = createJButton(FIVE, FIVE);
    layer3Buttons.add(fiveButton);
    sixButton = createJButton(SIX, SIX);
    layer3Buttons.add(sixButton);
    multiplyButton = createJButton(MULTIPLY, MULTIPLY);
    layer3Buttons.add(multiplyButton);
    openPButton = createJButton(OPENPAREN, OPENPAREN);
    layer3Buttons.add(openPButton);
    logarithmButton = createJButton(LOGARITHM, LOGARITHM);
    layer3Buttons.add(logarithmButton);
    result.add(layer3Buttons, BorderLayout.BEFORE_LINE_BEGINS);

    JPanel layer4Buttons = new JPanel(new GridLayout(1, 6));
    sevenButton = createJButton(SEVEN, SEVEN);
    layer4Buttons.add(sevenButton);
    eightButton = createJButton(EIGHT, EIGHT);
    layer4Buttons.add(eightButton);
    nineButton = createJButton(NINE, NINE);
    layer4Buttons.add(nineButton);
    divideButton = createJButton(DIVIDE, DIVIDE);
    layer4Buttons.add(divideButton);
    closePButton = createJButton(CLOSEPAREN, CLOSEPAREN);
    layer4Buttons.add(closePButton);
    squareRootButton = createJButton(SQUAREROOT, SQUAREROOT);
    layer4Buttons.add(squareRootButton);
    result.add(layer4Buttons, BorderLayout.BEFORE_LINE_BEGINS);

    JPanel layer5Buttons = new JPanel(new GridLayout(1, 6));
    iButton = createJButton(I, I);
    layer5Buttons.add(iButton);
    zeroButton = createJButton(ZERO, ZERO);
    layer5Buttons.add(zeroButton);
    equalsButton = createJButton(EQUALS, EQUALS);
    layer5Buttons.add(equalsButton);
    decimalButton = createJButton(DECIMAL, DECIMAL);
    layer5Buttons.add(decimalButton);
    formatButton = createJButton(FORMAT, FORMAT);
    layer5Buttons.add(formatButton);
    visualizationButton = createJButton(VISUALIZATION, VISUALIZATION);
    layer5Buttons.add(visualizationButton);
    result.add(layer5Buttons, BorderLayout.AFTER_LINE_ENDS);
    
    JPanel layer6Buttons = new JPanel(new GridLayout(1, 6));
    realButton = createJButton(REAL, REAL);
    layer6Buttons.add(realButton);
    imaginaryButton = createJButton(IMAGINARY, IMAGINARY);
    layer6Buttons.add(imaginaryButton);
    polarButton = createJButton(POLAR, POLAR);
    layer6Buttons.add(polarButton);
    result.add(layer6Buttons);
    
    

    return result;
  }

  // clears the input field
  public void clear()
  {
    display.setText(null);
  }

  // clears input and history
  public void reset()
  {
    display.setText("");
    historyDisplay.setText("");
  }

  public JTextArea getDisplay()
  {
    return display;
  }

  public JPanel getHistoryPanel()
  {
    return this.historyPanel;
  }

  public JTextArea getHistoryDisplay()
  {
    return this.historyDisplay;
  }
  
  public JPanel getVisualizationPanel() {
    return this.visualizationPanel;
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    switch (e.getKeyChar())
    {
      case '1':
        oneButton.doClick();
        break;
      case '2':
        twoButton.doClick();
        break;
      case '3':
        threeButton.doClick();
        break;
      case '4':
        fourButton.doClick();
        break;
      case '5':
        fiveButton.doClick();
        break;
      case '6':
        sixButton.doClick();
        break;
      case '7':
        sevenButton.doClick();
        break;
      case '8':
        eightButton.doClick();
        break;
      case '9':
        nineButton.doClick();
        break;
      case '0':
        zeroButton.doClick();
        break;
      case '.':
        decimalButton.doClick();
        break;
      case '(':
        openPButton.doClick();
        break;
      case ')':
        closePButton.doClick();
        break;
      case 'i':
        iButton.doClick();
        break;
      case '+':
        plusButton.doClick();
        break;
      case '-':
        minusButton.doClick();
        break;
      case '*':
        multiplyButton.doClick();
        break;
      case '/':
        divideButton.doClick();
        break;
      default:
        return;
    }
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_ENTER)
    {
      equalsButton.doClick();
    }
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // TODO Auto-generated method stub

  }
}
