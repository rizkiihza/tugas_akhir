package algorithm;

import data_structure.*;
import helper.SupportCounter;

import java.util.HashMap;
import java.util.HashSet;

public class MineSignatures {
    public static PredicatedBugSignature mine(ConditionalDatabase conditionalDatabase, ConditionalDatabase fullDatabase, int k, int negSup, int sizeLimit) {
        GrTree grTree = new GrTree(conditionalDatabase);

        GeneratorSet generatorSet= new GeneratorSet();
        MineRec.mineRec(fullDatabase, grTree, k, negSup, sizeLimit, generatorSet);

        generatorSet.print(fullDatabase.countTotalSupport());
        return ClusterGenerators.ClusterGeneratorToPredicatedBugSignature(conditionalDatabase,
                generatorSet, k);
    }
}
