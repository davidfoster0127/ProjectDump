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
public class Truck extends AbstractVehicle {
    /**
     * How long the vehicle is dead for when it collides with a vehicle
     * with a lower death time. Also used for computing collision results.
     */
    private static final int MY_DEATH_TIME = 0;

    /**
     * Constructor for a Truck vehicle.
     * 
     * @param theX initial X value
     * @param theY initial Y value
     * @param theDir initial vehicle direction
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
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
        boolean result;
        
        if (theTerrain.equals(Terrain.STREET)) {
            result = true;
        } else if (theTerrain.equals(Terrain.LIGHT)) {
            result = true;
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
        int n = 0;
        boolean goodD = false;
        final int timeToTurnAround = 500;

        while (!goodD) {
            goodDir = Direction.random();
            while (goodDir == dir.reverse()) {
                goodDir = Direction.random();
            }
            if (theNeighbors.get(goodDir) == Terrain.STREET) {
                goodD = true;     
            } else if (theNeighbors.get(goodDir) == Terrain.LIGHT) {
                goodD = true;
            }  else {
                n++;
            }
            if (n > timeToTurnAround) {
                goodD = true;
                goodDir = dir.reverse();
            }
        }

        return goodDir;
    }
}
