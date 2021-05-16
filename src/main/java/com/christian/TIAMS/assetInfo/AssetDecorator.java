package com.christian.TIAMS.assetInfo;

import com.christian.TIAMS.model.Asset;

public abstract class AssetDecorator implements IAsset{
    protected Asset tempAsset;

    public AssetDecorator(Asset newAsset) {
        this.tempAsset = newAsset;
    }

    @Override
    public Asset getAsset() {
        return tempAsset;
    }
}

