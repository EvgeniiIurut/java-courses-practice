package edu.java.course.core.task_03;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CardsDAOTest {
    private CardsDAO cardsDAO = new CardsDAO(TestDatabase.getDataSource());

    @Test
    void shouldSaveCardDao() {
        // given
        Card card = new Card(UUID.randomUUID(), "testNumber", BigDecimal.TEN);

        // when
        cardsDAO.add(card);

        // then
        Optional<Card> actualCard = cardsDAO.get(card.getId());
        assertThat(actualCard).isPresent().get().usingRecursiveComparison().isEqualTo(card);
    }

    @Test
    void shouldReturnNullWhenCardIsNotExist() {
        // given
        final UUID notExistCardId = UUID.randomUUID();

        // when
        final Optional<Card> actualCard = cardsDAO.get(notExistCardId);

        // then
        assertThat(actualCard).isEmpty();
    }
}