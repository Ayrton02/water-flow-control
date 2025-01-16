package infra.cronjobs.syncsession;

import core.valueobjects.ID;
import infra.logger.Logger;
import infra.logger.LoggerImpl;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.service.WaterFlowSessionService;

import java.util.List;

@ApplicationScoped
public class SyncSessionJob {
  private final Logger logger = new LoggerImpl(SyncSessionJob.class);

  @Inject
  private WaterFlowSessionService service;

  @Scheduled(every = "1m", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
  void cronJob() {
    List<ID> ids = service.findActiveIds();
    ids.forEach((id) -> {
          this.logger.info("Syncing session id %s", id);
          this.service.syncSession(id.getValue());
    });
  }
}
