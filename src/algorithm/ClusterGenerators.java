package algorithm;

import data_structure.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClusterGenerators {
    public static PredicatedBugSignature ClusterGeneratorToPredicatedBugSignature(ConditionalDatabase conditionalDatabase, GeneratorSet generatorSet) {
        PredicatedBugSignature predicatedBugSignature = new PredicatedBugSignature();

        for (Map.Entry<Support, ArrayList<Generator>> entry: generatorSet.GS.entrySet()) {
            for (Generator generator: entry.getValue()) {
                ArrayList<Integer> tx = TX.countTX(conditionalDatabase, generator.generator);

                if (!predicatedBugSignature.bugSignature.containsKey(tx)) {
                    predicatedBugSignature.bugSignature.put(tx, new ArrayList<>());
                }
                predicatedBugSignature.bugSignature.get(tx).add(generator);
            }
        }

        return predicatedBugSignature;
    }
}
