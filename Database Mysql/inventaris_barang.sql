-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 23 Nov 2023 pada 19.04
-- Versi server: 10.4.11-MariaDB
-- Versi PHP: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventaris_barang`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mst_barang`
--

CREATE TABLE `mst_barang` (
  `kd_barang` varchar(10) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `kategori_barang` varchar(20) NOT NULL,
  `merek` varchar(50) DEFAULT NULL,
  `ukuran` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mst_barang`
--

INSERT INTO `mst_barang` (`kd_barang`, `nama_barang`, `kategori_barang`, `merek`, `ukuran`) VALUES
('B1001', 'Suntikan', '- PERALATAN MEDIS -', 'Tidak ada', '10 Cm'),
('B1002', 'Kunci Inggris', '- SERVICE -', 'Barang Haja', '18'),
('B1003', 'Rak Penyimpanan', '- LOGISTIK -', 'Tidak ada', '1 Meter'),
('B1004', 'Obat', '- PERALATAN MEDIS -', 'Paracetamol', 'Nihil'),
('B1007', 'Meja', '- FURNITURE -', 'Tidak ada', '1 Meter');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mst_lokasi`
--

CREATE TABLE `mst_lokasi` (
  `kd_lokasi` varchar(10) NOT NULL,
  `nama_lokasi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mst_lokasi`
--

INSERT INTO `mst_lokasi` (`kd_lokasi`, `nama_lokasi`) VALUES
('L001', 'Gudang'),
('L003', 'Gerasi'),
('L004', 'Ruang Penyimpanan'),
('L005', 'Ruang Makan'),
('L006', 'Ruang Tamu');

-- --------------------------------------------------------

--
-- Struktur dari tabel `mst_user`
--

CREATE TABLE `mst_user` (
  `id_user` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nama` varchar(35) DEFAULT NULL,
  `jenis_kelamin` varchar(20) DEFAULT NULL,
  `alamat` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `mst_user`
--

INSERT INTO `mst_user` (`id_user`, `username`, `password`, `nama`, `jenis_kelamin`, `alamat`) VALUES
('A1001', 'PAHRUL', 'PAHRUL123', 'MUHAMMAD PAHRUL GUNAWAN', 'LAKI LAKI', 'KAYUTANGI UJUNG'),
('A1002', 'MONIKA', 'MONIKA123', 'MONIKA', 'PEREMPUAN', 'MARGASARI'),
('A1003', 'FATIH', 'FATIH123', 'AL FATIH', 'Laki - laki', 'ALALAK UTARA');

-- --------------------------------------------------------

--
-- Struktur dari tabel `trx_inventaris_keluar`
--

CREATE TABLE `trx_inventaris_keluar` (
  `kd_inventaris_keluar` char(15) NOT NULL,
  `kd_inventaris_masuk` char(15) NOT NULL,
  `jumlah` double NOT NULL,
  `tanggal_keluar` date DEFAULT NULL,
  `keterangan` varchar(20) NOT NULL,
  `id_user` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `trx_inventaris_keluar`
--

INSERT INTO `trx_inventaris_keluar` (`kd_inventaris_keluar`, `kd_inventaris_masuk`, `jumlah`, `tanggal_keluar`, `keterangan`, `id_user`) VALUES
('IN0001', '', 1, '2023-11-23', 'SA', 'A1001'),
('IN0002', '', 10, '2023-11-23', 'HILANG', 'A1001'),
('IN0003', 'IN0004', 12, '2023-11-23', 'SCSDCSDC', 'A1001'),
('IN0004', 'IN0005', 3, '2023-11-24', 'Kaki Patah', 'A1001'),
('OUT0002', 'IN0001', 1, '2020-08-23', 'rusak', '123'),
('OUT0003', 'IN0003', 1, '2020-08-23', '-', '123'),
('OUT0004', 'IN0003', 1, '2020-08-23', 'rusak terbanting', '123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `trx_inventaris_masuk`
--

CREATE TABLE `trx_inventaris_masuk` (
  `kd_inventaris_masuk` char(15) NOT NULL,
  `kd_barang` varchar(10) NOT NULL,
  `jumlah` float NOT NULL,
  `nilai_barang` float NOT NULL,
  `tanggal_masuk` date DEFAULT NULL,
  `id_user` varchar(10) NOT NULL,
  `kd_lokasi` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `trx_inventaris_masuk`
--

INSERT INTO `trx_inventaris_masuk` (`kd_inventaris_masuk`, `kd_barang`, `jumlah`, `nilai_barang`, `tanggal_masuk`, `id_user`, `kd_lokasi`) VALUES
('IN0001', 'B1001', 1, 2, '2023-11-23', 'A1001', 'L004'),
('IN0002', 'B1002', 10, 25, '2023-11-23', 'A1002', 'L004'),
('IN0003', 'B1003', 100, 18, '2023-11-23', 'A1001', 'L003'),
('IN0004', 'B1007', 3, 10, '2023-11-24', 'A1001', 'L006');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mst_barang`
--
ALTER TABLE `mst_barang`
  ADD PRIMARY KEY (`kd_barang`);

--
-- Indeks untuk tabel `mst_lokasi`
--
ALTER TABLE `mst_lokasi`
  ADD PRIMARY KEY (`kd_lokasi`);

--
-- Indeks untuk tabel `mst_user`
--
ALTER TABLE `mst_user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indeks untuk tabel `trx_inventaris_keluar`
--
ALTER TABLE `trx_inventaris_keluar`
  ADD PRIMARY KEY (`kd_inventaris_keluar`);

--
-- Indeks untuk tabel `trx_inventaris_masuk`
--
ALTER TABLE `trx_inventaris_masuk`
  ADD PRIMARY KEY (`kd_inventaris_masuk`,`kd_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
