package com.example.demo.JadwalDokter;

import com.example.demo.Dokter.DokterModel;
import java.util.List;

public interface JadwalDokterRepo {
        List<DokterModel> findAllDokter();

    List<JadwalDokterModel> findAllJadwalDokter();

    boolean addJadwalDokterData(JadwalDokterModel jadwalDokter);

    List<JadwalDokterModel> findJadwalDokterBySpesialisasi(String spesialisasi);

    List<JadwalDokterModel> findJadwalDokterByNamaAndSpesialisasi(String nama, String spesialisasi);

    boolean changeJadwalDokterData(
        long idJadwal,
        String originalHari,
        int originalJamMulai,
        int originalJamSelesai,
        String editHari,
        int editJamMulai,
        int editJamSelesai
    );
}
