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

    private Date startTime;

    private Long userId;

    private int isDone;

    private int isNotified;

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

    public Event(Long eventId, String title, String description, Date doneTime, Date startTime, Long userId) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.doneTime = doneTime;
        this.startTime = startTime;
        this.userId = userId;
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

    public Date getstartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getIsNotified() {
        return isNotified;
    }

    public void setIsNotified(int isNotified) {
        this.isNotified = isNotified;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", doneTime=" + doneTime +
                ", setTime=" + startTime +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(eventId, title, description, doneTime, startTime);
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
                && Objects.equal(this.startTime, other.startTime);
    }
}
