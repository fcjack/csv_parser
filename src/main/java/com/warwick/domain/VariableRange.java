package com.warwick.domain;

/**
 * Created by jackson on 18/06/17.
 */
public class VariableRange {

    private Integer min;
    private Integer max;

    public VariableRange(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public boolean isBetweenRange(Integer value) {
        return min <= value && value <= max;
    }

    public Integer getMin() {
        return min;
    }

    public Integer getMax() {
        return max;
    }

    @Override
    public String toString() {
        return String.format("Min value: %s and Max value: %s", getMin(), getMax());
    }
}
