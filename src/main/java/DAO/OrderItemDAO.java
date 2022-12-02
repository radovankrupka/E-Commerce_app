package DAO;

import config.DBConnection;
import model.CartItem;
import model.OrderItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {


    public static List<OrderItem> getAllOrderItemsOfOrder(int order_id) {

        List<OrderItem> orderItems = new ArrayList<>();


        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * FROM obj_polozky WHERE ID_objednavky =" + order_id;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next() ) {

                OrderItem item = new OrderItem();
                item.setId(rs.getInt("ID"));
                item.setTovar(ArticleDAO.getItemById(rs.getInt("ID_tovaru")));
                item.setCena_kus(rs.getDouble("cena"));
                item.setPoc_ks(rs.getInt("ks"));   //pocet ks v kosiku
                orderItems.add(item);

            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return orderItems;


    }

    public static void deleteOrderItemsByOrderId(int orderID) {


        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM obj_polozky WHERE ID_objednavky =" + orderID;
            stmt.executeUpdate(sql);

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
