package org.acme.statistics;

import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private double amount;
    private long timestampMillis;

    @Version
    private int version;

    public Transaction() { }

    public Transaction(double amount) {
        this.amount = amount;
        Instant instant = Instant.now();
        this.timestampMillis = instant.toEpochMilli();
    }

    public Transaction(double amount, long timestampMillis) {
        this.amount = amount;
        this.timestampMillis = timestampMillis;
    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestampMillis() {
        return timestampMillis;
    }

    public void setTimestampMillis(long timestampMillis) {
        this.timestampMillis = timestampMillis;
    }

}
