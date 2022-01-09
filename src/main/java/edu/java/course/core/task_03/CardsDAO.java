package edu.java.course.core.task_03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class CardsDAO implements DAO<Card> {
    private final static Logger LOG = LoggerFactory.getLogger(CardsDAO.class);
    private final DataSource dataSource;

    public CardsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void add(Card card) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String queryString = "INSERT INTO cards(id,cardnumber,balance) VALUES (?,?,?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setObject(1, card.getId());
            preparedStatement.setString(2, card.getCardNumber());
            preparedStatement.setBigDecimal(3, card.getBalance());
            preparedStatement.executeUpdate();
            LOG.debug("Card added into DB");
        } catch (SQLException e) {
            LOG.error("SQL failed. Card adding", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                LOG.error("Failed. Close connection process", e);
            }
        }
    }

    @Override
    public Optional<Card> get(UUID id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String queryString = "SELECT * FROM cards WHERE id=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Card card = new Card(UUID.fromString(resultSet.getString("id")), resultSet.getString("cardnumber"), resultSet.getBigDecimal("balance"));
                LOG.debug("UUID {}, Card number {}, Balance {}", card.getId(), card.getCardNumber(), card.getBalance());
                return Optional.of(card);
            }
            return Optional.empty();
        } catch (SQLException e) {
            LOG.error("SQL failed. Card getting", e);
            return Optional.empty();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                LOG.error("Failed. Close connection process in get", e);
            }
        }
    }

    @Override
    public void getAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String queryString = "SELECT * FROM cards";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LOG.debug("UUID {}, Card number {}, Balance {}",
                        resultSet.getObject("id"),
                        resultSet.getString("cardnumber"),
                        resultSet.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            LOG.error("SQL failed. All cards getting", e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
                LOG.error("Failed. Close connection process in getAll", e);
            }
        }
    }
}



