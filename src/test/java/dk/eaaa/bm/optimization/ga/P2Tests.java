package dk.eaaa.bm.optimization.ga;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import com.qampo.util.vizualization.DataSerie;
import dk.eaaa.bm.optimization.TestHelper;
import dk.eaaa.bm.optimization.problem.P2;
import dk.eaaa.bm.optimization.problem.Problem;

@RunWith(JUnit4.class)
public class P2Tests {

	@Test
	public void p2() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String loggerName = this.getClass().getName() + methodName;
		
		MDC.put("loggerFileName", methodName);
		Logger log = LoggerFactory.getLogger(loggerName);		

		List<DataSerie> plotData = new ArrayList<DataSerie>();
		
		Problem problem = new P2();
		
		// Low population size.
		AlgorithmParameters parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		// The default.
		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		// Increases uniform rate, i.e. higher probability of "merging"
		// the genes in the crossover to the next population.
		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(100)
				.uniformRate(0.9)
				.mutationRate(0.25)
				.eliteSize(5)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		// High population size.
		parms = AlgorithmParameters.builder()
				.populationSize(1000)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		// Low population size with increased uniform rate.
		parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.9)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		// Low population with increased uniform rate
		// and mutation rate.
		parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.9)
				.mutationRate(0.9)
				.eliteSize(5)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));



		TestHelper.buildGraph(plotData, methodName);
		log.info("**********************");
		
		MDC.remove("loggerFileName");
	}
		
	@Test
	public void p2WithHigherUniformAndMutationRatesAndDifferentEliteSizes() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String loggerName = this.getClass().getName() + methodName;
		
		MDC.put("loggerFileName", methodName);
		Logger log = LoggerFactory.getLogger(loggerName);		

		List<DataSerie> plotData = new ArrayList<DataSerie>();
		
		Problem problem = new P2();
		
		AlgorithmParameters parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(200)
				.uniformRate(0.8)
				.mutationRate(0.25)
				.eliteSize(2)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(200)
				.uniformRate(0.8)
				.mutationRate(0.1)
				.eliteSize(2)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(200)
				.uniformRate(0.8)
				.mutationRate(0.25)
				.eliteSize(15)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(200)
				.uniformRate(0.8)
				.mutationRate(0.1)
				.eliteSize(15)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		TestHelper.buildGraph(plotData, methodName);
		log.info("**********************");
		
		MDC.remove("loggerFileName");
	}
	
	@Test
	public void p2WithHigherUniformAndMutationRatesAndDifferentEliteSizesAndVeryLargePopulations() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String loggerName = this.getClass().getName() + methodName;
		
		MDC.put("loggerFileName", methodName);
		Logger log = LoggerFactory.getLogger(loggerName);		

		List<DataSerie> plotData = new ArrayList<DataSerie>();
		
		Problem problem = new P2();
		
		AlgorithmParameters parms = AlgorithmParameters.builder()
				.populationSize(10000)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.25)
				.eliteSize(2)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(10000)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.1)
				.eliteSize(2)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(10000)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.25)
				.eliteSize(15)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(10000)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.1)
				.eliteSize(15)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		TestHelper.buildGraph(plotData, methodName);
		log.info("**********************");
		
		MDC.remove("loggerFileName");
	}
	
	@Test
	public void p2WithHigherMutationRatesAndUniformMutationType() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String loggerName = this.getClass().getName() + methodName;
		
		MDC.put("loggerFileName", methodName);
		Logger log = LoggerFactory.getLogger(loggerName);		

		List<DataSerie> plotData = new ArrayList<DataSerie>();
		
		Problem problem = new P2();
		
		AlgorithmParameters parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.1)
				.eliteSize(5)
				.mutationType(MutationType.UNIFORM)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.3)
				.eliteSize(5)
				.mutationType(MutationType.UNIFORM)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.5)
				.eliteSize(15)
				.mutationType(MutationType.UNIFORM)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(500)
				.uniformRate(0.8)
				.mutationRate(0.7)
				.eliteSize(15)
				.mutationType(MutationType.UNIFORM)
				.build();
		plotData.add(TestHelper.runAlgorithm(problem, parms));

		TestHelper.buildGraph(plotData, methodName);
		log.info("**********************");
		
		MDC.remove("loggerFileName");
	}
}
