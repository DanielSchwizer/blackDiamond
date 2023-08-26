package com.blackdiamond.types;

public enum CleaningType {
    COCINA("Cocina"), PISOS("Pisos"), ROPA("Ropa"), MULTIUSO("Multiuso");
    private String type;

    private CleaningType(String type) {

        this.type = type;
    }
    public String getType() {
        return type;
    }
}
