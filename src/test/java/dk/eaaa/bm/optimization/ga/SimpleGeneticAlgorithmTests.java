package dk.eaaa.bm.optimization.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import com.qampo.util.vizualization.DataPoint;
import com.qampo.util.vizualization.DataSerie;
import com.qampo.util.vizualization.Plotter;

import dk.eaaa.bm.optimization.ga.SimpleGeneticAlgorithm;
import dk.eaaa.bm.optimization.problem.P1;
import dk.eaaa.bm.optimization.problem.P2;
import dk.eaaa.bm.optimization.problem.Problem;
import dk.eaaa.bm.optimization.problem.RevAckley;
import dk.eaaa.bm.optimization.util.TimerUtil;

@RunWith(JUnit4.class)
public class SimpleGeneticAlgorithmTests {

	@Test
	public void p1Test() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String loggerName = this.getClass().getName() + methodName;
		Logger log = LoggerFactory.getLogger(loggerName);		

		List<DataSerie> plotData = new ArrayList<DataSerie>();
		
		Problem problem = new P1();
		
		AlgorithmParameters parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(100)
				.uniformRate(0.9)
				.mutationRate(0.25)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(1000)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.9)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));
		
		parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.9)
				.mutationRate(0.9)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));


		Plotter plotter = new Plotter();
		String filename = plotter.plot(plotData, methodName, "Generations", "BestFitness");
		log.info("Graph file: {}",  filename);
		log.info("**********************");
			
	}
	
	@Test
	public void revAckley() throws Exception {
		
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String loggerName = this.getClass().getName() + methodName;
		Logger log = LoggerFactory.getLogger(loggerName);		

		List<DataSerie> plotData = new ArrayList<DataSerie>();
		
		Problem problem = new RevAckley();
		
		AlgorithmParameters parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(100)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(1000)
				.generations(100)
				.uniformRate(0.5)
				.mutationRate(0.025)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));

		parms = AlgorithmParameters.builder()
				.populationSize(10)
				.generations(100)
				.uniformRate(0.9)
				.mutationRate(0.25)
				.eliteSize(5)
				.build();
		plotData.add(runAlgorithm(problem, parms));


		Plotter plotter = new Plotter();
		String filename = plotter.plot(plotData, methodName, "Generations", "BestFitness");
		log.info("Graph file: {}",  filename);
		log.info("**********************");
	}
	
	private DataSerie runAlgorithm(Problem problem, AlgorithmParameters parms) throws Exception {
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
		List<GenerationProperties> algoResult = ga.runAlgorithm(problem, parms.getPopulationSize(), parms.getGenerations(), parms.getUniformRate(), parms.getMutationRate(), parms.getEliteSize());
		tu.stop();
		log.info("* * * * *");
		
		// Create data series for visual presentation.
		List<DataPoint> points = new ArrayList<>();
		for(GenerationProperties g : algoResult) {
			DataPoint dp = new DataPoint(g.getGenerationNumberAsDouble(), g.getBestFitnessVal());
			points.add(dp);
		}
		DataSerie series = new DataSerie(opName + ", Number of evaluations: " + ProblemNumbers.getInstance().getNoOfEvaluations(), points);
		return series;
	}
}
