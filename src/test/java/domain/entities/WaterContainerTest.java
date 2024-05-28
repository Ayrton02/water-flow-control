package domain.entities;

import domain.exceptions.WaterOverFlowException;
import domain.valueobjects.UUID;
import domain.valueobjects.Volume;
import org.junit.Assert;
import org.junit.Test;

public class WaterContainerTest {

    @Test()
    public void shouldNotFill() {
        Volume maxCapacity = new Volume(100d, 100d, 100d);
        Volume currentCapacity = new Volume(99d, 99d, 99d);
        WaterContainer container = WaterContainer.create(
                maxCapacity,
                currentCapacity
        );

        Assert.assertThrows(
                WaterOverFlowException.class, () -> {
                    container.fill(new Volume(100d, 100d, 100d));
                }
        );

        Assert.assertEquals(
                container.getCurrentCapacity().getValue(),
                currentCapacity.getValue()
        );
    }

}
