package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection(){ //ZMENIT DB NA NOVU ADRESU
        Connection con=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/chat_db","root","");
        }catch(Exception e){System.out.println(e);}
        return con;
    }

}
