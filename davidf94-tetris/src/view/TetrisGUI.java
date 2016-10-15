package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.Board;

/**
 * A GUI for the game Tetris.
 * @author David
 * @version Autumn 2015
 */
public class TetrisGUI extends JFrame implements ActionListener, Observer {

    /** A generated serial ID number. */
    private static final long serialVersionUID = 4912602761251888671L;

    /** The default width for the game board. */
    private static final int BOARD_WIDTH = 10;

    /** The default height for the game board. */
    private static final int BOARD_HEIGHT = 20;

    /** The initial delay interval for the timer. */
    private static final int I_DELAY = 1000;

    /** The number of lines needed to advance difficulties. */
    private static final int LINES_TO_ADVANCE = 5;
    
    /** String key for play/pause property change. */
    private static final String PP_KEY = "Playing";

    /** Instance of PauseStopAction for other class access. */
    private final PauseStopAction myPSAction = new PauseStopAction();

    /** Instance of NewGameAction for other class access. */
    private final EndGameAction myEGAction = new EndGameAction();
    
    /** Instance of NewGameAction for other class access. */
    private final NewGameAction myNGAction = new NewGameAction();

    /** A boolean for determining whether game is over. */
    private boolean myGameOver;

    /** The current delay interval. */
    private int myDelay;

    /** the games current difficulty level. */
    private int myDifficulty;

    /** A timer used to increment the dropping of pieces onto the game board. */
    private Timer myTimer;

    /** The game boards that handles all the pieces. */
    private Board myBoard;

    /** The panel where the pieces are drawn. */
    private GamePanel myGamePanel;

    /** The panel where next piece, score, and shortcuts are shown. */
    private ScorePanel myScorePanel;

    /** The games current score. */
    private int myScore;

    /** The number of lines cleared. */
    private int myLinesCleared;



    /**
     * Constructor for the game GUI. 
     */
    public TetrisGUI() {
        super("Tetris Project - davidf94");
    }

