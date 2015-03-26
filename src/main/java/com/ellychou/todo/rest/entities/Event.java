package com.ellychou.todo.rest.entities;

import com.ellychou.todo.rest.util.CustomJsonDateDeserializer;
import com.google.common.base.Objects;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

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

    public Event(String title, String description, Date doneTime, Long userId) {
        this.title = title;
        this.description = description;
        this.doneTime = doneTime;
        this.userId = userId;

    }

    public Event(String title, String description, Date doneTime) {
        this.title = title;
        this.description = description;
        this.doneTime = doneTime;


    }

    public Event(Long eventId, int isDone, int isNotified) {
        this.eventId = eventId;
        this.isDone = isDone;
        this.isNotified = isNotified;
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

    public Date getStartTime() {
        return startTime;
    }

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
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

    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
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
                ", startTime=" + startTime +
                ", userId=" + userId +
                ", isDone=" + isDone +
                ", isNotified=" + isNotified +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (isDone != event.isDone) return false;
        if (isNotified != event.isNotified) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (doneTime != null ? !doneTime.equals(event.doneTime) : event.doneTime != null) return false;
        if (eventId != null ? !eventId.equals(event.eventId) : event.eventId != null) return false;
        if (startTime != null ? !startTime.equals(event.startTime) : event.startTime != null) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;
        if (userId != null ? !userId.equals(event.userId) : event.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (doneTime != null ? doneTime.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + isDone;
        result = 31 * result + isNotified;
        return result;
    }
}
