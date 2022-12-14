package no.hiof.groupproject.tools.db;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;

import java.util.HashMap;

/*
Returns a specific UserProfile in the database based on the User id
*/
public class RetrieveUserProfileDB {

    public static UserProfile retrieve(int id) {

        //String sql = "SELECT * FROM userProfiles WHERE user_fk= " + id;

        UserProfile returnedUserProfile = null;

        User user = RetrieveUserDB.retrieveFromId(id);
        returnedUserProfile = new UserProfile(user);
        HashMap<User, Integer> ratings = RetrieveRatingDB.retrieve(returnedUserProfile);
        if (!ratings.isEmpty()) {
            returnedUserProfile.setRatings(ratings);
            returnedUserProfile.setAverageRating(Double.parseDouble(RetrieveAverageRatingDB.retrieve(returnedUserProfile)));
        }
        returnedUserProfile.setAdvertisements(RetrieveAdvertisementsDB.retrieveAdvertisementsObjectFromUserId(user.getId()));
        /*
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         */

        return returnedUserProfile;
    }

    public static UserProfile retrieveFromEmail(String email) {

        //String sql = "SELECT * FROM userProfiles INNER JOIN users ON user_fk = users_id " +
        //        "WHERE email = \'" + email + "\'";

        UserProfile returnedUserProfile = null;

        User user = RetrieveUserDB.retrieveFromEmail(email);
        returnedUserProfile = new UserProfile(user);
        HashMap<User, Integer> ratings = RetrieveRatingDB.retrieve(returnedUserProfile);
        if (!ratings.isEmpty()) {
            returnedUserProfile.setRatings(ratings);
            returnedUserProfile.setAverageRating(Double.parseDouble(RetrieveAverageRatingDB.retrieve(returnedUserProfile)));
        }
        returnedUserProfile.setAdvertisements(RetrieveAdvertisementsDB.retrieveAdvertisementsObjectFromUserId(user.getId()));
        /*
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         */
        return returnedUserProfile;
    }
/*

    public static UserProfile retrieveWithPriorConnection(int id, Connection conn) throws SQLException {

        //String sql = "SELECT * FROM userProfiles WHERE user_fk= " + id;

        UserProfile returnedUserProfile = null;

        User user = RetrieveUserDB.retrieveFromId(id);
        returnedUserProfile = new UserProfile(user);
        HashMap<User, Integer> ratings = RetrieveRatingDB.retrieve(returnedUserProfile);
        if (!ratings.isEmpty()) {
            returnedUserProfile.setRatings(ratings);
            returnedUserProfile.setAverageRating(
                    Double.parseDouble(RetrieveAverageRatingDB.retrieve(returnedUserProfile)));
        }
        returnedUserProfile.setAdvertisements(RetrieveAdvertisementsDB.retrieveAdvertisementObjectFromId(user.getId()));
        */
/*
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         *//*


        return returnedUserProfile;
    }

    public static UserProfile retrieveFromEmailWithPriorConnection(String email, Connection conn) throws SQLException {

        //String sql = "SELECT * FROM userProfiles INNER JOIN users ON user_fk = users_id " +
        //        "WHERE email = \'" + email + "\'";

        UserProfile returnedUserProfile = null;

        User user = RetrieveUserDB.retrieveFromEmail(email);
        returnedUserProfile = new UserProfile(user);
        HashMap<User, Integer> ratings = RetrieveRatingDB.retrieve(returnedUserProfile);
        if (!ratings.isEmpty()) {
            returnedUserProfile.setRatings(ratings);
            returnedUserProfile.setAverageRating(
                    Double.parseDouble(RetrieveAverageRatingDB.retrieve(returnedUserProfile)));
        }
        returnedUserProfile.setAdvertisements(RetrieveAdvertisementsDB.retrieveAdvertisementObjectFromId(user.getId()));
        */
/*
        try (Connection conn = ConnectDB.connectReadOnly();
             PreparedStatement str = conn.prepareStatement(sql)) {

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         *//*

        return returnedUserProfile;
    }
*/

}
