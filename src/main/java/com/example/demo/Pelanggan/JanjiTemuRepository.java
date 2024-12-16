package com.example.demo.Pelanggan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JanjiTemuRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JanjiTemuRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get list of doctors
    public List<String> getDoctors() {
        String sql = "SELECT name FROM Dokter"; // Adjust the query as per your database schema
        return jdbcTemplate.queryForList(sql, String.class);
    }

    // Get departments based on selected doctor
    public List<String> getDepartmentsByDoctor(String doctor) {
        String sql = "SELECT spesialisasi FROM Dokter WHERE username = ?";
        return jdbcTemplate.queryForList(sql, String.class, doctor);
    }

    // Save the appointment
    public int createJanjiTemu(JanjiTemu janjiTemu) {
        String sql = "INSERT INTO janji_temu (no_rekam_medis, id_dokter, tanggal, waktu, department) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, janjiTemu.getNoRekamMedis(), janjiTemu.getIdDokter(),
                java.sql.Date.valueOf(janjiTemu.getTanggal()), java.sql.Time.valueOf(janjiTemu.getWaktu()), janjiTemu.getDepartment());
    }

    // Get noRekamMedis based on username
    public String getNoRekamMedisByUsername(String username) {
        String sql = "SELECT noRekamMedis FROM Pasien WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, String.class, username);
    }
}
