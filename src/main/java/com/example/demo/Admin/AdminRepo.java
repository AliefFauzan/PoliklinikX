package com.example.demo.Admin;

import java.util.Optional;

// import com.example.demo.Admin.AdminModel;

public interface AdminRepo {
    Optional<AdminModel> validateUser(String username, String password);
    boolean register(String username, String nama, String password, String confPassword);
    boolean login(String username, String password);
}
