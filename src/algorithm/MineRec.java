package algorithm;

import data_structure.*;
import helper.MathHelper;

import java.util.ArrayList;

public class MineRec {
    public static void mineRec(ConditionalDatabase fullDatabase, GrTree grTree, int k, int negSup, int sizeLimit, GeneratorSet GS) {
        Support fullDatabaseSupport = fullDatabase.countTotalSupport();
        if (grTree.headTable.size() == 0) {
            return;
        }

        // update result
        for (Predicate p: grTree.headTable) {
            ArrayList<Integer> newPrefix = new ArrayList<>(grTree.prefix);
            newPrefix.add(p.id);

            Support patternSupport = fullDatabase.countSupportOfAPattern(newPrefix);

//            System.out.printf("%s: %s\n", newPrefix.toString(), patternSupport.toString());
            updateResult(GS, k, patternSupport, fullDatabaseSupport, newPrefix);
        }

//        System.out.println("GS");
//        GS.print(fullDatabaseSupport);
//        System.out.println();
        if (grTree.prefix.size() + 1 == sizeLimit) {
            return;
        }

//        System.out.println("GrTree head table");
//        System.out.println(grTree.headTable);
//        System.out.println();
        if (grTree.headTable.size() <= 1) {
            return;
        }

        for (Predicate p: grTree.headTable) {
            ArrayList<Integer> newPrefix = new ArrayList<>(grTree.prefix);
            newPrefix.add(p.id);

//            System.out.println(newPrefix.toString());

            ConditionalDatabase newDatabase = new ConditionalDatabase(grTree, p.id);
//            System.out.println("Conditional Database");
            newDatabase.removeItemByNegativeSupport(negSup);

            GrTree newGrTree = new GrTree(newDatabase);


            mineRec(fullDatabase, newGrTree, k, negSup, sizeLimit, GS);
//            newDatabase.printDatabase();
//            System.out.println("GrTree");
//            newGrTree.printHeadTable();
//            System.out.println("Trie");
//            newGrTree.printTrie();
//            System.out.println();
        }
    }

    private static void updateResult(GeneratorSet GS, int k, Support patternSupport, Support fullDatabaseSupport, ArrayList<Integer> newPrefix) {
        Generator generator = new Generator(newPrefix);
        if (GS.GS.containsKey(patternSupport)) {
            GS.GS.get(patternSupport).add(generator);
        }
        else  {
            GS.GS.put(patternSupport, new ArrayList<>());
            GS.GS.get(patternSupport).add(generator);
        }

        if (GS.GS.keySet().size() > k && k > 0) {
            removeMinDSFromGeneratorSet(GS, fullDatabaseSupport);
        }
    }

    private static void removeMinDSFromGeneratorSet(GeneratorSet GS, Support fullDatabaseSupport) {
        Support minimumSupport = GS.getMinimumDSSupport(fullDatabaseSupport);

        if (minimumSupport != null) {
            GS.GS.remove(minimumSupport);
        }
    }


    public static void mineRecMock(GrTree grTree, GeneratorSet GS) {
        Support support = new Support(1, 0);
        int[] arr1 = {4, 12};

        int[] arr2 = {2};

        int[] arr3 = {4,7,9,14,11};

        int[] arr4 = {4,7,9,14,16};

        if (!GS.GS.containsKey(support)) {
            GS.GS.put(new Support(1, 0), new ArrayList<>());
        }

        GS.GS.get(support).add(new Generator(arr1));
        GS.GS.get(support).add(new Generator(arr2));
        GS.GS.get(support).add(new Generator(arr3));
        GS.GS.get(support).add(new Generator(arr4));

        support = new Support(2, 0);

        int[] arr5 = {4,7,9,14};

        if (!GS.GS.containsKey(support)) {
            GS.GS.put(support, new ArrayList<>());
        }

        GS.GS.get(support).add(new Generator(arr5));
    }
}
