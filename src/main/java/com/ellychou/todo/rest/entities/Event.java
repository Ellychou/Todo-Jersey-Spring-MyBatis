package com.ellychou.todo.rest.entities;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by szhou on 2015/2/10.
 */

@XmlRootElement
public class Event implements Serializable {
    private static final long serialVersionUID = -8039686696076337057L;

    private int eventId;

    private String title;

    private String description;

    private Date doneTime;

    private Date setTime;

    public Event(){}

    public Event(String title, String description, Date doneTime, Date setTime) {
        this.title = title;
        this.description = description;
        this.doneTime = doneTime;
        this.setTime = setTime;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }
}
