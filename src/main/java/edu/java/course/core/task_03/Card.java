package edu.java.course.core.task_03;

import java.math.BigDecimal;
import java.util.UUID;

public class Card {
    private UUID uuid;
    private String cardNumber;
    private BigDecimal balance;

    public Card(String cardNumber, BigDecimal balance) {
        this.uuid = UUID.randomUUID();
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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
                "uuid=" + uuid +
                ", cardNumber='" + cardNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}

