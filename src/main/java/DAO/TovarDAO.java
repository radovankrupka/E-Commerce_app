package DAO;

import config.DBConnection;
import model.Tovar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TovarDAO {


        public static List<Tovar> getAllProducts(){

            List<Tovar> tovarList = new ArrayList<>();

            try {

                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                String sql = "select * FROM sklad";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next() ) {

                    Tovar item = new Tovar();
                    item.setId(rs.getInt("id"));
                    item.setNazov(rs.getString("nazov"));
                    item.setCena(rs.getDouble("cena"));
                    item.setKs(rs.getInt("ks"));
                    item.toString();

                    tovarList.add(item);
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                return tovarList;
            }
            return tovarList;


        }


    public static Tovar getItemById(String itemToAddId) {
            Tovar item = new Tovar();

        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * FROM sklad WHERE id = " + itemToAddId;
            ResultSet rs = stmt.executeQuery(sql);


            rs.next();
                item.setId(rs.getInt("id"));
                item.setNazov(rs.getString("nazov"));
                item.setCena(rs.getDouble("cena"));
                item.setKs(rs.getInt("ks"));
                item.toString();


            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return item;
        }
        return item;
    }

    public static int getNumInStore(int id) {
        int pocet = 0;
        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select ks FROM sklad WHERE id = " + id;
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            pocet = rs.getInt("ks");

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            
        }
        return pocet;


    }
}
