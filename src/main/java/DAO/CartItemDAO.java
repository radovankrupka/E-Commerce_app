package DAO;

import config.DBConnection;
import model.Article;
import model.CartItem;
import model.User;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {


    public static void addItemToCart(Article article, int user_id, int numOfItemsToBuy) {

        try{
            Connection con = DBConnection.getConnection();

            if (itemIsInCart(article, user_id)) {  //ak je article tohto typu uz v kosiku
                if ( enoughItemsInStore(article.getId(), numOfItemsToBuy, user_id) ) {  //skontroluj dostatocny pocet na sklade
                    System.out.println("sklad ma dost");
                    String sql = "UPDATE kosik SET ks = ks + " + numOfItemsToBuy + " WHERE id_tovaru =" + article.getId();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                }
                else {
                    System.out.println("sklad ma malo");
                }
            } else {

                String sql = "INSERT INTO kosik (ID_pouz,ID_tovaru,cena,ks) values (?,?,?,?)";

                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, user_id);
                preparedStmt.setInt(2, article.getId());
                preparedStmt.setDouble(3, article.getCena());
                preparedStmt.setInt(4, numOfItemsToBuy);

                preparedStmt.executeUpdate();

                con.close();
            }
        }catch(Exception e){e.printStackTrace();}



    }

    public static boolean enoughItemsInStore(int article_id, int numOfItems, int user_id) {

        if (getCartItem(article_id,user_id).getPoc_ks() + numOfItems <= ArticleDAO.getNumInStore(getCartItem(article_id,user_id).getArticle().getId())) return true;
            else return false;


    }

    private static CartItem getCartItem(int article_id, int user_id) {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM kosik WHERE ID_tovaru = " + article_id + " AND ID_pouz = " + user_id;

            System.out.println("hladam cart_item s article_ID = " + article_id + " a pouz_id = "+ user_id);

            ResultSet rs = stmt.executeQuery(sql);

            CartItem item = new CartItem();
            rs.next();

            item.setCena(rs.getDouble("cena"));
            item.setId_pouz(rs.getInt("ID_pouz"));
            item.setId_tovaru(rs.getInt("ID_tovaru"));
            item.setPoc_ks(rs.getInt("ks"));
            item.setArticle(ArticleDAO.getItemById(article_id));


            return item;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

        private static boolean itemIsInCart(Article article, int user_id) {

        try{
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT COUNT(*) as pocet FROM kosik WHERE ID_tovaru = '" + article.getId() + "' AND ID_pouz = " +user_id;

            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            if (rs.getInt("pocet") > 0 ){ // nachadza sa v kosiku
                return true;
            }
            else {
                return false;
            }


        }catch(Exception e){e.printStackTrace(); return false;}


    }

    public static List<CartItem> getAllProductsFromCart(User user){  // to redo

        List<CartItem> tovarList = new ArrayList<>();


        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * FROM kosik WHERE ID_pouz =" + user.getId();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next() ) {

                CartItem item = new CartItem();
                item.setId(rs.getInt("ID"));
                item.setId_pouz(rs.getInt("ID_pouz"));
                item.setId_tovaru(rs.getInt("ID_tovaru"));
                item.setCena(rs.getDouble("cena"));
                item.setPoc_ks(rs.getInt("ks"));   //pocet ks v kosiku
                item.setArticle(ArticleDAO.getItemById(item.getId_tovaru()));
                tovarList.add(item);
                System.out.println("pridavam do listu:" + item);
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

    public static void clearCartForUser(HttpSession session) {
        int userID =((User) session.getAttribute("user")).getId();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM kosik WHERE ID_pouz =" + userID;
            stmt.executeUpdate(sql);

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
    }


}
