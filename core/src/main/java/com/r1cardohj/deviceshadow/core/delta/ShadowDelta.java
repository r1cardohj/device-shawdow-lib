package com.r1cardohj.deviceshadow.core.delta;

import java.util.HashMap;
import java.util.Map;

public class ShadowDelta {
    private final Map<String, Object> delta;
    private final Map<String, FieldChange> fieldCHanges;
    private final long timestatmp;
    private final DeltaSeverity severity;

    public ShadowDelta(Map<String, Object> delat, Map<String, FieldChange> fieldChanges,DeltaSeverity severity) {
        this.delta = delat;
        this.fieldCHanges = fieldChanges;
        this.timestatmp = System.currentTimeMillis();
        this.severity = severity;
    }
    private static Map<String, FieldChange> extractFieldChanges(Map<String, Object> delta) {
        Map<String, FieldChange> fieldChanges = new HashMap<>();
        for (Map.Entry<String, Object> entry : delta.entrySet()) {
            fieldChanges.put(entry.getKey(), new FieldChange(entry.getKey(), null, entry.getValue()));
        }
        return fieldChanges;
    }

    public ShadowDelta(Map<String, Object> delta) {
        this(delta, extractFieldChanges(delta), calculateSeverity(delta));
    }

    private static DeltaSeverity calculateSeverity(Map<String, Object> delta) {
        return delta.isEmpty() ? DeltaSeverity.NONE : DeltaSeverity.MEDIUM;
    }
}
