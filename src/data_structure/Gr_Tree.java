package data_structure;


import constants.PredicateConstants;
import helper.Converter;
import helper.Sorter;
import helper.SupportCounter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Gr_Tree {
    public ArrayList<Predicate> headTable;
    public ArrayList<ArrayList<Predicate>> database;
    public ArrayList<Integer> dataClasses;
    public GrNode root;

    private class GrNode {
        public HashMap<Integer, GrNode> children;
        public Predicate predicate;

        public GrNode(Integer pId) {
            children = new HashMap<>();
            predicate = new Predicate(pId, 0, 0);
        }

        public GrNode(Predicate p) {
            children = new HashMap<>();
            predicate = new Predicate(p.id, p.plus_support, p.negative_support);
        }
    }

    public Gr_Tree(ArrayList<ArrayList<Predicate>> database, ArrayList<Integer> dataClasses) {
        // construct headTable and dataClasses
        ArrayList<Predicate> arrPredicate = SupportCounter.getSupportAllPredicate(database, dataClasses);
        this.dataClasses = dataClasses;
        this.headTable = Sorter.sortArrayBySupport(arrPredicate);

        // construct sorted database
        HashMap<Integer, Support> counter = Converter.convertPredicateArrayToHashMap(arrPredicate);
        Converter.fillDatabaseWithSupport(database, counter);
        this.database = Sorter.generateSortedDatabase(database);

        // construct Trie
        root = new GrNode(new Predicate(-1, -1, -1));
        for (int i = 0; i < this.database.size(); i++) {
            insertToTrie(this.database.get(i), this.dataClasses.get(i));
        }
    }

    public void insertToTrie(ArrayList<Predicate> arr, Integer dataClass) {
        GrNode currentNode = root;
        for (Predicate p: arr) {
            if (!currentNode.children.containsKey(p.id)) {
                currentNode.children.put(p.id, new GrNode(p.id));
            }
            currentNode = currentNode.children.get(p.id);
            if (dataClass == PredicateConstants.PLUS) {
                currentNode.predicate.plus_support += 1;
            } else {
                currentNode.predicate.negative_support += 1;
            }
        }
    }

    public void printHeadTable() {
        if (root == null) {
            return;
        }
        System.out.println(Arrays.toString(headTable.toArray()));
    }

    public void printDatabase() {
        for (ArrayList<Predicate> arrPredicate: database) {
            System.out.println(Arrays.toString(arrPredicate.toArray()));
        }
    }

    public void printTrie() {
        for (Map.Entry<Integer, GrNode> entry: this.root.children.entrySet()) {
            printRecurs(entry.getValue(), new ArrayList<Predicate>());
        }
    }

    private void printRecurs(GrNode gn, ArrayList<Predicate> path) {
        if (gn.children.size() == 0) {
            path.add(gn.predicate);
            System.out.println(Arrays.toString(path.toArray()));
            path.remove(path.size()-1);

            return;
        }
        path.add(gn.predicate);
        for (Map.Entry<Integer, GrNode> entry: gn.children.entrySet()) {
            printRecurs(entry.getValue(), path);
        }
        path.remove(path.size()-1);
    }
}
