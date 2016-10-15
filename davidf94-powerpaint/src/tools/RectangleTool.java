/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package tools;

import java.awt.Color;

import shapes.DrawableShape;
import shapes.MyRectangle;

/**
 * Tool class used to draw rectangles.
 * @author David
 * @version Autumn 2015
 */
public class RectangleTool extends AbstractTool implements Tool {

    /**
     * Constructor for a RectangleTool.
     * @param theStrokeWidth Stroke width for the tool.
     * @param theColor Color for the tool.
     */
    public RectangleTool(final int theStrokeWidth, final Color theColor) {
        super(theStrokeWidth, theColor);
    }

    @Override
    public DrawableShape createShape(final int theInX, final int theInY, 
                                     final int theEndX, final int theEndY) {
        
        int startX = theInX;
        int startY = theInY;
        int width = theEndX - theInX;
        int height = theEndY - theInY;
        //handles situation where end point is negative of the start
        if (startX > theEndX) {
            width = startX - theEndX;
            startX = theEndX;
        }
        if (startY > theEndY) {
            height = startY - theEndY;
            startY = theEndY;
        }   
        final MyRectangle rectangle = new MyRectangle(startX, startY, 
                                                      width, height);
        return new DrawableShape(rectangle, getMyColor(), getMyStrokeWidth());
    }

    @Override
    public char whichTool() {
        return 'r';
    }

    @Override
    public String toString() {
        return "Rectangle";
    }

}
