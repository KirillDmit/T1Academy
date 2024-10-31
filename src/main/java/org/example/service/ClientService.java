package org.example.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ClientDto;
import org.example.entity.Client;
import org.example.kafka.KafkaClientProducer;
import org.example.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final KafkaClientProducer kafkaClientProducer;
    private final ClientRepository repository;

    public void registerClients(List<Client> clients) {
        log.info("Registering clients... {}", clients);
        repository.saveAll(clients)
                .stream()
                .map(Client::getId)
                .forEach(kafkaClientProducer::send);
    }


    public List<ClientDto> parseJson() {
        ObjectMapper mapper = new ObjectMapper();

        ClientDto[] clients;
        try {
            clients = mapper.readValue(new File("src/main/resources/MOCK_DATA.json"), ClientDto[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Arrays.asList(clients);
    }
}
