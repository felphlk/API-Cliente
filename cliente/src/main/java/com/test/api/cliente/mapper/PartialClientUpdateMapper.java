package com.test.api.cliente.mapper;

import com.test.api.cliente.dto.PartialClientDTO;
import com.test.api.cliente.entity.Client;
import org.modelmapper.ModelMapper;

public class PartialClientUpdateMapper {



    public void map(PartialClientDTO clientDTO, Client client){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(clientDTO,client);
    }
}
