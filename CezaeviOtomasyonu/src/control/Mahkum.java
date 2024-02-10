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
public class Mahkum extends User{
    
    private String kogus,suc,yatıs,tahliye;
    Statement st = null;
    ResultSet rs = null;
    Connection con =  (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Mahkum(int id, String tckn, String name,String kogus, String suc,String yatıs, String tahliye) {
        super(id, tckn, name);
        this.kogus = kogus;
        this.suc = suc;
        this.yatıs = yatıs;
        this.tahliye = tahliye;
    }
    
     public ArrayList<Mahkum> getMahkumList() throws SQLException {

        ArrayList<Mahkum> list = new ArrayList<>();
        Mahkum obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM mahkümlar ");
            while (rs.next()) {
                obj = new Mahkum(rs.getInt("ID"), rs.getString("TCKN"), rs.getString("Name"), rs.getString("Koğuş"), rs.getString("Suç"),rs.getString("Yatış_Tarihi"), rs.getString("Tahliye_Tarihi"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }
     
     public boolean addMahkum(String tckn, String name, String kogus, String suc, String yatıs, String tahliye) throws SQLException {
        String query = "INSERT INTO mahkümlar" + "(TCKN,Name,Koğuş,Suç,Yatış Tarihi,Tahliye Tarihi) VALUES " + "(?,?,?,?,?,?)";
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
     
     public ArrayList<Mahkum> getMahkumList(int id) throws SQLException {

        ArrayList<Mahkum> list = new ArrayList<>();
        Mahkum obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM mahkümlar WHERE ID="+id);
            while (rs.next()) {
                obj = new Mahkum(rs.getInt("ID"), rs.getString("TCKN"), rs.getString("Name"), rs.getString("Koğuş"), rs.getString("Suç"),rs.getString("Yatış Tarihi"), rs.getString("Tahliye Tarihi"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    public Mahkum() {
    }

    public String getKogus() {
        return kogus;
    }

    public void setKogus(String kogus) {
        this.kogus = kogus;
    }

    public String getSuc() {
        return suc;
    }

    public void setSuc(String suc) {
        this.suc = suc;
    }

    public String getYatıs() {
        return yatıs;
    }

    public void setYatıs(String yatıs) {
        this.yatıs = yatıs;
    }

    public String getTahliye() {
        return tahliye;
    }

    public void setTahliye(String tahliye) {
        this.tahliye = tahliye;
    }
    
    
    
}
