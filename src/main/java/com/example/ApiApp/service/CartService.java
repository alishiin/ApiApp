package com.example.ApiApp.service;

import com.example.ApiApp.model.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    // Para simplificar, cada usuario tendría su propio carrito en memoria
    // En un sistema real se usaría base de datos
    private final List<Producto> carrito = new ArrayList<>();

    // Agregar producto al carrito
    public void addToCart(Producto producto) {
        carrito.add(producto);
    }

    // Eliminar producto del carrito
    public boolean removeFromCart(Long productoId) {
        return carrito.removeIf(p -> p.getId().equals(productoId));
    }

    // Obtener productos del carrito
    public List<Producto> getCart() {
        return new ArrayList<>(carrito);
    }

    // Vaciar carrito
    public void clearCart() {
        carrito.clear();
    }

    // Calcular total
    public int getTotal() {
        return carrito.stream().mapToInt(Producto::getPrecio).sum();
    }
}
