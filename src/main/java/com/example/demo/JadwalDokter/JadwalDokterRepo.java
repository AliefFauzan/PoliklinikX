package com.example.demo.JadwalDokter;

// import com.example.demo.Dokter.Dokter;
import com.example.demo.Dokter.DokterModel;
import java.util.List;

public interface JadwalDokterRepo {
    // List<Dokter> getAllDokters();
    public void addJadwalDokter(int idDokter, String hari, int jamMulai, int jamSelesai);

    public void updateJadwalDokter(int idJadwal, String hari, int jamMulai, int jamSelesai);
        List<DokterModel> findAllDokter();

    List<JadwalDokterModel> findAllJadwalDokter();

    // boolean addJadwalDokterData(JadwalDokterModel jadwalDokter);

    List<JadwalDokterModel> findJadwalDokterBySpesialisasi(String spesialisasi);

    List<JadwalDokterModel> findJadwalDokterByNamaAndSpesialisasi(String nama, String spesialisasi);

    // boolean changeJadwalDokterData(
    //     long idJadwal,
    //     String originalHari,
    //     int originalJamMulai,
    //     int originalJamSelesai,
    //     String editHari,
    //     int editJamMulai,
    //     int editJamSelesai
    // );
}
