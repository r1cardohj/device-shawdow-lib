package com.r1cardohj.deviceshadow.core.model;

import lombok.Data;

@Data
public class DeviceShadow implements IDeviceShadow {
    private String deviceId;
    private ShadowState state;
    private ShadowMetaData metaData;
    private Long version;
    private Long timestamp;
}
