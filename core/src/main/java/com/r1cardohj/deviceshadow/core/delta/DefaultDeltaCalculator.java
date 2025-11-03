package com.r1cardohj.deviceshadow.core.delta;

import com.r1cardohj.deviceshadow.core.model.IDeviceShadow;
import com.r1cardohj.deviceshadow.core.model.ShadowState;

import java.util.*;

/**
 * 默认差异计算器实现
 * 提供基础的期望状态与报告状态差异计算功能
 */
public class DefaultDeltaCalculator implements ShadowDeltaCalculator {

    private final Set<String> ignoredFields;

    public DefaultDeltaCalculator() {
        this(Collections.emptySet());
    }

    public DefaultDeltaCalculator(Set<String> ignoredFields) {
        this.ignoredFields = new HashSet<>(ignoredFields);
    }

    @Override
    public ShadowDelta calculateDelta(IDeviceShadow IDeviceShadow) {
        Objects.requireNonNull(IDeviceShadow, "deviceShadow must not be null");

        ShadowState state = IDeviceShadow.getState();

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
        if (desired == null || desired.isEmpty()) {
            return new ShadowDelta(Collections.emptyMap());
        }

        Map<String, Object> delta = new HashMap<>();

        for (Map.Entry<String, Object> desiredEntry : desired.entrySet()) {
            String field = desiredEntry.getKey();

            // 跳过忽略的字段
            if (ignoredFields.contains(field)) {
                continue;
            }

            Object desiredValue = desiredEntry.getValue();
            Object reportedValue = getReportedValue(reported, field);

            // 检查是否存在差异
            if (!isEqual(desiredValue, reportedValue)) {
                delta.put(field, desiredValue);
            }
        }

        return new ShadowDelta(delta);
    }

    private boolean isEqual(Object desired, Object reported) {
        if (desired == null && reported == null) {
            return true;
        }

        if (desired == null | reported == null) {
            return false;
        }

        if (desired instanceof Map && reported instanceof Map) {
            return isMapEqual((Map<?, ?>) desired, (Map<?, ?>) reported);
        }

        if (desired instanceof List && reported instanceof List) {
            return isListEqual((List<?>) desired, (List<?>) reported);
        }

        if (desired instanceof Number && reported instanceof Number) {
            return isNumberEqual((Number) desired, (Number) reported);
        }

        return desired.equals(reported);
    }

    private boolean isMapEqual(Map<?, ?> map1, Map<?, ?> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<?, ?> entry : map1.entrySet()) {
            Object key = entry.getKey();
            Object value1 = entry.getValue();
            Object value2 = map2.get(key);

            if (!isEqual(value1, value2)) {
                return false;
            }
        }

        return true;
    }

    private boolean isListEqual(List<?> list1, List<?> list2) {
        if (list1.size()!= list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            Object value1 = list1.get(i);
            Object value2 = list2.get(i);

            if (!isEqual(value1, value2)) {
                return false;
            }
        }

        return true;
    }


    private boolean isNumberEqual(Number num1, Number num2) {
        double d1 = num1.doubleValue();
        double d2 = num2.doubleValue();

        // 使用小的容差值处理浮点数精度
        return Math.abs(d1 - d2) < 0.000001;
    }

    @Override
    public boolean hasDelta(IDeviceShadow shadow) {
        if (shadow == null || shadow.getState() == null) {
            return false;
        }

        ShadowState state = shadow.getState();

        return hasDelta(state.getDesired(), state.getReported());
    }

    @Override
    public boolean hasDelta(Map<String, Object> desired, Map<String, Object> reported) {
        if (desired == null || desired.isEmpty())
            return false;


        for (Map.Entry<String, Object> entry : desired.entrySet()) {
            String field = entry.getKey();

            if (ignoredFields.contains(field)) {
                continue;
            }

            Object desiredValue = entry.getValue();
            Object reportedValue = getReportedValue(reported, field);

            if (!isEqual(desiredValue, reportedValue)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 从报告状态中安全获取字段值
     */
    private Object getReportedValue(Map<String, Object> reported, String field) {
        if (reported == null) {
            return null;
        }
        return reported.get(field);
    }
}
