CREATE TABLE Pasien (
    noRekamMedis SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    tanggalLahir DATE NOT NULL,
    alamat TEXT,
    umur INT,
    keluhan TEXT,
    hasilDiagnosa TEXT,
    hasilPreskripsi TEXT
);

CREATE TABLE Perawat (
    idPegawai SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL
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
    jadwalPraktek TEXT,
    tarif INT
);


CREATE TABLE Transaksi (
    idTransaksi SERIAL PRIMARY KEY,
    noRekamMedis INT REFERENCES Pasien(noRekamMedis) ON DELETE CASCADE,
    tanggal DATE NOT NULL,
    metodePembayaran VARCHAR(50)
);