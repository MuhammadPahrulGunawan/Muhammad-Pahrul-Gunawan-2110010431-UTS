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
public class FormInventarisKeluar extends javax.swing.JFrame {
   // Objek koneksi untuk membuat dan memperoleh koneksi ke database
    private Connection conn = new koneksi().connect();

    // Variabel-variabel yang menyimpan informasi terkait data inventaris masuk
    public String idinvmsk, kdbrg, nmbrg, ktgbrg, mrk, uk, kdlok, nmlok;

    // Model tabel default untuk menampung data inventaris masuk
    private DefaultTableModel tabmode;

    /**
     * Creates new form InventarisKeluar
     */
    public FormInventarisKeluar() {
        initComponents();
        
        //Membuat tampilan jadi layar penuh
        this.setExtendedState(MAXIMIZED_BOTH);
        
        // Set transparansi untuk JTextField
        //Menghilangkan Bacground JTextField pada Teks ID INVENTORY KELUAR dan memberi border 1 di bawah 
        IdInventoryKeluar.setOpaque(false);
        IdInventoryKeluar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        IdInventoryKeluar.setForeground(Color.BLACK); 
        //Menghilangkan Bacground JTextField pada Teks Kode INVENTORY dan memberi border 1 di bawah 
        KodeInventory.setOpaque(false);
        KodeInventory.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        KodeInventory.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks KETERANGAN dan memberi border 1 di bawah
        Keterangan.setOpaque(false);
        Keterangan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Keterangan.setForeground(Color.BLACK);
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
        Cari.setOpaque(false);
        Cari.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Cari.setForeground(Color.BLACK);
        
        // Mendapatkan ID user yang sedang login dan menetapkannya ke IdAdmin
        String kd = UserId.getUserLogin();
        IdAdmin.setText(kd);

        // Mengosongkan form, mengatur fokus, dan membuat nomor otomatis
        kosong();
        aktif();
        autonumber();
        // Mengambil dan menetapkan nama user dari database
        nama();
        // Mengisi data inventaris keluar pada tabel
        datatable();

        // Mengatur agar beberapa komponen tidak dapat diubah (editable)
        KodeInventory.setEditable(false);
        NamaBarang.setEditable(false);
        KodeBarang.setEditable(false);
        Kategori.setEditable(false);
        Merek.setEditable(false);
        Ukuran.setEditable(false);
        KodeLokasi.setEditable(false);
        NamaLokasi.setEditable(false);
    }
    
