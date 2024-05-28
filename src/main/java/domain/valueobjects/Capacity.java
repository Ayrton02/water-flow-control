package domain.valueobjects;

import domain.exceptions.NegativeCapacityException;

public interface Capacity<T> {
    Capacity<T> add(T quantity);
    Capacity<T> subtract(T quantity) throws NegativeCapacityException;
    void empty();
    Double getValue();
}
