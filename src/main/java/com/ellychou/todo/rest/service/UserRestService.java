package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.dao.TokenDao;
import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.User;
import com.ellychou.todo.rest.util.EncryptionKit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.UUID;

/**
 * Created by szhou on 2015/3/2.
 */
@Component
@Path("/user")
public class UserRestService {
    private static final Logger log = Logger.getLogger(UserRestService.class);

    @Autowired
    public UserDao userDao;

    @Autowired
    public TokenDao tokenDao;

    @Autowired
    public TokenService tokenService;
    /*
    * Create new event
    * @param event
    * @return
    */
    @POST @Path("/signup")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response createUser(User user) {
        String password = user.getPassword();
        resetPassword(user,password);
        int i = userDao.createUser(user);
        if (i == 0) {
            return Response.status(400).entity("User creation failed").build();
        }
        log.info("userService create user " + user);

        String token = tokenService.createToken(user);
        if (token == null) {
            return Response.status(400).entity("Token creation failed").build();
        }
        return Response.status(201).entity("{\"token\":\""+token+"\"}").build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response signUp(@FormParam("email") String email, @FormParam("userName") String userName, @FormParam("password") String password){
        User user = new User(email,userName,password);
        resetPassword(user,password);
        userDao.createUser(user);
        String token = tokenService.createToken(user);
        return Response.status(201).entity("New user has been created" + "{\"token\":\""+token+"\"}").build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<User> getUserList(){
        return userDao.getUsers();
    }

    @POST @Path("/login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Transactional
    public Response login(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User userGot = getUserByEmail(email);
        if (checkPassword(userGot,password)) {
            String token = tokenService.createToken(userGot);
            return Response.status(200).entity("{\"token\":\""+token+"\"}").build();
        }else {
            return Response.status(404).entity("User not found").build();
        }
    }

    @POST @Path("/logout")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response logout(@Context SecurityContext sc) {
        Long userId = Long.valueOf(sc.getUserPrincipal().getName());
        int i = tokenDao.deleteTokenByUserId(userId);
        if(i == 1) {
            return Response.status(200).entity("Deleted the user successfully").build();
        }else{
            return Response.status(404).entity("This user can not be deleted").build();
        }


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

    @PUT @Path("updatePassword")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response updatePassword(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        User userGot = getUserByEmail(email);
        if (userGot != null) {
            resetPassword(userGot,password);
            return Response.status(200).entity("Update password successfully").build();
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

    public User getUserByEmail(String email){
        User user = userDao.getUserByNameOrEmail("email",email);
        return user;
    }

    public boolean checkPassword(User user, String password) {
        String salt = user.getSalt();
        String psw = EncryptionKit.md5Encrypt(password+salt);
        return psw.equals(user.getPassword());

    }

    public void resetPassword (User user, String password) {
        user.setSalt(EncryptionKit.md5Encrypt(UUID.randomUUID().toString()));
        user.setPassword(EncryptionKit.md5Encrypt(password + user.getSalt()));
    }

    @GET
    @Path("testRest")
    public Response getTest() {
        String output = "Jersey say : ";
        return Response.ok(output).build();
    }








}
