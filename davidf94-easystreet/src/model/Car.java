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
public class Car extends AbstractVehicle {
    /**
     * How long the vehicle is dead for when it collides with a vehicle
     * with a lower death time. Also used for computing collision results.
     */
    private static final int MY_DEATH_TIME = 5;
    
    /**
     * Constructor for a Car vehicle.
     * 
     * @param theX initial X value
     * @param theY initial Y value
     * @param theDir initial vehicle direction
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }

    /** 
     * A query that returns whether this vehicle can pass through the given type of 
     * terrain, when the street lights are in the given state.
     * 
     * @param theTerrain The terrain the vehicle is attempting to pass.
     * @param theLight The state of the lights on the map at the time of the method call
     * @return True if vehicle can pass with given conditions or false otherwise
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;

        if (theTerrain.equals(Terrain.STREET)) {
            result = true;
        } else if (theTerrain.equals(Terrain.LIGHT)) {
            switch (theLight) {
                case GREEN:
                    result = true;
                    break;
                case YELLOW:
                    result = true;
                    break;
                case RED:
                    result = false;
                    break;
                default:
                    break;
            }
        } else {
            result = false;
        }

        return result;
    }

    /**
     * A query that returns the direction in which this vehicle would like to move, given 
     * the specified information.
     * 
     * @param theNeighbors A map containing the terrain mapped to a direction around the 
     * vehicle
     * @return goodDir A direction the vehicle would prefer to go in
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction dir = getDirection();
        Direction goodDir = dir;
        boolean goodD = false;
        while (!goodD) {
            if (theNeighbors.get(dir) == Terrain.STREET) {
                goodDir = dir;
                goodD = true;
            } else if (theNeighbors.get(dir) == Terrain.LIGHT) {
                goodDir = dir;
                goodD = true;
            } else if (theNeighbors.get(dir.left()) == Terrain.STREET) {
                goodDir = dir.left();
                goodD = true;
            } else if (theNeighbors.get(dir.left()) == Terrain.LIGHT) {
                goodDir = dir.left();
                goodD = true;
            } else if (theNeighbors.get(dir.right()) == Terrain.STREET) {
                goodDir = dir.right();
                goodD = true;
            } else if (theNeighbors.get(dir.right()) == Terrain.LIGHT) {
                goodDir = dir.right();
                goodD = true;
            } else {
                goodDir = dir.reverse();
                goodD = true;
            }

        }

        return goodDir;
    }
}
