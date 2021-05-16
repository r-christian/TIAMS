package com.christian.TIAMS.model;

import com.christian.TIAMS.utility.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class AssetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetTypeID;

    @Column(length = Constants.NAME_LENGTH)
    private String name;

    @OneToMany(mappedBy = "assetType")
    @JsonIgnore
    private Set<Asset> assets = new HashSet<>();

    public AssetType() {
    }

    public AssetType(Long assetTypeID, String name, Set<Asset> assets) {
        this.assetTypeID = assetTypeID;
        this.name = name;
        this.assets = assets;
    }

    public AssetType(String name, Set<Asset> assets) {
        this.name = name;
        this.assets = assets;
    }

    public AssetType(String name) {
        this.name = name;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }

    public Long getAssetTypeID() {
        return assetTypeID;
    }

    public void setAssetTypeID(Long assetTypeID) {
        this.assetTypeID = assetTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
