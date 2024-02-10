package control;

import DB_Item.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class Vardiya {

    private int id, memur_id;
    private String memur_name, tarih, status;

    DBConnection conn = new DBConnection();
    Statement st = null;
    ResultSet rs = null;
    Connection con = (Connection) conn.connDB();
    PreparedStatement preparedStatement = null;

    public Vardiya() {
    }

    public Vardiya(int id, int memur_id, String memur_name, String tarih, String status) {
        this.id = id;
        this.memur_id = memur_id;
        this.memur_name = memur_name;
        this.tarih = tarih;
        this.status = status;
    }

    public boolean updateHours(int memur_id, String tarih) throws SQLException {

        String query = "UPDATE memur_vardiya SET status='p' WHERE Memur_ID=" + memur_id + " AND Tarih='" + tarih + "'";
        boolean key = false;
        int count = 0;
        try {

            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM whours WHERE status='a' AND Memur_ID=" + memur_id + " AND Tarih='" + tarih + "'");
            while (rs.next()) {
                count++;

            }
            if (count == 0) {
                preparedStatement = con.prepareStatement(query);
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

    public ArrayList<Vardiya> getTarihList(int memur_id) throws SQLException {

        ArrayList<Vardiya> list = new ArrayList<>();
        Vardiya obj;

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM memur_vardiya WHERE status='a' AND Memur_ID=" + memur_id);
            while (rs.next()) {
                obj = new Vardiya(rs.getInt("ID"), rs.getInt("Memur_ID"), rs.getString("Memur_Name"), rs.getString("Tarih"), rs.getString("Status"));
                list.add(obj);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemur_id() {
        return memur_id;
    }

    public void setMemur_id(int memur_id) {
        this.memur_id = memur_id;
    }

    public String getMemur_name() {
        return memur_name;
    }

    public void setMemur_name(String memur_name) {
        this.memur_name = memur_name;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
