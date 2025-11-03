package com.r1cardohj.deviceshadow.core.delta;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode
public class ShadowDelta {
    private final Map<String, Object> deltaMap;
    private final long timestamp;

    public ShadowDelta(Map<String, Object> delat, Map<String, FieldChange> fieldChanges) {
        this.deltaMap = delat;
        this.timestamp = System.currentTimeMillis();
    }
    private static Map<String, FieldChange> extractFieldChanges(Map<String, Object> delta) {
        Map<String, FieldChange> fieldChanges = new HashMap<>();
        for (Map.Entry<String, Object> entry : delta.entrySet()) {
            fieldChanges.put(entry.getKey(), new FieldChange(entry.getKey(), null, entry.getValue()));
        }
        return fieldChanges;
    }

    public ShadowDelta(Map<String, Object> deltaMap) {
        this(deltaMap, extractFieldChanges(deltaMap));
    }
}
