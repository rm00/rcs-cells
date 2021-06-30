package com.rm00.cells;

import java.sql.Timestamp;

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

}
