import math_helper.MathHelper;

public class Main {

    public static void main(String[] args) {
        int plus_size = 4;
        int negative_size = 1;
        int p = 0;
        int n = 1;

        double ds = MathHelper.discriminativeSignificance(p, n, plus_size, negative_size);
        System.out.println(ds);
    }
}
