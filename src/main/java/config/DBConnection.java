package config;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection(HttpSession session){ //ZMENIT DB NA NOVU ADRESU

        return ((Connection) session.getAttribute("connection"));
    }

    public static void createConnection(HttpSession session) {
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/obchod","root","");
        }catch(Exception e){System.out.println(e);}

        session.setAttribute("connection",con);
    }

}
