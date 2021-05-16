package com.christian.TIAMS.assetInfo;

import com.christian.TIAMS.model.Asset;

public class TangibleAsset extends AssetDecorator {
    public TangibleAsset(Asset newAsset) {
        super(newAsset);
    }

    @Override
    public String getName() {
        return tempAsset.getName() + " - Tangible";
    }
}
