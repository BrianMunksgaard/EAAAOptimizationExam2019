package dk.eaaa.bm.optimization.ga;

import java.util.ArrayList;

import dk.eaaa.bm.optimization.problem.Problem;
import dk.eaaa.bm.optimization.util.ProblemUtil;

public class Individual {

	protected int defaultGeneLength = 2;
	private ArrayList<Double> genes;
	private Problem problem;
	
	private double fitness = -Double.MAX_VALUE;

	public Individual(Problem problem) {
		this.problem = problem;
		this.defaultGeneLength = problem.getDimensions();
		this.genes = ProblemUtil.getRandomPoint(this.problem);
	}

	protected double getSingleGene(int index) {
		return genes.get(index);
	}

	protected void setSingleGene(int index, double value) {
		genes.set(index, value);
		fitness = -Double.MAX_VALUE;
	}

	public double getFitness() {
		if (fitness == -Double.MAX_VALUE) {
			fitness = SimpleGeneticAlgorithm.getFitness(this, problem);
		}
		return fitness;
	}

	@Override
	public String toString() {
		String geneString = "";
		for (int i = 0; i < defaultGeneLength; i++) {
			geneString += getSingleGene(i);
		}
		return geneString;
	}

	public int getDefaultGeneLength() {
		return defaultGeneLength;
	}
	
	public ArrayList<Double> getGenes() {
		return genes;
	}

}