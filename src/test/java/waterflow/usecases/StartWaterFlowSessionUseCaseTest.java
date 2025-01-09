package waterflow.usecases;

import core.valueobjects.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import user.domain.entities.User;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionInput;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionRepository;
import waterflow.application.usecases.startwaterflowsession.StartWaterFlowSessionUseCase;
import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

import static org.mockito.Mockito.mockStatic;

public class StartWaterFlowSessionUseCaseTest {

    private AutoCloseable closeable;

    @InjectMocks()
    private StartWaterFlowSessionUseCase usecase;

    @Mock()
    private StartWaterFlowSessionRepository repository;

    @Before()
    public void setup() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @After()
    public void after() throws Exception {
        closeable.close();
    }

    @Test()
    public void shouldStartWaterFlowSession() {
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
                literFlow
        );
        User user = new User("Fulaninho", "999.999.999-99");

        DateTime firstTime = DateTime.parse("2024-10-09T21:00:00");
        DateTime secondTime = DateTime.parse("2024-10-09T21:00:05");

        try (MockedStatic<DateTime> mockedDateTime = mockStatic(DateTime.class)) {
            mockedDateTime.when(DateTime::now).thenReturn(firstTime);

            WaterFlowSession session = WaterFlowSession.create(source, container, pump, user.getId());

            StartWaterFlowSessionInput input = StartWaterFlowSessionInput.with(session.getId());

            Mockito.when(repository.findById(input.getId())).thenAnswer(a -> session);

            mockedDateTime.when(DateTime::now).thenReturn(secondTime);

            this.usecase.execute(input);
            Assert.assertTrue(session.getStartedAt().get().isAfter(session.getCreatedAt()));
        }
    }
}
