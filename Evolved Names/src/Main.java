// Assignment #2, Evolved Names
// David Foster & Zeeshan Karim

import java.util.Random;

public class Main {

	public static Random rng = new Random();
	public static final Integer D_GENOMES = 100;
	public static final Double D_MUTATIONRATE = .05;
	
	public static void main(String[] args) {
		Population pop = new Population(D_GENOMES, D_MUTATIONRATE);
		int gens = 0;
		long starttime = System.nanoTime(); 
		while (true) {
			pop.day();
			System.out.println("Generation: " + gens + " " + pop.mostFit.toString());
			gens++;
			
			if (pop.mostFit.fitness() == 0 || gens == 10000) break;
		}
		//System.out.println(pop.toString());
		long endtime = System.nanoTime();
		double runtime = (double)(endtime - starttime)/1000000000; // in milliseconds
		System.out.println("\nDone\n" + pop.mostFit.toString());
		System.out.println("Iterated for " +gens+ " generations");
		System.out.println("Runtime: " +runtime+ " seconds");

		//testGenome();
		//testPopulation();
		
	}
	// Tests Genome.java
	private static void testGenome() {
		Genome testG = new Genome(.05);
		Genome testG2 = new Genome(.05);
		int i = 0;
		while (i<1000) {
			testG.mutate();
			testG2.mutate();
			testG.crossover(testG2);
			// toString uses Genome.fitness()
			System.out.println(testG.toString() + testG2.toString() + testG.compareTo(testG2));
			i++;
		}
	}
	// Tests Population.java
	private static void testPopulation() {
		Population popTest = new Population(D_GENOMES, D_MUTATIONRATE);
		System.out.println(popTest.toString());
		popTest.day();
		System.out.println(popTest.toString());
	}

}
