package model;

public class Image {

    private int article_id;

    private  String img_name;

    private byte[] img_data;

    public String getImg_name() {
        return img_name;
    }

    public void setImg_name(String img_name) {
        this.img_name = img_name;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public byte[] getImg_data() {
        return img_data;
    }

    public void setImg_data(byte[] img_data) {
        this.img_data = img_data;
    }
}
