package org.example.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.example.entity.Client;
import org.example.dto.ClientDto;
import org.example.service.ClientService;
import org.example.util.ClientMapper;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaClientConsumer {

    private final ClientService clientService;

    @KafkaListener(id = "${t1.kafka.consumer.group-id}",
            topics = "${t1.kafka.topic.client_registration}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload List<ClientDto> messageList,
                         Acknowledgment ack,
                         @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        log.debug("Client consumer: Обработка новых сообщений");
        try {
            List<Client> clients = messageList.stream()
                    .map(dto -> {
                        dto.setFirstName(key + "@" + dto.getFirstName());
                        return ClientMapper.toEntity(dto);
                    })
                    .toList();
            clientService.registerClients(clients);
        } finally {
            /*
            Вызывается, когда запись или партия, для которой было создано подтверждение, обработана.
            Вызов этого метода подразумевает, что все предыдущие сообщения в разделе уже обработаны.
             */
            ack.acknowledge();
        }

        log.debug("Client consumer: записи обработаны");
    }
}