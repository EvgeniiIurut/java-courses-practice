package edu.java.course.core.task_03;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Transaction {
    private UUID uuid;
    private Instant date;
    private Card cardFrom;
    private Card cardWhere;
    private BigDecimal sum;

    public Transaction(Card cardFrom, Card cardWhere, BigDecimal sum) {
        this.uuid = UUID.randomUUID();
        this.date = Instant.now();
        this.cardFrom = cardFrom;
        this.cardWhere = cardWhere;
        this.sum = sum;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Card getCardWhere() {
        return cardWhere;
    }

    public void setCardWhere(Card cardWhere) {
        this.cardWhere = cardWhere;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "uuid=" + uuid +
                ", date=" + date +
                ", cardFrom='" + cardFrom + '\'' +
                ", cardWhere='" + cardWhere + '\'' +
                ", sum=" + sum +
                '}';
    }
}

