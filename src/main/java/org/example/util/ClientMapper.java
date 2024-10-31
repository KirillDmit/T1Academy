package org.example.util;

import org.example.dto.ClientDto;
import org.example.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public static Client toEntity(ClientDto dto) {
        return Client.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .middleName(dto.getMiddleName())
                .build();
    }

    public static ClientDto toDto(Client entity) {
        return ClientDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .middleName(entity.getMiddleName())
                .build();
    }
}