package com.onewingsoft.corestudio.business;

import com.onewingsoft.corestudio.model.Session;
import com.onewingsoft.corestudio.repository.SessionRepository;
import com.onewingsoft.corestudio.utils.CorestudioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by natete on 24/06/17.
 */
@Service
public class SessionBusinessLogic extends BaseBusinessLogic<Session> {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ProfessorBusinessLogic professorBusinessLogic;

    @Autowired
    private ClientBusinessLogic clientBusinessLogic;

    public Iterable<Session> getByDate(Date startDate, Date endDate) {
        return sessionRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Session createEntity(Session session) throws CorestudioException {
        session.setProfessor(professorBusinessLogic.getEntity(session.getProfessor().getId()));

        if (session.getClients() != null) {
            session.setClients(session.getClients().stream()
                                      .map(client -> clientBusinessLogic.getEntity(client.getId()))
                                      .collect(Collectors.toSet()));
        }

        return super.createEntity(session);
    }

    @Override
    protected void validateEntity(Session entity) throws CorestudioException {
        // TODO
    }

    @Override
    protected PagingAndSortingRepository<Session, Long> getRepository() {
        return this.sessionRepository;
    }
}
