package com.example.demo.Transaksi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Component;

import com.example.demo.Transaksi.TransaksiModel;

@Data
// @Getter
// @Setter
// @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@NoArgsConstructor  // Add this
@AllArgsConstructor
//@Component
public class TransaksiModel {
    int idTransaksi;
    int noRekamMedis;
    String hari;
    String keluhan;
    String metodePembayaran;
    String hasilDiagnosa;
    String hasilPreskripsi;
    String namaDokter;
    int jam;
    int tarif;


}
