package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.advertisements.Advertisement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/*
A class used to serialise an ArrayWithin date available to book into a database for permanent storage.
IMPORTANT: SQLite table 'availableWithin' has a multiple column constraint to prevent the same periods being added
 */
public class InsertAvailableWithinDB {

    public static void insert(Advertisement advertisement, LocalDate dateFrom, LocalDate dateTo) {

        String sql = "INSERT INTO availableWithin (availableWithin_id_fk, dateFrom, dateTo)" +
                "VALUES(?,?,?)";

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            str.setInt(1, advertisement.getId());
            str.setString(2, dateFrom.toString());
            str.setString(3, dateTo.toString());
            str.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*public static void insertWithPriorConnection(Advertisement advertisement, LocalDate dateFrom, LocalDate dateTo, Connection conn) throws SQLException {

        String sql = "INSERT INTO availableWithin (availableWithin_id_fk, dateFrom, dateTo)" +
                "VALUES(?,?,?)";

        PreparedStatement str = conn.prepareStatement(sql);

        str.setInt(1, advertisement.getId());
        str.setString(2, dateFrom.toString());
        str.setString(3, dateTo.toString());
        str.executeUpdate();

    }*/
}