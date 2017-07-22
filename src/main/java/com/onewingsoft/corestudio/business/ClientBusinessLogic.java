package com.onewingsoft.corestudio.business;

import com.onewingsoft.corestudio.dto.ClientDTO;
import com.onewingsoft.corestudio.model.BaseEntity;
import com.onewingsoft.corestudio.model.Client;
import com.onewingsoft.corestudio.model.Pass;
import com.onewingsoft.corestudio.model.RegisteredUser;
import com.onewingsoft.corestudio.repository.ClientRepository;
import com.onewingsoft.corestudio.utils.CorestudioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Business logic to manage clients.
 */
@Service
public class ClientBusinessLogic extends BaseBusinessLogic<Client> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PersonBusinessLogic personBusinessLogic;

    @Autowired
    private PassBusinessLogic passBusinessLogic;

    /**
     * @return list of {@link ClientDTO} that includes pass information.
     * @see BaseBusinessLogic#getAllEntities(Integer, Integer, String, String) .
     */
    public Page<ClientDTO> getAllDtos(Integer page, Integer size, String sortBy, String direction, String q) {
        Page<Client> clients;
        if (q == null) {
            clients = super.getAllEntities(page, size, sortBy, direction);
        } else {
            Pageable pageable = this.getPageable(page, size, sortBy, direction);
            clients = this.clientRepository.findByFullNameLike(q, pageable);
        }

        List<ClientDTO> result = clients.getContent().stream()
                                        .filter(Client::getIsActive)
                                        .map(this::mapToDto)
                                        .collect(Collectors.toList());

        Sort sort = null;
        if (sortBy != null) {
            sort = new Sort(Sort.Direction.fromString(direction), sortBy);
        }
        Pageable pageRequest = new PageRequest(page, size, sort);

        Page<ClientDTO> pageDTO = new PageImpl<>(result, pageRequest, clients.getTotalElements());

        return pageDTO;
    }

    @Override
    public Client createEntity(Client client) throws CorestudioException {
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setUsername((client.getName().toLowerCase() + "." + client.getFirstSurname()).replace(" ", ""));
        registeredUser.setPassword(UUID.randomUUID().toString());
        registeredUser.setRole(Arrays.asList(RegisteredUser.CorestudioRole.CLIENT));
        client.setRegisteredUser(registeredUser);
        personBusinessLogic.encodePassword(client.getRegisteredUser());
        return super.createEntity(client);
    }

    /**
     * @see BaseBusinessLogic#validateEntity(BaseEntity).
     */
    @Override
    protected void validateEntity(Client client) throws CorestudioException {

        personBusinessLogic.validateEntity(client.getRegisteredUser());

        if (client.getName() == null) {
            throw new CorestudioException("Un cliente debe tener un nombre");
        }
        if (client.getFirstSurname() == null) {
            throw new CorestudioException("Un cliente debe tener un primer apellido");
        }
        if (client.getFirstPhone() == null) {
            throw new CorestudioException("Un cliente debe tener al menos un teléfono principal");
        }
    }

    /**
     * @see BaseBusinessLogic#getRepository().
     */
    @Override
    protected PagingAndSortingRepository<Client, Long> getRepository() {
        return clientRepository;
    }

    private ClientDTO mapToDto(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setFirstSurname(client.getFirstSurname());
        dto.setSecondSurname(client.getSecondSurname());
        dto.setPhoto(client.getPhoto());
        Pass currentPass = passBusinessLogic.getCurrentPass(client.getId());
        if (currentPass != null) {
            dto.setLastDate(currentPass.getLastDate());
            dto.setPendingSessions(currentPass.getPendingSessions());
        }

        return dto;
    }
}