    /**
     * Initializes the components of the GUI and starts the timer/game.
     */
    public void start() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        
        setJMenuBar(createMenuBar());
        myBoard = new Board(BOARD_WIDTH, BOARD_HEIGHT);
        myScorePanel = new ScorePanel(this, myBoard);
        myGamePanel = new GamePanel(myBoard);
        myBoard.addObserver(this);
        addKeyListener(new MyKeyListener());
        newGame(); 
        addPropertyChangeListener(myScorePanel);
        pack();
        setVisible(true);    
    }

    /** Helper method that initializes the critical game components. */
    private void newGame() {
        myDifficulty = 1;
        myLinesCleared = 0;
        myScore = 0;
        myGameOver = false;
        myTimer = new Timer(I_DELAY, this);
        myDelay = I_DELAY;
        myBoard.newGame(BOARD_WIDTH, BOARD_HEIGHT, null);
        myScorePanel = new ScorePanel(this, myBoard);
        add(myScorePanel, BorderLayout.EAST);
        myGamePanel = new GamePanel(myBoard);
        add(myGamePanel, BorderLayout.CENTER);
        firePropertyChange("NewGame", false, true);       
        this.revalidate();
        timeStart();
    }

    /**
     * @return myPSAction
     */
    public PauseStopAction getMyPSAction() {
        return myPSAction;
    }

    /**
     * @return myNGAction
     */
    public EndGameAction getMyEGAction() {
        return myEGAction;
    }

    /**
     * 
     * @return myNGAction
     */
    public NewGameAction getMyNGAction() {
        return myNGAction;
    }
    
    /**
     * Helper method to set up the games MenuBar with all the utilities associated.
     * @return the JMenuBar for the window 
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menubar = new JMenuBar(); 
        final JMenu filemenu = new JMenu("File");
        filemenu.setMnemonic(KeyEvent.VK_F);
        filemenu.add(new JMenuItem(new ExitAction()));
        menubar.add(filemenu);
        final JMenu gamemenu = new JMenu("Game");
        gamemenu.add(new JMenuItem(myPSAction));
        menubar.add(gamemenu);
        return menubar;
    }

    /** Helper method to stop timer and change boolean. */
    private void timePause() {
        if (!myGameOver) {
            myTimer.stop();
            myGamePanel.setPaused(true);
            myGamePanel.repaint();
            firePropertyChange(PP_KEY, true, false);
        }
    }

    /** Helper method to start timer and change boolean. */
    private void timeStart() {
        if (!myGameOver) {
            myTimer.start();
            myGamePanel.setPaused(false);
            myGamePanel.repaint();
            firePropertyChange(PP_KEY, false, true);
        }
    }

    /**
     * Method to calculate total score.
     * @param theTimer true if timer was cause of method call
     */
    private void changeScore(final boolean theTimer) {
        final int ppLine = myDifficulty * 50;
        final int delayscale = 150;
        final int oldscore = myScore;
        final int olddiff = myDifficulty;

        if (!myGameOver) {
            if (theTimer) {
                //for when timer is the cause of a score change
                myScore = oldscore + 1;         
            } else {
                myScore =  oldscore + ppLine;
                myDifficulty = (myLinesCleared + LINES_TO_ADVANCE) / LINES_TO_ADVANCE;
                if (myDelay > delayscale) {
                    myDelay = I_DELAY - (delayscale * (myDifficulty - 1));
                    myTimer.setDelay(myDelay);
                }
            }           
            firePropertyChange("MyScore", oldscore, myScore);
            firePropertyChange("MyDifficulty", olddiff, myDifficulty);
        }   
    }

    /**
     * 
     */
    private void endGame() {
        if (!myGameOver) {   
            timePause();
            myGameOver = true;
            final int answer = JOptionPane.showConfirmDialog(this, "Game over! :( \n"
                            + "Final Score :  " + myScore
                            + "\nWould you like to play again?");
            if (answer == JOptionPane.YES_OPTION) {
                newGame();
            } else {
                firePropertyChange("GameOver", false, true);             
            }
        }
    }

    //timer calls this to notify the game board
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.step();
        changeScore(true);
    }

    //game board calls this when a piece has moved. Tells the GamePanel to repaint
    @Override
    public void update(final Observable theBoard, final Object theObject) {
        myGamePanel.repaint();
        myScorePanel.notifyNextPanel();
        if ("Line Removed".equals(theObject)) {
            final int oldlines = myLinesCleared;
            myLinesCleared++;
            firePropertyChange("MyLines", oldlines, myLinesCleared);
            changeScore(false);
        } else if (myBoard.isGameOver() && !myGameOver) {
            endGame();
        }
    }

    /**
     * Inner class for defining the exit action.
     * @author David
     * @version Autumn 2015
     */
    public class ExitAction extends AbstractAction {

        /** A generated serial ID. */
        private static final long serialVersionUID = -4263587945929176092L;

        /** Constructor for an ExitAction. */
        public ExitAction() {
            super("Exit");
            putValue(SHORT_DESCRIPTION, "Close program");
            putValue(MNEMONIC_KEY, KeyEvent.VK_X);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('x'),
                                                             ActionEvent.ALT_MASK));
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            System.exit(EXIT_ON_CLOSE);

        }
    }

    /**
     * Inner class for defining the pause/start action.
     * @author David
     * @version Autumn 2015
     */
    public class PauseStopAction extends AbstractAction {

        /** A generated serial id number. */
        private static final long serialVersionUID = -3954680716829699854L;

        /** Constructor for an ExitAction. */
        public PauseStopAction() {
            super("Pause");
            putValue(SHORT_DESCRIPTION, "Pause/Play");
            putValue(MNEMONIC_KEY, KeyEvent.VK_P);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('p'),
                                                             ActionEvent.ALT_MASK));
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            if (!myGameOver) {
                if (myTimer.isRunning()) {
                    timePause();
                } else {
                    timeStart();
                }
            }
        }
    }

    /**
     * Inner class for defining the end game action.
     * @author David
     * @version Autumn 2015
     */
    public class EndGameAction extends AbstractAction {

        /** A generated serial ID number. */
        private static final long serialVersionUID = -1823397320093985293L;

        /** Constructor for an EndGameAction. */
        public EndGameAction() {
            super("End Game");
            putValue(SHORT_DESCRIPTION, "End current game");
            putValue(MNEMONIC_KEY, KeyEvent.VK_E);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('e'),
                                                             ActionEvent.ALT_MASK));
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            timePause();
            final int yesNo = JOptionPane.showConfirmDialog(myGamePanel, "End your game?", 
                                                            "End Game?", 
                                                            JOptionPane.YES_NO_CANCEL_OPTION);
            if (yesNo == JOptionPane.YES_OPTION) {
                endGame();
            } else {
                timeStart();
            }

        }
    }

    /**
     * Inner class for defining the new game action.
     * @author David
     * @version Autumn 2015
     */
    public class NewGameAction extends AbstractAction {

        /** A generated serial ID number. */
        private static final long serialVersionUID = -1823397320093985293L;

        /** Constructor for an NewGameAction. */
        public NewGameAction() {
            super("New Game");
            putValue(SHORT_DESCRIPTION, "Start new game");
            putValue(MNEMONIC_KEY, KeyEvent.VK_N);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(new Character('n'),
                                                             ActionEvent.ALT_MASK));
        }
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            int yesNo = 0;
            if (myGameOver) {
                yesNo = JOptionPane.showConfirmDialog(myGamePanel, "Start a new game?", 
                                                      "New Game?", 
                                                      JOptionPane.YES_NO_CANCEL_OPTION);
                if (yesNo == JOptionPane.YES_OPTION) {
                    newGame();
                } 
            }
        }
    }

    /**
     * A KeyListener to create shortcuts for moving the piece.
     * @author David
     * @version Autumn 2015
     */
    public class MyKeyListener extends KeyAdapter {

        /**
         * Tells the game to move the shape in the appropriate manner.
         * @param theEvent the key that was pressed.
         */
        public void keyPressed(final KeyEvent theEvent) {
            final char key = theEvent.getKeyChar();
            if (myTimer.isRunning()) {
                switch (key) {
                    case 'a': myBoard.moveLeft();
                    break;
                    case 's': myBoard.moveDown();
                    break;
                    case 'd': myBoard.moveRight();
                    break;
                    case 'w': myBoard.rotate();
                    break;
                    case KeyEvent.VK_SPACE: myBoard.hardDrop();
                    break;
                    default:
                        break;        
                }
            }

        }
    }
}
