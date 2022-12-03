package DAO;

import config.DBConnection;
import model.Image;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImageDAO {
    public static Image getImageById(HttpSession session, int article_id, HttpServletRequest request, HttpServletResponse response) {

       Image image = new Image();

        try {

            Statement stmt = DBConnection.getConnection(session).createStatement();

            String sql = "SELECT * FROM images WHERE art_id = " + article_id;

            ResultSet rs = stmt.executeQuery(sql);


            if (rs.next()) {

                Blob blob = rs.getBlob("img_blob");
                image.setImg_data(blob.getBytes(1, (int)blob.length()));

                image.setArticle_id(article_id);
                image.setImg_name(rs.getString("img_name"));
                blob.free();

            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            }



            }
        catch (Exception e) {
            e.printStackTrace();
        }
        return image;


    }
}
