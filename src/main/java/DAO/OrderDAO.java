package DAO;

import config.DBConnection;
import model.*;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    public static void orderItemsFromCart(HttpSession session, List<CartItem> cartItems) {


        // vytvor objednavku, ziskaj jej pridelene ID
        int orderID = createOrder(session,cartItems);

        //pridaj objednavke tovar z kosika
        try{
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "INSERT INTO obj_polozky (ID_objednavky,ID_tovaru,cena,ks) values (?,?,?,?)";


            cartItems.stream().forEach( cartItem -> {

                PreparedStatement preparedStmt = null;
                try {
                    preparedStmt = con.prepareStatement(sql);
                    preparedStmt.setInt(1, orderID);
                    preparedStmt.setInt(2, cartItem.getArticle().getId());
                    preparedStmt.setDouble(3, cartItem.getCena());
                    preparedStmt.setInt(4, cartItem.getPoc_ks());
                    preparedStmt.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

            con.close();

        }catch(Exception e){e.printStackTrace();}
    }

    private static int createOrder(HttpSession session, List<CartItem> cartItems) {


        Timestamp date = new Timestamp(new Date().getTime());
        System.out.println("vytvorena obj s : ");
        cartItems.stream().forEach(System.out::println);
        double suma = cartItems.stream().mapToDouble(CartItem::getCenaCelkovo).sum();
        System.out.println("celkova suma = " +suma);
        int newID = 0;

        try{
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO objednavky (datum_obj,ID_pouz,suma,stav) values (?,?,?,?)";
            Statement stmt = con.createStatement();



            PreparedStatement preparedStmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setTimestamp(1, date);
            preparedStmt.setInt(2, ((User)session.getAttribute("user")).getId() );
            preparedStmt.setDouble(3, suma);
            preparedStmt.setString(4,"Caka na zabalenie");

            preparedStmt.executeUpdate();

            try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newID = (int) generatedKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating row failed, no ID obtained.");
                }
            }


            con.close();

        }catch(Exception e){e.printStackTrace();}

        return newID;
    }

    private static int getOrderID(Timestamp date, double suma) {
            int ID = 0;
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT ID FROM objednavky WHERE datum_obj = '" + date + "' AND suma = '" + suma + "'";

            ResultSet rs = stmt.executeQuery(sql);


            rs.next();
            ID = rs.getInt("ID");
            System.out.println("ziskane ID objednavky: " + ID);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ID;
    }

    public static void decreaseStockCount(List<CartItem> cartItems) {
        try{
            Connection con = DBConnection.getConnection();


            for (CartItem cartItem: cartItems) {
            String sql = "UPDATE sklad SET ks = ks - " + cartItem.getPoc_ks() + " WHERE ID =" + cartItem.getArticle().getId();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            }

            con.close();

        }catch(Exception e){e.printStackTrace();}




    }

    public static List<Order> getAllOrdersOfUser(HttpSession session) {
        int userID = ((User)session.getAttribute("user")).getId();
        List<Order> orderList = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * FROM objednavky WHERE ID_pouz = " + userID;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next() ) {

                Order order = new Order();
                order.setId(rs.getInt("ID"));
                order.setDate(rs.getString("datum_obj"));
                order.setId_pouz(rs.getInt("ID_pouz"));
                order.setCelkova_suma(rs.getDouble("suma"));
                order.setStav(rs.getString("stav"));

                order.setOrderItems(OrderItemDAO.getAllOrderItemsOfOrder(order.getId()));

                orderList.add(order);
            }

            rs.close();
            stmt.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            return orderList;
        }
        return orderList;

    }
    public static List<Order> getAllOrders() {

        List<Order> orderList = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * FROM objednavky";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next() ) {

                Order order = new Order();
                order.setId(rs.getInt("ID"));
                order.setDate(rs.getString("datum_obj"));
                order.setId_pouz(rs.getInt("ID_pouz"));
                order.setCelkova_suma(rs.getDouble("suma"));
                order.setStav(rs.getString("stav"));

                order.setOrderItems(OrderItemDAO.getAllOrderItemsOfOrder(order.getId()));

                orderList.add(order);
            }

            rs.close();
            stmt.close();


        }
        catch (SQLException e) {
            e.printStackTrace();
            return orderList;
        }
        return orderList;

    }

    public static void deleteOrderById(int orderID) {

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM objednavky WHERE ID =" + orderID;
            stmt.executeUpdate(sql);

            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }



    }

    public static void updateOrderById(int orderID, String status) {

        try{
            Connection con = DBConnection.getConnection();

                String sql = "UPDATE objednavky SET stav = '" + status + "' WHERE ID = " + orderID;
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);


            con.close();

        }catch(Exception e){e.printStackTrace();}



    }
}
