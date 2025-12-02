package com.example.ApiApp.repository;

import com.example.ApiApp.model.Producto;
import com.example.ApiApp.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);
}
