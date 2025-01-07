package waterflow.presenters.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import waterflow.application.service.WaterSourceService;
import waterflow.presenters.dto.WaterSourceRequestDTO;
import waterflow.presenters.dto.WaterSourceResponseDTO;

@Path("/water-sources")
public class WaterSourceController {

  @Inject
  private WaterSourceService service;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public WaterSourceResponseDTO createSource(WaterSourceRequestDTO request) {
    return this.service.createSource(request);
  }
}
