package com.example.demo.Register;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String nama;
    private String peran;
    private String noRekamMedis; // Tambahkan atribut ini
}