     protected void nama() {
        try {
        // Membuat query untuk mengambil nama user berdasarkan ID user dari komponen IdAdmin
        String sql = "SELECT * FROM mst_user WHERE id_user='" + IdAdmin.getText() + "'";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);
        // Jika data ditemukan, set teks NamaAdmin dengan nilai nama dari hasil query
        if (hasil.next()) {
            NamaAdmin.setText(hasil.getString("nama"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data gagal dipanggil " + e);
   }
    }

    protected void aktif() {
        // Mengatur fokus ke komponen IdInventoryKeluar dan menyesuaikan format TanggalKeluar
        KodeInventory.requestFocus();
        TanggalKeluar.setEditor(new JSpinner.DateEditor(TanggalKeluar, "yyyy/MM/dd"));
    }

    protected void kosong() {
        // Mengosongkan sejumlah komponen pada form
        KodeInventory.setText("");
        KodeBarang.setText("");
        NamaBarang.setText("");
        Kategori.setSelectedItem("");
        Merek.setText("");
        Ukuran.setText("");        
        KodeLokasi.setText("");
        NamaLokasi.setText("");
        Jumlah.setText("");
        Keterangan.setText("");
    }

    protected void autonumber() {
        try {
        // Mengambil ID inventaris keluar terakhir dari database untuk membuat nomor otomatis
        String sql = "SELECT kd_inventaris_keluar FROM trx_inventaris_keluar ORDER BY kd_inventaris_keluar DESC LIMIT 1";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);

        // Inisialisasi nomor otomatis awal
        IdInventoryKeluar.setText("OUT0001");

        while (hasil.next()) {
            // Mendapatkan angka dari ID inventaris keluar terakhir
            String id_inventori = hasil.getString("kd_inventaris_keluar").substring(3);
            int AN = Integer.parseInt(id_inventori) + 1;
            String Nol = "";

            // Membuat format nomor otomatis sesuai dengan panjang digit
            if (AN < 10) {
                Nol = "000";
            } else if (AN < 100) {
                Nol = "00";
            } else if (AN < 1000) {
                Nol = "0";
            } else if (AN < 10000) {
                Nol = "";
            }

            // Menetapkan nomor otomatis ke IdInventoryKeluar
            IdInventoryKeluar.setText("OUT" + Nol + AN);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Auto number gagal " + e);
    }
    }
    
    public void InventoryMasukTerpilih() {
        // Mendapatkan data dari form pop-up inventaris masuk
        FormPopupInventoriMasuk pp = new FormPopupInventoriMasuk();
        pp.invmsk = this;
        KodeInventory.setText(idinvmsk);
        KodeBarang.setText(kdbrg);
        NamaBarang.setText(nmbrg);
        Kategori.setSelectedItem(ktgbrg);
        Merek.setText(mrk);
        Ukuran.setText(uk);
        KodeLokasi.setText(kdlok);
        NamaLokasi.setText(nmlok);
    }
    
    protected void datatable(){
        // Mendefinisikan header kolom untuk tabel
        Object[] Baris = {"ID Inventaris Keluar", "ID Inventaris Masuk", "Jumlah", "Tanggal Keluar", "Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        String cariitem = Cari.getText();            
    try {
        // Menampilkan data inventaris keluar pada tabel berdasarkan pencarian
        String sql = "SELECT * FROM trx_inventaris_keluar WHERE kd_inventaris_keluar like '%"+cariitem+"%' order by kd_inventaris_keluar asc";
        Statement stat = conn.createStatement();
        ResultSet hasil = stat.executeQuery(sql);

        // Menambahkan baris ke dalam tabel berdasarkan hasil query
        while(hasil.next()) {
            tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5),
            });
        }

        // Menetapkan model tabel ke dalam TabelInventoryKeluar
        TabelInventoryKeluar.setModel(tabmode);
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

        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        IdAdmin = new javax.swing.JLabel();
        IdInventoryKeluar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        NamaAdmin = new javax.swing.JLabel();
        TanggalKeluar = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        KodeInventory = new javax.swing.JTextField();
        KodeBarang = new javax.swing.JTextField();
        NamaBarang = new javax.swing.JTextField();
        Kategori = new javax.swing.JComboBox();
        KodeLokasi = new javax.swing.JTextField();
        btncariinventory = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Jumlah = new javax.swing.JTextField();
        Keterangan = new javax.swing.JTextField();
        Merek = new javax.swing.JTextField();
        Ukuran = new javax.swing.JTextField();
        NamaLokasi = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Cari = new javax.swing.JTextField();
        btncaritabel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelInventoryKeluar = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        kGradientPanel1.setkEndColor(new java.awt.Color(123, 87, 129));
        kGradientPanel1.setkStartColor(new java.awt.Color(251, 206, 129));

        jPanel1.setBackground(new java.awt.Color(111, 110, 164));

        jLabel17.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/MenuInventoryKeluar.png"))); // NOI18N
        jLabel17.setText("INVENTORY KELUAR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 645, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setText("ID ADMIN :");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setText("ID INVENTORY KELUAR");

        IdAdmin.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        IdAdmin.setText("ID ADMIN");

        IdInventoryKeluar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        IdInventoryKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdInventoryKeluarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setText("NAMA ADMIN");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setText("TANGGAL KELUAR");

        NamaAdmin.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        NamaAdmin.setText("NAMA ADMIN");

        TanggalKeluar.setModel(new javax.swing.SpinnerDateModel());

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

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setText("KODE INVENTORY MASUK");

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel9.setText("KODE BARANG");

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setText("NAMA BARANG");

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel11.setText("KATEGORI BARANG");

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel12.setText("KODE LOKASI");

        KodeInventory.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        KodeInventory.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        KodeBarang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        KodeBarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        NamaBarang.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NamaBarang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NamaBarang.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        NamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaBarangActionPerformed(evt);
            }
        });

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

