package waterflow.usecases;

import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionRepository;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionUseCase;

import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

public class CreateWaterFlowSessionUseCaseTest {

    private AutoCloseable closeable;

    @Mock()
    private CreateWaterFlowSessionRepository<Liter> repository;

    @InjectMocks()
    private CreateWaterFlowSessionUseCase useCase;

    @Before()
    public void setup() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void after() throws Exception {
        closeable.close();
    }

    @Test()
    public void shouldCreateWaterFlowSession() {
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

        when(repository.findPumpById(pump.getId())).thenAnswer(a -> pump);
        when(repository.findContainerById(container.getId())).thenAnswer(a -> container);
        when(repository.findSourceById(source.getId())).thenAnswer(a -> source);

        this.useCase.execute(pump.getId(), container.getId(), source.getId());

        verify(repository, times(1)).save(any());
    }
}
