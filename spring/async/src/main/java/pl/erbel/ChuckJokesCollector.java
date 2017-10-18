package pl.erbel;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ChuckJokesCollector {

    private static Logger logger = Logger.getLogger(ChuckJokesCollector.class);

    RestTemplate restTemplate = new RestTemplate();

    public Joke getRandomJoke() {
        try {
            return restTemplate.getForObject(
                    new URI("http://api.icndb.com/jokes/random"), Joke.class);
        } catch (URISyntaxException e) {
            logger.error(e);
        }
        return null;
    }

}
