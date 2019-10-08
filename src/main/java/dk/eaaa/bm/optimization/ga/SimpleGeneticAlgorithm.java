package dk.eaaa.bm.optimization.ga;

import dk.eaaa.bm.optimization.problem.Problem;

public class SimpleGeneticAlgorithm {

	private Problem problem = null;
	private int populationSize = 0;
	private int generations = 0;
	private double uniformRate = 0.5;
	private double mutationRate = 0.025;
	private final int tournamentSize = 5;
	private final int elite = 0;
	
	public boolean runAlgorithm(Problem problem, int populationSize, int generations, double uniformRate, double mutationRate) {

		this.problem = problem;
		this.populationSize = populationSize;
		this.generations = generations;
		this.uniformRate = uniformRate;
		this.mutationRate = mutationRate;
		
		Population myPop = new Population(this.problem, this.populationSize, true);
		
		for(int generationCount = 1; generationCount <= this.generations; generationCount++) {
			
		}
		
//		while (myPop.getFittest().getFitness() < getMaxFitness()) {
//			System.out.println(
//					"Generation: " + generationCount + " Correct genes found: " + myPop.getFittest().getFitness());
//			myPop = evolvePopulation(myPop);
//			generationCount++;
//		}
//		System.out.println("Solution found!");
//		System.out.println("Generation: " + generationCount);
//		System.out.println("Genes: ");
//		System.out.println(myPop.getFittest());
		return true;
	}

	public Population evolvePopulation(Population pop) {
		int elitismOffset;
		Population newPopulation = new Population(problem, pop.getIndividuals().size(), false);

		for (int i = elitismOffset; i < pop.getIndividuals().size(); i++) {
			Individual indiv1 = tournamentSelection(pop);
			Individual indiv2 = tournamentSelection(pop);
			Individual newIndiv = crossover(indiv1, indiv2);
			newPopulation.getIndividuals().add(i, newIndiv);
		}

		for (int i = elitismOffset; i < newPopulation.getIndividuals().size(); i++) {
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}

	private Individual crossover(Individual indiv1, Individual indiv2) {
		Individual newSol = null;//new Individual();
		for (int i = 0; i < newSol.getDefaultGeneLength(); i++) {
			if (Math.random() <= uniformRate) {
				newSol.setSingleGene(i, indiv1.getSingleGene(i));
			} else {
				newSol.setSingleGene(i, indiv2.getSingleGene(i));
			}
		}
		return newSol;
	}

	private void mutate(Individual indiv) {
		for (int i = 0; i < indiv.getDefaultGeneLength(); i++) {
			if (Math.random() <= mutationRate) {
				byte gene = (byte) Math.round(Math.random());
				indiv.setSingleGene(i, gene);
			}
		}
	}

	private Individual tournamentSelection(Population pop) {
		Population tournament = new Population(tournamentSize, false);
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.getIndividuals().size());
			tournament.getIndividuals().add(i, pop.getIndividual(randomId));
		}
		Individual fittest = tournament.getFittest();
		return fittest;
	}

	protected static double getFitness(Individual individual, Problem problem) {
		double fitness = problem.eval(individual.getGenes());
		return fitness;
	}



}