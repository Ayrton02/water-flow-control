package waterflow.domain.entities;

import core.valueobjects.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

import static org.mockito.Mockito.mockStatic;

public class WaterFlowSessionTest {

    @Test()
    public void shouldSyncAndUpdateValues() {
        LiterWaterPump pump = new LiterWaterPump(new LiterFlow(new Liter(1d), TimeMeasurementUnit.SECONDS));
        LiterWaterContainer container = new LiterWaterContainer(new Liter(10000d), new Liter(0d));
        LiterWaterSource source = new LiterWaterSource(new Liter(2000d), new Liter(10d), new Liter(2000d));
        WaterFlowSession session = WaterFlowSession.create(source, container, pump);

        DateTime firstTime = DateTime.parse("2024-10-09T21:00:00");
        DateTime secondTime = DateTime.parse("2024-10-09T21:00:05");

        try (MockedStatic<DateTime> mockedDateTime = mockStatic(DateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mockedDateTime.when(DateTime::now).thenReturn(firstTime);
            session.start();

            mockedDateTime.when(DateTime::now).thenReturn(secondTime);

            session.sync();
            Assert.assertTrue(session.getWaterContainer().getCurrentVolume().compareTo(new Liter(0d)) > 0);
        }

    }
}
