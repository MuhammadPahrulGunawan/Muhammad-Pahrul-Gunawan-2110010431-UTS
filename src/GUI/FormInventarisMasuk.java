/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import Koneksi.koneksi;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author ITAdmin
 */
public class FormInventarisMasuk extends javax.swing.JFrame {
    // Mendeklarasikan dan menginisialisasi koneksi ke database menggunakan objek dari kelas koneksi
    private Connection conn = new koneksi().connect();

    // Mendeklarasikan variabel-variabel yang mungkin digunakan untuk menyimpan informasi barang
    public String kdbrg, nmbrg, kategbrg, mrk, uk;

    // Mendeklarasikan variabel-variabel yang mungkin digunakan untuk menyimpan informasi lokasi
    public String kdlok, nmlok;

    // Mendeklarasikan objek model tabel default untuk digunakan dalam tampilan dan manipulasi data dalam tabel
    private DefaultTableModel tabmode;

    /**
     * Creates new form InventarisMasuk
     */
    public FormInventarisMasuk() {
        initComponents();
        
         // Set transparansi untuk JTextField
        //Menghilangkan Bacground JTextField pada Teks ID INVENTORY MASUK dan memberi border 1 di bawah 
        IdInventoryMasuk.setOpaque(false);
        IdInventoryMasuk.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        IdInventoryMasuk.setForeground(Color.BLACK); 
        //Menghilangkan Bacground JTextField pada Teks Kode Barang dan memberi border 1 di bawah 
        KodeBarang.setOpaque(false);
        KodeBarang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        KodeBarang.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Nama Barang dan memberi border 1 di bawah
        NamaBarang.setOpaque(false);
        NamaBarang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        NamaBarang.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Jumlah dan memberi border 1 di bawah
        Jumlah.setOpaque(false);
        Jumlah.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Jumlah.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Nilai Barang dan memberi border 1 di bawah
        NilaiBarang.setOpaque(false);
        NilaiBarang.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        NilaiBarang.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Merek dan memberi border 1 di bawah
        Merek.setOpaque(false);
        Merek.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Merek.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Ukuran dan memberi border 1 di bawah
        Ukuran.setOpaque(false);
        Ukuran.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Ukuran.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Kode Nama Lokasi dan memberi border 1 di bawah
        NamaLokasi.setOpaque(false);
        NamaLokasi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        NamaLokasi.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Kode Lokasi dan memberi border 1 di bawah
        KodeLokasi.setOpaque(false);
        KodeLokasi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        KodeLokasi.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Nama Lokasi dan memberi border 1 di bawah
        NamaLokasi.setOpaque(false);
        NamaLokasi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        NamaLokasi.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada CARI dan memberi border 1 di bawah
        CariInventoryMasuk.setOpaque(false);
        CariInventoryMasuk.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        CariInventoryMasuk.setForeground(Color.BLACK);
        
        //Membuat tampilan jadi layar penuh
        this.setExtendedState(MAXIMIZED_BOTH);
        
        // Mendapatkan kode pengguna yang sedang login
        String KD = UserId.getUserLogin();
        IdAdmin.setText(KD);
        // Mengosongkan inputan dan menyiapkan antarmuka untuk pengisian data inventaris masuk baru
        kosong();
        aktif();
        autonumber();
        nama(); // Mengisi NamaAdmin dengan nama pengguna yang sedang login
        datatable(); // Menampilkan data inventaris masuk dalam tabel

        // Menetapkan beberapa field inputan sebagai non-editable
        NamaLokasi.setEditable(false);
        KodeLokasi.setEditable(false);
        Merek.setEditable(false);
        Ukuran.setEditable(false);
        KodeLokasi.setEditable(false);
        NamaLokasi.setEditable(false);
    }
        // Metode untuk mengambil nama pengguna berdasarkan ID pengguna yang sedang login
        protected void nama() {
    try {
        String sql = "SELECT * FROM mst_user WHERE id_user='" + IdAdmin.getText() + "'";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        if (hasil.next()) {
            NamaAdmin.setText(hasil.getString("nama"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal dipanggil " + e);
    }
}

        // Metode untuk menyiapkan antarmuka inputan dan memberikan fokus pada KodeBarang
        protected void aktif() {
        KodeBarang.requestFocus();
        TanggalMasuk.setEditor(new JSpinner.DateEditor(TanggalMasuk, "yyyy/MM/dd"));
}

        // Metode untuk mengosongkan inputan inventaris masuk
        protected void kosong() {
        KodeBarang.setText("");
        NamaBarang.setText("");
        Kategori.setSelectedItem("");
        Merek.setText("");
        Ukuran.setText("");
        Jumlah.setText("");
        NilaiBarang.setText("");
        KodeLokasi.setText("");
        NamaLokasi.setText("");
}

        // Metode untuk mengenerate otomatis nomor inventaris masuk
        protected void autonumber() {
    try {
        String sql = "SELECT kd_inventaris_masuk FROM trx_inventaris_masuk ORDER BY kd_inventaris_masuk DESC LIMIT 1";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        IdInventoryMasuk.setText("IN0001");
        while (hasil.next()) {
            System.out.println(hasil.getString("kd_inventaris_masuk").substring(2));
            String id_inventori = hasil.getString("kd_inventaris_masuk").substring(2);
            int AN = Integer.parseInt(id_inventori) + 1;
            String Nol = "";

            if (AN < 10) {
                Nol = "000";
            } else if (AN < 100) {
                Nol = "00";
            } else if (AN < 1000) {
                Nol = "0";
            } else if (AN < 10000) {
                Nol = "";
            }

            IdInventoryMasuk.setText("IN" + Nol + AN);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Auto number gagal " + e);
    }
}

// Metode untuk menangani pemilihan item dari tabel barang
public void itemTerpilih() {
    FormPopupBarang pp = new FormPopupBarang();
    pp.brg = this;
    KodeBarang.setText(kdbrg);
    NamaBarang.setText(nmbrg);
    Kategori.setSelectedItem(kategbrg);
    Merek.setText(mrk);
    Ukuran.setText(uk);
}

// Metode untuk menangani pemilihan item dari tabel lokasi
public void lokasiTerpilih() {
    FormPopupLokasi pp = new FormPopupLokasi();
    pp.lks = this;
    KodeLokasi.setText(kdlok);
    NamaLokasi.setText(nmlok);
}

// Metode untuk menampilkan data inventaris masuk dalam tabel
protected void datatable(){
    Object[] Baris = {"ID Inventaris Masuk", "Kode Barang", "Jumlah", "Nilai", "Tanggal Masuk", "ID User", "Kode Lokasi"};
    tabmode = new DefaultTableModel(null, Baris);
    String cariitem = CariInventoryMasuk.getText();            
    try{
        // Membuat query SQL untuk mengambil data inventaris masuk berdasarkan kriteria pencarian
        String sql = "SELECT * FROM trx_inventaris_masuk WHERE kd_inventaris_masuk like '%" + cariitem + "%' or kd_barang like '%" + cariitem + "%' order by kd_inventaris_masuk asc";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        
        // Mengisi model tabel dengan data hasil query
        while(hasil.next()){
            tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5),
                hasil.getString(6),
                hasil.getString(7)
            });
        }
        
        // Menetapkan model tabel ke komponen tabel (TabelInventoryMasuk)
        TabelInventoryMasuk.setModel(tabmode);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal di panggil"+e);
    }

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel3 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        Id = new javax.swing.JLabel();
        Idinv = new javax.swing.JLabel();
        IdAdmin = new javax.swing.JLabel();
        IdInventoryMasuk = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NamaAdmin = new javax.swing.JLabel();
        TanggalMasuk = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        KodeBarang = new javax.swing.JTextField();
        btncaribarang3 = new javax.swing.JButton();
        NamaBarang = new javax.swing.JTextField();
        Kategori = new javax.swing.JComboBox();
        KodeLokasi = new javax.swing.JTextField();
        btncarilokasi3 = new javax.swing.JButton();
        Jumlah = new javax.swing.JTextField();
        NilaiBarang = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        Merek = new javax.swing.JTextField();
        Ukuran = new javax.swing.JTextField();
        NamaLokasi = new javax.swing.JTextField();
        btnsimpan2 = new javax.swing.JButton();
        btnubah2 = new javax.swing.JButton();
        btnhapus2 = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnkeluar2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelInventoryMasuk = new javax.swing.JTable();
        btncaritabelbarang = new javax.swing.JButton();
        CariInventoryMasuk = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        kGradientPanel3.setkEndColor(new java.awt.Color(202, 255, 229));
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 78, 111));

