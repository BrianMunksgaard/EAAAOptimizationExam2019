package dk.eaaa.bm.optimization.ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dk.eaaa.bm.optimization.problem.Problem;

public class Population {

	private List<Individual> individuals;
	private Problem problem;
	
	public Population(Problem problem, int size, boolean createNew) {
		individuals = new ArrayList<>();
		this.problem = problem;
		if (createNew) {
			createNewPopulation(size);
		}
	}

	protected Individual getIndividual(int index) {
		return individuals.get(index);
	}
	
	protected void setIndividual(int index, Individual individual) {
		individuals.set(index, individual);
	}


	/**
	 * Returns the best individual from the population.
	 */
	protected Individual getFittest() {
		Individual fittest = individuals.get(0);
		for (int i = 0; i < individuals.size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

	/*
	 * Creates a new population of the specified size. Indidivuals
	 * are generated randomly. 
	 */
	private void createNewPopulation(int size) {
		for (int i = 0; i < size; i++) {
			Individual newIndividual = new Individual(problem);
			individuals.add(i, newIndividual);
		}
	}

	/**
	 * Returns all individuals from the population.
	 * @return
	 */
	public List<Individual> getIndividuals() {
		return individuals;
	}
	
	/**
	 * Sort the population from most fit to least fit individual.
	 */
	public void sort() {
		Collections.sort(individuals, Collections.reverseOrder());
	}
}
