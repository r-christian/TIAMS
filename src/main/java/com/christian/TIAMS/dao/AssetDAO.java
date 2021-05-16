package com.christian.TIAMS.dao;

import com.christian.TIAMS.builder.AssetBuilder;
import com.christian.TIAMS.dao.base.AbstractGenericDAO;
import com.christian.TIAMS.dao.base.IGenericDAO;
import com.christian.TIAMS.dto.AssetDTO;
import com.christian.TIAMS.model.Asset;
import com.christian.TIAMS.model.AssetType;
import com.christian.TIAMS.model.Company;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AssetDAO extends AbstractGenericDAO<Asset> {
    public AssetDAO() {
        super(Asset.class);
    }



    @Override
    public Asset update(Asset object) {
        AssetBuilder builder = new AssetBuilder(
                object.getCompany(),
                object.getAssetType(),
                object.getName(),
                object.getQuantity(),
                object.getManufacturer()
        );
        return super.update(builder.setID(object.getAssetID()).build());
    }

    public Asset insertDTO(AssetDTO assetDTO){
        CompanyDAO companyService = new CompanyDAO();
        AssetTypeDAO assetTypeService = new AssetTypeDAO();
        Asset currentObject = new AssetBuilder(
                companyService.getByName(assetDTO.getCompany()),
                assetTypeService.getByName(assetDTO.getAssetType()),
                assetDTO.getName(),
                assetDTO.getQuantity(),
                assetDTO.getManufacturer()
        ).setID(assetDTO.getAssetID()).build();
        return super.insert(currentObject);
    }

    public Asset updateDTO(AssetDTO assetDTO){
        CompanyDAO companyService = new CompanyDAO();
        AssetTypeDAO assetTypeService = new AssetTypeDAO();
        Asset currentObject = get(assetDTO.getAssetID());
        currentObject.setManufacturer(assetDTO.getManufacturer());
        currentObject.setName(assetDTO.getName());
        currentObject.setQuantity(assetDTO.getQuantity());
        currentObject.setCompany(companyService.getByName(assetDTO.getCompany()));
        currentObject.setAssetType(assetTypeService.getByName(assetDTO.getAssetType()));
        return super.update(currentObject);
    }
}
