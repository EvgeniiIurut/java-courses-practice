package edu.java.course.core.task_03;

import java.math.BigDecimal;
import java.util.UUID;

public class Card {
    private UUID id;
    private String cardNumber;
    private BigDecimal balance;

    public Card(String cardNumber, BigDecimal balance) {
        this.id = UUID.randomUUID();
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public UUID getUuid() {
        return id;
    }

    public void setUuid(UUID id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "uuid=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}

