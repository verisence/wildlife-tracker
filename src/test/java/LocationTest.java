import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;
import java.util.Arrays;

public class LocationTest {

    @Before
    public void setUp()  {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "verisence", "pass");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sqlLocation = "DELETE FROM locations *;";
            con.createQuery(sqlLocation).executeUpdate();
        }
    }

    @Test
    public void Location_instantiatesCorrectly_true(){
        Location location = new Location("Donson");
        assertEquals(true, location instanceof Location);
    }
    @Test
    public void Location_instantiatesWithName_String() {
        Location location = new Location("Donson");
        assertEquals("Donson", location.getName());
    }
    @Test
    public void Location_instantiatesWithAnId() {
        Location location = new Location("Donson");
        location.save();
        assertTrue(location.getId()>0);
    }
    @Test
    public void save_savesCorrectly() {
        Location location = new Location("Donson");
        location.save();
        assertTrue(Location.all().get(0).equals(location));
    }
    @Test
    public void find_returnsLocationWIthSameID_secondLocation(){
        Location location = new Location("Donson");
        location.save();
        Location locationTwo = new Location("Kalius");
        locationTwo.save();
        assertEquals(Location.find(locationTwo.getId()), locationTwo);
    }
    @Test
    public void equals_returnsTrueIfLocationsAreSame(){
        Location location = new Location("Donson");
        Location locationTwo = new Location("Donson");
        assertTrue(location.equals(locationTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        Location location = new Location("Donson");
        location.save();
        assertEquals(Location.all().get(0), location);
    }
    @Test
    public void all_returnsAllInstancesOfLocations_true(){
        Location locationOne = new Location("Donson");
        locationOne.save();
        Location locationTwo = new Location("Kalius");
        locationTwo.save();
        assertEquals(Location.all().get(0), locationOne);
        assertEquals(Location.all().get(1), locationTwo);
    }
    @Test
    public void save_assignsIdToObject() {
        Location location = new Location("Donson");
        location.save();
        Location savedLocation = Location.all().get(0);
        assertEquals(location.getId(), savedLocation.getId());
    }
    @Test
    public void update_updateLocation_true(){
        Location location = new Location("Capstone");
        location.save();
        location.update("Capstone");
    }
    @Test
    public void delete_deletesLocation_true(){
        Location location = new Location("Donson");
        location.save();
        int locationId = location.getId();
        location.delete();
        assertEquals(null, Location.find(locationId));
    }
}
