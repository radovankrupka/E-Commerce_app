package DAO;

import config.DBConnection;
import model.Article;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {


        public static List<Article> getAllProducts(){

            List<Article> articleList = new ArrayList<>();

            try {

                Connection con = DBConnection.getConnection();
                Statement stmt = con.createStatement();
                String sql = "select * FROM sklad";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next() ) {

                    Article item = new Article();
                    item.setId(rs.getInt("id"));
                    item.setNazov(rs.getString("nazov"));
                    item.setCena(rs.getDouble("cena"));
                    item.setKs(rs.getInt("ks"));
                    item.toString();

                    articleList.add(item);
                }

                rs.close();
                stmt.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
                return articleList;
            }
            return articleList;


        }


    public static Article getItemById(int itemToAddId) {
            Article item = new Article();

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

    public static int getNumInStore(int article_id) {
        int pocet = 0;
        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select ks FROM sklad WHERE id = " + article_id;
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
