package dk.eaaa.bm.optimization.ga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dk.eaaa.bm.optimization.ga.SimpleGeneticAlgorithm;
import dk.eaaa.bm.optimization.problem.P1;
import dk.eaaa.bm.optimization.problem.P2;
import dk.eaaa.bm.optimization.problem.Problem;
import dk.eaaa.bm.optimization.problem.RevAckley;

@RunWith(JUnit4.class)
public class SimpleGeneticAlgorithmTests {

	@Test
	public void runAlgo() {
		SimpleGeneticAlgorithm ga = new SimpleGeneticAlgorithm();
		Problem problem = new RevAckley();
		
		//ga.runAlgorithm(problem, populationSize, generations, uniformRate, mutationRate)
		ga.runAlgorithm(problem, 200, 200, 0.5, 0.025);
	}
}
