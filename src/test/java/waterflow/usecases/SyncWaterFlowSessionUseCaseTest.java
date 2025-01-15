package waterflow.usecases;

import core.valueobjects.DateTime;
import infra.logger.LoggerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import user.domain.entities.User;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionInput;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionOutput;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionRepository;
import waterflow.application.usecases.syncwaterflowsession.SyncWaterFlowSessionUseCase;
import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

import static org.mockito.Mockito.mockStatic;

public class SyncWaterFlowSessionUseCaseTest {

    private AutoCloseable closeable;

    @InjectMocks()
    private SyncWaterFlowSessionUseCase usecase;

    @Mock()
    private SyncWaterFlowSessionRepository repository;

    @Mock()
    private final LoggerImpl logger = new LoggerImpl(SyncWaterFlowSessionUseCaseTest.class);

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
            TimeMeasurementUnit.SECONDS
        );
        LiterWaterPump pump = new LiterWaterPump(
            literFlow,
            false
        );
        User user = new User("Fulaninho", "999.999.999-99");

        WaterFlowSession session = WaterFlowSession.create(source, container, pump, user.getId());

        DateTime firstTime = DateTime.parse("2024-10-09T21:00:00");
        DateTime secondTime = DateTime.parse("2024-10-09T21:00:05");

        try (MockedStatic<DateTime> mockedDateTime = mockStatic(DateTime.class, Mockito.CALLS_REAL_METHODS)) {
            mockedDateTime.when(DateTime::now).thenReturn(firstTime);
            session.start();

            SyncWaterFlowSessionInput input = SyncWaterFlowSessionInput.with(session.getId());

            Mockito.when(repository.findById(input.getId())).thenAnswer(a -> session);

            mockedDateTime.when(DateTime::now).thenReturn(secondTime);

            SyncWaterFlowSessionOutput output = this.usecase.execute(input);
            Assert.assertEquals(0,output.getContainerVolume().compareTo(new Liter(5d).getValue()));
        }
    }
}
