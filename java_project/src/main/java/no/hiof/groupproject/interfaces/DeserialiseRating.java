package no.hiof.groupproject.interfaces;

import no.hiof.groupproject.models.User;
import no.hiof.groupproject.models.UserProfile;
import no.hiof.groupproject.tools.db.RetrieveAverageRatingDB;
import no.hiof.groupproject.tools.db.RetrieveRatingDB;

import java.util.HashMap;

public interface DeserialiseRating {
    //returns either an averageRating in String format or a HashMap of all ratings based on a specific userProfile
    static String deserialiseAverageRating(UserProfile userProfile) {
        return RetrieveAverageRatingDB.retrieve(userProfile);
    }
    static HashMap<User, Integer> deserialise(UserProfile userProfile) {
        return RetrieveRatingDB.retrieve(userProfile);
    }

}
