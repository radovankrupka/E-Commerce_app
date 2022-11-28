package DAO;

import config.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    public static boolean checkIfUserExists(String login, String pwd)  {
    try {

    Connection con = DBConnection.getConnection();
    Statement stmt = con.createStatement();
    String sql = " select count(*) as poc FROM users WHERE login = '" + login + "' AND pwd = '" + pwd + "'";
    ResultSet rs = stmt.executeQuery(sql);


    rs.next();
    if (rs.getInt("poc") == 1){ // uspesne prihlasenie
        System.out.println("vysl: " + rs.getInt("poc"));
        return true;
    }
    else {
        return false;
    }
    } catch (SQLException e) {
    e.printStackTrace();
    return false;
    }
    }

    public static User getUserByLogin(String login){
        User user = new User();

        try {


            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = " select  * FROM users WHERE login = '" + login + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            System.out.println(rs.getInt("id"));
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setAdresa(rs.getString("adresa"));
            user.setPwd(rs.getString("pwd"));
            user.setZlava(rs.getInt("zlava"));
            user.setMeno(rs.getString("meno"));
            user.setPriezvisko(rs.getString("priezvisko"));
            user.setPoznamky(rs.getString("poznamky"));
            user.setJe_admin(rs.getBoolean("je_admin"));



            rs.close();
            stmt.close();
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
        return user;
    }

    public static User getUserById(int id){

        User user = new User();

        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = " select * FROM users WHERE id = '" + id + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setAdresa(rs.getString("adresa"));
            user.setPwd(rs.getString("pwd"));
            user.setZlava(rs.getInt("zlava"));
            user.setMeno(rs.getString("meno"));
            user.setPriezvisko(rs.getString("priezvisko"));
            user.setPoznamky(rs.getString("poznamky"));
            user.setJe_admin(rs.getBoolean("je_admin"));


            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
        return user;
    }




    /*public static void unbanUser(String id) {
        int ID = Integer.parseInt(id);

        try{
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE users SET banned = 0  WHERE id =" + ID;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            con.close();

        }catch(Exception e){e.printStackTrace();}



    }

    public static void banUser(String id) {

        int ID = Integer.parseInt(id);

        try{
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE users SET banned = 1  WHERE id =" + ID;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            con.close();

        }catch(Exception e){e.printStackTrace();}

    }*/
}