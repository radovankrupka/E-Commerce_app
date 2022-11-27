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


    public static boolean checkIfUserExists(String nick, String pwd)  {
    try {

    Connection con = DBConnection.getConnection();
    Statement stmt = con.createStatement();
    String sql = " select count(*) as poc FROM users " + " WHERE nickname = '" + nick + "' AND password = '" + pwd + "'";
    ResultSet rs = stmt.executeQuery(sql);

            /*"from users WHERE ( users.nickname = '" + nick + "' AND users.password = '" + pwd+"')");*/

    rs.next();
    if (rs.getInt("poc") == 1){ // uspesne prihlasenie
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

    public static User getUserByNick(String nick){
        User user = new User();

        try {


            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = " select  id, nickname,banned FROM users WHERE nickname = '" + nick + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            System.out.println(rs.getInt("id"));
            user.setId(rs.getInt("id"));
            user.setNickname(rs.getString("nickname"));
            user.setBanned(rs.getBoolean("banned"));


            rs.close();
            stmt.close();
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
            user.setNickname(rs.getString("nickname"));
            user.setBanned(rs.getBoolean("banned"));


            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return user;
        }
        return user;
    }


    public static List<User> getAllBannedUsers() {
        List<User> list = new ArrayList<>();

        try{
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE banned > 0");


            while(rs.next()){

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setNickname(rs.getString("nickname"));
                user.setBanned(true);
                System.out.println(user.getNickname());
                list.add(user);
            }

            con.close();

        }catch(Exception e){e.printStackTrace();}

        return list;
    }

    public static void unbanUser(String id) {
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

    }
}