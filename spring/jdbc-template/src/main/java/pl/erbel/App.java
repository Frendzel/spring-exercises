package pl.erbel;

import org.h2.jdbc.JdbcSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * wykorzystać JDBCTemplate do:
 * zdropowania tabeli jeżeli istnieje
 * stworzenia tabeli
 * uzupełnienia tabeli
 * wykonania selecta
 * wykonania batchowego inserta
 * przepisania danych z tabeli do modelu javowego
 */


@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    public void run(String... args) throws Exception {
        jdbcTemplate.execute("DROP TABLE IF EXISTS jednorozec ");
        jdbcTemplate.execute("create table jednorozec (\n" +
                "\tid INT,\n" +
                "\timie VARCHAR(50),\n" +
                "\tnazwisko VARCHAR(50),\n" +
                "\temail VARCHAR(50),\n" +
                "\tplec CHAR(10)\n" +
                ")");
        jdbcTemplate.execute("insert into jednorozec (id, imie, nazwisko, email, plec) values (1, 'Leslie', 'Clulow', 'lclulow0@cbslocal.com', 'Male');\n");
        List<Jednorozec> stajnia = jdbcTemplate.query("select * from jednorozec", (rs, number) ->
                new Jednorozec(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));

        String data = "2, \"Judon\", \"Romanini\", \"jromanini1@sun.com\", \"Male\";\n" +
                "3, \"Aurilia\", \"Kersting\", \"akersting2@google.fr\", \"Female\";\n" +
                "4, \"Ulla\", \"Lamden\", \"ulamden3@fotki.com\", \"Female\";\n" +
                "5, \"Jania\", \"Littell\", \"jlittell4@homestead.com\", \"Female\";\n" +
                "6, \"Beitris\", \"Halligan\", \"bhalligan5@reverbnation.com\", \"Female\";\n" +
                "7, \"Jaquenette\", \"Humphris\", \"jhumphris6@amazon.de\", \"Female\";\n" +
                "8, \"Susette\", \"Wescott\", \"swescott7@biblegateway.com\", \"Female\";\n" +
                "9, \"Cecil\", \"Esome\", \"cesome8@ezinearticles.com\", \"Male\";\n" +
                "10, \"Merwyn\", \"Marrion\", \"mmarrion9@omniture.com\", \"Male\";";
        List<Object[]> batchArgs = Arrays.stream(data.split(";")).map(s -> s.split(",")).collect(toList());

        jdbcTemplate.batchUpdate("insert into jednorozec (id, imie, nazwisko, email, plec) values (?, ?, ?, ?, ?)",
                batchArgs);

        zmianaPlci("shemale", 1);
        zmianaPlci("shemale", 2);
        System.out.println(jdbcTemplate.query("select * from jednorozec where id = ?", new Object[]{2},
                (rs, number) -> new Jednorozec(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))));

//        try {
        transactionalZmianaPlci("123456", 1, 2);
//        } catch (RuntimeException e) {
//            System.out.println(e);
//        }
        System.out.println(jdbcTemplate.query("select * from jednorozec where id = ?", new Object[]{1},
                (rs, number) -> new Jednorozec(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))));
        System.out.println(jdbcTemplate.query("select * from jednorozec where id = ?", new Object[]{2},
                (rs, number) -> new Jednorozec(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))));
    }

    private void zmianaPlci(String nowaPlec, int idTegoBiedaka) {
        jdbcTemplate.update("update jednorozec set plec = ? where id = ?", nowaPlec, idTegoBiedaka);
    }

    @Transactional(rollbackFor = {RuntimeException.class}, propagation = Propagation.REQUIRED)
    public void transactionalZmianaPlci(String nowaPlec, int idTegoBiedaka, int idOk) throws RuntimeException
    {
        jdbcTemplate.update("update jednorozec set plec = ? where id = ?", nowaPlec, idOk);
        throw new RuntimeException();
//        jdbcTemplate.update("update jednorozec set plec = ? where id = ?", nowaPlec + nowaPlec, idTegoBiedaka);
    }

}
