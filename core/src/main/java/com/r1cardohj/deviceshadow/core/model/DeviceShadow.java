package com.r1cardohj.deviceshadow.core.model;

public interface DeviceShadow {
    String getDeviceId();
    ShadowState getState();
    ShadowMetaData getMetaData();
    Long getVersion();
}
