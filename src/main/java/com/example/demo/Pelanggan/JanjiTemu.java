package com.example.demo.Pelanggan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JanjiTemu {
    private String noRekamMedis;
    private int idDokter;
    private LocalDate tanggal;
    private LocalTime waktu;
    private String department;
}
