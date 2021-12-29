package edu.java.course.core.task_03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TransactionDAO implements DAO<Transaction> {
    private final static Logger LOG = LoggerFactory.getLogger(TransactionDAO.class);
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
            LOG.debug("Start transaction");
            connection = getConnection();
            connection.setAutoCommit(false);

            String queryCardFrom = "UPDATE cards set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(queryCardFrom);
            preparedStatement.setBigDecimal(1, transaction.getCardFrom().getBalance().subtract(transaction.getSum()));
            preparedStatement.setObject(2, transaction.getCardFrom().getUuid());
            preparedStatement.executeUpdate();


            String queryCardWhere = "UPDATE cards set balance = ? where id = ?";
            preparedStatement = connection.prepareStatement(queryCardWhere);
            preparedStatement.setBigDecimal(1, transaction.getCardWhere().getBalance().add(transaction.getSum()));
            preparedStatement.setObject(2, transaction.getCardWhere().getUuid());
            preparedStatement.executeUpdate();


            String queryTransaction = "INSERT INTO transactions(id,date,cardfrom,cardwhere,sum) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryTransaction);
            preparedStatement.setObject(1, transaction.getUuid());
            preparedStatement.setTimestamp(2, java.sql.Timestamp.from(transaction.getDate()));
            preparedStatement.setObject(3, transaction.getCardFrom().getUuid());
            preparedStatement.setObject(4, transaction.getCardWhere().getUuid());
            preparedStatement.setBigDecimal(5, transaction.getSum());
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOG.error("Failed. Close connection process. Transaction", e);
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
            } catch (Exception e) {
                LOG.error("Failed. Close connection process in transaction", e);
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
