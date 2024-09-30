package waterflow.usecases;

import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.entities.WaterFlowSession;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import waterflow.usecases.startwaterflowsession.StartWaterFlowSessionRepository;
import waterflow.usecases.startwaterflowsession.StartWaterFlowSessionUseCase;

import java.util.concurrent.TimeUnit;

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
                new Liter(10d)
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

        Mockito.when(repository.findById(session.getId())).thenAnswer(a -> session);

        this.usecase.execute(session.getId());
        Assert.assertTrue(session.getStartedAt().isAfter(session.getCreatedAt()));
    }
}
