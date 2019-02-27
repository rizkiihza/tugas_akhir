package data_structure;

import helper.MathHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneratorSet {
    public HashMap<Support, ArrayList<Generator>> GS;

    public GeneratorSet() {
        GS = new HashMap<>();
    }

    public Support getMinimumDSSupport(Support fullDatabaseSupport) {
        Double minDS = -1.0;
        Support minimumSupport = null;

        int fullPositiveSupport = fullDatabaseSupport.plusSupport;
        int fullNegativeSupport = fullDatabaseSupport.negativeSupport;

        for (Support support: GS.keySet()) {
            Double currentDS = MathHelper.discriminativeSignificance(support.plusSupport, support.negativeSupport, fullPositiveSupport, fullNegativeSupport);
            if (minimumSupport == null || currentDS < minDS) {
                minDS = currentDS;
                minimumSupport = support;
            }
        }

        return minimumSupport;
    }
}
