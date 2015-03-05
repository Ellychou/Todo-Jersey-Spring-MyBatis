package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.dao.EventDao;
import com.ellychou.todo.rest.entities.Event;
import org.jboss.logging.FormatWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by szhou on 2015/2/10.
 */
@Component
@Path("/event")
public class EventRestService {

    @Autowired
    public EventDao eventDao;

    /************************************ CREATE ************************************/
    /**
     * Create new event
     * @param event
     * @return
     */
    @POST @Path("{userId}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response createEvent(Event event, @PathParam("userId") Long userId) {
        event.setUserId(userId);
        eventDao.createEvent(event);
        return Response.status(201).entity("A new event has been created").build();
    }

    /**
     *
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response createEventFromForm(@FormParam("title") String title,
                                        @FormParam("description") String description,
                                        @FormParam("doneTime") Date doneTime){
        Event event = new Event(title,description,doneTime);
        eventDao.createEvent(event);
        return Response.status(201).entity("A new event has been created").build();
    }

    /************************************ READ ************************************/

    @GET @Path("{userId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Event> getEventList(@PathParam("userId") Long userId) {
        return eventDao.getEventListByUserId(userId);
    }


    @GET @Path("one/{eventId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getEventById(@PathParam("eventId") Long id) {
        Event event = eventDao.getEventById(id);
        if(event == null) {
            return Response.status(404).entity("Event not found").build();
        }else{
            return Response.status(200).entity(event).build();

        }
    }

    @GET @Path("/completed/{userId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Event> getCompletedList(@PathParam("userId") Long userId) {
        return eventDao.getCompleteList(userId);
    }

    @GET @Path("/uncompleted/{userId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Event> getUnCompletedList(@PathParam("userId") Long userId) {
        return eventDao.getUncompletedList(userId);
    }

    @GET @Path("/notified/{userId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Event> getNotifiedList(@PathParam("userId") Long userId) {
        return eventDao.getNotifiedList(userId);
    }


    /************************************ UPDATE ************************************/

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response updateEventById(Event event) throws WebApplicationException{
        Long userId = event.getUserId();
        if(event.getEventId() == null) {
            throw new WebApplicationException("id not found");
        }
        int updated = eventDao.updateEvent(event,userId);
        String message;
        int status;
        if (updated == 1) {
            status = 200;
            message = "Updated Event Successfully";
        }else if(event.getDescription()!= null && event.getDoneTime() != null && event.getstartTime() != null) {
            eventDao.createEvent(event);
            status = 201;
            message = "Event has been created";
        }else{
            status = 404; //Not Acceptable
            message = "This event can not be updated";
        }
        return Response.status(status).entity(message).build();
    }

    /************************************ DELETE ************************************/

    @DELETE @Path("{id}")
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response deleteEventById(@PathParam("id") Long id) {
        int deleted = eventDao.deleteEventByEventId(id);
        if (deleted == 1) {
            return Response.status(204).entity("Event with the id" + id + "has been deleted").build();
        } else {
            return Response.status(404).entity("Event can not be deleted").build();
        }
    }

    @DELETE
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response deleteEvent() {
        eventDao.deleteEvent();
        return Response.status(204).entity("All the events have been deleted").build();
    }
}
