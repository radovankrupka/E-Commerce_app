package DAO;

import config.DBConnection;
import model.OrderItem;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {


    public static List<OrderItem> getAllOrderItemsOfOrder(int order_id, HttpSession session) {

        List<OrderItem> orderItems = new ArrayList<>();


        try {

            Statement stmt = DBConnection.getConnection(session).createStatement();
            String sql = "select * FROM obj_polozky WHERE ID_objednavky =" + order_id;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next() ) {

                OrderItem item = new OrderItem();
                item.setId(rs.getInt("ID"));
                item.setTovar(ArticleDAO.getItemById(rs.getInt("ID_tovaru"),session));
                item.setCena_kus(item.getTovar().getCena());
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

    public static void deleteOrderItemsByOrderId(int orderID, HttpSession session) {


        try {

            Statement stmt = DBConnection.getConnection(session).createStatement();
            String sql = "DELETE FROM obj_polozky WHERE ID_objednavky =" + orderID;
            stmt.executeUpdate(sql);

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
