package infra.cronjobs.waterflowsession;

import core.valueobjects.DateTime;
import core.valueobjects.ID;
import infra.logger.Logger;
import infra.logger.LoggerImpl;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import waterflow.application.service.WaterFlowSessionService;
import waterflow.domain.enums.TimeMeasurementUnit;
import waterflow.presenters.dto.SyncPreviewWaterFlowSessionRequestDTO;
import waterflow.presenters.dto.SyncPreviewWaterFlowSessionResponseDTO;

import java.util.List;

@ApplicationScoped
public class CompleteSessionJob {
  private final Logger logger = new LoggerImpl(CompleteSessionJob.class);

  @Inject
  private WaterFlowSessionService service;

  @Scheduled(every = "1m", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
  void cronJob() {
    List<ID> ids = this.service.findActiveIds();
    ids.forEach((id) -> {
      this.logger.info("Checking session id %s completion requirements", id);
      SyncPreviewWaterFlowSessionResponseDTO response = this.service.syncPreviewSession(
          new SyncPreviewWaterFlowSessionRequestDTO(
              id.getValue(),
              DateTime.now().toString(),
              DateTime.now().plus(5L, TimeMeasurementUnit.MINUTES).toString()
          )
      );

      try {
        if (response.status().equals("COMPLETED_WITH_ERROR")) {
          this.service.completeSession(id.getValue());
        }
      } catch (Exception e) {
        this.logger.error("Something went wrong when completing session id %s, error %s", id, e.getMessage());
      }
    });
  }
}
