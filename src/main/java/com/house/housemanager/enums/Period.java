package com.house.housemanager.enums;

public enum Period {
    JOUR("Jour"),
    SEMAINE("Semaine"),
    MOIS("Mois"),
    TRIMESTRE("Trimestre"),
    SEMESTRE("Semestre"),
    ANNEE("Annee");

    String label;
    Integer days;

    private Period(String label) {
        this.label = label;
    }

    public String getValue() {
        return label;
    }
}
