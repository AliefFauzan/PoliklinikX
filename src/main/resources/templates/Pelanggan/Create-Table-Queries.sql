CREATE TABLE Pasien (
    noRekamMedis SERIAL PRIMARY KEY,
    dataRekamMedis TEXT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    tanggalLahir DATE NOT NULL,
    alamat TEXT
);

CREATE TABLE Perawat (
    idPegawai SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
);

CREATE TABLE Administrasi (
    idPegawai SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL
);

CREATE TABLE Dokter (
    idPegawai SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    spesialisasi VARCHAR(100),
    koutaPasien INT,
    tarif INT
);


CREATE TABLE Transaksi (
    idTransaksi SERIAL PRIMARY KEY,
    noRekamMedis INT REFERENCES Pasien(noRekamMedis) ON DELETE CASCADE,
    hari VARCHAR(20) NOT NULL,
    keluhan TEXT,
    metodePembayaran VARCHAR(50),
    hasilDiagnosa TEXT,
    hasilPreskripsi TEXT,
    namaDokter TEXT,
    jam INT,
    tarif INT


);


CREATE TABLE JadwalDokter (
    idJadwal SERIAL PRIMARY KEY,
    idDokter INT REFERENCES Dokter(idPegawai) ON DELETE CASCADE,
    nama VARCHAR(100) NOT NULL, --dokter
    spesialisasi VARCHAR(100),
    hari VARCHAR(20) NOT NULL, -- Misalnya: Senin, Selasa
    jamMulai INT,
    jamSelesai INT
);


