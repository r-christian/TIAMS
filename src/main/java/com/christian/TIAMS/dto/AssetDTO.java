package com.christian.TIAMS.dto;

import com.christian.TIAMS.model.Company;
import com.christian.TIAMS.utility.Constants;

import javax.persistence.*;

public class AssetDTO {
    private Long assetID;
    private String company;
    private String assetType;
    private String name;
    private String manufacturer;
    private int quantity;

    public AssetDTO(){}

    public AssetDTO(Long assetID, String company, String assetType, String name, String manufacturer, int quantity) {
        this.assetID = assetID;
        this.company = company;
        this.assetType = assetType;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
    }

    public Long getAssetID() {
        return assetID;
    }

    public void setAssetID(Long assetID) {
        this.assetID = assetID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
