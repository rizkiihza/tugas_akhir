package algorithm;

import data_structure.*;
import helper.MathHelper;
import helper.SupportCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClusterGenerators {
    public static PredicatedBugSignature ClusterGeneratorToPredicatedBugSignature(ConditionalDatabase fullDatabase, GeneratorSet generatorSet, int k) {
        PredicatedBugSignature predicatedBugSignature = new PredicatedBugSignature();

        for (Map.Entry<Support, ArrayList<Generator>> entry: generatorSet.GS.entrySet()) {
            for (Generator generator: entry.getValue()) {
                ArrayList<Integer> tx = TX.countTX(fullDatabase, generator.generator);

                if (!predicatedBugSignature.bugSignature.containsKey(tx)) {
                    predicatedBugSignature.bugSignature.put(tx, new ArrayList<>());
                }
                predicatedBugSignature.bugSignature.get(tx).add(generator);
            }
        }

        while (predicatedBugSignature.bugSignature.size() > k && k > 0) {
            removeSmallestDS(predicatedBugSignature, fullDatabase);
        }
        return predicatedBugSignature;
    }

    private static void removeSmallestDS(PredicatedBugSignature predicatedBugSignature, ConditionalDatabase fullDatabase) {
        Double minimumDS = -1.0;
        ArrayList<Integer> minimumTransaction = null;

        for (ArrayList<Integer> transaction: predicatedBugSignature.bugSignature.keySet()) {
            Support transactionSupport = SupportCounter.getSuppotOfTransaction(transaction, fullDatabase);
            Support fullDatabaseSupport = fullDatabase.countTotalSupport();
            Double patternDS = MathHelper.discriminativeSignificance(
                transactionSupport.plusSupport,
                transactionSupport.negativeSupport,
                fullDatabaseSupport.plusSupport,
                fullDatabaseSupport.negativeSupport
            );

            if (minimumTransaction == null || patternDS < minimumDS) {
                minimumDS = patternDS;
                minimumTransaction = transaction;
            }
        }

        if (minimumTransaction != null) {
            predicatedBugSignature.bugSignature.remove(minimumTransaction);
        }
    }


}
