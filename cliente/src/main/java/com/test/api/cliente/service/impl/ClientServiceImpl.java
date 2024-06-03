package com.test.api.cliente.service.impl;

import com.test.api.cliente.controller.ClientController;
import com.test.api.cliente.dto.ClientDTO;
import com.test.api.cliente.entity.Client;
import com.test.api.cliente.repository.ClientRepository;
import com.test.api.cliente.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;


    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientDTO saveClient(Client client) {

        ModelMapper modelMapper = new ModelMapper();
        ClientDTO clientDTO = new ClientDTO();
        modelMapper.map(clientRepository.save(client), clientDTO);
        return clientDTO;
    }

    public ResponseEntity deleteClient(Long clientId){

        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isEmpty()){
            throw new ObjectNotFoundException(Long.class,"Objeto com id nao encontrado"+clientId.toString());
        }
        else{
            clientRepository.delete(client.get());
            return ResponseEntity.ok().build();

        }

    }

    @Override
    public ClientDTO findOne(Long clientId) {
        ModelMapper modelMapper = new ModelMapper();
        Client client =
                clientRepository.findById(clientId)
                        .orElseThrow
                        (()-> new ObjectNotFoundException(Long.class, "Objeto não com id"+ clientId + "não encontrado"));
        ClientDTO dto = new ClientDTO();

        modelMapper.map(client,dto);

        return dto;

    }

    @Override
    public Page<ClientDTO> findAllClientsPageable(Integer page,
                                                  Integer size,
                                                  String direction) {
        ModelMapper modelMapper = new ModelMapper();


        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.valueOf(direction.toUpperCase(Locale.ROOT)) ,"name"));
        var pageableRequest = clientRepository.findAll(pageable);
        Page<ClientDTO> clientDTOPage = pageableRequest.map(p -> modelMapper.map(p,ClientDTO.class));
        clientDTOPage.map(p -> p.add(linkTo(methodOn(ClientController.class).getAllClientsPage(page,size,direction)).withSelfRel()));

        return clientDTOPage;
    }

    @Override
    public Page<ClientDTO> findByName(Integer page,
                                Integer size,
                                String direction,
                                String clientName) {

        ModelMapper modelMapper = new ModelMapper();
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.valueOf(direction.toUpperCase(Locale.ROOT)) ,"name"));
        log.info(clientName);
        var pageableRequest = clientRepository.findClientsByName(clientName,pageable);
        Page<ClientDTO> clientDTOPage = pageableRequest.map(p -> modelMapper.map(p,ClientDTO.class));
        return  clientDTOPage;
    }

    ;
}
