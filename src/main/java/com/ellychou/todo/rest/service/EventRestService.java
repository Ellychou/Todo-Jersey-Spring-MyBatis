package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.dao.EventDao;
import com.ellychou.todo.rest.entities.Event;
import com.ellychou.todo.rest.entities.User;
import com.ellychou.todo.rest.util.MsgUtils;
import org.apache.log4j.Logger;
import org.jboss.logging.FormatWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Date;
import java.util.List;

/**
 * Service class that handles REST request about event
 * @author szhou
 * @version 1.0.1
 * @since 2015/3/1
 *
 */
@Component
@Path("/event")
public class EventRestService {
    private static final Logger log = Logger.getLogger(EventRestService.class);

    @Autowired
    public EventDao eventDao;

    /************************************ CREATE ************************************/
    /**
     * Create new event
     * @param event
     * @return Response with status code and message in json format
     */
    @POST @Path("/createNewEvent")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public Response createEvent(@Context SecurityContext sc,Event event) {
       //Get user id from the SecurityContext if this request pass the AuthenticationResquestFilter
        Long userId = Long.valueOf(sc.getUserPrincipal().getName());
        if (userId != null) {
            log.info("userid" + userId);
            event.setUserId(userId);
            int i = eventDao.createEvent(event);
            if(i == 1) {
                return Response.status(200).entity(MsgUtils.msg("A new event has been created")).build();
            }else{
                return Response.status(404).entity(MsgUtils.msg("Creation failed")).build();
            }
        }else {
            return Response.status(401).entity(MsgUtils.msg("Please log in fist")).build();
        }
    }

