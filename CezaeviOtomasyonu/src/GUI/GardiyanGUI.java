/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

//import Model.Gardiyan;
import control.Gardiyan;
import control.Mahkum;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Image;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class GardiyanGUI extends javax.swing.JFrame {

    static Gardiyan gardiyan = new Gardiyan();
    static Mahkum mahkum = new Mahkum();
    private DefaultTableModel vardiyaModel = null;
    private Object[] vardiyaData = null;
    private DefaultTableModel mahkumModel = null;
    private Object[] mahkumData = null;
    private DefaultTableModel sucModel = null;
    private Object[] sucData = null;

    public GardiyanGUI(Gardiyan gardiyan) throws SQLException {
        initComponents();
        Image icon = new ImageIcon(this.getClass().getResource("/icons/adalet32.png")).getImage();
        this.setIconImage(icon);
        
        Date tarih = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        lbl_tarih.setText(df.format(tarih));
        lbl_kullanıcı.setText(gardiyan.getName());

        //////////////////////////
        vardiyaModel = new DefaultTableModel();
        Object[] colVardiyaName = new Object[2];
        colVardiyaName[0] = "ID";
        colVardiyaName[1] = "Tarih";
        vardiyaModel.setColumnIdentifiers(colVardiyaName);
        vardiyaData = new Object[2];
        for (int i = 0; i < gardiyan.getTarihList(gardiyan.getId()).size(); i++) {
            vardiyaData[0] = gardiyan.getTarihList(gardiyan.getId()).get(i).getId();
            vardiyaData[1] = gardiyan.getTarihList(gardiyan.getId()).get(i).getTarih();
            vardiyaModel.addRow(vardiyaData);
        }
        tbl_vardiya.setModel(vardiyaModel);

        /////////////////////////////////
        sucModel = new DefaultTableModel();
        Object[] colSucName = new Object[3];
        colSucName[0] = "Suç";
        colSucName[1] = "Yatış Tarihi";
        colSucName[2] = "Tahliye Tarihi";
        sucModel.setColumnIdentifiers(colSucName);
        sucData = new Object[3];

        /////////////////////////
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
        lbl_date = new javax.swing.JLabel();
        lbl_search = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_tarih = new javax.swing.JLabel();
        lbl_kullanıcı = new javax.swing.JLabel();
        pnl_date = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_vardiya = new javax.swing.JTable();
        pnl_search = new javax.swing.JPanel();
        search_field = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_mahkumlar = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_suclar = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

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

        lbl_date.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(204, 0, 0));
        lbl_date.setText("Çalışma saatleri");
        lbl_date.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dateMouseClicked(evt);
            }
        });
        pnl_menu.add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, 30));

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

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Kullanıcı :");
        pnl_menu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, 20));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tarih: ");
        pnl_menu.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 50, 20));

        lbl_tarih.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        pnl_menu.add(lbl_tarih, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 110, 20));

        lbl_kullanıcı.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 14)); // NOI18N
        pnl_menu.add(lbl_kullanıcı, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 110, 20));

        pnl_wrapper.add(pnl_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 80));

        pnl_date.setBackground(new java.awt.Color(255, 255, 255));
        pnl_date.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        tbl_vardiya.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_vardiya);

        pnl_date.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 760, 350));

        pnl_wrapper.add(pnl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 370));

        pnl_search.setBackground(new java.awt.Color(255, 255, 255));
        pnl_search.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_field.setBackground(new java.awt.Color(255, 255, 255));
        search_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_fieldKeyPressed(evt);
            }
        });
        pnl_search.add(search_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 30));

        jScrollPane2.setBorder(null);

        tbl_mahkumlar.setBackground(new java.awt.Color(255, 255, 255));
        tbl_mahkumlar.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Suç", "Yatış Tarihi", "Tahliye Tarihi"
            }
        ));
        jScrollPane3.setViewportView(tbl_suclar);

        pnl_search.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 390, 300));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/SearchRed32.png"))); // NOI18N
        jButton1.setText("Ara");
        jButton1.setBorder(null);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnl_search.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 100, 30));

        pnl_wrapper.add(pnl_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 800, 370));

        getContentPane().add(pnl_wrapper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_dateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dateMouseClicked
        lbl_date.setForeground(new Color(204, 0, 0));
        lbl_search.setForeground(Color.BLACK);

        pnl_date.setVisible(true);
        pnl_search.setVisible(false);

    }//GEN-LAST:event_lbl_dateMouseClicked

    private void lbl_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_searchMouseClicked
        lbl_search.setForeground(new Color(204, 0, 0));
        lbl_date.setForeground(Color.BLACK);

        pnl_date.setVisible(false);
        pnl_search.setVisible(true);
    }//GEN-LAST:event_lbl_searchMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tbl_mahkumlar.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter(search_field.getText()));

        tbl_mahkumlar.setRowSorter(sorter);
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered

    }//GEN-LAST:event_jButton1MouseEntered

    private void search_fieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_fieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GardiyanGUI(gardiyan).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_kullanıcı;
    private javax.swing.JLabel lbl_search;
    private javax.swing.JLabel lbl_tarih;
    private javax.swing.JPanel pnl_date;
    private javax.swing.JPanel pnl_menu;
    private javax.swing.JPanel pnl_search;
    private javax.swing.JPanel pnl_wrapper;
    private javax.swing.JTextField search_field;
    private javax.swing.JTable tbl_mahkumlar;
    private javax.swing.JTable tbl_suclar;
    private javax.swing.JTable tbl_vardiya;
    // End of variables declaration//GEN-END:variables

}