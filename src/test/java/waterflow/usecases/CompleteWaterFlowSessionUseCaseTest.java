package waterflow.usecases;

import infra.logger.LoggerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import user.domain.entities.User;
import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionInput;
import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionRepository;
import waterflow.application.usecases.completewaterflowsession.CompleteWaterFlowSessionUseCase;
import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

public class CompleteWaterFlowSessionUseCaseTest {

    private AutoCloseable closeable;

    @InjectMocks()
    private CompleteWaterFlowSessionUseCase usecase;

    @Mock()
    private CompleteWaterFlowSessionRepository repository;

    @Mock()
    private final LoggerImpl logger = new LoggerImpl(CompleteWaterFlowSessionUseCaseTest.class);

    @Before()
    public void setup() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @After()
    public void after() throws Exception {
        closeable.close();
    }

    @Test()
    public void shouldCompleteWaterFlowSession() {
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
        session.start();

        CompleteWaterFlowSessionInput input = CompleteWaterFlowSessionInput.with(session.getId());

        Mockito.when(repository.findById(input.getId())).thenAnswer(a -> session);

        this.usecase.execute(input);
        Assert.assertEquals("COMPLETED", session.getStatus());
    }
}
