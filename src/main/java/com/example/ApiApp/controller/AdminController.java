package com.example.ApiApp.controller;

import com.example.ApiApp.model.Producto;
import com.example.ApiApp.model.Categoria;
import com.example.ApiApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private ProductService productService;

    // Crear nuevo producto
    @PostMapping("/productos")
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productService.saveProducto(producto);
        return ResponseEntity.ok(nuevoProducto);
    }

    // Actualizar producto existente
    @PutMapping("/productos/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto actualizado = productService.updateProducto(id, producto);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Eliminar producto
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        try {
            productService.deleteProducto(id);
            return ResponseEntity.ok(Map.of("message", "Producto eliminado"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Obtener todos los productos (opcional para admin)
    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productService.getAllProductos());
    }
}
