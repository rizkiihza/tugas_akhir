import algorithm.MineSignatures;
import data_structure.*;
import helper.Converter;
import helper.SupportCounter;
import jdk.nashorn.api.tree.ConditionalExpressionTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void testDB() {
        ArrayList<ArrayList<Predicate>> database = new ArrayList<>();
        ArrayList<Support> dataClasses = new ArrayList<>();

        String[] inputs = {
                "2 1",
                "4 12 1",
                "4 7 9 14 11 1",
                "4 7 9 14 16 1",
                "4 7 9 11 16 -1"
        };

        Converter.convertInputToData(inputs, database, dataClasses);
        GrTree grTree = new GrTree(database, dataClasses);

        System.out.println("grTree");
        grTree.printHeadTable();
        System.out.println();
        grTree.printTrie();

        System.out.println("Conditional database");
        ConditionalDatabase conditionalDatabase = new ConditionalDatabase(grTree, 16);
        System.out.println();
        conditionalDatabase.printDatabase();
        System.out.println();

        System.out.println("second grTree");
        grTree = new GrTree(conditionalDatabase);
        grTree.printHeadTable();
        System.out.println();
        grTree.printTrie();

        System.out.println("second Conditional Database");
        conditionalDatabase = new ConditionalDatabase(grTree, 14);
        System.out.println();
        conditionalDatabase.printDatabase();
        System.out.println();

        System.out.println("third grTree");
        grTree = new GrTree(conditionalDatabase);
        grTree.printHeadTable();
        System.out.println();
        grTree.printTrie();
    }

    public static void testHashSet() {
        HashSet<ArrayList<Integer>> integerSet = new HashSet<>();
        int[] array = {1,2,3,4,5};
        ArrayList<Integer> control = new ArrayList<>();
        for (int i: array) {
            control.add(i);
        }
        integerSet.add(control);

        int[] array_test = {1,2,3,4,5};
        ArrayList<Integer> test = new ArrayList<>();
        for (int i: array_test) {
            test.add(i);
        }

        System.out.printf("%b\n", integerSet.contains(test));
    }

    public static void testConditionalDB() {
        ArrayList<ArrayList<Predicate>> database = new ArrayList<>();
        ArrayList<Support> dataClasses = new ArrayList<>();

        String[] inputs = {
                "2 1",
                "4 12 1",
                "4 7 9 14 11 1",
                "4 7 9 14 16 1",
                "4 7 9 11 16 -1"
        };

        Converter.convertInputToData(inputs, database, dataClasses);

        ConditionalDatabase conditionalDatabase = new ConditionalDatabase(database, dataClasses);
        System.out.println();
        conditionalDatabase.printDatabase();
        System.out.println();

        PredicatedBugSignature predicatedBugSignature = MineSignatures.mine(conditionalDatabase, conditionalDatabase,5, 0, 100);
        System.out.println();
        predicatedBugSignature.print(conditionalDatabase);
    }


    public static void main(String[] args) {
        testConditionalDB();

    }
}
