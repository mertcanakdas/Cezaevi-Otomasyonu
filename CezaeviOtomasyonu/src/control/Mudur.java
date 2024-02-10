package control;

import java.sql.*;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author user
 */
public class Mudur extends User {

    Statement st = null;
    ResultSet rs = null;
    Connection con = (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Mudur() {
    }

    public Mudur(int id, String tcno, String name, String password, String type) {
        super(id, tcno, name, password, type);
    }

    public ArrayList<User> getMemurList() throws SQLException {

        ArrayList<User> list = new ArrayList<>();
        User obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM memurlar");
            while (rs.next()) {
                obj = new User(rs.getInt("ID"), rs.getString("TCKN"), rs.getString("Name"), rs.getString("Password"), rs.getString("Type"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    public boolean addGardiyan(String name, String tckn, String password) throws SQLException {
        String query = "INSERT INTO memurlar" + "(Name,TCKN,Password,Type) VALUES" + "(?,?,?,?)";
        boolean key = false;
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, tckn);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, "Gardiyan");
            preparedStatement.executeUpdate();
            key = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteGardiyanModel(int id) throws SQLException {
        boolean key = false;
        String query = "DELETE FROM user WHERE id = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public boolean updateGardiyan(String password, String tckn) throws SQLException {
        boolean key = false;
        String query = "UPDATE memurlar SET Password = ? WHERE TCKN = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, tckn);
            preparedStatement.executeUpdate();
            key = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<Mahkum> getMahkumList() throws SQLException {

        ArrayList<Mahkum> list = new ArrayList<>();
        Mahkum obj;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM mahkümlar");
            while (rs.next()) {
                obj = new Mahkum(rs.getInt("ID"), rs.getString("TCKN"), rs.getString("Name"), rs.getString("Koğuş"), rs.getString("Suç"), rs.getString("Yatış Tarihi"), rs.getString("Tahliye Tarihi"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    public boolean addMahkum(String tckn, String name, String kogus, String suc, String yatıs, String tahliye) throws SQLException {
        String query = "INSERT INTO mahkümlar " + "(TCKN,Name,Koğuş,Suç,Yatış_Tarihi,Tahliye_Tarihi) VALUES " + "(?,?,?,?,?,?)";
        boolean key = false;
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, tckn);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, kogus);
            preparedStatement.setString(4, suc);
            preparedStatement.setString(5, yatıs);
            preparedStatement.setString(6, tahliye);
            preparedStatement.executeUpdate();
            key = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteMahkumModel(int id) throws SQLException {
        boolean key = false;
        String query = "DELETE FROM mahkümlar WHERE id = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public boolean addHours(int memur_id, String memur_name, String tarih) throws SQLException {

        String query = "INSERT INTO memur_vardiya" + "(Memur_ID,Memur_Name,Tarih) VALUES" + "(?,?,?)";
        boolean key = false;
        
        try {

//            st = con.createStatement();
//            rs = st.executeQuery("SELECT * FROM memur_vardiya WHERE Memur_ID=" + memur_id + " AND Tarih='" + tarih + "'");
//            while (rs.next()) {
//                
//
//            }
            
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, memur_id);
                preparedStatement.setString(2, memur_name);
                preparedStatement.setString(3, tarih);
                preparedStatement.executeUpdate();
                key = true;
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        if (key) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteTarihModel(int id) throws SQLException {
        boolean key = false;
        String query = "DELETE FROM memur_vardiya WHERE ID = ?";
        try {
            st = con.createStatement();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (key) {
            return true;
        } else {
            return false;
        }

    }
}
