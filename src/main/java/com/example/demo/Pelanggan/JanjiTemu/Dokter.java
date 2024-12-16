package com.example.demo.Pelanggan.JanjiTemu;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dokter {
    private int idPegawai;
    private String username;
    private String password;
    private String nama;
    private String spesialisasi;
    private int koutaPasien;
    private java.sql.Date jadwalPraktek;
    private int tarif;
    private String roles;
}
