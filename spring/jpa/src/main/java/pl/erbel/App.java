package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class App implements CommandLineRunner {

    private static final Logger LOGGER =
            Logger.getLogger(App.class);

    @Autowired
    JednorozecRepository jednorozecRepository;

    @Autowired
    JednorozecService jednorozecService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100000; i++) {
            jednorozecRepository.save(new Jednorozec(i, "a" + i, "b" + i, "c" + i, "d" + i));
        }
        LOGGER.info(jednorozecRepository.findAll());
        LOGGER.info(jednorozecRepository.findByImie("a11"));
        LOGGER.info(jednorozecRepository.findByImie("a11"));
        LOGGER.info(jednorozecRepository.findByNazwisko("b12"));
        LOGGER.info(jednorozecRepository.findByNazwisko("b12"));
        LOGGER.info(jednorozecRepository.count());
        LOGGER.info(jednorozecRepository.doTheMagic("b0"));
        LOGGER.info(jednorozecRepository.doTheMagic("b0"));
        LOGGER.info(jednorozecRepository.doTheCachedMagic("b1"));
        LOGGER.info(jednorozecRepository.doTheCachedMagic("b1"));

//        jednorozecService.transactionalTest(
//                jednorozecRepository.findByImie("a11").get(0));
//
//
    }
}
