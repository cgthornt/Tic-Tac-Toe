/*
 * Welcome to the matrix! This class basically is the underlying system that
 * manages the game, users, etc. If you want to change the program in some
 * significant manner, this is the place!
 *
 * Some important functions of this file:
 * - Keeps track of the current player and manages players
 * - Computes whether someone is a winner or not
 * - Resets the game
 */

package tictac.game;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import tictac.gui.GameButton;
import tictac.gui.GamePanel;


/**
 *
 * @author Christopher Thornton
 */
public class TheMatrix
{
    // We only want a single instance of this!
    private static TheMatrix instance;

    // We want an array list of players. *Normally* there's only two players,
    // but I'm keeping this open so that this game can be modified in the
    // future, if need be.
    private ArrayList<Player> players = new ArrayList<Player>();

    // The person who's turn it currently is
    private Player currentPlayer;

    // The number of moves that have been made in this game.
    private int numMoves = 0;

    /**
     * This is where the fun begins. Basically these contain the "hard-coded"
     * settings for the game. If you want to modify the game in some special
     * way, this is the place to do it.
     */
    private TheMatrix()
    {
        players.add(0, new Player("Player 1", "X"));
        players.add(1, new Player("Player 2", "O"));
        currentPlayer = players.get(0);
    }

    /**
     * Basically we only want one instance of the matrix and it's kind of a pain
     * making all static methods. So... yeah
     * @return the singleton instance of this class
     */
    public static TheMatrix instance()
    {
        if(instance == null)
            instance = new TheMatrix();
        return instance;
    }

    /**
     * Gets all of the current players. Probably won't be used that much.
     * @return an array list of players
     */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    /**
     * Sets the current player to the next person in the que of players. If the
     * current player is the last one in the list, then we set the current
     * player to the first one in the list.
     */
    public void setNextPlayer()
    {
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).equals(getCurrentPlayer()))
            {
                // If the current player is the last one, then start over to
                // zero. If not, then simply use the next one.
                setCurrentPlayer(
                    getPlayer(
                        i + 1 >= players.size() ? 0 : i+1
                    ));

                break;
            }
        }
    }


    /**
     * Returns a specific player. Note that you might get an exception if the
     * player doesn't actually exist
     * @param player the player number to get
     * @return the specific player
     */
    public Player getPlayer(int player)
    {
        return players.get(player);
    }


    /**
     * Sets the current player
     * @param player sets the current player
     */
    public void setCurrentPlayer(Player player)
    {
        currentPlayer = player;
    }

    /**
     * Sets the current player, given the index.
     * @param index the index of the player
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void setCurrentPlayer(int index)
    {
        setCurrentPlayer(getPlayer(index));
    }


    /**
     * Gets the current player
     * @return the current player
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     * Resets the game. Use with caution.
     */
    public void resetGame()
    {
        // Removes all owners from the buttons
        for(GameButton btn : GamePanel.getButtons())
            btn.removeOwner();
            
        setCurrentPlayer(getPlayer(0));
        tictac.gui.GameMenu.updateLabel();
        numMoves = 0;
    }

    /**
     * Every time this is called, TheMatrix checks to see if there is a winning
     * combo. If not, then increment the moves. This *might* trigger a tie
     * scenario via the incrementMoves() method.
     */
    public void checkForWinner()
    {

        Player winner = computeWinner();

        // Someone has won!
        if(winner != null)
        {
            JOptionPane.showMessageDialog(null, winner.toString() + " wins!",
                    "Victory!", JOptionPane.INFORMATION_MESSAGE);

            resetGame();
        }

        // If there isn't a winner, simply increment the moves.
        else
        {
            // Note: This might trigger a tie scenario.
            incrementMoves();
        }
    }


    /**
     * Computes to see if there is a game winner! Helper method for the
     * handleWinner() method.
     * @return the winner of the game. NULL if there is no winner!
     */
    protected Player computeWinner()
    {
        // Too lazy to import the package :)
        ArrayList<GameButton> btns = GamePanel.getButtons();

        // Load up the win conditions from the config file!
        int winConditions[][] = tictac.Config.WIN_CONDITIONS;

        // If this reaches three, then we have a winner!
        int numCount = 0;

        // Let's loop through each player!
        for(Player p : getPlayers())
        {
            // Now we need to check out all possible win conditions...
            for(int i = 0; i < winConditions.length; i++)
            {
                // Now loop through each button!
                for(int j = 0; j < winConditions[i].length; j++)
                {
                    if(btns.get(winConditions[i][j]).getOwner() == p)
                       numCount++;
                    
                    // Winner winner! Return the winning player!
                    if(numCount == 3)
                    {
                       numMoves = 0;
                       return p;
                    }
                }

                // If we're here, then we should reset the count
                numCount = 0;
            }
        }

        // Now if we're here, then there's nothing
        return null;
    }

    /**
     * Whenever someone clicks a game button, this should be called. This
     * internally calculates how many moves have been made.
     */
    public void incrementMoves()
    {
        numMoves++;

        // For some reason, this is recursively added twice, so let's
        // take care of it like this and make it a todo item!
        // @todo: make this non-hackish!
        if(numMoves >= 9)
        {
            javax.swing.JOptionPane.showMessageDialog(
                    null, "Sorry, nobody has won!",
                    "Failiure!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            this.resetGame();
        }   
    }
}
