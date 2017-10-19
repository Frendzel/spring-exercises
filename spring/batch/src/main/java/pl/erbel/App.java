package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * Wczytanie danych z csv
 * Zapisanie danych do bazy danych
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        jdbcTemplate.execute("create table jednorozec (\n" +
                "\tid INT,\n" +
                "\timie VARCHAR(50),\n" +
                "\tnazwisko VARCHAR(50),\n" +
                "\temail VARCHAR(50),\n" +
                "\tplec VARCHAR(50)\n" +
                ")");
    }
}
