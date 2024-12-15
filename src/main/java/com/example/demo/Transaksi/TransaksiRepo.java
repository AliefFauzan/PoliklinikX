package com.example.demo.Transaksi;

import java.util.List;

import com.example.demo.Pasien.PasienModel;

public interface TransaksiRepo {
    // List<PasienModel> getAllPasiens();
   
    void addTransaction(int noRekamMedis, String namaDokter, String hari, int jam, int tarif);
    void updateHasilDiagnosa(int idTransaksi, String hasilDiagnosa);
    void updateHasilPreskripsi(int idTransaksi, String hasilPreskripsi);
    void updateTarif(int idTransaksi, int tarif);
    void updateMetodePembayaran(int idTransaksi, String metodePembayaran);
    void updateKeluhan(int idTransaksi, String keluhan);
    List<TransaksiModel> findAllTransaksi();
}
