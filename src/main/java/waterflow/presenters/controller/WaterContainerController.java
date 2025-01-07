package waterflow.presenters.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import waterflow.application.service.WaterContainerService;
import waterflow.presenters.dto.WaterContainerRequestDTO;
import waterflow.presenters.dto.WaterContainerResponseDTO;

@Path("/water-containers")
public class WaterContainerController {

  @Inject
  private WaterContainerService service;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public WaterContainerResponseDTO createContainer(WaterContainerRequestDTO request) {
    return this.service.createContainer(request);
  }
}
