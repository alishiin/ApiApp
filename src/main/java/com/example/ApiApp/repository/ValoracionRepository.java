package com.example.ApiApp.repository;

import com.example.ApiApp.model.Valoracion;
import com.example.ApiApp.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    List<Valoracion> findByProducto(Producto producto);
}
