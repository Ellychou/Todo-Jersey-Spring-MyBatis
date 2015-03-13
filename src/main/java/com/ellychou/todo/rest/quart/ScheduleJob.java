package com.ellychou.todo.rest.quart;

import com.ellychou.todo.rest.dao.EventDao;
import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.Event;
import com.ellychou.todo.rest.entities.User;
import com.ellychou.todo.rest.util.SendMailKit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by szhou on 2015/3/12.
 */
@Component("scheduleJob")
public class ScheduleJob {
    private static final Logger log = Logger.getLogger(ScheduleJob.class);
    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDao userDao;

    public void sendEmail() {
        log.info("run quartz job");

        List<Event> uncompleteList = eventDao.getUnNotifiedList();
        if(uncompleteList == null || uncompleteList.size() == 0) return;
        for (Event event : uncompleteList) {
            String title = event.getTitle();
            String description = event.getDescription();
            User user = userDao.getUserById(event.getUserId());

            String email = user.getEmail();
            SendMailKit.send(email,title,description);
            eventDao.updateEventIsNotified(event.getEventId());
        }



    }
}

