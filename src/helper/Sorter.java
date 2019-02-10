package helper;

import data_structure.Predicate;
import java.util.ArrayList;

import java.util.Comparator;

public class Sorter {
    public static ArrayList<Predicate> sortArrayBySupport(ArrayList<Predicate> predicateArray) {
        ArrayList<Predicate> copyPredicateArray = new ArrayList<>(predicateArray);
        copyPredicateArray.sort(new Comparator<Predicate>() {
            @Override
            public int compare(Predicate o1, Predicate o2) {
                return ((o2.plus_support + o2.negative_support) - (o1.plus_support + o1.negative_support) );
            }
        });
        return copyPredicateArray;
    }

    public static ArrayList<ArrayList<Predicate>> generateSortedDatabase(ArrayList<ArrayList<Predicate>> database) {
        ArrayList<ArrayList<Predicate>> sortedDatabase = new ArrayList<>();
        for (ArrayList<Predicate> arr: database) {
            sortedDatabase.add(Sorter.sortArrayBySupport(arr));
        }
        return sortedDatabase;
    }
}
