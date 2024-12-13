package com.example.demo.JadwalDokter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@FieldDefaults(makeFinal = false)
@AllArgsConstructor
public class JadwalDokterModel {
    Long idJadwal;
    Long idDokter;
    String nama; //dokter
    String spesialisasi;
    String hari;
    int jamMulai;
    int jamSelesai;
    // idJadwal SERIAL PRIMARY KEY,
    // idDokter INT REFERENCES Dokter(idPegawai) ON DELETE CASCADE,
    // hari VARCHAR(20) NOT NULL, -- Misalnya: Senin, Selasa
    // jamMulai TIME NOT NULL,
    // jamSelesai TIME NOT NULL

}