        jPanel1.setBackground(new java.awt.Color(111, 110, 164));

        jLabel17.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/MenuInventoryMasuk.png"))); // NOI18N
        jLabel17.setText("INVENTORY MASUK");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        Id.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Id.setText("ID ADMIN :");

        Idinv.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv.setText("ID INVENTORY MASUK");

        IdAdmin.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        IdAdmin.setText("ID ADMIN");

        IdInventoryMasuk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        IdInventoryMasuk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        IdInventoryMasuk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        IdInventoryMasuk.setOpaque(true);
        IdInventoryMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdInventoryMasukActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setText("NAMA ADMIN :");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setText("TANGGAL MASUK");

        NamaAdmin.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        NamaAdmin.setText(" NAMA ADMIN");

        TanggalMasuk.setModel(new javax.swing.SpinnerDateModel());
        TanggalMasuk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(111, 110, 164));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel36.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel36.setText("NAMA BARANG");

        jLabel37.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel37.setText("KATEGORI BARANG");

        jLabel38.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel38.setText("KODE LOKASI");

        jLabel39.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel39.setText("JUMLAH");

        jLabel40.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel40.setText("NILAI BARANG");

        KodeBarang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        KodeBarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        KodeBarang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btncaribarang3.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btncaribarang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/CARI.png"))); // NOI18N
        btncaribarang3.setText("CARI");
        btncaribarang3.setBorder(null);
        btncaribarang3.setOpaque(true);
        btncaribarang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaribarang3ActionPerformed(evt);
            }
        });

        NamaBarang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NamaBarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NamaBarang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Kategori.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Kategori.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- ELEKTRONIK -", "- SERVICE -", "- LOGISTIK -", "- FURNITURE -", "- PERALATAN KOMUNIKASI -" }));
        Kategori.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KategoriActionPerformed(evt);
            }
        });

        KodeLokasi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        KodeLokasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        KodeLokasi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btncarilokasi3.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btncarilokasi3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/CARI.png"))); // NOI18N
        btncarilokasi3.setText("CARI");
        btncarilokasi3.setBorder(null);
        btncarilokasi3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncarilokasi3ActionPerformed(evt);
            }
        });

        Jumlah.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Jumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Jumlah.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        NilaiBarang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NilaiBarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NilaiBarang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        jLabel41.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel41.setText("KODE BARANG");

        jLabel27.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel27.setText("MEREK");

        jLabel28.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel28.setText("UKURAN");

        jLabel29.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel29.setText("NAMA LOKASI");

        Merek.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Merek.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Merek.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Ukuran.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Ukuran.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ukuran.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        NamaLokasi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NamaLokasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NamaLokasi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnsimpan2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnsimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/btnSIMPAN.png"))); // NOI18N
        btnsimpan2.setText("SIMPAN");
        btnsimpan2.setBorder(null);
        btnsimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpan2ActionPerformed(evt);
            }
        });

        btnubah2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnubah2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/UBAH.png"))); // NOI18N
        btnubah2.setText("UBAH");
        btnubah2.setBorder(null);
        btnubah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubah2ActionPerformed(evt);
            }
        });

        btnhapus2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnhapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/HAPUS.png"))); // NOI18N
        btnhapus2.setText("HAPUS");
        btnhapus2.setBorder(null);
        btnhapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapus2ActionPerformed(evt);
            }
        });

        btnbatal.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnbatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/BATAL.png"))); // NOI18N
        btnbatal.setText("BATAL");
        btnbatal.setBorder(null);
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnkeluar2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnkeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/KELUAR.png"))); // NOI18N
        btnkeluar2.setText("KELUAR");
        btnkeluar2.setBorder(null);
        btnkeluar2.setOpaque(true);
        btnkeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluar2ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(111, 110, 164));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel3.setText("List Data Inventory Masuk :");

        TabelInventoryMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelInventoryMasuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelInventoryMasukMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelInventoryMasuk);

        btncaritabelbarang.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btncaritabelbarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/CARI.png"))); // NOI18N
        btncaritabelbarang.setText("CARI");
        btncaritabelbarang.setBorder(null);
        btncaritabelbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaritabelbarangActionPerformed(evt);
            }
        });

        CariInventoryMasuk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CariInventoryMasuk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Idinv)
                            .addComponent(Id))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IdAdmin)
                            .addComponent(IdInventoryMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NamaAdmin)
                            .addComponent(TanggalMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btncaribarang3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addComponent(KodeLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btncarilokasi3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NilaiBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel29))
                                .addGap(63, 63, 63)
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Merek)
                                    .addComponent(Ukuran)
                                    .addComponent(NamaLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addComponent(btnsimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnubah2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnhapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnkeluar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(CariInventoryMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncaritabelbarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Id)
                    .addComponent(IdAdmin)
                    .addComponent(jLabel4)
                    .addComponent(NamaAdmin))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Idinv)
                    .addComponent(IdInventoryMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(TanggalMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btncaribarang3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel41))
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel36)
                                    .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnsimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnubah2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnhapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(54, 54, 54))
                                        .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                            .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel37))
                                            .addGap(18, 18, 18)
                                            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(KodeLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btncarilokasi3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel38))
                                            .addGap(18, 18, 18)
                                            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel39))
                                            .addGap(18, 18, 18)
                                            .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(NilaiBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel40))))))
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addComponent(btnkeluar2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(Merek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(Ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(NamaLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncaritabelbarang, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(CariInventoryMasuk)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IdInventoryMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdInventoryMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdInventoryMasukActionPerformed

    private void btncaribarang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaribarang3ActionPerformed
        //Ketika button cari di klik pada KODE BARANG maka akan memanggil FormPopUpBarang
        FormPopupBarang pp = new FormPopupBarang();
        pp.brg = this;
        pp.setVisible(true);
        pp.setResizable(false);
    }//GEN-LAST:event_btncaribarang3ActionPerformed

    private void KategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KategoriActionPerformed

    private void btncarilokasi3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncarilokasi3ActionPerformed
        //Ketika button cari di klik pada KODE LOKASI maka akan memanggil FormPopUpBarang
        FormPopupLokasi pp = new FormPopupLokasi();
        pp.lks = this;
        pp.setVisible(true);
        pp.setResizable(false);
    }//GEN-LAST:event_btncarilokasi3ActionPerformed

    private void btnsimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan2ActionPerformed
       
    String idInventoryMasuk = IdInventoryMasuk.getText();
    String kodeBarang3 = KodeBarang.getText();
    String jumlah3 = Jumlah.getText();
    String nilaiBarang3 = NilaiBarang.getText();
    String tanggalMasuk = "";
    String idAdmin = IdAdmin.getText();
    String kodeLokasi3 = KodeLokasi.getText();

    // Periksa apakah input tidak boleh kosong
    if (idInventoryMasuk.isEmpty()) {
        JOptionPane.showMessageDialog(null, "ID Inventory Masuk Kosong");
        return; // Keluar dari metode jika ID Inventory Masuk kosong
    }

    if (kodeBarang3.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Kode Barang Kosong");
        return; // Keluar dari metode jika Kode Barang kosong
    }

    if (jumlah3.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Jumlah Kosong");
        return; // Keluar dari metode jika Jumlah kosong
    }

    if (nilaiBarang3.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nilai Barang Kosong");
        return; // Keluar dari metode jika Nilai Barang kosong
    }

    // Lakukan konversi tanggal menggunakan SimpleDateFormat
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tanggalMasuk = sdf.format(TanggalMasuk.getValue());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Format tanggal tidak valid");
        return; // Keluar dari metode jika format tanggal tidak valid
    }

    if (idAdmin.isEmpty()) {
        JOptionPane.showMessageDialog(null, "ID Admin Kosong");
        return; // Keluar dari metode jika ID Admin kosong
    }

    if (kodeLokasi3.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Kode Lokasi Kosong");
        return; // Keluar dari metode jika Kode Lokasi kosong
    }

    // Jika semua input sudah diisi, lanjutkan penyimpanan
    String sql = "INSERT INTO trx_inventaris_masuk VALUES (?,?,?,?,?,?,?)";
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, idInventoryMasuk);
        stat.setString(2, kodeBarang3);
        stat.setString(3, jumlah3);
        stat.setString(4, nilaiBarang3);
        stat.setString(5, tanggalMasuk);
        stat.setString(6, idAdmin);
        stat.setString(7, kodeLokasi3);
        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        kosong();
        KodeBarang.requestFocus();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Data gagal disimpan" + e);
    }
    datatable();
    }//GEN-LAST:event_btnsimpan2ActionPerformed

    private void btnubah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubah2ActionPerformed

        //Jika data pada tabel tidak di pilih maka akan muncul pesan "Pilih data yang akan diubah"
        int selectedRow = TabelInventoryMasuk.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah");
            return;
        }

        // Mendefinisikan query SQL untuk mengupdate data inventaris masuk berdasarkan ID inventaris masuk
        String sql = "UPDATE trx_inventaris_masuk SET kd_barang=?, jumlah=?, nilai_barang=?, tanggal_masuk=?, kd_lokasi=? WHERE kd_inventaris_masuk='" + IdInventoryMasuk.getText() + "'";
    try {
        // Menggunakan SimpleDateFormat untuk memformat nilai tanggal sebelum dimasukkan ke database
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(TanggalMasuk.getValue());

        // Membuat PreparedStatement untuk eksekusi query SQL
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, KodeBarang.getText());
        stat.setString(2, Jumlah.getText());
        stat.setString(3, NilaiBarang.getText());
        stat.setString(4, formattedDate);
        stat.setString(5, KodeLokasi.getText());

        // Mengeksekusi query SQL untuk mengupdate data inventaris masuk
        stat.executeUpdate();

        // Menampilkan pesan sukses dan melakukan pembaruan tampilan
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        kosong(); // Mengosongkan inputan
        KodeBarang.requestFocus(); // Fokus pada inputan KodeBarang
    } catch (SQLException e) {
        // Menampilkan pesan kesalahan jika query SQL gagal dieksekusi
        JOptionPane.showMessageDialog(null, "Data gagal diubah" + e);
 }
    // Memperbarui tampilan tabel dengan data terkini
    datatable();

    }//GEN-LAST:event_btnubah2ActionPerformed

    private void btnhapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus2ActionPerformed
        // Menampilkan dialog konfirmasi penghapusan data
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?", "konfirm dialog", JOptionPane.YES_NO_OPTION);
        // Jika pengguna menekan tombol 'Yes' pada dialog konfirmasi
        if(ok==0){
            //Membuat query SQL untuk menghapus data dari tabel trx_inventaris_masuk berdasarkan nilai pada kolom kd_inventaris_masuk.
            String sql = "DELETE FROM trx_inventaris_masuk WHERE kd_inventaris_masuk='"+IdInventoryMasuk.getText()+"'";
            try {
                // Membuat objek PreparedStatement untuk menjalankan query SQL
                PreparedStatement stat = conn.prepareStatement(sql);
                // Menjalankan query SQL untuk menghapus data
                stat.executeUpdate();
                // Menampilkan pesan sukses
                JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
                // Membersihkan form dan mengatur fokus ke KodeBarang setelah penghapusan
                kosong();
                //Mengatur fokus ke KodeBarang
                KodeBarang.requestFocus();
            } catch(SQLException e){
                // Menampilkan pesan error jika terjadi SQLException
                JOptionPane.showMessageDialog(null, "Data gagal di hapus"+e);
            }
            // Memanggil fungsi datatable() untuk memperbarui data pada tabel setelah penghapusan
            datatable();
        }
    }//GEN-LAST:event_btnhapus2ActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        kosong();
        datatable();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnkeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluar2ActionPerformed
        dispose();
    }//GEN-LAST:event_btnkeluar2ActionPerformed

    private void TabelInventoryMasukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelInventoryMasukMouseClicked
       
        // Mendapatkan indeks baris terpilih dari tabel inventaris masuk
        int bar = TabelInventoryMasuk.getSelectedRow();

        // Mengambil nilai-nilai dari tabel terpilih
        String id_inv = tabmode.getValueAt(bar, 0).toString();
        String kd_brg = tabmode.getValueAt(bar, 1).toString();
        String jml = tabmode.getValueAt(bar, 2).toString();
        String nil = tabmode.getValueAt(bar, 3).toString();
        String kd_lok = tabmode.getValueAt(bar, 6).toString();

