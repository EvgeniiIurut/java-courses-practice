package edu.java.course.core.task_03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CardsDAO implements DAO<Card> {
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
            System.out.println("Card added into DB");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
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
            System.out.println("UUID " + resultSet.getString("id")
                    + ", Card number " + resultSet.getString("cardnumber") + ", Balance "
                    + resultSet.getBigDecimal("balance"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
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
                System.out.println("UUID " + resultSet.getObject("id")
                        + ", Card number " + resultSet.getString("cardnumber") + ", Balance "
                        + resultSet.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



