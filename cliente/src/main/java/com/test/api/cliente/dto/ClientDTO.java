package com.test.api.cliente.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO extends RepresentationModel<ClientDTO> {

    private Long id;

    private String name;

    private String cpf;

    private String email;
}
