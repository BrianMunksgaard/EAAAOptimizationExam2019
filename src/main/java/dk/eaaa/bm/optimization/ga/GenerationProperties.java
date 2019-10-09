package dk.eaaa.bm.optimization.ga;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class GenerationProperties {

	private int generationNumber;
	
	private Individual bestIndividual;
	
	private double bestFitnessVal;
	
	private int numberOfEvaluations;
	
	public double getGenerationNumberAsDouble() {
		return Double.valueOf(generationNumber);
	}
}
