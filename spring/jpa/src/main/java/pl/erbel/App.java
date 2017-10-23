package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class App implements CommandLineRunner {

    @Autowired
    JednorozecRepository jednorozecRepository;

    @Autowired
    JednorozecService jednorozecService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            jednorozecRepository.save(new Jednorozec(i, "a" + i, "b" + i, "c" + i, "d" + i));
        }
        System.out.println(jednorozecRepository.findAll());
        System.out.println(jednorozecRepository.findByImie("a11"));
        System.out.println(jednorozecRepository.findByImie("a11"));
        System.out.println(jednorozecRepository.findByNazwisko("b12"));
        System.out.println(jednorozecRepository.findByNazwisko("b12"));
        System.out.println(jednorozecRepository.count());
        System.out.println(jednorozecRepository.doTheMagic("b0"));

//        jednorozecService.transactionalTest(
//                jednorozecRepository.findByImie("a11").get(0));
//
//
    }
}
