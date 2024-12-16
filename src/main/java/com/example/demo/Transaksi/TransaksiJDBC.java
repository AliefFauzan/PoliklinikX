package com.example.demo.Transaksi;

import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.example.demo.Pasien.PasienModel;


import com.example.demo.JadwalDokter.JadwalDokterModel;
import com.example.demo.Pasien.PasienModel;
// import com.lighthouse.project.Other.PenggunaModel;
// import com.lighthouse.project.Tower.TowerUnitJDBC;
// import com.lighthouse.project.Tower.TowerUnitModel;
// import com.lighthouse.project.Tower.TowerUnitRepo;

// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;
// import lombok.experimental.FieldDefaults;



@Repository
public class TransaksiJDBC implements TransaksiRepo {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        private TransaksiModel TransaksiModel;


    //
//     @Override
//     public boolean addTransaction(String hari, PenggunaModel pelanggan, JadwalDokterModel jadwal, String keluhan, String metodePembayaran) {
//     // SQL to insert a new transaction into the Transaksi table
//     String sql = """
//         INSERT INTO Transaksi (
//             noRekamMedis, 
//             hari, 
//             keluhan, 
//             metodePembayaran, 
//             hasilDiagnosa, 
//             hasilPreskripsi, 
//             namaDokter, 
//             jam, 
//             tarif
//         ) VALUES (?, ?, ?, ?, NULL, NULL, ?, ?, ?)
//     """;

//     // Extract necessary information
//     int noRekamMedis = pelanggan.getNoRekamMedis(); // Assuming `PenggunaModel` has this method
//     String namaDokter = jadwal.getNama();          // From `JadwalDokterModel`
//     int jam = jadwal.getJamMulai();                // Use start time from the schedule
//     int tarif = jadwal.getTarif();                 // Assuming `tarif` exists in `JadwalDokterModel`

//     // Execute the insertion
//     int rowsAffected = jdbcTemplate.update(sql, noRekamMedis, hari, keluhan, metodePembayaran, namaDokter, jam, tarif);

//     // Return true if the insertion was successful
//     return rowsAffected > 0;
// }



















    //  public List<PasienModel> getAllPasiens() {
    //     String sql = "SELECT * FROM Pasien";
    //     //experimental
    //     return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pasien.class));
    // 
    @Override
    public void addTransaction(int noRekamMedis, String namaDokter, String hari, int jam, int tarif) {
        String sql = "INSERT INTO Transaksi(noRekamMedis, namaDokter, hari, jam, tarif) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, noRekamMedis, namaDokter, hari, jam, tarif);
    }


// @Override
// public boolean addDiagnosa(int idTransaksi, String hasilDiagnosa) {
//     // SQL query to update the hasilDiagnosa field in the Transaksi table
//     String sql = """
//         UPDATE Transaksi
//         SET hasilDiagnosa = ?
//         WHERE idTransaksi = ?
//     """;

//     // Execute the update query
//     int rowsAffected = jdbcTemplate.update(sql, hasilDiagnosa, idTransaksi);

//     // Return true if the update was successful
//     return rowsAffected > 0;
// }

@Override
 public void updateHasilDiagnosa(int idTransaksi, String hasilDiagnosa) {
    String sql = "UPDATE Transaksi SET hasilDiagnosa = ? WHERE idTransaksi = ?";
    jdbcTemplate.update(sql, hasilDiagnosa, idTransaksi);

}
@Override
public void updateHasilPreskripsi(int idTransaksi, String hasilPreskripsi) {
    String sql = "UPDATE Transaksi SET hasilPreskripsi = ? WHERE idTransaksi = ?";
    jdbcTemplate.update(sql, hasilPreskripsi, idTransaksi);
}




// @Override
// public boolean addObat(int idTransaksi, String hasilPreskripsi) {
//     // SQL query to update the hasilPreskripsi field in the Transaksi table
//     String sql = """
//         UPDATE Transaksi
//         SET hasilPreskripsi = ?
//         WHERE idTransaksi = ?
//     """;

//     // Execute the update query
//     int rowsAffected = jdbcTemplate.update(sql, hasilPreskripsi, idTransaksi);

//     // Return true if the update was successful
//     return rowsAffected > 0;
// }


// @Override
// public boolean addTarif(int idTransaksi, int tarif ) {
//     // SQL query to update the hasilPreskripsi field in the Transaksi table
//     String sql = """
//         UPDATE Transaksi
//         SET tarif = ?
//         WHERE idTransaksi = ?
//     """;

//     // Execute the update query
//     int rowsAffected = jdbcTemplate.update(sql, tarif, idTransaksi);

//     // Return true if the update was successful
//     return rowsAffected > 0;
// }
@Override
public void updateTarif(int idTransaksi, int tarif) {
    String sql = "UPDATE Transaksi SET tarif = ? WHERE idTransaksi = ?";
    jdbcTemplate.update(sql, tarif, idTransaksi);
}

@Override
public void updateMetodePembayaran(int idTransaksi, String metodePembayaran) {
    String sql = "UPDATE Transaksi SET metodePembayaran = ? WHERE idTransaksi = ?";
    jdbcTemplate.update(sql, metodePembayaran, idTransaksi);
}

@Override
public void updateKeluhan(int idTransaksi, String keluhan) {
    String sql = "UPDATE Transaksi SET keluhan = ? WHERE idTransaksi = ?";
    jdbcTemplate.update(sql, keluhan, idTransaksi);
}

@Override
public List<TransaksiModel> findAllTransaksi() {
    String sql = """
        SELECT 
            t.idTransaksi, 
            t.noRekamMedis, 
            t.hari, 
            t.keluhan, 
            t.metodePembayaran, 
            t.hasilDiagnosa, 
            t.hasilPreskripsi, 
            t.namaDokter, 
            t.jam, 
            t.tarif
        FROM 
            Transaksi t
        JOIN 
            Pasien p ON t.noRekamMedis = p.noRekamMedis
    """;

    return jdbcTemplate.query(sql, this::mapRowToTransaksi);
}

private TransaksiModel mapRowToTransaksi(ResultSet rSet, int rowNum) throws SQLException {
    return new TransaksiModel(
        rSet.getInt("idTransaksi"),
        rSet.getInt("noRekamMedis"),
        // rSet.getString("pasienNama"),
        rSet.getString("hari"),
        rSet.getString("keluhan"),
        rSet.getString("metodePembayaran"),
        rSet.getString("hasilDiagnosa"),
        rSet.getString("hasilPreskripsi"),
        rSet.getString("namaDokter"),
        rSet.getInt("jam"),
        rSet.getInt("tarif")
    );
}












}
