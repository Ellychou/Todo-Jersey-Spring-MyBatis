package com.ellychou.todo.rest.service.integration;

import com.ellychou.todo.rest.dao.EventDao;
import com.ellychou.todo.rest.entities.Event;
import com.ellychou.todo.rest.service.EventRestService;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by szhou on 2015/2/28.
 */
@Ignore
public class EventDaoTest {
    private static final Logger log = Logger.getLogger(EventDaoTest.class);
    private static ApplicationContext context= new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    private EventDao eventDao = context.getBean(EventDao.class);


    @Test
    public void createEventTest() {
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String dateInString = "22-01-2015 10:20:56";
        Date date = sdf.parse(dateInString);
        */
        Event event = new Event("Test service","Finish by tomorrow",new Date(),1L);
        int i = eventDao.createEvent(event);
        log.info(event.toString() + i);
    }

    @Test
    public void getEventByIdTest() {
        Event event = eventDao.getEventById(1L);
        log.info(event);
    }

    @Test
    public void updateTest(){
        Event event = new Event(1L,"test", "finish now", new Date());
        int i = eventDao.updateEvent(event);
        log.info(i);
    }

    @Test
    public void deleteTest() {
        eventDao.deleteEvents(3L);
    }

}
