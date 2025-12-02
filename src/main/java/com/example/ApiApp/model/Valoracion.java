package com.example.ApiApp.model;

import jakarta.persistence.*;

@Entity
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private int estrellas;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // ====== Constructores ======
    public Valoracion() {}

    public Valoracion(String usuario, int estrellas, String comentario, Producto producto) {
        this.usuario = usuario;
        this.estrellas = estrellas;
        this.comentario = comentario;
        this.producto = producto;
    }

    // ====== Getters y Setters ======
    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
