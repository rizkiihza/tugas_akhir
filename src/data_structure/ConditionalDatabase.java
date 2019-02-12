package data_structure;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

public class ConditionalDatabase {
    public HashSet<Integer> prefix;
    public ArrayList<ArrayList<Predicate>> database;
    public ArrayList<Support> dataClasses;


    public ConditionalDatabase(ArrayList<ArrayList<Predicate>> database, ArrayList<Support> dataClasses) {
        // copy database
        this.database = new ArrayList<>();
        for (ArrayList<Predicate> arrPredicate: database) {
            this.database.add(new ArrayList<>(arrPredicate));
        }

        // copy dataClasses
        this.dataClasses = new ArrayList<>();
        this.dataClasses.addAll(dataClasses);

        // empty prefix
        prefix = new HashSet<>();
    }

    public ConditionalDatabase(GrTree grTree, Integer prefixAddition) {
        this.prefix = new HashSet<>();
        this.prefix.addAll(grTree.prefix);
        this.prefix.add(prefixAddition);

        database = new ArrayList<>();
        dataClasses = new ArrayList<>();

        createDatabase(grTree, prefixAddition);

    }

    private void createDatabase(GrTree grTree, Integer prefixAddition) {
        for (Map.Entry<Integer, GrTree.GrNode> entry: grTree.root.children.entrySet()) {
            GrTree.GrNode child = entry.getValue();
            traverse(child, prefixAddition, new ArrayList<>());
        }
    }

    private void traverse(GrTree.GrNode grNode, Integer prefixAddition, ArrayList<Predicate> path) {
        if (grNode.predicate.id == prefixAddition && path.size() > 0) {
            database.add(new ArrayList<>(path));

            Predicate predicate = grNode.predicate;
            Support support = new Support(predicate.plusSupport, predicate.negativeSupport);

            dataClasses.add(support);
            return;
        }

        for (Map.Entry<Integer, GrTree.GrNode> entry: grNode.children.entrySet()) {
            path.add(grNode.predicate);
            traverse(entry.getValue(), prefixAddition, path);
            path.remove(path.size()-1);
        }
    }

    public void printDatabase() {
        for (int i = 0; i < database.size(); i++) {
            System.out.printf("%s %s\n", Arrays.toString(database.get(i).toArray()), dataClasses.get(i).toString());
        }
    }
}
