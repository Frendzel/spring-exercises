package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static java.lang.Thread.currentThread;

@Component
@EnableAsync
@EnableScheduling
public class CommandPrinter implements CommandLineRunner {

    @Autowired
    ChuckJokesCollector chuckJokesCollector;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("beginOut: " + currentThread().getName());
        CompletableFuture<Joke> joke =
                chuckJokesCollector.invokeGetRandomJoke();
        CompletableFuture<Joke> joke2 =
                chuckJokesCollector.invokeGetRandomJoke();
        CompletableFuture<Joke> joke3 =
                chuckJokesCollector.invokeGetRandomJoke();
        joke3.thenRun(new innerThread());
        Object result = CompletableFuture.anyOf(joke, joke2, joke3).join();
        System.out.println(result);
    }

    private static class innerThread implements Runnable {

        @Override
        public void run() {
            System.out.println("hello");
        }
    }

    @Bean("chuckNorrisThreadPool")
    public Executor chuckNorrisThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(2);
        threadPoolTaskExecutor.setCorePoolSize(2);
        threadPoolTaskExecutor.setThreadNamePrefix("sda:");
        threadPoolTaskExecutor.setThreadGroupName("norris");
        threadPoolTaskExecutor.setBeanName("chuckNorrisThreadPool");
        threadPoolTaskExecutor.setBeanName("chuckNorrisThreadPool");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
