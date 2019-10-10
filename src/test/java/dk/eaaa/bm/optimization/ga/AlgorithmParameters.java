package dk.eaaa.bm.optimization.ga;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Algorithm parameters.
 */
@Getter
@Builder
@ToString
public class AlgorithmParameters {

	/*
	 * 
	 */
	@Builder.Default
	private int populationSize = 0;
	
	/*
	 * 
	 */
	@Builder.Default
	private int generations = 0;
	
	/*
	 * 
	 */
	@Builder.Default
	private double uniformRate = 0.5;
	
	/*
	 * 
	 */
	@Builder.Default
	private double mutationRate = 0.025;
	
	/*
	 * 
	 */
	@Builder.Default
	private final int tournamentSize = 5;
	
	/*
	 * The number of individuals that will survive from
	 * one generation to the next.
	 */
	@Builder.Default
	private int eliteSize = 0;

	@Builder.Default
	private MutationType mutationType = MutationType.GAUSSIAN;

}
