package com.r1cardohj.deviceshadow.core.delta;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 字段变化类，表示设备影子中某个字段的变化信息。
 */
@Getter
@EqualsAndHashCode
public class FieldChange {

    private final String fieldName;
    private final Object oldValue;
    private final Object newValue;
    private final ChangeType changeType;

    public FieldChange(String fieldName, Object oldValue, Object newValue) {
        this.fieldName = fieldName;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changeType = determineChangeType(oldValue, newValue);
    }

    private ChangeType determineChangeType(Object oldValue, Object newValue) {
        if (oldValue == null && newValue != null) {
            return ChangeType.ADDED;
        } else if (oldValue != null && newValue == null) {
            return ChangeType.REMOVED;
        } else if (oldValue != null && !oldValue.equals(newValue)) {
            return ChangeType.MODIFIED;
        } else {
            return ChangeType.UNCHANGED;
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public enum ChangeType {
        ADDED,
        REMOVED,
        MODIFIED,
        UNCHANGED
    }
}
