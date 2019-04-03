package io.pivotal.pa.phoenix.web;

import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.model.Time;
import io.pivotal.pa.phoenix.web.dao.AggregatedAiDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Random;

@Configuration
@Profile("local")
@Slf4j
public class LocalDataInitializer {

    @Bean
    public CommandLineRunner initAggregatedAICounts(final TimeDao timeDao, final AggregatedAiDao aggregatedAiDao) {
        final Random random = new Random();
        return (args) -> {
            Date startDate = DateUtils.addYears(new Date(), -3);
            Date today = new Date();
            while (startDate.compareTo(today) <= 0) {
                Time t = timeDao.save(new Time(startDate));
                AggregatedAI aggregatedAI = aggregatedAiDao.save(new AggregatedAI(null, random.nextInt(1800), t));
                log.info("Generated aggregated AI count of " + aggregatedAI.getAiCount() + " for date " + startDate);
                startDate = DateUtils.addDays(startDate, 1);
            }
        };
    }
}

@Profile("local")
interface TimeDao extends CrudRepository<Time, Long> {
}
