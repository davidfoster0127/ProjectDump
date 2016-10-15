/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package tools;

import java.awt.Color;

import shapes.DrawableShape;

/**
 * Tool interface for defining tools for each shape.
 * @author David
 * @version Autumn 2015
 */
public interface Tool {
    
    /**
     * Method that creates the ship with the given initial and ending x/y values.
     * @param theInX The initial X value
     * @param theInY The initial Y value
     * @param theEndX The ending X value
     * @param theEndY The ending Y value
     * @return DrawableShape with shape, color, and stroke width of the shape created 
     */
    DrawableShape createShape(int theInX, int theInY, int theEndX, int theEndY);

    /**
     * Method that returns the char representation of the tool.
     * @return char that defines the tool type
     */
    char whichTool();
    
    /**
     * @return myColor
     */
    Color getMyColor();

    /**
     * @param theColor the Color to set in the tool
     */
    void setMyColor(final Color theColor);

    /**
     * @return myStrokeWidth
     */
    int getMyStrokeWidth();

    /**
     * @param theStrokeWidth the stroke width to set in the tool
     */
    void setMyStrokeWidth(final int theStrokeWidth);
}
