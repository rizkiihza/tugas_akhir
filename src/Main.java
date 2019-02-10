import helper.MathHelper;
import helper.Sorter;
import data_structure.Predicate;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {



    public static void main(String[] args) {

        ArrayList<Predicate> arrPredicate = new ArrayList<>();

        int[][] initiate = {{1, 1, 1}, {2, 0, 1}, {3, 4, 0}, {4, 0, 0}, {5, 2, 2}};

        for (int i = 0; i < initiate.length; i++) {
            arrPredicate.add(new Predicate(initiate[i][0], initiate[i][1], initiate[i][2]));
        }

        ArrayList sortedArrPredicate = Sorter.sortArrayBySupport(arrPredicate);
        System.out.println(Arrays.toString(arrPredicate.toArray()));
        System.out.println(Arrays.toString(sortedArrPredicate.toArray()));
    }
}
