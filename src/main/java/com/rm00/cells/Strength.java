package com.rm00.cells;

import com.rm00.cells.strength.StrengthType;

import java.util.Objects;

public abstract class Strength {
    private final StrengthType strengthType;
    private double strengthParam;

    public Strength(StrengthType strengthType) {
        this.strengthType=strengthType;
    }

    public abstract double getStrengthValue(double distanceInKm);

    public double getStrengthParam() {
        return strengthParam;
    }

    public void setStrengthParam(double strengthParam) {
        this.strengthParam = strengthParam;
    }

    @Override
    public String toString() {
        return "Strength{" +
                "type=" + strengthType +
                ", param=" + strengthParam +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Strength strength = (Strength) o;
        return Double.compare(strength.strengthParam, strengthParam) == 0 && strengthType == strength.strengthType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strengthType, strengthParam);
    }
}
