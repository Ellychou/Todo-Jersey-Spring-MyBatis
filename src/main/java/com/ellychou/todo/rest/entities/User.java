package com.ellychou.todo.rest.entities;

import com.google.common.base.Objects;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

/**
 * Event entity
 * @author szhou
 * @version 1.0.1
 * @since 2015/2/27
 */

public class User implements Serializable, Principal {
    public static final long serialVersionUID = -8039686696076337056L;
    private static final Logger log = Logger.getLogger(User.class);

    /** id of the user*/
    private Long userId;
    /** email of the user*/
    private String email;
    /** user name*/
    private String userName;
    /** user login password*/
    private String password;
    /** the time when the user account has been creadted*/
    private Date createdTime;
    /** last login*/
    private Date loginTime;
    /** salt for token*/
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

    public User(String email, String password) {
        this.email = email;
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
    public String getName() {
        log.info("getname: " + userId);
        return userId.toString();
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createdTime=" + createdTime +
                ", loginTime=" + loginTime +
                ", salt='" + salt + '\'' +
                '}';
    }
}
