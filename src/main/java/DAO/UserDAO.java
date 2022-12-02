package DAO;

import config.DBConnection;
import model.OrderItem;
import model.User;

import java.sql.*;
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

    public static void createUser(User user){

        try{
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO users (login,pwd,adresa,zlava,meno,priezvisko,je_admin) values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStmt = con.prepareStatement(sql);
            preparedStmt.setString (1, user.getLogin());
            preparedStmt.setString (2, user.getPwd());
            preparedStmt.setString(3,user.getAdresa());
            preparedStmt.setInt(4,user.getZlava());
            preparedStmt.setString(5, user.getMeno());
            preparedStmt.setString(6, user.getPriezvisko());
            preparedStmt.setBoolean(7,user.isJe_admin());

            preparedStmt.executeUpdate();

            con.close();

        }catch(Exception e){e.printStackTrace();}


    }

    public static boolean checkUserByLogin(String login)  {

        ResultSet rs = null;
        Statement stmt = null;
        Connection con = null;
        try {

            con = DBConnection.getConnection();
            stmt = con.createStatement();
            String sql = " select  COUNT(*) as pocet FROM  users WHERE login = '" + login + "'";
            rs = stmt.executeQuery(sql);

            rs.next();
            int poc = rs.getInt("pocet");
            rs.close();
            stmt.close();
            con.close();

            return ( poc > 0);


        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public static List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();


        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * FROM users";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next() ) {

               User user = new User();

                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setAdresa(rs.getString("adresa"));
                user.setPwd(rs.getString("pwd"));
                user.setZlava(rs.getInt("zlava"));
                user.setMeno(rs.getString("meno"));
                user.setPriezvisko(rs.getString("priezvisko"));
                user.setPoznamky(rs.getString("poznamky"));
                user.setJe_admin(rs.getBoolean("je_admin"));

                userList.add(user);

            }

            rs.close();
            stmt.close();
            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userList;


    }



}