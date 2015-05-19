package com.ellychou.todo.rest.dao;

import com.ellychou.todo.rest.entities.Event;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Event interface mapped to event.xml to manipulate database
 * @author szhou
 * @version 1.0.1
 * @since 2015/2/10
 *
 */
public interface EventDao {
    public List<Event> getEventListByUserId(Long userId);

    public Event getEventById(Long id);

    /** Return a list of events which are already completed */
    public List<Event> getCompleteList(Long userId);

    /** Return a list of events which are uncompleted */
    public List<Event> getUncompletedList(Long userId);

    /** Return a list of events which have not been sent email to notify */
    public List<Event> getUnNotifiedList();

    /** Return a list of events which have been sent email to notify */
    public List<Event> getNotifiedList(Long userId);

    public Long getUserIdByEventId(Long eventId);

    public int createEvent(Event event);

    public int updateEventIsDone(Long eventId);

    public int updateEventIsNotified(Long eventId);

    public int updateEvent(Event event);

    public int deleteEvent(@Param("eventId") Long eventId, @Param("userId") Long userId);

    public int deleteEvents(Long userId);

}
