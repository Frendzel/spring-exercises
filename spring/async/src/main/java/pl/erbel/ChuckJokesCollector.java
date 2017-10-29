package pl.erbel;


import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.currentThread;

@Service
public class ChuckJokesCollector {

    private static Logger logger = Logger.getLogger(ChuckJokesCollector.class);

    RestTemplate restTemplate = new RestTemplate();

    public Joke getRandomJoke() {
        try {
            Joke joke = restTemplate.getForObject(
                    new URI("http://api.icndb.com/jokes/random"), Joke.class);
            System.out.println(joke);
            return joke;
        } catch (URISyntaxException e) {
            logger.error(e);
        }
        return null;
    }

    @Async("chuckNorrisThreadPool")
//    @Scheduled(cron = "* * * * * *")
    public CompletableFuture<Joke> invokeGetRandomJoke() throws InterruptedException {
        System.out.println("beginIn: " + currentThread().getName() + currentThread().getId());
        Joke randomJoke = getRandomJoke();
//        Thread.sleep(1000);
        System.out.println("endIn: " + currentThread().getName() + currentThread().getId());
        return CompletableFuture.completedFuture(randomJoke);
    }

}
