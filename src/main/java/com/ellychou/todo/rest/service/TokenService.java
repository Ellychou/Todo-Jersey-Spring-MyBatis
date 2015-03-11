package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.dao.TokenDao;
import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.Token;
import com.ellychou.todo.rest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by szhou on 2015/3/6.
 */
@Component
public class TokenService {
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
        return userDao.getUserById(userId);
    }

    public String getTokenById(Long userId) {
        return tokenDao.getTokenByUserId(userId).getToken();
    }






}
