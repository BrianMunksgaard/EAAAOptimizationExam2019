package dk.eaaa.bm.optimization.util;

import java.util.ArrayList;

import dk.eaaa.bm.optimization.problem.Problem;

/**
 * Utility methods for Problem.
 */
public class ProblemUtil {
	
	/**
	 * Generates a random starting point for the specified problem.
	 */
	public static ArrayList<Double> getRandomPoint(Problem problem) {
		
		ArrayList<Double> randomPoint = new ArrayList<>(problem.getDimensions());
		
		// Generate random value for each dimension in the point.
		for(int dim = 0; dim < problem.getDimensions(); dim++) {
			
			// Get lower and upper bounds for the current dimension.
			Double minValCurrentDim = problem.getMinValues().get(dim);
			Double maxValCurrentDim = problem.getMaxValues().get(dim);
			
			// Generate random value between lower and upper bounds.
			Double randomDimValue = minValCurrentDim + Math.random() * (maxValCurrentDim - minValCurrentDim);
			randomPoint.add(randomDimValue);
		}
		
		return randomPoint;
	}
}
