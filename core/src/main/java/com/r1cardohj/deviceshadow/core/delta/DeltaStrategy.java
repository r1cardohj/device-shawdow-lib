package com.r1cardohj.deviceshadow.core.delta;

/**
 * 差异检测策略枚举，定义了在比较设备影子状态时采用的不同策略。
 */
public enum DeltaStrategy {

    /**
     * 严格模式：任何差异都会被视为需要更新
     */
    STRICT,

    /**
     * 宽松模式： 允许类型转换和容忍某些差异
     */
    LENIENT,

    /**
     * 数值容差模式： 仅当数值差异超过预定义的容差时才视为需要更新
     */
    NUMERIC_TOLERANCE,

    /**
     * 自定义模式： 允许用户定义自己的差异检测逻辑
     */
    CUSTOM
}
