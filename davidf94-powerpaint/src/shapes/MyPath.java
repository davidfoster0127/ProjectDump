/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package shapes;

import java.awt.geom.Path2D;
/**
 * Cover class for a Path2D.
 * @author David
 * @version Autumn 2015
 */
public class MyPath extends Path2D.Double {

    /** Auto generated serial number. */
    private static final long serialVersionUID = -8234441977724828783L;
    
    /** Starting X value. */
    private final int myStartX;
    
    /** Starting Y value. */
    private final int myStartY;

    /**
     * Constructor for MyPath.
     * @param theStartX Initial X value
     * @param theStartY Initial Y value
     */
    public MyPath(final int theStartX, final int theStartY) {
        super();
        myStartX = theStartX;
        myStartY = theStartY;
        
    }

    /**
     * @return myStartY
     */
    public int getMyStartY() {
        return myStartY;
    }

    /**
     * @return myStartX
     */
    public int getMyStartX() {
        return myStartX;
    }   
}
