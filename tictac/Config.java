/*
 * This class should only contain public constants that will be used
 * throughout the program. The purpose of this file is to allow easy changes
 * in the code. Update as nescesary 
 */

package tictac;

/**
 *
 * @author Christopher Thornton
 */
public class Config
{
    // Strings go here
    public static final String 
            VERSION = "0.1", // Version #
            APP_TITLE = "Tic Tac Toe"; // Title of the application



    
    /**
     * **************************************
     * Stuff you really shouldn't mess with unless you know what you're doing...
     *****************/

    // Integers
    public static final int
            NUM_BUTTONS = 9; // # of buttons in the grid. USE WITH CARE!

    // Win Conditions. USE WITH CAUTION!
    // Idea for win conditions found at:
    // http://forum.codecall.net/java-tutorials/2141-java-tutorial-tic-tac-toe.html
    public static final int WIN_CONDITIONS[][] =  new int[][] {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };


}
