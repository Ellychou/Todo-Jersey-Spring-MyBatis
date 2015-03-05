package com.ellychou.todo.rest.dao;

import com.ellychou.todo.rest.entities.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by szhou on 2015/2/10.
 */
public interface EventDao {
    public List<Event> getEventListByUserId(Long userId);

    public Event getEventById(Long id);

    public List<Event> getCompleteList(Long userId);

    public List<Event> getUncompletedList(Long userId);

    public List<Event> getNotifiedList(Long userId);



    public int createEvent(Event event);


    public int updateEventIsDone(@Param("eventId") Long eventId, @Param("userId") Long userId);

    public int updateEventIsNotified(@Param("eventId") Long eventId, @Param("userId") Long userId);

    public int updateEvent(@Param("event") Event event, @Param("userId") Long userId);

    public int deleteEventByEventId(Long id);

    public int deleteEventByUserId(Long id);

    public void deleteEvent();

}
