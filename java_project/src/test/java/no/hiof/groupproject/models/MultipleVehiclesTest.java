package no.hiof.groupproject.models;

import no.hiof.groupproject.models.advertisements.Advertisement;
import no.hiof.groupproject.models.advertisements.RentOutAd;
import no.hiof.groupproject.models.vehicles.four_wheeled_vehicles.Car;
import no.hiof.groupproject.models.vehicles.Vehicle;
import no.hiof.groupproject.tools.db.ConnectDB;
import no.hiof.groupproject.tools.db.RetrieveUserDB;
import no.hiof.groupproject.tools.db.RetrieveUserProfileDB;
import no.hiof.groupproject.tools.db.RetrieveVehiclesDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MultipleVehiclesTest {

    @BeforeEach
    void initialiseDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/testable.db");
    }
    @AfterEach
    void rewindDatabasePath() {
        ConnectDB.setDb("jdbc:sqlite:sqlite/db/test.db");
    }

    @Test
    void assertsUserCanRegisterMoreThanOneVehicle() {

        User user = new User("jeff", "goldbum", "1777", "gfdgd",
                "12341234123", "dsfsd@hgh.no", "12341234",
                new License("98 41 123456 1", LocalDate.parse("2014-11-12"),
                        "Norway"));

        Vehicle car = new Car("99998888", "volkswagen", "e golf", "electric",
                "automatic", 2017, 5, 1400);

        Vehicle car2 = new Car("88887777", "ferrari", "f50", "petrol",
                "manual", 1997, 2, 800);

        RentOutAd roa = new RentOutAd(
                user,
                car,
                BigDecimal.valueOf(300), BigDecimal.valueOf(15), "moss"
        );

        RentOutAd roa2 = new RentOutAd(
                user,
                car2,
                BigDecimal.valueOf(800), BigDecimal.valueOf(65), "moss"
        );

        UserProfile up = RetrieveUserProfileDB.retrieve(user.getId());

        System.out.println(up.getAdvertisements());
        int count = 0;
        for (Advertisement advertisement : up.getAdvertisements()) {
            count++;
        }
        assertEquals(2, count);

    }

    @Test
    void assertsAllVehicleIdsBasedOnASpecificUserCanBeDeserialised() {

        //based on the user in the above test with several vehicles
        User user = RetrieveUserDB.retrieveFromEmail("dsfsd@hgh.no");
        assertNotNull(user);

        ArrayList<Integer> vehicles = RetrieveVehiclesDB.retrieveAllVehiclesIdLinkedToUser(user.getId());

        assertFalse(vehicles.isEmpty());
        assertTrue(vehicles.size() > 1);

    }

}