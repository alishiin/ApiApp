package com.example.ApiApp.service;

import com.example.ApiApp.model.Producto;
import com.example.ApiApp.model.Categoria;
import com.example.ApiApp.model.Valoracion;
import com.example.ApiApp.repository.ProductoRepository;
import com.example.ApiApp.repository.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ValoracionRepository valoracionRepository;

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Obtener productos por categoría
    public List<Producto> getProductosByCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    // Agregar una valoración a un producto
    public Valoracion addValoracion(Long productoId, Valoracion valoracion) throws Exception {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new Exception("Producto no encontrado"));
        valoracion.setProducto(producto);
        return valoracionRepository.save(valoracion);
    }

    // Obtener valoraciones de un producto
    public List<Valoracion> getValoracionesByProducto(Long productoId) throws Exception {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new Exception("Producto no encontrado"));
        return valoracionRepository.findByProducto(producto);
    }

    // Guardar producto
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar producto
    public Producto updateProducto(Long id, Producto productoDetails) throws Exception {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado"));

        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setCategoria(productoDetails.getCategoria());
        producto.setImagenUrl(productoDetails.getImagenUrl());
        producto.setTallas(productoDetails.getTallas());
        producto.setMedidas(productoDetails.getMedidas());

        return productoRepository.save(producto);
    }

    // Eliminar producto
    public void deleteProducto(Long id) throws Exception {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado"));
        productoRepository.delete(producto);
    }





}
