package com.example.demo.Pelanggan.JanjiTemu;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JanjiTemu {
    private int id;
    private String noRekamMedis;
    private int idDokter;
    private Date tanggal;
    private Time waktu;
    private String department;
}
