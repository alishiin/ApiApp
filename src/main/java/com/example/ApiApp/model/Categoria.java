package com.example.ApiApp.model;

public enum Categoria {

    POLERAS("Poleras"),
    PANTALONES("Pantalones"),
    POLERONES("Polerones"),
    CUADROS("Cuadros");

    private final String displayName;

    Categoria(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
