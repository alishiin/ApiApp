package com.example.ApiApp.controller;

import com.example.ApiApp.model.Producto;
import com.example.ApiApp.service.CartService;
import com.example.ApiApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    // Obtener carrito
    @GetMapping
    public ResponseEntity<List<Producto>> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    // Agregar producto al carrito
    @PostMapping("/add/{productoId}")
    public ResponseEntity<?> addToCart(@PathVariable Long productoId) {
        return productService.getProductoById(productoId)
                .map(producto -> {
                    cartService.addToCart(producto);
                    return ResponseEntity.ok().body("Producto agregado al carrito");
                })
                .orElse(ResponseEntity.badRequest().body("Producto no encontrado"));
    }

    // Eliminar producto del carrito
    @DeleteMapping("/remove/{productoId}")
    public ResponseEntity<?> removeFromCart(@PathVariable Long productoId) {
        boolean removed = cartService.removeFromCart(productoId);
        if (removed) {
            return ResponseEntity.ok().body("Producto eliminado del carrito");
        } else {
            return ResponseEntity.badRequest().body("Producto no encontrado en el carrito");
        }
    }

    // Vaciar carrito
    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart() {
        cartService.clearCart();
        return ResponseEntity.ok().body("Carrito vaciado");
    }

    // Total del carrito
    @GetMapping("/total")
    public ResponseEntity<?> getTotal() {
        return ResponseEntity.ok(cartService.getTotal());
    }
}
