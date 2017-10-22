package pl.erbel;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JednorozecScheduledComponent {

    @Autowired
    JobLauncher jobLauncher; //klasa pozwalająca na wywołanie danego joba manualnie

    @Autowired
    Job job;

    @Scheduled(cron = "* * * * * *") // konfuguracja Crona
    public JobExecution run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis()).toJobParameters();
        // jobParameters zawierają dodatkowe informacje o naszym jobie
        return jobLauncher.run(job, jobParameters);
    }
}
