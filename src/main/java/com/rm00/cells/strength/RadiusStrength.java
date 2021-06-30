package com.rm00.cells.strength;

import com.rm00.cells.Strength;

public class RadiusStrength extends Strength {

    RadiusStrength(double param) {
        super(StrengthType.Radius);
        super.setStrengthParam(param);
    }

    @Override
    public double getStrengthValue(double distanceInKm) {
        double r = super.getStrengthParam();
        return distanceInKm >= r ? 0 : -distanceInKm/r + 1;
    }
}
