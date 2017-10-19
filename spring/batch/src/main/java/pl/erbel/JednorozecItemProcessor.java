package pl.erbel;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

public class JednorozecItemProcessor implements ItemProcessor<Jednorozec, Jednorozec> {

    private static final Logger LOGGER =
            Logger.getLogger(JednorozecItemProcessor.class);

    @Override
    public Jednorozec process(Jednorozec item) throws Exception {
        LOGGER.debug("Processing object: " + item);
        return new Jednorozec(item.id, "Janusz", item.nazwisko, item.email, item.plec);
    }
}