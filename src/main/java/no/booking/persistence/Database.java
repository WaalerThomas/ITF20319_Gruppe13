package no.booking.persistence;

import no.booking.users.User;
import no.booking.logic.Booking;
import no.booking.logic.Tour;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Class for talking to the database.
 */
public class Database implements DataHandler {
    private final String folder_path = System.getProperty("user.dir") + "/data";
    private final String file_name = "database.sqlite";
    private Connection conn;

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    @Override
    public User getUserByUsername(String username) {
        try {
            connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                SELECT *
                FROM users
                WHERE LOWER(username) = LOWER(?)
            """);

            return new User(
                    rs.getString("username"),
                    "123456",
                    rs.getString("email")
            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Tour> getTours() {
        try {
            connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("""
                SELECT
                    (SELECT username
                    FROM users
                    WHERE users.id = id) as owner_username, *
                FROM tours
            """);

            List<Tour> tours = getToursListFromResult(rs);
            return tours;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            // NOTE (Thomas): Is it good enough to just return an empty list if there is an SQLException?
            return new ArrayList<>();
        } finally {
            disconnect();
        }
    }

    @Override
    public Tour getTourById(UUID id) {
        try {
            connect();

            PreparedStatement stmt = conn.prepareStatement("""
                SELECT
                    (SELECT username
                    FROM users
                    WHERE users.id = id) as owner_username, *
                FROM tours
                WHERE tours.id = ?;
            """);
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();

            List<Tour> tours = getToursListFromResult(rs);
            return tours.get(0);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Tour> getToursByCity(String city) {
        // Return every tour if the filter is blank
        if (city.isBlank()) return getTours();

        try {
            connect();

            PreparedStatement stmt = conn.prepareStatement("""
                SELECT
                    (SELECT username
                    FROM users
                    WHERE users.id = id) as owner_username, *
                FROM tours
                WHERE LOWER(tours.city) = LOWER(?);
            """);
            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();

            return getToursListFromResult(rs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        } finally {
            disconnect();
        }
    }

    @Override
    public List<Tour> getToursByOwner(String ownerUsername) {
        try {
            connect();

            PreparedStatement stmt = conn.prepareStatement("""
                SELECT
                    (SELECT username
                    FROM users
                    WHERE users.id = id) as owner_username, *
                FROM tours
                WHERE LOWER(tours.owner_username) = LOWER(?);
            """);
            stmt.setString(1, ownerUsername);
            ResultSet rs = stmt.executeQuery();

            return getToursListFromResult(rs);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return new ArrayList<>();
        } finally {
            disconnect();
        }
    }

    @Override
    public Tour createTour(String guideUsername, String title, String country, String city, String description, String date, int adultTicketPrice, int childTicketPrice, int infantTicketPrice, String meetingPoint, int maxTicketAmount) {
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

    private List<Tour> getToursListFromResult(ResultSet rs) throws SQLException {
        List<Tour> resultList = new ArrayList<>();
        while (rs.next()) {
            Tour tempTour = new Tour(
                    rs.getString("owner_username"),
                    rs.getString("title"),
                    rs.getString("country"),
                    rs.getString("city"),
                    rs.getString("description"),
                    "2023-10-10 17:00:00",
                    rs.getInt("adult_ticket_price"),
                    rs.getInt("child_ticket_price"),
                    rs.getInt("infant_ticket_price"),
                    rs.getString("meet_point"),
                    rs.getInt("max_ticket_amount")
            );

            // Need to also set how many tickets are available
            tempTour.decreaseTicketCount(tempTour.getMaxTicketAmount() - rs.getInt("available_tickets_count"));
            tempTour.setId(UUID.fromString(rs.getString("id")));
            resultList.add(tempTour);
        }

        return resultList;
    }

    private void connect() throws SQLException {
        // Check if the data folder exists
        boolean folderDoNotExist = !(new File(folder_path)).exists();
        if (folderDoNotExist)
            createDataFolder();

        conn = DriverManager.getConnection("jdbc:sqlite:" + folder_path + "/" + file_name);
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

    public void createDefaultApplicationData() {
        try {
            connect();
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
                    id varchar(36) PRIMARY KEY NOT NULL,
                    user_id integer NOT NULL,
                    title varchar(100) NOT NULL,
                    city varchar(100) NOT NULL,
                    country varchar(100) NOT NULL,
                    description varchar(255),
                    adult_ticket_price integer default 0,
                    child_ticket_price integer default 0,
                    infant_ticket_price integer default 0,
                    meet_point varchar(100) NOT NULL,
                    max_ticket_amount integer,
                    available_tickets_count integer,
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );""");

            // Create tour_times table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS tour_times (
                    id integer PRIMARY KEY AUTOINCREMENT,
                    tour_id varchar(36) NOT NULL,
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
                    book_date datetime DEFAULT CURRENT_TIMESTAMP,
                    PRIMARY KEY (tourTime_id, user_id),
                    FOREIGN KEY (tourTime_id) REFERENCES tour_times(id),
                    FOREIGN KEY (user_id) REFERENCES users(id)
                );""");

            // If there are user_types, then there is already default data here, skip adding
            ResultSet rs = stmt.executeQuery("""
                SELECT *
                FROM user_types
                """);
            if (rs.next()) return;

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

            // Inser the default tours
            PreparedStatement prepStmt = conn.prepareStatement("""
                INSERT INTO tours (id, user_id, title, city, country, description, adult_ticket_price, child_ticket_price, infant_ticket_price, meet_point, max_ticket_amount, available_tickets_count)
                VALUES
                    (?, (SELECT id FROM users WHERE users.username = 'guidegard'), 'Title1', 'Halden', 'Norge', 'Description1', 500, 250, 0, 'MeetPoint1', 10, 10)
                """);
            // Generate a UUID for all the lines
            prepStmt.setString(1, UUID.randomUUID().toString());
            prepStmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }
}
