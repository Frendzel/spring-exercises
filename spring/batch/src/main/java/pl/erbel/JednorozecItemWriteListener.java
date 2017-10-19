package pl.erbel;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JednorozecItemWriteListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = Logger.getLogger(JednorozecItemWriteListener.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOGGER.info("listener! ");
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            List<Jednorozec> zwierzaki = jdbcTemplate.query("SELECT * FROM jednorozec",
                    (rs, number) ->
                            new Jednorozec(rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4),
                                    rs.getString(5)));
            zwierzaki.forEach(System.out::println);
            LOGGER.info(zwierzaki);
        }
    }

}
