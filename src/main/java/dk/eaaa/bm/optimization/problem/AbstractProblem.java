package dk.eaaa.bm.optimization.problem;

import java.util.ArrayList;

public abstract class AbstractProblem implements Problem {
	
	private ArrayList<Double> maxValues;
	private ArrayList<Double> minValues;

	public AbstractProblem() {
		maxValues = new ArrayList<>();
		minValues = new ArrayList<>();
	}

	public abstract double eval(ArrayList<Double> paramVals);

	public abstract int getDimensions();

	public void setMaxValues(ArrayList<Double> maxVals) {
		maxValues = maxVals;
	}

	public void setMinValues(ArrayList<Double> minVals) {
		minValues = minVals;
	}

	public ArrayList<Double> getMaxValues() {
		return maxValues;
	}

	public ArrayList<Double> getMinValues() {
		return minValues;
	}
}
