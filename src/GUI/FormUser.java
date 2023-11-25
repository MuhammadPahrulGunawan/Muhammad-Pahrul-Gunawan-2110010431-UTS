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
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicRadioButtonUI;

/**
 *
 * @author ITAdmin
 */
public class FormUser extends javax.swing.JFrame {
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;

    /**
     * Creates new form User
     */
    public FormUser() {
        initComponents();
        
         // Set transparansi untuk JTextField
        
        //Menghilangkan Bacground JTextField pada Teks ID admin dan memberi border 1 di bawah
        IdAdmin.setOpaque(false);
        IdAdmin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        IdAdmin.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Nama Admin dan memberi border 1 di bawah
        NamaAdmin.setOpaque(false);
        NamaAdmin.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        NamaAdmin.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks Username dan memberi border 1 di bawah
        User.setOpaque(false);
        User.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        User.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Teks password dan memberi border 1 di bawah
        Pass.setOpaque(false);
        Pass.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Pass.setForeground(Color.BLACK);
        //Menghilangkan Bacground JTextField pada Cari Barang dan memberi border 1 di bawah
        Cari.setOpaque(false);
        Cari.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE));
        Cari.setForeground(Color.BLACK);
        //Menghilangkan Bacground RadioButton pada LAKI LAKI
        LakiLaki.setOpaque(false);
        LakiLaki.setForeground(Color.BLACK);
        //Menghilangkan Bacground RadioButton pada PEREMPUAN
        Perempuan.setOpaque(false);
        Perempuan.setForeground(Color.BLACK);
        
        //Mengatur jendela aplikasi menjadi maksimal
        this.setExtendedState(MAXIMIZED_BOTH);
        
        // Memanggil metode kosong(), aktif(), dan datatable() saat program dimulai atau suatu event tertentu terjadi
        kosong();
        aktif();
        datatable();
        
    }
    
         // Metode untuk mengatur fokus ke field input IdAdmin pada antarmuka pengguna terkait admin
        protected void aktif(){
        IdAdmin.requestFocus();
    }

        // Metode untuk mengosongkan field input pada antarmuka pengguna terkait admin
        protected void kosong(){
        IdAdmin.setText("");
        NamaAdmin.setText("");
        Alamat.setText("");
        Cari.setText("");
        // Mengosongkan pemilihan jenis kelamin dari button group
        buttonGroup1.clearSelection();
        Cari.setText("");
        Pass.setText("");
    }

        // Metode untuk menampilkan data admin pada tabel antarmuka pengguna berdasarkan kriteria pencarian
        protected void datatable(){
        // Menentukan kolom yang akan ditampilkan dalam tabel
        Object[] Baris = {"ID User", "Username", "Password", "Nama", "Jenis Kelamin", "Alamat"};
        // Membuat model tabel (tabmode) dengan kolom yang telah ditentukan
        tabmode = new DefaultTableModel(null, Baris);
        // Mendapatkan teks dari field pencarian
        String cariitem = Cari.getText();
    try {
        // Membuat query SQL untuk mengambil data admin berdasarkan ID User atau Nama yang sesuai dengan kriteria pencarian
        String sql = "SELECT * FROM mst_user where id_user like '%"+cariitem+"%' or nama like '%"+cariitem+"%' order by id_user asc";
        // Membuat objek Statement untuk menjalankan query SQL
        Statement stat = conn.createStatement();
        // Mengeksekusi query SQL dan mendapatkan hasil
        ResultSet hasil = stat.executeQuery(sql);
        // Mengisi model tabel dengan data hasil query
        while(hasil.next()){
            tabmode.addRow(new Object[]{
                hasil.getString(1),
                hasil.getString(2),
                hasil.getString(3),
                hasil.getString(4),
                hasil.getString(5),
                hasil.getString(6)
            });
        }
        // Menetapkan model tabel ke komponen tabel (TabelAdmin) pada antarmuka pengguna
        TabelAdmin.setModel(tabmode);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        Idinv3 = new javax.swing.JLabel();
        Idinv4 = new javax.swing.JLabel();
        Idinv5 = new javax.swing.JLabel();
        Idinv6 = new javax.swing.JLabel();
        Idinv7 = new javax.swing.JLabel();
        Idinv8 = new javax.swing.JLabel();
        NamaAdmin = new javax.swing.JTextField();
        Cari = new javax.swing.JTextField();
        IdAdmin = new javax.swing.JTextField();
        Pass = new javax.swing.JTextField();
        LakiLaki = new javax.swing.JRadioButton();
        Perempuan = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Alamat = new javax.swing.JTextArea();
        btnsimpan2 = new javax.swing.JButton();
        btnubah2 = new javax.swing.JButton();
        btnhapus2 = new javax.swing.JButton();
        btnbatal2 = new javax.swing.JButton();
        btnkeluar2 = new javax.swing.JButton();
        Idinv9 = new javax.swing.JLabel();
        User = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelAdmin = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel1.setkEndColor(new java.awt.Color(86, 125, 47));
        kGradientPanel1.setkStartColor(new java.awt.Color(157, 255, 240));

        jPanel2.setBackground(new java.awt.Color(111, 110, 164));

        jLabel18.setFont(new java.awt.Font("Lucida Fax", 1, 24)); // NOI18N
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/MenuDataUser.png"))); // NOI18N
        jLabel18.setText("DATA ADMIN");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 739, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        Idinv3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv3.setText("ID ADMIN");

        Idinv4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv4.setText("NAMA ADMIN");

        Idinv5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv5.setText("JENIS KELAMIN");

        Idinv6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv6.setText("USERNAME");

        Idinv7.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv7.setText("PASSWORD");

        Idinv8.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv8.setText("ALAMAT");

        NamaAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        NamaAdmin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Cari.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Cari.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CariKeyPressed(evt);
            }
        });

        IdAdmin.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        IdAdmin.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        Pass.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        LakiLaki.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        LakiLaki.setText("LAKI LAKI");

        Perempuan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        Perempuan.setText("PEREMPUAN");

        Alamat.setColumns(20);
        Alamat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Alamat.setRows(5);
        jScrollPane1.setViewportView(Alamat);

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

        btnbatal2.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btnbatal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/BATAL.png"))); // NOI18N
        btnbatal2.setText("BATAL");
        btnbatal2.setBorder(null);
        btnbatal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatal2ActionPerformed(evt);
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

        Idinv9.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        Idinv9.setText("List Data Admin :");

        User.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        User.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btncari.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/CARI.png"))); // NOI18N
        btncari.setText("CARI");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        TabelAdmin.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelAdminMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelAdmin);

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnkeluar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(btnsimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnubah2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnhapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(btnbatal2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(Idinv9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(User, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Idinv3)
                                .addComponent(Idinv4)
                                .addComponent(Idinv6)
                                .addComponent(Idinv5)
                                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Idinv7)
                                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                        .addComponent(LakiLaki)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Perempuan))
                                    .addComponent(IdAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                                    .addComponent(NamaAdmin)
                                    .addComponent(Pass))))
                        .addGap(132, 132, 132)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                .addComponent(Idinv8)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Idinv3)
                    .addComponent(Idinv8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addComponent(IdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Idinv4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NamaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Idinv5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LakiLaki)
                            .addComponent(Perempuan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Idinv6))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(User, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Idinv7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsimpan2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnubah2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnhapus2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbatal2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnkeluar2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Idinv9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
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

    private void btnsimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpan2ActionPerformed

     // Mendapatkan nilai dari komponen input jenis kelamin
    String jenis = null;
    if (LakiLaki.isSelected()) {
        jenis = "LAKI LAKI";
    } else {
        jenis = "PEREMPUAN";
    }

    // Validasi input sebelum menyimpan ke database
    if (IdAdmin.getText().isEmpty() || NamaAdmin.getText().isEmpty() || jenis == null
            || User.getText().isEmpty() || Pass.getText().isEmpty() || Alamat.getText().isEmpty()) {
        // Jika ada input yang kosong, tampilkan pesan kesalahan
        JOptionPane.showMessageDialog(null, "Mohon lengkapi semua kolom");
        return; // Keluar dari metode jika ada input yang kosong
    }

    // Jika semua input telah diisi, lanjutkan dengan penyimpanan
    String sql = "INSERT INTO mst_user VALUES (?,?,?,?,?,?)";
    try {
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, IdAdmin.getText());
        stat.setString(2, User.getText());
        stat.setString(3, Pass.getText());
        stat.setString(4, NamaAdmin.getText());
        stat.setString(5, jenis);
        stat.setString(6, Alamat.getText());

        stat.executeUpdate();
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
        kosong();
        IdAdmin.requestFocus();
    } catch(SQLException e) {
        JOptionPane.showMessageDialog(null, "Data gagal disimpan" + e);
    }
    datatable();
    }//GEN-LAST:event_btnsimpan2ActionPerformed

    private void btnubah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubah2ActionPerformed

        //Jika data pada tabel tidak di pilih maka akan muncul pesan "Pilih data yang akan diubah"
        int selectedRow = TabelAdmin.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah");
            return;
        }
         // Menentukan jenis kelamin berdasarkan pemilihan pada button group
        String jenis = null;
        if (LakiLaki.isSelected()) {
        jenis = "Laki - laki";
        } else {
          jenis = "Perempuan";
        }

        // Mendefinisikan query SQL untuk mengupdate data pengguna berdasarkan ID User
        String sql = "UPDATE mst_user SET username=?,password=?,nama=?,jenis_kelamin=?,alamat=? WHERE id_user='"+IdAdmin.getText()+"'";
        try {
        // Membuat objek PreparedStatement untuk mengeksekusi query SQL
        PreparedStatement stat = conn.prepareStatement(sql);
        // Mengatur nilai parameter pada query SQL berdasarkan input dari pengguna
        stat.setString(1, User.getText());
        stat.setString(2, Pass.getText());
        stat.setString(3, NamaAdmin.getText());
        stat.setString(4, jenis);
        stat.setString(5, Alamat.getText());

        // Mengeksekusi query SQL untuk mengupdate data
        stat.executeUpdate();
    
        // Menampilkan pesan sukses setelah data berhasil diubah
        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
    
        // Mengosongkan input fields dan mengatur fokus ke IdAdmin
        kosong();
        IdAdmin.requestFocus();
       } catch(SQLException e){
        // Menampilkan pesan kesalahan jika terjadi exception saat menjalankan query SQL
        JOptionPane.showMessageDialog(null, "Data gagal diubah"+e);
       }
        // Memanggil metode datatable() untuk memperbarui tampilan tabel setelah perubahan data
        datatable();

    }//GEN-LAST:event_btnubah2ActionPerformed

    private void btnhapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapus2ActionPerformed
        // Menampilkan dialog konfirmasi untuk menghapus data
        int ok = JOptionPane.showConfirmDialog(null, "Apakah anda yakin menghapus data ini?", "konfirm dialog", JOptionPane.YES_NO_OPTION);

        // Memeriksa apakah pengguna menekan tombol "Yes" pada dialog konfirmasi
        if (ok == 0) {
         // Menyiapkan query SQL untuk menghapus data pengguna berdasarkan ID User
        String sql = "DELETE FROM mst_user WHERE id_user='"+IdAdmin.getText()+"'";
        try {
        // Membuat objek PreparedStatement untuk mengeksekusi query SQL
        PreparedStatement stat = conn.prepareStatement(sql);
        // Mengeksekusi query SQL untuk menghapus data
        stat.executeUpdate();
        // Menampilkan pesan sukses setelah data berhasil dihapus
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
        // Mengosongkan input fields dan mengatur fokus ke IdAdmin
        kosong();
        IdAdmin.requestFocus();
      } catch(SQLException e){
        // Menampilkan pesan kesalahan jika terjadi exception saat menjalankan query SQL
        JOptionPane.showMessageDialog(null, "Data gagal dihapus"+e);
    }
    // Memanggil metode datatable() untuk memperbarui tampilan tabel setelah penghapusan data
    datatable();
    }

    }//GEN-LAST:event_btnhapus2ActionPerformed

    private void btnbatal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatal2ActionPerformed
        kosong();
        datatable();
    }//GEN-LAST:event_btnbatal2ActionPerformed

    private void btnkeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluar2ActionPerformed
        dispose();
    }//GEN-LAST:event_btnkeluar2ActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        // Memanggil metode datatable() untuk menampilkan data sesuai dengan kriteria pencarian
        datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void TabelAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelAdminMouseClicked
       //Menangani Aksi klik pada tabel Admin
        // Mendapatkan indeks baris yang dipilih dalam tabel TabelAdmin
       int bar = TabelAdmin.getSelectedRow();

        // Mengambil nilai dari setiap kolom pada baris yang dipilih
        String a = tabmode.getValueAt(bar, 0).toString();
        String b = tabmode.getValueAt(bar, 1).toString();
        String c = tabmode.getValueAt(bar, 2).toString();
        String d = tabmode.getValueAt(bar, 3).toString();
        String e = tabmode.getValueAt(bar, 4).toString();
        String f = tabmode.getValueAt(bar, 5).toString();

        // Menetapkan nilai-nilai yang diambil ke komponen-komponen input pada antarmuka pengguna
        IdAdmin.setText(a);
        User.setText(b);
        Pass.setText(c);
        NamaAdmin.setText(d);

        // Menetapkan nilai jenis kelamin berdasarkan nilai pada kolom jenis kelamin
        if("LAKI LAKI".equals(e)){
        LakiLaki.setSelected(true);
        } else {
    Perempuan.setSelected(true);
    }

    Alamat.setText(f);

    }//GEN-LAST:event_TabelAdminMouseClicked

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
                new FormUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Alamat;
    private javax.swing.JTextField Cari;
    private javax.swing.JTextField IdAdmin;
    private javax.swing.JLabel Idinv3;
    private javax.swing.JLabel Idinv4;
    private javax.swing.JLabel Idinv5;
    private javax.swing.JLabel Idinv6;
    private javax.swing.JLabel Idinv7;
    private javax.swing.JLabel Idinv8;
    private javax.swing.JLabel Idinv9;
    private javax.swing.JRadioButton LakiLaki;
    private javax.swing.JTextField NamaAdmin;
    private javax.swing.JTextField Pass;
    private javax.swing.JRadioButton Perempuan;
    private javax.swing.JTable TabelAdmin;
    private javax.swing.JTextField User;
    private javax.swing.JButton btnbatal2;
    private javax.swing.JButton btncari;
    private javax.swing.JButton btnhapus2;
    private javax.swing.JButton btnkeluar2;
    private javax.swing.JButton btnsimpan2;
    private javax.swing.JButton btnubah2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
