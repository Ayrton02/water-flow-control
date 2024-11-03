package waterflow.usecases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import user.application.usecases.finduser.FindUserRepository;
import user.domain.entities.User;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionInput;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionOutput;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionRepository;
import waterflow.application.usecases.createwaterflowsession.CreateWaterFlowSessionUseCase;
import waterflow.domain.entities.LiterWaterContainer;
import waterflow.domain.entities.LiterWaterPump;
import waterflow.domain.entities.LiterWaterSource;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.domain.valueobjects.Liter;
import waterflow.domain.valueobjects.LiterFlow;

import static org.mockito.Mockito.*;

public class CreateWaterFlowSessionUseCaseTest {

    private AutoCloseable closeable;

    @Mock()
    private CreateWaterFlowSessionRepository<Liter> repository;

    @Mock()
    private FindUserRepository findUserRepository;

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
                TimeMeasurementUnit.SECONDS
        );
        LiterWaterPump pump = new LiterWaterPump(
                literFlow
        );

        User user = new User("Zézim", "000.000.000-00");

        CreateWaterFlowSessionInput input = CreateWaterFlowSessionInput.with(
                pump.getId(),
                container.getId(),
                source.getId(),
                user.getId()
        );

        when(repository.findPumpById(input.getPumpId())).thenAnswer(a -> pump);
        when(repository.findContainerById(input.getContainerId())).thenAnswer(a -> container);
        when(repository.findSourceById(input.getSourceId())).thenAnswer(a -> source);
        when(findUserRepository.findUser(input.getUserId())).thenAnswer(a -> user);

        CreateWaterFlowSessionOutput output = this.useCase.execute(input);

        verify(repository, times(1)).save(any());
        Assert.assertEquals("OFF", output.getStatus());
    }
}
