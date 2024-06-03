package com.test.api.cliente.service;

import com.test.api.cliente.dto.ClientDTO;
import com.test.api.cliente.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<Client> findAllClients();

    ClientDTO saveClient(Client client);
    ResponseEntity deleteClient(Long client);

    ClientDTO findOne(Long clientId);

    Page<ClientDTO> findAllClientsPageable(Integer page,
                                           Integer size,
                                           String direction);

    Page<ClientDTO> findByName(Integer page,Integer size, String direction, String clientName);
}
