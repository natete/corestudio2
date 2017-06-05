package com.onewingsoft.corestudio.business;

import com.onewingsoft.corestudio.model.Person;
import com.onewingsoft.corestudio.model.RegisteredUser;
import com.onewingsoft.corestudio.repository.ClientRepository;
import com.onewingsoft.corestudio.repository.ProfessorRepository;
import com.onewingsoft.corestudio.utils.CorestudioException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonBusinessLogin {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public void encodePassword(RegisteredUser registeredUser) {
        registeredUser.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
     }

    public void validateEntity(RegisteredUser registeredUser) throws CorestudioException {
        if (StringUtils.isBlank(registeredUser.getUsername())) {
            throw new CorestudioException("Un profesor debe tener un username");
        }

        if (StringUtils.isBlank(registeredUser.getPassword())) {
            throw new CorestudioException("Un profesor debe tener un password");
        }

        if (registeredUser.getAuthorities() == null || registeredUser.getAuthorities().isEmpty()) {
            throw new CorestudioException("Un profesor debe tener al menos un rol");
        }
    }

    public Person findPerson(String username, RegisteredUser.CorestudioRole role) {
        if (role.equals(RegisteredUser.CorestudioRole.CLIENT)) {
            return clientRepository.findByUsername(username);
        } else {
            return professorRepository.findByUsername(username);
        }
    }
}
