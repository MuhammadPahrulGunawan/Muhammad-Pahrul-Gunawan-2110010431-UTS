/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import Koneksi.koneksi;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author ITAdmin
 */
public class FormLokasi extends javax.swing.JFrame {

    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private int barisTerpilih;
    /**
     * Creates new form Lokasi
     */
    public FormLokasi() {
        initComponents();
        
         // Set transparansi untuk JTextField
        
        //Menghilangkan Bacground JTextField pada TeksKode Kode Lokasi dan memberi border 1 di bawah
        KodeLokasi.setOpaque(false);
        KodeLokasi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        KodeLokasi.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Nama Lokasi dan memberi border 1 di bawah
        NamaLokasi.setOpaque(false);
        NamaLokasi.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        NamaLokasi.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Cari Barang dan memberi border 1 di bawah
        Cari.setOpaque(false);
        Cari.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Cari.setForeground(Color.BLACK);
        
        //Membuat tampilan jadi layar penuh
        this.setExtendedState(MAXIMIZED_BOTH);
        
        // Memanggil metode kosong(), aktif(), dan datatable() saat program dimulai atau suatu event tertentu terjadi
        kosong();
        aktif();
        datatable();
}

// Metode untuk mengosongkan field input pada antarmuka pengguna terkait lokasi
protected void kosong(){
    KodeLokasi.setText("");
    NamaLokasi.setText("");
    Cari.setText("");
}

// Metode untuk mengatur fokus ke field input KodeLokasi pada antarmuka pengguna terkait lokasi
protected void aktif(){
    KodeLokasi.requestFocus();
}

// Metode untuk menampilkan data lokasi pada tabel antarmuka pengguna berdasarkan kriteria pencarian
protected void datatable(){
    // Menentukan kolom yang akan ditampilkan dalam tabel
    Object[] Baris = {"Kode Lokasi", "Nama Lokasi"};
    // Membuat model tabel (tabmode) dengan kolom yang telah ditentukan
    tabmode = new DefaultTableModel(null, Baris);
    // Mendapatkan teks dari field pencarian
    String cariitem = Cari.getText();            
    try{
        // Membuat query SQL untuk mengambil data lokasi berdasarkan Kode atau Nama Lokasi yang sesuai dengan kriteria pencarian
        String sql = "SELECT * FROM mst_lokasi WHERE kd_lokasi like '%"+cariitem+"%' or nama_lokasi like '%"+cariitem+"%' order by kd_lokasi asc";
        // Membuat objek Statement untuk menjalankan query SQL
        Statement stat = conn.createStatement();
        // Mengeksekusi query SQL dan mendapatkan hasil
        ResultSet hasil = stat.executeQuery(sql);
        // Mengisi model tabel dengan data hasil query
        while(hasil.next()){
            tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2)
            });
        }
        // Menetapkan model tabel ke komponen tabel (TabelLokasi) pada antarmuka pengguna
        TabelLokasi.setModel(tabmode);
    } catch (Exception e) {
        // Menampilkan pesan kesalahan jika terjadi exception
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
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        NamaLokasi = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btnbatal = new javax.swing.JButton();
        btnkeluar = new javax.swing.JButton();
        Idinv5 = new javax.swing.JLabel();
        Cari = new javax.swing.JTextField();
        BtnCari = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelLokasi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        KodeLokasi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(93, 168, 206));
        kGradientPanel1.setkStartColor(new java.awt.Color(239, 252, 228));

        jPanel2.setBackground(new java.awt.Color(111, 110, 164));

        jLabel18.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/MenuDataLokasi.png"))); // NOI18N
        jLabel18.setText("DATA LOKASI");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 12, Short.MAX_VALUE))
        );

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
        btnkeluar.setOpaque(true);
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        Idinv5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv5.setText("List Data Lokasi :");

        Cari.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Cari.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CariMouseClicked(evt);
            }
        });
        Cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CariKeyPressed(evt);
            }
        });

        BtnCari.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/CARI.png"))); // NOI18N
        BtnCari.setText("CARI");
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });

        TabelLokasi.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelLokasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelLokasiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelLokasi);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setText("KODE LOKASI");

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setText("NAMA LOKASI");

        KodeLokasi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        KodeLokasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(NamaLokasi)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnkeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCari, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Idinv5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(KodeLokasi))
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(KodeLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(NamaLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnubah, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbatal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnkeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Idinv5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cari))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
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

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
    String kodeLokasi = KodeLokasi.getText();
    String namaLokasi = NamaLokasi.getText();

    // Periksa apakah KodeLokasi atau NamaLokasi kosong
    if (kodeLokasi.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Kode Lokasi Kosong");
        return; // Keluar dari metode jika KodeLokasi kosong
    }

    if (namaLokasi.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nama Lokasi Kosong");
        return; // Keluar dari metode jika NamaLokasi kosong
    }

    // Jika KodeLokasi dan NamaLokasi tidak kosong, lanjutkan penyimpanan
    String sql = "INSERT INTO mst_lokasi VALUES (?,?)";
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, kodeLokasi);
        stat.setString(2, namaLokasi);

        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        kosong();
        KodeLokasi.requestFocus();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Data gagal disimpan" + e);
    }
    datatable();
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed

        //Jika data pada tabel tidak di pilih maka akan muncul pesan "Pilih data yang akan diubah"
        int selectedRow = TabelLokasi.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah");
            return;
        }

        // Mendefinisikan query SQL untuk mengupdate data lokasi berdasarkan Kode Lokasi
        String sql = "UPDATE mst_lokasi SET nama_lokasi=? WHERE kd_lokasi='"+KodeLokasi.getText()+"'";
    try {
        // Membuat objek PreparedStatement untuk mengeksekusi query SQL
        PreparedStatement stat = conn.prepareStatement(sql);
        // Mengatur nilai parameter pada query SQL berdasarkan input dari pengguna
        stat.setString(1, NamaLokasi.getText());

        // Mengeksekusi query SQL untuk mengupdate data
        stat.executeUpdate();
    
        // Menampilkan pesan sukses setelah data berhasil diubah
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
    
        // Mengosongkan input fields dan mengatur fokus ke KodeLokasi
        kosong();
        KodeLokasi.requestFocus();
     } catch(SQLException e){
    // Menampilkan pesan kesalahan jika terjadi exception saat menjalankan query SQL
    JOptionPane.showMessageDialog(null, "Data gagal diubah"+e);
        }
        // Memanggil metode datatable() untuk memperbarui tampilan tabel setelah perubahan data
        datatable();
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
    // Menampilkan dialog konfirmasi untuk menghapus data
    int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?", "konfirm dialog", JOptionPane.YES_NO_OPTION);

    // Memeriksa apakah pengguna menekan tombol "Yes" pada dialog konfirmasi
    if(ok==0){
    // Menyiapkan query SQL untuk menghapus data lokasi berdasarkan Kode Lokasi
    String sql = "DELETE FROM mst_lokasi WHERE kd_lokasi='"+KodeLokasi.getText()+"'";
    try {
        // Membuat objek PreparedStatement untuk mengeksekusi query SQL
        PreparedStatement stat = conn.prepareStatement(sql);
        // Mengeksekusi query SQL untuk menghapus data
        stat.executeUpdate();
        // Menampilkan pesan sukses setelah data berhasil dihapus
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        // Mengosongkan input fields dan mengatur fokus ke KodeLokasi
        kosong();
        KodeLokasi.requestFocus();
    } catch(SQLException e){
        // Menampilkan pesan kesalahan jika terjadi exception saat menjalankan query SQL
        JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
    }
    // Memanggil metode datatable() untuk memperbarui tampilan tabel setelah penghapusan data
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

    private void TabelLokasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelLokasiMouseClicked
        // Menangani Aksi Klik pada Tabel Lokasi                                  
        // Mendapatkan indeks baris yang dipilih dalam tabel
        int bar = TabelLokasi.getSelectedRow();
        // Mengambil nilai dari kolom 0 (Kode Lokasi) dan kolom 1 (Nama Lokasi) pada baris yang dipilih
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
    
        // Menetapkan nilai-nilai yang diambil ke komponen-komponen input pada antarmuka pengguna
        KodeLokasi.setText(a);
        NamaLokasi.setText(b);

    
    }//GEN-LAST:event_TabelLokasiMouseClicked

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        // Memanggil metode datatable() untuk menampilkan data sesuai dengan kriteria pencarian
        datatable();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void CariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CariMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CariMouseClicked

    private void CariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CariKeyPressed
       // Metode yang dipanggil ketika pengguna menekan tombol pada komponen input Cari
        // Memeriksa apakah kunci yang ditekan adalah tombol "Enter"
    if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        // Jika tombol "Enter" ditekan, panggil metode datatable() untuk menampilkan data sesuai dengan kriteria pencarian
        datatable();
    }
    }//GEN-LAST:event_CariKeyPressed

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
                new FormLokasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCari;
    private javax.swing.JTextField Cari;
    private javax.swing.JLabel Idinv5;
    private javax.swing.JTextField KodeLokasi;
    private javax.swing.JTextField NamaLokasi;
    private javax.swing.JTable TabelLokasi;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btnubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
