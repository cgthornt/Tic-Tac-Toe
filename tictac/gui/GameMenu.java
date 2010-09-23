/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tictac.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import tictac.game.*;

/**
 *
 * @author Christopher Thornton
 */
public class GameMenu extends JPanel
{
    private static JLabel label;
    public GameMenu()
    {
        super(new GridLayout(1,1));

        // Create a new label to show who's turn it is
        label = new JLabel("");
        updateLabel();
        this.add(label);

        // Now a label with credits and version
        JLabel versionInfo = new JLabel(
                "Created by Christopher Thornton | Version "
              + tictac.Config.VERSION + "   ",
                JLabel.RIGHT
                );

        Font versionFont = new Font("SansSerif", Font.ITALIC, 11);

        versionInfo.setFont(versionFont);
        this.add(versionInfo);

    }

    /**
     * A static method to update the current label
     */
    public static void updateLabel()
    {
        label.setText(TheMatrix.instance().getCurrentPlayer().toString());
        
    }

}
