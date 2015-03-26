package com.ellychou.todo.rest.entities;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by szhou on 2015/3/5.
 */
public class Token implements Serializable {
    public static final long serialVersionUID = -8039686696076337046L;
    @JsonProperty("userId")
    private Long userId;
    @JsonProperty("token")
    private String token;
    private Date timeStamp;
    private long expire;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Token() {
    }

    public Token(Long userId) {
        this.userId = userId;
    }

    public Token(Long userId, String token, long expire, Date timeStamp) {
        this.userId = userId;
        this.token = token;
        this.expire = expire;
        this.timeStamp = timeStamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (expire != token1.expire) return false;
        if (timeStamp != null ? !timeStamp.equals(token1.timeStamp) : token1.timeStamp != null) return false;
        if (token != null ? !token.equals(token1.token) : token1.token != null) return false;
        if (userId != null ? !userId.equals(token1.userId) : token1.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (int) (expire ^ (expire >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", timeStamp=" + timeStamp +
                ", expire=" + expire +
                '}';
    }
}
