package no.hiof.groupproject.models;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    UserProfile userProfile = new UserProfile();
    private HashMap<User, Integer> ratings = new HashMap<>();
    User user = new User(1,"Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272");
    User user1 = new User(2,"Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272");
    User user2 = new User(1,"Jillian","Andes","1526","jack1",
            "10333922","gigi@gmail.com","6256728272");

    @Test
    public void check_calculate_average_works(){
        ratings.put(user,1);
        ratings.put(user1,5);
        ratings.put(user2,3);
        double val = userProfile.calculateAverageRating(ratings);
        assertEquals(3.0, val);

    }

}