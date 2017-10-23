package pl.erbel;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * "\tid INT,\n" +
 * "\timie VARCHAR(50),\n" +
 * "\tnazwisko VARCHAR(50),\n" +
 * "\temail VARCHAR(50),\n" +
 * "\tplec VARCHAR(50)\n" +
 */

@Entity
public class Jednorozec {

    @Id
    private int id;

    private String imie;

    private String nazwisko;

    private String email;

    private String plec;

    public Jednorozec(int id, String imie, String nazwisko, String email, String plec) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.plec = plec;
    }

    public Jednorozec() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    @Override
    public String toString() {
        return "Jednorozec{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", email='" + email + '\'' +
                ", plec='" + plec + '\'' +
                '}';
    }
}
