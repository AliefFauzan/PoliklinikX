DROP TABLE Perawat;
DROP TABLE Dokter;
DROP TABLE Transaksi;
DROP TABLE Administrasi;
DROP TABLE JadwalDokter;
DROP TABLE Pasien;

CREATE TABLE Pasien (
    noRekamMedis VARCHAR(255) PRIMARY KEY,
    dataRekamMedis VARCHAR(255) NULL,
    username VARCHAR(50) NOT NULL UNIQUE,  -- Menambahkan UNIQUE constraint untuk username
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    roles VARCHAR(50)
);


CREATE TABLE Perawat (
    idPegawai SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- Menambahkan UNIQUE constraint untuk username
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    roles VARCHAR(50)
);

CREATE TABLE Administrasi (
    idPegawai SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- Menambahkan UNIQUE constraint untuk username
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    roles VARCHAR(50)
);


CREATE TABLE Dokter (
    idPegawai SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,  -- Menambahkan UNIQUE constraint untuk username
    password VARCHAR(50) NOT NULL,
    nama VARCHAR(100) NOT NULL,
    spesialisasi VARCHAR(100),
    koutaPasien INT,
    jadwalPraktek DATE,
    tarif INT,
    roles VARCHAR(50)
);

CREATE TABLE Transaksi (
    idTransaksi SERIAL PRIMARY KEY,
    noRekamMedis VARCHAR(255) REFERENCES Pasien(noRekamMedis) ON DELETE CASCADE,
    tanggal DATE NOT NULL,
    keluhan TEXT,
    metodePembayaran VARCHAR(50),
    hasilDiagnosa TEXT,
    hasilPreskripsi TEXT,
    idDokter INT REFERENCES Dokter(idPegawai) ON DELETE CASCADE,  -- Menghubungkan transaksi dengan dokter
    jam TIME NOT NULL
);



CREATE TABLE JadwalDokter (
    idJadwal SERIAL PRIMARY KEY,
    idDokter INT REFERENCES Dokter(idPegawai) ON DELETE CASCADE,
    hari VARCHAR(20) NOT NULL, -- Misalnya: Senin, Selasa
    jamMulai TIME NOT NULL,
    jamSelesai TIME NOT NULL
);

CREATE TABLE janji_temu (
    id SERIAL PRIMARY KEY,  -- ID unik untuk setiap janji temu
    noRekamMedis VARCHAR(255) REFERENCES Pasien(noRekamMedis) ON DELETE CASCADE,  -- Mengacu pada Pasien
    idDokter INT REFERENCES Dokter(idPegawai) ON DELETE CASCADE,  -- Mengacu pada Dokter
    tanggal DATE NOT NULL,  -- Tanggal janji temu
    waktu TIME NOT NULL,  -- Waktu janji temu
    department VARCHAR(100),  -- Departemen berdasarkan spesialisasi dokter
    CONSTRAINT unique_janji_temu UNIQUE (noRekamMedis, tanggal, waktu, idDokter)  -- Menghindari pasien membuat janji temu ganda
);
