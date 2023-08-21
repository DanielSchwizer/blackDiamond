package com.blackdiamond.types;

public enum PackagingType {
    PLASTICO(0, "plastico"), VIDRIO(1, "vidrio"), LATA(2, "lata");

    private int id;
    private String type;

    private PackagingType(int id, String type) {
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
