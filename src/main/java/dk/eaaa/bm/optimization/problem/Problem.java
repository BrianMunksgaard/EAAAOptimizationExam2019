package dk.eaaa.bm.optimization.problem;

import java.util.ArrayList;

public interface Problem {

	public double eval(ArrayList<Double> paramVals);

	public int getDimensions();

	public void setMaxValues(ArrayList<Double> maxVals);

	public void setMinValues(ArrayList<Double> minVals);

	public ArrayList<Double> getMaxValues();

	public ArrayList<Double> getMinValues();
}
