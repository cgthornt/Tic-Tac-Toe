/*
 * The main class! It's pretty straight forward.
 */

package tictac;

import tictac.gui.GameFrame;

/**
 *
 * @author Christopher Thornton
 */
public class Main
{

    /**
     * This is a pre-init method that is run before the frame is even rendered.
     */
    public static void preInit()
    {

    }


    /**
     * The main entry point!
     * @param args not used
     */
    public static void main(String[] args) 
    {
        // Do stuff before the frame displays.
        preInit();

        // Let's run it!
        new GameFrame().setVisible(true);

    }

}
