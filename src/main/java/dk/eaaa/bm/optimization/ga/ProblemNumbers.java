package dk.eaaa.bm.optimization.ga;

public class ProblemNumbers {

	private static ProblemNumbers instance = new ProblemNumbers();

	private int noOfevaluations = 0;

	private ProblemNumbers() {
	}

	public static ProblemNumbers getInstance() {
		return instance;
	}

	public void reset() {
		noOfevaluations = 0;
	}

	public int getNoOfEvaluations() {
		return noOfevaluations;
	}

	public void incNumberOfEvaluations() {
		noOfevaluations++;
	}

}
