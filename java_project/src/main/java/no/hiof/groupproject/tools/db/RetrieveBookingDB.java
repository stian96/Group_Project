package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.Booking;
import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.payment_methods.Payment;
import no.hiof.groupproject.models.vehicle_types.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

//Returns a specific TreeMap of all valid rental periods in the database based on a specific User

public class RetrieveBookingDB {

    public static Booking retrieve(String strId) {

        String sql = "SELECT * FROM bookings " +
                "WHERE bookings_id = \'" + strId + "\'";

        Booking booking = null;

        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {

            ResultSet queryResult = str.executeQuery();

            User renter = RetrieveUserDB.retrieveFromId(queryResult.getInt("renter_fk"));
            User owner = RetrieveUserDB.retrieveFromId(queryResult.getInt("owner_fk"));
            LocalDate bookedFrom = LocalDate.parse(queryResult.getString("bookedFrom"));
            LocalDate bookedTo = LocalDate.parse(queryResult.getString("bookedTo"));
            Payment payment = RetrievePaymentDB.retrieve(queryResult.getInt("payment_fk"));
            Vehicle vehicle = RetrieveVehicleDB.retrieveFromId(queryResult.getInt("vehicle_fk"));

            booking = new Booking(renter, owner, bookedFrom, bookedTo, payment, vehicle);
            booking.setStrId(strId);

            return booking;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booking;
    }

}