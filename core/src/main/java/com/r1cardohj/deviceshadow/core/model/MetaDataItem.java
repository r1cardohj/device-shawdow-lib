package com.r1cardohj.deviceshadow.core.model;

public class MetaDataItem {
    private Long timestamp;
    private Long version;

    public MetaDataItem(Long timestamp, Long version) {
        this.timestamp = timestamp;
        this.version = version;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Long getVersion() {
        return version;
    }
}
