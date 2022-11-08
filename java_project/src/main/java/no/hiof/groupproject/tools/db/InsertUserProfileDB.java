package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
A class used to serialise a User into a database for permanent storage.
 */
public class InsertUserProfileDB {

    public static void insert(UserProfile userProfile) {

        String sql = "INSERT INTO userProfiles (user_fk, averageRating) " +
                "VALUES(?,?)";



        try (Connection conn = ConnectDB.connect();
             PreparedStatement str = conn.prepareStatement(sql)) {
            String avgRating = RetrieveAverageRatingDB.retrieve(userProfile);

            str.setInt(1, userProfile.getUser().getId());
            str.setString(2, avgRating);

            str.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}