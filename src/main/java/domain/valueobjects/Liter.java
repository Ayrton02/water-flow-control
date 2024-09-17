package domain.valueobjects;

import domain.exceptions.NegativeCapacityException;

public class Liter implements Capacity<Liter> {
    private Double value;

    public Liter(Double value) {
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }


    @Override
    public Liter add(Liter liter) {
        this.value += liter.getValue();
        return this;
    }

    @Override
    public Liter subtract(Liter liter) throws NegativeCapacityException {
        if (liter.getValue() > this.value) {
            throw new NegativeCapacityException("negative liter result");
        }

        this.value -= liter.getValue();
        return this;
    }

    @Override
    public void empty() {
        this.value = 0d;
    }

    @Override
    public int compareTo(Liter liter) {
        return this.value.compareTo(liter.getValue());
    }
}
