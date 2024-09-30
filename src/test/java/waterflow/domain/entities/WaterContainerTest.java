package waterflow.domain.entities;

import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.WaterContainer;
import waterflow.domain.exceptions.WaterOverFlowException;
import waterflow.domain.valueobjects.Liter;
import org.junit.Assert;
import org.junit.Test;

public class WaterContainerTest {

    @Test()
    public void shouldNotFill() {
        Liter maxCapacity = new Liter(100d);
        Liter currentVolume = new Liter(99d);
        WaterContainer<Liter> container = new LiterWaterContainer(
                maxCapacity,
                currentVolume
        );

        Assert.assertThrows(
                WaterOverFlowException.class, () -> container.fill(new Liter(100d))
        );

        Assert.assertEquals(
                container.getCurrentVolume().getValue(),
                currentVolume.getValue()
        );
    }

}
