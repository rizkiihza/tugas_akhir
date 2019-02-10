package helper;

import data_structure.Predicate;

import java.util.ArrayList;

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
}
