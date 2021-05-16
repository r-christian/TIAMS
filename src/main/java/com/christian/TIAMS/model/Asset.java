package com.christian.TIAMS.model;

import com.christian.TIAMS.builder.AssetBuilder;
import com.christian.TIAMS.utility.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AssetID;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=true)
    private Company company;

    @ManyToOne
    @JoinColumn(name="asset_type_id", nullable=true)
    private com.christian.TIAMS.model.AssetType assetType;

    @Column(length = Constants.NAME_LENGTH)
    private String name;

    @Column(length = Constants.NAME_LENGTH)
    private String manufacturer;

    private int quantity;

    public Asset() {
    }

    public Asset(Long assetID, Company company, com.christian.TIAMS.model.AssetType assetType, String name, String manufacturer, int quantity) {
        AssetID = assetID;
        this.company = company;
        this.assetType = assetType;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
    }

    public Asset(Company company, com.christian.TIAMS.model.AssetType assetType, String name, String manufacturer, int quantity) {
        this.company = company;
        this.assetType = assetType;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
    }

    public Asset(AssetBuilder assetBuilder) {
        this.AssetID = assetBuilder.assetID;
        this.company = assetBuilder.company;
        this.assetType = assetBuilder.assetType;
        this.name = assetBuilder.name;
        this.manufacturer = assetBuilder.manufacturer;
        this.quantity = assetBuilder.quantity;
    }

    public Long getAssetID() {
        return AssetID;
    }

    public void setAssetID(Long assetID) {
        AssetID = assetID;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public com.christian.TIAMS.model.AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(com.christian.TIAMS.model.AssetType assetType) {
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

    public String companyName(){
        if(company == null)
            return "";
        else
            return company.getName();
    }

    public String assetTypeName(){
        if(assetType == null)
            return "";
        else
            return assetType.getName();
    }
}
