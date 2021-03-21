package domain;

import java.util.Objects;

public class Pair<F, S> {
    private F first; //first member of pair
    private S second; //second member of pair

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Sets the first item.
     *
     * @param first the new first element of the pair
     */
    public void setFirst(F first) {
        this.first = first;
    }

    /**
     * Sets the second item.
     *
     * @param second the new second element of the pair
     */
    public void setSecond(S second) {
        this.second = second;
    }

    /**
     * Gets the first element of the pair.
     *
     * @return the first item
     */
    public F getFirst() {
        return first;
    }

    /**
     * Gets the second element of the pair.
     *
     * @return the second item
     */
    public S getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "(" + this.first + "," + this.second + ")";
    }
}