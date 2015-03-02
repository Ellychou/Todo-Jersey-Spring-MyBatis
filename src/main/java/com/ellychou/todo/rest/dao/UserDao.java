package com.ellychou.todo.rest.dao;

import com.ellychou.todo.rest.entities.User;

import java.util.List;

/**
 * Created by szhou on 2015/2/27.
 */
public interface UserDao {
    public int createUser(User user);
    public int updateUser(User user);
    public List<User> getUsers();
    public User getUserById(Long id);
    public int deleteUserById(Long id);
    public void deleteUsers();


}
