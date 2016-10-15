// Assignment #2, Evolved Names
// David Foster & Zeeshan Karim

import java.util.ArrayList;
import java.util.Collections;

public class Population {
	
	public final ArrayList<Genome> pop;
	public final Double muteRate;
	public Genome mostFit;
	
	public Population(Integer numGenomes, Double mutationRate) {
		pop = new ArrayList<Genome>(numGenomes);
		muteRate = mutationRate;
		for (int i = 0; i < numGenomes; i++) {
			pop.add(new Genome(muteRate));
		}
		mostFit = pop.get(0);
	}
	
	//called every breeding cycle
	//update mostfit, deletes least fit %50, refill population with new genomes
	public void day() {
		//sort them based on fitness!!! most fit (the lowest fitness level from 0-1) at the top
		
		mostFit = pop.get(0);
		final int size = pop.size();
		while (pop.size() > size/2) {
			pop.remove(size/2);
		}
		
		while (pop.size() < size) {
			if (Main.rng.nextBoolean()) {
				Genome toCopy = new Genome(pop.get(Main.rng.nextInt(size/2)));
				toCopy.mutate();
				pop.add(toCopy);
			} else {
				Genome toCross1 = new Genome(pop.get(Main.rng.nextInt(size/2)));
				Genome toCross2 = new Genome(pop.get(Main.rng.nextInt(size/2)));
				toCross1.crossover(toCross2);
				toCross1.mutate();
				pop.add(toCross1);
			}
		}	
		Collections.sort(pop);
	}
	
	public String toString() {
		StringBuilder popString = new StringBuilder();
		for (int i = 0; i < pop.size(); i++) {
			popString.append(i +": " +this.pop.get(i).toString() + "\n");
		}
		return popString.toString();
			
	}	
}
