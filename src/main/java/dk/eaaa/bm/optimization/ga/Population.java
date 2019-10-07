package dk.eaaa.bm.optimization.ga;

import java.util.ArrayList;
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

	protected Individual getFittest() {
		Individual fittest = individuals.get(0);
		for (int i = 0; i < individuals.size(); i++) {
			if (fittest.getFitness() <= getIndividual(i).getFitness()) {
				fittest = getIndividual(i);
			}
		}
		return fittest;
	}

	private void createNewPopulation(int size) {
		for (int i = 0; i < size; i++) {
			Individual newIndividual = new Individual(problem);
			individuals.add(i, newIndividual);
		}
	}

	public List<Individual> getIndividuals() {
		return individuals;
	}
}
