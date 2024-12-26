package waterflow.presenters.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import waterflow.application.service.WaterFlowSessionService;
import waterflow.presenters.dto.WaterFlowSessionRequestDTO;
import waterflow.presenters.dto.WaterFlowSessionResponseDTO;

@Path("/water-flow-sessions")
public class WaterFlowSessionController {

    @Inject
    private WaterFlowSessionService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public WaterFlowSessionResponseDTO createSession(WaterFlowSessionRequestDTO request) {
        return this.service.createSession(request);
    }
}
