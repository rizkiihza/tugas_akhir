package algorithm;

import data_structure.*;

import java.util.HashMap;
import java.util.HashSet;

public class MineSignatures {
    public static PredicatedBugSignature mine(ConditionalDatabase conditionalDatabase, int k, int neg_sup, int size_limit) {
        GrTree grTree = new GrTree(conditionalDatabase);

        GeneratorSet generatorSet= new GeneratorSet();
        MineRec.mineRec(grTree, k, neg_sup, size_limit, generatorSet);

        return ClusterGenerators.ClusterGeneratorToPredicatedBugSignature(conditionalDatabase,
                generatorSet);
    }
}
