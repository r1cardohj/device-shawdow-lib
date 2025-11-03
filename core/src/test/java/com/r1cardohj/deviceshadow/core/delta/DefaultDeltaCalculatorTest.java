package com.r1cardohj.deviceshadow.core.delta;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class DefaultDeltaCalculatorTest {

    @Test
    void testCalculateDeltaNoDifferences() {
        Map<String, Object> reported = Map.of("key1", "value1", "key2", "value2");
        Map<String, Object> desired = Map.of("key1", "value1", "key2", "value2");

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertTrue(delta.getDeltaMap().isEmpty());
    }

    @Test
    void testCalculateDeltaWithDifferences() {
        Map<String, Object> reported = Map.of("key1", "value1", "key2", "value2", "key3", "value3");
        Map<String, Object> desired = Map.of("key1", "value1", "key2", "value2");

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertTrue(delta.getDeltaMap().isEmpty());
    }

    @Test
    void testCalculateDeltaWithNoNumericalDifferences() {

        Map<String, Object> desired = Map.of("key1", 123, "key2", 456.789);
        Map<String, Object> reported = Map.of("key1", 123, "key2", 456.789);

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertTrue(delta.getDeltaMap().isEmpty());
    }

    @Test
    void testCalculateDeltaWithNumericalDifferences() {
        Map<String, Object> reported = Map.of("key1", 123.456, "key2", 456.789);
        Map<String, Object> desired = Map.of("key1", 123.456, "key2", 456.790);

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertFalse(delta.getDeltaMap().isEmpty());
        assertEquals(Map.of("key2", 456.790), delta.getDeltaMap());
    }

    @Test
    void testCalculateDeltaWithNumericalDifferencesMore() {

        Map<String, Object> reported = Map.of("key1", 123.456, "key2", 456.789, "key3", 789.123);
        Map<String, Object> desired = Map.of("key1", 123.456, "key2", 456.790);

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertFalse(delta.getDeltaMap().isEmpty());
        assertEquals(Map.of("key2", 456.790), delta.getDeltaMap());
    }

    @Test
    void testCalculateDeltaWithNumericalDifferencesLess() {
        Map<String, Object> reported = Map.of("key1", 123.456, "key2", 456.789);
        Map<String, Object> desired = Map.of("key1", 123.456, "key2", 456.790, "key3", 789.123);

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertFalse(delta.getDeltaMap().isEmpty());
        assertEquals(Map.of("key3", 789.123, "key2", 456.790), delta.getDeltaMap());
    }

    @Test
    void testCalculateDeltaWithNumericalZeroDifferences() {
        Map<String, Object> reported = Map.of("key1", 1, "key2", 1.2);
        Map<String, Object> desired = Map.of("key1", 0, "key2", 1.2);

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertFalse(delta.getDeltaMap().isEmpty());
        assertEquals(Map.of("key1", 0), delta.getDeltaMap());
    }

    @Test
    void testCalculateDeltaWithDifferentTypes() {
        Map<String, Object> reported = Map.of("key1", 1, "key2", "value2");
        Map<String, Object> desired = Map.of("key1", 1, "key2", 1.2);

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);

        assertFalse(delta.getDeltaMap().isEmpty());
        assertEquals(Map.of("key2", 1.2), delta.getDeltaMap());
    }

    @Test
    void testCalculateDeltaWithNullValues() {
        Map<String, Object> reported = new HashMap<>();
        reported.put("key1", null);
        reported.put("key2", null);
        Map<String, Object> desired = Map.of("key1", 1, "key2", 1.2);

        var calculator = DeltaCalculatorFactory.createDefault();

        var delta = calculator.calculateDelta(desired, reported);
        assertFalse(delta.getDeltaMap().isEmpty());
        assertEquals(Map.of("key1", 1, "key2", 1.2), delta.getDeltaMap());
    }
}
