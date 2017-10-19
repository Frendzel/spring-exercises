package pl.erbel;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

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
        return writer;
    }

    @Bean
    FlatFileItemReader<Jednorozec> jednorozecReader() {
        FlatFileItemReader<Jednorozec> flatFileItemReader = new FlatFileItemReader();
        flatFileItemReader.setResource(new ClassPathResource("jednorozec.csv"));
        flatFileItemReader.setLinesToSkip(1);
        new DelimitedLineTokenizer() {{

        }};
        flatFileItemReader.setLineMapper(new DefaultLineMapper<Jednorozec>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"id",
                        "imie",
                        "nazwisko",
                        "email",
                        "plec"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Jednorozec>() {{
                setTargetType(Jednorozec.class);
            }});

        }});
        return flatFileItemReader;
    }

    @Bean
    ItemProcessor<Jednorozec, Jednorozec> processor() {
        return new JednorozecItemProcessor();
    }

    @Bean
    Job saveJednorozec() {
        return jobBuilderFactory.get("saveJednorozec").
                listener(new JednorozecItemWriteListener()).
                flow(firstMigrationStep()).
                end().
                build();
    }

    @Bean
    Step firstMigrationStep() {
        return stepBuilderFactory.
                get("firstMigrationStep").
                <Jednorozec, Jednorozec>
                chunk(100).
                reader(jednorozecReader()).
                processor(processor()).
                writer(jednorozecWriter()).
                build();
    }

}
