package com.example.demo.Dokter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class DokterModel {
    int idPegawai;
   
    String username;
    String password;
    String nama;
    String spesialisasi;
    int kuotaPasien;
    int tarif;


}