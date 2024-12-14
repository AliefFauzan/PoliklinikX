package com.example.demo.Transaksi;

import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.JadwalDokter.JadwalDokterModel;
import com.lighthouse.project.Other.PenggunaModel;
import com.lighthouse.project.Tower.TowerUnitJDBC;
import com.lighthouse.project.Tower.TowerUnitModel;
import com.lighthouse.project.Tower.TowerUnitRepo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Repository
public class TransaksiJDBC {
    
    @Override
    public boolean addTransaction(String hari, PenggunaModel pelanggan, JadwalDokterModel jadwal, String keluhan, String metodePembayaran) {
    // SQL to insert a new transaction into the Transaksi table
    String sql = """
        INSERT INTO Transaksi (
            noRekamMedis, 
            hari, 
            keluhan, 
            metodePembayaran, 
            hasilDiagnosa, 
            hasilPreskripsi, 
            namaDokter, 
            jam, 
            tarif
        ) VALUES (?, ?, ?, ?, NULL, NULL, ?, ?, ?)
    """;

    // Extract necessary information
    int noRekamMedis = pelanggan.getNoRekamMedis(); // Assuming `PenggunaModel` has this method
    String namaDokter = jadwal.getNama();          // From `JadwalDokterModel`
    int jam = jadwal.getJamMulai();                // Use start time from the schedule
    int tarif = jadwal.getTarif();                 // Assuming `tarif` exists in `JadwalDokterModel`

    // Execute the insertion
    int rowsAffected = jdbcTemplate.update(sql, noRekamMedis, hari, keluhan, metodePembayaran, namaDokter, jam, tarif);

    // Return true if the insertion was successful
    return rowsAffected > 0;
}


@Override
public boolean addDiagnosa(int idTransaksi, String hasilDiagnosa) {
    // SQL query to update the hasilDiagnosa field in the Transaksi table
    String sql = """
        UPDATE Transaksi
        SET hasilDiagnosa = ?
        WHERE idTransaksi = ?
    """;

    // Execute the update query
    int rowsAffected = jdbcTemplate.update(sql, hasilDiagnosa, idTransaksi);

    // Return true if the update was successful
    return rowsAffected > 0;
}


@Override
public boolean addObat(int idTransaksi, String hasilPreskripsi) {
    // SQL query to update the hasilPreskripsi field in the Transaksi table
    String sql = """
        UPDATE Transaksi
        SET hasilPreskripsi = ?
        WHERE idTransaksi = ?
    """;

    // Execute the update query
    int rowsAffected = jdbcTemplate.update(sql, hasilPreskripsi, idTransaksi);

    // Return true if the update was successful
    return rowsAffected > 0;
}


@Override
public boolean addTarif(int idTransaksi, int tarif ) {
    // SQL query to update the hasilPreskripsi field in the Transaksi table
    String sql = """
        UPDATE Transaksi
        SET tarif = ?
        WHERE idTransaksi = ?
    """;

    // Execute the update query
    int rowsAffected = jdbcTemplate.update(sql, tarif, idTransaksi);

    // Return true if the update was successful
    return rowsAffected > 0;
}








}
