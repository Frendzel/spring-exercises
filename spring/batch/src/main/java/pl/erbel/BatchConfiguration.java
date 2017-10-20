package pl.erbel;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

//TODO
//Reader Listener
//Writer Listener
//JobParametersValidator
//JobLauncher https://gist.github.com/benas/9355801
//https://docs.spring.io/spring-batch/trunk/reference/html/configureJob.html#advancedMetaData
//Read properties from file
//Add tests
//https://docs.spring.io/spring-batch/trunk/reference/html/testing.html
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Qualifier("dataSource")
    //name of class which implements interface as a qualifier for candidate beans when autowiring
    @Autowired
    DataSource dataSource;

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    JednorozecItemReadListener jednorozecItemReadListener;

    @Autowired
    JednorozecItemWriteListener JednorozecItemWriteListener;

    @Bean
    JdbcBatchItemWriter<Jednorozec> jednorozecWriter() {
        JdbcBatchItemWriter<Jednorozec> writer = new JdbcBatchItemWriter();
        writer.setDataSource(dataSource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Jednorozec>());
        writer.setSql("INSERT INTO jednorozec (id,imie,nazwisko,email,plec) " +
                "VALUES (:id," +
                ":imie," +
                ":nazwisko," +
                ":email," +
                ":plec)");
        writer.afterPropertiesSet();
        return writer;
    }

    @Bean
    FlatFileItemReader<Jednorozec> jednorozecReader() {
        FlatFileItemReader<Jednorozec> flatFileItemReader = new FlatFileItemReader<Jednorozec>();
        flatFileItemReader.setResource(new ClassPathResource("jednorozec.csv"));
        flatFileItemReader.setLinesToSkip(1);
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setNames(new String[]
                {"id",
                        "imie",
                        "nazwisko",
                        "email",
                        "plec"});

        BeanWrapperFieldSetMapper<Jednorozec> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Jednorozec.class);
        fieldSetMapper.setStrict(false);

        DefaultLineMapper<Jednorozec> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(delimitedLineTokenizer);
        mapper.setFieldSetMapper(fieldSetMapper);

        flatFileItemReader.setLineMapper(mapper);
        return flatFileItemReader;
    }

    @Bean
    ItemProcessor<Jednorozec, Jednorozec> processor() {
        return new JednorozecItemProcessor();
    }

    @Bean
    Job saveJednorozec(JednorozecJobListener listener) {
        return jobBuilderFactory.get("saveJednorozec").
                flow(firstMigrationStep()).end().
                listener(listener).
                build();
    }

    @Bean
    Step firstMigrationStep() {
        return stepBuilderFactory.
                get("firstMigrationStep").
                <Jednorozec, Jednorozec>
                        chunk(10).
                reader(jednorozecReader()).
                listener(jednorozecItemReadListener).
                listener(JednorozecItemWriteListener).
                processor(processor()).
                writer(jednorozecWriter()).
                build();
    }

}
