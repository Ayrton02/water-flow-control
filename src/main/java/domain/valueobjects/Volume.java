package domain.valueobjects;

import domain.exceptions.NegativeCapacityException;

public class Volume implements Capacity<Volume> {
    private Double height;
    private Double length;
    private Double width;
    private Double value;

    public Volume(Double height, Double length, Double width) {
        this.height = height;
        this.length = length;
        this.width = width;
        this.value = calculateVolume();
    }

    private Double calculateVolume() {
        return this.height * this.length * this.width;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public Volume add(Volume volume) {
        this.value += volume.getValue();
        return this;
    }

    @Override
    public Volume subtract(Volume volume) throws NegativeCapacityException {
        if (volume.getValue() > this.value) {
            throw new NegativeCapacityException("negative volume");
        }

        this.value -= volume.getValue();
        return this;
    }

    @Override
    public void empty() {
        this.height = 0d;
        this.length = 0d;
        this.width = 0d;
        this.value = calculateVolume();
    }
}
