package algorithm;

import data_structure.*;

import java.util.ArrayList;

public class MineRec {
    public static void mineRec(GrTree grTree, int k, int neg_sup, int size_limit, GeneratorSet GS) {
        mineRecMock(grTree, GS);
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
