package edu.java.course.core.task_03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CardsDAO implements DAO<Card> {
    private final static Logger LOG = LoggerFactory.getLogger(CardsDAO.class);
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public CardsDAO() {
    }

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void add(Card card) {
        try {
            String queryString = "INSERT INTO cards(id,cardnumber,balance) VALUES (?,?,?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setObject(1, card.getUuid());
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
    public void get(UUID id) {
        try {
            String queryString = "SELECT * FROM cards WHERE uuid=?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            LOG.debug("UUID {}, Card number {}, Balance {}",
                    resultSet.getString("id"),
                    resultSet.getString("cardnumber"),
                    resultSet.getBigDecimal("balance"));
            LOG.debug("Card got from DB");
        } catch (SQLException e) {
            LOG.error("SQL failed. Card getting", e);
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
        try {
            String queryString = "SELECT * FROM cards";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(queryString);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                LOG.debug("UUID {}, Card number {}, Balance {}",
                        resultSet.getString("id"),
                        resultSet.getString("cardnumber"),
                        resultSet.getBigDecimal("balance"));
            }
            LOG.debug("Card got all cards from DB");
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



