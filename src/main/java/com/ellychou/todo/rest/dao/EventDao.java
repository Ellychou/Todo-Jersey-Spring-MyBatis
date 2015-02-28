package com.ellychou.todo.rest.dao;

import com.ellychou.todo.rest.entities.Event;

import java.util.List;

/**
 * Created by szhou on 2015/2/10.
 */
public interface EventDao {
    public List<Event> getEvent();

    public Event getEventById(Long id);

    public int deleteEventById(Long id);

    public int createEvent(Event event);

    public int updateEvent(Event event);

    public void deleteEvent();

}
