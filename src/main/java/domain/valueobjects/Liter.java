package domain.valueobjects;

import domain.exceptions.NegativeVolumeException;

public class Liter implements Volume<Liter> {
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
    public Liter subtract(Liter liter) throws NegativeVolumeException {
        if (liter.getValue() > this.value) {
            throw new NegativeVolumeException("negative liter result");
        }

        this.value -= liter.getValue();
        return this;
    }

    @Override
    public int compareTo(Liter liter) {
        return this.value.compareTo(liter.getValue());
    }
}
