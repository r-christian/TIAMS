package com.christian.TIAMS.assetInfo;

import com.christian.TIAMS.model.Asset;

public class NonTangibleAsset extends AssetDecorator{
    public NonTangibleAsset(Asset newAsset) {
        super(newAsset);
    }

    @Override
    public String getName() {
        return tempAsset.getName() + " - Non-Tangible";
    }
}
