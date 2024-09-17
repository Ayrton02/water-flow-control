package domain.entities;

import domain.exceptions.WaterOverFlowException;
import domain.valueobjects.Liter;
import org.junit.Assert;
import org.junit.Test;

public class WaterContainerTest {

    @Test()
    public void shouldNotFill() {
        Liter maxCapacity = new Liter(100d);
        Liter currentCapacity = new Liter(99d);
        WaterContainer<Liter> container = new LiterWaterContainer(
                maxCapacity,
                currentCapacity
        );

        Assert.assertThrows(
                WaterOverFlowException.class, () -> container.fill(new Liter(100d))
        );

        Assert.assertEquals(
                container.getCurrentVolume().getValue(),
                currentCapacity.getValue()
        );
    }

}
