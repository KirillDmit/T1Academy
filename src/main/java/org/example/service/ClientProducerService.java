package org.example.service;

import org.example.entity.Client;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientProducerService {

    private final KafkaTemplate<String, Client> kafkaTemplate;

    public ClientProducerService(KafkaTemplate<String, Client> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClients(List<Client> clients) {
        clients.forEach(client -> kafkaTemplate.send("client-topic", client));
    }
}