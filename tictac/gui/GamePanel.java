/*
 * This is the main panel where all of the fun happens. This contains an
 * array list of game buttons.
 */

package tictac.gui;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;


/**
 *
 * @author Christopher Thornton
 */
public class GamePanel extends JPanel
{
    // Meh a regular multidimensional array is easier to sort through
    private static ArrayList<GameButton> buttons = new ArrayList<GameButton>();

    public GamePanel()
    {
        super(new GridLayout(3, 3));

        this.setPreferredSize(new java.awt.Dimension(500, 500));


        // Add the buttons!
        for(int i = 0; i < tictac.Config.NUM_BUTTONS; i++)
        {
            buttons.add(i, new GameButton());
            this.add(buttons.get(i));
        }
        
    }

    public static ArrayList<GameButton> getButtons()
    {
        return buttons;
    }

}
