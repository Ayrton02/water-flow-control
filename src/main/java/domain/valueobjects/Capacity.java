package domain.valueobjects;

import domain.exceptions.NegativeCapacityException;

public interface Capacity<T> extends Comparable<T> {
    T add(T quantity);
    T subtract(T quantity) throws NegativeCapacityException;
    void empty();
    Double getValue();
}
