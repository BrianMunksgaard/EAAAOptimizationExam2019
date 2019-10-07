//package dk.eaaa.bm.optimization;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import org.slf4j.event.Level;
//
//import dk.eaaa.bm.optimization.util.TimerUtil;
//
//
//@RunWith(JUnit4.class)
//public class ExhaustiveSolverTests {
//
//	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass().getName());	
//		
//	@Test
//	public void testExhaustiveSolutionForP1() {
//		TimerUtil tu = TimerUtil.start(log, Level.INFO, "P1 exhaustive test");
//		Problem p = new P1();
//		ExhaustiveSolver solver = new ExhaustiveSolver();
//		solver.solve(p);
//		tu.stop();
//	}
//	
//
//	@Test
//	public void testExhaustiveSolutionForP2() {
//		TimerUtil tu = TimerUtil.start(log, Level.INFO, "P2 exhaustive test");
//		Problem p = new P2();
//		ExhaustiveSolver solver = new ExhaustiveSolver();
//		solver.solve(p);
//		tu.stop();
//	}
//
//	@Test
//	public void testExhaustiveSolutionForRevAckley() {
//		TimerUtil tu = TimerUtil.start(log, Level.INFO, "RevAckley exhaustive test");
//		Problem p = new RevAckley();
//		ExhaustiveSolver solver = new ExhaustiveSolver();
//		solver.solve(p);
//		tu.stop();
//	}
//}
