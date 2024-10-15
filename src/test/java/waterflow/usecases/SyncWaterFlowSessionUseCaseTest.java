package waterflow.usecases;

import core.valueobjects.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;
import waterflow.usecases.syncwaterflowsession.SyncWaterFlowSessionRepository;
import waterflow.usecases.syncwaterflowsession.SyncWaterFlowSessionUseCase;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mockStatic;

public class SyncWaterFlowSessionUseCaseTest {

    private AutoCloseable closeable;

    @InjectMocks()
    private SyncWaterFlowSessionUseCase usecase;

    @Mock()
    private SyncWaterFlowSessionRepository repository;

    @Before()
    public void setup() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @After()
    public void after() throws Exception {
        closeable.close();
    }

    @Test()
    public void shouldSyncWaterFlowSession() {
        LiterWaterContainer container = new LiterWaterContainer(
                new Liter(100d),
                new Liter(0d)
        );
        LiterWaterSource source = new LiterWaterSource(
                new Liter(100d),
                new Liter(0d),
                new Liter(100d)
        );
        LiterFlow literFlow = new LiterFlow(
                new Liter(1d),
                TimeUnit.SECONDS
        );
        LiterWaterPump pump = new LiterWaterPump(
                literFlow
        );

        WaterFlowSession session = WaterFlowSession.create(source, container, pump);

        DateTime firstTime = DateTime.parse("2024-10-09T21:00:00");
        DateTime secondTime = DateTime.parse("2024-10-09T21:00:05");

        try (MockedStatic<DateTime> mockedDateTime = mockStatic(DateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mockedDateTime.when(DateTime::now).thenReturn(firstTime);
            session.start();

            Mockito.when(repository.findById(session.getId())).thenAnswer(a -> session);

            mockedDateTime.when(DateTime::now).thenReturn(secondTime);

            this.usecase.execute(session.getId());
            Assert.assertEquals(0,session.getWaterContainer().getCurrentVolume().compareTo(new Liter(5d)));
        }
    }
}
