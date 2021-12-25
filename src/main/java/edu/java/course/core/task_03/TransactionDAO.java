package edu.java.course.core.task_03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TransactionDAO implements DAO<Transaction> {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;


    public TransactionDAO() {
    }

    private Connection getConnection() throws SQLException {
        return ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void add(Transaction transaction) {
        try {
            System.out.println("Start transaction");
            connection = getConnection();
            connection.setAutoCommit(false);
            System.out.println("connected");

            String queryCardFrom = "UPDATE cards set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(queryCardFrom);
            preparedStatement.setBigDecimal(1, transaction.getCardFrom().getBalance().subtract(transaction.getSum()));
            preparedStatement.setObject(2, transaction.getCardFrom().getUuid());
            preparedStatement.executeUpdate();

            System.out.println("Card 1");

            String queryCardWhere = "UPDATE cards set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(queryCardWhere);
            preparedStatement.setBigDecimal(1, transaction.getCardWhere().getBalance().add(transaction.getSum()));
            preparedStatement.setObject(2, transaction.getCardWhere().getUuid());
            preparedStatement.executeUpdate();

            System.out.println("Card 2");

            String queryTransaction = "INSERT INTO transactions(id,date,cardfrom,cardwhere,sum) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryTransaction);
            preparedStatement.setObject(1, transaction.getUuid());
            preparedStatement.setTimestamp(2, java.sql.Timestamp.from(transaction.getDate()));
            preparedStatement.setObject(3, transaction.getCardFrom().getUuid());
            preparedStatement.setObject(4, transaction.getCardWhere().getUuid());
            preparedStatement.setBigDecimal(5, transaction.getSum());
            preparedStatement.executeUpdate();

            System.out.println("END");

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
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

    }

    @Override
    public void getAll() {

    }
}
