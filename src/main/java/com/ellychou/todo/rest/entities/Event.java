package com.ellychou.todo.rest.entities;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by szhou on 2015/2/10.
 */

@XmlRootElement
public class Event implements Serializable {
    private static final long serialVersionUID = -8039686696076337057L;

    private Long eventId;

    private String title;

    private String description;

    private Date doneTime;

    private Date setTime;

    public Event(){}

    public Event(Long eventId, String title, String description, Date doneTime) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.doneTime = doneTime;
    }

    public Event(String title, String description, Date doneTime) {
        this.title = title;
        this.description = description;
        this.doneTime = doneTime;

    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
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

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", doneTime=" + doneTime +
                ", setTime=" + setTime +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eventId, title, description, doneTime, setTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        return Objects.equal(this.eventId, other.eventId)
                && Objects.equal(this.title, other.title)
                && Objects.equal(this.description, other.description)
                && Objects.equal(this.doneTime, other.doneTime)
                && Objects.equal(this.setTime, other.setTime);
    }
}
