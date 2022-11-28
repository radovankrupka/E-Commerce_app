package model;

public class User {


    private int id;
    private String login;
    private String pwd;
    private String adresa;
    private int zlava;
    private String meno;
    private String priezvisko;
    private String poznamky;
    private boolean je_admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getZlava() {
        return zlava;
    }

    public void setZlava(int zlava) {
        this.zlava = zlava;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public String getPoznamky() {
        return poznamky;
    }

    public void setPoznamky(String poznamky) {
        this.poznamky = poznamky;
    }

    public boolean isJe_admin() {
        return je_admin;
    }

    public void setJe_admin(boolean je_admin) {
        this.je_admin = je_admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                ", adresa='" + adresa + '\'' +
                ", zlava=" + zlava +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", poznamky='" + poznamky + '\'' +
                ", je_admin=" + je_admin +
                '}';
    }
}