        btncariinventory.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btncariinventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/CARI.png"))); // NOI18N
        btncariinventory.setText("CARI");
        btncariinventory.setBorder(null);
        btncariinventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariinventoryActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Roboto Bk", 1, 12)); // NOI18N
        jLabel16.setText("JUMLAH");

        jLabel18.setFont(new java.awt.Font("Roboto Bk", 1, 12)); // NOI18N
        jLabel18.setText("KETERANGAN");

        jLabel14.setFont(new java.awt.Font("Roboto Bk", 1, 12)); // NOI18N
        jLabel14.setText("MEREK");

        jLabel15.setFont(new java.awt.Font("Roboto Bk", 1, 12)); // NOI18N
        jLabel15.setText("UKURAN");

        jLabel13.setFont(new java.awt.Font("Roboto Bk", 1, 12)); // NOI18N
        jLabel13.setText("NAMA LOKASI");

        Jumlah.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Jumlah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Jumlah.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Keterangan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Keterangan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Keterangan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Merek.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Merek.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Merek.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        Ukuran.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Ukuran.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Ukuran.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Ukuran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UkuranActionPerformed(evt);
            }
        });

        NamaLokasi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NamaLokasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NamaLokasi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        btnsimpan.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/btnSIMPAN.png"))); // NOI18N
        btnsimpan.setText("SIMPAN");
        btnsimpan.setBorder(null);
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnubah.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/UBAH.png"))); // NOI18N
        btnubah.setText("UBAH");
        btnubah.setBorder(null);
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnhapus.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/HAPUS.png"))); // NOI18N
        btnhapus.setText("HAPUS");
        btnhapus.setBorder(null);
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
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

        btnkeluar.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/KELUAR.png"))); // NOI18N
        btnkeluar.setText("KELUAR");
        btnkeluar.setBorder(null);
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
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

        jLabel19.setFont(new java.awt.Font("Roboto Bk", 1, 14)); // NOI18N
        jLabel19.setText("List Data Inventory keluar :");

        Cari.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Cari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CariKeyPressed(evt);
            }
        });

        btncaritabel.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btncaritabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/CARI.png"))); // NOI18N
        btncaritabel.setText("CARI");
        btncaritabel.setBorder(null);
        btncaritabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaritabelActionPerformed(evt);
            }
        });

        TabelInventoryKeluar.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelInventoryKeluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelInventoryKeluarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelInventoryKeluar);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(31, 31, 31)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(IdAdmin)
                            .addComponent(IdInventoryKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(56, 56, 56)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TanggalKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NamaAdmin)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(62, 62, 62)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(KodeLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(27, 27, 27)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(KodeBarang, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(KodeInventory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(btncariinventory, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(51, 51, 51))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)))
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Ukuran, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(NamaLokasi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Merek, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel18)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(51, 51, 51)
                                    .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncaritabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(IdAdmin)
                    .addComponent(jLabel5)
                    .addComponent(NamaAdmin))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(TanggalKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IdInventoryKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(KodeInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(KodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncariinventory, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(NamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(Kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(KodeLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Keterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(Merek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(Ukuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(NamaLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan)
                    .addComponent(btnubah)
                    .addComponent(btnhapus)
                    .addComponent(btnbatal)
                    .addComponent(btnkeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncaritabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IdInventoryKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdInventoryKeluarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdInventoryKeluarActionPerformed

    private void NamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaBarangActionPerformed

    private void KategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KategoriActionPerformed

    private void btncariinventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariinventoryActionPerformed
        // Membuat objek dari kelas FormPopupInventoriMasuk
        FormPopupInventoriMasuk pp = new FormPopupInventoriMasuk();

        // Menetapkan referensi dari objek ini (kelas FormInventoryKeluar) ke objek FormPopupInventoriMasuk
        pp.invmsk = this;

        // Menampilkan form pop-up inventaris masuk
        pp.setVisible(true);

        // Mengatur resizable form pop-up inventaris masuk menjadi tidak dapat diubah ukurannya
        pp.setResizable(false);

    }//GEN-LAST:event_btncariinventoryActionPerformed

    private void UkuranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UkuranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UkuranActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // SQL query untuk menyimpan data ke tabel trx_inventaris_keluar
        String sql = "INSERT INTO trx_inventaris_keluar VALUES (?,?,?,?,?,?)";

try {
    // Format tanggal sesuai dengan pola "yyyy-MM-dd"
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fd = sdf.format(TanggalKeluar.getValue());

    // Persiapan statement SQL
    PreparedStatement stat = conn.prepareStatement(sql);

    // Mengambil nilai dari form
    String idInventoryKeluar = IdInventoryKeluar.getText();
    String kodeInventory = KodeInventory.getText();
    String jumlah = Jumlah.getText();
    String keterangan = Keterangan.getText();
    String idAdmin = IdAdmin.getText();

    // Periksa apakah input tidak boleh kosong
    if (idInventoryKeluar.isEmpty() || kodeInventory.isEmpty() || jumlah.isEmpty() || fd.isEmpty() || keterangan.isEmpty() || idAdmin.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Harap isi semua kolom");
        return; // Keluar dari metode jika ada yang kosong
    }

    // Mengatur nilai parameter pada statement SQL dengan data dari form
    stat.setString(1, idInventoryKeluar);
    stat.setString(2, kodeInventory);
    stat.setString(3, jumlah);
    stat.setString(4, fd);
    stat.setString(5, keterangan);
    stat.setString(6, idAdmin);

    // Eksekusi pernyataan SQL untuk menyimpan data
    stat.executeUpdate();

    // Menampilkan pesan berhasil setelah data disimpan
    JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

    // Mengosongkan form dan menetapkan fokus pada ID Inventory Keluar
    kosong();
    KodeInventory.requestFocus();
} catch (SQLException e) {
    // Menampilkan pesan kesalahan jika penyimpanan data gagal
    JOptionPane.showMessageDialog(null, "Data gagal disimpan: " + e);
}

// Memperbarui tabel data inventaris keluar
datatable();

    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        //Jika data pada tabel tidak di pilih maka akan muncul pesan "Pilih data yang akan diubah"
        int selectedRow = TabelInventoryKeluar.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah");
            return;
        }
        
        // SQL query untuk melakukan update data inventaris keluar berdasarkan ID Inventaris Keluar
       String sql = "UPDATE trx_inventaris_keluar SET kd_inventaris_masuk=?, jumlah=?, tanggal_keluar=?, keterangan=? WHERE kd_inventaris_keluar='" + IdInventoryKeluar.getText() + "'";

try {
    // Format tanggal sesuai dengan pola "yyyy-MM-dd"
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fd = sdf.format(TanggalKeluar.getValue());
    
    // Persiapan statement SQL
    PreparedStatement stat = conn.prepareStatement(sql);

    // Mengatur nilai parameter pada statement SQL dengan data dari form
    stat.setString(1, KodeInventory.getText());
    stat.setString(2, Jumlah.getText());
    stat.setString(3, fd);
    stat.setString(4, Keterangan.getText());

    // Eksekusi pernyataan SQL untuk melakukan update data
    stat.executeUpdate();

    // Menampilkan pesan berhasil setelah data diubah
    JOptionPane.showMessageDialog(null, "Data berhasil diubah");

    // Mengosongkan form dan menetapkan fokus pada ID Inventory Keluar
    kosong();
    KodeInventory.requestFocus();
} catch(SQLException e){
    // Menampilkan pesan kesalahan jika update data gagal
    JOptionPane.showMessageDialog(null, "Data gagal diubah: " + e);
}

// Memperbarui tabel data inventaris keluar
datatable();

    }//GEN-LAST:event_btnubahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
         // Menampilkan dialog konfirmasi
    int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
    
    // Jika user menekan tombol YES pada dialog konfirmasi
    if (ok == 0) {
        // Kode SQL untuk menghapus data berdasarkan ID
        String sql = "DELETE FROM trx_inventaris_keluar WHERE kd_inventaris_keluar=?";
        try {
            // Membuat prepared statement
            PreparedStatement stat = conn.prepareStatement(sql);
            // Mengatur parameter dengan nilai ID yang akan dihapus
            stat.setString(1, IdInventoryKeluar.getText());
            
            // Menjalankan SQL untuk menghapus data
            int rowsAffected = stat.executeUpdate();
            
            // Jika ada baris yang terpengaruh, artinya data berhasil dihapus
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                KodeInventory.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus" + e);
        }
        
        // Memperbarui tampilan tabel setelah penghapusan data
        datatable();
    }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
        kosong();
        datatable();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void CariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CariKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            datatable();
        }
    }//GEN-LAST:event_CariKeyPressed

    private void btncaritabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaritabelActionPerformed
        datatable();
    }//GEN-LAST:event_btncaritabelActionPerformed

    private void TabelInventoryKeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelInventoryKeluarMouseClicked
      int bar = TabelInventoryKeluar.getSelectedRow();
        String id_inv = tabmode.getValueAt(bar, 0).toString();
        String id_inv_msk = tabmode.getValueAt(bar, 1).toString();
        String jml = tabmode.getValueAt(bar, 2).toString();
        String tgl = tabmode.getValueAt(bar, 3).toString();
        String ket = tabmode.getValueAt(bar, 4).toString();
        try{
            String sql = "SELECT a.*,b.*,c.* FROM trx_inventaris_masuk a, mst_barang b, mst_lokasi c WHERE a.kd_barang=b.kd_barang AND a.kd_lokasi=c.kd_lokasi AND a.kd_inventaris_masuk='"+id_inv_msk+"'";
            Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            if (hasil.next()) {
                KodeInventory.setText(hasil.getString("kd_inventaris_masuk"));
                KodeBarang.setText(hasil.getString("kd_barang"));
                NamaBarang.setText(hasil.getString("nama_barang"));
                Kategori.setSelectedItem(hasil.getString("kategori_barang"));
                Merek.setText(hasil.getString("merek"));
                Ukuran.setText(hasil.getString("ukuran"));
                KodeLokasi.setText(hasil.getString("kd_lokasi"));
                NamaLokasi.setText(hasil.getString("nama_lokasi"));
                IdInventoryKeluar.setText(id_inv);
                KodeInventory.setText(id_inv_msk);
                Jumlah.setText(jml);
                Keterangan.setText(ket);
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data gagal dipanggil " + e);
        }
    }//GEN-LAST:event_TabelInventoryKeluarMouseClicked

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
                new FormInventarisKeluar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Cari;
    private javax.swing.JLabel IdAdmin;
    private javax.swing.JTextField IdInventoryKeluar;
    private javax.swing.JTextField Jumlah;
    private javax.swing.JComboBox Kategori;
    private javax.swing.JTextField Keterangan;
    private javax.swing.JTextField KodeBarang;
    private javax.swing.JTextField KodeInventory;
    private javax.swing.JTextField KodeLokasi;
    private javax.swing.JTextField Merek;
    private javax.swing.JLabel NamaAdmin;
    private javax.swing.JTextField NamaBarang;
    private javax.swing.JTextField NamaLokasi;
    private javax.swing.JTable TabelInventoryKeluar;
    private javax.swing.JSpinner TanggalKeluar;
    private javax.swing.JTextField Ukuran;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btncariinventory;
    private javax.swing.JButton btncaritabel;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnubah;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
