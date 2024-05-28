package domain.valueobjects;

import domain.exceptions.NegativeCapacityException;

public class Liter implements Capacity<Liter> {
    private Double value;

    public Liter(Double value) {
        this.value = value;
    }

    @Override
    public Liter add(Liter quantity) {
        this.value += quantity.getValue();
        return this;
    }

    @Override
    public Liter subtract(Liter quantity) throws NegativeCapacityException {
        if (quantity.getValue() > this.value) {
            throw new NegativeCapacityException("negative liter");
        }
        return this;
    }

    @Override
    public Double getValue() {
        return this.value;
    }

    @Override
    public void empty() {
        this.value = 0d;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
