/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package gui;

import java.awt.EventQueue;

/**
 * Class containing the main method for PowerPaint.
 * @author David
 * @version Autumn 2015
 */
public final class PowerPaintMain {


    /** Unimplemented constructor for a PowerPointMain. */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the DrawingPanel. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI().start();
            }
        });
    }

}


