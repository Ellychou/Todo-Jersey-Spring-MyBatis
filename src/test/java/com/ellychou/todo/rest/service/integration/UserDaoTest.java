package com.ellychou.todo.rest.service.integration;


import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.User;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by szhou on 2015/3/2.
 */
@Ignore
public class UserDaoTest {
    private static final Logger log = Logger.getLogger(UserDaoTest.class);
    private static ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    private UserDao userDao = context.getBean(UserDao.class);

    @Test
    public void createTest() {
        User user = new User("shanzhou321@icloud.com","shan2","zs");
        int i = userDao.createUser(user);
        log.info(i);
    }

    @Test
    public void updateEmailTest() {
        User user = new User(2L,"szhou@test.edu");
        int i = userDao.updateUser(user);
        log.info(i);
    }

    @Test
    public void deleteTest() {
        userDao.deleteUserById(2L);
    }

}
