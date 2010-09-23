/*
 * This represents a player. Overall there *should* be two players in the whole
 * game, but it's not going to be enforced in case I decide to modify this
 * in the future.
 *
 * Basically this player class keeps track of the name of a person, and his
 * label. A label is a string that displays on a GameButton, most likely an
 * X or an O.
 */

package tictac.game;

import tictac.gui.GameButton;

/**
 *
 * @author Christopher Thornton
 */
public class Player
{
    // The name and label of the person
    private String name, label;

    /**
     * Creates a new player with the specified name. The default label is "?"
     * @param name the name of the player (i.e. "player 1")
     */
    public Player(String name)
    {
        this(name, "?");
    }

    /**
     * Create a new player with the specified name and label.
     * @param name the name of the player (i.e. "player 1")
     * @param label the label of the player (i.e. "X" or "O")
     */
    public Player(String name, String label)
    {
        this.name = name;
        this.label = label;
    }

    /**
     * Gets the name of the player
     * @return the name of the player
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the label of the player
     * @return the label of the player
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * Gives a textual representation of the player
     * @return the label of the player, followed by the name. For example,
     * "X - Player 1"
     */
    @Override
    public String toString()
    {
        return label + " - " + name;
    }

    /**
     * Safely takes ownership of a button. This makes sure a button isn't
     * "stolen" from another player. This is primarily if an AI is ever created
     * for this game, so while testing, the AI doesn't accidentally take over
     * someone else's button.
     * @param btn the button to take ownership of
     * @throws IllegalArgumentException if the button already has an owner (even
     * if the current player owns it!)
     */
    public void takeButtonOwnership(GameButton btn)
    {
        if(btn.hasOwner())
            throw new IllegalArgumentException(
                    "Cannot take ownership of an owned button!");

        btn.setOwner(this);
    }

    /**
     * Forcefully takes ownership of a button. This method has complete
     * disregard whether the button is already owned or not.
     * @param btn the button to forcefully take ownership of
     */
    public void forceButtonOwnership(GameButton btn)
    {
        btn.setOwner(this);
    }

    
}
