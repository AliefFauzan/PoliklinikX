-- Insert dummy data for Pasien
INSERT INTO Pasien (dataRekamMedis, username, password, nama, tanggalLahir, alamat)
VALUES
('Rekam medis pasien A', 'pasienA', 'passwordA', 'Ahmad Rizky', '1990-05-15', 'Jl. Sejahtera No. 10, Jakarta'),
('Rekam medis pasien B', 'pasienB', 'passwordB', 'Budi Santoso', '1985-02-25', 'Jl. Merdeka No. 20, Bandung'),
('Rekam medis pasien C', 'pasienC', 'passwordC', 'Citra Dewi', '1992-11-03', 'Jl. Raya No. 30, Surabaya');

-- Insert dummy data for Perawat
INSERT INTO Perawat (username, password, nama)
VALUES
('perawatA', 'passwordA', 'Siti Aminah'),
('perawatB', 'passwordB', 'Dewi Lestari'),
('perawatC', 'passwordC', 'Rina Hidayat');

-- Insert dummy data for Administrasi
INSERT INTO Administrasi (username, password, nama)
VALUES
('adminA', 'passwordA', 'Rizal Hidayat'),
('adminB', 'passwordB', 'Lina Susanti');

-- Insert dummy data for Dokter
INSERT INTO Dokter (username, password, nama, spesialisasi, kuotaPasien, tarif)
VALUES
('dokterA', 'passwordA', 'Dr. Ali Fikri', 'Spesialis Bedah', 30, 500000),
('dokterB', 'passwordB', 'Dr. Budi Setiawan', 'Spesialis Anak', 40, 400000),
('dokterC', 'passwordC', 'Dr. Candra Wibowo', 'Spesialis Jantung', 25, 700000);

-- Insert dummy data for Transaksi
INSERT INTO Transaksi (noRekamMedis, hari, keluhan, metodePembayaran, hasilDiagnosa, hasilPreskripsi, namaDokter, jam, tarif)
VALUES
(1, 'Senin', 'Sakit kepala', 'Tunai', 'Migrain', 'Paracetamol 500mg', 'Dr. Ali Fikri', 9, 500000),
(2, 'Selasa', 'Demam', 'Transfer', 'Flu', 'Obat Flu', 'Dr. Budi Setiawan', 11, 400000),
(3, 'Rabu', 'Nyeri dada', 'Kartu Kredit', 'Angina', 'Obat Jantung', 'Dr. Candra Wibowo', 15, 700000);

-- Insert dummy data for JadwalDokter
INSERT INTO JadwalDokter (idDokter, nama, spesialisasi, hari, jamMulai, jamSelesai)
VALUES
(1, 'Dr. Ali Fikri', 'Spesialis Bedah', 'Senin', 8, 12),
(2, 'Dr. Budi Setiawan', 'Spesialis Anak', 'Selasa', 9, 13),
(3, 'Dr. Candra Wibowo', 'Spesialis Jantung', 'Rabu', 10, 14);