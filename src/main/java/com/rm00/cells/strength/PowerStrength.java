package com.rm00.cells.strength;

import com.rm00.cells.Strength;

public class PowerStrength extends Strength {

    PowerStrength(double param) {
        super(StrengthType.Power);
        super.setStrengthParam(param);
    }

    @Override
    public double getStrengthValue(double distanceInKm) {
        return 1 / Math.pow(super.getStrengthParam(), distanceInKm);    }
}
