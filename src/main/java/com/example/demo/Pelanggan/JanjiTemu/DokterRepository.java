package com.example.demo.Pelanggan.JanjiTemu;

import java.util.List;

public interface DokterRepository {
    List<Dokter> getAllDokters();
    Dokter getDokterById(int id);
    void saveJanjiTemu(JanjiTemu janjiTemu);
}
