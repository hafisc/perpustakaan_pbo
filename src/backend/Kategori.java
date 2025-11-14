package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Class Kategori untuk handle CRUD tabel kategori.
 * Mengikuti langkah pada modul GUI-Database (Percobaan 4).
 */
public class Kategori {
    private int idkategori;
    private String nama;
    private String keterangan;

    // konstruktor default
    public Kategori() {
    }

    // konstruktor custom (tanpa idkategori)
    public Kategori(String nama, String keterangan) {
        this.nama = nama;
        this.keterangan = keterangan;
    }

    // getter & setter
    public int getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(int idkategori) {
        this.idkategori = idkategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    // getById
    public Kategori getById(int id) {
        Kategori kat = new Kategori();

        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori WHERE idkategori = '" + id + "'");

        try {
            while (rs.next()) {
                kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kat;
    }

    // getAll
    public ArrayList<Kategori> getAll() {
        ArrayList<Kategori> listKategori = new ArrayList<>();

        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori");

        try {
            while (rs.next()) {
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));

                listKategori.add(kat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listKategori;
    }

    // search
    public ArrayList<Kategori> search(String keyword) {
        ArrayList<Kategori> listKategori = new ArrayList<>();

        String sql = "SELECT * FROM kategori WHERE "
                   + "nama LIKE '%" + keyword + "%' "
                   + "OR keterangan LIKE '%" + keyword + "%'";

        ResultSet rs = DBHelper.selectQuery(sql);

        try {
            while (rs.next()) {
                Kategori kat = new Kategori();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));

                listKategori.add(kat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listKategori;
    }

    // save (insert / update)
    public void save() {
        if (getById(idkategori).getIdkategori() == 0) {
            // insert
            String sql = "INSERT INTO kategori (nama, keterangan) VALUES ("
                       + " '" + this.nama + "', "
                       + " '" + this.keterangan + "' "
                       + " )";
            this.idkategori = DBHelper.insertQueryGetId(sql);
        } else {
            // update
            String sql = "UPDATE kategori SET "
                       + " nama = '" + this.nama + "', "
                       + " keterangan = '" + this.keterangan + "' "
                       + " WHERE idkategori = '" + this.idkategori + "'";
            DBHelper.executeQuery(sql);
        }
    }

    // delete
    public void delete() {
        String sql = "DELETE FROM kategori WHERE idkategori = '" + this.idkategori + "'";
        DBHelper.executeQuery(sql);
    }

    // supaya ketika ditampilkan di combo box, yang muncul nama kategori
    @Override
    public String toString() {
        return nama;
    }
}