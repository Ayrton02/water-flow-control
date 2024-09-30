package waterflow.domain.entities;

import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.exceptions.SafetyThresholdException;
import waterflow.domain.valueobjects.Liter;
import org.junit.Assert;
import org.junit.Test;

public class WaterSourceTest {

    @Test
    public void shouldNotDumpBecauseSafetyThresholdIsLow() {
        Liter currentVolume = new Liter(20d);
        LiterWaterSource source = new LiterWaterSource(
                new Liter(100d),
                new Liter(30d),
                currentVolume
        );

        Assert.assertThrows(
                SafetyThresholdException.class, () -> source.dump(new Liter(20d))
        );

        Assert.assertEquals(
                source.getCurrentVolume().getValue(),
                currentVolume.getValue()
        );
    }
}
