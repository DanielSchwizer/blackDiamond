package com.blackdiamond.types;

public enum CleaningType {
    COCINA(0, "Cocina"), PISOS(1, "Pisos"), ROPA(2, "Ropa"), MULTIUSO(3, "Multiuso");

    private int id;
    private String type;

    private CleaningType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
