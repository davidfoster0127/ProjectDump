package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.AbstractPiece;
import model.Board;
import model.Piece;

/**
 * The panel that displays the next piece to play.
 * @author David
 * @version Autumn 2015
 */
public class NextPiecePanel extends JPanel {
    
    /**A generated serial ID number. */
    private static final long serialVersionUID = -124720344938729754L;

    /** The scale of the Panel to the base board. */
    private static final int SCALE = 30;
    
    /** The number of blocks in each piece to be drawn. */
    private static final int BLOCKS = 4;
    
    /** Game board that stores all pieces. */
    private final Board myBoard;
    
    /**
     * Constructor for the NextPiecePanel.
     * @param theBoard the game board that stores the shapes being drawn.
     */
    public NextPiecePanel(final Board theBoard) {
        super(true);
        myBoard = theBoard;
        setPreferredSize(new Dimension(BLOCKS * SCALE, BLOCKS * SCALE));
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        final Piece nextPiece = myBoard.getNextPiece();
        g2d.setPaint(Color.WHITE);
        for (int i = 0; i < BLOCKS; i++) {
            g2d.fillRect(((AbstractPiece) nextPiece).getRotation()[i][0] * SCALE, 
                         (BLOCKS - ((AbstractPiece) nextPiece).getRotation()[i][1] - 1) 
                         * SCALE,
                         SCALE, SCALE);                 
        }     
    }
}
