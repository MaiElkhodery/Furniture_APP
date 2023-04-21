package com.example.furniture_app;

public enum FurnitureCategory {
    CHAIR("chair"),
    TABLE("table"),
    SOFA("sofa"),
    BED("bed"),
    LAMP("lamp");

    String name;
    FurnitureCategory(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
