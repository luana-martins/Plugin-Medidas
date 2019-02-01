package plugin.metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import plugin.persistences.Dependency;
import plugin.persistences.MetricsInformation;

public class LackOfConcernBasedCohesionLCC extends Metrics {

	public LackOfConcernBasedCohesionLCC(HashMap<String, ArrayList<Dependency>> codeFragments) {
		super(codeFragments);
		metricFeature = new ArrayList<MetricsInformation>();
		calculate();

		metricSystem.add(new MetricsInformation("Lack of Concern-based Cohesion (LCC)", metricFeature, Node.NON_LEAF,
				Propagation.AVERAGE));
	}

	public void calculate() {

		// one feature
		for (Entry<String, ArrayList<Dependency>> feature : code.entrySet()) {
			ArrayList<String> useFeatures = new ArrayList<>();
			useFeatures.add(feature.getKey());
			// check if it uses componentes from other features
			for (Entry<String, ArrayList<Dependency>> feature2 : code.entrySet()) {
				if (!feature.getKey().equals(feature2.getKey())) {
					for (int i = 0; i < feature.getValue().size(); i++) {
						for (int j = 0; j < feature.getValue().get(i).getDependencias().size(); j++) {

							for (int k = 0; k < feature2.getValue().size(); k++) {
								if (feature.getValue().get(i).getDependencias().get(j)
										.equals(feature2.getValue().get(k).getNewClassName())) {
									if (!useFeatures.contains(feature2.getKey())) {
										useFeatures.add(feature2.getKey());
									}
								}
							}

						}
					}
				}
			}
			System.out.println("\n\nFEATURE: " + feature.getKey());
			System.out.println("LCC: " + Arrays.toString(useFeatures.toArray()));
		}
	}
}
