package com.christian.TIAMS.controller;

import com.christian.TIAMS.dao.AssetDAO;
import com.christian.TIAMS.dao.UserDAO;
import com.christian.TIAMS.dao.base.IGenericDAO;
import com.christian.TIAMS.model.Asset;
import com.christian.TIAMS.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
public class UserController {
    private IGenericDAO userService = new UserDAO();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAll() {
        return userService.findAll();
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User add(User user) {
        return (User) userService.insert(user);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User update(User user) {
        return (User) userService.update(user);
    }

    @Path("/get")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User get(@QueryParam("id") Long id) {
        return (User) userService.get(id);
    }

    @Path("/delete")
    @DELETE
    public void delete(@QueryParam("id") Long id) {
        userService.delete(id);
    }
}


