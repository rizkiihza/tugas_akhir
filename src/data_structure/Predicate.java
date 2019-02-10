package data_structure;

public class Predicate {
    public int id;
    public int plus_support;
    public int negative_support;

    public Predicate(int id) {
        this.id = id;
        this.plus_support = -1;
        this.negative_support = -1;
    }

    public boolean isHasSupport() {
        return this.plus_support != -1 && this.negative_support != -1;
    }

    public Predicate(int id, int plus_support, int negative_support) {
        this.id = id;
        this.plus_support = plus_support;
        this.negative_support = negative_support;
    }

    @Override
    public String toString() {
        return String.format("(%d,[%d, %d])", this.id, this.plus_support, this.negative_support);
    }
}
