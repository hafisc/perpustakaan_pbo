package frontend;

import backend.Kategori;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * FrmKategori: GUI sederhana untuk CRUD data kategori.
 * Mengikuti langkah pada modul (Percobaan 6), namun layout disusun manual.
 */
public class FrmKategori extends JFrame {

    private javax.swing.JTextField txtIdKategori;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtCari;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambahBaru;
    private javax.swing.JButton btnCari;
    private javax.swing.JTable tblKategori;

    public FrmKategori() {
        initComponents();
        kosongkanForm();
        tampilkanData();
    }

    private void initComponents() {
        setTitle("Form Kategori");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        javax.swing.JLabel lblId = new javax.swing.JLabel("ID Kategori");
        javax.swing.JLabel lblNama = new javax.swing.JLabel("Nama");
        javax.swing.JLabel lblKet = new javax.swing.JLabel("Keterangan");
        javax.swing.JLabel lblCari = new javax.swing.JLabel("Cari");

        txtIdKategori = new javax.swing.JTextField();
        txtIdKategori.setEnabled(false);
        txtNama = new javax.swing.JTextField();
        txtKeterangan = new javax.swing.JTextField();
        txtCari = new javax.swing.JTextField();

        btnSimpan = new javax.swing.JButton("Simpan");
        btnHapus = new javax.swing.JButton("Hapus");
        btnTambahBaru = new javax.swing.JButton("Tambah Baru");
        btnCari = new javax.swing.JButton("Cari");

        tblKategori = new javax.swing.JTable();
        tblKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[] {"ID", "Nama", "Keterangan"}
        ));
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(tblKategori);

        // posisi komponen
        lblId.setBounds(20, 20, 100, 25);
        txtIdKategori.setBounds(130, 20, 80, 25);

        lblNama.setBounds(20, 55, 100, 25);
        txtNama.setBounds(130, 55, 200, 25);

        lblKet.setBounds(20, 90, 100, 25);
        txtKeterangan.setBounds(130, 90, 200, 25);

        btnTambahBaru.setBounds(20, 130, 120, 30);
        btnSimpan.setBounds(150, 130, 90, 30);
        btnHapus.setBounds(250, 130, 80, 30);

        lblCari.setBounds(360, 20, 80, 25);
        txtCari.setBounds(410, 20, 160, 25);
        btnCari.setBounds(580, 20, 70, 25);

        scroll.setBounds(360, 60, 300, 280);

        add(lblId);
        add(txtIdKategori);
        add(lblNama);
        add(txtNama);
        add(lblKet);
        add(txtKeterangan);
        add(btnTambahBaru);
        add(btnSimpan);
        add(btnHapus);
        add(lblCari);
        add(txtCari);
        add(btnCari);
        add(scroll);

        // event handler
        btnTambahBaru.addActionListener(e -> kosongkanForm());
        btnSimpan.addActionListener(e -> onSimpan());
        btnHapus.addActionListener(e -> onHapus());
        btnCari.addActionListener(e -> cari(txtCari.getText()));
        tblKategori.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                onTableClicked();
            }
        });
    }

    // mengosongkan form
    private void kosongkanForm() {
        txtIdKategori.setText("0");
        txtNama.setText("");
        txtKeterangan.setText("");
    }

    // menampilkan semua data ke JTable
    private void tampilkanData() {
        String[] kolom = {"ID", "Nama", "Keterangan"};
        ArrayList<Kategori> list = new Kategori().getAll();

        Object[] rowData = new Object[3];
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, kolom);

        for (Kategori k : list) {
            rowData[0] = k.getIdkategori();
            rowData[1] = k.getNama();
            rowData[2] = k.getKeterangan();
            model.addRow(rowData);
        }

        tblKategori.setModel(model);
    }

    // pencarian
    private void cari(String keyword) {
        String[] kolom = {"ID", "Nama", "Keterangan"};
        ArrayList<Kategori> list = new Kategori().search(keyword);

        Object[] rowData = new Object[3];
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, kolom);

        for (Kategori k : list) {
            rowData[0] = k.getIdkategori();
            rowData[1] = k.getNama();
            rowData[2] = k.getKeterangan();
            model.addRow(rowData);
        }

        tblKategori.setModel(model);
    }

    // simpan (insert / update)
    private void onSimpan() {
        try {
            int id = Integer.parseInt(txtIdKategori.getText());
            String nama = txtNama.getText();
            String ket = txtKeterangan.getText();

            Kategori kat = new Kategori();
            if (id != 0) {
                kat = new Kategori().getById(id);
            }

            kat.setNama(nama);
            kat.setKeterangan(ket);
            kat.save();

            txtIdKategori.setText(String.valueOf(kat.getIdkategori()));
            tampilkanData();
            JOptionPane.showMessageDialog(this, "Data tersimpan");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID tidak valid");
        }
    }

    // hapus
    private void onHapus() {
        try {
            int id = Integer.parseInt(txtIdKategori.getText());
            if (id == 0) {
                JOptionPane.showMessageDialog(this, "Pilih data dulu di tabel");
                return;
            }

            Kategori kat = new Kategori().getById(id);
            kat.delete();
            kosongkanForm();
            tampilkanData();
            JOptionPane.showMessageDialog(this, "Data terhapus");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID tidak valid");
        }
    }

    // ketika baris tabel diklik
    private void onTableClicked() {
        int row = tblKategori.getSelectedRow();
        if (row < 0) return;

        int id = Integer.parseInt(tblKategori.getValueAt(row, 0).toString());
        Kategori kat = new Kategori().getById(id);

        txtIdKategori.setText(String.valueOf(kat.getIdkategori()));
        txtNama.setText(kat.getNama());
        txtKeterangan.setText(kat.getKeterangan());
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new FrmKategori().setVisible(true);
        });
    }
}