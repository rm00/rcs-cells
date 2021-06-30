package com.rm00.cells;

import com.rm00.cells.strength.StrengthFactory;
import com.rm00.cells.strength.StrengthType;

import java.util.Objects;

public class Cell {
    private String name;
    private Coordinates2D coordinates2D;
    private Strength strength;

    public Cell(String name, double latitude, double longitude, StrengthType strengthType, double strengthParam) {
        this.name = name;
        this.coordinates2D = new Coordinates2D(latitude, longitude);
        this.strength = StrengthFactory.getStrength(strengthType, strengthParam);
    }

    public Cell(String name, Coordinates2D coordinates2D, Strength strength) {
        this.name = name;
        this.coordinates2D = coordinates2D;
        this.strength = strength;
    }

    public double getStrengthAtDistance(double distanceInKm) {
        return strength.getStrengthValue(distanceInKm);
    }

    public double getDistanceFrom(Coordinates2D targetPoint) { return Metrics.distanceInKm(this.coordinates2D, targetPoint); }

    public double getStrengthAtCoordinates(Coordinates2D coordinates2D) { return this.getStrengthAtDistance(this.getDistanceFrom(coordinates2D)); }

    @Override
    public String toString() {
        return "Cell{" +
                "name='" + name + '\'' +
                ", coordinates2D=" + coordinates2D +
                ", strength=" + strength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return name.equals(cell.name) && coordinates2D.equals(cell.coordinates2D) && strength.equals(cell.strength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates2D, strength);
    }
}
