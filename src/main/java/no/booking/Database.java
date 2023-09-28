package no.booking;

import java.io.File;
import java.sql.*;

/** Class for talking to the database.
 */
public class Database {
    private static final String file_path = System.getProperty("user.dir") + "/data/database.sqlite";
    private static Connection conn;

    public static void printAllUsers() {
        try {
            connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // Loop through the results and print it out
            System.out.println("==== users ====");
            System.out.println("<user_id | username | first_name | last_name | user_type>");
            while (rs.next()) {
                System.out.println(rs.getInt("user_id") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("first_name") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getInt("user_type"));
            }
            System.out.println("===============");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        boolean fileExists = (new File(file_path)).exists();

        conn = DriverManager.getConnection("jdbc:sqlite:" + file_path);
        if (!fileExists) createDatabase();
    }

    private static void disconnect() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void createDatabase() {
        try {
            Statement stmt = conn.createStatement();

            // Create user_types table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS user_types (
                    id integer PRIMARY KEY,
                    name varchar(50)
                );
                """);

            // Create users table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    user_id integer PRIMARY KEY,
                    username varchar(50) NOT NULL,
                    first_name varchar(50) NOT NULL,
                    last_name varchar(50) NOT NULL,
                    email varchar(50) NOT NULL,
                    user_type integer,
                    FOREIGN KEY (user_type) REFERENCES user_types(id)
                );""");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
