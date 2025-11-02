package com.r1cardohj.deviceshadow.core.model;

import java.util.Map;

public class ShadowMetaData {
    private Map<String, MetaDataItem> reported;
    private Map<String, MetaDataItem> desired;

    public ShadowMetaData(Map<String, MetaDataItem> reported, Map<String, MetaDataItem> desired) {
        this.reported = reported;
        this.desired = desired;
    }

    public Map<String, MetaDataItem> getReported() {
        return reported;
    }

    public Map<String, MetaDataItem> getDesired() {
        return desired;
    }
}
