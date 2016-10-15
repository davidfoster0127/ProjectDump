/*
 * TCSS 305 C
 * Assignment 5b - PowerPaint
 */
package tools;

import java.awt.Color;

import shapes.DrawableShape;
import shapes.MyLine;

/**
 * Tool class used to draw lines.
 * @author David
 * @version Autumn 2015
 */
public class LineTool extends AbstractTool implements Tool {

    /**
     * Constructor for the LineTool.
     * @param theStrokeWidth Stroke width for the tool.
     * @param theColor Color for the tool.
     */
    public LineTool(final int theStrokeWidth, final Color theColor) {
        super(theStrokeWidth, theColor);
    }

    @Override
    public DrawableShape createShape(final int theInX, final int theInY, 
                                     final int theEndX, final int theEndY) {
        final MyLine line = new MyLine(theInX, theInY, theEndX, theEndY);
        return new DrawableShape(line, getMyColor(), getMyStrokeWidth());
    }

    @Override
    public char whichTool() {
        return 'l';
    }

    @Override
    public String toString() {
        return "Line";
    }  
}
