package com.r1cardohj.deviceshadow.core.delta;

/**
 * 差异严重性枚举，定义了不同级别的差异严重性，用于指导设备影子状态的更新策略。
 */
public enum DeltaSeverity {

    NONE(0, "no difference"),
    LOW(1, "low severity difference"),
    MEDIUM(2, "medium severity difference"),
    HIGH(3, "high severity difference"),
    CRITICAL(4, "critical severity difference");

    private final int level;
    private final String description;

    DeltaSeverity(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public boolean isMoreSevereThan(DeltaSeverity other) {
        return this.level > other.level;
    }
}
