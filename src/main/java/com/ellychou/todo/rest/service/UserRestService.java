package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by szhou on 2015/3/2.
 */
@Component
@Path("/user")
public class UserRestService {

    @Autowired
    public UserDao userDao;
    /*
    * Create new event
    * @param event
    * @return
    */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response createUser(User user) {
        userDao.createUser(user);
        return Response.status(201).entity("User has been created").build();
    }

    @POST @Path("/signup")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response signUp(@FormParam("email") String email, @FormParam("userName") String userName, @FormParam("password") String password){
        User user = new User(email,userName,password);
        userDao.createUser(user);
        return Response.status(201).entity("New user has been created").build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<User> getUserList(){
        return userDao.getUsers();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public User getUserByName(String name){
        User user = userDao.getUserByNameOrEmail("user_name",name);
        return user;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public User getUserByEmail(String email){
        User user = userDao.getUserByNameOrEmail("email",email);
        return user;
    }

    @GET @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getUserById(@PathParam("id") Long id) {
        User user = userDao.getUserById(id);
        if (user == null) {
            return Response.status(404).entity("User with this id not found").build();
        }else{
            return Response.status(200).entity(user).build();
        }
    }

    @PUT @Path("id")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response updateUserById(@PathParam("id") Long id, User user) {
        if(user.getUserId() == null) {
            throw new WebApplicationException("ID Not Found");
        }
        int i = userDao.updateUser(user);
        if(i == 1) {
            return Response.status(200).entity("Updated successfully").build();
        }else{
            return Response.status(404).entity("This user can not be updated").build();
        }
    }

    @DELETE @Path("id")
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response deleteUserByid(@PathParam("id") Long id) {
        int deleted = userDao.deleteUserById(id);
        if (deleted == 1) {
            return Response.status(204).entity("User with the id" + id + "has been deleted").build();
        } else {
            return Response.status(404).entity("User can not be deleted").build();
        }
    }











}
