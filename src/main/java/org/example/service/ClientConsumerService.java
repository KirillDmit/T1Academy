package org.example.service;

import org.example.entity.Client;
import org.example.repository.ClientRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientConsumerService {

    private final ClientRepository clientRepository;
    private final KafkaTemplate<String, Client> kafkaTemplate;

    public ClientConsumerService(ClientRepository clientRepository, KafkaTemplate<String, Client> kafkaTemplate) {
        this.clientRepository = clientRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "client-topic", groupId = "my-group")
    public void listen(Client client) {
        client = clientRepository.save(client);
        kafkaTemplate.send("enriched-client-topic", client);
    }
}