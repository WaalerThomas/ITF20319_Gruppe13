package no.booking.persistence;

import no.booking.Users.User;
import no.booking.logic.Booking;
import no.booking.logic.Tour;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;
import java.util.UUID;

/** Class for talking to the database.
 */
public class Database implements DataHandler {
    private final String folder_path = System.getProperty("user.dir") + "/data";
    private final String file_name = "database.sqlite";
    private Connection conn;

    @Override
    public User getUserByUsername(String username) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<Tour> getTours() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Tour getTourById(UUID id) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<Tour> getToursByCity(String city) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Tour createTour(String title, String country, String city, String description, String date, int adultTicketPrice, int childTicketPrice, int infantTicketPrice, String meetingPoint, int maxTicketAmount) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void createTour(Tour tour) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void updateTour(Tour tour) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void addBooking(Booking booking) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<Booking> getBookingsByTourId(UUID tourId) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public List<Booking> getBookingsByUsername(String username) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public void createUser(String username) {
        throw new RuntimeException("Not implemented");
    }

    // NOTE: This is a temp function, going to be removed after testing the setup
    public void printAllUsers() {
        try {
            connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                SELECT id, username, first_name, last_name,
                    (SELECT name
                    FROM user_types
                    WHERE user_types.id = users.user_type) AS user_type
                FROM users
            """);

            // Loop through the results and print it out
            System.out.println("==== users ====");
            System.out.println("<id | username | first_name | last_name | user_type>");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("username") + "\t" +
                        rs.getString("first_name") + "\t" +
                        rs.getString("last_name") + "\t" +
                        rs.getString("user_type"));
            }
            System.out.println("===============");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void connect() throws SQLException {
        // Check if the data folder exists
        boolean folderDoNotExist = !(new File(folder_path)).exists();
        if (folderDoNotExist)
            createDataFolder();

        // Check if the file exists
        boolean fileDoNotExist = !(new File(folder_path + "/" + file_name)).exists();

        conn = DriverManager.getConnection("jdbc:sqlite:" + folder_path + "/" + file_name);
        if (fileDoNotExist) createDatabase();
    }

    private void disconnect() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // TODO: Handle this exception better
    private void createDataFolder() {
        try {
            Files.createDirectory(Path.of(folder_path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDatabase() {
        try {
            Statement stmt = conn.createStatement();

            // Create user_types table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS user_types (
                    id integer PRIMARY KEY AUTOINCREMENT,
                    name varchar(50)
                );
                """);

            // Create users table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id integer PRIMARY KEY AUTOINCREMENT,
                    username varchar(50) NOT NULL,
                    first_name varchar(50) NOT NULL,
                    last_name varchar(50) NOT NULL,
                    email varchar(50) NOT NULL,
                    user_type integer,
                    FOREIGN KEY (user_type) REFERENCES user_types(id)
                );""");

            // Create tours table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tours (
                    id integer PRIMARY KEY AUTOINCREMENT,
                    user_id integer NOT NULL,
                    title varchar(100) NOT NULL,
                    meet_point varchar(100) NOT NULL,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );""");

            // Create tour_times table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tour_times (
                    id integer PRIMARY KEY AUTOINCREMENT,
                    tour_id integer NOT NULL,
                    date_time datetime NOT NULL,
                    FOREIGN KEY (tour_id) REFERENCES tours(id)
                );""");

            // Create bookings table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS bookings (
                    tourTime_id integer,
                    user_id integer,
                    adult_count integer DEFAULT 0,
                    child_count integer DEFAULT 0,
                    infant_count integer DEFAULT 0,
                    cost integer NOT NULL,
                    PRIMARY KEY (tourTime_id, user_id),
                    FOREIGN KEY (tourTime_id) REFERENCES tour_times(id),
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );""");

            // Insert the different user_types
            stmt.execute("""
                INSERT INTO user_types (name) VALUES
                    ('bruker'),
                    ('admin');
                """);

            // Insert the default users
            stmt.execute("""
                INSERT INTO users (username, first_name, last_name, email, user_type)
                VALUES
                    ('brukerberit', 'berit', 'bruker', 'berit@bruker.no',
                        (SELECT id FROM user_types WHERE name=='bruker')),
                    ('guidegard', 'gard', 'guide', 'gard@guide.no',
                        (SELECT id FROM user_types WHERE name=='bruker')),
                    ('adminadam', 'adam', 'admin', 'adam@admin.no',
                        (SELECT id FROM user_types WHERE name=='admin'));
                """);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
