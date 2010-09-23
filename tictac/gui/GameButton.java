/*
 * This represents a button in the game. These buttons make up the playing grid.
 * A GameButton contains a Player owner. This is essential in calculating the
 * winner and what to display on the 
 */

package tictac.gui;


import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tictac.game.Player;
import tictac.game.TheMatrix;

/**
 *
 * @author Christopher Thornton
 */
public class GameButton extends JButton
{
    // The owner of this button
    private Player owner;

    // To save on memory
    private static Font buttonFont = new Font("SansSerif", Font.PLAIN, 44);

    /**
     * Create a game button, and by default, consider it neutral
     */
    public GameButton()
    {
        // Update the font!
        this.setFont(buttonFont);
        this.setRolloverEnabled(false);
        
        // Now let's set up an action listener!
        this.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                // Meh probably not the *safest* thing to do but w/e
                GameButton btn = (GameButton) e.getSource();
                TheMatrix matrix = TheMatrix.instance();
                
                matrix.getCurrentPlayer().takeButtonOwnership(btn);
                
                matrix.setNextPlayer();

                // Now update the current user
                GameMenu.updateLabel();
                matrix.checkForWinner();
            }
        });
    }


    /**
     * Banishes the current owner. The button will no longer belong to anyone.
     * Note that any references to getOwner() will return null, unless you give
     * the button a new owner.
     */
    public void removeOwner()
    {
        owner = null;
        this.setText("");
        this.setEnabled(true);
    }

    /**
     * Sets this button with the associated owner, regardless of whether the
     * button is already owned.
     * @param player sets the owner of this button
     */
    public void setOwner(Player player)
    {
        owner = player;
        updateButtonDisplay();
    }

    /**
     * Checks to see if this current button is owned by a player
     * @return true if there is an owner
     */
    public boolean hasOwner()
    {
        return (owner != null);
    }

    /**
     * Returns a Player object of the current owner. Note that this might be
     * null if there is no associated owner. Make sure to check out the
     * hasOwner() method before you use this!
     * @see tictac.game.Player
     * @return the current owner
     */
    public Player getOwner()
    {
        return owner;
    }

    /**
     * Updates the current button's display. For example, if someone clicks it,
     * then it will update it to the appropriate value.
     */
    public void updateButtonDisplay()
    {
        // First of all, check to see if someone owns this or not!
        if(hasOwner())
        {
            this.setEnabled(false);

            this.setText(getOwner().getLabel());
        }
        else
        {
            this.setEnabled(true);
            this.setText("");
        }

         
        // Naje sure to update the display!
        this.repaint();
        this.revalidate();       
    }

}
