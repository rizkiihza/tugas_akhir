import algorithm.MineSignatures;
import data_structure.*;
import helper.Converter;
import helper.FileReader;
import helper.JsonReaderHelper;
import helper.MemoryWatcher;

import java.util.ArrayList;
import java.util.HashMap;
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

        String filename = "input2.txt";
        String filePath = System.getProperty("user.dir") + "/input/" + filename;
        System.out.println(filePath);

        String[] inputs = FileReader.read(filePath);

        Converter.convertInputToData(inputs, database, dataClasses);

        ConditionalDatabase fullDatabase = new ConditionalDatabase(database, dataClasses);
        System.out.println();
        fullDatabase.printDatabase();
        System.out.println();

        PredicatedBugSignature predicatedBugSignature = MineSignatures.mine(fullDatabase, fullDatabase,5, 0, 100);
        System.out.println();
        predicatedBugSignature.addBugSignatureToDSPairs(fullDatabase);
        predicatedBugSignature.print();

        String predicateFileName = "predicate.json";
        String predicateFilePath = System.getProperty("user.dir") + "/input/" + predicateFileName;

        HashMap<Integer, String> predicateDictionary = JsonReaderHelper.readPredicateDictionary(predicateFilePath);

        System.out.println();
        for (Integer i: predicateDictionary.keySet()) {
            System.out.printf("%d : %s\n", i, predicateDictionary.get(i));
        }
    }


    public static void main(String[] args) {
        // init watcher
        MemoryWatcher memoryWatcher = MemoryWatcher.getInstance();
        long startTIme = System.currentTimeMillis();

        testConditionalDB();
        memoryWatcher.ping();
        long endTime = System.currentTimeMillis();

        System.out.println();
        System.out.printf("Time: %d\n", endTime - startTIme);
        System.out.printf("Memory: %d\n", memoryWatcher.getMaxMemoryUsed());
    }
}
