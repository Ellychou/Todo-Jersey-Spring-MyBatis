package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.dao.EventDao;
import com.ellychou.todo.rest.entities.Event;
import com.ellychou.todo.rest.entities.User;
import org.jboss.logging.FormatWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
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

    @Context
    private SecurityContext sc;


    /************************************ CREATE ************************************/
    /**
     * Create new event
     *
     * @param event
     * @return
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response createEvent(Event event) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
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
                                        @FormParam("doneTime") Date doneTime) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        Event event = new Event(title, description, doneTime, userId);
        eventDao.createEvent(event);
        return Response.status(201).entity("A new event has been created").build();
    }

    /**
     * ********************************* READ ***********************************
     */

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Event> getEventList() {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        return eventDao.getEventListByUserId(userId);
    }


    @GET
    @Path("one/{eventId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEventById(@PathParam("eventId") Long id) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        Event event = eventDao.getEventById(id);
        if (event == null) {
            return Response.status(404).entity("Event not found").build();
        }
        if (event.getUserId().equals(userId)) {
            return Response.status(200).entity(event).build();
        } else {
            return Response.status(404).entity("Not authorized").build();
        }
    }

    @GET
    @Path("/completed")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Event> getCompletedList() {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        return eventDao.getCompleteList(userId);
    }

    @GET
    @Path("/uncompleted")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Event> getUnCompletedList() {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        return eventDao.getUncompletedList(userId);
    }

    @GET
    @Path("/notified")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Event> getNotifiedList() {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        return eventDao.getNotifiedList(userId);
    }


    /**
     * ********************************* UPDATE ***********************************
     */

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response updateEventById(Event event) throws WebApplicationException {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        if (event.getEventId() == null) {
            throw new WebApplicationException("id not found");
        }
        int updated = eventDao.updateEvent(event, userId);
        String message;
        int status;
        if (updated == 1) {
            status = 200;
            message = "Updated Event Successfully";
        } else {
            status = 404; //Not Acceptable
            message = "This event can not be updated";
        }
        return Response.status(status).entity(message).build();
    }

    /**
     * ********************************* DELETE ***********************************
     */

    @DELETE
    @Path("{id}")
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response deleteEventById(@PathParam("id") Long id) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        int deleted = eventDao.deleteEvent(id, userId);
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
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        int deleted = eventDao.deleteEvents(userId);

        if (deleted == 1) {
            return Response.status(204).entity("All the events have been deleted").build();
        } else {
            return Response.status(404).entity("Event can not be deleted").build();
        }
    }
}
