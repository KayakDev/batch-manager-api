package com.kayak.batchManager.ManagerControl.Client.Mapper;

import com.kayak.batchManager.ManagerControl.Client.Dto.ClientDTO;
import com.kayak.batchManager.ManagerControl.Client.Entity.ClientModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toDTO(ClientModel clientModel);

    ClientModel toEntity(ClientDTO clientDTO);
}
