package plugin.metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.jdt.core.JavaModelException;

import plugin.persistences.Dependency;
import plugin.persistences.MetricsInformation;

public class NO_NumberOfOperations extends Metrics {

	public NO_NumberOfOperations(HashMap<String, ArrayList<Dependency>> code) throws JavaModelException {
		super(code);
		metricFeature = new ArrayList<MetricsInformation>();
		calculate();
		metricSystem.add(
				new MetricsInformation("Number of Operations (NO)", metricFeature, Node.NON_LEAF, Propagation.SUM));
	}

	public void calculate() {
		for (Entry<String, ArrayList<Dependency>> feature : code.entrySet()) {
			metricComponent = new ArrayList<MetricsInformation>();
			for (int i = 0; i < feature.getValue().size(); i++) {
				metricComponent.add(new MetricsInformation(feature.getValue().get(i).getClasseName(),
						feature.getValue().get(i).getMethods(), Node.LEAF));
			}
			metricFeature
					.add(new MetricsInformation(feature.getKey(), metricComponent, Node.NON_LEAF, Propagation.SUM));
		}
	}
}
