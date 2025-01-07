package waterflow.presenters.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import waterflow.application.service.WaterPumpService;
import waterflow.presenters.dto.WaterPumpRequestDTO;
import waterflow.presenters.dto.WaterPumpResponseDTO;

@Path("/water-pumps")
public class WaterPumpController {

  @Inject
  private WaterPumpService service;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public WaterPumpResponseDTO createPump(WaterPumpRequestDTO request) {
    return this.service.createPump(request);
  }
}
