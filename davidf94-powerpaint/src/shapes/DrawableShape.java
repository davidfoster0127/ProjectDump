/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package shapes;

import java.awt.Color;
import java.awt.Shape;

/**
 * A class that stores a shape, its color, and its stroke width.
 * @author David
 * @version Autumn 2015
 */
public class DrawableShape {
    
    /** The shape of this object. */
    private final Shape myShape;
    
    /** The color of this object. */
    private final Color myColor;
    
    /** The stroke width of the this object. */
    private final int myStrokeWidth;
    
    /**
     * Constructor for a DrawableShape.
     * @param theShape shape to be stored.
     * @param theColor color to be stored.
     * @param theStrokeWidth stroke width to be stored.
     */
    public DrawableShape(final Shape theShape, final Color theColor,
                         final int theStrokeWidth) {
        myShape = theShape;
        myColor = theColor;
        myStrokeWidth = theStrokeWidth;
    }

    /**
     * @return myShape
     */
    public Shape getMyShape() {
        return myShape;
    }

    /**
     * @return myColor
     */
    public Color getMyColor() {
        return myColor;
    }

    /**
     * @return myStrokeWidth
     */
    public int getMyStrokeWidth() {
        return myStrokeWidth;
    }
    
}
