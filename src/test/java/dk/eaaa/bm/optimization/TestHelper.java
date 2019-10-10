package dk.eaaa.bm.optimization;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.event.Level;

import com.qampo.util.vizualization.DataPoint;
import com.qampo.util.vizualization.DataSerie;
import com.qampo.util.vizualization.Plotter;

import dk.eaaa.bm.optimization.ga.AlgorithmParameters;
import dk.eaaa.bm.optimization.ga.GenerationProperties;
import dk.eaaa.bm.optimization.ga.ProblemNumbers;
import dk.eaaa.bm.optimization.ga.SimpleGeneticAlgorithm;
import dk.eaaa.bm.optimization.problem.Problem;
import dk.eaaa.bm.optimization.util.TimerUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestHelper {

	public static DataSerie runAlgorithm(Problem problem, AlgorithmParameters parms) throws Exception {
		Problem p = problem;
				
		// Name used when timing the run time of the algorithm.
		String opName = String.format("%s, Parms: %s", p.getClass().getSimpleName(), parms.toString());
				
		// Call algorithm.
		TimerUtil tu = TimerUtil.start(log, Level.INFO, opName);
		SimpleGeneticAlgorithm ga = new SimpleGeneticAlgorithm();
		List<GenerationProperties> algoResult = ga.runAlgorithm(problem, parms.getPopulationSize(), parms.getGenerations(), parms.getUniformRate(), parms.getMutationRate(), parms.getEliteSize(), parms.getMutationType());
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
	
	public static void buildGraph(List<DataSerie> plotData, String graphDescription) throws Exception {
		Plotter plotter = new Plotter();
		String filename = plotter.plot(plotData, graphDescription, "Generations", "BestFitness");
		
		Path fromFile = Paths.get(filename);
		Path toFile = Paths.get("/tmp/algorithm", fromFile.getFileName().toString());
		Files.move(fromFile, toFile, StandardCopyOption.REPLACE_EXISTING);
		
		log.info("Graph file: {}",  toFile.toString());
	}
}
