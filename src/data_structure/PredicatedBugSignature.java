package data_structure;

import helper.MathHelper;
import helper.SupportCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PredicatedBugSignature {
    public HashMap<ArrayList<Integer>, ArrayList<Generator>> bugSignature;

    public PredicatedBugSignature() {
        bugSignature = new HashMap<>();
    }

    public void print(ConditionalDatabase fullDatabase) {
        Support fullDatabaseSupport = fullDatabase.countTotalSupport();

        for (ArrayList<Integer> transaction: bugSignature.keySet()) {
            Support transactionSupport = SupportCounter.getSuppotOfTransaction(transaction, fullDatabase);

            Double ds = MathHelper.discriminativeSignificance(
                    transactionSupport.plusSupport,
                    transactionSupport.negativeSupport,
                    fullDatabaseSupport.plusSupport,
                    fullDatabaseSupport.negativeSupport
            );

            System.out.printf("%s: %.4f %s\n", transaction.toString(), ds, bugSignature.get(transaction).toString());
        }
    }

    public void print() {
        for (Map.Entry<ArrayList<Integer>, ArrayList<Generator>> entry: bugSignature.entrySet()) {

            System.out.printf("%s : %s \n", entry.getKey().toString(), entry.getValue().toString());
        }
    }
}
