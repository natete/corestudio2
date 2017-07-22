package com.onewingsoft.corestudio.business;

import com.onewingsoft.corestudio.model.Hall;
import com.onewingsoft.corestudio.repository.HallRepository;
import com.onewingsoft.corestudio.utils.CorestudioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Created by natete on 24/06/17.
 */
@Service
public class HallBusinessLogic extends BaseBusinessLogic<Hall> {

    @Autowired
    private HallRepository hallRepository;

    @Override
    protected void validateEntity(Hall hall) throws CorestudioException {
        if (hall.getName() == null) {
            throw new CorestudioException("El nombre es necesario");
        }
    }

    @Override
    protected PagingAndSortingRepository<Hall, Long> getRepository() {
        return this.hallRepository;
    }
}
