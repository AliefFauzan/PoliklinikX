package com.example.demo.Perawat;

import java.util.Optional;



public interface PerawatRepo {
    Optional<PerawatModel> validateUser(String username, String password);
    boolean register(String username, String nama, String password, String confPassword);
    boolean login(String username, String password);
}