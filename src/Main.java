import algorithm.MineSignatures;
import data_structure.*;
import helper.Converter;
import helper.FileReader;
import helper.JsonReaderHelper;
import helper.MemoryWatcher;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static PredicatedBugSignature analyze(String filePath, Integer k, Integer negSup, Double DSLimit, Integer sizeLimit) {
        ArrayList<ArrayList<Predicate>> database = new ArrayList<>();
        ArrayList<Support> dataClasses = new ArrayList<>();

        System.out.println(filePath);

        String[] inputs = FileReader.read(filePath);

        Converter.convertInputToData(inputs, database, dataClasses);

        ConditionalDatabase fullDatabase = new ConditionalDatabase(database, dataClasses);
        System.out.println();
        //fullDatabase.printDatabase();
        System.out.println();

        PredicatedBugSignature predicatedBugSignature = MineSignatures.mine(fullDatabase, fullDatabase, k, negSup, sizeLimit);
        System.out.println();
        predicatedBugSignature.addBugSignatureToDSPairs(fullDatabase);
        predicatedBugSignature.clean(DSLimit);
        //predicatedBugSignature.print();

        return predicatedBugSignature;
    }

    public static void predicateList(String filePath) {

        HashMap<Integer, String> predicateDictionary = JsonReaderHelper.readPredicateDictionary(filePath);

        System.out.println();
        for (Integer i: predicateDictionary.keySet()) {
            System.out.printf("%d : %s\n", i, predicateDictionary.get(i));
        }
    }

    public static Double accuracyCounter(String filepath, PredicatedBugSignature predicatedBugSignature) {
        String[] bugPredicateInput= FileReader.readBugPredicates(filepath);

        HashSet<Integer> bugPredicates = Converter.convertBugPredicateInputToHashSet(bugPredicateInput);


        Integer truePredict = 0;
        Integer wrongPredict = 0;

        for (BugSignatureDSPair pair: predicatedBugSignature.bugSignatureDSPairs) {
            for (Generator generator: pair.generators) {
                for (Integer predicate: generator.generator) {
                    if (bugPredicates.contains(predicate)) {
                        truePredict += 1;
                    } else {
                        wrongPredict += 1;
                    }
                }
            }
        }


        if (truePredict == 0 && wrongPredict == 0) {
            return 0.0;
        }
        return Double.valueOf(truePredict) / (Double.valueOf(truePredict) + Double.valueOf(wrongPredict)) ;
    }

    public static void main(String[] args) {
        // init watcher
        MemoryWatcher memoryWatcher = MemoryWatcher.getInstance();
        long startTIme = System.currentTimeMillis();

        if (args.length < 3) {
            System.out.println("needed 3 arguments for type, file1, and file2");
            System.exit(0);
        }
        String type = args[0];
        String file1 = args[1];
        String file2 = args[2];

        PredicatedBugSignature predicatedBugSignature;

        Double DSLimit = 0.01;
        Integer sizeLimit = 2;
        Integer k = 25;


        System.out.printf("config: -k: %d, -dslimit: %f, -sizeLimit: %d\n", k, DSLimit, sizeLimit);
        Integer numberOfPredicate = FileReader.getNumberOfPredicate(file1);
        System.out.printf("Number of predicate: %d\n", numberOfPredicate);
        System.out.println();
        if ("-s".equals(type)) {
            predicatedBugSignature = analyze(file1, k, 0, DSLimit, sizeLimit);
            Double result = accuracyCounter(file2, predicatedBugSignature);
            System.out.println();
            System.out.printf("Accuracy: %f\n", result);
        } else if ("-r".equals(type)) {
            analyze(file1, k, 0, DSLimit, sizeLimit);
            predicateList(file2);
        }

        memoryWatcher.ping();
        long endTime = System.currentTimeMillis();

        System.out.println();


        System.out.printf("Time: %d\n", endTime - startTIme);
        System.out.printf("Memory: %d\n", memoryWatcher.getMaxMemoryUsed());
    }
}
