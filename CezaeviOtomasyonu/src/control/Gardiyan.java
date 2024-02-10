/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.sql.*;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author user
 */
public class Gardiyan extends User{
    Statement st = null;
    ResultSet rs = null;
    Connection con =  (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    

    public Gardiyan() {
    }

    public Gardiyan(int id, String tckn, String name, String password, String type) {
        super(id, tckn, name, password, type);
    }

   public ArrayList<Vardiya> getTarihList(int memur_id) throws SQLException {

        ArrayList<Vardiya> list = new ArrayList<>();
        Vardiya obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM memur_vardiya WHERE Memur_ID=" + memur_id);
            
            while (rs.next()) {
                obj = new Vardiya(rs.getInt("ID"), rs.getInt("Memur_ID"), rs.getString("Memur_Name"), rs.getString("Tarih"), rs.getString("Status"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }
   
    public ArrayList<Vardiya> getTarihList() throws SQLException {

        ArrayList<Vardiya> list = new ArrayList<>();
        Vardiya obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM memur_vardiya");
            
            while (rs.next()) {
                obj = new Vardiya(rs.getInt("ID"), rs.getInt("Memur_ID"), rs.getString("Memur_Name"), rs.getString("Tarih"), rs.getString("Status"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }
   
  
   
    public boolean addHours(int memur_id, String memur_name, String tarih) throws SQLException {
        
        String query = "INSERT INTO memur_vardiya" + "(Memur_ID,Memur_Name,Tarih) VALUES" + "(?,?,?)";
        boolean key = false;
        int count = 0;
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM memur_vardiya WHERE Status='a' AND Memur_ID=" + memur_id + " AND Tarih='" + tarih + "'");
            while (rs.next()) {
                count++;
               
            }
            if (count == 0) {
                
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, memur_id);
                preparedStatement.setString(2, memur_name);
                preparedStatement.setString(3, tarih);
                preparedStatement.executeUpdate();
                 key = true;
            }
           
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
    
    public ArrayList<User> getMahkumList() throws SQLException {

        ArrayList<User> list = new ArrayList<>();
        User obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM mahkümlar ");
            while (rs.next()) {
                obj = new User(rs.getInt("ID"), rs.getString("TCKN"), rs.getString("Name"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
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
      
      public boolean addGardiyan(String tckn, String name, String password) throws SQLException {
        String query = "INSERT INTO memurlar" + "(TCKN,Name,Password,Type) VALUES" + "(?,?,?,?)";
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

    
}
