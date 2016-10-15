package view;

import java.awt.EventQueue;

/**
 * 
 * @author David
 * @version Autumn 2015
 */
public final class TetrisMain {

    /** Unimplemented constructor for a PowerPointMain. */
    private TetrisMain() {
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
                new TetrisGUI().start();
            }
        });
    }

}
