package edu.java.course.core.task_03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.UUID;

public class MoneyTransferService {
    private final static Logger LOG = LoggerFactory.getLogger(MoneyTransferService.class);

    public static UUID performTransfer(Card cardFrom, Card card2Where) {

        TransactionDAO transactionDAO = new TransactionDAO();
        Transaction transaction = new Transaction(cardFrom, card2Where, BigDecimal.valueOf(500));
        transactionDAO.add(transaction);

        return transaction.getUuid();
    }

    public static void main(String[] args) {
        LOG.debug("Hello from main debug");
        LOG.info("Hello from main info");
        CardsDAO cardsDAO = new CardsDAO();

        Card card1 = new Card("88005553535",BigDecimal.ZERO);

        Card card2 = new Card("22222222222",BigDecimal.ZERO);

        cardsDAO.add(card1);
        cardsDAO.add(card2);

        LOG.debug("{}",performTransfer(card1,card2));
    }
}
