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
 * Service class that handles REST request about token, which is for user authentication check
 * @author szhou
 * @version 1.0.1
 * @since 2015/3/6
 *
 */
@Component
public class TokenService {
    private static final Logger log = Logger.getLogger(TokenService.class);
    @Autowired
    public TokenDao tokenDao ;

    @Autowired
    public UserDao userDao;

    /**
     * Create token by userId
     * @param user
     * @return token
     *
     */
    public String createToken(User user) {
        Long userId = user.getUserId();
        Token token = new Token();
        token.setUserId(userId);
        token.setToken(UUID.randomUUID().toString());
         int i = tokenDao.createToken(token);
        if (i == 0) {
            return null;
        }
        return token.getToken();
    }
    /**
     * Find user by token
     * @param token
     * @return token
     */
    public User getUserByToken(String token) {
        Long userId = tokenDao.getUserIdByToken(token);
        log.info("get userId by token" + userId);
        return userDao.getUserById(userId);
    }


    /**
     * Find token by userId
     * @param userId
     * @return token
     */
    public String getTokenById(Long userId) {
        return tokenDao.getTokenByUserId(userId).getToken();
    }


}
