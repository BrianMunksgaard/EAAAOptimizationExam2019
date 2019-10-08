package dk.eaaa.bm.optimization.ga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import dk.eaaa.bm.optimization.ga.SimpleGeneticAlgorithm;
import dk.eaaa.bm.optimization.problem.P1;
import dk.eaaa.bm.optimization.problem.P2;
import dk.eaaa.bm.optimization.problem.Problem;
import dk.eaaa.bm.optimization.problem.RevAckley;
import dk.eaaa.bm.optimization.util.TimerUtil;

@RunWith(JUnit4.class)
public class SimpleGeneticAlgorithmTests {

	@Test
	public void runAlgo() {
		
		Problem problem = new RevAckley();
		
		AlgorithmParameters parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		
		runAlgorithm(problem, parms);
	}
	
	private void runAlgorithm(Problem problem, AlgorithmParameters parms) {
		Problem p = problem;
				
		// Name used when timing the run time of the algorithm.
		String opName = String.format("%s, Parms: %s", p.getClass().getSimpleName(), parms.toString());
		
		// Configure logging.
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String loggerName = this.getClass().getName() + methodName;
		Logger log = LoggerFactory.getLogger(loggerName);		
		
		// Call algorithm.
		TimerUtil tu = TimerUtil.start(log, Level.INFO, opName);
		SimpleGeneticAlgorithm ga = new SimpleGeneticAlgorithm();
		ga.runAlgorithm(problem, parms.getPopulationSize(), parms.getGenerations(), parms.getUniformRate(), parms.getMutationRate(), parms.getEliteSize());
		tu.stop();

//		Double best = p.eval(result);
		log.info("");
//		return Pair.with(best, result);
	}
	
	
//	private Pair<Double, ArrayList<Double>> findSolutionUsingNeighborFactoryCircumferenceImpl(Problem p, int iterations, double stepSize, int neighbors) {
//	Problem problem = new ProblemWrapper(p);
//	NeighborFactory neighborFactory = new NeighborFactoryCircumferenceImpl(neighbors);
//	NeighborSelector neighborSelector = new NeighborSelectorPickBestFromLocalHillImpl(problem, neighborFactory);
//	
//	String opName = 
//			neighborFactory.getClass().getSimpleName() + " - "
//			+ p.getClass().getSimpleName() + ", "
//			+ "Iter=" + Integer.toString(iterations) + ", " 
//			+ "StepSize=" + Double.toString(stepSize) + ", "
//			+ "Neighbors=" + Integer.toString(neighbors); 
//			
//	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//    String loggerName = this.getClass().getName() + methodName;
//    Logger log = LoggerFactory.getLogger(loggerName);
//
//	TimerUtil tu = TimerUtil.start(log, Level.INFO, opName);
//	ImprovedHillClimbing solver = new ImprovedHillClimbing(problem, neighborSelector);
//	ArrayList<Double> result = solver.findOptima(iterations, stepSize);
//	tu.stop();
//	Double best = p.eval(result);
//	log.info("");
//	return Pair.with(best, result);
//}
//
//private void findSolutionUsingNeighborFactoryGridImpl(Problem p, int iterations, double stepSize) {
//	Problem problem = new ProblemWrapper(p);
//	NeighborFactory neighborFactory = new NeighborFactoryGridImpl();
//	NeighborSelector neighborSelector = new NeighborSelectorPickBestFromLocalHillImpl(problem, neighborFactory);
//	
//	String opName = 
//			neighborFactory.getClass().getSimpleName() + " - "
//			+ p.getClass().getSimpleName() + ", "
//			+ "Iter=" + Integer.toString(iterations) + ", " 
//			+ "StepSize=" + Double.toString(stepSize); 
//			
//	String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//    String loggerName = this.getClass().getName() + methodName;
//    Logger log = LoggerFactory.getLogger(loggerName);
//
//	TimerUtil tu = TimerUtil.start(log, Level.INFO, opName);
//	ImprovedHillClimbing solver = new ImprovedHillClimbing(problem, neighborSelector);
//	solver.findOptima(iterations, stepSize);
//	tu.stop();
//	log.info("");
//}

}
