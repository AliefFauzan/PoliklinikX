package com.example.demo.Transaksi;

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
public class TransaksiModel {
    Long idTransaksi;
    Long noRekamMedis;
    String hari;
    String keluhan;
    String metodePembayaran;
    String hasilDiagnosa;
    String hasilPreskripsi;
    String namDokter;
    int jam;
    int tarif;


}
