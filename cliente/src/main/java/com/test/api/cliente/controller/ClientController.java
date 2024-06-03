package com.test.api.cliente.controller;


import com.test.api.cliente.dto.ClientDTO;
import com.test.api.cliente.dto.PartialClientDTO;
import com.test.api.cliente.entity.Client;
import com.test.api.cliente.mapper.PartialClientUpdateMapper;
import com.test.api.cliente.repository.ClientRepository;
import com.test.api.cliente.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/all" , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Get all clients",
            responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                    content = {@Content( mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Client.class))
                            )
                    }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public ResponseEntity<List<Client>> getAllClients(){
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Get all clients",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content( mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Client.class))
                            )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Page<ClientDTO>> getAllClientsPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "3") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction){

        return ResponseEntity.ok(clientService.findAllClientsPageable(page,size,direction));
    }

//    @GetMapping(value = "/{clientId}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<ClientDTO> getOneClient(@PathVariable Long clientId){
//        return ResponseEntity.ok(clientService.findOne(clientId));
//    }

    @GetMapping(value = "/{name}",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<ClientDTO>> getClientByName(@PathVariable String name,
          @RequestParam(value = "page", defaultValue = "0") Integer page,
          @RequestParam(value = "size", defaultValue = "3") Integer size,
          @RequestParam(value = "direction", defaultValue = "asc") String direction){
        return ResponseEntity.ok(clientService.findByName(page,
                size,
                direction,name));
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> saveClients(@Valid @RequestBody Client client){
        log.info(client.toString());
        ClientDTO clientSaved = clientService.saveClient(client);
        return ResponseEntity.ok(clientSaved);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Long clientId){
        log.info(clientId.toString());
        return clientService.deleteClient(clientId);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<?> updateClientPartial(@PathVariable Long clientId , @RequestBody PartialClientDTO partialClientDTO){

        log.info(partialClientDTO.toString());
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new ObjectNotFoundException(Long.class,""));

        PartialClientUpdateMapper mapper = new PartialClientUpdateMapper();
        mapper.map(partialClientDTO, client);
        return ResponseEntity.ok(client);
    }

}
