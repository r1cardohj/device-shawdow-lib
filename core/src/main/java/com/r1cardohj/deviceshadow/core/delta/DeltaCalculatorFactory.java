package com.r1cardohj.deviceshadow.core.delta;

import java.util.Set;

public class DeltaCalculatorFactory {
    private DeltaCalculatorFactory() {}

    public static ShadowDeltaCalculator createDefault() {
        return new DefaultDeltaCalculator();
    }

    public static ShadowDeltaCalculator createCustom(Set<String> ignoreFields) {
        return new DefaultDeltaCalculator(ignoreFields);
    }

}
