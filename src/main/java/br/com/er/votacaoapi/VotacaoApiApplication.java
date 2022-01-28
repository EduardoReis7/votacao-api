package br.com.er.votacaoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableKafka
@EnableScheduling
@SpringBootApplication
public class VotacaoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotacaoApiApplication.class, args);
    }

}
