/*
 * This is the overall frame for the tictac application. Isn't she beautiful?
 * Pretty self explanatory
 */

package tictac.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;

/**
 *
 * @author Christopher Thornton
 */
public class GameFrame extends JFrame
{
    /**
     * Creates a new frame and adds all of the panels and whatnot
     */
    public GameFrame()
    {
        // Sets the title of the window
        super(tictac.Config.APP_TITLE);

        // Layout settings
        this.setLayout(new BorderLayout(4, 4));

        // Frame preferences
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(500, 520));

        // add the panels!
        this.getContentPane().add(new GameMenu(), BorderLayout.PAGE_START);
        this.getContentPane().add(new GamePanel());

        // Pack it!
        this.pack();
    }
}
