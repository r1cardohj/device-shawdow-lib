package com.r1cardohj.deviceshadow.core.model;

import com.r1cardohj.deviceshadow.core.delta.DeltaCalculatorFactory;
import com.r1cardohj.deviceshadow.core.delta.ShadowDelta;
import com.r1cardohj.deviceshadow.core.delta.ShadowDeltaCalculator;

public interface IDeviceShadow {
    String getDeviceId();
    ShadowState getState();
    ShadowMetaData getMetaData();
    Long getVersion();
    Long getTimestamp();


    default ShadowDelta calculateDelta() {
        return DeltaCalculatorFactory.createDefault().calculateDelta(this);
    }

    default boolean hasDelta() {
        return DeltaCalculatorFactory.createDefault().hasDelta(this);
    }

    default boolean hasDelta(ShadowDeltaCalculator calculator) {
        return calculator.hasDelta(this);
    }

    default public ShadowDelta calculateDelta(ShadowDeltaCalculator calculator) {
        return calculator.calculateDelta(this);
    }
}
