package com.onewingsoft.corestudio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "professor")
public class Professor extends Person {

    @Column
    @Size(min = 0, max = 300)
    private String qualification;

    @Column
    @Size(min = 0, max = 300)
    private String training;

    @Column
    private Integer holidaysUsed;

    @Column
    private String color;

    public Professor() {
        super();
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public Integer getHolidaysUsed() {
        return holidaysUsed;
    }

    public void setHolidaysUsed(Integer holidaysUsed) {
        this.holidaysUsed = holidaysUsed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
