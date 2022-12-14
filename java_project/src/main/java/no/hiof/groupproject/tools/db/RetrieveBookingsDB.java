package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.advertisements.RentOutAd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//Returns a specific ArrayList of all confirmedBookings based on a specific RentOutAd

public class RetrieveBookingsDB {

    public static ArrayList<Booking> retrieve(RentOutAd rentOutAd) {

        String sql = "SELECT * FROM bookings " +
                "WHERE vehicle_fk = " + rentOutAd.getVehicle().getId();

        ArrayList<Booking> bookings = new ArrayList<>();

        try (Connection conn = ConnectDB.connectReadOnly();
             Statement str = conn.createStatement();
             ResultSet rs = str.executeQuery(sql)) {

            //loops through rows in the sql SELECT statement
            while (rs.next()) {
                Booking booking = RetrieveBookingDB.retrieve(rs.getString("bookings_id"));
                bookings.add(booking);
            }
            return bookings;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookings;
    }

   /* public static ArrayList<Booking> retrieveWithPriorConnection(RentOutAd rentOutAd, Connection conn) throws SQLException {

        String sql = "SELECT * FROM bookings " +
                "WHERE vehicle_fk = " + rentOutAd.getVehicle().getId();

        ArrayList<Booking> bookings = new ArrayList<>();

         Statement str = conn.createStatement();
         ResultSet rs = str.executeQuery(sql);

        //loops through rows in the sql SELECT statement
        while (rs.next()) {
            Booking booking = RetrieveBookingDB.retrieve(rs.getString("bookings_id"));
            bookings.add(booking);
        }
        return bookings;
    }*/

}