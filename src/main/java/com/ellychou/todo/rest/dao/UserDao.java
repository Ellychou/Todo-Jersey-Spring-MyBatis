package com.ellychou.todo.rest.dao;

import com.ellychou.todo.rest.entities.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by szhou on 2015/2/27.
 */
public interface UserDao {
    public int createUser(User user);
    public int updateUser(User user);
    public List<User> getUsers();
    public User getUserById(Long id);
    public User getUserByNameOrEmail(@Param("key")String key, @Param("value")String value);
    public int deleteUserById(Long id);
    public void deleteUsers();


}
