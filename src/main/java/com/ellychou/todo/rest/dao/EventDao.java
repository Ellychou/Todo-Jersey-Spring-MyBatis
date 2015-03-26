package com.ellychou.todo.rest.dao;

import com.ellychou.todo.rest.entities.Event;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by szhou on 2015/2/10.
 */
public interface EventDao {
    public List<Event> getEventListByUserId(Long userId);

    public Event getEventById(Long id);

    public List<Event> getCompleteList(Long userId);

    public List<Event> getUncompletedList(Long userId);

    public List<Event> getUnNotifiedList();

    public List<Event> getNotifiedList(Long userId);

    public Long getUserIdByEventId(Long eventId);



    public int createEvent(Event event);


    public int updateEventIsDone(Long eventId);

    public int updateEventIsNotified(Long eventId);

    public int updateEvent(Event event);

    public int deleteEvent(@Param("eventId") Long eventId, @Param("userId") Long userId);

    public int deleteEvents(Long userId);

}
