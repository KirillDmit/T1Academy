package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ClientDto;
import org.example.kafka.KafkaClientProducer;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;
    private final KafkaClientProducer kafkaClientProducer;
    @Value("${t1.kafka.topic.client_registration}")
    private String topic;


    @GetMapping(value = "/parse")
    public void parseSource() {
        List<ClientDto> clientDtos = clientService.parseJson();
        clientDtos.forEach(dto -> kafkaClientProducer.sendTo(topic, dto));
    }
}
