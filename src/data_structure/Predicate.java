package data_structure;

public class Predicate {
    public double id;
    public double plus_support;
    public double negative_support;

    public Predicate(double id, double plus_support, double negative_support) {
        this.id = id;
        this.plus_support = plus_support;
        this.negative_support = negative_support;
    }

    @Override
    public String toString() {
        return String.format("(%f,[+%f, -%f])", this.id, this.plus_support, this.negative_support);
    }
}
