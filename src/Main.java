import data_structure.Gr_Tree;
import data_structure.Support;
import helper.Converter;
import helper.MathHelper;
import helper.Sorter;
import data_structure.Predicate;
import helper.SupportCounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
        Gr_Tree grTree = new Gr_Tree(database, dataClasses);

        grTree.printHeadTable();
        System.out.println();
        grTree.printDatabase();
        System.out.println();
        grTree.printTrie();
    }
}
