import data_structure.ConditionalDatabase;
import data_structure.GrTree;
import data_structure.Support;
import helper.Converter;
import data_structure.Predicate;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
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

        grTree.printHeadTable();
        System.out.println();
        grTree.printTrie();

        ConditionalDatabase conditionalDatabase = new ConditionalDatabase(grTree, 16);
        System.out.println();
        conditionalDatabase.printDatabase();
        System.out.println();

        grTree = new GrTree(conditionalDatabase);
        grTree.printHeadTable();
        System.out.println();
        grTree.printTrie();
    }
}
