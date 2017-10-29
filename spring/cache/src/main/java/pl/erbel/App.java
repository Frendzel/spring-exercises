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
    ManagerRepository managerRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("First invocation:");
        LOGGER.info("Manager: " + managerRepository.getManager());
        LOGGER.info("Second invocation:");
        LOGGER.info("Manager: " + managerRepository.getManager());

        LOGGER.info("First cached invocation:");
        LOGGER.info("CachedManager: " + managerRepository.getCachedManager());
        LOGGER.info("Second cached invocation:");
        LOGGER.info("CachedManager: " + managerRepository.getCachedManager());

        LOGGER.info("First random cached invocation:");
        LOGGER.info("RandomCachedManager: " + managerRepository.getRandomCachedManager());
        LOGGER.info("Second random cached invocation:");
        LOGGER.info("RandomCachedManager: " + managerRepository.getRandomCachedManager());
    }
}
