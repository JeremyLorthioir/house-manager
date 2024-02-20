package com.house.housemanager.enums;

public enum TaskType {
    MENAGE("Menage"),
    RANGEMENT("Rangement"),
    LINGE("Linge");

    String label;

    private TaskType(String label) {
        this.label = label;
    }

    public String getValue() {
        return label;
    }
}
