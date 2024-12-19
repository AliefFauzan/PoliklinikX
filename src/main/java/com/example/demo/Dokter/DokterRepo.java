package com.example.demo.Dokter;

import java.util.List;
import java.util.Optional;

// import com.example.demo.Admin.AdminModel;

public interface DokterRepo {
    Optional<DokterModel> validateUser(String username, String password);
    List<DokterModel> findAllDokter();
    List<DokterModel> findDokterByNama(String nama);
    boolean register(String username, String nama, String password, String confPassword);
    boolean login(String username, String password);
    List<DokterModel> findDokterById(int idPegawai);
}
