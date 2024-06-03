package com.test.api.cliente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;


    @Size(min = 11,message = "Campo cpf precisa de 11 caracteres")
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;


}