try {
        // Mengambil data barang berdasarkan kode barang
        String sql = "SELECT * FROM mst_barang WHERE kd_barang='" + kd_brg + "'";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);

        // Jika data barang ditemukan
        if (hasil.next()) {
        // Mengisi data barang ke dalam inputan terkait
        NamaBarang.setText(hasil.getString("nama_barang"));
        Kategori.setSelectedItem(hasil.getString("kategori_barang"));
        Merek.setText(hasil.getString("merek"));
        Ukuran.setText(hasil.getString("ukuran"));
    }

    // Mengambil data lokasi berdasarkan kode lokasi
    String sql2 = "SELECT * FROM mst_lokasi WHERE kd_lokasi='" + kd_lok + "'";
    Statement stat2 = conn.createStatement();
    ResultSet hasil2 = stat2.executeQuery(sql2);

    // Jika data lokasi ditemukan
    if (hasil2.next()) {
        // Mengisi data lokasi ke dalam inputan terkait
        KodeLokasi.setText(hasil2.getString("kd_lokasi"));
        NamaLokasi.setText(hasil2.getString("nama_lokasi"));
    }
} catch (Exception e) {
    // Menangani kesalahan saat mengambil data dari database
    JOptionPane.showMessageDialog(null, "Data gagal dipanggil " + e);
}

    // Mengisi inputan dengan nilai-nilai dari tabel terpilih
    KodeBarang.setText(kd_brg);
    Jumlah.setText(jml);
    NilaiBarang.setText(nil);
    KodeLokasi.setText(kd_lok);

    }//GEN-LAST:event_TabelInventoryMasukMouseClicked

    private void btncaritabelbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaritabelbarangActionPerformed
        // Memanggil metode datatable() untuk menampilkan data sesuai dengan kriteria pencarian
        datatable();
    }//GEN-LAST:event_btncaritabelbarangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         try {
    // Iterasi melalui semua look and feel yang terinstall
    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        // Jika ditemukan look and feel dengan nama "Nimbus", atur antarmuka pengguna menggunakan look and feel tersebut
        if ("Nimbus".equals(info.getName())) {
            javax.swing.UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            // Tangani exception yang mungkin terjadi selama proses mengatur look and feel
        java.util.logging.Logger.getLogger(FormInventarisMasuk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormInventarisMasuk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CariInventoryMasuk;
    private javax.swing.JLabel Id;
    private javax.swing.JLabel IdAdmin;
    private javax.swing.JTextField IdInventoryMasuk;
    private javax.swing.JLabel Idinv;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JComboBox Kategori;
    private javax.swing.JTextField KodeBarang;
    private javax.swing.JTextField KodeLokasi;
    private javax.swing.JTextField Merek;
    private javax.swing.JLabel NamaAdmin;
    private javax.swing.JTextField NamaBarang;
    private javax.swing.JTextField NamaLokasi;
    private javax.swing.JTextField NilaiBarang;
    private javax.swing.JTable TabelInventoryMasuk;
    private javax.swing.JSpinner TanggalMasuk;
    private javax.swing.JTextField Ukuran;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncaribarang3;
    private javax.swing.JButton btncarilokasi3;
    private javax.swing.JButton btncaritabelbarang;
    private javax.swing.JButton btnhapus2;
    private javax.swing.JButton btnkeluar2;
    private javax.swing.JButton btnsimpan2;
    private javax.swing.JButton btnubah2;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel3;
    // End of variables declaration//GEN-END:variables
}
