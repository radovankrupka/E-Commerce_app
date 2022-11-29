package DAO;

import config.DBConnection;
import model.CartItem;
import model.Tovar;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAO {


    public static void addItemToCart(Tovar tovar, int id, int numOfItems) {

        try{
            Connection con = DBConnection.getConnection();

            if (itemIsInCart(tovar)) {  //ak je tovar tohto typu uz v kosiku
                if ( enoughItemsInStore(tovar.getId(), numOfItems) ) {  //skontroluj dostatocny pocet na sklade
                    System.out.println("sklad ma dost");
                    String sql = "UPDATE kosik SET ks = ks + " + numOfItems + " WHERE id_tovaru =" + tovar.getId();
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                }
                else {
                    System.out.println("sklad ma malo");
                }
            } else {

                String sql = "INSERT INTO kosik (ID_pouz,ID_tovaru,cena,ks) values (?,?,?,?)";

                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, id);
                preparedStmt.setInt(2, tovar.getId());
                preparedStmt.setDouble(3, tovar.getCena());
                preparedStmt.setInt(4, numOfItems);

                preparedStmt.executeUpdate();

                con.close();
            }
        }catch(Exception e){e.printStackTrace();}



    }

    private static boolean enoughItemsInStore(int id, int numOfItems) {

        if (getCartItem(id).getPoc_ks() + numOfItems <= TovarDAO.getNumInStore(id)) return true;
            else return false;


    }

    private static CartItem getCartItem(int id) {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM kosik WHERE ID_tovaru = " + id;

            ResultSet rs = stmt.executeQuery(sql);

            CartItem item = new CartItem();
            rs.next();

            item.setCena(rs.getDouble("cena"));
            item.setId_pouz(rs.getInt("ID_pouz"));
            item.setId_tovaru(rs.getInt("ID_tovaru"));
            item.setPoc_ks(rs.getInt("ks"));


            return item;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

        private static boolean itemIsInCart(Tovar tovar) {

        try{
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT COUNT(*) as pocet FROM kosik WHERE ID_tovaru = " + tovar.getId();

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
}
