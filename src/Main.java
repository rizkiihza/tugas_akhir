import helper.Converter;
import helper.MathHelper;
import helper.Sorter;
import data_structure.Predicate;
import helper.SupportCounter;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Predicate>> database = new ArrayList<>();
        ArrayList<Integer> dataClasses = new ArrayList<>();

        String[] inputs = {
                "2 1",
                "4 12 1",
                "4 7 9 14 11 1",
                "4 7 9 14 16 1",
                "4 7 9 11 16 -1"
        };

        Converter.convertInputToData(inputs, database, dataClasses);

        for (int i = 0; i < database.size(); i++) {
            System.out.printf("%s, %d\n", Arrays.toString(database.get(i).toArray()), dataClasses.get(i));
        }

        ArrayList<Predicate> arrPredicate = SupportCounter.getSupportAllPredicate(database, dataClasses);
        System.out.println();
        for (Predicate p: arrPredicate) {
            System.out.println(p);
        }

        System.out.println();
        arrPredicate = Sorter.sortArrayBySupport(arrPredicate);
        for (Predicate p: arrPredicate) {
            System.out.println(p);
        }
    }
}
