package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandPrinter implements CommandLineRunner{

    @Autowired
    ChuckJokesCollector chuckJokesCollector;

    @Override
    public void run(String... args) throws Exception {

    }
}
