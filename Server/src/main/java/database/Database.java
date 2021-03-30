package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String connectionString = "jdbc:postgresql://rogue.db.elephantsql.com:5432/mwauuqab";
    private static final String userName = "mwauuqab";
    private static final String password = "ocvl9Z95H3Qp4i06S0686yqqthL83qlS";

    private Database(){}

    public static Connection newConnection() throws SQLException {
        try {
            return DriverManager.getConnection(connectionString, userName, password);
        }
        catch (SQLException exception){
            throw new SQLException("Couldn't connect to database.");
        }
    }
}
