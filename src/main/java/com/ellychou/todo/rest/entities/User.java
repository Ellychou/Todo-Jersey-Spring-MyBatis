package com.ellychou.todo.rest.entities;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by szhou on 2015/2/27.
 */
public class User implements Serializable {
    public static final long serialVersionUID = -8039686696076337056L;

    private Long userId;
    private String email;
    private String userName;
    private String password;
    private Date createdTime;
    private Date loginTime;
    private String salt;

    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public User(Long userId, String email) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
    }

    public User(Long userId, String email, String userName, String password) {
        this.userId = userId;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId, email, userName, password, createdTime, loginTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equal(this.userId, other.userId)
                && Objects.equal(this.email, other.email)
                && Objects.equal(this.userName, other.userName)
                && Objects.equal(this.password, other.password)
                && Objects.equal(this.createdTime, other.createdTime)
                && Objects.equal(this.loginTime, other.loginTime);
    }
}
