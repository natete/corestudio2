package com.onewingsoft.corestudio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onewingsoft.corestudio.utils.Day;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ignacio González Bullón - <nacho.gonzalez.bullon@gmail.com>
 * @since 22/12/15.
 */
@Entity
@Table(name = "pass")
public class Pass extends BaseEntity {

    @Column
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @Column
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date initialDate;

    @Column
    @NotNull
    private Long price;

    @ManyToOne
    @JoinColumn(name = "passType_id")
    private PassType passType;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ElementCollection(fetch = FetchType.EAGER)
    @Temporal(TemporalType.DATE)
    private Set<Date> frozenDates = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Temporal(TemporalType.DATE)
    private Set<Date> consumedDates = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Temporal(TemporalType.DATE)
    private Set<Date> pendingDates = new HashSet<>();

    @Column
    @Temporal(TemporalType.DATE)
    private Date lastDate;

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    @JsonIgnore
    public Long getPrice() {
        return price;
    }

    @JsonIgnore
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * Returns persisted Long price converted to double adding decimals
     * @return price with decimals
     */
    @Transient
    public double getMoney() {
        return (double) price / 100;
    }

    /**
     * Sets the price removing the decimals to convert it to Long
     * @param price price with decimals
     */
    @Transient
    public void setMoney(double price) {
        this.price = Math.round(price * 100);
    }

    public PassType getPassType() {
        return passType;
    }

    public void setPassType(PassType passType) {
        this.passType = passType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<Date> getFrozenDates() {
        return frozenDates;
    }

    public void setFrozenDates(Set<Date> frozenDates) {
        this.frozenDates = frozenDates;
    }

    public Set<Date> getConsumedDates() {
        return consumedDates;
    }

    public void setConsumedDates(Set<Date> consumedDates) {
        this.consumedDates = consumedDates;
    }

    public Set<Date> getPendingDates() {
        return pendingDates;
    }

    public void setPendingDates(Set<Date> pendingDates) {
        this.pendingDates = pendingDates;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    @Transient
    @JsonIgnore
    public int getNumberOfSession() {
        return this.passType.getNumberOfSessions();
    }

    @Transient
    @JsonIgnore
    public boolean isGroupPass() {
        return this.passType.isGroupActivity();
    }

    @Transient
    @JsonIgnore
    public List<Day> getDays() {
        return this.group.getDays();
    }

    @Transient
    @JsonIgnore
    public int getPendingSessions() {
        return getNumberOfSession() - consumedDates.size();
    }

    public void addPendingDate(Date date) {
        pendingDates.add(date);
        pendingDates = pendingDates.stream().sorted().collect(Collectors.toSet());
    }

    public void addFrozenDate(Date date) {
        frozenDates.add(date);
        frozenDates = frozenDates.stream().sorted().collect(Collectors.toSet());
    }

    public void addConsumedDate(Date date) {
        consumedDates.add(date);
        consumedDates = consumedDates.stream().sorted().collect(Collectors.toSet());
    }

    public void freezeDate(Date date) {
        addFrozenDate(date);

        removeConsumedDate(date);
        removePendingDate(date);
    }

    public void removeLastDate() {
        pendingDates.remove(lastDate);
        lastDate = pendingDates.stream().sorted().collect(Collectors.toList()).get(pendingDates.size() - 1);
    }

    public void releaseDate(Date date) {
        removeConsumedDate(date);
        removeFrozenDate(date);
    }

    private void removeConsumedDate(Date date) {
        if(consumedDates.contains(date)) {
            consumedDates.remove(date);
            consumedDates = consumedDates.stream().sorted().collect(Collectors.toSet());
        }
    }

    private void removeFrozenDate(Date date) {
        if(frozenDates.contains(date)) {
            frozenDates.remove(date);
            frozenDates = frozenDates.stream().sorted().collect(Collectors.toSet());
        }
    }

    private void removePendingDate(Date date) {
        if(pendingDates.contains(date)) {
            pendingDates.remove(date);
            pendingDates = pendingDates.stream().sorted().collect(Collectors.toSet());
        }
    }

    @Transient
    @JsonIgnore
    public boolean isGroupDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        for (Day day : group.getDays()) {
            if(day.getValue() == cal.get(Calendar.DAY_OF_WEEK)) {
                return true;
            }
        }
        return false;
    }

    public void updateLastDate(Date date) {
        addPendingDate(date);
        lastDate = date;
    }

    @Transient
    @JsonIgnore
    public Long getPricePerSession() {
        return Math.round((double)price / passType.getNumberOfSessions());
    }

    @Override
    public String toString() {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG, new Locale("es", "ES"));
        return passType.toString() + " fecha de inicio: " + formatter.format(initialDate);
    }
}
