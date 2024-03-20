package com.house.housemanager.enums;

public enum Period {
    JOUR("Jour", 1),
    SEMAINE("Semaine", 7),
    MOIS("Mois", 31),
    TRIMESTRE("Trimestre", 90),
    SEMESTRE("Semestre", 180),
    ANNEE("Annee", 365);

    String label;
    Integer days;

    private Period(String label, Integer days) {
        this.label = label;
        this.days = days;
    }

    public String getValue() {
        return label;
    }

    public Integer getDays() {
        return days;
    }
}
