package com.test.api.cliente.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartialClientDTO {

        private String name;

        private String cpf;

        private String email;
}
