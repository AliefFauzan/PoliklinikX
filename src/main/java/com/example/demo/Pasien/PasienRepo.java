package com.example.demo.Pasien;
import java.util.List;
import java.util.Optional;

public interface PasienRepo {
    Optional<PasienModel> validateUser(String username, String password);
    boolean register(String username, String nama, String password, String confPassword, String tanggalLahir, String alamat);
    String generateNoRekamMedis();
    boolean login(String username, String password);
    void updateDataRekamMedis(int noRekamMedis, String dataRekamMedis);
    // List<PasienModel> findAllPasien();
}