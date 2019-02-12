package data_structure;

import constants.PredicateConstants;

public class Support {
    public int plusSupport;
    public int negativeSupport;

    public Support(int plusSupport, int negativeSupport) {
        this.plusSupport = plusSupport;
        this.negativeSupport = negativeSupport;
    }

    public Support(int dataClass) {
        if (dataClass == PredicateConstants.PLUS) {
            this.plusSupport = 1;
            this.negativeSupport = 0;
        } else {
            this.plusSupport = 0;
            this.negativeSupport = 1;
        }
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", plusSupport, negativeSupport);
    }
}
