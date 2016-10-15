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
public class Human extends AbstractVehicle {
    /**
     * 
     */
    private static final int MY_DEATH_TIME = 45;
    /**
     * 
     */
    private final Terrain myTerrain;

    /**
     * Constructor for a Human vehicle.
     * 
     * @param theX initial X value
     * @param theY initial Y value
     * @param theDir initial vehicle direction
     * @param theTerrain initial vehicle terrain specifically for humans
     */
    public Human(final int theX, final int theY, final Direction theDir, 
                 final Terrain theTerrain) {
        super(theX, theY, theDir, MY_DEATH_TIME);
        myTerrain = theTerrain;
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

        if (myTerrain.equals(Terrain.STREET)) {
            if (theTerrain == Terrain.STREET) {
                result = true;
            } else if (theTerrain == Terrain.LIGHT) {
                result = true;
            }
        } else if (myTerrain.equals(Terrain.LIGHT)) {
            if (theTerrain == Terrain.STREET) {
                result = true;
            } else if (theTerrain == Terrain.LIGHT) {
                result = true;
            }
        } else if (myTerrain.equals(theTerrain)) {
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
        boolean goodD = false;
        while (!goodD) {
            goodDir = Direction.random();
            if (myTerrain == Terrain.STREET) {
                if (theNeighbors.get(goodDir) == Terrain.STREET) {
                    goodD = true;
                } else if (theNeighbors.get(goodDir) == Terrain.LIGHT) {
                    goodD = true;
                }
            } else if (myTerrain == Terrain.LIGHT) {
                if (theNeighbors.get(goodDir) == Terrain.STREET) {
                    goodD = true;
                } else if (theNeighbors.get(goodDir) == Terrain.LIGHT) {
                    goodD = true;
                }
            } else if (theNeighbors.get(goodDir) == myTerrain) {
                goodD = true;
            }

        }
        return goodDir;
    }
}
