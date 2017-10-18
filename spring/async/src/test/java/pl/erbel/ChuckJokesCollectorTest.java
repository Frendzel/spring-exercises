package pl.erbel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ChuckJokesCollectorTest {

    @Autowired
    ChuckJokesCollector chuckJokesCollector;

    @Test
    public void getRandomJokeTest() {
        Joke randomJoke = chuckJokesCollector.getRandomJoke();
        System.out.println(randomJoke);
    }

}