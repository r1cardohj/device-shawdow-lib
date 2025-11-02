package com.r1cardohj.deviceshadow.core.model;

import java.util.Map;

public class ShadowState {
    private Map<String, Object> reported;
    private Map<String, Object> desired;
    private Map<String, Object> delta;

    public ShadowState(Map<String, Object> reported, Map<String, Object> desired, Map<String, Object> delta) {
        this.reported = reported;
        this.desired = desired;
        this.delta = delta;
    }

    public Map<String, Object> getReported() {
        return reported;
    }

    public Map<String, Object> getDesired() {
        return desired;
    }

    public Map<String, Object> getDelta() {
        return delta;
    }
}
