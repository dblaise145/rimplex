package main;

import java.lang.reflect.*;
import javax.swing.*;

import gui.RimplexController;
import gui.RimplexWindow;

/**
 * Rimplex.
 * 
 * @author Team 15
 * @version v3
 */
public class Rimplex implements Runnable
{

  /**
   * Main Method.
   * 
   * @param args
   *          - comand line arguments
   * @throws InterruptedException
   *           exception
   * @throws InvocationTargetException
   *           exception
   */
  public static void main(final String[] args)
      throws InterruptedException, InvocationTargetException
  {
    // Perform all of the setup activities in the event dispatch thread
    SwingUtilities.invokeAndWait(new Rimplex());
  }

  /**
   * run - run Rimplex.
   */
  public void run()
  {
    setLookAndFeel();

    // Setup the application
    RimplexController controller = new RimplexController();
    RimplexWindow window = new RimplexWindow(controller);

    // Start the application
    window.setVisible(true);
  }

  private void setLookAndFeel()
  {
    // Setup the look and feel
    boolean done = false;
    try
    {
      // Use Nimbus if it is available
      UIManager.LookAndFeelInfo[] lfs = UIManager.getInstalledLookAndFeels();
      for (int i = 0; i < lfs.length && !done; i++)
      {
        if ("Nimbus".equals(lfs[i].getName()))
        {
          UIManager.setLookAndFeel(lfs[i].getClassName());
          done = true;
        }
      }

      // If Nimbus isn't available, use the system look and feel
      if (!done)
      {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
      }
    }
    catch (ClassNotFoundException cnfe)
    {
      // Use the default look and feel
    }
    catch (IllegalAccessException iae)
    {
      // Use the default look and feel
    }
    catch (InstantiationException ie)
    {
      // Use the default look and feel
    }
    catch (UnsupportedLookAndFeelException ulale)
    {
      // Use the default look and feel
    }
  }
}
