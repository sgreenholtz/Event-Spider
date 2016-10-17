package eventspider.rest;

import eventspider.database.EventHandler;

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
    @Produces("text/json")
    public Response getEventByID(@PathParam("ID") String eventID) {
        EventHandler eventHandler = new EventHandler();
        Integer id = Integer.parseInt(eventID);
        if (eventHandler.eventExistsInDatabase(id)) {
            String json = EventToJSON.parse(eventHandler.getEventByID(id));
            return constructResponse(json);
        } else {
            return constructResponse(404);
        }

    }

    @GET
    @Produces("text/json")
    public Response getAllEvents() {
        EventHandler eventHandler = new EventHandler();
        String json = EventToJSON.parse(eventHandler.getAllEvents());
        return constructResponse(json);
    }

    private Response constructResponse(String json) {
        if (json != null) {
            return Response.status(200).entity(json).build();
        } else {
            return constructResponse(500);
        }
    }

    private Response constructResponse(Integer error) {
        return Response.status(error).build();
    }

}
