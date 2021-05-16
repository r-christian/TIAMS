package com.christian.TIAMS.controller;

import com.christian.TIAMS.dao.AssetTypeDAO;
import com.christian.TIAMS.dao.CompanyDAO;
import com.christian.TIAMS.dao.base.IGenericDAO;
import com.christian.TIAMS.model.AssetType;
import com.christian.TIAMS.model.Company;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/assetType")
public class AssetTypeController {
    private AssetTypeDAO assetTypeService = new AssetTypeDAO();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AssetType> findAll() {
        return assetTypeService.findAll();
    }

    @Path("/listNames")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AssetType> getNames() {
        return assetTypeService.getTypeNames();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AssetType add(AssetType assetType) {
        return (AssetType) assetTypeService.insert(assetType);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AssetType update(AssetType assetType) {
        return (AssetType) assetTypeService.update(assetType);
    }

    @Path("/get")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AssetType get(@QueryParam("id") Long id) {
        return (AssetType) assetTypeService.get(id);
    }

    @Path("/delete")
    @DELETE
    public void delete(@QueryParam("id") Long id) {
        assetTypeService.delete(id);
    }
}
