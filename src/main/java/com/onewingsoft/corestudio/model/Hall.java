package com.onewingsoft.corestudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 24/06/17.
 */
@Entity
@Table(name = "hall")
public class Hall extends BaseEntity {

    @Column
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
