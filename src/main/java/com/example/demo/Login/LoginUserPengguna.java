package com.example.demo.Login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserPengguna {
    private String username;
    private String password;
    private String nama;
    private String peran;
}