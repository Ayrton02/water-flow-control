package domain.valueobjects;

import domain.exceptions.NegativeVolumeException;

public interface Volume<T> extends Comparable<T> {
    T add(T quantity);
    T subtract(T quantity) throws NegativeVolumeException;
    Double getValue();
}
