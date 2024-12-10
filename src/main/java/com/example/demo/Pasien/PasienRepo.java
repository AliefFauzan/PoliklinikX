package com.example.demo.Pasien;
import java.util.Optional;

public interface PasienRepo {
    Optional<PasienModel> validateUser(String username, String password);
    boolean register(String username, String nama, String password, String confPassword, String tanggalLahir, String alamat);
    boolean login(String username, String password);
}
