import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class EndangeredEndangeredAnimalTest {

    @Before
    public void setUp()  {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "verisence", "pass");
    }

    @After
    public void tearDown() {
        try(Connection con = DB.sql2o.open()) {
            String sqlEndangeredAnimal = "DELETE FROM endangeredAnimals *;";
            con.createQuery(sqlEndangeredAnimal).executeUpdate();
        }
    }

    @Test
    public void EndangeredAnimal_instantiatesCorrectly_true(){
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        assertEquals(true, endangeredAnimal instanceof EndangeredAnimal);
    }
    @Test
    public void EndangeredAnimal_instantiatesWithName_String() {
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        assertEquals("Chui", endangeredAnimal.getName());
    }
    @Test
    public void EndangeredAnimal_instantiatesWithType_String() {
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        assertEquals("endangeredAnimal", endangeredAnimal.getType());
    }
    @Test
    public void EndangeredAnimal_instantiatesWithAnId() {
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        endangeredAnimal.save();
        assertTrue(endangeredAnimal.getId()>0);
    }
    @Test
    public void save_savesCorrectly() {
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        endangeredAnimal.save();
        assertTrue(EndangeredAnimal.all().get(0).equals(endangeredAnimal));
    }
    @Test
    public void find_returnsEndangeredAnimalWIthSameID_secondEndangeredAnimal(){
        EndangeredAnimal endangeredAnimalOne = new  EndangeredAnimal("Chui");
        endangeredAnimalOne.save();
        EndangeredAnimal endangeredAnimalTwo = new EndangeredAnimal("Impalla");
        endangeredAnimalTwo.save();
        assertEquals(EndangeredAnimal.find(endangeredAnimalTwo.getId()), endangeredAnimalTwo);
    }
    @Test
    public void equals_returnsTrueIfEndangeredAnimalsAreSame(){
        EndangeredAnimal endangeredAnimalOne = new  EndangeredAnimal("Chui");
        EndangeredAnimal endangeredAnimalTwo = new EndangeredAnimal("Chui");
        assertTrue(endangeredAnimalOne.equals(endangeredAnimalTwo));
    }
    @Test
    public void save_returnsTrueIfNamesAreTheSame(){
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        endangeredAnimal.save();
        assertEquals(EndangeredAnimal.all().get(0), endangeredAnimal);
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimals_true(){
        EndangeredAnimal endangeredAnimalOne = new  EndangeredAnimal("Chui");
        endangeredAnimalOne.save();
        EndangeredAnimal endangeredAnimalTwo = new EndangeredAnimal("Impala");
        endangeredAnimalTwo.save();
        assertEquals(EndangeredAnimal.all().get(0), endangeredAnimalOne);
        assertEquals(EndangeredAnimal.all().get(1), endangeredAnimalTwo);
    }
    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        endangeredAnimal.save();
        EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
        assertEquals(endangeredAnimal.getId(), savedEndangeredAnimal.getId());
    }
    @Test
    public void update_updateEndangeredAnimal_true(){
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        endangeredAnimal.save();
        endangeredAnimal.update("Impala");
    }
    @Test
    public void delete_deletesEndangeredAnimal_true(){
        EndangeredAnimal endangeredAnimal = new EndangeredAnimal("Chui");
        endangeredAnimal.save();
        int endangeredAnimalId = endangeredAnimal.getId();
        endangeredAnimal.delete();
        assertEquals(null, EndangeredAnimal.find(endangeredAnimalId));
    }
}