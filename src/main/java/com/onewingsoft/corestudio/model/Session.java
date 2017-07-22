package com.onewingsoft.corestudio.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 24/06/17.
 */
@Entity
@Table(name = "session")
public class Session extends BaseEntity {

    @Column
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "session_client",
               joinColumns = { @JoinColumn(name = "session_id") },
               inverseJoinColumns = { @JoinColumn(name = "client_id") }
    )
    private Set<Client> clients;

    @Column
    @NotNull
    private boolean isGroup;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }
}
