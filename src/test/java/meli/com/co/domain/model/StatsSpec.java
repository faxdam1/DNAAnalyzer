package meli.com.co.domain.model;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class StatsSpec {

    @Test
    void init_stats() {
        Stats stats=new Stats();
    }

    @Test
    void invalid_count_human() {
        Stats stats=new Stats((long)20,(long)0);
        Assertions.assertTrue(stats.getRatio()==-1);
    }




}