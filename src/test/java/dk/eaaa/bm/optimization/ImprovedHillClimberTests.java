//package dk.eaaa.bm.optimization;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.javatuples.Pair;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.event.Level;
//
//import dk.eaaa.bm.optimization.util.TimerUtil;
//
//@RunWith(JUnit4.class)
//public class ImprovedHillClimberTests {
//	
//	@Test
//	public void testSolutionForP1() {
//		Problem p = new P1();
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.01);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.01, 10);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.01);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.01, 100);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.1);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.1, 10);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.1);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.1, 100);
//	}
//
//	@Test
//	public void testSolutionForP2() {
//		Problem p = new P2();
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.01);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.01, 10);
//		
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.01);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.01, 100);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.1);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.1, 10);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.1);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.1, 100);
//	}
//
//	@Test
//	@Ignore
//	public void _testSolutionForRevAckleyUsingNeighborFactoryGridImpl() {
//		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//        String loggerName = this.getClass().getName() + methodName;
//        Logger log = LoggerFactory.getLogger(loggerName);
//        
//		Problem p = new RevAckley();
//		ArrayList<Double> point = new ArrayList<>(Arrays.asList(-0.00, -0.00));
//		String msg = String.format("%.4f", p.eval(point));
//		log.info(msg);
//	}
//	
//	@Test
//	public void testSolutionForRevAckleyUsingNeighborFactoryGridImpl() {
//		Problem p = new RevAckley();
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.001);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.001, 10);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.001);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.001, 100);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 200, 0.001);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 200, 0.001, 200);
//
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.01);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.01, 10);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.01);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.01, 100);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 200, 0.01);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 200, 0.01, 200);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 10, 0.1);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 10, 0.1, 10);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 100, 0.1);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 100, 0.1, 100);
//		
//		findSolutionUsingNeighborFactoryGridImpl(p, 200, 0.1);
//		findSolutionUsingNeighborFactoryCircumferenceImpl(p, 200, 0.1, 200);
//	}
//		
//	private Pair<Double, ArrayList<Double>> findSolutionUsingNeighborFactoryCircumferenceImpl(Problem p, int iterations, double stepSize, int neighbors) {
//		Problem problem = new ProblemWrapper(p);
//		NeighborFactory neighborFactory = new NeighborFactoryCircumferenceImpl(neighbors);
//		NeighborSelector neighborSelector = new NeighborSelectorPickBestFromLocalHillImpl(problem, neighborFactory);
//		
//		String opName = 
//				neighborFactory.getClass().getSimpleName() + " - "
//				+ p.getClass().getSimpleName() + ", "
//				+ "Iter=" + Integer.toString(iterations) + ", " 
//				+ "StepSize=" + Double.toString(stepSize) + ", "
//				+ "Neighbors=" + Integer.toString(neighbors); 
//				
//		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//        String loggerName = this.getClass().getName() + methodName;
//        Logger log = LoggerFactory.getLogger(loggerName);
//    
//		TimerUtil tu = TimerUtil.start(log, Level.INFO, opName);
//		ImprovedHillClimbing solver = new ImprovedHillClimbing(problem, neighborSelector);
//		ArrayList<Double> result = solver.findOptima(iterations, stepSize);
//		tu.stop();
//		Double best = p.eval(result);
//		log.info("");
//		return Pair.with(best, result);
//	}
//	
//	private void findSolutionUsingNeighborFactoryGridImpl(Problem p, int iterations, double stepSize) {
//		Problem problem = new ProblemWrapper(p);
//		NeighborFactory neighborFactory = new NeighborFactoryGridImpl();
//		NeighborSelector neighborSelector = new NeighborSelectorPickBestFromLocalHillImpl(problem, neighborFactory);
//		
//		String opName = 
//				neighborFactory.getClass().getSimpleName() + " - "
//				+ p.getClass().getSimpleName() + ", "
//				+ "Iter=" + Integer.toString(iterations) + ", " 
//				+ "StepSize=" + Double.toString(stepSize); 
//				
//		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
//        String loggerName = this.getClass().getName() + methodName;
//        Logger log = LoggerFactory.getLogger(loggerName);
//    
//		TimerUtil tu = TimerUtil.start(log, Level.INFO, opName);
//		ImprovedHillClimbing solver = new ImprovedHillClimbing(problem, neighborSelector);
//		solver.findOptima(iterations, stepSize);
//		tu.stop();
//		log.info("");
//	}
//
//}
