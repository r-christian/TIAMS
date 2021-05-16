package com.christian.TIAMS.model;

import com.christian.TIAMS.utility.Constants;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyID;

    @Column(length = Constants.NAME_LENGTH)
    private String name;

    @OneToMany(mappedBy = "company")
    @JsonIgnore
    private Set<Asset> assets = new HashSet<>();

    public Company() {
    }

    public Company(Long companyID, String name, Set<Asset> assets) {
        this.companyID = companyID;
        this.name = name;
        this.assets = assets;
    }

    public Company(String name, Set<Asset> assets) {
        this.name = name;
        this.assets = assets;
    }

    public Company(String name) {
        this.name = name;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Asset> getAssets() {
        return assets;
    }

    public void setAssets(Set<Asset> assets) {
        this.assets = assets;
    }
}


