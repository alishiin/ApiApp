package com.example.ApiApp.controller;

import com.example.ApiApp.model.Producto;
import com.example.ApiApp.model.Categoria;
import com.example.ApiApp.model.Valoracion;
import com.example.ApiApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productService.getAllProductos());
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        var producto = productService.getProductoById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Producto no encontrado"));
        }
    }

    // Obtener productos por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Categoria categoria) {
        return ResponseEntity.ok(productService.getProductosByCategoria(categoria));
    }

    // Agregar valoración
    @PostMapping("/{id}/valoraciones")
    public ResponseEntity<?> addValoracion(@PathVariable Long id, @RequestBody Valoracion valoracion) {
        try {
            Valoracion nuevaValoracion = productService.addValoracion(id, valoracion);
            return ResponseEntity.ok(nuevaValoracion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // Obtener valoraciones de un producto
    @GetMapping("/{id}/valoraciones")
    public ResponseEntity<?> getValoraciones(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.getValoracionesByProducto(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
