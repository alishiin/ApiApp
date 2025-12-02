package com.example.ApiApp.service;

import com.example.ApiApp.model.User;
import com.example.ApiApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Registro de usuario
    public User register(String nombre, String email, String password) throws Exception {
        if (userRepository.existsByEmail(email)) {
            throw new Exception("El email ya está registrado");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(nombre, email, encodedPassword);
        return userRepository.save(user);
    }

    // Login de usuario
    public User login(String email, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Contraseña incorrecta");
        }
        return user;
    }
}
