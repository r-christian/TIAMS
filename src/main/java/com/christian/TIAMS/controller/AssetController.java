package com.christian.TIAMS.controller;

import com.christian.TIAMS.dao.AssetDAO;
import com.christian.TIAMS.dao.AssetTypeDAO;
import com.christian.TIAMS.dao.base.IGenericDAO;
import com.christian.TIAMS.dto.AssetDTO;
import com.christian.TIAMS.model.Asset;
import com.christian.TIAMS.model.AssetType;
import com.christian.TIAMS.model.Company;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/asset")
public class AssetController {
    private AssetDAO assetService = new AssetDAO();

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Asset> findAll() {
        return assetService.findAll();
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Asset add(AssetDTO assetDTO) {
        return assetService.insertDTO(assetDTO);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Asset update(AssetDTO assetDTO) {
        return (Asset) assetService.updateDTO(assetDTO);
    }

    @Path("/get")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Asset get(@QueryParam("id") Long id) {
        return (Asset) assetService.get(id);
    }

    @Path("/delete")
    @DELETE
    public void delete(@QueryParam("id") Long id) {
        assetService.delete(id);
    }
}

