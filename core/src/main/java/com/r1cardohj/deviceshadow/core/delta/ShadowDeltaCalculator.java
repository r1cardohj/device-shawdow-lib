package com.r1cardohj.deviceshadow.core.delta;

import com.r1cardohj.deviceshadow.core.model.IDeviceShadow;
import com.r1cardohj.deviceshadow.core.model.ShadowState;

import java.util.Map;

/*
 * device shadow delta calculator interface
 */
public interface ShadowDeltaCalculator {
    /*
     * calculates the delta of a device shadow
     */
    ShadowDelta calculateDelta(IDeviceShadow IDeviceShadow);
    /*
     * calculates the delta between current and previous states
     */
    ShadowDelta calculateDelta(ShadowState currentState, ShadowState previousState);
    /*
     * calculates the delta between desired and reported states
     */
    ShadowDelta calculateDelta(Map<String, Object> desired, Map<String, Object> reported);
    /*
     * checks if the device shadow has delta
     */
    boolean hasDelta(IDeviceShadow shadow);

    boolean hasDelta(Map<String, Object> desired, Map<String, Object> reported);
}
