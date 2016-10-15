package eventspider.rest;

import eventspider.database.EventHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Controller returns the requested event
 * @author Sebastian Greenholtz
 */
@Path("/events")
public class EventsRESTController {
    @GET
    @Path("{ID}")
    public Response getEventByID(@PathParam("ID") Integer eventID) {
        EventHandler eventHandler = new EventHandler();
        String json = EventToJSON.parse(eventHandler.getEventByID(eventID));
        return Response.status(200).entity(json).build();
    }

    @GET
    public Response getAllEvents() {
        EventHandler eventHandler = new EventHandler();
        String json = EventToJSON.parse(eventHandler.getAllEvents());
        return Response.status(200).entity(json).build();
    }

}
