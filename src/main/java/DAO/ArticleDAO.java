package DAO;

import config.DBConnection;
import model.Article;
import model.User;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {


        public static List<Article> getAllProducts(HttpSession session){
            User user = UserDAO.getUserFromSession(session);
            List<Article> articleList = new ArrayList<>();

            try {

                Statement stmt = DBConnection.getConnection(session).createStatement();
                String sql = "select * FROM sklad";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next() ) {

                    Article item = new Article();
                    item.setId(rs.getInt("id"));
                    item.setNazov(rs.getString("nazov"));
                    item.setCena(rs.getDouble("cena")*(100-user.getZlava())/100);
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


    public static Article getItemById(int itemToAddId, HttpSession session) {
            User user = UserDAO.getUserFromSession(session);
            Article item = new Article();

        try {


            Statement stmt = DBConnection.getConnection(session).createStatement();
            String sql = "select * FROM sklad WHERE id = " + itemToAddId;
            ResultSet rs = stmt.executeQuery(sql);


            rs.next();
                item.setId(rs.getInt("id"));
                item.setNazov(rs.getString("nazov"));
                item.setCena(rs.getDouble("cena")*(100-user.getZlava())/100);
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

    public static int getNumInStore(int article_id, HttpSession session) {
        int pocet = 0;
        try {


            Statement stmt = DBConnection.getConnection(session).createStatement();
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
