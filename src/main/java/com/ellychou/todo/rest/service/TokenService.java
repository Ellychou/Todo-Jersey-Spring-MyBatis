package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.dao.TokenDao;
import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.Token;
import com.ellychou.todo.rest.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by szhou on 2015/3/6.
 */
@Component
public class TokenService {
    private static final Logger log = Logger.getLogger(TokenService.class);
    @Autowired
    public TokenDao tokenDao ;

    @Autowired
    public UserDao userDao;

    public String createToken(User user) {
        Token token = new Token(user.getUserId());
        token.setToken(UUID.randomUUID().toString());
        tokenDao.createToken(token);
        return token.getToken();
    }

    public User getUserByToken(String token) {
        Long userId = tokenDao.getUserIdByToken(token);
        log.info("get userId by token" + userId);
        return userDao.getUserById(userId);
    }

    public String getTokenById(Long userId) {
        return tokenDao.getTokenByUserId(userId).getToken();
    }






}
