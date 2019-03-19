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

    public static void analyze() {
        ArrayList<ArrayList<Predicate>> database = new ArrayList<>();
        ArrayList<Support> dataClasses = new ArrayList<>();

        String appName = "contoh";

        String filename = String.format("input_%s.txt", appName);
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

        String predicateFileName = String.format("predicate_%s.json", appName);
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

        analyze();
        memoryWatcher.ping();
        long endTime = System.currentTimeMillis();

        System.out.println();
        System.out.printf("Time: %d\n", endTime - startTIme);
        System.out.printf("Memory: %d\n", memoryWatcher.getMaxMemoryUsed());
    }
}
