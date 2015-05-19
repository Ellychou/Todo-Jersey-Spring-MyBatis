package com.ellychou.todo.rest.dao;

import com.ellychou.todo.rest.entities.Token;

/**
 * Token interface mapped to token.xml to manipulate database
 * @author szhou
 * @version 1.0.1
 * @since 2015/3/5
 *
 */
public interface TokenDao {
    public int createToken(Token token);
    public int updateToken(Token token);
    public Token getTokenByUserId(Long userId);
    public Long getUserIdByToken(String token);
    public int deleteTokenByUserId(Long userId);
}
