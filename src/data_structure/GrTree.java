package data_structure;


import constants.PredicateConstants;
import helper.Converter;
import helper.Sorter;
import helper.SupportCounter;

import java.util.*;

public class GrTree {
    public ArrayList<Predicate> headTable;
    public HashSet<Integer> prefix;
    public GrNode root;

    public class GrNode {
        public HashMap<Integer, GrNode> children;
        public Predicate predicate;

        public GrNode(Integer pId) {
            children = new HashMap<>();
            predicate = new Predicate(pId, 0, 0);
        }

        public GrNode(Predicate p) {
            children = new HashMap<>();
            predicate = new Predicate(p.id, p.plusSupport, p.negativeSupport);
        }
    }

    public GrTree(ArrayList<ArrayList<Predicate>> database, ArrayList<Support> dataClasses) {
        HashSet<Integer> prefix = new HashSet<>();
        createGrTree(database, dataClasses, prefix);
    }

    public GrTree(ArrayList<ArrayList<Predicate>> database, ArrayList<Support> dataClasses, HashSet<Integer> prefix) {
        createGrTree(database, dataClasses, prefix);
    }

    public GrTree(ConditionalDatabase conditionalDatabase) {
        createGrTree(conditionalDatabase.database, conditionalDatabase.dataClasses, conditionalDatabase.prefix);
    }

    private void createGrTree(ArrayList<ArrayList<Predicate>> database, ArrayList<Support> dataClasses, HashSet<Integer> prefix) {
        // construct headTable and dataClasses
        ArrayList<Predicate> arrPredicate = SupportCounter.getSupportAllPredicate(database, dataClasses);
        this.headTable = Sorter.sortArrayBySupport(arrPredicate);

        // construct sorted database
        HashMap<Integer, Support> counter = Converter.convertPredicateArrayToHashMap(arrPredicate);
        Converter.fillDatabaseWithSupport(database, counter);
        ArrayList<ArrayList<Predicate>> sortedDatabase= Sorter.generateSortedDatabase(database);

        // construct Trie
        root = new GrNode(new Predicate(-1, -1, -1));
        for (int i = 0; i < sortedDatabase.size(); i++) {
            insertToTrie(sortedDatabase.get(i), dataClasses.get(i));
        }

        // construct prefix
        this.prefix = new HashSet<>();
        this.prefix.addAll(prefix);
    }

    public void insertToTrie(ArrayList<Predicate> arr, Support dataClass) {
        GrNode currentNode = root;
        for (Predicate p: arr) {
            if (!currentNode.children.containsKey(p.id)) {
                currentNode.children.put(p.id, new GrNode(p.id));
            }
            currentNode = currentNode.children.get(p.id);
            currentNode.predicate.plusSupport += dataClass.plusSupport;
            currentNode.predicate.negativeSupport += dataClass.negativeSupport;
        }
    }

    public void printHeadTable() {
        if (root == null) {
            return;
        }
        System.out.println(Arrays.toString(headTable.toArray()));
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
