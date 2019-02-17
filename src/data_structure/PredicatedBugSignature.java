package data_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PredicatedBugSignature {
    public HashMap<ArrayList<Integer>, ArrayList<Generator>> bugSignature;

    public PredicatedBugSignature() {
        bugSignature = new HashMap<>();
    }

    public void print() {
        for (Map.Entry<ArrayList<Integer>, ArrayList<Generator>> entry: bugSignature.entrySet()) {
            System.out.print(entry.getKey().toString() + " : ");
            for (Generator g: entry.getValue()) {
                g.print();
            }
            System.out.println();
        }
    }
}
