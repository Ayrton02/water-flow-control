package waterflow.presenters.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/start")
    public WaterFlowSessionResponseDTO startSession(@PathParam("id") String id) {
        return this.service.startSession(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/complete")
    public WaterFlowSessionResponseDTO completeSession(@PathParam("id") String id) {
        return this.service.completeSession(id);
    }
}
