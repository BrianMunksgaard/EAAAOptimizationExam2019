package dk.eaaa.bm.optimization.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import dk.eaaa.bm.optimization.problem.Problem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleGeneticAlgorithm {

	private Problem problem = null;
	private int populationSize = 0;
	private int generations = 0;
	private double uniformRate = 0.5;
	private double mutationRate = 0.025;
	private final int tournamentSize = 5;
	private int eliteSize = 0;
	
	/**
	 * 
	 * @param problem
	 * @param populationSize
	 * @param generations
	 * @param uniformRate
	 * @param mutationRate
	 * @param eliteSize
	 * @return
	 */
	public List<GenerationProperties> runAlgorithm(Problem problem, int populationSize, int generations, double uniformRate, double mutationRate, int eliteSize) {

		this.problem = problem;
		this.populationSize = populationSize;
		this.generations = generations;
		this.uniformRate = uniformRate;
		this.mutationRate = mutationRate;
		this.eliteSize = eliteSize;
		
		ProblemNumbers.getInstance().reset();
		List <GenerationProperties> algoResult = new ArrayList<>();
		
		// Population.
		Population myPop = null;
		int generationCount = 0;
		
		// Best overall individual.
		Individual bestIndividual = null;
		double bestFitness = -Double.MAX_VALUE;
		
		// Best individual in the current population.
		Individual bestPopulationIndividual = null;
		double bestPopulationFitness = -Double.MAX_VALUE;
				
		// Now evolve the population until the specified number of generations has been reached.
		while (++generationCount <= this.generations) {
			
			// First generation.
			if(generationCount == 1) {
				myPop = new Population(this.problem, this.populationSize, true);
				bestIndividual = myPop.getFittest();
				bestFitness = bestIndividual.getFitness();
				bestPopulationIndividual = bestIndividual;
				bestPopulationFitness = bestFitness;
				
			// Next generation.	
			} else {
				myPop = evolvePopulation(myPop);
				
				bestPopulationIndividual = myPop.getFittest();
				bestPopulationFitness = bestPopulationIndividual.getFitness();
				
				if(bestFitness < bestPopulationIndividual.getFitness()) {
					bestIndividual = bestPopulationIndividual;
					bestFitness = bestPopulationFitness;
				}		
			}

			GenerationProperties result = GenerationProperties.builder()
					.generationNumber(generationCount)
					.bestIndividual(bestIndividual)
					.bestFitnessVal(bestFitness)
					.numberOfEvaluations(ProblemNumbers.getInstance().getNoOfEvaluations())
					.build();
			
			algoResult.add(result);

			String msg = String.format("Generations: %d, Evaluations: %d, Best fitness: %.4f, Current fitness: %.4f", generationCount, ProblemNumbers.getInstance().getNoOfEvaluations(), bestFitness, bestPopulationFitness);
			log.info(msg);
		} 
		
		return algoResult;
	}

	/*
	 * Grows the current population and returns the next generation. 
	 */
	private Population evolvePopulation(Population pop) {
		int elitismOffset = eliteSize;
		
		// Prepare the new population.
		Population newPopulation = new Population(problem, pop.getIndividuals().size(), false);
		
		// Let the best individuals survive to the next generation. 
		pop.sort();
		for (int i = 0; i < elitismOffset - 1; i++) {
			newPopulation.getIndividuals().add(pop.getIndividual(i));
		}

		// Now prepare for the crossover. For each individual, except the survivors,
		// pick 2 best from tournament and generate offspring.
		for (int i = elitismOffset; i < pop.getIndividuals().size(); i++) {
			Individual indiv1 = tournamentSelection(pop);
			Individual indiv2 = tournamentSelection(pop);
			Individual newIndiv = crossover(indiv1, indiv2);
			newPopulation.getIndividuals().add(newIndiv);
		}

		// Mutate the individuals except the best that survived from
		// the previous generation.
		for (int i = elitismOffset; i < newPopulation.getIndividuals().size(); i++) {
			mutate(newPopulation.getIndividual(i));
		}

		return newPopulation;
	}

	/*
	 * Generates a new offspring from the two individuals.
	 */
	private Individual crossover(Individual indiv1, Individual indiv2) {
		
		Individual child = new Individual(problem);
		
		// Determine the genes for the new offspring.
		for (int i = 0; i < child.getDefaultGeneLength(); i++) {
			
			// "Merge" the genes into a new gene according to uniform rate?
			if (Math.random() <= uniformRate) {
				double newGene = (indiv1.getSingleGene(i) + indiv2.getSingleGene(i)) / 2.0;
				child.setSingleGene(i, newGene);
			
			// Or, pick a gene from one of the parents by 50/50 chance.
			} else {
				if(Math.random() <= 0.5) {
					child.setSingleGene(i, indiv1.getSingleGene(i));
				} else {
					child.setSingleGene(i, indiv2.getSingleGene(i));	
				}
			}
		}
		return child;
	}

	/*
	 * Mutate the specified individual according to
	 * the mutation rate.
	 */
	private void mutate(Individual indiv) {
		for (int i = 0; i < indiv.getDefaultGeneLength(); i++) {
			if (Math.random() <= mutationRate) {
				Random r = new Random();
				double gene = 0.1 * r.nextGaussian() + indiv.getSingleGene(i);
				indiv.setSingleGene(i, gene);
			}
		}
	}

	/*
	 * Create a random population of size tournamentSize from the
	 * current population and select the best individual from that
	 * population.
	 */
	private Individual tournamentSelection(Population pop) {
		Population tournament = new Population(problem, tournamentSize, false);
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.getIndividuals().size());
			tournament.getIndividuals().add(i, pop.getIndividual(randomId));
		}
		Individual fittest = tournament.getFittest();
		return fittest;
	}

//	/**
//	 * Returns the fitness of the specified individual according
//	 * to the problem.
//	 * 
//	 * @param individual
//	 * @param problem
//	 * @return
//	 */
//	protected static double getFitness(Individual individual, Problem problem) {
//		double fitness = problem.eval(individual.getGenes());
//		return fitness;
//	}


}