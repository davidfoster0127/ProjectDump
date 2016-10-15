/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package tools;

import java.awt.Color;

import shapes.DrawableShape;

/**
 * An abstract class for all the different implementation of the tool.
 * @author David
 * @version Autumn 2015
 */
public abstract class AbstractTool implements Tool {

    /** Stroke width of the tool. */
    private int myStrokeWidth = 1;
    
    /** Color of the tool. */
    private Color myColor = Color.BLACK;
    
    /**
     * Constructor for an AbstractTool.
     * @param theStrokeWidth Stroke width to set the tool to.
     * @param theColor Color to set the tool to.
     */
    public AbstractTool(final int theStrokeWidth, final Color theColor) {
        setMyStrokeWidth(theStrokeWidth);
        setMyColor(theColor);
    }

    @Override
    public abstract char whichTool();

    @Override
    public abstract DrawableShape createShape(int theInX, int theInY, 
                                              int theEndX, int theEndY);
    
    @Override
    public abstract String toString();
    
    /**
     * @return myColor
     */
    public Color getMyColor() {
        return myColor;
    }

    /**
     * @param theColor the Color to set tool to.
     */
    public void setMyColor(final Color theColor) {
        myColor = theColor;
    }

    /**
     * @return myStrokeWidth
     */
    public int getMyStrokeWidth() {
        return myStrokeWidth;
    }

    /**
     * @param theStrokeWidth the Stroke Width to set tool to.
     */
    public void setMyStrokeWidth(final int theStrokeWidth) {
        myStrokeWidth = theStrokeWidth;
    }
    
}
