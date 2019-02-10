package helper;

import data_structure.Predicate;
import data_structure.Support;

import java.util.ArrayList;
import java.util.HashMap;

public class Converter {
    public static void convertInputToData(String[] input, ArrayList<ArrayList<Predicate>> database,
                                          ArrayList<Integer> dataClasses) {
        for (String s: input) {
            String[] splited_s = s.split("\\s+");
            if (splited_s.length == 1) {
                dataClasses.add(Integer.valueOf(splited_s[0]));
            } else {
                ArrayList<Predicate> arrPredicate = new ArrayList<>();
                for (int i = 0; i < splited_s.length - 1; i++) {
                    arrPredicate.add(new Predicate(Integer.valueOf(splited_s[i])));
                }
                database.add(arrPredicate);
                dataClasses.add(Integer.valueOf(splited_s[splited_s.length - 1]));
            }
        }
    }

    public static HashMap<Integer, Support> convertPredicateArrayToHashMap(ArrayList<Predicate> arrPredicate) {
        HashMap<Integer, Support> counter = new HashMap<>();

        for (Predicate p: arrPredicate) {
            counter.put(p.id, new Support(p.plus_support, p.negative_support));
        }
        return counter;
    }

    public static void fillDatabaseWithSupport(ArrayList<ArrayList<Predicate>> database,
                                               HashMap<Integer, Support> counter) {
        for (ArrayList<Predicate> arrPredicate: database) {
            for (Predicate p: arrPredicate) {
                Support support = counter.get(p.id);
                p.plus_support = support.plus_support;
                p.negative_support = support.negative_support;
            }
        }
    }
}

