package com.rm00.cells.strength;

import com.rm00.cells.Strength;

public class StrengthFactory {

    public static Strength getRadiusStrength(double param) {
        return getStrength(StrengthType.Radius, param);
    }

    public static Strength getPowerStrength(double param) {
        return getStrength(StrengthType.Power, param);
    }

    public static Strength getStrength(StrengthType type, double param) {
        if(type.equals(StrengthType.Radius)) {
            return new RadiusStrength(param);
        } else if (type.equals(StrengthType.Power)) {
            return new PowerStrength(param);
        } else {
            throw new IllegalArgumentException("Unknown StrengthType: " + type);
        }
    }
}
