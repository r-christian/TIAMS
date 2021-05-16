package com.christian.TIAMS.controller;

import com.christian.TIAMS.dao.CompanyDAO;
import com.christian.TIAMS.dao.base.IGenericDAO;
import com.christian.TIAMS.model.AssetType;
import com.christian.TIAMS.model.Company;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/company")
public class CompanyController {
    private CompanyDAO companyService = new CompanyDAO();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> findAll(){
        return companyService.findAll();
    }

    @Path("/listNames")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getNames() {
        return companyService.getCompanyNames();
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Company add(Company company) {
        return (Company) companyService.insert(company);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Company update(Company company) {
        return (Company) companyService.update(company);
    }

    @Path("/get")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Company get(@QueryParam("id") Long id) {
        return (Company) companyService.get(id);
    }

    @Path("/delete")
    @DELETE
    public void delete(@QueryParam("id") Long id) {
        companyService.delete(id);
    }
}
