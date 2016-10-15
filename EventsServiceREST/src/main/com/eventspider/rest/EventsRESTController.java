package com.eventspider.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Controller returns the requested event
 * @author Sebastian Greenholtz
 */
@Path("/events")
public class EventsRESTController {
    @GET
    @Path("{ID}")
    public Response getEventByID() {
        String json = "";
        return Response.status(200).entity(json).build();
    }

    @GET
    public Response getAllEvents() {
        String json = "";
        return Response.status(200).entity(json).build();
    }

}
