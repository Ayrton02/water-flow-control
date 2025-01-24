package waterflow.domain.entities;

import core.valueobjects.DateTime;
import core.valueobjects.UUID;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import user.domain.entities.User;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.exceptions.ContainerAlreadyFullException;
import waterflow.domain.exceptions.SafetyThresholdException;
import waterflow.domain.exceptions.WaterFlowSessionException;
import waterflow.domain.exceptions.WaterPumpInUseException;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

import static org.mockito.Mockito.mockStatic;

public class WaterFlowSessionTest {

    @Test()
    public void shouldSyncAndUpdateValues() {
        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(new Liter(1d), TimeMeasurementUnit.SECONDS),
            false
        );
        LiterWaterContainer container = new LiterWaterContainer(new Liter(10000d), new Liter(0d));
        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(10d),
            new Liter(2000d)
        );
        User user = new User("Zezim", "000.000.000-00");
        WaterFlowSession session = WaterFlowSession.create(source, container, pump, user.getId());

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

    @Test()
    public void shouldSyncAndCompleteWithError() {
        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(new Liter(1d), TimeMeasurementUnit.SECONDS),
            false
        );
        LiterWaterContainer container = new LiterWaterContainer(new Liter(10000d), new Liter(0d));
        LiterWaterSource source = new LiterWaterSource(
            new Liter(200d),
            new Liter(100d),
            new Liter(100d)
        );
        User user = new User("Zezim", "000.000.000-00");
        WaterFlowSession session = WaterFlowSession.create(source, container, pump, user.getId());

        DateTime firstTime = DateTime.parse("2024-10-09T21:00:00");
        DateTime secondTime = DateTime.parse("2024-10-09T21:10:00");

        try (MockedStatic<DateTime> mockedDateTime = mockStatic(DateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mockedDateTime.when(DateTime::now).thenReturn(firstTime);
            session.start();

            mockedDateTime.when(DateTime::now).thenReturn(secondTime);

            session.sync();
            Assert.assertEquals("COMPLETED_WITH_ERROR", session.getStatus());
        }

    }

    @Test
    public void shouldStartSession() {
        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(
                new Liter(1d),
                TimeMeasurementUnit.SECONDS
            ),
            false
        );

        LiterWaterContainer container = new LiterWaterContainer(
            new Liter(10000d),
            new Liter(0d)
        );

        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(100d),
            new Liter(1000d)
        );
        User user = new User(
            "Zezim",
            "000.000.000-00"
        );

        WaterFlowSession session = new WaterFlowSession(
            UUID.generate(),
            pump,
            source,
            container,
            user.getId(),
            null,
            null,
            "OFF"
        );

        session.start();

        Assert.assertEquals(session.getStatus(), "ON");
        Assert.assertNotNull(session.getStartedAt());
        Assert.assertTrue(pump.isActive());
        Assert.assertTrue(session.getFinishedAt().isEmpty());
    }

    @Test
    public void shouldNotStartBecauseStatusIsNotOff() {
        String[] statuses = {"PAUSED", "ON", "COMPLETED", "ERRORED", "COMPLETED_WITH_ERROR"};

        for (String status: statuses) {
            LiterWaterPump pump = new LiterWaterPump(
                new LiterFlow(
                    new Liter(1d),
                    TimeMeasurementUnit.SECONDS
                ),
                false
            );

            LiterWaterContainer container = new LiterWaterContainer(
                new Liter(10000d),
                new Liter(0d)
            );

            LiterWaterSource source = new LiterWaterSource(
                new Liter(2000d),
                new Liter(10d),
                new Liter(2000d)
            );

            User user = new User("Zezim", "000.000.000-00");

            WaterFlowSession session = new WaterFlowSession(
                UUID.generate(),
                pump,
                source,
                container,
                user.getId(),
                null,
                null,
                status
            );

            Assert.assertThrows(
                WaterFlowSessionException.class, session::start
            );
        }
    }

    @Test
    public void shouldNotStartBecauseSourceVolumeIsLowerThanSafetyThreshold() {
        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(
                new Liter(1d),
                TimeMeasurementUnit.SECONDS
            ),
            false
        );

        LiterWaterContainer container = new LiterWaterContainer(
            new Liter(10000d),
            new Liter(0d)
        );

        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(100d),
            new Liter(99d)
        );
        User user = new User(
            "Zezim",
            "000.000.000-00"
        );

        WaterFlowSession session = new WaterFlowSession(
            UUID.generate(),
            pump,
            source,
            container,
            user.getId(),
            null,
            null,
            "OFF"
        );

        Assert.assertThrows(
            SafetyThresholdException.class, session::start
        );
        Assert.assertEquals(session.getStatus(), "ERRORED");
        Assert.assertNotNull(session.getStartedAt());
        Assert.assertNotNull(session.getFinishedAt());
    }

    @Test
    public void shouldNotStartBecauseContainerIsAlreadyFull() {
        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(
                new Liter(1d),
                TimeMeasurementUnit.SECONDS
            ),
            false
        );

        LiterWaterContainer container = new LiterWaterContainer(
            new Liter(100d),
            new Liter(100d)
        );

        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(100d),
            new Liter(2000d)
        );
        User user = new User(
            "Zezim",
            "000.000.000-00"
        );

        WaterFlowSession session = new WaterFlowSession(
            UUID.generate(),
            pump,
            source,
            container,
            user.getId(),
            null,
            null,
            "OFF"
        );

        Assert.assertThrows(
            ContainerAlreadyFullException.class, session::start
        );
        Assert.assertEquals(session.getStatus(), "ERRORED");
        Assert.assertNotNull(session.getStartedAt());
        Assert.assertNotNull(session.getFinishedAt());
    }

    @Test
    public void shouldNotStartBecausePumpIsActiveInUse() {
        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(
                new Liter(1d),
                TimeMeasurementUnit.SECONDS
            ),
            true
        );

        LiterWaterContainer container = new LiterWaterContainer(
            new Liter(100d),
            new Liter(0d)
        );

        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(100d),
            new Liter(2000d)
        );
        User user = new User(
            "Zezim",
            "000.000.000-00"
        );

        WaterFlowSession session = new WaterFlowSession(
            UUID.generate(),
            pump,
            source,
            container,
            user.getId(),
            null,
            null,
            "OFF"
        );

        Assert.assertThrows(
            WaterPumpInUseException.class, session::start
        );
        Assert.assertEquals(session.getStatus(), "ERRORED");
        Assert.assertNotNull(session.getStartedAt());
        Assert.assertNotNull(session.getFinishedAt());
    }

    @Test
    public void shouldCompleteSession() {
        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(
                new Liter(1d),
                TimeMeasurementUnit.SECONDS
            ),
            true
        );

        LiterWaterContainer container = new LiterWaterContainer(
            new Liter(10000d),
            new Liter(500d)
        );

        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(100d),
            new Liter(1000d)
        );
        User user = new User(
            "Zezim",
            "000.000.000-00"
        );

        WaterFlowSession session = new WaterFlowSession(
            UUID.generate(),
            pump,
            source,
            container,
            user.getId(),
            DateTime.now(),
            null,
            "ON"
        );

        session.complete();

        Assert.assertEquals(session.getStatus(), "COMPLETED");
        Assert.assertFalse(pump.isActive());
        Assert.assertTrue(session.getFinishedAt().isPresent());
    }

    @Test
    public void shouldNotCompleteSessionBecauseStatusIsNotOn() {
        String[] statuses = {"PAUSED", "OFF", "COMPLETED", "ERRORED", "COMPLETED_WITH_ERROR"};

        for (String status: statuses) {
            LiterWaterPump pump = new LiterWaterPump(
                new LiterFlow(
                    new Liter(1d),
                    TimeMeasurementUnit.SECONDS
                ),
                true
            );

            LiterWaterContainer container = new LiterWaterContainer(
                new Liter(10000d),
                new Liter(500d)
            );

            LiterWaterSource source = new LiterWaterSource(
                new Liter(2000d),
                new Liter(100d),
                new Liter(1000d)
            );
            User user = new User(
                "Zezim",
                "000.000.000-00"
            );

            WaterFlowSession session = new WaterFlowSession(
                UUID.generate(),
                pump,
                source,
                container,
                user.getId(),
                DateTime.now(),
                null,
                status
            );

            Assert.assertThrows(
                WaterFlowSessionException.class, session::complete
            );

            Assert.assertEquals(session.getStatus(), status);
            Assert.assertTrue(pump.isActive());
            Assert.assertTrue(session.getFinishedAt().isEmpty());
        }
    }

    @Test
    public void shouldSyncPreview() {
        Double initialContainerValue = 0d;
        Double initialSourceValue = 2000d;

        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(
                new Liter(1d),
                TimeMeasurementUnit.SECONDS
            ),
            false
        );

        LiterWaterContainer container = new LiterWaterContainer(
            new Liter(10000d),
            new Liter(initialContainerValue)
        );

        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(10d),
            new Liter(initialSourceValue)
        );

        User user = new User(
            "Zezim",
            "000.000.000-00"
        );

        WaterFlowSession session = WaterFlowSession.create(source, container, pump, user.getId());

        session.syncPreview(
            DateTime.parse("2024-10-09T21:00:00"),
            DateTime.parse("2024-10-09T21:00:05")
        );

        Assert.assertTrue(source.getCurrentVolume().getValue() < initialSourceValue);
        Assert.assertTrue(container.getCurrentVolume().getValue() > initialContainerValue);
        Assert.assertNotEquals(session.getStatus(), "COMPLETED_WITH_ERROR");
    }

    @Test
    public void shouldFailSyncPreviewingBecauseStartDateIsInFuture() {
        Double initialContainerValue = 0d;
        Double initialSourceValue = 2000d;

        LiterWaterPump pump = new LiterWaterPump(
            new LiterFlow(
                new Liter(1d),
                TimeMeasurementUnit.SECONDS
            ),
            false
        );

        LiterWaterContainer container = new LiterWaterContainer(
            new Liter(10000d),
            new Liter(initialContainerValue)
        );

        LiterWaterSource source = new LiterWaterSource(
            new Liter(2000d),
            new Liter(10d),
            new Liter(initialSourceValue)
        );

        User user = new User(
            "Zezim",
            "000.000.000-00"
        );

        WaterFlowSession session = WaterFlowSession.create(source, container, pump, user.getId());

        Assert.assertThrows(
            WaterFlowSessionException.class,
            () -> session.syncPreview(
                DateTime.parse("2024-10-09T21:00:05"),
                DateTime.parse("2024-10-09T21:00:00")
            )
        );
    }
}
