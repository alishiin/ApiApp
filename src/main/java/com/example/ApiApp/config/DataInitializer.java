package com.example.ApiApp.config;

import com.example.ApiApp.model.Producto;
import com.example.ApiApp.model.Categoria;
import com.example.ApiApp.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(ProductoRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) { // solo si no hay productos
                Producto p1 = new Producto(
                        "Polera Negra",
                        15000,
                        "Polera negra básica de algodón",
                        "https://example.com/polera_negra.jpg",
                        Categoria.POLERAS,
                        Arrays.asList("S", "M", "L"),
                        Arrays.asList("M")
                );

                Producto p2 = new Producto(
                        "Polerón Gris",
                        25000,
                        "Polerón gris con capucha",
                        "https://example.com/poleron_gris.jpg",
                        Categoria.POLERONES,
                        Arrays.asList("M", "L", "XL"),
                        Arrays.asList("M")
                );

                Producto p3 = new Producto(
                        "Gorra Azul",
                        8000,
                        "Gorra deportiva azul",
                        "https://example.com/gorra_azul.jpg",
                        Categoria.POLERAS,
                        Arrays.asList("Única"),
                        Arrays.asList()
                );

                productRepository.saveAll(Arrays.asList(p1, p2, p3));
            }
        };
    }
}
