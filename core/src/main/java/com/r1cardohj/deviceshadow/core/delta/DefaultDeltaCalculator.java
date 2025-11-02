package com.r1cardohj.deviceshadow.core.delta;

import com.r1cardohj.deviceshadow.core.model.DeviceShadow;
import com.r1cardohj.deviceshadow.core.model.ShadowState;

import java.util.*;

/**
 * 默认差异计算器实现
 */
public class DefaultDeltaCalculator implements ShadowDeltaCalculator {

    private final DeltaStrategy strategy;
    private final Set<String> ignoredFields;

    public DefaultDeltaCalculator() {
        this(DeltaStrategy.STRICT, Collections.emptySet());
    }

    public DefaultDeltaCalculator(DeltaStrategy strategy, Set<String> ignoredFields) {
        this.strategy = strategy;
        this.ignoredFields = new HashSet<>(ignoredFields);
    }

    @Override
    public ShadowDelta calculateDelta(DeviceShadow deviceShadow) {
        Objects.requireNonNull(deviceShadow, "deviceShadow must not be null");

        ShadowState state = deviceShadow.getState();

        if (state == null) {
            return new ShadowDelta(Collections.emptyMap());
        }

        return calculateDelta(state.getDesired(), state.getReported());
    }

    @Override
    public ShadowDelta calculateDelta(ShadowState currentState, ShadowState previousState) {
        if (currentState == null || previousState == null) {
            return new ShadowDelta(Collections.emptyMap());
        }
        Map<String, Object> currentReported = currentState.getReported() != null
                ? currentState.getReported() : Collections.emptyMap();
        Map<String, Object> previousReported = previousState.getReported() != null
                ? previousState.getReported() : Collections.emptyMap();

        return calculateDelta(currentReported, previousReported);
    }

    @Override
    public ShadowDelta calculateDelta(Map<String, Object> desired, Map<String, Object> reported) {
        return null;
    }

    @Override
    public boolean hasDelta(DeviceShadow shadow) {
        return false;
    }

    @Override
    public DeltaStrategy getStrategy() {
        return null;
    }
}
