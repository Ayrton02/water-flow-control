package waterflow.domain.entities;

import org.junit.Assert;
import org.junit.Test;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

public class WaterFlowSessionTest {

    @Test()
    public void shouldSyncAndUpdateValues() {
        LiterWaterPump pump = new LiterWaterPump(new LiterFlow(new Liter(0.01d), TimeMeasurementUnit.HOURS));
        LiterWaterContainer container = new LiterWaterContainer(new Liter(10000d), new Liter(0d));
        LiterWaterSource source = new LiterWaterSource(new Liter(2000d), new Liter(10d), new Liter(2000d));
        WaterFlowSession session = WaterFlowSession.create(source, container, pump);

        session.start();
        session.sync();
        Assert.assertTrue(session.getWaterContainer().getCurrentVolume().compareTo(new Liter(0d)) > 0);
    }
}
