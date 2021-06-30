package com.rm00.cells;

public enum CellEventType {

    Connected("Connected"),
    Disconnected("Disconnected");

    private String label;

    CellEventType(String label) {
        this.label = label;
    }

}