    /**
     *Create Event from form input
     * @return Response with status code and message in json format
     */
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response createEventFromForm(@Context SecurityContext sc,@FormParam("title") String title,
                                        @FormParam("description") String description,
                                        @FormParam("doneTime") Date doneTime) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        Event event = new Event(title, description, doneTime, userId);
        eventDao.createEvent(event);
        return Response.status(201).entity("A new event has been created").build();
    }


     /********************************** READ ***********************************/

    /**
     * @return Event list, all the events for the user
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Event> getEventList(@Context SecurityContext sc) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        return eventDao.getEventListByUserId(userId);
    }

    /**
     * @param id event id
     * @return Response with event entity if the event is found by userId and eventId
     */
    @GET
    @Path("one/{eventId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getEventById(@Context SecurityContext sc,@PathParam("eventId") Long id) {
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


    /**
     * @return Completed Event list, all the events that have been completed for the user
     */
    @GET
    @Path("/completeList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Event> getCompletedList(@Context SecurityContext sc) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        return eventDao.getCompleteList(userId);
    }

    /**
     * @return Uncompleted Event list, all the events that have not been completed for the user
     */
    @GET
    @Path("/todoList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUnCompletedList(@Context SecurityContext sc) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        if (userId == null) {
            return Response.status(401).entity(MsgUtils.msg("Please log in fist")).build();
        }else {
            List<Event> events = eventDao.getUncompletedList(userId);
            return Response.status(200).entity(events).build();
        }
    }

    /**
     * @return Event list, all the events that have been notified for the user
     */
    @GET
    @Path("/notified")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Event> getNotifiedList(@Context SecurityContext sc) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        return eventDao.getNotifiedList(userId);
    }


    /**
     * ********************************* UPDATE ***********************************
     */

    /**
     * Update the event which has the same event id with the parameter event
     * @param event
     * @return Response with the status code and message in json format
     */
    @PUT @Path("updateEvent")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response updateEventById(@Context SecurityContext sc,Event event) throws WebApplicationException {
        Long userId = Long.valueOf(sc.getUserPrincipal().getName());
        if (userId == null) {
            return Response.status(404).entity(MsgUtils.msg("Please log in first")).build();
        }
        log.info("userId in updateEvent : " + userId);
        Long eventId = event.getEventId();
        if (eventId == null) {
            throw new WebApplicationException("id not found");
        }
        if (!userId.equals(eventDao.getUserIdByEventId(eventId)) ) {
            return Response.status(404).entity(MsgUtils.msg("This user does not has this event")).build();
        }
        int updated = eventDao.updateEvent(event);
        String message;
        int status;
        if (updated == 1) {
            status = 200;
            message = "Updated Event Successfully";
        } else {
            status = 404; //Not Acceptable
            message = "This event can not be updated";
        }
        return Response.status(status).entity(MsgUtils.msg(message)).build();
    }

    /**
     * Update the isDone field to 1
     * @param event
     * @return Response with the status code and message in json format
     */
    @POST @Path("/setDone")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public Response setEventIsDone (@Context SecurityContext sc,Event event) {
        Long eventId = event.getEventId();
        if (eventId == null) {
            throw new WebApplicationException("id not found");
        }
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        log.info(userId + "eventId : " + eventId);

        String message;
        int status;
        if (!userId.equals(eventDao.getUserIdByEventId(eventId)) ) {
            message = "This user does not has this event";
            return Response.status(404).entity(MsgUtils.msg(message)).build();
        }
        event.setIsDone(1);
        int updated = eventDao.updateEvent(event);
        if (updated == 1) {
            status = 200;
            message = "Updated Event Successfully";
        } else {
            status = 404; //Not Acceptable
            message = "This event can not be updated";
        }
        return Response.status(status).entity(MsgUtils.msg(message)).build();
    }

    /**
     * Update the isDone field to 0
     * @param event
     * @return Response with the status code and message in json format
     */
    @POST @Path("/setUndone")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public Response setUnDone (@Context SecurityContext sc,Event event) {
        Long eventId = event.getEventId();
        if (eventId == null) {
            throw new WebApplicationException("id not found");
        }
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        log.info(userId + "eventId : " + eventId);

        String message;
        int status;
        if (!userId.equals(eventDao.getUserIdByEventId(eventId)) ) {
            message = "This user does not has this event";
            return Response.status(404).entity(MsgUtils.msg(message)).build();
        }
        event.setIsDone(0);
        int updated = eventDao.updateEvent(event);
        if (updated == 1) {
            status = 200;
            message = "Updated Event Successfully";
        } else {
            status = 404; //Not Acceptable
            message = "This event can not be updated";
        }
        return Response.status(status).entity(MsgUtils.msg(message)).build();
    }



     /********************************* DELETE ***********************************/


    /**
     * Delete the event by event id
     * @param event
     * @return Response with the status code and message in json format
     */
    @DELETE
    @Path("deleteTask")
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public Response deleteEventById(@Context SecurityContext sc,Event event) {

        Long eventId = event.getEventId();
        if (eventId == null) {
            throw new WebApplicationException("id not found");
        }
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        log.info(userId + "eventId : " + eventId);

        if (!userId.equals(eventDao.getUserIdByEventId(eventId)) ) {
            return Response.status(404).entity(MsgUtils.msg("This user does not has this event")).build();
        }
        int deleted = eventDao.deleteEvent(eventId, userId);
        if (deleted == 1) {
            return Response.status(204).entity(MsgUtils.msg("Event has been deleted")).build();
        } else {
            return Response.status(404).entity(MsgUtils.msg("Event can not be deleted")).build();
        }
    }
    /**
     * Delete all the events for one user
     * @return Response with the status code and message in json format
     */
    @DELETE
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response deleteEvents(@Context SecurityContext sc) {
        Long userId = ((User) sc.getUserPrincipal()).getUserId();
        int deleted = eventDao.deleteEvents(userId);

        if (deleted == 1) {
            return Response.status(204).entity(MsgUtils.msg("All the events have been deleted")).build();
        } else {
            return Response.status(404).entity(MsgUtils.msg("Event can not be deleted")).build();
        }
    }
}
