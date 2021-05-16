package com.christian.TIAMS.builder;

import com.christian.TIAMS.model.Asset;
import com.christian.TIAMS.model.AssetType;
import com.christian.TIAMS.model.Company;

public class AssetBuilder {
    public Long assetID;
    public Company company;
    public AssetType assetType;
    public String name;
    public int quantity;
    public String manufacturer;

    public AssetBuilder(Company company, AssetType assetType, String name, int quantity, String manufacturer) {
        this.company = company;
        this.assetType = assetType;
        this.name = name;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

    public Asset build(){
        return new Asset(this);
    }

    public AssetBuilder setID(Long Id){
        this.assetID = Id;
        return this;
    }
}
