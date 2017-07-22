package com.onewingsoft.corestudio.rest;

import com.onewingsoft.corestudio.business.BaseBusinessLogic;
import com.onewingsoft.corestudio.business.HallBusinessLogic;
import com.onewingsoft.corestudio.model.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by natete on 24/06/17.
 */
@RestController
@RequestMapping("/api/admin/hall")
public class HallRestService extends BaseRestService<Hall> {

    @Autowired
    private HallBusinessLogic hallBusinessLogic;

    @Override
    protected BaseBusinessLogic<Hall> getBusinessLogic() {
        return this.hallBusinessLogic;
    }

    @Override
    protected String getUri() {
        return "/api/admin/hall";
    }

    @Override
    protected String getMessage(Object hall) {
        return " la sala " + hall.toString();
    }
}
