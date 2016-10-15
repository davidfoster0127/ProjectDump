/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package shapes;

import java.awt.geom.Rectangle2D;

/**
 * Cover class for a Rectangle2D.
 * @author David
 * @version Autumn 2015
 */
public class MyRectangle extends Rectangle2D.Double {
    
    /** Auto Generated serial number. */
    private static final long serialVersionUID = 953122216332881567L;

    /**
     * Constructor for MyRectangle.
     * @param theX1 Initial X value
     * @param theY1 Initial Y value
     * @param theX2 Ending X value
     * @param theY2 Ending Y value
     */
    public MyRectangle(final double theX1, final double theY1,
                       final double theX2, final double theY2) {
        super(theX1, theY1, theX2, theY2);
    }

}
