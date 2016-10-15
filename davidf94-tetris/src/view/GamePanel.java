package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import model.AbstractPiece;
import model.Block;
import model.Board;
import model.Piece;
 
/**
 * The panel where pieces in play and frozen ones are drawn.
 * @author David
 * @version Autumn 2015
 */
public class GamePanel extends JPanel {

    /** A generated serial ID number. */
    private static final long serialVersionUID = 1860979716621182121L;
    
    /** The default width for the game board. */
    private static final int BOARD_WIDTH = 10;
    
    /** The default height for the game board. */
    private static final int BOARD_HEIGHT = 20;
    
    /** The scale of the Panel to the base board. */
    private static final int SCALE = 30;
    
    /** Number of blocks stored in each piece to be drawn. */
    private static final int BLOCKS = 4;
    
    /** Font size for Game Over message. */
    private static final int FONT_SIZE = 49;
    
    /** Default font for notifications. */
    private static final String FONT = "Ariel";
    
    /** Game board that stores all pieces. */
    private final Board myBoard;
    
    /** Represents pause state of game. */
    private boolean myPaused;

    /**
     * Constructor for the GamePanel.
     * @param theBoard the game board that stores the shapes being drawn
     */
    public GamePanel(final Board theBoard) {
        super(true);
        myBoard = theBoard;
        setPreferredSize(new Dimension(BOARD_WIDTH * SCALE, BOARD_HEIGHT * SCALE));
        setBackground(Color.gray);   
    }
    
    /** 
     * Sets the pause state of the game so the panel knows when to paint.
     * @param thePause the pause state of the GUI
     */
    public void setPaused(final boolean thePause) {
        myPaused = thePause;
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        final Piece currentPiece = myBoard.getCurrentPiece();
        g2d.setPaint(Color.CYAN);
        for (int i = 0; i < BLOCKS; i++) {
            g2d.fillRect(((AbstractPiece) currentPiece).getBoardCoordinates()[i][0] * SCALE, 
                         (BOARD_HEIGHT
                          - ((AbstractPiece) currentPiece).getBoardCoordinates()[i][1] - 1)
                         * SCALE, SCALE, SCALE);                 
        }
        
        g2d.setPaint(Color.WHITE);
        for (int i = 0; i < myBoard.getFrozenBlocks().size(); i++) {
            for (int j = 0; j < myBoard.getWidth(); j++) {
                if (myBoard.getFrozenBlocks().get(i)[j] != Block.EMPTY) {
                    g2d.fillRect(j * SCALE, (BOARD_HEIGHT - i - 1) * SCALE, SCALE, SCALE); 
                }       
            }           
        }
        if (myPaused) {
            g2d.setPaint(Color.RED);
            g2d.setFont(new java.awt.Font(FONT, java.awt.Font.BOLD, FONT_SIZE));
            g2d.drawString("PAUSED", 0, BOARD_HEIGHT * SCALE / 2);
        } 
        
        if (myBoard.isGameOver()) {
            g2d.setPaint(Color.RED);
            g2d.setFont(new java.awt.Font(FONT, java.awt.Font.BOLD, FONT_SIZE));
            g2d.drawString("GAME OVER", 0, BOARD_HEIGHT * SCALE / 2);
        }  
    }
}
