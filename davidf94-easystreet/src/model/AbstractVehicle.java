/*
 * TCSS 305 C
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.Map;

/**
 * @author David
 * @version Autumn 2015
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Direction this vehicle is facing.
     */
    private Direction myDir;
    /**
     * Initial direction this vehicle spawned with.
     */
    private final Direction myIDir;
    /**
     * Integer used for counting pokes.
     */
    private int myDeathCounter = -1;
    /**
     * Vehicles  x coordinate.
     */
    private int myX;
    /**
     * Vehicles y coordinate.
     */
    private int myY;
    /**
     * Vehicles initial x coordinate.
     */
    private final int myIX;
    /**
     * Vehicles initial y coordinate.
     */
    private final int myIY;
    /**
     * The time a vehicle spends dead in a collision.
     */
    private final int myDeathTime;
    /**
     * Value of this vehicles alive status.
     */
    private boolean myLife = true;
    
    
    /**
     * Parent constructor for vehicles.
     * 
     * @param theX initial X value
     * @param theY initial Y value
     * @param theDir initial vehicle direction
     * @param theDeathTime vehicles death timer (stays dead for this number of moves)
     */
    public AbstractVehicle(final int theX, final int theY, final Direction theDir, 
                           final int theDeathTime) {
        super();
        myX = theX;
        myIX = theX;
        myY = theY;
        myIY = theY;
        myDir = theDir;
        myIDir = theDir;
        myDeathTime = theDeathTime;
    }
    
    /** 
     * A query that returns whether this vehicle can pass through the given type of 
     * terrain, when the street lights are in the given state.
     */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    /** 
     * A query that returns the direction in which this vehicle would like to move, given 
     * the specified information.
     */
    @Override
    public abstract Direction chooseDirection(final Map<Direction, Terrain> theNeighbors);

    /** 
     * A command that notifies this vehicle that it has collided with the given 
     * other Vehicle object.
     * 
     * @param theOther The vehicle this one collided with.
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive()) {
            if (myDeathTime > theOther.getDeathTime()) {
                myLife = false;    
            }
            
        }
    }

    /**
     * A query that returns the number of updates between this vehicle's death 
     * and when it should revive.
     * 
     * @return myDeathTime An integer representing how long a vehicle stays dead for
     * after a collision with a vehicle with a lower death time.
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /** 
     * A query that returns the name of the image file that the GUI will 
     * use to draw this Vehicle object on the screen.
     * 
     * @return imageFile A string representation of the .gif file of the vehicle. Returns
     * the normal picture if alive, the upside down version if the vehicle is dead.
     * 
     */
    @Override
    public String getImageFileName() {
        String imageFile = null;
        if (!this.isAlive()) {
            imageFile = this.getClass().getSimpleName().toLowerCase() + "_dead.gif";
        } else {
            imageFile = this.getClass().getSimpleName().toLowerCase() + ".gif";
        }
        return imageFile;
    }

    /**
     * A query that returns the direction this vehicle is facing, one of Direction.NORTH, 
     * Direction.SOUTH, Direction.EAST, or Direction.WEST.
     * 
     * @return myDir The direction this vehicle is currently facing.
     */
    @Override
    public Direction getDirection() {
        return myDir;
    }

    /**
     * Query that returns the x coordinate of this vehicle.
     * 
     * @return myX The current x coordinate of this vehicle.
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * Query that returns the y coordinate of this vehicle.
     * 
     * @return myY The current y coordinate of this vehicle.
     */
    @Override
    public int getY() {
        return myY;
    }

    /** 
     * Query that returns whether this vehicle is alive.
     * 
     * @return myLife A boolean representing this vehicles alive status.
     */
    @Override
    public boolean isAlive() {
        return myLife;
    }

    /** 
     * A command called by the graphical user interface once for 
     * each time the city animates one turn.
     */
    @Override
    public void poke() {
        myDeathCounter++;
        if (myDeathTime <= myDeathCounter) {
            myLife = true;
            myDeathCounter = -1;
            myDir = Direction.random();
        }

    }

    /** 
     * A command that instructs this Vehicle object to return to the initial state.
     */
    @Override
    public void reset() {
        myX = myIX;
        myY = myIY;
        myDir = myIDir;
        myLife = true;
    }

    /**
     * A command that sets a vehicles direction.
     * 
     * @param theDir The direction 
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDir = theDir;
    }

    /**
     * A command that sets a vehicles x coordinate.
     * 
     * @param theX X coordinate for vehicle to be set to.
     */
    @Override
    public void setX(final int theX) {
        myX = theX;
    }

    /**
     * A command that sets a vehicles y coordinate.
     * 
     * @param Y coordinate for vehicle to be set to.
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }
    
    /**
     * A query that returns a vehicles name and deathcounter.
     * 
     * @return A string with vehicles name and deathcounter.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName().toLowerCase() + " " + myDeathCounter;

    }

}
