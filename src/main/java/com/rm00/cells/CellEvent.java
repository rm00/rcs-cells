package com.rm00.cells;

import com.rm00.cells.strength.StrengthFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CellEvent {
    private final Cell cell;
    private final CellEventType cellEventType;
    private final Timestamp timestamp;

    public CellEvent(Cell cell, CellEventType cellEventType, Timestamp timestamp) {
        this.cell = cell;
        this.cellEventType = cellEventType;
        this.timestamp = timestamp;
    }

    public Cell getCell() {
        return cell;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "CellEvent{" +
                "cell=" + cell +
                ",\ncellEventType=" + cellEventType +
                ",\ntimestamp=" + timestamp +
                '}';
    }

    public static void main(String[] args) {
        Cell a = new Cell("A", new Coordinates2D(45., 8.), StrengthFactory.getRadiusStrength(10.));
        CellEvent ce1 = new CellEvent(a, CellEventType.Connected, Timestamp.valueOf(LocalDateTime.of(2010, 1, 30, 23, 55)));
        System.out.println(ce1);
    }
}
