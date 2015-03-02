package com.ellychou.todo.rest.service.integration;

import com.ellychou.todo.rest.dao.EventDao;
import com.ellychou.todo.rest.entities.Event;
import com.ellychou.todo.rest.service.EventRestService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by szhou on 2015/2/28.
 */
public class EventServiceTest {
    private static final Logger log = Logger.getLogger(EventServiceTest.class);
    private static ApplicationContext context= new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    private EventDao eventDao = context.getBean(EventDao.class);
    private EventRestService eventService = context.getBean(EventRestService.class);

    @Test
    public void createEvent() throws ParseException {
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String dateInString = "22-01-2015 10:20:56";
        Date date = sdf.parse(dateInString);
        */
        Event event = new Event("Test service","Finish by tomorrow",new Date());
        int i = eventDao.createEvent(event);
        log.info(event.toString() + i);


    }


}
