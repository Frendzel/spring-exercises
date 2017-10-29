package pl.erbel;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;

import javax.validation.Valid;

import static java.lang.Math.random;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public class ManagerRepository {

    public Manager getManager() {
        simulateBusyDb();
        return new Manager("Krzysztof", "Jarzyna", 20, "ze Szczecina");
    }

    @Cacheable("managers")
    public @Valid Manager getCachedManager() {
        simulateBusyDb();
        return new Manager("Janusz", "Malina", 30, "ze Jaworzyna");
    }

    @Cacheable("randomManagers")
    public Manager getRandomCachedManager() {
        simulateBusyDb();
        return generateRandomManager();
    }

    private Manager generateRandomManager() {
        return new Manager(
                randomAlphabetic(6),
                randomAlphabetic(8),
                (int) random() * 100,
                randomAlphabetic(10));
    }

    private void simulateBusyDb() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
