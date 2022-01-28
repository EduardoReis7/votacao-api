package br.com.er.votacaoapi.message;

import br.com.er.votacaoapi.model.dto.ResultadoVotacaoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class VoteResultsProducer {

    @Value(value = "${vote-results-topic}")
    private String topicName;

    private final KafkaTemplate<String, ResultadoVotacaoDto> kafkaTemplate;

    public void sendMessage(Long key, ResultadoVotacaoDto value) {
        log.info("Mensagem enviada. idSessao: {}. ResultadoVotacao: {}.", key, value);
        kafkaTemplate.send(topicName, String.valueOf(key), value);
    }

}
