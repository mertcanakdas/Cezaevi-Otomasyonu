/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import control.Gardiyan;
import control.Mahkum;
import control.Mudur;
import DB_Item.Item;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class MudurGUI extends javax.swing.JFrame {

    static Gardiyan gardiyan = new Gardiyan();
    static Mudur mudur = new Mudur();
    static Mahkum mahkum = new Mahkum();
    private DefaultTableModel vardiyaModel = null;
    private Object[] vardiyaData = null;
    private DefaultTableModel mahkumModel = null;
    private Object[] mahkumData = null;
    private DefaultTableModel sucModel = null;
    private Object[] sucData = null;
    private DefaultTableModel mahkum_tbl_Model = null;
    private Object[] mahkum_tbl_data = null;

    public MudurGUI(Mudur mudur) throws SQLException {
        initComponents();
        Image icon = new ImageIcon(this.getClass().getResource("/icons/adalet32.png")).getImage();
        this.setIconImage(icon);
        
        Date tarih = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        lbl_tarih.setText(df.format(tarih));
        lbl_kullanıcı.setText(mudur.getName());
        
        //table_mahküm.getColumnModel().getColumn(3).setPreferredWidth(200);

        ///////////////////////////////////////////////////
        vardiyaModel = new DefaultTableModel();
        Object[] colvardiyaName = new Object[3];
        colvardiyaName[0] = "ID";
        colvardiyaName[1] = "Ad Soyad";
        colvardiyaName[2] = "Tarih";
        vardiyaModel.setColumnIdentifiers(colvardiyaName);
        vardiyaData = new Object[3];
        for (int i = 0; i < gardiyan.getTarihList().size(); i++) {
            vardiyaData[0] = gardiyan.getTarihList().get(i).getId();
            vardiyaData[1] = gardiyan.getTarihList().get(i).getMemur_name();
            vardiyaData[2] = gardiyan.getTarihList().get(i).getTarih();
            vardiyaModel.addRow(vardiyaData);
        }
        tbl_vardiya.setModel(vardiyaModel);
        //////////////////////////////////////

        //////////////////////////////////////
        sucModel = new DefaultTableModel();
        Object[] colSucName = new Object[3];
        colSucName[0] = "Suç";
        colSucName[1] = "Yatış Tarihi";
        colSucName[2] = "Tahliye Tarihi";
        sucModel.setColumnIdentifiers(colSucName);
        sucData = new Object[3];

        /////////////////////////////////
        mahkumModel = new DefaultTableModel();
        Object[] colMahkumName = new Object[4];
        colMahkumName[0] = "ID";
        colMahkumName[1] = "TCKN";
        colMahkumName[2] = "İsim";
        colMahkumName[3] = "Koğuş";
        mahkumModel.setColumnIdentifiers(colMahkumName);
        mahkumData = new Object[4];
        for (int i = 0; i < mahkum.getMahkumList().size(); i++) {
            mahkumData[0] = mahkum.getMahkumList().get(i).getId();
            mahkumData[1] = mahkum.getMahkumList().get(i).getTckn();
            mahkumData[2] = mahkum.getMahkumList().get(i).getName();
            mahkumData[3] = mahkum.getMahkumList().get(i).getKogus();
            mahkumModel.addRow(mahkumData);
        }
        tbl_mahkumlar.setModel(mahkumModel);

        //////////////////////////////////////////////
        mahkum_tbl_Model = new DefaultTableModel();
        Object[] col_tbl_name = new Object[6];
        col_tbl_name[0] = "ID";
        col_tbl_name[1] = "Ad Soyad";
        col_tbl_name[2] = "Suç";
        col_tbl_name[3] = "Koğuş";
        col_tbl_name[4] = "Yatış Tarihi";
        col_tbl_name[5] = "Tahliye Tarihi";
        mahkum_tbl_data = new Object[6];
        mahkum_tbl_Model.setColumnIdentifiers(col_tbl_name);
        for (int i = 0; i < mahkum.getMahkumList().size(); i++) {
            mahkum_tbl_data[0] = mahkum.getMahkumList().get(i).getId();
            mahkum_tbl_data[1] = mahkum.getMahkumList().get(i).getName();
            mahkum_tbl_data[2] = mahkum.getMahkumList().get(i).getSuc();
            mahkum_tbl_data[3] = mahkum.getMahkumList().get(i).getKogus();
            mahkum_tbl_data[4] = mahkum.getMahkumList().get(i).getYatıs();
            mahkum_tbl_data[5] = mahkum.getMahkumList().get(i).getTahliye();
            mahkum_tbl_Model.addRow(mahkum_tbl_data);
        }
        tbl_mahkumlar2.setModel(mahkum_tbl_Model);

        //////////////////////////////////////////////////////////////////
        for (int i = 0; i < mudur.getMemurList().size(); i++) {
            select_gardiyan.addItem(new Item(mudur.getMemurList().get(i).getId(), mudur.getMemurList().get(i).getName()));
        }

       
        /////////////////////////////////////////////////////////////
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gardiyan.setName(select_gardiyan.getSelectedItem().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String date = "";

                date += sdf.format(date_chooser.getDate());
                // tekrar bak errorlar çalışmıyor
                if (select_gardiyan.getSelectedIndex() == -1 && date.equals("seçiniz")) {
                    error1.setForeground(new Color(204, 0, 0));
                    error2.setForeground(new Color(204, 0, 0));
                } else if (select_gardiyan.getSelectedIndex() == -1 && !date.equals("seçiniz")) {
                    error1.setForeground(new Color(204, 0, 0));
                } else if (select_gardiyan.getSelectedIndex() != -1 && date.equals("seçiniz")) {
                    error2.setForeground(new Color(204, 0, 0));
                } else {

                    String selectedDate = date;

                    try {
                        boolean control = mudur.addHours(gardiyan.getId(), gardiyan.getName(), selectedDate);
                        if (control) {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "İşlem başarılı", "Çalışma Saatleri", JOptionPane.OK_OPTION, new ImageIcon(getClass().getResource("/icons/Done.png")));
                            updateTarihModel();
                        } else {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "İşlem Başarısız", "Çalışma Saatleri", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }

        });
        //

        ////////////////////////////////////////////////////////////////
        btn_addMahkum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                String yatıs = "";
                String tahliye = "";
                yatıs += sdf.format(yatıs_chooser.getDate());
                tahliye += sdf.format(tahliye_chooser.getDate());

                String suc = select_suc.getSelectedItem().toString();
                String kogus = select_kogus.getSelectedItem().toString();
                
                

                // tekrar bak errorlar çalışmıyor
                if (nameMahkum_field.getText().length() == 0 && tcknMahkum_field.getText().length() == 0 && suc.length() == 0 && yatıs.length() == 0 && tahliye.length() == 0 && kogus.length() == 0) {
                    mahkum_error1.setForeground(new Color(204, 0, 0));
                    mahkum_error2.setForeground(new Color(204, 0, 0));
                    mahkum_error3.setForeground(new Color(204, 0, 0));
                    mahkum_error4.setForeground(new Color(204, 0, 0));
                    mahkum_error5.setForeground(new Color(204, 0, 0));
                    mahkum_error6.setForeground(new Color(204, 0, 0));
                } 
                else {

                    try {
                        boolean control = mudur.addMahkum(tcknMahkum_field.getText(), nameMahkum_field.getText(), kogus, suc, yatıs, tahliye);
                        if (control) {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "İşlem başarılı", "Mahkum Ekleme", JOptionPane.OK_OPTION, new ImageIcon(getClass().getResource("/icons/Done.png")));
                            updateMahkumModel();
                        } else {
                            UIManager.put("OptionPane.okButtonText", "Tamam");
                            JOptionPane.showMessageDialog(rootPane, "İşlem Başarısız", "Mahkum Ekleme", JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/icons/closeRed48px.png")));
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }

        });

        //
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_wrapper = new javax.swing.JPanel();
        pnl_menu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_gardiyan = new javax.swing.JLabel();
        lbl_search = new javax.swing.JLabel();
        lbl_mahküm = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_kullanıcı = new javax.swing.JLabel();
        lbl_tarih = new javax.swing.JLabel();
        pnl_gardiyan = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_vardiya = new javax.swing.JTable();
        select_gardiyan = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        date_chooser = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        error1 = new javax.swing.JLabel();
        error2 = new javax.swing.JLabel();
        btn_delete = new javax.swing.JButton();
        id_field = new javax.swing.JTextField();
        error3 = new javax.swing.JLabel();
        pnl_search = new javax.swing.JPanel();
        search_field = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_mahkumlar = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_suclar = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        pnl_mahküm = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        nameMahkum_field = new javax.swing.JTextField();
        tcknMahkum_field = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        idMahkum_field = new javax.swing.JTextField();
        btn_deleteMahkum = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_mahkumlar2 = new javax.swing.JTable();
        mahkum_error1 = new javax.swing.JLabel();
        mahkum_error2 = new javax.swing.JLabel();
        mahkum_error3 = new javax.swing.JLabel();
        mahkum_error7 = new javax.swing.JLabel();
        btn_addMahkum = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        select_suc = new javax.swing.JComboBox();
        yatıs_chooser = new com.toedter.calendar.JDateChooser();
        tahliye_chooser = new com.toedter.calendar.JDateChooser();
        select_kogus = new javax.swing.JComboBox();
        mahkum_error5 = new javax.swing.JLabel();
        mahkum_error6 = new javax.swing.JLabel();
        mahkum_error4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_wrapper.setBackground(new java.awt.Color(255, 255, 255));
        pnl_wrapper.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_menu.setBackground(new java.awt.Color(204, 204, 204));
        pnl_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/ADALET BAKANLIĞI LOGO PNG.png"))); // NOI18N
        pnl_menu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ADALET");
        pnl_menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 90, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("BAKANLIĞI");
        pnl_menu.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 100, -1));

        lbl_gardiyan.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        lbl_gardiyan.setForeground(new java.awt.Color(204, 0, 0));
        lbl_gardiyan.setText("Gardiyan vardiya");
        lbl_gardiyan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_gardiyanMouseClicked(evt);
            }
        });
        pnl_menu.add(lbl_gardiyan, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, 30));

        lbl_search.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        lbl_search.setForeground(new java.awt.Color(0, 0, 0));
        lbl_search.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_search.setText("Arama");
        lbl_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_searchMouseClicked(evt);
            }
        });
        pnl_menu.add(lbl_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 60, 30));

        lbl_mahküm.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        lbl_mahküm.setForeground(new java.awt.Color(0, 0, 0));
        lbl_mahküm.setText("Mahküm panel");
        lbl_mahküm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_mahkümMouseClicked(evt);
            }
        });
        pnl_menu.add(lbl_mahküm, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Kullanıcı :");
        pnl_menu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, 20));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tarih: ");
        pnl_menu.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 50, 20));

        lbl_kullanıcı.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        pnl_menu.add(lbl_kullanıcı, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 100, 20));

        lbl_tarih.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        pnl_menu.add(lbl_tarih, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 110, 20));

        pnl_wrapper.add(pnl_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 80));

        pnl_gardiyan.setBackground(new java.awt.Color(255, 255, 255));
        pnl_gardiyan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        tbl_vardiya.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Ad Soyad", "Tarih"
            }
        ));
        tbl_vardiya.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_vardiyaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_vardiya);

        pnl_gardiyan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 530, 350));

        select_gardiyan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "seçiniz" }));
        select_gardiyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                select_gardiyanActionPerformed(evt);
            }
        });
        pnl_gardiyan.add(select_gardiyan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 120, 40));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        jLabel4.setText("Tarih Belirleme");
        pnl_gardiyan.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 30));
        pnl_gardiyan.add(date_chooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 120, 30));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        jLabel5.setText("Gardiyan Atama");
        pnl_gardiyan.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 120, 30));

        btn_add.setBackground(new java.awt.Color(204, 0, 0));
        btn_add.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setText("Ekle");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        pnl_gardiyan.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 120, 40));

        error1.setForeground(new java.awt.Color(255, 255, 255));
        error1.setText("Lütfen geçerli bir gardiyan seçiniz.");
        pnl_gardiyan.add(error1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 200, -1));

        error2.setForeground(new java.awt.Color(255, 255, 255));
        error2.setText("Lütfen geçerli bir tarih seçiniz.");
        pnl_gardiyan.add(error2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 200, -1));

        btn_delete.setBackground(new java.awt.Color(204, 0, 0));
        btn_delete.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("sil");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        pnl_gardiyan.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 120, 40));

        id_field.setBackground(new java.awt.Color(255, 255, 255));
        id_field.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        id_field.setForeground(new java.awt.Color(0, 0, 0));
        id_field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id_field.setEnabled(false);
        pnl_gardiyan.add(id_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 120, 40));

        error3.setForeground(new java.awt.Color(255, 255, 255));
        error3.setText("Lütfen geçerli bir gardiyan seçiniz.");
        pnl_gardiyan.add(error3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 200, -1));

        pnl_wrapper.add(pnl_gardiyan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 370));

        pnl_search.setBackground(new java.awt.Color(255, 255, 255));
        pnl_search.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_field.setBackground(new java.awt.Color(255, 255, 255));
        search_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fieldActionPerformed(evt);
            }
        });
        pnl_search.add(search_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 30));

        jScrollPane2.setBorder(null);

        tbl_mahkumlar.setBackground(new java.awt.Color(255, 255, 255));
        tbl_mahkumlar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Ad Soyad", "Koğuş"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_mahkumlar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_mahkumlarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_mahkumlar);

        pnl_search.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 390, 300));

        jScrollPane3.setBorder(null);

        tbl_suclar.setBackground(new java.awt.Color(255, 255, 255));
        tbl_suclar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Suçlar", "Yatış Tarihi", "Tahliye Tarihi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbl_suclar);

        pnl_search.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 390, 300));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SearchRed32.png"))); // NOI18N
        jButton1.setText("Ara");
        jButton1.setBorder(null);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnl_search.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 100, 30));

        pnl_wrapper.add(pnl_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 370));

        pnl_mahküm.setBackground(new java.awt.Color(255, 255, 255));
        pnl_mahküm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("Koğuş");
        pnl_mahküm.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, -1));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("TCKN");
        pnl_mahküm.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 0));
        jLabel14.setText("Suç");
        pnl_mahküm.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        nameMahkum_field.setBackground(new java.awt.Color(255, 255, 255));
        nameMahkum_field.setForeground(new java.awt.Color(0, 0, 0));
        nameMahkum_field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nameMahkum_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameMahkum_fieldActionPerformed(evt);
            }
        });
        pnl_mahküm.add(nameMahkum_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, 30));

        tcknMahkum_field.setBackground(new java.awt.Color(255, 255, 255));
        tcknMahkum_field.setForeground(new java.awt.Color(0, 0, 0));
        tcknMahkum_field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tcknMahkum_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcknMahkum_fieldActionPerformed(evt);
            }
        });
        pnl_mahküm.add(tcknMahkum_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 130, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("ID");
        pnl_mahküm.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 20, 30));

        idMahkum_field.setBackground(new java.awt.Color(255, 255, 255));
        idMahkum_field.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        idMahkum_field.setForeground(new java.awt.Color(0, 0, 0));
        idMahkum_field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        idMahkum_field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idMahkum_field.setEnabled(false);
        idMahkum_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idMahkum_fieldActionPerformed(evt);
            }
        });
        pnl_mahküm.add(idMahkum_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 130, 30));

        btn_deleteMahkum.setBackground(new java.awt.Color(24, 119, 242));
        btn_deleteMahkum.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        btn_deleteMahkum.setForeground(new java.awt.Color(255, 255, 255));
        btn_deleteMahkum.setText("Sil");
        btn_deleteMahkum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteMahkumActionPerformed(evt);
            }
        });
        pnl_mahküm.add(btn_deleteMahkum, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 210, 30));

        jScrollPane4.setBorder(null);

        tbl_mahkumlar2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Ad Soyad", "Suç","Koğuş","Yatış Tarihi","Tahliye Tarihi"
            }
        )
        {
            public boolean isCellEditable(int row,int column){
                return false;
            }
        }
    );
    tbl_mahkumlar2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tbl_mahkumlar2MouseClicked(evt);
        }
    });
    jScrollPane4.setViewportView(tbl_mahkumlar2);

    pnl_mahküm.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 470, 350));

    mahkum_error1.setForeground(new java.awt.Color(255, 255, 255));
    mahkum_error1.setText("Bu kısım boş bırakılamaz");
    pnl_mahküm.add(mahkum_error1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 140, -1));

    mahkum_error2.setForeground(new java.awt.Color(255, 255, 255));
    mahkum_error2.setText("Bu kısım boş bırakılamaz");
    pnl_mahküm.add(mahkum_error2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, -1));

    mahkum_error3.setForeground(new java.awt.Color(255, 255, 255));
    mahkum_error3.setText("Bu kısım boş bırakılamaz");
    pnl_mahküm.add(mahkum_error3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 160, -1));

    mahkum_error7.setForeground(new java.awt.Color(255, 255, 255));
    mahkum_error7.setText("Lütfen geçerli bir mahküm giriniz");
    pnl_mahküm.add(mahkum_error7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 180, -1));

    btn_addMahkum.setBackground(new java.awt.Color(204, 0, 0));
    btn_addMahkum.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
    btn_addMahkum.setForeground(new java.awt.Color(255, 255, 255));
    btn_addMahkum.setText("Ekle");
    btn_addMahkum.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            btn_addMahkumActionPerformed(evt);
        }
    });
    pnl_mahküm.add(btn_addMahkum, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 200, 30));

    jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
    jLabel16.setForeground(new java.awt.Color(204, 0, 0));
    jLabel16.setText("Ad Soyad");
    pnl_mahküm.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

    jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
    jLabel17.setForeground(new java.awt.Color(204, 0, 0));
    jLabel17.setText("Yatış Tarihi");
    pnl_mahküm.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

    jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
    jLabel18.setForeground(new java.awt.Color(204, 0, 0));
    jLabel18.setText("Tahliye Tarih");
    pnl_mahküm.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

    select_suc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Soykırım", "Göçmen Kaçakçılığı", "İnsan Ticareti", "Kasten yaralama", "Taksirle yaralama", "Tehdit", "Şantaj", "Kişiyi hürriyetinden yoksun kılma", "Eğitim ve öğretim hakkının engellenmesi", "Kamu hizmetlerinden yararlanma hakkının engellenmesi", "Hırsızlık", "Nitelikli hırsızlık", "Yağma", "Nitelikli yağma", "Mala zarar verme", "Mala zarar vermenin nitelikli halleri", "İbadethanelere ve mezarlıklara zarar verme", "Hakkı olmayan yere tecavüz" }));
    select_suc.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            select_sucActionPerformed(evt);
        }
    });
    pnl_mahküm.add(select_suc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 130, 30));

    yatıs_chooser.setBackground(new java.awt.Color(255, 255, 255));
    pnl_mahküm.add(yatıs_chooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 120, 30));
    pnl_mahküm.add(tahliye_chooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 120, 30));

    select_kogus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "D1", "D2", "D3" }));
    pnl_mahküm.add(select_kogus, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 120, 30));

    mahkum_error5.setForeground(new java.awt.Color(255, 255, 255));
    mahkum_error5.setText("Bu kısım boş bırakılamaz");
    pnl_mahküm.add(mahkum_error5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 150, -1));

    mahkum_error6.setForeground(new java.awt.Color(255, 255, 255));
    mahkum_error6.setText("Bu kısım boş bırakılamaz");
    pnl_mahküm.add(mahkum_error6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 140, -1));

    mahkum_error4.setForeground(new java.awt.Color(255, 255, 255));
    mahkum_error4.setText("Bu kısım boş bırakılamaz");
    pnl_mahküm.add(mahkum_error4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 140, -1));

    pnl_wrapper.add(pnl_mahküm, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 370));

    getContentPane().add(pnl_wrapper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 450));

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_gardiyanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_gardiyanMouseClicked
        lbl_gardiyan.setForeground(new Color(204, 0, 0));
        lbl_search.setForeground(Color.BLACK);
        lbl_mahküm.setForeground(Color.BLACK);

        pnl_gardiyan.setVisible(true);
        pnl_search.setVisible(false);
        pnl_mahküm.setVisible(false);


    }//GEN-LAST:event_lbl_gardiyanMouseClicked

    private void lbl_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_searchMouseClicked
        lbl_gardiyan.setForeground(Color.BLACK);
        lbl_search.setForeground(new Color(204, 0, 0));
        lbl_mahküm.setForeground(Color.BLACK);

        pnl_gardiyan.setVisible(false);
        pnl_search.setVisible(true);
        pnl_mahküm.setVisible(false);

    }//GEN-LAST:event_lbl_searchMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tbl_mahkumlar.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(search_field.getText()));

        tbl_mahkumlar.setRowSorter(sorter);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_deleteMahkumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteMahkumActionPerformed
         if (idMahkum_field.getText().length() == 0) {
            mahkum_error7.setForeground(new Color(204,0,0));
        } else {
            int selectedID = Integer.parseInt(idMahkum_field.getText());
            try {
                UIManager.put("OptionPane.okButtonText", "Evet");
                UIManager.put("OptionPane.noButtonText", "Hayır");
                int result = JOptionPane.showConfirmDialog(rootPane, "Bu işlemi gerçekleştirmek istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    boolean control = mudur.deleteMahkumModel(selectedID);
                    if (control) {

                        idMahkum_field.setText(null);
                        updateMahkumModel();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_deleteMahkumActionPerformed

    private void nameMahkum_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameMahkum_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameMahkum_fieldActionPerformed

    private void lbl_mahkümMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_mahkümMouseClicked
        lbl_gardiyan.setForeground(Color.BLACK);
        lbl_search.setForeground(Color.BLACK);
        lbl_mahküm.setForeground(new Color(204, 0, 0));

        pnl_gardiyan.setVisible(false);
        pnl_search.setVisible(false);
        pnl_mahküm.setVisible(true);

    }//GEN-LAST:event_lbl_mahkümMouseClicked

    private void select_gardiyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_gardiyanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_gardiyanActionPerformed

    private void tbl_mahkumlarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mahkumlarMouseClicked
        int selRow = tbl_mahkumlar.getSelectedRow();
        if (selRow >= 0) {
            String selMahkum = tbl_mahkumlar.getModel().getValueAt(selRow, 0).toString();
            int selMahkumID = Integer.parseInt(selMahkum);
            DefaultTableModel clearModel = (DefaultTableModel) tbl_suclar.getModel();
            clearModel.setRowCount(0);
            try {
                for (int i = 0; i < mahkum.getMahkumList().size(); i++) {
                    sucData[0] = mahkum.getMahkumList().get(i).getSuc();
                    sucData[1] = mahkum.getMahkumList().get(i).getYatıs();
                    sucData[2] = mahkum.getMahkumList().get(i).getTahliye();
                    sucModel.addRow(sucData);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            tbl_suclar.setModel(sucModel);
        }
    }//GEN-LAST:event_tbl_mahkumlarMouseClicked

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed

    }//GEN-LAST:event_btn_addActionPerformed

    private void search_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fieldActionPerformed

    }//GEN-LAST:event_search_fieldActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        if (id_field.getText().length() == 0) {
            error3.setForeground(Color.red);
        } else {
            int selectedID = Integer.parseInt(id_field.getText());
            try {
                UIManager.put("OptionPane.okButtonText", "Evet");
                UIManager.put("OptionPane.noButtonText", "Hayır");
                int result = JOptionPane.showConfirmDialog(rootPane, "Bu işlemi gerçekleştirmek istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    boolean control = mudur.deleteTarihModel(selectedID);
                    if (control) {

                        id_field.setText(null);
                        updateTarihModel();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void tbl_vardiyaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vardiyaMouseClicked
        id_field.setText(tbl_vardiya.getValueAt(tbl_vardiya.getSelectedRow(), 0).toString());
        error3.setForeground(Color.WHITE);
    }//GEN-LAST:event_tbl_vardiyaMouseClicked

    private void idMahkum_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idMahkum_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idMahkum_fieldActionPerformed

    private void btn_addMahkumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addMahkumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_addMahkumActionPerformed

    private void select_sucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_select_sucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_select_sucActionPerformed

    private void tcknMahkum_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcknMahkum_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcknMahkum_fieldActionPerformed

    private void tbl_mahkumlar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_mahkumlar2MouseClicked
        idMahkum_field.setText(tbl_mahkumlar2.getValueAt(tbl_mahkumlar2.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tbl_mahkumlar2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MudurGUI(mudur).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void updateTarihModel() throws SQLException {
        DefaultTableModel updatedTable = (DefaultTableModel) tbl_vardiya.getModel();
        updatedTable.setRowCount(0);
        for (int i = 0; i < gardiyan.getTarihList().size(); i++) {
            vardiyaData[0] = gardiyan.getTarihList().get(i).getId();
            vardiyaData[1] = gardiyan.getTarihList().get(i).getMemur_name();
            vardiyaData[2] = gardiyan.getTarihList().get(i).getTarih();
            vardiyaModel.addRow(vardiyaData);
        }

    }
    
    public void updateMahkumModel() throws SQLException {
        DefaultTableModel updatedTable = (DefaultTableModel) tbl_mahkumlar2.getModel();
        updatedTable.setRowCount(0);
        for (int i = 0; i < mahkum.getMahkumList().size(); i++) {
            mahkum_tbl_data[0] = mahkum.getMahkumList().get(i).getId();
            mahkum_tbl_data[1] = mahkum.getMahkumList().get(i).getName();
            mahkum_tbl_data[2] = mahkum.getMahkumList().get(i).getSuc();
            mahkum_tbl_data[3] = mahkum.getMahkumList().get(i).getKogus();
            mahkum_tbl_data[4] = mahkum.getMahkumList().get(i).getYatıs();
            mahkum_tbl_data[5] = mahkum.getMahkumList().get(i).getTahliye();
            mahkum_tbl_Model.addRow(mahkum_tbl_data);
        }

    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_addMahkum;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_deleteMahkum;
    private com.toedter.calendar.JDateChooser date_chooser;
    private javax.swing.JLabel error1;
    private javax.swing.JLabel error2;
    private javax.swing.JLabel error3;
    private javax.swing.JTextField idMahkum_field;
    private javax.swing.JTextField id_field;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbl_gardiyan;
    private javax.swing.JLabel lbl_kullanıcı;
    private javax.swing.JLabel lbl_mahküm;
    private javax.swing.JLabel lbl_search;
    private javax.swing.JLabel lbl_tarih;
    private javax.swing.JLabel mahkum_error1;
    private javax.swing.JLabel mahkum_error2;
    private javax.swing.JLabel mahkum_error3;
    private javax.swing.JLabel mahkum_error4;
    private javax.swing.JLabel mahkum_error5;
    private javax.swing.JLabel mahkum_error6;
    private javax.swing.JLabel mahkum_error7;
    private javax.swing.JTextField nameMahkum_field;
    private javax.swing.JPanel pnl_gardiyan;
    private javax.swing.JPanel pnl_mahküm;
    private javax.swing.JPanel pnl_menu;
    private javax.swing.JPanel pnl_search;
    private javax.swing.JPanel pnl_wrapper;
    private javax.swing.JTextField search_field;
    private javax.swing.JComboBox select_gardiyan;
    private javax.swing.JComboBox select_kogus;
    private javax.swing.JComboBox select_suc;
    private com.toedter.calendar.JDateChooser tahliye_chooser;
    private javax.swing.JTable tbl_mahkumlar;
    private javax.swing.JTable tbl_mahkumlar2;
    private javax.swing.JTable tbl_suclar;
    private javax.swing.JTable tbl_vardiya;
    private javax.swing.JTextField tcknMahkum_field;
    private com.toedter.calendar.JDateChooser yatıs_chooser;
    // End of variables declaration//GEN-END:variables
}
