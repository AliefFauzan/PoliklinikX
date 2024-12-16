package com.example.demo.Pelanggan.JanjiTemu;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcDokterRepository implements DokterRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcDokterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Dokter> getAllDokters() {
        String sql = "SELECT * FROM Dokter";
        return jdbcTemplate.query(sql, this::mapDokterRow);
    }

    @Override
    public Dokter getDokterById(int id) {
        String sql = "SELECT * FROM Dokter WHERE idPegawai = ?";
        return jdbcTemplate.queryForObject(sql, this::mapDokterRow, id);  // Use varargs for parameters
    }


    @Override
    public void saveJanjiTemu(JanjiTemu janjiTemu) {
        String sql = "INSERT INTO janji_temu (noRekamMedis, idDokter, tanggal, waktu, department) " +
                     "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, janjiTemu.getNoRekamMedis(), janjiTemu.getIdDokter(),
                janjiTemu.getTanggal(), janjiTemu.getWaktu(), janjiTemu.getDepartment());
    }

    // Separate method to map Dokter row
    private Dokter mapDokterRow(ResultSet rs, int rowNum) throws SQLException {
        Dokter dokter = new Dokter();
        dokter.setIdPegawai(rs.getInt("idPegawai"));
        dokter.setUsername(rs.getString("username"));
        dokter.setPassword(rs.getString("password"));
        dokter.setNama(rs.getString("nama"));
        dokter.setSpesialisasi(rs.getString("spesialisasi"));
        dokter.setKoutaPasien(rs.getInt("koutaPasien"));
        dokter.setJadwalPraktek(rs.getDate("jadwalPraktek"));
        dokter.setTarif(rs.getInt("tarif"));
        dokter.setRoles(rs.getString("roles"));
        return dokter;
    }

}
