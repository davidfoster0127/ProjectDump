/*
 * TCSS 305 C
 * Assignment 3 - EasyStreet
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import model.ATV;
import model.Car;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import org.junit.Test;

/**
 * @author David
 * @version Autumn 2015
 */
public class TruckTest {
    
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /**
     * Test method for Truck constructor.
     */
    @Test
    public void testTruckConstructor() {
        final Truck t = new Truck(1, 1, Direction.SOUTH);
        
        assertEquals("Truck x coordinate not initialized correctly!", 1, t.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 1, t.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.SOUTH, t.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, t.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", t.isAlive());
    }
    
    /**  
     * Test method for Truck setters.
     */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(1, 1, Direction.SOUTH);
        
        t.setX(12);
        assertEquals("Human setX failed!", 12, t.getX());
        t.setY(13);
        assertEquals("Human setY failed!", 13, t.getY());
        t.setDirection(Direction.NORTH);
        assertEquals("Human setDirection failed!", Direction.NORTH, t.getDirection());
    }
    /**
     * Test for canPass method.
     */
    @Test
    public void testCanPass() {
        final Truck truck = new Truck(1, 1, Direction.SOUTH);
        
        for (final Terrain t : Terrain.values()) {
            for (final Light l : Light.values()) {
                if (t == Terrain.STREET || t == Terrain.LIGHT) {
                    assertTrue("Trucks can travel on streets and lights", truck.canPass(t, l));
                } else {
                    assertFalse("Trucks can't travel on anything other than streets or "
                                    + "lights", truck.canPass(t, l));
                }
                    
                
            } 
        }
    }
    /**
     * Test for chooseDirection method.
     */
    @Test
    public void testChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        final Truck truck = new Truck(0, 0, Direction.NORTH);    
        
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.LIGHT) {
                continue;
            }
            final Truck truck2 = new Truck(0, 0, Direction.NORTH);
            neighbors.put(Direction.EAST, t);
            
            if (t == Terrain.STREET) {
                neighbors.put(Direction.SOUTH, Terrain.LIGHT);
            } else {
                neighbors.put(Direction.SOUTH, t);
            }

            int tries = 0;
            while (tries < TRIES_FOR_RANDOMNESS) {
                tries = tries + 1;
                final Direction dir = truck2.chooseDirection(neighbors);
                assertTrue(dir == Direction.EAST || dir == Direction.SOUTH);
            }
        }

        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        final Direction dir = truck.chooseDirection(neighbors);
        assertSame(dir, Direction.SOUTH);

    }
    /**
     * Test for collide method.
     */
    @Test
    public void testCollide() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        final Car car = new Car(0, 0, Direction.NORTH);
        final ATV atv = new ATV(1, 1, Direction.NORTH);
        truck.collide(car);
        assertTrue(truck.isAlive());
        car.collide(truck);
        assertTrue(truck.isAlive());
        truck.collide(atv);
        car.collide(atv);
        atv.collide(car);
        assertFalse(car.isAlive());
        
    }
    /**
     * Test for getImageFileName method.
     */
    @Test
    public void testGetImageFileName() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        final Car car = new Car(0, 0, Direction.NORTH);
        assertEquals("truck.gif", truck.getImageFileName());
        car.collide(truck);
        assertEquals("car_dead.gif", car.getImageFileName());
    }
    /**
     * Test for the poke method.
     */
    @Test
    public void testPoke() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        final Car car = new Car(0, 0, Direction.NORTH);
        int i = -1;
        car.collide(truck);
        truck.poke();
        assertFalse(car.isAlive());
        while (i < 5) {
            car.poke();
            i++;
        }
        
    }
    
    /**
     * Test for the toString method.
     */
    @Test
    public void testReset() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        truck.setX(2);
        truck.setY(4);
        truck.setDirection(Direction.SOUTH);
        truck.reset();
        assertEquals(0, truck.getX());
        assertEquals(0, truck.getY());
        assertEquals(Direction.NORTH, truck.getDirection());

    }
    /**
     * Test for the toString method.
     */
    @Test
    public void testToString() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        assertEquals("truck -1", truck.toString());

    }
    
}

