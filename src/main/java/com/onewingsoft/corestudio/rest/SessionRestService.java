package com.onewingsoft.corestudio.rest;

import com.onewingsoft.corestudio.business.BaseBusinessLogic;
import com.onewingsoft.corestudio.business.SessionBusinessLogic;
import com.onewingsoft.corestudio.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by natete on 24/06/17.
 */
@RestController
@RequestMapping("/api/session")
public class SessionRestService extends BaseRestService<Session> {

    @Autowired
    private SessionBusinessLogic sessionBusinessLogic;

    @RequestMapping(value = "/getByActivity/{activityId}", method = RequestMethod.GET)
    public Iterable<Session> getByActivity(@PathVariable final Date startDate, @PathVariable final Date endDate) {
        return sessionBusinessLogic.getByDate(startDate, endDate);
    }

    @Override
    protected BaseBusinessLogic<Session> getBusinessLogic() {
        return this.sessionBusinessLogic;
    }

    @Override
    protected String getUri() {
        return "/api/session";
    }

    @Override
    protected String getMessage(Object session) {
        return " la sesión " + session.toString();
    }
}
