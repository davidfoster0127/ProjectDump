/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package shapes;

import java.awt.geom.Ellipse2D;
/**
 * Cover class for an Ellipse2D.
 * @author David
 * @version Autumn 2015
 */
public class MyEllipse extends Ellipse2D.Double {

    /** Auto generated serial number. */
    private static final long serialVersionUID = 6478789089898175993L;

    /**
     * Constructor for MyEllipse.
     * @param theX1 Initial X value
     * @param theY1 Initial Y value
     * @param theX2 Ending X value
     * @param theY2 Ending Y value
     */
    public MyEllipse(final double theX1, final double theY1,
                     final double theX2, final double theY2) {
        super(theX1, theY1, theX2, theY2);
    }

}
