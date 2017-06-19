package com.onewingsoft.corestudio.dto;

import com.onewingsoft.corestudio.model.Client;

import java.util.Date;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 02/01/16.
 */
public class ClientDTO extends Client {

    private Integer pendingSessions;
    private Date lastDate;

    public Integer getPendingSessions() {
        return pendingSessions;
    }

    public void setPendingSessions(Integer pendingSessions) {
        this.pendingSessions = pendingSessions;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
