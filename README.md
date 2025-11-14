# Perpustakaan_PBO – Modul Kategori Saja

Repo ini isinya **hanya bagian Kategori** sesuai modul GUI-Database (backend + GUI),
jadi pas banget buat kamu yang minggu ini cuma diminta ngerjain Kategori.

## Struktur Folder

- `src/backend`
  - `DBHelper.java` – helper koneksi ke MySQL (sesuai kode di modul)
  - `Kategori.java` – class model `kategori` lengkap dengan `getById()`, `getAll()`, `search()`, `save()`, `delete()`
- `src/frontend`
  - `TestBackend.java` – main sederhana buat ngetes class `Kategori` lewat console
  - `FrmKategori.java` – form Swing buat CRUD data kategori

## Database

Pastikan sudah punya database `dbperpus` di MySQL/XAMPP, dengan tabel `kategori`:

```sql
CREATE DATABASE dbperpus;
USE dbperpus;

CREATE TABLE kategori (
  idkategori  INT AUTO_INCREMENT PRIMARY KEY,
  nama        VARCHAR(100),
  keterangan  VARCHAR(255)
);
```

## Cara Import di NetBeans

1. `File → New Project → Java with Ant → Java Project with Existing Sources`.
2. Pilih folder project ini (`Perpustakaan_PBO_Kategori`).
3. Pada bagian **Source Package Folders**, arahkan ke folder `src`.
4. Selesai, klik Finish.

## Tambah Library MySQL JDBC

1. Di panel Projects, klik kanan project → **Properties**.
2. Pilih **Libraries** → klik **Add JAR/Folder**.
3. Pilih file **mysql-connector-java** (driver MySQL).
4. OK.

## Setting Koneksi

Jika pakai XAMPP default:

- DB name: `dbperpus`
- host: `localhost`
- port: `3306`
- user: `root`
- password: *kosong*

Maka `DBHelper.java` tidak perlu diubah.
Kalau setting MySQL kamu beda, edit bagian ini di `backend/DBHelper.java`:

```java
String url = "jdbc:mysql://localhost:3306/dbperpus";
String user = "root";
String password = "";
```

## Cara Ngetes

### 1. Test backend (console)

- Klik kanan `frontend/TestBackend.java` → **Run File**.
- Lihat output di console:
  - Insert beberapa kategori,
  - update satu kategori,
  - delete satu kategori,
  - tampilkan semua kategori,
  - test `search("ilmiah")`.

### 2. Test GUI FrmKategori

- Klik kanan `frontend/FrmKategori.java` → **Run File**.
- Form bisa dipakai untuk:
  - Tambah kategori baru (`Tambah Baru` → isi Nama & Keterangan → `Simpan`),
  - Edit data (klik baris di tabel → ubah Nama/Keterangan → `Simpan`),
  - Hapus data (`Hapus`),
  - Cari berdasarkan keyword (`txtCari` + tombol `Cari`).

Silakan pakai project ini sebagai referensi waktu bikin branch `Percobaan-3.4` / `Percobaan-Kategori`
di repo GitHub kamu.